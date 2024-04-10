package com.iemr.ecd.controller.callallocation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import java.sql.Timestamp;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.iemr.ecd.dto.OutboundCallsDTO;
import com.iemr.ecd.dto.RequestCallAllocationDTO;
import com.iemr.ecd.dto.supervisor.ResponseEligibleCallRecordsDTO;
import com.iemr.ecd.service.call_conf_allocation.CallAllocationImpl;

@ContextConfiguration(classes = { CallAllocationController.class, RequestCallAllocationDTO.class,
		OutboundCallsDTO.class })
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class CallAllocationControllerDiffblueTest {
	@Autowired
	private OutboundCallsDTO outboundCallsDTO;

	@Autowired
	private CallAllocationController callAllocationController;

	@MockBean
	private CallAllocationImpl callAllocationImpl;

	@Autowired
	private RequestCallAllocationDTO requestCallAllocationDTO;

	@Test
	void testAllocateCalls() throws Exception {

		RequestCallAllocationDTO requestCallAllocationDTO2 = new RequestCallAllocationDTO();
		requestCallAllocationDTO2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
		requestCallAllocationDTO2.setFDate("2020-03-01");
		requestCallAllocationDTO2.setFromDate(mock(Timestamp.class));
		requestCallAllocationDTO2.setFromUserIds(new Integer[] { 1 });
		requestCallAllocationDTO2.setIsIntroductory(true);
		requestCallAllocationDTO2.setNoOfCalls(1);
		requestCallAllocationDTO2.setPhoneNoType("6625550144");
		requestCallAllocationDTO2.setProviderServiceMapId(1);
		requestCallAllocationDTO2.setPsmId(1);
		requestCallAllocationDTO2.setRecordType("Record Type");
		requestCallAllocationDTO2.setRoleId(1);
		requestCallAllocationDTO2.setRoleName("Role Name");
		requestCallAllocationDTO2.setTDate("2020-03-01");
		requestCallAllocationDTO2.setToDate(mock(Timestamp.class));
		requestCallAllocationDTO2.setToUserIds(new Integer[] { 1 });
		requestCallAllocationDTO2.setUserId(1);

		requestCallAllocationDTO2.toString();

		ResponseEntity<Object> response = callAllocationController.allocateCalls(requestCallAllocationDTO2);

		assertEquals(response, callAllocationController.allocateCalls(requestCallAllocationDTO2));
	}

	@Test
	void testGetAllocatedCallCountUser() throws Exception {

		RequestCallAllocationDTO requestCallAllocationDTO2 = new RequestCallAllocationDTO();
		requestCallAllocationDTO2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
		requestCallAllocationDTO2.setFDate("2020-03-01");
		requestCallAllocationDTO2.setFromDate(mock(Timestamp.class));
		requestCallAllocationDTO2.setFromUserIds(new Integer[] { 1 });
		requestCallAllocationDTO2.setIsIntroductory(true);
		requestCallAllocationDTO2.setNoOfCalls(1);
		requestCallAllocationDTO2.setPhoneNoType("6625550144");
		requestCallAllocationDTO2.setProviderServiceMapId(1);
		requestCallAllocationDTO2.setPsmId(1);
		requestCallAllocationDTO2.setRecordType("Record Type");
		requestCallAllocationDTO2.setRoleId(1);
		requestCallAllocationDTO2.setRoleName("Role Name");
		requestCallAllocationDTO2.setTDate("2020-03-01");
		requestCallAllocationDTO2.setToDate(mock(Timestamp.class));
		requestCallAllocationDTO2.setToUserIds(new Integer[] { 1 });
		requestCallAllocationDTO2.setUserId(1);

		requestCallAllocationDTO2.toString();

		ResponseEntity<String> response = callAllocationController.getAllocatedCallCountUser(requestCallAllocationDTO2);

		assertEquals(response, callAllocationController.getAllocatedCallCountUser(requestCallAllocationDTO2));
	}

	@Test
	void testGetEligibleRecordsInfo() throws Exception {

		int psmId = 123;
		String phoneNoTyp = "Airtel";
		String recordTyp = "SIM Type";
		String fDate = "2013";
		String tDate = "2027";

		ResponseEligibleCallRecordsDTO responseEligibleCallRecordsDTO = new ResponseEligibleCallRecordsDTO();

		responseEligibleCallRecordsDTO.setTotalIntroductoryRecord(1);
		responseEligibleCallRecordsDTO.setTotalLowRiskRecord(1);
		responseEligibleCallRecordsDTO.setTotalHighRiskRecord(1);
		responseEligibleCallRecordsDTO.setTotalRecord(3);

		responseEligibleCallRecordsDTO.toString();

		ResponseEntity<ResponseEligibleCallRecordsDTO> response = callAllocationController.getEligibleRecordsInfo(psmId,
				phoneNoTyp, recordTyp, fDate, tDate);

		assertEquals(response,
				callAllocationController.getEligibleRecordsInfo(psmId, phoneNoTyp, recordTyp, fDate, tDate));
	}

	@Test
	void testInsertRecordsInOutboundCalls() throws Exception {

		OutboundCallsDTO outboundCallsDTO2 = new OutboundCallsDTO();
		outboundCallsDTO2.setPsmId(1);

		outboundCallsDTO2.toString();

		ResponseEntity<String> response = callAllocationController.insertRecordsInOutboundCalls(outboundCallsDTO2);

		assertEquals(response, callAllocationController.insertRecordsInOutboundCalls(outboundCallsDTO2));

	}

	@Test
	void testReAllocateCalls() throws Exception {

		RequestCallAllocationDTO requestCallAllocationDTO2 = new RequestCallAllocationDTO();
		requestCallAllocationDTO2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
		requestCallAllocationDTO2.setFDate("2020-03-01");
		requestCallAllocationDTO2.setFromDate(mock(Timestamp.class));
		requestCallAllocationDTO2.setFromUserIds(new Integer[] { 1 });
		requestCallAllocationDTO2.setIsIntroductory(true);
		requestCallAllocationDTO2.setNoOfCalls(1);
		requestCallAllocationDTO2.setPhoneNoType("6625550144");
		requestCallAllocationDTO2.setProviderServiceMapId(1);
		requestCallAllocationDTO2.setPsmId(1);
		requestCallAllocationDTO2.setRecordType("Record Type");
		requestCallAllocationDTO2.setRoleId(1);
		requestCallAllocationDTO2.setRoleName("Role Name");
		requestCallAllocationDTO2.setTDate("2020-03-01");
		requestCallAllocationDTO2.setToDate(mock(Timestamp.class));
		requestCallAllocationDTO2.setToUserIds(new Integer[] { 1 });
		requestCallAllocationDTO2.setUserId(1);

		requestCallAllocationDTO2.toString();

		ResponseEntity<Object> response = callAllocationController.reAllocateCalls(requestCallAllocationDTO2);

		assertEquals(response, callAllocationController.reAllocateCalls(requestCallAllocationDTO2));
	}

	@Test
	void testUpdateAlerts() throws Exception {

		RequestCallAllocationDTO requestCallAllocationDTO2 = new RequestCallAllocationDTO();
		requestCallAllocationDTO2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
		requestCallAllocationDTO2.setFDate("2020-03-01");
		requestCallAllocationDTO2.setFromDate(mock(Timestamp.class));
		requestCallAllocationDTO2.setFromUserIds(new Integer[] { 1 });
		requestCallAllocationDTO2.setIsIntroductory(true);
		requestCallAllocationDTO2.setNoOfCalls(1);
		requestCallAllocationDTO2.setPhoneNoType("6625550144");
		requestCallAllocationDTO2.setProviderServiceMapId(1);
		requestCallAllocationDTO2.setPsmId(1);
		requestCallAllocationDTO2.setRecordType("Record Type");
		requestCallAllocationDTO2.setRoleId(1);
		requestCallAllocationDTO2.setRoleName("Role Name");
		requestCallAllocationDTO2.setTDate("2020-03-01");
		requestCallAllocationDTO2.setToDate(mock(Timestamp.class));
		requestCallAllocationDTO2.setToUserIds(new Integer[] { 1 });
		requestCallAllocationDTO2.setUserId(1);

		requestCallAllocationDTO2.toString();

		ResponseEntity<String> response = callAllocationController.updateAlerts(requestCallAllocationDTO2);

		assertEquals(response, callAllocationController.updateAlerts(requestCallAllocationDTO2));
	}
}
