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
package com.iemr.ecd.utils.redis;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisStringCommands;
import org.springframework.data.redis.connection.RedisStringCommands.SetOption;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.types.Expiration;
import org.springframework.test.util.ReflectionTestUtils;

class RedisStorageTest {

	@Mock
	private LettuceConnectionFactory connectionFactory;
	@Mock
	private RedisConnection redisConnection;
	@Mock
	private RedisStringCommands stringCommands;

	private RedisStorage redisStorage;
	private AutoCloseable mocks;

	@BeforeEach
	void setUp() {
		mocks = MockitoAnnotations.openMocks(this);
		redisStorage = new RedisStorage();
		ReflectionTestUtils.setField(redisStorage, "connection", connectionFactory);
		ReflectionTestUtils.setField(redisStorage, "sessionExpiryTimeInSec", 1800);
		when(connectionFactory.getConnection()).thenReturn(redisConnection);
		when(redisConnection.stringCommands()).thenReturn(stringCommands);
	}

	@org.junit.jupiter.api.AfterEach
	void tearDown() throws Exception {
		mocks.close();
	}

	@Test
	void getSessionObjectReturnsStoredValue() throws Exception {
		when(stringCommands.get("key".getBytes())).thenReturn("session-data".getBytes());

		assertEquals("session-data", redisStorage.getSessionObject("key"));
	}

	@Test
	void getSessionObjectThrowsWhenMissing() {
		when(stringCommands.get("key".getBytes())).thenReturn(null);

		assertThrows(Exception.class, () -> redisStorage.getSessionObject("key"));
	}

	@Test
	void getSessionObjectThrowsWhenBlank() {
		when(stringCommands.get("key".getBytes())).thenReturn("   ".getBytes());

		assertThrows(Exception.class, () -> redisStorage.getSessionObject("key"));
	}

	@Test
	void updateSessionObjectRefreshesExpiry() throws Exception {
		when(stringCommands.get("key".getBytes())).thenReturn("session-data".getBytes());

		assertEquals("key", redisStorage.updateSessionObject("key"));
		verify(stringCommands).set(eq("key".getBytes()), eq("session-data".getBytes()), any(Expiration.class),
				eq(SetOption.UPSERT));
	}

	@Test
	void updateSessionObjectThrowsWhenMissing() {
		when(stringCommands.get("key".getBytes())).thenReturn(null);

		assertThrows(Exception.class, () -> redisStorage.updateSessionObject("key"));
	}

	@Test
	void updateSessionObjectThrowsWhenEmptyPayload() {
		when(stringCommands.get("key".getBytes())).thenReturn(new byte[0]);

		assertThrows(Exception.class, () -> redisStorage.updateSessionObject("key"));
	}

	@Test
	void updateConcurrentSessionObjectRefreshesUserSession() {
		when(stringCommands.get("user1".getBytes())).thenReturn("session-data".getBytes());

		redisStorage.updateConcurrentSessionObject("{\"userName\":\" User1 \"}");

		verify(stringCommands).set(eq("user1".getBytes()), eq("session-data".getBytes()), any(Expiration.class),
				eq(SetOption.UPSERT));
	}

	@Test
	void updateConcurrentSessionObjectSwallowsInvalidJson() {
		redisStorage.updateConcurrentSessionObject("not-json");

		verify(stringCommands, never()).set(any(), any(), any(Expiration.class), any(SetOption.class));
	}

	@Test
	void updateConcurrentSessionObjectSkipsWhenUserNameAbsent() {
		redisStorage.updateConcurrentSessionObject("{\"other\":\"x\"}");

		verify(stringCommands, never()).set(any(), any(), any(Expiration.class), any(SetOption.class));
	}
}
