/*
* AMRIT – Accessible Medical Records via Integrated Technology
* Integrated EHR (Electronic Health Records) Solution
*
* Copyright (C) "Piramal Swasthya Management and Research Institute"
*
* This file is part of AMRIT.
*
* This program is free software: you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation, either version 3 of the License, or
* (at your option) any later version.
*
* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with this program.  If not, see https://www.gnu.org/licenses/.
*/
package com.iemr.ecd.service.quality;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.iemr.ecd.dao.GradeConfiguration;
import com.iemr.ecd.dao.QualityAuditorCallResponse;
import com.iemr.ecd.dao.QualityAuditorRating;
import com.iemr.ecd.dao.SampleSelectionConfiguration;
import com.iemr.ecd.dao.V_get_Qualityaudit_SectionQuestionaireValues;
import com.iemr.ecd.dao.associate.Bencall;
import com.iemr.ecd.dto.BeneficiaryCasesheetDTO;
import com.iemr.ecd.dto.BeneficiaryCasesheetQuestionnaireDTO;
import com.iemr.ecd.dto.QualityAuditorWorklistDatewiseRequestDTO;
import com.iemr.ecd.dto.QualityAuditorWorklistDatewiseResponseDTO;
import com.iemr.ecd.dto.QualityAuditorWorklistRequestDTO;
import com.iemr.ecd.dto.QualityAuditorWorklistResponseDTO;
import com.iemr.ecd.dto.QualityQuestionareOptionsDTO;
import com.iemr.ecd.dto.ResponseCallAuditSectionQuestionMapDTO;
import com.iemr.ecd.repo.call_conf_allocation.GradeConfigurationRepo;
import com.iemr.ecd.repo.call_conf_allocation.SampleSelectionConfigurationRepo;
import com.iemr.ecd.repository.quality.AgentQualityAuditorMapRepo;
import com.iemr.ecd.repository.quality.QualityAuditorCallResponseRepo;
import com.iemr.ecd.repository.quality.QualityAuditorRatingRepo;
import com.iemr.ecd.repository.quality.T_benCallRepo;
import com.iemr.ecd.repository.quality.V_QualityAuditorCallResponseRepo;
import com.iemr.ecd.repository.quality.V_get_Qualityaudit_SectionQuestionaireValuesRepo;
import com.iemr.ecd.utils.advice.exception_handler.ECDException;
import com.iemr.ecd.utils.advice.exception_handler.InvalidRequestException;
import com.iemr.ecd.utils.mapper.InputMapper;

@Service
public class QualityAuditImpl {

	@Autowired
	private AgentQualityAuditorMapRepo agentQualityAuditorMapRepo;
	@Autowired
	private SampleSelectionConfigurationRepo sampleSelectionConfigurationRepo;
	@Autowired
	private QualityAuditorRatingRepo qualityAuditorRatingRepo;
	@Autowired
	private QualityAuditorCallResponseRepo qualityAuditorCallResponseRepo;

	@Autowired
	private T_benCallRepo t_benCallRepo;

	@Autowired
	GradeConfigurationRepo gradeConfigurationRepo;

	@Autowired
	private V_get_Qualityaudit_SectionQuestionaireValuesRepo v_get_Qualityaudit_SectionQuestionaireValuesRepo;
	@Autowired
	private V_QualityAuditorCallResponseRepo v_QualityAuditorCallResponseRepo;

