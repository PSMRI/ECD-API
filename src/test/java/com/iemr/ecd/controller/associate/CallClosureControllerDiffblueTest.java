package com.iemr.ecd.controller.associate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import java.sql.Date;
import java.sql.Timestamp;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.iemr.ecd.dto.associate.CallClosureDTO;
import com.iemr.ecd.service.associate.CallClosureImpl;

@ContextConfiguration(classes = { CallClosureController.class, CallClosureDTO.class })
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class CallClosureControllerDiffblueTest {
	@Autowired
	private CallClosureController callClosureController;

	@Autowired
	private CallClosureDTO callClosureDTO;

	@MockBean
	private CallClosureImpl callClosureImpl;

	@Test
	void testCloseCall() throws Exception {

		CallClosureDTO callClosureDTO2 = new CallClosureDTO();
		callClosureDTO2.setAgentId(1);
		callClosureDTO2.setAltPhoneNo("6625550144");
		callClosureDTO2.setBenCallId(1L);
		callClosureDTO2.setBeneficiaryRegId(1L);
		callClosureDTO2.setCallId("42");
		callClosureDTO2.setCallRemarks("Call Remarks");
		callClosureDTO2.setChildId(1L);
		callClosureDTO2.setComplaintId(1);
		callClosureDTO2.setComplaintRemarks("Complaint Remarks");
		callClosureDTO2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
		callClosureDTO2.setCreatedDate(mock(Timestamp.class));
		callClosureDTO2.setDeleted(true);
		callClosureDTO2.setEcdCallType("Ecd Call Type");
		callClosureDTO2.setIsCallAnswered(true);
		callClosureDTO2.setIsCallDisconnected(true);
		callClosureDTO2.setIsCallVerified(true);
		callClosureDTO2.setIsFurtherCallRequired(true);
		callClosureDTO2.setIsHrni(true);
		callClosureDTO2.setIsHrp(true);
		callClosureDTO2.setIsOutbound(true);
		callClosureDTO2.setIsStickyAgentRequired(true);
		callClosureDTO2.setIsWrongNumber(true);
		callClosureDTO2.setLastModDate(mock(Date.class));
		callClosureDTO2.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
		callClosureDTO2.setMotherId(1L);
		callClosureDTO2.setNextAttemptDate("2020-03-01");
		callClosureDTO2.setNextCallDate(mock(Timestamp.class));
		callClosureDTO2.setObCallId(1L);
		callClosureDTO2.setPhoneNo("6625550144");
		callClosureDTO2.setPsmId(1);
		callClosureDTO2.setReasonForCallNotAnswered("Just cause");
		callClosureDTO2.setReasonForCallNotAnsweredId(1);
		callClosureDTO2.setReasonForNoFurtherCalls("Just cause");
		callClosureDTO2.setReasonForNoFurtherCallsId(1);
		callClosureDTO2.setRole("Role");
		callClosureDTO2.setSendAdvice("Send Advice");
		callClosureDTO2.setTypeOfComplaint("Type Of Complaint");
		callClosureDTO2.setUserId(1);

		callClosureDTO2.toString();

		ResponseEntity<String> response = callClosureController.closeCall(callClosureDTO2);

		assertEquals(response, callClosureController.closeCall(callClosureDTO2));
	}
}
