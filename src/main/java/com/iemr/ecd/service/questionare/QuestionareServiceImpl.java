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
package com.iemr.ecd.service.questionare;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.iemr.ecd.dao.CallConfiguration;
import com.iemr.ecd.dao.MapQuestion;
import com.iemr.ecd.dao.Questionnaire;
import com.iemr.ecd.dao.QuestionnaireSections;
import com.iemr.ecd.dao.QuestionnaireValues;
import com.iemr.ecd.dao.SectionQuestionnaireMapping;
import com.iemr.ecd.dao.V_GetSectionQuestionMapping;
import com.iemr.ecd.dao.V_GetSectionQuestionMappingAssociates;
import com.iemr.ecd.dto.ECDMapQuestions;
import com.iemr.ecd.dto.RequestSectionQuestionnaireMappingDTO;
import com.iemr.ecd.dto.ResponseSectionQuestionnaireMappingDTO;
import com.iemr.ecd.repo.call_conf_allocation.CallConfigurationRepo;
import com.iemr.ecd.repo.call_conf_allocation.EcdQuestionnaireRepo;
import com.iemr.ecd.repo.call_conf_allocation.QuestionnaireSectionRepo;
import com.iemr.ecd.repo.call_conf_allocation.QuestionnaireValuesRepo;
import com.iemr.ecd.repository.ecd.MapQuestionRepo;
import com.iemr.ecd.repository.ecd.SectionQuestionnaireMappingRepo;
import com.iemr.ecd.repository.ecd.V_GetSectionQuestionMappingAssociatesRepo;
import com.iemr.ecd.repository.ecd.V_GetSectionQuestionMappingRepo;
import com.iemr.ecd.utils.advice.exception_handler.ECDException;
import com.iemr.ecd.utils.advice.exception_handler.InvalidRequestException;

import io.micrometer.observation.ObservationRegistry;
import jakarta.transaction.Transactional;

@Service
public class QuestionareServiceImpl {
	@Autowired
	private ObservationRegistry registry;

	@Autowired
	private EcdQuestionnaireRepo ecdQuestionnaireRepo;
	@Autowired
	private QuestionnaireValuesRepo questionnaireValuesRepo;
	@Autowired
	private QuestionnaireSectionRepo questionnaireSectionRepo;
	@Autowired
	private SectionQuestionnaireMappingRepo sectionQuestionnaireMappingRepo;
	@Autowired
	private V_GetSectionQuestionMappingRepo v_GetSectionQuestionMappingRepo;
	@Autowired
	private V_GetSectionQuestionMappingAssociatesRepo v_GetSectionQuestionMappingAssociatesRepo;
	@Autowired
	private MapQuestionRepo mapQuestionRepo;

	@Transactional(rollbackOn = Exception.class)
	public String createQuestionares(List<Questionnaire> questionares) {

		try {
			QuestionnaireValues questionnaireValues;
			// Set<QuestionnaireValues> optionsSet = null;

			for (Questionnaire questionare : questionares) {

				Questionnaire resultSet = ecdQuestionnaireRepo.save(questionare);

				if (questionare.getQuestionnaireValues() != null && questionare.getQuestionnaireValues().size() > 0) {
					for (QuestionnaireValues options : questionare.getQuestionnaireValues()) {
						options.setQuestionId(resultSet.getQuestionnaireId());
					}
					questionnaireValuesRepo.saveAll(questionare.getQuestionnaireValues());
				}

			}

			Map<String, Object> responseMap = new HashMap<>();
			responseMap.put("response", "created successfully");
			return new Gson().toJson(responseMap);
		} catch (Exception e) {
			throw new ECDException(e);
		}

	}

