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
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import com.iemr.ecd.dao.CallConfiguration;
import com.iemr.ecd.dao.associate.ChildRecord;
import com.iemr.ecd.dao.associate.MotherRecord;
import com.iemr.ecd.dao.associate.OutboundCalls;
import com.iemr.ecd.dto.OutboundCallsDTO;
import com.iemr.ecd.dto.RequestCallAllocationDTO;
import com.iemr.ecd.dto.supervisor.ResponseEligibleCallRecordsDTO;
import com.iemr.ecd.repo.call_conf_allocation.CallConfigurationRepo;
import com.iemr.ecd.repo.call_conf_allocation.ChildRecordRepo;
import com.iemr.ecd.repo.call_conf_allocation.MotherRecordRepo;
import com.iemr.ecd.repo.call_conf_allocation.OutboundCallsRepo;
import com.iemr.ecd.utils.advice.exception_handler.ECDException;
import com.iemr.ecd.utils.constants.Constants;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class CallAllocationImplTest {

	@Mock
	private MotherRecordRepo motherRecordRepo;
	@Mock
	private OutboundCallsRepo outboundCallsRepo;
	@Mock
	private ChildRecordRepo childRecordRepo;
	@Mock
	private CallConfigurationRepo callConfigurationRepo;

	@InjectMocks
	private CallAllocationImpl service;

	private RequestCallAllocationDTO request(String roleName, String recordType) {
		RequestCallAllocationDTO request = new RequestCallAllocationDTO();
		request.setRoleName(roleName);
		request.setRecordType(recordType);
		request.setToUserIds(new Integer[] { 11, 12 });
		request.setNoOfCalls(1);
		request.setUserId(7);
		request.setPsmId(1);
		request.setPhoneNoType("Self");
		request.setCreatedBy("supervisor");
		request.setFDate("2024-01-01T00:00:00");
		request.setTDate("2024-01-31T00:00:00");
		return request;
	}

	private OutboundCalls outboundCall(Long motherId, Long childId) {
		OutboundCalls call = new OutboundCalls();
		call.setObCallId(2L);
		call.setMotherId(motherId);
		call.setChildId(childId);
		call.setEcdCallType("introductory");
		call.setCallAttemptNo(3);
		call.setPhoneNumberType("Self");
		call.setPsmId(1);
		call.setBeneficiaryRegId(99L);
		call.setCreatedBy("supervisor");
		return call;
	}

	private MotherRecord motherRecord() {
		MotherRecord record = new MotherRecord();
		record.setEcdIdNo(3L);
		record.setBeneficiaryRegID(99L);
		record.setWhomPhoneNo("Self");
		record.setHighRisk(true);
		record.setHighRiskReason("Anaemia");
		record.setCreatedDate(Timestamp.valueOf("2024-01-01 00:00:00"));
		record.setLmpDate(Timestamp.valueOf("2024-01-01 00:00:00"));
		return record;
	}

	private ChildRecord childRecord() {
		ChildRecord record = new ChildRecord();
		record.setEcdIdNoChildId(4L);
		record.setMotherId(3L);
		record.setBeneficiaryRegId(99L);
		record.setWhomPhoneNo("Self");
		record.setIsHrni(true);
		record.setHrni_Reason("Low birth weight");
		record.setCreatedDate(Timestamp.valueOf("2024-01-01 00:00:00"));
		record.setDob(Timestamp.valueOf("2024-01-01 00:00:00"));
		return record;
	}

	private CallConfiguration configuration(String callType, String baseLine, String terms) {
		CallConfiguration configuration = new CallConfiguration();
		configuration.setCallConfigId(55L);
		configuration.setCallType(callType);
		configuration.setDisplayName("Display " + callType);
		configuration.setBaseLine(baseLine);
		configuration.setConfigTerms(terms);
		configuration.setTermRange(30);
		configuration.setNoOfAttempts(3);
		return configuration;
	}

	@SuppressWarnings("unchecked")
	@Test
	void allocateMotherRecordsToAssociateCreatesIntroductoryCalls() {
		when(motherRecordRepo.getMotherRecordForAllocation(any(), any(), anyString(), anyInt()))
				.thenReturn(List.of(motherRecord()));

		String response = service.allocateCalls(request("associate", "mother"));

		ArgumentCaptor<List<OutboundCalls>> captor = ArgumentCaptor.forClass(List.class);
		verify(outboundCallsRepo).saveAll(captor.capture());
		OutboundCalls created = captor.getValue().get(0);
		assertEquals(3L, created.getMotherId());
		assertEquals(99L, created.getBeneficiaryRegId());
		assertEquals("introductory", created.getEcdCallType());
		assertEquals(Constants.ALLOCATED, created.getAllocationStatus());
		assertEquals(11, created.getAllocatedUserId());
		assertEquals(true, created.getIsHighRisk());
		assertEquals("Anaemia", created.getHighRiskReason());
		assertEquals(0, created.getCallAttemptNo());
		verify(motherRecordRepo).updateIsAllocatedStatus(List.of(3L));
		assertTrue(response.contains("mother record(s) allocated successfully"));
	}

	@Test
	void allocateMotherRecordsToAssociateUsesLanguagePageWhenLanguageGiven() {
		RequestCallAllocationDTO request = request("associate", "mother");
		request.setPreferredLanguage("Hindi");
		OutboundCalls call = outboundCall(3L, null);
		when(outboundCallsRepo.getMotherRecordsForAssociate(any(), anyString(), anyInt(), any(), any(),
				anyString())).thenReturn(new PageImpl<>(List.of(call)));

		service.allocateCalls(request);

		assertEquals(Constants.ALLOCATED, call.getAllocationStatus());
		assertEquals(11, call.getAllocatedUserId());
		assertEquals(0, call.getCallAttemptNo());
		verify(motherRecordRepo).updateIsAllocatedStatus(List.of(3L));
	}

	@Test
	void allocateMotherRecordsToAssociateFailsWhenNoLanguageRecords() {
		RequestCallAllocationDTO request = request("associate", "mother");
		request.setPreferredLanguage("Hindi");
		when(outboundCallsRepo.getMotherRecordsForAssociate(any(), anyString(), anyInt(), any(), any(),
				anyString())).thenReturn(new PageImpl<>(List.of()));

		assertThrows(ECDException.class, () -> service.allocateCalls(request));
	}

	@Test
	void allocateMotherRecordsToAssociateFailsWhenNoEligibleRecords() {
		when(motherRecordRepo.getMotherRecordForAllocation(any(), any(), anyString(), anyInt()))
				.thenReturn(List.of());

		assertThrows(ECDException.class, () -> service.allocateCalls(request("associate", "mother")));
	}

	@Test
	void allocateCallsRejectsMissingUsersAndDates() {
		RequestCallAllocationDTO noUsers = request("associate", "mother");
		noUsers.setToUserIds(new Integer[0]);
		RequestCallAllocationDTO noCalls = request("associate", "mother");
		noCalls.setNoOfCalls(0);
		RequestCallAllocationDTO noDates = request("associate", "mother");
		noDates.setFDate(null);

		assertThrows(ECDException.class, () -> service.allocateCalls(noUsers));
		assertThrows(ECDException.class, () -> service.allocateCalls(noCalls));
		assertThrows(ECDException.class, () -> service.allocateCalls(noDates));
	}

	@SuppressWarnings("unchecked")
	@Test
	void allocateChildRecordsToAssociateCreatesIntroductoryCalls() {
		when(childRecordRepo.getChildRecordForAllocation(any(), any(), anyString(), anyInt()))
				.thenReturn(List.of(childRecord()));

		String response = service.allocateCalls(request("associate", "child"));

		ArgumentCaptor<List<OutboundCalls>> captor = ArgumentCaptor.forClass(List.class);
		verify(outboundCallsRepo).saveAll(captor.capture());
		OutboundCalls created = captor.getValue().get(0);
		assertEquals(4L, created.getChildId());
		assertEquals(3L, created.getMotherId());
		assertEquals(true, created.getIsHrni());
		assertEquals("Low birth weight", created.getHrniReason());
		verify(childRecordRepo).updateIsAllocatedStatus(List.of(4L));
		assertTrue(response.contains("child record allocated successfully"));
	}

	@Test
	void allocateChildRecordsToAssociateUsesLanguagePageWhenLanguageGiven() {
		RequestCallAllocationDTO request = request("associate", "child");
		request.setPreferredLanguage("Hindi");
		OutboundCalls call = outboundCall(3L, 4L);
		when(outboundCallsRepo.getChildRecordsForAssociate(any(), anyString(), anyInt(), any(), any(), anyString()))
				.thenReturn(new PageImpl<>(List.of(call)));

		service.allocateCalls(request);

		assertEquals(Constants.ALLOCATED, call.getAllocationStatus());
		verify(childRecordRepo).updateIsAllocatedStatus(List.of(4L));
	}

	@Test
	void allocateChildRecordsToAssociateFailsWhenNoRecords() {
		when(childRecordRepo.getChildRecordForAllocation(any(), any(), anyString(), anyInt())).thenReturn(List.of());

		assertThrows(ECDException.class, () -> service.allocateCalls(request("associate", "child")));
	}

	@Test
	void allocateMotherRecordsToAnmMarksPageAllocated() {
		OutboundCalls call = outboundCall(3L, null);
		when(outboundCallsRepo.getMotherRecordsForANM(any(), anyString(), anyInt(), any(), any(), any()))
				.thenReturn(new PageImpl<>(List.of(call)));

		String response = service.allocateCalls(request("ANM", "mother"));

		assertEquals(Constants.ALLOCATED, call.getAllocationStatus());
		assertEquals(0, call.getCallAttemptNo());
		assertTrue(response.contains("mother record allocated successfully to selected ANM"));
	}

	@Test
	void allocateMotherRecordsToAnmFailsWhenNoRecords() {
		when(outboundCallsRepo.getMotherRecordsForANM(any(), anyString(), anyInt(), any(), any(), any()))
				.thenReturn(new PageImpl<>(List.of()));

		assertThrows(ECDException.class, () -> service.allocateCalls(request("ANM", "mother")));
	}

	@Test
	void allocateChildRecordsToAnmMarksPageAllocated() {
		OutboundCalls call = outboundCall(3L, 4L);
		when(outboundCallsRepo.getChildRecordsForANM(any(), anyString(), anyInt(), any(), any(), any()))
				.thenReturn(new PageImpl<>(List.of(call)));

		String response = service.allocateCalls(request("ANM", "child"));

		assertEquals(Constants.ALLOCATED, call.getAllocationStatus());
		assertTrue(response.contains("child record allocated successfully to selected ANM"));
	}

	@Test
	void allocateChildRecordsToAnmFailsWhenNoRecords() {
		when(outboundCallsRepo.getChildRecordsForANM(any(), anyString(), anyInt(), any(), any(), any()))
				.thenReturn(new PageImpl<>(List.of()));

		assertThrows(ECDException.class, () -> service.allocateCalls(request("ANM", "child")));
	}

	@Test
	void allocateMotherRecordsToMedicalOfficerMarksPageAllocated() {
		OutboundCalls call = outboundCall(3L, null);
		when(outboundCallsRepo.getMotherRecordsForMO(any(), anyString(), anyInt(), any(), any()))
				.thenReturn(new PageImpl<>(List.of(call)));

		String response = service.allocateCalls(request("MO", "mother"));

		assertEquals(Constants.ALLOCATED, call.getAllocationStatus());
		assertTrue(response.contains("mother record allocated successfully to selected MO"));
	}

	@Test
	void allocateMotherRecordsToMedicalOfficerFailsWhenNoRecords() {
		when(outboundCallsRepo.getMotherRecordsForMO(any(), anyString(), anyInt(), any(), any()))
				.thenReturn(new PageImpl<>(List.of()));

		assertThrows(ECDException.class, () -> service.allocateCalls(request("MO", "mother")));
	}

	@Test
	void allocateChildRecordsToMedicalOfficerMarksPageAllocated() {
		OutboundCalls call = outboundCall(3L, 4L);
		when(outboundCallsRepo.getChildRecordsForMO(any(), anyString(), anyInt(), any(), any()))
				.thenReturn(new PageImpl<>(List.of(call)));

		String response = service.allocateCalls(request("MO", "child"));

		assertEquals(Constants.ALLOCATED, call.getAllocationStatus());
		assertTrue(response.contains("child record allocated successfully to selected MO"));
	}

	@Test
	void allocateChildRecordsToMedicalOfficerFailsWhenNoRecords() {
		when(outboundCallsRepo.getChildRecordsForMO(any(), anyString(), anyInt(), any(), any()))
				.thenReturn(new PageImpl<>(List.of()));

		assertThrows(ECDException.class, () -> service.allocateCalls(request("MO", "child")));
	}

	@Test
	void allocateCallsRejectsUnknownRoleOrRecordType() {
		assertThrows(ECDException.class, () -> service.allocateCalls(request("SUPERVISOR", "mother")));
		assertThrows(ECDException.class, () -> service.allocateCalls(request("associate", "father")));
	}

	@Test
	void eligibleRecordsInfoAggregatesMotherCounts() {
		when(motherRecordRepo.getRecordCount(anyBoolean(), any(), any(), anyString())).thenReturn(4);
		when(outboundCallsRepo.getMotherUnAllocatedCountLR(anyString(), anyInt(), any(), any(), anyString()))
				.thenReturn(3);
		when(outboundCallsRepo.getMotherUnAllocatedCountHR(anyString(), anyInt(), any(), any(), anyString()))
				.thenReturn(2);
		when(outboundCallsRepo.getTotalAllocatedCountMother(anyString(), anyInt(), any(), any(), anyString()))
				.thenReturn(1);

		ResponseEligibleCallRecordsDTO response = service.getEligibleRecordsInfo(1, "Self", "Mother",
				"2024-01-01T00:00:00", "2024-01-31T00:00:00");

		assertEquals(4, response.getTotalIntroductoryRecord());
		assertEquals(3, response.getTotalLowRiskRecord());
		assertEquals(2, response.getTotalHighRiskRecord());
		assertEquals(9, response.getTotalRecord());
		assertEquals(1, response.getTotalAllocatedRecord());
	}

	@Test
	void eligibleRecordsInfoAggregatesChildCounts() {
		when(childRecordRepo.getRecordCount(anyBoolean(), any(), any(), anyString())).thenReturn(5);
		when(outboundCallsRepo.getChildUnAllocatedCountLR(anyString(), anyInt(), any(), any(), anyString()))
				.thenReturn(1);
		when(outboundCallsRepo.getChildUnAllocatedCountHR(anyString(), anyInt(), any(), any(), anyString()))
				.thenReturn(1);
		when(outboundCallsRepo.getTotalAllocatedCountChild(anyString(), anyInt(), any(), any(), anyString()))
				.thenReturn(2);

		ResponseEligibleCallRecordsDTO response = service.getEligibleRecordsInfo(1, "Self", "Child",
				"2024-01-01T00:00:00", "2024-01-31T00:00:00");

		assertEquals(5, response.getTotalIntroductoryRecord());
		assertEquals(7, response.getTotalRecord());
		assertEquals(2, response.getTotalAllocatedRecord());
	}

	@Test
	void eligibleRecordsInfoReturnsZeroesForUnknownRecordType() {
		ResponseEligibleCallRecordsDTO response = service.getEligibleRecordsInfo(1, "Self", "Father",
				"2024-01-01T00:00:00", "2024-01-31T00:00:00");

		assertEquals(0, response.getTotalRecord());
	}

	@Test
	void eligibleRecordsInfoRejectsMissingDates() {
		assertThrows(ECDException.class,
				() -> service.getEligibleRecordsInfo(1, "Self", "Mother", null, "2024-01-31T00:00:00"));
	}

	@SuppressWarnings("unchecked")
	@Test
	void moveToBinReleasesMotherIntroductoryCallsForAnm() {
		RequestCallAllocationDTO request = request("ANM", "Mother");
		request.setPreferredLanguage("Hindi");
		OutboundCalls call = outboundCall(3L, null);
		when(outboundCallsRepo.getAllocatedRecordsUserByRecordTypeAndPhoneTypeMotherANM(any(), anyInt(), anyString(),
				anyString(), any(), any(), anyString())).thenReturn(new PageImpl<>(List.of(call)));

		String response = service.moveAllocatedCallsToBin(request);

		assertNull(call.getAllocatedUserId());
		assertEquals(Constants.UNALLOCATED, call.getAllocationStatus());
		assertEquals(true, call.getDeleted());
		verify(motherRecordRepo).updateIsAllocatedFalse(List.of(3L));
		verify(childRecordRepo, never()).updateIsAllocatedFalse(any());
		ArgumentCaptor<List<OutboundCalls>> captor = ArgumentCaptor.forClass(List.class);
		verify(outboundCallsRepo).saveAll(captor.capture());
		assertEquals(1, captor.getValue().size());
		assertTrue(response.contains("Records successfully moved to bin"));
	}

	@Test
	void moveToBinReleasesMotherCallsForAssociateWithAndWithoutLanguage() {
		RequestCallAllocationDTO withLanguage = request("associate", "Mother");
		withLanguage.setPreferredLanguage("Hindi");
		OutboundCalls call = outboundCall(3L, null);
		when(outboundCallsRepo.getAllocatedRecordsUserByRecordTypeAndPhoneTypeMotherAssociate(any(), anyInt(),
				anyString(), anyString(), any(), any(), anyString())).thenReturn(new PageImpl<>(List.of(call)));
		when(outboundCallsRepo.getAllocatedRecordsUserByRecordTypeAndPhoneTypeMotherAssociate(any(), anyInt(),
				anyString(), anyString(), any(), any())).thenReturn(new PageImpl<>(List.of(outboundCall(3L, null))));

		service.moveAllocatedCallsToBin(withLanguage);
		service.moveAllocatedCallsToBin(request("associate", "Mother"));

		verify(outboundCallsRepo, org.mockito.Mockito.times(2)).saveAll(any());
	}

	@Test
	void moveToBinReleasesMotherCallsForOtherRoles() {
		OutboundCalls call = outboundCall(3L, null);
		call.setEcdCallType("ecd1");
		when(outboundCallsRepo.getAllocatedRecordsUserByRecordTypeAndPhoneTypeMother(any(), anyInt(), anyString(),
				anyString(), any(), any())).thenReturn(new PageImpl<>(List.of(call)));

		service.moveAllocatedCallsToBin(request("MO", "Mother"));

		assertNull(call.getDeleted());
		verify(motherRecordRepo, never()).updateIsAllocatedFalse(any());
	}

	@Test
	void moveToBinReleasesChildCallsForEveryRole() {
		OutboundCalls anmCall = outboundCall(3L, 4L);
		when(outboundCallsRepo.getAllocatedRecordsUserByRecordTypeAndPhoneTypeChildANM(any(), anyInt(), anyString(),
				anyString(), any(), any(), any())).thenReturn(new PageImpl<>(List.of(anmCall)));
		when(outboundCallsRepo.getAllocatedRecordsUserByRecordTypeAndPhoneTypeChildAssociate(any(), anyInt(),
				anyString(), anyString(), any(), any(), anyString()))
						.thenReturn(new PageImpl<>(List.of(outboundCall(3L, 4L))));
		when(outboundCallsRepo.getAllocatedRecordsUserByRecordTypeAndPhoneTypeChildAssociate(any(), anyInt(),
				anyString(), anyString(), any(), any())).thenReturn(new PageImpl<>(List.of(outboundCall(3L, 4L))));
		when(outboundCallsRepo.getAllocatedRecordsUserByRecordTypeAndPhoneTypeChild(any(), anyInt(), anyString(),
				anyString(), any(), any())).thenReturn(new PageImpl<>(List.of(outboundCall(3L, 4L))));

		service.moveAllocatedCallsToBin(request("ANM", "Child"));
		RequestCallAllocationDTO associateWithLanguage = request("associate", "Child");
		associateWithLanguage.setPreferredLanguage("Hindi");
		service.moveAllocatedCallsToBin(associateWithLanguage);
		service.moveAllocatedCallsToBin(request("associate", "Child"));
		service.moveAllocatedCallsToBin(request("MO", "Child"));

		verify(childRecordRepo, org.mockito.Mockito.times(4)).updateIsAllocatedFalse(List.of(4L));
	}

	@Test
	void moveToBinFailsWhenNoRecordsAvailable() {
		when(outboundCallsRepo.getAllocatedRecordsUserByRecordTypeAndPhoneTypeMother(any(), anyInt(), anyString(),
				anyString(), any(), any())).thenReturn(new PageImpl<>(List.of()));

		assertThrows(ECDException.class, () -> service.moveAllocatedCallsToBin(request("MO", "Mother")));
	}

	@Test
	void moveToBinFailsWhenRoleMissing() {
		RequestCallAllocationDTO request = request(null, "Mother");

		assertThrows(ECDException.class, () -> service.moveAllocatedCallsToBin(request));
	}

	@Test
	void moveToBinRejectsIncompleteRequest() {
		RequestCallAllocationDTO request = request("MO", "Mother");
		request.setUserId(null);

		assertThrows(ECDException.class, () -> service.moveAllocatedCallsToBin(request));
	}

	@Test
	void allocatedCallCountUsesRoleSpecificQueriesForMother() {
		when(outboundCallsRepo.getAllocatedRecordsCountMotherUserANM(anyInt(), any(), any(), anyString(),
				anyString(), any())).thenReturn(4);
		when(outboundCallsRepo.getAllocatedRecordsCountMotherUserAssociateWithPreferredLanguage(anyInt(), any(),
				any(), anyString(), anyString(), anyString())).thenReturn(3);
		when(outboundCallsRepo.getAllocatedRecordsCountMotherUserAssociate(anyInt(), any(), any(), anyString(),
				anyString())).thenReturn(2);
		when(outboundCallsRepo.getAllocatedRecordsCountMotherUser(anyInt(), any(), any(), anyString(), anyString()))
				.thenReturn(1);

		assertTrue(service.getAllocatedCallCountUser(request("ANM", "Mother")).contains("4"));
		RequestCallAllocationDTO associateWithLanguage = request("ASSOCIATE", "Mother");
		associateWithLanguage.setPreferredLanguage("Hindi");
		assertTrue(service.getAllocatedCallCountUser(associateWithLanguage).contains("3"));
		assertTrue(service.getAllocatedCallCountUser(request("ASSOCIATE", "Mother")).contains("2"));
		assertTrue(service.getAllocatedCallCountUser(request("MO", "Mother")).contains("1"));
	}

	@Test
	void allocatedCallCountUsesRoleSpecificQueriesForChild() {
		when(outboundCallsRepo.getAllocatedRecordsCountChildUserANM(anyInt(), any(), any(), anyString(),
				anyString(), any())).thenReturn(4);
		when(outboundCallsRepo.getAllocatedRecordsCountChildUserAssociate(anyInt(), any(), any(), anyString(),
				anyString(), anyString())).thenReturn(3);
		when(outboundCallsRepo.getAllocatedRecordsCountChildUserAssociate(anyInt(), any(), any(), anyString(),
				anyString())).thenReturn(2);
		when(outboundCallsRepo.getAllocatedRecordsCountChildUser(anyInt(), any(), any(), anyString(), anyString()))
				.thenReturn(1);

		assertTrue(service.getAllocatedCallCountUser(request("ANM", "Child")).contains("4"));
		RequestCallAllocationDTO associateWithLanguage = request("ASSOCIATE", "Child");
		associateWithLanguage.setPreferredLanguage("Hindi");
		assertTrue(service.getAllocatedCallCountUser(associateWithLanguage).contains("3"));
		assertTrue(service.getAllocatedCallCountUser(request("ASSOCIATE", "Child")).contains("2"));
		assertTrue(service.getAllocatedCallCountUser(request("MO", "Child")).contains("1"));
	}

	@Test
	void allocatedCallCountRejectsInvalidRequests() {
		RequestCallAllocationDTO unknownType = request("MO", "Father");
		RequestCallAllocationDTO noDates = request("MO", "Mother");
		noDates.setFDate(null);

		assertThrows(ECDException.class, () -> service.getAllocatedCallCountUser(unknownType));
		assertThrows(ECDException.class, () -> service.getAllocatedCallCountUser(noDates));
		assertThrows(ECDException.class,
				() -> service.getAllocatedCallCountUser(new RequestCallAllocationDTO()));
	}

	@Test
	void reAllocateCallsMovesMotherCallsToNewUsers() {
		OutboundCalls call = outboundCall(3L, null);
		when(outboundCallsRepo.getAllocatedRecordsUserByRecordTypeAndPhoneTypeMother(any(), anyInt(), anyString(),
				anyString(), any(), any())).thenReturn(new PageImpl<>(List.of(call)));

		String response = service.reAllocateCalls(request("MO", "Mother"));

		assertEquals(Constants.ALLOCATED, call.getAllocationStatus());
		assertEquals(11, call.getAllocatedUserId());
		assertTrue(response.contains("records successfully re-allocated"));
	}

	@Test
	void reAllocateCallsSupportsAnmAndChildQueries() {
		when(outboundCallsRepo.getAllocatedRecordsUserByRecordTypeAndPhoneTypeMotherANM(any(), anyInt(), anyString(),
				anyString(), any(), any(), any())).thenReturn(new PageImpl<>(List.of(outboundCall(3L, null))));
		when(outboundCallsRepo.getAllocatedRecordsUserByRecordTypeAndPhoneTypeChildANM(any(), anyInt(), anyString(),
				anyString(), any(), any(), any())).thenReturn(new PageImpl<>(List.of(outboundCall(3L, 4L))));
		when(outboundCallsRepo.getAllocatedRecordsUserByRecordTypeAndPhoneTypeChild(any(), anyInt(), anyString(),
				anyString(), any(), any())).thenReturn(new PageImpl<>(List.of(outboundCall(3L, 4L))));

		service.reAllocateCalls(request("ANM", "Mother"));
		service.reAllocateCalls(request("ANM", "Child"));
		service.reAllocateCalls(request("MO", "Child"));

		verify(outboundCallsRepo, org.mockito.Mockito.times(3)).saveAll(any());
	}

	@Test
	void reAllocateCallsSkipsSaveWhenNoRecordsFound() {
		when(outboundCallsRepo.getAllocatedRecordsUserByRecordTypeAndPhoneTypeMother(any(), anyInt(), anyString(),
				anyString(), any(), any())).thenReturn(new PageImpl<>(List.of(), PageRequest.of(0, 1), 0));

		service.reAllocateCalls(request("MO", "Mother"));

		verify(outboundCallsRepo, never()).saveAll(any());
	}

	@Test
	void reAllocateCallsRejectsInvalidRequests() {
		RequestCallAllocationDTO noDates = request("MO", "Mother");
		noDates.setFDate(null);
		RequestCallAllocationDTO zeroCalls = request("MO", "Mother");
		zeroCalls.setNoOfCalls(0);

		assertThrows(ECDException.class, () -> service.reAllocateCalls(noDates));
		assertThrows(ECDException.class, () -> service.reAllocateCalls(zeroCalls));
		assertThrows(ECDException.class, () -> service.reAllocateCalls(new RequestCallAllocationDTO()));
	}

	@SuppressWarnings("unchecked")
	@Test
	void insertRecordsInOutboundCallsExpandsConfigurationForMother() {
		OutboundCallsDTO request = new OutboundCallsDTO();
		request.setPsmId(1);
		when(callConfigurationRepo.getCallConfiguration(1))
				.thenReturn(new java.util.ArrayList<>(List.of(configuration("ecd1", "LMP", "days"),
						configuration("ecd2", "DOB", "days"), configuration("ecd3", "LMP", "months"))));
		when(outboundCallsRepo.getIntroductoryRecordsUser(1, "completed", "introductory"))
				.thenReturn(List.of(outboundCall(3L, null)));
		when(motherRecordRepo.findByEcdIdNo(3L)).thenReturn(motherRecord());

		String response = service.insertRecordsInOutboundCalls(request);

		ArgumentCaptor<List<OutboundCalls>> captor = ArgumentCaptor.forClass(List.class);
		verify(outboundCallsRepo).saveAll(captor.capture());
		List<OutboundCalls> created = captor.getValue();
		assertEquals(2, created.size());
		assertEquals("ecd1", created.get(0).getEcdCallType());
		assertEquals("Display ecd1", created.get(0).getDisplayEcdCallType());
		assertEquals("open", created.get(0).getCallStatus());
		assertEquals("unallocated", created.get(0).getAllocationStatus());
		assertEquals(3L, created.get(0).getMotherId());
		assertTrue(response.contains("records successfully inserted"));
	}

	@SuppressWarnings("unchecked")
	@Test
	void insertRecordsInOutboundCallsExpandsConfigurationForChild() {
		OutboundCallsDTO request = new OutboundCallsDTO();
		request.setPsmId(1);
		OutboundCalls source = outboundCall(3L, 4L);
		source.setIsHighRisk(true);
		source.setIsHrni(true);
		when(callConfigurationRepo.getCallConfiguration(1)).thenReturn(new java.util.ArrayList<>(
				List.of(configuration("ecd1", "DOB", "days"), configuration("ecd2", "LMP", "days"),
						configuration("ecd3", "DOB", "months"))));
		when(outboundCallsRepo.getIntroductoryRecordsUser(1, "completed", "introductory"))
				.thenReturn(List.of(source));
		when(childRecordRepo.findByEcdIdNoChildId(4L)).thenReturn(childRecord());

		service.insertRecordsInOutboundCalls(request);

		ArgumentCaptor<List<OutboundCalls>> captor = ArgumentCaptor.forClass(List.class);
		verify(outboundCallsRepo).saveAll(captor.capture());
		assertEquals(2, captor.getValue().size());
		assertEquals(4L, captor.getValue().get(0).getChildId());
		assertEquals(true, captor.getValue().get(0).getIsHrni());
		assertEquals(true, captor.getValue().get(0).getIsHighRisk());
	}

	@Test
	void insertRecordsInOutboundCallsSkipsWhenNothingToProcess() {
		OutboundCallsDTO request = new OutboundCallsDTO();
		request.setPsmId(1);
		when(outboundCallsRepo.getIntroductoryRecordsUser(1, "completed", "introductory")).thenReturn(null);

		assertTrue(service.insertRecordsInOutboundCalls(request).contains("records successfully inserted"));
		verify(outboundCallsRepo, never()).saveAll(any());
	}

	@Test
	void insertRecordsInOutboundCallsSkipsWhenNoConfiguration() {
		OutboundCallsDTO request = new OutboundCallsDTO();
		request.setPsmId(1);
		when(outboundCallsRepo.getIntroductoryRecordsUser(1, "completed", "introductory"))
				.thenReturn(List.of(outboundCall(3L, null)));
		when(callConfigurationRepo.getCallConfiguration(1)).thenReturn(new java.util.ArrayList<>());

		service.insertRecordsInOutboundCalls(request);

		verify(outboundCallsRepo, never()).saveAll(any());
	}

	@Test
	void insertRecordsInOutboundCallsFailsWhenRchIdMissing() {
		OutboundCallsDTO request = new OutboundCallsDTO();
		request.setPsmId(1);
		when(outboundCallsRepo.getIntroductoryRecordsUser(1, "completed", "introductory"))
				.thenReturn(List.of(outboundCall(null, null)));
		when(callConfigurationRepo.getCallConfiguration(1))
				.thenReturn(new java.util.ArrayList<>(List.of(configuration("ecd1", "LMP", "days"))));

		assertThrows(ECDException.class, () -> service.insertRecordsInOutboundCalls(request));
	}

	@Test
	void eligibleRecordsLanguageInfoReturnsLowRiskCountsForAnm() {
		when(outboundCallsRepo.getMotherUnAllocatedCountLRByLanguage(anyString(), anyInt(), any(), any(),
				anyString(), anyString())).thenReturn(4);
		when(outboundCallsRepo.getChildUnAllocatedCountLRByLanguage(anyString(), anyInt(), any(), any(), anyString(),
				anyString())).thenReturn(3);

		assertEquals(4, service.getEligibleRecordsLanguageInfo(1, "Self", "Mother", "2024-01-01T00:00:00",
				"2024-01-31T00:00:00", "Hindi", "ANM").getTotalLowRiskRecord());
		assertEquals(3, service.getEligibleRecordsLanguageInfo(1, "Self", "Child", "2024-01-01T00:00:00",
				"2024-01-31T00:00:00", "Hindi", "ANM").getTotalLowRiskRecord());
	}

	@Test
	void eligibleRecordsLanguageInfoReturnsIntroductoryCountsForAssociate() {
		when(outboundCallsRepo.getMotherUnAllocatedCountIntroductoryByLanguage(anyString(), anyInt(), any(), any(),
				anyString(), anyString())).thenReturn(6);
		when(outboundCallsRepo.getChildUnAllocatedCountIntroductoryByLanguage(anyString(), anyInt(), any(), any(),
				anyString(), anyString())).thenReturn(5);

		assertEquals(6, service.getEligibleRecordsLanguageInfo(1, "Self", "Mother", "2024-01-01T00:00:00",
				"2024-01-31T00:00:00", "Hindi", "ASSOCIATE").getTotalIntroductoryRecord());
		assertEquals(5, service.getEligibleRecordsLanguageInfo(1, "Self", "Child", "2024-01-01T00:00:00",
				"2024-01-31T00:00:00", "Hindi", "ASSOCIATE").getTotalIntroductoryRecord());
	}

	@Test
	void eligibleRecordsLanguageInfoReturnsNullForUnsupportedCombination() {
		assertNull(service.getEligibleRecordsLanguageInfo(1, "Self", "Father", "2024-01-01T00:00:00",
				"2024-01-31T00:00:00", "Hindi", "ANM"));
	}

	@Test
	void eligibleRecordsLanguageInfoRejectsMissingLanguageAndDates() {
		assertThrows(ECDException.class, () -> service.getEligibleRecordsLanguageInfo(1, "Self", "Mother",
				"2024-01-01T00:00:00", "2024-01-31T00:00:00", "  ", "ANM"));
		assertThrows(ECDException.class, () -> service.getEligibleRecordsLanguageInfo(1, "Self", "Mother", null,
				"2024-01-31T00:00:00", "Hindi", "ANM"));
	}
	@SuppressWarnings("unchecked")
	@Test
	void insertRecordsInOutboundCallsCopesWithSparseSourceAndConfiguration() {
		OutboundCallsDTO request = new OutboundCallsDTO();
		request.setPsmId(1);
		OutboundCalls source = new OutboundCalls();
		source.setMotherId(3L);
		CallConfiguration sparseConfiguration = new CallConfiguration();
		sparseConfiguration.setBaseLine("LMP");
		sparseConfiguration.setTermRange(30);
		when(callConfigurationRepo.getCallConfiguration(1))
				.thenReturn(new java.util.ArrayList<>(List.of(sparseConfiguration)));
		when(outboundCallsRepo.getIntroductoryRecordsUser(1, "completed", "introductory"))
				.thenReturn(List.of(source));
		MotherRecord record = motherRecord();
		record.setLmpDate(null);
		when(motherRecordRepo.findByEcdIdNo(3L)).thenReturn(record);

		service.insertRecordsInOutboundCalls(request);

		ArgumentCaptor<List<OutboundCalls>> captor = ArgumentCaptor.forClass(List.class);
		verify(outboundCallsRepo).saveAll(captor.capture());
		OutboundCalls created = captor.getValue().get(0);
		assertEquals(3L, created.getMotherId());
		assertNull(created.getEcdCallType());
		assertNull(created.getDisplayEcdCallType());
		assertNull(created.getPhoneNumberType());
		assertNull(created.getCallDateFrom());
	}

	@SuppressWarnings("unchecked")
	@Test
	void insertRecordsInOutboundCallsSkipsUnknownConfigurationTerms() {
		OutboundCallsDTO request = new OutboundCallsDTO();
		request.setPsmId(1);
		CallConfiguration configuration = configuration("ecd1", "LMP", null);
		when(callConfigurationRepo.getCallConfiguration(1))
				.thenReturn(new java.util.ArrayList<>(List.of(configuration)));
		when(outboundCallsRepo.getIntroductoryRecordsUser(1, "completed", "introductory"))
				.thenReturn(List.of(outboundCall(3L, null)));
		when(motherRecordRepo.findByEcdIdNo(3L)).thenReturn(motherRecord());

		assertThrows(ECDException.class, () -> service.insertRecordsInOutboundCalls(request));
	}

	@SuppressWarnings("unchecked")
	@Test
	void insertRecordsInOutboundCallsSkipsUnknownConfigurationTermsForChild() {
		OutboundCallsDTO request = new OutboundCallsDTO();
		request.setPsmId(1);
		when(callConfigurationRepo.getCallConfiguration(1))
				.thenReturn(new java.util.ArrayList<>(List.of(configuration("ecd1", "DOB", null))));
		when(outboundCallsRepo.getIntroductoryRecordsUser(1, "completed", "introductory"))
				.thenReturn(List.of(outboundCall(3L, 4L)));
		when(childRecordRepo.findByEcdIdNoChildId(4L)).thenReturn(childRecord());

		assertThrows(ECDException.class, () -> service.insertRecordsInOutboundCalls(request));
	}

	@SuppressWarnings("unchecked")
	@Test
	void allocateMotherRecordsToAssociateCopesWithSparseMotherRecord() {
		MotherRecord record = new MotherRecord();
		record.setEcdIdNo(3L);
		when(motherRecordRepo.getMotherRecordForAllocation(any(), any(), anyString(), anyInt()))
				.thenReturn(List.of(record));

		service.allocateCalls(request("associate", "mother"));

		ArgumentCaptor<List<OutboundCalls>> captor = ArgumentCaptor.forClass(List.class);
		verify(outboundCallsRepo).saveAll(captor.capture());
		OutboundCalls created = captor.getValue().get(0);
		assertNull(created.getBeneficiaryRegId());
		assertNull(created.getPhoneNumberType());
		assertNull(created.getIsHighRisk());
	}

	@SuppressWarnings("unchecked")
	@Test
	void allocateChildRecordsToAssociateCopesWithSparseChildRecord() {
		ChildRecord record = new ChildRecord();
		when(childRecordRepo.getChildRecordForAllocation(any(), any(), anyString(), anyInt()))
				.thenReturn(List.of(record));

		service.allocateCalls(request("associate", "child"));

		ArgumentCaptor<List<OutboundCalls>> captor = ArgumentCaptor.forClass(List.class);
		verify(outboundCallsRepo).saveAll(captor.capture());
		OutboundCalls created = captor.getValue().get(0);
		assertNull(created.getChildId());
		assertNull(created.getMotherId());
		assertNull(created.getBeneficiaryRegId());
		assertNull(created.getPhoneNumberType());
	}

	@Test
	void eligibleRecordsLanguageInfoRejectsNullLanguage() {
		assertThrows(ECDException.class, () -> service.getEligibleRecordsLanguageInfo(1, "Self", "Mother",
				"2024-01-01T00:00:00", "2024-01-31T00:00:00", null, "ANM"));
	}

	@Test
	void allocateCallsRejectsNullRequest() {
		assertThrows(ECDException.class, () -> service.allocateCalls(null));
	}

	@Test
	void moveToBinRejectsEachMissingRequestField() {
		java.util.List<java.util.function.Consumer<RequestCallAllocationDTO>> mutations = java.util.List.of(
				dto -> dto.setUserId(null), dto -> dto.setNoOfCalls(null), dto -> dto.setRecordType(null),
				dto -> dto.setPhoneNoType(null), dto -> dto.setFDate(null), dto -> dto.setTDate(null));

		for (java.util.function.Consumer<RequestCallAllocationDTO> mutation : mutations) {
			RequestCallAllocationDTO request = request("MO", "Mother");
			mutation.accept(request);
			assertThrows(ECDException.class, () -> service.moveAllocatedCallsToBin(request));
		}
	}

	@Test
	void reAllocateCallsRejectsEachMissingRequestField() {
		java.util.List<java.util.function.Consumer<RequestCallAllocationDTO>> mutations = java.util.List.of(
				dto -> dto.setUserId(null), dto -> dto.setToUserIds(null),
				dto -> dto.setToUserIds(new Integer[0]), dto -> dto.setRecordType(null),
				dto -> dto.setPhoneNoType(null), dto -> dto.setNoOfCalls(null));

		for (java.util.function.Consumer<RequestCallAllocationDTO> mutation : mutations) {
			RequestCallAllocationDTO request = request("MO", "Mother");
			mutation.accept(request);
			assertThrows(ECDException.class, () -> service.reAllocateCalls(request));
		}
	}

	@Test
	void reAllocateCallsIgnoresUnknownRecordType() {
		String response = service.reAllocateCalls(request("MO", "Father"));

		assertTrue(response.contains("records successfully re-allocated"));
		verify(outboundCallsRepo, never()).saveAll(any());
	}

	@Test
	void eligibleRecordsInfoAcceptsNullRecordType() {
		assertEquals(0, service.getEligibleRecordsInfo(1, "Self", null, "2024-01-01T00:00:00",
				"2024-01-31T00:00:00").getTotalRecord());
	}

	@Test
	void eligibleRecordsLanguageInfoAcceptsNullRecordType() {
		assertNull(service.getEligibleRecordsLanguageInfo(1, "Self", null, "2024-01-01T00:00:00",
				"2024-01-31T00:00:00", "Hindi", "ANM"));
	}
}
