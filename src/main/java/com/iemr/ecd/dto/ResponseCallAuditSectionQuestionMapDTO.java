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

import java.sql.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class ResponseCallAuditSectionQuestionMapDTO {

	private Integer sectionId;
	private String sectionName;
	private Integer sectionRank;
	private Integer questionId;
	private String question;
	private Integer questionRank;
	private Integer psmId;

	private List<QualityQuestionareOptionsDTO> options;

	private boolean deleted;
	private String createdBy;
	private Date createdDate;
	private String modifiedBy;
	private Date lastModDate;

}
