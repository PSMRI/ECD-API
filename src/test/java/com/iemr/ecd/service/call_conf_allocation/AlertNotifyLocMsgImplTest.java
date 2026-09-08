/*
* AMRIT – Accessible Medical Records via Integrated Technology
* Integrated EHR (Electronic Health Records) Solution
*
* Copyright (C) "Piramal Swasthya Management and Research Institute"
*
* This file is part of AMRIT.
*
* This program is free software: you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation, either version 3 of the License, or
* (at your option) any later version.
*
* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with this program.  If not, see https://www.gnu.org/licenses/.
*/
package com.iemr.ecd.service.call_conf_allocation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.iemr.ecd.dao.AlertNotificationLocMsg;
import com.iemr.ecd.repo.call_conf_allocation.AlertNotifyLocMsgRepo;
import com.iemr.ecd.utils.advice.exception_handler.ECDException;

@ExtendWith(MockitoExtension.class)
class AlertNotifyLocMsgImplTest {

	@Mock
	private AlertNotifyLocMsgRepo alertNotifyLocMsgRepo;

	@InjectMocks
	private AlertNotifyLocMsgImpl service;

	@SuppressWarnings("unchecked")
	@Test
	void createFansOutOneMessagePerOffice() {
		AlertNotificationLocMsg request = new AlertNotificationLocMsg();
		request.setOfficeId(new Integer[] { 10, 20 });
		request.setCreatedBy("admin");
		request.setRoleId(3);
		request.setPsmId(1);
		request.setTypeId(2);
		request.setSubject("subject");
		request.setMessage("message");
		request.setValidFrom(new Timestamp(0));
		request.setValidTo(new Timestamp(1000));

		service.createAlertNotificationLocMsg(request);

		ArgumentCaptor<List<AlertNotificationLocMsg>> captor = ArgumentCaptor.forClass(List.class);
		verify(alertNotifyLocMsgRepo).saveAll(captor.capture());
		List<AlertNotificationLocMsg> saved = captor.getValue();
		assertEquals(2, saved.size());
		assertEquals(10, saved.get(0).getWorkingLocationID());
		assertEquals(20, saved.get(1).getWorkingLocationID());
		assertEquals("subject", saved.get(0).getSubject());
		assertEquals("message", saved.get(0).getMessage());
		assertEquals(3, saved.get(0).getRoleId());
		assertEquals(2, saved.get(0).getTypeId());
	}

	@SuppressWarnings("unchecked")
	@Test
	void createSavesSingleMessageWhenNoOfficeIds() {
		AlertNotificationLocMsg request = new AlertNotificationLocMsg();

		service.createAlertNotificationLocMsg(request);

		ArgumentCaptor<List<AlertNotificationLocMsg>> captor = ArgumentCaptor.forClass(List.class);
		verify(alertNotifyLocMsgRepo).saveAll(captor.capture());
		assertEquals(List.of(request), captor.getValue());
	}

	@SuppressWarnings("unchecked")
	@Test
	void createSavesSingleMessageWhenOfficeIdsEmpty() {
		AlertNotificationLocMsg request = new AlertNotificationLocMsg();
		request.setOfficeId(new Integer[0]);

		service.createAlertNotificationLocMsg(request);

		ArgumentCaptor<List<AlertNotificationLocMsg>> captor = ArgumentCaptor.forClass(List.class);
		verify(alertNotifyLocMsgRepo).saveAll(captor.capture());
		assertEquals(1, captor.getValue().size());
	}

	@SuppressWarnings("unchecked")
	@Test
	void createSavesNullRequestAsSingleEntry() {
		service.createAlertNotificationLocMsg(null);

		ArgumentCaptor<List<AlertNotificationLocMsg>> captor = ArgumentCaptor.forClass(List.class);
		verify(alertNotifyLocMsgRepo).saveAll(captor.capture());
		assertEquals(1, captor.getValue().size());
	}

	@Test
	void getAllReturnsNonDeletedMessages() {
		List<AlertNotificationLocMsg> messages = List.of(new AlertNotificationLocMsg());
		when(alertNotifyLocMsgRepo.findByDeleted(false)).thenReturn(messages);

		assertSame(messages, service.getAlertNotificationLocMsgs());
	}

	@Test
	void getByIdReturnsMessageWhenPresent() {
		AlertNotificationLocMsg message = new AlertNotificationLocMsg();
		when(alertNotifyLocMsgRepo.findById(5L)).thenReturn(Optional.of(message));

		assertSame(message, service.getAlertNotificationLocMsgById(5L));
	}

	@Test
	void getByIdThrowsWhenAbsent() {
		when(alertNotifyLocMsgRepo.findById(5L)).thenReturn(Optional.empty());

		assertThrows(ECDException.class, () -> service.getAlertNotificationLocMsgById(5L));
	}

	@Test
	void getByPsmIdDelegatesToRepository() {
		List<AlertNotificationLocMsg> messages = List.of(new AlertNotificationLocMsg());
		when(alertNotifyLocMsgRepo.findByPsmId(1)).thenReturn(messages);

		assertSame(messages, service.getAlertNotificationLocMsgByPSMId(1));
	}

	@Test
	void getByRoleIdAndPsmIdDelegatesToRepository() {
		List<AlertNotificationLocMsg> messages = List.of(new AlertNotificationLocMsg());
		when(alertNotifyLocMsgRepo.findByRoleIdAndPsmId(3, 1)).thenReturn(messages);

		assertSame(messages, service.getAlertNotificationLocMsgByRoleIdAndPSMId(3, 1));
	}

	@Test
	void updateSavesAllMessages() {
		List<AlertNotificationLocMsg> messages = List.of(new AlertNotificationLocMsg());
		when(alertNotifyLocMsgRepo.saveAll(anyList())).thenReturn(messages);

		assertSame(messages, service.updateAlertNotificationLocMsg(messages));
	}
}
