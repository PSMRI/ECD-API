package com.iemr.ecd.service.questionare;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.iemr.ecd.dao.CallConfiguration;
import com.iemr.ecd.dao.MapQuestion;
import com.iemr.ecd.dao.Questionnaire;
import com.iemr.ecd.dao.QuestionnaireSections;
import com.iemr.ecd.dao.QuestionnaireValues;
import com.iemr.ecd.dao.SectionQuestionnaireMapping;
import com.iemr.ecd.dao.V_GetSectionQuestionMapping;
import com.iemr.ecd.dao.V_GetSectionQuestionMappingAssociates;
import com.iemr.ecd.dto.ECDMapQuestions;
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

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = { QuestionareServiceImpl.class, RequestSectionQuestionnaireMappingDTO.class })
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class QuestionareServiceImplDiffblueTest {
	@Autowired
	private RequestSectionQuestionnaireMappingDTO requestSectionQuestionnaireMappingDTO;

	@MockBean
	private CallConfigurationRepo callConfigurationRepo;

	@MockBean
	private EcdQuestionnaireRepo ecdQuestionnaireRepo;

	@MockBean
	private MapQuestionRepo mapQuestionRepo;

	@MockBean
	private ObservationRegistry observationRegistry;

	@Autowired
	private QuestionareServiceImpl questionareServiceImpl;

	@MockBean
	private QuestionnaireSectionRepo questionnaireSectionRepo;

	@MockBean
	private QuestionnaireValuesRepo questionnaireValuesRepo;

	@MockBean
	private SectionQuestionnaireMappingRepo sectionQuestionnaireMappingRepo;

	@MockBean
	private V_GetSectionQuestionMappingAssociatesRepo v_GetSectionQuestionMappingAssociatesRepo;

	@MockBean
	private V_GetSectionQuestionMappingRepo v_GetSectionQuestionMappingRepo;

	/**
	 * Method under test: {@link QuestionareServiceImpl#createQuestionares(List)}
	 */
	@Test
	void testCreateQuestionares() {
		// Arrange, Act and Assert
		assertEquals("{\"response\":\"created successfully\"}",
				questionareServiceImpl.createQuestionares(new ArrayList<>()));
	}

	/**
	 * Method under test: {@link QuestionareServiceImpl#createQuestionares(List)}
	 */
	@Test
	void testCreateQuestionares2() {
		// Arrange
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

		when(ecdQuestionnaireRepo.save(Mockito.<Questionnaire>any())).thenReturn(questionnaire);

		Questionnaire questionnaire2 = new Questionnaire();
		questionnaire2.setAnswerType("response");
		questionnaire2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
		questionnaire2.setCreatedDate(mock(Timestamp.class));
		questionnaire2.setDeleted(true);
		questionnaire2.setLastModDate(mock(Timestamp.class));
		questionnaire2.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
		questionnaire2.setOptions(new String[] { "response" });
		questionnaire2.setPsmId(1);
		questionnaire2.setQuestionRank(1);
		questionnaire2.setQuestionnaire("response");
		questionnaire2.setQuestionnaireId(1);
		questionnaire2.setQuestionnaireType("response");
		questionnaire2.setQuestionnaireTypeId(1);
		questionnaire2.setQuestionnaireValues(new ArrayList<>());
		questionnaire2.setRank(1);
		questionnaire2.setRoles(new String[] { "response" });
		questionnaire2.setSecQuesMapId(1);

		ArrayList<Questionnaire> questionares = new ArrayList<>();
		questionares.add(questionnaire2);

		// Act
		String actualCreateQuestionaresResult = questionareServiceImpl.createQuestionares(questionares);

		// Assert
		verify(ecdQuestionnaireRepo).save(isA(Questionnaire.class));
		assertEquals("{\"response\":\"created successfully\"}", actualCreateQuestionaresResult);
	}

	/**
	 * Method under test: {@link QuestionareServiceImpl#createQuestionares(List)}
	 */
	@Test
	void testCreateQuestionares3() {
		// Arrange
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
		when(ecdQuestionnaireRepo.save(Mockito.<Questionnaire>any())).thenReturn(questionnaire);

		Questionnaire questionnaire2 = new Questionnaire();
		questionnaire2.setAnswerType("response");
		questionnaire2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
		questionnaire2.setCreatedDate(mock(Timestamp.class));
		questionnaire2.setDeleted(true);
		questionnaire2.setLastModDate(mock(Timestamp.class));
		questionnaire2.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
		questionnaire2.setOptions(new String[] { "response" });
		questionnaire2.setPsmId(1);
		questionnaire2.setQuestionRank(1);
		questionnaire2.setQuestionnaire("response");
		questionnaire2.setQuestionnaireId(1);
		questionnaire2.setQuestionnaireType("response");
		questionnaire2.setQuestionnaireTypeId(1);
		questionnaire2.setQuestionnaireValues(new ArrayList<>());
		questionnaire2.setRank(1);
		questionnaire2.setRoles(new String[] { "response" });
		questionnaire2.setSecQuesMapId(1);

		Questionnaire questionnaire3 = new Questionnaire();
		questionnaire3.setAnswerType("created successfully");
		questionnaire3.setCreatedBy("response");
		questionnaire3.setCreatedDate(mock(Timestamp.class));
		questionnaire3.setDeleted(false);
		questionnaire3.setLastModDate(mock(Timestamp.class));
		questionnaire3.setModifiedBy("response");
		questionnaire3.setOptions(new String[] { "response" });
		questionnaire3.setPsmId(2);
		questionnaire3.setQuestionRank(0);
		questionnaire3.setQuestionnaire("created successfully");
		questionnaire3.setQuestionnaireId(2);
		questionnaire3.setQuestionnaireType("created successfully");
		questionnaire3.setQuestionnaireTypeId(2);
		questionnaire3.setQuestionnaireValues(new ArrayList<>());
		questionnaire3.setRank(0);
		questionnaire3.setRoles(new String[] { "response" });
		questionnaire3.setSecQuesMapId(2);

		ArrayList<Questionnaire> questionares = new ArrayList<>();
		questionares.add(questionnaire3);
		questionares.add(questionnaire2);

		// Act
		String actualCreateQuestionaresResult = questionareServiceImpl.createQuestionares(questionares);

		// Assert
		verify(ecdQuestionnaireRepo, atLeast(1)).save(Mockito.<Questionnaire>any());
		assertEquals("{\"response\":\"created successfully\"}", actualCreateQuestionaresResult);
	}

	/**
	 * Method under test: {@link QuestionareServiceImpl#createQuestionares(List)}
	 */
	@Test
	void testCreateQuestionares4() {
		// Arrange
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
		when(ecdQuestionnaireRepo.save(Mockito.<Questionnaire>any())).thenReturn(questionnaire);
		when(questionnaireValuesRepo.saveAll(Mockito.<Iterable<QuestionnaireValues>>any()))
				.thenReturn(new ArrayList<>());

		QuestionnaireValues questionnaireValues = new QuestionnaireValues();
		questionnaireValues.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
		questionnaireValues.setCreatedDate(mock(Timestamp.class));
		questionnaireValues.setDeleted(true);
		questionnaireValues.setId(1);
		questionnaireValues.setLastModDate(mock(Timestamp.class));
		questionnaireValues.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
		questionnaireValues.setOptions("response");
		questionnaireValues.setPsmId(1);
		questionnaireValues.setQuestionId(1);
		questionnaireValues.setQuestionValuesWeightage(42);

		ArrayList<QuestionnaireValues> questionnaireValues2 = new ArrayList<>();
		questionnaireValues2.add(questionnaireValues);

		Questionnaire questionnaire2 = new Questionnaire();
		questionnaire2.setAnswerType("response");
		questionnaire2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
		questionnaire2.setCreatedDate(mock(Timestamp.class));
		questionnaire2.setDeleted(true);
		questionnaire2.setLastModDate(mock(Timestamp.class));
		questionnaire2.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
		questionnaire2.setOptions(new String[] { "response" });
		questionnaire2.setPsmId(1);
		questionnaire2.setQuestionRank(1);
		questionnaire2.setQuestionnaire("response");
		questionnaire2.setQuestionnaireId(1);
		questionnaire2.setQuestionnaireType("response");
		questionnaire2.setQuestionnaireTypeId(1);
		questionnaire2.setQuestionnaireValues(questionnaireValues2);
		questionnaire2.setRank(1);
		questionnaire2.setRoles(new String[] { "response" });
		questionnaire2.setSecQuesMapId(1);

		ArrayList<Questionnaire> questionares = new ArrayList<>();
		questionares.add(questionnaire2);

		// Act
		String actualCreateQuestionaresResult = questionareServiceImpl.createQuestionares(questionares);

		// Assert
		verify(ecdQuestionnaireRepo).save(isA(Questionnaire.class));
		verify(questionnaireValuesRepo).saveAll(isA(Iterable.class));
		assertEquals("{\"response\":\"created successfully\"}", actualCreateQuestionaresResult);
	}

	/**
	 * Method under test:
	 * {@link QuestionareServiceImpl#updateQuestionares(Questionnaire)}
	 */
	@Test
	void testUpdateQuestionares() {
		// Arrange
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
		when(ecdQuestionnaireRepo.save(Mockito.<Questionnaire>any())).thenReturn(questionnaire);

		Questionnaire questionare = new Questionnaire();
		questionare.setAnswerType("Answer Type");
		questionare.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
		questionare.setCreatedDate(mock(Timestamp.class));
		questionare.setDeleted(true);
		questionare.setLastModDate(mock(Timestamp.class));
		questionare.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
		questionare.setOptions(new String[] { "Options" });
		questionare.setPsmId(1);
		questionare.setQuestionRank(1);
		questionare.setQuestionnaire("Questionnaire");
		questionare.setQuestionnaireId(1);
		questionare.setQuestionnaireType("Questionnaire Type");
		questionare.setQuestionnaireTypeId(1);
		questionare.setQuestionnaireValues(new ArrayList<>());
		questionare.setRank(1);
		questionare.setRoles(new String[] { "Roles" });
		questionare.setSecQuesMapId(1);

		// Act
		String actualUpdateQuestionaresResult = questionareServiceImpl.updateQuestionares(questionare);

		// Assert
		verify(ecdQuestionnaireRepo).save(isA(Questionnaire.class));
		assertEquals("{\"response\":\"modified successfully\"}", actualUpdateQuestionaresResult);
	}

	/**
	 * Method under test:
	 * {@link QuestionareServiceImpl#updateQuestionares(Questionnaire)}
	 */
	@Test
	void testUpdateQuestionares2() {
		// Arrange
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
		when(ecdQuestionnaireRepo.save(Mockito.<Questionnaire>any())).thenReturn(questionnaire);
		when(questionnaireValuesRepo.saveAll(Mockito.<Iterable<QuestionnaireValues>>any()))
				.thenReturn(new ArrayList<>());

		QuestionnaireValues questionnaireValues = new QuestionnaireValues();
		questionnaireValues.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
		questionnaireValues.setCreatedDate(mock(Timestamp.class));
		questionnaireValues.setDeleted(true);
		questionnaireValues.setId(1);
		questionnaireValues.setLastModDate(mock(Timestamp.class));
		questionnaireValues.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
		questionnaireValues.setOptions("response");
		questionnaireValues.setPsmId(1);
		questionnaireValues.setQuestionId(1);
		questionnaireValues.setQuestionValuesWeightage(42);

		ArrayList<QuestionnaireValues> questionnaireValues2 = new ArrayList<>();
		questionnaireValues2.add(questionnaireValues);

		Questionnaire questionare = new Questionnaire();
		questionare.setAnswerType("Answer Type");
		questionare.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
		questionare.setCreatedDate(mock(Timestamp.class));
		questionare.setDeleted(true);
		questionare.setLastModDate(mock(Timestamp.class));
		questionare.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
		questionare.setOptions(new String[] { "Options" });
		questionare.setPsmId(1);
		questionare.setQuestionRank(1);
		questionare.setQuestionnaire("Questionnaire");
		questionare.setQuestionnaireId(1);
		questionare.setQuestionnaireType("Questionnaire Type");
		questionare.setQuestionnaireTypeId(1);
		questionare.setQuestionnaireValues(questionnaireValues2);
		questionare.setRank(1);
		questionare.setRoles(new String[] { "Roles" });
		questionare.setSecQuesMapId(1);

		questionare.toString();

		// Act
		String actualUpdateQuestionaresResult = questionareServiceImpl.updateQuestionares(questionare);

		// Assert
		verify(ecdQuestionnaireRepo).save(isA(Questionnaire.class));
		verify(questionnaireValuesRepo).saveAll(isA(Iterable.class));
		assertEquals("{\"response\":\"modified successfully\"}", actualUpdateQuestionaresResult);
	}

	/**
	 * Method under test: {@link QuestionareServiceImpl#createSections(List)}
	 */
	@Test
	void testCreateSections() {
		// Arrange, Act and Assert
		assertThrows(ECDException.class, () -> questionareServiceImpl.createSections(new ArrayList<>()));
	}

	/**
	 * Method under test: {@link QuestionareServiceImpl#createSections(List)}
	 */
	@Test
	void testCreateSections2() {
		// Arrange
		when(questionnaireSectionRepo.saveAll(Mockito.<Iterable<QuestionnaireSections>>any()))
				.thenReturn(new ArrayList<>());

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

		ArrayList<QuestionnaireSections> questionareSections = new ArrayList<>();
		questionareSections.add(questionnaireSections);

		// Act
		String actualCreateSectionsResult = questionareServiceImpl.createSections(questionareSections);

		// Assert
		verify(questionnaireSectionRepo).saveAll(isA(Iterable.class));
		assertEquals("{\"response\":\"created successfully\"}", actualCreateSectionsResult);
	}

	/**
	 * Method under test: {@link QuestionareServiceImpl#createSections(List)}
	 */
	@Test
	void testCreateSections3() {
		// Arrange
		when(questionnaireSectionRepo.saveAll(Mockito.<Iterable<QuestionnaireSections>>any()))
				.thenReturn(new ArrayList<>());

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

		QuestionnaireSections questionnaireSections2 = new QuestionnaireSections();
		questionnaireSections2.setCreatedBy("response");
		questionnaireSections2.setCreatedDate(mock(Timestamp.class));
		questionnaireSections2.setDeleted(false);
		questionnaireSections2.setIsChecked(false);
		questionnaireSections2.setLastModDate(mock(Timestamp.class));
		questionnaireSections2.setModifiedBy("response");
		questionnaireSections2.setPsmId(2);
		questionnaireSections2.setSectionDesc("created successfully");
		questionnaireSections2.setSectionId(2);
		questionnaireSections2.setSectionName("created successfully");
		questionnaireSections2.setSectionRank(0);

		ArrayList<QuestionnaireSections> questionareSections = new ArrayList<>();
		questionareSections.add(questionnaireSections2);
		questionareSections.add(questionnaireSections);

		// Act
		String actualCreateSectionsResult = questionareServiceImpl.createSections(questionareSections);

		// Assert
		verify(questionnaireSectionRepo).saveAll(isA(Iterable.class));
		assertEquals("{\"response\":\"created successfully\"}", actualCreateSectionsResult);
	}

	/**
	 * Method under test: {@link QuestionareServiceImpl#createSections(List)}
	 */
	@Test
	void testCreateSections4() {
		// Arrange
		when(questionnaireSectionRepo.saveAll(Mockito.<Iterable<QuestionnaireSections>>any()))
				.thenThrow(new ECDException("response"));

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
		
		ArrayList<QuestionnaireSections> questionareSections = new ArrayList<>();
		questionareSections.add(questionnaireSections);

		// Act and Assert
		assertThrows(ECDException.class, () -> questionareServiceImpl.createSections(questionareSections));
		verify(questionnaireSectionRepo).saveAll(isA(Iterable.class));
	}

	/**
	 * Method under test: {@link QuestionareServiceImpl#getSectionsByProvider(int)}
	 */
	@Test
	void testGetSectionsByProvider() {
		// Arrange
		ArrayList<QuestionnaireSections> questionnaireSectionsList = new ArrayList<>();
		when(questionnaireSectionRepo.findByPsmIdOrderByLastModDateDesc(Mockito.<Integer>any()))
				.thenReturn(questionnaireSectionsList);

		// Act
		List<QuestionnaireSections> actualSectionsByProvider = questionareServiceImpl.getSectionsByProvider(1);

		// Assert
		verify(questionnaireSectionRepo).findByPsmIdOrderByLastModDateDesc(isA(Integer.class));
		assertTrue(actualSectionsByProvider.isEmpty());
		assertSame(questionnaireSectionsList, actualSectionsByProvider);
	}

	/**
	 * Method under test: {@link QuestionareServiceImpl#getSectionsByProvider(int)}
	 */
	@Test
	void testGetSectionsByProvider2() {
		// Arrange, Act and Assert
		assertThrows(ECDException.class, () -> questionareServiceImpl.getSectionsByProvider(0));
	}

	/**
	 * Method under test: {@link QuestionareServiceImpl#getSectionsByProvider(int)}
	 */
	@Test
	void testGetSectionsByProvider3() {
		// Arrange
		when(questionnaireSectionRepo.findByPsmIdOrderByLastModDateDesc(Mockito.<Integer>any()))
				.thenThrow(new ECDException("Exception Msg"));

		// Act and Assert
		assertThrows(ECDException.class, () -> questionareServiceImpl.getSectionsByProvider(1));
		verify(questionnaireSectionRepo).findByPsmIdOrderByLastModDateDesc(isA(Integer.class));
	}

	/**
	 * Method under test:
	 * {@link QuestionareServiceImpl#updateSections(QuestionnaireSections)}
	 */
	@Test
	void testUpdateSections() {
		// Arrange
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
		when(questionnaireSectionRepo.save(Mockito.<QuestionnaireSections>any())).thenReturn(questionnaireSections);

		QuestionnaireSections questionareSection = new QuestionnaireSections();
		questionareSection.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
		questionareSection.setCreatedDate(mock(Timestamp.class));
		questionareSection.setDeleted(true);
		questionareSection.setIsChecked(true);
		questionareSection.setLastModDate(mock(Timestamp.class));
		questionareSection.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
		questionareSection.setPsmId(1);
		questionareSection.setSectionDesc("Section Desc");
		questionareSection.setSectionId(1);
		questionareSection.setSectionName("Section Name");
		questionareSection.setSectionRank(1);

		// Act
		String actualUpdateSectionsResult = questionareServiceImpl.updateSections(questionareSection);

		// Assert
		verify(questionnaireSectionRepo).save(isA(QuestionnaireSections.class));
		assertEquals("{\"response\":\"updated successfully\"}", actualUpdateSectionsResult);
	}

	/**
	 * Method under test:
	 * {@link QuestionareServiceImpl#updateSections(QuestionnaireSections)}
	 */
	@Test
	void testUpdateSections2() {
		// Arrange
		QuestionnaireSections questionareSection = new QuestionnaireSections();
		questionareSection.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
		questionareSection.setCreatedDate(mock(Timestamp.class));
		questionareSection.setDeleted(true);
		questionareSection.setIsChecked(true);
		questionareSection.setLastModDate(mock(Timestamp.class));
		questionareSection.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
		questionareSection.setPsmId(1);
		questionareSection.setSectionDesc("Section Desc");
		questionareSection.setSectionId(null);
		questionareSection.setSectionName("Section Name");
		questionareSection.setSectionRank(1);

		// Act and Assert
		assertThrows(ECDException.class, () -> questionareServiceImpl.updateSections(questionareSection));
	}

	/**
	 * Method under test:
	 * {@link QuestionareServiceImpl#updateSections(QuestionnaireSections)}
	 */
	@Test
	void testUpdateSections3() {
		// Arrange
		when(questionnaireSectionRepo.save(Mockito.<QuestionnaireSections>any()))
				.thenThrow(new ECDException("response"));

		QuestionnaireSections questionareSection = new QuestionnaireSections();
		questionareSection.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
		questionareSection.setCreatedDate(mock(Timestamp.class));
		questionareSection.setDeleted(true);
		questionareSection.setIsChecked(true);
		questionareSection.setLastModDate(mock(Timestamp.class));
		questionareSection.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
		questionareSection.setPsmId(1);
		questionareSection.setSectionDesc("Section Desc");
		questionareSection.setSectionId(1);
		questionareSection.setSectionName("Section Name");
		questionareSection.setSectionRank(1);

		// Act and Assert
		assertThrows(ECDException.class, () -> questionareServiceImpl.updateSections(questionareSection));
		verify(questionnaireSectionRepo).save(isA(QuestionnaireSections.class));
	}

	/**
	 * Method under test:
	 * {@link QuestionareServiceImpl#mapQuestionnairesAndSection(RequestSectionQuestionnaireMappingDTO)}
	 */
	@Test
	void testMapQuestionnairesAndSection() {
		// Arrange, Act and Assert
		assertThrows(ECDException.class,
				() -> questionareServiceImpl.mapQuestionnairesAndSection(requestSectionQuestionnaireMappingDTO));
	}

	/**
	 * Method under test:
	 * {@link QuestionareServiceImpl#getQuestionaresByProvider(int)}
	 */
	@Test
	void testGetQuestionaresByProvider() {
		// Arrange
		ArrayList<Questionnaire> questionnaireList = new ArrayList<>();
		when(ecdQuestionnaireRepo.findByPsmIdOrderByLastModDateDesc(Mockito.<Integer>any()))
				.thenReturn(questionnaireList);

		// Act
		List<Questionnaire> actualQuestionaresByProvider = questionareServiceImpl.getQuestionaresByProvider(1);

		// Assert
		verify(ecdQuestionnaireRepo).findByPsmIdOrderByLastModDateDesc(isA(Integer.class));
		assertTrue(actualQuestionaresByProvider.isEmpty());
		assertSame(questionnaireList, actualQuestionaresByProvider);
	}

	/**
	 * Method under test:
	 * {@link QuestionareServiceImpl#getQuestionaresByProvider(int)}
	 */
	@Test
	void testGetQuestionaresByProvider2() {
		// Arrange
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

		ArrayList<Questionnaire> questionnaireList = new ArrayList<>();
		questionnaireList.add(questionnaire);
		when(ecdQuestionnaireRepo.findByPsmIdOrderByLastModDateDesc(Mockito.<Integer>any()))
				.thenReturn(questionnaireList);

		// Act
		List<Questionnaire> actualQuestionaresByProvider = questionareServiceImpl.getQuestionaresByProvider(1);

		// Assert
		verify(ecdQuestionnaireRepo).findByPsmIdOrderByLastModDateDesc(isA(Integer.class));
		assertEquals(1, actualQuestionaresByProvider.size());
		assertSame(questionnaireList, actualQuestionaresByProvider);
	}

	/**
	 * Method under test:
	 * {@link QuestionareServiceImpl#getQuestionaresByProvider(int)}
	 */
	@Test
	void testGetQuestionaresByProvider3() {
		// Arrange
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

		Questionnaire questionnaire2 = new Questionnaire();
		questionnaire2.setAnswerType("Dropdown");
		questionnaire2.setCreatedBy("Radio");
		questionnaire2.setCreatedDate(mock(Timestamp.class));
		questionnaire2.setDeleted(false);
		questionnaire2.setLastModDate(mock(Timestamp.class));
		questionnaire2.setModifiedBy("Radio");
		questionnaire2.setOptions(new String[] { "Radio" });
		questionnaire2.setPsmId(2);
		questionnaire2.setQuestionRank(0);
		questionnaire2.setQuestionnaire("Dropdown");
		questionnaire2.setQuestionnaireId(2);
		questionnaire2.setQuestionnaireType("Dropdown");
		questionnaire2.setQuestionnaireTypeId(2);
		questionnaire2.setQuestionnaireValues(new ArrayList<>());
		questionnaire2.setRank(0);
		questionnaire2.setRoles(new String[] { "Radio" });
		questionnaire2.setSecQuesMapId(2);

		ArrayList<Questionnaire> questionnaireList = new ArrayList<>();
		questionnaireList.add(questionnaire2);
		questionnaireList.add(questionnaire);
		when(ecdQuestionnaireRepo.findByPsmIdOrderByLastModDateDesc(Mockito.<Integer>any()))
				.thenReturn(questionnaireList);
		when(questionnaireValuesRepo.findByQuestionIdAndDeleted(Mockito.<Integer>any(), Mockito.<Boolean>any()))
				.thenReturn(new ArrayList<>());

		// Act
		List<Questionnaire> actualQuestionaresByProvider = questionareServiceImpl.getQuestionaresByProvider(1);

		// Assert
		verify(ecdQuestionnaireRepo).findByPsmIdOrderByLastModDateDesc(isA(Integer.class));
		verify(questionnaireValuesRepo).findByQuestionIdAndDeleted(isA(Integer.class), isA(Boolean.class));
		assertEquals(2, actualQuestionaresByProvider.size());
		assertSame(questionnaireList, actualQuestionaresByProvider);
	}

	/**
	 * Method under test:
	 * {@link QuestionareServiceImpl#getQuestionaresByProvider(int)}
	 */
	@Test
	void testGetQuestionaresByProvider4() {
		// Arrange
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

		Questionnaire questionnaire2 = new Questionnaire();
		questionnaire2.setAnswerType("Dropdown");
		questionnaire2.setCreatedBy("Radio");
		questionnaire2.setCreatedDate(mock(Timestamp.class));
		questionnaire2.setDeleted(false);
		questionnaire2.setLastModDate(mock(Timestamp.class));
		questionnaire2.setModifiedBy("Radio");
		questionnaire2.setOptions(new String[] { "Radio" });
		questionnaire2.setPsmId(2);
		questionnaire2.setQuestionRank(0);
		questionnaire2.setQuestionnaire("Dropdown");
		questionnaire2.setQuestionnaireId(2);
		questionnaire2.setQuestionnaireType("Dropdown");
		questionnaire2.setQuestionnaireTypeId(2);
		questionnaire2.setQuestionnaireValues(new ArrayList<>());
		questionnaire2.setRank(0);
		questionnaire2.setRoles(new String[] { "Radio" });
		questionnaire2.setSecQuesMapId(2);

		Questionnaire questionnaire3 = new Questionnaire();
		questionnaire3.setAnswerType("Multiple");
		questionnaire3.setCreatedBy("Dropdown");
		questionnaire3.setCreatedDate(mock(Timestamp.class));
		questionnaire3.setDeleted(true);
		questionnaire3.setLastModDate(mock(Timestamp.class));
		questionnaire3.setModifiedBy("Dropdown");
		questionnaire3.setOptions(new String[] { "Radio" });
		questionnaire3.setPsmId(3);
		questionnaire3.setQuestionRank(0);
		questionnaire3.setQuestionnaire("Multiple");
		questionnaire3.setQuestionnaireId(3);
		questionnaire3.setQuestionnaireType("Multiple");
		questionnaire3.setQuestionnaireTypeId(3);
		questionnaire3.setQuestionnaireValues(new ArrayList<>());
		questionnaire3.setRank(0);
		questionnaire3.setRoles(new String[] { "Radio" });
		questionnaire3.setSecQuesMapId(3);

		ArrayList<Questionnaire> questionnaireList = new ArrayList<>();
		questionnaireList.add(questionnaire3);
		questionnaireList.add(questionnaire2);
		questionnaireList.add(questionnaire);
		when(ecdQuestionnaireRepo.findByPsmIdOrderByLastModDateDesc(Mockito.<Integer>any()))
				.thenReturn(questionnaireList);
		when(questionnaireValuesRepo.findByQuestionIdAndDeleted(Mockito.<Integer>any(), Mockito.<Boolean>any()))
				.thenReturn(new ArrayList<>());

		// Act
		List<Questionnaire> actualQuestionaresByProvider = questionareServiceImpl.getQuestionaresByProvider(1);

		// Assert
		verify(ecdQuestionnaireRepo).findByPsmIdOrderByLastModDateDesc(isA(Integer.class));
		verify(questionnaireValuesRepo, atLeast(1)).findByQuestionIdAndDeleted(Mockito.<Integer>any(),
				isA(Boolean.class));
		assertEquals(3, actualQuestionaresByProvider.size());
		assertSame(questionnaireList, actualQuestionaresByProvider);
	}

	/**
	 * Method under test: {@link QuestionareServiceImpl#getQuestionares(int)}
	 */
	@Test
	void testGetQuestionares() {
		// Arrange
		ArrayList<Questionnaire> questionnaireList = new ArrayList<>();
		when(ecdQuestionnaireRepo.findByPsmIdAndDeletedOrderByLastModDateDesc(Mockito.<Integer>any(),
				Mockito.<Boolean>any())).thenReturn(questionnaireList);

		// Act
		List<Questionnaire> actualQuestionares = questionareServiceImpl.getQuestionares(1);

		// Assert
		verify(ecdQuestionnaireRepo).findByPsmIdAndDeletedOrderByLastModDateDesc(isA(Integer.class),
				isA(Boolean.class));
		assertTrue(actualQuestionares.isEmpty());
		assertSame(questionnaireList, actualQuestionares);
	}

	/**
	 * Method under test: {@link QuestionareServiceImpl#getQuestionares(int)}
	 */
	@Test
	void testGetQuestionares2() {
		// Arrange
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

		ArrayList<Questionnaire> questionnaireList = new ArrayList<>();
		questionnaireList.add(questionnaire);
		when(ecdQuestionnaireRepo.findByPsmIdAndDeletedOrderByLastModDateDesc(Mockito.<Integer>any(),
				Mockito.<Boolean>any())).thenReturn(questionnaireList);

		// Act
		List<Questionnaire> actualQuestionares = questionareServiceImpl.getQuestionares(1);

		// Assert
		verify(ecdQuestionnaireRepo).findByPsmIdAndDeletedOrderByLastModDateDesc(isA(Integer.class),
				isA(Boolean.class));
		assertEquals(1, actualQuestionares.size());
		assertSame(questionnaireList, actualQuestionares);
	}

	/**
	 * Method under test: {@link QuestionareServiceImpl#getQuestionares(int)}
	 */
	@Test
	void testGetQuestionares3() {
		// Arrange
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

		Questionnaire questionnaire2 = new Questionnaire();
		questionnaire2.setAnswerType("Dropdown");
		questionnaire2.setCreatedBy("Radio");
		questionnaire2.setCreatedDate(mock(Timestamp.class));
		questionnaire2.setDeleted(false);
		questionnaire2.setLastModDate(mock(Timestamp.class));
		questionnaire2.setModifiedBy("Radio");
		questionnaire2.setOptions(new String[] { "Radio" });
		questionnaire2.setPsmId(2);
		questionnaire2.setQuestionRank(0);
		questionnaire2.setQuestionnaire("Dropdown");
		questionnaire2.setQuestionnaireId(2);
		questionnaire2.setQuestionnaireType("Dropdown");
		questionnaire2.setQuestionnaireTypeId(2);
		questionnaire2.setQuestionnaireValues(new ArrayList<>());
		questionnaire2.setRank(0);
		questionnaire2.setRoles(new String[] { "Radio" });
		questionnaire2.setSecQuesMapId(2);

		ArrayList<Questionnaire> questionnaireList = new ArrayList<>();
		questionnaireList.add(questionnaire2);
		questionnaireList.add(questionnaire);
		when(ecdQuestionnaireRepo.findByPsmIdAndDeletedOrderByLastModDateDesc(Mockito.<Integer>any(),
				Mockito.<Boolean>any())).thenReturn(questionnaireList);
		when(questionnaireValuesRepo.findByQuestionIdAndDeleted(Mockito.<Integer>any(), Mockito.<Boolean>any()))
				.thenReturn(new ArrayList<>());

		// Act
		List<Questionnaire> actualQuestionares = questionareServiceImpl.getQuestionares(1);

		// Assert
		verify(ecdQuestionnaireRepo).findByPsmIdAndDeletedOrderByLastModDateDesc(isA(Integer.class),
				isA(Boolean.class));
		verify(questionnaireValuesRepo).findByQuestionIdAndDeleted(isA(Integer.class), isA(Boolean.class));
		assertEquals(2, actualQuestionares.size());
		assertSame(questionnaireList, actualQuestionares);
	}

	/**
	 * Method under test: {@link QuestionareServiceImpl#getQuestionares(int)}
	 */
	@Test
	void testGetQuestionares4() {
		// Arrange
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

		Questionnaire questionnaire2 = new Questionnaire();
		questionnaire2.setAnswerType("Dropdown");
		questionnaire2.setCreatedBy("Radio");
		questionnaire2.setCreatedDate(mock(Timestamp.class));
		questionnaire2.setDeleted(false);
		questionnaire2.setLastModDate(mock(Timestamp.class));
		questionnaire2.setModifiedBy("Radio");
		questionnaire2.setOptions(new String[] { "Radio" });
		questionnaire2.setPsmId(2);
		questionnaire2.setQuestionRank(0);
		questionnaire2.setQuestionnaire("Dropdown");
		questionnaire2.setQuestionnaireId(2);
		questionnaire2.setQuestionnaireType("Dropdown");
		questionnaire2.setQuestionnaireTypeId(2);
		questionnaire2.setQuestionnaireValues(new ArrayList<>());
		questionnaire2.setRank(0);
		questionnaire2.setRoles(new String[] { "Radio" });
		questionnaire2.setSecQuesMapId(2);

		Questionnaire questionnaire3 = new Questionnaire();
		questionnaire3.setAnswerType("Multiple");
		questionnaire3.setCreatedBy("Dropdown");
		questionnaire3.setCreatedDate(mock(Timestamp.class));
		questionnaire3.setDeleted(true);
		questionnaire3.setLastModDate(mock(Timestamp.class));
		questionnaire3.setModifiedBy("Dropdown");
		questionnaire3.setOptions(new String[] { "Radio" });
		questionnaire3.setPsmId(3);
		questionnaire3.setQuestionRank(0);
		questionnaire3.setQuestionnaire("Multiple");
		questionnaire3.setQuestionnaireId(3);
		questionnaire3.setQuestionnaireType("Multiple");
		questionnaire3.setQuestionnaireTypeId(3);
		questionnaire3.setQuestionnaireValues(new ArrayList<>());
		questionnaire3.setRank(0);
		questionnaire3.setRoles(new String[] { "Radio" });
		questionnaire3.setSecQuesMapId(3);

		ArrayList<Questionnaire> questionnaireList = new ArrayList<>();
		questionnaireList.add(questionnaire3);
		questionnaireList.add(questionnaire2);
		questionnaireList.add(questionnaire);
		when(ecdQuestionnaireRepo.findByPsmIdAndDeletedOrderByLastModDateDesc(Mockito.<Integer>any(),
				Mockito.<Boolean>any())).thenReturn(questionnaireList);
		when(questionnaireValuesRepo.findByQuestionIdAndDeleted(Mockito.<Integer>any(), Mockito.<Boolean>any()))
				.thenReturn(new ArrayList<>());

		// Act
		List<Questionnaire> actualQuestionares = questionareServiceImpl.getQuestionares(1);

		// Assert
		verify(ecdQuestionnaireRepo).findByPsmIdAndDeletedOrderByLastModDateDesc(isA(Integer.class),
				isA(Boolean.class));
		verify(questionnaireValuesRepo, atLeast(1)).findByQuestionIdAndDeleted(Mockito.<Integer>any(),
				isA(Boolean.class));
		assertEquals(3, actualQuestionares.size());
		assertSame(questionnaireList, actualQuestionares);
	}

	/**
	 * Method under test:
	 * {@link QuestionareServiceImpl#getUnMappedQuestionnairesByPSMId(int)}
	 */
	@Test
	void testGetUnMappedQuestionnairesByPSMId() {
		// Arrange
		ArrayList<Questionnaire> questionnaireList = new ArrayList<>();
		when(ecdQuestionnaireRepo.getUnMappedQuestionnairesByPSMId(Mockito.<Integer>any()))
				.thenReturn(questionnaireList);

		// Act
		List<Questionnaire> actualUnMappedQuestionnairesByPSMId = questionareServiceImpl
				.getUnMappedQuestionnairesByPSMId(1);

		// Assert
		verify(ecdQuestionnaireRepo).getUnMappedQuestionnairesByPSMId(isA(Integer.class));
		assertTrue(actualUnMappedQuestionnairesByPSMId.isEmpty());
		assertSame(questionnaireList, actualUnMappedQuestionnairesByPSMId);
	}

	/**
	 * Method under test:
	 * {@link QuestionareServiceImpl#getUnMappedQuestionnairesByPSMId(int)}
	 */
	@Test
	void testGetUnMappedQuestionnairesByPSMId2() {
		// Arrange
		when(ecdQuestionnaireRepo.getUnMappedQuestionnairesByPSMId(Mockito.<Integer>any()))
				.thenThrow(new ECDException("Exception Msg"));

		// Act and Assert
		assertThrows(ECDException.class, () -> questionareServiceImpl.getUnMappedQuestionnairesByPSMId(1));
		verify(ecdQuestionnaireRepo).getUnMappedQuestionnairesByPSMId(isA(Integer.class));
	}

	/**
	 * Method under test:
	 * {@link QuestionareServiceImpl#getQuestionnairesAndSectionMappingDataByProvider(int)}
	 */
	@Test
	void testGetQuestionnairesAndSectionMappingDataByProvider() {
		// Arrange
		when(questionnaireSectionRepo.findByPsmIdOrderByLastModDateDesc(Mockito.<Integer>any()))
				.thenReturn(new ArrayList<>());

		// Act
		List<V_GetSectionQuestionMapping> actualQuestionnairesAndSectionMappingDataByProvider = questionareServiceImpl
				.getQuestionnairesAndSectionMappingDataByProvider(1);

		// Assert
		verify(questionnaireSectionRepo).findByPsmIdOrderByLastModDateDesc(isA(Integer.class));
		assertTrue(actualQuestionnairesAndSectionMappingDataByProvider.isEmpty());
	}

	/**
	 * Method under test:
	 * {@link QuestionareServiceImpl#getQuestionnairesAndSectionMappingDataByProvider(int)}
	 */
	@Test
	void testGetQuestionnairesAndSectionMappingDataByProvider2() {
		// Arrange
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

		ArrayList<QuestionnaireSections> questionnaireSectionsList = new ArrayList<>();
		questionnaireSectionsList.add(questionnaireSections);
		when(questionnaireSectionRepo.findByPsmIdOrderByLastModDateDesc(Mockito.<Integer>any()))
				.thenReturn(questionnaireSectionsList);
		when(v_GetSectionQuestionMappingRepo.findBySectionid(Mockito.<Integer>any())).thenReturn(new ArrayList<>());

		// Act
		List<V_GetSectionQuestionMapping> actualQuestionnairesAndSectionMappingDataByProvider = questionareServiceImpl
				.getQuestionnairesAndSectionMappingDataByProvider(1);

		// Assert
		verify(questionnaireSectionRepo).findByPsmIdOrderByLastModDateDesc(isA(Integer.class));
		verify(v_GetSectionQuestionMappingRepo).findBySectionid(isA(Integer.class));
		assertTrue(actualQuestionnairesAndSectionMappingDataByProvider.isEmpty());
	}

	/**
	 * Method under test:
	 * {@link QuestionareServiceImpl#getQuestionnairesAndSectionMappingDataByProvider(int)}
	 */
	@Test
	void testGetQuestionnairesAndSectionMappingDataByProvider3() {
		// Arrange
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

		ArrayList<QuestionnaireSections> questionnaireSectionsList = new ArrayList<>();
		questionnaireSectionsList.add(questionnaireSections);
		when(questionnaireSectionRepo.findByPsmIdOrderByLastModDateDesc(Mockito.<Integer>any()))
				.thenReturn(questionnaireSectionsList);

		V_GetSectionQuestionMapping v_GetSectionQuestionMapping = new V_GetSectionQuestionMapping();
		v_GetSectionQuestionMapping.setDeleted(true);
		v_GetSectionQuestionMapping.setId(1);
		v_GetSectionQuestionMapping.setOptions(new ArrayList<>());
		v_GetSectionQuestionMapping.setOptionsArr(new String[] { "Options Arr" });
		v_GetSectionQuestionMapping.setPsmId(1);
		v_GetSectionQuestionMapping.setQuestion("Question");
		v_GetSectionQuestionMapping.setQuestionRank(1);
		v_GetSectionQuestionMapping.setQuestionType("Question Type");
		v_GetSectionQuestionMapping.setQuestionTypeID(1);
		v_GetSectionQuestionMapping.setQuestionid(1);
		v_GetSectionQuestionMapping.setRole("Role");
		v_GetSectionQuestionMapping.setRoles(new String[] { "Roles" });
		v_GetSectionQuestionMapping.setSectionName("Section Name");
		v_GetSectionQuestionMapping.setSectionQuestionRank(1);
		v_GetSectionQuestionMapping.setSectionid(1);
		
		v_GetSectionQuestionMapping.toString();
		
		ArrayList<V_GetSectionQuestionMapping> v_GetSectionQuestionMappingList = new ArrayList<>();
		v_GetSectionQuestionMappingList.add(v_GetSectionQuestionMapping);
		when(v_GetSectionQuestionMappingRepo.findBySectionid(Mockito.<Integer>any()))
				.thenReturn(v_GetSectionQuestionMappingList);

		// Act
		List<V_GetSectionQuestionMapping> actualQuestionnairesAndSectionMappingDataByProvider = questionareServiceImpl
				.getQuestionnairesAndSectionMappingDataByProvider(1);

		// Assert
		verify(questionnaireSectionRepo).findByPsmIdOrderByLastModDateDesc(isA(Integer.class));
		verify(v_GetSectionQuestionMappingRepo).findBySectionid(isA(Integer.class));
		assertEquals(1, actualQuestionnairesAndSectionMappingDataByProvider.size());
	}

	/**
	 * Method under test:
	 * {@link QuestionareServiceImpl#getQuestionnairesAndSectionMappingDataByProvider(int)}
	 */
	@Test
	void testGetQuestionnairesAndSectionMappingDataByProvider4() {
		// Arrange, Act and Assert
		assertThrows(ECDException.class,
				() -> questionareServiceImpl.getQuestionnairesAndSectionMappingDataByProvider(0));
	}

	/**
	 * Method under test:
	 * {@link QuestionareServiceImpl#getQuestionnairesAndSectionMappingDataByProvider(int)}
	 */
	@Test
	void testGetQuestionnairesAndSectionMappingDataByProvider5() {
		// Arrange
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
		
		ArrayList<QuestionnaireSections> questionnaireSectionsList = new ArrayList<>();
		questionnaireSectionsList.add(questionnaireSections);
		when(questionnaireSectionRepo.findByPsmIdOrderByLastModDateDesc(Mockito.<Integer>any()))
				.thenReturn(questionnaireSectionsList);
		when(v_GetSectionQuestionMappingRepo.findBySectionid(Mockito.<Integer>any()))
				.thenThrow(new ECDException("Exception Msg"));

		// Act and Assert
		assertThrows(ECDException.class,
				() -> questionareServiceImpl.getQuestionnairesAndSectionMappingDataByProvider(1));
		verify(questionnaireSectionRepo).findByPsmIdOrderByLastModDateDesc(isA(Integer.class));
		verify(v_GetSectionQuestionMappingRepo).findBySectionid(isA(Integer.class));
	}

	/**
	 * Method under test:
	 * {@link QuestionareServiceImpl#getQuesAndSecMapAssociateByProvider(int, String, String)}
	 */
	@Test
	void testGetQuesAndSecMapAssociateByProvider() {
		// Arrange
		CallConfiguration callConfiguration = new CallConfiguration();
		callConfiguration.setBaseLine("Base Line");
		callConfiguration.setCallConfigId(1L);
		callConfiguration.setCallType("Call Type");
		callConfiguration.setConfigId(1L);
		callConfiguration.setConfigTerms("Config Terms");
		callConfiguration.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
		callConfiguration.setCreatedDate(mock(Timestamp.class));
		callConfiguration.setDeleted(true);
		callConfiguration.setDisplayName("Display Name");
		callConfiguration.setEffectiveEndDate(mock(Timestamp.class));
		callConfiguration.setEffectiveStartDate(mock(Timestamp.class));
		callConfiguration.setLastModDate(mock(Timestamp.class));
		callConfiguration.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
		callConfiguration.setNextAttemptPeriod(1);
		callConfiguration.setNoOfAttempts(1);
		callConfiguration.setPsmId(1);
		callConfiguration.setTermRange(1);
		
		
		callConfiguration.toString();
		
		when(callConfigurationRepo.getCallConfigurationByProviderAndCallType(Mockito.<Integer>any(),
				Mockito.<String>any())).thenReturn(callConfiguration);
		when(v_GetSectionQuestionMappingAssociatesRepo.findByPsmIdAndCallConfigIdAndRole(Mockito.<Integer>any(),
				Mockito.<Integer>any(), Mockito.<String>any())).thenReturn(new ArrayList<>());

		// Act
		List<V_GetSectionQuestionMappingAssociates> actualQuesAndSecMapAssociateByProvider = questionareServiceImpl
				.getQuesAndSecMapAssociateByProvider(1, "Ecd Call Type", "Role");

		// Assert
		verify(callConfigurationRepo).getCallConfigurationByProviderAndCallType(isA(Integer.class),
				eq("Ecd Call Type"));
		verify(v_GetSectionQuestionMappingAssociatesRepo).findByPsmIdAndCallConfigIdAndRole(isA(Integer.class),
				isA(Integer.class), eq("Role"));
		assertTrue(actualQuesAndSecMapAssociateByProvider.isEmpty());
	}

	/**
	 * Method under test:
	 * {@link QuestionareServiceImpl#getQuesAndSecMapAssociateByProvider(int, String, String)}
	 */
	@Test
	void testGetQuesAndSecMapAssociateByProvider2() {
		// Arrange
		CallConfiguration callConfiguration = new CallConfiguration();
		callConfiguration.setBaseLine("Base Line");
		callConfiguration.setCallConfigId(1L);
		callConfiguration.setCallType("Call Type");
		callConfiguration.setConfigId(1L);
		callConfiguration.setConfigTerms("Config Terms");
		callConfiguration.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
		callConfiguration.setCreatedDate(mock(Timestamp.class));
		callConfiguration.setDeleted(true);
		callConfiguration.setDisplayName("Display Name");
		callConfiguration.setEffectiveEndDate(mock(Timestamp.class));
		callConfiguration.setEffectiveStartDate(mock(Timestamp.class));
		callConfiguration.setLastModDate(mock(Timestamp.class));
		callConfiguration.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
		callConfiguration.setNextAttemptPeriod(1);
		callConfiguration.setNoOfAttempts(1);
		callConfiguration.setPsmId(1);
		callConfiguration.setTermRange(1);
		when(callConfigurationRepo.getCallConfigurationByProviderAndCallType(Mockito.<Integer>any(),
				Mockito.<String>any())).thenReturn(callConfiguration);
		when(v_GetSectionQuestionMappingAssociatesRepo.findByPsmIdAndCallConfigIdAndRole(Mockito.<Integer>any(),
				Mockito.<Integer>any(), Mockito.<String>any())).thenThrow(new ECDException("Exception Msg"));

		// Act and Assert
		assertThrows(ECDException.class,
				() -> questionareServiceImpl.getQuesAndSecMapAssociateByProvider(1, "Ecd Call Type", "Role"));
		verify(callConfigurationRepo).getCallConfigurationByProviderAndCallType(isA(Integer.class),
				eq("Ecd Call Type"));
		verify(v_GetSectionQuestionMappingAssociatesRepo).findByPsmIdAndCallConfigIdAndRole(isA(Integer.class),
				isA(Integer.class), eq("Role"));
	}

	/**
	 * Method under test:
	 * {@link QuestionareServiceImpl#getQuesAndSecMapAssociateByProvider(int, String, String)}
	 */
	@Test
	void testGetQuesAndSecMapAssociateByProvider3() {
		// Arrange
		CallConfiguration callConfiguration = new CallConfiguration();
		callConfiguration.setBaseLine("Base Line");
		callConfiguration.setCallConfigId(null);
		callConfiguration.setCallType("Call Type");
		callConfiguration.setConfigId(1L);
		callConfiguration.setConfigTerms("Config Terms");
		callConfiguration.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
		callConfiguration.setCreatedDate(mock(Timestamp.class));
		callConfiguration.setDeleted(true);
		callConfiguration.setDisplayName("Display Name");
		callConfiguration.setEffectiveEndDate(mock(Timestamp.class));
		callConfiguration.setEffectiveStartDate(mock(Timestamp.class));
		callConfiguration.setLastModDate(mock(Timestamp.class));
		callConfiguration.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
		callConfiguration.setNextAttemptPeriod(1);
		callConfiguration.setNoOfAttempts(1);
		callConfiguration.setPsmId(1);
		callConfiguration.setTermRange(1);
		when(callConfigurationRepo.getCallConfigurationByProviderAndCallType(Mockito.<Integer>any(),
				Mockito.<String>any())).thenReturn(callConfiguration);

		// Act and Assert
		assertThrows(ECDException.class,
				() -> questionareServiceImpl.getQuesAndSecMapAssociateByProvider(1, "Ecd Call Type", "Role"));
		verify(callConfigurationRepo).getCallConfigurationByProviderAndCallType(isA(Integer.class),
				eq("Ecd Call Type"));
	}

	/**
	 * Method under test:
	 * {@link QuestionareServiceImpl#getQuesAndSecMapAssociateByProvider(int, String, String)}
	 */
	@Test
	void testGetQuesAndSecMapAssociateByProvider4() {
		// Arrange
		CallConfiguration callConfiguration = new CallConfiguration();
		callConfiguration.setBaseLine("Base Line");
		callConfiguration.setCallConfigId(1L);
		callConfiguration.setCallType("Call Type");
		callConfiguration.setConfigId(1L);
		callConfiguration.setConfigTerms("Config Terms");
		callConfiguration.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
		callConfiguration.setCreatedDate(mock(Timestamp.class));
		callConfiguration.setDeleted(true);
		callConfiguration.setDisplayName("Display Name");
		callConfiguration.setEffectiveEndDate(mock(Timestamp.class));
		callConfiguration.setEffectiveStartDate(mock(Timestamp.class));
		callConfiguration.setLastModDate(mock(Timestamp.class));
		callConfiguration.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
		callConfiguration.setNextAttemptPeriod(1);
		callConfiguration.setNoOfAttempts(1);
		callConfiguration.setPsmId(1);
		callConfiguration.setTermRange(1);
		when(callConfigurationRepo.getCallConfigurationByProviderAndCallType(Mockito.<Integer>any(),
				Mockito.<String>any())).thenReturn(callConfiguration);
		when(questionnaireValuesRepo.findByQuestionIdAndDeleted(Mockito.<Integer>any(), Mockito.<Boolean>any()))
				.thenReturn(new ArrayList<>());

		V_GetSectionQuestionMappingAssociates v_GetSectionQuestionMappingAssociates = new V_GetSectionQuestionMappingAssociates();
		v_GetSectionQuestionMappingAssociates.setAnswerType("Answer Type");
		v_GetSectionQuestionMappingAssociates.setCallConfigId(1);
		v_GetSectionQuestionMappingAssociates.setCallSectionRank(1);
		v_GetSectionQuestionMappingAssociates.setDeleted(true);
		v_GetSectionQuestionMappingAssociates.setId(1);
		v_GetSectionQuestionMappingAssociates.setOptions(new ArrayList<>());
		v_GetSectionQuestionMappingAssociates.setOptionsArr(new String[] { "Options Arr" });
		v_GetSectionQuestionMappingAssociates.setParentAnswer(new ArrayList<>());
		v_GetSectionQuestionMappingAssociates.setParentAnswerDb("Parent Answer Db");
		v_GetSectionQuestionMappingAssociates.setParentQuestion(new String[] { "Parent Question" });
		v_GetSectionQuestionMappingAssociates.setParentQuestionDb("Parent Question Db");
		v_GetSectionQuestionMappingAssociates.setParentQuestionId(new int[] { 1, 2, 1, 2 });
		v_GetSectionQuestionMappingAssociates.setParentQuestionIdDb("Parent Question Id Db");
		v_GetSectionQuestionMappingAssociates.setPsmId(1);
		v_GetSectionQuestionMappingAssociates.setQuestion("Question");
		v_GetSectionQuestionMappingAssociates.setQuestionRank(1);
		v_GetSectionQuestionMappingAssociates.setQuestionType("Question Type");
		v_GetSectionQuestionMappingAssociates.setQuestionTypeID(1);
		v_GetSectionQuestionMappingAssociates.setQuestionid(1);
		v_GetSectionQuestionMappingAssociates.setRole("Role");
		v_GetSectionQuestionMappingAssociates.setSectionName("Section Name");
		v_GetSectionQuestionMappingAssociates.setSectionQuestionRank(1);
		v_GetSectionQuestionMappingAssociates.setSectionid(1);
		
		v_GetSectionQuestionMappingAssociates.toString();
		
		ArrayList<V_GetSectionQuestionMappingAssociates> v_GetSectionQuestionMappingAssociatesList = new ArrayList<>();
		v_GetSectionQuestionMappingAssociatesList.add(v_GetSectionQuestionMappingAssociates);
		when(v_GetSectionQuestionMappingAssociatesRepo.findByPsmIdAndCallConfigIdAndRole(Mockito.<Integer>any(),
				Mockito.<Integer>any(), Mockito.<String>any())).thenReturn(v_GetSectionQuestionMappingAssociatesList);

		// Act and Assert
		assertThrows(ECDException.class,
				() -> questionareServiceImpl.getQuesAndSecMapAssociateByProvider(1, "Ecd Call Type", "Role"));
		verify(callConfigurationRepo).getCallConfigurationByProviderAndCallType(isA(Integer.class),
				eq("Ecd Call Type"));
		verify(questionnaireValuesRepo).findByQuestionIdAndDeleted(isA(Integer.class), isA(Boolean.class));
		verify(v_GetSectionQuestionMappingAssociatesRepo).findByPsmIdAndCallConfigIdAndRole(isA(Integer.class),
				isA(Integer.class), eq("Role"));
	}

	/**
	 * Method under test:
	 * {@link QuestionareServiceImpl#getQuesAndSecMapAssociateByProvider(int, String, String)}
	 */
	@Test
	void testGetQuesAndSecMapAssociateByProvider5() {
		// Arrange
		CallConfiguration callConfiguration = new CallConfiguration();
		callConfiguration.setBaseLine("Base Line");
		callConfiguration.setCallConfigId(1L);
		callConfiguration.setCallType("Call Type");
		callConfiguration.setConfigId(1L);
		callConfiguration.setConfigTerms("Config Terms");
		callConfiguration.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
		callConfiguration.setCreatedDate(mock(Timestamp.class));
		callConfiguration.setDeleted(true);
		callConfiguration.setDisplayName("Display Name");
		callConfiguration.setEffectiveEndDate(mock(Timestamp.class));
		callConfiguration.setEffectiveStartDate(mock(Timestamp.class));
		callConfiguration.setLastModDate(mock(Timestamp.class));
		callConfiguration.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
		callConfiguration.setNextAttemptPeriod(1);
		callConfiguration.setNoOfAttempts(1);
		callConfiguration.setPsmId(1);
		callConfiguration.setTermRange(1);
		when(callConfigurationRepo.getCallConfigurationByProviderAndCallType(Mockito.<Integer>any(),
				Mockito.<String>any())).thenReturn(callConfiguration);

		QuestionnaireValues questionnaireValues = new QuestionnaireValues();
		questionnaireValues.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
		questionnaireValues.setCreatedDate(mock(Timestamp.class));
		questionnaireValues.setDeleted(true);
		questionnaireValues.setId(1);
		questionnaireValues.setLastModDate(mock(Timestamp.class));
		questionnaireValues.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
		questionnaireValues.setOptions(",");
		questionnaireValues.setPsmId(1);
		questionnaireValues.setQuestionId(1);
		questionnaireValues.setQuestionValuesWeightage(42);
		
		questionnaireValues.toString();
		
		ArrayList<QuestionnaireValues> questionnaireValuesList = new ArrayList<>();
		questionnaireValuesList.add(questionnaireValues);
		when(questionnaireValuesRepo.findByQuestionIdAndDeleted(Mockito.<Integer>any(), Mockito.<Boolean>any()))
				.thenReturn(questionnaireValuesList);

		V_GetSectionQuestionMappingAssociates v_GetSectionQuestionMappingAssociates = new V_GetSectionQuestionMappingAssociates();
		v_GetSectionQuestionMappingAssociates.setAnswerType("Answer Type");
		v_GetSectionQuestionMappingAssociates.setCallConfigId(1);
		v_GetSectionQuestionMappingAssociates.setCallSectionRank(1);
		v_GetSectionQuestionMappingAssociates.setDeleted(true);
		v_GetSectionQuestionMappingAssociates.setId(1);
		v_GetSectionQuestionMappingAssociates.setOptions(new ArrayList<>());
		v_GetSectionQuestionMappingAssociates.setOptionsArr(new String[] { "Options Arr" });
		v_GetSectionQuestionMappingAssociates.setParentAnswer(new ArrayList<>());
		v_GetSectionQuestionMappingAssociates.setParentAnswerDb("Parent Answer Db");
		v_GetSectionQuestionMappingAssociates.setParentQuestion(new String[] { "Parent Question" });
		v_GetSectionQuestionMappingAssociates.setParentQuestionDb("Parent Question Db");
		v_GetSectionQuestionMappingAssociates.setParentQuestionId(new int[] { 1, 2, 1, 2 });
		v_GetSectionQuestionMappingAssociates.setParentQuestionIdDb("Parent Question Id Db");
		v_GetSectionQuestionMappingAssociates.setPsmId(1);
		v_GetSectionQuestionMappingAssociates.setQuestion("Question");
		v_GetSectionQuestionMappingAssociates.setQuestionRank(1);
		v_GetSectionQuestionMappingAssociates.setQuestionType("Question Type");
		v_GetSectionQuestionMappingAssociates.setQuestionTypeID(1);
		v_GetSectionQuestionMappingAssociates.setQuestionid(1);
		v_GetSectionQuestionMappingAssociates.setRole("Role");
		v_GetSectionQuestionMappingAssociates.setSectionName("Section Name");
		v_GetSectionQuestionMappingAssociates.setSectionQuestionRank(1);
		v_GetSectionQuestionMappingAssociates.setSectionid(1);
		
		v_GetSectionQuestionMappingAssociates.toString();
		
		ArrayList<V_GetSectionQuestionMappingAssociates> v_GetSectionQuestionMappingAssociatesList = new ArrayList<>();
		v_GetSectionQuestionMappingAssociatesList.add(v_GetSectionQuestionMappingAssociates);
		when(v_GetSectionQuestionMappingAssociatesRepo.findByPsmIdAndCallConfigIdAndRole(Mockito.<Integer>any(),
				Mockito.<Integer>any(), Mockito.<String>any())).thenReturn(v_GetSectionQuestionMappingAssociatesList);

		// Act and Assert
		assertThrows(ECDException.class,
				() -> questionareServiceImpl.getQuesAndSecMapAssociateByProvider(1, "Ecd Call Type", "Role"));
		verify(callConfigurationRepo).getCallConfigurationByProviderAndCallType(isA(Integer.class),
				eq("Ecd Call Type"));
		verify(questionnaireValuesRepo).findByQuestionIdAndDeleted(isA(Integer.class), isA(Boolean.class));
		verify(v_GetSectionQuestionMappingAssociatesRepo).findByPsmIdAndCallConfigIdAndRole(isA(Integer.class),
				isA(Integer.class), eq("Role"));
	}

	/**
	 * Method under test:
	 * {@link QuestionareServiceImpl#getQuestionnairesAndSectionMappingDataBySectionId(int)}
	 */
	@Test
	void testGetQuestionnairesAndSectionMappingDataBySectionId() {
		// Arrange
		ArrayList<V_GetSectionQuestionMapping> v_GetSectionQuestionMappingList = new ArrayList<>();
		when(v_GetSectionQuestionMappingRepo.findBySectionid(Mockito.<Integer>any()))
				.thenReturn(v_GetSectionQuestionMappingList);

		// Act
		ResponseSectionQuestionnaireMappingDTO actualQuestionnairesAndSectionMappingDataBySectionId = questionareServiceImpl
				.getQuestionnairesAndSectionMappingDataBySectionId(1);

		// Assert
		verify(v_GetSectionQuestionMappingRepo).findBySectionid(isA(Integer.class));
		assertEquals(v_GetSectionQuestionMappingList,
				actualQuestionnairesAndSectionMappingDataBySectionId.getQuestionIds());
	}

	/**
	 * Method under test:
	 * {@link QuestionareServiceImpl#getQuestionnairesAndSectionMappingDataBySectionId(int)}
	 */
	@Test
	void testGetQuestionnairesAndSectionMappingDataBySectionId2() {
		// Arrange
		V_GetSectionQuestionMapping v_GetSectionQuestionMapping = new V_GetSectionQuestionMapping();
		v_GetSectionQuestionMapping.setDeleted(true);
		v_GetSectionQuestionMapping.setId(1);
		v_GetSectionQuestionMapping.setOptions(new ArrayList<>());
		v_GetSectionQuestionMapping.setOptionsArr(new String[] { "Options Arr" });
		v_GetSectionQuestionMapping.setPsmId(1);
		v_GetSectionQuestionMapping.setQuestion("Question");
		v_GetSectionQuestionMapping.setQuestionRank(1);
		v_GetSectionQuestionMapping.setQuestionType("Question Type");
		v_GetSectionQuestionMapping.setQuestionTypeID(1);
		v_GetSectionQuestionMapping.setQuestionid(1);
		v_GetSectionQuestionMapping.setRole("Role");
		v_GetSectionQuestionMapping.setRoles(new String[] { "Roles" });
		v_GetSectionQuestionMapping.setSectionName("Section Name");
		v_GetSectionQuestionMapping.setSectionQuestionRank(1);
		v_GetSectionQuestionMapping.setSectionid(1);
		
		v_GetSectionQuestionMapping.toString();

		ArrayList<V_GetSectionQuestionMapping> v_GetSectionQuestionMappingList = new ArrayList<>();
		v_GetSectionQuestionMappingList.add(v_GetSectionQuestionMapping);
		when(v_GetSectionQuestionMappingRepo.findBySectionid(Mockito.<Integer>any()))
				.thenReturn(v_GetSectionQuestionMappingList);

		// Act
		ResponseSectionQuestionnaireMappingDTO actualQuestionnairesAndSectionMappingDataBySectionId = questionareServiceImpl
				.getQuestionnairesAndSectionMappingDataBySectionId(1);

		// Assert
		verify(v_GetSectionQuestionMappingRepo).findBySectionid(isA(Integer.class));
		assertEquals("Section Name", actualQuestionnairesAndSectionMappingDataBySectionId.getSectionName());
		assertEquals(1, actualQuestionnairesAndSectionMappingDataBySectionId.getPsmId());
		assertEquals(1, actualQuestionnairesAndSectionMappingDataBySectionId.getSectionId());
		assertEquals(1, actualQuestionnairesAndSectionMappingDataBySectionId.getQuestionIds().size());
	}

	/**
	 * Method under test:
	 * {@link QuestionareServiceImpl#getQuestionnairesAndSectionMappingDataBySectionId(int)}
	 */
	@Test
	void testGetQuestionnairesAndSectionMappingDataBySectionId3() {
		// Arrange, Act and Assert
		assertThrows(ECDException.class,
				() -> questionareServiceImpl.getQuestionnairesAndSectionMappingDataBySectionId(0));
	}

	/**
	 * Method under test:
	 * {@link QuestionareServiceImpl#getQuestionnairesAndSectionMappingDataBySectionId(int)}
	 */
	@Test
	void testGetQuestionnairesAndSectionMappingDataBySectionId4() {
		// Arrange
		when(v_GetSectionQuestionMappingRepo.findBySectionid(Mockito.<Integer>any()))
				.thenThrow(new ECDException("Exception Msg"));

		// Act and Assert
		assertThrows(ECDException.class,
				() -> questionareServiceImpl.getQuestionnairesAndSectionMappingDataBySectionId(1));
		verify(v_GetSectionQuestionMappingRepo).findBySectionid(isA(Integer.class));
	}

	/**
	 * Method under test:
	 * {@link QuestionareServiceImpl#editQuestionnaireSectionMap(SectionQuestionnaireMapping)}
	 */
	@Test
	void testEditQuestionnaireSectionMap() {
		// Arrange
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
		
		when(sectionQuestionnaireMappingRepo.save(Mockito.<SectionQuestionnaireMapping>any()))
				.thenReturn(sectionQuestionnaireMapping);

		SectionQuestionnaireMapping sectionQuestionnaireMapping2 = new SectionQuestionnaireMapping();
		sectionQuestionnaireMapping2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
		sectionQuestionnaireMapping2.setCreatedDate(mock(Timestamp.class));
		sectionQuestionnaireMapping2.setDeleted(true);
		sectionQuestionnaireMapping2.setId(1);
		sectionQuestionnaireMapping2.setLastModDate(mock(Timestamp.class));
		sectionQuestionnaireMapping2.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
		sectionQuestionnaireMapping2.setPsmId(1);
		sectionQuestionnaireMapping2.setQuestionId(1);
		sectionQuestionnaireMapping2.setRank(1);
		sectionQuestionnaireMapping2.setRole("Role");
		sectionQuestionnaireMapping2.setRoles(new String[] { "Roles" });
		sectionQuestionnaireMapping2.setSectionId(1);
		sectionQuestionnaireMapping2.setSectionName("Section Name");

		// Act
		String actualEditQuestionnaireSectionMapResult = questionareServiceImpl
				.editQuestionnaireSectionMap(sectionQuestionnaireMapping2);

		// Assert
		verify(sectionQuestionnaireMappingRepo).save(isA(SectionQuestionnaireMapping.class));
		assertEquals("Roles", sectionQuestionnaireMapping2.getRole());
		assertEquals("{\"response\":\"updated successfully\"}", actualEditQuestionnaireSectionMapResult);
	}

	/**
	 * Method under test:
	 * {@link QuestionareServiceImpl#editQuestionnaireSectionMap(SectionQuestionnaireMapping)}
	 */
	@Test
	void testEditQuestionnaireSectionMap2() {
		// Arrange
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
		when(sectionQuestionnaireMappingRepo.save(Mockito.<SectionQuestionnaireMapping>any()))
				.thenReturn(sectionQuestionnaireMapping);
		SectionQuestionnaireMapping sectionQuestionnaireMapping2 = mock(SectionQuestionnaireMapping.class);
		when(sectionQuestionnaireMapping2.getId()).thenReturn(1);
		when(sectionQuestionnaireMapping2.getRoles()).thenReturn(new String[] {});
		doNothing().when(sectionQuestionnaireMapping2).setCreatedBy(Mockito.<String>any());
		doNothing().when(sectionQuestionnaireMapping2).setCreatedDate(Mockito.<Timestamp>any());
		doNothing().when(sectionQuestionnaireMapping2).setDeleted(Mockito.<Boolean>any());
		doNothing().when(sectionQuestionnaireMapping2).setId(Mockito.<Integer>any());
		doNothing().when(sectionQuestionnaireMapping2).setLastModDate(Mockito.<Timestamp>any());
		doNothing().when(sectionQuestionnaireMapping2).setModifiedBy(Mockito.<String>any());
		doNothing().when(sectionQuestionnaireMapping2).setPsmId(Mockito.<Integer>any());
		doNothing().when(sectionQuestionnaireMapping2).setQuestionId(Mockito.<Integer>any());
		doNothing().when(sectionQuestionnaireMapping2).setRank(Mockito.<Integer>any());
		doNothing().when(sectionQuestionnaireMapping2).setRole(Mockito.<String>any());
		doNothing().when(sectionQuestionnaireMapping2).setRoles(Mockito.<String[]>any());
		doNothing().when(sectionQuestionnaireMapping2).setSectionId(Mockito.<Integer>any());
		doNothing().when(sectionQuestionnaireMapping2).setSectionName(Mockito.<String>any());
		sectionQuestionnaireMapping2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
		sectionQuestionnaireMapping2.setCreatedDate(mock(Timestamp.class));
		sectionQuestionnaireMapping2.setDeleted(true);
		sectionQuestionnaireMapping2.setId(1);
		sectionQuestionnaireMapping2.setLastModDate(mock(Timestamp.class));
		sectionQuestionnaireMapping2.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
		sectionQuestionnaireMapping2.setPsmId(1);
		sectionQuestionnaireMapping2.setQuestionId(1);
		sectionQuestionnaireMapping2.setRank(1);
		sectionQuestionnaireMapping2.setRole("Role");
		sectionQuestionnaireMapping2.setRoles(new String[] { "Roles" });
		sectionQuestionnaireMapping2.setSectionId(1);
		sectionQuestionnaireMapping2.setSectionName("Section Name");

		// Act
		String actualEditQuestionnaireSectionMapResult = questionareServiceImpl
				.editQuestionnaireSectionMap(sectionQuestionnaireMapping2);

		// Assert
		verify(sectionQuestionnaireMapping2).getId();
		verify(sectionQuestionnaireMapping2, atLeast(1)).getRoles();
		verify(sectionQuestionnaireMapping2).setCreatedBy(eq("Jan 1, 2020 8:00am GMT+0100"));
		verify(sectionQuestionnaireMapping2).setCreatedDate(isA(Timestamp.class));
		verify(sectionQuestionnaireMapping2).setDeleted(isA(Boolean.class));
		verify(sectionQuestionnaireMapping2).setId(isA(Integer.class));
		verify(sectionQuestionnaireMapping2).setLastModDate(isA(Timestamp.class));
		verify(sectionQuestionnaireMapping2).setModifiedBy(eq("Jan 1, 2020 9:00am GMT+0100"));
		verify(sectionQuestionnaireMapping2).setPsmId(isA(Integer.class));
		verify(sectionQuestionnaireMapping2).setQuestionId(isA(Integer.class));
		verify(sectionQuestionnaireMapping2).setRank(isA(Integer.class));
		verify(sectionQuestionnaireMapping2).setRole(eq("Role"));
		verify(sectionQuestionnaireMapping2).setRoles(isA(String[].class));
		verify(sectionQuestionnaireMapping2).setSectionId(isA(Integer.class));
		verify(sectionQuestionnaireMapping2).setSectionName(eq("Section Name"));
		verify(sectionQuestionnaireMappingRepo).save(isA(SectionQuestionnaireMapping.class));
		assertEquals("{\"response\":\"updated successfully\"}", actualEditQuestionnaireSectionMapResult);
	}

	/**
	 * Method under test:
	 * {@link QuestionareServiceImpl#getUnMappedQuestionnaires(int, int)}
	 */
	@Test
	void testGetUnMappedQuestionnaires() {
		// Arrange
		ArrayList<Questionnaire> questionnaireList = new ArrayList<>();
		when(ecdQuestionnaireRepo.getUnMappedQuestionnaires(Mockito.<Integer>any(), Mockito.<Integer>any()))
				.thenReturn(questionnaireList);

		// Act
		List<Questionnaire> actualUnMappedQuestionnaires = questionareServiceImpl.getUnMappedQuestionnaires(1, 1);

		// Assert
		verify(ecdQuestionnaireRepo).getUnMappedQuestionnaires(isA(Integer.class), isA(Integer.class));
		assertTrue(actualUnMappedQuestionnaires.isEmpty());
		assertSame(questionnaireList, actualUnMappedQuestionnaires);
	}

	/**
	 * Method under test:
	 * {@link QuestionareServiceImpl#getUnMappedQuestionnaires(int, int)}
	 */
	@Test
	void testGetUnMappedQuestionnaires2() {
		// Arrange
		when(ecdQuestionnaireRepo.getUnMappedQuestionnaires(Mockito.<Integer>any(), Mockito.<Integer>any()))
				.thenThrow(new ECDException("Exception Msg"));

		// Act and Assert
		assertThrows(ECDException.class, () -> questionareServiceImpl.getUnMappedQuestionnaires(1, 1));
		verify(ecdQuestionnaireRepo).getUnMappedQuestionnaires(isA(Integer.class), isA(Integer.class));
	}

	/**
	 * Method under test:
	 * {@link QuestionareServiceImpl#createQuestionnairesMap(MapQuestion)}
	 */
	@Test
	void testCreateQuestionnairesMap() {
		// Arrange
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
		
		when(mapQuestionRepo.save(Mockito.<MapQuestion>any())).thenReturn(mapQuestion);

		MapQuestion questionnaireMap = new MapQuestion();
		questionnaireMap.setAnswer(new String[] { "Answer" });
		questionnaireMap.setAnswerDb("Answer Db");
		questionnaireMap.setChildQuestionId(1);
		questionnaireMap.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
		questionnaireMap.setCreatedDate(mock(Timestamp.class));
		questionnaireMap.setDeleted(true);
		questionnaireMap.setId(1);
		questionnaireMap.setLastModDate(mock(Timestamp.class));
		questionnaireMap.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
		questionnaireMap.setParentQuestionId(1);
		questionnaireMap.setPsmId(1);

		// Act
		String actualCreateQuestionnairesMapResult = questionareServiceImpl.createQuestionnairesMap(questionnaireMap);

		// Assert
		verify(mapQuestionRepo).save(isA(MapQuestion.class));
		assertEquals("{\"response\":\"created successfully\"}", actualCreateQuestionnairesMapResult);
	}

	/**
	 * Method under test:
	 * {@link QuestionareServiceImpl#createQuestionnairesMap(MapQuestion)}
	 */
	@Test
	void testCreateQuestionnairesMap2() {
		// Arrange
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
		when(mapQuestionRepo.save(Mockito.<MapQuestion>any())).thenReturn(mapQuestion);
		MapQuestion questionnaireMap = mock(MapQuestion.class);
		when(questionnaireMap.getChildQuestionId()).thenReturn(1);
		when(questionnaireMap.getParentQuestionId()).thenReturn(1);
		when(questionnaireMap.getPsmId()).thenReturn(1);
		when(questionnaireMap.getCreatedBy()).thenReturn("Jan 1, 2020 8:00am GMT+0100");
		when(questionnaireMap.getAnswer()).thenReturn(new String[] {});
		doNothing().when(questionnaireMap).setAnswer(Mockito.<String[]>any());
		doNothing().when(questionnaireMap).setAnswerDb(Mockito.<String>any());
		doNothing().when(questionnaireMap).setChildQuestionId(anyInt());
		doNothing().when(questionnaireMap).setCreatedBy(Mockito.<String>any());
		doNothing().when(questionnaireMap).setCreatedDate(Mockito.<Timestamp>any());
		doNothing().when(questionnaireMap).setDeleted(Mockito.<Boolean>any());
		doNothing().when(questionnaireMap).setId(Mockito.<Integer>any());
		doNothing().when(questionnaireMap).setLastModDate(Mockito.<Timestamp>any());
		doNothing().when(questionnaireMap).setModifiedBy(Mockito.<String>any());
		doNothing().when(questionnaireMap).setParentQuestionId(anyInt());
		doNothing().when(questionnaireMap).setPsmId(anyInt());
		questionnaireMap.setAnswer(new String[] { "Answer" });
		questionnaireMap.setAnswerDb("Answer Db");
		questionnaireMap.setChildQuestionId(1);
		questionnaireMap.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
		questionnaireMap.setCreatedDate(mock(Timestamp.class));
		questionnaireMap.setDeleted(true);
		questionnaireMap.setId(1);
		questionnaireMap.setLastModDate(mock(Timestamp.class));
		questionnaireMap.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
		questionnaireMap.setParentQuestionId(1);
		questionnaireMap.setPsmId(1);

		// Act
		String actualCreateQuestionnairesMapResult = questionareServiceImpl.createQuestionnairesMap(questionnaireMap);

		// Assert
		verify(questionnaireMap, atLeast(1)).getAnswer();
		verify(questionnaireMap).getChildQuestionId();
		verify(questionnaireMap).getCreatedBy();
		verify(questionnaireMap).getParentQuestionId();
		verify(questionnaireMap).getPsmId();
		verify(questionnaireMap).setAnswer(isA(String[].class));
		verify(questionnaireMap).setAnswerDb(eq("Answer Db"));
		verify(questionnaireMap).setChildQuestionId(eq(1));
		verify(questionnaireMap).setCreatedBy(eq("Jan 1, 2020 8:00am GMT+0100"));
		verify(questionnaireMap).setCreatedDate(isA(Timestamp.class));
		verify(questionnaireMap).setDeleted(isA(Boolean.class));
		verify(questionnaireMap).setId(isA(Integer.class));
		verify(questionnaireMap).setLastModDate(isA(Timestamp.class));
		verify(questionnaireMap).setModifiedBy(eq("Jan 1, 2020 9:00am GMT+0100"));
		verify(questionnaireMap).setParentQuestionId(eq(1));
		verify(questionnaireMap).setPsmId(eq(1));
		verify(mapQuestionRepo).save(isA(MapQuestion.class));
		assertEquals("{\"response\":\"created successfully\"}", actualCreateQuestionnairesMapResult);
	}

	/**
	 * Method under test:
	 * {@link QuestionareServiceImpl#editQuestionnairesMap(MapQuestion)}
	 */
	@Test
	void testEditQuestionnairesMap() {
		// Arrange
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
		when(mapQuestionRepo.save(Mockito.<MapQuestion>any())).thenReturn(mapQuestion);

		MapQuestion editMapQuestion = new MapQuestion();
		editMapQuestion.setAnswer(new String[] { "Answer" });
		editMapQuestion.setAnswerDb("Answer Db");
		editMapQuestion.setChildQuestionId(1);
		editMapQuestion.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
		editMapQuestion.setCreatedDate(mock(Timestamp.class));
		editMapQuestion.setDeleted(true);
		editMapQuestion.setId(1);
		editMapQuestion.setLastModDate(mock(Timestamp.class));
		editMapQuestion.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
		editMapQuestion.setParentQuestionId(1);
		editMapQuestion.setPsmId(1);
		
		editMapQuestion.toString();

		// Act
		String actualEditQuestionnairesMapResult = questionareServiceImpl.editQuestionnairesMap(editMapQuestion);

		// Assert
		verify(mapQuestionRepo).save(isA(MapQuestion.class));
		assertEquals("Answer", editMapQuestion.getAnswerDb());
		assertEquals("{\"response\":\"Questionnaire Map Updated Successfully\"}", actualEditQuestionnairesMapResult);
	}

	/**
	 * Method under test:
	 * {@link QuestionareServiceImpl#editQuestionnairesMap(MapQuestion)}
	 */
	@Test
	void testEditQuestionnairesMap2() {
		// Arrange
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
		when(mapQuestionRepo.save(Mockito.<MapQuestion>any())).thenReturn(mapQuestion);
		MapQuestion editMapQuestion = mock(MapQuestion.class);
		when(editMapQuestion.getId()).thenReturn(1);
		when(editMapQuestion.getAnswer()).thenReturn(new String[] {});
		doNothing().when(editMapQuestion).setAnswer(Mockito.<String[]>any());
		doNothing().when(editMapQuestion).setAnswerDb(Mockito.<String>any());
		doNothing().when(editMapQuestion).setChildQuestionId(anyInt());
		doNothing().when(editMapQuestion).setCreatedBy(Mockito.<String>any());
		doNothing().when(editMapQuestion).setCreatedDate(Mockito.<Timestamp>any());
		doNothing().when(editMapQuestion).setDeleted(Mockito.<Boolean>any());
		doNothing().when(editMapQuestion).setId(Mockito.<Integer>any());
		doNothing().when(editMapQuestion).setLastModDate(Mockito.<Timestamp>any());
		doNothing().when(editMapQuestion).setModifiedBy(Mockito.<String>any());
		doNothing().when(editMapQuestion).setParentQuestionId(anyInt());
		doNothing().when(editMapQuestion).setPsmId(anyInt());
		editMapQuestion.setAnswer(new String[] { "Answer" });
		editMapQuestion.setAnswerDb("Answer Db");
		editMapQuestion.setChildQuestionId(1);
		editMapQuestion.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
		editMapQuestion.setCreatedDate(mock(Timestamp.class));
		editMapQuestion.setDeleted(true);
		editMapQuestion.setId(1);
		editMapQuestion.setLastModDate(mock(Timestamp.class));
		editMapQuestion.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
		editMapQuestion.setParentQuestionId(1);
		editMapQuestion.setPsmId(1);

		// Act
		String actualEditQuestionnairesMapResult = questionareServiceImpl.editQuestionnairesMap(editMapQuestion);

		// Assert
		verify(editMapQuestion, atLeast(1)).getAnswer();
		verify(editMapQuestion).getId();
		verify(editMapQuestion).setAnswer(isA(String[].class));
		verify(editMapQuestion).setAnswerDb(eq("Answer Db"));
		verify(editMapQuestion).setChildQuestionId(eq(1));
		verify(editMapQuestion).setCreatedBy(eq("Jan 1, 2020 8:00am GMT+0100"));
		verify(editMapQuestion).setCreatedDate(isA(Timestamp.class));
		verify(editMapQuestion).setDeleted(isA(Boolean.class));
		verify(editMapQuestion).setId(isA(Integer.class));
		verify(editMapQuestion).setLastModDate(isA(Timestamp.class));
		verify(editMapQuestion).setModifiedBy(eq("Jan 1, 2020 9:00am GMT+0100"));
		verify(editMapQuestion).setParentQuestionId(eq(1));
		verify(editMapQuestion).setPsmId(eq(1));
		verify(mapQuestionRepo).save(isA(MapQuestion.class));
		assertEquals("{\"response\":\"Questionnaire Map Updated Successfully\"}", actualEditQuestionnairesMapResult);
	}

	/**
	 * Method under test:
	 * {@link QuestionareServiceImpl#getMappedParentChildQuestionnaire(int)}
	 */
	@Test
	void testGetMappedParentChildQuestionnaire() {
		// Arrange
		when(mapQuestionRepo.getMappedParentChildQuestionnaire(Mockito.<Integer>any())).thenReturn(new ArrayList<>());

		// Act
		List<ECDMapQuestions> actualMappedParentChildQuestionnaire = questionareServiceImpl
				.getMappedParentChildQuestionnaire(1);

		// Assert
		verify(mapQuestionRepo).getMappedParentChildQuestionnaire(isA(Integer.class));
		assertTrue(actualMappedParentChildQuestionnaire.isEmpty());
	}

	/**
	 * Method under test:
	 * {@link QuestionareServiceImpl#getMappedParentChildQuestionnaire(int)}
	 */
	@Test
	void testGetMappedParentChildQuestionnaire2() {
		// Arrange
		ArrayList<String[]> stringArrayList = new ArrayList<>();
		stringArrayList.add(new String[] { "foo" });
		when(mapQuestionRepo.getMappedParentChildQuestionnaire(Mockito.<Integer>any())).thenReturn(stringArrayList);

		// Act and Assert
		assertThrows(ECDException.class, () -> questionareServiceImpl.getMappedParentChildQuestionnaire(1));
		verify(mapQuestionRepo).getMappedParentChildQuestionnaire(isA(Integer.class));
	}

	/**
	 * Method under test:
	 * {@link QuestionareServiceImpl#getMappedParentChildQuestionnaire(int)}
	 */
	@Test
	void testGetMappedParentChildQuestionnaire3() {
		// Arrange
		when(mapQuestionRepo.getMappedParentChildQuestionnaire(Mockito.<Integer>any()))
				.thenThrow(new ECDException("Exception Msg"));

		// Act and Assert
		assertThrows(ECDException.class, () -> questionareServiceImpl.getMappedParentChildQuestionnaire(1));
		verify(mapQuestionRepo).getMappedParentChildQuestionnaire(isA(Integer.class));
	}
}
