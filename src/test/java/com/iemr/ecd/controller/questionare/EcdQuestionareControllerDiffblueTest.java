package com.iemr.ecd.controller.questionare;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

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

@ContextConfiguration(classes = { EcdQuestionareController.class, RequestSectionQuestionnaireMappingDTO.class })
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class EcdQuestionareControllerDiffblueTest {
	@Autowired
	private RequestSectionQuestionnaireMappingDTO requestSectionQuestionnaireMappingDTO;

	@Autowired
	private EcdQuestionareController ecdQuestionareController;

	@MockBean
	private QuestionareServiceImpl questionareServiceImpl;

	@Test
	void testCreateQuestionnaires() throws Exception {

		ArrayList<Questionnaire> questionnaireList = new ArrayList<>();

		Questionnaire questionnaire = new Questionnaire();
		questionnaire.setAnswerType("Answer Type");
		questionnaire.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
		questionnaire.setCreatedDate(mock(Timestamp.class));
		questionnaire.setDeleted(true);
		questionnaire.setLastModDate(mock(Timestamp.class));
		questionnaire.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
		questionnaire.setOptions(new String[] { "Options" });
		questionnaire.setPsmId(1);
		questionnaire.setQuestionRank(1);
		questionnaire.setQuestionnaire("Questionnaire");
		questionnaire.setQuestionnaireId(1);
		questionnaire.setQuestionnaireType("Questionnaire Type");
		questionnaire.setQuestionnaireTypeId(1);
		questionnaire.setRank(1);
		questionnaire.setRoles(new String[] { "Roles" });
		questionnaire.setSecQuesMapId(1);
		questionnaire.setQuestionnaireValues(null);

		questionnaire.toString();

		questionnaireList.add(questionnaire);

		ResponseEntity<String> response = ecdQuestionareController.createQuestionnaires(questionnaireList);

		assertEquals(response, ecdQuestionareController.createQuestionnaires(questionnaireList));
	}

	@Test
	void testCreateQuestionnairesMap() throws Exception {

		MapQuestion mapQuestion = new MapQuestion();
		mapQuestion.setAnswer(new String[] { "Answer" });
		mapQuestion.setAnswerDb("Answer Db");
		mapQuestion.setChildQuestionId(1);
		mapQuestion.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
		mapQuestion.setCreatedDate(mock(Timestamp.class));
		mapQuestion.setDeleted(true);
		mapQuestion.setId(1);
		mapQuestion.setLastModDate(mock(Timestamp.class));
		mapQuestion.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
		mapQuestion.setParentQuestionId(1);
		mapQuestion.setPsmId(1);

		mapQuestion.toString();

		ResponseEntity<String> response = ecdQuestionareController.createQuestionnairesMap(mapQuestion);

		assertEquals(response, ecdQuestionareController.createQuestionnairesMap(mapQuestion));
	}

	@Test
	void testCreateSections() throws Exception {

		QuestionnaireSections questionnaireSections = new QuestionnaireSections();
		questionnaireSections.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
		questionnaireSections.setCreatedDate(mock(Timestamp.class));
		questionnaireSections.setDeleted(true);
		questionnaireSections.setIsChecked(true);
		questionnaireSections.setLastModDate(mock(Timestamp.class));
		questionnaireSections.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
		questionnaireSections.setPsmId(1);
		questionnaireSections.setSectionDesc("NULL/Empty request");
		questionnaireSections.setSectionId(1);
		questionnaireSections.setSectionName("NULL/Empty request");
		questionnaireSections.setSectionRank(1);

		questionnaireSections.toString();

		List<QuestionnaireSections> questionareSections = new ArrayList<>();

		questionareSections.add(questionnaireSections);

		ResponseEntity<String> response = ecdQuestionareController.createSections(questionareSections);

		assertEquals(response, ecdQuestionareController.createSections(questionareSections));

	}

	@Test
	void testEditQuestionnaireSectionMap() throws Exception {

		SectionQuestionnaireMapping sectionQuestionnaireMapping = new SectionQuestionnaireMapping();
		sectionQuestionnaireMapping.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
		sectionQuestionnaireMapping.setCreatedDate(mock(Timestamp.class));
		sectionQuestionnaireMapping.setDeleted(true);
		sectionQuestionnaireMapping.setId(1);
		sectionQuestionnaireMapping.setLastModDate(mock(Timestamp.class));
		sectionQuestionnaireMapping.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
		sectionQuestionnaireMapping.setPsmId(1);
		sectionQuestionnaireMapping.setQuestionId(1);
		sectionQuestionnaireMapping.setRank(1);
		sectionQuestionnaireMapping.setRole("Role");
		sectionQuestionnaireMapping.setRoles(new String[] { "Roles" });
		sectionQuestionnaireMapping.setSectionId(1);
		sectionQuestionnaireMapping.setSectionName("Section Name");

		sectionQuestionnaireMapping.toString();

		ResponseEntity<String> response = ecdQuestionareController
				.editQuestionnaireSectionMap(sectionQuestionnaireMapping);

		assertEquals(response, ecdQuestionareController.editQuestionnaireSectionMap(sectionQuestionnaireMapping));

	}

	@Test
	void testEditQuestionnairesMap() throws Exception {

		MapQuestion mapQuestion = new MapQuestion();
		mapQuestion.setAnswer(new String[] { "Answer" });
		mapQuestion.setAnswerDb("Answer Db");
		mapQuestion.setChildQuestionId(1);
		mapQuestion.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
		mapQuestion.setCreatedDate(mock(Timestamp.class));
		mapQuestion.setDeleted(true);
		mapQuestion.setId(1);
		mapQuestion.setLastModDate(mock(Timestamp.class));
		mapQuestion.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
		mapQuestion.setParentQuestionId(1);
		mapQuestion.setPsmId(1);

		mapQuestion.toString();

		ResponseEntity<String> response = ecdQuestionareController.editQuestionnairesMap(mapQuestion);

		assertEquals(response, ecdQuestionareController.editQuestionnairesMap(mapQuestion));

	}

	@Test
	void testGetMappedParentChildQuestionnaire() throws Exception {

		int psmId = 12;

		ResponseEntity<List<ECDMapQuestions>> response = ecdQuestionareController
				.getMappedParentChildQuestionnaire(psmId);

		assertEquals(response, ecdQuestionareController.getMappedParentChildQuestionnaire(psmId));
	}

	@Test
	void testGetQuesAndSecMapAssociateByProvider() throws Exception {

		int psmId = 1;
		String ecdCallType = "Normal";
		String role = "ECDAdmin";

		ResponseEntity<List<V_GetSectionQuestionMappingAssociates>> response = ecdQuestionareController
				.getQuesAndSecMapAssociateByProvider(psmId, ecdCallType, role);

		assertEquals(response, ecdQuestionareController.getQuesAndSecMapAssociateByProvider(psmId, ecdCallType, role));
	}

	@Test
	void testGetQuestionnaires() throws Exception {

		int psmId = 1;

		ResponseEntity<List<Questionnaire>> response = ecdQuestionareController.getQuestionnaires(psmId);

		assertEquals(response, ecdQuestionareController.getQuestionnaires(psmId));

	}

	@Test
	void testGetQuestionnairesAndSectionMap() throws Exception {

		int sectionId = 1;

		ResponseEntity<ResponseSectionQuestionnaireMappingDTO> response = ecdQuestionareController
				.getQuestionnairesAndSectionMap(sectionId);

		assertEquals(response, ecdQuestionareController.getQuestionnairesAndSectionMap(sectionId));
	}

	@Test
	void testGetQuestionnairesAndSectionMapByProvider() throws Exception {

		int psmId = 1;

		ResponseEntity<List<V_GetSectionQuestionMapping>> response = ecdQuestionareController
				.getQuestionnairesAndSectionMapByProvider(psmId);

		assertEquals(response, ecdQuestionareController.getQuestionnairesAndSectionMapByProvider(psmId));
	}

	@Test
	void testGetQuestionnairesByPSMId() throws Exception {

		int psmId = 1;

		ResponseEntity<List<Questionnaire>> response = ecdQuestionareController.getQuestionnairesByPSMId(psmId);

		assertEquals(response, ecdQuestionareController.getQuestionnairesByPSMId(psmId));
	}

	@Test
	void testGetSectionsByProvider() throws Exception {

		int psmId = 1;

		ResponseEntity<List<QuestionnaireSections>> response = ecdQuestionareController.getSectionsByProvider(psmId);

		assertEquals(response, ecdQuestionareController.getSectionsByProvider(psmId));
	}

	@Test
	void testGetUnMappedQuestionnaires() throws Exception {

		int psmId = 1;

		int sectionId = 2;

		ResponseEntity<List<Questionnaire>> response = ecdQuestionareController.getUnMappedQuestionnaires(psmId,
				sectionId);

		assertEquals(response, ecdQuestionareController.getUnMappedQuestionnaires(psmId, sectionId));
	}

	@Test
	void testGetUnMappedQuestionnairesByPSMId() throws Exception {

		int psmId = 1;

		ResponseEntity<List<Questionnaire>> response = ecdQuestionareController.getUnMappedQuestionnairesByPSMId(psmId);

		assertEquals(response, ecdQuestionareController.getUnMappedQuestionnairesByPSMId(psmId));

	}

	@Test
	void testMapQuestionnairesAndSection() throws Exception {

		RequestSectionQuestionnaireMappingDTO requestSectionQuestionnaireMappingDTO2 = new RequestSectionQuestionnaireMappingDTO();
		requestSectionQuestionnaireMappingDTO2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
		requestSectionQuestionnaireMappingDTO2.setDeleted(true);
		requestSectionQuestionnaireMappingDTO2.setId(1);
		requestSectionQuestionnaireMappingDTO2.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
		requestSectionQuestionnaireMappingDTO2.setPsmId(1);
		requestSectionQuestionnaireMappingDTO2.setQuestionIds(new ArrayList<>());
		requestSectionQuestionnaireMappingDTO2.setRank(1);
		requestSectionQuestionnaireMappingDTO2.setSectionId(1);

		requestSectionQuestionnaireMappingDTO2.toString();

		ResponseEntity<Object> response = ecdQuestionareController
				.mapQuestionnairesAndSection(requestSectionQuestionnaireMappingDTO2);

		assertEquals(response,
				ecdQuestionareController.mapQuestionnairesAndSection(requestSectionQuestionnaireMappingDTO2));

	}

	@Test
	void testUpdateQuestionnaire() throws Exception {

		Questionnaire questionnaire = new Questionnaire();
		questionnaire.setAnswerType("Answer Type");
		questionnaire.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
		questionnaire.setCreatedDate(mock(Timestamp.class));
		questionnaire.setDeleted(true);
		questionnaire.setLastModDate(mock(Timestamp.class));
		questionnaire.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
		questionnaire.setOptions(new String[] { "Options" });
		questionnaire.setPsmId(1);
		questionnaire.setQuestionRank(1);
		questionnaire.setQuestionnaire("Questionnaire");
		questionnaire.setQuestionnaireId(1);
		questionnaire.setQuestionnaireType("Questionnaire Type");
		questionnaire.setQuestionnaireTypeId(1);
		questionnaire.setQuestionnaireValues(new ArrayList<>());
		questionnaire.setRank(1);
		questionnaire.setRoles(new String[] { "Roles" });
		questionnaire.setSecQuesMapId(1);

		questionnaire.toString();

		ResponseEntity<Object> response = ecdQuestionareController.updateQuestionnaire(questionnaire);

		assertEquals(response, ecdQuestionareController.updateQuestionnaire(questionnaire));

	}

	@Test
	void testUpdateSection() throws Exception {

		QuestionnaireSections questionnaireSections = new QuestionnaireSections();
		questionnaireSections.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
		questionnaireSections.setCreatedDate(mock(Timestamp.class));
		questionnaireSections.setDeleted(true);
		questionnaireSections.setIsChecked(true);
		questionnaireSections.setLastModDate(mock(Timestamp.class));
		questionnaireSections.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
		questionnaireSections.setPsmId(1);
		questionnaireSections.setSectionDesc("Section Desc");
		questionnaireSections.setSectionId(1);
		questionnaireSections.setSectionName("Section Name");
		questionnaireSections.setSectionRank(1);

		questionnaireSections.toString();

		ResponseEntity<String> response = ecdQuestionareController.updateSection(questionnaireSections);

		assertEquals(response, ecdQuestionareController.updateSection(questionnaireSections));

	}
}
