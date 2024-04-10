package com.iemr.ecd.controller.associate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import java.sql.Timestamp;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.iemr.ecd.dao.associate.Bencall;
import com.iemr.ecd.dto.RequestBeneficiaryQuestionnaireResponseDTO;
import com.iemr.ecd.service.associate.BeneficiaryCallHistoryImpl;

@ContextConfiguration(classes = { BeneficiaryCallHistoryController.class,
		RequestBeneficiaryQuestionnaireResponseDTO.class })
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class BeneficiaryCallHistoryControllerDiffblueTest {
	@Autowired
	private RequestBeneficiaryQuestionnaireResponseDTO requestBeneficiaryQuestionnaireResponseDTO;

	@Autowired
	private BeneficiaryCallHistoryController beneficiaryCallHistoryController;

	@MockBean
	private BeneficiaryCallHistoryImpl beneficiaryCallHistoryImpl;

	@Test
	void testGetBeneficiaryCallHistory() throws Exception {

		Long motherId = 123L;
		Long childId = 234L;

		ResponseEntity<Object> response = beneficiaryCallHistoryController.getBeneficiaryCallHistory(motherId, childId);

		assertEquals(response, beneficiaryCallHistoryController.getBeneficiaryCallHistory(motherId, childId));
	}

	@Test
	void testGetBeneficiaryCallDetails() throws Exception {

		Long obCallId = 12L;

		ResponseEntity<Object> response = beneficiaryCallHistoryController.getBeneficiaryCallDetails(obCallId);

		assertEquals(response, beneficiaryCallHistoryController.getBeneficiaryCallDetails(obCallId));

	}

	@Test
	void testGetHrpHrniDetails() throws Exception {

		Long motherId = 123L;

		Long childId = 234L;

		ResponseEntity<Object> response = beneficiaryCallHistoryController.getHrpHrniDetails(motherId, childId);

		assertEquals(response, beneficiaryCallHistoryController.getHrpHrniDetails(motherId, childId));

	}

	@Test
	void testSaveBenCallDetails() throws Exception {

		Bencall bencall = new Bencall();
		bencall.setAgentId("42");
		bencall.setArchieveFilePath("/directory/foo.txt");
		bencall.setBenCallId(1L);
		bencall.setBeneficiaryRegId(1L);
		bencall.setCallClosureType("Call Closure Type");
		bencall.setCallDuration("Call Duration");
		bencall.setCallEndTime(mock(Timestamp.class));
		bencall.setCallEndUserId(1);
		bencall.setCallId("42");
		bencall.setCallReceivedUserId(1);
		bencall.setCallRemarks("Call Remarks");
		bencall.setCallTime(mock(Timestamp.class));
		bencall.setCallTypeId(1);
		bencall.setCalledServiceId(1);
		bencall.setCategory("Category");
		bencall.setCdiCallStatus("Cdi Call Status");
		bencall.setComplaintRemarks("Complaint Remarks");
		bencall.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
		bencall.setCreatedDate(mock(Timestamp.class));
		bencall.setCzCallDuration(1);
		bencall.setCzCallEndTime(mock(Timestamp.class));
		bencall.setCzCallStartTime(mock(Timestamp.class));
		bencall.setDeleted(true);
		bencall.setDispositionStatusId(1);
		bencall.setIsCallAnswered(true);
		bencall.setIsCallAudited(true);
		bencall.setIsCallDisconnected(true);
		bencall.setIsCallVerified(true);
		bencall.setIsCalledEarlier(true);
		bencall.setIsFurtherCallRequired(true);
		bencall.setIsMother(true);
		bencall.setIsOutbound(true);
		bencall.setIsWrongNumber(true);
		bencall.setIsZeroCall(true);
		bencall.setLastModDate(mock(Timestamp.class));
		bencall.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
		bencall.setObCallId(1L);
		bencall.setPhoneNo("6625550144");
		bencall.setReasonForCallNotAnswered("Just cause");
		bencall.setReasonForNoFurtherCalls("Just cause");
		bencall.setReceivedRoleName("Received Role Name");
		bencall.setRecordingFilePath("/directory/foo.txt");
		bencall.setServicesProvided("Services Provided");
		bencall.setSessionId("42");
		bencall.setSmsAdvice("Sms Advice");
		bencall.setSmsPhone("6625550144");
		bencall.setSubCategory("Sub Category");
		bencall.setTypeOfComplaint("Type Of Complaint");

		bencall.toString();

		ResponseEntity<String> response = beneficiaryCallHistoryController.saveBenCallDetails(bencall);

		assertEquals(response, beneficiaryCallHistoryController.saveBenCallDetails(bencall));

	}

	@Test
	void testSaveBeneficiaryQuestionnaireResponse() throws Exception {

		RequestBeneficiaryQuestionnaireResponseDTO requestBeneficiaryQuestionnaireResponseDTO2 = new RequestBeneficiaryQuestionnaireResponseDTO();
		requestBeneficiaryQuestionnaireResponseDTO2.setBenCallId(1L);
		requestBeneficiaryQuestionnaireResponseDTO2.setCallId("42");
		requestBeneficiaryQuestionnaireResponseDTO2.setChildId(1L);
		requestBeneficiaryQuestionnaireResponseDTO2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
		requestBeneficiaryQuestionnaireResponseDTO2.setEcdCallType("Ecd Call Type");
		requestBeneficiaryQuestionnaireResponseDTO2.setMotherId(1L);
		requestBeneficiaryQuestionnaireResponseDTO2.setObCallId(1L);
		requestBeneficiaryQuestionnaireResponseDTO2.setPsmId(1);
		requestBeneficiaryQuestionnaireResponseDTO2.setQuestionnaireResponse(new ArrayList<>());

		requestBeneficiaryQuestionnaireResponseDTO2.toString();

		ResponseEntity<String> response = beneficiaryCallHistoryController
				.saveBeneficiaryQuestionnaireResponse(requestBeneficiaryQuestionnaireResponseDTO2);

		assertEquals(response, beneficiaryCallHistoryController
				.saveBeneficiaryQuestionnaireResponse(requestBeneficiaryQuestionnaireResponseDTO2));

	}
}
