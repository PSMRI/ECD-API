package com.iemr.ecd.dto;

import java.sql.Timestamp;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QualityAuditorWorklistDatewiseRequestDTO {

	private Timestamp validFrom;
	private Timestamp validTill;
	private Integer psmId;
	private Integer roleId;
	private Integer agentId;
	private String beneficiaryPhoneNumber;
	private String languageId;
	private boolean isValid;
	
	//private Integer year;
	//private Integer month;

	//private Timestamp fromDate;
	//private Timestamp toDate;
	//private String fDate;
	//private String tDate;
	}
	

