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

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class RequestSaveCallQualityRatingsDTO {

	

	private Integer psmId;
	private Integer qualityAuditorId;
	private Long benCallId;
	private Integer sectionId;
	private String sectionName;
	private Integer questionId;
	private String questionName;
	private Integer agentId;
	private String agentName;
	private String answer;
	private Integer score;
	private Integer finalScore;
	private String finalGrade;
	private String callRemarks;
	private String outboundCallType;
	private String createdBy;

}
