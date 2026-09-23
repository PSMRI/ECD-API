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
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;

class AuthorizationHeaderRequestWrapperTest {

	private AuthorizationHeaderRequestWrapper wrapper(String authValue) {
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.addHeader("Authorization", "original");
		request.addHeader("Other", "other-value");
		return new AuthorizationHeaderRequestWrapper(request, authValue);
	}

	@Test
	void getHeaderOverridesAuthorization() {
		assertEquals("replaced", wrapper("replaced").getHeader("authorization"));
	}

	@Test
	void getHeaderDelegatesOtherHeaders() {
		assertEquals("other-value", wrapper("replaced").getHeader("Other"));
	}

	@Test
	void getHeadersOverridesAuthorization() {
		List<String> values = Collections.list(wrapper("replaced").getHeaders("Authorization"));

		assertEquals(List.of("replaced"), values);
	}

	@Test
	void getHeadersDelegatesOtherHeaders() {
		List<String> values = Collections.list(wrapper("replaced").getHeaders("Other"));

		assertEquals(List.of("other-value"), values);
	}

	@Test
	void getHeaderNamesAlwaysContainsAuthorization() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.addHeader("Other", "other-value");
		AuthorizationHeaderRequestWrapper plain = new AuthorizationHeaderRequestWrapper(request, "x");

		List<String> names = Collections.list(plain.getHeaderNames());

		assertTrue(names.contains("Authorization"));
		assertTrue(names.contains("Other"));
	}

	@Test
	void getHeaderNamesDoesNotDuplicateAuthorization() {
		List<String> names = Collections.list(wrapper("replaced").getHeaderNames());

		assertEquals(1, names.stream().filter("Authorization"::equals).count());
	}
}
