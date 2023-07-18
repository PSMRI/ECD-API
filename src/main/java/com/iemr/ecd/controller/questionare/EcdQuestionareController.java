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
package com.iemr.ecd.controller.questionare;

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

import com.iemr.ecd.dao.Questionnaire;
import com.iemr.ecd.dao.QuestionnaireSections;
import com.iemr.ecd.dao.SectionQuestionnaireMapping;
import com.iemr.ecd.dao.V_GetSectionQuestionMapping;
import com.iemr.ecd.dao.V_GetSectionQuestionMappingAssociates;
import com.iemr.ecd.dto.RequestSectionQuestionnaireMappingDTO;
import com.iemr.ecd.dto.ResponseSectionQuestionnaireMappingDTO;
import com.iemr.ecd.service.questionare.QuestionareServiceImpl;
import com.iemr.ecd.utils.advice.exception_handler.CustomExceptionResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

/***
 * 
 * @author NE298657
 *
 */

@RestController
@RequestMapping(value = "/Questionnaire", headers = "Authorization")
@CrossOrigin()
public class EcdQuestionareController {

	@Autowired
	private QuestionareServiceImpl questionareServiceImpl;

	@PostMapping(value = "/createQuestionnaires", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Create questionnaire", description = "Desc - Create questionnaire")
	@ApiResponses(value = {
			@ApiResponse(responseCode = CustomExceptionResponse.SUCCESS_SC_V, description = CustomExceptionResponse.SUCCESS_SC, content = {
					@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = CustomExceptionResponse.NOT_FOUND_SC_V, description = CustomExceptionResponse.NOT_FOUND_SC),
			@ApiResponse(responseCode = CustomExceptionResponse.INTERNAL_SERVER_ERROR_SC_V, description = CustomExceptionResponse.INTERNAL_SERVER_ERROR_SC),
			@ApiResponse(responseCode = CustomExceptionResponse.DB_EXCEPTION_SC_V, description = CustomExceptionResponse.DB_EXCEPTION_SC),
			@ApiResponse(responseCode = CustomExceptionResponse.BAD_REQUEST_SC_V, description = CustomExceptionResponse.BAD_REQUEST_SC) })
	public ResponseEntity<String> createQuestionnaires(@Valid @RequestBody List<Questionnaire> questionareList) {

		return new ResponseEntity<>(questionareServiceImpl.createQuestionares(questionareList), HttpStatus.OK);
	}

	@Deprecated
	@GetMapping("/getQuestionnaires")
	public ResponseEntity<List<Questionnaire>> getQuestionnaires() {
		return new ResponseEntity<>(null, HttpStatus.OK);
	}

	@Deprecated
	@GetMapping("/getQuestionnaireById/{id}")
	public ResponseEntity<Questionnaire> getQuestionnaireById(@PathVariable Long id) {
		return new ResponseEntity<>(null, HttpStatus.OK);

	}

	@GetMapping(value = "/getQuestionnairesByPSMId/{psmId}", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Fetch questionnaire", description = "Desc - Fetch questionnaire")
	@ApiResponses(value = {
			@ApiResponse(responseCode = CustomExceptionResponse.SUCCESS_SC_V, description = CustomExceptionResponse.SUCCESS_SC, content = {
					@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = CustomExceptionResponse.NOT_FOUND_SC_V, description = CustomExceptionResponse.NOT_FOUND_SC),
			@ApiResponse(responseCode = CustomExceptionResponse.INTERNAL_SERVER_ERROR_SC_V, description = CustomExceptionResponse.INTERNAL_SERVER_ERROR_SC),
			@ApiResponse(responseCode = CustomExceptionResponse.DB_EXCEPTION_SC_V, description = CustomExceptionResponse.DB_EXCEPTION_SC),
			@ApiResponse(responseCode = CustomExceptionResponse.BAD_REQUEST_SC_V, description = CustomExceptionResponse.BAD_REQUEST_SC) })
	public ResponseEntity<List<Questionnaire>> getQuestionnairesByPSMId(@PathVariable int psmId) {
		return new ResponseEntity<>(questionareServiceImpl.getQuestionaresByProvider(psmId), HttpStatus.OK);
	}

	@GetMapping(value = "/getUnMappedQuestionnairesByPSMId/{psmId}", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Fetch Unmapped questionnaire", description = "Desc - Fetch Unmapped questionnaire")
	@ApiResponses(value = {
			@ApiResponse(responseCode = CustomExceptionResponse.SUCCESS_SC_V, description = CustomExceptionResponse.SUCCESS_SC, content = {
					@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = CustomExceptionResponse.NOT_FOUND_SC_V, description = CustomExceptionResponse.NOT_FOUND_SC),
			@ApiResponse(responseCode = CustomExceptionResponse.INTERNAL_SERVER_ERROR_SC_V, description = CustomExceptionResponse.INTERNAL_SERVER_ERROR_SC),
			@ApiResponse(responseCode = CustomExceptionResponse.DB_EXCEPTION_SC_V, description = CustomExceptionResponse.DB_EXCEPTION_SC),
			@ApiResponse(responseCode = CustomExceptionResponse.BAD_REQUEST_SC_V, description = CustomExceptionResponse.BAD_REQUEST_SC) })
	public ResponseEntity<List<Questionnaire>> getUnMappedQuestionnairesByPSMId(@PathVariable int psmId) {
		return new ResponseEntity<>(questionareServiceImpl.getUnMappedQuestionnairesByPSMId(psmId), HttpStatus.OK);
	}

	@PostMapping(value = "/updateQuestionnaire", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Update questionnaire", description = "Desc - Update questionnaire")
	@ApiResponses(value = {
			@ApiResponse(responseCode = CustomExceptionResponse.SUCCESS_SC_V, description = CustomExceptionResponse.SUCCESS_SC, content = {
					@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = CustomExceptionResponse.NOT_FOUND_SC_V, description = CustomExceptionResponse.NOT_FOUND_SC),
			@ApiResponse(responseCode = CustomExceptionResponse.INTERNAL_SERVER_ERROR_SC_V, description = CustomExceptionResponse.INTERNAL_SERVER_ERROR_SC),
			@ApiResponse(responseCode = CustomExceptionResponse.DB_EXCEPTION_SC_V, description = CustomExceptionResponse.DB_EXCEPTION_SC),
			@ApiResponse(responseCode = CustomExceptionResponse.BAD_REQUEST_SC_V, description = CustomExceptionResponse.BAD_REQUEST_SC) })
	public ResponseEntity<Object> updateQuestionnaire(@RequestBody Questionnaire questionare) {
		return new ResponseEntity<>(questionareServiceImpl.updateQuestionares(questionare), HttpStatus.OK);
	}

	// sections

	@PostMapping(value = "/createSections", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Create sections", description = "Desc - Create sections")
	@ApiResponses(value = {
			@ApiResponse(responseCode = CustomExceptionResponse.SUCCESS_SC_V, description = CustomExceptionResponse.SUCCESS_SC, content = {
					@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = CustomExceptionResponse.NOT_FOUND_SC_V, description = CustomExceptionResponse.NOT_FOUND_SC),
			@ApiResponse(responseCode = CustomExceptionResponse.INTERNAL_SERVER_ERROR_SC_V, description = CustomExceptionResponse.INTERNAL_SERVER_ERROR_SC),
			@ApiResponse(responseCode = CustomExceptionResponse.DB_EXCEPTION_SC_V, description = CustomExceptionResponse.DB_EXCEPTION_SC),
			@ApiResponse(responseCode = CustomExceptionResponse.BAD_REQUEST_SC_V, description = CustomExceptionResponse.BAD_REQUEST_SC) })
	public ResponseEntity<String> createSections(@Valid @RequestBody List<QuestionnaireSections> questionareSections) {

		return new ResponseEntity<>(questionareServiceImpl.createSections(questionareSections), HttpStatus.OK);
	}

	@Deprecated
	@GetMapping("/getSections")
	public ResponseEntity<List<QuestionnaireSections>> getSections() {
		return new ResponseEntity<>(null, HttpStatus.OK);
	}

	@GetMapping(value = "/getSectionsByProvider/{psmId}", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Fetch sections", description = "Desc - Fetch sections")
	@ApiResponses(value = {
			@ApiResponse(responseCode = CustomExceptionResponse.SUCCESS_SC_V, description = CustomExceptionResponse.SUCCESS_SC, content = {
					@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = CustomExceptionResponse.NOT_FOUND_SC_V, description = CustomExceptionResponse.NOT_FOUND_SC),
			@ApiResponse(responseCode = CustomExceptionResponse.INTERNAL_SERVER_ERROR_SC_V, description = CustomExceptionResponse.INTERNAL_SERVER_ERROR_SC),
			@ApiResponse(responseCode = CustomExceptionResponse.DB_EXCEPTION_SC_V, description = CustomExceptionResponse.DB_EXCEPTION_SC),
			@ApiResponse(responseCode = CustomExceptionResponse.BAD_REQUEST_SC_V, description = CustomExceptionResponse.BAD_REQUEST_SC) })
	public ResponseEntity<List<QuestionnaireSections>> getSectionsByProvider(@PathVariable int psmId) {
		return new ResponseEntity<>(questionareServiceImpl.getSectionsByProvider(psmId), HttpStatus.OK);
	}

	@PutMapping(value = "/updateSection", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Update section", description = "Desc - Update section")
	@ApiResponses(value = {
			@ApiResponse(responseCode = CustomExceptionResponse.SUCCESS_SC_V, description = CustomExceptionResponse.SUCCESS_SC, content = {
					@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = CustomExceptionResponse.NOT_FOUND_SC_V, description = CustomExceptionResponse.NOT_FOUND_SC),
			@ApiResponse(responseCode = CustomExceptionResponse.INTERNAL_SERVER_ERROR_SC_V, description = CustomExceptionResponse.INTERNAL_SERVER_ERROR_SC),
			@ApiResponse(responseCode = CustomExceptionResponse.DB_EXCEPTION_SC_V, description = CustomExceptionResponse.DB_EXCEPTION_SC),
			@ApiResponse(responseCode = CustomExceptionResponse.BAD_REQUEST_SC_V, description = CustomExceptionResponse.BAD_REQUEST_SC) })
	public ResponseEntity<String> updateSection(@RequestBody QuestionnaireSections questionareSections) {
		return new ResponseEntity<>(questionareServiceImpl.updateSections(questionareSections), HttpStatus.OK);
	}

	@PostMapping(value = "/mapQuestionnairesAndSection", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Create questionnaire and section mapping", description = "Desc - Create questionnaire and section mapping")
	@ApiResponses(value = {
			@ApiResponse(responseCode = CustomExceptionResponse.SUCCESS_SC_V, description = CustomExceptionResponse.SUCCESS_SC, content = {
					@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = CustomExceptionResponse.NOT_FOUND_SC_V, description = CustomExceptionResponse.NOT_FOUND_SC),
			@ApiResponse(responseCode = CustomExceptionResponse.INTERNAL_SERVER_ERROR_SC_V, description = CustomExceptionResponse.INTERNAL_SERVER_ERROR_SC),
			@ApiResponse(responseCode = CustomExceptionResponse.DB_EXCEPTION_SC_V, description = CustomExceptionResponse.DB_EXCEPTION_SC),
			@ApiResponse(responseCode = CustomExceptionResponse.BAD_REQUEST_SC_V, description = CustomExceptionResponse.BAD_REQUEST_SC) })
	public ResponseEntity<Object> mapQuestionnairesAndSection(
			@Valid @RequestBody RequestSectionQuestionnaireMappingDTO requestSectionQuestionnaireMappingDTO) {

		return new ResponseEntity<>(
				questionareServiceImpl.mapQuestionnairesAndSection(requestSectionQuestionnaireMappingDTO),
				HttpStatus.OK);
	}

	@GetMapping(value = "/getQuestionnairesAndSectionMapBySectionId/{sectionId}", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Fetch mapped questionnaire and section by sectionId", description = "Desc - Fetch mapped questionnaire and section by sectionId")
	@ApiResponses(value = {
			@ApiResponse(responseCode = CustomExceptionResponse.SUCCESS_SC_V, description = CustomExceptionResponse.SUCCESS_SC, content = {
					@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = CustomExceptionResponse.NOT_FOUND_SC_V, description = CustomExceptionResponse.NOT_FOUND_SC),
			@ApiResponse(responseCode = CustomExceptionResponse.INTERNAL_SERVER_ERROR_SC_V, description = CustomExceptionResponse.INTERNAL_SERVER_ERROR_SC),
			@ApiResponse(responseCode = CustomExceptionResponse.DB_EXCEPTION_SC_V, description = CustomExceptionResponse.DB_EXCEPTION_SC),
			@ApiResponse(responseCode = CustomExceptionResponse.BAD_REQUEST_SC_V, description = CustomExceptionResponse.BAD_REQUEST_SC) })
	public ResponseEntity<ResponseSectionQuestionnaireMappingDTO> getQuestionnairesAndSectionMap(
			@PathVariable int sectionId) {
		return new ResponseEntity<>(questionareServiceImpl.getQuestionnairesAndSectionMappingDataBySectionId(sectionId),
				HttpStatus.OK);
	}

	@GetMapping(value = "/getQuestionnairesAndSectionMapByProvider/{psmId}", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Fetch mapped questionnaire and section by psmId", description = "Desc - Fetch mapped questionnaire and section by psmId")
	@ApiResponses(value = {
			@ApiResponse(responseCode = CustomExceptionResponse.SUCCESS_SC_V, description = CustomExceptionResponse.SUCCESS_SC, content = {
					@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = CustomExceptionResponse.NOT_FOUND_SC_V, description = CustomExceptionResponse.NOT_FOUND_SC),
			@ApiResponse(responseCode = CustomExceptionResponse.INTERNAL_SERVER_ERROR_SC_V, description = CustomExceptionResponse.INTERNAL_SERVER_ERROR_SC),
			@ApiResponse(responseCode = CustomExceptionResponse.DB_EXCEPTION_SC_V, description = CustomExceptionResponse.DB_EXCEPTION_SC),
			@ApiResponse(responseCode = CustomExceptionResponse.BAD_REQUEST_SC_V, description = CustomExceptionResponse.BAD_REQUEST_SC) })
	public ResponseEntity<List<V_GetSectionQuestionMapping>> getQuestionnairesAndSectionMapByProvider(
			@PathVariable int psmId) {
		return new ResponseEntity<>(questionareServiceImpl.getQuestionnairesAndSectionMappingDataByProvider(psmId),
				HttpStatus.OK);
	}

	@GetMapping(value = "/getQuesAndSecMapAssociateByProvider/{psmId}/{ecdCallType}", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Fetch mapped questionnaire and section for associate", description = "Desc - Fetch mapped questionnaire and section for associate")
	@ApiResponses(value = {
			@ApiResponse(responseCode = CustomExceptionResponse.SUCCESS_SC_V, description = CustomExceptionResponse.SUCCESS_SC, content = {
					@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = CustomExceptionResponse.NOT_FOUND_SC_V, description = CustomExceptionResponse.NOT_FOUND_SC),
			@ApiResponse(responseCode = CustomExceptionResponse.INTERNAL_SERVER_ERROR_SC_V, description = CustomExceptionResponse.INTERNAL_SERVER_ERROR_SC),
			@ApiResponse(responseCode = CustomExceptionResponse.DB_EXCEPTION_SC_V, description = CustomExceptionResponse.DB_EXCEPTION_SC),
			@ApiResponse(responseCode = CustomExceptionResponse.BAD_REQUEST_SC_V, description = CustomExceptionResponse.BAD_REQUEST_SC) })
	public ResponseEntity<List<V_GetSectionQuestionMappingAssociates>> getQuesAndSecMapAssociateByProvider(
			@PathVariable int psmId, @PathVariable String ecdCallType) {
		return new ResponseEntity<>(questionareServiceImpl.getQuesAndSecMapAssociateByProvider(psmId, ecdCallType),
				HttpStatus.OK);
	}

	@PutMapping(value = "/editQuestionnaireSectionMap", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Edit mapped questionnaire and section", description = "Desc - Edit mapped questionnaire and section")
	@ApiResponses(value = {
			@ApiResponse(responseCode = CustomExceptionResponse.SUCCESS_SC_V, description = CustomExceptionResponse.SUCCESS_SC, content = {
					@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = CustomExceptionResponse.NOT_FOUND_SC_V, description = CustomExceptionResponse.NOT_FOUND_SC),
			@ApiResponse(responseCode = CustomExceptionResponse.INTERNAL_SERVER_ERROR_SC_V, description = CustomExceptionResponse.INTERNAL_SERVER_ERROR_SC),
			@ApiResponse(responseCode = CustomExceptionResponse.DB_EXCEPTION_SC_V, description = CustomExceptionResponse.DB_EXCEPTION_SC),
			@ApiResponse(responseCode = CustomExceptionResponse.BAD_REQUEST_SC_V, description = CustomExceptionResponse.BAD_REQUEST_SC) })
	public ResponseEntity<String> editQuestionnaireSectionMap(
			@RequestBody SectionQuestionnaireMapping sectionQuestionnaireMapping) {
		return new ResponseEntity<>(questionareServiceImpl.editQuestionnaireSectionMap(sectionQuestionnaireMapping),
				HttpStatus.OK);
	}
	
	@GetMapping(value = "/getUnMappedQuestionnaires/{psmId}/{sectionId}", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Fetch Unmapped questionnaire", description = "Desc - Fetch Unmapped questionnaire")
	@ApiResponses(value = {
			@ApiResponse(responseCode = CustomExceptionResponse.SUCCESS_SC_V, description = CustomExceptionResponse.SUCCESS_SC, content = {
					@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = CustomExceptionResponse.NOT_FOUND_SC_V, description = CustomExceptionResponse.NOT_FOUND_SC),
			@ApiResponse(responseCode = CustomExceptionResponse.INTERNAL_SERVER_ERROR_SC_V, description = CustomExceptionResponse.INTERNAL_SERVER_ERROR_SC),
			@ApiResponse(responseCode = CustomExceptionResponse.DB_EXCEPTION_SC_V, description = CustomExceptionResponse.DB_EXCEPTION_SC),
			@ApiResponse(responseCode = CustomExceptionResponse.BAD_REQUEST_SC_V, description = CustomExceptionResponse.BAD_REQUEST_SC) })
	public ResponseEntity<List<Questionnaire>> getUnMappedQuestionnaires(@PathVariable int psmId,
			@PathVariable int sectionId) {
		return new ResponseEntity<>(questionareServiceImpl.getUnMappedQuestionnaires(psmId, sectionId), HttpStatus.OK);
	}

}
