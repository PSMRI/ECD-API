package com.iemr.ecd.controller.quality;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.iemr.ecd.dto.ResponseFetchQualityChartsDataDTO;
import com.iemr.ecd.service.quality.ChartsImpl;

@ContextConfiguration(classes = { ChartsController.class })
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class ChartsControllerDiffblueTest {
	@Autowired
	private ChartsController chartsController;

	@MockBean
	private ChartsImpl chartsImpl;

	@Test
	void testGetActorWiseQualityRatingByRoleIdAndPSMIdAndFrequency() throws Exception {

		Integer psmId = 123;
		String frequency = "Often";
		String frequencyValue = "10 times";

		ResponseEntity<List<ResponseFetchQualityChartsDataDTO>> response = chartsController
				.getActorWiseQualityRatingByRoleIdAndPSMIdAndFrequency(psmId, frequency, frequencyValue);

		assertEquals(response, chartsController.getActorWiseQualityRatingByRoleIdAndPSMIdAndFrequency(psmId, frequency,
				frequencyValue));

	}

	@Test
	void testGetActorWiseQualityRatings() throws Exception {

		Integer psmId = 123;
		String roleName = "Admin";
		String month = "November";

		ResponseEntity<List<ResponseFetchQualityChartsDataDTO>> response = chartsController
				.getActorWiseQualityRatings(psmId, roleName, month);

		assertEquals(response, chartsController.getActorWiseQualityRatings(psmId, roleName, month));
	}

	@Test
	void testGetTenureWiseQualityRatings() throws Exception {

		Integer psmId = 123;
		String roleName = "Admin";

		ResponseEntity<List<ResponseFetchQualityChartsDataDTO>> response = chartsController
				.getTenureWiseQualityRatings(psmId, roleName);

		assertEquals(response, chartsController.getTenureWiseQualityRatings(psmId, roleName));

	}

	@Test
	void testGetTrendAnalysisOfCentreOverallQualityRatings() throws Exception {

		Integer psmId = 9;
		String frequency = "10 Frequently";
		String month = "February";

		ResponseEntity<List<ResponseFetchQualityChartsDataDTO>> response = chartsController
				.getTrendAnalysisOfCentreOverallQualityRatings(psmId, frequency, month);

		assertEquals(response, chartsController.getTrendAnalysisOfCentreOverallQualityRatings(psmId, frequency, month));
	}
}
