package com.iemr.ecd.service.quality;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.iemr.ecd.dto.ResponseFetchQualityChartsDataDTO;
import com.iemr.ecd.repository.quality.QualityAuditorRatingRepo;
import com.iemr.ecd.utils.advice.exception_handler.ECDException;

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

@ContextConfiguration(classes = {ChartsImpl.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class ChartsImplDiffblueTest {
    @Autowired
    private ChartsImpl chartsImpl;

    @MockBean
    private QualityAuditorRatingRepo qualityAuditorRatingRepo;

    /**
     * Method under test:
     * {@link ChartsImpl#getTrendAnalysisOfCentreOverallQualityRatings(Integer, String, String)}
     */
    @Test
    void testGetTrendAnalysisOfCentreOverallQualityRatings() {
        // Arrange
        when(qualityAuditorRatingRepo.getTrendAnalysisOfCentreOverallQualityRatings(Mockito.<Integer>any(),
                Mockito.<String>any(), Mockito.<String>any())).thenReturn(new ArrayList<>());

        // Act
        List<ResponseFetchQualityChartsDataDTO> actualTrendAnalysisOfCentreOverallQualityRatings = chartsImpl
                .getTrendAnalysisOfCentreOverallQualityRatings(1, "Frequency", "Month");

        // Assert
        verify(qualityAuditorRatingRepo).getTrendAnalysisOfCentreOverallQualityRatings(isA(Integer.class), eq("Frequency"),
                eq("Month"));
        assertTrue(actualTrendAnalysisOfCentreOverallQualityRatings.isEmpty());
    }

    /**
     * Method under test:
     * {@link ChartsImpl#getTrendAnalysisOfCentreOverallQualityRatings(Integer, String, String)}
     */
    @Test
    void testGetTrendAnalysisOfCentreOverallQualityRatings2() {
        // Arrange
        ArrayList<String[]> stringArrayList = new ArrayList<>();
        stringArrayList.add(new String[]{"foo"});
        when(qualityAuditorRatingRepo.getTrendAnalysisOfCentreOverallQualityRatings(Mockito.<Integer>any(),
                Mockito.<String>any(), Mockito.<String>any())).thenReturn(stringArrayList);

        // Act
        List<ResponseFetchQualityChartsDataDTO> actualTrendAnalysisOfCentreOverallQualityRatings = chartsImpl
                .getTrendAnalysisOfCentreOverallQualityRatings(1, "Frequency", "Month");

        // Assert
        verify(qualityAuditorRatingRepo).getTrendAnalysisOfCentreOverallQualityRatings(isA(Integer.class), eq("Frequency"),
                eq("Month"));
        assertTrue(actualTrendAnalysisOfCentreOverallQualityRatings.isEmpty());
    }

    /**
     * Method under test:
     * {@link ChartsImpl#getTrendAnalysisOfCentreOverallQualityRatings(Integer, String, String)}
     */
    @Test
    void testGetTrendAnalysisOfCentreOverallQualityRatings3() {
        // Arrange
        when(qualityAuditorRatingRepo.getTrendAnalysisOfCentreOverallQualityRatings(Mockito.<Integer>any(),
                Mockito.<String>any(), Mockito.<String>any())).thenThrow(new ECDException("Exception Msg"));

        // Act and Assert
        assertThrows(ECDException.class,
                () -> chartsImpl.getTrendAnalysisOfCentreOverallQualityRatings(1, "Frequency", "Month"));
        verify(qualityAuditorRatingRepo).getTrendAnalysisOfCentreOverallQualityRatings(isA(Integer.class), eq("Frequency"),
                eq("Month"));
    }

    /**
     * Method under test:
     * {@link ChartsImpl#getTrendAnalysisOfCentreOverallQualityRatings(Integer, String, String)}
     */
    @Test
    void testGetTrendAnalysisOfCentreOverallQualityRatings4() {
        // Arrange
        ArrayList<String[]> stringArrayList = new ArrayList<>();
        stringArrayList.add(new String[]{"foo", null});
        when(qualityAuditorRatingRepo.getTrendAnalysisOfCentreOverallQualityRatings(Mockito.<Integer>any(),
                Mockito.<String>any(), Mockito.<String>any())).thenReturn(stringArrayList);

        // Act
        List<ResponseFetchQualityChartsDataDTO> actualTrendAnalysisOfCentreOverallQualityRatings = chartsImpl
                .getTrendAnalysisOfCentreOverallQualityRatings(1, "Frequency", "Month");

        // Assert
        verify(qualityAuditorRatingRepo).getTrendAnalysisOfCentreOverallQualityRatings(isA(Integer.class), eq("Frequency"),
                eq("Month"));
        assertEquals(1, actualTrendAnalysisOfCentreOverallQualityRatings.size());
        assertEquals("foo", actualTrendAnalysisOfCentreOverallQualityRatings.get(0).getName());
    }

    /**
     * Method under test:
     * {@link ChartsImpl#getTrendAnalysisOfCentreOverallQualityRatings(Integer, String, String)}
     */
    @Test
    void testGetTrendAnalysisOfCentreOverallQualityRatings5() {
        // Arrange
        ArrayList<String[]> stringArrayList = new ArrayList<>();
        stringArrayList.add(new String[]{"foo", null, "foo"});
        when(qualityAuditorRatingRepo.getTrendAnalysisOfCentreOverallQualityRatings(Mockito.<Integer>any(),
                Mockito.<String>any(), Mockito.<String>any())).thenReturn(stringArrayList);

        // Act and Assert
        assertThrows(ECDException.class,
                () -> chartsImpl.getTrendAnalysisOfCentreOverallQualityRatings(1, "Frequency", "Month"));
        verify(qualityAuditorRatingRepo).getTrendAnalysisOfCentreOverallQualityRatings(isA(Integer.class), eq("Frequency"),
                eq("Month"));
    }

    /**
     * Method under test:
     * {@link ChartsImpl#getTrendAnalysisOfCentreOverallQualityRatings(Integer, String, String)}
     */
    @Test
    void testGetTrendAnalysisOfCentreOverallQualityRatings6() {
        // Arrange
        ArrayList<String[]> stringArrayList = new ArrayList<>();
        stringArrayList.add(new String[]{null, null});
        when(qualityAuditorRatingRepo.getTrendAnalysisOfCentreOverallQualityRatings(Mockito.<Integer>any(),
                Mockito.<String>any(), Mockito.<String>any())).thenReturn(stringArrayList);

        // Act
        List<ResponseFetchQualityChartsDataDTO> actualTrendAnalysisOfCentreOverallQualityRatings = chartsImpl
                .getTrendAnalysisOfCentreOverallQualityRatings(1, "Frequency", "Month");

        // Assert
        verify(qualityAuditorRatingRepo).getTrendAnalysisOfCentreOverallQualityRatings(isA(Integer.class), eq("Frequency"),
                eq("Month"));
        assertTrue(actualTrendAnalysisOfCentreOverallQualityRatings.isEmpty());
    }

    /**
     * Method under test:
     * {@link ChartsImpl#getTrendAnalysisOfCentreOverallQualityRatings(Integer, String, String)}
     */
    @Test
    void testGetTrendAnalysisOfCentreOverallQualityRatings7() {
        // Arrange
        ArrayList<String[]> stringArrayList = new ArrayList<>();
        stringArrayList.add(new String[]{"foo", "foo"});
        when(qualityAuditorRatingRepo.getTrendAnalysisOfCentreOverallQualityRatings(Mockito.<Integer>any(),
                Mockito.<String>any(), Mockito.<String>any())).thenReturn(stringArrayList);

        // Act and Assert
        assertThrows(ECDException.class,
                () -> chartsImpl.getTrendAnalysisOfCentreOverallQualityRatings(1, "Frequency", "Month"));
        verify(qualityAuditorRatingRepo).getTrendAnalysisOfCentreOverallQualityRatings(isA(Integer.class), eq("Frequency"),
                eq("Month"));
    }

    /**
     * Method under test:
     * {@link ChartsImpl#getTrendAnalysisOfCentreOverallQualityRatings(Integer, String, String)}
     */
    @Test
    void testGetTrendAnalysisOfCentreOverallQualityRatings8() {
        // Arrange
        ArrayList<String[]> stringArrayList = new ArrayList<>();
        stringArrayList.add(new String[]{"foo", "42"});
        when(qualityAuditorRatingRepo.getTrendAnalysisOfCentreOverallQualityRatings(Mockito.<Integer>any(),
                Mockito.<String>any(), Mockito.<String>any())).thenReturn(stringArrayList);

        // Act
        List<ResponseFetchQualityChartsDataDTO> actualTrendAnalysisOfCentreOverallQualityRatings = chartsImpl
                .getTrendAnalysisOfCentreOverallQualityRatings(1, "Frequency", "Month");

        // Assert
        verify(qualityAuditorRatingRepo).getTrendAnalysisOfCentreOverallQualityRatings(isA(Integer.class), eq("Frequency"),
                eq("Month"));
        assertEquals(1, actualTrendAnalysisOfCentreOverallQualityRatings.size());
        ResponseFetchQualityChartsDataDTO getResult = actualTrendAnalysisOfCentreOverallQualityRatings.get(0);
        assertEquals("foo", getResult.getName());
        assertEquals(42, getResult.getValue().intValue());
    }

    /**
     * Method under test:
     * {@link ChartsImpl#getTrendAnalysisOfCentreOverallQualityRatings(Integer, String, String)}
     */
    @Test
    void testGetTrendAnalysisOfCentreOverallQualityRatings9() {
        // Arrange
        ArrayList<String[]> stringArrayList = new ArrayList<>();
        stringArrayList.add(new String[]{"foo", "foo", "foo"});
        when(qualityAuditorRatingRepo.getTrendAnalysisOfCentreOverallQualityRatings(Mockito.<Integer>any(),
                Mockito.<String>any(), Mockito.<String>any())).thenReturn(stringArrayList);

        // Act and Assert
        assertThrows(ECDException.class,
                () -> chartsImpl.getTrendAnalysisOfCentreOverallQualityRatings(1, "Frequency", "Month"));
        verify(qualityAuditorRatingRepo).getTrendAnalysisOfCentreOverallQualityRatings(isA(Integer.class), eq("Frequency"),
                eq("Month"));
    }

    /**
     * Method under test:
     * {@link ChartsImpl#getTrendAnalysisOfCentreOverallQualityRatings(Integer, String, String)}
     */
    @Test
    void testGetTrendAnalysisOfCentreOverallQualityRatings10() {
        // Arrange
        ArrayList<String[]> stringArrayList = new ArrayList<>();
        stringArrayList.add(new String[]{"foo", null, null});
        when(qualityAuditorRatingRepo.getTrendAnalysisOfCentreOverallQualityRatings(Mockito.<Integer>any(),
                Mockito.<String>any(), Mockito.<String>any())).thenReturn(stringArrayList);

        // Act
        List<ResponseFetchQualityChartsDataDTO> actualTrendAnalysisOfCentreOverallQualityRatings = chartsImpl
                .getTrendAnalysisOfCentreOverallQualityRatings(1, "Frequency", "Month");

        // Assert
        verify(qualityAuditorRatingRepo).getTrendAnalysisOfCentreOverallQualityRatings(isA(Integer.class), eq("Frequency"),
                eq("Month"));
        assertEquals(1, actualTrendAnalysisOfCentreOverallQualityRatings.size());
    }

    /**
     * Method under test:
     * {@link ChartsImpl#getTrendAnalysisOfCentreOverallQualityRatings(Integer, String, String)}
     */
    @Test
    void testGetTrendAnalysisOfCentreOverallQualityRatings11() {
        // Arrange
        ArrayList<String[]> stringArrayList = new ArrayList<>();
        stringArrayList.add(new String[]{"foo", null, "42"});
        when(qualityAuditorRatingRepo.getTrendAnalysisOfCentreOverallQualityRatings(Mockito.<Integer>any(),
                Mockito.<String>any(), Mockito.<String>any())).thenReturn(stringArrayList);

        // Act
        List<ResponseFetchQualityChartsDataDTO> actualTrendAnalysisOfCentreOverallQualityRatings = chartsImpl
                .getTrendAnalysisOfCentreOverallQualityRatings(1, "Frequency", "Month");

        // Assert
        verify(qualityAuditorRatingRepo).getTrendAnalysisOfCentreOverallQualityRatings(isA(Integer.class), eq("Frequency"),
                eq("Month"));
        assertEquals(1, actualTrendAnalysisOfCentreOverallQualityRatings.size());
        assertEquals(42, actualTrendAnalysisOfCentreOverallQualityRatings.get(0).getValue().intValue());
    }

    /**
     * Method under test:
     * {@link ChartsImpl#getActorWiseQualityRatings(Integer, String, String)}
     */
    @Test
    void testGetActorWiseQualityRatings() {
        // Arrange
        when(qualityAuditorRatingRepo.getActorWiseQualityRatings(Mockito.<Integer>any(), Mockito.<String>any(),
                Mockito.<String>any())).thenReturn(new ArrayList<>());

        // Act
        List<ResponseFetchQualityChartsDataDTO> actualActorWiseQualityRatings = chartsImpl.getActorWiseQualityRatings(1,
                "Role Name", "Month");

        // Assert
        verify(qualityAuditorRatingRepo).getActorWiseQualityRatings(isA(Integer.class), eq("Role Name"), eq("Month"));
        assertTrue(actualActorWiseQualityRatings.isEmpty());
    }

    /**
     * Method under test:
     * {@link ChartsImpl#getActorWiseQualityRatings(Integer, String, String)}
     */
    @Test
    void testGetActorWiseQualityRatings2() {
        // Arrange
        ArrayList<String[]> stringArrayList = new ArrayList<>();
        stringArrayList.add(new String[]{"foo"});
        when(qualityAuditorRatingRepo.getActorWiseQualityRatings(Mockito.<Integer>any(), Mockito.<String>any(),
                Mockito.<String>any())).thenReturn(stringArrayList);

        // Act
        List<ResponseFetchQualityChartsDataDTO> actualActorWiseQualityRatings = chartsImpl.getActorWiseQualityRatings(1,
                "Role Name", "Month");

        // Assert
        verify(qualityAuditorRatingRepo).getActorWiseQualityRatings(isA(Integer.class), eq("Role Name"), eq("Month"));
        assertTrue(actualActorWiseQualityRatings.isEmpty());
    }

    /**
     * Method under test:
     * {@link ChartsImpl#getActorWiseQualityRatings(Integer, String, String)}
     */
    @Test
    void testGetActorWiseQualityRatings3() {
        // Arrange
        when(qualityAuditorRatingRepo.getActorWiseQualityRatings(Mockito.<Integer>any(), Mockito.<String>any(),
                Mockito.<String>any())).thenThrow(new ECDException("Exception Msg"));

        // Act and Assert
        assertThrows(ECDException.class, () -> chartsImpl.getActorWiseQualityRatings(1, "Role Name", "Month"));
        verify(qualityAuditorRatingRepo).getActorWiseQualityRatings(isA(Integer.class), eq("Role Name"), eq("Month"));
    }

    /**
     * Method under test:
     * {@link ChartsImpl#getActorWiseQualityRatings(Integer, String, String)}
     */
    @Test
    void testGetActorWiseQualityRatings4() {
        // Arrange
        ArrayList<String[]> stringArrayList = new ArrayList<>();
        stringArrayList.add(new String[]{"foo", null});
        when(qualityAuditorRatingRepo.getActorWiseQualityRatings(Mockito.<Integer>any(), Mockito.<String>any(),
                Mockito.<String>any())).thenReturn(stringArrayList);

        // Act
        List<ResponseFetchQualityChartsDataDTO> actualActorWiseQualityRatings = chartsImpl.getActorWiseQualityRatings(1,
                "Role Name", "Month");

        // Assert
        verify(qualityAuditorRatingRepo).getActorWiseQualityRatings(isA(Integer.class), eq("Role Name"), eq("Month"));
        assertEquals(1, actualActorWiseQualityRatings.size());
        assertEquals("foo", actualActorWiseQualityRatings.get(0).getName());
    }

    /**
     * Method under test:
     * {@link ChartsImpl#getActorWiseQualityRatings(Integer, String, String)}
     */
    @Test
    void testGetActorWiseQualityRatings5() {
        // Arrange
        ArrayList<String[]> stringArrayList = new ArrayList<>();
        stringArrayList.add(new String[]{"foo", null, "foo"});
        when(qualityAuditorRatingRepo.getActorWiseQualityRatings(Mockito.<Integer>any(), Mockito.<String>any(),
                Mockito.<String>any())).thenReturn(stringArrayList);

        // Act and Assert
        assertThrows(ECDException.class, () -> chartsImpl.getActorWiseQualityRatings(1, "Role Name", "Month"));
        verify(qualityAuditorRatingRepo).getActorWiseQualityRatings(isA(Integer.class), eq("Role Name"), eq("Month"));
    }

    /**
     * Method under test:
     * {@link ChartsImpl#getActorWiseQualityRatings(Integer, String, String)}
     */
    @Test
    void testGetActorWiseQualityRatings6() {
        // Arrange
        ArrayList<String[]> stringArrayList = new ArrayList<>();
        stringArrayList.add(new String[]{null, null});
        when(qualityAuditorRatingRepo.getActorWiseQualityRatings(Mockito.<Integer>any(), Mockito.<String>any(),
                Mockito.<String>any())).thenReturn(stringArrayList);

        // Act
        List<ResponseFetchQualityChartsDataDTO> actualActorWiseQualityRatings = chartsImpl.getActorWiseQualityRatings(1,
                "Role Name", "Month");

        // Assert
        verify(qualityAuditorRatingRepo).getActorWiseQualityRatings(isA(Integer.class), eq("Role Name"), eq("Month"));
        assertTrue(actualActorWiseQualityRatings.isEmpty());
    }

    /**
     * Method under test:
     * {@link ChartsImpl#getActorWiseQualityRatings(Integer, String, String)}
     */
    @Test
    void testGetActorWiseQualityRatings7() {
        // Arrange
        ArrayList<String[]> stringArrayList = new ArrayList<>();
        stringArrayList.add(new String[]{"foo", "foo"});
        when(qualityAuditorRatingRepo.getActorWiseQualityRatings(Mockito.<Integer>any(), Mockito.<String>any(),
                Mockito.<String>any())).thenReturn(stringArrayList);

        // Act and Assert
        assertThrows(ECDException.class, () -> chartsImpl.getActorWiseQualityRatings(1, "Role Name", "Month"));
        verify(qualityAuditorRatingRepo).getActorWiseQualityRatings(isA(Integer.class), eq("Role Name"), eq("Month"));
    }

    /**
     * Method under test:
     * {@link ChartsImpl#getActorWiseQualityRatings(Integer, String, String)}
     */
    @Test
    void testGetActorWiseQualityRatings8() {
        // Arrange
        ArrayList<String[]> stringArrayList = new ArrayList<>();
        stringArrayList.add(new String[]{"foo", "42"});
        when(qualityAuditorRatingRepo.getActorWiseQualityRatings(Mockito.<Integer>any(), Mockito.<String>any(),
                Mockito.<String>any())).thenReturn(stringArrayList);

        // Act
        List<ResponseFetchQualityChartsDataDTO> actualActorWiseQualityRatings = chartsImpl.getActorWiseQualityRatings(1,
                "Role Name", "Month");

        // Assert
        verify(qualityAuditorRatingRepo).getActorWiseQualityRatings(isA(Integer.class), eq("Role Name"), eq("Month"));
        assertEquals(1, actualActorWiseQualityRatings.size());
        ResponseFetchQualityChartsDataDTO getResult = actualActorWiseQualityRatings.get(0);
        assertEquals("foo", getResult.getName());
        assertEquals(42, getResult.getValue().intValue());
    }

    /**
     * Method under test:
     * {@link ChartsImpl#getActorWiseQualityRatings(Integer, String, String)}
     */
    @Test
    void testGetActorWiseQualityRatings9() {
        // Arrange
        ArrayList<String[]> stringArrayList = new ArrayList<>();
        stringArrayList.add(new String[]{"foo", "foo", "foo"});
        when(qualityAuditorRatingRepo.getActorWiseQualityRatings(Mockito.<Integer>any(), Mockito.<String>any(),
                Mockito.<String>any())).thenReturn(stringArrayList);

        // Act and Assert
        assertThrows(ECDException.class, () -> chartsImpl.getActorWiseQualityRatings(1, "Role Name", "Month"));
        verify(qualityAuditorRatingRepo).getActorWiseQualityRatings(isA(Integer.class), eq("Role Name"), eq("Month"));
    }

    /**
     * Method under test:
     * {@link ChartsImpl#getActorWiseQualityRatings(Integer, String, String)}
     */
    @Test
    void testGetActorWiseQualityRatings10() {
        // Arrange
        ArrayList<String[]> stringArrayList = new ArrayList<>();
        stringArrayList.add(new String[]{"foo", null, null});
        when(qualityAuditorRatingRepo.getActorWiseQualityRatings(Mockito.<Integer>any(), Mockito.<String>any(),
                Mockito.<String>any())).thenReturn(stringArrayList);

        // Act
        List<ResponseFetchQualityChartsDataDTO> actualActorWiseQualityRatings = chartsImpl.getActorWiseQualityRatings(1,
                "Role Name", "Month");

        // Assert
        verify(qualityAuditorRatingRepo).getActorWiseQualityRatings(isA(Integer.class), eq("Role Name"), eq("Month"));
        assertEquals(1, actualActorWiseQualityRatings.size());
    }

    /**
     * Method under test:
     * {@link ChartsImpl#getActorWiseQualityRatings(Integer, String, String)}
     */
    @Test
    void testGetActorWiseQualityRatings11() {
        // Arrange
        ArrayList<String[]> stringArrayList = new ArrayList<>();
        stringArrayList.add(new String[]{"foo", null, "42"});
        when(qualityAuditorRatingRepo.getActorWiseQualityRatings(Mockito.<Integer>any(), Mockito.<String>any(),
                Mockito.<String>any())).thenReturn(stringArrayList);

        // Act
        List<ResponseFetchQualityChartsDataDTO> actualActorWiseQualityRatings = chartsImpl.getActorWiseQualityRatings(1,
                "Role Name", "Month");

        // Assert
        verify(qualityAuditorRatingRepo).getActorWiseQualityRatings(isA(Integer.class), eq("Role Name"), eq("Month"));
        assertEquals(1, actualActorWiseQualityRatings.size());
        assertEquals(42, actualActorWiseQualityRatings.get(0).getValue().intValue());
    }

    /**
     * Method under test:
     * {@link ChartsImpl#getTenureWiseQualityRatings(Integer, String)}
     */
    @Test
    void testGetTenureWiseQualityRatings() {
        // Arrange
        when(qualityAuditorRatingRepo.getTenureWiseQualityRatings(Mockito.<Integer>any(), Mockito.<String>any()))
                .thenReturn(new ArrayList<>());

        // Act
        List<ResponseFetchQualityChartsDataDTO> actualTenureWiseQualityRatings = chartsImpl.getTenureWiseQualityRatings(1,
                "Role Name");

        // Assert
        verify(qualityAuditorRatingRepo).getTenureWiseQualityRatings(isA(Integer.class), eq("Role Name"));
        assertTrue(actualTenureWiseQualityRatings.isEmpty());
    }

    /**
     * Method under test:
     * {@link ChartsImpl#getTenureWiseQualityRatings(Integer, String)}
     */
    @Test
    void testGetTenureWiseQualityRatings2() {
        // Arrange
        ArrayList<String[]> stringArrayList = new ArrayList<>();
        stringArrayList.add(new String[]{"foo"});
        when(qualityAuditorRatingRepo.getTenureWiseQualityRatings(Mockito.<Integer>any(), Mockito.<String>any()))
                .thenReturn(stringArrayList);

        // Act
        List<ResponseFetchQualityChartsDataDTO> actualTenureWiseQualityRatings = chartsImpl.getTenureWiseQualityRatings(1,
                "Role Name");

        // Assert
        verify(qualityAuditorRatingRepo).getTenureWiseQualityRatings(isA(Integer.class), eq("Role Name"));
        assertTrue(actualTenureWiseQualityRatings.isEmpty());
    }

    /**
     * Method under test:
     * {@link ChartsImpl#getTenureWiseQualityRatings(Integer, String)}
     */
    @Test
    void testGetTenureWiseQualityRatings3() {
        // Arrange
        when(qualityAuditorRatingRepo.getTenureWiseQualityRatings(Mockito.<Integer>any(), Mockito.<String>any()))
                .thenThrow(new ECDException("Exception Msg"));

        // Act and Assert
        assertThrows(ECDException.class, () -> chartsImpl.getTenureWiseQualityRatings(1, "Role Name"));
        verify(qualityAuditorRatingRepo).getTenureWiseQualityRatings(isA(Integer.class), eq("Role Name"));
    }

    /**
     * Method under test:
     * {@link ChartsImpl#getTenureWiseQualityRatings(Integer, String)}
     */
    @Test
    void testGetTenureWiseQualityRatings4() {
        // Arrange
        ArrayList<String[]> stringArrayList = new ArrayList<>();
        stringArrayList.add(null);
        when(qualityAuditorRatingRepo.getTenureWiseQualityRatings(Mockito.<Integer>any(), Mockito.<String>any()))
                .thenReturn(stringArrayList);

        // Act and Assert
        assertThrows(ECDException.class, () -> chartsImpl.getTenureWiseQualityRatings(1, "Role Name"));
        verify(qualityAuditorRatingRepo).getTenureWiseQualityRatings(isA(Integer.class), eq("Role Name"));
    }

    /**
     * Method under test:
     * {@link ChartsImpl#getTenureWiseQualityRatings(Integer, String)}
     */
    @Test
    void testGetTenureWiseQualityRatings5() {
        // Arrange
        ArrayList<String[]> stringArrayList = new ArrayList<>();
        stringArrayList.add(new String[]{"foo", null});
        when(qualityAuditorRatingRepo.getTenureWiseQualityRatings(Mockito.<Integer>any(), Mockito.<String>any()))
                .thenReturn(stringArrayList);

        // Act
        List<ResponseFetchQualityChartsDataDTO> actualTenureWiseQualityRatings = chartsImpl.getTenureWiseQualityRatings(1,
                "Role Name");

        // Assert
        verify(qualityAuditorRatingRepo).getTenureWiseQualityRatings(isA(Integer.class), eq("Role Name"));
        assertEquals(1, actualTenureWiseQualityRatings.size());
        assertEquals("foo", actualTenureWiseQualityRatings.get(0).getName());
    }

    /**
     * Method under test:
     * {@link ChartsImpl#getTenureWiseQualityRatings(Integer, String)}
     */
    @Test
    void testGetTenureWiseQualityRatings6() {
        // Arrange
        ArrayList<String[]> stringArrayList = new ArrayList<>();
        stringArrayList.add(new String[]{"foo", null, "foo"});
        when(qualityAuditorRatingRepo.getTenureWiseQualityRatings(Mockito.<Integer>any(), Mockito.<String>any()))
                .thenReturn(stringArrayList);

        // Act and Assert
        assertThrows(ECDException.class, () -> chartsImpl.getTenureWiseQualityRatings(1, "Role Name"));
        verify(qualityAuditorRatingRepo).getTenureWiseQualityRatings(isA(Integer.class), eq("Role Name"));
    }

    /**
     * Method under test:
     * {@link ChartsImpl#getTenureWiseQualityRatings(Integer, String)}
     */
    @Test
    void testGetTenureWiseQualityRatings7() {
        // Arrange
        ArrayList<String[]> stringArrayList = new ArrayList<>();
        stringArrayList.add(new String[]{null, null});
        when(qualityAuditorRatingRepo.getTenureWiseQualityRatings(Mockito.<Integer>any(), Mockito.<String>any()))
                .thenReturn(stringArrayList);

        // Act
        List<ResponseFetchQualityChartsDataDTO> actualTenureWiseQualityRatings = chartsImpl.getTenureWiseQualityRatings(1,
                "Role Name");

        // Assert
        verify(qualityAuditorRatingRepo).getTenureWiseQualityRatings(isA(Integer.class), eq("Role Name"));
        assertTrue(actualTenureWiseQualityRatings.isEmpty());
    }

    /**
     * Method under test:
     * {@link ChartsImpl#getTenureWiseQualityRatings(Integer, String)}
     */
    @Test
    void testGetTenureWiseQualityRatings8() {
        // Arrange
        ArrayList<String[]> stringArrayList = new ArrayList<>();
        stringArrayList.add(new String[]{"foo", "foo"});
        when(qualityAuditorRatingRepo.getTenureWiseQualityRatings(Mockito.<Integer>any(), Mockito.<String>any()))
                .thenReturn(stringArrayList);

        // Act and Assert
        assertThrows(ECDException.class, () -> chartsImpl.getTenureWiseQualityRatings(1, "Role Name"));
        verify(qualityAuditorRatingRepo).getTenureWiseQualityRatings(isA(Integer.class), eq("Role Name"));
    }

    /**
     * Method under test:
     * {@link ChartsImpl#getTenureWiseQualityRatings(Integer, String)}
     */
    @Test
    void testGetTenureWiseQualityRatings9() {
        // Arrange
        ArrayList<String[]> stringArrayList = new ArrayList<>();
        stringArrayList.add(new String[]{"foo", "42"});
        when(qualityAuditorRatingRepo.getTenureWiseQualityRatings(Mockito.<Integer>any(), Mockito.<String>any()))
                .thenReturn(stringArrayList);

        // Act
        List<ResponseFetchQualityChartsDataDTO> actualTenureWiseQualityRatings = chartsImpl.getTenureWiseQualityRatings(1,
                "Role Name");

        // Assert
        verify(qualityAuditorRatingRepo).getTenureWiseQualityRatings(isA(Integer.class), eq("Role Name"));
        assertEquals(1, actualTenureWiseQualityRatings.size());
        ResponseFetchQualityChartsDataDTO getResult = actualTenureWiseQualityRatings.get(0);
        assertEquals("foo", getResult.getName());
        assertEquals(42, getResult.getValue().intValue());
    }

    /**
     * Method under test:
     * {@link ChartsImpl#getTenureWiseQualityRatings(Integer, String)}
     */
    @Test
    void testGetTenureWiseQualityRatings10() {
        // Arrange
        ArrayList<String[]> stringArrayList = new ArrayList<>();
        stringArrayList.add(new String[]{"foo", "foo", "foo"});
        when(qualityAuditorRatingRepo.getTenureWiseQualityRatings(Mockito.<Integer>any(), Mockito.<String>any()))
                .thenReturn(stringArrayList);

        // Act and Assert
        assertThrows(ECDException.class, () -> chartsImpl.getTenureWiseQualityRatings(1, "Role Name"));
        verify(qualityAuditorRatingRepo).getTenureWiseQualityRatings(isA(Integer.class), eq("Role Name"));
    }

    /**
     * Method under test:
     * {@link ChartsImpl#getTenureWiseQualityRatings(Integer, String)}
     */
    @Test
    void testGetTenureWiseQualityRatings11() {
        // Arrange
        ArrayList<String[]> stringArrayList = new ArrayList<>();
        stringArrayList.add(new String[]{"foo", null, null});
        when(qualityAuditorRatingRepo.getTenureWiseQualityRatings(Mockito.<Integer>any(), Mockito.<String>any()))
                .thenReturn(stringArrayList);

        // Act
        List<ResponseFetchQualityChartsDataDTO> actualTenureWiseQualityRatings = chartsImpl.getTenureWiseQualityRatings(1,
                "Role Name");

        // Assert
        verify(qualityAuditorRatingRepo).getTenureWiseQualityRatings(isA(Integer.class), eq("Role Name"));
        assertEquals(1, actualTenureWiseQualityRatings.size());
    }

    /**
     * Method under test:
     * {@link ChartsImpl#getTenureWiseQualityRatings(Integer, String)}
     */
    @Test
    void testGetTenureWiseQualityRatings12() {
        // Arrange
        ArrayList<String[]> stringArrayList = new ArrayList<>();
        stringArrayList.add(new String[]{"foo", null, "42"});
        when(qualityAuditorRatingRepo.getTenureWiseQualityRatings(Mockito.<Integer>any(), Mockito.<String>any()))
                .thenReturn(stringArrayList);

        // Act
        List<ResponseFetchQualityChartsDataDTO> actualTenureWiseQualityRatings = chartsImpl.getTenureWiseQualityRatings(1,
                "Role Name");

        // Assert
        verify(qualityAuditorRatingRepo).getTenureWiseQualityRatings(isA(Integer.class), eq("Role Name"));
        assertEquals(1, actualTenureWiseQualityRatings.size());
        assertEquals(42, actualTenureWiseQualityRatings.get(0).getValue().intValue());
    }

    /**
     * Method under test:
     * {@link ChartsImpl#gradeWiseAgentCount(Integer, String, String)}
     */
    @Test
    void testGradeWiseAgentCount() {
        // Arrange
        when(qualityAuditorRatingRepo.getGradeWiseAgentCount(Mockito.<Integer>any(), Mockito.<String>any(),
                Mockito.<String>any())).thenReturn(new ArrayList<>());

        // Act
        List<ResponseFetchQualityChartsDataDTO> actualGradeWiseAgentCountResult = chartsImpl.gradeWiseAgentCount(1,
                "Frequency", "42");

        // Assert
        verify(qualityAuditorRatingRepo).getGradeWiseAgentCount(isA(Integer.class), eq("Frequency"), eq("42"));
        assertTrue(actualGradeWiseAgentCountResult.isEmpty());
    }

    /**
     * Method under test:
     * {@link ChartsImpl#gradeWiseAgentCount(Integer, String, String)}
     */
    @Test
    void testGradeWiseAgentCount2() {
        // Arrange
        ArrayList<String[]> stringArrayList = new ArrayList<>();
        stringArrayList.add(new String[]{"foo"});
        when(qualityAuditorRatingRepo.getGradeWiseAgentCount(Mockito.<Integer>any(), Mockito.<String>any(),
                Mockito.<String>any())).thenReturn(stringArrayList);

        // Act
        List<ResponseFetchQualityChartsDataDTO> actualGradeWiseAgentCountResult = chartsImpl.gradeWiseAgentCount(1,
                "Frequency", "42");

        // Assert
        verify(qualityAuditorRatingRepo).getGradeWiseAgentCount(isA(Integer.class), eq("Frequency"), eq("42"));
        assertTrue(actualGradeWiseAgentCountResult.isEmpty());
    }

    /**
     * Method under test:
     * {@link ChartsImpl#gradeWiseAgentCount(Integer, String, String)}
     */
    @Test
    void testGradeWiseAgentCount3() {
        // Arrange
        when(qualityAuditorRatingRepo.getGradeWiseAgentCount(Mockito.<Integer>any(), Mockito.<String>any(),
                Mockito.<String>any())).thenThrow(new ECDException("Exception Msg"));

        // Act and Assert
        assertThrows(ECDException.class, () -> chartsImpl.gradeWiseAgentCount(1, "Frequency", "42"));
        verify(qualityAuditorRatingRepo).getGradeWiseAgentCount(isA(Integer.class), eq("Frequency"), eq("42"));
    }

    /**
     * Method under test:
     * {@link ChartsImpl#gradeWiseAgentCount(Integer, String, String)}
     */
    @Test
    void testGradeWiseAgentCount4() {
        // Arrange
        ArrayList<String[]> stringArrayList = new ArrayList<>();
        stringArrayList.add(new String[]{"foo", null});
        when(qualityAuditorRatingRepo.getGradeWiseAgentCount(Mockito.<Integer>any(), Mockito.<String>any(),
                Mockito.<String>any())).thenReturn(stringArrayList);

        // Act
        List<ResponseFetchQualityChartsDataDTO> actualGradeWiseAgentCountResult = chartsImpl.gradeWiseAgentCount(1,
                "Frequency", "42");

        // Assert
        verify(qualityAuditorRatingRepo).getGradeWiseAgentCount(isA(Integer.class), eq("Frequency"), eq("42"));
        assertEquals(1, actualGradeWiseAgentCountResult.size());
        assertEquals("foo", actualGradeWiseAgentCountResult.get(0).getName());
    }

    /**
     * Method under test:
     * {@link ChartsImpl#gradeWiseAgentCount(Integer, String, String)}
     */
    @Test
    void testGradeWiseAgentCount5() {
        // Arrange
        ArrayList<String[]> stringArrayList = new ArrayList<>();
        stringArrayList.add(new String[]{"foo", null, "foo"});
        when(qualityAuditorRatingRepo.getGradeWiseAgentCount(Mockito.<Integer>any(), Mockito.<String>any(),
                Mockito.<String>any())).thenReturn(stringArrayList);

        // Act and Assert
        assertThrows(ECDException.class, () -> chartsImpl.gradeWiseAgentCount(1, "Frequency", "42"));
        verify(qualityAuditorRatingRepo).getGradeWiseAgentCount(isA(Integer.class), eq("Frequency"), eq("42"));
    }

    /**
     * Method under test:
     * {@link ChartsImpl#gradeWiseAgentCount(Integer, String, String)}
     */
    @Test
    void testGradeWiseAgentCount6() {
        // Arrange
        ArrayList<String[]> stringArrayList = new ArrayList<>();
        stringArrayList.add(new String[]{null, null});
        when(qualityAuditorRatingRepo.getGradeWiseAgentCount(Mockito.<Integer>any(), Mockito.<String>any(),
                Mockito.<String>any())).thenReturn(stringArrayList);

        // Act
        List<ResponseFetchQualityChartsDataDTO> actualGradeWiseAgentCountResult = chartsImpl.gradeWiseAgentCount(1,
                "Frequency", "42");

        // Assert
        verify(qualityAuditorRatingRepo).getGradeWiseAgentCount(isA(Integer.class), eq("Frequency"), eq("42"));
        assertTrue(actualGradeWiseAgentCountResult.isEmpty());
    }

    /**
     * Method under test:
     * {@link ChartsImpl#gradeWiseAgentCount(Integer, String, String)}
     */
    @Test
    void testGradeWiseAgentCount7() {
        // Arrange
        ArrayList<String[]> stringArrayList = new ArrayList<>();
        stringArrayList.add(new String[]{"foo", "foo"});
        when(qualityAuditorRatingRepo.getGradeWiseAgentCount(Mockito.<Integer>any(), Mockito.<String>any(),
                Mockito.<String>any())).thenReturn(stringArrayList);

        // Act and Assert
        assertThrows(ECDException.class, () -> chartsImpl.gradeWiseAgentCount(1, "Frequency", "42"));
        verify(qualityAuditorRatingRepo).getGradeWiseAgentCount(isA(Integer.class), eq("Frequency"), eq("42"));
    }

    /**
     * Method under test:
     * {@link ChartsImpl#gradeWiseAgentCount(Integer, String, String)}
     */
    @Test
    void testGradeWiseAgentCount8() {
        // Arrange
        ArrayList<String[]> stringArrayList = new ArrayList<>();
        stringArrayList.add(new String[]{"foo", "42"});
        when(qualityAuditorRatingRepo.getGradeWiseAgentCount(Mockito.<Integer>any(), Mockito.<String>any(),
                Mockito.<String>any())).thenReturn(stringArrayList);

        // Act
        List<ResponseFetchQualityChartsDataDTO> actualGradeWiseAgentCountResult = chartsImpl.gradeWiseAgentCount(1,
                "Frequency", "42");

        // Assert
        verify(qualityAuditorRatingRepo).getGradeWiseAgentCount(isA(Integer.class), eq("Frequency"), eq("42"));
        assertEquals(1, actualGradeWiseAgentCountResult.size());
        ResponseFetchQualityChartsDataDTO getResult = actualGradeWiseAgentCountResult.get(0);
        assertEquals("foo", getResult.getName());
        assertEquals(42, getResult.getValue().intValue());
    }

    /**
     * Method under test:
     * {@link ChartsImpl#gradeWiseAgentCount(Integer, String, String)}
     */
    @Test
    void testGradeWiseAgentCount9() {
        // Arrange
        ArrayList<String[]> stringArrayList = new ArrayList<>();
        stringArrayList.add(new String[]{"foo", "foo", "foo"});
        when(qualityAuditorRatingRepo.getGradeWiseAgentCount(Mockito.<Integer>any(), Mockito.<String>any(),
                Mockito.<String>any())).thenReturn(stringArrayList);

        // Act and Assert
        assertThrows(ECDException.class, () -> chartsImpl.gradeWiseAgentCount(1, "Frequency", "42"));
        verify(qualityAuditorRatingRepo).getGradeWiseAgentCount(isA(Integer.class), eq("Frequency"), eq("42"));
    }

    /**
     * Method under test:
     * {@link ChartsImpl#gradeWiseAgentCount(Integer, String, String)}
     */
    @Test
    void testGradeWiseAgentCount10() {
        // Arrange
        ArrayList<String[]> stringArrayList = new ArrayList<>();
        stringArrayList.add(new String[]{"foo", null, null});
        when(qualityAuditorRatingRepo.getGradeWiseAgentCount(Mockito.<Integer>any(), Mockito.<String>any(),
                Mockito.<String>any())).thenReturn(stringArrayList);

        // Act
        List<ResponseFetchQualityChartsDataDTO> actualGradeWiseAgentCountResult = chartsImpl.gradeWiseAgentCount(1,
                "Frequency", "42");

        // Assert
        verify(qualityAuditorRatingRepo).getGradeWiseAgentCount(isA(Integer.class), eq("Frequency"), eq("42"));
        assertEquals(1, actualGradeWiseAgentCountResult.size());
    }

    /**
     * Method under test:
     * {@link ChartsImpl#gradeWiseAgentCount(Integer, String, String)}
     */
    @Test
    void testGradeWiseAgentCount11() {
        // Arrange
        ArrayList<String[]> stringArrayList = new ArrayList<>();
        stringArrayList.add(new String[]{"foo", null, "42"});
        when(qualityAuditorRatingRepo.getGradeWiseAgentCount(Mockito.<Integer>any(), Mockito.<String>any(),
                Mockito.<String>any())).thenReturn(stringArrayList);

        // Act
        List<ResponseFetchQualityChartsDataDTO> actualGradeWiseAgentCountResult = chartsImpl.gradeWiseAgentCount(1,
                "Frequency", "42");

        // Assert
        verify(qualityAuditorRatingRepo).getGradeWiseAgentCount(isA(Integer.class), eq("Frequency"), eq("42"));
        assertEquals(1, actualGradeWiseAgentCountResult.size());
        assertEquals(42, actualGradeWiseAgentCountResult.get(0).getValue().intValue());
    }
}