	@Transactional(rollbackOn = Exception.class)
	public String updateQuestionares(Questionnaire questionare) {
		try {
			if (questionare != null) {
				ecdQuestionnaireRepo.save(questionare);

				if (questionare.getQuestionnaireValues() != null && questionare.getQuestionnaireValues().size() > 0)
					questionnaireValuesRepo.saveAll(questionare.getQuestionnaireValues());

			} else
				throw new InvalidRequestException("Invalid/NULL request", "please pass correct questionare object");

			Map<String, Object> responseMap = new HashMap<>();
			responseMap.put("response", "modified successfully");
			return new Gson().toJson(responseMap);
		} catch (Exception e) {
			throw new ECDException(e);
		}

	}

	@Transactional(rollbackOn = Exception.class)
	public String createSections(List<QuestionnaireSections> questionareSections) {
		try {
			if (questionareSections != null && questionareSections.size() > 0)
				questionnaireSectionRepo.saveAll(questionareSections);
			else
				throw new InvalidRequestException("NULL/Empty request", "please pass valid section list");

			Map<String, Object> responseMap = new HashMap<>();
			responseMap.put("response", "created successfully");
			return new Gson().toJson(responseMap);
		} catch (Exception e) {
			throw new ECDException(e);
		}

	}

	public List<QuestionnaireSections> getSectionsByProvider(int psmId) {
		try {
			if (psmId > 0)
				return questionnaireSectionRepo.findByPsmId(psmId);
			else
				throw new InvalidRequestException("psmId :" + psmId, "Invalid request parameter");
		} catch (Exception e) {
			throw new ECDException(e);
		}

	}

	@Transactional(rollbackOn = Exception.class)
	public String updateSections(QuestionnaireSections questionareSection) {
		try {
			if (questionareSection != null && questionareSection.getSectionId() != null)
				questionnaireSectionRepo.save(questionareSection);
			else
				throw new InvalidRequestException("NULL/Empty request", "please pass valid section object with Id");

			Map<String, Object> responseMap = new HashMap<>();
			responseMap.put("response", "updated successfully");
			return new Gson().toJson(responseMap);
		} catch (Exception e) {
			throw new ECDException(e);
		}

	}

	@Transactional(rollbackOn = Exception.class)
	public String mapQuestionnairesAndSection(
			RequestSectionQuestionnaireMappingDTO requestSectionQuestionnaireMappingDTO) {
		try {
			if (requestSectionQuestionnaireMappingDTO != null
					&& requestSectionQuestionnaireMappingDTO.getSectionId() > 0
					&& requestSectionQuestionnaireMappingDTO.getQuestionIds() != null
					&& requestSectionQuestionnaireMappingDTO.getQuestionIds().size() > 0) {

				SectionQuestionnaireMapping sectionQuestionnaireMapping;
				List<SectionQuestionnaireMapping> sectionQuestionnaireMappingList = new ArrayList<>();
				for (Questionnaire questionare : requestSectionQuestionnaireMappingDTO.getQuestionIds()) {
					sectionQuestionnaireMapping = new SectionQuestionnaireMapping();

					sectionQuestionnaireMapping.setSectionId(requestSectionQuestionnaireMappingDTO.getSectionId());
					sectionQuestionnaireMapping.setCreatedBy(requestSectionQuestionnaireMappingDTO.getCreatedBy());
					sectionQuestionnaireMapping.setPsmId(requestSectionQuestionnaireMappingDTO.getPsmId());
					sectionQuestionnaireMapping.setQuestionId(questionare.getQuestionnaireId());
					sectionQuestionnaireMapping.setRank(questionare.getRank());
					if (questionare.getRoles() != null && questionare.getRoles().length > 0) {
						StringBuffer sb = new StringBuffer();
						for (String role : questionare.getRoles()) {
							sb.append(role).append(",");
						}
						if (sb.length() >= 1)
							sectionQuestionnaireMapping.setRole(sb.substring(0, sb.length() - 1));

					}

					sectionQuestionnaireMappingList.add(sectionQuestionnaireMapping);
				}

				sectionQuestionnaireMappingRepo.saveAll(sectionQuestionnaireMappingList);

			} else
				throw new InvalidRequestException("Invalid request", "please pass valid section Id");

			Map<String, Object> responseMap = new HashMap<>();
			responseMap.put("response", "mapping done successfully");
			return new Gson().toJson(responseMap);
		} catch (Exception e) {
			throw new ECDException(e);
		}

	}