	public List<QualityAuditorWorklistResponseDTO> getQualityAuditorWorklist(
			QualityAuditorWorklistRequestDTO qualityAuditorWorklistRequestDTO) {
		List<QualityAuditorWorklistResponseDTO> responseList = new ArrayList<>();
		try {

			QualityAuditorWorklistResponseDTO response;
			Integer preDate = null;
			Integer preFromDay = null;
			Integer preToDay = null;
			SampleSelectionConfiguration sampleSelectionConfiguration = null;
			if (null != qualityAuditorWorklistRequestDTO.getCycleId()) {
				List<Object[]> dates = sampleSelectionConfigurationRepo
						.getdate(qualityAuditorWorklistRequestDTO.getCycleId(), qualityAuditorWorklistRequestDTO.getPsmId());
				Object[] obj = dates.get(0);
				
				Integer fromDay = (Integer)obj[0];
				Integer toDay = (Integer)obj[1];
				if (null != fromDay && fromDay == 1) {
					preDate = 31;
					sampleSelectionConfiguration = sampleSelectionConfigurationRepo
							.getSampleSelectionConfiguration(preDate);
					if(sampleSelectionConfiguration == null) {
						List<SampleSelectionConfiguration> resultSet = sampleSelectionConfigurationRepo.getSampleSize(preDate);
						sampleSelectionConfiguration = resultSet.get(resultSet.size() - 1);
					}
					preFromDay = sampleSelectionConfiguration.getFromDay();
					preToDay = sampleSelectionConfiguration.getToDay();
					qualityAuditorWorklistRequestDTO.setPrevCycleFromDate(getTimeStampFromDateValue(qualityAuditorWorklistRequestDTO,preFromDay,1));
					qualityAuditorWorklistRequestDTO.setPrevCycleToDate(getTimeStampToDateValue(qualityAuditorWorklistRequestDTO,preToDay,1));
					qualityAuditorWorklistRequestDTO.setFromDate(getTimeStampFromDate(qualityAuditorWorklistRequestDTO, fromDay));
					qualityAuditorWorklistRequestDTO.setToDate(getTimeStampToDate(qualityAuditorWorklistRequestDTO,toDay));
					//calling SP to get work-list data
					responseList = getWorkListData(qualityAuditorWorklistRequestDTO);

				} else if (null != fromDay && fromDay != 1) {
					preDate = fromDay - 1;
					sampleSelectionConfiguration = sampleSelectionConfigurationRepo
							.getSampleSelectionConfiguration(preDate);
					preFromDay = sampleSelectionConfiguration.getFromDay();
					preToDay = sampleSelectionConfiguration.getToDay();
					qualityAuditorWorklistRequestDTO.setPrevCycleFromDate(getTimeStampFromDateValue(qualityAuditorWorklistRequestDTO,preFromDay,0));
					qualityAuditorWorklistRequestDTO.setPrevCycleToDate(getTimeStampToDateValue(qualityAuditorWorklistRequestDTO,preToDay,0));
					qualityAuditorWorklistRequestDTO.setFromDate(getTimeStampFromDate(qualityAuditorWorklistRequestDTO, fromDay));
					qualityAuditorWorklistRequestDTO.setToDate(getTimeStampToDate(qualityAuditorWorklistRequestDTO,toDay));
					//calling SP to get work-list data
					responseList = getWorkListData(qualityAuditorWorklistRequestDTO);
				}
			}

		} catch (Exception e) {
			throw new ECDException(e);
		}
		return responseList;
	}

	private Timestamp getTimeStampFromDateValue(QualityAuditorWorklistRequestDTO qualityAuditorWorklistRequestDTO,
			Integer preDay, Integer minusMonth) {
		Timestamp date = null;
		try {
			Calendar call = Calendar.getInstance();
			if(qualityAuditorWorklistRequestDTO.getMonth() == 1) {
				call.set(Calendar.YEAR, qualityAuditorWorklistRequestDTO.getYear() -0);
			}else {
				call.set(Calendar.YEAR, qualityAuditorWorklistRequestDTO.getYear());
			}
			call.set(Calendar.MONTH, qualityAuditorWorklistRequestDTO.getMonth() - minusMonth - 1);
			call.set(Calendar.DATE, preDay);
			call.set(Calendar.HOUR_OF_DAY, 0);
			call.set(Calendar.MINUTE, 0);
			call.set(Calendar.SECOND, 0);
			call.set(Calendar.MILLISECOND, 0);
			date = new Timestamp(call.getTimeInMillis());
			
		} catch (Exception e) {
			throw new ECDException(e);
		}
		return date;

	}

