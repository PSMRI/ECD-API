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
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.test.util.ReflectionTestUtils;

import com.iemr.ecd.dao.CallConfiguration;
import com.iemr.ecd.dao.associate.Bencall;
import com.iemr.ecd.dao.associate.ChildRecord;
import com.iemr.ecd.dao.associate.MotherRecord;
import com.iemr.ecd.dao.associate.OutboundCalls;
import com.iemr.ecd.dao.masters.V_GetUserlangmapping;
import com.iemr.ecd.dto.associate.CallClosureDTO;
import com.iemr.ecd.repo.call_conf_allocation.CallConfigurationRepo;
import com.iemr.ecd.repo.call_conf_allocation.ChildRecordRepo;
import com.iemr.ecd.repo.call_conf_allocation.MotherRecordRepo;
import com.iemr.ecd.repo.call_conf_allocation.OutboundCallsRepo;
import com.iemr.ecd.repository.ecd.BencallRepo;
import com.iemr.ecd.service.masters.MasterServiceImpl;
import com.iemr.ecd.utils.advice.exception_handler.ECDException;
import com.iemr.ecd.utils.constants.Constants;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class CallClosureImplTest {

	@Mock
	private BencallRepo bencallRepo;
	@Mock
	private OutboundCallsRepo outboundCallsRepo;
	@Mock
	private CallConfigurationRepo callConfigurationRepo;
	@Mock
	private MotherRecordRepo motherRecordRepo;
	@Mock
	private ChildRecordRepo childRecordRepo;
	@Mock
	private MasterServiceImpl masterServiceImpl;

	private CallClosureImpl service;

	@BeforeEach
	void setUp() {
		service = new CallClosureImpl();
		ReflectionTestUtils.setField(service, "bencallRepo", bencallRepo);
		ReflectionTestUtils.setField(service, "outboundCallsRepo", outboundCallsRepo);
		ReflectionTestUtils.setField(service, "callConfigurationRepo", callConfigurationRepo);
		ReflectionTestUtils.setField(service, "motherRecordRepo", motherRecordRepo);
		ReflectionTestUtils.setField(service, "childRecordRepo", childRecordRepo);
		ReflectionTestUtils.setField(service, "masterServiceImpl", masterServiceImpl);
		when(masterServiceImpl.getLanguageByUserId(any())).thenReturn(List.of());
	}

	private CallClosureDTO request() {
		CallClosureDTO request = new CallClosureDTO();
		request.setBenCallId(1L);
		request.setObCallId(2L);
		request.setMotherId(3L);
		request.setPsmId(1);
		request.setUserId(7);
		request.setIsOutbound(true);
		request.setIsCallAnswered(true);
		request.setIsCallDisconnected(false);
		request.setIsFurtherCallRequired(false);
		request.setCreatedBy("agent");
		request.setModifiedBy("agent");
		return request;
	}

	private CallClosureDTO fullRequest() {
		CallClosureDTO request = request();
		request.setBeneficiaryRegId(99L);
		request.setReasonForNoFurtherCalls("Beneficiary opted out");
		request.setIsCallVerified(true);
		request.setTypeOfComplaint("Fever");
		request.setComplaintRemarks("remarks");
		request.setCallRemarks("call remarks");
		request.setSendAdvice("advice");
		request.setAltPhoneNo("9999999999");
		request.setIsWrongNumber(false);
		return request;
	}

	private Bencall bencall(String receivedRole) {
		Bencall bencall = new Bencall();
		bencall.setBenCallId(1L);
		bencall.setCallTime(new Timestamp(System.currentTimeMillis() - 3_723_000L));
		bencall.setReceivedRoleName(receivedRole);
		return bencall;
	}

	private OutboundCalls outboundCall(String ecdCallType) {
		OutboundCalls call = new OutboundCalls();
		call.setObCallId(2L);
		call.setMotherId(3L);
		call.setEcdCallType(ecdCallType);
		call.setCallAttemptNo(0);
		call.setPhoneNumberType("Self");
		return call;
	}

	private java.util.ArrayList<CallConfiguration> configurations(CallConfiguration... configurations) {
		return new java.util.ArrayList<>(List.of(configurations));
	}

	private CallConfiguration configuration(String callType, String baseLine, String terms, int termRange) {
		CallConfiguration configuration = new CallConfiguration();
		configuration.setCallConfigId(55L);
		configuration.setCallType(callType);
		configuration.setDisplayName("Display " + callType);
		configuration.setBaseLine(baseLine);
		configuration.setConfigTerms(terms);
		configuration.setTermRange(termRange);
		configuration.setNoOfAttempts(3);
		return configuration;
	}

	@Test
	void closeCallCopiesRequestOntoBeneficiaryCallAndCompletesCall() {
		Bencall bencall = bencall(Constants.ASSOCIATE);
		OutboundCalls call = outboundCall("ecd1");
		when(bencallRepo.findByBenCallId(1L)).thenReturn(bencall);
		when(outboundCallsRepo.findByObCallId(2L)).thenReturn(call);
		when(callConfigurationRepo.getCallConfiguration(1))
				.thenReturn(configurations(configuration("ecd1", "LMP", "days", 30)));

		String response = service.closeCall(fullRequest());

		assertEquals(true, bencall.getIsOutbound());
		assertEquals(99L, bencall.getBeneficiaryRegId());
		assertEquals("Beneficiary opted out", bencall.getReasonForNoFurtherCalls());
		assertEquals(true, bencall.getIsCallVerified());
		assertEquals("Fever", bencall.getTypeOfComplaint());
		assertEquals("remarks", bencall.getComplaintRemarks());
		assertEquals("call remarks", bencall.getCallRemarks());
		assertEquals("advice", bencall.getSmsAdvice());
		assertEquals("9999999999", bencall.getSmsPhone());
		assertEquals(7, bencall.getCallEndUserId());
		assertEquals("1 hour 2 mins 3 secs ", bencall.getCallDuration());
		assertEquals(Constants.COMPLETED, call.getCallStatus());
		assertEquals(1, call.getCallAttemptNo());
		verify(bencallRepo).save(bencall);
		verify(outboundCallsRepo).save(call);
		assertTrue(response.contains("Call closed successfully"));
	}

	@Test
	void closeCallReopensCallWhenDisconnected() {
		Bencall bencall = bencall(Constants.ASSOCIATE);
		OutboundCalls call = outboundCall("ecd1");
		CallClosureDTO request = request();
		request.setIsFurtherCallRequired(true);
		request.setIsCallDisconnected(true);
		when(bencallRepo.findByBenCallId(1L)).thenReturn(bencall);
		when(outboundCallsRepo.findByObCallId(2L)).thenReturn(call);
		when(callConfigurationRepo.getCallConfiguration(1))
				.thenReturn(configurations(configuration("ecd1", "LMP", "days", 30)));

		service.closeCall(request);

		assertEquals(Constants.OPEN, call.getCallStatus());
		assertEquals(Constants.UNALLOCATED, call.getAllocationStatus());
		assertNull(call.getAllocatedUserId());
	}

	@Test
	void closeCallKeepsAllocationWhenNotAnsweredReasonIsKnown() {
		Bencall bencall = bencall(Constants.ASSOCIATE);
		OutboundCalls call = outboundCall("ecd1");
		call.setAllocatedUserId(7);
		CallClosureDTO request = request();
		request.setIsFurtherCallRequired(true);
		request.setIsCallDisconnected(true);
		request.setReasonForCallNotAnswered("No reply");
		when(bencallRepo.findByBenCallId(1L)).thenReturn(bencall);
		when(outboundCallsRepo.findByObCallId(2L)).thenReturn(call);
		when(callConfigurationRepo.getCallConfiguration(1))
				.thenReturn(configurations(configuration("ecd1", "LMP", "days", 30)));

		service.closeCall(request);

		assertEquals(true, bencall.getIsFurtherCallRequired());
		assertEquals(true, bencall.getIsCallDisconnected());
		assertEquals(Constants.OPEN, call.getCallStatus());
		assertEquals(7, call.getAllocatedUserId());
	}

	@Test
	void closeCallReopensIntroductoryCallWhenLanguagePreferenceGiven() {
		Bencall bencall = bencall(Constants.ASSOCIATE);
		OutboundCalls call = outboundCall("introductory");
		CallClosureDTO request = request();
		request.setIsCallDisconnected(true);
		request.setIsFurtherCallRequired(true);
		request.setPreferredLanguage("Hindi");
		when(bencallRepo.findByBenCallId(1L)).thenReturn(bencall);
		when(outboundCallsRepo.findByObCallId(2L)).thenReturn(call);
		when(callConfigurationRepo.getCallConfiguration(1))
				.thenReturn(configurations(configuration("ecd1", "LMP", "days", 30)));

		service.closeCall(request);

		assertEquals(Constants.OPEN, call.getCallStatus());
		assertEquals(Constants.UNALLOCATED, call.getAllocationStatus());
		assertEquals(0, call.getCallAttemptNo());
		verify(motherRecordRepo).updatePreferredLanguage("Hindi", 3L);
		verify(motherRecordRepo).updateAllocatedStatus(false, 3L);
	}

	@Test
	void closeCallKeepsAgentWhenPreferredLanguageIsMapped() {
		Bencall bencall = bencall(Constants.ANM);
		OutboundCalls call = outboundCall("ecd1");
		CallClosureDTO request = request();
		request.setPreferredLanguage("Hindi");
		V_GetUserlangmapping mapping = new V_GetUserlangmapping();
		mapping.setLanguageName("Hindi");
		when(masterServiceImpl.getLanguageByUserId(7)).thenReturn(List.of(mapping));
		when(bencallRepo.findByBenCallId(1L)).thenReturn(bencall);
		when(outboundCallsRepo.findByObCallId(2L)).thenReturn(call);
		when(callConfigurationRepo.getCallConfiguration(1))
				.thenReturn(configurations(configuration("ecd1", "LMP", "days", 30)));

		service.closeCall(request);

		assertEquals(1, call.getCallAttemptNo());
		verify(motherRecordRepo).updatePreferredLanguage("Hindi", 3L);
		verify(motherRecordRepo, never()).updateAllocatedStatus(anyBoolean(), anyLong());
	}

	@Test
	void closeCallSetsNextCallDateWhenNextAttemptProvided() {
		Bencall bencall = bencall(Constants.ASSOCIATE);
		OutboundCalls call = outboundCall("ecd1");
		CallClosureDTO request = request();
		request.setNextAttemptDate("2024-06-01T10:00:00+05:30");
		when(bencallRepo.findByBenCallId(1L)).thenReturn(bencall);
		when(outboundCallsRepo.findByObCallId(2L)).thenReturn(call);
		when(callConfigurationRepo.getCallConfiguration(1))
				.thenReturn(configurations(configuration("ecd1", "LMP", "days", 30)));

		service.closeCall(request);

		assertEquals(Timestamp.valueOf("2024-06-01 10:00:00"), call.getNextCallDate());
		assertEquals(Constants.OPEN, call.getCallStatus());
	}

	@Test
	void closeCallCompletesCallWhenMaximumAttemptsReached() {
		Bencall bencall = bencall(Constants.ASSOCIATE);
		OutboundCalls call = outboundCall("ecd1");
		call.setCallAttemptNo(2);
		CallClosureDTO request = request();
		request.setReasonForCallNotAnswered("No reply");
		when(bencallRepo.findByBenCallId(1L)).thenReturn(bencall);
		when(outboundCallsRepo.findByObCallId(2L)).thenReturn(call);
		when(callConfigurationRepo.getCallConfiguration(1))
				.thenReturn(configurations(configuration("ecd1", "LMP", "days", 30)));

		service.closeCall(request);

		assertEquals(Constants.COMPLETED, call.getCallStatus());
	}

	@Test
	void closeCallReopensHrpCallForAnm() {
		Bencall bencall = bencall(Constants.ANM);
		OutboundCalls call = outboundCall("ecd1");
		call.setAllocatedUserId(7);
		CallClosureDTO request = request();
		request.setIsHrp(true);
		when(bencallRepo.findByBenCallId(1L)).thenReturn(bencall);
		when(outboundCallsRepo.findByObCallId(2L)).thenReturn(call);
		when(callConfigurationRepo.getCallConfiguration(1))
				.thenReturn(configurations(configuration("ecd1", "LMP", "days", 30)));

		service.closeCall(request);

		assertEquals(true, call.getIsHighRisk());
		assertEquals(Constants.OPEN, call.getCallStatus());
		assertEquals(Constants.UNALLOCATED, call.getAllocationStatus());
		assertNull(call.getAllocatedUserId());
	}

	@Test
	void closeCallGeneratesFollowUpsForDisconnectedHrpIntroductoryCall() {
		Bencall bencall = bencall(Constants.ANM);
		OutboundCalls call = outboundCall("introductory");
		CallClosureDTO request = request();
		request.setEcdCallType("introductory");
		request.setIsHrp(true);
		request.setIsCallDisconnected(true);
		request.setIsFurtherCallRequired(true);
		MotherRecord motherRecord = new MotherRecord();
		motherRecord.setLmpDate(Timestamp.valueOf("2024-01-01 00:00:00"));
		when(bencallRepo.findByBenCallId(1L)).thenReturn(bencall);
		when(outboundCallsRepo.findByObCallId(2L)).thenReturn(call);
		when(callConfigurationRepo.getCallConfiguration(1))
				.thenReturn(configurations(configuration("ecd1", "LMP", "days", 30), configuration("ecd2", "DOB", "days", 30),
						configuration("ecd3", "LMP", "months", 2)));
		when(motherRecordRepo.findByEcdIdNo(3L)).thenReturn(motherRecord);

		service.closeCall(request);

		assertEquals(Constants.COMPLETED, call.getCallStatus());
		verify(outboundCallsRepo).saveAll(any());
	}

	@SuppressWarnings("unchecked")
	@Test
	void closeCallGeneratesFollowUpsFromChildDateOfBirth() {
		Bencall bencall = bencall(Constants.ASSOCIATE);
		OutboundCalls call = outboundCall("introductory");
		call.setChildId(4L);
		CallClosureDTO request = request();
		request.setChildId(4L);
		request.setEcdCallType("introductory");
		request.setIsFurtherCallRequired(true);
		request.setIsHrni(true);
		request.setPreferredLanguage("Hindi");
		ChildRecord childRecord = new ChildRecord();
		childRecord.setDob(Timestamp.valueOf("2024-01-01 00:00:00"));
		when(bencallRepo.findByBenCallId(1L)).thenReturn(bencall);
		when(outboundCallsRepo.findByObCallId(2L)).thenReturn(call);
		when(callConfigurationRepo.getCallConfiguration(1))
				.thenReturn(configurations(configuration("ecd1", "DOB", "days", 30), configuration("ecd2", "LMP", "days", 30),
						configuration("ecd3", "DOB", "months", 1)));
		when(childRecordRepo.findByEcdIdNoChildId(4L)).thenReturn(childRecord);

		service.closeCall(request);

		ArgumentCaptor<List<OutboundCalls>> captor = ArgumentCaptor.forClass(List.class);
		verify(outboundCallsRepo).saveAll(captor.capture());
		List<OutboundCalls> created = captor.getValue();
		assertEquals(2, created.size());
		assertEquals("open", created.get(0).getCallStatus());
		assertEquals("unallocated", created.get(0).getAllocationStatus());
		assertEquals("Self", created.get(0).getPhoneNumberType());
		assertEquals(4L, created.get(0).getChildId());
		assertEquals(true, created.get(0).getIsHrni());
		verify(childRecordRepo).updatePreferredLanguage(anyString(), anyLong());
	}

	@Test
	void closeCallMovesLowRiskCallBackToAnmBucketForMedicalOfficer() {
		Bencall bencall = bencall("MO");
		OutboundCalls call = outboundCall("ecd1");
		CallClosureDTO request = request();
		request.setIsHrp(false);
		request.setIsHrni(false);
		when(bencallRepo.findByBenCallId(1L)).thenReturn(bencall);
		when(outboundCallsRepo.findByObCallId(2L)).thenReturn(call);
		when(callConfigurationRepo.getCallConfiguration(1))
				.thenReturn(configurations(configuration("ecd1", "LMP", "days", 30)));

		service.closeCall(request);

		assertEquals(Constants.OPEN, call.getCallStatus());
		assertEquals(Constants.UNALLOCATED, call.getAllocationStatus());
		assertEquals(0, call.getCallAttemptNo());
	}

	@Test
	void closeCallCompletesLowRiskCallForAssociate() {
		Bencall bencall = bencall(Constants.ASSOCIATE);
		OutboundCalls call = outboundCall("ecd1");
		CallClosureDTO request = request();
		request.setIsHrp(false);
		when(bencallRepo.findByBenCallId(1L)).thenReturn(bencall);
		when(outboundCallsRepo.findByObCallId(2L)).thenReturn(call);
		when(callConfigurationRepo.getCallConfiguration(1))
				.thenReturn(configurations(configuration("ecd1", "LMP", "days", 30)));

		service.closeCall(request);

		assertEquals(Constants.COMPLETED, call.getCallStatus());
	}

	@Test
	void closeCallPropagatesHrniToUpcomingChildCalls() {
		Bencall bencall = bencall(Constants.ASSOCIATE);
		OutboundCalls call = outboundCall("ecd1");
		call.setChildId(4L);
		call.setIsHrni(true);
		CallClosureDTO request = request();
		request.setChildId(4L);
		when(bencallRepo.findByBenCallId(1L)).thenReturn(bencall);
		when(outboundCallsRepo.findByObCallId(2L)).thenReturn(call);
		when(callConfigurationRepo.getCallConfiguration(1))
				.thenReturn(configurations(configuration("ecd1", "LMP", "days", 30)));

		service.closeCall(request);

		verify(outboundCallsRepo).updateHRNIForUpcomingCall(4L, true);
		verify(outboundCallsRepo).updateIsFurtherCallRequiredForUpcomingCallForChild(4L, false);
	}

	@Test
	void closeCallPropagatesHrpToUpcomingMotherCalls() {
		Bencall bencall = bencall(Constants.ASSOCIATE);
		OutboundCalls call = outboundCall("ecd1");
		call.setIsHighRisk(true);
		call.setHighRiskReason("Anaemia");
		when(bencallRepo.findByBenCallId(1L)).thenReturn(bencall);
		when(outboundCallsRepo.findByObCallId(2L)).thenReturn(call);
		when(callConfigurationRepo.getCallConfiguration(1))
				.thenReturn(configurations(configuration("ecd1", "LMP", "days", 30)));

		service.closeCall(request());

		verify(outboundCallsRepo).updateHRPForUpcomingCall(3L, true, "Anaemia");
		verify(outboundCallsRepo).updateIsFurtherCallRequiredForUpcomingCallForMother(3L, false);
	}

	@Test
	void closeCallFailsWhenUpcomingChildUpdateFails() {
		Bencall bencall = bencall(Constants.ASSOCIATE);
		OutboundCalls call = outboundCall("ecd1");
		call.setChildId(4L);
		when(bencallRepo.findByBenCallId(1L)).thenReturn(bencall);
		when(outboundCallsRepo.findByObCallId(2L)).thenReturn(call);
		when(callConfigurationRepo.getCallConfiguration(1))
				.thenReturn(configurations(configuration("ecd1", "LMP", "days", 30)));
		org.mockito.Mockito.doThrow(new IllegalStateException("db")).when(outboundCallsRepo)
				.updateIsFurtherCallRequiredForUpcomingCallForChild(anyLong(), anyBoolean());

		assertThrows(ECDException.class, () -> service.closeCall(request()));
	}

	@Test
	void closeCallFailsWhenUpcomingMotherUpdateFails() {
		Bencall bencall = bencall(Constants.ASSOCIATE);
		OutboundCalls call = outboundCall("ecd1");
		when(bencallRepo.findByBenCallId(1L)).thenReturn(bencall);
		when(outboundCallsRepo.findByObCallId(2L)).thenReturn(call);
		when(callConfigurationRepo.getCallConfiguration(1))
				.thenReturn(configurations(configuration("ecd1", "LMP", "days", 30)));
		org.mockito.Mockito.doThrow(new IllegalStateException("db")).when(outboundCallsRepo)
				.updateIsFurtherCallRequiredForUpcomingCallForMother(anyLong(), anyBoolean());

		assertThrows(ECDException.class, () -> service.closeCall(request()));
	}

	@Test
	void closeCallAllocatesStickyAgentForMotherAndChild() {
		Bencall bencall = bencall(Constants.ASSOCIATE);
		OutboundCalls call = outboundCall("ecd1");
		CallClosureDTO motherRequest = request();
		motherRequest.setIsStickyAgentRequired(true);
		when(bencallRepo.findByBenCallId(1L)).thenReturn(bencall);
		when(outboundCallsRepo.findByObCallId(2L)).thenReturn(call);
		when(callConfigurationRepo.getCallConfiguration(1))
				.thenReturn(configurations(configuration("ecd1", "LMP", "days", 30)));

		service.closeCall(motherRequest);
		verify(outboundCallsRepo).stickyMotherAgentAllocation(2L, 3L, 7);

		CallClosureDTO childRequest = request();
		childRequest.setIsStickyAgentRequired(true);
		childRequest.setChildId(4L);
		service.closeCall(childRequest);
		verify(outboundCallsRepo).stickyChildAgentAllocation(2L, 4L, 7);
	}

	@Test
	void closeCallUpdatesCorrectPhoneNumberForMotherAndChild() {
		Bencall bencall = bencall(Constants.ASSOCIATE);
		OutboundCalls motherCall = outboundCall("ecd1");
		CallClosureDTO request = request();
		request.setCorrectPhoneNumber("8888888888");
		when(bencallRepo.findByBenCallId(1L)).thenReturn(bencall);
		when(outboundCallsRepo.findByObCallId(2L)).thenReturn(motherCall);
		when(callConfigurationRepo.getCallConfiguration(1))
				.thenReturn(configurations(configuration("ecd1", "LMP", "days", 30)));

		service.closeCall(request);
		verify(motherRecordRepo).updateCorrectPhoneNumber("8888888888", 3L);

		OutboundCalls childCall = outboundCall("ecd1");
		childCall.setChildId(4L);
		when(outboundCallsRepo.findByObCallId(2L)).thenReturn(childCall);
		service.closeCall(request);
		verify(childRecordRepo).updateCorrectPhoneNumber("8888888888", 4L);
	}

	@Test
	void closeCallFailsWhenPreferredLanguageUpdateFailsForMother() {
		Bencall bencall = bencall(Constants.ASSOCIATE);
		OutboundCalls call = outboundCall("ecd1");
		CallClosureDTO request = request();
		request.setPreferredLanguage("Hindi");
		when(bencallRepo.findByBenCallId(1L)).thenReturn(bencall);
		when(outboundCallsRepo.findByObCallId(2L)).thenReturn(call);
		when(callConfigurationRepo.getCallConfiguration(1))
				.thenReturn(configurations(configuration("ecd1", "LMP", "days", 30)));
		org.mockito.Mockito.doThrow(new IllegalStateException("db")).when(motherRecordRepo)
				.updatePreferredLanguage(anyString(), anyLong());

		assertThrows(ECDException.class, () -> service.closeCall(request));
	}

	@Test
	void closeCallFailsWhenPreferredLanguageUpdateFailsForChild() {
		Bencall bencall = bencall(Constants.ASSOCIATE);
		OutboundCalls call = outboundCall("ecd1");
		call.setChildId(4L);
		CallClosureDTO request = request();
		request.setChildId(4L);
		request.setPreferredLanguage("Hindi");
		when(bencallRepo.findByBenCallId(1L)).thenReturn(bencall);
		when(outboundCallsRepo.findByObCallId(2L)).thenReturn(call);
		when(callConfigurationRepo.getCallConfiguration(1))
				.thenReturn(configurations(configuration("ecd1", "LMP", "days", 30)));
		org.mockito.Mockito.doThrow(new IllegalStateException("db")).when(childRecordRepo)
				.updatePreferredLanguage(anyString(), anyLong());

		assertThrows(ECDException.class, () -> service.closeCall(request));
	}

	@Test
	void closeCallIgnoresBlankPreferredLanguage() {
		Bencall bencall = bencall(Constants.ASSOCIATE);
		OutboundCalls call = outboundCall("ecd1");
		CallClosureDTO request = request();
		request.setPreferredLanguage("   ");
		when(bencallRepo.findByBenCallId(1L)).thenReturn(bencall);
		when(outboundCallsRepo.findByObCallId(2L)).thenReturn(call);
		when(callConfigurationRepo.getCallConfiguration(1))
				.thenReturn(configurations(configuration("ecd1", "LMP", "days", 30)));

		service.closeCall(request);

		verify(motherRecordRepo, never()).updatePreferredLanguage(anyString(), anyLong());
	}

	@Test
	void closeCallFailsWhenOutboundCallMissing() {
		when(bencallRepo.findByBenCallId(1L)).thenReturn(bencall(Constants.ASSOCIATE));
		when(outboundCallsRepo.findByObCallId(2L)).thenReturn(null);

		assertThrows(ECDException.class, () -> service.closeCall(request()));
	}

	@Test
	void closeCallFailsWhenBeneficiaryCallMissing() {
		when(bencallRepo.findByBenCallId(1L)).thenReturn(null);
		when(outboundCallsRepo.findByObCallId(2L)).thenReturn(outboundCall("ecd1"));
		when(callConfigurationRepo.getCallConfiguration(1))
				.thenReturn(configurations(configuration("ecd1", "LMP", "days", 30)));

		assertThrows(ECDException.class, () -> service.closeCall(request()));
	}

	@Test
	void closeCallFailsWhenRchIdMissingForIntroductoryFollowUps() {
		Bencall bencall = bencall(Constants.ASSOCIATE);
		OutboundCalls call = outboundCall("introductory");
		call.setMotherId(null);
		CallClosureDTO request = request();
		request.setMotherId(null);
		request.setEcdCallType("introductory");
		request.setIsFurtherCallRequired(true);
		when(bencallRepo.findByBenCallId(1L)).thenReturn(bencall);
		when(outboundCallsRepo.findByObCallId(2L)).thenReturn(call);
		when(callConfigurationRepo.getCallConfiguration(1))
				.thenReturn(configurations(configuration("ecd1", "LMP", "days", 30)));

		assertThrows(ECDException.class, () -> service.closeCall(request));
	}

	@Test
	void callDurationIsFormattedForShortCalls() {
		Timestamp start = Timestamp.valueOf("2024-01-01 10:00:00");

		assertEquals("1 sec ", ReflectionTestUtils.invokeMethod(service, "calculateCallDuration", start,
				Timestamp.valueOf("2024-01-01 10:00:01")));
		assertEquals("1 min ", ReflectionTestUtils.invokeMethod(service, "calculateCallDuration", start,
				Timestamp.valueOf("2024-01-01 10:01:00")));
		assertEquals("2 hours 2 mins 2 secs ", ReflectionTestUtils.invokeMethod(service, "calculateCallDuration",
				start, Timestamp.valueOf("2024-01-01 12:02:02")));
		assertEquals("", ReflectionTestUtils.invokeMethod(service, "calculateCallDuration", start, start));
	}

	@Test
	void closeCallCompletesCallWhenFurtherCallFlagsAreAbsent() {
		Bencall bencall = bencall(Constants.ASSOCIATE);
		OutboundCalls call = outboundCall("ecd1");
		CallClosureDTO request = request();
		request.setIsFurtherCallRequired(null);
		request.setIsCallDisconnected(null);
		request.setIsCallAnswered(null);
		request.setEcdCallType("ecd1");
		when(bencallRepo.findByBenCallId(1L)).thenReturn(bencall);
		when(outboundCallsRepo.findByObCallId(2L)).thenReturn(call);
		when(callConfigurationRepo.getCallConfiguration(1))
				.thenReturn(configurations(configuration("ecd1", "LMP", "days", 30)));

		service.closeCall(request);

		assertEquals(Constants.COMPLETED, call.getCallStatus());
		verify(outboundCallsRepo, never()).saveAll(any());
	}

	@Test
	void closeCallSkipsUpcomingUpdatesForIntroductoryCalls() {
		Bencall bencall = bencall(Constants.ASSOCIATE);
		OutboundCalls call = outboundCall("introductory");
		call.setIsHighRisk(true);
		when(bencallRepo.findByBenCallId(1L)).thenReturn(bencall);
		when(outboundCallsRepo.findByObCallId(2L)).thenReturn(call);
		when(callConfigurationRepo.getCallConfiguration(1))
				.thenReturn(configurations(configuration("ecd1", "LMP", "days", 30)));

		service.closeCall(request());

		verify(outboundCallsRepo, never()).updateHRPForUpcomingCall(anyLong(), anyBoolean(), any());
	}
}
