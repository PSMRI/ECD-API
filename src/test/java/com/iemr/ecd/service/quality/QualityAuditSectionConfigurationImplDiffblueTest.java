package com.iemr.ecd.service.quality;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.iemr.ecd.dao.QualityAuditSectionConfiguration;
import com.iemr.ecd.repository.quality.QualityAuditSectionConfigurationRepo;
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

@ContextConfiguration(classes = {QualityAuditSectionConfigurationImpl.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class QualityAuditSectionConfigurationImplDiffblueTest {
    @Autowired
    private QualityAuditSectionConfigurationImpl qualityAuditSectionConfigurationImpl;

    @MockBean
    private QualityAuditSectionConfigurationRepo qualityAuditSectionConfigurationRepo;

    /**
     * Method under test:
     * {@link QualityAuditSectionConfigurationImpl#createQualityAuditSectionConfiguration(List)}
     */
    @Test
    void testCreateQualityAuditSectionConfiguration() {
        // Arrange
        when(qualityAuditSectionConfigurationRepo.saveAll(Mockito.<Iterable<QualityAuditSectionConfiguration>>any()))
                .thenReturn(new ArrayList<>());

        // Act
        String actualCreateQualityAuditSectionConfigurationResult = qualityAuditSectionConfigurationImpl
                .createQualityAuditSectionConfiguration(new ArrayList<>());

        // Assert
        verify(qualityAuditSectionConfigurationRepo).saveAll(isA(Iterable.class));
        assertEquals("{\"response\":\"Section Configuration Created Successfully\"}",
                actualCreateQualityAuditSectionConfigurationResult);
    }

    /**
     * Method under test:
     * {@link QualityAuditSectionConfigurationImpl#createQualityAuditSectionConfiguration(List)}
     */
    @Test
    void testCreateQualityAuditSectionConfiguration2() {
        // Arrange
        when(qualityAuditSectionConfigurationRepo.saveAll(Mockito.<Iterable<QualityAuditSectionConfiguration>>any()))
                .thenReturn(new ArrayList<>());

        QualityAuditSectionConfiguration qualityAuditSectionConfiguration = new QualityAuditSectionConfiguration();
        qualityAuditSectionConfiguration.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        qualityAuditSectionConfiguration.setCreatedDate(mock(Timestamp.class));
        qualityAuditSectionConfiguration.setDeleted(true);
        qualityAuditSectionConfiguration.setLastModDate(mock(Timestamp.class));
        qualityAuditSectionConfiguration.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        qualityAuditSectionConfiguration.setPsmId(1);
        qualityAuditSectionConfiguration.setRank(1);
        qualityAuditSectionConfiguration.setSectionDesc("response");
        qualityAuditSectionConfiguration.setSectionId(1);
        qualityAuditSectionConfiguration.setSectionName("response");
        
        qualityAuditSectionConfiguration.toString();

        ArrayList<QualityAuditSectionConfiguration> qualityAuditSectionConfiguration2 = new ArrayList<>();
        qualityAuditSectionConfiguration2.add(qualityAuditSectionConfiguration);

        // Act
        String actualCreateQualityAuditSectionConfigurationResult = qualityAuditSectionConfigurationImpl
                .createQualityAuditSectionConfiguration(qualityAuditSectionConfiguration2);

        // Assert
        verify(qualityAuditSectionConfigurationRepo).saveAll(isA(Iterable.class));
        assertEquals("{\"response\":\"Section Configuration Created Successfully\"}",
                actualCreateQualityAuditSectionConfigurationResult);
    }

    /**
     * Method under test:
     * {@link QualityAuditSectionConfigurationImpl#createQualityAuditSectionConfiguration(List)}
     */
    @Test
    void testCreateQualityAuditSectionConfiguration3() {
        // Arrange
        when(qualityAuditSectionConfigurationRepo.saveAll(Mockito.<Iterable<QualityAuditSectionConfiguration>>any()))
                .thenReturn(new ArrayList<>());

        QualityAuditSectionConfiguration qualityAuditSectionConfiguration = new QualityAuditSectionConfiguration();
        qualityAuditSectionConfiguration.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        qualityAuditSectionConfiguration.setCreatedDate(mock(Timestamp.class));
        qualityAuditSectionConfiguration.setDeleted(true);
        qualityAuditSectionConfiguration.setLastModDate(mock(Timestamp.class));
        qualityAuditSectionConfiguration.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        qualityAuditSectionConfiguration.setPsmId(1);
        qualityAuditSectionConfiguration.setRank(1);
        qualityAuditSectionConfiguration.setSectionDesc("response");
        qualityAuditSectionConfiguration.setSectionId(1);
        qualityAuditSectionConfiguration.setSectionName("response");

        QualityAuditSectionConfiguration qualityAuditSectionConfiguration2 = new QualityAuditSectionConfiguration();
        qualityAuditSectionConfiguration2.setCreatedBy("response");
        qualityAuditSectionConfiguration2.setCreatedDate(mock(Timestamp.class));
        qualityAuditSectionConfiguration2.setDeleted(false);
        qualityAuditSectionConfiguration2.setLastModDate(mock(Timestamp.class));
        qualityAuditSectionConfiguration2.setModifiedBy("response");
        qualityAuditSectionConfiguration2.setPsmId(2);
        qualityAuditSectionConfiguration2.setRank(0);
        qualityAuditSectionConfiguration2.setSectionDesc("Section Configuration Created Successfully");
        qualityAuditSectionConfiguration2.setSectionId(2);
        qualityAuditSectionConfiguration2.setSectionName("Section Configuration Created Successfully");

        ArrayList<QualityAuditSectionConfiguration> qualityAuditSectionConfiguration3 = new ArrayList<>();
        qualityAuditSectionConfiguration3.add(qualityAuditSectionConfiguration2);
        qualityAuditSectionConfiguration3.add(qualityAuditSectionConfiguration);

        // Act
        String actualCreateQualityAuditSectionConfigurationResult = qualityAuditSectionConfigurationImpl
                .createQualityAuditSectionConfiguration(qualityAuditSectionConfiguration3);

        // Assert
        verify(qualityAuditSectionConfigurationRepo).saveAll(isA(Iterable.class));
        assertEquals("{\"response\":\"Section Configuration Created Successfully\"}",
                actualCreateQualityAuditSectionConfigurationResult);
    }

    /**
     * Method under test:
     * {@link QualityAuditSectionConfigurationImpl#createQualityAuditSectionConfiguration(List)}
     */
    @Test
    void testCreateQualityAuditSectionConfiguration4() {
        // Arrange
        when(qualityAuditSectionConfigurationRepo.saveAll(Mockito.<Iterable<QualityAuditSectionConfiguration>>any()))
                .thenThrow(new ECDException("response"));

        // Act and Assert
        assertThrows(ECDException.class,
                () -> qualityAuditSectionConfigurationImpl.createQualityAuditSectionConfiguration(new ArrayList<>()));
        verify(qualityAuditSectionConfigurationRepo).saveAll(isA(Iterable.class));
    }

    /**
     * Method under test:
     * {@link QualityAuditSectionConfigurationImpl#getQualityAuditSectionConfigurationByPSMId(Integer)}
     */
    @Test
    void testGetQualityAuditSectionConfigurationByPSMId() {
        // Arrange
        ArrayList<QualityAuditSectionConfiguration> qualityAuditSectionConfigurationList = new ArrayList<>();
        when(qualityAuditSectionConfigurationRepo.findByPsmIdOrderByLastModDateDesc(Mockito.<Integer>any()))
                .thenReturn(qualityAuditSectionConfigurationList);

        // Act
        List<QualityAuditSectionConfiguration> actualQualityAuditSectionConfigurationByPSMId = qualityAuditSectionConfigurationImpl
                .getQualityAuditSectionConfigurationByPSMId(1);

        // Assert
        verify(qualityAuditSectionConfigurationRepo).findByPsmIdOrderByLastModDateDesc(isA(Integer.class));
        assertTrue(actualQualityAuditSectionConfigurationByPSMId.isEmpty());
        assertSame(qualityAuditSectionConfigurationList, actualQualityAuditSectionConfigurationByPSMId);
    }

    /**
     * Method under test:
     * {@link QualityAuditSectionConfigurationImpl#getQualityAuditSectionConfigurationByPSMId(Integer)}
     */
    @Test
    void testGetQualityAuditSectionConfigurationByPSMId2() {
        // Arrange
        when(qualityAuditSectionConfigurationRepo.findByPsmIdOrderByLastModDateDesc(Mockito.<Integer>any()))
                .thenThrow(new ECDException("Exception Msg"));

        // Act and Assert
        assertThrows(ECDException.class,
                () -> qualityAuditSectionConfigurationImpl.getQualityAuditSectionConfigurationByPSMId(1));
        verify(qualityAuditSectionConfigurationRepo).findByPsmIdOrderByLastModDateDesc(isA(Integer.class));
    }

    /**
     * Method under test:
     * {@link QualityAuditSectionConfigurationImpl#updateQualityAuditSectionConfiguration(QualityAuditSectionConfiguration)}
     */
    @Test
    void testUpdateQualityAuditSectionConfiguration() {
        // Arrange
        QualityAuditSectionConfiguration qualityAuditSectionConfiguration = new QualityAuditSectionConfiguration();
        qualityAuditSectionConfiguration.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        qualityAuditSectionConfiguration.setCreatedDate(mock(Timestamp.class));
        qualityAuditSectionConfiguration.setDeleted(true);
        qualityAuditSectionConfiguration.setLastModDate(mock(Timestamp.class));
        qualityAuditSectionConfiguration.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        qualityAuditSectionConfiguration.setPsmId(1);
        qualityAuditSectionConfiguration.setRank(1);
        qualityAuditSectionConfiguration.setSectionDesc("Section Desc");
        qualityAuditSectionConfiguration.setSectionId(1);
        qualityAuditSectionConfiguration.setSectionName("Section Name");
        when(qualityAuditSectionConfigurationRepo.save(Mockito.<QualityAuditSectionConfiguration>any()))
                .thenReturn(qualityAuditSectionConfiguration);

        QualityAuditSectionConfiguration qualityAuditSectionConfiguration2 = new QualityAuditSectionConfiguration();
        qualityAuditSectionConfiguration2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        qualityAuditSectionConfiguration2.setCreatedDate(mock(Timestamp.class));
        qualityAuditSectionConfiguration2.setDeleted(true);
        qualityAuditSectionConfiguration2.setLastModDate(mock(Timestamp.class));
        qualityAuditSectionConfiguration2.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        qualityAuditSectionConfiguration2.setPsmId(1);
        qualityAuditSectionConfiguration2.setRank(1);
        qualityAuditSectionConfiguration2.setSectionDesc("Section Desc");
        qualityAuditSectionConfiguration2.setSectionId(1);
        qualityAuditSectionConfiguration2.setSectionName("Section Name");

        // Act
        String actualUpdateQualityAuditSectionConfigurationResult = qualityAuditSectionConfigurationImpl
                .updateQualityAuditSectionConfiguration(qualityAuditSectionConfiguration2);

        // Assert
        verify(qualityAuditSectionConfigurationRepo).save(isA(QualityAuditSectionConfiguration.class));
        assertEquals("{\"response\":\"Agent Quality Auditor Mapping Updated Successfully\"}",
                actualUpdateQualityAuditSectionConfigurationResult);
    }

    /**
     * Method under test:
     * {@link QualityAuditSectionConfigurationImpl#updateQualityAuditSectionConfiguration(QualityAuditSectionConfiguration)}
     */
    @Test
    void testUpdateQualityAuditSectionConfiguration2() {
        // Arrange
        QualityAuditSectionConfiguration qualityAuditSectionConfiguration = new QualityAuditSectionConfiguration();
        qualityAuditSectionConfiguration.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        qualityAuditSectionConfiguration.setCreatedDate(mock(Timestamp.class));
        qualityAuditSectionConfiguration.setDeleted(true);
        qualityAuditSectionConfiguration.setLastModDate(mock(Timestamp.class));
        qualityAuditSectionConfiguration.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        qualityAuditSectionConfiguration.setPsmId(1);
        qualityAuditSectionConfiguration.setRank(1);
        qualityAuditSectionConfiguration.setSectionDesc("Section Desc");
        qualityAuditSectionConfiguration.setSectionId(null);
        qualityAuditSectionConfiguration.setSectionName("Section Name");

        // Act and Assert
        assertEquals("{\"response\":\"Agent Quality Auditor Mapping Updated Successfully\"}",
                qualityAuditSectionConfigurationImpl.updateQualityAuditSectionConfiguration(qualityAuditSectionConfiguration));
    }

    /**
     * Method under test:
     * {@link QualityAuditSectionConfigurationImpl#updateQualityAuditSectionConfiguration(QualityAuditSectionConfiguration)}
     */
    @Test
    void testUpdateQualityAuditSectionConfiguration3() {
        // Arrange
        when(qualityAuditSectionConfigurationRepo.save(Mockito.<QualityAuditSectionConfiguration>any()))
                .thenThrow(new ECDException("response"));

        QualityAuditSectionConfiguration qualityAuditSectionConfiguration = new QualityAuditSectionConfiguration();
        qualityAuditSectionConfiguration.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        qualityAuditSectionConfiguration.setCreatedDate(mock(Timestamp.class));
        qualityAuditSectionConfiguration.setDeleted(true);
        qualityAuditSectionConfiguration.setLastModDate(mock(Timestamp.class));
        qualityAuditSectionConfiguration.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        qualityAuditSectionConfiguration.setPsmId(1);
        qualityAuditSectionConfiguration.setRank(1);
        qualityAuditSectionConfiguration.setSectionDesc("Section Desc");
        qualityAuditSectionConfiguration.setSectionId(1);
        qualityAuditSectionConfiguration.setSectionName("Section Name");

        // Act and Assert
        assertThrows(ECDException.class, () -> qualityAuditSectionConfigurationImpl
                .updateQualityAuditSectionConfiguration(qualityAuditSectionConfiguration));
        verify(qualityAuditSectionConfigurationRepo).save(isA(QualityAuditSectionConfiguration.class));
    }
}