	private Timestamp getTimeStampToDateValue(QualityAuditorWorklistRequestDTO qualityAuditorWorklistRequestDTO,
			Integer preDay, Integer minusMonth) {
		Timestamp date = null;
		try {
			Calendar cal2 = Calendar.getInstance();
			if(qualityAuditorWorklistRequestDTO.getMonth() ==1) {
				cal2.set(Calendar.YEAR, qualityAuditorWorklistRequestDTO.getYear() -0);
			}else {
				cal2.set(Calendar.YEAR, qualityAuditorWorklistRequestDTO.getYear());
			}
			cal2.set(Calendar.MONTH, qualityAuditorWorklistRequestDTO.getMonth() - minusMonth -1);
			cal2.set(Calendar.DATE, preDay);
			cal2.set(Calendar.HOUR_OF_DAY, 23);
			cal2.set(Calendar.MINUTE, 59);
			cal2.set(Calendar.SECOND, 59);
			cal2.set(Calendar.MILLISECOND, 59);
			date = new Timestamp(cal2.getTimeInMillis());
		} catch (Exception e) {
			throw new ECDException(e);
		}
		return date;

	}
	
	private Timestamp getTimeStampFromDate(QualityAuditorWorklistRequestDTO qualityAuditorWorklistRequestDTO,
			Integer preDay) {
		Timestamp date = null;
		try {
			Calendar call = Calendar.getInstance();
			call.set(Calendar.YEAR, qualityAuditorWorklistRequestDTO.getYear());
			call.set(Calendar.MONTH, qualityAuditorWorklistRequestDTO.getMonth() - 1);
			call.set(Calendar.DATE, preDay);
			call.set(Calendar.HOUR_OF_DAY, 0);
			call.set(Calendar.MINUTE, 0);
			call.set(Calendar.SECOND, 0);
			call.set(Calendar.MILLISECOND, 0);
			date = new Timestamp(call.getTimeInMillis());
			
		} catch (Exception e) {
			throw new ECDException(e);
		}
		return date;

	}

	private Timestamp getTimeStampToDate(QualityAuditorWorklistRequestDTO qualityAuditorWorklistRequestDTO,
			Integer preDay) {
		Timestamp date = null;
		try {
			Calendar cal2 = Calendar.getInstance();
			cal2.set(Calendar.YEAR, qualityAuditorWorklistRequestDTO.getYear());
			cal2.set(Calendar.MONTH, qualityAuditorWorklistRequestDTO.getMonth() -1);
			cal2.set(Calendar.DATE, preDay);
			cal2.set(Calendar.HOUR_OF_DAY, 23);
			cal2.set(Calendar.MINUTE, 59);
			cal2.set(Calendar.SECOND, 59);
			cal2.set(Calendar.MILLISECOND, 59);
			date = new Timestamp(cal2.getTimeInMillis());
		} catch (Exception e) {
			throw new ECDException(e);
		}
		return date;

	}
	
	public List<QualityAuditorWorklistResponseDTO> getWorkListData(
			QualityAuditorWorklistRequestDTO qualityAuditorWorklistRequestDTO) {
		List<QualityAuditorWorklistResponseDTO> responseList = new ArrayList<>();
		QualityAuditorWorklistResponseDTO response;
		List<String[]> resultSet = agentQualityAuditorMapRepo.getQualityAuditorWorklist(
				qualityAuditorWorklistRequestDTO.getFromDate(), qualityAuditorWorklistRequestDTO.getToDate(),
				qualityAuditorWorklistRequestDTO.getPsmId(), qualityAuditorWorklistRequestDTO.getLanguageId(),
				qualityAuditorWorklistRequestDTO.getAgentId(), qualityAuditorWorklistRequestDTO.getRoleId(),
				qualityAuditorWorklistRequestDTO.getIsValid(), qualityAuditorWorklistRequestDTO.getCycleId(),
				qualityAuditorWorklistRequestDTO.getPrevCycleFromDate(),
				qualityAuditorWorklistRequestDTO.getPrevCycleToDate());

		if (resultSet != null && resultSet.size() > 0) {
			for (String[] strings : resultSet) {
				response = new QualityAuditorWorklistResponseDTO();
				if (strings[0] != null)
					response.setBeneficiaryid(Long.valueOf(strings[0]));
				if (strings[1] != null)
					response.setBeneficiaryname(strings[1]);
				if (strings[2] != null)
					response.setPhoneNo(strings[2]);
				if (strings[3] != null)
					response.setAgentid(Integer.valueOf(strings[3]));
				if (strings[4] != null)
					response.setAgetname(strings[4]);
				if (strings[5] != null)
					response.setCalltype(strings[5]);
				if (strings[6] != null)
					response.setBenCallID(Long.valueOf(strings[6]));
				if (strings[7] != null)
					response.setIsCallAudited(Boolean.valueOf(strings[7]));
				if (strings[8] != null)
					response.setOutboundCallType(strings[8]);
				if (strings[9] != null)
					response.setRoleID(Integer.valueOf(strings[9]));
				if (strings[10] != null)
					response.setRoleName(strings[10]);
				if (strings[11] != null)
					response.setCallId(strings[11]);

				responseList.add(response);
			}
		}

		return responseList;

	}


	

