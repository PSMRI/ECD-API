package com.iemr.ecd.service.associate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.iemr.ecd.dao.QualityAuditorRating;
import com.iemr.ecd.dao.masters.UserServiceRoleMapping;
import com.iemr.ecd.dao_temp.V_GetDialPreference;
import com.iemr.ecd.dto.ResponseAutoPreviewDialingDTO;
import com.iemr.ecd.dto.ResponseGetRatingsDTO;
import com.iemr.ecd.repository.ecd.V_GetDialPreferenceRepo;
import com.iemr.ecd.repository.masters.UserServiceRoleMappingRepo;
import com.iemr.ecd.repository.quality.QualityAuditorRatingRepo;
import com.iemr.ecd.utils.advice.exception_handler.ECDException;
import com.iemr.ecd.utils.advice.exception_handler.InvalidRequestException;

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

@ContextConfiguration(classes = {AutoPreviewDialingImpl.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class AutoPreviewDialingImplDiffblueTest {
    @Autowired
    private AutoPreviewDialingImpl autoPreviewDialingImpl;

    @MockBean
    private QualityAuditorRatingRepo qualityAuditorRatingRepo;

    @MockBean
    private UserServiceRoleMappingRepo userServiceRoleMappingRepo;

    @MockBean
    private V_GetDialPreferenceRepo v_GetDialPreferenceRepo;

    /**
     * Method under test:
     * {@link AutoPreviewDialingImpl#addDialPreference(UserServiceRoleMapping)}
     */
    @Test
    void testAddDialPreference() {
        // Arrange
        UserServiceRoleMapping userServiceRoleMapping = new UserServiceRoleMapping();
        userServiceRoleMapping.setAgentId("42");
        userServiceRoleMapping.setAgentPassword("iloveyou");
        userServiceRoleMapping.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        userServiceRoleMapping.setCreatedDate(mock(Timestamp.class));
        userServiceRoleMapping.setCzRole("Cz Role");
        userServiceRoleMapping.setDeleted(true);
        userServiceRoleMapping.setIsDialPreference(true);
        userServiceRoleMapping.setIsInbound(true);
        userServiceRoleMapping.setIsOutbound(true);
        userServiceRoleMapping.setLastModDate(mock(Timestamp.class));
        userServiceRoleMapping.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        userServiceRoleMapping.setPreviewWindowTime(1);
        userServiceRoleMapping.setPsmId(1);
        userServiceRoleMapping.setRoleId(1);
        userServiceRoleMapping.setServiceProviderId(1);
        userServiceRoleMapping.setUserId(1);
        userServiceRoleMapping.setUsrMappingId(1);
        userServiceRoleMapping.setWorkingLocationID(1);
        
        userServiceRoleMapping.toString();
        
        UserServiceRoleMapping userServiceRoleMapping2 = new UserServiceRoleMapping();
        userServiceRoleMapping2.setAgentId("42");
        userServiceRoleMapping2.setAgentPassword("iloveyou");
        userServiceRoleMapping2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        userServiceRoleMapping2.setCreatedDate(mock(Timestamp.class));
        userServiceRoleMapping2.setCzRole("Cz Role");
        userServiceRoleMapping2.setDeleted(true);
        userServiceRoleMapping2.setIsDialPreference(true);
        userServiceRoleMapping2.setIsInbound(true);
        userServiceRoleMapping2.setIsOutbound(true);
        userServiceRoleMapping2.setLastModDate(mock(Timestamp.class));
        userServiceRoleMapping2.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        userServiceRoleMapping2.setPreviewWindowTime(1);
        userServiceRoleMapping2.setPsmId(1);
        userServiceRoleMapping2.setRoleId(1);
        userServiceRoleMapping2.setServiceProviderId(1);
        userServiceRoleMapping2.setUserId(1);
        userServiceRoleMapping2.setUsrMappingId(1);
        userServiceRoleMapping2.setWorkingLocationID(1);
        when(userServiceRoleMappingRepo.save(Mockito.<UserServiceRoleMapping>any())).thenReturn(userServiceRoleMapping2);
        when(userServiceRoleMappingRepo.findByUserIdAndRoleIdAndPsmIdAndDeleted(Mockito.<Integer>any(),
                Mockito.<Integer>any(), Mockito.<Integer>any(), Mockito.<Boolean>any())).thenReturn(userServiceRoleMapping);

        UserServiceRoleMapping userServiceRoleMapping3 = new UserServiceRoleMapping();
        userServiceRoleMapping3.setAgentId("42");
        userServiceRoleMapping3.setAgentPassword("iloveyou");
        userServiceRoleMapping3.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        userServiceRoleMapping3.setCreatedDate(mock(Timestamp.class));
        userServiceRoleMapping3.setCzRole("Cz Role");
        userServiceRoleMapping3.setDeleted(true);
        userServiceRoleMapping3.setIsDialPreference(true);
        userServiceRoleMapping3.setIsInbound(true);
        userServiceRoleMapping3.setIsOutbound(true);
        userServiceRoleMapping3.setLastModDate(mock(Timestamp.class));
        userServiceRoleMapping3.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        userServiceRoleMapping3.setPreviewWindowTime(1);
        userServiceRoleMapping3.setPsmId(1);
        userServiceRoleMapping3.setRoleId(1);
        userServiceRoleMapping3.setServiceProviderId(1);
        userServiceRoleMapping3.setUserId(1);
        userServiceRoleMapping3.setUsrMappingId(1);
        userServiceRoleMapping3.setWorkingLocationID(1);

        // Act
        String actualAddDialPreferenceResult = autoPreviewDialingImpl.addDialPreference(userServiceRoleMapping3);

        // Assert
        verify(userServiceRoleMappingRepo).findByUserIdAndRoleIdAndPsmIdAndDeleted(isA(Integer.class), isA(Integer.class),
                isA(Integer.class), isA(Boolean.class));
        verify(userServiceRoleMappingRepo).save(isA(UserServiceRoleMapping.class));
        assertEquals("{\"response\":\"Dial Preference Added Successfully\"}", actualAddDialPreferenceResult);
    }

    /**
     * Method under test:
     * {@link AutoPreviewDialingImpl#addDialPreference(UserServiceRoleMapping)}
     */
    @Test
    void testAddDialPreference2() {
        // Arrange
        UserServiceRoleMapping userServiceRoleMapping = new UserServiceRoleMapping();
        userServiceRoleMapping.setAgentId("42");
        userServiceRoleMapping.setAgentPassword("iloveyou");
        userServiceRoleMapping.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        userServiceRoleMapping.setCreatedDate(mock(Timestamp.class));
        userServiceRoleMapping.setCzRole("Cz Role");
        userServiceRoleMapping.setDeleted(true);
        userServiceRoleMapping.setIsDialPreference(true);
        userServiceRoleMapping.setIsInbound(true);
        userServiceRoleMapping.setIsOutbound(true);
        userServiceRoleMapping.setLastModDate(mock(Timestamp.class));
        userServiceRoleMapping.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        userServiceRoleMapping.setPreviewWindowTime(1);
        userServiceRoleMapping.setPsmId(1);
        userServiceRoleMapping.setRoleId(1);
        userServiceRoleMapping.setServiceProviderId(1);
        userServiceRoleMapping.setUserId(1);
        userServiceRoleMapping.setUsrMappingId(1);
        userServiceRoleMapping.setWorkingLocationID(1);
        when(userServiceRoleMappingRepo.save(Mockito.<UserServiceRoleMapping>any()))
                .thenThrow(new InvalidRequestException("An error occurred"));
        when(userServiceRoleMappingRepo.findByUserIdAndRoleIdAndPsmIdAndDeleted(Mockito.<Integer>any(),
                Mockito.<Integer>any(), Mockito.<Integer>any(), Mockito.<Boolean>any())).thenReturn(userServiceRoleMapping);

        UserServiceRoleMapping userServiceRoleMapping2 = new UserServiceRoleMapping();
        userServiceRoleMapping2.setAgentId("42");
        userServiceRoleMapping2.setAgentPassword("iloveyou");
        userServiceRoleMapping2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        userServiceRoleMapping2.setCreatedDate(mock(Timestamp.class));
        userServiceRoleMapping2.setCzRole("Cz Role");
        userServiceRoleMapping2.setDeleted(true);
        userServiceRoleMapping2.setIsDialPreference(true);
        userServiceRoleMapping2.setIsInbound(true);
        userServiceRoleMapping2.setIsOutbound(true);
        userServiceRoleMapping2.setLastModDate(mock(Timestamp.class));
        userServiceRoleMapping2.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        userServiceRoleMapping2.setPreviewWindowTime(1);
        userServiceRoleMapping2.setPsmId(1);
        userServiceRoleMapping2.setRoleId(1);
        userServiceRoleMapping2.setServiceProviderId(1);
        userServiceRoleMapping2.setUserId(1);
        userServiceRoleMapping2.setUsrMappingId(1);
        userServiceRoleMapping2.setWorkingLocationID(1);

        // Act and Assert
        assertThrows(ECDException.class, () -> autoPreviewDialingImpl.addDialPreference(userServiceRoleMapping2));
        verify(userServiceRoleMappingRepo).findByUserIdAndRoleIdAndPsmIdAndDeleted(isA(Integer.class), isA(Integer.class),
                isA(Integer.class), isA(Boolean.class));
        verify(userServiceRoleMappingRepo).save(isA(UserServiceRoleMapping.class));
    }

    /**
     * Method under test:
     * {@link AutoPreviewDialingImpl#addDialPreference(UserServiceRoleMapping)}
     */
    @Test
    void testAddDialPreference3() {
        // Arrange
        UserServiceRoleMapping userServiceRoleMapping = mock(UserServiceRoleMapping.class);
        doNothing().when(userServiceRoleMapping).setAgentId(Mockito.<String>any());
        doNothing().when(userServiceRoleMapping).setAgentPassword(Mockito.<String>any());
        doNothing().when(userServiceRoleMapping).setCreatedBy(Mockito.<String>any());
        doNothing().when(userServiceRoleMapping).setCreatedDate(Mockito.<Timestamp>any());
        doNothing().when(userServiceRoleMapping).setCzRole(Mockito.<String>any());
        doNothing().when(userServiceRoleMapping).setDeleted(Mockito.<Boolean>any());
        doNothing().when(userServiceRoleMapping).setIsAutoPreviewDial(Mockito.<Boolean>any());
        doNothing().when(userServiceRoleMapping).setIsDialPreference(Mockito.<Boolean>any());
        doNothing().when(userServiceRoleMapping).setIsInbound(Mockito.<Boolean>any());
        doNothing().when(userServiceRoleMapping).setIsOutbound(Mockito.<Boolean>any());
        doNothing().when(userServiceRoleMapping).setLastModDate(Mockito.<Timestamp>any());
        doNothing().when(userServiceRoleMapping).setModifiedBy(Mockito.<String>any());
        doNothing().when(userServiceRoleMapping).setPreviewWindowTime(Mockito.<Integer>any());
        doNothing().when(userServiceRoleMapping).setPsmId(Mockito.<Integer>any());
        doNothing().when(userServiceRoleMapping).setRoleId(Mockito.<Integer>any());
        doNothing().when(userServiceRoleMapping).setServiceProviderId(Mockito.<Integer>any());
        doNothing().when(userServiceRoleMapping).setUserId(Mockito.<Integer>any());
        doNothing().when(userServiceRoleMapping).setUsrMappingId(Mockito.<Integer>any());
        doNothing().when(userServiceRoleMapping).setWorkingLocationID(Mockito.<Integer>any());
        userServiceRoleMapping.setAgentId("42");
        userServiceRoleMapping.setAgentPassword("iloveyou");
        userServiceRoleMapping.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        userServiceRoleMapping.setCreatedDate(mock(Timestamp.class));
        userServiceRoleMapping.setCzRole("Cz Role");
        userServiceRoleMapping.setDeleted(true);
        userServiceRoleMapping.setIsDialPreference(true);
        userServiceRoleMapping.setIsInbound(true);
        userServiceRoleMapping.setIsOutbound(true);
        userServiceRoleMapping.setLastModDate(mock(Timestamp.class));
        userServiceRoleMapping.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        userServiceRoleMapping.setPreviewWindowTime(1);
        userServiceRoleMapping.setPsmId(1);
        userServiceRoleMapping.setRoleId(1);
        userServiceRoleMapping.setServiceProviderId(1);
        userServiceRoleMapping.setUserId(1);
        userServiceRoleMapping.setUsrMappingId(1);
        userServiceRoleMapping.setWorkingLocationID(1);

        UserServiceRoleMapping userServiceRoleMapping2 = new UserServiceRoleMapping();
        userServiceRoleMapping2.setAgentId("42");
        userServiceRoleMapping2.setAgentPassword("iloveyou");
        userServiceRoleMapping2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        userServiceRoleMapping2.setCreatedDate(mock(Timestamp.class));
        userServiceRoleMapping2.setCzRole("Cz Role");
        userServiceRoleMapping2.setDeleted(true);
        userServiceRoleMapping2.setIsDialPreference(true);
        userServiceRoleMapping2.setIsInbound(true);
        userServiceRoleMapping2.setIsOutbound(true);
        userServiceRoleMapping2.setLastModDate(mock(Timestamp.class));
        userServiceRoleMapping2.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        userServiceRoleMapping2.setPreviewWindowTime(1);
        userServiceRoleMapping2.setPsmId(1);
        userServiceRoleMapping2.setRoleId(1);
        userServiceRoleMapping2.setServiceProviderId(1);
        userServiceRoleMapping2.setUserId(1);
        userServiceRoleMapping2.setUsrMappingId(1);
        userServiceRoleMapping2.setWorkingLocationID(1);
        when(userServiceRoleMappingRepo.save(Mockito.<UserServiceRoleMapping>any())).thenReturn(userServiceRoleMapping2);
        when(userServiceRoleMappingRepo.findByUserIdAndRoleIdAndPsmIdAndDeleted(Mockito.<Integer>any(),
                Mockito.<Integer>any(), Mockito.<Integer>any(), Mockito.<Boolean>any())).thenReturn(userServiceRoleMapping);

        UserServiceRoleMapping userServiceRoleMapping3 = new UserServiceRoleMapping();
        userServiceRoleMapping3.setAgentId("42");
        userServiceRoleMapping3.setAgentPassword("iloveyou");
        userServiceRoleMapping3.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        userServiceRoleMapping3.setCreatedDate(mock(Timestamp.class));
        userServiceRoleMapping3.setCzRole("Cz Role");
        userServiceRoleMapping3.setDeleted(true);
        userServiceRoleMapping3.setIsDialPreference(true);
        userServiceRoleMapping3.setIsInbound(true);
        userServiceRoleMapping3.setIsOutbound(true);
        userServiceRoleMapping3.setLastModDate(mock(Timestamp.class));
        userServiceRoleMapping3.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        userServiceRoleMapping3.setPreviewWindowTime(1);
        userServiceRoleMapping3.setPsmId(1);
        userServiceRoleMapping3.setRoleId(1);
        userServiceRoleMapping3.setServiceProviderId(1);
        userServiceRoleMapping3.setUserId(1);
        userServiceRoleMapping3.setUsrMappingId(1);
        userServiceRoleMapping3.setWorkingLocationID(1);

        // Act
        String actualAddDialPreferenceResult = autoPreviewDialingImpl.addDialPreference(userServiceRoleMapping3);

        // Assert
        verify(userServiceRoleMapping).setAgentId(eq("42"));
        verify(userServiceRoleMapping).setAgentPassword(eq("iloveyou"));
        verify(userServiceRoleMapping).setCreatedBy(eq("Jan 1, 2020 8:00am GMT+0100"));
        verify(userServiceRoleMapping).setCreatedDate(isA(Timestamp.class));
        verify(userServiceRoleMapping).setCzRole(eq("Cz Role"));
        verify(userServiceRoleMapping).setDeleted(isA(Boolean.class));
        verify(userServiceRoleMapping).setIsAutoPreviewDial(isA(Boolean.class));
        verify(userServiceRoleMapping).setIsDialPreference(isA(Boolean.class));
        verify(userServiceRoleMapping).setIsInbound(isA(Boolean.class));
        verify(userServiceRoleMapping).setIsOutbound(isA(Boolean.class));
        verify(userServiceRoleMapping).setLastModDate(isA(Timestamp.class));
        verify(userServiceRoleMapping).setModifiedBy(eq("Jan 1, 2020 9:00am GMT+0100"));
        verify(userServiceRoleMapping, atLeast(1)).setPreviewWindowTime(isA(Integer.class));
        verify(userServiceRoleMapping).setPsmId(isA(Integer.class));
        verify(userServiceRoleMapping).setRoleId(isA(Integer.class));
        verify(userServiceRoleMapping).setServiceProviderId(isA(Integer.class));
        verify(userServiceRoleMapping).setUserId(isA(Integer.class));
        verify(userServiceRoleMapping).setUsrMappingId(isA(Integer.class));
        verify(userServiceRoleMapping).setWorkingLocationID(isA(Integer.class));
        verify(userServiceRoleMappingRepo).findByUserIdAndRoleIdAndPsmIdAndDeleted(isA(Integer.class), isA(Integer.class),
                isA(Integer.class), isA(Boolean.class));
        verify(userServiceRoleMappingRepo).save(isA(UserServiceRoleMapping.class));
        assertEquals("{\"response\":\"Dial Preference Added Successfully\"}", actualAddDialPreferenceResult);
    }

    /**
     * Method under test: {@link AutoPreviewDialingImpl#getDialPreference(int)}
     */
    @Test
    void testGetDialPreference() {
        // Arrange
        ArrayList<V_GetDialPreference> v_GetDialPreferenceList = new ArrayList<>();
        when(v_GetDialPreferenceRepo.findByPsmId(Mockito.<Integer>any())).thenReturn(v_GetDialPreferenceList);

        // Act
        List<V_GetDialPreference> actualDialPreference = autoPreviewDialingImpl.getDialPreference(1);

        // Assert
        verify(v_GetDialPreferenceRepo).findByPsmId(isA(Integer.class));
        assertTrue(actualDialPreference.isEmpty());
        assertSame(v_GetDialPreferenceList, actualDialPreference);
    }

    /**
     * Method under test: {@link AutoPreviewDialingImpl#getDialPreference(int)}
     */
    @Test
    void testGetDialPreference2() {
        // Arrange
        when(v_GetDialPreferenceRepo.findByPsmId(Mockito.<Integer>any()))
                .thenThrow(new InvalidRequestException("An error occurred"));

        // Act and Assert
        assertThrows(ECDException.class, () -> autoPreviewDialingImpl.getDialPreference(1));
        verify(v_GetDialPreferenceRepo).findByPsmId(isA(Integer.class));
    }

    /**
     * Method under test:
     * {@link AutoPreviewDialingImpl#getAutoPreviewDialingByUserIdAndRoleIdAndPsmId(Integer, Integer, Integer)}
     */
    @Test
    void testGetAutoPreviewDialingByUserIdAndRoleIdAndPsmId() {
        // Arrange
        UserServiceRoleMapping userServiceRoleMapping = new UserServiceRoleMapping();
        userServiceRoleMapping.setAgentId("42");
        userServiceRoleMapping.setAgentPassword("iloveyou");
        userServiceRoleMapping.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        userServiceRoleMapping.setCreatedDate(mock(Timestamp.class));
        userServiceRoleMapping.setCzRole("Cz Role");
        userServiceRoleMapping.setDeleted(true);
        userServiceRoleMapping.setIsDialPreference(true);
        userServiceRoleMapping.setIsInbound(true);
        userServiceRoleMapping.setIsOutbound(true);
        userServiceRoleMapping.setLastModDate(mock(Timestamp.class));
        userServiceRoleMapping.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        userServiceRoleMapping.setPreviewWindowTime(1);
        userServiceRoleMapping.setPsmId(1);
        userServiceRoleMapping.setRoleId(1);
        userServiceRoleMapping.setServiceProviderId(1);
        userServiceRoleMapping.setUserId(1);
        userServiceRoleMapping.setUsrMappingId(1);
        userServiceRoleMapping.setWorkingLocationID(1);
        when(userServiceRoleMappingRepo.findByUserIdAndRoleIdAndPsmIdAndDeleted(Mockito.<Integer>any(),
                Mockito.<Integer>any(), Mockito.<Integer>any(), Mockito.<Boolean>any())).thenReturn(userServiceRoleMapping);

        // Act
        ResponseAutoPreviewDialingDTO actualAutoPreviewDialingByUserIdAndRoleIdAndPsmId = autoPreviewDialingImpl
                .getAutoPreviewDialingByUserIdAndRoleIdAndPsmId(1, 1, 1);

        // Assert
        verify(userServiceRoleMappingRepo).findByUserIdAndRoleIdAndPsmIdAndDeleted(isA(Integer.class), isA(Integer.class),
                isA(Integer.class), isA(Boolean.class));
        assertNull(actualAutoPreviewDialingByUserIdAndRoleIdAndPsmId.getIsAutoPreviewDial());
        assertEquals(1, actualAutoPreviewDialingByUserIdAndRoleIdAndPsmId.getPreviewWindowTime().intValue());
    }

    /**
     * Method under test:
     * {@link AutoPreviewDialingImpl#getAutoPreviewDialingByUserIdAndRoleIdAndPsmId(Integer, Integer, Integer)}
     */
    @Test
    void testGetAutoPreviewDialingByUserIdAndRoleIdAndPsmId2() {
        // Arrange
        UserServiceRoleMapping userServiceRoleMapping = mock(UserServiceRoleMapping.class);
        when(userServiceRoleMapping.getIsAutoPreviewDial()).thenReturn(true);
        when(userServiceRoleMapping.getPreviewWindowTime()).thenReturn(1);
        doNothing().when(userServiceRoleMapping).setAgentId(Mockito.<String>any());
        doNothing().when(userServiceRoleMapping).setAgentPassword(Mockito.<String>any());
        doNothing().when(userServiceRoleMapping).setCreatedBy(Mockito.<String>any());
        doNothing().when(userServiceRoleMapping).setCreatedDate(Mockito.<Timestamp>any());
        doNothing().when(userServiceRoleMapping).setCzRole(Mockito.<String>any());
        doNothing().when(userServiceRoleMapping).setDeleted(Mockito.<Boolean>any());
        doNothing().when(userServiceRoleMapping).setIsDialPreference(Mockito.<Boolean>any());
        doNothing().when(userServiceRoleMapping).setIsInbound(Mockito.<Boolean>any());
        doNothing().when(userServiceRoleMapping).setIsOutbound(Mockito.<Boolean>any());
        doNothing().when(userServiceRoleMapping).setLastModDate(Mockito.<Timestamp>any());
        doNothing().when(userServiceRoleMapping).setModifiedBy(Mockito.<String>any());
        doNothing().when(userServiceRoleMapping).setPreviewWindowTime(Mockito.<Integer>any());
        doNothing().when(userServiceRoleMapping).setPsmId(Mockito.<Integer>any());
        doNothing().when(userServiceRoleMapping).setRoleId(Mockito.<Integer>any());
        doNothing().when(userServiceRoleMapping).setServiceProviderId(Mockito.<Integer>any());
        doNothing().when(userServiceRoleMapping).setUserId(Mockito.<Integer>any());
        doNothing().when(userServiceRoleMapping).setUsrMappingId(Mockito.<Integer>any());
        doNothing().when(userServiceRoleMapping).setWorkingLocationID(Mockito.<Integer>any());
        userServiceRoleMapping.setAgentId("42");
        userServiceRoleMapping.setAgentPassword("iloveyou");
        userServiceRoleMapping.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        userServiceRoleMapping.setCreatedDate(mock(Timestamp.class));
        userServiceRoleMapping.setCzRole("Cz Role");
        userServiceRoleMapping.setDeleted(true);
        userServiceRoleMapping.setIsDialPreference(true);
        userServiceRoleMapping.setIsInbound(true);
        userServiceRoleMapping.setIsOutbound(true);
        userServiceRoleMapping.setLastModDate(mock(Timestamp.class));
        userServiceRoleMapping.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        userServiceRoleMapping.setPreviewWindowTime(1);
        userServiceRoleMapping.setPsmId(1);
        userServiceRoleMapping.setRoleId(1);
        userServiceRoleMapping.setServiceProviderId(1);
        userServiceRoleMapping.setUserId(1);
        userServiceRoleMapping.setUsrMappingId(1);
        userServiceRoleMapping.setWorkingLocationID(1);
        when(userServiceRoleMappingRepo.findByUserIdAndRoleIdAndPsmIdAndDeleted(Mockito.<Integer>any(),
                Mockito.<Integer>any(), Mockito.<Integer>any(), Mockito.<Boolean>any())).thenReturn(userServiceRoleMapping);

        // Act
        ResponseAutoPreviewDialingDTO actualAutoPreviewDialingByUserIdAndRoleIdAndPsmId = autoPreviewDialingImpl
                .getAutoPreviewDialingByUserIdAndRoleIdAndPsmId(1, 1, 1);

        // Assert
        verify(userServiceRoleMapping).getIsAutoPreviewDial();
        verify(userServiceRoleMapping).getPreviewWindowTime();
        verify(userServiceRoleMapping).setAgentId(eq("42"));
        verify(userServiceRoleMapping).setAgentPassword(eq("iloveyou"));
        verify(userServiceRoleMapping).setCreatedBy(eq("Jan 1, 2020 8:00am GMT+0100"));
        verify(userServiceRoleMapping).setCreatedDate(isA(Timestamp.class));
        verify(userServiceRoleMapping).setCzRole(eq("Cz Role"));
        verify(userServiceRoleMapping).setDeleted(isA(Boolean.class));
        verify(userServiceRoleMapping).setIsDialPreference(isA(Boolean.class));
        verify(userServiceRoleMapping).setIsInbound(isA(Boolean.class));
        verify(userServiceRoleMapping).setIsOutbound(isA(Boolean.class));
        verify(userServiceRoleMapping).setLastModDate(isA(Timestamp.class));
        verify(userServiceRoleMapping).setModifiedBy(eq("Jan 1, 2020 9:00am GMT+0100"));
        verify(userServiceRoleMapping).setPreviewWindowTime(isA(Integer.class));
        verify(userServiceRoleMapping).setPsmId(isA(Integer.class));
        verify(userServiceRoleMapping).setRoleId(isA(Integer.class));
        verify(userServiceRoleMapping).setServiceProviderId(isA(Integer.class));
        verify(userServiceRoleMapping).setUserId(isA(Integer.class));
        verify(userServiceRoleMapping).setUsrMappingId(isA(Integer.class));
        verify(userServiceRoleMapping).setWorkingLocationID(isA(Integer.class));
        verify(userServiceRoleMappingRepo).findByUserIdAndRoleIdAndPsmIdAndDeleted(isA(Integer.class), isA(Integer.class),
                isA(Integer.class), isA(Boolean.class));
        assertEquals(1, actualAutoPreviewDialingByUserIdAndRoleIdAndPsmId.getPreviewWindowTime().intValue());
        assertTrue(actualAutoPreviewDialingByUserIdAndRoleIdAndPsmId.getIsAutoPreviewDial());
    }

    /**
     * Method under test:
     * {@link AutoPreviewDialingImpl#getRatingsByUserIdAndPsmId(Integer, Integer)}
     */
    @Test
    void testGetRatingsByUserIdAndPsmId() {
        // Arrange
        when(qualityAuditorRatingRepo.getLatestRatingsByAgentIdAndPsmId(Mockito.<Integer>any(), Mockito.<Integer>any()))
                .thenReturn(new ArrayList<>());

        // Act
        List<ResponseGetRatingsDTO> actualRatingsByUserIdAndPsmId = autoPreviewDialingImpl.getRatingsByUserIdAndPsmId(1, 1);

        // Assert
        verify(qualityAuditorRatingRepo).getLatestRatingsByAgentIdAndPsmId(isA(Integer.class), isA(Integer.class));
        assertTrue(actualRatingsByUserIdAndPsmId.isEmpty());
    }

    /**
     * Method under test:
     * {@link AutoPreviewDialingImpl#getRatingsByUserIdAndPsmId(Integer, Integer)}
     */
    @Test
    void testGetRatingsByUserIdAndPsmId2() {
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

        ArrayList<QualityAuditorRating> qualityAuditorRatingList = new ArrayList<>();
        qualityAuditorRatingList.add(qualityAuditorRating);
        when(qualityAuditorRatingRepo.getLatestRatingsByAgentIdAndPsmId(Mockito.<Integer>any(), Mockito.<Integer>any()))
                .thenReturn(qualityAuditorRatingList);

        // Act
        List<ResponseGetRatingsDTO> actualRatingsByUserIdAndPsmId = autoPreviewDialingImpl.getRatingsByUserIdAndPsmId(1, 1);

        // Assert
        verify(qualityAuditorRatingRepo).getLatestRatingsByAgentIdAndPsmId(isA(Integer.class), isA(Integer.class));
        assertEquals(1, actualRatingsByUserIdAndPsmId.size());
        ResponseGetRatingsDTO getResult = actualRatingsByUserIdAndPsmId.get(0);
        assertEquals("3%", getResult.getScoreInPercentage());
        assertEquals("Final Grade", getResult.getGrade());
    }

    /**
     * Method under test:
     * {@link AutoPreviewDialingImpl#getRatingsByUserIdAndPsmId(Integer, Integer)}
     */
    @Test
    void testGetRatingsByUserIdAndPsmId3() {
        // Arrange
        when(qualityAuditorRatingRepo.getLatestRatingsByAgentIdAndPsmId(Mockito.<Integer>any(), Mockito.<Integer>any()))
                .thenThrow(new InvalidRequestException("An error occurred"));

        // Act and Assert
        assertThrows(ECDException.class, () -> autoPreviewDialingImpl.getRatingsByUserIdAndPsmId(1, 1));
        verify(qualityAuditorRatingRepo).getLatestRatingsByAgentIdAndPsmId(isA(Integer.class), isA(Integer.class));
    }
}
