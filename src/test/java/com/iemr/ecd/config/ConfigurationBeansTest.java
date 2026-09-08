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
package com.iemr.ecd.config;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.mock.env.MockEnvironment;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

import com.iemr.ecd.dao.Users;
import com.iemr.ecd.utils.http_request_interceptor.HttpInterceptor;

import io.swagger.v3.oas.models.OpenAPI;

class ConfigurationBeansTest {

	@Test
	void httpInterceptorConfigRegistersInterceptor() {
		HttpInterceptorConfig config = new HttpInterceptorConfig();
		HttpInterceptor interceptor = mock(HttpInterceptor.class);
		ReflectionTestUtils.setField(config, "httpInterceptor", interceptor);
		InterceptorRegistry registry = mock(InterceptorRegistry.class);

		config.addInterceptors(registry);

		verify(registry).addInterceptor(interceptor);
	}

	@Test
	void redisConfigCreatesLettuceConnectionFactory() {
		RedisConfig config = new RedisConfig();
		ReflectionTestUtils.setField(config, "redisHost", "localhost");
		ReflectionTestUtils.setField(config, "redisPort", 6379);

		LettuceConnectionFactory factory = config.lettuceConnectionFactory();

		assertNotNull(factory);
		assertEquals("localhost", factory.getHostName());
		assertEquals(6379, factory.getPort());
	}

	@Test
	void redisConfigCreatesRedisTemplate() {
		RedisConfig config = new RedisConfig();
		RedisConnectionFactory factory = mock(RedisConnectionFactory.class);

		RedisTemplate<String, Users> template = config.redisTemplate(factory);

		assertSame(factory, template.getConnectionFactory());
		assertNotNull(template.getValueSerializer());
		assertEquals(StringRedisSerializer.class, template.getKeySerializer().getClass());
	}

	@Test
	void swaggerConfigUsesConfiguredServerUrls() {
		MockEnvironment env = new MockEnvironment();
		env.setProperty("api.dev.url", "http://dev");
		env.setProperty("api.uat.url", "http://uat");
		env.setProperty("api.demo.url", "http://demo");

		OpenAPI openAPI = new SwaggerConfig().customOpenAPI(env);

		assertEquals("ECD API", openAPI.getInfo().getTitle());
		assertEquals(3, openAPI.getServers().size());
		assertEquals("http://dev", openAPI.getServers().get(0).getUrl());
		assertEquals("http://demo", openAPI.getServers().get(2).getUrl());
		assertNotNull(openAPI.getComponents().getSecuritySchemes().get("my security"));
	}

	@Test
	void swaggerConfigFallsBackToLocalhost() {
		OpenAPI openAPI = new SwaggerConfig().customOpenAPI(new MockEnvironment());

		assertEquals("http://localhost:9090", openAPI.getServers().get(0).getUrl());
	}
}
