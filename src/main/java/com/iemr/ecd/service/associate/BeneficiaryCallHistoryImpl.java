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
package com.iemr.ecd.service.associate;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.iemr.ecd.dao.associate.Bencall;
import com.iemr.ecd.dao.associate.ECDCallResponse;
import com.iemr.ecd.dao.associate.OutboundCalls;
import com.iemr.ecd.dao_temp.Pr_GetCallHistory;
import com.iemr.ecd.dto.RequestBeneficiaryQuestionnaireResponseDTO;
import com.iemr.ecd.repo.call_conf_allocation.OutboundCallsRepo;
import com.iemr.ecd.repository.ecd.ECDCallResponseRepo;
import com.iemr.ecd.repository.quality.T_benCallRepo;
import com.iemr.ecd.utils.advice.exception_handler.ECDException;
import com.iemr.ecd.utils.advice.exception_handler.InvalidRequestException;

import jakarta.transaction.Transactional;

@Service
public class BeneficiaryCallHistoryImpl {
	@Autowired
	private OutboundCallsRepo outboundCallsRepo;
	@Autowired
	private T_benCallRepo t_benCallRepo;
	@Autowired
	private ECDCallResponseRepo ecdCallResponseRepo;

	public List<OutboundCalls> getBeneficiaryCallHistory(Long motherId, Long childId) {
		List<OutboundCalls> callHistory = new ArrayList<>();
		try {
			if (motherId != null) {
				callHistory = outboundCallsRepo.getCallHistoryForMother(motherId);
			} else if (childId != null) {
				callHistory = outboundCallsRepo.getCallHistoryForChild(childId);
			}
			return callHistory;
		} catch (Exception e) {
			throw new ECDException(e);
		}
	}

	public Pr_GetCallHistory getBeneficiaryCallDetails(Long obCallId) {
		try {
			List<String[]> benCallDetails = outboundCallsRepo.getBeneficiaryCallDetails(obCallId);
			return getBenCallDetails(benCallDetails);
		} catch (Exception e) {
			throw new ECDException(e);
		}
	}

	private Pr_GetCallHistory getBenCallDetails(List<String[]> benCallData) {
		try {
			Pr_GetCallHistory obj = new Pr_GetCallHistory();
			if (benCallData != null && benCallData.size() > 0) {
				for (String[] strArr : benCallData) {
					if (strArr[0] != null)
						obj.setCallId(strArr[0]);
					if (strArr[1] != null)
						obj.setMotherId(Long.valueOf(strArr[1]));
					if (strArr[2] != null)
						obj.setMotherName(strArr[2]);
					if (strArr[3] != null)
						obj.setChildId(Long.valueOf(strArr[3]));
					if (strArr[4] != null)
						obj.setChildName(strArr[4]);
					if (strArr[5] != null)
						obj.setPhoneNo(strArr[5]);
					if (strArr[6] != null)
						obj.setAshaName(strArr[6]);
					if (strArr[7] != null)
						obj.setRole(strArr[7]);
					if (strArr[8] != null)
						obj.setLmpDate(Timestamp.valueOf(strArr[8]));
					if (strArr[9] != null)
						obj.setDob(Timestamp.valueOf(strArr[9]));
					if (strArr[10] != null)
						obj.setCallTime(Timestamp.valueOf(strArr[10]));
					if (strArr[11] != null)
						obj.setEcdCallType(strArr[11]);
					if (strArr[12] != null)
						obj.setTypeOfComplaint(strArr[12]);
					if (strArr[13] != null)
						obj.setComplaintRemarks(strArr[13]);
					if (strArr[14] != null)
						obj.setAdviseProvided(strArr[14]);

				}
			}
			return obj;
		} catch (Exception e) {
			throw new ECDException(e);
		}
	}

