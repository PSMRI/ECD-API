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
package com.iemr.ecd.controller.call_allocation_configuration;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iemr.ecd.dao.CallConfiguration;
import com.iemr.ecd.dto.CallSectionMappingDTO;
import com.iemr.ecd.service.call_conf_allocation.CallConfigurationImpl;
import com.iemr.ecd.utils.advice.exception_handler.CustomExceptionResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

/***
 * 
 * @author NE298657
 *
 */

@RestController
@RequestMapping(value = "/callConfiguration", headers = "Authorization")
@CrossOrigin()
public class CallConfigurationController {

	@Autowired
	private CallConfigurationImpl callConfigurationImpl;

	@PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Create call configuration", description = "Desc - Create call configuration")
	@ApiResponses(value = {
			@ApiResponse(responseCode = CustomExceptionResponse.SUCCESS_SC_V, description = CustomExceptionResponse.SUCCESS_SC, content = {
					@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = CustomExceptionResponse.NOT_FOUND_SC_V, description = CustomExceptionResponse.NOT_FOUND_SC),
			@ApiResponse(responseCode = CustomExceptionResponse.INTERNAL_SERVER_ERROR_SC_V, description = CustomExceptionResponse.INTERNAL_SERVER_ERROR_SC),
			@ApiResponse(responseCode = CustomExceptionResponse.DB_EXCEPTION_SC_V, description = CustomExceptionResponse.DB_EXCEPTION_SC),
			@ApiResponse(responseCode = CustomExceptionResponse.BAD_REQUEST_SC_V, description = CustomExceptionResponse.BAD_REQUEST_SC) })
	public ResponseEntity<List<CallConfiguration>> createCallConfiguration(
			@RequestBody List<CallConfiguration> callConfigurations) {
		// add logic to create call configuration
		return new ResponseEntity<>(callConfigurationImpl.createCallConfigurations(callConfigurations), HttpStatus.OK);
	}

	@Deprecated
	@GetMapping(value = "/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Fetch call configuration", description = "Desc - Fetch call configuration")
	@ApiResponses(value = {
			@ApiResponse(responseCode = CustomExceptionResponse.SUCCESS_SC_V, description = CustomExceptionResponse.SUCCESS_SC, content = {
					@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = CustomExceptionResponse.NOT_FOUND_SC_V, description = CustomExceptionResponse.NOT_FOUND_SC),
			@ApiResponse(responseCode = CustomExceptionResponse.INTERNAL_SERVER_ERROR_SC_V, description = CustomExceptionResponse.INTERNAL_SERVER_ERROR_SC),
			@ApiResponse(responseCode = CustomExceptionResponse.DB_EXCEPTION_SC_V, description = CustomExceptionResponse.DB_EXCEPTION_SC),
			@ApiResponse(responseCode = CustomExceptionResponse.BAD_REQUEST_SC_V, description = CustomExceptionResponse.BAD_REQUEST_SC) })
	public ResponseEntity<List<CallConfiguration>> getAllCallConfigurations() {
		return new ResponseEntity<>(callConfigurationImpl.getCallConfigurations(), HttpStatus.OK);
	}

	@GetMapping(value = "/getById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Fetch call configuration by id", description = "Desc - Fetch call configuration by id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = CustomExceptionResponse.SUCCESS_SC_V, description = CustomExceptionResponse.SUCCESS_SC, content = {
					@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = CustomExceptionResponse.NOT_FOUND_SC_V, description = CustomExceptionResponse.NOT_FOUND_SC),
			@ApiResponse(responseCode = CustomExceptionResponse.INTERNAL_SERVER_ERROR_SC_V, description = CustomExceptionResponse.INTERNAL_SERVER_ERROR_SC),
			@ApiResponse(responseCode = CustomExceptionResponse.DB_EXCEPTION_SC_V, description = CustomExceptionResponse.DB_EXCEPTION_SC),
			@ApiResponse(responseCode = CustomExceptionResponse.BAD_REQUEST_SC_V, description = CustomExceptionResponse.BAD_REQUEST_SC) })
	public ResponseEntity<CallConfiguration> getCallConfigurationById(@PathVariable Long id) {
		return new ResponseEntity<>(callConfigurationImpl.getCallConfigurationById(id), HttpStatus.OK);

	}

