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
@Table(name = "V_get_Qualityaudit_Sectionquestionairevalues")
@Entity
public class V_get_Qualityaudit_SectionQuestionaireValues {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", insertable = false)
	private String id;

	@Column(name = "sectionId")
	private Integer sectionId;

	@Column(name = "sectionName")
	private String sectionName;

	@Column(name = "SectionRank")
	private Integer sectionRank;

	@Column(name = "questionId")
	private Integer questionId;

	@Column(name = "Question")
	private String question;

	@Column(name = "QuestionRank")
	private Integer questionRank;

	@Column(name = "optionId")
	private Integer optionId;

	@Column(name = "QuestionValues")
	private String questionValues;

	@Column(name = "Score")
	private Integer score;

	@Column(name = "ProviderServiceMapID")
	private Integer psmId;

}
