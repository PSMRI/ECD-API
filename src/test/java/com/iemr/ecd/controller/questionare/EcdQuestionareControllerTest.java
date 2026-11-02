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
package com.iemr.ecd.controller.questionare;

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

import com.iemr.ecd.dao.MapQuestion;
import com.iemr.ecd.dao.Questionnaire;
import com.iemr.ecd.dao.QuestionnaireSections;
import com.iemr.ecd.dao.SectionQuestionnaireMapping;
import com.iemr.ecd.dao.V_GetSectionQuestionMapping;
import com.iemr.ecd.dao.V_GetSectionQuestionMappingAssociates;
import com.iemr.ecd.dto.ECDMapQuestions;
import com.iemr.ecd.dto.RequestSectionQuestionnaireMappingDTO;
import com.iemr.ecd.dto.ResponseSectionQuestionnaireMappingDTO;
import com.iemr.ecd.service.questionare.QuestionareServiceImpl;

@ExtendWith(MockitoExtension.class)
class EcdQuestionareControllerTest {

	@Mock
	private QuestionareServiceImpl questionareServiceImpl;

	@InjectMocks
	private EcdQuestionareController controller;

	@Test
	void createQuestionnairesDelegatesToService() {
		List<Questionnaire> questionnaires = List.of(new Questionnaire());
		when(questionareServiceImpl.createQuestionares(questionnaires)).thenReturn("created");

		assertEquals(HttpStatus.OK, controller.createQuestionnaires(questionnaires).getStatusCode());
		assertEquals("created", controller.createQuestionnaires(questionnaires).getBody());
	}

	@Test
	void getQuestionnairesByPsmIdDelegatesToService() {
		List<Questionnaire> questionnaires = List.of(new Questionnaire());
		when(questionareServiceImpl.getQuestionaresByProvider(1)).thenReturn(questionnaires);

		assertSame(questionnaires, controller.getQuestionnairesByPSMId(1).getBody());
	}

	@Test
	void getQuestionnairesDelegatesToService() {
		List<Questionnaire> questionnaires = List.of(new Questionnaire());
		when(questionareServiceImpl.getQuestionares(1)).thenReturn(questionnaires);

		assertSame(questionnaires, controller.getQuestionnaires(1).getBody());
	}

	@Test
	void getUnMappedQuestionnairesByPsmIdDelegatesToService() {
		List<Questionnaire> questionnaires = List.of(new Questionnaire());
		when(questionareServiceImpl.getUnMappedQuestionnairesByPSMId(1)).thenReturn(questionnaires);

		assertSame(questionnaires, controller.getUnMappedQuestionnairesByPSMId(1).getBody());
	}

	@Test
	void updateQuestionnaireDelegatesToService() {
		Questionnaire questionnaire = new Questionnaire();
		when(questionareServiceImpl.updateQuestionares(questionnaire)).thenReturn("updated");

		assertEquals("updated", controller.updateQuestionnaire(questionnaire).getBody());
	}

	@Test
	void createSectionsDelegatesToService() {
		List<QuestionnaireSections> sections = List.of(new QuestionnaireSections());
		when(questionareServiceImpl.createSections(sections)).thenReturn("created");

		assertEquals("created", controller.createSections(sections).getBody());
	}

	@Test
	void getSectionsByProviderDelegatesToService() {
		List<QuestionnaireSections> sections = List.of(new QuestionnaireSections());
		when(questionareServiceImpl.getSectionsByProvider(1)).thenReturn(sections);

		assertSame(sections, controller.getSectionsByProvider(1).getBody());
	}

	@Test
	void updateSectionDelegatesToService() {
		QuestionnaireSections section = new QuestionnaireSections();
		when(questionareServiceImpl.updateSections(section)).thenReturn("updated");

		assertEquals("updated", controller.updateSection(section).getBody());
	}

	@Test
	void mapQuestionnairesAndSectionDelegatesToService() {
		RequestSectionQuestionnaireMappingDTO request = new RequestSectionQuestionnaireMappingDTO();
		when(questionareServiceImpl.mapQuestionnairesAndSection(request)).thenReturn("mapped");

		assertEquals("mapped", controller.mapQuestionnairesAndSection(request).getBody());
	}

	@Test
	void getQuestionnairesAndSectionMapDelegatesToService() {
		ResponseSectionQuestionnaireMappingDTO dto = new ResponseSectionQuestionnaireMappingDTO();
		when(questionareServiceImpl.getQuestionnairesAndSectionMappingDataBySectionId(3)).thenReturn(dto);

		assertSame(dto, controller.getQuestionnairesAndSectionMap(3).getBody());
	}

	@Test
	void getQuestionnairesAndSectionMapByProviderDelegatesToService() {
		List<V_GetSectionQuestionMapping> mappings = List.of(new V_GetSectionQuestionMapping());
		when(questionareServiceImpl.getQuestionnairesAndSectionMappingDataByProvider(1)).thenReturn(mappings);

		assertSame(mappings, controller.getQuestionnairesAndSectionMapByProvider(1).getBody());
	}

	@Test
	void getQuesAndSecMapAssociateByProviderDelegatesToService() {
		List<V_GetSectionQuestionMappingAssociates> mappings = List
				.of(new V_GetSectionQuestionMappingAssociates());
		when(questionareServiceImpl.getQuesAndSecMapAssociateByProvider(1, "Mother", "ASSOCIATE"))
				.thenReturn(mappings);

		assertSame(mappings, controller.getQuesAndSecMapAssociateByProvider(1, "Mother", "ASSOCIATE").getBody());
	}

	@Test
	void editQuestionnaireSectionMapDelegatesToService() {
		SectionQuestionnaireMapping mapping = new SectionQuestionnaireMapping();
		when(questionareServiceImpl.editQuestionnaireSectionMap(mapping)).thenReturn("edited");

		assertEquals("edited", controller.editQuestionnaireSectionMap(mapping).getBody());
	}

	@Test
	void getUnMappedQuestionnairesDelegatesToService() {
		List<Questionnaire> questionnaires = List.of(new Questionnaire());
		when(questionareServiceImpl.getUnMappedQuestionnaires(1, 2)).thenReturn(questionnaires);

		assertSame(questionnaires, controller.getUnMappedQuestionnaires(1, 2).getBody());
	}

	@Test
	void createQuestionnairesMapDelegatesToService() {
		MapQuestion mapQuestion = new MapQuestion();
		when(questionareServiceImpl.createQuestionnairesMap(mapQuestion)).thenReturn("created");

		assertEquals("created", controller.createQuestionnairesMap(mapQuestion).getBody());
	}

	@Test
	void editQuestionnairesMapDelegatesToService() {
		MapQuestion mapQuestion = new MapQuestion();
		when(questionareServiceImpl.editQuestionnairesMap(mapQuestion)).thenReturn("edited");

		assertEquals("edited", controller.editQuestionnairesMap(mapQuestion).getBody());
	}

	@Test
	void getMappedParentChildQuestionnaireDelegatesToService() {
		List<ECDMapQuestions> questions = List.of(new ECDMapQuestions());
		when(questionareServiceImpl.getMappedParentChildQuestionnaire(1)).thenReturn(questions);

		assertSame(questions, controller.getMappedParentChildQuestionnaire(1).getBody());
	}
}
