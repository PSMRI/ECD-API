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
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

class UserAgentContextTest {

	@AfterEach
	void tearDown() {
		UserAgentContext.clear();
	}

	@Test
	void setAndGetUserAgent() {
		UserAgentContext.setUserAgent("okhttp/4.9.0");
		assertEquals("okhttp/4.9.0", UserAgentContext.getUserAgent());
	}

	@Test
	void clearRemovesUserAgent() {
		UserAgentContext.setUserAgent("okhttp/4.9.0");
		UserAgentContext.clear();
		assertNull(UserAgentContext.getUserAgent());
	}

	@Test
	void constructorIsAccessible() {
		assertNull(new UserAgentContext().getUserAgent());
	}
}
