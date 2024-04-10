package com.iemr.ecd.service.quality;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.iemr.ecd.dao.QualityAuditQuestionConfig;
import com.iemr.ecd.dao.QualityAuditQuestionnaireValues;
import com.iemr.ecd.dto.QualityAuditorSectionQuestionaireResponseDTO;
import com.iemr.ecd.repository.quality.QualityAuditQuestionConfigRepo;
import com.iemr.ecd.repository.quality.QualityAuditQuestionnaireValuesRepo;
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

@ContextConfiguration(classes = {QualityAuditQuestionConfigurationImpl.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class QualityAuditQuestionConfigurationImplDiffblueTest {
    @MockBean
    private QualityAuditQuestionConfigRepo qualityAuditQuestionConfigRepo;

    @Autowired
    private QualityAuditQuestionConfigurationImpl qualityAuditQuestionConfigurationImpl;

    @MockBean
    private QualityAuditQuestionnaireValuesRepo qualityAuditQuestionnaireValuesRepo;

    /**
     * Method under test:
     * {@link QualityAuditQuestionConfigurationImpl#createQualityAuditQuestionnaireConfiguration(List)}
     */
    @Test
    void testCreateQualityAuditQuestionnaireConfiguration() {
        // Arrange, Act and Assert
        assertEquals("{\"response\":\"Qulaity Audit Questionnaire Created Successfully\"}",
                qualityAuditQuestionConfigurationImpl.createQualityAuditQuestionnaireConfiguration(new ArrayList<>()));
    }

    /**
     * Method under test:
     * {@link QualityAuditQuestionConfigurationImpl#createQualityAuditQuestionnaireConfiguration(List)}
     */
    @Test
    void testCreateQualityAuditQuestionnaireConfiguration2() {
        // Arrange
        QualityAuditQuestionConfig qualityAuditQuestionConfig = new QualityAuditQuestionConfig();
        qualityAuditQuestionConfig.setAnswerType("Answer Type");
        qualityAuditQuestionConfig.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        qualityAuditQuestionConfig.setCreatedDate(mock(Timestamp.class));
        qualityAuditQuestionConfig.setDeleted(true);
        qualityAuditQuestionConfig.setId(1);
        qualityAuditQuestionConfig.setIsFatalQues(true);
        qualityAuditQuestionConfig.setLastModDate(mock(Timestamp.class));
        qualityAuditQuestionConfig.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        qualityAuditQuestionConfig.setOptions(new String[]{"Options"});
        qualityAuditQuestionConfig.setPsmId(1);
        qualityAuditQuestionConfig.setQuestionRank(1);
        qualityAuditQuestionConfig.setQuestionnaire("Questionnaire");
        qualityAuditQuestionConfig.setScores(new Integer[]{3});
        qualityAuditQuestionConfig.setSectionId(1);
        
        
        qualityAuditQuestionConfig.toString();
        
        when(qualityAuditQuestionConfigRepo.save(Mockito.<QualityAuditQuestionConfig>any()))
                .thenReturn(qualityAuditQuestionConfig);
        when(qualityAuditQuestionnaireValuesRepo.saveAll(Mockito.<Iterable<QualityAuditQuestionnaireValues>>any()))
                .thenReturn(new ArrayList<>());

        QualityAuditQuestionConfig qualityAuditQuestionConfig2 = new QualityAuditQuestionConfig();
        qualityAuditQuestionConfig2.setAnswerType("response");
        qualityAuditQuestionConfig2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        qualityAuditQuestionConfig2.setCreatedDate(mock(Timestamp.class));
        qualityAuditQuestionConfig2.setDeleted(true);
        qualityAuditQuestionConfig2.setId(1);
        qualityAuditQuestionConfig2.setIsFatalQues(true);
        qualityAuditQuestionConfig2.setLastModDate(mock(Timestamp.class));
        qualityAuditQuestionConfig2.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        qualityAuditQuestionConfig2.setOptions(new String[]{"response"});
        qualityAuditQuestionConfig2.setPsmId(1);
        qualityAuditQuestionConfig2.setQuestionRank(1);
        qualityAuditQuestionConfig2.setQuestionnaire("response");
        qualityAuditQuestionConfig2.setScores(new Integer[]{3});
        qualityAuditQuestionConfig2.setSectionId(1);

        ArrayList<QualityAuditQuestionConfig> qualityAuditQuestionConfig3 = new ArrayList<>();
        qualityAuditQuestionConfig3.add(qualityAuditQuestionConfig2);

        // Act
        String actualCreateQualityAuditQuestionnaireConfigurationResult = qualityAuditQuestionConfigurationImpl
                .createQualityAuditQuestionnaireConfiguration(qualityAuditQuestionConfig3);

        // Assert
        verify(qualityAuditQuestionConfigRepo).save(isA(QualityAuditQuestionConfig.class));
        verify(qualityAuditQuestionnaireValuesRepo).saveAll(isA(Iterable.class));
        assertEquals("{\"response\":\"Qulaity Audit Questionnaire Created Successfully\"}",
                actualCreateQualityAuditQuestionnaireConfigurationResult);
    }

    /**
     * Method under test:
     * {@link QualityAuditQuestionConfigurationImpl#createQualityAuditQuestionnaireConfiguration(List)}
     */
    @Test
    void testCreateQualityAuditQuestionnaireConfiguration3() {
        // Arrange
        QualityAuditQuestionConfig qualityAuditQuestionConfig = new QualityAuditQuestionConfig();
        qualityAuditQuestionConfig.setAnswerType("Answer Type");
        qualityAuditQuestionConfig.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        qualityAuditQuestionConfig.setCreatedDate(mock(Timestamp.class));
        qualityAuditQuestionConfig.setDeleted(true);
        qualityAuditQuestionConfig.setId(1);
        qualityAuditQuestionConfig.setIsFatalQues(true);
        qualityAuditQuestionConfig.setLastModDate(mock(Timestamp.class));
        qualityAuditQuestionConfig.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        qualityAuditQuestionConfig.setOptions(new String[]{"Options"});
        qualityAuditQuestionConfig.setPsmId(1);
        qualityAuditQuestionConfig.setQuestionRank(1);
        qualityAuditQuestionConfig.setQuestionnaire("Questionnaire");
        qualityAuditQuestionConfig.setScores(new Integer[]{3});
        qualityAuditQuestionConfig.setSectionId(1);
        when(qualityAuditQuestionConfigRepo.save(Mockito.<QualityAuditQuestionConfig>any()))
                .thenReturn(qualityAuditQuestionConfig);
        when(qualityAuditQuestionnaireValuesRepo.saveAll(Mockito.<Iterable<QualityAuditQuestionnaireValues>>any()))
                .thenThrow(new ECDException("response"));

        QualityAuditQuestionConfig qualityAuditQuestionConfig2 = new QualityAuditQuestionConfig();
        qualityAuditQuestionConfig2.setAnswerType("response");
        qualityAuditQuestionConfig2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        qualityAuditQuestionConfig2.setCreatedDate(mock(Timestamp.class));
        qualityAuditQuestionConfig2.setDeleted(true);
        qualityAuditQuestionConfig2.setId(1);
        qualityAuditQuestionConfig2.setIsFatalQues(true);
        qualityAuditQuestionConfig2.setLastModDate(mock(Timestamp.class));
        qualityAuditQuestionConfig2.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        qualityAuditQuestionConfig2.setOptions(new String[]{"response"});
        qualityAuditQuestionConfig2.setPsmId(1);
        qualityAuditQuestionConfig2.setQuestionRank(1);
        qualityAuditQuestionConfig2.setQuestionnaire("response");
        qualityAuditQuestionConfig2.setScores(new Integer[]{3});
        qualityAuditQuestionConfig2.setSectionId(1);

        ArrayList<QualityAuditQuestionConfig> qualityAuditQuestionConfig3 = new ArrayList<>();
        qualityAuditQuestionConfig3.add(qualityAuditQuestionConfig2);

        // Act and Assert
        assertThrows(ECDException.class, () -> qualityAuditQuestionConfigurationImpl
                .createQualityAuditQuestionnaireConfiguration(qualityAuditQuestionConfig3));
        verify(qualityAuditQuestionConfigRepo).save(isA(QualityAuditQuestionConfig.class));
        verify(qualityAuditQuestionnaireValuesRepo).saveAll(isA(Iterable.class));
    }

    /**
     * Method under test:
     * {@link QualityAuditQuestionConfigurationImpl#createQualityAuditQuestionnaireConfiguration(List)}
     */
    @Test
    void testCreateQualityAuditQuestionnaireConfiguration4() {
        // Arrange
        QualityAuditQuestionConfig qualityAuditQuestionConfig = new QualityAuditQuestionConfig();
        qualityAuditQuestionConfig.setAnswerType("Answer Type");
        qualityAuditQuestionConfig.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        qualityAuditQuestionConfig.setCreatedDate(mock(Timestamp.class));
        qualityAuditQuestionConfig.setDeleted(true);
        qualityAuditQuestionConfig.setId(null);
        qualityAuditQuestionConfig.setIsFatalQues(true);
        qualityAuditQuestionConfig.setLastModDate(mock(Timestamp.class));
        qualityAuditQuestionConfig.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        qualityAuditQuestionConfig.setOptions(new String[]{"Options"});
        qualityAuditQuestionConfig.setPsmId(1);
        qualityAuditQuestionConfig.setQuestionRank(1);
        qualityAuditQuestionConfig.setQuestionnaire("Questionnaire");
        qualityAuditQuestionConfig.setScores(new Integer[]{3});
        qualityAuditQuestionConfig.setSectionId(1);
        when(qualityAuditQuestionConfigRepo.save(Mockito.<QualityAuditQuestionConfig>any()))
                .thenReturn(qualityAuditQuestionConfig);

        QualityAuditQuestionConfig qualityAuditQuestionConfig2 = new QualityAuditQuestionConfig();
        qualityAuditQuestionConfig2.setAnswerType("response");
        qualityAuditQuestionConfig2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        qualityAuditQuestionConfig2.setCreatedDate(mock(Timestamp.class));
        qualityAuditQuestionConfig2.setDeleted(true);
        qualityAuditQuestionConfig2.setId(1);
        qualityAuditQuestionConfig2.setIsFatalQues(true);
        qualityAuditQuestionConfig2.setLastModDate(mock(Timestamp.class));
        qualityAuditQuestionConfig2.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        qualityAuditQuestionConfig2.setOptions(new String[]{"response"});
        qualityAuditQuestionConfig2.setPsmId(1);
        qualityAuditQuestionConfig2.setQuestionRank(1);
        qualityAuditQuestionConfig2.setQuestionnaire("response");
        qualityAuditQuestionConfig2.setScores(new Integer[]{3});
        qualityAuditQuestionConfig2.setSectionId(1);

        ArrayList<QualityAuditQuestionConfig> qualityAuditQuestionConfig3 = new ArrayList<>();
        qualityAuditQuestionConfig3.add(qualityAuditQuestionConfig2);

        // Act
        String actualCreateQualityAuditQuestionnaireConfigurationResult = qualityAuditQuestionConfigurationImpl
                .createQualityAuditQuestionnaireConfiguration(qualityAuditQuestionConfig3);

        // Assert
        verify(qualityAuditQuestionConfigRepo).save(isA(QualityAuditQuestionConfig.class));
        assertEquals("{\"response\":\"Qulaity Audit Questionnaire Created Successfully\"}",
                actualCreateQualityAuditQuestionnaireConfigurationResult);
    }

    /**
     * Method under test:
     * {@link QualityAuditQuestionConfigurationImpl#createQualityAuditQuestionnaireConfiguration(List)}
     */
    @Test
    void testCreateQualityAuditQuestionnaireConfiguration5() {
        // Arrange
        QualityAuditQuestionConfig qualityAuditQuestionConfig = new QualityAuditQuestionConfig();
        qualityAuditQuestionConfig.setAnswerType("Answer Type");
        qualityAuditQuestionConfig.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        qualityAuditQuestionConfig.setCreatedDate(mock(Timestamp.class));
        qualityAuditQuestionConfig.setDeleted(true);
        qualityAuditQuestionConfig.setId(1);
        qualityAuditQuestionConfig.setIsFatalQues(true);
        qualityAuditQuestionConfig.setLastModDate(mock(Timestamp.class));
        qualityAuditQuestionConfig.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        qualityAuditQuestionConfig.setOptions(new String[]{"Options"});
        qualityAuditQuestionConfig.setPsmId(1);
        qualityAuditQuestionConfig.setQuestionRank(1);
        qualityAuditQuestionConfig.setQuestionnaire("Questionnaire");
        qualityAuditQuestionConfig.setScores(new Integer[]{3});
        qualityAuditQuestionConfig.setSectionId(1);
        when(qualityAuditQuestionConfigRepo.save(Mockito.<QualityAuditQuestionConfig>any()))
                .thenReturn(qualityAuditQuestionConfig);
        when(qualityAuditQuestionnaireValuesRepo.saveAll(Mockito.<Iterable<QualityAuditQuestionnaireValues>>any()))
                .thenReturn(new ArrayList<>());

        QualityAuditQuestionConfig qualityAuditQuestionConfig2 = new QualityAuditQuestionConfig();
        qualityAuditQuestionConfig2.setAnswerType("response");
        qualityAuditQuestionConfig2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        qualityAuditQuestionConfig2.setCreatedDate(mock(Timestamp.class));
        qualityAuditQuestionConfig2.setDeleted(true);
        qualityAuditQuestionConfig2.setId(1);
        qualityAuditQuestionConfig2.setIsFatalQues(true);
        qualityAuditQuestionConfig2.setLastModDate(mock(Timestamp.class));
        qualityAuditQuestionConfig2.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        qualityAuditQuestionConfig2.setOptions(new String[]{"response"});
        qualityAuditQuestionConfig2.setPsmId(1);
        qualityAuditQuestionConfig2.setQuestionRank(1);
        qualityAuditQuestionConfig2.setQuestionnaire("response");
        qualityAuditQuestionConfig2.setScores(new Integer[]{3});
        qualityAuditQuestionConfig2.setSectionId(1);

        QualityAuditQuestionConfig qualityAuditQuestionConfig3 = new QualityAuditQuestionConfig();
        qualityAuditQuestionConfig3.setAnswerType("Qulaity Audit Questionnaire Created Successfully");
        qualityAuditQuestionConfig3.setCreatedBy("response");
        qualityAuditQuestionConfig3.setCreatedDate(mock(Timestamp.class));
        qualityAuditQuestionConfig3.setDeleted(false);
        qualityAuditQuestionConfig3.setId(2);
        qualityAuditQuestionConfig3.setIsFatalQues(false);
        qualityAuditQuestionConfig3.setLastModDate(mock(Timestamp.class));
        qualityAuditQuestionConfig3.setModifiedBy("response");
        qualityAuditQuestionConfig3.setOptions(new String[]{"response"});
        qualityAuditQuestionConfig3.setPsmId(2);
        qualityAuditQuestionConfig3.setQuestionRank(59);
        qualityAuditQuestionConfig3.setQuestionnaire("Qulaity Audit Questionnaire Created Successfully");
        qualityAuditQuestionConfig3.setScores(new Integer[]{3});
        qualityAuditQuestionConfig3.setSectionId(2);
        
        qualityAuditQuestionConfig3.toString();

        ArrayList<QualityAuditQuestionConfig> qualityAuditQuestionConfig4 = new ArrayList<>();
        qualityAuditQuestionConfig4.add(qualityAuditQuestionConfig3);
        qualityAuditQuestionConfig4.add(qualityAuditQuestionConfig2);

        // Act
        String actualCreateQualityAuditQuestionnaireConfigurationResult = qualityAuditQuestionConfigurationImpl
                .createQualityAuditQuestionnaireConfiguration(qualityAuditQuestionConfig4);

        // Assert
        verify(qualityAuditQuestionConfigRepo, atLeast(1)).save(Mockito.<QualityAuditQuestionConfig>any());
        verify(qualityAuditQuestionnaireValuesRepo, atLeast(1))
                .saveAll(Mockito.<Iterable<QualityAuditQuestionnaireValues>>any());
        assertEquals("{\"response\":\"Qulaity Audit Questionnaire Created Successfully\"}",
                actualCreateQualityAuditQuestionnaireConfigurationResult);
    }

    /**
     * Method under test:
     * {@link QualityAuditQuestionConfigurationImpl#createQualityAuditQuestionnaireConfiguration(List)}
     */
    @Test
    void testCreateQualityAuditQuestionnaireConfiguration6() {
        // Arrange
        QualityAuditQuestionConfig qualityAuditQuestionConfig = mock(QualityAuditQuestionConfig.class);
        when(qualityAuditQuestionConfig.getPsmId()).thenThrow(new ECDException("Exception Msg"));
        when(qualityAuditQuestionConfig.getScores()).thenReturn(new Integer[]{3});
        when(qualityAuditQuestionConfig.getOptions()).thenReturn(new String[]{"Options"});
        doNothing().when(qualityAuditQuestionConfig).setAnswerType(Mockito.<String>any());
        doNothing().when(qualityAuditQuestionConfig).setCreatedBy(Mockito.<String>any());
        doNothing().when(qualityAuditQuestionConfig).setCreatedDate(Mockito.<Timestamp>any());
        doNothing().when(qualityAuditQuestionConfig).setDeleted(Mockito.<Boolean>any());
        doNothing().when(qualityAuditQuestionConfig).setId(Mockito.<Integer>any());
        doNothing().when(qualityAuditQuestionConfig).setIsFatalQues(Mockito.<Boolean>any());
        doNothing().when(qualityAuditQuestionConfig).setLastModDate(Mockito.<Timestamp>any());
        doNothing().when(qualityAuditQuestionConfig).setModifiedBy(Mockito.<String>any());
        doNothing().when(qualityAuditQuestionConfig).setOptions(Mockito.<String[]>any());
        doNothing().when(qualityAuditQuestionConfig).setPsmId(Mockito.<Integer>any());
        doNothing().when(qualityAuditQuestionConfig).setQuestionRank(Mockito.<Integer>any());
        doNothing().when(qualityAuditQuestionConfig).setQuestionnaire(Mockito.<String>any());
        doNothing().when(qualityAuditQuestionConfig).setScores(Mockito.<Integer[]>any());
        doNothing().when(qualityAuditQuestionConfig).setSectionId(Mockito.<Integer>any());
        qualityAuditQuestionConfig.setAnswerType("response");
        qualityAuditQuestionConfig.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        qualityAuditQuestionConfig.setCreatedDate(mock(Timestamp.class));
        qualityAuditQuestionConfig.setDeleted(true);
        qualityAuditQuestionConfig.setId(1);
        qualityAuditQuestionConfig.setIsFatalQues(true);
        qualityAuditQuestionConfig.setLastModDate(mock(Timestamp.class));
        qualityAuditQuestionConfig.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        qualityAuditQuestionConfig.setOptions(new String[]{"response"});
        qualityAuditQuestionConfig.setPsmId(1);
        qualityAuditQuestionConfig.setQuestionRank(1);
        qualityAuditQuestionConfig.setQuestionnaire("response");
        qualityAuditQuestionConfig.setScores(new Integer[]{3});
        qualityAuditQuestionConfig.setSectionId(1);

        ArrayList<QualityAuditQuestionConfig> qualityAuditQuestionConfig2 = new ArrayList<>();
        qualityAuditQuestionConfig2.add(qualityAuditQuestionConfig);

        // Act and Assert
        assertThrows(ECDException.class, () -> qualityAuditQuestionConfigurationImpl
                .createQualityAuditQuestionnaireConfiguration(qualityAuditQuestionConfig2));
        verify(qualityAuditQuestionConfig, atLeast(1)).getOptions();
        verify(qualityAuditQuestionConfig).getPsmId();
        verify(qualityAuditQuestionConfig).getScores();
        verify(qualityAuditQuestionConfig).setAnswerType(eq("response"));
        verify(qualityAuditQuestionConfig).setCreatedBy(eq("Jan 1, 2020 8:00am GMT+0100"));
        verify(qualityAuditQuestionConfig).setCreatedDate(isA(Timestamp.class));
        verify(qualityAuditQuestionConfig).setDeleted(isA(Boolean.class));
        verify(qualityAuditQuestionConfig).setId(isA(Integer.class));
        verify(qualityAuditQuestionConfig).setIsFatalQues(isA(Boolean.class));
        verify(qualityAuditQuestionConfig).setLastModDate(isA(Timestamp.class));
        verify(qualityAuditQuestionConfig).setModifiedBy(eq("Jan 1, 2020 9:00am GMT+0100"));
        verify(qualityAuditQuestionConfig).setOptions(isA(String[].class));
        verify(qualityAuditQuestionConfig).setPsmId(isA(Integer.class));
        verify(qualityAuditQuestionConfig).setQuestionRank(isA(Integer.class));
        verify(qualityAuditQuestionConfig).setQuestionnaire(eq("response"));
        verify(qualityAuditQuestionConfig).setScores(isA(Integer[].class));
        verify(qualityAuditQuestionConfig).setSectionId(isA(Integer.class));
    }

    /**
     * Method under test:
     * {@link QualityAuditQuestionConfigurationImpl#getQualityAuditQuestionnaireConfigurationByPSMId(Integer)}
     */
    @Test
    void testGetQualityAuditQuestionnaireConfigurationByPSMId() {
        // Arrange
        when(qualityAuditQuestionConfigRepo.getQualityAuditQuestionnaire(Mockito.<Integer>any()))
                .thenReturn(new ArrayList<>());

        // Act
        List<QualityAuditorSectionQuestionaireResponseDTO> actualQualityAuditQuestionnaireConfigurationByPSMId = qualityAuditQuestionConfigurationImpl
                .getQualityAuditQuestionnaireConfigurationByPSMId(1);

        // Assert
        verify(qualityAuditQuestionConfigRepo).getQualityAuditQuestionnaire(isA(Integer.class));
        assertTrue(actualQualityAuditQuestionnaireConfigurationByPSMId.isEmpty());
    }

    /**
     * Method under test:
     * {@link QualityAuditQuestionConfigurationImpl#getQualityAuditQuestionnaireConfigurationByPSMId(Integer)}
     */
    @Test
    void testGetQualityAuditQuestionnaireConfigurationByPSMId2() {
        // Arrange
        ArrayList<String[]> stringArrayList = new ArrayList<>();
        stringArrayList.add(new String[]{"foo"});
        when(qualityAuditQuestionConfigRepo.getQualityAuditQuestionnaire(Mockito.<Integer>any()))
                .thenReturn(stringArrayList);

        // Act and Assert
        assertThrows(ECDException.class,
                () -> qualityAuditQuestionConfigurationImpl.getQualityAuditQuestionnaireConfigurationByPSMId(1));
        verify(qualityAuditQuestionConfigRepo).getQualityAuditQuestionnaire(isA(Integer.class));
    }

    /**
     * Method under test:
     * {@link QualityAuditQuestionConfigurationImpl#getQualityAuditQuestionnaireConfigurationByPSMId(Integer)}
     */
    @Test
    void testGetQualityAuditQuestionnaireConfigurationByPSMId3() {
        // Arrange
        when(qualityAuditQuestionConfigRepo.getQualityAuditQuestionnaire(Mockito.<Integer>any()))
                .thenThrow(new ECDException("Exception Msg"));

        // Act and Assert
        assertThrows(ECDException.class,
                () -> qualityAuditQuestionConfigurationImpl.getQualityAuditQuestionnaireConfigurationByPSMId(1));
        verify(qualityAuditQuestionConfigRepo).getQualityAuditQuestionnaire(isA(Integer.class));
    }

    /**
     * Method under test:
     * {@link QualityAuditQuestionConfigurationImpl#updateQualityAuditQuestionnaireConfiguration(QualityAuditQuestionConfig)}
     */
    @Test
    void testUpdateQualityAuditQuestionnaireConfiguration() {
        // Arrange
        QualityAuditQuestionConfig qualityAuditQuestionConfig = new QualityAuditQuestionConfig();
        qualityAuditQuestionConfig.setAnswerType("Answer Type");
        qualityAuditQuestionConfig.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        qualityAuditQuestionConfig.setCreatedDate(mock(Timestamp.class));
        qualityAuditQuestionConfig.setDeleted(true);
        qualityAuditQuestionConfig.setId(1);
        qualityAuditQuestionConfig.setIsFatalQues(true);
        qualityAuditQuestionConfig.setLastModDate(mock(Timestamp.class));
        qualityAuditQuestionConfig.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        qualityAuditQuestionConfig.setOptions(new String[]{"Options"});
        qualityAuditQuestionConfig.setPsmId(1);
        qualityAuditQuestionConfig.setQuestionRank(1);
        qualityAuditQuestionConfig.setQuestionnaire("Questionnaire");
        qualityAuditQuestionConfig.setScores(new Integer[]{3});
        qualityAuditQuestionConfig.setSectionId(1);
        when(qualityAuditQuestionConfigRepo.save(Mockito.<QualityAuditQuestionConfig>any()))
                .thenReturn(qualityAuditQuestionConfig);
        when(qualityAuditQuestionnaireValuesRepo.saveAll(Mockito.<Iterable<QualityAuditQuestionnaireValues>>any()))
                .thenReturn(new ArrayList<>());
        when(qualityAuditQuestionnaireValuesRepo.findByQuestionIdAndDeleted(Mockito.<Integer>any(), Mockito.<Boolean>any()))
                .thenReturn(new ArrayList<>());

        QualityAuditQuestionConfig qualityAuditQuestionnaire = new QualityAuditQuestionConfig();
        qualityAuditQuestionnaire.setAnswerType("Answer Type");
        qualityAuditQuestionnaire.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        qualityAuditQuestionnaire.setCreatedDate(mock(Timestamp.class));
        qualityAuditQuestionnaire.setDeleted(true);
        qualityAuditQuestionnaire.setId(1);
        qualityAuditQuestionnaire.setIsFatalQues(true);
        qualityAuditQuestionnaire.setLastModDate(mock(Timestamp.class));
        qualityAuditQuestionnaire.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        qualityAuditQuestionnaire.setOptions(new String[]{"Options"});
        qualityAuditQuestionnaire.setPsmId(1);
        qualityAuditQuestionnaire.setQuestionRank(1);
        qualityAuditQuestionnaire.setQuestionnaire("Questionnaire");
        qualityAuditQuestionnaire.setScores(new Integer[]{3});
        qualityAuditQuestionnaire.setSectionId(1);

        // Act
        String actualUpdateQualityAuditQuestionnaireConfigurationResult = qualityAuditQuestionConfigurationImpl
                .updateQualityAuditQuestionnaireConfiguration(qualityAuditQuestionnaire);

        // Assert
        verify(qualityAuditQuestionnaireValuesRepo).findByQuestionIdAndDeleted(isA(Integer.class), isA(Boolean.class));
        verify(qualityAuditQuestionConfigRepo).save(isA(QualityAuditQuestionConfig.class));
        verify(qualityAuditQuestionnaireValuesRepo).saveAll(isA(Iterable.class));
        assertEquals("{\"response\":\"Qulaity Audit Questionnaire Updated Successfully\"}",
                actualUpdateQualityAuditQuestionnaireConfigurationResult);
    }

    /**
     * Method under test:
     * {@link QualityAuditQuestionConfigurationImpl#updateQualityAuditQuestionnaireConfiguration(QualityAuditQuestionConfig)}
     */
    @Test
    void testUpdateQualityAuditQuestionnaireConfiguration2() {
        // Arrange
        QualityAuditQuestionConfig qualityAuditQuestionConfig = new QualityAuditQuestionConfig();
        qualityAuditQuestionConfig.setAnswerType("Answer Type");
        qualityAuditQuestionConfig.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        qualityAuditQuestionConfig.setCreatedDate(mock(Timestamp.class));
        qualityAuditQuestionConfig.setDeleted(true);
        qualityAuditQuestionConfig.setId(1);
        qualityAuditQuestionConfig.setIsFatalQues(true);
        qualityAuditQuestionConfig.setLastModDate(mock(Timestamp.class));
        qualityAuditQuestionConfig.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        qualityAuditQuestionConfig.setOptions(new String[]{"Options"});
        qualityAuditQuestionConfig.setPsmId(1);
        qualityAuditQuestionConfig.setQuestionRank(1);
        qualityAuditQuestionConfig.setQuestionnaire("Questionnaire");
        qualityAuditQuestionConfig.setScores(new Integer[]{3});
        qualityAuditQuestionConfig.setSectionId(1);
        when(qualityAuditQuestionConfigRepo.save(Mockito.<QualityAuditQuestionConfig>any()))
                .thenReturn(qualityAuditQuestionConfig);
        when(qualityAuditQuestionnaireValuesRepo.findByQuestionIdAndDeleted(Mockito.<Integer>any(), Mockito.<Boolean>any()))
                .thenThrow(new ECDException("response"));

        QualityAuditQuestionConfig qualityAuditQuestionnaire = new QualityAuditQuestionConfig();
        qualityAuditQuestionnaire.setAnswerType("Answer Type");
        qualityAuditQuestionnaire.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        qualityAuditQuestionnaire.setCreatedDate(mock(Timestamp.class));
        qualityAuditQuestionnaire.setDeleted(true);
        qualityAuditQuestionnaire.setId(1);
        qualityAuditQuestionnaire.setIsFatalQues(true);
        qualityAuditQuestionnaire.setLastModDate(mock(Timestamp.class));
        qualityAuditQuestionnaire.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        qualityAuditQuestionnaire.setOptions(new String[]{"Options"});
        qualityAuditQuestionnaire.setPsmId(1);
        qualityAuditQuestionnaire.setQuestionRank(1);
        qualityAuditQuestionnaire.setQuestionnaire("Questionnaire");
        qualityAuditQuestionnaire.setScores(new Integer[]{3});
        qualityAuditQuestionnaire.setSectionId(1);

        // Act and Assert
        assertThrows(ECDException.class, () -> qualityAuditQuestionConfigurationImpl
                .updateQualityAuditQuestionnaireConfiguration(qualityAuditQuestionnaire));
        verify(qualityAuditQuestionnaireValuesRepo).findByQuestionIdAndDeleted(isA(Integer.class), isA(Boolean.class));
        verify(qualityAuditQuestionConfigRepo).save(isA(QualityAuditQuestionConfig.class));
    }

    /**
     * Method under test:
     * {@link QualityAuditQuestionConfigurationImpl#updateQualityAuditQuestionnaireConfiguration(QualityAuditQuestionConfig)}
     */
    @Test
    void testUpdateQualityAuditQuestionnaireConfiguration3() {
        // Arrange
        QualityAuditQuestionConfig qualityAuditQuestionConfig = new QualityAuditQuestionConfig();
        qualityAuditQuestionConfig.setAnswerType("Answer Type");
        qualityAuditQuestionConfig.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        qualityAuditQuestionConfig.setCreatedDate(mock(Timestamp.class));
        qualityAuditQuestionConfig.setDeleted(true);
        qualityAuditQuestionConfig.setId(1);
        qualityAuditQuestionConfig.setIsFatalQues(true);
        qualityAuditQuestionConfig.setLastModDate(mock(Timestamp.class));
        qualityAuditQuestionConfig.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        qualityAuditQuestionConfig.setOptions(new String[]{"Options"});
        qualityAuditQuestionConfig.setPsmId(1);
        qualityAuditQuestionConfig.setQuestionRank(1);
        qualityAuditQuestionConfig.setQuestionnaire("Questionnaire");
        qualityAuditQuestionConfig.setScores(new Integer[]{3});
        qualityAuditQuestionConfig.setSectionId(1);
        when(qualityAuditQuestionConfigRepo.save(Mockito.<QualityAuditQuestionConfig>any()))
                .thenReturn(qualityAuditQuestionConfig);

        QualityAuditQuestionnaireValues qualityAuditQuestionnaireValues = new QualityAuditQuestionnaireValues();
        qualityAuditQuestionnaireValues.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        qualityAuditQuestionnaireValues.setCreatedDate(mock(Timestamp.class));
        qualityAuditQuestionnaireValues.setDeleted(true);
        qualityAuditQuestionnaireValues.setId(1);
        qualityAuditQuestionnaireValues.setLastModDate(mock(Timestamp.class));
        qualityAuditQuestionnaireValues.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        qualityAuditQuestionnaireValues.setPsmId(1);
        qualityAuditQuestionnaireValues.setQuestionId(1);
        qualityAuditQuestionnaireValues.setQuestionValues("42");
        qualityAuditQuestionnaireValues.setScore(3);

        ArrayList<QualityAuditQuestionnaireValues> qualityAuditQuestionnaireValuesList = new ArrayList<>();
        qualityAuditQuestionnaireValuesList.add(qualityAuditQuestionnaireValues);
        when(qualityAuditQuestionnaireValuesRepo.saveAll(Mockito.<Iterable<QualityAuditQuestionnaireValues>>any()))
                .thenReturn(new ArrayList<>());
        when(qualityAuditQuestionnaireValuesRepo.findByQuestionIdAndDeleted(Mockito.<Integer>any(), Mockito.<Boolean>any()))
                .thenReturn(qualityAuditQuestionnaireValuesList);

        QualityAuditQuestionConfig qualityAuditQuestionnaire = new QualityAuditQuestionConfig();
        qualityAuditQuestionnaire.setAnswerType("Answer Type");
        qualityAuditQuestionnaire.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        qualityAuditQuestionnaire.setCreatedDate(mock(Timestamp.class));
        qualityAuditQuestionnaire.setDeleted(true);
        qualityAuditQuestionnaire.setId(1);
        qualityAuditQuestionnaire.setIsFatalQues(true);
        qualityAuditQuestionnaire.setLastModDate(mock(Timestamp.class));
        qualityAuditQuestionnaire.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        qualityAuditQuestionnaire.setOptions(new String[]{"Options"});
        qualityAuditQuestionnaire.setPsmId(1);
        qualityAuditQuestionnaire.setQuestionRank(1);
        qualityAuditQuestionnaire.setQuestionnaire("Questionnaire");
        qualityAuditQuestionnaire.setScores(new Integer[]{3});
        qualityAuditQuestionnaire.setSectionId(1);
        
        qualityAuditQuestionnaire.toString();

        // Act
        String actualUpdateQualityAuditQuestionnaireConfigurationResult = qualityAuditQuestionConfigurationImpl
                .updateQualityAuditQuestionnaireConfiguration(qualityAuditQuestionnaire);

        // Assert
        verify(qualityAuditQuestionnaireValuesRepo).findByQuestionIdAndDeleted(isA(Integer.class), isA(Boolean.class));
        verify(qualityAuditQuestionConfigRepo).save(isA(QualityAuditQuestionConfig.class));
        verify(qualityAuditQuestionnaireValuesRepo).saveAll(isA(Iterable.class));
        assertEquals("{\"response\":\"Qulaity Audit Questionnaire Updated Successfully\"}",
                actualUpdateQualityAuditQuestionnaireConfigurationResult);
    }

    /**
     * Method under test:
     * {@link QualityAuditQuestionConfigurationImpl#updateQualityAuditQuestionnaireConfiguration(QualityAuditQuestionConfig)}
     */
    @Test
    void testUpdateQualityAuditQuestionnaireConfiguration4() {
        // Arrange
        QualityAuditQuestionConfig qualityAuditQuestionConfig = new QualityAuditQuestionConfig();
        qualityAuditQuestionConfig.setAnswerType("Answer Type");
        qualityAuditQuestionConfig.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        qualityAuditQuestionConfig.setCreatedDate(mock(Timestamp.class));
        qualityAuditQuestionConfig.setDeleted(true);
        qualityAuditQuestionConfig.setId(1);
        qualityAuditQuestionConfig.setIsFatalQues(true);
        qualityAuditQuestionConfig.setLastModDate(mock(Timestamp.class));
        qualityAuditQuestionConfig.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        qualityAuditQuestionConfig.setOptions(new String[]{"Options"});
        qualityAuditQuestionConfig.setPsmId(1);
        qualityAuditQuestionConfig.setQuestionRank(1);
        qualityAuditQuestionConfig.setQuestionnaire("Questionnaire");
        qualityAuditQuestionConfig.setScores(new Integer[]{3});
        qualityAuditQuestionConfig.setSectionId(1);
        when(qualityAuditQuestionConfigRepo.save(Mockito.<QualityAuditQuestionConfig>any()))
                .thenReturn(qualityAuditQuestionConfig);
        when(qualityAuditQuestionnaireValuesRepo.findByQuestionIdAndDeleted(Mockito.<Integer>any(), Mockito.<Boolean>any()))
                .thenReturn(new ArrayList<>());
        QualityAuditQuestionConfig qualityAuditQuestionnaire = mock(QualityAuditQuestionConfig.class);
        when(qualityAuditQuestionnaire.getPsmId()).thenThrow(new ECDException("Exception Msg"));
        when(qualityAuditQuestionnaire.getScores()).thenReturn(new Integer[]{3});
        when(qualityAuditQuestionnaire.getId()).thenReturn(1);
        when(qualityAuditQuestionnaire.getOptions()).thenReturn(new String[]{"Options"});
        doNothing().when(qualityAuditQuestionnaire).setAnswerType(Mockito.<String>any());
        doNothing().when(qualityAuditQuestionnaire).setCreatedBy(Mockito.<String>any());
        doNothing().when(qualityAuditQuestionnaire).setCreatedDate(Mockito.<Timestamp>any());
        doNothing().when(qualityAuditQuestionnaire).setDeleted(Mockito.<Boolean>any());
        doNothing().when(qualityAuditQuestionnaire).setId(Mockito.<Integer>any());
        doNothing().when(qualityAuditQuestionnaire).setIsFatalQues(Mockito.<Boolean>any());
        doNothing().when(qualityAuditQuestionnaire).setLastModDate(Mockito.<Timestamp>any());
        doNothing().when(qualityAuditQuestionnaire).setModifiedBy(Mockito.<String>any());
        doNothing().when(qualityAuditQuestionnaire).setOptions(Mockito.<String[]>any());
        doNothing().when(qualityAuditQuestionnaire).setPsmId(Mockito.<Integer>any());
        doNothing().when(qualityAuditQuestionnaire).setQuestionRank(Mockito.<Integer>any());
        doNothing().when(qualityAuditQuestionnaire).setQuestionnaire(Mockito.<String>any());
        doNothing().when(qualityAuditQuestionnaire).setScores(Mockito.<Integer[]>any());
        doNothing().when(qualityAuditQuestionnaire).setSectionId(Mockito.<Integer>any());
        qualityAuditQuestionnaire.setAnswerType("Answer Type");
        qualityAuditQuestionnaire.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        qualityAuditQuestionnaire.setCreatedDate(mock(Timestamp.class));
        qualityAuditQuestionnaire.setDeleted(true);
        qualityAuditQuestionnaire.setId(1);
        qualityAuditQuestionnaire.setIsFatalQues(true);
        qualityAuditQuestionnaire.setLastModDate(mock(Timestamp.class));
        qualityAuditQuestionnaire.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        qualityAuditQuestionnaire.setOptions(new String[]{"Options"});
        qualityAuditQuestionnaire.setPsmId(1);
        qualityAuditQuestionnaire.setQuestionRank(1);
        qualityAuditQuestionnaire.setQuestionnaire("Questionnaire");
        qualityAuditQuestionnaire.setScores(new Integer[]{3});
        qualityAuditQuestionnaire.setSectionId(1);

        // Act and Assert
        assertThrows(ECDException.class, () -> qualityAuditQuestionConfigurationImpl
                .updateQualityAuditQuestionnaireConfiguration(qualityAuditQuestionnaire));
        verify(qualityAuditQuestionnaire, atLeast(1)).getId();
        verify(qualityAuditQuestionnaire, atLeast(1)).getOptions();
        verify(qualityAuditQuestionnaire).getPsmId();
        verify(qualityAuditQuestionnaire, atLeast(1)).getScores();
        verify(qualityAuditQuestionnaire).setAnswerType(eq("Answer Type"));
        verify(qualityAuditQuestionnaire).setCreatedBy(eq("Jan 1, 2020 8:00am GMT+0100"));
        verify(qualityAuditQuestionnaire).setCreatedDate(isA(Timestamp.class));
        verify(qualityAuditQuestionnaire).setDeleted(isA(Boolean.class));
        verify(qualityAuditQuestionnaire).setId(isA(Integer.class));
        verify(qualityAuditQuestionnaire).setIsFatalQues(isA(Boolean.class));
        verify(qualityAuditQuestionnaire).setLastModDate(isA(Timestamp.class));
        verify(qualityAuditQuestionnaire).setModifiedBy(eq("Jan 1, 2020 9:00am GMT+0100"));
        verify(qualityAuditQuestionnaire).setOptions(isA(String[].class));
        verify(qualityAuditQuestionnaire).setPsmId(isA(Integer.class));
        verify(qualityAuditQuestionnaire).setQuestionRank(isA(Integer.class));
        verify(qualityAuditQuestionnaire).setQuestionnaire(eq("Questionnaire"));
        verify(qualityAuditQuestionnaire).setScores(isA(Integer[].class));
        verify(qualityAuditQuestionnaire).setSectionId(isA(Integer.class));
        verify(qualityAuditQuestionnaireValuesRepo).findByQuestionIdAndDeleted(isA(Integer.class), isA(Boolean.class));
        verify(qualityAuditQuestionConfigRepo).save(isA(QualityAuditQuestionConfig.class));
    }
}
