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
package com.iemr.ecd.repository.ecd;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.iemr.ecd.dao.V_GetSectionQuestionMappingAssociates;

public interface V_GetSectionQuestionMappingAssociatesRepo
		extends CrudRepository<V_GetSectionQuestionMappingAssociates, Long> {
	List<V_GetSectionQuestionMappingAssociates> findBySectionid(Integer sectionId);

	List<V_GetSectionQuestionMappingAssociates> findByPsmId(Integer psmId);

//	@Query(value = "SELECT * FROM V_GetSectionQuestionMappingAssociates "
//			+ "WHERE psmId =:psmId AND callConfigId =:callConfigId AND role LIKE %:role%", nativeQuery = true)
//	List<V_GetSectionQuestionMappingAssociates> findByPsmIdAndCallConfigIdAndRole(@Param("psmId") Integer psmId,
//			@Param("callConfigId") Integer callConfigId, @Param("role") String role);

	@Query("SELECT t FROM V_GetSectionQuestionMappingAssociates t "
			+ "WHERE t.psmId =:psmId AND t.callConfigId =:callConfigId AND t.role LIKE %:role%")
	List<V_GetSectionQuestionMappingAssociates> findByPsmIdAndCallConfigIdAndRole(Integer psmId, Integer callConfigId,
			String role);

}