	//Method to fetch call audit data datewise
		public List<QualityAuditorWorklistDatewiseResponseDTO> getQualityAuditorWorklistDatewise(
				QualityAuditorWorklistDatewiseRequestDTO qualityAuditorWorklistDatewiseRequestDTO) {

			try {
				List<QualityAuditorWorklistDatewiseResponseDTO> responseList = new ArrayList<>();
				QualityAuditorWorklistDatewiseResponseDTO response;

				// create from-date & to-date from cycle info
				if (qualityAuditorWorklistDatewiseRequestDTO.getValidFrom()!=null && qualityAuditorWorklistDatewiseRequestDTO.getValidTill()!= null) {
					List<SampleSelectionConfiguration> resultList = sampleSelectionConfigurationRepo
							.findByPsmIdOrderByLastModDateDesc(qualityAuditorWorklistDatewiseRequestDTO.getPsmId());

					if (resultList != null && resultList.size() > 0) {
						SampleSelectionConfiguration obj = resultList.get(0);
					} else
						throw new ECDException(
								"given cycle is not available for the provider, please contact administrator");
				} else
					throw new InvalidRequestException("invalid/NULL cycle id : ", "please pass cycle info to get the data");

				// call SP to get work-list data
				List<String[]> resultSet = agentQualityAuditorMapRepo.getQualityAuditorWorklistDatewise(
						qualityAuditorWorklistDatewiseRequestDTO.getValidFrom(), qualityAuditorWorklistDatewiseRequestDTO.getValidTill(),
						qualityAuditorWorklistDatewiseRequestDTO.getPsmId(), qualityAuditorWorklistDatewiseRequestDTO.getLanguageId(),
						qualityAuditorWorklistDatewiseRequestDTO.getAgentId(), qualityAuditorWorklistDatewiseRequestDTO.getRoleId(),
						qualityAuditorWorklistDatewiseRequestDTO.isValid());
						

				if (resultSet != null && resultSet.size() > 0) {
					for (String[] strings : resultSet) {
						response = new QualityAuditorWorklistDatewiseResponseDTO();
						if (strings[0] != null)
							response.setBeneficiaryid(Long.valueOf(strings[0]));
						if (strings[1] != null)
							response.setBeneficiaryname(strings[1]);
						if (strings[2] != null)
							response.setPhoneNo(strings[2]);
						if (strings[3] != null)
							response.setAgentid(Integer.valueOf(strings[3]));
						if (strings[4] != null)
							response.setAgetname(strings[4]);
						if (strings[5] != null)
							response.setCalltype(strings[5]);
						if (strings[6] != null)
							response.setBenCallID(Long.valueOf(strings[6]));
						if (strings[7] != null)
							response.setIsCallAudited(Boolean.valueOf(strings[7]));
						if (strings[8] != null)
							response.setOutboundCallType(strings[8]);
						if (strings[9] != null)
							response.setRoleID(Integer.valueOf(strings[9]));
						if (strings[10] != null)
							response.setRoleName(strings[10]);
						if (strings[11] != null)
							response.setCallId(strings[11]);

						responseList.add(response);
					}
				}

				return responseList;

			} catch (Exception e) {
				throw new ECDException(e);
			}
		}
	public List<ResponseCallAuditSectionQuestionMapDTO> getQuestionSectionForCallRatings(Integer psmId) {
		try {

			ResponseCallAuditSectionQuestionMapDTO responseDTO;
			List<ResponseCallAuditSectionQuestionMapDTO> responseDTOList = new ArrayList<>();

			QualityQuestionareOptionsDTO option;
			List<QualityQuestionareOptionsDTO> options;

			List<V_get_Qualityaudit_SectionQuestionaireValues> resultList = v_get_Qualityaudit_SectionQuestionaireValuesRepo
					.findByPsmIdOrderByQuestionId(psmId);

			int qId = 0;
			if (resultList != null && resultList.size() > 0) {
				for (V_get_Qualityaudit_SectionQuestionaireValues obj : resultList) {
					if (qId == 0 || qId != obj.getQuestionId()) {
						responseDTO = new ResponseCallAuditSectionQuestionMapDTO();
						if(obj.getRole() != null && !obj.getRole().isEmpty()) {
							responseDTO.setRoles(Arrays.asList(obj.getRole().split(",")));
						}
						responseDTO.setSectionId(obj.getSectionId());
						if (obj.getSectionName() != null)
							responseDTO.setSectionName(obj.getSectionName());
						if (obj.getSectionRank() != null)
							responseDTO.setSectionRank(obj.getSectionRank());

						responseDTO.setQuestionId(obj.getQuestionId());
						if (obj.getQuestion() != null)
							responseDTO.setQuestion(obj.getQuestion());
						if (obj.getQuestionRank() != null)
							responseDTO.setQuestionRank(obj.getQuestionRank());
						responseDTO.setIsFatalQues(obj.getIsFatalQues());

						option = new QualityQuestionareOptionsDTO();
						options = new ArrayList<>();

						option.setId(obj.getOptionId());
						if (obj.getQuestionValues() != null)
							option.setOption(obj.getQuestionValues());
						if (obj.getScore() != null)
							option.setScore(obj.getScore());

						options.add(option);

						responseDTO.setOptions(options);

						responseDTOList.add(responseDTO);

						qId = obj.getQuestionId();

					} else {
						option = new QualityQuestionareOptionsDTO();

						option.setId(obj.getOptionId());
						if (obj.getQuestionValues() != null)
							option.setOption(obj.getQuestionValues());
						if (obj.getScore() != null)
							option.setScore(obj.getScore());

						List<QualityQuestionareOptionsDTO> optionsTemp = responseDTOList.get(responseDTOList.size() - 1)
								.getOptions();

						if (optionsTemp != null) {
							optionsTemp.add(option);

							responseDTOList.get(responseDTOList.size() - 1).setOptions(optionsTemp);
						}

					}
				}
			}

			return responseDTOList;
		} catch (Exception e) {
			throw new ECDException(e);
		}
	}

