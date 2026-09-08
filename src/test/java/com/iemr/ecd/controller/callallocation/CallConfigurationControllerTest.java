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
package com.iemr.ecd.controller.callallocation;

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

import com.iemr.ecd.dao.CallConfiguration;
import com.iemr.ecd.dao.V_GetCallSectionMapping;
import com.iemr.ecd.dto.CallSectionMappingDTO;
import com.iemr.ecd.service.call_conf_allocation.CallConfigurationImpl;

@ExtendWith(MockitoExtension.class)
class CallConfigurationControllerTest {

	@Mock
	private CallConfigurationImpl callConfigurationImpl;

	@InjectMocks
	private CallConfigurationController controller;

	@Test
	void createDelegatesToService() {
		List<CallConfiguration> configurations = List.of(new CallConfiguration());
		when(callConfigurationImpl.createCallConfigurations(configurations)).thenReturn(configurations);

		ResponseEntity<List<CallConfiguration>> response = controller.createCallConfiguration(configurations);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertSame(configurations, response.getBody());
	}

	@Test
	void getByIdDelegatesToService() {
		CallConfiguration configuration = new CallConfiguration();
		when(callConfigurationImpl.getCallConfigurationById(3L)).thenReturn(configuration);

		assertSame(configuration, controller.getCallConfigurationById(3L).getBody());
	}

	@Test
	void getByPsmIdDelegatesToService() {
		List<CallConfiguration> configurations = List.of(new CallConfiguration());
		when(callConfigurationImpl.getCallConfigurationByPSMID(2)).thenReturn(configurations);

		assertSame(configurations, controller.getCallConfigurationByPSMId(2).getBody());
	}

	@Test
	void updateDelegatesToService() {
		List<CallConfiguration> configurations = List.of(new CallConfiguration());
		when(callConfigurationImpl.updateCallConfigurations(configurations)).thenReturn(configurations);

		assertSame(configurations, controller.updateCallConfiguration(configurations).getBody());
	}

	@Test
	void mapCallAndSectionDelegatesToService() {
		CallSectionMappingDTO dto = new CallSectionMappingDTO();
		when(callConfigurationImpl.mapCallAndSection(dto)).thenReturn("mapped");

		assertEquals("mapped", controller.mapCallAndSection(dto).getBody());
	}

	@Test
	void getCallAndSectionMapDelegatesToService() {
		List<V_GetCallSectionMapping> mappings = List.of(new V_GetCallSectionMapping());
		when(callConfigurationImpl.getCallAndSectionMapByPsmIdAndCallConfigId(1, 2)).thenReturn(mappings);

		assertSame(mappings, controller.getCallAndSectionMapByPsmIdAndCallConfigId(1, 2).getBody());
	}
}
