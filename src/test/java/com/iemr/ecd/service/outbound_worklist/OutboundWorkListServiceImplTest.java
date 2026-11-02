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
package com.iemr.ecd.service.outbound_worklist;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.iemr.ecd.dao_temp.FetchChildOutboundWorklist;
import com.iemr.ecd.dao_temp.FetchMotherOutboundWorklist;
import com.iemr.ecd.repo.call_conf_allocation.OutboundCallsRepo;
import com.iemr.ecd.utils.advice.exception_handler.ECDException;

@ExtendWith(MockitoExtension.class)
class OutboundWorkListServiceImplTest {

	@Mock
	private OutboundCallsRepo outboundCallsRepo;

	@InjectMocks
	private OutboundWorkListServiceImpl service;

	private String[] motherRow() {
		return new String[] { "1", "2", "3", "4", "ANC1", "2024-01-01 00:00:00", "2", "ANC Call 1", "Asha Devi",
				"MCTS-1", "Self", "9999999999", "true", "Husband", "Address", "Block", "PHC", "SubFacility",
				"2024-02-01 00:00:00", "Asha", "ANM", "2024-03-01 00:00:00", "5", "2024-04-01 00:00:00",
				"2024-05-01 00:00:00", "Female", "10", "State", "20", "District", "30", "BlockName", "40",
				"Village", "8888888888", "7777777777", "6666666666", "28" };
	}

	private String[] childRow() {
		return new String[] { "1", "CHILD1", "Child Call 1", "2024-01-01 00:00:00", "3", "MCTS-C1", "Baby",
				"MOTHER-1", "Asha Devi", "Self", "9999999999", "11", "Address", "Block", "PHC", "SubFacility",
				"Asha", "ANM", "2024-02-01 00:00:00", "2024-03-01 00:00:00", "true", "4",
				"2024-04-01 00:00:00", "Father", "Male", "10", "State", "20", "District", "30", "BlockName", "40",
				"Village", "8888888888", "7777777777", "6666666666" };
	}

	@Test
	void motherWorkListMapsEveryColumn() {
		when(outboundCallsRepo.getAgentAllocatedMotherList(4)).thenReturn(List.<String[]>of(motherRow()));

		FetchMotherOutboundWorklist row = service.getMotherWorkList(4).get(0);

		assertEquals(1L, row.getObCallId());
		assertEquals(2L, row.getBeneficiaryRegId());
		assertEquals(3, row.getAllocatedUserId());
		assertEquals(4, row.getProviderServiceMapId());
		assertEquals("ANC1", row.getOutboundCallType());
		assertEquals(Timestamp.valueOf("2024-01-01 00:00:00"), row.getCallDateFrom());
		assertEquals(2, row.getNoOfTrials());
		assertEquals("ANC Call 1", row.getDisplayOBCallType());
		assertEquals("Asha Devi", row.getName());
		assertEquals("MCTS-1", row.getMCTSIdNo());
		assertEquals("Self", row.getPhoneNoOfWhom());
		assertEquals("9999999999", row.getWhomPhoneNo());
		assertTrue(row.getHighRisk());
		assertEquals("Husband", row.getHusbandName());
		assertEquals("Address", row.getAddress());
		assertEquals("Block", row.getHealthBlock());
		assertEquals("PHC", row.getPhcName());
		assertEquals("SubFacility", row.getSubFacility());
		assertEquals(Timestamp.valueOf("2024-02-01 00:00:00"), row.getLmpDate());
		assertEquals("Asha", row.getAshaName());
		assertEquals("ANM", row.getAnmName());
		assertEquals(Timestamp.valueOf("2024-03-01 00:00:00"), row.getNextCallDate());
		assertEquals("5 days", row.getLapseTime());
		assertEquals(Timestamp.valueOf("2024-04-01 00:00:00"), row.getRecordUploadDate());
		assertEquals(Timestamp.valueOf("2024-05-01 00:00:00"), row.getEdd());
		assertEquals("Female", row.getGender());
		assertEquals(10, row.getStateId());
		assertEquals("State", row.getStateName());
		assertEquals(20, row.getDistrictId());
		assertEquals("District", row.getDistrictName());
		assertEquals(30, row.getBlockId());
		assertEquals("BlockName", row.getBlockName());
		assertEquals(40, row.getDistrictBranchId());
		assertEquals("Village", row.getVillageName());
		assertEquals("8888888888", row.getAlternatePhoneNo());
		assertEquals("7777777777", row.getAshaPhoneNo());
		assertEquals("6666666666", row.getAnmPhoneNo());
		assertEquals(28, row.getAge());
	}

	@Test
	void motherWorkListSkipsNullColumns() {
		when(outboundCallsRepo.getAgentAllocatedMotherList(4)).thenReturn(List.<String[]>of(new String[38]));

		FetchMotherOutboundWorklist row = service.getMotherWorkList(4).get(0);

		assertNull(row.getObCallId());
		assertNull(row.getName());
	}

