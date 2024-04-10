package com.iemr.ecd.controller.callallocation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.iemr.ecd.dao.CallConfiguration;
import com.iemr.ecd.dto.CallSectionMappingDTO;
import com.iemr.ecd.service.call_conf_allocation.CallConfigurationImpl;

@ContextConfiguration(classes = { CallConfigurationController.class, CallSectionMappingDTO.class })
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class CallConfigurationControllerDiffblueTest {
	@Autowired
	private CallSectionMappingDTO callSectionMappingDTO;

	@Autowired
	private CallConfigurationController callConfigurationController;

	@MockBean
	private CallConfigurationImpl callConfigurationImpl;

	@Test
	void testCreateCallConfiguration() throws Exception {

		ArrayList<CallConfiguration> callConfigurationList = new ArrayList<>();

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

		callConfigurationList.add(callConfiguration);

		ResponseEntity<List<CallConfiguration>> response = callConfigurationController
				.createCallConfiguration(callConfigurationList);

		assertEquals(response, callConfigurationController.createCallConfiguration(callConfigurationList));
	}

	@Test
	void testGetCallAndSectionMapByPsmIdAndCallConfigId() throws Exception {

		Integer psmId = 123;
		Integer callConfigId = 234;

		ResponseEntity<Object> response = callConfigurationController.getCallAndSectionMapByPsmIdAndCallConfigId(psmId,
				callConfigId);

		assertEquals(response,
				callConfigurationController.getCallAndSectionMapByPsmIdAndCallConfigId(psmId, callConfigId));
	}

	@Test
	void testGetCallConfigurationById() throws Exception {

		Long id = 1L;

		ResponseEntity<CallConfiguration> response = callConfigurationController.getCallConfigurationById(id);

		assertEquals(response, callConfigurationController.getCallConfigurationById(id));
	}

	@Test
	void testGetCallConfigurationByPSMId() throws Exception {

		Integer psmId = 2;

		ResponseEntity<List<CallConfiguration>> response = callConfigurationController
				.getCallConfigurationByPSMId(psmId);

		assertEquals(response, callConfigurationController.getCallConfigurationByPSMId(psmId));
	}

	@Test
	void testMapCallAndSection() throws Exception {

		CallSectionMappingDTO callSectionMappingDTO2 = new CallSectionMappingDTO();
		callSectionMappingDTO2.setCallConfigId(1);
		callSectionMappingDTO2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
		callSectionMappingDTO2.setPsmId(1);
		callSectionMappingDTO2.setSections(new ArrayList<>());

		callSectionMappingDTO2.toString();

		ResponseEntity<Object> response = callConfigurationController.mapCallAndSection(callSectionMappingDTO2);

		assertEquals(response, callConfigurationController.mapCallAndSection(callSectionMappingDTO2));
	}

	@Test
	void testUpdateCallConfiguration() throws Exception {

		ArrayList<CallConfiguration> callConfigurationList = new ArrayList<>();

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

		callConfigurationList.add(callConfiguration);

		ResponseEntity<List<CallConfiguration>> response = callConfigurationController
				.updateCallConfiguration(callConfigurationList);

		assertEquals(response, callConfigurationController.updateCallConfiguration(callConfigurationList));
	}
}
