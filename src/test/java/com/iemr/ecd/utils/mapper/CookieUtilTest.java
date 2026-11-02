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
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;

import jakarta.servlet.http.Cookie;

class CookieUtilTest {

	private final CookieUtil cookieUtil = new CookieUtil();

	@Test
	void getCookieValueReturnsValueWhenCookiePresent() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setCookies(new Cookie("other", "x"), new Cookie("Jwttoken", "token-1"));

		Optional<String> value = cookieUtil.getCookieValue(request, "Jwttoken");

		assertTrue(value.isPresent());
		assertEquals("token-1", value.get());
	}

	@Test
	void getCookieValueReturnsEmptyWhenCookieMissing() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setCookies(new Cookie("other", "x"));

		assertFalse(cookieUtil.getCookieValue(request, "Jwttoken").isPresent());
	}

	@Test
	void getCookieValueReturnsEmptyWhenNoCookies() {
		assertFalse(cookieUtil.getCookieValue(new MockHttpServletRequest(), "Jwttoken").isPresent());
	}

	@Test
	void getJwtTokenFromCookieReturnsToken() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setCookies(new Cookie("Jwttoken", "token-2"));

		assertEquals("token-2", CookieUtil.getJwtTokenFromCookie(request));
	}

	@Test
	void getJwtTokenFromCookieReturnsNullWhenNoCookies() {
		assertNull(CookieUtil.getJwtTokenFromCookie(new MockHttpServletRequest()));
	}

	@Test
	void getJwtTokenFromCookieReturnsNullWhenTokenCookieAbsent() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setCookies(new Cookie("other", "x"));

		assertNull(CookieUtil.getJwtTokenFromCookie(request));
	}
}
