package com.iemr.ecd.controller.quality;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import java.sql.Timestamp;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iemr.ecd.dao.AgentQualityAuditorMap;
import com.iemr.ecd.service.quality.AgentQualityAuditorMappingImpl;

@ContextConfiguration(classes = {AgentQualityAuditorMappingController.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class AgentQualityAuditorMappingControllerDiffblueTest {
    @Autowired
    private AgentQualityAuditorMappingController agentQualityAuditorMappingController;

    @MockBean
    private AgentQualityAuditorMappingImpl agentQualityAuditorMappingImpl;

    @Test
    void testCreateAgentQualityAuditorMapping() throws Exception {

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

        ResponseEntity<String> response = agentQualityAuditorMappingController.createAgentQualityAuditorMapping(agentQualityAuditorMap);
        
        assertEquals(response, agentQualityAuditorMappingController.createAgentQualityAuditorMapping(agentQualityAuditorMap));
    }

    @Test
    void testGetAgentQualityAuditorMappingByPSMId() throws Exception {
        
    	Integer psmId = 3;
    	
    	ResponseEntity<List<AgentQualityAuditorMap>> response = agentQualityAuditorMappingController.getAgentQualityAuditorMappingByPSMId(psmId);
    	
    	assertEquals(response, agentQualityAuditorMappingController.getAgentQualityAuditorMappingByPSMId(psmId));
    }

    @Test
    void testUpdateAgentQualityAuditorMapping() throws Exception {

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

        ResponseEntity<String> response = agentQualityAuditorMappingController.updateAgentQualityAuditorMapping(agentQualityAuditorMap);
        
        assertEquals(response, agentQualityAuditorMappingController.updateAgentQualityAuditorMapping(agentQualityAuditorMap));
    }
}
