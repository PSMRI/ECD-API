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
package com.iemr.ecd.repo.call_conf_allocation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.iemr.ecd.dao.CallConfiguration;

@Repository
public interface CallConfigurationRepo extends CrudRepository<CallConfiguration, Long> {

	List<CallConfiguration> findTop10ByDeleted(Boolean deleted);

	CallConfiguration findByCallConfigId(Long callConfigId);

	List<CallConfiguration> findByPsmIdAndDeleted(Integer psmId, Boolean deleted);

	@Query(" SELECT t FROM CallConfiguration AS t WHERE t.psmId=:psmId "
			+ " AND t.effectiveStartDate <= current_date() AND t.effectiveEndDate >= current_date() "
			+ " AND t.callType !='Introductory' AND t.deleted = false ")
	public ArrayList<CallConfiguration> getCallConfiguration(@Param("psmId") Integer psmId);

	@Query(" SELECT t FROM CallConfiguration AS t WHERE t.psmId=:psmId AND t.callType=:callType "
			+ " AND t.effectiveStartDate <= current_date() AND t.effectiveEndDate >= current_date() AND t.deleted = false ")
	public CallConfiguration getCallConfigurationByProviderAndCallType(@Param("psmId") Integer psmId,
			@Param("callType") String callType);

}
