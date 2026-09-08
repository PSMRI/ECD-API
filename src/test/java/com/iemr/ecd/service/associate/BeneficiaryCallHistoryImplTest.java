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
import static org.mockito.ArgumentMatchers.anyLong;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import com.iemr.ecd.dao.associate.Bencall;
import com.iemr.ecd.dao.associate.ECDCallResponse;
import com.iemr.ecd.dao.associate.OutboundCalls;
import com.iemr.ecd.dao_temp.Pr_GetCallHistory;
import com.iemr.ecd.dto.RequestBeneficiaryQuestionnaireResponseDTO;
import com.iemr.ecd.repo.call_conf_allocation.OutboundCallsRepo;
import com.iemr.ecd.repository.ecd.ECDCallResponseRepo;
import com.iemr.ecd.repository.quality.T_benCallRepo;
import com.iemr.ecd.utils.advice.exception_handler.ECDException;

@ExtendWith(MockitoExtension.class)
class BeneficiaryCallHistoryImplTest {

	@Mock
	private OutboundCallsRepo outboundCallsRepo;
	@Mock
	private T_benCallRepo tBenCallRepo;
	@Mock
	private ECDCallResponseRepo ecdCallResponseRepo;

	@InjectMocks
	private BeneficiaryCallHistoryImpl service;

	@Test
	void getBeneficiaryCallHistoryUsesMotherQueryWhenMotherIdGiven() {
		List<OutboundCalls> calls = List.of(new OutboundCalls());
		when(outboundCallsRepo.getCallHistoryForMother(5L)).thenReturn(calls);

		assertSame(calls, service.getBeneficiaryCallHistory(5L, null));
	}

	@Test
	void getBeneficiaryCallHistoryUsesChildQueryWhenOnlyChildIdGiven() {
		List<OutboundCalls> calls = List.of(new OutboundCalls());
		when(outboundCallsRepo.getCallHistoryForChild(6L)).thenReturn(calls);

		assertSame(calls, service.getBeneficiaryCallHistory(null, 6L));
	}

	@Test
	void getBeneficiaryCallHistoryReturnsEmptyWhenNoIdsGiven() {
		assertTrue(service.getBeneficiaryCallHistory(null, null).isEmpty());
	}

	@Test
	void getBeneficiaryCallHistoryWrapsRepositoryFailure() {
		when(outboundCallsRepo.getCallHistoryForMother(anyLong())).thenThrow(new IllegalStateException("db"));

		assertThrows(ECDException.class, () -> service.getBeneficiaryCallHistory(5L, null));
	}

	@Test
	void getBeneficiaryCallDetailsMapsAllColumns() {
		String[] row = { "call-1", "5", "Mother", "6", "Child", "9999999999", "Asha", "ANM",
				"2024-01-01 00:00:00", "2024-02-01 00:00:00", "2024-03-01 10:00:00", "Mother", "Fever",
				"remarks", "advise" };
		when(outboundCallsRepo.getBeneficiaryCallDetails(7L)).thenReturn(List.<String[]>of(row));

		Pr_GetCallHistory details = service.getBeneficiaryCallDetails(7L);

		assertEquals("call-1", details.getCallId());
		assertEquals(5L, details.getMotherId());
		assertEquals("Mother", details.getMotherName());
		assertEquals(6L, details.getChildId());
		assertEquals("Child", details.getChildName());
		assertEquals("9999999999", details.getPhoneNo());
		assertEquals("Asha", details.getAshaName());
		assertEquals("ANM", details.getRole());
		assertEquals(Timestamp.valueOf("2024-01-01 00:00:00"), details.getLmpDate());
		assertEquals(Timestamp.valueOf("2024-02-01 00:00:00"), details.getDob());
		assertEquals(Timestamp.valueOf("2024-03-01 10:00:00"), details.getCallTime());
		assertEquals("Mother", details.getEcdCallType());
		assertEquals("Fever", details.getTypeOfComplaint());
		assertEquals("remarks", details.getComplaintRemarks());
		assertEquals("advise", details.getAdviseProvided());
	}

