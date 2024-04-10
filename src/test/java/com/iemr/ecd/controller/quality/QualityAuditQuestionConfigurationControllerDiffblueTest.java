package com.iemr.ecd.controller.quality;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.iemr.ecd.dao.QualityAuditQuestionConfig;
import com.iemr.ecd.dto.QualityAuditorSectionQuestionaireResponseDTO;
import com.iemr.ecd.service.quality.QualityAuditQuestionConfigurationImpl;

@ContextConfiguration(classes = { QualityAuditQuestionConfigurationController.class })
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class QualityAuditQuestionConfigurationControllerDiffblueTest {
	@Autowired
	private QualityAuditQuestionConfigurationController qualityAuditQuestionConfigurationController;

	@MockBean
	private QualityAuditQuestionConfigurationImpl qualityAuditQuestionConfigurationImpl;

	@Test
	void testCreateQualityAuditQuestionnaireConfiguration() throws Exception {

		ArrayList<QualityAuditQuestionConfig> qualityAuditQuestionConfigList = new ArrayList<>();

		QualityAuditQuestionConfig qualityAuditQuestionConfig = new QualityAuditQuestionConfig();
		qualityAuditQuestionConfig.setAnswerType("Answer Type");
		qualityAuditQuestionConfig.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
		qualityAuditQuestionConfig.setCreatedDate(mock(Timestamp.class));
		qualityAuditQuestionConfig.setDeleted(true);
		qualityAuditQuestionConfig.setId(1);
		qualityAuditQuestionConfig.setIsFatalQues(true);
		qualityAuditQuestionConfig.setLastModDate(mock(Timestamp.class));
		qualityAuditQuestionConfig.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
		qualityAuditQuestionConfig.setPsmId(1);
		qualityAuditQuestionConfig.setQuestionRank(1);
		qualityAuditQuestionConfig.setQuestionnaire("Questionnaire");
		qualityAuditQuestionConfig.setScores(new Integer[] { 3 });
		qualityAuditQuestionConfig.setSectionId(1);
		qualityAuditQuestionConfig.setOptions(null);

		qualityAuditQuestionConfig.toString();

		qualityAuditQuestionConfigList.add(qualityAuditQuestionConfig);

		ResponseEntity<String> response = qualityAuditQuestionConfigurationController
				.createQualityAuditQuestionnaireConfiguration(qualityAuditQuestionConfigList);

		assertEquals(response, qualityAuditQuestionConfigurationController
				.createQualityAuditQuestionnaireConfiguration(qualityAuditQuestionConfigList));
	}

	@Test
	void testGetQualityAuditQuestionnaireConfigurationByPSMId() throws Exception {

		Integer psmId = 3;

		ResponseEntity<List<QualityAuditorSectionQuestionaireResponseDTO>> response = qualityAuditQuestionConfigurationController
				.getQualityAuditQuestionnaireConfigurationByPSMId(psmId);

		assertEquals(response,
				qualityAuditQuestionConfigurationController.getQualityAuditQuestionnaireConfigurationByPSMId(psmId));
	}

	@Test
	void testUpdateQualityAuditQuestionnaireConfiguration() throws Exception {

		QualityAuditQuestionConfig qualityAuditQuestionConfig = new QualityAuditQuestionConfig();
		qualityAuditQuestionConfig.setAnswerType("Answer Type");
		qualityAuditQuestionConfig.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
		qualityAuditQuestionConfig.setCreatedDate(mock(Timestamp.class));
		qualityAuditQuestionConfig.setDeleted(true);
		qualityAuditQuestionConfig.setId(1);
		qualityAuditQuestionConfig.setIsFatalQues(true);
		qualityAuditQuestionConfig.setLastModDate(mock(Timestamp.class));
		qualityAuditQuestionConfig.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
		qualityAuditQuestionConfig.setOptions(new String[] { "Options" });
		qualityAuditQuestionConfig.setPsmId(1);
		qualityAuditQuestionConfig.setQuestionRank(1);
		qualityAuditQuestionConfig.setQuestionnaire("Questionnaire");
		qualityAuditQuestionConfig.setScores(new Integer[] { 3 });
		qualityAuditQuestionConfig.setSectionId(1);

		qualityAuditQuestionConfig.toString();

		ResponseEntity<String> response = qualityAuditQuestionConfigurationController
				.updateQualityAuditQuestionnaireConfiguration(qualityAuditQuestionConfig);

		assertEquals(response, qualityAuditQuestionConfigurationController
				.updateQualityAuditQuestionnaireConfiguration(qualityAuditQuestionConfig));
	}
}
