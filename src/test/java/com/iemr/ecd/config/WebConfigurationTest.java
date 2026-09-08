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
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

class WebConfigurationTest {

	@SuppressWarnings("unchecked")
	@Test
	void addCorsMappingsRegistersTrimmedOriginPatterns() {
		WebConfiguration configuration = new WebConfiguration();
		ReflectionTestUtils.setField(configuration, "allowedOrigins",
				"http://localhost:4200, https://app.example.com");
		CorsRegistry registry = new CorsRegistry();

		configuration.addCorsMappings(registry);

		Map<String, CorsConfiguration> registered = (Map<String, CorsConfiguration>) ReflectionTestUtils
				.invokeMethod(registry, "getCorsConfigurations");
		CorsConfiguration config = registered.get("/**");
		assertEquals(List.of("http://localhost:4200", "https://app.example.com"), config.getAllowedOriginPatterns());
		assertEquals(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"), config.getAllowedMethods());
		assertEquals(List.of("*"), config.getAllowedHeaders());
		assertEquals(List.of("Authorization", "Jwttoken"), config.getExposedHeaders());
		assertTrue(config.getAllowCredentials());
		assertEquals(3600L, config.getMaxAge());
	}
}
