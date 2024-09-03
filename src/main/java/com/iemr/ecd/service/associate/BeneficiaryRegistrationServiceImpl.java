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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import jakarta.ws.rs.core.MediaType;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.iemr.ecd.dto.RequestBeneficiaryRegistrationDTO;
import com.iemr.ecd.repo.call_conf_allocation.ChildRecordRepo;
import com.iemr.ecd.repo.call_conf_allocation.MotherRecordRepo;
import com.iemr.ecd.repo.call_conf_allocation.OutboundCallsRepo;
import com.iemr.ecd.utils.advice.exception_handler.ECDException;

import jakarta.transaction.Transactional;

@Service
public class BeneficiaryRegistrationServiceImpl {

	@Autowired
	private MotherRecordRepo motherRecordRepo;

	@Autowired
	private ChildRecordRepo childRecordRepo;

	@Autowired
	private OutboundCallsRepo outboundCallsRepo;

	@Value("${registerBeneficiaryUrl}")
	private String registerBeneficiaryUrl;

	@Value("${beneficiaryEditUrl}")
	private String beneficiaryEditUrl;
	ObjectMapper objectMapper = new ObjectMapper();

	@Transactional(rollbackOn = Exception.class)
	public String beneficiaryRegistration(RequestBeneficiaryRegistrationDTO request, String Authorization) {
		Long beneficiaryRegID = null;
		Long beneficiaryID = null;
		Map<String, String> resMap = new HashMap<>();

		try {

			if (request != null && request.getName() != null) {
				String[] nameArray = request.getName().split(" ", -1);

				if (nameArray.length > 1) {
					request.setFirstName(nameArray[0]);
					request.setLastName(nameArray[1]);
				} else
					request.setFirstName(nameArray[0]);
			}

			if (request.getDateOfBirth() != null) {
				Timestamp dob = getTimestampFromString(request.getDateOfBirth());
				request.setDOB(dob);
			}

//			if (request.getLmpStr() != null)
//				request.setLmp(getTimestampFromString(request.getLmpStr()));
//
//			if (request.getEddStr() != null)
//				request.setEdd(getTimestampFromString(request.getEddStr()));

			RestTemplate restTemplate = new RestTemplate();
			MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
			headers.add("Content-Type", MediaType.APPLICATION_JSON + ";charset=utf-8");
			headers.add("AUTHORIZATION", Authorization);
			String json = objectMapper.writeValueAsString(request);
			
			HttpEntity<Object> requestObj = new HttpEntity<Object>(json, headers);

			ResponseEntity<String> response = restTemplate.exchange(registerBeneficiaryUrl, HttpMethod.POST, requestObj,
					String.class);

			if (response.getStatusCode() == HttpStatus.OK && response.hasBody()) {

				String responseStr = response.getBody();
				JSONObject responseOBJ = new JSONObject(responseStr);
				beneficiaryRegID = responseOBJ.getJSONObject("data").getLong("beneficiaryRegID");
				beneficiaryID = responseOBJ.getJSONObject("data").getLong("beneficiaryID");

				if (request.getChildId() == null) {
					motherRecordRepo.updateBeneficiaryRegIdForMother(beneficiaryRegID,

							request.getMotherId(), request.getName(), request.getSpouseName(),
							request.getPhoneNoOfWhom(), request.getPhoneNo(), request.getLmp(), request.getEdd(),
							request.getI_bendemographics().getAddressLine1(), request.getAshaName(),
							request.getAshaPh(), request.getAnmName(), request.getAnmPh(), request.getPhcName(),
							request.getBlockName(), request.getAge());
					outboundCallsRepo.updateBeneficiaryRegIdForMother(beneficiaryRegID, request.getMotherId(),
							request.getPhoneNoOfWhom());
				} else {
					childRecordRepo.updateBeneficiaryRegIdForChild(beneficiaryRegID,

							request.getChildId(), request.getName(), request.getSpouseName(),
							request.getPhoneNoOfWhom(), request.getPhoneNo(), request.getDOB(), request.getMotherName(),
							request.getGenderName(), request.getI_bendemographics().getAddressLine1(),
							request.getAshaName(), request.getAshaPh(), request.getAnmName(), request.getAnmPh(),
							request.getPhcName(), request.getBlockName());
					outboundCallsRepo.updateBeneficiaryRegIdForChild(beneficiaryRegID, request.getChildId(),
							request.getPhoneNoOfWhom());
				}

				resMap.put("BeneficiaryId", beneficiaryID.toString());
				resMap.put("BenRegId", beneficiaryRegID.toString());

			} else
				throw new ECDException("error while registration in common service" + response.toString());

			Map<String, Object> responseMap = new HashMap<>();
			responseMap.put("response", resMap);
			return new Gson().toJson(responseMap);

		} catch (Exception e) {
			throw new ECDException(e);
		}

	}

