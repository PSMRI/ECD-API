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
package com.iemr.ecd.controller.masters;

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

import com.iemr.ecd.dao.QuestionnaireSections;
import com.iemr.ecd.dao.masters.AgentsViewMaster;
import com.iemr.ecd.dao.masters.AnswerType;
import com.iemr.ecd.dao.masters.CallNotAnsweredReason;
import com.iemr.ecd.dao.masters.CongenitalAnomalies;
import com.iemr.ecd.dao.masters.Cycles;
import com.iemr.ecd.dao.masters.Frequency;
import com.iemr.ecd.dao.masters.Gender;
import com.iemr.ecd.dao.masters.GradeMaster;
import com.iemr.ecd.dao.masters.HRNIReasons;
import com.iemr.ecd.dao.masters.HRPReasons;
import com.iemr.ecd.dao.masters.Language;
import com.iemr.ecd.dao.masters.NoFurtherCallsReason;
import com.iemr.ecd.dao.masters.Offices;
import com.iemr.ecd.dao.masters.QuestionnaireType;
import com.iemr.ecd.dao.masters.Role;
import com.iemr.ecd.dao.masters.SMSParameters;
import com.iemr.ecd.dao.masters.SMSParametersMapping;
import com.iemr.ecd.dao.masters.SMSType;
import com.iemr.ecd.dao.masters.TypeOfComplaints;
import com.iemr.ecd.dao.masters.V_GetUserlangmapping;
import com.iemr.ecd.service.masters.MasterServiceImpl;

@ExtendWith(MockitoExtension.class)
class MastersControllerTest {

	@Mock
	private MasterServiceImpl masterServiceImpl;

	@InjectMocks
	private MastersController controller;

	@Test
	void getAgentsByRoleIdDelegatesToService() {
		List<AgentsViewMaster> agents = List.of(new AgentsViewMaster());
		when(masterServiceImpl.getAgentByRoleId(2)).thenReturn(agents);

		assertEquals(HttpStatus.OK, controller.getAgentsByRoleId("auth", 2).getStatusCode());
		assertSame(agents, controller.getAgentsByRoleId("auth", 2).getBody());
	}

	@Test
	void getAnswerTypeDelegatesToService() {
		List<AnswerType> types = List.of(new AnswerType());
		when(masterServiceImpl.getAnswerType()).thenReturn(types);

		assertSame(types, controller.getAnswerType().getBody());
	}

	@Test
	void getCallNotAnsweredReasonsDelegatesToService() {
		List<CallNotAnsweredReason> reasons = List.of(new CallNotAnsweredReason());
		when(masterServiceImpl.getCallNotAnsweredReasons()).thenReturn(reasons);

		assertSame(reasons, controller.getCallNotAnsweredReasons().getBody());
	}

	@Test
	void getCongenitalAnomaliesDelegatesToService() {
		List<CongenitalAnomalies> anomalies = List.of(new CongenitalAnomalies());
		when(masterServiceImpl.getCongenitalAnomalies()).thenReturn(anomalies);

		assertSame(anomalies, controller.getCongenitalAnomalies().getBody());
	}

	@Test
	void getCyclesDelegatesToService() {
		List<Cycles> cycles = List.of(new Cycles());
		when(masterServiceImpl.getAllCycles()).thenReturn(cycles);

		assertSame(cycles, controller.getCycles().getBody());
	}

	@Test
	void getHrniReasonsDelegatesToService() {
		List<HRNIReasons> reasons = List.of(new HRNIReasons());
		when(masterServiceImpl.getAllHRNIReasons()).thenReturn(reasons);

		assertSame(reasons, controller.getHRNIReasons().getBody());
	}

	@Test
	void getHrpReasonsDelegatesToService() {
		List<HRPReasons> reasons = List.of(new HRPReasons());
		when(masterServiceImpl.getAllHRPReasons()).thenReturn(reasons);

		assertSame(reasons, controller.getHRPReasons().getBody());
	}

	@Test
	void getLanguageDelegatesToService() {
		List<Language> languages = List.of(new Language());
		when(masterServiceImpl.getLanguage()).thenReturn(languages);

		assertSame(languages, controller.getLanguage().getBody());
	}

	@Test
	void getNoFurtherCallsReasonDelegatesToService() {
		List<NoFurtherCallsReason> reasons = List.of(new NoFurtherCallsReason());
		when(masterServiceImpl.getNoFurtherCallsReason()).thenReturn(reasons);

		assertSame(reasons, controller.getNoFurtherCallsReason().getBody());
	}

