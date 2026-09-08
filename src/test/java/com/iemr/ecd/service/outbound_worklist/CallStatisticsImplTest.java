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
package com.iemr.ecd.service.outbound_worklist;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.iemr.ecd.dao.associate.Bencall;
import com.iemr.ecd.dto.associate.CallStatisticsDTO;
import com.iemr.ecd.repo.call_conf_allocation.CallStatisticsRepo;
import com.iemr.ecd.utils.advice.exception_handler.ECDException;

@ExtendWith(MockitoExtension.class)
class CallStatisticsImplTest {

	@Mock
	private CallStatisticsRepo callStatisticsRepo;

	@InjectMocks
	private CallStatisticsImpl service;

	private Bencall call(Boolean answered, Boolean verified) {
		Bencall bencall = new Bencall();
		bencall.setIsCallAnswered(answered);
		bencall.setIsCallVerified(verified);
		return bencall;
	}

	@Test
	void countsAnsweredAndVerifiedCalls() {
		when(callStatisticsRepo.getCallCurrentDateStatistics("agent-1")).thenReturn(
				List.of(call(true, true), call(true, false), call(false, true), call(null, null)));

		CallStatisticsDTO statistics = service.getCallStatisticsByAgentId("agent-1");

		assertEquals(2, statistics.getTotalCallsAnswered());
		assertEquals(2, statistics.getTotalCallsVerified());
	}

	@Test
	void returnsEmptyStatisticsWhenNoCalls() {
		when(callStatisticsRepo.getCallCurrentDateStatistics("agent-1")).thenReturn(List.of());

		CallStatisticsDTO statistics = service.getCallStatisticsByAgentId("agent-1");

		assertNull(statistics.getTotalCallsAnswered());
		assertNull(statistics.getTotalCallsVerified());
	}

	@Test
	void returnsEmptyStatisticsWhenRepositoryReturnsNull() {
		when(callStatisticsRepo.getCallCurrentDateStatistics("agent-1")).thenReturn(null);

		assertNull(service.getCallStatisticsByAgentId("agent-1").getTotalCallsAnswered());
	}

	@Test
	void wrapsRepositoryFailure() {
		when(callStatisticsRepo.getCallCurrentDateStatistics(anyString())).thenThrow(new IllegalStateException("db"));

		assertThrows(ECDException.class, () -> service.getCallStatisticsByAgentId("agent-1"));
	}
}
