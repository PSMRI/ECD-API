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
package com.iemr.ecd.dto;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestBeneficiaryRegistrationDTO {

	private Long beneficiaryRegID;
	private Long motherId;
	private Long childId;
	private String name;
	private String motherName;
	private Timestamp dOB;
	private String dateOfBirth;
	private String spouseName;
	private Boolean emergencyRegistration;
	private Integer genderID;
	private String genderName;
	private String phoneNo;
	private String phoneNoOfWhom;
	private String alternatePhoneNo;
	private Timestamp lmp;
	private Timestamp edd;
	private String ashaName;
	private String ashaPh;
	private String anmName;
	private String anmPh;
	private String blockName;
	private String phcName;

	private RequestBeneficiaryDemographicsDTO i_bendemographics;
	private List<RequestBenPhoneMapsDTO> benPhoneMaps;
	private Boolean changeInSelfDetails;
	private Boolean changeInOtherDetails;
	private Boolean changeInAddress;
	private Boolean changeInContacts;
	private Boolean changeInFamilyDetails;
	private Integer vanID;
	private Integer providerServiceMapID;
	private String createdBy;
	private String modifiedBy;

	private String firstName;
	private String lastName;
}
