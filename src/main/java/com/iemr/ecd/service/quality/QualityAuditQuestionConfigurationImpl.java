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
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.iemr.ecd.dao.QualityAuditQuestionConfig;
import com.iemr.ecd.dao.QualityAuditQuestionnaireValues;
import com.iemr.ecd.dto.QualityAuditorSectionQuestionaireResponseDTO;
import com.iemr.ecd.repository.quality.QualityAuditQuestionConfigRepo;
import com.iemr.ecd.repository.quality.QualityAuditQuestionnaireValuesRepo;
import com.iemr.ecd.utils.advice.exception_handler.ECDException;

import jakarta.transaction.Transactional;

@Service
public class QualityAuditQuestionConfigurationImpl {
	@Autowired
	private QualityAuditQuestionConfigRepo qualityAuditQuestionConfigRepo;

	@Autowired
	private QualityAuditQuestionnaireValuesRepo qualityAuditQuestionnaireValuesRepo;

	@Transactional(rollbackOn = Exception.class)
	public String createQualityAuditQuestionnaireConfiguration(
			List<QualityAuditQuestionConfig> qualityAuditQuestionConfig) {
		try {
			QualityAuditQuestionnaireValues questionnaireOptions;
			Set<QualityAuditQuestionnaireValues> optionsSet = null;

			for (QualityAuditQuestionConfig question : qualityAuditQuestionConfig) {
				if (question.getOptions() != null && question.getOptions().length > 0) {
					int j = 0;
					Integer[] scoreArr = question.getScores();
					optionsSet = new HashSet<>();
					for (String option : question.getOptions()) {
						questionnaireOptions = new QualityAuditQuestionnaireValues();
						questionnaireOptions.setQuestionValues(option);
						questionnaireOptions.setScore(scoreArr[j]);
						questionnaireOptions.setPsmId(question.getPsmId());
						questionnaireOptions.setCreatedBy(question.getCreatedBy());

						optionsSet.add(questionnaireOptions);
						j++;
					}
				}
				QualityAuditQuestionConfig resultSet = qualityAuditQuestionConfigRepo.save(question);
				if (resultSet != null && resultSet.getId() != null && optionsSet != null && optionsSet.size() > 0) {
					for (QualityAuditQuestionnaireValues optionsValues : optionsSet) {
						optionsValues.setQuestionId(resultSet.getId());
					}
					qualityAuditQuestionnaireValuesRepo.saveAll(optionsSet);
				}
			}

			Map<String, Object> responseMap = new HashMap<>();
			responseMap.put("response", "Qulaity Audit Questionnaire Created Successfully");
			return new Gson().toJson(responseMap);
		} catch (Exception e) {
			throw new ECDException(e);
		}

	}

	public List<QualityAuditorSectionQuestionaireResponseDTO> getQualityAuditQuestionnaireConfigurationByPSMId(
			Integer psmId) {
		try {
			List<String[]> quesList = qualityAuditQuestionConfigRepo.getQualityAuditQuestionnaire(psmId);
			return getQuesSecDtoList(quesList);
		} catch (Exception e) {
			throw new ECDException(e);
		}
	}

