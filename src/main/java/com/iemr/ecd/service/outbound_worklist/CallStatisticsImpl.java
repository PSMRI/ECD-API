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

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iemr.ecd.dao.associate.Bencall;
import com.iemr.ecd.dto.associate.CallStatisticsDTO;
import com.iemr.ecd.repo.call_conf_allocation.CallStatisticsRepo;
import com.iemr.ecd.utils.advice.exception_handler.ECDException;

@Service
public class CallStatisticsImpl {

	@Autowired
	private CallStatisticsRepo callStatisticsRepo;

	public CallStatisticsDTO getCallStatisticsByAgentId(String agentId) {
		int callVerifiedCount = 0;
		int callAnsweredCount = 0;

		try {
			CallStatisticsDTO callStatistics = new CallStatisticsDTO();

			List<Bencall> data = callStatisticsRepo.getCallCurrentDateStatistics(agentId);

			if (data != null && data.size() > 0) {
//			callStatistics.setTotalCalls(data.size());
				for (Bencall obj : data) {
					if (obj.getIsCallAnswered() != null && obj.getIsCallAnswered() == true) {
						callAnsweredCount++;
					}
					if (obj.getIsCallVerified() != null && obj.getIsCallVerified() == true) {
						callVerifiedCount++;
					}
				}
				callStatistics.setTotalCallsAnswered(callAnsweredCount);
				callStatistics.setTotalCallsVerified(callVerifiedCount);
			}

			return callStatistics;
		} catch (Exception e) {
			throw new ECDException(e);
		}

	}

}