	@Test
	void getOfficesDelegatesToService() {
		List<Offices> offices = List.of(new Offices());
		when(masterServiceImpl.findOffices()).thenReturn(offices);

		assertSame(offices, controller.getOffices().getBody());
	}

	@Test
	void getOfficesByPsmIdDelegatesToService() {
		List<Offices> offices = List.of(new Offices());
		when(masterServiceImpl.findOfficesByPsmId(1)).thenReturn(offices);

		assertSame(offices, controller.getOfficesByPSMID(1).getBody());
	}

	@Test
	void getOfficesByDistrictIdDelegatesToService() {
		List<Offices> offices = List.of(new Offices());
		when(masterServiceImpl.findOfficesByDistrictId(7)).thenReturn(offices);

		assertSame(offices, controller.getOfficesByDistrictId(7).getBody());
	}

	@Test
	void getOfficesByDistrictIdAndPsmIdDelegatesToService() {
		List<Offices> offices = List.of(new Offices());
		when(masterServiceImpl.findOfficesByDistrictIdAndPsmId(7, 1)).thenReturn(offices);

		assertSame(offices, controller.getOfficesByDistrictIdAndPSMID(7, 1).getBody());
	}

	@Test
	void getSmsParametersDelegatesToService() {
		List<SMSParametersMapping> parameters = List.of(new SMSParametersMapping());
		when(masterServiceImpl.getSMSParameters()).thenReturn(parameters);

		assertSame(parameters, controller.getSMSParameters().getBody());
	}

	@Test
	void getQuestionnaireTypeDelegatesToService() {
		List<QuestionnaireType> types = List.of(new QuestionnaireType());
		when(masterServiceImpl.getQuestionnaireType()).thenReturn(types);

		assertSame(types, controller.getQuestionnaireType().getBody());
	}

	@Test
	void getRolesByPsmIdDelegatesToService() {
		List<Role> roles = List.of(new Role());
		when(masterServiceImpl.getRolesByPsmId(1)).thenReturn(roles);

		assertSame(roles, controller.getRolesByPsmId(1).getBody());
	}

	@Test
	void getSmsTypesDelegatesToService() {
		List<SMSType> types = List.of(new SMSType());
		when(masterServiceImpl.getSMSTypes()).thenReturn(types);

		assertSame(types, controller.getSMSTypes().getBody());
	}

	@Test
	void getTypeOfComplaintsDelegatesToService() {
		List<TypeOfComplaints> complaints = List.of(new TypeOfComplaints());
		when(masterServiceImpl.getTypeOfComplaints()).thenReturn(complaints);

		assertSame(complaints, controller.getTypeOfComplaints().getBody());
	}

	@Test
	void getSmsValuesDelegatesToService() {
		List<SMSParameters> values = List.of(new SMSParameters());
		when(masterServiceImpl.getSMSValues()).thenReturn(values);

		assertSame(values, controller.getSMSValues().getBody());
	}

	@Test
	void getGradesDelegatesToService() {
		List<GradeMaster> grades = List.of(new GradeMaster());
		when(masterServiceImpl.getGrades()).thenReturn(grades);

		assertSame(grades, controller.getGrades().getBody());
	}

	@Test
	void getSectionsByPsmIdDelegatesToService() {
		List<QuestionnaireSections> sections = List.of(new QuestionnaireSections());
		when(masterServiceImpl.getSectionsByPsmId(1)).thenReturn(sections);

		assertSame(sections, controller.getSectionsByPsmId(1).getBody());
	}

	@Test
	void getFrequencyDelegatesToService() {
		List<Frequency> frequencies = List.of(new Frequency());
		when(masterServiceImpl.getFrequency()).thenReturn(frequencies);

		assertSame(frequencies, controller.getFrequency().getBody());
	}

	@Test
	void getLanguageByUserIdDelegatesToService() {
		List<V_GetUserlangmapping> mappings = List.of(new V_GetUserlangmapping());
		when(masterServiceImpl.getLanguageByUserId(9)).thenReturn(mappings);

		assertSame(mappings, controller.getLanguageByUserId(9).getBody());
	}

	@Test
	void getGenderDelegatesToService() {
		List<Gender> genders = List.of(new Gender());
		when(masterServiceImpl.getGenders()).thenReturn(genders);

		assertSame(genders, controller.getGender().getBody());
	}

	@Test
	void getAgentsByRoleIdAndLanguageDelegatesToService() {
		List<AgentsViewMaster> agents = List.of(new AgentsViewMaster());
		when(masterServiceImpl.getAgentByRoleIdAndLanguage(2, "Hindi")).thenReturn(agents);

		assertSame(agents, controller.getAgentsByRoleIdAndLanguage("auth", 2, "Hindi").getBody());
	}
}
