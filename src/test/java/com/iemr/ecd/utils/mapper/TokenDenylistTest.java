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

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.util.ReflectionTestUtils;

class TokenDenylistTest {

	@Mock
	private RedisTemplate<String, Object> redisTemplate;
	@Mock
	private ValueOperations<String, Object> valueOperations;

	private TokenDenylist tokenDenylist;
	private AutoCloseable mocks;

	@BeforeEach
	void setUp() {
		mocks = MockitoAnnotations.openMocks(this);
		tokenDenylist = new TokenDenylist();
		ReflectionTestUtils.setField(tokenDenylist, "redisTemplate", redisTemplate);
	}

	@Test
	void addTokenToDenylistStoresKeyWithPrefix() {
		when(redisTemplate.opsForValue()).thenReturn(valueOperations);

		tokenDenylist.addTokenToDenylist("jti-1", 1000L);

		verify(valueOperations).set(eq("denied_jti-1"), eq(" "), eq(1000L), eq(TimeUnit.MILLISECONDS));
	}

	@Test
	void addTokenToDenylistIgnoresBlankJti() {
		tokenDenylist.addTokenToDenylist("  ", 1000L);
		tokenDenylist.addTokenToDenylist(null, 1000L);

		verify(redisTemplate, never()).opsForValue();
	}

	@Test
	void addTokenToDenylistRejectsNonPositiveExpiry() {
		assertThrows(IllegalArgumentException.class, () -> tokenDenylist.addTokenToDenylist("jti", 0L));
		assertThrows(IllegalArgumentException.class, () -> tokenDenylist.addTokenToDenylist("jti", null));
	}

	@Test
	void addTokenToDenylistWrapsRedisFailure() {
		when(redisTemplate.opsForValue()).thenReturn(valueOperations);
		doThrow(new IllegalStateException("boom")).when(valueOperations).set(anyString(), any(), anyLong(),
				any(TimeUnit.class));

		assertThrows(RuntimeException.class, () -> tokenDenylist.addTokenToDenylist("jti", 5L));
	}

	@Test
	void isTokenDenylistedReturnsTrueWhenKeyExists() {
		when(redisTemplate.hasKey("denied_jti-1")).thenReturn(Boolean.TRUE);

		assertTrue(tokenDenylist.isTokenDenylisted("jti-1"));
	}

	@Test
	void isTokenDenylistedReturnsFalseWhenKeyMissing() {
		when(redisTemplate.hasKey("denied_jti-1")).thenReturn(Boolean.FALSE);

		assertFalse(tokenDenylist.isTokenDenylisted("jti-1"));
	}

	@Test
	void isTokenDenylistedReturnsFalseForBlankJti() {
		assertFalse(tokenDenylist.isTokenDenylisted(null));
		assertFalse(tokenDenylist.isTokenDenylisted(" "));
	}

	@Test
	void isTokenDenylistedReturnsFalseOnRedisFailure() {
		when(redisTemplate.hasKey(anyString())).thenThrow(new IllegalStateException("down"));

		assertFalse(tokenDenylist.isTokenDenylisted("jti-1"));
	}

	@org.junit.jupiter.api.AfterEach
	void tearDown() throws Exception {
		mocks.close();
	}
}