	@Test
	void motherWorkListDropsRowsThatFailToParse() {
		String[] broken = motherRow();
		broken[0] = "not-a-number";
		when(outboundCallsRepo.getAgentAllocatedMotherList(4)).thenReturn(List.<String[]>of(broken));

		assertTrue(service.getMotherWorkList(4).isEmpty());
	}

	@Test
	void motherWorkListHandlesEmptyAndNullResults() {
		when(outboundCallsRepo.getAgentAllocatedMotherList(anyInt())).thenReturn(List.of(), null);

		assertTrue(service.getMotherWorkList(4).isEmpty());
		assertTrue(service.getMotherWorkList(4).isEmpty());
	}

	@Test
	void motherWorkListWrapsRepositoryFailure() {
		when(outboundCallsRepo.getAgentAllocatedMotherList(anyInt())).thenThrow(new IllegalStateException("db"));

		assertThrows(ECDException.class, () -> service.getMotherWorkList(4));
	}

	@Test
	void childWorkListMapsEveryColumn() {
		when(outboundCallsRepo.getAgentAllocatedChildList(4)).thenReturn(List.<String[]>of(childRow()));

		FetchChildOutboundWorklist row = service.getChildWorkList(4).get(0);

		assertEquals(1L, row.getBeneficiaryRegId());
		assertEquals("CHILD1", row.getOutboundCallType());
		assertEquals("Child Call 1", row.getDisplayOBCallType());
		assertEquals(Timestamp.valueOf("2024-01-01 00:00:00"), row.getCallDateFrom());
		assertEquals(3, row.getNoOfTrials());
		assertEquals("MCTS-C1", row.getMCTSIdNoChildId());
		assertEquals("Baby", row.getChildName());
		assertEquals("MOTHER-1", row.getMotherId());
		assertEquals("Asha Devi", row.getMotherName());
		assertEquals("Self", row.getPhoneNoOf());
		assertEquals("9999999999", row.getPhoneNo());
		assertEquals(11L, row.getObCallId());
		assertEquals("Address", row.getAddress());
		assertEquals("Block", row.getHealthBlock());
		assertEquals("PHC", row.getPhcName());
		assertEquals("SubFacility", row.getSubFacility());
		assertEquals("Asha", row.getAshaName());
		assertEquals("ANM", row.getAnmName());
		assertEquals(Timestamp.valueOf("2024-02-01 00:00:00"), row.getDob());
		assertEquals(Timestamp.valueOf("2024-03-01 00:00:00"), row.getNextCallDate());
		assertTrue(row.getIsHrni());
		assertEquals("4 days", row.getLapseTime());
		assertEquals(Timestamp.valueOf("2024-04-01 00:00:00"), row.getRecordUploadDate());
		assertEquals("Father", row.getFatherName());
		assertEquals("Male", row.getGender());
		assertEquals(10, row.getStateId());
		assertEquals("State", row.getStateName());
		assertEquals(20, row.getDistrictId());
		assertEquals("District", row.getDistrictName());
		assertEquals(30, row.getBlockId());
		assertEquals("BlockName", row.getBlockName());
		assertEquals(40, row.getDistrictBranchId());
		assertEquals("8888888888", row.getVillageName());
		assertEquals("8888888888", row.getAlternatePhoneNo());
		assertEquals("7777777777", row.getAshaPhoneNo());
		assertEquals("6666666666", row.getAnmPhoneNo());
	}

	@Test
	void childWorkListSkipsNullColumns() {
		when(outboundCallsRepo.getAgentAllocatedChildList(4)).thenReturn(List.<String[]>of(new String[36]));

		FetchChildOutboundWorklist row = service.getChildWorkList(4).get(0);

		assertNull(row.getBeneficiaryRegId());
		assertNull(row.getChildName());
	}

	@Test
	void childWorkListWrapsRowParsingFailure() {
		String[] broken = childRow();
		broken[0] = "not-a-number";
		when(outboundCallsRepo.getAgentAllocatedChildList(4)).thenReturn(List.<String[]>of(broken));

		assertThrows(ECDException.class, () -> service.getChildWorkList(4));
	}

	@Test
	void childWorkListHandlesEmptyAndNullResults() {
		when(outboundCallsRepo.getAgentAllocatedChildList(anyInt())).thenReturn(List.of(), null);

		assertTrue(service.getChildWorkList(4).isEmpty());
		assertTrue(service.getChildWorkList(4).isEmpty());
	}

	@Test
	void childWorkListWrapsRepositoryFailure() {
		when(outboundCallsRepo.getAgentAllocatedChildList(anyInt())).thenThrow(new IllegalStateException("db"));

		assertThrows(ECDException.class, () -> service.getChildWorkList(4));
	}
}
