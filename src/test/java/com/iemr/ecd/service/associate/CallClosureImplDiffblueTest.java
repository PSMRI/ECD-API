package com.iemr.ecd.service.associate;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.iemr.ecd.dao.associate.Bencall;
import com.iemr.ecd.dao.associate.OutboundCalls;
import com.iemr.ecd.dto.associate.CallClosureDTO;
import com.iemr.ecd.repo.call_conf_allocation.CallConfigurationRepo;
import com.iemr.ecd.repo.call_conf_allocation.ChildRecordRepo;
import com.iemr.ecd.repo.call_conf_allocation.MotherRecordRepo;
import com.iemr.ecd.repo.call_conf_allocation.OutboundCallsRepo;
import com.iemr.ecd.repository.ecd.BencallRepo;
import com.iemr.ecd.utils.advice.exception_handler.ECDException;

import java.sql.Timestamp;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {CallClosureImpl.class, CallClosureDTO.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class CallClosureImplDiffblueTest {
    @MockBean
    private BencallRepo bencallRepo;

    @Autowired
    private CallClosureDTO callClosureDTO;

    @Autowired
    private CallClosureImpl callClosureImpl;

    @MockBean
    private CallConfigurationRepo callConfigurationRepo;

    @MockBean
    private ChildRecordRepo childRecordRepo;

    @MockBean
    private MotherRecordRepo motherRecordRepo;

    @MockBean
    private OutboundCallsRepo outboundCallsRepo;

    /**
     * Method under test: {@link CallClosureImpl#closeCall(CallClosureDTO)}
     */
    @Test
    void testCloseCall() {
        // Arrange
        Timestamp callTime = mock(Timestamp.class);
        when(callTime.getTime()).thenReturn(10L);

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
        bencall.setCallTime(callTime);
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
        when(bencallRepo.save(Mockito.<Bencall>any())).thenReturn(bencall2);
        when(bencallRepo.findByBenCallId(Mockito.<Long>any())).thenReturn(bencall);
        when(callConfigurationRepo.getCallConfiguration(Mockito.<Integer>any())).thenThrow(new ECDException(" hours "));

        OutboundCalls outboundCalls = new OutboundCalls();
        outboundCalls.setAllocatedUserId(1);
        outboundCalls.setAllocationStatus("Allocation Status");
        outboundCalls.setBeneficiaryRegId(1L);
        outboundCalls.setCallAttemptNo(1);
        outboundCalls.setCallDateFrom(mock(Timestamp.class));
        outboundCalls.setCallDateTo(mock(Timestamp.class));
        outboundCalls.setCallStatus("Call Status");
        outboundCalls.setChildId(1L);
        outboundCalls.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        outboundCalls.setCreatedDate(mock(Timestamp.class));
        outboundCalls.setDeleted(true);
        outboundCalls.setDisplayEcdCallType("Display Ecd Call Type");
        outboundCalls.setEcdCallType("Ecd Call Type");
        outboundCalls.setHighRiskReason("Just cause");
        outboundCalls.setHrniReason("Just cause");
        outboundCalls.setIsHighRisk(true);
        outboundCalls.setIsHrni(true);
        outboundCalls.setIsSMSSent(true);
        outboundCalls.setLastModDate(mock(Timestamp.class));
        outboundCalls.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        outboundCalls.setMotherId(1L);
        outboundCalls.setNextCallDate(mock(Timestamp.class));
        outboundCalls.setObCallId(1L);
        outboundCalls.setPhoneNumberType("6625550144");
        outboundCalls.setPsmId(1);
        
        outboundCalls.toString();
        
        when(outboundCallsRepo.findByObCallId(Mockito.<Long>any())).thenReturn(outboundCalls);

        // Act and Assert
        assertThrows(ECDException.class, () -> callClosureImpl.closeCall(callClosureDTO));
        verify(callConfigurationRepo).getCallConfiguration(isNull());
        verify(outboundCallsRepo).findByObCallId(isNull());
        verify(bencallRepo).findByBenCallId(isNull());
        verify(callTime).getTime();
        verify(bencallRepo).save(isA(Bencall.class));
    }
}
