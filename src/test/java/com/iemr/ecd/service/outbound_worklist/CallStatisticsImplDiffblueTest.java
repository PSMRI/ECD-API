package com.iemr.ecd.service.outbound_worklist;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.iemr.ecd.dao.associate.Bencall;
import com.iemr.ecd.dto.associate.CallStatisticsDTO;
import com.iemr.ecd.repo.call_conf_allocation.CallStatisticsRepo;
import com.iemr.ecd.utils.advice.exception_handler.ECDException;

import java.sql.Timestamp;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {CallStatisticsImpl.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class CallStatisticsImplDiffblueTest {
    @Autowired
    private CallStatisticsImpl callStatisticsImpl;

    @MockBean
    private CallStatisticsRepo callStatisticsRepo;

    /**
     * Method under test:
     * {@link CallStatisticsImpl#getCallStatisticsByAgentId(String)}
     */
    @Test
    void testGetCallStatisticsByAgentId() {
        // Arrange
        when(callStatisticsRepo.getCallCurrentDateStatistics(Mockito.<String>any())).thenReturn(new ArrayList<>());

        // Act
        callStatisticsImpl.getCallStatisticsByAgentId("42");

        // Assert
        verify(callStatisticsRepo).getCallCurrentDateStatistics(eq("42"));
    }

    /**
     * Method under test:
     * {@link CallStatisticsImpl#getCallStatisticsByAgentId(String)}
     */
    @Test
    void testGetCallStatisticsByAgentId2() {
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

        ArrayList<Bencall> bencallList = new ArrayList<>();
        bencallList.add(bencall);
        when(callStatisticsRepo.getCallCurrentDateStatistics(Mockito.<String>any())).thenReturn(bencallList);

        // Act
        CallStatisticsDTO actualCallStatisticsByAgentId = callStatisticsImpl.getCallStatisticsByAgentId("42");

        // Assert
        verify(callStatisticsRepo).getCallCurrentDateStatistics(eq("42"));
        assertEquals(1, actualCallStatisticsByAgentId.getTotalCallsAnswered().intValue());
        assertEquals(1, actualCallStatisticsByAgentId.getTotalCallsVerified().intValue());
    }

    /**
     * Method under test:
     * {@link CallStatisticsImpl#getCallStatisticsByAgentId(String)}
     */
    @Test
    void testGetCallStatisticsByAgentId3() {
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

        Bencall bencall2 = new Bencall();
        bencall2.setAgentId("Agent Id");
        bencall2.setArchieveFilePath("Archieve File Path");
        bencall2.setBenCallId(2L);
        bencall2.setBeneficiaryRegId(2L);
        bencall2.setCallClosureType("com.iemr.ecd.dao.associate.Bencall");
        bencall2.setCallDuration("com.iemr.ecd.dao.associate.Bencall");
        bencall2.setCallEndTime(mock(Timestamp.class));
        bencall2.setCallEndUserId(2);
        bencall2.setCallId("Call Id");
        bencall2.setCallReceivedUserId(2);
        bencall2.setCallRemarks("com.iemr.ecd.dao.associate.Bencall");
        bencall2.setCallTime(mock(Timestamp.class));
        bencall2.setCallTypeId(2);
        bencall2.setCalledServiceId(2);
        bencall2.setCategory("com.iemr.ecd.dao.associate.Bencall");
        bencall2.setCdiCallStatus("com.iemr.ecd.dao.associate.Bencall");
        bencall2.setComplaintRemarks("com.iemr.ecd.dao.associate.Bencall");
        bencall2.setCreatedBy("Created By");
        bencall2.setCreatedDate(mock(Timestamp.class));
        bencall2.setCzCallDuration(0);
        bencall2.setCzCallEndTime(mock(Timestamp.class));
        bencall2.setCzCallStartTime(mock(Timestamp.class));
        bencall2.setDeleted(false);
        bencall2.setDispositionStatusId(2);
        bencall2.setIsCallAnswered(false);
        bencall2.setIsCallAudited(false);
        bencall2.setIsCallDisconnected(false);
        bencall2.setIsCallVerified(false);
        bencall2.setIsCalledEarlier(false);
        bencall2.setIsFurtherCallRequired(false);
        bencall2.setIsMother(false);
        bencall2.setIsOutbound(false);
        bencall2.setIsWrongNumber(false);
        bencall2.setIsZeroCall(false);
        bencall2.setLastModDate(mock(Timestamp.class));
        bencall2.setModifiedBy("Modified By");
        bencall2.setObCallId(2L);
        bencall2.setPhoneNo("8605550118");
        bencall2.setReasonForCallNotAnswered("Reason For Call Not Answered");
        bencall2.setReasonForNoFurtherCalls("Reason For No Further Calls");
        bencall2.setReceivedRoleName("com.iemr.ecd.dao.associate.Bencall");
        bencall2.setRecordingFilePath("Recording File Path");
        bencall2.setServicesProvided("com.iemr.ecd.dao.associate.Bencall");
        bencall2.setSessionId("Session Id");
        bencall2.setSmsAdvice("com.iemr.ecd.dao.associate.Bencall");
        bencall2.setSmsPhone("8605550118");
        bencall2.setSubCategory("com.iemr.ecd.dao.associate.Bencall");
        bencall2.setTypeOfComplaint("com.iemr.ecd.dao.associate.Bencall");

        ArrayList<Bencall> bencallList = new ArrayList<>();
        bencallList.add(bencall2);
        bencallList.add(bencall);
        when(callStatisticsRepo.getCallCurrentDateStatistics(Mockito.<String>any())).thenReturn(bencallList);

        // Act
        CallStatisticsDTO actualCallStatisticsByAgentId = callStatisticsImpl.getCallStatisticsByAgentId("42");

        // Assert
        verify(callStatisticsRepo).getCallCurrentDateStatistics(eq("42"));
        assertEquals(1, actualCallStatisticsByAgentId.getTotalCallsAnswered().intValue());
        assertEquals(1, actualCallStatisticsByAgentId.getTotalCallsVerified().intValue());
    }

    /**
     * Method under test:
     * {@link CallStatisticsImpl#getCallStatisticsByAgentId(String)}
     */
    @Test
    void testGetCallStatisticsByAgentId4() {
        // Arrange
        when(callStatisticsRepo.getCallCurrentDateStatistics(Mockito.<String>any()))
                .thenThrow(new ECDException("Exception Msg"));

        // Act and Assert
        assertThrows(ECDException.class, () -> callStatisticsImpl.getCallStatisticsByAgentId("42"));
        verify(callStatisticsRepo).getCallCurrentDateStatistics(eq("42"));
    }
}
