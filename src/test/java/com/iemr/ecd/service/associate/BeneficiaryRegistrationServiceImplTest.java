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
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedConstruction;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import com.iemr.ecd.dto.RequestBeneficiaryDemographicsDTO;
import com.iemr.ecd.dto.RequestBeneficiaryRegistrationDTO;
import com.iemr.ecd.repo.call_conf_allocation.ChildRecordRepo;
import com.iemr.ecd.repo.call_conf_allocation.MotherRecordRepo;
import com.iemr.ecd.repo.call_conf_allocation.OutboundCallsRepo;
import com.iemr.ecd.utils.advice.exception_handler.ECDException;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class BeneficiaryRegistrationServiceImplTest {

	private static final String SUCCESS_BODY = "{\"data\":{\"beneficiaryRegID\":111,\"beneficiaryID\":222}}";

	@Mock
	private MotherRecordRepo motherRecordRepo;
	@Mock
	private ChildRecordRepo childRecordRepo;
	@Mock
	private OutboundCallsRepo outboundCallsRepo;

	private BeneficiaryRegistrationServiceImpl service;

	@BeforeEach
	void setUp() {
		service = new BeneficiaryRegistrationServiceImpl();
		ReflectionTestUtils.setField(service, "motherRecordRepo", motherRecordRepo);
		ReflectionTestUtils.setField(service, "childRecordRepo", childRecordRepo);
		ReflectionTestUtils.setField(service, "outboundCallsRepo", outboundCallsRepo);
		ReflectionTestUtils.setField(service, "registerBeneficiaryUrl", "http://common/register");
		ReflectionTestUtils.setField(service, "beneficiaryEditUrl", "http://common/edit");
	}

	private RequestBeneficiaryRegistrationDTO motherRequest() {
		RequestBeneficiaryRegistrationDTO request = new RequestBeneficiaryRegistrationDTO();
		request.setName("Asha Devi");
		request.setMotherId(5L);
		request.setPhoneNo("9999999999");
		request.setPhoneNoOfWhom("Self");
		request.setLmp(new Timestamp(0));
		request.setEdd(new Timestamp(1000));
		request.setAge(28);
		RequestBeneficiaryDemographicsDTO demographics = new RequestBeneficiaryDemographicsDTO();
		demographics.setAddressLine1("Address 1");
		request.setI_bendemographics(demographics);
		return request;
	}

	private RequestBeneficiaryRegistrationDTO childRequest() {
		RequestBeneficiaryRegistrationDTO request = motherRequest();
		request.setMotherId(null);
		request.setChildId(6L);
		request.setMotherName("Asha Devi");
		request.setGenderName("Female");
		return request;
	}

	private MockedConstruction<RestTemplate> restTemplateReturning(ResponseEntity<String> response) {
		return Mockito.mockConstruction(RestTemplate.class, (mock, context) -> when(
				mock.exchange(any(String.class), eq(HttpMethod.POST), any(), eq(String.class)))
						.thenReturn(response));
	}

	@Test
	void registrationSplitsNameAndUpdatesMotherRecord() {
		RequestBeneficiaryRegistrationDTO request = motherRequest();

		String response;
		try (MockedConstruction<RestTemplate> ignored = restTemplateReturning(
				new ResponseEntity<>(SUCCESS_BODY, HttpStatus.OK))) {
			response = service.beneficiaryRegistration(request, "auth");
		}

		assertEquals("Asha", request.getFirstName());
		assertEquals("Devi", request.getLastName());
		assertTrue(response.contains("222"));
		assertTrue(response.contains("111"));
		verify(motherRecordRepo).updateBeneficiaryRegIdForMother(eq(111L), eq(5L), eq("Asha Devi"), any(), any(),
				any(), any(), any(), any(), any(), any(), any(), any(), any(), any(), any());
		verify(outboundCallsRepo).updateBeneficiaryRegIdForMother(111L, 5L, "Self");
		verify(childRecordRepo, never()).updateBeneficiaryRegIdForChild(any(), any(), any(), any(), any(), any(),
				any(), any(), any(), any(), any(), any(), any(), any(), any(), any());
	}

	@Test
	void registrationKeepsSingleWordNameAsFirstName() {
		RequestBeneficiaryRegistrationDTO request = motherRequest();
		request.setName("Asha");

		try (MockedConstruction<RestTemplate> ignored = restTemplateReturning(
				new ResponseEntity<>(SUCCESS_BODY, HttpStatus.OK))) {
			service.beneficiaryRegistration(request, "auth");
		}

		assertEquals("Asha", request.getFirstName());
		assertNull(request.getLastName());
	}

	@Test
	void registrationParsesDateOfBirth() {
		RequestBeneficiaryRegistrationDTO request = childRequest();
		request.setDateOfBirth("2024-01-15T10:30:00");

		try (MockedConstruction<RestTemplate> ignored = restTemplateReturning(
				new ResponseEntity<>(SUCCESS_BODY, HttpStatus.OK))) {
			service.beneficiaryRegistration(request, "auth");
		}

		assertEquals(Timestamp.valueOf("2024-01-15 10:30:00"), request.getDOB());
	}

	@Test
	void registrationUpdatesChildRecordWhenChildIdPresent() {
		RequestBeneficiaryRegistrationDTO request = childRequest();

		try (MockedConstruction<RestTemplate> ignored = restTemplateReturning(
				new ResponseEntity<>(SUCCESS_BODY, HttpStatus.OK))) {
			service.beneficiaryRegistration(request, "auth");
		}

		verify(childRecordRepo).updateBeneficiaryRegIdForChild(eq(111L), eq(6L), eq("Asha Devi"), any(), any(),
				any(), any(), any(), any(), any(), any(), any(), any(), any(), any(), any());
		verify(outboundCallsRepo).updateBeneficiaryRegIdForChild(111L, 6L, "Self");
	}

	@Test
	void registrationFailsWhenCommonServiceRejectsRequest() {
		RequestBeneficiaryRegistrationDTO request = motherRequest();

		try (MockedConstruction<RestTemplate> ignored = restTemplateReturning(
				new ResponseEntity<>(HttpStatus.BAD_REQUEST))) {
			assertThrows(ECDException.class, () -> service.beneficiaryRegistration(request, "auth"));
		}
	}

	@Test
	void registrationFailsWhenResponseBodyIsUnparseable() {
		RequestBeneficiaryRegistrationDTO request = motherRequest();

		try (MockedConstruction<RestTemplate> ignored = restTemplateReturning(
				new ResponseEntity<>("not-json", HttpStatus.OK))) {
			assertThrows(ECDException.class, () -> service.beneficiaryRegistration(request, "auth"));
		}
	}

	@Test
	void registrationFailsOnInvalidDateOfBirth() {
		RequestBeneficiaryRegistrationDTO request = motherRequest();
		request.setDateOfBirth("not-a-date");

		assertThrows(ECDException.class, () -> service.beneficiaryRegistration(request, "auth"));
	}

	@Test
	void updateBeneficiaryDetailsUpdatesMotherRecord() {
		RequestBeneficiaryRegistrationDTO request = motherRequest();
		request.setBeneficiaryRegID(111L);

		String response;
		try (MockedConstruction<RestTemplate> ignored = restTemplateReturning(
				new ResponseEntity<>("{}", HttpStatus.OK))) {
			response = service.updateBeneficiaryDetails(request, "auth");
		}

		assertTrue(response.contains("Beneficiary Updated Successfully"));
		verify(motherRecordRepo).updateBeneficiaryDetails(eq(111L), eq(5L), eq("Asha Devi"), any(), any(), any(),
				any(), any(), any(), any(), any(), any(), any(), any(), any(), any());
		verify(outboundCallsRepo).updatePhoneNoTypeForMother(111L, 5L, "Self");
	}

	@Test
	void updateBeneficiaryDetailsUpdatesChildRecord() {
		RequestBeneficiaryRegistrationDTO request = childRequest();
		request.setBeneficiaryRegID(111L);
		request.setName("Baby");

		try (MockedConstruction<RestTemplate> ignored = restTemplateReturning(
				new ResponseEntity<>("{}", HttpStatus.OK))) {
			service.updateBeneficiaryDetails(request, "auth");
		}

		assertEquals("Baby", request.getFirstName());
		verify(childRecordRepo).updateBeneficiaryDetails(eq(111L), eq(6L), eq("Baby"), any(), any(), any(), any(),
				any(), any(), any(), any(), any(), any(), any(), any(), any());
		verify(outboundCallsRepo).updatePhoneNoTypeForChild(111L, 6L, "Self");
	}

	@Test
	void updateBeneficiaryDetailsParsesDateOfBirth() {
		RequestBeneficiaryRegistrationDTO request = childRequest();
		request.setDateOfBirth("2024-01-15T10:30:00");

		try (MockedConstruction<RestTemplate> ignored = restTemplateReturning(
				new ResponseEntity<>("{}", HttpStatus.OK))) {
			service.updateBeneficiaryDetails(request, "auth");
		}

		assertEquals(Timestamp.valueOf("2024-01-15 10:30:00"), request.getDOB());
	}

	@Test
	void updateBeneficiaryDetailsFailsWhenCommonServiceRejectsRequest() {
		RequestBeneficiaryRegistrationDTO request = motherRequest();

		try (MockedConstruction<RestTemplate> ignored = restTemplateReturning(
				new ResponseEntity<>(HttpStatus.BAD_REQUEST))) {
			assertThrows(ECDException.class, () -> service.updateBeneficiaryDetails(request, "auth"));
		}
	}
}