	public List<Questionnaire> getQuestionaresByProvider(int psmId) {
		try {

			List<Questionnaire> questionareList = ecdQuestionnaireRepo.findByPsmId(psmId);

			if (questionareList != null && questionareList.size() > 0) {

				for (Questionnaire questionnaire : questionareList) {

					if (questionnaire != null && questionnaire.getAnswerType() != null
							&& (questionnaire.getAnswerType().equalsIgnoreCase("Radio")
									|| questionnaire.getAnswerType().equalsIgnoreCase("Dropdown")
									|| questionnaire.getAnswerType().equalsIgnoreCase("Multiple"))) {
						List<QuestionnaireValues> optionList = questionnaireValuesRepo
								.findByQuestionIdAndDeleted(questionnaire.getQuestionnaireId(), false);

						if (optionList != null && optionList.size() > 0) {
							String[] questionArr = new String[optionList.size()];
							int i = 0;

							for (QuestionnaireValues option : optionList) {
								questionArr[i] = option.getOptions();
								i++;
							}
							questionnaire.setOptions(questionArr);
							questionnaire.setQuestionnaireValues(optionList);
						}
					}
				}
			}
			return questionareList;
		} catch (Exception e) {
			throw new ECDException(e);
		}
	}

	public List<Questionnaire> getQuestionares(int psmId) {
		try {

			List<Questionnaire> questionareList = ecdQuestionnaireRepo.findByPsmIdAndDeleted(psmId, false);

			if (questionareList != null && questionareList.size() > 0) {

				for (Questionnaire questionnaire : questionareList) {

					if (questionnaire != null && questionnaire.getAnswerType() != null
							&& (questionnaire.getAnswerType().equalsIgnoreCase("Radio")
									|| questionnaire.getAnswerType().equalsIgnoreCase("Dropdown")
									|| questionnaire.getAnswerType().equalsIgnoreCase("Multiple"))) {
						List<QuestionnaireValues> optionList = questionnaireValuesRepo
								.findByQuestionIdAndDeleted(questionnaire.getQuestionnaireId(), false);

						if (optionList != null && optionList.size() > 0) {
							String[] questionArr = new String[optionList.size()];
							int i = 0;

							for (QuestionnaireValues option : optionList) {
								questionArr[i] = option.getOptions();
								i++;
							}
							questionnaire.setOptions(questionArr);
							questionnaire.setQuestionnaireValues(optionList);
						}
					}
				}
			}
			return questionareList;
		} catch (Exception e) {
			throw new ECDException(e);
		}
	}

	public List<Questionnaire> getUnMappedQuestionnairesByPSMId(int psmId) {
		try {
			return ecdQuestionnaireRepo.getUnMappedQuestionnairesByPSMId(psmId);
		} catch (Exception e) {
			throw new ECDException(e);
		}

	}

	public List<V_GetSectionQuestionMapping> getQuestionnairesAndSectionMappingDataByProvider(int psmId) {
		try {

			List<V_GetSectionQuestionMapping> responseList = new ArrayList<>();

			List<QuestionnaireSections> sectionList = getSectionsByProvider(psmId);

			if (sectionList != null && sectionList.size() > 0) {
				for (QuestionnaireSections questionnaireSections : sectionList) {
					List<V_GetSectionQuestionMapping> tempList = v_GetSectionQuestionMappingRepo
							.findBySectionid(questionnaireSections.getSectionId());
					if (tempList != null) {
						for (V_GetSectionQuestionMapping resultset : tempList) {
							if (resultset.getRole() != null) {
								resultset.setRoles(resultset.getRole().split(","));
							}

							responseList.add(resultset);
						}
					}
				}
			}

			return responseList;

		} catch (Exception e) {
			throw new ECDException(e);
		}
	}

