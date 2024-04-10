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

import com.iemr.ecd.dao.SampleSelectionConfiguration;
import com.iemr.ecd.service.quality.SampleSelectionConfigurationImpl;

@ContextConfiguration(classes = { SampleSelectionConfigurationController.class })
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class SampleSelectionConfigurationControllerDiffblueTest {
	@Autowired
	private SampleSelectionConfigurationController sampleSelectionConfigurationController;

	@MockBean
	private SampleSelectionConfigurationImpl sampleSelectionConfigurationImpl;

	@Test
	void testCreateSampleSelectionConfiguration() throws Exception {

		SampleSelectionConfiguration sampleSelectionConfiguration2 = new SampleSelectionConfiguration();
		sampleSelectionConfiguration2.setAncSampleSize(3);
		sampleSelectionConfiguration2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
		sampleSelectionConfiguration2.setCreatedDate(mock(Timestamp.class));
		sampleSelectionConfiguration2.setCycleId(1);
		sampleSelectionConfiguration2.setCycleName("Cycle Name");
		sampleSelectionConfiguration2.setDeleted(true);
		sampleSelectionConfiguration2.setFromDay(1);
		sampleSelectionConfiguration2.setId(1);
		sampleSelectionConfiguration2.setLastModDate(mock(Timestamp.class));
		sampleSelectionConfiguration2.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
		sampleSelectionConfiguration2.setPncSampleSize(3);
		sampleSelectionConfiguration2.setPsmId(1);
		sampleSelectionConfiguration2.setToDay(1);
		sampleSelectionConfiguration2.setTotalSampleSize(3);

		sampleSelectionConfiguration2.toString();

		ArrayList<SampleSelectionConfiguration> sampleSelectionConfiguration = new ArrayList<>();
		sampleSelectionConfiguration.add(sampleSelectionConfiguration2);

		ResponseEntity<String> response = sampleSelectionConfigurationController
				.createSampleSelectionConfiguration(sampleSelectionConfiguration);

		assertEquals(response, sampleSelectionConfigurationController
				.createSampleSelectionConfiguration(sampleSelectionConfiguration));
	}

	@Test
	void testGetSampleSelectionConfigurationByPSMId() throws Exception {

		Integer psmId = 3;

		ResponseEntity<List<SampleSelectionConfiguration>> response = sampleSelectionConfigurationController
				.getSampleSelectionConfigurationByPSMId(psmId);

		assertEquals(response, sampleSelectionConfigurationController.getSampleSelectionConfigurationByPSMId(psmId));
	}

	@Test
	void testUpdateSampleSelectionConfiguration() throws Exception {

		SampleSelectionConfiguration sampleSelectionConfiguration = new SampleSelectionConfiguration();
		sampleSelectionConfiguration.setAncSampleSize(3);
		sampleSelectionConfiguration.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
		sampleSelectionConfiguration.setCreatedDate(mock(Timestamp.class));
		sampleSelectionConfiguration.setCycleId(1);
		sampleSelectionConfiguration.setCycleName("Cycle Name");
		sampleSelectionConfiguration.setDeleted(true);
		sampleSelectionConfiguration.setFromDay(1);
		sampleSelectionConfiguration.setId(1);
		sampleSelectionConfiguration.setLastModDate(mock(Timestamp.class));
		sampleSelectionConfiguration.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
		sampleSelectionConfiguration.setPncSampleSize(3);
		sampleSelectionConfiguration.setPsmId(1);
		sampleSelectionConfiguration.setToDay(1);
		sampleSelectionConfiguration.setTotalSampleSize(3);

		sampleSelectionConfiguration.toString();

		ResponseEntity<String> response = sampleSelectionConfigurationController
				.updateSampleSelectionConfiguration(sampleSelectionConfiguration);

		assertEquals(response, sampleSelectionConfigurationController
				.updateSampleSelectionConfiguration(sampleSelectionConfiguration));

	}
}