	@Test
	void getBeneficiaryCallDetailsSkipsNullColumns() {
		when(outboundCallsRepo.getBeneficiaryCallDetails(7L)).thenReturn(List.<String[]>of(new String[15]));

		Pr_GetCallHistory details = service.getBeneficiaryCallDetails(7L);

		assertNull(details.getCallId());
		assertNull(details.getMotherId());
	}

	@Test
	void getBeneficiaryCallDetailsHandlesEmptyResult() {
		when(outboundCallsRepo.getBeneficiaryCallDetails(7L)).thenReturn(List.of());

		assertNull(service.getBeneficiaryCallDetails(7L).getCallId());
	}

	@Test
	void getBeneficiaryCallDetailsHandlesNullResult() {
		when(outboundCallsRepo.getBeneficiaryCallDetails(7L)).thenReturn(null);

		assertNull(service.getBeneficiaryCallDetails(7L).getCallId());
	}

	@Test
	void getBeneficiaryCallDetailsWrapsFailure() {
		when(outboundCallsRepo.getBeneficiaryCallDetails(anyLong())).thenThrow(new IllegalStateException("db"));

		assertThrows(ECDException.class, () -> service.getBeneficiaryCallDetails(7L));
	}

	@Test
	void saveBenCallDetailsStampsCallTimeAndReturnsId() {
		Bencall bencall = new Bencall();
		Bencall saved = new Bencall();
		saved.setBenCallId(42L);
		when(tBenCallRepo.getCallTypeId()).thenReturn(3);
		when(tBenCallRepo.save(bencall)).thenReturn(saved);

		String response = service.saveBenCallDetails(bencall);

		assertEquals(3, bencall.getCallTypeId());
		assertTrue(bencall.getCallTime() != null);
		assertTrue(response.contains("42"));
	}

	@Test
	void saveBenCallDetailsKeepsProvidedCallTypeId() {
		Bencall bencall = new Bencall();
		bencall.setCallTypeId(9);
		Bencall saved = new Bencall();
		saved.setBenCallId(43L);
		when(tBenCallRepo.save(bencall)).thenReturn(saved);

		service.saveBenCallDetails(bencall);

		assertEquals(9, bencall.getCallTypeId());
		verify(tBenCallRepo, never()).getCallTypeId();
	}

	@Test
	void saveBenCallDetailsWrapsFailure() {
		Bencall bencall = new Bencall();
		bencall.setCallTypeId(9);
		when(tBenCallRepo.save(bencall)).thenThrow(new IllegalStateException("db"));

		assertThrows(ECDException.class, () -> service.saveBenCallDetails(bencall));
	}

	private RequestBeneficiaryQuestionnaireResponseDTO questionnaireRequest() {
		RequestBeneficiaryQuestionnaireResponseDTO request = new RequestBeneficiaryQuestionnaireResponseDTO();
		request.setObCallId(1L);
		request.setBenCallId(2L);
		request.setMotherId(3L);
		request.setChildId(4L);
		request.setEcdCallType("Mother");
		request.setPsmId(1);
		request.setCreatedBy("agent");
		request.setQuestionnaireResponse(List.of(new ECDCallResponse()));
		return request;
	}

	@Test
	void saveQuestionnaireResponseCopiesRequestContextOntoResponses() {
		RequestBeneficiaryQuestionnaireResponseDTO request = questionnaireRequest();
		when(ecdCallResponseRepo.getEcdCallResponseList(2L, 1L)).thenReturn("Updation has done Successfully");

		String response = service.saveBeneficiaryQuestionnaireResponse(request);

		ECDCallResponse saved = request.getQuestionnaireResponse().get(0);
		assertEquals(1L, saved.getObCallId());
		assertEquals(2L, saved.getBenCallId());
		assertEquals(3L, saved.getMotherId());
		assertEquals(4L, saved.getChildId());
		assertEquals("Mother", saved.getEcdCallType());
		assertEquals(1, saved.getPsmId());
		assertEquals("agent", saved.getCreatedBy());
		verify(ecdCallResponseRepo).saveAll(request.getQuestionnaireResponse());
		assertTrue(response.contains("Questionnaire response saved successfully"));
	}

