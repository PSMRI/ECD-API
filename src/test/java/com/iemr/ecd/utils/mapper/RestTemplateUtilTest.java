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
package com.iemr.ecd.utils.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.Cookie;

class RestTemplateUtilTest {

	@AfterEach
	void tearDown() {
		RequestContextHolder.resetRequestAttributes();
		UserAgentContext.clear();
	}

	@Test
	void createRequestEntityWithoutRequestContextUsesBasicHeaders() {
		RequestContextHolder.resetRequestAttributes();

		HttpEntity<Object> entity = RestTemplateUtil.createRequestEntity("body", "auth-token");

		assertEquals("body", entity.getBody());
		assertEquals("auth-token", entity.getHeaders().getFirst(HttpHeaders.AUTHORIZATION));
		assertTrue(entity.getHeaders().getFirst(HttpHeaders.CONTENT_TYPE).contains("charset=utf-8"));
	}

	@Test
	void createRequestEntityCopiesJwtHeaderCookieAndUserAgent() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.addHeader("Jwttoken", "header-jwt");
		request.setCookies(new Cookie("Jwttoken", "cookie-jwt"));
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		UserAgentContext.setUserAgent("okhttp/4.9.0");

		HttpEntity<Object> entity = RestTemplateUtil.createRequestEntity("body", "auth-token");

		HttpHeaders headers = entity.getHeaders();
		assertEquals("okhttp/4.9.0", headers.getFirst(HttpHeaders.USER_AGENT));
		assertEquals("header-jwt", headers.getFirst("Jwttoken"));
		assertEquals("Jwttoken=cookie-jwt", headers.getFirst(HttpHeaders.COOKIE));
	}

	@Test
	void createRequestEntitySkipsOptionalHeadersWhenAbsent() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

		HttpEntity<Object> entity = RestTemplateUtil.createRequestEntity(null, "auth-token");

		assertNull(entity.getHeaders().getFirst(HttpHeaders.USER_AGENT));
		assertNull(entity.getHeaders().getFirst("Jwttoken"));
		assertNull(entity.getHeaders().getFirst(HttpHeaders.COOKIE));
	}

	@Test
	void constructorIsAccessible() {
		assertTrue(new RestTemplateUtil() instanceof RestTemplateUtil);
	}
}
