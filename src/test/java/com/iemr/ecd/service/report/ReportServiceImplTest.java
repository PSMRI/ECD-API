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
package com.iemr.ecd.service.report;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import java.io.ByteArrayInputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import com.iemr.ecd.repository.report.EcdReportRepo;

class ReportServiceImplTest {

	private static final String FULL_REQUEST = "{\"startDate\":\"2024-01-01T00:00:00.000Z\","
			+ "\"endDate\":\"2024-01-31T00:00:00.000Z\",\"role\":\"ASSOCIATE\",\"agentId\":7,\"psmId\":1}";
	private static final String MINIMAL_REQUEST = "{\"psmId\":1}";

	@FunctionalInterface
	private interface Report {
		ByteArrayInputStream generate(ReportServiceImpl service, String request) throws Exception;
	}

	private record Endpoint(String name, Report report) {
	}

	private ReportServiceImpl serviceReturning(List<Object[]> rows) {
		ReportServiceImpl service = new ReportServiceImpl();
		ReflectionTestUtils.setField(service, "ecdReportRepo", mock(EcdReportRepo.class, invocation -> rows));
		return service;
	}

	private List<Endpoint> endpoints() {
		List<Endpoint> endpoints = new ArrayList<>();
		endpoints.add(new Endpoint("callDetails", (s, r) -> s.getCallDetailsReport(r, "f")));
		endpoints.add(new Endpoint("callSummary", (s, r) -> s.getCallSummaryReport(r, "f")));
		endpoints.add(new Endpoint("cumulativeDistrict", (s, r) -> s.getCumulativeDistrictReport(r, "f")));
		endpoints.add(new Endpoint("beneficiarywiseFollowup",
				(s, r) -> s.getBeneficiarywisefollowupdetails(r, "f")));
		endpoints.add(new Endpoint("callDetailUnique", (s, r) -> s.getCallDetailReportUnique(r, "f")));
		endpoints.add(new Endpoint("birthDefect", (s, r) -> s.getBirthDefectReport(r, "f")));
		endpoints.add(new Endpoint("aashaHomeVisitGap", (s, r) -> s.getAashaHomeVisitGapReport(r, "f")));
		endpoints.add(new Endpoint("calciumIfaNonadherence",
				(s, r) -> s.getCalciumIFATabNonadherenceReport(r, "f")));
		endpoints.add(new Endpoint("absenceInVhsnd", (s, r) -> s.getAbsenceInVHSNDReport(r, "f")));
		endpoints.add(new Endpoint("vaccineDropOut", (s, r) -> s.getVaccineDropOutIdentifiedReport(r, "f")));
		endpoints.add(new Endpoint("vaccineLeftOut", (s, r) -> s.getVaccineLeftOutIdentifiedReport(r, "f")));
		endpoints.add(new Endpoint("developmentalDelay", (s, r) -> s.getDevelopmentalDelayReport(r, "f")));
		endpoints.add(new Endpoint("abortion", (s, r) -> s.getAbortionReport(r, "f")));
		endpoints.add(new Endpoint("deliveryStatus", (s, r) -> s.getDeliveryStatusReport(r, "f")));
		endpoints.add(new Endpoint("hrpCasesIdentified", (s, r) -> s.getHRPCasesIdentifiedReport(r, "f")));
		endpoints.add(new Endpoint("infantsHighRisk", (s, r) -> s.getInfantsHighRiskReport(r, "f")));
		endpoints.add(new Endpoint("maternalDeath", (s, r) -> s.getMaternalDeathReport(r, "f")));
		endpoints.add(new Endpoint("stillBirth", (s, r) -> s.getStillBirthReport(r, "f")));
		endpoints.add(new Endpoint("babyDeath", (s, r) -> s.getBabyDeathReport(r, "f")));
		endpoints.add(new Endpoint("notConnectedPhonelist",
				(s, r) -> s.getNotConnectedPhonelistDiffformatReport(r, "f")));
		endpoints.add(new Endpoint("jsyRelatedComplaints", (s, r) -> s.getJSYRelatedComplaintsReport(r, "f")));
		endpoints.add(new Endpoint("miscarriage", (s, r) -> s.getMiscarriageReport(r, "f")));
		return endpoints;
	}

	@Test
	void everyReportIsGeneratedAsAnExcelStreamWhenRowsExist() throws Exception {
		List<Object[]> rows = List.of(new Object[] { "value", "2024-01-01" }, new Object[] { null, 5 });
		ReportServiceImpl service = serviceReturning(rows);

		for (Endpoint endpoint : endpoints()) {
			assertNotNull(endpoint.report().generate(service, FULL_REQUEST), endpoint.name());
		}
	}

	@Test
	void everyReportReturnsNullWhenNoRowsExist() throws Exception {
		ReportServiceImpl service = serviceReturning(List.of());

		for (Endpoint endpoint : endpoints()) {
			assertNull(endpoint.report().generate(service, FULL_REQUEST), endpoint.name());
		}
	}

	@Test
	void everyReportReturnsNullWhenRepositoryReturnsNull() throws Exception {
		ReportServiceImpl service = serviceReturning(null);

		for (Endpoint endpoint : endpoints()) {
			assertNull(endpoint.report().generate(service, MINIMAL_REQUEST), endpoint.name());
		}
	}

	@Test
	void everyReportAcceptsRequestWithoutFiltersAndFailsOnMissingCriteriaDates() {
		ReportServiceImpl service = serviceReturning(List.<Object[]>of(new Object[] { "value" }));

		for (Endpoint endpoint : endpoints()) {
			assertThrows(Exception.class, () -> endpoint.report().generate(service, MINIMAL_REQUEST),
					endpoint.name());
		}
	}

	@Test
	void everyReportPropagatesInvalidDateInput() {
		ReportServiceImpl service = serviceReturning(List.of());

		for (Endpoint endpoint : endpoints()) {
			assertThrows(Exception.class,
					() -> endpoint.report().generate(service, "{\"startDate\":\"not-a-date\",\"psmId\":1}"),
					endpoint.name());
		}
	}

	@Test
	void timestampParsingUsesIsoDateTime() {
		ReportServiceImpl service = serviceReturning(List.of());

		Timestamp timestamp = ReflectionTestUtils.invokeMethod(service, "getTimestampFromString",
				"2024-01-01T00:00:00+05:30");

		assertEquals(Timestamp.valueOf("2024-01-01 00:00:00"), timestamp);
	}
}
