package com.iemr.ecd.controller.outboundworklist;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.iemr.ecd.dao_temp.FetchChildOutboundWorklist;
import com.iemr.ecd.dao_temp.FetchMotherOutboundWorklist;
import com.iemr.ecd.service.outbound_worklist.OutboundWorkListServiceImpl;

@ContextConfiguration(classes = { OutBoundWorklistController.class })
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class OutBoundWorklistControllerDiffblueTest {
	@Autowired
	private OutBoundWorklistController outBoundWorklistController;

	@MockBean
	private OutboundWorkListServiceImpl outboundWorkListServiceImpl;

	@Test
	void testGetChildWorklist() throws Exception {

		int userId = 1;

		ResponseEntity<List<FetchChildOutboundWorklist>> response = outBoundWorklistController.getChildWorklist(userId);

		assertEquals(response, outBoundWorklistController.getChildWorklist(userId));
	}

	@Test
	void testGetMotherWorklist() throws Exception {

		int userId = 0;

		ResponseEntity<List<FetchMotherOutboundWorklist>> response = outBoundWorklistController
				.getMotherWorklist(userId);

		assertEquals(response, outBoundWorklistController.getMotherWorklist(userId));
	}
}
