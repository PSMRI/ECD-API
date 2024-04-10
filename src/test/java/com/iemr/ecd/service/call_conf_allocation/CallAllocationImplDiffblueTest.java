package com.iemr.ecd.service.call_conf_allocation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.iemr.ecd.dao.CallConfiguration;
import com.iemr.ecd.dao.associate.ChildRecord;
import com.iemr.ecd.dao.associate.OutboundCalls;
import com.iemr.ecd.dto.OutboundCallsDTO;
import com.iemr.ecd.dto.RequestCallAllocationDTO;
import com.iemr.ecd.dto.supervisor.ResponseEligibleCallRecordsDTO;
import com.iemr.ecd.repo.call_conf_allocation.CallConfigurationRepo;
import com.iemr.ecd.repo.call_conf_allocation.ChildRecordRepo;
import com.iemr.ecd.repo.call_conf_allocation.MotherRecordRepo;
import com.iemr.ecd.repo.call_conf_allocation.OutboundCallsRepo;
import com.iemr.ecd.utils.advice.exception_handler.ECDException;
import com.iemr.ecd.utils.advice.exception_handler.InvalidRequestException;

import java.sql.Timestamp;
import java.util.ArrayList;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {CallAllocationImpl.class, RequestCallAllocationDTO.class, OutboundCallsDTO.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class CallAllocationImplDiffblueTest {
    @Autowired
    private OutboundCallsDTO outboundCallsDTO;

    @Autowired
    private CallAllocationImpl callAllocationImpl;

    @MockBean
    private CallConfigurationRepo callConfigurationRepo;

    @MockBean
    private ChildRecordRepo childRecordRepo;

    @MockBean
    private MotherRecordRepo motherRecordRepo;

    @MockBean
    private OutboundCallsRepo outboundCallsRepo;

    @Autowired
    private RequestCallAllocationDTO requestCallAllocationDTO;

    /**
     * Method under test:
     * {@link CallAllocationImpl#allocateCalls(RequestCallAllocationDTO)}
     */
    @Test
    void testAllocateCalls() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange, Act and Assert
        assertThrows(ECDException.class, () -> (new CallAllocationImpl()).allocateCalls(null));
    }

    /**
     * Method under test:
     * {@link CallAllocationImpl#allocateCalls(RequestCallAllocationDTO)}
     */
    @Test
    void testAllocateCalls2() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        CallAllocationImpl callAllocationImpl = new CallAllocationImpl();

        RequestCallAllocationDTO callAllocationDto = new RequestCallAllocationDTO();
        callAllocationDto.setTDate(null);
        callAllocationDto.setToUserIds(null);
        callAllocationDto.setPsmId(null);
        callAllocationDto.setRecordType("mother");
        callAllocationDto.setRoleName("associate");
        callAllocationDto.setFDate(null);
        callAllocationDto.setNoOfCalls(0);
        
        callAllocationDto.toString();
        
        // Act and Assert
        assertThrows(ECDException.class, () -> callAllocationImpl.allocateCalls(callAllocationDto));
    }

    /**
     * Method under test:
     * {@link CallAllocationImpl#allocateCalls(RequestCallAllocationDTO)}
     */
    @Test
    void testAllocateCalls3() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        CallAllocationImpl callAllocationImpl = new CallAllocationImpl();

        RequestCallAllocationDTO callAllocationDto = new RequestCallAllocationDTO();
        callAllocationDto.setTDate(null);
        callAllocationDto.setToUserIds(new Integer[]{1});
        callAllocationDto.setPsmId(null);
        callAllocationDto.setRecordType("mother");
        callAllocationDto.setRoleName("associate");
        callAllocationDto.setFDate(null);
        callAllocationDto.setNoOfCalls(0);

        // Act and Assert
        assertThrows(ECDException.class, () -> callAllocationImpl.allocateCalls(callAllocationDto));
    }

    /**
     * Method under test:
     * {@link CallAllocationImpl#allocateCalls(RequestCallAllocationDTO)}
     */
    @Test
    void testAllocateCalls4() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        CallAllocationImpl callAllocationImpl = new CallAllocationImpl();

        RequestCallAllocationDTO callAllocationDto = new RequestCallAllocationDTO();
        callAllocationDto.setTDate(null);
        callAllocationDto.setToUserIds(new Integer[]{1});
        callAllocationDto.setPsmId(null);
        callAllocationDto.setRecordType("mother");
        callAllocationDto.setRoleName("associate");
        callAllocationDto.setFDate(null);
        callAllocationDto.setNoOfCalls(1);

        // Act and Assert
        assertThrows(ECDException.class, () -> callAllocationImpl.allocateCalls(callAllocationDto));
    }

    /**
     * Method under test:
     * {@link CallAllocationImpl#allocateCalls(RequestCallAllocationDTO)}
     */
    @Test
    void testAllocateCalls5() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        CallAllocationImpl callAllocationImpl = new CallAllocationImpl();

        RequestCallAllocationDTO callAllocationDto = new RequestCallAllocationDTO();
        callAllocationDto.setTDate(null);
        callAllocationDto.setToUserIds(new Integer[]{1});
        callAllocationDto.setPsmId(null);
        callAllocationDto.setRecordType("mother");
        callAllocationDto.setRoleName("associate");
        callAllocationDto.setFDate("foo");
        callAllocationDto.setNoOfCalls(1);

        // Act and Assert
        assertThrows(ECDException.class, () -> callAllocationImpl.allocateCalls(callAllocationDto));
    }

    /**
     * Method under test:
     * {@link CallAllocationImpl#allocateCalls(RequestCallAllocationDTO)}
     */
    @Test
    void testAllocateCalls6() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        CallAllocationImpl callAllocationImpl = new CallAllocationImpl();

        RequestCallAllocationDTO callAllocationDto = new RequestCallAllocationDTO();
        callAllocationDto.setTDate("foo");
        callAllocationDto.setToUserIds(new Integer[]{1});
        callAllocationDto.setPsmId(null);
        callAllocationDto.setRecordType("mother");
        callAllocationDto.setRoleName("associate");
        callAllocationDto.setFDate("foo");
        callAllocationDto.setNoOfCalls(1);

        // Act and Assert
        assertThrows(ECDException.class, () -> callAllocationImpl.allocateCalls(callAllocationDto));
    }

    /**
     * Method under test:
     * {@link CallAllocationImpl#allocateCalls(RequestCallAllocationDTO)}
     */
    @Test
    void testAllocateCalls7() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        CallAllocationImpl callAllocationImpl = new CallAllocationImpl();

        RequestCallAllocationDTO callAllocationDto = new RequestCallAllocationDTO();
        callAllocationDto.setTDate(null);
        callAllocationDto.setToUserIds(null);
        callAllocationDto.setPsmId(null);
        callAllocationDto.setRecordType("child");
        callAllocationDto.setRoleName("associate");
        callAllocationDto.setFDate(null);
        callAllocationDto.setNoOfCalls(0);

        // Act and Assert
        assertThrows(ECDException.class, () -> callAllocationImpl.allocateCalls(callAllocationDto));
    }

    /**
     * Method under test:
     * {@link CallAllocationImpl#allocateCalls(RequestCallAllocationDTO)}
     */
    @Test
    void testAllocateCalls8() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        CallAllocationImpl callAllocationImpl = new CallAllocationImpl();

        // Act and Assert
        assertThrows(ECDException.class,
                () -> callAllocationImpl.allocateCalls(new RequestCallAllocationDTO(new Integer[]{1}, new Integer[]{1}, 1, 1, 1,
                        "associate", "2020-03-01", "2020-03-01", mock(Timestamp.class), mock(Timestamp.class), "associate",
                        "6625550144", 1, 1, "Jan 1, 2020 8:00am GMT+0100", true)));
    }

    /**
     * Method under test:
     * {@link CallAllocationImpl#allocateCalls(RequestCallAllocationDTO)}
     */
    @Test
    void testAllocateCalls9() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        CallAllocationImpl callAllocationImpl = new CallAllocationImpl();
        RequestCallAllocationDTO callAllocationDto = mock(RequestCallAllocationDTO.class);
        when(callAllocationDto.getRoleName()).thenReturn("Role Name");

        // Act and Assert
        assertThrows(ECDException.class, () -> callAllocationImpl.allocateCalls(callAllocationDto));
        verify(callAllocationDto, atLeast(1)).getRoleName();
    }

    /**
     * Method under test:
     * {@link CallAllocationImpl#allocateCalls(RequestCallAllocationDTO)}
     */
    @Test
    void testAllocateCalls10() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        CallAllocationImpl callAllocationImpl = new CallAllocationImpl();
        RequestCallAllocationDTO callAllocationDto = mock(RequestCallAllocationDTO.class);
        when(callAllocationDto.getRecordType()).thenReturn("Record Type");
        when(callAllocationDto.getRoleName()).thenReturn("associate");

        // Act and Assert
        assertThrows(ECDException.class, () -> callAllocationImpl.allocateCalls(callAllocationDto));
        verify(callAllocationDto, atLeast(1)).getRecordType();
        verify(callAllocationDto, atLeast(1)).getRoleName();
    }

    /**
     * Method under test:
     * {@link CallAllocationImpl#allocateCalls(RequestCallAllocationDTO)}
     */
    @Test
    void testAllocateCalls11() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        CallAllocationImpl callAllocationImpl = new CallAllocationImpl();
        RequestCallAllocationDTO callAllocationDto = mock(RequestCallAllocationDTO.class);
        when(callAllocationDto.getPhoneNoType()).thenThrow(new ECDException("associate"));
        when(callAllocationDto.getTDate()).thenReturn("2020-03-01");
        when(callAllocationDto.getFDate()).thenReturn("2020-03-01");
        when(callAllocationDto.getNoOfCalls()).thenReturn(1);
        when(callAllocationDto.getToUserIds()).thenReturn(new Integer[]{1});
        when(callAllocationDto.getRecordType()).thenReturn("mother");
        when(callAllocationDto.getRoleName()).thenReturn("associate");

        // Act and Assert
        assertThrows(ECDException.class, () -> callAllocationImpl.allocateCalls(callAllocationDto));
        verify(callAllocationDto, atLeast(1)).getFDate();
        verify(callAllocationDto, atLeast(1)).getNoOfCalls();
        verify(callAllocationDto).getPhoneNoType();
        verify(callAllocationDto).getRecordType();
        verify(callAllocationDto).getRoleName();
        verify(callAllocationDto, atLeast(1)).getTDate();
        verify(callAllocationDto, atLeast(1)).getToUserIds();
    }

    /**
     * Method under test:
     * {@link CallAllocationImpl#allocateCalls(RequestCallAllocationDTO)}
     */
    @Test
    void testAllocateCalls12() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        CallAllocationImpl callAllocationImpl = new CallAllocationImpl();
        RequestCallAllocationDTO callAllocationDto = mock(RequestCallAllocationDTO.class);
        when(callAllocationDto.getToUserIds()).thenReturn(new Integer[]{});
        when(callAllocationDto.getRecordType()).thenReturn("mother");
        when(callAllocationDto.getRoleName()).thenReturn("associate");

        // Act and Assert
        assertThrows(ECDException.class, () -> callAllocationImpl.allocateCalls(callAllocationDto));
        verify(callAllocationDto).getRecordType();
        verify(callAllocationDto).getRoleName();
        verify(callAllocationDto, atLeast(1)).getToUserIds();
    }

    /**
     * Method under test:
     * {@link CallAllocationImpl#allocateCalls(RequestCallAllocationDTO)}
     */
    @Test
    void testAllocateCalls13() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        CallAllocationImpl callAllocationImpl = new CallAllocationImpl();
        RequestCallAllocationDTO callAllocationDto = mock(RequestCallAllocationDTO.class);
        when(callAllocationDto.getTDate()).thenReturn("associate");
        when(callAllocationDto.getFDate()).thenReturn("2020-03-01");
        when(callAllocationDto.getNoOfCalls()).thenReturn(1);
        when(callAllocationDto.getToUserIds()).thenReturn(new Integer[]{1});
        when(callAllocationDto.getRecordType()).thenReturn("mother");
        when(callAllocationDto.getRoleName()).thenReturn("associate");

        // Act and Assert
        assertThrows(ECDException.class, () -> callAllocationImpl.allocateCalls(callAllocationDto));
        verify(callAllocationDto, atLeast(1)).getFDate();
        verify(callAllocationDto, atLeast(1)).getNoOfCalls();
        verify(callAllocationDto).getRecordType();
        verify(callAllocationDto).getRoleName();
        verify(callAllocationDto, atLeast(1)).getTDate();
        verify(callAllocationDto, atLeast(1)).getToUserIds();
    }

    /**
     * Method under test:
     * {@link CallAllocationImpl#allocateCalls(RequestCallAllocationDTO)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testAllocateCalls14() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   com.iemr.ecd.utils.advice.exception_handler.ECDException: java.lang.NullPointerException: Cannot invoke "String.equalsIgnoreCase(String)" because the return value of "com.iemr.ecd.dto.RequestCallAllocationDTO.getRoleName()" is null
        //       at com.iemr.ecd.service.call_conf_allocation.CallAllocationImpl.allocateCalls(CallAllocationImpl.java:94)
        //   java.lang.NullPointerException: Cannot invoke "String.equalsIgnoreCase(String)" because the return value of "com.iemr.ecd.dto.RequestCallAllocationDTO.getRoleName()" is null
        //       at com.iemr.ecd.service.call_conf_allocation.CallAllocationImpl.allocateCalls(CallAllocationImpl.java:73)
        //   See https://diff.blue/R013 to resolve this issue.

        // Arrange and Act
        callAllocationImpl.allocateCalls(requestCallAllocationDTO);
    }

    /**
     * Method under test:
     * {@link CallAllocationImpl#getEligibleRecordsInfo(int, String, String, String, String)}
     */
    @Test
    void testGetEligibleRecordsInfo() {
        // Arrange and Act
        ResponseEligibleCallRecordsDTO actualEligibleRecordsInfo = callAllocationImpl.getEligibleRecordsInfo(1,
                "6625550144", "Record Type", "2020-03-01", "2020-03-01");

        // Assert
        assertEquals(0, actualEligibleRecordsInfo.getTotalAllocatedRecord());
        assertEquals(0, actualEligibleRecordsInfo.getTotalHighRiskRecord());
        assertEquals(0, actualEligibleRecordsInfo.getTotalIntroductoryRecord());
        assertEquals(0, actualEligibleRecordsInfo.getTotalLowRiskRecord());
        assertEquals(0, actualEligibleRecordsInfo.getTotalRecord());
    }

    /**
     * Method under test:
     * {@link CallAllocationImpl#getEligibleRecordsInfo(int, String, String, String, String)}
     */
    @Test
    void testGetEligibleRecordsInfo2() {
        // Arrange
        when(motherRecordRepo.getRecordCount(Mockito.<Boolean>any(), Mockito.<Timestamp>any(), Mockito.<Timestamp>any(),
                Mockito.<String>any())).thenReturn(3);
        when(outboundCallsRepo.getMotherUnAllocatedCountHR(Mockito.<String>any(), Mockito.<Integer>any(),
                Mockito.<Timestamp>any(), Mockito.<Timestamp>any(), Mockito.<String>any())).thenReturn(3);
        when(outboundCallsRepo.getMotherUnAllocatedCountLR(Mockito.<String>any(), Mockito.<Integer>any(),
                Mockito.<Timestamp>any(), Mockito.<Timestamp>any(), Mockito.<String>any())).thenReturn(3);
        when(outboundCallsRepo.getTotalAllocatedCountMother(Mockito.<String>any(), Mockito.<Integer>any(),
                Mockito.<Timestamp>any(), Mockito.<Timestamp>any(), Mockito.<String>any())).thenReturn(3);

        // Act
        ResponseEligibleCallRecordsDTO actualEligibleRecordsInfo = callAllocationImpl.getEligibleRecordsInfo(1,
                "6625550144", "Mother", "2020-03-01", "2020-03-01");

        // Assert
        verify(motherRecordRepo).getRecordCount(isA(Boolean.class), isA(Timestamp.class), isA(Timestamp.class),
                eq("6625550144"));
        verify(outboundCallsRepo).getMotherUnAllocatedCountHR(eq("unallocated"), isA(Integer.class), isA(Timestamp.class),
                isA(Timestamp.class), eq("6625550144"));
        verify(outboundCallsRepo).getMotherUnAllocatedCountLR(eq("unallocated"), isA(Integer.class), isA(Timestamp.class),
                isA(Timestamp.class), eq("6625550144"));
        verify(outboundCallsRepo).getTotalAllocatedCountMother(eq("allocated"), isA(Integer.class), isA(Timestamp.class),
                isA(Timestamp.class), eq("6625550144"));
        assertEquals(3, actualEligibleRecordsInfo.getTotalAllocatedRecord());
        assertEquals(3, actualEligibleRecordsInfo.getTotalHighRiskRecord());
        assertEquals(3, actualEligibleRecordsInfo.getTotalIntroductoryRecord());
        assertEquals(3, actualEligibleRecordsInfo.getTotalLowRiskRecord());
        assertEquals(9, actualEligibleRecordsInfo.getTotalRecord());
    }

    /**
     * Method under test:
     * {@link CallAllocationImpl#getEligibleRecordsInfo(int, String, String, String, String)}
     */
    @Test
    void testGetEligibleRecordsInfo3() {
        // Arrange
        when(motherRecordRepo.getRecordCount(Mockito.<Boolean>any(), Mockito.<Timestamp>any(), Mockito.<Timestamp>any(),
                Mockito.<String>any())).thenReturn(3);
        when(outboundCallsRepo.getMotherUnAllocatedCountLR(Mockito.<String>any(), Mockito.<Integer>any(),
                Mockito.<Timestamp>any(), Mockito.<Timestamp>any(), Mockito.<String>any()))
                .thenThrow(new InvalidRequestException("An error occurred"));

        // Act and Assert
        assertThrows(ECDException.class,
                () -> callAllocationImpl.getEligibleRecordsInfo(1, "6625550144", "Mother", "2020-03-01", "2020-03-01"));
        verify(motherRecordRepo).getRecordCount(isA(Boolean.class), isA(Timestamp.class), isA(Timestamp.class),
                eq("6625550144"));
        verify(outboundCallsRepo).getMotherUnAllocatedCountLR(eq("unallocated"), isA(Integer.class), isA(Timestamp.class),
                isA(Timestamp.class), eq("6625550144"));
    }

    /**
     * Method under test:
     * {@link CallAllocationImpl#getEligibleRecordsInfo(int, String, String, String, String)}
     */
    @Test
    void testGetEligibleRecordsInfo4() {
        // Arrange and Act
        ResponseEligibleCallRecordsDTO actualEligibleRecordsInfo = callAllocationImpl.getEligibleRecordsInfo(1,
                "6625550144", null, "2020-03-01", "2020-03-01");

        // Assert
        assertEquals(0, actualEligibleRecordsInfo.getTotalAllocatedRecord());
        assertEquals(0, actualEligibleRecordsInfo.getTotalHighRiskRecord());
        assertEquals(0, actualEligibleRecordsInfo.getTotalIntroductoryRecord());
        assertEquals(0, actualEligibleRecordsInfo.getTotalLowRiskRecord());
        assertEquals(0, actualEligibleRecordsInfo.getTotalRecord());
    }

    /**
     * Method under test:
     * {@link CallAllocationImpl#getEligibleRecordsInfo(int, String, String, String, String)}
     */
    @Test
    void testGetEligibleRecordsInfo5() {
        // Arrange
        when(childRecordRepo.getRecordCount(Mockito.<Boolean>any(), Mockito.<Timestamp>any(), Mockito.<Timestamp>any(),
                Mockito.<String>any())).thenReturn(3);
        when(outboundCallsRepo.getChildUnAllocatedCountHR(Mockito.<String>any(), Mockito.<Integer>any(),
                Mockito.<Timestamp>any(), Mockito.<Timestamp>any(), Mockito.<String>any())).thenReturn(3);
        when(outboundCallsRepo.getChildUnAllocatedCountLR(Mockito.<String>any(), Mockito.<Integer>any(),
                Mockito.<Timestamp>any(), Mockito.<Timestamp>any(), Mockito.<String>any())).thenReturn(3);
        when(outboundCallsRepo.getTotalAllocatedCountChild(Mockito.<String>any(), Mockito.<Integer>any(),
                Mockito.<Timestamp>any(), Mockito.<Timestamp>any(), Mockito.<String>any())).thenReturn(3);

        // Act
        ResponseEligibleCallRecordsDTO actualEligibleRecordsInfo = callAllocationImpl.getEligibleRecordsInfo(1,
                "6625550144", "Child", "2020-03-01", "2020-03-01");

        // Assert
        verify(childRecordRepo).getRecordCount(isA(Boolean.class), isA(Timestamp.class), isA(Timestamp.class),
                eq("6625550144"));
        verify(outboundCallsRepo).getChildUnAllocatedCountHR(eq("unallocated"), isA(Integer.class), isA(Timestamp.class),
                isA(Timestamp.class), eq("6625550144"));
        verify(outboundCallsRepo).getChildUnAllocatedCountLR(eq("unallocated"), isA(Integer.class), isA(Timestamp.class),
                isA(Timestamp.class), eq("6625550144"));
        verify(outboundCallsRepo).getTotalAllocatedCountChild(eq("allocated"), isA(Integer.class), isA(Timestamp.class),
                isA(Timestamp.class), eq("6625550144"));
        assertEquals(3, actualEligibleRecordsInfo.getTotalAllocatedRecord());
        assertEquals(3, actualEligibleRecordsInfo.getTotalHighRiskRecord());
        assertEquals(3, actualEligibleRecordsInfo.getTotalIntroductoryRecord());
        assertEquals(3, actualEligibleRecordsInfo.getTotalLowRiskRecord());
        assertEquals(9, actualEligibleRecordsInfo.getTotalRecord());
    }

    /**
     * Method under test:
     * {@link CallAllocationImpl#getEligibleRecordsInfo(int, String, String, String, String)}
     */
    @Test
    void testGetEligibleRecordsInfo6() {
        // Arrange
        when(childRecordRepo.getRecordCount(Mockito.<Boolean>any(), Mockito.<Timestamp>any(), Mockito.<Timestamp>any(),
                Mockito.<String>any())).thenReturn(3);
        when(outboundCallsRepo.getChildUnAllocatedCountLR(Mockito.<String>any(), Mockito.<Integer>any(),
                Mockito.<Timestamp>any(), Mockito.<Timestamp>any(), Mockito.<String>any()))
                .thenThrow(new InvalidRequestException("An error occurred"));

        // Act and Assert
        assertThrows(ECDException.class,
                () -> callAllocationImpl.getEligibleRecordsInfo(1, "6625550144", "Child", "2020-03-01", "2020-03-01"));
        verify(childRecordRepo).getRecordCount(isA(Boolean.class), isA(Timestamp.class), isA(Timestamp.class),
                eq("6625550144"));
        verify(outboundCallsRepo).getChildUnAllocatedCountLR(eq("unallocated"), isA(Integer.class), isA(Timestamp.class),
                isA(Timestamp.class), eq("6625550144"));
    }

    /**
     * Method under test:
     * {@link CallAllocationImpl#getEligibleRecordsInfo(int, String, String, String, String)}
     */
    @Test
    void testGetEligibleRecordsInfo7() {
        // Arrange, Act and Assert
        assertThrows(ECDException.class,
                () -> callAllocationImpl.getEligibleRecordsInfo(1, "6625550144", "Child", "2020/03/01", "2020-03-01"));
    }

    /**
     * Method under test:
     * {@link CallAllocationImpl#getEligibleRecordsInfo(int, String, String, String, String)}
     */
    @Test
    void testGetEligibleRecordsInfo8() {
        // Arrange, Act and Assert
        assertThrows(ECDException.class,
                () -> callAllocationImpl.getEligibleRecordsInfo(1, "6625550144", "Child", null, "2020-03-01"));
    }

    /**
     * Method under test:
     * {@link CallAllocationImpl#getEligibleRecordsInfo(int, String, String, String, String)}
     */
    @Test
    void testGetEligibleRecordsInfo9() {
        // Arrange, Act and Assert
        assertThrows(ECDException.class,
                () -> callAllocationImpl.getEligibleRecordsInfo(1, "6625550144", "Child", "2020-03-01", "2020/03/01"));
    }

    /**
     * Method under test:
     * {@link CallAllocationImpl#getEligibleRecordsInfo(int, String, String, String, String)}
     */
    @Test
    void testGetEligibleRecordsInfo10() {
        // Arrange, Act and Assert
        assertThrows(ECDException.class,
                () -> callAllocationImpl.getEligibleRecordsInfo(1, "6625550144", "Child", "2020-03-01", null));
    }

    /**
     * Method under test:
     * {@link CallAllocationImpl#moveAllocatedCallsToBin(RequestCallAllocationDTO)}
     */
    @Test
    void testMoveAllocatedCallsToBin() {
        // Arrange, Act and Assert
        assertThrows(ECDException.class, () -> callAllocationImpl.moveAllocatedCallsToBin(requestCallAllocationDTO));
    }

    /**
     * Method under test:
     * {@link CallAllocationImpl#getAllocatedCallCountUser(RequestCallAllocationDTO)}
     */
    @Test
    void testGetAllocatedCallCountUser() {
        // Arrange, Act and Assert
        assertThrows(ECDException.class, () -> callAllocationImpl.getAllocatedCallCountUser(requestCallAllocationDTO));
    }

    /**
     * Method under test:
     * {@link CallAllocationImpl#reAllocateCalls(RequestCallAllocationDTO)}
     */
    @Test
    void testReAllocateCalls() {
        // Arrange, Act and Assert
        assertThrows(ECDException.class, () -> callAllocationImpl.reAllocateCalls(requestCallAllocationDTO));
    }

    /**
     * Method under test:
     * {@link CallAllocationImpl#insertRecordsInOutboundCalls(OutboundCallsDTO)}
     */
    @Test
    void testInsertRecordsInOutboundCalls() {
        // Arrange
        when(callConfigurationRepo.getCallConfiguration(Mockito.<Integer>any())).thenReturn(new ArrayList<>());
        when(outboundCallsRepo.getIntroductoryRecordsUser(Mockito.<Integer>any(), Mockito.<String>any(),
                Mockito.<String>any())).thenReturn(new ArrayList<>());

        // Act
        String actualInsertRecordsInOutboundCallsResult = callAllocationImpl.insertRecordsInOutboundCalls(outboundCallsDTO);

        // Assert
        verify(callConfigurationRepo).getCallConfiguration(isNull());
        verify(outboundCallsRepo).getIntroductoryRecordsUser(isNull(), eq("completed"), eq("introductory"));
        assertEquals("{\"response\":\"records successfully inserted\"}", actualInsertRecordsInOutboundCallsResult);
    }

    /**
     * Method under test:
     * {@link CallAllocationImpl#insertRecordsInOutboundCalls(OutboundCallsDTO)}
     */
    @Test
    void testInsertRecordsInOutboundCalls2() {
        // Arrange
        when(callConfigurationRepo.getCallConfiguration(Mockito.<Integer>any())).thenReturn(new ArrayList<>());
        when(outboundCallsRepo.getIntroductoryRecordsUser(Mockito.<Integer>any(), Mockito.<String>any(),
                Mockito.<String>any())).thenThrow(new ECDException("completed"));

        // Act and Assert
        assertThrows(ECDException.class, () -> callAllocationImpl.insertRecordsInOutboundCalls(outboundCallsDTO));
        verify(callConfigurationRepo).getCallConfiguration(isNull());
        verify(outboundCallsRepo).getIntroductoryRecordsUser(isNull(), eq("completed"), eq("introductory"));
    }

    /**
     * Method under test:
     * {@link CallAllocationImpl#insertRecordsInOutboundCalls(OutboundCallsDTO)}
     */
    @Test
    void testInsertRecordsInOutboundCalls3() {
        // Arrange
        CallConfiguration callConfiguration = new CallConfiguration();
        callConfiguration.setBaseLine("completed");
        callConfiguration.setCallConfigId(1L);
        callConfiguration.setCallType("completed");
        callConfiguration.setConfigId(1L);
        callConfiguration.setConfigTerms("completed");
        callConfiguration.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        callConfiguration.setCreatedDate(mock(Timestamp.class));
        callConfiguration.setDeleted(true);
        callConfiguration.setDisplayName("completed");
        callConfiguration.setEffectiveEndDate(mock(Timestamp.class));
        callConfiguration.setEffectiveStartDate(mock(Timestamp.class));
        callConfiguration.setLastModDate(mock(Timestamp.class));
        callConfiguration.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        callConfiguration.setNextAttemptPeriod(1);
        callConfiguration.setNoOfAttempts(1);
        callConfiguration.setPsmId(1);
        callConfiguration.setTermRange(1);
        
        callConfiguration.toString();

        ArrayList<CallConfiguration> callConfigurationList = new ArrayList<>();
        callConfigurationList.add(callConfiguration);
        when(callConfigurationRepo.getCallConfiguration(Mockito.<Integer>any())).thenReturn(callConfigurationList);
        when(outboundCallsRepo.saveAll(Mockito.<Iterable<OutboundCalls>>any())).thenReturn(new ArrayList<>());
        when(outboundCallsRepo.getIntroductoryRecordsUser(Mockito.<Integer>any(), Mockito.<String>any(),
                Mockito.<String>any())).thenReturn(new ArrayList<>());

        // Act
        String actualInsertRecordsInOutboundCallsResult = callAllocationImpl.insertRecordsInOutboundCalls(outboundCallsDTO);

        // Assert
        verify(callConfigurationRepo).getCallConfiguration(isNull());
        verify(outboundCallsRepo).getIntroductoryRecordsUser(isNull(), eq("completed"), eq("introductory"));
        verify(outboundCallsRepo).saveAll(isA(Iterable.class));
        assertEquals("{\"response\":\"records successfully inserted\"}", actualInsertRecordsInOutboundCallsResult);
    }

    /**
     * Method under test:
     * {@link CallAllocationImpl#insertRecordsInOutboundCalls(OutboundCallsDTO)}
     */
    @Test
    void testInsertRecordsInOutboundCalls4() {
        // Arrange
        CallConfiguration callConfiguration = mock(CallConfiguration.class);
        when(callConfiguration.getConfigTerms()).thenThrow(new ECDException("completed"));
        when(callConfiguration.getBaseLine()).thenReturn("Base Line");
        doNothing().when(callConfiguration).setBaseLine(Mockito.<String>any());
        doNothing().when(callConfiguration).setCallConfigId(Mockito.<Long>any());
        doNothing().when(callConfiguration).setCallType(Mockito.<String>any());
        doNothing().when(callConfiguration).setConfigId(Mockito.<Long>any());
        doNothing().when(callConfiguration).setConfigTerms(Mockito.<String>any());
        doNothing().when(callConfiguration).setCreatedBy(Mockito.<String>any());
        doNothing().when(callConfiguration).setCreatedDate(Mockito.<Timestamp>any());
        doNothing().when(callConfiguration).setDeleted(Mockito.<Boolean>any());
        doNothing().when(callConfiguration).setDisplayName(Mockito.<String>any());
        doNothing().when(callConfiguration).setEffectiveEndDate(Mockito.<Timestamp>any());
        doNothing().when(callConfiguration).setEffectiveStartDate(Mockito.<Timestamp>any());
        doNothing().when(callConfiguration).setLastModDate(Mockito.<Timestamp>any());
        doNothing().when(callConfiguration).setModifiedBy(Mockito.<String>any());
        doNothing().when(callConfiguration).setNextAttemptPeriod(Mockito.<Integer>any());
        doNothing().when(callConfiguration).setNoOfAttempts(Mockito.<Integer>any());
        doNothing().when(callConfiguration).setPsmId(Mockito.<Integer>any());
        doNothing().when(callConfiguration).setTermRange(Mockito.<Integer>any());
        callConfiguration.setBaseLine("completed");
        callConfiguration.setCallConfigId(1L);
        callConfiguration.setCallType("completed");
        callConfiguration.setConfigId(1L);
        callConfiguration.setConfigTerms("completed");
        callConfiguration.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        callConfiguration.setCreatedDate(mock(Timestamp.class));
        callConfiguration.setDeleted(true);
        callConfiguration.setDisplayName("completed");
        callConfiguration.setEffectiveEndDate(mock(Timestamp.class));
        callConfiguration.setEffectiveStartDate(mock(Timestamp.class));
        callConfiguration.setLastModDate(mock(Timestamp.class));
        callConfiguration.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        callConfiguration.setNextAttemptPeriod(1);
        callConfiguration.setNoOfAttempts(1);
        callConfiguration.setPsmId(1);
        callConfiguration.setTermRange(1);

        ArrayList<CallConfiguration> callConfigurationList = new ArrayList<>();
        callConfigurationList.add(callConfiguration);
        when(callConfigurationRepo.getCallConfiguration(Mockito.<Integer>any())).thenReturn(callConfigurationList);

        ChildRecord childRecord = new ChildRecord();
        childRecord.setAddress("42 Main St");
        childRecord.setAnmId(1);
        childRecord.setAnmName("Anm Name");
        childRecord.setAnmPh("Anm Ph");
        childRecord.setAshaId(1);
        childRecord.setAshaName("Asha Name");
        childRecord.setAshaPh("Asha Ph");
        childRecord.setBcgDate(mock(Timestamp.class));
        childRecord.setBeneficiaryRegId(1L);
        childRecord.setBlockID(1);
        childRecord.setBlockName("Block Name");
        childRecord.setCaste("Caste");
        childRecord.setChildMctsId(1L);
        childRecord.setChildName("Child Name");
        childRecord.setChildRchIdNo(1L);
        childRecord.setCityID(1L);
        childRecord.setCityName("Oxford");
        childRecord.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        childRecord.setCreatedDate(mock(Timestamp.class));
        childRecord.setDateOfEntry(mock(Timestamp.class));
        childRecord.setDeleted(true);
        childRecord.setDistrictID(1);
        childRecord.setDistrictName("District Name");
        childRecord.setDob(mock(Timestamp.class));
        childRecord.setDpt1Date(mock(Timestamp.class));
        childRecord.setDptBoosterDate(mock(Timestamp.class));
        childRecord.setEcdIdNoChildId(1L);
        childRecord.setEntryType(1);
        childRecord.setEntryTypeStr("Entry Type Str");
        childRecord.setErrorReason("An error occurred");
        childRecord.setFatherName("Father Name");
        childRecord.setFileID(1L);
        childRecord.setGPVillage("GPVillage");
        childRecord.setGender("Gender");
        childRecord.setHepatitisB0Dt(mock(Timestamp.class));
        childRecord.setHrni_Reason("Just cause");
        childRecord.setIsAllocated(true);
        childRecord.setIsError(true);
        childRecord.setIsHrni(true);
        childRecord.setIsUpload(true);
        childRecord.setIsValid(true);
        childRecord.setJeDate(mock(Timestamp.class));
        childRecord.setLastModDate(mock(Timestamp.class));
        childRecord.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        childRecord.setMotherId(1L);
        childRecord.setMotherMctsId(1L);
        childRecord.setMotherName("Mother Name");
        childRecord.setMotherRchIdNo(1L);
        childRecord.setMrDate(mock(Timestamp.class));
        childRecord.setOpv0Date(mock(Timestamp.class));
        childRecord.setOpv2Date(mock(Timestamp.class));
        childRecord.setOpv3Date(mock(Timestamp.class));
        childRecord.setOpvBoosterDate(mock(Timestamp.class));
        childRecord.setPHCID(1);
        childRecord.setPHCName("PHCName");
        childRecord.setPhoneNo("6625550144");
        childRecord.setPlaceOfBirth("Place Of Birth");
        childRecord.setRegistrationDate(mock(Timestamp.class));
        childRecord.setRemarks("Remarks");
        childRecord.setRowId(1L);
        childRecord.setStateID(1);
        childRecord.setStateName("MD");
        childRecord.setStatus("Status");
        childRecord.setSubCenterId(1);
        childRecord.setSubCenterName("Sub Center Name");
        childRecord.setTalukaId(1);
        childRecord.setTalukaName("Taluka Name");
        childRecord.setVillageID(1);
        childRecord.setVillageName("Village Name");
        childRecord.setVitADose1Date(mock(Timestamp.class));
        childRecord.setVitADose2Date(mock(Timestamp.class));
        childRecord.setVitADose3Date(mock(Timestamp.class));
        childRecord.setVitADose9Date(mock(Timestamp.class));
        childRecord.setWeightOfChild(10.0d);
        childRecord.setWhomPhoneNo("6625550144");
        
        childRecord.toString();        
        
        when(childRecordRepo.findByEcdIdNoChildId(Mockito.<Long>any())).thenReturn(childRecord);

        OutboundCalls outboundCalls = new OutboundCalls();
        outboundCalls.setAllocatedUserId(1);
        outboundCalls.setAllocationStatus("completed");
        outboundCalls.setBeneficiaryRegId(1L);
        outboundCalls.setCallAttemptNo(1);
        outboundCalls.setCallDateFrom(mock(Timestamp.class));
        outboundCalls.setCallDateTo(mock(Timestamp.class));
        outboundCalls.setCallStatus("completed");
        outboundCalls.setChildId(1L);
        outboundCalls.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        outboundCalls.setCreatedDate(mock(Timestamp.class));
        outboundCalls.setDeleted(true);
        outboundCalls.setDisplayEcdCallType("completed");
        outboundCalls.setEcdCallType("completed");
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
        
        ArrayList<OutboundCalls> outboundCallsList = new ArrayList<>();
        outboundCallsList.add(outboundCalls);
        when(outboundCallsRepo.getIntroductoryRecordsUser(Mockito.<Integer>any(), Mockito.<String>any(),
                Mockito.<String>any())).thenReturn(outboundCallsList);

        // Act and Assert
        assertThrows(ECDException.class, () -> callAllocationImpl.insertRecordsInOutboundCalls(outboundCallsDTO));
        verify(callConfiguration).getBaseLine();
        verify(callConfiguration).getConfigTerms();
        verify(callConfiguration).setBaseLine(eq("completed"));
        verify(callConfiguration).setCallConfigId(isA(Long.class));
        verify(callConfiguration).setCallType(eq("completed"));
        verify(callConfiguration).setConfigId(isA(Long.class));
        verify(callConfiguration).setConfigTerms(eq("completed"));
        verify(callConfiguration).setCreatedBy(eq("Jan 1, 2020 8:00am GMT+0100"));
        verify(callConfiguration).setCreatedDate(isA(Timestamp.class));
        verify(callConfiguration).setDeleted(isA(Boolean.class));
        verify(callConfiguration).setDisplayName(eq("completed"));
        verify(callConfiguration).setEffectiveEndDate(isA(Timestamp.class));
        verify(callConfiguration).setEffectiveStartDate(isA(Timestamp.class));
        verify(callConfiguration).setLastModDate(isA(Timestamp.class));
        verify(callConfiguration).setModifiedBy(eq("Jan 1, 2020 9:00am GMT+0100"));
        verify(callConfiguration).setNextAttemptPeriod(isA(Integer.class));
        verify(callConfiguration).setNoOfAttempts(isA(Integer.class));
        verify(callConfiguration).setPsmId(isA(Integer.class));
        verify(callConfiguration).setTermRange(isA(Integer.class));
        verify(callConfigurationRepo).getCallConfiguration(isNull());
        verify(childRecordRepo).findByEcdIdNoChildId(isA(Long.class));
        verify(outboundCallsRepo).getIntroductoryRecordsUser(isNull(), eq("completed"), eq("introductory"));
    }
}
