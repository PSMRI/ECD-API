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

import com.iemr.ecd.dao.QualityAuditSectionConfiguration;
import com.iemr.ecd.service.quality.QualityAuditSectionConfigurationImpl;

@ContextConfiguration(classes = { QualityAuditSectionConfigurationController.class })
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class QualityAuditSectionConfigurationControllerDiffblueTest {
	@Autowired
	private QualityAuditSectionConfigurationController qualityAuditSectionConfigurationController;

	@MockBean
	private QualityAuditSectionConfigurationImpl qualityAuditSectionConfigurationImpl;

	@Test
	void testCreateQualityAuditSectionConfiguration() throws Exception {

		QualityAuditSectionConfiguration qualityAuditSectionConfiguration2 = new QualityAuditSectionConfiguration();
		qualityAuditSectionConfiguration2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
		qualityAuditSectionConfiguration2.setCreatedDate(mock(Timestamp.class));
		qualityAuditSectionConfiguration2.setDeleted(true);
		qualityAuditSectionConfiguration2.setLastModDate(mock(Timestamp.class));
		qualityAuditSectionConfiguration2.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
		qualityAuditSectionConfiguration2.setPsmId(1);
		qualityAuditSectionConfiguration2.setRank(1);
		qualityAuditSectionConfiguration2.setSectionDesc("Section Desc");
		qualityAuditSectionConfiguration2.setSectionId(1);
		qualityAuditSectionConfiguration2.setSectionName("Section Name");

		qualityAuditSectionConfiguration2.toString();

		List<QualityAuditSectionConfiguration> qualityAuditSectionConfiguration = new ArrayList<>();
		qualityAuditSectionConfiguration.add(qualityAuditSectionConfiguration2);

		ResponseEntity<String> response = qualityAuditSectionConfigurationController
				.createQualityAuditSectionConfiguration(qualityAuditSectionConfiguration);

		assertEquals(response, qualityAuditSectionConfigurationController
				.createQualityAuditSectionConfiguration(qualityAuditSectionConfiguration));

	}

	@Test
	void testGetQualityAuditSectionConfigurationByPSMId() throws Exception {

		Integer psmId = 3;

		ResponseEntity<List<QualityAuditSectionConfiguration>> response = qualityAuditSectionConfigurationController
				.getQualityAuditSectionConfigurationByPSMId(psmId);

		assertEquals(response,
				qualityAuditSectionConfigurationController.getQualityAuditSectionConfigurationByPSMId(psmId));
	}

	@Test
	void testUpdateQualityAuditSectionConfiguration() throws Exception {

		QualityAuditSectionConfiguration qualityAuditSectionConfiguration = new QualityAuditSectionConfiguration();
		qualityAuditSectionConfiguration.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
		qualityAuditSectionConfiguration.setCreatedDate(mock(Timestamp.class));
		qualityAuditSectionConfiguration.setDeleted(true);
		qualityAuditSectionConfiguration.setLastModDate(mock(Timestamp.class));
		qualityAuditSectionConfiguration.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
		qualityAuditSectionConfiguration.setPsmId(1);
		qualityAuditSectionConfiguration.setRank(1);
		qualityAuditSectionConfiguration.setSectionDesc("Section Desc");
		qualityAuditSectionConfiguration.setSectionId(1);
		qualityAuditSectionConfiguration.setSectionName("Section Name");

		qualityAuditSectionConfiguration.toString();

		ResponseEntity<String> response = qualityAuditSectionConfigurationController
				.updateQualityAuditSectionConfiguration(qualityAuditSectionConfiguration);

		assertEquals(response, qualityAuditSectionConfigurationController
				.updateQualityAuditSectionConfiguration(qualityAuditSectionConfiguration));
	}
}
