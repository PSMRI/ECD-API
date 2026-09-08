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
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Date;

import javax.crypto.SecretKey;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

class JwtUtilTest {

	private static final String SECRET = "0123456789012345678901234567890123456789012345678901234567890123";

	private JwtUtil jwtUtil;
	private TokenDenylist tokenDenylist;

	@BeforeEach
	void setUp() {
		jwtUtil = new JwtUtil();
		tokenDenylist = Mockito.mock(TokenDenylist.class);
		ReflectionTestUtils.setField(jwtUtil, "SECRET_KEY", SECRET);
		ReflectionTestUtils.setField(jwtUtil, "tokenDenylist", tokenDenylist);
	}

	private String token(String subject, String jti) {
		SecretKey key = Keys.hmacShaKeyFor(SECRET.getBytes());
		return Jwts.builder().subject(subject).id(jti).claim("userId", "1")
				.expiration(new Date(System.currentTimeMillis() + 600000)).signWith(key).compact();
	}

	@Test
	void validateTokenReturnsClaimsForValidToken() {
		when(tokenDenylist.isTokenDenylisted("jti-1")).thenReturn(false);

		Claims claims = jwtUtil.validateToken(token("user1", "jti-1"));

		assertNotNull(claims);
		assertEquals("user1", claims.getSubject());
	}

	@Test
	void validateTokenReturnsClaimsWhenNoJti() {
		Claims claims = jwtUtil.validateToken(token("user1", null));

		assertNotNull(claims);
		assertEquals("user1", claims.getSubject());
	}

	@Test
	void validateTokenReturnsNullForDenylistedToken() {
		when(tokenDenylist.isTokenDenylisted("jti-2")).thenReturn(true);

		assertNull(jwtUtil.validateToken(token("user1", "jti-2")));
	}

	@Test
	void validateTokenReturnsNullForMalformedToken() {
		assertNull(jwtUtil.validateToken("not-a-token"));
	}

	@Test
	void validateTokenReturnsNullWhenSecretMissing() {
		ReflectionTestUtils.setField(jwtUtil, "SECRET_KEY", "");

		assertNull(jwtUtil.validateToken("anything"));
	}

	@Test
	void extractUsernameReturnsSubject() {
		assertEquals("user9", jwtUtil.extractUsername(token("user9", "jti-9")));
	}

	@Test
	void extractClaimResolvesClaim() {
		String userId = jwtUtil.extractClaim(token("user9", "jti-9"), c -> c.get("userId", String.class));

		assertEquals("1", userId);
	}

	@Test
	void extractClaimPropagatesFailureForInvalidToken() {
		assertThrows(RuntimeException.class, () -> jwtUtil.extractUsername("bad"));
	}

	@Test
	void getSigningKeyFailsWhenSecretNull() {
		ReflectionTestUtils.setField(jwtUtil, "SECRET_KEY", null);

		assertThrows(IllegalStateException.class,
				() -> ReflectionTestUtils.invokeMethod(jwtUtil, "getSigningKey"));
	}
}
