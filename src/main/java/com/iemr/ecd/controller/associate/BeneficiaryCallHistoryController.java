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
package com.iemr.ecd.controller.associate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iemr.ecd.dao.associate.Bencall;
import com.iemr.ecd.dto.RequestBeneficiaryQuestionnaireResponseDTO;
import com.iemr.ecd.service.associate.BeneficiaryCallHistoryImpl;
import com.iemr.ecd.utils.advice.exception_handler.CustomExceptionResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping(value = "/callHistory",headers = "Authorization")
@CrossOrigin()
public class BeneficiaryCallHistoryController {
	@Autowired
	private BeneficiaryCallHistoryImpl beneficiaryCallHistoryImpl;

	@GetMapping(value = "/getBeneficiaryCallHistory", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Fetch call history data for beneficiary", description = "Desc - Fetch call history data for beneficiary")
	@ApiResponses(value = {
			@ApiResponse(responseCode = CustomExceptionResponse.SUCCESS_SC_V, description = CustomExceptionResponse.SUCCESS_SC, content = {
					@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = CustomExceptionResponse.NOT_FOUND_SC_V, description = CustomExceptionResponse.NOT_FOUND_SC),
			@ApiResponse(responseCode = CustomExceptionResponse.INTERNAL_SERVER_ERROR_SC_V, description = CustomExceptionResponse.INTERNAL_SERVER_ERROR_SC),
			@ApiResponse(responseCode = CustomExceptionResponse.DB_EXCEPTION_SC_V, description = CustomExceptionResponse.DB_EXCEPTION_SC),
			@ApiResponse(responseCode = CustomExceptionResponse.BAD_REQUEST_SC_V, description = CustomExceptionResponse.BAD_REQUEST_SC)})
	public ResponseEntity<Object> getBeneficiaryCallHistory(
			@RequestParam(value = "motherId", required = false) Long motherId,
			@RequestParam(value = "childId", required = false) Long childId) {
		return new ResponseEntity<>(beneficiaryCallHistoryImpl.getBeneficiaryCallHistory(motherId, childId),
				HttpStatus.OK);
	}

	@GetMapping(value = "/getBeneficiaryCallDetails/{obCallId}", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Fetch call details for Beneficiary", description = "Desc - Fetch call details for Beneficiary")
	@ApiResponses(value = {
			@ApiResponse(responseCode = CustomExceptionResponse.SUCCESS_SC_V, description = CustomExceptionResponse.SUCCESS_SC, content = {
					@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = CustomExceptionResponse.NOT_FOUND_SC_V, description = CustomExceptionResponse.NOT_FOUND_SC),
			@ApiResponse(responseCode = CustomExceptionResponse.INTERNAL_SERVER_ERROR_SC_V, description = CustomExceptionResponse.INTERNAL_SERVER_ERROR_SC),
			@ApiResponse(responseCode = CustomExceptionResponse.DB_EXCEPTION_SC_V, description = CustomExceptionResponse.DB_EXCEPTION_SC),
			@ApiResponse(responseCode = CustomExceptionResponse.BAD_REQUEST_SC_V, description = CustomExceptionResponse.BAD_REQUEST_SC)})
	public ResponseEntity<Object> getBeneficiaryCallDetails(@PathVariable Long obCallId) {
		return new ResponseEntity<>(beneficiaryCallHistoryImpl.getBeneficiaryCallDetails(obCallId), HttpStatus.OK);
	}

	@PostMapping(value = "/saveBenCallDetails", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Saving Beneficiary call details", description = "Desc - Saving Beneficiary call details")
	@ApiResponses(value = {
			@ApiResponse(responseCode = CustomExceptionResponse.SUCCESS_SC_V, description = CustomExceptionResponse.SUCCESS_SC, content = {
					@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = CustomExceptionResponse.NOT_FOUND_SC_V, description = CustomExceptionResponse.NOT_FOUND_SC),
			@ApiResponse(responseCode = CustomExceptionResponse.INTERNAL_SERVER_ERROR_SC_V, description = CustomExceptionResponse.INTERNAL_SERVER_ERROR_SC),
			@ApiResponse(responseCode = CustomExceptionResponse.DB_EXCEPTION_SC_V, description = CustomExceptionResponse.DB_EXCEPTION_SC),
			@ApiResponse(responseCode = CustomExceptionResponse.BAD_REQUEST_SC_V, description = CustomExceptionResponse.BAD_REQUEST_SC)})
	public ResponseEntity<String> saveBenCallDetails(@RequestBody Bencall bencall) {

		return new ResponseEntity<>(beneficiaryCallHistoryImpl.saveBenCallDetails(bencall), HttpStatus.OK);
	}

	@PostMapping(value = "/create/beneficiaryQuestionnaireResponse", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Saving questionnaire responses of Beneficiary", description = "Desc - Saving questionnaire responses of Beneficiary")
	@ApiResponses(value = {
			@ApiResponse(responseCode = CustomExceptionResponse.SUCCESS_SC_V, description = CustomExceptionResponse.SUCCESS_SC, content = {
					@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = CustomExceptionResponse.NOT_FOUND_SC_V, description = CustomExceptionResponse.NOT_FOUND_SC),
			@ApiResponse(responseCode = CustomExceptionResponse.INTERNAL_SERVER_ERROR_SC_V, description = CustomExceptionResponse.INTERNAL_SERVER_ERROR_SC),
			@ApiResponse(responseCode = CustomExceptionResponse.DB_EXCEPTION_SC_V, description = CustomExceptionResponse.DB_EXCEPTION_SC),
			@ApiResponse(responseCode = CustomExceptionResponse.BAD_REQUEST_SC_V, description = CustomExceptionResponse.BAD_REQUEST_SC)})
	public ResponseEntity<String> saveBeneficiaryQuestionnaireResponse(
			@RequestBody RequestBeneficiaryQuestionnaireResponseDTO requestBeneficiaryQuestionnaireResponseDTO) {

		return new ResponseEntity<>(beneficiaryCallHistoryImpl
				.saveBeneficiaryQuestionnaireResponse(requestBeneficiaryQuestionnaireResponseDTO), HttpStatus.OK);
	}

	@GetMapping(value = "/getHrpHrniDetails", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Fetch HRP details for mother and HRNI details for child", description = "Desc - Fetch HRP details for mother and HRNI details for child")
	@ApiResponses(value = {
			@ApiResponse(responseCode = CustomExceptionResponse.SUCCESS_SC_V, description = CustomExceptionResponse.SUCCESS_SC, content = {
					@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = CustomExceptionResponse.NOT_FOUND_SC_V, description = CustomExceptionResponse.NOT_FOUND_SC),
			@ApiResponse(responseCode = CustomExceptionResponse.INTERNAL_SERVER_ERROR_SC_V, description = CustomExceptionResponse.INTERNAL_SERVER_ERROR_SC),
			@ApiResponse(responseCode = CustomExceptionResponse.DB_EXCEPTION_SC_V, description = CustomExceptionResponse.DB_EXCEPTION_SC),
			@ApiResponse(responseCode = CustomExceptionResponse.BAD_REQUEST_SC_V, description = CustomExceptionResponse.BAD_REQUEST_SC)})
	public ResponseEntity<Object> getHrpHrniDetails(@RequestParam(value = "motherId", required = false) Long motherId,
			@RequestParam(value = "childId", required = false) Long childId) {
		return new ResponseEntity<>(beneficiaryCallHistoryImpl.getHrpHrniDetails(motherId, childId), HttpStatus.OK);
	}

}
