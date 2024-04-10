package com.iemr.ecd.service.quality;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.iemr.ecd.dao.GradeConfiguration;
import com.iemr.ecd.dao.QualityAuditorRating;
import com.iemr.ecd.dao.V_get_Qualityaudit_SectionQuestionaireValues;
import com.iemr.ecd.dto.BeneficiaryCasesheetDTO;
import com.iemr.ecd.dto.QualityAuditorWorklistDatewiseRequestDTO;
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

@ContextConfiguration(classes = { QualityAuditImpl.class, BeneficiaryCasesheetDTO.class,
		QualityAuditorWorklistRequestDTO.class, QualityAuditorWorklistDatewiseRequestDTO.class })
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class QualityAuditImplDiffblueTest {
	@Autowired
	private QualityAuditorWorklistDatewiseRequestDTO qualityAuditorWorklistDatewiseRequestDTO;

	@Autowired
	private QualityAuditorWorklistRequestDTO qualityAuditorWorklistRequestDTO;

	@Autowired
	private BeneficiaryCasesheetDTO beneficiaryCasesheetDTO;

	@MockBean
	private AgentQualityAuditorMapRepo agentQualityAuditorMapRepo;

	@MockBean
	private GradeConfigurationRepo gradeConfigurationRepo;

	@Autowired
	private QualityAuditImpl qualityAuditImpl;

	@MockBean
	private QualityAuditorCallResponseRepo qualityAuditorCallResponseRepo;

	@MockBean
	private QualityAuditorRatingRepo qualityAuditorRatingRepo;

	@MockBean
	private SampleSelectionConfigurationRepo sampleSelectionConfigurationRepo;

	@MockBean
	private T_benCallRepo t_benCallRepo;

	@MockBean
	private V_QualityAuditorCallResponseRepo v_QualityAuditorCallResponseRepo;

	@MockBean
	private V_get_Qualityaudit_SectionQuestionaireValuesRepo v_get_Qualityaudit_SectionQuestionaireValuesRepo;

	/**
	 * Method under test:
	 * {@link QualityAuditImpl#getQualityAuditorWorklist(QualityAuditorWorklistRequestDTO)}
	 */
	@Test
	void testGetQualityAuditorWorklist() {
		// Arrange, Act and Assert
		assertTrue(qualityAuditImpl.getQualityAuditorWorklist(qualityAuditorWorklistRequestDTO).isEmpty());
	}

	/**
	 * Method under test:
	 * {@link QualityAuditImpl#getWorkListData(QualityAuditorWorklistRequestDTO)}
	 */
	@Test
	void testGetWorkListData() {
		// Arrange
		when(agentQualityAuditorMapRepo.getQualityAuditorWorklist(Mockito.<Timestamp>any(), Mockito.<Timestamp>any(),
				Mockito.<Integer>any(), Mockito.<Integer>any(), Mockito.<Integer>any(), Mockito.<Integer>any(),
				Mockito.<Boolean>any(), Mockito.<Integer>any(), Mockito.<Timestamp>any(), Mockito.<Timestamp>any()))
				.thenReturn(new ArrayList<>());

		// Act
		List<QualityAuditorWorklistResponseDTO> actualWorkListData = qualityAuditImpl
				.getWorkListData(qualityAuditorWorklistRequestDTO);

		// Assert
		verify(agentQualityAuditorMapRepo).getQualityAuditorWorklist(isNull(), isNull(), isNull(), isNull(), isNull(),
				isNull(), isNull(), isNull(), isNull(), isNull());
		assertTrue(actualWorkListData.isEmpty());
	}

	/**
	 * Method under test:
	 * {@link QualityAuditImpl#getWorkListData(QualityAuditorWorklistRequestDTO)}
	 */
	@Test
	void testGetWorkListData2() {
		// Arrange
		when(agentQualityAuditorMapRepo.getQualityAuditorWorklist(Mockito.<Timestamp>any(), Mockito.<Timestamp>any(),
				Mockito.<Integer>any(), Mockito.<Integer>any(), Mockito.<Integer>any(), Mockito.<Integer>any(),
				Mockito.<Boolean>any(), Mockito.<Integer>any(), Mockito.<Timestamp>any(), Mockito.<Timestamp>any()))
				.thenThrow(new ECDException("Exception Msg"));

		// Act and Assert
		assertThrows(ECDException.class, () -> qualityAuditImpl.getWorkListData(qualityAuditorWorklistRequestDTO));
		verify(agentQualityAuditorMapRepo).getQualityAuditorWorklist(isNull(), isNull(), isNull(), isNull(), isNull(),
				isNull(), isNull(), isNull(), isNull(), isNull());
	}

	/**
	 * Method under test:
	 * {@link QualityAuditImpl#getQualityAuditorWorklistDatewise(QualityAuditorWorklistDatewiseRequestDTO)}
	 */
	@Test
	void testGetQualityAuditorWorklistDatewise() {
		// Arrange, Act and Assert
		assertThrows(ECDException.class,
				() -> qualityAuditImpl.getQualityAuditorWorklistDatewise(qualityAuditorWorklistDatewiseRequestDTO));
	}

	/**
	 * Method under test:
	 * {@link QualityAuditImpl#getQuestionSectionForCallRatings(Integer)}
	 */
	@Test
	void testGetQuestionSectionForCallRatings() {
		// Arrange
		when(v_get_Qualityaudit_SectionQuestionaireValuesRepo.findByPsmIdOrderByQuestionId(Mockito.<Integer>any()))
				.thenReturn(new ArrayList<>());

		// Act
		List<ResponseCallAuditSectionQuestionMapDTO> actualQuestionSectionForCallRatings = qualityAuditImpl
				.getQuestionSectionForCallRatings(1);

		// Assert
		verify(v_get_Qualityaudit_SectionQuestionaireValuesRepo).findByPsmIdOrderByQuestionId(isA(Integer.class));
		assertTrue(actualQuestionSectionForCallRatings.isEmpty());
	}

	/**
	 * Method under test:
	 * {@link QualityAuditImpl#getQuestionSectionForCallRatings(Integer)}
	 */
	@Test
	void testGetQuestionSectionForCallRatings2() {
		// Arrange
		V_get_Qualityaudit_SectionQuestionaireValues v_get_Qualityaudit_SectionQuestionaireValues = new V_get_Qualityaudit_SectionQuestionaireValues();
		v_get_Qualityaudit_SectionQuestionaireValues.setId("42");
		v_get_Qualityaudit_SectionQuestionaireValues.setIsFatalQues(true);
		v_get_Qualityaudit_SectionQuestionaireValues.setOptionId(1);
		v_get_Qualityaudit_SectionQuestionaireValues.setPsmId(1);
		v_get_Qualityaudit_SectionQuestionaireValues.setQuestion("Question");
		v_get_Qualityaudit_SectionQuestionaireValues.setQuestionId(1);
		v_get_Qualityaudit_SectionQuestionaireValues.setQuestionRank(1);
		v_get_Qualityaudit_SectionQuestionaireValues.setQuestionValues("42");
		v_get_Qualityaudit_SectionQuestionaireValues.setScore(3);
		v_get_Qualityaudit_SectionQuestionaireValues.setSectionId(1);
		v_get_Qualityaudit_SectionQuestionaireValues.setSectionName("Section Name");
		v_get_Qualityaudit_SectionQuestionaireValues.setSectionRank(1);

		ArrayList<V_get_Qualityaudit_SectionQuestionaireValues> v_get_Qualityaudit_SectionQuestionaireValuesList = new ArrayList<>();
		v_get_Qualityaudit_SectionQuestionaireValuesList.add(v_get_Qualityaudit_SectionQuestionaireValues);
		when(v_get_Qualityaudit_SectionQuestionaireValuesRepo.findByPsmIdOrderByQuestionId(Mockito.<Integer>any()))
				.thenReturn(v_get_Qualityaudit_SectionQuestionaireValuesList);

		// Act
		List<ResponseCallAuditSectionQuestionMapDTO> actualQuestionSectionForCallRatings = qualityAuditImpl
				.getQuestionSectionForCallRatings(1);

		// Assert
		verify(v_get_Qualityaudit_SectionQuestionaireValuesRepo).findByPsmIdOrderByQuestionId(isA(Integer.class));
		assertEquals(1, actualQuestionSectionForCallRatings.size());
	}

	/**
	 * Method under test:
	 * {@link QualityAuditImpl#getQuestionSectionForCallRatings(Integer)}
	 */
	@Test
	void testGetQuestionSectionForCallRatings3() {
		// Arrange
		V_get_Qualityaudit_SectionQuestionaireValues v_get_Qualityaudit_SectionQuestionaireValues = new V_get_Qualityaudit_SectionQuestionaireValues();
		v_get_Qualityaudit_SectionQuestionaireValues.setId("42");
		v_get_Qualityaudit_SectionQuestionaireValues.setIsFatalQues(true);
		v_get_Qualityaudit_SectionQuestionaireValues.setOptionId(1);
		v_get_Qualityaudit_SectionQuestionaireValues.setPsmId(1);
		v_get_Qualityaudit_SectionQuestionaireValues.setQuestion("Question");
		v_get_Qualityaudit_SectionQuestionaireValues.setQuestionId(1);
		v_get_Qualityaudit_SectionQuestionaireValues.setQuestionRank(1);
		v_get_Qualityaudit_SectionQuestionaireValues.setQuestionValues("42");
		v_get_Qualityaudit_SectionQuestionaireValues.setScore(3);
		v_get_Qualityaudit_SectionQuestionaireValues.setSectionId(1);
		v_get_Qualityaudit_SectionQuestionaireValues.setSectionName("Section Name");
		v_get_Qualityaudit_SectionQuestionaireValues.setSectionRank(1);

		V_get_Qualityaudit_SectionQuestionaireValues v_get_Qualityaudit_SectionQuestionaireValues2 = new V_get_Qualityaudit_SectionQuestionaireValues();
		v_get_Qualityaudit_SectionQuestionaireValues2.setId("Id");
		v_get_Qualityaudit_SectionQuestionaireValues2.setIsFatalQues(false);
		v_get_Qualityaudit_SectionQuestionaireValues2.setOptionId(2);
		v_get_Qualityaudit_SectionQuestionaireValues2.setPsmId(2);
		v_get_Qualityaudit_SectionQuestionaireValues2
				.setQuestion("com.iemr.ecd.dao.V_get_Qualityaudit_SectionQuestionaireValues");
		v_get_Qualityaudit_SectionQuestionaireValues2.setQuestionId(2);
		v_get_Qualityaudit_SectionQuestionaireValues2.setQuestionRank(0);
		v_get_Qualityaudit_SectionQuestionaireValues2.setQuestionValues("Question Values");
		v_get_Qualityaudit_SectionQuestionaireValues2.setScore(1);
		v_get_Qualityaudit_SectionQuestionaireValues2.setSectionId(2);
		v_get_Qualityaudit_SectionQuestionaireValues2
				.setSectionName("com.iemr.ecd.dao.V_get_Qualityaudit_SectionQuestionaireValues");
		v_get_Qualityaudit_SectionQuestionaireValues2.setSectionRank(0);

		ArrayList<V_get_Qualityaudit_SectionQuestionaireValues> v_get_Qualityaudit_SectionQuestionaireValuesList = new ArrayList<>();
		v_get_Qualityaudit_SectionQuestionaireValuesList.add(v_get_Qualityaudit_SectionQuestionaireValues2);
		v_get_Qualityaudit_SectionQuestionaireValuesList.add(v_get_Qualityaudit_SectionQuestionaireValues);
		when(v_get_Qualityaudit_SectionQuestionaireValuesRepo.findByPsmIdOrderByQuestionId(Mockito.<Integer>any()))
				.thenReturn(v_get_Qualityaudit_SectionQuestionaireValuesList);

		// Act
		List<ResponseCallAuditSectionQuestionMapDTO> actualQuestionSectionForCallRatings = qualityAuditImpl
				.getQuestionSectionForCallRatings(1);

		// Assert
		verify(v_get_Qualityaudit_SectionQuestionaireValuesRepo).findByPsmIdOrderByQuestionId(isA(Integer.class));
		assertEquals(2, actualQuestionSectionForCallRatings.size());
	}

	/**
	 * Method under test:
	 * {@link QualityAuditImpl#getQuestionSectionForCallRatings(Integer)}
	 */
	@Test
	void testGetQuestionSectionForCallRatings4() {
		// Arrange
		when(v_get_Qualityaudit_SectionQuestionaireValuesRepo.findByPsmIdOrderByQuestionId(Mockito.<Integer>any()))
				.thenThrow(new ECDException("Exception Msg"));

		// Act and Assert
		assertThrows(ECDException.class, () -> qualityAuditImpl.getQuestionSectionForCallRatings(1));
		verify(v_get_Qualityaudit_SectionQuestionaireValuesRepo).findByPsmIdOrderByQuestionId(isA(Integer.class));
	}

	/**
	 * Method under test:
	 * {@link QualityAuditImpl#getQuestionSectionForCallRatings(Integer)}
	 */
	@Test
	void testGetQuestionSectionForCallRatings5() {
		// Arrange
		V_get_Qualityaudit_SectionQuestionaireValues v_get_Qualityaudit_SectionQuestionaireValues = mock(
				V_get_Qualityaudit_SectionQuestionaireValues.class);
		when(v_get_Qualityaudit_SectionQuestionaireValues.getOptionId()).thenReturn(1);
		when(v_get_Qualityaudit_SectionQuestionaireValues.getQuestionId()).thenReturn(1);
		when(v_get_Qualityaudit_SectionQuestionaireValues.getScore()).thenReturn(3);
		when(v_get_Qualityaudit_SectionQuestionaireValues.getQuestionValues()).thenReturn("42");
		doNothing().when(v_get_Qualityaudit_SectionQuestionaireValues).setId(Mockito.<String>any());
		doNothing().when(v_get_Qualityaudit_SectionQuestionaireValues).setIsFatalQues(Mockito.<Boolean>any());
		doNothing().when(v_get_Qualityaudit_SectionQuestionaireValues).setOptionId(Mockito.<Integer>any());
		doNothing().when(v_get_Qualityaudit_SectionQuestionaireValues).setPsmId(Mockito.<Integer>any());
		doNothing().when(v_get_Qualityaudit_SectionQuestionaireValues).setQuestion(Mockito.<String>any());
		doNothing().when(v_get_Qualityaudit_SectionQuestionaireValues).setQuestionId(Mockito.<Integer>any());
		doNothing().when(v_get_Qualityaudit_SectionQuestionaireValues).setQuestionRank(Mockito.<Integer>any());
		doNothing().when(v_get_Qualityaudit_SectionQuestionaireValues).setQuestionValues(Mockito.<String>any());
		doNothing().when(v_get_Qualityaudit_SectionQuestionaireValues).setScore(Mockito.<Integer>any());
		doNothing().when(v_get_Qualityaudit_SectionQuestionaireValues).setSectionId(Mockito.<Integer>any());
		doNothing().when(v_get_Qualityaudit_SectionQuestionaireValues).setSectionName(Mockito.<String>any());
		doNothing().when(v_get_Qualityaudit_SectionQuestionaireValues).setSectionRank(Mockito.<Integer>any());
		v_get_Qualityaudit_SectionQuestionaireValues.setId("42");
		v_get_Qualityaudit_SectionQuestionaireValues.setIsFatalQues(true);
		v_get_Qualityaudit_SectionQuestionaireValues.setOptionId(1);
		v_get_Qualityaudit_SectionQuestionaireValues.setPsmId(1);
		v_get_Qualityaudit_SectionQuestionaireValues.setQuestion("Question");
		v_get_Qualityaudit_SectionQuestionaireValues.setQuestionId(1);
		v_get_Qualityaudit_SectionQuestionaireValues.setQuestionRank(1);
		v_get_Qualityaudit_SectionQuestionaireValues.setQuestionValues("42");
		v_get_Qualityaudit_SectionQuestionaireValues.setScore(3);
		v_get_Qualityaudit_SectionQuestionaireValues.setSectionId(1);
		v_get_Qualityaudit_SectionQuestionaireValues.setSectionName("Section Name");
		v_get_Qualityaudit_SectionQuestionaireValues.setSectionRank(1);

		V_get_Qualityaudit_SectionQuestionaireValues v_get_Qualityaudit_SectionQuestionaireValues2 = new V_get_Qualityaudit_SectionQuestionaireValues();
		v_get_Qualityaudit_SectionQuestionaireValues2.setId("42");
		v_get_Qualityaudit_SectionQuestionaireValues2.setIsFatalQues(false);
		v_get_Qualityaudit_SectionQuestionaireValues2.setOptionId(1);
		v_get_Qualityaudit_SectionQuestionaireValues2.setPsmId(1);
		v_get_Qualityaudit_SectionQuestionaireValues2.setQuestion("Question");
		v_get_Qualityaudit_SectionQuestionaireValues2.setQuestionId(1);
		v_get_Qualityaudit_SectionQuestionaireValues2.setQuestionRank(1);
		v_get_Qualityaudit_SectionQuestionaireValues2.setQuestionValues("42");
		v_get_Qualityaudit_SectionQuestionaireValues2.setScore(3);
		v_get_Qualityaudit_SectionQuestionaireValues2.setSectionId(1);
		v_get_Qualityaudit_SectionQuestionaireValues2.setSectionName("Section Name");
		v_get_Qualityaudit_SectionQuestionaireValues2.setSectionRank(1);

		ArrayList<V_get_Qualityaudit_SectionQuestionaireValues> v_get_Qualityaudit_SectionQuestionaireValuesList = new ArrayList<>();
		v_get_Qualityaudit_SectionQuestionaireValuesList.add(v_get_Qualityaudit_SectionQuestionaireValues2);
		v_get_Qualityaudit_SectionQuestionaireValuesList.add(v_get_Qualityaudit_SectionQuestionaireValues);
		when(v_get_Qualityaudit_SectionQuestionaireValuesRepo.findByPsmIdOrderByQuestionId(Mockito.<Integer>any()))
				.thenReturn(v_get_Qualityaudit_SectionQuestionaireValuesList);

		// Act
		List<ResponseCallAuditSectionQuestionMapDTO> actualQuestionSectionForCallRatings = qualityAuditImpl
				.getQuestionSectionForCallRatings(1);

		// Assert
		verify(v_get_Qualityaudit_SectionQuestionaireValues).getOptionId();
		verify(v_get_Qualityaudit_SectionQuestionaireValues).getQuestionId();
		verify(v_get_Qualityaudit_SectionQuestionaireValues, atLeast(1)).getQuestionValues();
		verify(v_get_Qualityaudit_SectionQuestionaireValues, atLeast(1)).getScore();
		verify(v_get_Qualityaudit_SectionQuestionaireValues).setId(eq("42"));
		verify(v_get_Qualityaudit_SectionQuestionaireValues).setIsFatalQues(isA(Boolean.class));
		verify(v_get_Qualityaudit_SectionQuestionaireValues).setOptionId(isA(Integer.class));
		verify(v_get_Qualityaudit_SectionQuestionaireValues).setPsmId(isA(Integer.class));
		verify(v_get_Qualityaudit_SectionQuestionaireValues).setQuestion(eq("Question"));
		verify(v_get_Qualityaudit_SectionQuestionaireValues).setQuestionId(isA(Integer.class));
		verify(v_get_Qualityaudit_SectionQuestionaireValues).setQuestionRank(isA(Integer.class));
		verify(v_get_Qualityaudit_SectionQuestionaireValues).setQuestionValues(eq("42"));
		verify(v_get_Qualityaudit_SectionQuestionaireValues).setScore(isA(Integer.class));
		verify(v_get_Qualityaudit_SectionQuestionaireValues).setSectionId(isA(Integer.class));
		verify(v_get_Qualityaudit_SectionQuestionaireValues).setSectionName(eq("Section Name"));
		verify(v_get_Qualityaudit_SectionQuestionaireValues).setSectionRank(isA(Integer.class));
		verify(v_get_Qualityaudit_SectionQuestionaireValuesRepo).findByPsmIdOrderByQuestionId(isA(Integer.class));
		assertEquals(1, actualQuestionSectionForCallRatings.size());
	}

	/**
	 * Method under test: {@link QualityAuditImpl#getQualityAuditGrades(Integer)}
	 */
	@Test
	void testGetQualityAuditGrades() {
		// Arrange
		ArrayList<GradeConfiguration> gradeConfigurationList = new ArrayList<>();
		when(gradeConfigurationRepo.findByPsmIdAndDeleted(Mockito.<Integer>any(), Mockito.<Boolean>any()))
				.thenReturn(gradeConfigurationList);

		// Act
		List<GradeConfiguration> actualQualityAuditGrades = qualityAuditImpl.getQualityAuditGrades(1);

		// Assert
		verify(gradeConfigurationRepo).findByPsmIdAndDeleted(isA(Integer.class), isA(Boolean.class));
		assertTrue(actualQualityAuditGrades.isEmpty());
		assertSame(gradeConfigurationList, actualQualityAuditGrades);
	}

	/**
	 * Method under test: {@link QualityAuditImpl#getQualityAuditGrades(Integer)}
	 */
	@Test
	void testGetQualityAuditGrades2() {
		// Arrange
		when(gradeConfigurationRepo.findByPsmIdAndDeleted(Mockito.<Integer>any(), Mockito.<Boolean>any()))
				.thenThrow(new ECDException("Exception Msg"));

		// Act and Assert
		assertThrows(ECDException.class, () -> qualityAuditImpl.getQualityAuditGrades(1));
		verify(gradeConfigurationRepo).findByPsmIdAndDeleted(isA(Integer.class), isA(Boolean.class));
	}

	/**
	 * Method under test: {@link QualityAuditImpl#saveCallQualityRatings(String)}
	 */
	@Test
	void testSaveCallQualityRatings() {
		// Arrange, Act and Assert
		assertThrows(ECDException.class, () -> qualityAuditImpl.saveCallQualityRatings("Request OBJ"));
		assertThrows(ECDException.class, () -> qualityAuditImpl.saveCallQualityRatings(null));
		assertThrows(ECDException.class, () -> qualityAuditImpl.saveCallQualityRatings("42"));
	}

	/**
	 * Method under test: {@link QualityAuditImpl#getCallQualityRatings(Long)}
	 */

	/**
	 * Method under test: {@link QualityAuditImpl#getCallQualityRatings(Long)}
	 */
	@Test
	void testGetCallQualityRatings2() {
		// Arrange
		QualityAuditorRating qualityAuditorRating = new QualityAuditorRating();
		qualityAuditorRating.setAgentId(1);
		qualityAuditorRating.setBenCallId(1L);
		qualityAuditorRating.setCallRemarks("Call Remarks");
		qualityAuditorRating.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
		qualityAuditorRating.setCreatedDate(mock(Timestamp.class));
		qualityAuditorRating.setDeleted(true);
		qualityAuditorRating.setFinalGrade("Final Grade");
		qualityAuditorRating.setFinalScore(3);
		qualityAuditorRating.setGradeId(1);
		qualityAuditorRating.setId(1L);
		qualityAuditorRating.setIsZeroCall(true);
		qualityAuditorRating.setLastModDate(mock(Timestamp.class));
		qualityAuditorRating.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
		qualityAuditorRating.setPsmId(1);
		qualityAuditorRating.setQualityAuditorId(1);
		
		qualityAuditorRating.toString();
		
		when(qualityAuditorRatingRepo.findByBenCallId(Mockito.<Long>any())).thenReturn(qualityAuditorRating);
		when(v_QualityAuditorCallResponseRepo.findByBenCallId(Mockito.<Long>any()))
				.thenThrow(new ECDException("qualityRating"));

		// Act and Assert
		assertThrows(ECDException.class, () -> qualityAuditImpl.getCallQualityRatings(1L));
		verify(qualityAuditorRatingRepo).findByBenCallId(isA(Long.class));
		verify(v_QualityAuditorCallResponseRepo).findByBenCallId(isA(Long.class));
	}

	/**
	 * Method under test: {@link QualityAuditImpl#getCallQualityRatings(Long)}
	 */

	/**
	 * Method under test: {@link QualityAuditImpl#getCallQualityRatings(Long)}
	 */

	/**
	 * Method under test: {@link QualityAuditImpl#callReaudit(JsonObject)}
	 */
	@Test
	void testCallReaudit() {
		// Arrange, Act and Assert
		assertEquals("{\"response\":\"call reaudited successfully\"}", qualityAuditImpl.callReaudit(new JsonObject()));
		assertEquals("{\"response\":\"call reaudited successfully\"}", qualityAuditImpl.callReaudit(null));
	}

	/**
	 * Method under test: {@link QualityAuditImpl#callReaudit(JsonObject)}
	 */
	@Test
	void testCallReaudit2() {
		// Arrange
		JsonObject requestOBJ = new JsonObject();
		requestOBJ.add("qualityQuestionResponse", new JsonArray(3));

		// Act and Assert
		assertEquals("{\"response\":\"call reaudited successfully\"}", qualityAuditImpl.callReaudit(requestOBJ));
	}

	/**
	 * Method under test: {@link QualityAuditImpl#callReaudit(JsonObject)}
	 */
	@Test
	void testCallReaudit3() {
		// Arrange
		JsonObject requestOBJ = new JsonObject();
		requestOBJ.add("yyyy-MM-dd'T'HH:mm:ss.SSS", new JsonArray(3));
		requestOBJ.add("qualityQuestionResponse", new JsonArray(3));

		// Act and Assert
		assertEquals("{\"response\":\"call reaudited successfully\"}", qualityAuditImpl.callReaudit(requestOBJ));
	}

	/**
	 * Method under test:
	 * {@link QualityAuditImpl#getBeneficiaryCasesheet(BeneficiaryCasesheetDTO)}
	 */
	@Test
	void testGetBeneficiaryCasesheet() {
		// Arrange, Act and Assert
		assertThrows(ECDException.class, () -> qualityAuditImpl.getBeneficiaryCasesheet(beneficiaryCasesheetDTO));
	}
}
