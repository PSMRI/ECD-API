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
package com.iemr.ecd.dao_temp;

import java.sql.Date;
import java.sql.Timestamp;

import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "m_notification")
public class Alerts {

	@Column(name = "NotificationID")
	private Long alertId;

	@Column(name = "ValidFrom")
	private Timestamp validFrom;

	@Column(name = "ValidTill")
	private Timestamp validTo;

	@Column(name = "Notification")
	private String subject;

	@Column(name = "NotificationDesc")
	private String message;

//	private String cluster;

	@Column(name = "ProviderServiceMapID")
	private Integer psmId;

	private Integer[] workLocationId;

	@Column(name = "RoleID")
	private Integer roleId;

	@Column(name = "Deleted")
	private Boolean deleted;

	@Column(name = "CreatedBy")
	private String createdBy;

	@Column(name = "CreatedDate")
	private Timestamp createdDate;

	@Column(name = "ModifiedBy")
	private String modifiedBy;

	@Column(name = "LastModDate")
	private Date lastModDate;

}
