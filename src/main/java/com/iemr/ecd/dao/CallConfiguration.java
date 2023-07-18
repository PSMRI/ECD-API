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
package com.iemr.ecd.dao;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "m_mctscallconfiguration")
@Entity
public class CallConfiguration {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MCTSCallConfigID", insertable = false)
	private Long callConfigId;

	@Column(name = "OutboundCallType")
	private String callType;

	@Column(name = "DisplayOBCallType")
	private String displayName;

	@Column(name = "BaseLine")
	private String baseLine;

	@Column(name = "TermRange")
	private Integer termRange;

	@Column(name = "EffectiveFrom")
	private Timestamp effectiveStartDate;

	@Column(name = "EffectiveUpto")
	private Timestamp effectiveEndDate;

	@Column(name = "ConfigTerms")
	private String configTerms;

	@Column(name = "NoOfAttempts")
	private Integer noOfAttempts;

	@Column(name = "NextAttemptPeriod")
	private Integer nextAttemptPeriod;

	@Column(name = "ProviderServiceMapID")
	private Integer psmId;

	@Column(name = "Deleted", insertable = false)
	private Boolean deleted;

	@Column(name = "CreatedBy")
	private String createdBy;

	@Column(name = "CreatedDate", insertable = false, updatable = false)
	private Timestamp createdDate;

	@Column(name = "ModifiedBy")
	private String modifiedBy;

	@Column(name = "LastModDate", insertable = false, updatable = false)
	private Timestamp lastModDate;

	@Column(name = "ConfigId")
	private Long configId;

}
