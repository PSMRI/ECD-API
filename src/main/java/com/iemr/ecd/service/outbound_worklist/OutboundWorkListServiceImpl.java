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

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iemr.ecd.dao_temp.FetchChildOutboundWorklist;
import com.iemr.ecd.dao_temp.FetchMotherOutboundWorklist;
import com.iemr.ecd.repo.call_conf_allocation.OutboundCallsRepo;
import com.iemr.ecd.utils.advice.exception_handler.ECDException;

@Service
public class OutboundWorkListServiceImpl {

	@Autowired
	private OutboundCallsRepo outboundCallsRepo;

	// mother work-list
	public List<FetchMotherOutboundWorklist> getMotherWorkList(Integer userId) {
		try {
			List<String[]> motherList = outboundCallsRepo.getAgentAllocatedMotherList(userId);

			return getMotherDtoList(motherList);
		} catch (Exception e) {
			throw new ECDException(e);
		}

	}

	private List<FetchMotherOutboundWorklist> getMotherDtoList(List<String[]> motherSpData) throws Exception {
		List<FetchMotherOutboundWorklist> motherList = new ArrayList<>();
		FetchMotherOutboundWorklist obj;
		if (motherSpData != null && motherSpData.size() > 0) {
			for (String[] strArr : motherSpData) {
				try {
					obj = new FetchMotherOutboundWorklist();
					if (strArr[0] != null)
						obj.setObCallId(Long.valueOf(strArr[0]));
					if (strArr[1] != null)
						obj.setBeneficiaryRegId(Long.valueOf(strArr[1]));
					if (strArr[2] != null)
						obj.setAllocatedUserId(Integer.valueOf(strArr[2]));
					if (strArr[3] != null)
						obj.setProviderServiceMapId(Integer.valueOf(strArr[3]));
					if (strArr[4] != null)
						obj.setOutboundCallType(strArr[4]);
					if (strArr[5] != null)
						obj.setCallDateFrom(Timestamp.valueOf(strArr[5]));
					if (strArr[6] != null)
						obj.setNoOfTrials(Integer.valueOf(strArr[6]));
					if (strArr[7] != null)
						obj.setDisplayOBCallType(strArr[7]);
					if (strArr[8] != null)
						obj.setName(String.valueOf(strArr[8]));
					if (strArr[9] != null)
						obj.setMCTSIdNo((strArr[9]));
					if (strArr[10] != null)
						obj.setPhoneNoOfWhom(strArr[10]);
					if (strArr[11] != null)
						obj.setWhomPhoneNo(strArr[11]);
					if (strArr[12] != null)
						obj.setHighRisk(Boolean.valueOf(strArr[12]));

					if (strArr[13] != null)
						obj.setHusbandName(strArr[13]);
					if (strArr[14] != null)
						obj.setAddress(strArr[14]);
					if (strArr[15] != null)
						obj.setHealthBlock(strArr[15]);
					if (strArr[16] != null)
						obj.setPhcName(strArr[16]);
					if (strArr[17] != null)
						obj.setSubFacility(strArr[17]);
					if (strArr[18] != null) {
						obj.setLmpDate(Timestamp.valueOf(strArr[18]));
					}
					if (strArr[19] != null)
						obj.setAshaName(strArr[19]);
					if (strArr[20] != null)
						obj.setAnmName(strArr[20]);
					if (strArr[21] != null) {
						obj.setNextCallDate(Timestamp.valueOf(strArr[21]));
					}
					if (strArr[22] != null)
						obj.setLapseTime(strArr[22] + " days");
					if (strArr[23] != null) {
						obj.setRecordUploadDate(Timestamp.valueOf(strArr[23]));
					}
					if (strArr[24] != null) {
						obj.setEdd(Timestamp.valueOf(strArr[24]));
					}

					// new columns in WorkList, 15042023
					if (strArr[25] != null) {
						obj.setGender(strArr[25]);
					}
					if (strArr[26] != null) {
						obj.setStateId(Integer.valueOf(strArr[26]));
					}
					if (strArr[27] != null) {
						obj.setStateName(strArr[27]);
					}
					if (strArr[28] != null) {
						obj.setDistrictId(Integer.valueOf(strArr[28]));
					}
					if (strArr[29] != null) {
						obj.setDistrictName(strArr[29]);
					}
					if (strArr[30] != null) {
						obj.setBlockId(Integer.valueOf(strArr[30]));
					}
					if (strArr[31] != null) {
						obj.setBlockName(strArr[31]);
					}
					if (strArr[32] != null) {
						obj.setDistrictBranchId(Integer.valueOf(strArr[32]));
					}
					if (strArr[33] != null) {
						obj.setVillageName(strArr[33]);
					}
					if (strArr[34] != null) {
						obj.setAlternatePhoneNo(strArr[34]);
					}
					if (strArr[35] != null) {
						obj.setAshaPhoneNo(strArr[35]);
					}
					if (strArr[36] != null) {
						obj.setAnmPhoneNo(strArr[36]);
					}

					motherList.add(obj);
				} catch (Exception e) {
					// System.out.println(e.getLocalizedMessage());
					// log
				}
			}
		}
		return motherList;
	}

