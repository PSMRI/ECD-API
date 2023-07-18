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

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.iemr.ecd.dao.associate.ECDCallResponse;

@Repository
public interface ECDCallResponseRepo extends JpaRepository<ECDCallResponse, Long> {

	@Query(value = "call Pr_GetHRPDetails(:motherId, :childId)", nativeQuery = true)
	List<String[]> getHrpHrniDetailsOld(@Param("motherId") Long motherId, @Param("childId") Long childId);

	@Query(value = " SELECT t FROM ECDCallResponse t WHERE t.motherId=:motherId AND t.reasonsForHrpDB IS NOT NULL ORDER BY 1 DESC ")
	Page<ECDCallResponse> getHrpDetailsMother(Pageable pageable, @Param("motherId") Long motherId);

	@Query(value = " SELECT t FROM ECDCallResponse t WHERE t.childId=:childId AND t.reasonsForHrniDB IS NOT NULL ORDER BY 1 DESC ")
	Page<ECDCallResponse> getHrniDetailsChild(Pageable pageable, @Param("childId") Long childId);

//	@Query(value = "call Pr_GetHRPDetails(:childId)", nativeQuery = true)
//	List<String[]> getHrniDetailsForChild(@Param("childId") Long childId);

}
