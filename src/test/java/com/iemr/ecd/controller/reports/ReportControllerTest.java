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
package com.iemr.ecd.controller.reports;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;

import com.iemr.ecd.service.report.ReportService;

class ReportControllerTest {

	private static final String REQUEST = "{\"fromDate\":\"2024-01-01\"}";

	@FunctionalInterface
	private interface Stub {
		void apply(ReportService service, Answer<ByteArrayInputStream> answer) throws Exception;
	}

	@FunctionalInterface
	private interface Invoke {
		ResponseEntity<Object> apply(ReportController controller) throws Exception;
	}

	private record Endpoint(String name, Stub stub, Invoke invoke) {
	}

	private ReportService reportService;
	private ReportController controller;

	@BeforeEach
	void setUp() {
		reportService = Mockito.mock(ReportService.class);
		controller = new ReportController();
		ReflectionTestUtils.setField(controller, "reportService", reportService);
	}

	private List<Endpoint> endpoints() {
		List<Endpoint> endpoints = new ArrayList<>();
		endpoints.add(new Endpoint("callDetails",
				(s, a) -> when(s.getCallDetailsReport(anyString(), anyString())).thenAnswer(a),
				c -> c.getCallDetailsReport(REQUEST)));
		endpoints.add(new Endpoint("callSummary",
				(s, a) -> when(s.getCallSummaryReport(anyString(), anyString())).thenAnswer(a),
				c -> c.getCallSummaryReport(REQUEST)));
		endpoints.add(new Endpoint("cumulativeDistrict",
				(s, a) -> when(s.getCumulativeDistrictReport(anyString(), anyString())).thenAnswer(a),
				c -> c.getCumulativeDistrictReport(REQUEST)));
		endpoints.add(new Endpoint("beneficiarywiseFollowup",
				(s, a) -> when(s.getBeneficiarywisefollowupdetails(anyString(), anyString())).thenAnswer(a),
				c -> c.getBeneficiarywisefollowupdetails(REQUEST)));
		endpoints.add(new Endpoint("callDetailUnique",
				(s, a) -> when(s.getCallDetailReportUnique(anyString(), anyString())).thenAnswer(a),
				c -> c.getCallDetailReportUnique(REQUEST)));
		endpoints.add(new Endpoint("birthDefect",
				(s, a) -> when(s.getBirthDefectReport(anyString(), anyString())).thenAnswer(a),
				c -> c.getBirthDefectReport(REQUEST)));
		endpoints.add(new Endpoint("aashaHomeVisitGap",
				(s, a) -> when(s.getAashaHomeVisitGapReport(anyString(), anyString())).thenAnswer(a),
				c -> c.getAashaHomeVisitGapReport(REQUEST)));
		endpoints.add(new Endpoint("calciumIfaNonadherence",
				(s, a) -> when(s.getCalciumIFATabNonadherenceReport(anyString(), anyString())).thenAnswer(a),
				c -> c.getCalciumIFATabNonadherenceReport(REQUEST)));
		endpoints.add(new Endpoint("absenceInVhsnd",
				(s, a) -> when(s.getAbsenceInVHSNDReport(anyString(), anyString())).thenAnswer(a),
				c -> c.getAbsenceInVHSNDReport(REQUEST)));
		endpoints.add(new Endpoint("vaccineDropOut",
				(s, a) -> when(s.getVaccineDropOutIdentifiedReport(anyString(), anyString())).thenAnswer(a),
				c -> c.getVaccineDropOutIdentifiedReport(REQUEST)));
		endpoints.add(new Endpoint("vaccineLeftOut",
				(s, a) -> when(s.getVaccineLeftOutIdentifiedReport(anyString(), anyString())).thenAnswer(a),
				c -> c.getVaccineLeftOutIdentifiedReport(REQUEST)));
		endpoints.add(new Endpoint("developmentalDelay",
				(s, a) -> when(s.getDevelopmentalDelayReport(anyString(), anyString())).thenAnswer(a),
				c -> c.getDevelopmentalDelayReport(REQUEST)));
		endpoints.add(new Endpoint("abortion",
				(s, a) -> when(s.getAbortionReport(anyString(), anyString())).thenAnswer(a),
				c -> c.getAbortionReport(REQUEST)));
		endpoints.add(new Endpoint("deliveryStatus",
				(s, a) -> when(s.getDeliveryStatusReport(anyString(), anyString())).thenAnswer(a),
				c -> c.getDeliveryStatusReport(REQUEST)));
		endpoints.add(new Endpoint("hrpCasesIdentified",
				(s, a) -> when(s.getHRPCasesIdentifiedReport(anyString(), anyString())).thenAnswer(a),
				c -> c.getHRPCasesIdentifiedReport(REQUEST)));
		endpoints.add(new Endpoint("infantsHighRisk",
				(s, a) -> when(s.getInfantsHighRiskReport(anyString(), anyString())).thenAnswer(a),
				c -> c.getInfantsHighRiskReport(REQUEST)));
		endpoints.add(new Endpoint("maternalDeath",
				(s, a) -> when(s.getMaternalDeathReport(anyString(), anyString())).thenAnswer(a),
				c -> c.getMaternalDeathReport(REQUEST)));
		endpoints.add(new Endpoint("stillBirth",
				(s, a) -> when(s.getStillBirthReport(anyString(), anyString())).thenAnswer(a),
				c -> c.getStillBirthReport(REQUEST)));
		endpoints.add(new Endpoint("babyDeath",
				(s, a) -> when(s.getBabyDeathReport(anyString(), anyString())).thenAnswer(a),
				c -> c.getBabyDeathReport(REQUEST)));
		endpoints.add(new Endpoint("notConnectedPhonelist",
				(s, a) -> when(s.getNotConnectedPhonelistDiffformatReport(anyString(), anyString())).thenAnswer(a),
				c -> c.getNotConnectedPhonelistDiffformatReport(REQUEST)));
		endpoints.add(new Endpoint("jsyRelatedComplaints",
				(s, a) -> when(s.getJSYRelatedComplaintsReport(anyString(), anyString())).thenAnswer(a),
				c -> c.getJSYRelatedComplaintsReport(REQUEST)));
		endpoints.add(new Endpoint("miscarriage",
				(s, a) -> when(s.getMiscarriageReport(anyString(), anyString())).thenAnswer(a),
				c -> c.getMiscarriageReport(REQUEST)));
		return endpoints;
	}

