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
package com.iemr.ecd.controller.quality;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import com.google.gson.JsonObject;
import com.iemr.ecd.dao.AgentQualityAuditorMap;
import com.iemr.ecd.dao.GradeConfiguration;
import com.iemr.ecd.dao.QualityAuditQuestionConfig;
import com.iemr.ecd.dao.QualityAuditSectionConfiguration;
import com.iemr.ecd.dao.SampleSelectionConfiguration;
import com.iemr.ecd.dto.BeneficiaryCasesheetDTO;
import com.iemr.ecd.dto.QualityAuditorSectionQuestionaireResponseDTO;
import com.iemr.ecd.dto.QualityAuditorWorklistDatewiseRequestDTO;
import com.iemr.ecd.dto.QualityAuditorWorklistDatewiseResponseDTO;
import com.iemr.ecd.dto.QualityAuditorWorklistRequestDTO;
import com.iemr.ecd.dto.QualityAuditorWorklistResponseDTO;
import com.iemr.ecd.dto.ResponseCallAuditSectionQuestionMapDTO;
import com.iemr.ecd.dto.ResponseFetchQualityChartsDataDTO;
import com.iemr.ecd.service.quality.AgentQualityAuditorMappingImpl;
import com.iemr.ecd.service.quality.ChartsImpl;
import com.iemr.ecd.service.quality.GradeConfigurationImpl;
import com.iemr.ecd.service.quality.QualityAuditImpl;
import com.iemr.ecd.service.quality.QualityAuditQuestionConfigurationImpl;
import com.iemr.ecd.service.quality.QualityAuditSectionConfigurationImpl;
import com.iemr.ecd.service.quality.SampleSelectionConfigurationImpl;

@ExtendWith(MockitoExtension.class)
class QualityControllersTest {

	@Mock
	private AgentQualityAuditorMappingImpl agentQualityAuditorMappingImpl;
	@Mock
	private ChartsImpl chartsImpl;
	@Mock
	private GradeConfigurationImpl gradeConfigurationImpl;
	@Mock
	private QualityAuditImpl qualityAuditImpl;
	@Mock
	private QualityAuditQuestionConfigurationImpl qualityAuditQuestionConfigurationImpl;
	@Mock
	private QualityAuditSectionConfigurationImpl qualityAuditSectionConfigurationImpl;
	@Mock
	private SampleSelectionConfigurationImpl sampleSelectionConfigurationImpl;

	@InjectMocks
	private AgentQualityAuditorMappingController agentQualityAuditorMappingController;
	@InjectMocks
	private ChartsController chartsController;
	@InjectMocks
	private GradeConfigurationController gradeConfigurationController;
	@InjectMocks
	private QualityAuditController qualityAuditController;
	@InjectMocks
	private QualityAuditQuestionConfigurationController qualityAuditQuestionConfigurationController;
	@InjectMocks
	private QualityAuditSectionConfigurationController qualityAuditSectionConfigurationController;
	@InjectMocks
	private SampleSelectionConfigurationController sampleSelectionConfigurationController;

	@Test
	void createAgentQualityAuditorMappingDelegatesToService() {
		AgentQualityAuditorMap map = new AgentQualityAuditorMap();
		when(agentQualityAuditorMappingImpl.createAgentQualityAuditorMapping(map)).thenReturn("created");

		assertEquals(HttpStatus.OK,
				agentQualityAuditorMappingController.createAgentQualityAuditorMapping(map).getStatusCode());
	}

	@Test
	void getAgentQualityAuditorMappingDelegatesToService() {
		List<AgentQualityAuditorMap> maps = List.of(new AgentQualityAuditorMap());
		when(agentQualityAuditorMappingImpl.getAgentQualityAuditorMappingByPSMId(1)).thenReturn(maps);

		assertSame(maps,
				agentQualityAuditorMappingController.getAgentQualityAuditorMappingByPSMId(1).getBody());
	}

	@Test
	void updateAgentQualityAuditorMappingDelegatesToService() {
		AgentQualityAuditorMap map = new AgentQualityAuditorMap();
		when(agentQualityAuditorMappingImpl.updateAgentQualityAuditorMapping(map)).thenReturn("updated");

		assertEquals("updated",
				agentQualityAuditorMappingController.updateAgentQualityAuditorMapping(map).getBody());
	}

	@Test
	void centreOverallQualityRatingsDelegatesToService() {
		List<ResponseFetchQualityChartsDataDTO> data = List.of(new ResponseFetchQualityChartsDataDTO());
		when(chartsImpl.getTrendAnalysisOfCentreOverallQualityRatings(1, "Monthly", "Jan")).thenReturn(data);

		assertSame(data,
				chartsController.getTrendAnalysisOfCentreOverallQualityRatings(1, "Monthly", "Jan").getBody());
	}

