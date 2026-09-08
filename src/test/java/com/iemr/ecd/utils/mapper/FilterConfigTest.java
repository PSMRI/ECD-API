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
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.core.Ordered;
import org.springframework.test.util.ReflectionTestUtils;

class FilterConfigTest {

	@Test
	void jwtUserIdValidationFilterRegistersFilterForAllPaths() {
		FilterConfig config = new FilterConfig();
		ReflectionTestUtils.setField(config, "allowedOrigins", "http://localhost:4200");
		JwtAuthenticationUtil util = Mockito.mock(JwtAuthenticationUtil.class);

		FilterRegistrationBean<JwtUserIdValidationFilter> bean = config.jwtUserIdValidationFilter(util);

		assertNotNull(bean.getFilter());
		assertEquals(Ordered.HIGHEST_PRECEDENCE, bean.getOrder());
		assertTrue(bean.getUrlPatterns().contains("/*"));
	}
}
