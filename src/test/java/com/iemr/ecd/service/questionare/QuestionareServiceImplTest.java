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
package com.iemr.ecd.service.questionare;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
import com.iemr.ecd.dao.MapQuestion;
import com.iemr.ecd.dao.Questionnaire;
import com.iemr.ecd.dao.QuestionnaireSections;
import com.iemr.ecd.dao.QuestionnaireValues;
import com.iemr.ecd.dao.SectionQuestionnaireMapping;
import com.iemr.ecd.dao.V_GetSectionQuestionMapping;
import com.iemr.ecd.dao.V_GetSectionQuestionMappingAssociates;
import com.iemr.ecd.dto.ECDMapQuestions;
import com.iemr.ecd.dto.ParentAnswer;
import com.iemr.ecd.dto.RequestSectionQuestionnaireMappingDTO;
import com.iemr.ecd.dto.ResponseSectionQuestionnaireMappingDTO;
import com.iemr.ecd.repo.call_conf_allocation.CallConfigurationRepo;
import com.iemr.ecd.repo.call_conf_allocation.EcdQuestionnaireRepo;
import com.iemr.ecd.repo.call_conf_allocation.QuestionnaireSectionRepo;
import com.iemr.ecd.repo.call_conf_allocation.QuestionnaireValuesRepo;
import com.iemr.ecd.repository.ecd.MapQuestionRepo;
import com.iemr.ecd.repository.ecd.SectionQuestionnaireMappingRepo;
import com.iemr.ecd.repository.ecd.V_GetSectionQuestionMappingAssociatesRepo;
import com.iemr.ecd.repository.ecd.V_GetSectionQuestionMappingRepo;
import com.iemr.ecd.utils.advice.exception_handler.ECDException;

