package com.iemr.ecd.service.quality;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.iemr.ecd.dao.SampleSelectionConfiguration;
import com.iemr.ecd.repo.call_conf_allocation.SampleSelectionConfigurationRepo;
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

@ContextConfiguration(classes = {SampleSelectionConfigurationImpl.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class SampleSelectionConfigurationImplDiffblueTest {
    @Autowired
    private SampleSelectionConfigurationImpl sampleSelectionConfigurationImpl;

    @MockBean
    private SampleSelectionConfigurationRepo sampleSelectionConfigurationRepo;

    /**
     * Method under test:
     * {@link SampleSelectionConfigurationImpl#createSampleSelectionConfiguration(List)}
     */
    @Test
    void testCreateSampleSelectionConfiguration() {
        // Arrange
        when(sampleSelectionConfigurationRepo.saveAll(Mockito.<Iterable<SampleSelectionConfiguration>>any()))
                .thenReturn(new ArrayList<>());

        // Act
        String actualCreateSampleSelectionConfigurationResult = sampleSelectionConfigurationImpl
                .createSampleSelectionConfiguration(new ArrayList<>());

        // Assert
        verify(sampleSelectionConfigurationRepo).saveAll(isA(Iterable.class));
        assertEquals("{\"response\":\"Sample Selection Configuration Created Successfully\"}",
                actualCreateSampleSelectionConfigurationResult);
    }

    /**
     * Method under test:
     * {@link SampleSelectionConfigurationImpl#createSampleSelectionConfiguration(List)}
     */
    @Test
    void testCreateSampleSelectionConfiguration2() {
        // Arrange
        when(sampleSelectionConfigurationRepo.saveAll(Mockito.<Iterable<SampleSelectionConfiguration>>any()))
                .thenReturn(new ArrayList<>());

        SampleSelectionConfiguration sampleSelectionConfiguration = new SampleSelectionConfiguration();
        sampleSelectionConfiguration.setAncSampleSize(3);
        sampleSelectionConfiguration.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        sampleSelectionConfiguration.setCreatedDate(mock(Timestamp.class));
        sampleSelectionConfiguration.setCycleId(1);
        sampleSelectionConfiguration.setCycleName("response");
        sampleSelectionConfiguration.setDeleted(true);
        sampleSelectionConfiguration.setFromDay(1);
        sampleSelectionConfiguration.setId(1);
        sampleSelectionConfiguration.setLastModDate(mock(Timestamp.class));
        sampleSelectionConfiguration.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        sampleSelectionConfiguration.setPncSampleSize(3);
        sampleSelectionConfiguration.setPsmId(1);
        sampleSelectionConfiguration.setToDay(1);
        sampleSelectionConfiguration.setTotalSampleSize(3);
        
        sampleSelectionConfiguration.toString();

        ArrayList<SampleSelectionConfiguration> sampleSelectionConfiguration2 = new ArrayList<>();
        sampleSelectionConfiguration2.add(sampleSelectionConfiguration);

        // Act
        String actualCreateSampleSelectionConfigurationResult = sampleSelectionConfigurationImpl
                .createSampleSelectionConfiguration(sampleSelectionConfiguration2);

        // Assert
        verify(sampleSelectionConfigurationRepo).saveAll(isA(Iterable.class));
        assertEquals("{\"response\":\"Sample Selection Configuration Created Successfully\"}",
                actualCreateSampleSelectionConfigurationResult);
    }

    /**
     * Method under test:
     * {@link SampleSelectionConfigurationImpl#createSampleSelectionConfiguration(List)}
     */
    @Test
    void testCreateSampleSelectionConfiguration3() {
        // Arrange
        when(sampleSelectionConfigurationRepo.saveAll(Mockito.<Iterable<SampleSelectionConfiguration>>any()))
                .thenReturn(new ArrayList<>());

        SampleSelectionConfiguration sampleSelectionConfiguration = new SampleSelectionConfiguration();
        sampleSelectionConfiguration.setAncSampleSize(3);
        sampleSelectionConfiguration.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        sampleSelectionConfiguration.setCreatedDate(mock(Timestamp.class));
        sampleSelectionConfiguration.setCycleId(1);
        sampleSelectionConfiguration.setCycleName("response");
        sampleSelectionConfiguration.setDeleted(true);
        sampleSelectionConfiguration.setFromDay(1);
        sampleSelectionConfiguration.setId(1);
        sampleSelectionConfiguration.setLastModDate(mock(Timestamp.class));
        sampleSelectionConfiguration.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        sampleSelectionConfiguration.setPncSampleSize(3);
        sampleSelectionConfiguration.setPsmId(1);
        sampleSelectionConfiguration.setToDay(1);
        sampleSelectionConfiguration.setTotalSampleSize(3);

        SampleSelectionConfiguration sampleSelectionConfiguration2 = new SampleSelectionConfiguration();
        sampleSelectionConfiguration2.setAncSampleSize(1);
        sampleSelectionConfiguration2.setCreatedBy("response");
        sampleSelectionConfiguration2.setCreatedDate(mock(Timestamp.class));
        sampleSelectionConfiguration2.setCycleId(2);
        sampleSelectionConfiguration2.setCycleName("Sample Selection Configuration Created Successfully");
        sampleSelectionConfiguration2.setDeleted(false);
        sampleSelectionConfiguration2.setFromDay(0);
        sampleSelectionConfiguration2.setId(2);
        sampleSelectionConfiguration2.setLastModDate(mock(Timestamp.class));
        sampleSelectionConfiguration2.setModifiedBy("response");
        sampleSelectionConfiguration2.setPncSampleSize(1);
        sampleSelectionConfiguration2.setPsmId(2);
        sampleSelectionConfiguration2.setToDay(0);
        sampleSelectionConfiguration2.setTotalSampleSize(1);

        ArrayList<SampleSelectionConfiguration> sampleSelectionConfiguration3 = new ArrayList<>();
        sampleSelectionConfiguration3.add(sampleSelectionConfiguration2);
        sampleSelectionConfiguration3.add(sampleSelectionConfiguration);

        // Act
        String actualCreateSampleSelectionConfigurationResult = sampleSelectionConfigurationImpl
                .createSampleSelectionConfiguration(sampleSelectionConfiguration3);

        // Assert
        verify(sampleSelectionConfigurationRepo).saveAll(isA(Iterable.class));
        assertEquals("{\"response\":\"Sample Selection Configuration Created Successfully\"}",
                actualCreateSampleSelectionConfigurationResult);
    }

    /**
     * Method under test:
     * {@link SampleSelectionConfigurationImpl#createSampleSelectionConfiguration(List)}
     */
    @Test
    void testCreateSampleSelectionConfiguration4() {
        // Arrange
        when(sampleSelectionConfigurationRepo.saveAll(Mockito.<Iterable<SampleSelectionConfiguration>>any()))
                .thenThrow(new ECDException("response"));

        // Act and Assert
        assertThrows(ECDException.class,
                () -> sampleSelectionConfigurationImpl.createSampleSelectionConfiguration(new ArrayList<>()));
        verify(sampleSelectionConfigurationRepo).saveAll(isA(Iterable.class));
    }

    /**
     * Method under test:
     * {@link SampleSelectionConfigurationImpl#getSampleSelectionConfigurationByPSMId(Integer)}
     */
    @Test
    void testGetSampleSelectionConfigurationByPSMId() {
        // Arrange
        ArrayList<SampleSelectionConfiguration> sampleSelectionConfigurationList = new ArrayList<>();
        when(sampleSelectionConfigurationRepo.findByPsmIdOrderByLastModDateDesc(Mockito.<Integer>any()))
                .thenReturn(sampleSelectionConfigurationList);

        // Act
        List<SampleSelectionConfiguration> actualSampleSelectionConfigurationByPSMId = sampleSelectionConfigurationImpl
                .getSampleSelectionConfigurationByPSMId(1);

        // Assert
        verify(sampleSelectionConfigurationRepo).findByPsmIdOrderByLastModDateDesc(isA(Integer.class));
        assertTrue(actualSampleSelectionConfigurationByPSMId.isEmpty());
        assertSame(sampleSelectionConfigurationList, actualSampleSelectionConfigurationByPSMId);
    }

    /**
     * Method under test:
     * {@link SampleSelectionConfigurationImpl#getSampleSelectionConfigurationByPSMId(Integer)}
     */
    @Test
    void testGetSampleSelectionConfigurationByPSMId2() {
        // Arrange
        when(sampleSelectionConfigurationRepo.findByPsmIdOrderByLastModDateDesc(Mockito.<Integer>any()))
                .thenThrow(new ECDException("Exception Msg"));

        // Act and Assert
        assertThrows(ECDException.class, () -> sampleSelectionConfigurationImpl.getSampleSelectionConfigurationByPSMId(1));
        verify(sampleSelectionConfigurationRepo).findByPsmIdOrderByLastModDateDesc(isA(Integer.class));
    }

    /**
     * Method under test:
     * {@link SampleSelectionConfigurationImpl#updateSampleSelectionConfiguration(SampleSelectionConfiguration)}
     */
    @Test
    void testUpdateSampleSelectionConfiguration() {
        // Arrange
        SampleSelectionConfiguration sampleSelectionConfiguration = new SampleSelectionConfiguration();
        sampleSelectionConfiguration.setAncSampleSize(3);
        sampleSelectionConfiguration.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        sampleSelectionConfiguration.setCreatedDate(mock(Timestamp.class));
        sampleSelectionConfiguration.setCycleId(1);
        sampleSelectionConfiguration.setCycleName("Cycle Name");
        sampleSelectionConfiguration.setDeleted(true);
        sampleSelectionConfiguration.setFromDay(1);
        sampleSelectionConfiguration.setId(1);
        sampleSelectionConfiguration.setLastModDate(mock(Timestamp.class));
        sampleSelectionConfiguration.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        sampleSelectionConfiguration.setPncSampleSize(3);
        sampleSelectionConfiguration.setPsmId(1);
        sampleSelectionConfiguration.setToDay(1);
        sampleSelectionConfiguration.setTotalSampleSize(3);
        when(sampleSelectionConfigurationRepo.save(Mockito.<SampleSelectionConfiguration>any()))
                .thenReturn(sampleSelectionConfiguration);

        SampleSelectionConfiguration sampleSelectionConfiguration2 = new SampleSelectionConfiguration();
        sampleSelectionConfiguration2.setAncSampleSize(3);
        sampleSelectionConfiguration2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        sampleSelectionConfiguration2.setCreatedDate(mock(Timestamp.class));
        sampleSelectionConfiguration2.setCycleId(1);
        sampleSelectionConfiguration2.setCycleName("Cycle Name");
        sampleSelectionConfiguration2.setDeleted(true);
        sampleSelectionConfiguration2.setFromDay(1);
        sampleSelectionConfiguration2.setId(1);
        sampleSelectionConfiguration2.setLastModDate(mock(Timestamp.class));
        sampleSelectionConfiguration2.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        sampleSelectionConfiguration2.setPncSampleSize(3);
        sampleSelectionConfiguration2.setPsmId(1);
        sampleSelectionConfiguration2.setToDay(1);
        sampleSelectionConfiguration2.setTotalSampleSize(3);

        // Act
        String actualUpdateSampleSelectionConfigurationResult = sampleSelectionConfigurationImpl
                .updateSampleSelectionConfiguration(sampleSelectionConfiguration2);

        // Assert
        verify(sampleSelectionConfigurationRepo).save(isA(SampleSelectionConfiguration.class));
        assertEquals("{\"response\":\"Sample Selection Configuration Upated Sucessfully\"}",
                actualUpdateSampleSelectionConfigurationResult);
    }

    /**
     * Method under test:
     * {@link SampleSelectionConfigurationImpl#updateSampleSelectionConfiguration(SampleSelectionConfiguration)}
     */
    @Test
    void testUpdateSampleSelectionConfiguration2() {
        // Arrange
        SampleSelectionConfiguration sampleSelectionConfiguration = new SampleSelectionConfiguration();
        sampleSelectionConfiguration.setAncSampleSize(3);
        sampleSelectionConfiguration.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        sampleSelectionConfiguration.setCreatedDate(mock(Timestamp.class));
        sampleSelectionConfiguration.setCycleId(1);
        sampleSelectionConfiguration.setCycleName("Cycle Name");
        sampleSelectionConfiguration.setDeleted(true);
        sampleSelectionConfiguration.setFromDay(1);
        sampleSelectionConfiguration.setId(null);
        sampleSelectionConfiguration.setLastModDate(mock(Timestamp.class));
        sampleSelectionConfiguration.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        sampleSelectionConfiguration.setPncSampleSize(3);
        sampleSelectionConfiguration.setPsmId(1);
        sampleSelectionConfiguration.setToDay(1);
        sampleSelectionConfiguration.setTotalSampleSize(3);

        // Act and Assert
        assertEquals("{\"response\":\"Sample Selection Configuration Upated Sucessfully\"}",
                sampleSelectionConfigurationImpl.updateSampleSelectionConfiguration(sampleSelectionConfiguration));
    }

    /**
     * Method under test:
     * {@link SampleSelectionConfigurationImpl#updateSampleSelectionConfiguration(SampleSelectionConfiguration)}
     */
    @Test
    void testUpdateSampleSelectionConfiguration3() {
        // Arrange
        when(sampleSelectionConfigurationRepo.save(Mockito.<SampleSelectionConfiguration>any()))
                .thenThrow(new ECDException("response"));

        SampleSelectionConfiguration sampleSelectionConfiguration = new SampleSelectionConfiguration();
        sampleSelectionConfiguration.setAncSampleSize(3);
        sampleSelectionConfiguration.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        sampleSelectionConfiguration.setCreatedDate(mock(Timestamp.class));
        sampleSelectionConfiguration.setCycleId(1);
        sampleSelectionConfiguration.setCycleName("Cycle Name");
        sampleSelectionConfiguration.setDeleted(true);
        sampleSelectionConfiguration.setFromDay(1);
        sampleSelectionConfiguration.setId(1);
        sampleSelectionConfiguration.setLastModDate(mock(Timestamp.class));
        sampleSelectionConfiguration.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        sampleSelectionConfiguration.setPncSampleSize(3);
        sampleSelectionConfiguration.setPsmId(1);
        sampleSelectionConfiguration.setToDay(1);
        sampleSelectionConfiguration.setTotalSampleSize(3);

        // Act and Assert
        assertThrows(ECDException.class,
                () -> sampleSelectionConfigurationImpl.updateSampleSelectionConfiguration(sampleSelectionConfiguration));
        verify(sampleSelectionConfigurationRepo).save(isA(SampleSelectionConfiguration.class));
    }
}