	public List<GradeConfiguration> getQualityAuditGrades(Integer psmId) {
		try {
			return gradeConfigurationRepo.findByPsmIdAndDeleted(psmId, false);
		} catch (Exception e) {
			throw new ECDException(e);
		}

	}

	public String saveCallQualityRatings(String requestOBJ) {
		try {
			List<QualityAuditorRating> qualityAuditorRatingList = new ArrayList<>();

			List<QualityAuditorCallResponse> qualityAuditorCallResponseList = new ArrayList<>();

			if (requestOBJ != null) {

				// ratings
				QualityAuditorRating[] qualityAuditorRatingArr = InputMapper.gson().fromJson(requestOBJ,
						QualityAuditorRating[].class);
				qualityAuditorRatingList = Arrays.asList(qualityAuditorRatingArr);

				// response
				QualityAuditorCallResponse[] qualityAuditorCallResponseArr = InputMapper.gson().fromJson(requestOBJ,
						QualityAuditorCallResponse[].class);

				qualityAuditorCallResponseList = Arrays.asList(qualityAuditorCallResponseArr);
			} else
				throw new InvalidRequestException("request : " + requestOBJ,
						"Invalid/NULL request, please pass correct data");

			if (qualityAuditorRatingList.size() > 0)
				qualityAuditorRatingRepo.save(qualityAuditorRatingList.get(0));

			if (qualityAuditorCallResponseList.size() > 0)
				qualityAuditorCallResponseRepo.saveAll(qualityAuditorCallResponseList);

			// update in t_bencall - call review status - boolean
			if (qualityAuditorRatingList.size() > 0) {
				Optional<Bencall> bencall = t_benCallRepo.findById(qualityAuditorRatingList.get(0).getBenCallId());
				if (bencall != null && bencall.get() != null) {
					Bencall tempBencall = bencall.get();
					tempBencall.setIsCallAudited(true);
					tempBencall.setIsZeroCall(qualityAuditorRatingList.get(0).getIsZeroCall());
					t_benCallRepo.save(tempBencall);
				}

			}

			Map<String, Object> responseMap = new HashMap<>();
			responseMap.put("response", "data saved successfully");
			return new Gson().toJson(responseMap);
		} catch (Exception e) {
			throw new ECDException(e);
		}

	}

