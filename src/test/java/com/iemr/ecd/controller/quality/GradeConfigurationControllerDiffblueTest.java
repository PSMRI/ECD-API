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

import com.iemr.ecd.dao.GradeConfiguration;
import com.iemr.ecd.service.quality.GradeConfigurationImpl;

@ContextConfiguration(classes = { GradeConfigurationController.class })
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class GradeConfigurationControllerDiffblueTest {
	@Autowired
	private GradeConfigurationController gradeConfigurationController;

	@MockBean
	private GradeConfigurationImpl gradeConfigurationImpl;

	@Test
	void testCreateGradeConfiguration() throws Exception {

		GradeConfiguration gradeConfiguration2 = new GradeConfiguration();
		gradeConfiguration2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
		gradeConfiguration2.setCreatedDate(mock(Timestamp.class));
		gradeConfiguration2.setDeleted(true);
		gradeConfiguration2.setGrade("response");
		gradeConfiguration2.setGradeId(1);
		gradeConfiguration2.setId(1);
		gradeConfiguration2.setLastModDate(mock(Timestamp.class));
		gradeConfiguration2.setMaxValue(10.0d);
		gradeConfiguration2.setMinValue(10.0d);
		gradeConfiguration2.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
		gradeConfiguration2.setPsmId(1);

		gradeConfiguration2.toString();

		ArrayList<GradeConfiguration> gradeConfiguration = new ArrayList<>();
		gradeConfiguration.add(gradeConfiguration2);

		ResponseEntity<String> response = gradeConfigurationController.createGradeConfiguration(gradeConfiguration);

		assertEquals(response, gradeConfigurationController.createGradeConfiguration(gradeConfiguration));
	}

	@Test
	void testGetGradeConfigurationByPSMId() throws Exception {

		Integer psmId = 3;

		ResponseEntity<List<GradeConfiguration>> response = gradeConfigurationController
				.getGradeConfigurationByPSMId(psmId);

		assertEquals(response, gradeConfigurationController.getGradeConfigurationByPSMId(psmId));
	}

	@Test
	void testUpdateGradeConfiguration() throws Exception {

		GradeConfiguration gradeConfiguration = new GradeConfiguration();
		gradeConfiguration.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
		gradeConfiguration.setCreatedDate(mock(Timestamp.class));
		gradeConfiguration.setDeleted(true);
		gradeConfiguration.setGrade("Grade");
		gradeConfiguration.setGradeId(1);
		gradeConfiguration.setId(1);
		gradeConfiguration.setLastModDate(mock(Timestamp.class));
		gradeConfiguration.setMaxValue(10.0d);
		gradeConfiguration.setMinValue(10.0d);
		gradeConfiguration.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
		gradeConfiguration.setPsmId(1);

		gradeConfiguration.toString();

		ResponseEntity<String> response = gradeConfigurationController.updateGradeConfiguration(gradeConfiguration);

		assertEquals(response, gradeConfigurationController.updateGradeConfiguration(gradeConfiguration));

	}
}
