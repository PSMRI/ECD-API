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
package com.iemr.ecd.utils.http_request_interceptor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.util.ReflectionTestUtils;

import com.iemr.ecd.utils.redis.RedisStorage;

class HttpInterceptorTest {

	@Mock
	private RedisStorage redisStorage;

	private HttpInterceptor interceptor;
	private MockHttpServletResponse response;
	private AutoCloseable mocks;

	@BeforeEach
	void setUp() {
		mocks = MockitoAnnotations.openMocks(this);
		interceptor = new HttpInterceptor();
		ReflectionTestUtils.setField(interceptor, "redisStorage", redisStorage);
		ReflectionTestUtils.setField(interceptor, "allowedOrigins", "http://localhost:4200");
		response = new MockHttpServletResponse();
	}

	@org.junit.jupiter.api.AfterEach
	void tearDown() throws Exception {
		mocks.close();
	}

	private MockHttpServletRequest request(String method, String uri) {
		MockHttpServletRequest request = new MockHttpServletRequest(method, uri);
		request.setRequestURI(uri);
		return request;
	}

	@Test
	void preHandleAllowsSwaggerUi() throws Exception {
		assertTrue(interceptor.preHandle(request("GET", "/swagger-ui/index.html"), response, new Object()));
	}

	@Test
	void preHandleAllowsRequestWithoutAuthorization() throws Exception {
		assertTrue(interceptor.preHandle(request("GET", "/api/data"), response, new Object()));
	}

	@Test
	void preHandleStripsBearerPrefixAndAllowsRequest() throws Exception {
		MockHttpServletRequest request = request("GET", "/api/data");
		request.addHeader("Authorization", "Bearer token-1");

		assertTrue(interceptor.preHandle(request, response, new Object()));
	}

	@Test
	void preHandleAcceptsRawAuthorizationToken() throws Exception {
		MockHttpServletRequest request = request("GET", "/api/data");
		request.addHeader("Authorization", "token-1");

		assertTrue(interceptor.preHandle(request, response, new Object()));
	}

	@Test
	void preHandleRejectsErrorEndpoint() throws Exception {
		MockHttpServletRequest request = request("GET", "/error");
		request.addHeader("Authorization", "token-1");

		assertFalse(interceptor.preHandle(request, response, new Object()));
	}

	@Test
	void preHandleAllowsWhitelistedSwaggerEndpoints() throws Exception {
		for (String uri : new String[] { "/swagger-ui.html", "/index.html", "/swagger-initializer.js",
				"/swagger-config", "/ui", "/swagger-resources", "/api-docs" }) {
			MockHttpServletRequest request = request("GET", uri);
			request.addHeader("Authorization", "token-1");

			assertTrue(interceptor.preHandle(request, new MockHttpServletResponse(), new Object()), uri);
		}
	}

	@Test
	void preHandleSkipsValidationForOptions() throws Exception {
		MockHttpServletRequest request = request("OPTIONS", "/error");
		request.addHeader("Authorization", "token-1");

		assertTrue(interceptor.preHandle(request, response, new Object()));
	}

	@Test
	void postHandleRefreshesRedisSession() throws Exception {
		MockHttpServletRequest request = request("GET", "/api/data");
		request.addHeader("Authorization", "Bearer token-1");
		when(redisStorage.getSessionObject("token-1")).thenReturn("{\"userName\":\"user1\"}");

		interceptor.postHandle(request, response, new Object(), null);

		verify(redisStorage).updateConcurrentSessionObject("{\"userName\":\"user1\"}");
		verify(redisStorage).updateSessionObject("token-1");
	}

	@Test
	void postHandleSkipsWhenNoAuthorization() throws Exception {
		interceptor.postHandle(request("GET", "/api/data"), response, new Object(), null);

		verify(redisStorage, never()).updateSessionObject(anyString());
	}

	@Test
	void postHandleSwallowsRedisFailure() throws Exception {
		MockHttpServletRequest request = request("GET", "/api/data");
		request.addHeader("Authorization", "token-1");
		when(redisStorage.getSessionObject("token-1")).thenThrow(new IllegalStateException("down"));

		interceptor.postHandle(request, response, new Object(), null);

		verify(redisStorage, never()).updateSessionObject(anyString());
	}

	@Test
	void afterCompletionDoesNotFail() throws Exception {
		interceptor.afterCompletion(request("GET", "/api/data"), response, new Object(), null);

		assertEquals(200, response.getStatus());
		assertNull(response.getHeader("Access-Control-Allow-Origin"));
	}
}