	public String getCallQualityRatings(Long benCallId) {
		try {
			Map<String, Object> responseMap = new HashMap<>();
			responseMap.put("qualityRating", qualityAuditorRatingRepo.findByBenCallId(benCallId));
			responseMap.put("qualityQuestionResponse", v_QualityAuditorCallResponseRepo.findByBenCallId(benCallId));
			return new Gson().toJson(responseMap);
		} catch (Exception e) {
			throw new ECDException(e);
		}
	}

	public String callReaudit(JsonObject requestOBJ) {
		try {

			if (requestOBJ != null && requestOBJ.has("qualityQuestionResponse")
					&& !requestOBJ.get("qualityQuestionResponse").isJsonNull()) {
				QualityAuditorCallResponse[] qualityAuditorCallResponseArr = InputMapper.gson().fromJson(
						requestOBJ.getAsJsonObject().getAsJsonArray("qualityQuestionResponse"),
						QualityAuditorCallResponse[].class);

				if (qualityAuditorCallResponseArr.length > 0)
					qualityAuditorCallResponseRepo.saveAll(Arrays.asList(qualityAuditorCallResponseArr));

			}
			if (requestOBJ != null && requestOBJ.has("qualityRating")
					&& !requestOBJ.get("qualityRating").isJsonNull()) {
				QualityAuditorRating qualityAuditorRating = InputMapper.gson()
						.fromJson(requestOBJ.getAsJsonObject("qualityRating"), QualityAuditorRating.class);
				if (qualityAuditorRating != null && qualityAuditorRating.getId() != null)
					qualityAuditorRatingRepo.save(qualityAuditorRating);
			}
			Map<String, Object> responseMap = new HashMap<>();
			responseMap.put("response", "call reaudited successfully");
			return new Gson().toJson(responseMap);
		} catch (Exception e) {
			throw new ECDException(e);
		}

	}