	private List<QualityAuditorSectionQuestionaireResponseDTO> getQuesSecDtoList(List<String[]> quesSecSpData) {
		try {
			List<QualityAuditorSectionQuestionaireResponseDTO> quesList = new ArrayList<>();
			QualityAuditorSectionQuestionaireResponseDTO obj;
			if (quesSecSpData != null && quesSecSpData.size() > 0) {
				for (String[] strArr : quesSecSpData) {
					obj = new QualityAuditorSectionQuestionaireResponseDTO();
					if (strArr[0] != null)
						obj.setSectionId(Integer.valueOf(strArr[0]));
					if (strArr[1] != null)
						obj.setSectionName(strArr[1]);
					if (strArr[2] != null)
						obj.setQuestionId(Integer.valueOf(strArr[2]));
					if (strArr[3] != null)
						obj.setQuestion(strArr[3]);
					if (strArr[4] != null)
						obj.setSectionRank(Integer.valueOf(strArr[4]));
					if (strArr[5] != null)
						obj.setQuestionRank(Integer.valueOf(strArr[5]));
					if (strArr[6] != null)
						obj.setAnswerType(strArr[6]);
					if (strArr[7] != null)
						obj.setPsmId(Integer.valueOf(strArr[7]));
					if (strArr[8] != null)
						obj.setDeleted(Boolean.valueOf(strArr[8]));
					if (strArr[9] != null)
						obj.setCreatedBy(strArr[9]);
					if (strArr[10] != null)
						obj.setCreatedDate(Timestamp.valueOf(strArr[10]));
					if (strArr[11] != null)
						obj.setModifiedBy(strArr[11]);
					if (strArr[12] != null)
						obj.setLastModDate(Timestamp.valueOf(strArr[12]));

					List<QualityAuditQuestionnaireValues> optionsList = qualityAuditQuestionnaireValuesRepo
							.findByQuestionIdAndPsmIdAndDeleted(obj.getQuestionId(), obj.getPsmId(), false);
					List<String> options = new ArrayList<>();
					List<Integer> scores = new ArrayList<>();
					for (QualityAuditQuestionnaireValues option : optionsList) {
						options.add(option.getQuestionValues());
						scores.add(option.getScore());

					}
					obj.setOptions(options);
					obj.setScores(scores);

					quesList.add(obj);

				}
			}
			return quesList;
		} catch (Exception e) {
			throw new ECDException(e);
		}
	}

	@Transactional(rollbackOn = Exception.class)
	public String updateQualityAuditQuestionnaireConfiguration(QualityAuditQuestionConfig qualityAuditQuestionnaire) {

		try {
			QualityAuditQuestionnaireValues questionnaireOptions;
			Set<QualityAuditQuestionnaireValues> optionsSet = new HashSet<>();

			qualityAuditQuestionConfigRepo.save(qualityAuditQuestionnaire);

			List<QualityAuditQuestionnaireValues> optionsValue = qualityAuditQuestionnaireValuesRepo
					.findByQuestionIdAndDeleted(qualityAuditQuestionnaire.getId(), false);
			if (optionsValue != null && optionsValue.size() > 0) {
				for (QualityAuditQuestionnaireValues option : optionsValue) {
					option.setDeleted(true);
					optionsSet.add(option);
				}
			}

			if (qualityAuditQuestionnaire.getOptions() != null && qualityAuditQuestionnaire.getOptions().length > 0
					&& qualityAuditQuestionnaire.getScores() != null
					&& qualityAuditQuestionnaire.getScores().length > 0) {
				int i = 0;
				Integer[] scoreArr = qualityAuditQuestionnaire.getScores();
				for (String option : qualityAuditQuestionnaire.getOptions()) {
					questionnaireOptions = new QualityAuditQuestionnaireValues();
					questionnaireOptions.setQuestionValues(option);
					questionnaireOptions.setScore(scoreArr[i]);
					questionnaireOptions.setQuestionId(qualityAuditQuestionnaire.getId());
					questionnaireOptions.setPsmId(qualityAuditQuestionnaire.getPsmId());
					questionnaireOptions.setCreatedBy(qualityAuditQuestionnaire.getCreatedBy());

					optionsSet.add(questionnaireOptions);
					i++;
				}
			}
			if (optionsSet.size() > 0) {
				qualityAuditQuestionnaireValuesRepo.saveAll(optionsSet);
			}

			Map<String, Object> responseMap = new HashMap<>();
			responseMap.put("response", "Qulaity Audit Questionnaire Updated Successfully");
			return new Gson().toJson(responseMap);

		} catch (Exception e) {
			throw new ECDException(e);
		}
	}

}
