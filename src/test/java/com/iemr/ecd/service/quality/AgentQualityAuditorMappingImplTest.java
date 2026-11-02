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
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.iemr.ecd.dao.AgentQualityAuditorMap;
import com.iemr.ecd.repository.quality.AgentQualityAuditorMapRepo;
import com.iemr.ecd.utils.advice.exception_handler.ECDException;

@ExtendWith(MockitoExtension.class)
class AgentQualityAuditorMappingImplTest {

	@Mock
	private AgentQualityAuditorMapRepo agentQualityAuditorMapRepo;

	@InjectMocks
	private AgentQualityAuditorMappingImpl service;

	@SuppressWarnings("unchecked")
	@Test
	void createExpandsAgentIdsIntoIndividualMappings() {
		AgentQualityAuditorMap request = new AgentQualityAuditorMap();
		request.setAgentIds(new Integer[] { 11, 12 });
		request.setAgentNames(new String[] { "Agent One", "Agent Two" });
		request.setQualityAuditorId(5);
		request.setQualityAuditorName("Auditor");
		request.setRoleId(3);
		request.setRoleName("QA");
		request.setPsmId(1);
		request.setCreatedBy("admin");

		String response = service.createAgentQualityAuditorMapping(request);

		ArgumentCaptor<List<AgentQualityAuditorMap>> captor = ArgumentCaptor.forClass(List.class);
		verify(agentQualityAuditorMapRepo).saveAll(captor.capture());
		List<AgentQualityAuditorMap> saved = captor.getValue();
		assertEquals(2, saved.size());
		assertEquals(11, saved.get(0).getAgentId());
		assertEquals("Agent One", saved.get(0).getAgentName());
		assertEquals(12, saved.get(1).getAgentId());
		assertEquals("Agent Two", saved.get(1).getAgentName());
		assertEquals("Auditor", saved.get(0).getQualityAuditorName());
		assertEquals(1, saved.get(0).getPsmId());
		assertTrue(response.contains("Agent Quality Auditor Mapping Created Successfully"));
	}

	@SuppressWarnings("unchecked")
	@Test
	void createSavesRequestAsIsWhenNoAgentIdsProvided() {
		AgentQualityAuditorMap request = new AgentQualityAuditorMap();

		service.createAgentQualityAuditorMapping(request);

		ArgumentCaptor<List<AgentQualityAuditorMap>> captor = ArgumentCaptor.forClass(List.class);
		verify(agentQualityAuditorMapRepo).saveAll(captor.capture());
		assertEquals(List.of(request), captor.getValue());
	}

	@SuppressWarnings("unchecked")
	@Test
	void createSavesRequestAsIsWhenAgentIdsEmpty() {
		AgentQualityAuditorMap request = new AgentQualityAuditorMap();
		request.setAgentIds(new Integer[0]);

		service.createAgentQualityAuditorMapping(request);

		ArgumentCaptor<List<AgentQualityAuditorMap>> captor = ArgumentCaptor.forClass(List.class);
		verify(agentQualityAuditorMapRepo).saveAll(captor.capture());
		assertEquals(1, captor.getValue().size());
	}

	@Test
	void createWrapsFailure() {
		AgentQualityAuditorMap request = new AgentQualityAuditorMap();
		request.setAgentIds(new Integer[] { 11 });

		assertThrows(ECDException.class, () -> service.createAgentQualityAuditorMapping(request));
	}

	@Test
	void getByPsmIdReturnsRepositoryResult() {
		List<AgentQualityAuditorMap> maps = List.of(new AgentQualityAuditorMap());
		when(agentQualityAuditorMapRepo.findByPsmId(1)).thenReturn(maps);

		assertSame(maps, service.getAgentQualityAuditorMappingByPSMId(1));
	}

	@Test
	void getByPsmIdWrapsFailure() {
		when(agentQualityAuditorMapRepo.findByPsmId(anyInt())).thenThrow(new IllegalStateException("db"));

		assertThrows(ECDException.class, () -> service.getAgentQualityAuditorMappingByPSMId(1));
	}

	@Test
	void updateSavesWhenIdPresent() {
		AgentQualityAuditorMap map = new AgentQualityAuditorMap();
		map.setId(9);

		String response = service.updateAgentQualityAuditorMapping(map);

		verify(agentQualityAuditorMapRepo).save(map);
		assertTrue(response.contains("Updated Successfully"));
	}

	@Test
	void updateSkipsSaveWhenIdMissing() {
		service.updateAgentQualityAuditorMapping(new AgentQualityAuditorMap());
		service.updateAgentQualityAuditorMapping(null);

		verify(agentQualityAuditorMapRepo, never()).save(any());
	}

	@Test
	void updateWrapsFailure() {
		AgentQualityAuditorMap map = new AgentQualityAuditorMap();
		map.setId(9);
		when(agentQualityAuditorMapRepo.save(map)).thenThrow(new IllegalStateException("db"));

		assertThrows(ECDException.class, () -> service.updateAgentQualityAuditorMapping(map));
	}
}
