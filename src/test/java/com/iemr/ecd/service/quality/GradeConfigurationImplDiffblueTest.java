package com.iemr.ecd.service.quality;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.iemr.ecd.dao.GradeConfiguration;
import com.iemr.ecd.repo.call_conf_allocation.GradeConfigurationRepo;
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

@ContextConfiguration(classes = {GradeConfigurationImpl.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class GradeConfigurationImplDiffblueTest {
    @Autowired
    private GradeConfigurationImpl gradeConfigurationImpl;

    @MockBean
    private GradeConfigurationRepo gradeConfigurationRepo;

    /**
     * Method under test:
     * {@link GradeConfigurationImpl#createGradeConfiguration(List)}
     */
    @Test
    void testCreateGradeConfiguration() {
        // Arrange
        when(gradeConfigurationRepo.saveAll(Mockito.<Iterable<GradeConfiguration>>any())).thenReturn(new ArrayList<>());

        // Act
        String actualCreateGradeConfigurationResult = gradeConfigurationImpl.createGradeConfiguration(new ArrayList<>());

        // Assert
        verify(gradeConfigurationRepo).saveAll(isA(Iterable.class));
        assertEquals("{\"response\":\"Grade Configuration Created Successfully\"}", actualCreateGradeConfigurationResult);
    }

    /**
     * Method under test:
     * {@link GradeConfigurationImpl#createGradeConfiguration(List)}
     */
    @Test
    void testCreateGradeConfiguration2() {
        // Arrange
        when(gradeConfigurationRepo.saveAll(Mockito.<Iterable<GradeConfiguration>>any())).thenReturn(new ArrayList<>());

        GradeConfiguration gradeConfiguration = new GradeConfiguration();
        gradeConfiguration.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        gradeConfiguration.setCreatedDate(mock(Timestamp.class));
        gradeConfiguration.setDeleted(true);
        gradeConfiguration.setGrade("response");
        gradeConfiguration.setGradeId(1);
        gradeConfiguration.setId(1);
        gradeConfiguration.setLastModDate(mock(Timestamp.class));
        gradeConfiguration.setMaxValue(10.0d);
        gradeConfiguration.setMinValue(10.0d);
        gradeConfiguration.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        gradeConfiguration.setPsmId(1);
        
        gradeConfiguration.toString();

        ArrayList<GradeConfiguration> gradeConfiguration2 = new ArrayList<>();
        gradeConfiguration2.add(gradeConfiguration);

        // Act
        String actualCreateGradeConfigurationResult = gradeConfigurationImpl.createGradeConfiguration(gradeConfiguration2);

        // Assert
        verify(gradeConfigurationRepo).saveAll(isA(Iterable.class));
        assertEquals("{\"response\":\"Grade Configuration Created Successfully\"}", actualCreateGradeConfigurationResult);
    }

    /**
     * Method under test:
     * {@link GradeConfigurationImpl#createGradeConfiguration(List)}
     */
    @Test
    void testCreateGradeConfiguration3() {
        // Arrange
        when(gradeConfigurationRepo.saveAll(Mockito.<Iterable<GradeConfiguration>>any())).thenReturn(new ArrayList<>());

        GradeConfiguration gradeConfiguration = new GradeConfiguration();
        gradeConfiguration.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        gradeConfiguration.setCreatedDate(mock(Timestamp.class));
        gradeConfiguration.setDeleted(true);
        gradeConfiguration.setGrade("response");
        gradeConfiguration.setGradeId(1);
        gradeConfiguration.setId(1);
        gradeConfiguration.setLastModDate(mock(Timestamp.class));
        gradeConfiguration.setMaxValue(10.0d);
        gradeConfiguration.setMinValue(10.0d);
        gradeConfiguration.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        gradeConfiguration.setPsmId(1);

        GradeConfiguration gradeConfiguration2 = new GradeConfiguration();
        gradeConfiguration2.setCreatedBy("response");
        gradeConfiguration2.setCreatedDate(mock(Timestamp.class));
        gradeConfiguration2.setDeleted(false);
        gradeConfiguration2.setGrade("Grade Configuration Created Successfully");
        gradeConfiguration2.setGradeId(2);
        gradeConfiguration2.setId(2);
        gradeConfiguration2.setLastModDate(mock(Timestamp.class));
        gradeConfiguration2.setMaxValue(0.5d);
        gradeConfiguration2.setMinValue(0.5d);
        gradeConfiguration2.setModifiedBy("response");
        gradeConfiguration2.setPsmId(2);

        ArrayList<GradeConfiguration> gradeConfiguration3 = new ArrayList<>();
        gradeConfiguration3.add(gradeConfiguration2);
        gradeConfiguration3.add(gradeConfiguration);

        // Act
        String actualCreateGradeConfigurationResult = gradeConfigurationImpl.createGradeConfiguration(gradeConfiguration3);

        // Assert
        verify(gradeConfigurationRepo).saveAll(isA(Iterable.class));
        assertEquals("{\"response\":\"Grade Configuration Created Successfully\"}", actualCreateGradeConfigurationResult);
    }

    /**
     * Method under test:
     * {@link GradeConfigurationImpl#createGradeConfiguration(List)}
     */
    @Test
    void testCreateGradeConfiguration4() {
        // Arrange
        when(gradeConfigurationRepo.saveAll(Mockito.<Iterable<GradeConfiguration>>any()))
                .thenThrow(new ECDException("response"));

        // Act and Assert
        assertThrows(ECDException.class, () -> gradeConfigurationImpl.createGradeConfiguration(new ArrayList<>()));
        verify(gradeConfigurationRepo).saveAll(isA(Iterable.class));
    }

    /**
     * Method under test:
     * {@link GradeConfigurationImpl#getGradeConfigurationByPSMId(Integer)}
     */
    @Test
    void testGetGradeConfigurationByPSMId() {
        // Arrange
        ArrayList<GradeConfiguration> gradeConfigurationList = new ArrayList<>();
        when(gradeConfigurationRepo.findByPsmIdOrderByLastModDateDesc(Mockito.<Integer>any()))
                .thenReturn(gradeConfigurationList);

        // Act
        List<GradeConfiguration> actualGradeConfigurationByPSMId = gradeConfigurationImpl.getGradeConfigurationByPSMId(1);

        // Assert
        verify(gradeConfigurationRepo).findByPsmIdOrderByLastModDateDesc(isA(Integer.class));
        assertTrue(actualGradeConfigurationByPSMId.isEmpty());
        assertSame(gradeConfigurationList, actualGradeConfigurationByPSMId);
    }

    /**
     * Method under test:
     * {@link GradeConfigurationImpl#getGradeConfigurationByPSMId(Integer)}
     */
    @Test
    void testGetGradeConfigurationByPSMId2() {
        // Arrange
        when(gradeConfigurationRepo.findByPsmIdOrderByLastModDateDesc(Mockito.<Integer>any()))
                .thenThrow(new ECDException("Exception Msg"));

        // Act and Assert
        assertThrows(ECDException.class, () -> gradeConfigurationImpl.getGradeConfigurationByPSMId(1));
        verify(gradeConfigurationRepo).findByPsmIdOrderByLastModDateDesc(isA(Integer.class));
    }

    /**
     * Method under test:
     * {@link GradeConfigurationImpl#updateGradeConfiguration(GradeConfiguration)}
     */
    @Test
    void testUpdateGradeConfiguration() {
        // Arrange
        GradeConfiguration gradeConfiguration = new GradeConfiguration();
        gradeConfiguration.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        gradeConfiguration.setCreatedDate(mock(Timestamp.class));
        gradeConfiguration.setDeleted(true);
        gradeConfiguration.setGrade("Grade");
        gradeConfiguration.setGradeId(1);
        gradeConfiguration.setId(1);
        gradeConfiguration.setLastModDate(mock(Timestamp.class));
        gradeConfiguration.setMaxValue(10.0d);
        gradeConfiguration.setMinValue(10.0d);
        gradeConfiguration.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        gradeConfiguration.setPsmId(1);
        when(gradeConfigurationRepo.save(Mockito.<GradeConfiguration>any())).thenReturn(gradeConfiguration);

        GradeConfiguration gradeConfiguration2 = new GradeConfiguration();
        gradeConfiguration2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        gradeConfiguration2.setCreatedDate(mock(Timestamp.class));
        gradeConfiguration2.setDeleted(true);
        gradeConfiguration2.setGrade("Grade");
        gradeConfiguration2.setGradeId(1);
        gradeConfiguration2.setId(1);
        gradeConfiguration2.setLastModDate(mock(Timestamp.class));
        gradeConfiguration2.setMaxValue(10.0d);
        gradeConfiguration2.setMinValue(10.0d);
        gradeConfiguration2.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        gradeConfiguration2.setPsmId(1);

        // Act
        String actualUpdateGradeConfigurationResult = gradeConfigurationImpl.updateGradeConfiguration(gradeConfiguration2);

        // Assert
        verify(gradeConfigurationRepo).save(isA(GradeConfiguration.class));
        assertEquals("{\"response\":\"Grade Configuration Updated Successfully\"}", actualUpdateGradeConfigurationResult);
    }

    /**
     * Method under test:
     * {@link GradeConfigurationImpl#updateGradeConfiguration(GradeConfiguration)}
     */
    @Test
    void testUpdateGradeConfiguration2() {
        // Arrange
        GradeConfiguration gradeConfiguration = new GradeConfiguration();
        gradeConfiguration.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        gradeConfiguration.setCreatedDate(mock(Timestamp.class));
        gradeConfiguration.setDeleted(true);
        gradeConfiguration.setGrade("Grade");
        gradeConfiguration.setGradeId(1);
        gradeConfiguration.setId(null);
        gradeConfiguration.setLastModDate(mock(Timestamp.class));
        gradeConfiguration.setMaxValue(10.0d);
        gradeConfiguration.setMinValue(10.0d);
        gradeConfiguration.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        gradeConfiguration.setPsmId(1);

        // Act and Assert
        assertEquals("{\"response\":\"Grade Configuration Updated Successfully\"}",
                gradeConfigurationImpl.updateGradeConfiguration(gradeConfiguration));
    }

    /**
     * Method under test:
     * {@link GradeConfigurationImpl#updateGradeConfiguration(GradeConfiguration)}
     */
    @Test
    void testUpdateGradeConfiguration3() {
        // Arrange
        when(gradeConfigurationRepo.save(Mockito.<GradeConfiguration>any())).thenThrow(new ECDException("response"));

        GradeConfiguration gradeConfiguration = new GradeConfiguration();
        gradeConfiguration.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        gradeConfiguration.setCreatedDate(mock(Timestamp.class));
        gradeConfiguration.setDeleted(true);
        gradeConfiguration.setGrade("Grade");
        gradeConfiguration.setGradeId(1);
        gradeConfiguration.setId(1);
        gradeConfiguration.setLastModDate(mock(Timestamp.class));
        gradeConfiguration.setMaxValue(10.0d);
        gradeConfiguration.setMinValue(10.0d);
        gradeConfiguration.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        gradeConfiguration.setPsmId(1);

        // Act and Assert
        assertThrows(ECDException.class, () -> gradeConfigurationImpl.updateGradeConfiguration(gradeConfiguration));
        verify(gradeConfigurationRepo).save(isA(GradeConfiguration.class));
    }
}
