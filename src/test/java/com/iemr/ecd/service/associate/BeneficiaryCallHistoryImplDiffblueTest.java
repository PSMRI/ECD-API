package com.iemr.ecd.service.associate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.iemr.ecd.dao.associate.Bencall;
import com.iemr.ecd.dao.associate.ECDCallResponse;
import com.iemr.ecd.dao.associate.OutboundCalls;
import com.iemr.ecd.dto.RequestBeneficiaryQuestionnaireResponseDTO;
import com.iemr.ecd.repo.call_conf_allocation.OutboundCallsRepo;
import com.iemr.ecd.repository.ecd.ECDCallResponseRepo;
import com.iemr.ecd.repository.quality.T_benCallRepo;
import com.iemr.ecd.utils.advice.exception_handler.ECDException;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {BeneficiaryCallHistoryImpl.class, RequestBeneficiaryQuestionnaireResponseDTO.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class BeneficiaryCallHistoryImplDiffblueTest {
    @Autowired
    private RequestBeneficiaryQuestionnaireResponseDTO requestBeneficiaryQuestionnaireResponseDTO;

    @Autowired
    private BeneficiaryCallHistoryImpl beneficiaryCallHistoryImpl;

    @MockBean
    private ECDCallResponseRepo eCDCallResponseRepo;

    @MockBean
    private OutboundCallsRepo outboundCallsRepo;

    @MockBean
    private T_benCallRepo t_benCallRepo;

    /**
     * Method under test:
     * {@link BeneficiaryCallHistoryImpl#getBeneficiaryCallHistory(Long, Long)}
     */
    @Test
    void testGetBeneficiaryCallHistory() {
        // Arrange
        ArrayList<OutboundCalls> outboundCallsList = new ArrayList<>();
        when(outboundCallsRepo.getCallHistoryForMother(Mockito.<Long>any())).thenReturn(outboundCallsList);

        // Act
        List<OutboundCalls> actualBeneficiaryCallHistory = beneficiaryCallHistoryImpl.getBeneficiaryCallHistory(1L, 1L);

        // Assert
        verify(outboundCallsRepo).getCallHistoryForMother(isA(Long.class));
        assertTrue(actualBeneficiaryCallHistory.isEmpty());
        assertSame(outboundCallsList, actualBeneficiaryCallHistory);
    }

    /**
     * Method under test:
     * {@link BeneficiaryCallHistoryImpl#getBeneficiaryCallHistory(Long, Long)}
     */
    @Test
    void testGetBeneficiaryCallHistory2() {
        // Arrange
        ArrayList<OutboundCalls> outboundCallsList = new ArrayList<>();
        when(outboundCallsRepo.getCallHistoryForChild(Mockito.<Long>any())).thenReturn(outboundCallsList);

        // Act
        List<OutboundCalls> actualBeneficiaryCallHistory = beneficiaryCallHistoryImpl.getBeneficiaryCallHistory(null, 1L);

        // Assert
        verify(outboundCallsRepo).getCallHistoryForChild(isA(Long.class));
        assertTrue(actualBeneficiaryCallHistory.isEmpty());
        assertSame(outboundCallsList, actualBeneficiaryCallHistory);
    }

    /**
     * Method under test:
     * {@link BeneficiaryCallHistoryImpl#getBeneficiaryCallHistory(Long, Long)}
     */
    @Test
    void testGetBeneficiaryCallHistory3() {
        // Arrange, Act and Assert
        assertTrue(beneficiaryCallHistoryImpl.getBeneficiaryCallHistory(null, null).isEmpty());
    }

    /**
     * Method under test:
     * {@link BeneficiaryCallHistoryImpl#getBeneficiaryCallHistory(Long, Long)}
     */
    @Test
    void testGetBeneficiaryCallHistory4() {
        // Arrange
        when(outboundCallsRepo.getCallHistoryForChild(Mockito.<Long>any())).thenThrow(new ECDException("Exception Msg"));

        // Act and Assert
        assertThrows(ECDException.class, () -> beneficiaryCallHistoryImpl.getBeneficiaryCallHistory(null, 1L));
        verify(outboundCallsRepo).getCallHistoryForChild(isA(Long.class));
    }

    /**
     * Method under test:
     * {@link BeneficiaryCallHistoryImpl#getBeneficiaryCallDetails(Long)}
     */
    @Test
    void testGetBeneficiaryCallDetails() {
        // Arrange
        when(outboundCallsRepo.getBeneficiaryCallDetails(Mockito.<Long>any())).thenReturn(new ArrayList<>());

        // Act
        beneficiaryCallHistoryImpl.getBeneficiaryCallDetails(1L);

        // Assert
        verify(outboundCallsRepo).getBeneficiaryCallDetails(isA(Long.class));
    }

    /**
     * Method under test:
     * {@link BeneficiaryCallHistoryImpl#getBeneficiaryCallDetails(Long)}
     */
    @Test
    void testGetBeneficiaryCallDetails2() {
        // Arrange
        when(outboundCallsRepo.getBeneficiaryCallDetails(Mockito.<Long>any())).thenThrow(new ECDException("Exception Msg"));

        // Act and Assert
        assertThrows(ECDException.class, () -> beneficiaryCallHistoryImpl.getBeneficiaryCallDetails(1L));
        verify(outboundCallsRepo).getBeneficiaryCallDetails(isA(Long.class));
    }

    /**
     * Method under test:
     * {@link BeneficiaryCallHistoryImpl#getBeneficiaryCallDetails(Long)}
     */
    @Test
    void testGetBeneficiaryCallDetails3() {
        // Arrange
        ArrayList<String[]> stringArrayList = new ArrayList<>();
        stringArrayList.add(new String[]{"foo", "foo"});
        when(outboundCallsRepo.getBeneficiaryCallDetails(Mockito.<Long>any())).thenReturn(stringArrayList);

        // Act and Assert
        assertThrows(ECDException.class, () -> beneficiaryCallHistoryImpl.getBeneficiaryCallDetails(1L));
        verify(outboundCallsRepo).getBeneficiaryCallDetails(isA(Long.class));
    }

    /**
     * Method under test:
     * {@link BeneficiaryCallHistoryImpl#saveBenCallDetails(Bencall)}
     */
    @Test
    void testSaveBenCallDetails() throws ECDException {
        // Arrange
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
        
        when(t_benCallRepo.save(Mockito.<Bencall>any())).thenReturn(bencall);

        Bencall bencall2 = new Bencall();
        bencall2.setAgentId("42");
        bencall2.setArchieveFilePath("/directory/foo.txt");
        bencall2.setBenCallId(1L);
        bencall2.setBeneficiaryRegId(1L);
        bencall2.setCallClosureType("Call Closure Type");
        bencall2.setCallDuration("Call Duration");
        bencall2.setCallEndTime(mock(Timestamp.class));
        bencall2.setCallEndUserId(1);
        bencall2.setCallId("42");
        bencall2.setCallReceivedUserId(1);
        bencall2.setCallRemarks("Call Remarks");
        bencall2.setCallTime(mock(Timestamp.class));
        bencall2.setCallTypeId(1);
        bencall2.setCalledServiceId(1);
        bencall2.setCategory("Category");
        bencall2.setCdiCallStatus("Cdi Call Status");
        bencall2.setComplaintRemarks("Complaint Remarks");
        bencall2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        bencall2.setCreatedDate(mock(Timestamp.class));
        bencall2.setCzCallDuration(1);
        bencall2.setCzCallEndTime(mock(Timestamp.class));
        bencall2.setCzCallStartTime(mock(Timestamp.class));
        bencall2.setDeleted(true);
        bencall2.setDispositionStatusId(1);
        bencall2.setIsCallAnswered(true);
        bencall2.setIsCallAudited(true);
        bencall2.setIsCallDisconnected(true);
        bencall2.setIsCallVerified(true);
        bencall2.setIsCalledEarlier(true);
        bencall2.setIsFurtherCallRequired(true);
        bencall2.setIsMother(true);
        bencall2.setIsOutbound(true);
        bencall2.setIsWrongNumber(true);
        bencall2.setIsZeroCall(true);
        bencall2.setLastModDate(mock(Timestamp.class));
        bencall2.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        bencall2.setObCallId(1L);
        bencall2.setPhoneNo("6625550144");
        bencall2.setReasonForCallNotAnswered("Just cause");
        bencall2.setReasonForNoFurtherCalls("Just cause");
        bencall2.setReceivedRoleName("Received Role Name");
        bencall2.setRecordingFilePath("/directory/foo.txt");
        bencall2.setServicesProvided("Services Provided");
        bencall2.setSessionId("42");
        bencall2.setSmsAdvice("Sms Advice");
        bencall2.setSmsPhone("6625550144");
        bencall2.setSubCategory("Sub Category");
        bencall2.setTypeOfComplaint("Type Of Complaint");

        // Act
        String actualSaveBenCallDetailsResult = beneficiaryCallHistoryImpl.saveBenCallDetails(bencall2);

        // Assert
        verify(t_benCallRepo).save(isA(Bencall.class));
        assertEquals("{\"response\":{\"benCallId\":\"1\"}}", actualSaveBenCallDetailsResult);
    }

    /**
     * Method under test:
     * {@link BeneficiaryCallHistoryImpl#saveBeneficiaryQuestionnaireResponse(RequestBeneficiaryQuestionnaireResponseDTO)}
     */
    @Test
    void testSaveBeneficiaryQuestionnaireResponse() {
        // Arrange, Act and Assert
        assertThrows(ECDException.class, () -> beneficiaryCallHistoryImpl
                .saveBeneficiaryQuestionnaireResponse(requestBeneficiaryQuestionnaireResponseDTO));
    }

    /**
     * Method under test:
     * {@link BeneficiaryCallHistoryImpl#getHrpHrniDetails(Long, Long)}
     */
    @Test
    void testGetHrpHrniDetails() {
        // Arrange
        when(eCDCallResponseRepo.getHrniDetailsChild(Mockito.<Pageable>any(), Mockito.<Long>any()))
                .thenReturn(new PageImpl<>(new ArrayList<>()));

        // Act
        beneficiaryCallHistoryImpl.getHrpHrniDetails(1L, 1L);

        // Assert
        verify(eCDCallResponseRepo).getHrniDetailsChild(isA(Pageable.class), isA(Long.class));
    }

    /**
     * Method under test:
     * {@link BeneficiaryCallHistoryImpl#getHrpHrniDetails(Long, Long)}
     */
    @Test
    void testGetHrpHrniDetails2() {
        // Arrange
        ECDCallResponse ecdCallResponse = new ECDCallResponse();
        ecdCallResponse.setAnswer("Answer");
        ecdCallResponse.setBenCallId(1L);
        ecdCallResponse.setCallDetailID(1L);
        ecdCallResponse.setChildId(1L);
        ecdCallResponse.setCongentialAnomalies(new String[]{"Congential Anomalies"});
        ecdCallResponse.setCongentialAnomaliesDB("Congential Anomalies DB");
        ecdCallResponse.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        ecdCallResponse.setCreatedDate(mock(Timestamp.class));
        ecdCallResponse.setDeleted(true);
        ecdCallResponse.setEcdCallResponseId(1L);
        ecdCallResponse.setEcdCallType("Ecd Call Type");
        ecdCallResponse.setLastModDate(mock(Timestamp.class));
        ecdCallResponse.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        ecdCallResponse.setMotherId(1L);
        ecdCallResponse.setObCallId(1L);
        ecdCallResponse.setOtherCongentialAnomalies("Other Congential Anomalies");
        ecdCallResponse.setOtherHrni("Other Hrni");
        ecdCallResponse.setOtherHrpReason("Just cause");
        ecdCallResponse.setProbableCauseOfDefect("Probable Cause Of Defect");
        ecdCallResponse.setPsmId(1);
        ecdCallResponse.setQuestion("Question");
        ecdCallResponse.setQuestionId(1);
        ecdCallResponse.setReasonsForHrni(new String[]{"Just cause"});
        ecdCallResponse.setReasonsForHrniDB("Just cause");
        ecdCallResponse.setReasonsForHrp(new String[]{"Just cause"});
        ecdCallResponse.setReasonsForHrpDB("Just cause");
        ecdCallResponse.setRemarks("Remarks");
        ecdCallResponse.setSectionId(1);
        
        ecdCallResponse.toString();

        ArrayList<ECDCallResponse> content = new ArrayList<>();
        content.add(ecdCallResponse);
        PageImpl<ECDCallResponse> pageImpl = new PageImpl<>(content);
        when(eCDCallResponseRepo.getHrniDetailsChild(Mockito.<Pageable>any(), Mockito.<Long>any())).thenReturn(pageImpl);

        // Act
        ECDCallResponse actualHrpHrniDetails = beneficiaryCallHistoryImpl.getHrpHrniDetails(1L, 1L);

        // Assert
        verify(eCDCallResponseRepo).getHrniDetailsChild(isA(Pageable.class), isA(Long.class));
        assertSame(ecdCallResponse, actualHrpHrniDetails);
    }

    /**
     * Method under test:
     * {@link BeneficiaryCallHistoryImpl#getHrpHrniDetails(Long, Long)}
     */
    @Test
    void testGetHrpHrniDetails3() {
        // Arrange
        when(eCDCallResponseRepo.getHrniDetailsChild(Mockito.<Pageable>any(), Mockito.<Long>any())).thenReturn(null);

        // Act and Assert
        assertThrows(ECDException.class, () -> beneficiaryCallHistoryImpl.getHrpHrniDetails(1L, 1L));
        verify(eCDCallResponseRepo).getHrniDetailsChild(isA(Pageable.class), isA(Long.class));
    }

    /**
     * Method under test:
     * {@link BeneficiaryCallHistoryImpl#getHrpHrniDetails(Long, Long)}
     */
    @Test
    void testGetHrpHrniDetails4() {
        // Arrange
        when(eCDCallResponseRepo.getHrpDetailsMother(Mockito.<Pageable>any(), Mockito.<Long>any()))
                .thenReturn(new PageImpl<>(new ArrayList<>()));

        // Act
        beneficiaryCallHistoryImpl.getHrpHrniDetails(1L, null);

        // Assert
        verify(eCDCallResponseRepo).getHrpDetailsMother(isA(Pageable.class), isA(Long.class));
    }

    /**
     * Method under test:
     * {@link BeneficiaryCallHistoryImpl#getHrpHrniDetails(Long, Long)}
     */
    @Test
    void testGetHrpHrniDetails5() {
        // Arrange
        ECDCallResponse ecdCallResponse = new ECDCallResponse();
        ecdCallResponse.setAnswer("Answer");
        ecdCallResponse.setBenCallId(1L);
        ecdCallResponse.setCallDetailID(1L);
        ecdCallResponse.setChildId(1L);
        ecdCallResponse.setCongentialAnomalies(new String[]{"Congential Anomalies"});
        ecdCallResponse.setCongentialAnomaliesDB("Congential Anomalies DB");
        ecdCallResponse.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        ecdCallResponse.setCreatedDate(mock(Timestamp.class));
        ecdCallResponse.setDeleted(true);
        ecdCallResponse.setEcdCallResponseId(1L);
        ecdCallResponse.setEcdCallType("Ecd Call Type");
        ecdCallResponse.setLastModDate(mock(Timestamp.class));
        ecdCallResponse.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        ecdCallResponse.setMotherId(1L);
        ecdCallResponse.setObCallId(1L);
        ecdCallResponse.setOtherCongentialAnomalies("Other Congential Anomalies");
        ecdCallResponse.setOtherHrni("Other Hrni");
        ecdCallResponse.setOtherHrpReason("Just cause");
        ecdCallResponse.setProbableCauseOfDefect("Probable Cause Of Defect");
        ecdCallResponse.setPsmId(1);
        ecdCallResponse.setQuestion("Question");
        ecdCallResponse.setQuestionId(1);
        ecdCallResponse.setReasonsForHrni(new String[]{"Just cause"});
        ecdCallResponse.setReasonsForHrniDB("Just cause");
        ecdCallResponse.setReasonsForHrp(new String[]{"Just cause"});
        ecdCallResponse.setReasonsForHrpDB("Just cause");
        ecdCallResponse.setRemarks("Remarks");
        ecdCallResponse.setSectionId(1);

        ArrayList<ECDCallResponse> content = new ArrayList<>();
        content.add(ecdCallResponse);
        PageImpl<ECDCallResponse> pageImpl = new PageImpl<>(content);
        when(eCDCallResponseRepo.getHrpDetailsMother(Mockito.<Pageable>any(), Mockito.<Long>any())).thenReturn(pageImpl);

        // Act
        ECDCallResponse actualHrpHrniDetails = beneficiaryCallHistoryImpl.getHrpHrniDetails(1L, null);

        // Assert
        verify(eCDCallResponseRepo).getHrpDetailsMother(isA(Pageable.class), isA(Long.class));
        assertSame(ecdCallResponse, actualHrpHrniDetails);
    }

    /**
     * Method under test:
     * {@link BeneficiaryCallHistoryImpl#getHrpHrniDetails(Long, Long)}
     */
    @Test
    void testGetHrpHrniDetails6() {
        // Arrange
        when(eCDCallResponseRepo.getHrpDetailsMother(Mockito.<Pageable>any(), Mockito.<Long>any())).thenReturn(null);

        // Act and Assert
        assertThrows(ECDException.class, () -> beneficiaryCallHistoryImpl.getHrpHrniDetails(1L, null));
        verify(eCDCallResponseRepo).getHrpDetailsMother(isA(Pageable.class), isA(Long.class));
    }

    /**
     * Method under test:
     * {@link BeneficiaryCallHistoryImpl#getHrpHrniDetails(Long, Long)}
     */
    @Test
    void testGetHrpHrniDetails7() {
        // Arrange, Act and Assert
        assertThrows(ECDException.class, () -> beneficiaryCallHistoryImpl.getHrpHrniDetails(null, null));
    }

    /**
     * Method under test:
     * {@link BeneficiaryCallHistoryImpl#getHrpHrniDetails(Long, Long)}
     */
    @Test
    void testGetHrpHrniDetails8() {
        // Arrange
        when(eCDCallResponseRepo.getHrpDetailsMother(Mockito.<Pageable>any(), Mockito.<Long>any()))
                .thenThrow(new ECDException("Exception Msg"));

        // Act and Assert
        assertThrows(ECDException.class, () -> beneficiaryCallHistoryImpl.getHrpHrniDetails(1L, null));
        verify(eCDCallResponseRepo).getHrpDetailsMother(isA(Pageable.class), isA(Long.class));
    }
}
