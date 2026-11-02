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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.iemr.ecd.dto.ResponseFetchQualityChartsDataDTO;
import com.iemr.ecd.repository.quality.QualityAuditorRatingRepo;
import com.iemr.ecd.utils.advice.exception_handler.ECDException;

@ExtendWith(MockitoExtension.class)
class ChartsImplTest {

	@Mock
	private QualityAuditorRatingRepo qualityAuditorRatingRepo;

	@InjectMocks
	private ChartsImpl service;

	@Test
	void centreOverallQualityRatingsMapsTwoColumnRows() {
		when(qualityAuditorRatingRepo.getTrendAnalysisOfCentreOverallQualityRatings(1, "Monthly", "Jan"))
				.thenReturn(List.<String[]>of(new String[] { "Jan", "80" }));

		List<ResponseFetchQualityChartsDataDTO> data = service
				.getTrendAnalysisOfCentreOverallQualityRatings(1, "Monthly", "Jan");

		assertEquals(1, data.size());
		assertEquals("Jan", data.get(0).getName());
		assertEquals(80, data.get(0).getValue());
	}

	@Test
	void centreOverallQualityRatingsSkipsRowsWithoutName() {
		when(qualityAuditorRatingRepo.getTrendAnalysisOfCentreOverallQualityRatings(1, "Monthly", "Jan"))
				.thenReturn(List.<String[]>of(new String[] { null, "80" }));

		assertTrue(service.getTrendAnalysisOfCentreOverallQualityRatings(1, "Monthly", "Jan").isEmpty());
	}

	@Test
	void centreOverallQualityRatingsLeavesValueNullWhenMissing() {
		when(qualityAuditorRatingRepo.getTrendAnalysisOfCentreOverallQualityRatings(1, "Monthly", "Jan"))
				.thenReturn(List.<String[]>of(new String[] { "Jan", null }));

		assertNull(service.getTrendAnalysisOfCentreOverallQualityRatings(1, "Monthly", "Jan").get(0).getValue());
	}

	@Test
	void centreOverallQualityRatingsReturnsEmptyForNullAndEmptyResults() {
		when(qualityAuditorRatingRepo.getTrendAnalysisOfCentreOverallQualityRatings(anyInt(), anyString(),
				anyString())).thenReturn(null, List.of());

		assertTrue(service.getTrendAnalysisOfCentreOverallQualityRatings(1, "Monthly", "Jan").isEmpty());
		assertTrue(service.getTrendAnalysisOfCentreOverallQualityRatings(1, "Monthly", "Jan").isEmpty());
	}

	@Test
	void centreOverallQualityRatingsWrapsFailure() {
		when(qualityAuditorRatingRepo.getTrendAnalysisOfCentreOverallQualityRatings(anyInt(), anyString(),
				anyString())).thenThrow(new IllegalStateException("db"));

		assertThrows(ECDException.class,
				() -> service.getTrendAnalysisOfCentreOverallQualityRatings(1, "Monthly", "Jan"));
	}

	@Test
	void actorWiseQualityRatingsMapsThreeColumnRows() {
		when(qualityAuditorRatingRepo.getActorWiseQualityRatings(1, "ASSOCIATE", "Jan"))
				.thenReturn(List.<String[]>of(new String[] { "1", "Agent", "75" }));

		List<ResponseFetchQualityChartsDataDTO> data = service.getActorWiseQualityRatings(1, "ASSOCIATE", "Jan");

		assertEquals("Agent", data.get(0).getName());
		assertEquals(75, data.get(0).getValue());
	}

	@Test
	void actorWiseQualityRatingsHandlesNullColumns() {
		when(qualityAuditorRatingRepo.getActorWiseQualityRatings(1, "ASSOCIATE", "Jan"))
				.thenReturn(List.<String[]>of(new String[] { "1", null, null }));

		List<ResponseFetchQualityChartsDataDTO> data = service.getActorWiseQualityRatings(1, "ASSOCIATE", "Jan");

		assertEquals(1, data.size());
		assertNull(data.get(0).getName());
		assertNull(data.get(0).getValue());
	}

	@Test
	void actorWiseQualityRatingsIgnoresUnexpectedColumnCounts() {
		when(qualityAuditorRatingRepo.getActorWiseQualityRatings(1, "ASSOCIATE", "Jan"))
				.thenReturn(List.<String[]>of(new String[] { "only-one" }));

		assertTrue(service.getActorWiseQualityRatings(1, "ASSOCIATE", "Jan").isEmpty());
	}

	@Test
	void actorWiseQualityRatingsWrapsFailure() {
		when(qualityAuditorRatingRepo.getActorWiseQualityRatings(anyInt(), anyString(), anyString()))
				.thenThrow(new IllegalStateException("db"));

		assertThrows(ECDException.class, () -> service.getActorWiseQualityRatings(1, "ASSOCIATE", "Jan"));
	}

	@Test
	void tenureWiseQualityRatingsMapsRows() {
		when(qualityAuditorRatingRepo.getTenureWiseQualityRatings(1, "ASSOCIATE"))
				.thenReturn(Arrays.<String[]>asList(new String[] { "0-6 months", "60" }));

		assertEquals("0-6 months", service.getTenureWiseQualityRatings(1, "ASSOCIATE").get(0).getName());
	}

	@Test
	void tenureWiseQualityRatingsWrapsFailure() {
		when(qualityAuditorRatingRepo.getTenureWiseQualityRatings(anyInt(), anyString()))
				.thenThrow(new IllegalStateException("db"));

		assertThrows(ECDException.class, () -> service.getTenureWiseQualityRatings(1, "ASSOCIATE"));
	}

	@Test
	void gradeWiseAgentCountMapsRows() {
		when(qualityAuditorRatingRepo.getGradeWiseAgentCount(1, "Monthly", "Jan"))
				.thenReturn(List.<String[]>of(new String[] { "A", "4" }));

		assertEquals(4, service.gradeWiseAgentCount(1, "Monthly", "Jan").get(0).getValue());
	}

	@Test
	void gradeWiseAgentCountWrapsFailure() {
		when(qualityAuditorRatingRepo.getGradeWiseAgentCount(anyInt(), anyString(), anyString()))
				.thenThrow(new IllegalStateException("db"));

		assertThrows(ECDException.class, () -> service.gradeWiseAgentCount(1, "Monthly", "Jan"));
	}
}
