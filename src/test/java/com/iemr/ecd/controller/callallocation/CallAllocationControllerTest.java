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

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.iemr.ecd.dto.OutboundCallsDTO;
import com.iemr.ecd.dto.RequestCallAllocationDTO;
import com.iemr.ecd.dto.supervisor.ResponseEligibleCallRecordsDTO;
import com.iemr.ecd.service.call_conf_allocation.CallAllocationImpl;

@ExtendWith(MockitoExtension.class)
class CallAllocationControllerTest {

	@Mock
	private CallAllocationImpl callAllocationImpl;

	@InjectMocks
	private CallAllocationController controller;

	@Test
	void allocateCallsDelegatesToService() throws Exception {
		RequestCallAllocationDTO request = new RequestCallAllocationDTO();
		when(callAllocationImpl.allocateCalls(request)).thenReturn("allocated");

		ResponseEntity<Object> response = controller.allocateCalls(request);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals("allocated", response.getBody());
	}

	@Test
	void getEligibleRecordsInfoDelegatesToService() throws Exception {
		ResponseEligibleCallRecordsDTO dto = new ResponseEligibleCallRecordsDTO();
		when(callAllocationImpl.getEligibleRecordsInfo(1, "primary", "Mother", "2024-01-01", "2024-01-31"))
				.thenReturn(dto);

		ResponseEntity<ResponseEligibleCallRecordsDTO> response = controller.getEligibleRecordsInfo(1, "primary",
				"Mother", "2024-01-01", "2024-01-31");

		assertSame(dto, response.getBody());
	}

	@Test
	void getAllocatedCallCountUserDelegatesToService() {
		RequestCallAllocationDTO request = new RequestCallAllocationDTO();
		when(callAllocationImpl.getAllocatedCallCountUser(request)).thenReturn("5");

		assertEquals("5", controller.getAllocatedCallCountUser(request).getBody());
	}

	@Test
	void reAllocateCallsDelegatesToService() {
		RequestCallAllocationDTO request = new RequestCallAllocationDTO();
		when(callAllocationImpl.reAllocateCalls(request)).thenReturn("reallocated");

		assertEquals("reallocated", controller.reAllocateCalls(request).getBody());
	}

	@Test
	void moveToBinDelegatesToService() {
		RequestCallAllocationDTO request = new RequestCallAllocationDTO();
		when(callAllocationImpl.moveAllocatedCallsToBin(request)).thenReturn("moved");

		assertEquals("moved", controller.updateAlerts(request).getBody());
	}

	@Test
	void insertRecordsInOutboundCallsDelegatesToService() {
		OutboundCallsDTO request = new OutboundCallsDTO();
		when(callAllocationImpl.insertRecordsInOutboundCalls(request)).thenReturn("inserted");

		assertEquals("inserted", controller.insertRecordsInOutboundCalls(request).getBody());
	}

	@Test
	void getEligibleRecordsLanguageInfoDelegatesToService() {
		ResponseEligibleCallRecordsDTO dto = new ResponseEligibleCallRecordsDTO();
		when(callAllocationImpl.getEligibleRecordsLanguageInfo(1, "primary", "Mother", "2024-01-01", "2024-01-31",
				"Hindi", "ASSOCIATE")).thenReturn(dto);

		ResponseEntity<ResponseEligibleCallRecordsDTO> response = controller.getEligibleRecordsLanguageInfo(1,
				"primary", "Mother", "2024-01-01", "2024-01-31", "Hindi", "ASSOCIATE");

		assertSame(dto, response.getBody());
	}
}