import io.micrometer.observation.ObservationRegistry;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class QuestionareServiceImplTest {

	@Mock
	private EcdQuestionnaireRepo ecdQuestionnaireRepo;
	@Mock
	private QuestionnaireValuesRepo questionnaireValuesRepo;
	@Mock
	private QuestionnaireSectionRepo questionnaireSectionRepo;
	@Mock
	private SectionQuestionnaireMappingRepo sectionQuestionnaireMappingRepo;
	@Mock
	private V_GetSectionQuestionMappingRepo vGetSectionQuestionMappingRepo;
	@Mock
	private V_GetSectionQuestionMappingAssociatesRepo vGetSectionQuestionMappingAssociatesRepo;
	@Mock
	private MapQuestionRepo mapQuestionRepo;
	@Mock
	private CallConfigurationRepo callConfigurationRepo;

	private QuestionareServiceImpl service;

	@BeforeEach
	void setUp() {
		service = new QuestionareServiceImpl();
		ReflectionTestUtils.setField(service, "registry", ObservationRegistry.create());
		ReflectionTestUtils.setField(service, "ecdQuestionnaireRepo", ecdQuestionnaireRepo);
		ReflectionTestUtils.setField(service, "questionnaireValuesRepo", questionnaireValuesRepo);
		ReflectionTestUtils.setField(service, "questionnaireSectionRepo", questionnaireSectionRepo);
		ReflectionTestUtils.setField(service, "sectionQuestionnaireMappingRepo", sectionQuestionnaireMappingRepo);
		ReflectionTestUtils.setField(service, "v_GetSectionQuestionMappingRepo", vGetSectionQuestionMappingRepo);
		ReflectionTestUtils.setField(service, "v_GetSectionQuestionMappingAssociatesRepo",
				vGetSectionQuestionMappingAssociatesRepo);
		ReflectionTestUtils.setField(service, "mapQuestionRepo", mapQuestionRepo);
		ReflectionTestUtils.setField(service, "callConfigurationRepo", callConfigurationRepo);
	}

	private Questionnaire questionnaire(Integer id, String answerType) {
		Questionnaire questionnaire = new Questionnaire();
		questionnaire.setQuestionnaireId(id);
		questionnaire.setAnswerType(answerType);
		questionnaire.setPsmId(1);
		return questionnaire;
	}

	private QuestionnaireValues option(String value) {
		QuestionnaireValues option = new QuestionnaireValues();
		option.setOptions(value);
		return option;
	}

	@Test
	void createQuestionaresSavesOptionsAgainstSavedQuestionId() {
		Questionnaire questionnaire = questionnaire(null, "Radio");
		QuestionnaireValues option = option("Yes");
		questionnaire.setQuestionnaireValues(List.of(option));
		when(ecdQuestionnaireRepo.save(questionnaire)).thenReturn(questionnaire(77, "Radio"));

		String response = service.createQuestionares(List.of(questionnaire));

		assertEquals(77, option.getQuestionId());
		verify(questionnaireValuesRepo).saveAll(questionnaire.getQuestionnaireValues());
		assertTrue(response.contains("created successfully"));
	}

	@Test
	void createQuestionaresSkipsOptionSaveWhenNoOptions() {
		Questionnaire questionnaire = questionnaire(null, "Text");
		when(ecdQuestionnaireRepo.save(questionnaire)).thenReturn(questionnaire(77, "Text"));

		service.createQuestionares(List.of(questionnaire));

		verify(questionnaireValuesRepo, never()).saveAll(any());
	}

	@Test
	void createQuestionaresWrapsFailure() {
		Questionnaire questionnaire = questionnaire(null, "Text");
		when(ecdQuestionnaireRepo.save(questionnaire)).thenThrow(new IllegalStateException("db"));

		assertThrows(ECDException.class, () -> service.createQuestionares(List.of(questionnaire)));
	}

	@Test
	void updateQuestionaresSavesQuestionAndOptions() {
		Questionnaire questionnaire = questionnaire(77, "Radio");
		questionnaire.setQuestionnaireValues(List.of(option("Yes")));

		String response = service.updateQuestionares(questionnaire);

		verify(ecdQuestionnaireRepo).save(questionnaire);
		verify(questionnaireValuesRepo).saveAll(questionnaire.getQuestionnaireValues());
		assertTrue(response.contains("modified successfully"));
	}

	@Test
	void updateQuestionaresSkipsOptionsWhenAbsent() {
		Questionnaire questionnaire = questionnaire(77, "Text");
		questionnaire.setQuestionnaireValues(List.of());

		service.updateQuestionares(questionnaire);

		verify(questionnaireValuesRepo, never()).saveAll(any());
	}

	@Test
	void updateQuestionaresRejectsNullRequest() {
		assertThrows(ECDException.class, () -> service.updateQuestionares(null));
	}

	@Test
	void createSectionsSavesAll() {
		List<QuestionnaireSections> sections = List.of(new QuestionnaireSections());

		String response = service.createSections(sections);

		verify(questionnaireSectionRepo).saveAll(sections);
		assertTrue(response.contains("created successfully"));
	}

	@Test
	void createSectionsRejectsEmptyRequest() {
		assertThrows(ECDException.class, () -> service.createSections(List.of()));
		assertThrows(ECDException.class, () -> service.createSections(null));
	}

	@Test
	void getSectionsByProviderReturnsSections() {
		List<QuestionnaireSections> sections = List.of(new QuestionnaireSections());
		when(questionnaireSectionRepo.findByPsmIdOrderByLastModDateDesc(1)).thenReturn(sections);

		assertSame(sections, service.getSectionsByProvider(1));
	}

	@Test
	void getSectionsByProviderRejectsInvalidPsmId() {
		assertThrows(ECDException.class, () -> service.getSectionsByProvider(0));
	}

	@Test
	void updateSectionsSavesSectionWithId() {
		QuestionnaireSections section = new QuestionnaireSections();
		section.setSectionId(9);

		String response = service.updateSections(section);

		verify(questionnaireSectionRepo).save(section);
		assertTrue(response.contains("updated successfully"));
	}

	@Test
	void updateSectionsRejectsSectionWithoutId() {
		assertThrows(ECDException.class, () -> service.updateSections(new QuestionnaireSections()));
		assertThrows(ECDException.class, () -> service.updateSections(null));
	}

	@SuppressWarnings("unchecked")
	@Test
	void mapQuestionnairesAndSectionBuildsMappingsWithFlattenedRoles() {
		Questionnaire questionnaire = questionnaire(77, "Radio");
		questionnaire.setRank(2);
		questionnaire.setRoles(new String[] { "ASSOCIATE", "ANM" });
		RequestSectionQuestionnaireMappingDTO request = new RequestSectionQuestionnaireMappingDTO();
		request.setSectionId(9);
		request.setPsmId(1);
		request.setCreatedBy("admin");
		request.setQuestionIds(List.of(questionnaire));

		String response = service.mapQuestionnairesAndSection(request);

		ArgumentCaptor<List<SectionQuestionnaireMapping>> captor = ArgumentCaptor.forClass(List.class);
		verify(sectionQuestionnaireMappingRepo).saveAll(captor.capture());
		SectionQuestionnaireMapping mapping = captor.getValue().get(0);
		assertEquals(9, mapping.getSectionId());
		assertEquals(77, mapping.getQuestionId());
		assertEquals(2, mapping.getRank());
		assertEquals("admin", mapping.getCreatedBy());
		assertEquals("ASSOCIATE,ANM", mapping.getRole());
		assertTrue(response.contains("mapping done successfully"));
	}

	@SuppressWarnings("unchecked")
	@Test
	void mapQuestionnairesAndSectionLeavesRoleNullWhenNoRolesGiven() {
		RequestSectionQuestionnaireMappingDTO request = new RequestSectionQuestionnaireMappingDTO();
		request.setSectionId(9);
		request.setQuestionIds(List.of(questionnaire(77, "Text")));

		service.mapQuestionnairesAndSection(request);

		ArgumentCaptor<List<SectionQuestionnaireMapping>> captor = ArgumentCaptor.forClass(List.class);
		verify(sectionQuestionnaireMappingRepo).saveAll(captor.capture());
		assertNull(captor.getValue().get(0).getRole());
	}

	@Test
	void mapQuestionnairesAndSectionRejectsInvalidRequests() {
		RequestSectionQuestionnaireMappingDTO noSection = new RequestSectionQuestionnaireMappingDTO();
		noSection.setQuestionIds(List.of(questionnaire(77, "Text")));
		RequestSectionQuestionnaireMappingDTO noQuestions = new RequestSectionQuestionnaireMappingDTO();
		noQuestions.setSectionId(9);
		noQuestions.setQuestionIds(List.of());

		assertThrows(ECDException.class, () -> service.mapQuestionnairesAndSection(noSection));
		assertThrows(ECDException.class, () -> service.mapQuestionnairesAndSection(noQuestions));
		assertThrows(ECDException.class, () -> service.mapQuestionnairesAndSection(null));
	}

	@Test
	void getQuestionaresByProviderAttachesOptionsForChoiceQuestions() {
		Questionnaire radio = questionnaire(77, "Radio");
		when(ecdQuestionnaireRepo.findByPsmIdOrderByLastModDateDesc(1)).thenReturn(List.of(radio));
		when(questionnaireValuesRepo.findByQuestionIdAndDeleted(77, false))
				.thenReturn(List.of(option("Yes"), option("No")));

		List<Questionnaire> questionnaires = service.getQuestionaresByProvider(1);

		assertArrayEquals(new String[] { "Yes", "No" }, questionnaires.get(0).getOptions());
		assertEquals(2, questionnaires.get(0).getQuestionnaireValues().size());
	}

	@Test
	void getQuestionaresByProviderIgnoresQuestionsWithoutChoices() {
		Questionnaire text = questionnaire(77, "Text");
		Questionnaire noType = questionnaire(78, null);
		Questionnaire dropdownWithoutOptions = questionnaire(79, "Dropdown");
		when(ecdQuestionnaireRepo.findByPsmIdOrderByLastModDateDesc(1))
				.thenReturn(List.of(text, noType, dropdownWithoutOptions));
		when(questionnaireValuesRepo.findByQuestionIdAndDeleted(79, false)).thenReturn(List.of());

		service.getQuestionaresByProvider(1);

		assertNull(text.getOptions());
		assertNull(dropdownWithoutOptions.getOptions());
	}

	@Test
	void getQuestionaresByProviderHandlesEmptyAndNullResults() {
		when(ecdQuestionnaireRepo.findByPsmIdOrderByLastModDateDesc(anyInt())).thenReturn(List.of(), null);

		assertTrue(service.getQuestionaresByProvider(1).isEmpty());
		assertNull(service.getQuestionaresByProvider(1));
	}

	@Test
	void getQuestionaresByProviderWrapsFailure() {
		when(ecdQuestionnaireRepo.findByPsmIdOrderByLastModDateDesc(anyInt()))
				.thenThrow(new IllegalStateException("db"));

		assertThrows(ECDException.class, () -> service.getQuestionaresByProvider(1));
	}

	@Test
	void getQuestionaresAttachesOptionsForChoiceQuestions() {
		Questionnaire multiple = questionnaire(77, "Multiple");
		when(ecdQuestionnaireRepo.findByPsmIdAndDeletedOrderByLastModDateDesc(1, false))
				.thenReturn(List.of(multiple));
		when(questionnaireValuesRepo.findByQuestionIdAndDeleted(77, false)).thenReturn(List.of(option("Yes")));

		assertArrayEquals(new String[] { "Yes" }, service.getQuestionares(1).get(0).getOptions());
	}

	@Test
	void getQuestionaresIgnoresQuestionsWithoutChoices() {
		Questionnaire text = questionnaire(77, "Text");
		when(ecdQuestionnaireRepo.findByPsmIdAndDeletedOrderByLastModDateDesc(1, false)).thenReturn(List.of(text));

		service.getQuestionares(1);

		assertNull(text.getOptions());
	}

	@Test
	void getQuestionaresHandlesEmptyResultAndFailure() {
		when(ecdQuestionnaireRepo.findByPsmIdAndDeletedOrderByLastModDateDesc(anyInt(), anyBoolean()))
				.thenReturn(List.of()).thenThrow(new IllegalStateException("db"));

		assertTrue(service.getQuestionares(1).isEmpty());
		assertThrows(ECDException.class, () -> service.getQuestionares(1));
	}

	@Test
	void getUnMappedQuestionnairesByPsmIdDelegatesToRepository() {
		List<Questionnaire> questionnaires = List.of(questionnaire(77, "Text"));
		when(ecdQuestionnaireRepo.getUnMappedQuestionnairesByPSMId(1)).thenReturn(questionnaires);

		assertSame(questionnaires, service.getUnMappedQuestionnairesByPSMId(1));
	}

	@Test
	void getUnMappedQuestionnairesByPsmIdWrapsFailure() {
		when(ecdQuestionnaireRepo.getUnMappedQuestionnairesByPSMId(anyInt()))
				.thenThrow(new IllegalStateException("db"));

		assertThrows(ECDException.class, () -> service.getUnMappedQuestionnairesByPSMId(1));
	}

	@Test
	void getUnMappedQuestionnairesDelegatesToRepository() {
		List<Questionnaire> questionnaires = List.of(questionnaire(77, "Text"));
		when(ecdQuestionnaireRepo.getUnMappedQuestionnaires(1, 9)).thenReturn(questionnaires);

		assertSame(questionnaires, service.getUnMappedQuestionnaires(1, 9));
	}

	@Test
	void getUnMappedQuestionnairesWrapsFailure() {
		when(ecdQuestionnaireRepo.getUnMappedQuestionnaires(anyInt(), anyInt()))
				.thenThrow(new IllegalStateException("db"));

		assertThrows(ECDException.class, () -> service.getUnMappedQuestionnaires(1, 9));
	}

	@Test
	void mappingDataByProviderSplitsRoles() {
		QuestionnaireSections section = new QuestionnaireSections();
		section.setSectionId(9);
		V_GetSectionQuestionMapping withRole = new V_GetSectionQuestionMapping();
		withRole.setRole("ASSOCIATE,ANM");
		V_GetSectionQuestionMapping withoutRole = new V_GetSectionQuestionMapping();
		when(questionnaireSectionRepo.findByPsmIdOrderByLastModDateDesc(1)).thenReturn(List.of(section));
		when(vGetSectionQuestionMappingRepo.findBySectionid(9)).thenReturn(List.of(withRole, withoutRole));

		List<V_GetSectionQuestionMapping> response = service.getQuestionnairesAndSectionMappingDataByProvider(1);

		assertEquals(2, response.size());
		assertArrayEquals(new String[] { "ASSOCIATE", "ANM" }, withRole.getRoles());
		assertNull(withoutRole.getRoles());
	}

	@Test
	void mappingDataByProviderHandlesMissingSectionsAndMappings() {
		when(questionnaireSectionRepo.findByPsmIdOrderByLastModDateDesc(1)).thenReturn(List.of());
		assertTrue(service.getQuestionnairesAndSectionMappingDataByProvider(1).isEmpty());

		QuestionnaireSections section = new QuestionnaireSections();
		section.setSectionId(9);
		when(questionnaireSectionRepo.findByPsmIdOrderByLastModDateDesc(1)).thenReturn(List.of(section));
		when(vGetSectionQuestionMappingRepo.findBySectionid(9)).thenReturn(null);
		assertTrue(service.getQuestionnairesAndSectionMappingDataByProvider(1).isEmpty());
	}

	@Test
	void mappingDataByProviderWrapsFailure() {
		when(questionnaireSectionRepo.findByPsmIdOrderByLastModDateDesc(anyInt()))
				.thenThrow(new IllegalStateException("db"));

		assertThrows(ECDException.class, () -> service.getQuestionnairesAndSectionMappingDataByProvider(1));
	}

	private V_GetSectionQuestionMappingAssociates associate() {
		V_GetSectionQuestionMappingAssociates associate = new V_GetSectionQuestionMappingAssociates();
		associate.setQuestionid(77);
		associate.setParentQuestionDb("Parent A,Parent B");
		associate.setParentQuestionIdDb("11,12");
		associate.setParentAnswerDb("Yes,No||Maybe");
		return associate;
	}

	@Test
	void associateMappingAttachesOptionsAndParentAnswers() {
		CallConfiguration configuration = new CallConfiguration();
		configuration.setCallConfigId(55L);
		V_GetSectionQuestionMappingAssociates associate = associate();
		when(callConfigurationRepo.getCallConfigurationByProviderAndCallType(1, "Mother"))
				.thenReturn(configuration);
		when(vGetSectionQuestionMappingAssociatesRepo.findByPsmIdAndCallConfigIdAndRole(1, 55, "ASSOCIATE"))
				.thenReturn(List.of(associate));
		when(questionnaireValuesRepo.findByQuestionIdAndDeleted(77, false)).thenReturn(List.of(option("Yes")));

		List<V_GetSectionQuestionMappingAssociates> response = service.getQuesAndSecMapAssociateByProvider(1,
				"Mother", "ASSOCIATE");

		assertEquals(1, response.size());
		assertArrayEquals(new String[] { "Yes" }, associate.getOptionsArr());
		assertArrayEquals(new String[] { "Parent A", "Parent B" }, associate.getParentQuestion());
		assertArrayEquals(new int[] { 11, 12 }, associate.getParentQuestionId());
		List<ParentAnswer> parentAnswers = associate.getParentAnswer();
		assertEquals(2, parentAnswers.size());
		assertEquals(11, parentAnswers.get(0).getParentQuesId());
		assertArrayEquals(new String[] { "Yes", "No" }, parentAnswers.get(0).getParentAnswerList());
		assertArrayEquals(new String[] { "Maybe" }, parentAnswers.get(1).getParentAnswerList());
	}

	@Test
	void associateMappingHandlesAssociatesWithoutOptionsOrParents() {
		CallConfiguration configuration = new CallConfiguration();
		configuration.setCallConfigId(55L);
		V_GetSectionQuestionMappingAssociates associate = new V_GetSectionQuestionMappingAssociates();
		associate.setQuestionid(77);
		when(callConfigurationRepo.getCallConfigurationByProviderAndCallType(1, "Mother"))
				.thenReturn(configuration);
		when(vGetSectionQuestionMappingAssociatesRepo.findByPsmIdAndCallConfigIdAndRole(1, 55, "ASSOCIATE"))
				.thenReturn(List.of(associate));
		when(questionnaireValuesRepo.findByQuestionIdAndDeleted(77, false)).thenReturn(List.of());

		service.getQuesAndSecMapAssociateByProvider(1, "Mother", "ASSOCIATE");

		assertNull(associate.getOptionsArr());
		assertNull(associate.getParentQuestion());
		assertTrue(associate.getParentAnswer().isEmpty());
	}

	@Test
	void associateMappingReturnsEmptyWhenNoMappingsFound() {
		CallConfiguration configuration = new CallConfiguration();
		configuration.setCallConfigId(55L);
		when(callConfigurationRepo.getCallConfigurationByProviderAndCallType(1, "Mother"))
				.thenReturn(configuration);
		when(vGetSectionQuestionMappingAssociatesRepo.findByPsmIdAndCallConfigIdAndRole(1, 55, "ASSOCIATE"))
				.thenReturn(List.of());

		assertTrue(service.getQuesAndSecMapAssociateByProvider(1, "Mother", "ASSOCIATE").isEmpty());
	}

	@Test
	void associateMappingRejectsMissingCallConfiguration() {
		when(callConfigurationRepo.getCallConfigurationByProviderAndCallType(1, "Mother")).thenReturn(null);
		assertThrows(ECDException.class, () -> service.getQuesAndSecMapAssociateByProvider(1, "Mother", "ASSOCIATE"));

		when(callConfigurationRepo.getCallConfigurationByProviderAndCallType(1, "Mother"))
				.thenReturn(new CallConfiguration());
		assertThrows(ECDException.class, () -> service.getQuesAndSecMapAssociateByProvider(1, "Mother", "ASSOCIATE"));
	}

	@Test
	void mappingDataBySectionIdBuildsResponse() {
		V_GetSectionQuestionMapping viewData = new V_GetSectionQuestionMapping();
		viewData.setId(3);
		viewData.setQuestionid(77);
		viewData.setQuestion("Question?");
		viewData.setQuestionRank(1);
		viewData.setSectionQuestionRank(2);
		viewData.setSectionName("Greeting");
		viewData.setPsmId(1);
		when(vGetSectionQuestionMappingRepo.findBySectionid(9)).thenReturn(List.of(viewData));

		ResponseSectionQuestionnaireMappingDTO response = service
				.getQuestionnairesAndSectionMappingDataBySectionId(9);

		assertEquals(9, response.getSectionId());
		assertEquals("Greeting", response.getSectionName());
		assertEquals(1, response.getPsmId());
		Questionnaire mapped = response.getQuestionIds().get(0);
		assertEquals(77, mapped.getQuestionnaireId());
		assertEquals("Question?", mapped.getQuestionnaire());
		assertEquals(1, mapped.getQuestionRank());
		assertEquals(2, mapped.getRank());
		assertEquals(3, mapped.getSecQuesMapId());
	}

	@Test
	void mappingDataBySectionIdReturnsEmptyQuestionListWhenNoRows() {
		when(vGetSectionQuestionMappingRepo.findBySectionid(9)).thenReturn(List.of());

		assertTrue(service.getQuestionnairesAndSectionMappingDataBySectionId(9).getQuestionIds().isEmpty());
	}

	@Test
	void mappingDataBySectionIdRejectsInvalidSectionId() {
		assertThrows(ECDException.class, () -> service.getQuestionnairesAndSectionMappingDataBySectionId(0));
	}

	@Test
	void editQuestionnaireSectionMapFlattensRoles() {
		SectionQuestionnaireMapping mapping = new SectionQuestionnaireMapping();
		mapping.setId(3);
		mapping.setRoles(new String[] { "ASSOCIATE", "ANM" });

		String response = service.editQuestionnaireSectionMap(mapping);

		assertEquals("ASSOCIATE,ANM", mapping.getRole());
		verify(sectionQuestionnaireMappingRepo).save(mapping);
		assertTrue(response.contains("updated successfully"));
	}

	@Test
	void editQuestionnaireSectionMapKeepsRoleNullWhenNoRoles() {
		SectionQuestionnaireMapping mapping = new SectionQuestionnaireMapping();
		mapping.setId(3);
		mapping.setRoles(new String[0]);

		service.editQuestionnaireSectionMap(mapping);

		assertNull(mapping.getRole());
	}

	@Test
	void editQuestionnaireSectionMapRejectsMappingWithoutId() {
		assertThrows(ECDException.class,
				() -> service.editQuestionnaireSectionMap(new SectionQuestionnaireMapping()));
		assertThrows(ECDException.class, () -> service.editQuestionnaireSectionMap(null));
	}

	@Test
	void createQuestionnairesMapFlattensAnswers() {
		MapQuestion request = new MapQuestion();
		request.setParentQuestionId(11);
		request.setChildQuestionId(12);
		request.setPsmId(1);
		request.setCreatedBy("admin");
		request.setAnswer(new String[] { "Yes", "No" });

		String response = service.createQuestionnairesMap(request);

		ArgumentCaptor<MapQuestion> captor = ArgumentCaptor.forClass(MapQuestion.class);
		verify(mapQuestionRepo).save(captor.capture());
		MapQuestion saved = captor.getValue();
		assertEquals(11, saved.getParentQuestionId());
		assertEquals(12, saved.getChildQuestionId());
		assertEquals(1, saved.getPsmId());
		assertEquals("admin", saved.getCreatedBy());
		assertEquals("Yes,No", saved.getAnswerDb());
		assertTrue(response.contains("created successfully"));
	}

	@Test
	void createQuestionnairesMapKeepsAnswerDbNullWhenNoAnswers() {
		MapQuestion request = new MapQuestion();
		request.setAnswer(new String[0]);

		service.createQuestionnairesMap(request);

		ArgumentCaptor<MapQuestion> captor = ArgumentCaptor.forClass(MapQuestion.class);
		verify(mapQuestionRepo).save(captor.capture());
		assertNull(captor.getValue().getAnswerDb());
	}

	@Test
	void createQuestionnairesMapWrapsFailure() {
		assertThrows(ECDException.class, () -> service.createQuestionnairesMap(null));
	}

	@Test
	void editQuestionnairesMapFlattensAnswers() {
		MapQuestion request = new MapQuestion();
		request.setId(3);
		request.setAnswer(new String[] { "Yes", "No" });

		String response = service.editQuestionnairesMap(request);

		assertEquals("Yes,No", request.getAnswerDb());
		verify(mapQuestionRepo).save(request);
		assertTrue(response.contains("Questionnaire Map Updated Successfully"));
	}

	@Test
	void editQuestionnairesMapSkipsSaveWhenIdMissing() {
		service.editQuestionnairesMap(new MapQuestion());
		service.editQuestionnairesMap(null);

		verify(mapQuestionRepo, never()).save(any());
	}

	@Test
	void editQuestionnairesMapKeepsAnswerDbNullWhenNoAnswers() {
		MapQuestion request = new MapQuestion();
		request.setId(3);
		request.setAnswer(new String[0]);

		service.editQuestionnairesMap(request);

		assertNull(request.getAnswerDb());
	}

	@Test
	void editQuestionnairesMapWrapsFailure() {
		MapQuestion request = new MapQuestion();
		request.setId(3);
		when(mapQuestionRepo.save(request)).thenThrow(new IllegalStateException("db"));

		assertThrows(ECDException.class, () -> service.editQuestionnairesMap(request));
	}

	@Test
	void getMappedParentChildQuestionnaireMapsEveryColumn() {
		String[] row = { "1", "11", "Parent?", "Yes,No", "12", "Child?", "1", "false" };
		when(mapQuestionRepo.getMappedParentChildQuestionnaire(1)).thenReturn(List.<String[]>of(row));

		ECDMapQuestions mapped = service.getMappedParentChildQuestionnaire(1).get(0);

		assertEquals(1, mapped.getId());
		assertEquals(11, mapped.getParentQuestionId());
		assertEquals("Parent?", mapped.getParentQuestion());
		assertArrayEquals(new String[] { "Yes", "No" }, mapped.getAnswer());
		assertEquals(12, mapped.getChildQuestionId());
		assertEquals("Child?", mapped.getChildQuestion());
		assertEquals(1, mapped.getPsmId());
		assertEquals(false, mapped.getDeleted());
	}

	@Test
	void getMappedParentChildQuestionnaireSkipsNullColumns() {
		when(mapQuestionRepo.getMappedParentChildQuestionnaire(1)).thenReturn(List.<String[]>of(new String[8]));

		assertNull(service.getMappedParentChildQuestionnaire(1).get(0).getId());
	}

	@Test
	void getMappedParentChildQuestionnaireHandlesEmptyAndNullResults() {
		when(mapQuestionRepo.getMappedParentChildQuestionnaire(anyInt())).thenReturn(List.of(), null);

		assertTrue(service.getMappedParentChildQuestionnaire(1).isEmpty());
		assertTrue(service.getMappedParentChildQuestionnaire(1).isEmpty());
	}

	@Test
	void getMappedParentChildQuestionnaireWrapsRowFailure() {
		String[] row = { "not-a-number", "11", "Parent?", "Yes", "12", "Child?", "1", "false" };
		when(mapQuestionRepo.getMappedParentChildQuestionnaire(1)).thenReturn(List.<String[]>of(row));

		assertThrows(ECDException.class, () -> service.getMappedParentChildQuestionnaire(1));
	}

	@Test
	void getMappedParentChildQuestionnaireWrapsRepositoryFailure() {
		when(mapQuestionRepo.getMappedParentChildQuestionnaire(anyInt()))
				.thenThrow(new IllegalStateException("db"));

		assertThrows(ECDException.class, () -> service.getMappedParentChildQuestionnaire(1));
	}

	@Test
	void getQuestionaresAttachesOptionsForRadioAndDropdownQuestions() {
		Questionnaire radio = questionnaire(77, "Radio");
		Questionnaire dropdown = questionnaire(78, "Dropdown");
		when(ecdQuestionnaireRepo.findByPsmIdAndDeletedOrderByLastModDateDesc(1, false))
				.thenReturn(List.of(radio, dropdown));
		when(questionnaireValuesRepo.findByQuestionIdAndDeleted(77, false)).thenReturn(List.of(option("Yes")));
		when(questionnaireValuesRepo.findByQuestionIdAndDeleted(78, false)).thenReturn(null);

		service.getQuestionares(1);

		assertArrayEquals(new String[] { "Yes" }, radio.getOptions());
		assertNull(dropdown.getOptions());
	}

	@Test
	void getQuestionaresByProviderIgnoresNullQuestionValues() {
		Questionnaire multiple = questionnaire(77, "Multiple");
		when(ecdQuestionnaireRepo.findByPsmIdOrderByLastModDateDesc(1)).thenReturn(List.of(multiple));
		when(questionnaireValuesRepo.findByQuestionIdAndDeleted(77, false)).thenReturn(null);

		service.getQuestionaresByProvider(1);

		assertNull(multiple.getOptions());
	}

	@Test
	void createQuestionnairesMapAcceptsRequestWithoutAnswers() {
		MapQuestion request = new MapQuestion();
		request.setParentQuestionId(11);

		service.createQuestionnairesMap(request);

		ArgumentCaptor<MapQuestion> captor = ArgumentCaptor.forClass(MapQuestion.class);
		verify(mapQuestionRepo).save(captor.capture());
		assertNull(captor.getValue().getAnswerDb());
	}

	@Test
	void editQuestionnairesMapAcceptsRequestWithoutAnswers() {
		MapQuestion request = new MapQuestion();
		request.setId(3);

		service.editQuestionnairesMap(request);

		assertNull(request.getAnswerDb());
		verify(mapQuestionRepo).save(request);
	}
}
