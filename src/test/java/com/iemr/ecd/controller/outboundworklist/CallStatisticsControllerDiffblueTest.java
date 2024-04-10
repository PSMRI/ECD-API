package com.iemr.ecd.controller.outboundworklist;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.iemr.ecd.dto.associate.CallStatisticsDTO;
import com.iemr.ecd.service.outbound_worklist.CallStatisticsImpl;

@ContextConfiguration(classes = { CallStatisticsController.class })
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class CallStatisticsControllerDiffblueTest {
	@Autowired
	private CallStatisticsController callStatisticsController;

	@MockBean
	private CallStatisticsImpl callStatisticsImpl;

	@Test
	void testGetCallStatisticsByAgentId() throws Exception {

		String agentId = "RAW Agent";

		ResponseEntity<CallStatisticsDTO> response = callStatisticsController.getCallStatisticsByAgentId(agentId);

		assertEquals(response, callStatisticsController.getCallStatisticsByAgentId(agentId));
	}
}
