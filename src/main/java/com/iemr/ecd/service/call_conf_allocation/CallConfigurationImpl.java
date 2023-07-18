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
package com.iemr.ecd.service.call_conf_allocation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.iemr.ecd.dao.CallConfiguration;
import com.iemr.ecd.dao.CallSectionMapping;
import com.iemr.ecd.dao.V_GetCallSectionMapping;
import com.iemr.ecd.dto.CallSectionMappingDTO;
import com.iemr.ecd.repo.call_conf_allocation.CallConfigurationRepo;
import com.iemr.ecd.repo.call_conf_allocation.CallSectionMappingRepo;
import com.iemr.ecd.repo.call_conf_allocation.V_GetCallSectionMappingRepo;
import com.iemr.ecd.utils.advice.exception_handler.ECDException;
import com.iemr.ecd.utils.advice.exception_handler.InvalidRequestException;

import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationRegistry;
import jakarta.transaction.Transactional;

@Service
public class CallConfigurationImpl {

	@Autowired
	private ObservationRegistry registry;

	@Autowired
	private CallConfigurationRepo callConfigurationRepo;
	@Autowired
	private CallSectionMappingRepo callSectionMappingRepo;
	@Autowired
	private V_GetCallSectionMappingRepo v_GetCallSectionMappingRepo;

	@Transactional(rollbackOn = Exception.class)
	public List<CallConfiguration> createCallConfigurations(List<CallConfiguration> callConfigurations) {
		try {
			if (callConfigurations != null && callConfigurations.size() > 0) {

				long currTimeMilli = System.currentTimeMillis();
				for (CallConfiguration callConfiguration : callConfigurations) {
					callConfiguration.setConfigId(currTimeMilli);
				}
			}

			return Observation.createNotStarted("addConfiguration", registry)
					.observe(() -> (List<CallConfiguration>) callConfigurationRepo.saveAll(callConfigurations));
		} catch (Exception e) {
			throw new ECDException(e);
		}

	}

	public List<CallConfiguration> getCallConfigurations() {
		try {
			return Observation.createNotStarted("getConfigurations", registry)
					.observe(() -> (List<CallConfiguration>) callConfigurationRepo.findTop10ByDeleted(false));
		} catch (Exception e) {
			throw new ECDException(e);
		}

	}

	public CallConfiguration getCallConfigurationById(Long callConfigId) {
		try {
			return Observation.createNotStarted("getConfigurationById", registry)
					.observe(() -> callConfigurationRepo.findByCallConfigId(callConfigId));
		} catch (Exception e) {
			throw new ECDException(e);
		}

	}

	public List<CallConfiguration> getCallConfigurationByPSMID(Integer psmId) {
		try {
			return Observation.createNotStarted("getConfigurationByPSMID", registry)
					.observe(() -> (List<CallConfiguration>) callConfigurationRepo.findByPsmIdAndDeleted(psmId, false));
		} catch (Exception e) {
			throw new ECDException(e);
		}

	}

	@Transactional(rollbackOn = Exception.class)
	public List<CallConfiguration> updateCallConfigurations(List<CallConfiguration> callConfigurations) {
		try {
			return Observation.createNotStarted("updateConfigurations", registry)
					.observe(() -> (List<CallConfiguration>) callConfigurationRepo.saveAll(callConfigurations));
		} catch (Exception e) {
			throw new ECDException(e);
		}

	}

	@Transactional(rollbackOn = Exception.class)
	public String mapCallAndSection(CallSectionMappingDTO callSectionMappingDTO) {
		try {
			if (callSectionMappingDTO != null && callSectionMappingDTO.getSections() != null
					&& callSectionMappingDTO.getSections().size() > 0) {
				List<CallSectionMapping> callSecMapList = callSectionMappingDTO.getSections();
				for (CallSectionMapping obj : callSecMapList) {
					obj.setCallConfigId(callSectionMappingDTO.getCallConfigId());

				}

				callSectionMappingRepo.saveAll(callSecMapList);
			} else
				throw new InvalidRequestException("Invalid request", "please pass valid call-section mapping info");

			Map<String, Object> responseMap = new HashMap<>();
			responseMap.put("response", "Call section mapping done successfully");
			return new Gson().toJson(responseMap);
		} catch (Exception e) {
			throw new ECDException(e);
		}

	}

	public List<V_GetCallSectionMapping> getCallAndSectionMapByPsmIdAndCallConfigId(Integer psmId,
			Integer callConfigId) {
		try {
			List<V_GetCallSectionMapping> responseList = new ArrayList<>();

			if (callConfigId != null && callConfigId > 0) {
				List<CallSectionMapping> callSectionMappingList = callSectionMappingRepo
						.findByPsmIdAndCallConfigIdAndDeleted(psmId, callConfigId, false);

				if (callSectionMappingList != null && callSectionMappingList.size() > 0) {
					for (CallSectionMapping callSectionMapping : callSectionMappingList) {
						List<V_GetCallSectionMapping> mappingList = v_GetCallSectionMappingRepo
								.findByCallConfigIdAndSectionIdAndPsmId(callSectionMapping.getCallConfigId(),
										callSectionMapping.getSectionId(), callSectionMapping.getPsmId());
						if (mappingList != null && mappingList.size() > 0) {
							responseList.addAll(mappingList);
						}

					}
				}
			} else {
				List<CallSectionMapping> callSectionMappingList = callSectionMappingRepo.findByPsmIdAndDeleted(psmId,
						false);

				if (callSectionMappingList != null && callSectionMappingList.size() > 0) {
					for (CallSectionMapping callSectionMapping : callSectionMappingList) {
						List<V_GetCallSectionMapping> mappingList = v_GetCallSectionMappingRepo.findBySectionIdAndPsmId(
								callSectionMapping.getSectionId(), callSectionMapping.getPsmId());
						if (mappingList != null && mappingList.size() > 0) {
							responseList.addAll(mappingList);
						}

					}
				}
			}
			return responseList;
		} catch (Exception e) {
			throw new ECDException(e);
		}
	}
}
