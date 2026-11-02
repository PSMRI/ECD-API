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

import org.junit.jupiter.api.Test;

import com.google.gson.JsonParser;

class InputMapperTest {

	static class Sample {
		String name;
	}

	@Test
	void gsonReturnsInstance() {
		assertNotNull(InputMapper.gson());
	}

	@Test
	void fromJsonStringDeserializes() throws Exception {
		Sample sample = InputMapper.gson().fromJson("{\"name\":\"abc\"}", Sample.class);
		assertEquals("abc", sample.name);
	}

	@Test
	void fromJsonElementDeserializes() throws Exception {
		Sample sample = InputMapper.gson().fromJson(JsonParser.parseString("{\"name\":\"def\"}"), Sample.class);
		assertEquals("def", sample.name);
	}
}
