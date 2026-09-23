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
package com.iemr.ecd.service.call_conf_allocation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import com.iemr.ecd.dao.CallConfiguration;
import com.iemr.ecd.dao.CallSectionMapping;
import com.iemr.ecd.dao.V_GetCallSectionMapping;
import com.iemr.ecd.dto.CallSectionMappingDTO;
import com.iemr.ecd.repo.call_conf_allocation.CallConfigurationRepo;
import com.iemr.ecd.repo.call_conf_allocation.CallSectionMappingRepo;
import com.iemr.ecd.repo.call_conf_allocation.V_GetCallSectionMappingRepo;
import com.iemr.ecd.utils.advice.exception_handler.ECDException;

import io.micrometer.observation.ObservationRegistry;

@ExtendWith(MockitoExtension.class)
class CallConfigurationImplTest {

	@Mock
	private CallConfigurationRepo callConfigurationRepo;
	@Mock
	private CallSectionMappingRepo callSectionMappingRepo;
	@Mock
	private V_GetCallSectionMappingRepo vGetCallSectionMappingRepo;

	private CallConfigurationImpl service;

	@BeforeEach
	void setUp() {
		service = new CallConfigurationImpl();
		ReflectionTestUtils.setField(service, "registry", ObservationRegistry.create());
		ReflectionTestUtils.setField(service, "callConfigurationRepo", callConfigurationRepo);
		ReflectionTestUtils.setField(service, "callSectionMappingRepo", callSectionMappingRepo);
		ReflectionTestUtils.setField(service, "v_GetCallSectionMappingRepo", vGetCallSectionMappingRepo);
	}

	@Test
	void createStampsConfigIdAndSaves() {
		CallConfiguration configuration = new CallConfiguration();
		List<CallConfiguration> configurations = List.of(configuration);
		when(callConfigurationRepo.saveAll(configurations)).thenReturn(configurations);

		List<CallConfiguration> saved = service.createCallConfigurations(configurations);

		assertSame(configurations, saved);
		assertNotNull(configuration.getConfigId());
	}

	@Test
	void createAcceptsEmptyList() {
		when(callConfigurationRepo.saveAll(List.of())).thenReturn(List.of());

		assertTrue(service.createCallConfigurations(List.of()).isEmpty());
	}

	@Test
	void createAcceptsNullList() {
		when(callConfigurationRepo.saveAll(null)).thenReturn(List.of());

		assertTrue(service.createCallConfigurations(null).isEmpty());
	}

	@Test
	void createWrapsRepositoryFailure() {
		List<CallConfiguration> configurations = List.of(new CallConfiguration());
		when(callConfigurationRepo.saveAll(configurations)).thenThrow(new IllegalStateException("db"));

		assertThrows(ECDException.class, () -> service.createCallConfigurations(configurations));
	}

	@Test
	void getCallConfigurationsReturnsTopTen() {
		List<CallConfiguration> configurations = List.of(new CallConfiguration());
		when(callConfigurationRepo.findTop10ByDeleted(false)).thenReturn(configurations);

		assertSame(configurations, service.getCallConfigurations());
	}

	@Test
	void getCallConfigurationsWrapsRepositoryFailure() {
		when(callConfigurationRepo.findTop10ByDeleted(anyBoolean())).thenThrow(new IllegalStateException("db"));

		assertThrows(ECDException.class, () -> service.getCallConfigurations());
	}

	@Test
	void getByIdDelegatesToRepository() {
		CallConfiguration configuration = new CallConfiguration();
		when(callConfigurationRepo.findByCallConfigId(4L)).thenReturn(configuration);

		assertSame(configuration, service.getCallConfigurationById(4L));
	}

	@Test
	void getByIdWrapsRepositoryFailure() {
		when(callConfigurationRepo.findByCallConfigId(anyLong())).thenThrow(new IllegalStateException("db"));

		assertThrows(ECDException.class, () -> service.getCallConfigurationById(4L));
	}

	@Test
	void getByPsmIdDelegatesToRepository() {
		List<CallConfiguration> configurations = List.of(new CallConfiguration());
		when(callConfigurationRepo.findByPsmIdAndDeleted(1, false)).thenReturn(configurations);

		assertSame(configurations, service.getCallConfigurationByPSMID(1));
	}

	@Test
	void getByPsmIdWrapsRepositoryFailure() {
		when(callConfigurationRepo.findByPsmIdAndDeleted(anyInt(), anyBoolean()))
				.thenThrow(new IllegalStateException("db"));

		assertThrows(ECDException.class, () -> service.getCallConfigurationByPSMID(1));
	}

	@Test
	void updateDelegatesToRepository() {
		List<CallConfiguration> configurations = List.of(new CallConfiguration());
		when(callConfigurationRepo.saveAll(configurations)).thenReturn(configurations);

		assertSame(configurations, service.updateCallConfigurations(configurations));
	}

