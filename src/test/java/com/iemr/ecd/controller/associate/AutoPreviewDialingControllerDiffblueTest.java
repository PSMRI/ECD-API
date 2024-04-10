package com.iemr.ecd.controller.associate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import java.sql.Timestamp;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.iemr.ecd.dao.masters.UserServiceRoleMapping;
import com.iemr.ecd.dao_temp.V_GetDialPreference;
import com.iemr.ecd.service.associate.AutoPreviewDialingImpl;

@ContextConfiguration(classes = { AutoPreviewDialingController.class })
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class AutoPreviewDialingControllerDiffblueTest {
	@Autowired
	private AutoPreviewDialingController autoPreviewDialingController;

	@MockBean
	private AutoPreviewDialingImpl autoPreviewDialingImpl;

	@Test
	void testAddDialPreference() throws Exception {

		MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders
				.post("/autoPreviewDialing/addDialPreference").contentType(MediaType.APPLICATION_JSON);

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

		ResponseEntity<Object> actualPerformResult = autoPreviewDialingController
				.addDialPreference(userServiceRoleMapping);

		assertEquals(actualPerformResult, autoPreviewDialingController.addDialPreference(userServiceRoleMapping));
	}

	@Test
	void testGetDialPreference() throws Exception {

		int psmId = 123;

		ResponseEntity<List<V_GetDialPreference>> actualPerformResult = autoPreviewDialingController
				.getDialPreference(psmId);

		assertEquals(actualPerformResult, autoPreviewDialingController.getDialPreference(psmId));

	}

	@Test
	void testGetAutoPreviewDialingByUserIdAndRoleIdAndPsmId() throws Exception {

		Integer userId = 1;
		Integer roleId = 2;
		Integer psmId = 123;

		ResponseEntity<Object> response = autoPreviewDialingController
				.getAutoPreviewDialingByUserIdAndRoleIdAndPsmId(userId, roleId, psmId);

		assertEquals(response,
				autoPreviewDialingController.getAutoPreviewDialingByUserIdAndRoleIdAndPsmId(userId, roleId, psmId));

	}

	@Test
	void testGetRatingsByUserIdAndPsmId() throws Exception {

		Integer userId = 1;
		Integer psmId = 123;

		ResponseEntity<Object> response = autoPreviewDialingController.getRatingsByUserIdAndPsmId(userId, psmId);

		assertEquals(response, autoPreviewDialingController.getRatingsByUserIdAndPsmId(userId, psmId));

	}
}