	public String getBeneficiaryCasesheet(BeneficiaryCasesheetDTO request) {

		try {
			List<BeneficiaryCasesheetQuestionnaireDTO> questionnaireResponseList = new ArrayList<>();
			BeneficiaryCasesheetDTO resultSet = new BeneficiaryCasesheetDTO();
			BeneficiaryCasesheetQuestionnaireDTO response;

			Map<String, Object> responseMap = new HashMap<>();
			if (request.getBenCallId() != null) {

				// call SP to get beneficiary data and call closure details
				List<String[]> casesheetResp = agentQualityAuditorMapRepo
						.getBeneficiaryCasesheet(request.getBenCallId());

				if (casesheetResp != null && casesheetResp.size() == 1) {

					String[] benDetails = casesheetResp.get(0);
					if (benDetails[0] != null)
						resultSet.setBeneficiaryRegId(Long.valueOf(benDetails[0]));
					if (benDetails[1] != null)
						resultSet.setBenCallId(Long.valueOf(benDetails[1]));
					if (benDetails[2] != null)
						resultSet.setMotherId(Long.valueOf(benDetails[2]));
					;
					if (benDetails[3] != null)
						resultSet.setChildId(Long.valueOf(benDetails[3]));
					if (benDetails[4] != null)
						resultSet.setBeneficiaryName(benDetails[4]);
					if (benDetails[5] != null)
						resultSet.setMotherName(benDetails[5]);
					if (benDetails[6] != null)
						resultSet.setSpouseName(benDetails[6]);
					if (benDetails[7] != null)
						resultSet.setGenderID(Integer.valueOf(benDetails[7]));
					if (benDetails[8] != null)
						resultSet.setGenderName(benDetails[8]);
					if (benDetails[9] != null)
						resultSet.setPhoneNo(benDetails[9]);
					if (benDetails[10] != null)
						resultSet.setPhoneNoOfWhom(benDetails[10]);
					if (benDetails[11] != null)
						resultSet.setAlternatePhoneNo(benDetails[11]);
					if (benDetails[12] != null)
						resultSet.setDateOfBirth(benDetails[12]);
					if (benDetails[13] != null)
						resultSet.setLmp(benDetails[13]);
					if (benDetails[14] != null)
						resultSet.setEdd(benDetails[14]);
					if (benDetails[15] != null) {
						int newAge = Integer.parseInt(String.valueOf(benDetails[15]).split("\\.")[0]);
						resultSet.setAge(newAge);
					}
					if (benDetails[16] != null)
						resultSet.setAddress(benDetails[16]);
					if (benDetails[17] != null)
						resultSet.setAshaName(benDetails[17]);
					if (benDetails[18] != null)
						resultSet.setAshaPh(benDetails[18]);
					if (benDetails[19] != null)
						resultSet.setAnmName(benDetails[19]);
					if (benDetails[20] != null)
						resultSet.setAnmPh(benDetails[20]);
					if (benDetails[21] != null)
						resultSet.setPhcName(benDetails[21]);
					if (benDetails[22] != null)
						resultSet.setBlockName(benDetails[22]);
					if (benDetails[23] != null)
						resultSet.setOutboundCallType(benDetails[23]);
					if (benDetails[24] != null)
						resultSet.setIsFurtherCallRequired(Boolean.valueOf(benDetails[24]));
					if (benDetails[25] != null)
						resultSet.setReasonForNoFurtherCalls(benDetails[25]);
					if (benDetails[26] != null)
						resultSet.setIsCallVerified(Boolean.valueOf(benDetails[26]));
					if (benDetails[27] != null)
						resultSet.setIsCallAnswered(Boolean.valueOf(benDetails[27]));
					if (benDetails[28] != null)
						resultSet.setReasonForCallNotAnswered(benDetails[28]);
					if (benDetails[29] != null)
						resultSet.setIsCallDisconnected(Boolean.valueOf(benDetails[29]));
					if (benDetails[30] != null)
						resultSet.setTypeOfComplaint(benDetails[30]);
					if (benDetails[31] != null)
						resultSet.setComplaintRemarks(benDetails[31]);
					if (benDetails[32] != null)
						resultSet.setNextAttemptDate(benDetails[32]);
					if (benDetails[33] != null)
						resultSet.setCallRemarks(benDetails[33]);
					if (benDetails[34] != null)
						resultSet.setSendAdvice(benDetails[34]);
					if (benDetails[35] != null)
						resultSet.setIsWrongNumber(Boolean.valueOf(benDetails[35]));
					if (benDetails[36] != null)
						resultSet.setProviderServiceMapID(Integer.valueOf(benDetails[36]));
					if (benDetails[37] != null)
						resultSet.setCreatedDate(benDetails[37]);

					responseMap.put("beneficiaryDetails", resultSet);
				}

				// call SP to get beneficiary questionnaire response
				List<String[]> questionnaireResultSet = agentQualityAuditorMapRepo
						.getBeneficiaryCallResponse(request.getBenCallId());

				if (questionnaireResultSet != null && questionnaireResultSet.size() > 0) {
					for (String[] strings : questionnaireResultSet) {
						response = new BeneficiaryCasesheetQuestionnaireDTO();
						if (strings[0] != null)
							response.setSectionId(Integer.valueOf(strings[0]));
						if (strings[1] != null)
							response.setSectionName(strings[1]);
						if (strings[2] != null)
							response.setQuestionId(Integer.valueOf(strings[2]));
						if (strings[3] != null)
							response.setQuestion(strings[3]);
						if (strings[4] != null)
							response.setAnswer(strings[4]);

						questionnaireResponseList.add(response);
					}
				}

				responseMap.put("questionnaireResponse", questionnaireResponseList);

				return new Gson().toJson(responseMap);
			} else {
				throw new InvalidRequestException("request : " + request,
						"Invalid/NULL request, please pass correct data");
			}

		} catch (Exception e) {
			throw new ECDException(e);
		}
	}

}
