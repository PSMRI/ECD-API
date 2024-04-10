package com.iemr.ecd.service.call_conf_allocation;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.iemr.ecd.dao.AlertNotificationLocMsg;
import com.iemr.ecd.repo.call_conf_allocation.AlertNotifyLocMsgRepo;
import com.iemr.ecd.utils.advice.exception_handler.ECDException;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {AlertNotifyLocMsgImpl.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class AlertNotifyLocMsgImplDiffblueTest {
    @Autowired
    private AlertNotifyLocMsgImpl alertNotifyLocMsgImpl;

    @MockBean
    private AlertNotifyLocMsgRepo alertNotifyLocMsgRepo;

    /**
     * Method under test:
     * {@link AlertNotifyLocMsgImpl#createAlertNotificationLocMsg(AlertNotificationLocMsg)}
     */
    @Test
    void testCreateAlertNotificationLocMsg() {
        // Arrange
        ArrayList<AlertNotificationLocMsg> alertNotificationLocMsgList = new ArrayList<>();
        when(alertNotifyLocMsgRepo.saveAll(Mockito.<Iterable<AlertNotificationLocMsg>>any()))
                .thenReturn(alertNotificationLocMsgList);

        AlertNotificationLocMsg alertNotificationLocMsg = new AlertNotificationLocMsg();
        alertNotificationLocMsg.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        alertNotificationLocMsg.setCreatedDate(mock(Timestamp.class));
        alertNotificationLocMsg.setDeleted(true);
        alertNotificationLocMsg.setId(1L);
        alertNotificationLocMsg.setLastModDate(mock(Timestamp.class));
        alertNotificationLocMsg.setMessage("Not all who wander are lost");
        alertNotificationLocMsg.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        alertNotificationLocMsg.setOfficeId(new Integer[]{1});
        alertNotificationLocMsg.setPsmId(1);
        alertNotificationLocMsg.setRoleId(1);
        alertNotificationLocMsg.setSubject("Hello from the Dreaming Spires");
        alertNotificationLocMsg.setTypeId(1);
        alertNotificationLocMsg.setValidFrom(mock(Timestamp.class));
        alertNotificationLocMsg.setValidTo(mock(Timestamp.class));
        alertNotificationLocMsg.setWorkingLocationID(1);
        alertNotificationLocMsg.setWorkingLocationIds("Working Location Ids");
        
        alertNotificationLocMsg.toString();

        // Act
        List<AlertNotificationLocMsg> actualCreateAlertNotificationLocMsgResult = alertNotifyLocMsgImpl
                .createAlertNotificationLocMsg(alertNotificationLocMsg);

        // Assert
        verify(alertNotifyLocMsgRepo).saveAll(isA(Iterable.class));
        assertTrue(actualCreateAlertNotificationLocMsgResult.isEmpty());
        assertSame(alertNotificationLocMsgList, actualCreateAlertNotificationLocMsgResult);
    }

    /**
     * Method under test:
     * {@link AlertNotifyLocMsgImpl#createAlertNotificationLocMsg(AlertNotificationLocMsg)}
     */
    @Test
    void testCreateAlertNotificationLocMsg2() {
        // Arrange
        AlertNotificationLocMsg alertNotificationLocMsg = mock(AlertNotificationLocMsg.class);
        when(alertNotificationLocMsg.getCreatedBy()).thenThrow(new ECDException("Exception Msg"));
        when(alertNotificationLocMsg.getOfficeId()).thenReturn(new Integer[]{1});
        doNothing().when(alertNotificationLocMsg).setCreatedBy(Mockito.<String>any());
        doNothing().when(alertNotificationLocMsg).setCreatedDate(Mockito.<Timestamp>any());
        doNothing().when(alertNotificationLocMsg).setDeleted(Mockito.<Boolean>any());
        doNothing().when(alertNotificationLocMsg).setId(Mockito.<Long>any());
        doNothing().when(alertNotificationLocMsg).setLastModDate(Mockito.<Timestamp>any());
        doNothing().when(alertNotificationLocMsg).setMessage(Mockito.<String>any());
        doNothing().when(alertNotificationLocMsg).setModifiedBy(Mockito.<String>any());
        doNothing().when(alertNotificationLocMsg).setOfficeId(Mockito.<Integer[]>any());
        doNothing().when(alertNotificationLocMsg).setPsmId(Mockito.<Integer>any());
        doNothing().when(alertNotificationLocMsg).setRoleId(Mockito.<Integer>any());
        doNothing().when(alertNotificationLocMsg).setSubject(Mockito.<String>any());
        doNothing().when(alertNotificationLocMsg).setTypeId(Mockito.<Integer>any());
        doNothing().when(alertNotificationLocMsg).setValidFrom(Mockito.<Timestamp>any());
        doNothing().when(alertNotificationLocMsg).setValidTo(Mockito.<Timestamp>any());
        doNothing().when(alertNotificationLocMsg).setWorkingLocationID(Mockito.<Integer>any());
        doNothing().when(alertNotificationLocMsg).setWorkingLocationIds(Mockito.<String>any());
        alertNotificationLocMsg.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        alertNotificationLocMsg.setCreatedDate(mock(Timestamp.class));
        alertNotificationLocMsg.setDeleted(true);
        alertNotificationLocMsg.setId(1L);
        alertNotificationLocMsg.setLastModDate(mock(Timestamp.class));
        alertNotificationLocMsg.setMessage("Not all who wander are lost");
        alertNotificationLocMsg.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        alertNotificationLocMsg.setOfficeId(new Integer[]{1});
        alertNotificationLocMsg.setPsmId(1);
        alertNotificationLocMsg.setRoleId(1);
        alertNotificationLocMsg.setSubject("Hello from the Dreaming Spires");
        alertNotificationLocMsg.setTypeId(1);
        alertNotificationLocMsg.setValidFrom(mock(Timestamp.class));
        alertNotificationLocMsg.setValidTo(mock(Timestamp.class));
        alertNotificationLocMsg.setWorkingLocationID(1);
        alertNotificationLocMsg.setWorkingLocationIds("Working Location Ids");

        // Act and Assert
        assertThrows(ECDException.class,
                () -> alertNotifyLocMsgImpl.createAlertNotificationLocMsg(alertNotificationLocMsg));
        verify(alertNotificationLocMsg).getCreatedBy();
        verify(alertNotificationLocMsg, atLeast(1)).getOfficeId();
        verify(alertNotificationLocMsg).setCreatedBy(eq("Jan 1, 2020 8:00am GMT+0100"));
        verify(alertNotificationLocMsg).setCreatedDate(isA(Timestamp.class));
        verify(alertNotificationLocMsg).setDeleted(isA(Boolean.class));
        verify(alertNotificationLocMsg).setId(isA(Long.class));
        verify(alertNotificationLocMsg).setLastModDate(isA(Timestamp.class));
        verify(alertNotificationLocMsg).setMessage(eq("Not all who wander are lost"));
        verify(alertNotificationLocMsg).setModifiedBy(eq("Jan 1, 2020 9:00am GMT+0100"));
        verify(alertNotificationLocMsg).setOfficeId(isA(Integer[].class));
        verify(alertNotificationLocMsg).setPsmId(isA(Integer.class));
        verify(alertNotificationLocMsg).setRoleId(isA(Integer.class));
        verify(alertNotificationLocMsg).setSubject(eq("Hello from the Dreaming Spires"));
        verify(alertNotificationLocMsg).setTypeId(isA(Integer.class));
        verify(alertNotificationLocMsg).setValidFrom(isA(Timestamp.class));
        verify(alertNotificationLocMsg).setValidTo(isA(Timestamp.class));
        verify(alertNotificationLocMsg).setWorkingLocationID(isA(Integer.class));
        verify(alertNotificationLocMsg).setWorkingLocationIds(eq("Working Location Ids"));
    }

    /**
     * Method under test:
     * {@link AlertNotifyLocMsgImpl#createAlertNotificationLocMsg(AlertNotificationLocMsg)}
     */
    @Test
    void testCreateAlertNotificationLocMsg3() {
        // Arrange
        ArrayList<AlertNotificationLocMsg> alertNotificationLocMsgList = new ArrayList<>();
        when(alertNotifyLocMsgRepo.saveAll(Mockito.<Iterable<AlertNotificationLocMsg>>any()))
                .thenReturn(alertNotificationLocMsgList);
        AlertNotificationLocMsg alertNotificationLocMsg = mock(AlertNotificationLocMsg.class);
        when(alertNotificationLocMsg.getOfficeId()).thenReturn(null);
        doNothing().when(alertNotificationLocMsg).setCreatedBy(Mockito.<String>any());
        doNothing().when(alertNotificationLocMsg).setCreatedDate(Mockito.<Timestamp>any());
        doNothing().when(alertNotificationLocMsg).setDeleted(Mockito.<Boolean>any());
        doNothing().when(alertNotificationLocMsg).setId(Mockito.<Long>any());
        doNothing().when(alertNotificationLocMsg).setLastModDate(Mockito.<Timestamp>any());
        doNothing().when(alertNotificationLocMsg).setMessage(Mockito.<String>any());
        doNothing().when(alertNotificationLocMsg).setModifiedBy(Mockito.<String>any());
        doNothing().when(alertNotificationLocMsg).setOfficeId(Mockito.<Integer[]>any());
        doNothing().when(alertNotificationLocMsg).setPsmId(Mockito.<Integer>any());
        doNothing().when(alertNotificationLocMsg).setRoleId(Mockito.<Integer>any());
        doNothing().when(alertNotificationLocMsg).setSubject(Mockito.<String>any());
        doNothing().when(alertNotificationLocMsg).setTypeId(Mockito.<Integer>any());
        doNothing().when(alertNotificationLocMsg).setValidFrom(Mockito.<Timestamp>any());
        doNothing().when(alertNotificationLocMsg).setValidTo(Mockito.<Timestamp>any());
        doNothing().when(alertNotificationLocMsg).setWorkingLocationID(Mockito.<Integer>any());
        doNothing().when(alertNotificationLocMsg).setWorkingLocationIds(Mockito.<String>any());
        alertNotificationLocMsg.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        alertNotificationLocMsg.setCreatedDate(mock(Timestamp.class));
        alertNotificationLocMsg.setDeleted(true);
        alertNotificationLocMsg.setId(1L);
        alertNotificationLocMsg.setLastModDate(mock(Timestamp.class));
        alertNotificationLocMsg.setMessage("Not all who wander are lost");
        alertNotificationLocMsg.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        alertNotificationLocMsg.setOfficeId(new Integer[]{1});
        alertNotificationLocMsg.setPsmId(1);
        alertNotificationLocMsg.setRoleId(1);
        alertNotificationLocMsg.setSubject("Hello from the Dreaming Spires");
        alertNotificationLocMsg.setTypeId(1);
        alertNotificationLocMsg.setValidFrom(mock(Timestamp.class));
        alertNotificationLocMsg.setValidTo(mock(Timestamp.class));
        alertNotificationLocMsg.setWorkingLocationID(1);
        alertNotificationLocMsg.setWorkingLocationIds("Working Location Ids");

        // Act
        List<AlertNotificationLocMsg> actualCreateAlertNotificationLocMsgResult = alertNotifyLocMsgImpl
                .createAlertNotificationLocMsg(alertNotificationLocMsg);

        // Assert
        verify(alertNotificationLocMsg).getOfficeId();
        verify(alertNotificationLocMsg).setCreatedBy(eq("Jan 1, 2020 8:00am GMT+0100"));
        verify(alertNotificationLocMsg).setCreatedDate(isA(Timestamp.class));
        verify(alertNotificationLocMsg).setDeleted(isA(Boolean.class));
        verify(alertNotificationLocMsg).setId(isA(Long.class));
        verify(alertNotificationLocMsg).setLastModDate(isA(Timestamp.class));
        verify(alertNotificationLocMsg).setMessage(eq("Not all who wander are lost"));
        verify(alertNotificationLocMsg).setModifiedBy(eq("Jan 1, 2020 9:00am GMT+0100"));
        verify(alertNotificationLocMsg).setOfficeId(isA(Integer[].class));
        verify(alertNotificationLocMsg).setPsmId(isA(Integer.class));
        verify(alertNotificationLocMsg).setRoleId(isA(Integer.class));
        verify(alertNotificationLocMsg).setSubject(eq("Hello from the Dreaming Spires"));
        verify(alertNotificationLocMsg).setTypeId(isA(Integer.class));
        verify(alertNotificationLocMsg).setValidFrom(isA(Timestamp.class));
        verify(alertNotificationLocMsg).setValidTo(isA(Timestamp.class));
        verify(alertNotificationLocMsg).setWorkingLocationID(isA(Integer.class));
        verify(alertNotificationLocMsg).setWorkingLocationIds(eq("Working Location Ids"));
        verify(alertNotifyLocMsgRepo).saveAll(isA(Iterable.class));
        assertTrue(actualCreateAlertNotificationLocMsgResult.isEmpty());
        assertSame(alertNotificationLocMsgList, actualCreateAlertNotificationLocMsgResult);
    }

    /**
     * Method under test:
     * {@link AlertNotifyLocMsgImpl#createAlertNotificationLocMsg(AlertNotificationLocMsg)}
     */
    @Test
    void testCreateAlertNotificationLocMsg4() {
        // Arrange
        ArrayList<AlertNotificationLocMsg> alertNotificationLocMsgList = new ArrayList<>();
        when(alertNotifyLocMsgRepo.saveAll(Mockito.<Iterable<AlertNotificationLocMsg>>any()))
                .thenReturn(alertNotificationLocMsgList);
        AlertNotificationLocMsg alertNotificationLocMsg = mock(AlertNotificationLocMsg.class);
        when(alertNotificationLocMsg.getOfficeId()).thenReturn(new Integer[]{});
        doNothing().when(alertNotificationLocMsg).setCreatedBy(Mockito.<String>any());
        doNothing().when(alertNotificationLocMsg).setCreatedDate(Mockito.<Timestamp>any());
        doNothing().when(alertNotificationLocMsg).setDeleted(Mockito.<Boolean>any());
        doNothing().when(alertNotificationLocMsg).setId(Mockito.<Long>any());
        doNothing().when(alertNotificationLocMsg).setLastModDate(Mockito.<Timestamp>any());
        doNothing().when(alertNotificationLocMsg).setMessage(Mockito.<String>any());
        doNothing().when(alertNotificationLocMsg).setModifiedBy(Mockito.<String>any());
        doNothing().when(alertNotificationLocMsg).setOfficeId(Mockito.<Integer[]>any());
        doNothing().when(alertNotificationLocMsg).setPsmId(Mockito.<Integer>any());
        doNothing().when(alertNotificationLocMsg).setRoleId(Mockito.<Integer>any());
        doNothing().when(alertNotificationLocMsg).setSubject(Mockito.<String>any());
        doNothing().when(alertNotificationLocMsg).setTypeId(Mockito.<Integer>any());
        doNothing().when(alertNotificationLocMsg).setValidFrom(Mockito.<Timestamp>any());
        doNothing().when(alertNotificationLocMsg).setValidTo(Mockito.<Timestamp>any());
        doNothing().when(alertNotificationLocMsg).setWorkingLocationID(Mockito.<Integer>any());
        doNothing().when(alertNotificationLocMsg).setWorkingLocationIds(Mockito.<String>any());
        alertNotificationLocMsg.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        alertNotificationLocMsg.setCreatedDate(mock(Timestamp.class));
        alertNotificationLocMsg.setDeleted(true);
        alertNotificationLocMsg.setId(1L);
        alertNotificationLocMsg.setLastModDate(mock(Timestamp.class));
        alertNotificationLocMsg.setMessage("Not all who wander are lost");
        alertNotificationLocMsg.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        alertNotificationLocMsg.setOfficeId(new Integer[]{1});
        alertNotificationLocMsg.setPsmId(1);
        alertNotificationLocMsg.setRoleId(1);
        alertNotificationLocMsg.setSubject("Hello from the Dreaming Spires");
        alertNotificationLocMsg.setTypeId(1);
        alertNotificationLocMsg.setValidFrom(mock(Timestamp.class));
        alertNotificationLocMsg.setValidTo(mock(Timestamp.class));
        alertNotificationLocMsg.setWorkingLocationID(1);
        alertNotificationLocMsg.setWorkingLocationIds("Working Location Ids");

        // Act
        List<AlertNotificationLocMsg> actualCreateAlertNotificationLocMsgResult = alertNotifyLocMsgImpl
                .createAlertNotificationLocMsg(alertNotificationLocMsg);

        // Assert
        verify(alertNotificationLocMsg, atLeast(1)).getOfficeId();
        verify(alertNotificationLocMsg).setCreatedBy(eq("Jan 1, 2020 8:00am GMT+0100"));
        verify(alertNotificationLocMsg).setCreatedDate(isA(Timestamp.class));
        verify(alertNotificationLocMsg).setDeleted(isA(Boolean.class));
        verify(alertNotificationLocMsg).setId(isA(Long.class));
        verify(alertNotificationLocMsg).setLastModDate(isA(Timestamp.class));
        verify(alertNotificationLocMsg).setMessage(eq("Not all who wander are lost"));
        verify(alertNotificationLocMsg).setModifiedBy(eq("Jan 1, 2020 9:00am GMT+0100"));
        verify(alertNotificationLocMsg).setOfficeId(isA(Integer[].class));
        verify(alertNotificationLocMsg).setPsmId(isA(Integer.class));
        verify(alertNotificationLocMsg).setRoleId(isA(Integer.class));
        verify(alertNotificationLocMsg).setSubject(eq("Hello from the Dreaming Spires"));
        verify(alertNotificationLocMsg).setTypeId(isA(Integer.class));
        verify(alertNotificationLocMsg).setValidFrom(isA(Timestamp.class));
        verify(alertNotificationLocMsg).setValidTo(isA(Timestamp.class));
        verify(alertNotificationLocMsg).setWorkingLocationID(isA(Integer.class));
        verify(alertNotificationLocMsg).setWorkingLocationIds(eq("Working Location Ids"));
        verify(alertNotifyLocMsgRepo).saveAll(isA(Iterable.class));
        assertTrue(actualCreateAlertNotificationLocMsgResult.isEmpty());
        assertSame(alertNotificationLocMsgList, actualCreateAlertNotificationLocMsgResult);
    }

    /**
     * Method under test:
     * {@link AlertNotifyLocMsgImpl#getAlertNotificationLocMsgs()}
     */
    @Test
    void testGetAlertNotificationLocMsgs() {
        // Arrange
        ArrayList<AlertNotificationLocMsg> alertNotificationLocMsgList = new ArrayList<>();
        when(alertNotifyLocMsgRepo.findByDeleted(Mockito.<Boolean>any())).thenReturn(alertNotificationLocMsgList);

        // Act
        List<AlertNotificationLocMsg> actualAlertNotificationLocMsgs = alertNotifyLocMsgImpl.getAlertNotificationLocMsgs();

        // Assert
        verify(alertNotifyLocMsgRepo).findByDeleted(isA(Boolean.class));
        assertTrue(actualAlertNotificationLocMsgs.isEmpty());
        assertSame(alertNotificationLocMsgList, actualAlertNotificationLocMsgs);
    }

    /**
     * Method under test:
     * {@link AlertNotifyLocMsgImpl#getAlertNotificationLocMsgs()}
     */
    @Test
    void testGetAlertNotificationLocMsgs2() {
        // Arrange
        when(alertNotifyLocMsgRepo.findByDeleted(Mockito.<Boolean>any())).thenThrow(new ECDException("Exception Msg"));

        // Act and Assert
        assertThrows(ECDException.class, () -> alertNotifyLocMsgImpl.getAlertNotificationLocMsgs());
        verify(alertNotifyLocMsgRepo).findByDeleted(isA(Boolean.class));
    }

    /**
     * Method under test:
     * {@link AlertNotifyLocMsgImpl#getAlertNotificationLocMsgById(Long)}
     */
    @Test
    void testGetAlertNotificationLocMsgById() {
        // Arrange
        AlertNotificationLocMsg alertNotificationLocMsg = new AlertNotificationLocMsg();
        alertNotificationLocMsg.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        alertNotificationLocMsg.setCreatedDate(mock(Timestamp.class));
        alertNotificationLocMsg.setDeleted(true);
        alertNotificationLocMsg.setId(1L);
        alertNotificationLocMsg.setLastModDate(mock(Timestamp.class));
        alertNotificationLocMsg.setMessage("Not all who wander are lost");
        alertNotificationLocMsg.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        alertNotificationLocMsg.setOfficeId(new Integer[]{1});
        alertNotificationLocMsg.setPsmId(1);
        alertNotificationLocMsg.setRoleId(1);
        alertNotificationLocMsg.setSubject("Hello from the Dreaming Spires");
        alertNotificationLocMsg.setTypeId(1);
        alertNotificationLocMsg.setValidFrom(mock(Timestamp.class));
        alertNotificationLocMsg.setValidTo(mock(Timestamp.class));
        alertNotificationLocMsg.setWorkingLocationID(1);
        alertNotificationLocMsg.setWorkingLocationIds("Working Location Ids");
        Optional<AlertNotificationLocMsg> ofResult = Optional.of(alertNotificationLocMsg);
        when(alertNotifyLocMsgRepo.findById(Mockito.<Long>any())).thenReturn(ofResult);

        // Act
        AlertNotificationLocMsg actualAlertNotificationLocMsgById = alertNotifyLocMsgImpl
                .getAlertNotificationLocMsgById(1L);

        // Assert
        verify(alertNotifyLocMsgRepo).findById(isA(Long.class));
        assertSame(alertNotificationLocMsg, actualAlertNotificationLocMsgById);
    }

    /**
     * Method under test:
     * {@link AlertNotifyLocMsgImpl#getAlertNotificationLocMsgById(Long)}
     */
    @Test
    void testGetAlertNotificationLocMsgById2() {
        // Arrange
        Optional<AlertNotificationLocMsg> emptyResult = Optional.empty();
        when(alertNotifyLocMsgRepo.findById(Mockito.<Long>any())).thenReturn(emptyResult);

        // Act and Assert
        assertThrows(ECDException.class, () -> alertNotifyLocMsgImpl.getAlertNotificationLocMsgById(1L));
        verify(alertNotifyLocMsgRepo).findById(isA(Long.class));
    }

    /**
     * Method under test:
     * {@link AlertNotifyLocMsgImpl#getAlertNotificationLocMsgById(Long)}
     */
    @Test
    void testGetAlertNotificationLocMsgById3() {
        // Arrange
        when(alertNotifyLocMsgRepo.findById(Mockito.<Long>any())).thenThrow(new ECDException("No such element found"));

        // Act and Assert
        assertThrows(ECDException.class, () -> alertNotifyLocMsgImpl.getAlertNotificationLocMsgById(1L));
        verify(alertNotifyLocMsgRepo).findById(isA(Long.class));
    }

    /**
     * Method under test:
     * {@link AlertNotifyLocMsgImpl#getAlertNotificationLocMsgByPSMId(Integer)}
     */
    @Test
    void testGetAlertNotificationLocMsgByPSMId() {
        // Arrange
        ArrayList<AlertNotificationLocMsg> alertNotificationLocMsgList = new ArrayList<>();
        when(alertNotifyLocMsgRepo.findByPsmId(Mockito.<Integer>any())).thenReturn(alertNotificationLocMsgList);

        // Act
        List<AlertNotificationLocMsg> actualAlertNotificationLocMsgByPSMId = alertNotifyLocMsgImpl
                .getAlertNotificationLocMsgByPSMId(1);

        // Assert
        verify(alertNotifyLocMsgRepo).findByPsmId(isA(Integer.class));
        assertTrue(actualAlertNotificationLocMsgByPSMId.isEmpty());
        assertSame(alertNotificationLocMsgList, actualAlertNotificationLocMsgByPSMId);
    }

    /**
     * Method under test:
     * {@link AlertNotifyLocMsgImpl#getAlertNotificationLocMsgByPSMId(Integer)}
     */
    @Test
    void testGetAlertNotificationLocMsgByPSMId2() {
        // Arrange
        when(alertNotifyLocMsgRepo.findByPsmId(Mockito.<Integer>any())).thenThrow(new ECDException("Exception Msg"));

        // Act and Assert
        assertThrows(ECDException.class, () -> alertNotifyLocMsgImpl.getAlertNotificationLocMsgByPSMId(1));
        verify(alertNotifyLocMsgRepo).findByPsmId(isA(Integer.class));
    }

    /**
     * Method under test:
     * {@link AlertNotifyLocMsgImpl#getAlertNotificationLocMsgByRoleIdAndPSMId(Integer, Integer)}
     */
    @Test
    void testGetAlertNotificationLocMsgByRoleIdAndPSMId() {
        // Arrange
        ArrayList<AlertNotificationLocMsg> alertNotificationLocMsgList = new ArrayList<>();
        when(alertNotifyLocMsgRepo.findByRoleIdAndPsmId(Mockito.<Integer>any(), Mockito.<Integer>any()))
                .thenReturn(alertNotificationLocMsgList);

        // Act
        List<AlertNotificationLocMsg> actualAlertNotificationLocMsgByRoleIdAndPSMId = alertNotifyLocMsgImpl
                .getAlertNotificationLocMsgByRoleIdAndPSMId(1, 1);

        // Assert
        verify(alertNotifyLocMsgRepo).findByRoleIdAndPsmId(isA(Integer.class), isA(Integer.class));
        assertTrue(actualAlertNotificationLocMsgByRoleIdAndPSMId.isEmpty());
        assertSame(alertNotificationLocMsgList, actualAlertNotificationLocMsgByRoleIdAndPSMId);
    }

    /**
     * Method under test:
     * {@link AlertNotifyLocMsgImpl#getAlertNotificationLocMsgByRoleIdAndPSMId(Integer, Integer)}
     */
    @Test
    void testGetAlertNotificationLocMsgByRoleIdAndPSMId2() {
        // Arrange
        when(alertNotifyLocMsgRepo.findByRoleIdAndPsmId(Mockito.<Integer>any(), Mockito.<Integer>any()))
                .thenThrow(new ECDException("Exception Msg"));

        // Act and Assert
        assertThrows(ECDException.class, () -> alertNotifyLocMsgImpl.getAlertNotificationLocMsgByRoleIdAndPSMId(1, 1));
        verify(alertNotifyLocMsgRepo).findByRoleIdAndPsmId(isA(Integer.class), isA(Integer.class));
    }

    /**
     * Method under test:
     * {@link AlertNotifyLocMsgImpl#updateAlertNotificationLocMsg(List)}
     */
    @Test
    void testUpdateAlertNotificationLocMsg() {
        // Arrange
        ArrayList<AlertNotificationLocMsg> alertNotificationLocMsgList = new ArrayList<>();
        when(alertNotifyLocMsgRepo.saveAll(Mockito.<Iterable<AlertNotificationLocMsg>>any()))
                .thenReturn(alertNotificationLocMsgList);

        // Act
        List<AlertNotificationLocMsg> actualUpdateAlertNotificationLocMsgResult = alertNotifyLocMsgImpl
                .updateAlertNotificationLocMsg(new ArrayList<>());

        // Assert
        verify(alertNotifyLocMsgRepo).saveAll(isA(Iterable.class));
        assertTrue(actualUpdateAlertNotificationLocMsgResult.isEmpty());
        assertSame(alertNotificationLocMsgList, actualUpdateAlertNotificationLocMsgResult);
    }

    /**
     * Method under test:
     * {@link AlertNotifyLocMsgImpl#updateAlertNotificationLocMsg(List)}
     */
    @Test
    void testUpdateAlertNotificationLocMsg2() {
        // Arrange
        ArrayList<AlertNotificationLocMsg> alertNotificationLocMsgList = new ArrayList<>();
        when(alertNotifyLocMsgRepo.saveAll(Mockito.<Iterable<AlertNotificationLocMsg>>any()))
                .thenReturn(alertNotificationLocMsgList);

        AlertNotificationLocMsg alertNotificationLocMsg = new AlertNotificationLocMsg();
        alertNotificationLocMsg.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        alertNotificationLocMsg.setCreatedDate(mock(Timestamp.class));
        alertNotificationLocMsg.setDeleted(true);
        alertNotificationLocMsg.setId(1L);
        alertNotificationLocMsg.setLastModDate(mock(Timestamp.class));
        alertNotificationLocMsg.setMessage("Not all who wander are lost");
        alertNotificationLocMsg.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        alertNotificationLocMsg.setOfficeId(new Integer[]{1});
        alertNotificationLocMsg.setPsmId(1);
        alertNotificationLocMsg.setRoleId(1);
        alertNotificationLocMsg.setSubject("Hello from the Dreaming Spires");
        alertNotificationLocMsg.setTypeId(1);
        alertNotificationLocMsg.setValidFrom(mock(Timestamp.class));
        alertNotificationLocMsg.setValidTo(mock(Timestamp.class));
        alertNotificationLocMsg.setWorkingLocationID(1);
        alertNotificationLocMsg.setWorkingLocationIds("Working Location Ids");

        ArrayList<AlertNotificationLocMsg> alertNotificationLocMsg2 = new ArrayList<>();
        alertNotificationLocMsg2.add(alertNotificationLocMsg);

        // Act
        List<AlertNotificationLocMsg> actualUpdateAlertNotificationLocMsgResult = alertNotifyLocMsgImpl
                .updateAlertNotificationLocMsg(alertNotificationLocMsg2);

        // Assert
        verify(alertNotifyLocMsgRepo).saveAll(isA(Iterable.class));
        assertTrue(actualUpdateAlertNotificationLocMsgResult.isEmpty());
        assertSame(alertNotificationLocMsgList, actualUpdateAlertNotificationLocMsgResult);
    }

    /**
     * Method under test:
     * {@link AlertNotifyLocMsgImpl#updateAlertNotificationLocMsg(List)}
     */
    @Test
    void testUpdateAlertNotificationLocMsg3() {
        // Arrange
        ArrayList<AlertNotificationLocMsg> alertNotificationLocMsgList = new ArrayList<>();
        when(alertNotifyLocMsgRepo.saveAll(Mockito.<Iterable<AlertNotificationLocMsg>>any()))
                .thenReturn(alertNotificationLocMsgList);

        AlertNotificationLocMsg alertNotificationLocMsg = new AlertNotificationLocMsg();
        alertNotificationLocMsg.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        alertNotificationLocMsg.setCreatedDate(mock(Timestamp.class));
        alertNotificationLocMsg.setDeleted(true);
        alertNotificationLocMsg.setId(1L);
        alertNotificationLocMsg.setLastModDate(mock(Timestamp.class));
        alertNotificationLocMsg.setMessage("Not all who wander are lost");
        alertNotificationLocMsg.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        alertNotificationLocMsg.setOfficeId(new Integer[]{1});
        alertNotificationLocMsg.setPsmId(1);
        alertNotificationLocMsg.setRoleId(1);
        alertNotificationLocMsg.setSubject("Hello from the Dreaming Spires");
        alertNotificationLocMsg.setTypeId(1);
        alertNotificationLocMsg.setValidFrom(mock(Timestamp.class));
        alertNotificationLocMsg.setValidTo(mock(Timestamp.class));
        alertNotificationLocMsg.setWorkingLocationID(1);
        alertNotificationLocMsg.setWorkingLocationIds("Working Location Ids");

        AlertNotificationLocMsg alertNotificationLocMsg2 = new AlertNotificationLocMsg();
        alertNotificationLocMsg2.setCreatedBy("Created By");
        alertNotificationLocMsg2.setCreatedDate(mock(Timestamp.class));
        alertNotificationLocMsg2.setDeleted(false);
        alertNotificationLocMsg2.setId(2L);
        alertNotificationLocMsg2.setLastModDate(mock(Timestamp.class));
        alertNotificationLocMsg2.setMessage("Message");
        alertNotificationLocMsg2.setModifiedBy("Modified By");
        alertNotificationLocMsg2.setOfficeId(new Integer[]{1});
        alertNotificationLocMsg2.setPsmId(2);
        alertNotificationLocMsg2.setRoleId(2);
        alertNotificationLocMsg2.setSubject("Subject");
        alertNotificationLocMsg2.setTypeId(2);
        alertNotificationLocMsg2.setValidFrom(mock(Timestamp.class));
        alertNotificationLocMsg2.setValidTo(mock(Timestamp.class));
        alertNotificationLocMsg2.setWorkingLocationID(2);
        alertNotificationLocMsg2.setWorkingLocationIds("com.iemr.ecd.dao.AlertNotificationLocMsg");

        ArrayList<AlertNotificationLocMsg> alertNotificationLocMsg3 = new ArrayList<>();
        alertNotificationLocMsg3.add(alertNotificationLocMsg2);
        alertNotificationLocMsg3.add(alertNotificationLocMsg);

        // Act
        List<AlertNotificationLocMsg> actualUpdateAlertNotificationLocMsgResult = alertNotifyLocMsgImpl
                .updateAlertNotificationLocMsg(alertNotificationLocMsg3);

        // Assert
        verify(alertNotifyLocMsgRepo).saveAll(isA(Iterable.class));
        assertTrue(actualUpdateAlertNotificationLocMsgResult.isEmpty());
        assertSame(alertNotificationLocMsgList, actualUpdateAlertNotificationLocMsgResult);
    }

    /**
     * Method under test:
     * {@link AlertNotifyLocMsgImpl#updateAlertNotificationLocMsg(List)}
     */
    @Test
    void testUpdateAlertNotificationLocMsg4() {
        // Arrange
        when(alertNotifyLocMsgRepo.saveAll(Mockito.<Iterable<AlertNotificationLocMsg>>any()))
                .thenThrow(new ECDException("Exception Msg"));

        // Act and Assert
        assertThrows(ECDException.class, () -> alertNotifyLocMsgImpl.updateAlertNotificationLocMsg(new ArrayList<>()));
        verify(alertNotifyLocMsgRepo).saveAll(isA(Iterable.class));
    }
}
