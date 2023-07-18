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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iemr.ecd.dao.AlertNotificationLocMsg;
import com.iemr.ecd.repo.call_conf_allocation.AlertNotifyLocMsgRepo;
import com.iemr.ecd.utils.advice.exception_handler.ECDException;

@Service
public class AlertNotifyLocMsgImpl {

	@Autowired
	private AlertNotifyLocMsgRepo alertNotifyLocMsgRepo;
//	@Autowired
//	private UserServiceRoleMappingRepo userServiceRoleMappingRepo;
//	@Autowired
//	private UserNotificationMapRepo userNotificationMapRepo;

	public List<AlertNotificationLocMsg> createAlertNotificationLocMsg(
			AlertNotificationLocMsg alertNotificationLocMsg) {
		AlertNotificationLocMsg obj;
		List<AlertNotificationLocMsg> alertNotificationLocMsgs = new ArrayList<>();

		if (alertNotificationLocMsg != null && alertNotificationLocMsg.getOfficeId() != null
				&& alertNotificationLocMsg.getOfficeId().length > 0) {
			for (Integer i : alertNotificationLocMsg.getOfficeId()) {
				obj = new AlertNotificationLocMsg();
				obj.setCreatedBy(alertNotificationLocMsg.getCreatedBy());
				obj.setRoleId(alertNotificationLocMsg.getRoleId());
				obj.setValidFrom(alertNotificationLocMsg.getValidFrom());
				obj.setValidTo(alertNotificationLocMsg.getValidTo());
				obj.setSubject(alertNotificationLocMsg.getSubject());
				obj.setMessage(alertNotificationLocMsg.getMessage());
				obj.setPsmId(alertNotificationLocMsg.getPsmId());
				obj.setTypeId(alertNotificationLocMsg.getTypeId());
				obj.setWorkingLocationID(i);

				alertNotificationLocMsgs.add(obj);
			}

		} else {
			alertNotificationLocMsgs.add(alertNotificationLocMsg);
		}

		return (List<AlertNotificationLocMsg>) alertNotifyLocMsgRepo.saveAll(alertNotificationLocMsgs);

	}

	public List<AlertNotificationLocMsg> getAlertNotificationLocMsgs() {
		return alertNotifyLocMsgRepo.findByDeleted(false);
	}

	public AlertNotificationLocMsg getAlertNotificationLocMsgById(Long id) {
		Optional<AlertNotificationLocMsg> resultSet = alertNotifyLocMsgRepo.findById(id);
		if (resultSet.isPresent())
			return resultSet.get();
		else
			throw new ECDException("No such element found");
	}

	public List<AlertNotificationLocMsg> getAlertNotificationLocMsgByPSMId(Integer psmId) {
		return alertNotifyLocMsgRepo.findByPsmId(psmId);
	}

	public List<AlertNotificationLocMsg> getAlertNotificationLocMsgByRoleIdAndPSMId(Integer roleId, Integer psmId) {
		return alertNotifyLocMsgRepo.findByRoleIdAndPsmId(roleId, psmId);
	}

	public List<AlertNotificationLocMsg> updateAlertNotificationLocMsg(
			List<AlertNotificationLocMsg> alertNotificationLocMsg) {

		return (List<AlertNotificationLocMsg>) alertNotifyLocMsgRepo.saveAll(alertNotificationLocMsg);

	}
}