	@GetMapping(value = "/getByPSMId/{psmId}", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Fetch call configuration by psmId", description = "Desc - Fetch call configuration by psmId")
	@ApiResponses(value = {
			@ApiResponse(responseCode = CustomExceptionResponse.SUCCESS_SC_V, description = CustomExceptionResponse.SUCCESS_SC, content = {
					@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = CustomExceptionResponse.NOT_FOUND_SC_V, description = CustomExceptionResponse.NOT_FOUND_SC),
			@ApiResponse(responseCode = CustomExceptionResponse.INTERNAL_SERVER_ERROR_SC_V, description = CustomExceptionResponse.INTERNAL_SERVER_ERROR_SC),
			@ApiResponse(responseCode = CustomExceptionResponse.DB_EXCEPTION_SC_V, description = CustomExceptionResponse.DB_EXCEPTION_SC),
			@ApiResponse(responseCode = CustomExceptionResponse.BAD_REQUEST_SC_V, description = CustomExceptionResponse.BAD_REQUEST_SC) })
	public ResponseEntity<List<CallConfiguration>> getCallConfigurationByPSMId(@PathVariable Integer psmId) {
		return new ResponseEntity<>(callConfigurationImpl.getCallConfigurationByPSMID(psmId), HttpStatus.OK);
	}

	@PutMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Update call configuration", description = "Desc - Update call configuration")
	@ApiResponses(value = {
			@ApiResponse(responseCode = CustomExceptionResponse.SUCCESS_SC_V, description = CustomExceptionResponse.SUCCESS_SC, content = {
					@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = CustomExceptionResponse.NOT_FOUND_SC_V, description = CustomExceptionResponse.NOT_FOUND_SC),
			@ApiResponse(responseCode = CustomExceptionResponse.INTERNAL_SERVER_ERROR_SC_V, description = CustomExceptionResponse.INTERNAL_SERVER_ERROR_SC),
			@ApiResponse(responseCode = CustomExceptionResponse.DB_EXCEPTION_SC_V, description = CustomExceptionResponse.DB_EXCEPTION_SC),
			@ApiResponse(responseCode = CustomExceptionResponse.BAD_REQUEST_SC_V, description = CustomExceptionResponse.BAD_REQUEST_SC) })
	public ResponseEntity<List<CallConfiguration>> updateCallConfiguration(
			@RequestBody List<CallConfiguration> callConfigurations) {
		return new ResponseEntity<>(callConfigurationImpl.updateCallConfigurations(callConfigurations), HttpStatus.OK);
	}

	@PostMapping(value = "/mapCallAndSection", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Create call and section mapping", description = "Desc - Create call and section mapping")
	@ApiResponses(value = {
			@ApiResponse(responseCode = CustomExceptionResponse.SUCCESS_SC_V, description = CustomExceptionResponse.SUCCESS_SC, content = {
					@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = CustomExceptionResponse.NOT_FOUND_SC_V, description = CustomExceptionResponse.NOT_FOUND_SC),
			@ApiResponse(responseCode = CustomExceptionResponse.INTERNAL_SERVER_ERROR_SC_V, description = CustomExceptionResponse.INTERNAL_SERVER_ERROR_SC),
			@ApiResponse(responseCode = CustomExceptionResponse.DB_EXCEPTION_SC_V, description = CustomExceptionResponse.DB_EXCEPTION_SC),
			@ApiResponse(responseCode = CustomExceptionResponse.BAD_REQUEST_SC_V, description = CustomExceptionResponse.BAD_REQUEST_SC) })
	public ResponseEntity<Object> mapCallAndSection(@RequestBody CallSectionMappingDTO callSectionMappingDTO) {
		return new ResponseEntity<>(callConfigurationImpl.mapCallAndSection(callSectionMappingDTO), HttpStatus.OK);
	}

	@GetMapping(value = "/getCallAndSectionMapByPsmIdAndCallConfigId/{psmId}/{callConfigId}", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Fetch mapped call and section", description = "Desc - Fetch mapped call and section")
	@ApiResponses(value = {
			@ApiResponse(responseCode = CustomExceptionResponse.SUCCESS_SC_V, description = CustomExceptionResponse.SUCCESS_SC, content = {
					@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = CustomExceptionResponse.NOT_FOUND_SC_V, description = CustomExceptionResponse.NOT_FOUND_SC),
			@ApiResponse(responseCode = CustomExceptionResponse.INTERNAL_SERVER_ERROR_SC_V, description = CustomExceptionResponse.INTERNAL_SERVER_ERROR_SC),
			@ApiResponse(responseCode = CustomExceptionResponse.DB_EXCEPTION_SC_V, description = CustomExceptionResponse.DB_EXCEPTION_SC),
			@ApiResponse(responseCode = CustomExceptionResponse.BAD_REQUEST_SC_V, description = CustomExceptionResponse.BAD_REQUEST_SC) })
	public ResponseEntity<Object> getCallAndSectionMapByPsmIdAndCallConfigId(@PathVariable Integer psmId,
			@PathVariable Integer callConfigId) {
		return new ResponseEntity<>(
				callConfigurationImpl.getCallAndSectionMapByPsmIdAndCallConfigId(psmId, callConfigId), HttpStatus.OK);
	}

}
