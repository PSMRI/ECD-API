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
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import org.json.JSONException;
import org.junit.jupiter.api.Test;

class CustomExceptionResponseTest {

	@Test
	void setResponseWithJsonObjectMarksSuccess() {
		CustomExceptionResponse response = new CustomExceptionResponse();

		response.setResponse("{\"key\":\"value\"}");

		assertTrue(response.isSuccess());
		assertEquals(CustomExceptionResponse.SUCCESS, response.getStatusCode());
		assertEquals("Success", response.getErrorMessage());
		assertEquals("Success", response.getStatus());
		assertTrue(response.getData().contains("value"));
	}

	@Test
	void setResponseWithJsonArrayStoresArray() {
		CustomExceptionResponse response = new CustomExceptionResponse();

		response.setResponse("[{\"key\":\"value\"}]");

		assertTrue(response.isSuccess());
		assertTrue(response.toString().contains("value"));
	}

	@Test
	void setResponseWithPlainStringWrapsItInResponseObject() {
		CustomExceptionResponse response = new CustomExceptionResponse();

		response.setResponse("plain-text");

		assertTrue(response.toString().contains("plain-text"));
	}

	@Test
	void getDataReturnsNullWhenNoDataPresent() {
		assertNull(new CustomExceptionResponse().getData());
	}

	@Test
	void setErrorWithCodeAndMessage() {
		CustomExceptionResponse response = new CustomExceptionResponse();

		response.setError(CustomExceptionResponse.BAD_REQUEST, "bad input");

		assertEquals(CustomExceptionResponse.BAD_REQUEST, response.getStatusCode());
		assertEquals("bad input", response.getErrorMessage());
		assertEquals("bad input", response.getStatus());
		assertFalse(response.isSuccess());
	}

	@Test
	void setErrorWithCodeMessageAndStatus() {
		CustomExceptionResponse response = new CustomExceptionResponse();

		response.setError(CustomExceptionResponse.NOT_FOUND, "missing", CustomExceptionResponse.NOT_FOUND_SC);

		assertEquals(CustomExceptionResponse.NOT_FOUND, response.getStatusCode());
		assertEquals(CustomExceptionResponse.NOT_FOUND_SC, response.getStatus());
	}

	@Test
	void setErrorMapsSqlExceptionToDbException() {
		CustomExceptionResponse response = new CustomExceptionResponse();

		response.setError(new RuntimeException("wrapper", new SQLException("db down")));

		assertEquals(CustomExceptionResponse.DB_EXCEPTION, response.getStatusCode());
		assertEquals(CustomExceptionResponse.DB_EXCEPTION_SC, response.getStatus());
	}

	@Test
	void setErrorMapsJsonExceptionToObjectFailure() {
		CustomExceptionResponse response = new CustomExceptionResponse();

		response.setError(new RuntimeException("wrapper", new JSONException("bad json")));

		assertEquals(CustomExceptionResponse.OBJECT_FAILURE, response.getStatusCode());
		assertEquals("Invalid object conversion", response.getErrorMessage());
	}

	@Test
	void setErrorMapsIoExceptionToEnvironmentException() {
		CustomExceptionResponse response = new CustomExceptionResponse();

		response.setError(new RuntimeException("wrapper", new IOException("io")));

		assertEquals(CustomExceptionResponse.ENVIRONMENT_EXCEPTION, response.getStatusCode());
	}

	@Test
	void setErrorMapsParseExceptionToEnvironmentException() {
		CustomExceptionResponse response = new CustomExceptionResponse();

		response.setError(new RuntimeException("wrapper", new ParseException("parse", 0)));

		assertEquals(CustomExceptionResponse.ENVIRONMENT_EXCEPTION, response.getStatusCode());
	}

	@Test
	void setErrorMapsNullPointerExceptionToEnvironmentException() {
		CustomExceptionResponse response = new CustomExceptionResponse();

		response.setError(new RuntimeException("wrapper", new NullPointerException("npe")));

		assertEquals(CustomExceptionResponse.ENVIRONMENT_EXCEPTION, response.getStatusCode());
	}

	@Test
	void setErrorMapsUnknownCauseToGenericFailure() {
		CustomExceptionResponse response = new CustomExceptionResponse();

		response.setError(new RuntimeException("wrapper", new IllegalStateException("boom")));

		assertEquals(CustomExceptionResponse.GENERIC_FAILURE, response.getStatusCode());
		assertTrue(response.getStatus().contains("Failed with"));
	}

	@Test
	void toStringWithSerializationIncludesNulls() {
		CustomExceptionResponse response = new CustomExceptionResponse();

		String json = response.toStringWithSerialization();

		assertNotNull(json);
		assertTrue(json.contains("data"));
	}

	@Test
	void statusCodeConstantsAreStable() {
		assertEquals(200, CustomExceptionResponse.SUCCESS);
		assertEquals(5000, CustomExceptionResponse.GENERIC_FAILURE);
		assertEquals(5001, CustomExceptionResponse.OBJECT_FAILURE);
		assertEquals(5002, CustomExceptionResponse.USERID_FAILURE);
		assertEquals(5003, CustomExceptionResponse.PASSWORD_FAILURE);
		assertEquals(5004, CustomExceptionResponse.PREVILAGE_FAILURE);
		assertEquals(5005, CustomExceptionResponse.CODE_EXCEPTION);
		assertEquals(5006, CustomExceptionResponse.ENVIRONMENT_EXCEPTION);
		assertEquals(5007, CustomExceptionResponse.PARSE_EXCEPTION);
		assertEquals(5008, CustomExceptionResponse.DB_EXCEPTION);
		assertEquals(400, CustomExceptionResponse.BAD_REQUEST);
		assertEquals(404, CustomExceptionResponse.NOT_FOUND);
		assertEquals("SUCCESS", CustomExceptionResponse.SUCCESS_SC);
		assertEquals("BAD_REQUEST", CustomExceptionResponse.BAD_REQUEST_SC);
		assertEquals("INTERNAL_SERVER_ERROR", CustomExceptionResponse.INTERNAL_SERVER_ERROR_SC);
		assertEquals("200", CustomExceptionResponse.SUCCESS_SC_V);
		assertEquals("404", CustomExceptionResponse.NOT_FOUND_SC_V);
		assertEquals("5008", CustomExceptionResponse.DB_EXCEPTION_SC_V);
		assertEquals("400", CustomExceptionResponse.BAD_REQUEST_SC_V);
		assertEquals("500", CustomExceptionResponse.INTERNAL_SERVER_ERROR_SC_V);
	}

	@Test
	void setResponsePropagatesFailureWhenMessageIsMalformedJson() {
		CustomExceptionResponse response = new CustomExceptionResponse();

		assertThrows(com.google.gson.JsonSyntaxException.class, () -> response.setResponse("{\"unclosed\": "));
	}
}
