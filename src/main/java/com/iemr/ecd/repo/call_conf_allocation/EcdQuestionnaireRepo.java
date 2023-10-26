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

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.iemr.ecd.dao.Questionnaire;

@Repository
public interface EcdQuestionnaireRepo extends CrudRepository<Questionnaire, Integer> {

	List<Questionnaire> findByPsmIdOrderByLastModDateDesc(Integer psmId);

	List<Questionnaire> findByPsmIdAndDeletedOrderByLastModDateDesc(Integer psmId, Boolean deleted);

	Questionnaire findByQuestionnaireId(int qId);

	@Query(value = " select q.* from m_questionnaire q "
			+ " left join m_sectionqamapping s on s.questionid=q.questionid and s.ProviderServiceMapID=q.ProviderServiceMapID"
			+ " where q.deleted is false and s.sectionid is null AND q.ProviderServiceMapID =:psmId ORDER BY q.lastModDate DESC", nativeQuery = true)
	public List<Questionnaire> getUnMappedQuestionnairesByPSMId(@Param("psmId") Integer psmId);

	@Query(value = " SELECT q.*\r\n" + "FROM m_questionnaire q\r\n"
			+ "LEFT JOIN m_sectionqamapping s ON s.questionid = q.questionid AND s.ProviderServiceMapID = q.ProviderServiceMapID\r\n"
			+ "WHERE q.deleted = false\r\n" + "  AND q.ProviderServiceMapID = :psmId\r\n"
			+ "  AND q.questionid NOT IN (\r\n" + "    SELECT questionid\r\n" + "    FROM m_sectionqamapping\r\n"
			+ "    WHERE sectionid = :sectionId\r\n" + "      AND ProviderServiceMapID = q.ProviderServiceMapID\r\n )"
			+ " ORDER BY q.lastModDate DESC  ", nativeQuery = true)
	public List<Questionnaire> getUnMappedQuestionnaires(@Param("psmId") Integer psmId,
			@Param("sectionId") Integer sectionId);
}
