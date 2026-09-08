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
package com.iemr.ecd.controller.outboundworklist;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.iemr.ecd.dao_temp.FetchChildOutboundWorklist;
import com.iemr.ecd.dao_temp.FetchMotherOutboundWorklist;
import com.iemr.ecd.dto.associate.CallStatisticsDTO;
import com.iemr.ecd.service.outbound_worklist.CallStatisticsImpl;
import com.iemr.ecd.service.outbound_worklist.OutboundWorkListServiceImpl;

@ExtendWith(MockitoExtension.class)
class OutboundWorklistControllersTest {

	@Mock
	private OutboundWorkListServiceImpl outboundWorkListServiceImpl;
	@Mock
	private CallStatisticsImpl callStatisticsImpl;

	@InjectMocks
	private OutBoundWorklistController outBoundWorklistController;
	@InjectMocks
	private CallStatisticsController callStatisticsController;

	@Test
	void getMotherWorklistSerialisesServiceResponse() throws Exception {
		FetchMotherOutboundWorklist worklist = new FetchMotherOutboundWorklist();
		when(outboundWorkListServiceImpl.getMotherWorkList(4)).thenReturn(List.of(worklist));

		ResponseEntity<String> response = outBoundWorklistController.getMotherWorklist(4);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertTrue(response.getBody().startsWith("[{"));
	}

	@Test
	void getMotherWorklistSerialisesEmptyList() throws Exception {
		when(outboundWorkListServiceImpl.getMotherWorkList(4)).thenReturn(List.of());

		assertEquals("[]", outBoundWorklistController.getMotherWorklist(4).getBody());
	}

	@Test
	void getChildWorklistDelegatesToService() {
		List<FetchChildOutboundWorklist> worklist = List.of(new FetchChildOutboundWorklist());
		when(outboundWorkListServiceImpl.getChildWorkList(4)).thenReturn(worklist);

		assertSame(worklist, outBoundWorklistController.getChildWorklist(4).getBody());
	}

	@Test
	void getCallStatisticsDelegatesToService() {
		CallStatisticsDTO dto = new CallStatisticsDTO();
		when(callStatisticsImpl.getCallStatisticsByAgentId("agent-1")).thenReturn(dto);

		ResponseEntity<CallStatisticsDTO> response = callStatisticsController
				.getCallStatisticsByAgentId("agent-1");

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertSame(dto, response.getBody());
	}
}
