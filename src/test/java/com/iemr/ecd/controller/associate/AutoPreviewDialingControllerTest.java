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
package com.iemr.ecd.controller.associate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.iemr.ecd.dao.masters.UserServiceRoleMapping;
import com.iemr.ecd.dao_temp.V_GetDialPreference;
import com.iemr.ecd.dto.ResponseAutoPreviewDialingDTO;
import com.iemr.ecd.dto.ResponseGetRatingsDTO;
import com.iemr.ecd.service.associate.AutoPreviewDialingImpl;

@ExtendWith(MockitoExtension.class)
class AutoPreviewDialingControllerTest {

	@Mock
	private AutoPreviewDialingImpl autoPreviewDialingImpl;

	@InjectMocks
	private AutoPreviewDialingController controller;

	@Test
	void addDialPreferenceDelegatesToService() {
		UserServiceRoleMapping request = new UserServiceRoleMapping();
		when(autoPreviewDialingImpl.addDialPreference(request)).thenReturn("saved");

		ResponseEntity<Object> response = controller.addDialPreference(request);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals("saved", response.getBody());
	}

	@Test
	void getDialPreferenceDelegatesToService() {
		List<V_GetDialPreference> preferences = List.of(new V_GetDialPreference());
		when(autoPreviewDialingImpl.getDialPreference(1)).thenReturn(preferences);

		ResponseEntity<List<V_GetDialPreference>> response = controller.getDialPreference(1);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertSame(preferences, response.getBody());
	}

	@Test
	void getAutoPreviewDialingDelegatesToService() {
		ResponseAutoPreviewDialingDTO dto = new ResponseAutoPreviewDialingDTO();
		when(autoPreviewDialingImpl.getAutoPreviewDialingByUserIdAndRoleIdAndPsmId(1, 2, 3)).thenReturn(dto);

		ResponseEntity<Object> response = controller.getAutoPreviewDialingByUserIdAndRoleIdAndPsmId(1, 2, 3);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertSame(dto, response.getBody());
	}

	@Test
	void getRatingsDelegatesToService() {
		List<ResponseGetRatingsDTO> ratings = List.of();
		when(autoPreviewDialingImpl.getRatingsByUserIdAndPsmId(1, 2)).thenReturn(ratings);

		ResponseEntity<Object> response = controller.getRatingsByUserIdAndPsmId(1, 2);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertSame(ratings, response.getBody());
	}
}
