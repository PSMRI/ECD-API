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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

class JwtUserIdValidationFilterTest {

	private static final String ORIGINS = "http://localhost:4200,https://*.example.com";

	@Mock
	private JwtAuthenticationUtil jwtAuthenticationUtil;
	@Mock
	private FilterChain filterChain;

	private JwtUserIdValidationFilter filter;
	private MockHttpServletResponse response;
	private AutoCloseable mocks;

	@BeforeEach
	void setUp() {
		mocks = MockitoAnnotations.openMocks(this);
		filter = new JwtUserIdValidationFilter(jwtAuthenticationUtil, ORIGINS);
		response = new MockHttpServletResponse();
	}

	@AfterEach
	void tearDown() throws Exception {
		UserAgentContext.clear();
		mocks.close();
	}

	private MockHttpServletRequest request(String method, String uri) {
		MockHttpServletRequest request = new MockHttpServletRequest(method, uri);
		request.setRequestURI(uri);
		return request;
	}

	@Test
	void optionsWithoutOriginIsForbidden() throws Exception {
		filter.doFilter(request("OPTIONS", "/api/data"), response, filterChain);

		assertEquals(HttpServletResponse.SC_FORBIDDEN, response.getStatus());
		verify(filterChain, never()).doFilter(any(), any());
	}

	@Test
	void optionsWithDisallowedOriginIsForbidden() throws Exception {
		MockHttpServletRequest request = request("OPTIONS", "/api/data");
		request.addHeader("Origin", "http://evil.com");

		filter.doFilter(request, response, filterChain);

		assertEquals(HttpServletResponse.SC_FORBIDDEN, response.getStatus());
	}

	@Test
	void optionsWithAllowedOriginShortCircuitsWithCorsHeaders() throws Exception {
		MockHttpServletRequest request = request("OPTIONS", "/api/data");
		request.addHeader("Origin", "http://localhost:4200");

		filter.doFilter(request, response, filterChain);

		assertEquals(HttpServletResponse.SC_OK, response.getStatus());
		assertEquals("http://localhost:4200", response.getHeader("Access-Control-Allow-Origin"));
		assertEquals("true", response.getHeader("Access-Control-Allow-Credentials"));
		verify(filterChain, never()).doFilter(any(), any());
	}

	@Test
	void wildcardOriginPatternIsAllowed() throws Exception {
		MockHttpServletRequest request = request("OPTIONS", "/api/data");
		request.addHeader("Origin", "https://app.example.com");

		filter.doFilter(request, response, filterChain);

		assertEquals(HttpServletResponse.SC_OK, response.getStatus());
	}

	@Test
	void nonOptionsWithDisallowedOriginIsForbidden() throws Exception {
		MockHttpServletRequest request = request("GET", "/api/data");
		request.addHeader("Origin", "http://evil.com");

		filter.doFilter(request, response, filterChain);

		assertEquals(HttpServletResponse.SC_FORBIDDEN, response.getStatus());
	}

	@Test
	void publicPathsSkipJwtValidation() throws Exception {
		for (String uri : Arrays.asList("/user/userAuthenticate", "/user/logOutUserFromConcurrentSession",
				"/swagger-ui/index.html", "/v3/api-docs", "/user/refreshToken", "/public/info", "/health",
				"/version")) {
			FilterChain chain = org.mockito.Mockito.mock(FilterChain.class);
			filter.doFilter(request("GET", uri), new MockHttpServletResponse(), chain);
			verify(chain).doFilter(any(), any());
		}
	}

	@Test
	void userIdCookieIsCleared() throws Exception {
		MockHttpServletRequest request = request("GET", "/health");
		request.setCookies(new Cookie("userId", "5"));

		filter.doFilter(request, response, filterChain);

		Cookie cleared = response.getCookie("userId");
		assertEquals(0, cleared.getMaxAge());
		assertNull(cleared.getValue());
	}

