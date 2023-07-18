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

import java.math.BigInteger;
import java.sql.Timestamp;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class FetchChildOutboundWorklist {

	private Long beneficiaryRegId;
	private String outboundCallType;
	private String displayOBCallType;
	private Timestamp callDateFrom;
	private Integer noOfTrials;
	private String mCTSIdNoChildId;
	private String childName;
	private String motherId;
	private String motherName;
	private String PhoneNoOf;
	private String PhoneNo;
	private Long obCallId;

	private String address;
	private String healthBlock;
	private String phcName;
	private String subFacility;
	private String ashaName;
	private String anmName;
	private Timestamp dob;
	private Timestamp nextCallDate;
	private Boolean isHrni;
	private String lapseTime;
	private Timestamp recordUploadDate;

	// new fields / columns
	private String fatherName;
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