	@Autowired
	private CallConfigurationRepo callConfigurationRepo;

	public List<V_GetSectionQuestionMappingAssociates> getQuesAndSecMapAssociateByProvider(int psmId,
			String ecdCallType, String role) {

		try {

			CallConfiguration callConfiguration = callConfigurationRepo.getCallConfigurationByProviderAndCallType(psmId,
					ecdCallType);

			if (callConfiguration != null && callConfiguration.getCallConfigId() != null) {
				List<V_GetSectionQuestionMappingAssociates> responseList = new ArrayList<>();

				List<V_GetSectionQuestionMappingAssociates> tempList = v_GetSectionQuestionMappingAssociatesRepo
						.findByPsmIdAndCallConfigIdAndRole(psmId, callConfiguration.getCallConfigId().intValue(), role);

				if (tempList != null && tempList.size() > 0) {
					for (V_GetSectionQuestionMappingAssociates obj : tempList) {
						List<QuestionnaireValues> optionList = questionnaireValuesRepo
								.findByQuestionIdAndDeleted(obj.getQuestionid(), false);

						if (optionList != null && optionList.size() > 0) {
							String[] optionArr = new String[optionList.size()];
							int i = 0;

							for (QuestionnaireValues option : optionList) {
								optionArr[i] = option.getOptions();
								i++;
							}
							obj.setOptionsArr(optionArr);
							obj.setOptions(optionList);
						}
					}
					responseList.addAll(tempList);
				}

				return responseList;
			} else
				throw new Exception(
						"Not found call configuration against given ECD call type. Please contact administrator/supervisor");
		} catch (Exception e) {
			throw new ECDException(e);
		}
	}

	// not in use as of now
	public ResponseSectionQuestionnaireMappingDTO getQuestionnairesAndSectionMappingDataBySectionId(int sectionId) {

		try {
			ResponseSectionQuestionnaireMappingDTO responseSectionQuestionnaireMappingDTO;

			Questionnaire questionnaire;
			List<Questionnaire> questionnaireList;

			if (sectionId > 0) {

				questionnaireList = new ArrayList<>();
				responseSectionQuestionnaireMappingDTO = new ResponseSectionQuestionnaireMappingDTO();

				List<V_GetSectionQuestionMapping> resultSet1 = v_GetSectionQuestionMappingRepo
						.findBySectionid(sectionId);

				if (resultSet1 != null && resultSet1.size() > 0) {
					responseSectionQuestionnaireMappingDTO.setSectionId(sectionId);
					responseSectionQuestionnaireMappingDTO.setSectionName(resultSet1.get(0).getSectionName());
					responseSectionQuestionnaireMappingDTO.setPsmId(resultSet1.get(0).getPsmId());
					for (V_GetSectionQuestionMapping viewData : resultSet1) {
						questionnaire = new Questionnaire();
						questionnaire.setQuestionnaireId(viewData.getQuestionid());
						questionnaire.setQuestionnaire(viewData.getQuestion());
						questionnaire.setQuestionRank(viewData.getQuestionRank());
						questionnaire.setRank(viewData.getSectionQuestionRank());
						questionnaire.setSecQuesMapId(viewData.getId());

						questionnaireList.add(questionnaire);
					}
				}
				responseSectionQuestionnaireMappingDTO.setQuestionIds(questionnaireList);

			} else
				throw new InvalidRequestException("NULL/Invalid section Id", "Please pass valid section Id");

			return responseSectionQuestionnaireMappingDTO;
		} catch (Exception e) {
			throw new ECDException(e);
		}
	}