	@Test
	void jwtFromCookieIsValidatedAndChainContinues() throws Exception {
		MockHttpServletRequest request = request("GET", "/api/data");
		request.setCookies(new Cookie("Jwttoken", "cookie-token"));
		when(jwtAuthenticationUtil.validateUserIdAndJwtToken("cookie-token")).thenReturn(true);

		filter.doFilter(request, response, filterChain);

		verify(filterChain).doFilter(any(), any());
	}

	@Test
	void jwtFromHeaderIsValidatedAndChainContinues() throws Exception {
		MockHttpServletRequest request = request("GET", "/api/data");
		request.addHeader("Jwttoken", "header-token");
		when(jwtAuthenticationUtil.validateUserIdAndJwtToken("header-token")).thenReturn(true);

		filter.doFilter(request, response, filterChain);

		verify(filterChain).doFilter(any(), any());
	}

	@Test
	void invalidJwtResultsInUnauthorized() throws Exception {
		MockHttpServletRequest request = request("GET", "/api/data");
		request.addHeader("Jwttoken", "header-token");
		when(jwtAuthenticationUtil.validateUserIdAndJwtToken("header-token")).thenReturn(false);

		filter.doFilter(request, response, filterChain);

		assertEquals(HttpServletResponse.SC_UNAUTHORIZED, response.getStatus());
		verify(filterChain, never()).doFilter(any(), any());
	}

	@Test
	void mobileClientWithAuthorizationHeaderBypassesJwtValidation() throws Exception {
		MockHttpServletRequest request = request("GET", "/api/data");
		request.addHeader("User-Agent", "okhttp/4.9.0");
		request.addHeader("Authorization", "Bearer abc");

		filter.doFilter(request, response, filterChain);

		verify(filterChain).doFilter(any(), any());
	}

	@Test
	void missingTokenResultsInUnauthorized() throws Exception {
		filter.doFilter(request("GET", "/api/data"), response, filterChain);

		assertEquals(HttpServletResponse.SC_UNAUTHORIZED, response.getStatus());
	}

	@Test
	void nonMobileUserAgentWithoutTokenIsUnauthorized() throws Exception {
		MockHttpServletRequest request = request("GET", "/api/data");
		request.addHeader("User-Agent", "Mozilla/5.0");
		request.addHeader("Authorization", "Bearer abc");

		filter.doFilter(request, response, filterChain);

		assertEquals(HttpServletResponse.SC_UNAUTHORIZED, response.getStatus());
	}

	@Test
	void validationExceptionResultsInUnauthorized() throws Exception {
		MockHttpServletRequest request = request("GET", "/api/data");
		request.addHeader("Jwttoken", "header-token");
		when(jwtAuthenticationUtil.validateUserIdAndJwtToken(anyString()))
				.thenThrow(new IllegalStateException("boom"));

		filter.doFilter(request, response, filterChain);

		assertEquals(HttpServletResponse.SC_UNAUTHORIZED, response.getStatus());
	}

	@Test
	void blankAllowedOriginsRejectsEveryOrigin() throws Exception {
		JwtUserIdValidationFilter blankFilter = new JwtUserIdValidationFilter(jwtAuthenticationUtil, "  ");
		MockHttpServletRequest request = request("GET", "/api/data");
		request.addHeader("Origin", "http://localhost:4200");

		blankFilter.doFilter(request, response, filterChain);

		assertEquals(HttpServletResponse.SC_FORBIDDEN, response.getStatus());
	}

	@Test
	void nullAllowedOriginsRejectsEveryOrigin() throws Exception {
		JwtUserIdValidationFilter nullFilter = new JwtUserIdValidationFilter(jwtAuthenticationUtil, null);
		MockHttpServletRequest request = request("OPTIONS", "/api/data");
		request.addHeader("Origin", "http://localhost:4200");

		nullFilter.doFilter(request, response, filterChain);

		assertEquals(HttpServletResponse.SC_FORBIDDEN, response.getStatus());
	}
}
