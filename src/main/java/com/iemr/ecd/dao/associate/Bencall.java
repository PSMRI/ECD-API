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
package com.iemr.ecd.dao.associate;

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
@Table(name = "t_bencall")
@Entity
public class Bencall {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "BenCallID", insertable = false)
	private Long benCallId;

	@Column(name = "BeneficiaryRegID")
	private Long beneficiaryRegId;

	@Column(name = "CallID")
	private String callId;

	@Column(name = "SessionID")
	private String sessionId;

	@Column(name = "PhoneNo")
	private String phoneNo;

	@Column(name = "CalledServiceID")
	private Integer calledServiceId;

	@Column(name = "CallTypeID")
	private Integer callTypeId;

	@Column(name = "CallTime")
	private Timestamp callTime;

	@Column(name = "Remarks")
	private String callRemarks;

	@Column(name = "ServicesProvided")
	private String servicesProvided;

	@Column(name = "CallClosureType")
	private String callClosureType;

	@Column(name = "DispositionStatusID")
	private Integer dispositionStatusId;

	@Column(name = "CallReceivedUserID")
	private Integer callReceivedUserId;

	@Column(name = "ReceivedRoleName")
	private String receivedRoleName;

	@Column(name = "ReceivedAgentID")
	private String agentId;

	@Column(name = "CallEndUserID")
	private Integer callEndUserId;

	@Column(name = "CallEndTime")
	private Timestamp callEndTime;

	@Column(name = "CallDuration")
	private String callDuration;

	@Column(name = "Category")
	private String category;

	@Column(name = "SubCategory")
	private String subCategory;

	@Column(name = "CDICallStatus")
	private String cdiCallStatus;

	@Column(name = "IsOutbound")
	private Boolean isOutbound;

	@Column(name = "IsCalledEarlier")
	private Boolean isCalledEarlier;

	@Column(name = "RecordingFilePath")
	private String recordingFilePath;

	@Column(name = "ArchiveFilePath")
	private String archieveFilePath;

	@Column(name = "OBCallID")
	private Long obCallId;

	@Column(name = "IsMother")
	private Boolean isMother;

	@Column(name = "SMS_Advice")
	private String smsAdvice;

	@Column(name = "SMS_Ph")
	private String smsPhone;

	@Column(name = "IsVerified")
	private Boolean isCallVerified;

	@Column(name = "isCallAnswered")
	private Boolean isCallAnswered;

	@Column(name = "isCallDisconnected")
	private Boolean isCallDisconnected;

	@Column(name = "isFurtherCallRequired")
	private Boolean isFurtherCallRequired;

	@Column(name = "TypeOfComplaint")
	private String typeOfComplaint;

	@Column(name = "complaintRemarks")
	private String complaintRemarks;

	@Column(name = "reasonForNoFurtherCalls")
	private String reasonForNoFurtherCalls;

	@Column(name = "reasonForCallNotAnswered")
	private String reasonForCallNotAnswered;

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

	@Column(name = "CZcallStartTime")
	private Timestamp czCallStartTime;

	@Column(name = "CZcallEndTime")
	private Timestamp czCallEndTime;

	@Column(name = "CZcallDuration")
	private Integer czCallDuration;

	@Column(name = "isCallAudited")
	private Boolean isCallAudited;
	
	@Column(name = "isWrongNumber")
	private Boolean isWrongNumber;
	
	@Column(name = "isZeroCall")
	private Boolean isZeroCall;

}