	@Transactional(rollbackOn = Exception.class)
	public String editQuestionnaireSectionMap(SectionQuestionnaireMapping sectionQuestionnaireMapping) {

		try {
			if (sectionQuestionnaireMapping != null && sectionQuestionnaireMapping.getId() != null) {
				if (sectionQuestionnaireMapping.getRoles() != null
						&& sectionQuestionnaireMapping.getRoles().length > 0) {
					StringBuffer sb = new StringBuffer();
					for (String roles : sectionQuestionnaireMapping.getRoles()) {
						sb.append(roles).append(",");
					}
					if (sb.length() >= 1)
						sectionQuestionnaireMapping.setRole(sb.substring(0, sb.length() - 1));
				}
				sectionQuestionnaireMappingRepo.save(sectionQuestionnaireMapping);
			} else
				throw new InvalidRequestException("NULL/Empty request", "please pass valid Id");

			Map<String, Object> responseMap = new HashMap<>();
			responseMap.put("response", "updated successfully");
			return new Gson().toJson(responseMap);
		} catch (Exception e) {
			throw new ECDException(e);
		}

	}

	public List<Questionnaire> getUnMappedQuestionnaires(int psmId, int sectionId) {
		try {
			return ecdQuestionnaireRepo.getUnMappedQuestionnaires(psmId, sectionId);
		} catch (Exception e) {
			throw new ECDException(e);
		}

	}

	@Transactional(rollbackOn = Exception.class)
	public String createQuestionnairesMap(MapQuestion questionnaireMap) {

		try {

			mapQuestionRepo.save(questionnaireMap);

			Map<String, Object> responseMap = new HashMap<>();
			responseMap.put("response", "created successfully");
			return new Gson().toJson(responseMap);
		} catch (Exception e) {
			throw new ECDException(e);
		}

	}

	@Transactional(rollbackOn = Exception.class)
	public String editQuestionnairesMap(MapQuestion editMapQuestion) {
		try {
			if (editMapQuestion != null && editMapQuestion.getId() != null)
				mapQuestionRepo.save(editMapQuestion);

			Map<String, Object> responseMap = new HashMap<>();
			responseMap.put("response", "Questionnaire Map Updated Successfully");
			return new Gson().toJson(responseMap);
		} catch (Exception e) {
			throw new ECDException(e);
		}

	}

	public List<ECDMapQuestions> getMappedParentChildQuestionnaire(int psmId) {
		try {
			List<String[]> mappingList = mapQuestionRepo.getMappedParentChildQuestionnaire(psmId);
			return getMappedQuestionList(mappingList);

		} catch (Exception e) {
			throw new ECDException(e);
		}
	}

	private List<ECDMapQuestions> getMappedQuestionList(List<String[]> mappedQuestionData) throws Exception {
		List<ECDMapQuestions> mappingList = new ArrayList<>();
		ECDMapQuestions obj;
		if (mappedQuestionData != null && mappedQuestionData.size() > 0) {
			for (String[] strArr : mappedQuestionData) {
				try {
					obj = new ECDMapQuestions();
					if (strArr[0] != null)
						obj.setId(Integer.valueOf(strArr[0]));
					if (strArr[1] != null)
						obj.setParentQuestionId(Integer.valueOf(strArr[1]));
					if (strArr[2] != null)
						obj.setParentQuestion(strArr[2]);
					if (strArr[3] != null)
						obj.setAnswer(strArr[3]);
					if (strArr[4] != null)
						obj.setChildQuestionId(Integer.valueOf(strArr[4]));
					if (strArr[5] != null)
						obj.setChildQuestion(strArr[5]);
					if (strArr[6] != null)
						obj.setPsmId(Integer.valueOf(strArr[6]));
					if (strArr[7] != null)
						obj.setDeleted(Boolean.valueOf(strArr[7]));

					mappingList.add(obj);
				} catch (Exception e) {
					throw new ECDException(e);
				}
			}
		}
		return mappingList;
	}

}
