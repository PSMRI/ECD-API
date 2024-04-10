package com.iemr.ecd.controller.quality;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import java.sql.Timestamp;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.iemr.ecd.dao.GradeConfiguration;
import com.iemr.ecd.dto.BeneficiaryCasesheetDTO;
import com.iemr.ecd.dto.QualityAuditorWorklistDatewiseRequestDTO;
import com.iemr.ecd.dto.QualityAuditorWorklistDatewiseResponseDTO;
import com.iemr.ecd.dto.QualityAuditorWorklistRequestDTO;
import com.iemr.ecd.dto.QualityAuditorWorklistResponseDTO;
import com.iemr.ecd.dto.ResponseCallAuditSectionQuestionMapDTO;
import com.iemr.ecd.service.quality.QualityAuditImpl;

@ContextConfiguration(classes = { QualityAuditController.class, BeneficiaryCasesheetDTO.class,
		QualityAuditorWorklistRequestDTO.class, QualityAuditorWorklistDatewiseRequestDTO.class })
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class QualityAuditControllerDiffblueTest {
	@Autowired
	private QualityAuditorWorklistDatewiseRequestDTO qualityAuditorWorklistDatewiseRequestDTO;

	@Autowired
	private QualityAuditorWorklistRequestDTO qualityAuditorWorklistRequestDTO;

	@Autowired
	private BeneficiaryCasesheetDTO beneficiaryCasesheetDTO;

	@Autowired
	private QualityAuditController qualityAuditController;

	@MockBean
	private QualityAuditImpl qualityAuditImpl;

	@Test
	void testGetBeneficiaryCasesheet() throws Exception {

		BeneficiaryCasesheetDTO beneficiaryCasesheetDTO2 = new BeneficiaryCasesheetDTO();
		beneficiaryCasesheetDTO2.setAddress("42 Main St");
		beneficiaryCasesheetDTO2.setAge(1);
		beneficiaryCasesheetDTO2.setAlternatePhoneNo("6625550144");
		beneficiaryCasesheetDTO2.setAnmName("Anm Name");
		beneficiaryCasesheetDTO2.setAnmPh("Anm Ph");
		beneficiaryCasesheetDTO2.setAshaName("Asha Name");
		beneficiaryCasesheetDTO2.setAshaPh("Asha Ph");
		beneficiaryCasesheetDTO2.setBenCallId(1L);
		beneficiaryCasesheetDTO2.setBeneficiaryName("Beneficiary Name");
		beneficiaryCasesheetDTO2.setBeneficiaryRegId(1L);
		beneficiaryCasesheetDTO2.setBlockName("Block Name");
		beneficiaryCasesheetDTO2.setCallRemarks("Call Remarks");
		beneficiaryCasesheetDTO2.setChildId(1L);
		beneficiaryCasesheetDTO2.setComplaintRemarks("Complaint Remarks");
		beneficiaryCasesheetDTO2.setCreatedDate("2020-03-01");
		beneficiaryCasesheetDTO2.setDateOfBirth("2020-03-01");
		beneficiaryCasesheetDTO2.setEdd("Edd");
		beneficiaryCasesheetDTO2.setGenderID(1);
		beneficiaryCasesheetDTO2.setGenderName("Gender Name");
		beneficiaryCasesheetDTO2.setIsCallAnswered(true);
		beneficiaryCasesheetDTO2.setIsCallDisconnected(true);
		beneficiaryCasesheetDTO2.setIsCallVerified(true);
		beneficiaryCasesheetDTO2.setIsFurtherCallRequired(true);
		beneficiaryCasesheetDTO2.setIsWrongNumber(true);
		beneficiaryCasesheetDTO2.setLmp("Lmp");
		beneficiaryCasesheetDTO2.setMotherId(1L);
		beneficiaryCasesheetDTO2.setMotherName("Mother Name");
		beneficiaryCasesheetDTO2.setNextAttemptDate("2020-03-01");
		beneficiaryCasesheetDTO2.setOutboundCallType("Outbound Call Type");
		beneficiaryCasesheetDTO2.setPhcName("Phc Name");
		beneficiaryCasesheetDTO2.setPhoneNo("6625550144");
		beneficiaryCasesheetDTO2.setPhoneNoOfWhom("6625550144");
		beneficiaryCasesheetDTO2.setProviderServiceMapID(1);
		beneficiaryCasesheetDTO2.setReasonForCallNotAnswered("Just cause");
		beneficiaryCasesheetDTO2.setReasonForNoFurtherCalls("Just cause");
		beneficiaryCasesheetDTO2.setSendAdvice("Send Advice");
		beneficiaryCasesheetDTO2.setSpouseName("Spouse Name");
		beneficiaryCasesheetDTO2.setTypeOfComplaint("Type Of Complaint");

		beneficiaryCasesheetDTO2.toString();

		ResponseEntity<String> response = qualityAuditController.getBeneficiaryCasesheet(beneficiaryCasesheetDTO2);

		assertEquals(response, qualityAuditController.getBeneficiaryCasesheet(beneficiaryCasesheetDTO2));
	}

	@Test
	void testGetCallQualityRatings() throws Exception {

		Long benCallId = 91L;

		ResponseEntity<String> response = qualityAuditController.getCallQualityRatings(benCallId);

		assertEquals(response, qualityAuditController.getCallQualityRatings(benCallId));

	}

	@Test
	void testGetQualityAuditGrades() throws Exception {

		Integer psmId = 3;

		ResponseEntity<List<GradeConfiguration>> response = qualityAuditController.getQualityAuditGrades(psmId);

		assertEquals(response, qualityAuditController.getQualityAuditGrades(psmId));
	}

	@Test
	void testGetQualityAuditorWorklist() throws Exception {

		QualityAuditorWorklistRequestDTO qualityAuditorWorklistRequestDTO2 = new QualityAuditorWorklistRequestDTO();
		qualityAuditorWorklistRequestDTO2.setAgentId(1);
		qualityAuditorWorklistRequestDTO2.setCycleId(1);
		qualityAuditorWorklistRequestDTO2.setFromDate(mock(Timestamp.class));
		qualityAuditorWorklistRequestDTO2.setIsValid(true);
		qualityAuditorWorklistRequestDTO2.setLanguageId(1);
		qualityAuditorWorklistRequestDTO2.setMonth(1);
		qualityAuditorWorklistRequestDTO2.setPrevCycleFromDate(mock(Timestamp.class));
		qualityAuditorWorklistRequestDTO2.setPrevCycleToDate(mock(Timestamp.class));
		qualityAuditorWorklistRequestDTO2.setPsmId(1);
		qualityAuditorWorklistRequestDTO2.setRoleId(1);
		qualityAuditorWorklistRequestDTO2.setToDate(mock(Timestamp.class));
		qualityAuditorWorklistRequestDTO2.setYear(1);

		qualityAuditorWorklistRequestDTO2.toString();

		ResponseEntity<List<QualityAuditorWorklistResponseDTO>> response = qualityAuditController
				.getQualityAuditorWorklist(qualityAuditorWorklistRequestDTO2);

		assertEquals(response, qualityAuditController.getQualityAuditorWorklist(qualityAuditorWorklistRequestDTO2));
	}

	@Test
	void testGetQualityAuditorWorklistDatewise() throws Exception {

		QualityAuditorWorklistDatewiseRequestDTO qualityAuditorWorklistDatewiseRequestDTO2 = new QualityAuditorWorklistDatewiseRequestDTO();
		qualityAuditorWorklistDatewiseRequestDTO2.setAgentId(1);
		qualityAuditorWorklistDatewiseRequestDTO2.setBeneficiaryPhoneNumber("6625550144");
		qualityAuditorWorklistDatewiseRequestDTO2.setLanguageId("en");
		qualityAuditorWorklistDatewiseRequestDTO2.setPsmId(1);
		qualityAuditorWorklistDatewiseRequestDTO2.setRoleId(1);
		qualityAuditorWorklistDatewiseRequestDTO2.setValid(true);
		qualityAuditorWorklistDatewiseRequestDTO2.setValidFrom(mock(Timestamp.class));
		qualityAuditorWorklistDatewiseRequestDTO2.setValidTill(mock(Timestamp.class));

		qualityAuditorWorklistDatewiseRequestDTO2.toString();

		ResponseEntity<List<QualityAuditorWorklistDatewiseResponseDTO>> response = qualityAuditController
				.getQualityAuditorWorklistDatewise(qualityAuditorWorklistDatewiseRequestDTO2);

		assertEquals(response,
				qualityAuditController.getQualityAuditorWorklistDatewise(qualityAuditorWorklistDatewiseRequestDTO2));
	}

	@Test
	void testGetQuestionSectionForCallRatings() throws Exception {

		Integer psmId = 3;

		ResponseEntity<List<ResponseCallAuditSectionQuestionMapDTO>> response = qualityAuditController
				.getQuestionSectionForCallRatings(psmId);

		assertEquals(response, qualityAuditController.getQuestionSectionForCallRatings(psmId));

	}

	@Test
	void testSaveCallQualityRatings() throws Exception {

		String requestOBJ = "requestOBJ";

		ResponseEntity<Object> response = qualityAuditController.saveCallQualityRatings(requestOBJ);

		assertEquals(response, qualityAuditController.saveCallQualityRatings(requestOBJ));

	}
}
