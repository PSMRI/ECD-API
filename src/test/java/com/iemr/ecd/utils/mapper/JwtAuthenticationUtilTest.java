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
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.util.ReflectionTestUtils;

import com.iemr.ecd.dao.Users;
import com.iemr.ecd.repository.ecd.UserLoginRepo;

import io.jsonwebtoken.Claims;

class JwtAuthenticationUtilTest {

	@Mock
	private CookieUtil cookieUtil;
	@Mock
	private JwtUtil jwtUtil;
	@Mock
	private RedisTemplate<String, Object> redisTemplate;
	@Mock
	private ValueOperations<String, Object> valueOperations;
	@Mock
	private UserLoginRepo userLoginRepo;
	@Mock
	private Claims claims;

	private JwtAuthenticationUtil util;
	private AutoCloseable mocks;

	@BeforeEach
	void setUp() {
		mocks = MockitoAnnotations.openMocks(this);
		util = new JwtAuthenticationUtil(cookieUtil, jwtUtil);
		ReflectionTestUtils.setField(util, "redisTemplate", redisTemplate);
		ReflectionTestUtils.setField(util, "userLoginRepo", userLoginRepo);
	}

	@org.junit.jupiter.api.AfterEach
	void tearDown() throws Exception {
		mocks.close();
	}

	@Test
	void validateJwtTokenReturnsUnauthorizedWhenCookieMissing() {
		when(cookieUtil.getCookieValue(any(), eq("Jwttoken"))).thenReturn(Optional.empty());

		ResponseEntity<String> response = util.validateJwtToken(new MockHttpServletRequest());

		assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
		assertTrue(response.getBody().contains("JWT Token is not set"));
	}

	@Test
	void validateJwtTokenReturnsUnauthorizedWhenTokenInvalid() {
		when(cookieUtil.getCookieValue(any(), eq("Jwttoken"))).thenReturn(Optional.of("tok"));
		when(jwtUtil.validateToken("tok")).thenReturn(null);

		ResponseEntity<String> response = util.validateJwtToken(new MockHttpServletRequest());

		assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
		assertTrue(response.getBody().contains("Invalid JWT Token"));
	}

	@Test
	void validateJwtTokenReturnsUnauthorizedWhenSubjectMissing() {
		when(cookieUtil.getCookieValue(any(), eq("Jwttoken"))).thenReturn(Optional.of("tok"));
		when(jwtUtil.validateToken("tok")).thenReturn(claims);
		when(claims.getSubject()).thenReturn("");

		ResponseEntity<String> response = util.validateJwtToken(new MockHttpServletRequest());

		assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
		assertTrue(response.getBody().contains("Username is missing"));
	}

	@Test
	void validateJwtTokenReturnsUsernameWhenValid() {
		when(cookieUtil.getCookieValue(any(), eq("Jwttoken"))).thenReturn(Optional.of("tok"));
		when(jwtUtil.validateToken("tok")).thenReturn(claims);
		when(claims.getSubject()).thenReturn("user1");

		ResponseEntity<String> response = util.validateJwtToken(new MockHttpServletRequest());

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals("user1", response.getBody());
	}

	@Test
	void validateUserIdAndJwtTokenReturnsTrueWhenUserCached() throws Exception {
		when(jwtUtil.validateToken("tok")).thenReturn(claims);
		when(claims.get("userId", String.class)).thenReturn("5");
		when(redisTemplate.opsForValue()).thenReturn(valueOperations);
		when(valueOperations.get("user_5")).thenReturn(new Users());

		assertTrue(util.validateUserIdAndJwtToken("tok"));
		verify(userLoginRepo, never()).getUserByUserID(anyLong());
	}

	@Test
	void validateUserIdAndJwtTokenFetchesFromDbAndCaches() throws Exception {
		Users user = new Users();
		user.setUserID(5L);
		user.setUserName("user1");
		when(jwtUtil.validateToken("tok")).thenReturn(claims);
		when(claims.get("userId", String.class)).thenReturn("5");
		when(redisTemplate.opsForValue()).thenReturn(valueOperations);
		when(valueOperations.get("user_5")).thenReturn(null);
		when(userLoginRepo.getUserByUserID(5L)).thenReturn(user);

		assertTrue(util.validateUserIdAndJwtToken("tok"));
		verify(valueOperations).set(eq("user_5"), any(Users.class), eq(30L), eq(TimeUnit.MINUTES));
	}

	@Test
	void validateUserIdAndJwtTokenThrowsWhenUserNotFound() {
		when(jwtUtil.validateToken("tok")).thenReturn(claims);
		when(claims.get("userId", String.class)).thenReturn("5");
		when(redisTemplate.opsForValue()).thenReturn(valueOperations);
		when(valueOperations.get("user_5")).thenReturn(null);
		when(userLoginRepo.getUserByUserID(5L)).thenReturn(null);

		Exception ex = assertThrows(Exception.class, () -> util.validateUserIdAndJwtToken("tok"));
		assertTrue(ex.getMessage().contains("Invalid User ID"));
	}

	@Test
	void validateUserIdAndJwtTokenThrowsWhenClaimsNull() {
		when(jwtUtil.validateToken(anyString())).thenReturn(null);

		Exception ex = assertThrows(Exception.class, () -> util.validateUserIdAndJwtToken("tok"));
		assertTrue(ex.getMessage().contains("Invalid JWT token"));
	}
}
