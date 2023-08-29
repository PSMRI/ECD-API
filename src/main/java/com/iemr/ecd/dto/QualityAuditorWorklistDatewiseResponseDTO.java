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
public class QualityAuditorWorklistDatewiseResponseDTO {

	private Long beneficiaryid;
	private String beneficiaryname;
	private String phoneNo;
	private Integer agentid;
	private String agetname;

	private String calltype;
	private Long benCallID;
	private Boolean isCallAudited;
	private String outboundCallType;
	private Integer roleID;

	private String roleName;
	private String callId;
}
