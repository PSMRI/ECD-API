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

import com.iemr.ecd.dao.QuestionnaireSections;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "t_mctscallresponse")
@Entity
public class ECDCallResponse {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MctsCallResponseID", insertable = false)
	private Long ecdCallResponseId;

	@Column(name = "MotherID")
	private Long motherId;

	@Column(name = "ChildID")
	private Long childId;

	@Column(name = "CallDetailID")
	private Long callDetailID;

	@Column(name = "BenCallID")
	private Long benCallId;

	@Column(name = "obcallid")
	private Long obCallId;

	@Column(name = "outboundCallType")
	private String ecdCallType;

	@Column(name = "QuestionID")
	private Integer questionId;

	@Column(name = "SectionID")
	private Integer sectionId;

	@Column(name = "Question")
	private String question;

	@Column(name = "Answer")
	private String answer;

	@Column(name = "ReasonsforHrni")
	private String reasonsForHrniDB;

	@Column(name = "OtherHrni")
	private String otherHrni;

	@Column(name = "CongentialAnomalies")
	private String congentialAnomaliesDB;

	@Column(name = "OtherCongentialAnomalies")
	private String otherCongentialAnomalies;

	@Column(name = "ProbableCauseOfDefect")
	private String probableCauseOfDefect;

	@Column(name = "ReasonsForHrp")
	private String reasonsForHrpDB;

	@Column(name = "OtherHrpReason")
	private String otherHrpReason;

	@Column(name = "Remarks")
	private String remarks;
	
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
	@Transient
	private String[] reasonsForHrp;
	@Transient
	private String[] reasonsForHrni;
	@Transient
	private String[] congentialAnomalies;
	

}
