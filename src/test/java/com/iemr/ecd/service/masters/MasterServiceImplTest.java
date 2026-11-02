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
package com.iemr.ecd.service.masters;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
import com.iemr.ecd.repo.call_conf_allocation.QuestionnaireSectionRepo;
import com.iemr.ecd.repository.masters.AgentsViewMasterRepo;
import com.iemr.ecd.repository.masters.AnswerTypeRepo;
import com.iemr.ecd.repository.masters.CallNotAnsweredRepo;
import com.iemr.ecd.repository.masters.CongenitalAnomaliesRepo;
import com.iemr.ecd.repository.masters.CycleRepo;
import com.iemr.ecd.repository.masters.FrequencyRepo;
import com.iemr.ecd.repository.masters.GenderRepo;
import com.iemr.ecd.repository.masters.GradeMasterRepo;
import com.iemr.ecd.repository.masters.HRNIReasonsRepo;
import com.iemr.ecd.repository.masters.HRPReasonsRepo;
import com.iemr.ecd.repository.masters.LanguageRepo;
import com.iemr.ecd.repository.masters.NoFurtherCallsReasonRepo;
import com.iemr.ecd.repository.masters.OfficesRepo;
import com.iemr.ecd.repository.masters.ParametersRepo;
import com.iemr.ecd.repository.masters.QuestionnaireTypeRepo;
import com.iemr.ecd.repository.masters.RoleRepo;
import com.iemr.ecd.repository.masters.SMSTypeRepo;
import com.iemr.ecd.repository.masters.TypeOfComplaintsRepo;
import com.iemr.ecd.repository.masters.V_GetUserlangmappingRepo;
import com.iemr.ecd.repository.masters.ValueRepo;

@ExtendWith(MockitoExtension.class)
class MasterServiceImplTest {

	@Mock
	private HRPReasonsRepo hrpReasonsRepo;
	@Mock
	private HRNIReasonsRepo hrniReasonsRepo;
	@Mock
	private CycleRepo cycleRepo;
	@Mock
	private OfficesRepo officesRepo;
	@Mock
	private AnswerTypeRepo answerTypeRepo;
	@Mock
	private CallNotAnsweredRepo callNotAnsweredRepo;
	@Mock
	private CongenitalAnomaliesRepo congenitalAnomaliesRepo;
	@Mock
	private GradeMasterRepo gradeMasterRepo;
	@Mock
	private LanguageRepo languageRepo;
	@Mock
	private NoFurtherCallsReasonRepo noFurtherCallsReasonRepo;
	@Mock
	private ParametersRepo parametersRepo;
	@Mock
	private QuestionnaireTypeRepo questionnaireTypeRepo;
	@Mock
	private RoleRepo roleRepo;
	@Mock
	private SMSTypeRepo smsTypeRepo;
	@Mock
	private TypeOfComplaintsRepo typeOfComplaintsRepo;
	@Mock
	private ValueRepo valueRepo;
	@Mock
	private AgentsViewMasterRepo agentsViewMasterRepo;
	@Mock
	private QuestionnaireSectionRepo questionnaireSectionRepo;
	@Mock
	private FrequencyRepo frequencyRepo;
	@Mock
	private V_GetUserlangmappingRepo vGetUserlangmappingRepo;
	@Mock
	private GenderRepo genderRepo;

	@InjectMocks
	private MasterServiceImpl service;

	@Test
	void getAllHrpReasonsReturnsNonDeleted() {
		List<HRPReasons> reasons = List.of(new HRPReasons());
		when(hrpReasonsRepo.findByDeleted(false)).thenReturn(reasons);

		assertSame(reasons, service.getAllHRPReasons());
	}

	@Test
	void getAllHrniReasonsReturnsNonDeleted() {
		List<HRNIReasons> reasons = List.of(new HRNIReasons());
		when(hrniReasonsRepo.findByDeleted(false)).thenReturn(reasons);

		assertSame(reasons, service.getAllHRNIReasons());
	}

	@Test
	void getAllCyclesReturnsNonDeleted() {
		List<Cycles> cycles = List.of(new Cycles());
		when(cycleRepo.findByDeleted(false)).thenReturn(cycles);

		assertSame(cycles, service.getAllCycles());
	}

	@Test
	void findOfficesReturnsNonDeleted() {
		List<Offices> offices = List.of(new Offices());
		when(officesRepo.findByDeleted(false)).thenReturn(offices);

		assertSame(offices, service.findOffices());
	}

	@Test
	void findOfficesByPsmIdDelegatesToRepository() {
		List<Offices> offices = List.of(new Offices());
		when(officesRepo.findByPsmId(1)).thenReturn(offices);

		assertSame(offices, service.findOfficesByPsmId(1));
	}

	@Test
	void findOfficesByDistrictIdDelegatesToRepository() {
		List<Offices> offices = List.of(new Offices());
		when(officesRepo.findByDistrictId(7)).thenReturn(offices);

		assertSame(offices, service.findOfficesByDistrictId(7));
	}

	@Test
	void findOfficesByDistrictIdAndPsmIdDelegatesToRepository() {
		List<Offices> offices = List.of(new Offices());
		when(officesRepo.findByDistrictIdAndPsmId(7, 1)).thenReturn(offices);

		assertSame(offices, service.findOfficesByDistrictIdAndPsmId(7, 1));
	}

