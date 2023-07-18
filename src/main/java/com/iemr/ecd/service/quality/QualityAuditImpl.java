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

		try {
			List<QualityAuditorWorklistResponseDTO> responseList = new ArrayList<>();
			QualityAuditorWorklistResponseDTO response;

			// create from-date & to-date from cycle info
			if (qualityAuditorWorklistRequestDTO.getCycleId() != null) {
				List<SampleSelectionConfiguration> resultList = sampleSelectionConfigurationRepo
						.findByCycleIdAndDeletedAndPsmId(qualityAuditorWorklistRequestDTO.getCycleId(), false,
								qualityAuditorWorklistRequestDTO.getPsmId());

				if (resultList != null && resultList.size() > 0) {
					SampleSelectionConfiguration obj = resultList.get(0);
					// from_date
					Calendar cal1 = new GregorianCalendar();
					cal1.set(Calendar.YEAR, qualityAuditorWorklistRequestDTO.getYear());
					cal1.set(Calendar.MONTH, qualityAuditorWorklistRequestDTO.getMonth() - 1);
					cal1.set(Calendar.DATE, obj.getFromDay());
					cal1.set(Calendar.HOUR_OF_DAY, 0);
					cal1.set(Calendar.MINUTE, 0);
					cal1.set(Calendar.SECOND, 0);
					cal1.set(Calendar.MILLISECOND, 0);
					Timestamp fDate = new Timestamp(cal1.getTimeInMillis());

					// to_date
					Calendar cal2 = new GregorianCalendar();
					cal2.set(Calendar.YEAR, qualityAuditorWorklistRequestDTO.getYear());
					cal2.set(Calendar.MONTH, qualityAuditorWorklistRequestDTO.getMonth() - 1);
					cal2.set(Calendar.DATE, obj.getToDay());
					cal2.set(Calendar.HOUR_OF_DAY, 23);
					cal2.set(Calendar.MINUTE, 59);
					cal2.set(Calendar.SECOND, 59);
					cal2.set(Calendar.MILLISECOND, 59);
					Timestamp tDate = new Timestamp(cal2.getTimeInMillis());

					qualityAuditorWorklistRequestDTO.setFromDate(fDate);
					qualityAuditorWorklistRequestDTO.setToDate(tDate);

				} else
					throw new ECDException(
							"given cycle is not available for the provider, please contact administrator");
			} else
				throw new InvalidRequestException("invalid/NULL cycle id : ", "please pass cycle info to get the data");

			// call SP to get work-list data
			List<String[]> resultSet = agentQualityAuditorMapRepo.getQualityAuditorWorklist(
					qualityAuditorWorklistRequestDTO.getFromDate(), qualityAuditorWorklistRequestDTO.getToDate(),
					qualityAuditorWorklistRequestDTO.getPsmId(), qualityAuditorWorklistRequestDTO.getLanguageId(),
					qualityAuditorWorklistRequestDTO.getAgentId(), qualityAuditorWorklistRequestDTO.getRoleId(),
					qualityAuditorWorklistRequestDTO.getIsValid());

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
}
