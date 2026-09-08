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
package com.iemr.ecd.service.quality;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.iemr.ecd.dao.GradeConfiguration;
import com.iemr.ecd.dao.QualityAuditorRating;
import com.iemr.ecd.dao.SampleSelectionConfiguration;
import com.iemr.ecd.dao.V_get_Qualityaudit_SectionQuestionaireValues;
import com.iemr.ecd.dao.associate.Bencall;
import com.iemr.ecd.dto.BeneficiaryCasesheetDTO;
import com.iemr.ecd.dto.QualityAuditorWorklistDatewiseRequestDTO;
import com.iemr.ecd.dto.QualityAuditorWorklistDatewiseResponseDTO;
import com.iemr.ecd.dto.QualityAuditorWorklistRequestDTO;
import com.iemr.ecd.dto.QualityAuditorWorklistResponseDTO;
import com.iemr.ecd.dto.ResponseCallAuditSectionQuestionMapDTO;
import com.iemr.ecd.repo.call_conf_allocation.GradeConfigurationRepo;
import com.iemr.ecd.repo.call_conf_allocation.SampleSelectionConfigurationRepo;
import com.iemr.ecd.repository.quality.AgentQualityAuditorMapRepo;
import com.iemr.ecd.repository.quality.QualityAuditorCallResponseRepo;
import com.iemr.ecd.repository.quality.QualityAuditorRatingRepo;
import com.iemr.ecd.repository.quality.T_benCallRepo;
import com.iemr.ecd.repository.quality.V_QualityAuditorCallResponseRepo;
import com.iemr.ecd.repository.quality.V_get_Qualityaudit_SectionQuestionaireValuesRepo;
import com.iemr.ecd.utils.advice.exception_handler.ECDException;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class QualityAuditImplTest {

	@Mock
	private AgentQualityAuditorMapRepo agentQualityAuditorMapRepo;
	@Mock
	private SampleSelectionConfigurationRepo sampleSelectionConfigurationRepo;
	@Mock
	private QualityAuditorRatingRepo qualityAuditorRatingRepo;
	@Mock
	private QualityAuditorCallResponseRepo qualityAuditorCallResponseRepo;
	@Mock
	private T_benCallRepo tBenCallRepo;
	@Mock
	private GradeConfigurationRepo gradeConfigurationRepo;
	@Mock
	private V_get_Qualityaudit_SectionQuestionaireValuesRepo sectionQuestionaireValuesRepo;
	@Mock
	private V_QualityAuditorCallResponseRepo vQualityAuditorCallResponseRepo;

	@InjectMocks
	private QualityAuditImpl service;

	private QualityAuditorWorklistRequestDTO worklistRequest(Integer cycleId, int month) {
		QualityAuditorWorklistRequestDTO request = new QualityAuditorWorklistRequestDTO();
		request.setCycleId(cycleId);
		request.setPsmId(1);
		request.setLanguageId(2);
		request.setAgentId(7);
		request.setRoleId(3);
		request.setIsValid(true);
		request.setYear(2024);
		request.setMonth(month);
		return request;
	}

	private SampleSelectionConfiguration sampleConfiguration(int fromDay, int toDay) {
		SampleSelectionConfiguration configuration = new SampleSelectionConfiguration();
		configuration.setFromDay(fromDay);
		configuration.setToDay(toDay);
		return configuration;
	}

	private String[] worklistRow() {
		return new String[] { "10", "Asha Devi", "9999999999", "7", "Agent One", "outbound", "20", "true",
				"ANC1", "3", "ASSOCIATE", "call-1" };
	}

	@Test
	void worklistForFirstDayCycleUsesPreviousMonthWindow() {
		QualityAuditorWorklistRequestDTO request = worklistRequest(5, 3);
		when(sampleSelectionConfigurationRepo.getdate(5, 1))
				.thenReturn(List.<Object[]>of(new Object[] { 1, 10 }));
		when(sampleSelectionConfigurationRepo.getSampleSelectionConfiguration(31))
				.thenReturn(sampleConfiguration(21, 31));
		when(agentQualityAuditorMapRepo.getQualityAuditorWorklist(any(), any(), anyInt(), anyInt(), anyInt(),
				anyInt(), anyBoolean(), anyInt(), any(), any())).thenReturn(List.<String[]>of(worklistRow()));

		List<QualityAuditorWorklistResponseDTO> response = service.getQualityAuditorWorklist(request);

		assertEquals(1, response.size());
		assertEquals(10L, response.get(0).getBeneficiaryid());
		assertEquals("Asha Devi", response.get(0).getBeneficiaryname());
		assertEquals("9999999999", response.get(0).getPhoneNo());
		assertEquals(7, response.get(0).getAgentid());
		assertEquals("Agent One", response.get(0).getAgetname());
		assertEquals("outbound", response.get(0).getCalltype());
		assertEquals(20L, response.get(0).getBenCallID());
		assertTrue(response.get(0).getIsCallAudited());
		assertEquals("ANC1", response.get(0).getOutboundCallType());
		assertEquals(3, response.get(0).getRoleID());
		assertEquals("ASSOCIATE", response.get(0).getRoleName());
		assertEquals("call-1", response.get(0).getCallId());
	}

	@Test
	void worklistForFirstDayCycleFallsBackToSampleSizeQuery() {
		QualityAuditorWorklistRequestDTO request = worklistRequest(5, 1);
		when(sampleSelectionConfigurationRepo.getdate(5, 1))
				.thenReturn(List.<Object[]>of(new Object[] { 1, 10 }));
		when(sampleSelectionConfigurationRepo.getSampleSelectionConfiguration(31)).thenReturn(null);
		when(sampleSelectionConfigurationRepo.getSampleSize(31))
				.thenReturn(List.of(sampleConfiguration(11, 20), sampleConfiguration(21, 31)));
		when(agentQualityAuditorMapRepo.getQualityAuditorWorklist(any(), any(), anyInt(), anyInt(), anyInt(),
				anyInt(), anyBoolean(), anyInt(), any(), any())).thenReturn(List.of());

		assertTrue(service.getQualityAuditorWorklist(request).isEmpty());
	}

	@Test
	void worklistForMidMonthCycleUsesPrecedingCycleWindow() {
		QualityAuditorWorklistRequestDTO request = worklistRequest(5, 1);
		when(sampleSelectionConfigurationRepo.getdate(5, 1))
				.thenReturn(List.<Object[]>of(new Object[] { 11, 20 }));
		when(sampleSelectionConfigurationRepo.getSampleSelectionConfiguration(10))
				.thenReturn(sampleConfiguration(1, 10));
		when(agentQualityAuditorMapRepo.getQualityAuditorWorklist(any(), any(), anyInt(), anyInt(), anyInt(),
				anyInt(), anyBoolean(), anyInt(), any(), any())).thenReturn(List.<String[]>of(new String[12]));

		List<QualityAuditorWorklistResponseDTO> response = service.getQualityAuditorWorklist(request);

		assertEquals(1, response.size());
		assertNull(response.get(0).getBeneficiaryid());
	}

	@Test
	void worklistReturnsEmptyWhenCycleMissing() {
		assertTrue(service.getQualityAuditorWorklist(worklistRequest(null, 3)).isEmpty());
	}

	@Test
	void worklistWrapsFailure() {
		QualityAuditorWorklistRequestDTO request = worklistRequest(5, 3);
		when(sampleSelectionConfigurationRepo.getdate(5, 1)).thenThrow(new IllegalStateException("db"));

		assertThrows(ECDException.class, () -> service.getQualityAuditorWorklist(request));
	}

	@Test
	void worklistDataHandlesEmptyAndNullResults() {
		QualityAuditorWorklistRequestDTO request = worklistRequest(5, 3);
		when(agentQualityAuditorMapRepo.getQualityAuditorWorklist(any(), any(), anyInt(), anyInt(), anyInt(),
				anyInt(), anyBoolean(), anyInt(), any(), any())).thenReturn(List.of(), null);

		assertTrue(service.getWorkListData(request).isEmpty());
		assertTrue(service.getWorkListData(request).isEmpty());
	}

	private QualityAuditorWorklistDatewiseRequestDTO datewiseRequest() {
		QualityAuditorWorklistDatewiseRequestDTO request = new QualityAuditorWorklistDatewiseRequestDTO();
		request.setValidFrom(Timestamp.valueOf("2024-01-01 00:00:00"));
		request.setValidTill(Timestamp.valueOf("2024-01-31 23:59:59"));
		request.setPsmId(1);
		request.setRoleId(3);
		request.setAgentId(7);
		request.setLanguageId("2");
		request.setValid(true);
		return request;
	}

	@Test
	void datewiseWorklistMapsEveryColumn() {
		when(sampleSelectionConfigurationRepo.findByPsmIdOrderByLastModDateDesc(1))
				.thenReturn(List.of(sampleConfiguration(1, 10)));
		when(agentQualityAuditorMapRepo.getQualityAuditorWorklistDatewise(any(), any(), anyInt(), any(), anyInt(),
				anyInt(), anyBoolean())).thenReturn(List.<String[]>of(worklistRow()));

		List<QualityAuditorWorklistDatewiseResponseDTO> response = service
				.getQualityAuditorWorklistDatewise(datewiseRequest());

		assertEquals(10L, response.get(0).getBeneficiaryid());
		assertEquals("Asha Devi", response.get(0).getBeneficiaryname());
		assertEquals("9999999999", response.get(0).getPhoneNo());
		assertEquals(7, response.get(0).getAgentid());
		assertEquals("Agent One", response.get(0).getAgetname());
		assertEquals("outbound", response.get(0).getCalltype());
		assertEquals(20L, response.get(0).getBenCallID());
		assertTrue(response.get(0).getIsCallAudited());
		assertEquals("ANC1", response.get(0).getOutboundCallType());
		assertEquals(3, response.get(0).getRoleID());
		assertEquals("ASSOCIATE", response.get(0).getRoleName());
		assertEquals("call-1", response.get(0).getCallId());
	}

	@Test
	void datewiseWorklistSkipsNullColumns() {
		when(sampleSelectionConfigurationRepo.findByPsmIdOrderByLastModDateDesc(1))
				.thenReturn(List.of(sampleConfiguration(1, 10)));
		when(agentQualityAuditorMapRepo.getQualityAuditorWorklistDatewise(any(), any(), anyInt(), any(), anyInt(),
				anyInt(), anyBoolean())).thenReturn(List.<String[]>of(new String[12]));

		assertNull(service.getQualityAuditorWorklistDatewise(datewiseRequest()).get(0).getBeneficiaryid());
	}

	@Test
	void datewiseWorklistHandlesEmptyAndNullResults() {
		when(sampleSelectionConfigurationRepo.findByPsmIdOrderByLastModDateDesc(1))
				.thenReturn(List.of(sampleConfiguration(1, 10)));
		when(agentQualityAuditorMapRepo.getQualityAuditorWorklistDatewise(any(), any(), anyInt(), any(), anyInt(),
				anyInt(), anyBoolean())).thenReturn(List.of(), null);

		assertTrue(service.getQualityAuditorWorklistDatewise(datewiseRequest()).isEmpty());
		assertTrue(service.getQualityAuditorWorklistDatewise(datewiseRequest()).isEmpty());
	}

	@Test
	void datewiseWorklistRejectsMissingCycleConfiguration() {
		when(sampleSelectionConfigurationRepo.findByPsmIdOrderByLastModDateDesc(1)).thenReturn(List.of());

		assertThrows(ECDException.class, () -> service.getQualityAuditorWorklistDatewise(datewiseRequest()));
	}

	@Test
	void datewiseWorklistRejectsMissingDateRange() {
		assertThrows(ECDException.class, () -> service
				.getQualityAuditorWorklistDatewise(new QualityAuditorWorklistDatewiseRequestDTO()));
	}

	private V_get_Qualityaudit_SectionQuestionaireValues sectionValue(Integer questionId, Integer optionId,
			String role) {
		V_get_Qualityaudit_SectionQuestionaireValues value = new V_get_Qualityaudit_SectionQuestionaireValues();
		value.setSectionId(3);
		value.setSectionName("Greeting");
		value.setSectionRank(1);
		value.setQuestionId(questionId);
		value.setQuestion("Was the greeting polite?");
		value.setQuestionRank(2);
		value.setOptionId(optionId);
		value.setQuestionValues("Yes");
		value.setScore(10);
		value.setIsFatalQues(false);
		value.setRole(role);
		return value;
	}

	@Test
	void questionSectionForCallRatingsGroupsOptionsUnderQuestions() {
		when(sectionQuestionaireValuesRepo.findByPsmIdOrderByQuestionId(1))
				.thenReturn(List.of(sectionValue(55, 1, "ASSOCIATE,ANM"), sectionValue(55, 2, null),
						sectionValue(56, 3, "")));

		List<ResponseCallAuditSectionQuestionMapDTO> response = service.getQuestionSectionForCallRatings(1);

		assertEquals(2, response.size());
		assertEquals(3, response.get(0).getSectionId());
		assertEquals("Greeting", response.get(0).getSectionName());
		assertEquals(1, response.get(0).getSectionRank());
		assertEquals(55, response.get(0).getQuestionId());
		assertEquals("Was the greeting polite?", response.get(0).getQuestion());
		assertEquals(2, response.get(0).getQuestionRank());
		assertEquals(List.of("ASSOCIATE", "ANM"), response.get(0).getRoles());
		assertEquals(2, response.get(0).getOptions().size());
		assertEquals(10, response.get(0).getOptions().get(0).getScore());
		assertNull(response.get(1).getRoles());
	}

	@Test
	void questionSectionForCallRatingsSkipsNullOptionValues() {
		V_get_Qualityaudit_SectionQuestionaireValues value = sectionValue(55, 1, null);
		value.setQuestionValues(null);
		value.setScore(null);
		value.setSectionName(null);
		value.setSectionRank(null);
		value.setQuestion(null);
		value.setQuestionRank(null);
		when(sectionQuestionaireValuesRepo.findByPsmIdOrderByQuestionId(1)).thenReturn(List.of(value));

		ResponseCallAuditSectionQuestionMapDTO response = service.getQuestionSectionForCallRatings(1).get(0);

		assertNull(response.getSectionName());
		assertNull(response.getOptions().get(0).getOption());
		assertNull(response.getOptions().get(0).getScore());
	}

	@Test
	void questionSectionForCallRatingsHandlesEmptyAndNullResults() {
		when(sectionQuestionaireValuesRepo.findByPsmIdOrderByQuestionId(anyInt())).thenReturn(List.of(), null);

		assertTrue(service.getQuestionSectionForCallRatings(1).isEmpty());
		assertTrue(service.getQuestionSectionForCallRatings(1).isEmpty());
	}

	@Test
	void questionSectionForCallRatingsWrapsFailure() {
		when(sectionQuestionaireValuesRepo.findByPsmIdOrderByQuestionId(anyInt()))
				.thenThrow(new IllegalStateException("db"));

		assertThrows(ECDException.class, () -> service.getQuestionSectionForCallRatings(1));
	}

	@Test
	void qualityAuditGradesDelegateToRepository() {
		List<GradeConfiguration> grades = List.of(new GradeConfiguration());
		when(gradeConfigurationRepo.findByPsmIdAndDeleted(1, false)).thenReturn(grades);

		assertSame(grades, service.getQualityAuditGrades(1));
	}

	@Test
	void qualityAuditGradesWrapFailure() {
		when(gradeConfigurationRepo.findByPsmIdAndDeleted(anyInt(), anyBoolean()))
				.thenThrow(new IllegalStateException("db"));

		assertThrows(ECDException.class, () -> service.getQualityAuditGrades(1));
	}

	@Test
	void saveCallQualityRatingsPersistsRatingResponseAndAuditFlag() {
		String request = "[{\"id\":1,\"benCallId\":20,\"finalScore\":88,\"isZeroCall\":false}]";
		Bencall bencall = new Bencall();
		when(tBenCallRepo.findById(20L)).thenReturn(Optional.of(bencall));

		String response = service.saveCallQualityRatings(request);

		verify(qualityAuditorRatingRepo).save(any(QualityAuditorRating.class));
		verify(qualityAuditorCallResponseRepo).saveAll(any());
		assertTrue(bencall.getIsCallAudited());
		assertEquals(false, bencall.getIsZeroCall());
		verify(tBenCallRepo).save(bencall);
		assertTrue(response.contains("data saved successfully"));
	}

	@Test
	void saveCallQualityRatingsHandlesEmptyPayload() {
		String response = service.saveCallQualityRatings("[]");

		verify(qualityAuditorRatingRepo, never()).save(any());
		verify(qualityAuditorCallResponseRepo, never()).saveAll(any());
		assertTrue(response.contains("data saved successfully"));
	}

	@Test
	void saveCallQualityRatingsRejectsNullRequest() {
		assertThrows(ECDException.class, () -> service.saveCallQualityRatings(null));
	}

	@Test
	void saveCallQualityRatingsWrapsFailure() {
		String request = "[{\"id\":1,\"benCallId\":20}]";
		when(qualityAuditorRatingRepo.save(any(QualityAuditorRating.class)))
				.thenThrow(new IllegalStateException("db"));

		assertThrows(ECDException.class, () -> service.saveCallQualityRatings(request));
	}

	@Test
	void getCallQualityRatingsCombinesRatingAndResponses() {
		when(qualityAuditorRatingRepo.findByBenCallId(20L)).thenReturn(new QualityAuditorRating());
		when(vQualityAuditorCallResponseRepo.findByBenCallId(20L)).thenReturn(List.of());

		String response = service.getCallQualityRatings(20L);

		assertTrue(response.contains("qualityRating"));
		assertTrue(response.contains("qualityQuestionResponse"));
	}

	@Test
	void getCallQualityRatingsWrapsFailure() {
		when(qualityAuditorRatingRepo.findByBenCallId(anyLong())).thenThrow(new IllegalStateException("db"));

		assertThrows(ECDException.class, () -> service.getCallQualityRatings(20L));
	}

	@Test
	void callReauditSavesResponsesAndRating() {
		JsonObject request = JsonParser
				.parseString("{\"qualityQuestionResponse\":[{\"id\":1,\"benCallId\":20}],"
						+ "\"qualityRating\":{\"id\":5,\"benCallId\":20}}")
				.getAsJsonObject();

		String response = service.callReaudit(request);

		verify(qualityAuditorCallResponseRepo).saveAll(any());
		verify(qualityAuditorRatingRepo).save(any(QualityAuditorRating.class));
		assertTrue(response.contains("call reaudited successfully"));
	}

	@Test
	void callReauditSkipsEmptyPayloads() {
		JsonObject request = JsonParser
				.parseString("{\"qualityQuestionResponse\":[],\"qualityRating\":{\"benCallId\":20}}")
				.getAsJsonObject();

		service.callReaudit(request);

		verify(qualityAuditorCallResponseRepo, never()).saveAll(any());
		verify(qualityAuditorRatingRepo, never()).save(any(QualityAuditorRating.class));
	}

	@Test
	void callReauditIgnoresMissingAndNullSections() {
		JsonObject request = JsonParser
				.parseString("{\"qualityQuestionResponse\":null,\"qualityRating\":null}").getAsJsonObject();

		service.callReaudit(request);
		service.callReaudit(new JsonObject());

		verify(qualityAuditorCallResponseRepo, never()).saveAll(any());
	}

	@Test
	void callReauditWrapsFailure() {
		JsonObject request = JsonParser
				.parseString("{\"qualityRating\":{\"id\":5,\"benCallId\":20}}").getAsJsonObject();
		when(qualityAuditorRatingRepo.save(any(QualityAuditorRating.class)))
				.thenThrow(new IllegalStateException("db"));

		assertThrows(ECDException.class, () -> service.callReaudit(request));
	}

	private String[] casesheetRow() {
		return new String[] { "100", "20", "3", "4", "Asha Devi", "Mother Name", "Spouse", "2", "Female",
				"9999999999", "Self", "8888888888", "2000-01-01", "2024-01-01", "2024-10-01", "28.5", "Address",
				"Asha", "7777777777", "ANM", "6666666666", "PHC", "Block", "ANC1", "true", "opted out", "true",
				"true", "No reply", "false", "Fever", "remarks", "2024-05-01", "call remarks", "advice", "false",
				"1", "2024-04-01" };
	}

	@Test
	void beneficiaryCasesheetMapsBeneficiaryAndQuestionnaireDetails() {
		when(agentQualityAuditorMapRepo.getBeneficiaryCasesheet(20L))
				.thenReturn(List.<String[]>of(casesheetRow()));
		when(agentQualityAuditorMapRepo.getBeneficiaryCallResponse(20L)).thenReturn(
				List.<String[]>of(new String[] { "3", "Greeting", "55", "Question?", "Yes" }));
		BeneficiaryCasesheetDTO request = new BeneficiaryCasesheetDTO();
		request.setBenCallId(20L);

		String response = service.getBeneficiaryCasesheet(request);

		assertTrue(response.contains("beneficiaryDetails"));
		assertTrue(response.contains("questionnaireResponse"));
		assertTrue(response.contains("Asha Devi"));
		assertTrue(response.contains("Greeting"));
		assertTrue(response.contains("\"age\":28"));
	}

	@Test
	void beneficiaryCasesheetSkipsNullColumns() {
		when(agentQualityAuditorMapRepo.getBeneficiaryCasesheet(20L))
				.thenReturn(List.<String[]>of(new String[38]));
		when(agentQualityAuditorMapRepo.getBeneficiaryCallResponse(20L))
				.thenReturn(List.<String[]>of(new String[5]));
		BeneficiaryCasesheetDTO request = new BeneficiaryCasesheetDTO();
		request.setBenCallId(20L);

		String response = service.getBeneficiaryCasesheet(request);

		assertTrue(response.contains("beneficiaryDetails"));
	}

	@Test
	void beneficiaryCasesheetHandlesMissingRows() {
		when(agentQualityAuditorMapRepo.getBeneficiaryCasesheet(20L)).thenReturn(List.of());
		when(agentQualityAuditorMapRepo.getBeneficiaryCallResponse(20L)).thenReturn(null);
		BeneficiaryCasesheetDTO request = new BeneficiaryCasesheetDTO();
		request.setBenCallId(20L);

		String response = service.getBeneficiaryCasesheet(request);

		assertTrue(response.contains("questionnaireResponse"));
	}

	@Test
	void beneficiaryCasesheetRejectsMissingCallId() {
		assertThrows(ECDException.class, () -> service.getBeneficiaryCasesheet(new BeneficiaryCasesheetDTO()));
	}

	@Test
	void beneficiaryCasesheetWrapsFailure() {
		when(agentQualityAuditorMapRepo.getBeneficiaryCasesheet(anyLong()))
				.thenThrow(new IllegalStateException("db"));
		BeneficiaryCasesheetDTO request = new BeneficiaryCasesheetDTO();
		request.setBenCallId(20L);

		assertThrows(ECDException.class, () -> service.getBeneficiaryCasesheet(request));
	}
}
