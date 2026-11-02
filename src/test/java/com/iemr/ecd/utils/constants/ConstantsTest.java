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
package com.iemr.ecd.utils.constants;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class ConstantsTest {

	@Test
	void constantValuesAreStable() {
		assertEquals("allocated", Constants.ALLOCATED);
		assertEquals("unallocated", Constants.UNALLOCATED);
		assertEquals("Mother", Constants.MOTHER);
		assertEquals("Child", Constants.CHILD);
		assertEquals("ANM", Constants.ANM);
		assertEquals("ASSOCIATE", Constants.ASSOCIATE);
		assertEquals("open", Constants.OPEN);
		assertEquals("Completed", Constants.COMPLETED);
		assertEquals("T00:00:00+05:30", Constants.TIME_FORMAT_START_TIME);
		assertEquals("T23:59:59+05:30", Constants.TIME_FORMAT_END_TIME);
		assertEquals("T", Constants.T);
		assertEquals("from date / to date is null", Constants.FROM_DATE_TO_DATE_IS_NULL);
		assertEquals("Jwttoken", Constants.JWT_TOKEN);
		assertEquals("User-Agent", Constants.USER_AGENT);
		assertEquals("okhttp", Constants.OKHTTP);
	}

	@Test
	void callNotAnsweredReasonsAreListed() {
		assertEquals(7, Constants.REASONFORCALLNOTANSWERED.size());
		assertTrue(Constants.REASONFORCALLNOTANSWERED.contains("Invalid number"));
		assertTrue(Constants.REASONFORCALLNOTANSWERED.contains("Call not connected"));
	}
}