	@Test
	void allEndpointsReturnXlsxAttachmentWhenReportIsGenerated() throws Exception {
		for (Endpoint endpoint : endpoints()) {
			setUp();
			endpoint.stub().apply(reportService, invocation -> new ByteArrayInputStream("report".getBytes()));

			ResponseEntity<Object> response = endpoint.invoke().apply(controller);

			assertEquals(HttpStatus.OK, response.getStatusCode(), endpoint.name());
			assertNotNull(response.getBody(), endpoint.name());
			assertTrue(response.getHeaders().getFirst(HttpHeaders.CONTENT_DISPOSITION).endsWith(".xlsx"),
					endpoint.name());
			assertEquals("application/vnd.ms-excel", response.getHeaders().getContentType().toString(),
					endpoint.name());
		}
	}

	@Test
	void allEndpointsReturnNoDataFoundWhenReportIsEmpty() throws Exception {
		for (Endpoint endpoint : endpoints()) {
			setUp();
			endpoint.stub().apply(reportService, invocation -> null);

			ResponseEntity<Object> response = endpoint.invoke().apply(controller);

			assertEquals(HttpStatus.OK, response.getStatusCode(), endpoint.name());
			assertEquals("No data found", response.getBody(), endpoint.name());
		}
	}

	@Test
	void allEndpointsReturnServerErrorWhenServiceFails() throws Exception {
		for (Endpoint endpoint : endpoints()) {
			setUp();
			endpoint.stub().apply(reportService, invocation -> {
				throw new IllegalStateException("boom");
			});

			ResponseEntity<Object> response = endpoint.invoke().apply(controller);

			assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode(), endpoint.name());
			assertEquals("boom", response.getBody(), endpoint.name());
		}
	}

	@Test
	void getFileNameUsesRequestedFileName() {
		assertEquals("custom", controller.getFileName("{\"fileName\":\"custom\"}", "fallback"));
	}

	@Test
	void getFileNameFallsBackToDefaultName() {
		assertEquals("fallback", controller.getFileName("{\"other\":\"x\"}", "fallback"));
	}
}