	// child work-list
	public List<FetchChildOutboundWorklist> getChildWorkList(Integer userId) {
		try {
			List<String[]> childList = outboundCallsRepo.getAgentAllocatedChildList(userId);

			return getChildDtoList(childList);
		} catch (Exception e) {
			throw new ECDException(e);
		}

	}

	private List<FetchChildOutboundWorklist> getChildDtoList(List<String[]> childSpData) throws Exception {
		List<FetchChildOutboundWorklist> childList = new ArrayList<>();
		FetchChildOutboundWorklist obj;

		if (childSpData != null && childSpData.size() > 0) {
			for (String[] strArr : childSpData) {
				try {
					obj = new FetchChildOutboundWorklist();
					if (strArr[0] != null)
						obj.setBeneficiaryRegId(Long.valueOf(strArr[0]));
					if (strArr[1] != null)
						obj.setOutboundCallType(strArr[1]);
					if (strArr[2] != null)
						obj.setDisplayOBCallType(strArr[2]);
					if (strArr[3] != null)
						obj.setCallDateFrom(Timestamp.valueOf(strArr[3]));
					if (strArr[4] != null)
						obj.setNoOfTrials(Integer.valueOf(strArr[4]));
					if (strArr[5] != null)
						obj.setMCTSIdNoChildId(strArr[5]);
					if (strArr[6] != null)
						obj.setChildName(strArr[6]);
					if (strArr[7] != null)
						obj.setMotherId(strArr[7]);
					if (strArr[8] != null)
						obj.setMotherName(strArr[8]);
					if (strArr[9] != null)
						obj.setPhoneNoOf(strArr[9]);
					if (strArr[10] != null)
						obj.setPhoneNo(strArr[10]);
					if (strArr[11] != null)
						obj.setObCallId(Long.valueOf(strArr[11]));

					if (strArr[12] != null)
						obj.setAddress(strArr[12]);
					if (strArr[13] != null)
						obj.setHealthBlock(strArr[13]);
					if (strArr[14] != null)
						obj.setPhcName(strArr[14]);
					if (strArr[15] != null)
						obj.setSubFacility(strArr[15]);
					if (strArr[16] != null)
						obj.setAshaName(strArr[16]);
					if (strArr[17] != null)
						obj.setAnmName(strArr[17]);

					if (strArr[18] != null)
						obj.setDob(Timestamp.valueOf(strArr[18]));
					if (strArr[19] != null)
						obj.setNextCallDate(Timestamp.valueOf(strArr[19]));

					if (strArr[20] != null)
						obj.setIsHrni(Boolean.valueOf(strArr[20]));
					if (strArr[21] != null)
						obj.setLapseTime(strArr[21] + " days");
					if (strArr[22] != null)
						obj.setRecordUploadDate(Timestamp.valueOf(strArr[22]));

					// new columns in WorkList, 15042023
					if (strArr[23] != null) {
						obj.setFatherName(strArr[23]);
					}
					if (strArr[24] != null) {
						obj.setGender(strArr[24]);
					}
					if (strArr[25] != null) {
						obj.setStateId(Integer.valueOf(strArr[25]));
					}
					if (strArr[26] != null) {
						obj.setStateName(strArr[26]);
					}
					if (strArr[27] != null) {
						obj.setDistrictId(Integer.valueOf(strArr[27]));
					}
					if (strArr[28] != null) {
						obj.setDistrictName(strArr[28]);
					}
					if (strArr[29] != null) {
						obj.setBlockId(Integer.valueOf(strArr[29]));
					}
					if (strArr[30] != null) {
						obj.setBlockName(strArr[30]);
					}
					if (strArr[31] != null) {
						obj.setDistrictBranchId(Integer.valueOf(strArr[31]));
					}
					if (strArr[32] != null) {
						obj.setVillageName(strArr[33]);
					}
					if (strArr[33] != null) {
						obj.setAlternatePhoneNo(strArr[33]);
					}
					if (strArr[34] != null) {
						obj.setAshaPhoneNo(strArr[34]);
					}
					if (strArr[35] != null) {
						obj.setAnmPhoneNo(strArr[35]);
					}

					childList.add(obj);
				} catch (Exception e) {
					throw new ECDException(e);
				}
			}
		}
		return childList;
	}

}
