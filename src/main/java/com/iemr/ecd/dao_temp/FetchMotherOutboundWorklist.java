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

import java.sql.Timestamp;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class FetchMotherOutboundWorklist {

	private Long obCallId;
	private Long beneficiaryRegId;
	private Integer allocatedUserId;
	private Integer providerServiceMapId;
	private String outboundCallType;
	private Timestamp callDateFrom;
	private Integer noOfTrials;
	private String displayOBCallType;
	private String name;
	private String mCTSIdNo;
	private String phoneNoOfWhom;
	private String whomPhoneNo;
	private Boolean highRisk;

	private String husbandName;
	private String address;
	private String healthBlock;
	private String phcName;
	private String subFacility;
	private Timestamp lmpDate;
	private String ashaName;
	private String anmName;
	private Timestamp nextCallDate;
	private String lapseTime;
	private Timestamp recordUploadDate;

	private Timestamp edd;

	// new columns
	private String gender;
	private Integer stateId;
	private String stateName;
	private Integer districtId;
	private String districtName;
	private Integer blockId;

	private String blockName;
	private Integer districtBranchId;
	private String villageName;
	private String alternatePhoneNo;
	private String ashaPhoneNo;
	private String anmPhoneNo;

}