	@Test
	void getAgentByRoleIdDelegatesToRepository() {
		List<AgentsViewMaster> agents = List.of(new AgentsViewMaster());
		when(agentsViewMasterRepo.findByRoleId(3)).thenReturn(agents);

		assertSame(agents, service.getAgentByRoleId(3));
	}

	@Test
	void getAnswerTypeReturnsNonDeleted() {
		List<AnswerType> types = List.of(new AnswerType());
		when(answerTypeRepo.findByDeleted(false)).thenReturn(types);

		assertSame(types, service.getAnswerType());
	}

	@Test
	void getCallNotAnsweredReasonsReturnsNonDeleted() {
		List<CallNotAnsweredReason> reasons = List.of(new CallNotAnsweredReason());
		when(callNotAnsweredRepo.findByDeleted(false)).thenReturn(reasons);

		assertSame(reasons, service.getCallNotAnsweredReasons());
	}

	@Test
	void getCongenitalAnomaliesReturnsNonDeleted() {
		List<CongenitalAnomalies> anomalies = List.of(new CongenitalAnomalies());
		when(congenitalAnomaliesRepo.findByDeleted(false)).thenReturn(anomalies);

		assertSame(anomalies, service.getCongenitalAnomalies());
	}

	@Test
	void getGradesReturnsNonDeleted() {
		List<GradeMaster> grades = List.of(new GradeMaster());
		when(gradeMasterRepo.findByDeleted(false)).thenReturn(grades);

		assertSame(grades, service.getGrades());
	}

	@Test
	void getLanguageReturnsNonDeleted() {
		List<Language> languages = List.of(new Language());
		when(languageRepo.findByDeleted(false)).thenReturn(languages);

		assertSame(languages, service.getLanguage());
	}

	@Test
	void getNoFurtherCallsReasonReturnsNonDeleted() {
		List<NoFurtherCallsReason> reasons = List.of(new NoFurtherCallsReason());
		when(noFurtherCallsReasonRepo.findByDeleted(false)).thenReturn(reasons);

		assertSame(reasons, service.getNoFurtherCallsReason());
	}

	@Test
	void getSmsParametersReturnsNonDeleted() {
		List<SMSParametersMapping> parameters = List.of(new SMSParametersMapping());
		when(parametersRepo.findByDeleted(false)).thenReturn(parameters);

		assertSame(parameters, service.getSMSParameters());
	}

	@Test
	void getQuestionnaireTypeReturnsNonDeleted() {
		List<QuestionnaireType> types = List.of(new QuestionnaireType());
		when(questionnaireTypeRepo.findByDeleted(false)).thenReturn(types);

		assertSame(types, service.getQuestionnaireType());
	}

	@Test
	void getRolesByPsmIdReturnsNonDeleted() {
		List<Role> roles = List.of(new Role());
		when(roleRepo.findByPsmIdAndDeleted(1, false)).thenReturn(roles);

		assertSame(roles, service.getRolesByPsmId(1));
	}

	@Test
	void getSmsTypesReturnsNonDeleted() {
		List<SMSType> types = List.of(new SMSType());
		when(smsTypeRepo.findByDeleted(false)).thenReturn(types);

		assertSame(types, service.getSMSTypes());
	}

	@Test
	void getTypeOfComplaintsReturnsNonDeleted() {
		List<TypeOfComplaints> complaints = List.of(new TypeOfComplaints());
		when(typeOfComplaintsRepo.findByDeleted(false)).thenReturn(complaints);

		assertSame(complaints, service.getTypeOfComplaints());
	}

	@Test
	void getSmsValuesReturnsNonDeleted() {
		List<SMSParameters> values = List.of(new SMSParameters());
		when(valueRepo.findByDeleted(false)).thenReturn(values);

		assertSame(values, service.getSMSValues());
	}

	@Test
	void getSectionsByPsmIdReturnsNonDeleted() {
		List<QuestionnaireSections> sections = List.of(new QuestionnaireSections());
		when(questionnaireSectionRepo.findByPsmIdAndDeleted(1, false)).thenReturn(sections);

		assertSame(sections, service.getSectionsByPsmId(1));
	}

	@Test
	void getFrequencyReturnsNonDeleted() {
		List<Frequency> frequencies = List.of(new Frequency());
		when(frequencyRepo.findByDeleted(false)).thenReturn(frequencies);

		assertSame(frequencies, service.getFrequency());
	}

	@Test
	void getLanguageByUserIdDelegatesToRepository() {
		List<V_GetUserlangmapping> mappings = List.of(new V_GetUserlangmapping());
		when(vGetUserlangmappingRepo.findByUserId(9)).thenReturn(mappings);

		assertSame(mappings, service.getLanguageByUserId(9));
	}

	@Test
	void getGendersReturnsNonDeleted() {
		List<Gender> genders = List.of(new Gender());
		when(genderRepo.findByDeleted(false)).thenReturn(genders);

		assertSame(genders, service.getGenders());
	}

	@Test
	void getAgentByRoleIdAndLanguageDelegatesToRepository() {
		List<AgentsViewMaster> agents = List.of(new AgentsViewMaster());
		when(agentsViewMasterRepo.findByRoleIdAndLanguage(3, "Hindi")).thenReturn(agents);

		assertSame(agents, service.getAgentByRoleIdAndLanguage(3, "Hindi"));
	}
}
