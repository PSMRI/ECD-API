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
package com.iemr.ecd.controller.alerts_notifications_locmsg;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iemr.ecd.dao.AlertNotificationLocMsg;
import com.iemr.ecd.service.call_conf_allocation.AlertNotifyLocMsgImpl;

import jakarta.validation.Valid;

/***
 * 
 * @author NE298657
 *
 */

@RestController
@RequestMapping(value = "/alert-notification-locMsg", headers = "Authorization")
@CrossOrigin()
public class AlertNotifyLocMsgController {

	@Autowired
	private AlertNotifyLocMsgImpl alertNotifyLocMsgImpl;

	@Deprecated
	@PostMapping(value = "/create")
	public ResponseEntity<List<AlertNotificationLocMsg>> createNotifications(
			@RequestBody AlertNotificationLocMsg notification) {

		return new ResponseEntity<>(alertNotifyLocMsgImpl.createAlertNotificationLocMsg(notification), HttpStatus.OK);

	}

	@Deprecated
	@GetMapping("/get")
	public ResponseEntity<List<AlertNotificationLocMsg>> getNotifications() {
		return new ResponseEntity<>(alertNotifyLocMsgImpl.getAlertNotificationLocMsgs(), HttpStatus.OK);
	}

	@Deprecated
	@GetMapping("/getById/{id}")
	public ResponseEntity<AlertNotificationLocMsg> getNotificationById(@PathVariable Long id) {
		return new ResponseEntity<>(alertNotifyLocMsgImpl.getAlertNotificationLocMsgById(id), HttpStatus.OK);

	}

	@Deprecated
	@GetMapping("/getByPSMId/{psmId}")
	public ResponseEntity<List<AlertNotificationLocMsg>> getNotificationByPSMId(@PathVariable Integer psmId) {
		return new ResponseEntity<>(alertNotifyLocMsgImpl.getAlertNotificationLocMsgByPSMId(psmId), HttpStatus.OK);
	}

	@Deprecated
	@GetMapping("/getByPSMId/{roleId}/{psmId}")
	public ResponseEntity<List<AlertNotificationLocMsg>> getNotificationByPSMId(@PathVariable Integer roleId,
			@PathVariable Integer psmId) {
		return new ResponseEntity<>(alertNotifyLocMsgImpl.getAlertNotificationLocMsgByRoleIdAndPSMId(roleId, psmId),
				HttpStatus.OK);
	}

	@Deprecated
	@PutMapping("/update")
	public ResponseEntity<List<AlertNotificationLocMsg>> updateNotifications(
			@RequestBody List<AlertNotificationLocMsg> notifications) {
		return new ResponseEntity<>(alertNotifyLocMsgImpl.updateAlertNotificationLocMsg(notifications), HttpStatus.OK);
	}

	@Deprecated
	@PostMapping(value = "/publish")
	public ResponseEntity<List<AlertNotificationLocMsg>> publishNotifications(
			@Valid @RequestBody List<AlertNotificationLocMsg> notificationList) {
		return null;
	}

}