	@Test
	void updateWrapsRepositoryFailure() {
		List<CallConfiguration> configurations = List.of(new CallConfiguration());
		when(callConfigurationRepo.saveAll(configurations)).thenThrow(new IllegalStateException("db"));

		assertThrows(ECDException.class, () -> service.updateCallConfigurations(configurations));
	}

	@Test
	void mapCallAndSectionStampsCallConfigIdAndSaves() {
		CallSectionMapping mapping = new CallSectionMapping();
		CallSectionMappingDTO request = new CallSectionMappingDTO();
		request.setCallConfigId(88);
		request.setSections(List.of(mapping));

		String response = service.mapCallAndSection(request);

		verify(callSectionMappingRepo).saveAll(request.getSections());
		assertEquals(88, mapping.getCallConfigId());
		assertTrue(response.contains("Call section mapping done successfully"));
	}

	@Test
	void mapCallAndSectionRejectsEmptyRequest() {
		CallSectionMappingDTO empty = new CallSectionMappingDTO();
		empty.setSections(List.of());

		assertThrows(ECDException.class, () -> service.mapCallAndSection(empty));
		assertThrows(ECDException.class, () -> service.mapCallAndSection(new CallSectionMappingDTO()));
		assertThrows(ECDException.class, () -> service.mapCallAndSection(null));
		verify(callSectionMappingRepo, never()).saveAll(any());
	}

	@Test
	void getCallAndSectionMapByCallConfigIdCollectsViewRows() {
		CallSectionMapping mapping = new CallSectionMapping();
		mapping.setCallConfigId(88);
		mapping.setSectionId(3);
		mapping.setPsmId(1);
		V_GetCallSectionMapping row = new V_GetCallSectionMapping();
		when(callSectionMappingRepo.findByPsmIdAndCallConfigIdAndDeletedOrderByLastModDateDesc(1, 88, false))
				.thenReturn(List.of(mapping));
		when(vGetCallSectionMappingRepo.findByCallConfigIdAndSectionIdAndPsmId(88, 3, 1)).thenReturn(List.of(row));

		List<V_GetCallSectionMapping> response = service.getCallAndSectionMapByPsmIdAndCallConfigId(1, 88);

		assertEquals(List.of(row), response);
	}

	@Test
	void getCallAndSectionMapByCallConfigIdSkipsEmptyViewRows() {
		CallSectionMapping mapping = new CallSectionMapping();
		when(callSectionMappingRepo.findByPsmIdAndCallConfigIdAndDeletedOrderByLastModDateDesc(1, 88, false))
				.thenReturn(List.of(mapping));
		when(vGetCallSectionMappingRepo.findByCallConfigIdAndSectionIdAndPsmId(null, null, null))
				.thenReturn(List.of());

		assertTrue(service.getCallAndSectionMapByPsmIdAndCallConfigId(1, 88).isEmpty());
	}

	@Test
	void getCallAndSectionMapByCallConfigIdHandlesNoMappings() {
		when(callSectionMappingRepo.findByPsmIdAndCallConfigIdAndDeletedOrderByLastModDateDesc(1, 88, false))
				.thenReturn(List.of());

		assertTrue(service.getCallAndSectionMapByPsmIdAndCallConfigId(1, 88).isEmpty());
	}

	@Test
	void getCallAndSectionMapWithoutCallConfigIdUsesPsmIdOnly() {
		CallSectionMapping mapping = new CallSectionMapping();
		mapping.setSectionId(3);
		mapping.setPsmId(1);
		V_GetCallSectionMapping row = new V_GetCallSectionMapping();
		when(callSectionMappingRepo.findByPsmIdAndDeletedOrderByLastModDateDesc(1, false))
				.thenReturn(List.of(mapping));
		when(vGetCallSectionMappingRepo.findBySectionIdAndPsmId(3, 1)).thenReturn(List.of(row));

		assertEquals(List.of(row), service.getCallAndSectionMapByPsmIdAndCallConfigId(1, null));
	}

	@Test
	void getCallAndSectionMapWithZeroCallConfigIdUsesPsmIdOnly() {
		when(callSectionMappingRepo.findByPsmIdAndDeletedOrderByLastModDateDesc(1, false)).thenReturn(null);

		assertTrue(service.getCallAndSectionMapByPsmIdAndCallConfigId(1, 0).isEmpty());
	}

	@Test
	void getCallAndSectionMapWrapsRepositoryFailure() {
		when(callSectionMappingRepo.findByPsmIdAndDeletedOrderByLastModDateDesc(anyInt(), anyBoolean()))
				.thenThrow(new IllegalStateException("db"));

		assertThrows(ECDException.class, () -> service.getCallAndSectionMapByPsmIdAndCallConfigId(1, null));
	}
}
