package com.iemr.ecd.service.quality;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

import com.iemr.ecd.dao.AgentQualityAuditorMap;
import com.iemr.ecd.repository.quality.AgentQualityAuditorMapRepo;
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

@ContextConfiguration(classes = {AgentQualityAuditorMappingImpl.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class AgentQualityAuditorMappingImplDiffblueTest {
    @MockBean
    private AgentQualityAuditorMapRepo agentQualityAuditorMapRepo;

    @Autowired
    private AgentQualityAuditorMappingImpl agentQualityAuditorMappingImpl;

    /**
     * Method under test:
     * {@link AgentQualityAuditorMappingImpl#createAgentQualityAuditorMapping(AgentQualityAuditorMap)}
     */
    @Test
    void testCreateAgentQualityAuditorMapping() {
        // Arrange
        when(agentQualityAuditorMapRepo.saveAll(Mockito.<Iterable<AgentQualityAuditorMap>>any()))
                .thenReturn(new ArrayList<>());

        AgentQualityAuditorMap agentQualityAuditorMap = new AgentQualityAuditorMap();
        agentQualityAuditorMap.setAgentId(1);
        agentQualityAuditorMap.setAgentIds(new Integer[]{1});
        agentQualityAuditorMap.setAgentName("Agent Name");
        agentQualityAuditorMap.setAgentNames(new String[]{"Agent Names"});
        agentQualityAuditorMap.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        agentQualityAuditorMap.setCreatedDate(mock(Timestamp.class));
        agentQualityAuditorMap.setDeleted(true);
        agentQualityAuditorMap.setId(1);
        agentQualityAuditorMap.setLastModDate(mock(Timestamp.class));
        agentQualityAuditorMap.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        agentQualityAuditorMap.setPsmId(1);
        agentQualityAuditorMap.setQualityAuditorId(1);
        agentQualityAuditorMap.setQualityAuditorName("Quality Auditor Name");
        agentQualityAuditorMap.setRoleId(1);
        agentQualityAuditorMap.setRoleName("Role Name");
        
        agentQualityAuditorMap.toString();

        // Act
        String actualCreateAgentQualityAuditorMappingResult = agentQualityAuditorMappingImpl
                .createAgentQualityAuditorMapping(agentQualityAuditorMap);

        // Assert
        verify(agentQualityAuditorMapRepo).saveAll(isA(Iterable.class));
        assertEquals("{\"response\":\"Agent Quality Auditor Mapping Created Successfully\"}",
                actualCreateAgentQualityAuditorMappingResult);
    }

    /**
     * Method under test:
     * {@link AgentQualityAuditorMappingImpl#createAgentQualityAuditorMapping(AgentQualityAuditorMap)}
     */
    @Test
    void testCreateAgentQualityAuditorMapping2() {
        // Arrange
        AgentQualityAuditorMap agentQualityAuditorMap = mock(AgentQualityAuditorMap.class);
        when(agentQualityAuditorMap.getPsmId()).thenThrow(new ECDException("Exception Msg"));
        when(agentQualityAuditorMap.getQualityAuditorId()).thenReturn(1);
        when(agentQualityAuditorMap.getRoleId()).thenReturn(1);
        when(agentQualityAuditorMap.getQualityAuditorName()).thenReturn("Quality Auditor Name");
        when(agentQualityAuditorMap.getRoleName()).thenReturn("Role Name");
        when(agentQualityAuditorMap.getAgentNames()).thenReturn(new String[]{"Agent Names"});
        when(agentQualityAuditorMap.getAgentIds()).thenReturn(new Integer[]{1});
        doNothing().when(agentQualityAuditorMap).setAgentId(Mockito.<Integer>any());
        doNothing().when(agentQualityAuditorMap).setAgentIds(Mockito.<Integer[]>any());
        doNothing().when(agentQualityAuditorMap).setAgentName(Mockito.<String>any());
        doNothing().when(agentQualityAuditorMap).setAgentNames(Mockito.<String[]>any());
        doNothing().when(agentQualityAuditorMap).setCreatedBy(Mockito.<String>any());
        doNothing().when(agentQualityAuditorMap).setCreatedDate(Mockito.<Timestamp>any());
        doNothing().when(agentQualityAuditorMap).setDeleted(Mockito.<Boolean>any());
        doNothing().when(agentQualityAuditorMap).setId(Mockito.<Integer>any());
        doNothing().when(agentQualityAuditorMap).setLastModDate(Mockito.<Timestamp>any());
        doNothing().when(agentQualityAuditorMap).setModifiedBy(Mockito.<String>any());
        doNothing().when(agentQualityAuditorMap).setPsmId(Mockito.<Integer>any());
        doNothing().when(agentQualityAuditorMap).setQualityAuditorId(Mockito.<Integer>any());
        doNothing().when(agentQualityAuditorMap).setQualityAuditorName(Mockito.<String>any());
        doNothing().when(agentQualityAuditorMap).setRoleId(Mockito.<Integer>any());
        doNothing().when(agentQualityAuditorMap).setRoleName(Mockito.<String>any());
        agentQualityAuditorMap.setAgentId(1);
        agentQualityAuditorMap.setAgentIds(new Integer[]{1});
        agentQualityAuditorMap.setAgentName("Agent Name");
        agentQualityAuditorMap.setAgentNames(new String[]{"Agent Names"});
        agentQualityAuditorMap.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        agentQualityAuditorMap.setCreatedDate(mock(Timestamp.class));
        agentQualityAuditorMap.setDeleted(true);
        agentQualityAuditorMap.setId(1);
        agentQualityAuditorMap.setLastModDate(mock(Timestamp.class));
        agentQualityAuditorMap.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        agentQualityAuditorMap.setPsmId(1);
        agentQualityAuditorMap.setQualityAuditorId(1);
        agentQualityAuditorMap.setQualityAuditorName("Quality Auditor Name");
        agentQualityAuditorMap.setRoleId(1);
        agentQualityAuditorMap.setRoleName("Role Name");
        
        agentQualityAuditorMap.toString();

        // Act and Assert
        assertThrows(ECDException.class,
                () -> agentQualityAuditorMappingImpl.createAgentQualityAuditorMapping(agentQualityAuditorMap));
        verify(agentQualityAuditorMap, atLeast(1)).getAgentIds();
        verify(agentQualityAuditorMap).getAgentNames();
        verify(agentQualityAuditorMap).getPsmId();
        verify(agentQualityAuditorMap).getQualityAuditorId();
        verify(agentQualityAuditorMap).getQualityAuditorName();
        verify(agentQualityAuditorMap).getRoleId();
        verify(agentQualityAuditorMap).getRoleName();
        verify(agentQualityAuditorMap).setAgentId(isA(Integer.class));
        verify(agentQualityAuditorMap).setAgentIds(isA(Integer[].class));
        verify(agentQualityAuditorMap).setAgentName(eq("Agent Name"));
        verify(agentQualityAuditorMap).setAgentNames(isA(String[].class));
        verify(agentQualityAuditorMap).setCreatedBy(eq("Jan 1, 2020 8:00am GMT+0100"));
        verify(agentQualityAuditorMap).setCreatedDate(isA(Timestamp.class));
        verify(agentQualityAuditorMap).setDeleted(isA(Boolean.class));
        verify(agentQualityAuditorMap).setId(isA(Integer.class));
        verify(agentQualityAuditorMap).setLastModDate(isA(Timestamp.class));
        verify(agentQualityAuditorMap).setModifiedBy(eq("Jan 1, 2020 9:00am GMT+0100"));
        verify(agentQualityAuditorMap).setPsmId(isA(Integer.class));
        verify(agentQualityAuditorMap).setQualityAuditorId(isA(Integer.class));
        verify(agentQualityAuditorMap).setQualityAuditorName(eq("Quality Auditor Name"));
        verify(agentQualityAuditorMap).setRoleId(isA(Integer.class));
        verify(agentQualityAuditorMap).setRoleName(eq("Role Name"));
    }

    /**
     * Method under test:
     * {@link AgentQualityAuditorMappingImpl#createAgentQualityAuditorMapping(AgentQualityAuditorMap)}
     */
    @Test
    void testCreateAgentQualityAuditorMapping3() {
        // Arrange
        when(agentQualityAuditorMapRepo.saveAll(Mockito.<Iterable<AgentQualityAuditorMap>>any()))
                .thenReturn(new ArrayList<>());
        AgentQualityAuditorMap agentQualityAuditorMap = mock(AgentQualityAuditorMap.class);
        when(agentQualityAuditorMap.getAgentIds()).thenReturn(null);
        doNothing().when(agentQualityAuditorMap).setAgentId(Mockito.<Integer>any());
        doNothing().when(agentQualityAuditorMap).setAgentIds(Mockito.<Integer[]>any());
        doNothing().when(agentQualityAuditorMap).setAgentName(Mockito.<String>any());
        doNothing().when(agentQualityAuditorMap).setAgentNames(Mockito.<String[]>any());
        doNothing().when(agentQualityAuditorMap).setCreatedBy(Mockito.<String>any());
        doNothing().when(agentQualityAuditorMap).setCreatedDate(Mockito.<Timestamp>any());
        doNothing().when(agentQualityAuditorMap).setDeleted(Mockito.<Boolean>any());
        doNothing().when(agentQualityAuditorMap).setId(Mockito.<Integer>any());
        doNothing().when(agentQualityAuditorMap).setLastModDate(Mockito.<Timestamp>any());
        doNothing().when(agentQualityAuditorMap).setModifiedBy(Mockito.<String>any());
        doNothing().when(agentQualityAuditorMap).setPsmId(Mockito.<Integer>any());
        doNothing().when(agentQualityAuditorMap).setQualityAuditorId(Mockito.<Integer>any());
        doNothing().when(agentQualityAuditorMap).setQualityAuditorName(Mockito.<String>any());
        doNothing().when(agentQualityAuditorMap).setRoleId(Mockito.<Integer>any());
        doNothing().when(agentQualityAuditorMap).setRoleName(Mockito.<String>any());
        agentQualityAuditorMap.setAgentId(1);
        agentQualityAuditorMap.setAgentIds(new Integer[]{1});
        agentQualityAuditorMap.setAgentName("Agent Name");
        agentQualityAuditorMap.setAgentNames(new String[]{"Agent Names"});
        agentQualityAuditorMap.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        agentQualityAuditorMap.setCreatedDate(mock(Timestamp.class));
        agentQualityAuditorMap.setDeleted(true);
        agentQualityAuditorMap.setId(1);
        agentQualityAuditorMap.setLastModDate(mock(Timestamp.class));
        agentQualityAuditorMap.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        agentQualityAuditorMap.setPsmId(1);
        agentQualityAuditorMap.setQualityAuditorId(1);
        agentQualityAuditorMap.setQualityAuditorName("Quality Auditor Name");
        agentQualityAuditorMap.setRoleId(1);
        agentQualityAuditorMap.setRoleName("Role Name");

        // Act
        String actualCreateAgentQualityAuditorMappingResult = agentQualityAuditorMappingImpl
                .createAgentQualityAuditorMapping(agentQualityAuditorMap);

        // Assert
        verify(agentQualityAuditorMap).getAgentIds();
        verify(agentQualityAuditorMap).setAgentId(isA(Integer.class));
        verify(agentQualityAuditorMap).setAgentIds(isA(Integer[].class));
        verify(agentQualityAuditorMap).setAgentName(eq("Agent Name"));
        verify(agentQualityAuditorMap).setAgentNames(isA(String[].class));
        verify(agentQualityAuditorMap).setCreatedBy(eq("Jan 1, 2020 8:00am GMT+0100"));
        verify(agentQualityAuditorMap).setCreatedDate(isA(Timestamp.class));
        verify(agentQualityAuditorMap).setDeleted(isA(Boolean.class));
        verify(agentQualityAuditorMap).setId(isA(Integer.class));
        verify(agentQualityAuditorMap).setLastModDate(isA(Timestamp.class));
        verify(agentQualityAuditorMap).setModifiedBy(eq("Jan 1, 2020 9:00am GMT+0100"));
        verify(agentQualityAuditorMap).setPsmId(isA(Integer.class));
        verify(agentQualityAuditorMap).setQualityAuditorId(isA(Integer.class));
        verify(agentQualityAuditorMap).setQualityAuditorName(eq("Quality Auditor Name"));
        verify(agentQualityAuditorMap).setRoleId(isA(Integer.class));
        verify(agentQualityAuditorMap).setRoleName(eq("Role Name"));
        verify(agentQualityAuditorMapRepo).saveAll(isA(Iterable.class));
        assertEquals("{\"response\":\"Agent Quality Auditor Mapping Created Successfully\"}",
                actualCreateAgentQualityAuditorMappingResult);
    }

    /**
     * Method under test:
     * {@link AgentQualityAuditorMappingImpl#createAgentQualityAuditorMapping(AgentQualityAuditorMap)}
     */
    @Test
    void testCreateAgentQualityAuditorMapping4() {
        // Arrange
        when(agentQualityAuditorMapRepo.saveAll(Mockito.<Iterable<AgentQualityAuditorMap>>any()))
                .thenReturn(new ArrayList<>());
        AgentQualityAuditorMap agentQualityAuditorMap = mock(AgentQualityAuditorMap.class);
        when(agentQualityAuditorMap.getAgentIds()).thenReturn(new Integer[]{});
        doNothing().when(agentQualityAuditorMap).setAgentId(Mockito.<Integer>any());
        doNothing().when(agentQualityAuditorMap).setAgentIds(Mockito.<Integer[]>any());
        doNothing().when(agentQualityAuditorMap).setAgentName(Mockito.<String>any());
        doNothing().when(agentQualityAuditorMap).setAgentNames(Mockito.<String[]>any());
        doNothing().when(agentQualityAuditorMap).setCreatedBy(Mockito.<String>any());
        doNothing().when(agentQualityAuditorMap).setCreatedDate(Mockito.<Timestamp>any());
        doNothing().when(agentQualityAuditorMap).setDeleted(Mockito.<Boolean>any());
        doNothing().when(agentQualityAuditorMap).setId(Mockito.<Integer>any());
        doNothing().when(agentQualityAuditorMap).setLastModDate(Mockito.<Timestamp>any());
        doNothing().when(agentQualityAuditorMap).setModifiedBy(Mockito.<String>any());
        doNothing().when(agentQualityAuditorMap).setPsmId(Mockito.<Integer>any());
        doNothing().when(agentQualityAuditorMap).setQualityAuditorId(Mockito.<Integer>any());
        doNothing().when(agentQualityAuditorMap).setQualityAuditorName(Mockito.<String>any());
        doNothing().when(agentQualityAuditorMap).setRoleId(Mockito.<Integer>any());
        doNothing().when(agentQualityAuditorMap).setRoleName(Mockito.<String>any());
        agentQualityAuditorMap.setAgentId(1);
        agentQualityAuditorMap.setAgentIds(new Integer[]{1});
        agentQualityAuditorMap.setAgentName("Agent Name");
        agentQualityAuditorMap.setAgentNames(new String[]{"Agent Names"});
        agentQualityAuditorMap.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        agentQualityAuditorMap.setCreatedDate(mock(Timestamp.class));
        agentQualityAuditorMap.setDeleted(true);
        agentQualityAuditorMap.setId(1);
        agentQualityAuditorMap.setLastModDate(mock(Timestamp.class));
        agentQualityAuditorMap.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        agentQualityAuditorMap.setPsmId(1);
        agentQualityAuditorMap.setQualityAuditorId(1);
        agentQualityAuditorMap.setQualityAuditorName("Quality Auditor Name");
        agentQualityAuditorMap.setRoleId(1);
        agentQualityAuditorMap.setRoleName("Role Name");

        // Act
        String actualCreateAgentQualityAuditorMappingResult = agentQualityAuditorMappingImpl
                .createAgentQualityAuditorMapping(agentQualityAuditorMap);

        // Assert
        verify(agentQualityAuditorMap, atLeast(1)).getAgentIds();
        verify(agentQualityAuditorMap).setAgentId(isA(Integer.class));
        verify(agentQualityAuditorMap).setAgentIds(isA(Integer[].class));
        verify(agentQualityAuditorMap).setAgentName(eq("Agent Name"));
        verify(agentQualityAuditorMap).setAgentNames(isA(String[].class));
        verify(agentQualityAuditorMap).setCreatedBy(eq("Jan 1, 2020 8:00am GMT+0100"));
        verify(agentQualityAuditorMap).setCreatedDate(isA(Timestamp.class));
        verify(agentQualityAuditorMap).setDeleted(isA(Boolean.class));
        verify(agentQualityAuditorMap).setId(isA(Integer.class));
        verify(agentQualityAuditorMap).setLastModDate(isA(Timestamp.class));
        verify(agentQualityAuditorMap).setModifiedBy(eq("Jan 1, 2020 9:00am GMT+0100"));
        verify(agentQualityAuditorMap).setPsmId(isA(Integer.class));
        verify(agentQualityAuditorMap).setQualityAuditorId(isA(Integer.class));
        verify(agentQualityAuditorMap).setQualityAuditorName(eq("Quality Auditor Name"));
        verify(agentQualityAuditorMap).setRoleId(isA(Integer.class));
        verify(agentQualityAuditorMap).setRoleName(eq("Role Name"));
        verify(agentQualityAuditorMapRepo).saveAll(isA(Iterable.class));
        assertEquals("{\"response\":\"Agent Quality Auditor Mapping Created Successfully\"}",
                actualCreateAgentQualityAuditorMappingResult);
    }

    /**
     * Method under test:
     * {@link AgentQualityAuditorMappingImpl#getAgentQualityAuditorMappingByPSMId(Integer)}
     */
    @Test
    void testGetAgentQualityAuditorMappingByPSMId() {
        // Arrange
        ArrayList<AgentQualityAuditorMap> agentQualityAuditorMapList = new ArrayList<>();
        when(agentQualityAuditorMapRepo.findByPsmId(Mockito.<Integer>any())).thenReturn(agentQualityAuditorMapList);

        // Act
        List<AgentQualityAuditorMap> actualAgentQualityAuditorMappingByPSMId = agentQualityAuditorMappingImpl
                .getAgentQualityAuditorMappingByPSMId(1);

        // Assert
        verify(agentQualityAuditorMapRepo).findByPsmId(isA(Integer.class));
        assertTrue(actualAgentQualityAuditorMappingByPSMId.isEmpty());
        assertSame(agentQualityAuditorMapList, actualAgentQualityAuditorMappingByPSMId);
    }

    /**
     * Method under test:
     * {@link AgentQualityAuditorMappingImpl#getAgentQualityAuditorMappingByPSMId(Integer)}
     */
    @Test
    void testGetAgentQualityAuditorMappingByPSMId2() {
        // Arrange
        when(agentQualityAuditorMapRepo.findByPsmId(Mockito.<Integer>any())).thenThrow(new ECDException("Exception Msg"));

        // Act and Assert
        assertThrows(ECDException.class, () -> agentQualityAuditorMappingImpl.getAgentQualityAuditorMappingByPSMId(1));
        verify(agentQualityAuditorMapRepo).findByPsmId(isA(Integer.class));
    }

    /**
     * Method under test:
     * {@link AgentQualityAuditorMappingImpl#updateAgentQualityAuditorMapping(AgentQualityAuditorMap)}
     */
    @Test
    void testUpdateAgentQualityAuditorMapping() {
        // Arrange
        AgentQualityAuditorMap agentQualityAuditorMap = new AgentQualityAuditorMap();
        agentQualityAuditorMap.setAgentId(1);
        agentQualityAuditorMap.setAgentIds(new Integer[]{1});
        agentQualityAuditorMap.setAgentName("Agent Name");
        agentQualityAuditorMap.setAgentNames(new String[]{"Agent Names"});
        agentQualityAuditorMap.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        agentQualityAuditorMap.setCreatedDate(mock(Timestamp.class));
        agentQualityAuditorMap.setDeleted(true);
        agentQualityAuditorMap.setId(1);
        agentQualityAuditorMap.setLastModDate(mock(Timestamp.class));
        agentQualityAuditorMap.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        agentQualityAuditorMap.setPsmId(1);
        agentQualityAuditorMap.setQualityAuditorId(1);
        agentQualityAuditorMap.setQualityAuditorName("Quality Auditor Name");
        agentQualityAuditorMap.setRoleId(1);
        agentQualityAuditorMap.setRoleName("Role Name");
        when(agentQualityAuditorMapRepo.save(Mockito.<AgentQualityAuditorMap>any())).thenReturn(agentQualityAuditorMap);

        AgentQualityAuditorMap agentQualityAuditorMap2 = new AgentQualityAuditorMap();
        agentQualityAuditorMap2.setAgentId(1);
        agentQualityAuditorMap2.setAgentIds(new Integer[]{1});
        agentQualityAuditorMap2.setAgentName("Agent Name");
        agentQualityAuditorMap2.setAgentNames(new String[]{"Agent Names"});
        agentQualityAuditorMap2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        agentQualityAuditorMap2.setCreatedDate(mock(Timestamp.class));
        agentQualityAuditorMap2.setDeleted(true);
        agentQualityAuditorMap2.setId(1);
        agentQualityAuditorMap2.setLastModDate(mock(Timestamp.class));
        agentQualityAuditorMap2.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        agentQualityAuditorMap2.setPsmId(1);
        agentQualityAuditorMap2.setQualityAuditorId(1);
        agentQualityAuditorMap2.setQualityAuditorName("Quality Auditor Name");
        agentQualityAuditorMap2.setRoleId(1);
        agentQualityAuditorMap2.setRoleName("Role Name");

        // Act
        String actualUpdateAgentQualityAuditorMappingResult = agentQualityAuditorMappingImpl
                .updateAgentQualityAuditorMapping(agentQualityAuditorMap2);

        // Assert
        verify(agentQualityAuditorMapRepo).save(isA(AgentQualityAuditorMap.class));
        assertEquals("{\"response\":\"Agent Quality Auditor Mapping Updated Successfully\"}",
                actualUpdateAgentQualityAuditorMappingResult);
    }

    /**
     * Method under test:
     * {@link AgentQualityAuditorMappingImpl#updateAgentQualityAuditorMapping(AgentQualityAuditorMap)}
     */
    @Test
    void testUpdateAgentQualityAuditorMapping2() {
        // Arrange
        AgentQualityAuditorMap agentQualityAuditorMap = new AgentQualityAuditorMap();
        agentQualityAuditorMap.setAgentId(1);
        agentQualityAuditorMap.setAgentIds(new Integer[]{1});
        agentQualityAuditorMap.setAgentName("Agent Name");
        agentQualityAuditorMap.setAgentNames(new String[]{"Agent Names"});
        agentQualityAuditorMap.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        agentQualityAuditorMap.setCreatedDate(mock(Timestamp.class));
        agentQualityAuditorMap.setDeleted(true);
        agentQualityAuditorMap.setId(null);
        agentQualityAuditorMap.setLastModDate(mock(Timestamp.class));
        agentQualityAuditorMap.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        agentQualityAuditorMap.setPsmId(1);
        agentQualityAuditorMap.setQualityAuditorId(1);
        agentQualityAuditorMap.setQualityAuditorName("Quality Auditor Name");
        agentQualityAuditorMap.setRoleId(1);
        agentQualityAuditorMap.setRoleName("Role Name");

        // Act and Assert
        assertEquals("{\"response\":\"Agent Quality Auditor Mapping Updated Successfully\"}",
                agentQualityAuditorMappingImpl.updateAgentQualityAuditorMapping(agentQualityAuditorMap));
    }
}
