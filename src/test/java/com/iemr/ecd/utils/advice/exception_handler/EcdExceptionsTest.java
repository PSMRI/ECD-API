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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class EcdExceptionsTest {

	@Test
	void ecdExceptionDefaultConstructor() {
		ECDException exception = new ECDException();

		assertNull(exception.getMessage());
	}

	@Test
	void ecdExceptionPrefixesMessage() {
		assertEquals("Exception Occurred: boom", new ECDException("boom").getMessage());
	}

	@Test
	void ecdExceptionWrapsCause() {
		IllegalStateException cause = new IllegalStateException("cause");

		assertSame(cause, new ECDException(cause).getCause());
	}

	@Test
	void invalidRequestExceptionDefaultConstructor() {
		assertNull(new InvalidRequestException().getMessage());
	}

	@Test
	void invalidRequestExceptionPrefixesMessage() {
		assertEquals("invalid request data. boom", new InvalidRequestException("boom").getMessage());
	}

	@Test
	void invalidRequestExceptionIncludesRequestParam() {
		assertEquals("invalid request data. userId is null",
				new InvalidRequestException("userId", "is null").getMessage());
	}

	@Test
	void invalidRequestExceptionWrapsCause() {
		IllegalStateException cause = new IllegalStateException("cause");

		assertSame(cause, new InvalidRequestException(cause).getCause());
	}

	@Test
	void globalHandlerConvertsInvalidRequestException() {
		EcdGlobalExceptionHandler handler = new EcdGlobalExceptionHandler();

		CustomExceptionResponse response = handler.handleInvalidRequestParameterException(
				new InvalidRequestException(new IllegalStateException("bad")));

		assertNotNull(response);
		assertEquals(CustomExceptionResponse.GENERIC_FAILURE, response.getStatusCode());
	}

	@Test
	void globalHandlerConvertsEcdException() {
		EcdGlobalExceptionHandler handler = new EcdGlobalExceptionHandler();

		CustomExceptionResponse response = handler.handleGeneralException(
				new ECDException(new java.sql.SQLException("db")));

		assertEquals(CustomExceptionResponse.DB_EXCEPTION, response.getStatusCode());
		assertTrue(response.getStatus().contains("DB_EXCEPTION"));
	}
}
