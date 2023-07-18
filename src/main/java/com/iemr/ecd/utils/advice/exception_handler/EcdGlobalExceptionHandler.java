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
package com.iemr.ecd.utils.advice.exception_handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class EcdGlobalExceptionHandler extends ResponseEntityExceptionHandler {

	Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	@ExceptionHandler
	public CustomExceptionResponse handleInvalidRequestParameterException(InvalidRequestException e) {
		logger.error("invalid request exception : " + e);
		CustomExceptionResponse customExceptionResponse = new CustomExceptionResponse();
		customExceptionResponse.setError(e);

		return customExceptionResponse;

	}

	@ExceptionHandler
	public CustomExceptionResponse handleGeneralException(ECDException e) {
		logger.error("ECD exception : " + e);
		CustomExceptionResponse customExceptionResponse = new CustomExceptionResponse();
		customExceptionResponse.setError(e);

		return customExceptionResponse;
	}

}
