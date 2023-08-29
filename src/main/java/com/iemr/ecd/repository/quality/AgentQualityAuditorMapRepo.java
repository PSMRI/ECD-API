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
package com.iemr.ecd.repository.quality;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.iemr.ecd.dao.AgentQualityAuditorMap;
import com.iemr.ecd.dto.BeneficiaryCasesheetDTO;

@Repository
public interface AgentQualityAuditorMapRepo extends CrudRepository<AgentQualityAuditorMap, Integer> {

	List<AgentQualityAuditorMap> findByPsmId(Integer psmId);

	@Query(value = " CALL Pr_QualityAuditorWorklist(:fDate, :tDate,:psmId, :langId, :agentId, :roleId, :isValid, :cycleId, :prevCycleFromDate, :prevCycleToDate ) ", nativeQuery = true)
	public List<String[]> getQualityAuditorWorklist(@Param("fDate") Timestamp fDate, @Param("tDate") Timestamp tDate,
			@Param("psmId") Integer psmId, @Param("langId") Integer langId, @Param("agentId") Integer agentId,
			@Param("roleId") Integer roleId, @Param("isValid") Boolean isValid, @Param("cycleId") Integer cycleId,
			@Param("prevCycleFromDate") Timestamp prevCycleFromDate,
			@Param("prevCycleToDate") Timestamp prevCycleToDate);

	@Query(value = " CALL db_iemr.Pr_BeneficiaryCasesheet(:benCallId) ", nativeQuery = true)
	public List<String[]> getBeneficiaryCasesheet(@Param("benCallId") Long benCallId);

	@Query(value = " CALL db_iemr.Pr_BeneficiaryCallResponse(:benCallId) ", nativeQuery = true)
	public List<String[]> getBeneficiaryCallResponse(@Param("benCallId") Long benCallId);

}