	@Transactional(rollbackOn = Exception.class)
	public String updateBeneficiaryDetails(RequestBeneficiaryRegistrationDTO request, String Authorization) {
		try {
			if (request != null && request.getName() != null) {
				String[] nameArray = request.getName().split(" ", -1);

				if (nameArray.length > 1) {
					request.setFirstName(nameArray[0]);
					request.setLastName(nameArray[1]);
				} else
					request.setFirstName(nameArray[0]);
			}

			if (request.getDateOfBirth() != null) {
				Timestamp dob = getTimestampFromString(request.getDateOfBirth());
				request.setDOB(dob);
			}

			RestTemplate restTemplate = new RestTemplate();
			MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
			headers.add("Content-Type", MediaType.APPLICATION_JSON + ";charset=utf-8");
			headers.add("AUTHORIZATION", Authorization);
			HttpEntity<Object> requestObj = new HttpEntity<Object>(request, headers);
			ResponseEntity<String> response = restTemplate.exchange(beneficiaryEditUrl, HttpMethod.POST, requestObj,
					String.class);

			if (response.getStatusCode() == HttpStatus.OK && response.hasBody()) {

				if (request.getChildId() == null) {

					motherRecordRepo.updateBeneficiaryDetails(request.getBeneficiaryRegID(), request.getMotherId(),
							request.getName(), request.getSpouseName(), request.getPhoneNoOfWhom(),
							request.getPhoneNo(), request.getLmp(), request.getEdd(),
							request.getI_bendemographics().getAddressLine1(), request.getAshaName(),
							request.getAshaPh(), request.getAnmName(), request.getAnmPh(), request.getPhcName(),
							request.getBlockName(), request.getAge());

					outboundCallsRepo.updatePhoneNoTypeForMother(request.getBeneficiaryRegID(), request.getMotherId(),
							request.getPhoneNoOfWhom());

				} else {
					childRecordRepo.updateBeneficiaryDetails(request.getBeneficiaryRegID(), request.getChildId(),
							request.getName(), request.getSpouseName(), request.getPhoneNoOfWhom(),
							request.getPhoneNo(), request.getDOB(), request.getMotherName(), request.getGenderName(),
							request.getI_bendemographics().getAddressLine1(), request.getAshaName(),
							request.getAshaPh(), request.getAnmName(), request.getAnmPh(), request.getPhcName(),
							request.getBlockName());

					outboundCallsRepo.updatePhoneNoTypeForChild(request.getBeneficiaryRegID(), request.getChildId(),
							request.getPhoneNoOfWhom());

				}

			} else
				throw new ECDException("error while beneficiary update in common service" + response.toString());

			Map<String, Object> responseMap = new HashMap<>();
			responseMap.put("response", "Beneficiary Updated Successfully");
			return new Gson().toJson(responseMap);
		} catch (Exception e) {
			throw new ECDException(e);
		}
	}

	private Timestamp getTimestampFromString(String date) throws Exception {

		DateTimeFormatter ISO_DATE_TIME = DateTimeFormatter.ISO_DATE_TIME;
		LocalDateTime localDateTime = LocalDateTime.from(ISO_DATE_TIME.parse(date));

		Timestamp timestamp = Timestamp.valueOf(localDateTime);

		return timestamp;

	}

}
