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

import com.iemr.ecd.dao.associate.Bencall;
import com.iemr.ecd.dao.associate.ECDCallResponse;
import com.iemr.ecd.dao.associate.OutboundCalls;
import com.iemr.ecd.dao_temp.Pr_GetCallHistory;
import com.iemr.ecd.dto.RequestBeneficiaryQuestionnaireResponseDTO;
import com.iemr.ecd.service.associate.BeneficiaryCallHistoryImpl;

@ExtendWith(MockitoExtension.class)
class BeneficiaryCallHistoryControllerTest {

	@Mock
	private BeneficiaryCallHistoryImpl beneficiaryCallHistoryImpl;

	@InjectMocks
	private BeneficiaryCallHistoryController controller;

	@Test
	void getBeneficiaryCallHistoryDelegatesToService() {
		List<OutboundCalls> calls = List.of(new OutboundCalls());
		when(beneficiaryCallHistoryImpl.getBeneficiaryCallHistory(1L, 2L)).thenReturn(calls);

		ResponseEntity<Object> response = controller.getBeneficiaryCallHistory(1L, 2L);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertSame(calls, response.getBody());
	}

	@Test
	void getBeneficiaryCallDetailsDelegatesToService() {
		Pr_GetCallHistory history = new Pr_GetCallHistory();
		when(beneficiaryCallHistoryImpl.getBeneficiaryCallDetails(7L)).thenReturn(history);

		ResponseEntity<Object> response = controller.getBeneficiaryCallDetails(7L);

		assertSame(history, response.getBody());
	}

	@Test
	void saveBenCallDetailsDelegatesToService() {
		Bencall bencall = new Bencall();
		when(beneficiaryCallHistoryImpl.saveBenCallDetails(bencall)).thenReturn("saved");

		ResponseEntity<String> response = controller.saveBenCallDetails(bencall);

		assertEquals("saved", response.getBody());
	}

	@Test
	void saveBeneficiaryQuestionnaireResponseDelegatesToService() {
		RequestBeneficiaryQuestionnaireResponseDTO request = new RequestBeneficiaryQuestionnaireResponseDTO();
		when(beneficiaryCallHistoryImpl.saveBeneficiaryQuestionnaireResponse(request)).thenReturn("saved");

		ResponseEntity<String> response = controller.saveBeneficiaryQuestionnaireResponse(request);

		assertEquals("saved", response.getBody());
	}

	@Test
	void getHrpHrniDetailsDelegatesToService() {
		ECDCallResponse callResponse = new ECDCallResponse();
		when(beneficiaryCallHistoryImpl.getHrpHrniDetails(1L, null)).thenReturn(callResponse);

		ResponseEntity<Object> response = controller.getHrpHrniDetails(1L, null);

		assertSame(callResponse, response.getBody());
	}
}