	@Test
	void saveQuestionnaireResponseSkipsOptionalBeneficiaryIds() {
		RequestBeneficiaryQuestionnaireResponseDTO request = questionnaireRequest();
		request.setMotherId(null);
		request.setChildId(null);
		when(ecdCallResponseRepo.getEcdCallResponseList(2L, 1L)).thenReturn("Updation has done Successfully");

		service.saveBeneficiaryQuestionnaireResponse(request);

		assertNull(request.getQuestionnaireResponse().get(0).getMotherId());
		assertNull(request.getQuestionnaireResponse().get(0).getChildId());
	}

	@Test
	void saveQuestionnaireResponseFailsWhenStoredProcedureDoesNotConfirm() {
		RequestBeneficiaryQuestionnaireResponseDTO request = questionnaireRequest();
		when(ecdCallResponseRepo.getEcdCallResponseList(2L, 1L)).thenReturn("failed");

		assertThrows(ECDException.class, () -> service.saveBeneficiaryQuestionnaireResponse(request));
	}

	@Test
	void saveQuestionnaireResponseRejectsEmptyRequest() {
		RequestBeneficiaryQuestionnaireResponseDTO empty = new RequestBeneficiaryQuestionnaireResponseDTO();
		empty.setQuestionnaireResponse(List.of());

		assertThrows(ECDException.class, () -> service.saveBeneficiaryQuestionnaireResponse(empty));
		assertThrows(ECDException.class, () -> service
				.saveBeneficiaryQuestionnaireResponse(new RequestBeneficiaryQuestionnaireResponseDTO()));
		assertThrows(ECDException.class, () -> service.saveBeneficiaryQuestionnaireResponse(null));
		verify(ecdCallResponseRepo, never()).saveAll(any());
	}

	@Test
	void getHrpHrniDetailsReturnsChildRecordWhenChildIdGiven() {
		ECDCallResponse expected = new ECDCallResponse();
		Page<ECDCallResponse> page = new PageImpl<>(List.of(expected));
		when(ecdCallResponseRepo.getHrniDetailsChild(any(), any())).thenReturn(page);

		assertSame(expected, service.getHrpHrniDetails(null, 6L));
	}

	@Test
	void getHrpHrniDetailsReturnsEmptyRecordWhenChildPageEmpty() {
		when(ecdCallResponseRepo.getHrniDetailsChild(any(), any())).thenReturn(new PageImpl<>(List.of()));

		assertNull(service.getHrpHrniDetails(null, 6L).getEcdCallResponseId());
	}

	@Test
	void getHrpHrniDetailsReturnsMotherRecordWhenMotherIdGiven() {
		ECDCallResponse expected = new ECDCallResponse();
		when(ecdCallResponseRepo.getHrpDetailsMother(any(), any())).thenReturn(new PageImpl<>(List.of(expected)));

		assertSame(expected, service.getHrpHrniDetails(5L, null));
	}

	@Test
	void getHrpHrniDetailsReturnsEmptyRecordWhenMotherPageEmpty() {
		when(ecdCallResponseRepo.getHrpDetailsMother(any(), any())).thenReturn(new PageImpl<>(List.of()));

		assertNull(service.getHrpHrniDetails(5L, null).getEcdCallResponseId());
	}

	@Test
	void getHrpHrniDetailsRejectsMissingIds() {
		assertThrows(ECDException.class, () -> service.getHrpHrniDetails(null, null));
	}
}
