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
package com.iemr.ecd.service.associate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.iemr.ecd.dao.QualityAuditorRating;
import com.iemr.ecd.dao.masters.UserServiceRoleMapping;
import com.iemr.ecd.dao_temp.V_GetDialPreference;
import com.iemr.ecd.dto.ResponseAutoPreviewDialingDTO;
import com.iemr.ecd.dto.ResponseGetRatingsDTO;
import com.iemr.ecd.repository.ecd.V_GetDialPreferenceRepo;
import com.iemr.ecd.repository.masters.UserServiceRoleMappingRepo;
import com.iemr.ecd.repository.quality.QualityAuditorRatingRepo;
import com.iemr.ecd.utils.advice.exception_handler.ECDException;

@ExtendWith(MockitoExtension.class)
class AutoPreviewDialingImplTest {

	@Mock
	private UserServiceRoleMappingRepo userServiceRoleMappingRepo;
	@Mock
	private QualityAuditorRatingRepo qualityAuditorRatingRepo;
	@Mock
	private V_GetDialPreferenceRepo vGetDialPreferenceRepo;

	@InjectMocks
	private AutoPreviewDialingImpl service;

	private UserServiceRoleMapping request() {
		UserServiceRoleMapping request = new UserServiceRoleMapping();
		request.setUserId(7);
		request.setRoleId(3);
		request.setPsmId(1);
		request.setIsDialPreference(true);
		request.setPreviewWindowTime(15);
		return request;
	}

	@Test
	void addDialPreferenceUpdatesExistingMapping() {
		UserServiceRoleMapping existing = new UserServiceRoleMapping();
		when(userServiceRoleMappingRepo.findByUserIdAndRoleIdAndPsmIdAndDeleted(7, 3, 1, false))
				.thenReturn(existing);

		String response = service.addDialPreference(request());

		verify(userServiceRoleMappingRepo).save(existing);
		assertEquals(true, existing.getIsAutoPreviewDial());
		assertEquals(15, existing.getPreviewWindowTime());
		assertTrue(response.contains("Dial Preference Added Successfully"));
	}

	@Test
	void addDialPreferenceRejectsUnknownMapping() {
		when(userServiceRoleMappingRepo.findByUserIdAndRoleIdAndPsmIdAndDeleted(7, 3, 1, false)).thenReturn(null);

		assertThrows(ECDException.class, () -> service.addDialPreference(request()));
		verify(userServiceRoleMappingRepo, never()).save(any());
	}

	@Test
	void getDialPreferenceDelegatesToRepository() {
		List<V_GetDialPreference> preferences = List.of(new V_GetDialPreference());
		when(vGetDialPreferenceRepo.findByPsmId(1)).thenReturn(preferences);

		assertSame(preferences, service.getDialPreference(1));
	}

	@Test
	void getDialPreferenceWrapsFailure() {
		when(vGetDialPreferenceRepo.findByPsmId(anyInt())).thenThrow(new IllegalStateException("db"));

		assertThrows(ECDException.class, () -> service.getDialPreference(1));
	}

	@Test
	void getAutoPreviewDialingReturnsMappingValues() {
		UserServiceRoleMapping mapping = new UserServiceRoleMapping();
		mapping.setIsAutoPreviewDial(true);
		mapping.setPreviewWindowTime(20);
		when(userServiceRoleMappingRepo.findByUserIdAndRoleIdAndPsmIdAndDeleted(7, 3, 1, false)).thenReturn(mapping);

		ResponseAutoPreviewDialingDTO response = service.getAutoPreviewDialingByUserIdAndRoleIdAndPsmId(7, 3, 1);

		assertEquals(true, response.getIsAutoPreviewDial());
		assertEquals(20, response.getPreviewWindowTime());
	}

	@Test
	void getAutoPreviewDialingReturnsEmptyDtoWhenMappingMissing() {
		when(userServiceRoleMappingRepo.findByUserIdAndRoleIdAndPsmIdAndDeleted(7, 3, 1, false)).thenReturn(null);

		ResponseAutoPreviewDialingDTO response = service.getAutoPreviewDialingByUserIdAndRoleIdAndPsmId(7, 3, 1);

		assertNull(response.getIsAutoPreviewDial());
		assertNull(response.getPreviewWindowTime());
	}

	@Test
	void getAutoPreviewDialingWrapsFailure() {
		when(userServiceRoleMappingRepo.findByUserIdAndRoleIdAndPsmIdAndDeleted(anyInt(), anyInt(), anyInt(),
				anyBoolean())).thenThrow(new IllegalStateException("db"));

		assertThrows(ECDException.class, () -> service.getAutoPreviewDialingByUserIdAndRoleIdAndPsmId(7, 3, 1));
	}

	@Test
	void getRatingsMapsRatingsToResponse() {
		QualityAuditorRating rating = new QualityAuditorRating();
		rating.setFinalScore(88);
		rating.setFinalGrade("A");
		rating.setCreatedDate(new Timestamp(0));
		when(qualityAuditorRatingRepo.getLatestRatingsByAgentIdAndPsmId(7, 1)).thenReturn(List.of(rating));

		List<ResponseGetRatingsDTO> response = service.getRatingsByUserIdAndPsmId(7, 1);

		assertEquals(1, response.size());
		assertEquals("88%", response.get(0).getScoreInPercentage());
		assertEquals("A", response.get(0).getGrade());
		assertEquals(new Timestamp(0), response.get(0).getDate());
	}

	@Test
	void getRatingsReturnsEmptyListWhenNoRatings() {
		when(qualityAuditorRatingRepo.getLatestRatingsByAgentIdAndPsmId(7, 1)).thenReturn(null);

		assertTrue(service.getRatingsByUserIdAndPsmId(7, 1).isEmpty());
	}

	@Test
	void getRatingsWrapsFailure() {
		when(qualityAuditorRatingRepo.getLatestRatingsByAgentIdAndPsmId(anyInt(), anyInt()))
				.thenThrow(new IllegalStateException("db"));

		assertThrows(ECDException.class, () -> service.getRatingsByUserIdAndPsmId(7, 1));
	}
}
