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
import com.iemr.ecd.dao.SampleSelectionConfiguration;
import com.iemr.ecd.repo.call_conf_allocation.SampleSelectionConfigurationRepo;
import com.iemr.ecd.utils.advice.exception_handler.ECDException;

import jakarta.transaction.Transactional;

@Service
public class SampleSelectionConfigurationImpl {
	@Autowired
	private SampleSelectionConfigurationRepo sampleSelectionConfigurationRepo;

	@Transactional(rollbackOn = Exception.class)
	public String createSampleSelectionConfiguration(List<SampleSelectionConfiguration> sampleSelectionConfiguration) {
		try {
			sampleSelectionConfigurationRepo.saveAll(sampleSelectionConfiguration);

			Map<String, Object> responseMap = new HashMap<>();
			responseMap.put("response", "Sample Selection Configuration Created Successfully");
			return new Gson().toJson(responseMap);
		} catch (Exception e) {
			throw new ECDException(e);
		}

	}

	public List<SampleSelectionConfiguration> getSampleSelectionConfigurationByPSMId(Integer psmId) {
		try {
			return sampleSelectionConfigurationRepo.findByPsmId(psmId);
		} catch (Exception e) {
			throw new ECDException(e);
		}
	}

	@Transactional(rollbackOn = Exception.class)
	public String updateSampleSelectionConfiguration(SampleSelectionConfiguration sampleSelectionConfiguration) {
		try {
			if (sampleSelectionConfiguration != null && sampleSelectionConfiguration.getId() != null)
				sampleSelectionConfigurationRepo.save(sampleSelectionConfiguration);

			Map<String, Object> responseMap = new HashMap<>();
			responseMap.put("response", "Sample Selection Configuration Upated Sucessfully");
			return new Gson().toJson(responseMap);
		} catch (Exception e) {
			throw new ECDException(e);
		}

	}

}