	@Test
	void actorWiseQualityRatingsDelegatesToService() {
		List<ResponseFetchQualityChartsDataDTO> data = List.of(new ResponseFetchQualityChartsDataDTO());
		when(chartsImpl.getActorWiseQualityRatings(1, "ASSOCIATE", "Jan")).thenReturn(data);

		assertSame(data, chartsController.getActorWiseQualityRatings(1, "ASSOCIATE", "Jan").getBody());
	}

	@Test
	void tenureWiseQualityRatingsDelegatesToService() {
		List<ResponseFetchQualityChartsDataDTO> data = List.of(new ResponseFetchQualityChartsDataDTO());
		when(chartsImpl.getTenureWiseQualityRatings(1, "ASSOCIATE")).thenReturn(data);

		assertSame(data, chartsController.getTenureWiseQualityRatings(1, "ASSOCIATE").getBody());
	}

	@Test
	void gradeWiseAgentCountDelegatesToService() {
		List<ResponseFetchQualityChartsDataDTO> data = List.of(new ResponseFetchQualityChartsDataDTO());
		when(chartsImpl.gradeWiseAgentCount(1, "Monthly", "Jan")).thenReturn(data);

		assertSame(data, chartsController
				.getActorWiseQualityRatingByRoleIdAndPSMIdAndFrequency(1, "Monthly", "Jan").getBody());
	}

	@Test
	void createGradeConfigurationDelegatesToService() {
		List<GradeConfiguration> configurations = List.of(new GradeConfiguration());
		when(gradeConfigurationImpl.createGradeConfiguration(configurations)).thenReturn("created");

		assertEquals("created", gradeConfigurationController.createGradeConfiguration(configurations).getBody());
	}

	@Test
	void getGradeConfigurationDelegatesToService() {
		List<GradeConfiguration> configurations = List.of(new GradeConfiguration());
		when(gradeConfigurationImpl.getGradeConfigurationByPSMId(1)).thenReturn(configurations);

		assertSame(configurations, gradeConfigurationController.getGradeConfigurationByPSMId(1).getBody());
	}

	@Test
	void updateGradeConfigurationDelegatesToService() {
		GradeConfiguration configuration = new GradeConfiguration();
		when(gradeConfigurationImpl.updateGradeConfiguration(configuration)).thenReturn("updated");

		assertEquals("updated", gradeConfigurationController.updateGradeConfiguration(configuration).getBody());
	}

	@Test
	void getQualityAuditorWorklistDelegatesToService() {
		QualityAuditorWorklistRequestDTO request = new QualityAuditorWorklistRequestDTO();
		List<QualityAuditorWorklistResponseDTO> worklist = List.of(new QualityAuditorWorklistResponseDTO());
		when(qualityAuditImpl.getQualityAuditorWorklist(request)).thenReturn(worklist);

		assertSame(worklist, qualityAuditController.getQualityAuditorWorklist(request).getBody());
	}

	@Test
	void getQualityAuditorWorklistDatewiseDelegatesToService() {
		QualityAuditorWorklistDatewiseRequestDTO request = new QualityAuditorWorklistDatewiseRequestDTO();
		List<QualityAuditorWorklistDatewiseResponseDTO> worklist = List
				.of(new QualityAuditorWorklistDatewiseResponseDTO());
		when(qualityAuditImpl.getQualityAuditorWorklistDatewise(request)).thenReturn(worklist);

		assertSame(worklist, qualityAuditController.getQualityAuditorWorklistDatewise(request).getBody());
	}

	@Test
	void getQuestionSectionForCallRatingsDelegatesToService() {
		List<ResponseCallAuditSectionQuestionMapDTO> sections = List
				.of(new ResponseCallAuditSectionQuestionMapDTO());
		when(qualityAuditImpl.getQuestionSectionForCallRatings(1)).thenReturn(sections);

		assertSame(sections, qualityAuditController.getQuestionSectionForCallRatings(1).getBody());
	}

	@Test
	void getQualityAuditGradesDelegatesToService() {
		List<GradeConfiguration> grades = List.of(new GradeConfiguration());
		when(qualityAuditImpl.getQualityAuditGrades(1)).thenReturn(grades);

		assertSame(grades, qualityAuditController.getQualityAuditGrades(1).getBody());
	}

	@Test
	void saveCallQualityRatingsDelegatesToService() throws Exception {
		when(qualityAuditImpl.saveCallQualityRatings("{}")).thenReturn("saved");

		assertEquals("saved", qualityAuditController.saveCallQualityRatings("{}").getBody());
	}

	@Test
	void getCallQualityRatingsDelegatesToService() {
		when(qualityAuditImpl.getCallQualityRatings(11L)).thenReturn("ratings");

		assertEquals("ratings", qualityAuditController.getCallQualityRatings(11L).getBody());
	}

