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
package com.iemr.ecd.service.quality;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.iemr.ecd.dao.GradeConfiguration;
import com.iemr.ecd.repo.call_conf_allocation.GradeConfigurationRepo;
import com.iemr.ecd.utils.advice.exception_handler.ECDException;

import jakarta.transaction.Transactional;

@Service
public class GradeConfigurationImpl {

	@Autowired
	private GradeConfigurationRepo gradeConfigurationRepo;

	@Transactional(rollbackOn = Exception.class)
	public String createGradeConfiguration(List<GradeConfiguration> gradeConfiguration) {
		try {
			gradeConfigurationRepo.saveAll(gradeConfiguration);

			Map<String, Object> responseMap = new HashMap<>();
			responseMap.put("response", "Grade Configuration Created Successfully");
			return new Gson().toJson(responseMap);
		} catch (Exception e) {
			throw new ECDException(e);
		}
	}

	public List<GradeConfiguration> getGradeConfigurationByPSMId(Integer psmId) {
		try {
			return gradeConfigurationRepo.findByPsmId(psmId);
		} catch (Exception e) {
			throw new ECDException(e);
		}
	}

	@Transactional(rollbackOn = Exception.class)
	public String updateGradeConfiguration(GradeConfiguration gradeConfiguration) {
		try {
			if (gradeConfiguration != null && gradeConfiguration.getId() != null)
				gradeConfigurationRepo.save(gradeConfiguration);

			Map<String, Object> responseMap = new HashMap<>();
			responseMap.put("response", "Grade Configuration Updated Successfully");
			return new Gson().toJson(responseMap);
		} catch (Exception e) {
			throw new ECDException(e);
		}

	}

}