	@Transactional(rollbackOn = Exception.class)
	public String saveBenCallDetails(Bencall bencall) throws ECDException {
		Map<String, String> resMap = new HashMap<>();
		try {
			bencall.setCallTime(Timestamp.from(Instant.now()));
			if(bencall.getCallTypeId() == null)
				bencall.setCallTypeId(9999);
			Bencall callDetails = t_benCallRepo.save(bencall);
			resMap.put("benCallId", callDetails.getBenCallId().toString());

			Map<String, Object> responseMap = new HashMap<>();
			responseMap.put("response", resMap);
			return new Gson().toJson(responseMap);
		} catch (Exception e) {
			throw new ECDException(e);
		}

	}

	@Transactional(rollbackOn = Exception.class)
	public String saveBeneficiaryQuestionnaireResponse(
			RequestBeneficiaryQuestionnaireResponseDTO requestBeneficiaryQuestionnaireResponseDTO) {
		try {
			if (requestBeneficiaryQuestionnaireResponseDTO != null
					&& requestBeneficiaryQuestionnaireResponseDTO.getQuestionnaireResponse() != null
					&& requestBeneficiaryQuestionnaireResponseDTO.getQuestionnaireResponse().size() > 0) {
				List<ECDCallResponse> responseList = requestBeneficiaryQuestionnaireResponseDTO
						.getQuestionnaireResponse();
				for (ECDCallResponse ecdCallResponse : responseList) {
					ecdCallResponse.setObCallId(requestBeneficiaryQuestionnaireResponseDTO.getObCallId());
					if (requestBeneficiaryQuestionnaireResponseDTO.getMotherId() != null)
						ecdCallResponse.setMotherId(requestBeneficiaryQuestionnaireResponseDTO.getMotherId());
					if (requestBeneficiaryQuestionnaireResponseDTO.getChildId() != null)
						ecdCallResponse.setChildId(requestBeneficiaryQuestionnaireResponseDTO.getChildId());
					ecdCallResponse.setEcdCallType(requestBeneficiaryQuestionnaireResponseDTO.getEcdCallType());
					ecdCallResponse.setPsmId(requestBeneficiaryQuestionnaireResponseDTO.getPsmId());
					ecdCallResponse.setCreatedBy(requestBeneficiaryQuestionnaireResponseDTO.getCreatedBy());
					ecdCallResponse.setBenCallId(requestBeneficiaryQuestionnaireResponseDTO.getBenCallId());

				}
				ecdCallResponseRepo.saveAll(responseList);
				String callResponseList = ecdCallResponseRepo.getEcdCallResponseList(
						requestBeneficiaryQuestionnaireResponseDTO.getBenCallId(),
						requestBeneficiaryQuestionnaireResponseDTO.getObCallId());

				if (!callResponseList.equals("Updation has done Successfully"))
					throw new ECDException("SP is not updated");

			} else
				throw new InvalidRequestException("NULL/Invalid questions", "pass valid question-answer");

			Map<String, Object> responseMap = new HashMap<>();
			responseMap.put("response", "Questionnaire response saved successfully");
			return new Gson().toJson(responseMap);

		} catch (Exception e) {
			throw new ECDException(e.getLocalizedMessage());
		}

	}

	public ECDCallResponse getHrpHrniDetails(Long motherId, Long childId) {
		ECDCallResponse obj = new ECDCallResponse();

		try {
			Pageable pageable = PageRequest.of(0, 1);

			if (childId != null) {
				Page<ECDCallResponse> page = ecdCallResponseRepo.getHrniDetailsChild(pageable, childId);
				if (page.getSize() > 0) {
					List<ECDCallResponse> objLIst = page.getContent();
					obj = objLIst.get(0);

				}
			} else if (motherId != null) {
				Page<ECDCallResponse> page = ecdCallResponseRepo.getHrpDetailsMother(pageable, motherId);
				if (page.getSize() > 0) {
					List<ECDCallResponse> objLIst = page.getContent();
					obj = objLIst.get(0);

				}
			} else
				throw new InvalidRequestException("NULL motherId/childId", "send valid motherId or childId");

			return obj;
		} catch (Exception e) {
			throw new ECDException(e.getLocalizedMessage());
		}
	}

}