	@Test
	void callReauditParsesRequestAndDelegatesToService() throws Exception {
		when(qualityAuditImpl.callReaudit(any(JsonObject.class))).thenReturn("reaudited");

		assertEquals("reaudited", qualityAuditController.callReaudit("{\"benCallId\":11}").getBody());
	}

	@Test
	void getBeneficiaryCasesheetDelegatesToService() {
		BeneficiaryCasesheetDTO request = new BeneficiaryCasesheetDTO();
		when(qualityAuditImpl.getBeneficiaryCasesheet(request)).thenReturn("casesheet");

		assertEquals("casesheet", qualityAuditController.getBeneficiaryCasesheet(request).getBody());
	}

	@Test
	void createQuestionnaireConfigurationDelegatesToService() {
		List<QualityAuditQuestionConfig> configs = List.of(new QualityAuditQuestionConfig());
		when(qualityAuditQuestionConfigurationImpl.createQualityAuditQuestionnaireConfiguration(configs))
				.thenReturn("created");

		assertEquals("created", qualityAuditQuestionConfigurationController
				.createQualityAuditQuestionnaireConfiguration(configs).getBody());
	}

	@Test
	void getQuestionnaireConfigurationDelegatesToService() {
		List<QualityAuditorSectionQuestionaireResponseDTO> configs = List
				.of(new QualityAuditorSectionQuestionaireResponseDTO());
		when(qualityAuditQuestionConfigurationImpl.getQualityAuditQuestionnaireConfigurationByPSMId(1))
				.thenReturn(configs);

		assertSame(configs, qualityAuditQuestionConfigurationController
				.getQualityAuditQuestionnaireConfigurationByPSMId(1).getBody());
	}

	@Test
	void updateQuestionnaireConfigurationDelegatesToService() {
		QualityAuditQuestionConfig config = new QualityAuditQuestionConfig();
		when(qualityAuditQuestionConfigurationImpl.updateQualityAuditQuestionnaireConfiguration(config))
				.thenReturn("updated");

		assertEquals("updated", qualityAuditQuestionConfigurationController
				.updateQualityAuditQuestionnaireConfiguration(config).getBody());
	}

	@Test
	void createSectionConfigurationDelegatesToService() {
		List<QualityAuditSectionConfiguration> configs = List.of(new QualityAuditSectionConfiguration());
		when(qualityAuditSectionConfigurationImpl.createQualityAuditSectionConfiguration(configs))
				.thenReturn("created");

		assertEquals("created", qualityAuditSectionConfigurationController
				.createQualityAuditSectionConfiguration(configs).getBody());
	}

	@Test
	void getSectionConfigurationDelegatesToService() {
		List<QualityAuditSectionConfiguration> configs = List.of(new QualityAuditSectionConfiguration());
		when(qualityAuditSectionConfigurationImpl.getQualityAuditSectionConfigurationByPSMId(1)).thenReturn(configs);

		assertSame(configs, qualityAuditSectionConfigurationController
				.getQualityAuditSectionConfigurationByPSMId(1).getBody());
	}

	@Test
	void updateSectionConfigurationDelegatesToService() {
		QualityAuditSectionConfiguration config = new QualityAuditSectionConfiguration();
		when(qualityAuditSectionConfigurationImpl.updateQualityAuditSectionConfiguration(config))
				.thenReturn("updated");

		assertEquals("updated", qualityAuditSectionConfigurationController
				.updateQualityAuditSectionConfiguration(config).getBody());
	}

	@Test
	void createSampleSelectionConfigurationDelegatesToService() {
		List<SampleSelectionConfiguration> configs = List.of(new SampleSelectionConfiguration());
		when(sampleSelectionConfigurationImpl.createSampleSelectionConfiguration(configs)).thenReturn("created");

		assertEquals("created",
				sampleSelectionConfigurationController.createSampleSelectionConfiguration(configs).getBody());
	}

	@Test
	void getSampleSelectionConfigurationDelegatesToService() {
		List<SampleSelectionConfiguration> configs = List.of(new SampleSelectionConfiguration());
		when(sampleSelectionConfigurationImpl.getSampleSelectionConfigurationByPSMId(1)).thenReturn(configs);

		assertSame(configs,
				sampleSelectionConfigurationController.getSampleSelectionConfigurationByPSMId(1).getBody());
	}

	@Test
	void updateSampleSelectionConfigurationDelegatesToService() {
		SampleSelectionConfiguration config = new SampleSelectionConfiguration();
		when(sampleSelectionConfigurationImpl.updateSampleSelectionConfiguration(config)).thenReturn("updated");

		assertEquals("updated",
				sampleSelectionConfigurationController.updateSampleSelectionConfiguration(config).getBody());
	}
}
