package com.iemr.ecd.service.outbound_worklist;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.iemr.ecd.dao_temp.FetchChildOutboundWorklist;
import com.iemr.ecd.dao_temp.FetchMotherOutboundWorklist;
import com.iemr.ecd.repo.call_conf_allocation.OutboundCallsRepo;
import com.iemr.ecd.utils.advice.exception_handler.ECDException;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {OutboundWorkListServiceImpl.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class OutboundWorkListServiceImplDiffblueTest {
    @MockBean
    private OutboundCallsRepo outboundCallsRepo;

    @Autowired
    private OutboundWorkListServiceImpl outboundWorkListServiceImpl;

    /**
     * Method under test:
     * {@link OutboundWorkListServiceImpl#getMotherWorkList(Integer)}
     */
    @Test
    void testGetMotherWorkList() {
        // Arrange
        when(outboundCallsRepo.getAgentAllocatedMotherList(Mockito.<Integer>any())).thenReturn(new ArrayList<>());

        // Act
        List<FetchMotherOutboundWorklist> actualMotherWorkList = outboundWorkListServiceImpl.getMotherWorkList(1);

        // Assert
        verify(outboundCallsRepo).getAgentAllocatedMotherList(isA(Integer.class));
        assertTrue(actualMotherWorkList.isEmpty());
    }

    /**
     * Method under test:
     * {@link OutboundWorkListServiceImpl#getMotherWorkList(Integer)}
     */
    @Test
    void testGetMotherWorkList2() {
        // Arrange
        ArrayList<String[]> stringArrayList = new ArrayList<>();
        stringArrayList.add(new String[]{"foo"});
        when(outboundCallsRepo.getAgentAllocatedMotherList(Mockito.<Integer>any())).thenReturn(stringArrayList);

        // Act
        List<FetchMotherOutboundWorklist> actualMotherWorkList = outboundWorkListServiceImpl.getMotherWorkList(1);

        // Assert
        verify(outboundCallsRepo).getAgentAllocatedMotherList(isA(Integer.class));
        assertTrue(actualMotherWorkList.isEmpty());
    }

    /**
     * Method under test:
     * {@link OutboundWorkListServiceImpl#getMotherWorkList(Integer)}
     */
    @Test
    void testGetMotherWorkList3() {
        // Arrange
        when(outboundCallsRepo.getAgentAllocatedMotherList(Mockito.<Integer>any()))
                .thenThrow(new ECDException("Exception Msg"));

        // Act and Assert
        assertThrows(ECDException.class, () -> outboundWorkListServiceImpl.getMotherWorkList(1));
        verify(outboundCallsRepo).getAgentAllocatedMotherList(isA(Integer.class));
    }

    /**
     * Method under test:
     * {@link OutboundWorkListServiceImpl#getMotherWorkList(Integer)}
     */
    @Test
    void testGetMotherWorkList4() {
        // Arrange
        ArrayList<String[]> stringArrayList = new ArrayList<>();
        stringArrayList.add(null);
        when(outboundCallsRepo.getAgentAllocatedMotherList(Mockito.<Integer>any())).thenReturn(stringArrayList);

        // Act
        List<FetchMotherOutboundWorklist> actualMotherWorkList = outboundWorkListServiceImpl.getMotherWorkList(1);

        // Assert
        verify(outboundCallsRepo).getAgentAllocatedMotherList(isA(Integer.class));
        assertTrue(actualMotherWorkList.isEmpty());
    }

    /**
     * Method under test:
     * {@link OutboundWorkListServiceImpl#getMotherWorkList(Integer)}
     */
    @Test
    void testGetMotherWorkList5() {
        // Arrange
        ArrayList<String[]> stringArrayList = new ArrayList<>();
        stringArrayList.add(new String[]{"foo"});
        stringArrayList.add(new String[]{"42"});
        when(outboundCallsRepo.getAgentAllocatedMotherList(Mockito.<Integer>any())).thenReturn(stringArrayList);

        // Act
        List<FetchMotherOutboundWorklist> actualMotherWorkList = outboundWorkListServiceImpl.getMotherWorkList(1);

        // Assert
        verify(outboundCallsRepo).getAgentAllocatedMotherList(isA(Integer.class));
        assertTrue(actualMotherWorkList.isEmpty());
    }

    /**
     * Method under test:
     * {@link OutboundWorkListServiceImpl#getMotherWorkList(Integer)}
     */
    @Test
    void testGetMotherWorkList6() {
        // Arrange
        ArrayList<String[]> stringArrayList = new ArrayList<>();
        stringArrayList.add(new String[]{null});
        stringArrayList.add(new String[]{"42"});
        when(outboundCallsRepo.getAgentAllocatedMotherList(Mockito.<Integer>any())).thenReturn(stringArrayList);

        // Act
        List<FetchMotherOutboundWorklist> actualMotherWorkList = outboundWorkListServiceImpl.getMotherWorkList(1);

        // Assert
        verify(outboundCallsRepo).getAgentAllocatedMotherList(isA(Integer.class));
        assertTrue(actualMotherWorkList.isEmpty());
    }

    /**
     * Method under test:
     * {@link OutboundWorkListServiceImpl#getChildWorkList(Integer)}
     */
    @Test
    void testGetChildWorkList() {
        // Arrange
        when(outboundCallsRepo.getAgentAllocatedChildList(Mockito.<Integer>any())).thenReturn(new ArrayList<>());

        // Act
        List<FetchChildOutboundWorklist> actualChildWorkList = outboundWorkListServiceImpl.getChildWorkList(1);

        // Assert
        verify(outboundCallsRepo).getAgentAllocatedChildList(isA(Integer.class));
        assertTrue(actualChildWorkList.isEmpty());
    }

    /**
     * Method under test:
     * {@link OutboundWorkListServiceImpl#getChildWorkList(Integer)}
     */
    @Test
    void testGetChildWorkList2() {
        // Arrange
        ArrayList<String[]> stringArrayList = new ArrayList<>();
        stringArrayList.add(new String[]{"foo"});
        when(outboundCallsRepo.getAgentAllocatedChildList(Mockito.<Integer>any())).thenReturn(stringArrayList);

        // Act and Assert
        assertThrows(ECDException.class, () -> outboundWorkListServiceImpl.getChildWorkList(1));
        verify(outboundCallsRepo).getAgentAllocatedChildList(isA(Integer.class));
    }

    /**
     * Method under test:
     * {@link OutboundWorkListServiceImpl#getChildWorkList(Integer)}
     */
    @Test
    void testGetChildWorkList3() {
        // Arrange
        when(outboundCallsRepo.getAgentAllocatedChildList(Mockito.<Integer>any()))
                .thenThrow(new ECDException("Exception Msg"));

        // Act and Assert
        assertThrows(ECDException.class, () -> outboundWorkListServiceImpl.getChildWorkList(1));
        verify(outboundCallsRepo).getAgentAllocatedChildList(isA(Integer.class));
    }
}
