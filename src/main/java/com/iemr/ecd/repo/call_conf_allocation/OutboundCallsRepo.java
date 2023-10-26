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

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.iemr.ecd.dao.associate.OutboundCalls;

import jakarta.transaction.Transactional;

@Repository
public interface OutboundCallsRepo extends CrudRepository<OutboundCalls, Long> {

	OutboundCalls findByObCallId(Long obCallId);

	@Query(value = "call PR_FetchECDMotherOutboundWorklist(:allocatedUserID)", nativeQuery = true)
	List<String[]> getAgentAllocatedMotherList(@Param("allocatedUserID") Integer allocatedUserID);

	@Query(value = "call PR_FetchECDChildOutboundWorklist(:allocatedUserID)", nativeQuery = true)
	List<String[]> getAgentAllocatedChildList(@Param("allocatedUserID") Integer allocatedUserID);

	@Query("SELECT ob FROM OutboundCalls ob WHERE ob.motherId = :motherId AND ob.callStatus = 'completed'")
	List<OutboundCalls> getCallHistoryForMother(@Param("motherId") Long motherId);

	@Query("SELECT ob FROM OutboundCalls ob WHERE ob.childId = :childId AND ob.callStatus = 'completed'")
	List<OutboundCalls> getCallHistoryForChild(@Param("childId") Long childId);

	@Query(value = "call Pr_GetCallHistory(:obCallId)", nativeQuery = true)
	List<String[]> getBeneficiaryCallDetails(@Param("obCallId") Long obCallId);

//	@Query(value = " SELECT t FROM OutboundCalls AS t WHERE t.allocationStatus =:allocationStatus AND "
//			+ " t.psmId=:psmId AND t.callDateFrom >= :fDate  AND t.callDateTo <= :tDate AND "
//			+ " t.childId IS NULL AND t.motherId IS NOT NULL AND (t.isHighRisk = 0 OR t.isHighRisk IS NULL ) ")
//	Page<OutboundCalls> getMotherRecordsForANM(Pageable pageable, @Param("allocationStatus") String allocationStatus,
//			@Param("psmId") Integer psmId, @Param("fDate") Timestamp fDate, @Param("tDate") Timestamp tDate);

	@Query(value = " SELECT t FROM OutboundCalls AS t WHERE t.allocationStatus =:allocationStatus AND "
			+ " t.psmId=:psmId AND ((:fDate between t.callDateFrom AND t.callDateTo) OR (:tDate between t.callDateFrom AND t.callDateTo)) AND "
			+ " t.childId IS NULL AND t.motherId IS NOT NULL AND (t.isHighRisk = 0 OR t.isHighRisk IS NULL ) ")
	Page<OutboundCalls> getMotherRecordsForANM(Pageable pageable, @Param("allocationStatus") String allocationStatus,
			@Param("psmId") Integer psmId, @Param("fDate") Timestamp fDate, @Param("tDate") Timestamp tDate);

//	@Query(value = " SELECT t FROM OutboundCalls AS t WHERE t.allocationStatus =:allocationStatus AND "
//			+ " t.psmId=:psmId AND t.callDateFrom >= :fDate  AND t.callDateTo <= :tDate AND "
//			+ " t.childId IS NOT NULL AND (t.isHrni = 0 OR t.isHrni IS NULL ) ")
//	Page<OutboundCalls> getChildRecordsForANM(Pageable pageable, @Param("allocationStatus") String allocationStatus,
//			@Param("psmId") Integer psmId, @Param("fDate") Timestamp fDate, @Param("tDate") Timestamp tDate);

	@Query(value = " SELECT t FROM OutboundCalls AS t WHERE t.allocationStatus =:allocationStatus AND "
			+ " t.psmId=:psmId AND ((:fDate between t.callDateFrom AND t.callDateTo) OR (:tDate between t.callDateFrom AND t.callDateTo)) AND "
			+ " t.childId IS NOT NULL AND (t.isHrni = 0 OR t.isHrni IS NULL ) ")
	Page<OutboundCalls> getChildRecordsForANM(Pageable pageable, @Param("allocationStatus") String allocationStatus,
			@Param("psmId") Integer psmId, @Param("fDate") Timestamp fDate, @Param("tDate") Timestamp tDate);

//	@Query(value = " SELECT t FROM OutboundCalls AS t WHERE t.allocationStatus =:allocationStatus AND "
//			+ " t.psmId=:psmId AND t.callDateFrom >= :fDate  AND t.callDateTo <= :tDate AND "
//			+ " t.childId IS NULL AND t.motherId IS NOT NULL AND t.isHighRisk = 1 ")
//	Page<OutboundCalls> getMotherRecordsForMO(Pageable pageable, @Param("allocationStatus") String allocationStatus,
//			@Param("psmId") Integer psmId, @Param("fDate") Timestamp fDate, @Param("tDate") Timestamp tDate);
	@Query(value = " SELECT t FROM OutboundCalls AS t WHERE t.allocationStatus =:allocationStatus AND "
			+ " t.psmId=:psmId AND ((:fDate between t.callDateFrom AND t.callDateTo) OR (:tDate between t.callDateFrom AND t.callDateTo)) AND "
			+ " t.childId IS NULL AND t.motherId IS NOT NULL AND t.isHighRisk = 1 ")
	Page<OutboundCalls> getMotherRecordsForMO(Pageable pageable, @Param("allocationStatus") String allocationStatus,
			@Param("psmId") Integer psmId, @Param("fDate") Timestamp fDate, @Param("tDate") Timestamp tDate);

//	@Query(value = " SELECT t FROM OutboundCalls AS t WHERE t.allocationStatus =:allocationStatus AND "
//			+ " t.psmId=:psmId AND t.callDateFrom >= :fDate  AND t.callDateTo <= :tDate AND "
//			+ " t.childId IS NOT NULL AND t.isHrni = 1  ")
//	Page<OutboundCalls> getChildRecordsForMO(Pageable pageable, @Param("allocationStatus") String allocationStatus,
//			@Param("psmId") Integer psmId, @Param("fDate") Timestamp fDate, @Param("tDate") Timestamp tDate);
	@Query(value = " SELECT t FROM OutboundCalls AS t WHERE t.allocationStatus =:allocationStatus AND "
			+ " t.psmId=:psmId AND ((:fDate between t.callDateFrom AND t.callDateTo) OR (:tDate between t.callDateFrom AND t.callDateTo)) AND "
			+ " t.childId IS NOT NULL AND t.isHrni = 1  ")
	Page<OutboundCalls> getChildRecordsForMO(Pageable pageable, @Param("allocationStatus") String allocationStatus,
			@Param("psmId") Integer psmId, @Param("fDate") Timestamp fDate, @Param("tDate") Timestamp tDate);

	// count
	// un-allocated, mother low risk,
//	@Query(value = " SELECT COUNT(1) FROM OutboundCalls AS t WHERE t.allocationStatus =:allocationStatus AND "
//			+ " t.psmId=:psmId AND t.callDateFrom >= :fDate  AND t.callDateTo <= :tDate AND "
//			+ " t.childId IS NULL AND t.motherId IS NOT NULL  AND (t.isHighRisk = 0 OR t.isHighRisk IS NULL ) "
//			+ " AND t.phoneNumberType=:phoneNoType AND t.deleted = 0  ")
//	int getMotherUnAllocatedCountLR(@Param("allocationStatus") String allocationStatus, @Param("psmId") Integer psmId,
//			@Param("fDate") Timestamp fDate, @Param("tDate") Timestamp tDate, @Param("phoneNoType") String phoneNoType);
	@Query(value = " SELECT COUNT(1) FROM OutboundCalls AS t WHERE t.allocationStatus =:allocationStatus AND "
			+ " t.psmId=:psmId AND ((:fDate between t.callDateFrom AND t.callDateTo) OR (:tDate between t.callDateFrom AND t.callDateTo)) AND "
			+ " t.childId IS NULL AND t.motherId IS NOT NULL  AND (t.isHighRisk = 0 OR t.isHighRisk IS NULL ) "
			+ " AND t.phoneNumberType=:phoneNoType AND t.deleted = 0  ")
	int getMotherUnAllocatedCountLR(@Param("allocationStatus") String allocationStatus, @Param("psmId") Integer psmId,
			@Param("fDate") Timestamp fDate, @Param("tDate") Timestamp tDate, @Param("phoneNoType") String phoneNoType);

	// un-allocated, child low risk,
//	@Query(value = " SELECT COUNT(1) FROM OutboundCalls AS t WHERE t.allocationStatus =:allocationStatus AND "
//			+ " t.psmId=:psmId AND t.callDateFrom >= :fDate  AND t.callDateTo <= :tDate AND "
//			+ " t.childId IS NOT NULL AND (t.isHrni = 0 OR t.isHrni IS NULL )  "
//			+ " AND t.phoneNumberType=:phoneNoType AND t.deleted = 0 ")
//	int getChildUnAllocatedCountLR(@Param("allocationStatus") String allocationStatus, @Param("psmId") Integer psmId,
//			@Param("fDate") Timestamp fDate, @Param("tDate") Timestamp tDate, @Param("phoneNoType") String phoneNoType);

	@Query(value = " SELECT COUNT(1) FROM OutboundCalls AS t WHERE t.allocationStatus =:allocationStatus AND "
			+ " t.psmId=:psmId AND ((:fDate between t.callDateFrom AND t.callDateTo) OR (:tDate between t.callDateFrom AND t.callDateTo)) AND "
			+ " t.childId IS NOT NULL AND (t.isHrni = 0 OR t.isHrni IS NULL )  "
			+ " AND t.phoneNumberType=:phoneNoType AND t.deleted = 0 ")
	int getChildUnAllocatedCountLR(@Param("allocationStatus") String allocationStatus, @Param("psmId") Integer psmId,
			@Param("fDate") Timestamp fDate, @Param("tDate") Timestamp tDate, @Param("phoneNoType") String phoneNoType);

	// un-allocated, mother high risk,
//	@Query(value = " SELECT count(1) FROM OutboundCalls AS t WHERE t.allocationStatus =:allocationStatus AND "
//			+ " t.psmId=:psmId AND t.callDateFrom >= :fDate  AND t.callDateTo <= :tDate AND "
//			+ " t.childId IS NULL AND t.motherId IS NOT NULL AND t.isHighRisk = 1 "
//			+ " AND t.phoneNumberType=:phoneNoType AND t.deleted = 0 ")
//	int getMotherUnAllocatedCountHR(@Param("allocationStatus") String allocationStatus, @Param("psmId") Integer psmId,
//			@Param("fDate") Timestamp fDate, @Param("tDate") Timestamp tDate, @Param("phoneNoType") String phoneNoType);
	@Query(value = " SELECT count(1) FROM OutboundCalls AS t WHERE t.allocationStatus =:allocationStatus AND "
			+ " t.psmId=:psmId AND ((:fDate between t.callDateFrom AND t.callDateTo) OR (:tDate between t.callDateFrom AND t.callDateTo)) AND "
			+ " t.childId IS NULL AND t.motherId IS NOT NULL AND t.isHighRisk = 1 "
			+ " AND t.phoneNumberType=:phoneNoType AND t.deleted = 0 ")
	int getMotherUnAllocatedCountHR(@Param("allocationStatus") String allocationStatus, @Param("psmId") Integer psmId,
			@Param("fDate") Timestamp fDate, @Param("tDate") Timestamp tDate, @Param("phoneNoType") String phoneNoType);

	// un-allocated, child high risk,
//	@Query(value = " SELECT COUNT(1) FROM OutboundCalls AS t WHERE t.allocationStatus =:allocationStatus AND "
//			+ " t.psmId=:psmId AND t.callDateFrom >= :fDate  AND t.callDateTo <= :tDate AND "
//			+ " t.childId IS NOT NULL AND t.isHrni = 1  " + " AND t.phoneNumberType=:phoneNoType AND t.deleted = 0 ")
//	int getChildUnAllocatedCountHR(@Param("allocationStatus") String allocationStatus, @Param("psmId") Integer psmId,
//			@Param("fDate") Timestamp fDate, @Param("tDate") Timestamp tDate, @Param("phoneNoType") String phoneNoType);
	@Query(value = " SELECT COUNT(1) FROM OutboundCalls AS t WHERE t.allocationStatus =:allocationStatus AND "
			+ " t.psmId=:psmId AND ((:fDate between t.callDateFrom AND t.callDateTo) OR (:tDate between t.callDateFrom AND t.callDateTo)) AND "
			+ " t.childId IS NOT NULL AND t.isHrni = 1  " + " AND t.phoneNumberType=:phoneNoType AND t.deleted = 0 ")
	int getChildUnAllocatedCountHR(@Param("allocationStatus") String allocationStatus, @Param("psmId") Integer psmId,
			@Param("fDate") Timestamp fDate, @Param("tDate") Timestamp tDate, @Param("phoneNoType") String phoneNoType);

	// allocated, mother record,
//	@Query(value = " SELECT COUNT(1) FROM OutboundCalls AS t WHERE t.allocationStatus =:allocationStatus AND "
//			+ " t.psmId=:psmId AND t.callDateFrom >= :fDate  AND t.callDateTo <= :tDate AND "
//			+ " t.childId IS NULL AND t.motherId IS NOT NULL " + " AND t.phoneNumberType=:phoneNoType ")
//	int getTotalAllocatedCountMother(@Param("allocationStatus") String allocationStatus, @Param("psmId") Integer psmId,
//			@Param("fDate") Timestamp fDate, @Param("tDate") Timestamp tDate, @Param("phoneNoType") String phoneNoType);
	@Query(value = " SELECT COUNT(1) FROM OutboundCalls AS t WHERE t.allocationStatus =:allocationStatus AND "
			+ " t.psmId=:psmId AND ((:fDate between t.callDateFrom AND t.callDateTo) OR (:tDate between t.callDateFrom AND t.callDateTo)) AND "
			+ " t.childId IS NULL AND t.motherId IS NOT NULL " + " AND t.phoneNumberType=:phoneNoType ")
	int getTotalAllocatedCountMother(@Param("allocationStatus") String allocationStatus, @Param("psmId") Integer psmId,
			@Param("fDate") Timestamp fDate, @Param("tDate") Timestamp tDate, @Param("phoneNoType") String phoneNoType);

	// allocated, child record,
//	@Query(value = " SELECT COUNT(1) FROM OutboundCalls AS t WHERE t.allocationStatus =:allocationStatus AND "
//			+ " t.psmId=:psmId AND t.callDateFrom >= :fDate  AND t.callDateTo <= :tDate  AND "
//			+ " t.childId IS NOT NULL  AND t.phoneNumberType=:phoneNoType ")
//	int getTotalAllocatedCountChild(@Param("allocationStatus") String allocationStatus, @Param("psmId") Integer psmId,
//			@Param("fDate") Timestamp fDate, @Param("tDate") Timestamp tDate, @Param("phoneNoType") String phoneNoType);
	@Query(value = " SELECT COUNT(1) FROM OutboundCalls AS t WHERE t.allocationStatus =:allocationStatus AND "
			+ " t.psmId=:psmId AND ((:fDate between t.callDateFrom AND t.callDateTo) OR (:tDate between t.callDateFrom AND t.callDateTo))  AND "
			+ " t.childId IS NOT NULL  AND t.phoneNumberType=:phoneNoType ")
	int getTotalAllocatedCountChild(@Param("allocationStatus") String allocationStatus, @Param("psmId") Integer psmId,
			@Param("fDate") Timestamp fDate, @Param("tDate") Timestamp tDate, @Param("phoneNoType") String phoneNoType);

	// users allocated calls
	@Query(value = " SELECT t FROM OutboundCalls AS t WHERE t.allocatedUserId=:allocatedUserId AND "
			+ " t.callStatus=:callStatus ")
	List<OutboundCalls> getAllocatedRecordsUser(Pageable pageable, @Param("allocatedUserId") Integer allocatedUserId,
			@Param("callStatus") String callStatus);

	// get users allocated calls count mother , not completed
//	@Query(value = " SELECT COUNT(1) FROM OutboundCalls AS t WHERE t.allocatedUserId=:allocatedUserId AND "
//			+ " t.callDateFrom >= :fDate  AND t.callDateTo <= :tDate AND  t.callStatus=:callStatus "
//			+ " AND t.phoneNumberType=:phoneNoType AND t.childId IS NULL AND t.motherId IS NOT NULL ")
//	int getAllocatedRecordsCountMotherUser(@Param("allocatedUserId") Integer allocatedUserId,
//			@Param("fDate") Timestamp fDate, @Param("tDate") Timestamp tDate, @Param("callStatus") String callStatus,
//			@Param("phoneNoType") String phoneNoType);
	@Query(value = " SELECT COUNT(1) FROM OutboundCalls AS t WHERE t.allocatedUserId=:allocatedUserId AND "
			+ " ((:fDate between t.callDateFrom AND t.callDateTo) OR (:tDate between t.callDateFrom AND t.callDateTo)) AND  t.callStatus=:callStatus "
			+ " AND t.phoneNumberType=:phoneNoType AND t.childId IS NULL AND t.motherId IS NOT NULL ")
	int getAllocatedRecordsCountMotherUser(@Param("allocatedUserId") Integer allocatedUserId,
			@Param("fDate") Timestamp fDate, @Param("tDate") Timestamp tDate, @Param("callStatus") String callStatus,
			@Param("phoneNoType") String phoneNoType);

	// get users allocated calls count mother , not completed
//	@Query(value = " SELECT COUNT(1) FROM OutboundCalls AS t WHERE t.allocatedUserId=:allocatedUserId AND "
//			+ " t.callDateFrom >= :fDate  AND t.callDateTo <= :tDate AND  t.callStatus=:callStatus "
//			+ " AND t.phoneNumberType=:phoneNoType AND t.childId IS NOT NULL ")
//	int getAllocatedRecordsCountChildUser(@Param("allocatedUserId") Integer allocatedUserId,
//			@Param("fDate") Timestamp fDate, @Param("tDate") Timestamp tDate, @Param("callStatus") String callStatus,
//			@Param("phoneNoType") String phoneNoType);
	@Query(value = " SELECT COUNT(1) FROM OutboundCalls AS t WHERE t.allocatedUserId=:allocatedUserId AND "
			+ " ((:fDate between t.callDateFrom AND t.callDateTo) OR (:tDate between t.callDateFrom AND t.callDateTo)) AND  t.callStatus=:callStatus "
			+ " AND t.phoneNumberType=:phoneNoType AND t.childId IS NOT NULL ")
	int getAllocatedRecordsCountChildUser(@Param("allocatedUserId") Integer allocatedUserId,
			@Param("fDate") Timestamp fDate, @Param("tDate") Timestamp tDate, @Param("callStatus") String callStatus,
			@Param("phoneNoType") String phoneNoType);

	// users allocated calls, Mother, by RecordType And PhoneType
//	@Query(value = " SELECT t FROM OutboundCalls AS t WHERE t.allocatedUserId=:allocatedUserId AND "
//			+ " t.callStatus=:callStatus AND t.phoneNumberType=:phoneNoType AND t.callDateFrom >= :fDate "
//			+ " AND t.callDateTo <= :tDate AND " + " t.childId IS NULL AND t.motherId IS NOT NULL ")
//	Page<OutboundCalls> getAllocatedRecordsUserByRecordTypeAndPhoneTypeMother(Pageable pageable,
//			@Param("allocatedUserId") Integer allocatedUserId, @Param("callStatus") String callStatus,
//			@Param("phoneNoType") String phoneNoType, @Param("fDate") Timestamp fDate, @Param("tDate") Timestamp tDate);
	@Query(value = " SELECT t FROM OutboundCalls AS t WHERE t.allocatedUserId=:allocatedUserId AND "
			+ " t.callStatus=:callStatus AND t.phoneNumberType=:phoneNoType "
			+ " AND ((:fDate between t.callDateFrom AND t.callDateTo) OR (:tDate between t.callDateFrom AND t.callDateTo)) AND "
			+ " t.childId IS NULL AND t.motherId IS NOT NULL ")
	Page<OutboundCalls> getAllocatedRecordsUserByRecordTypeAndPhoneTypeMother(Pageable pageable,
			@Param("allocatedUserId") Integer allocatedUserId, @Param("callStatus") String callStatus,
			@Param("phoneNoType") String phoneNoType, @Param("fDate") Timestamp fDate, @Param("tDate") Timestamp tDate);

//	// users allocated calls, Child, by RecordType And PhoneType
//	@Query(value = " SELECT t FROM OutboundCalls AS t WHERE t.allocatedUserId=:allocatedUserId AND "
//			+ " t.callStatus=:callStatus AND t.phoneNumberType=:phoneNoType "
//			+ " AND t.callDateFrom >= :fDate AND t.callDateTo <= :tDate AND t.childId IS NOT NULL  ")
//	Page<OutboundCalls> getAllocatedRecordsUserByRecordTypeAndPhoneTypeChild(Pageable pageable,
//			@Param("allocatedUserId") Integer allocatedUserId, @Param("callStatus") String callStatus,
//			@Param("phoneNoType") String phoneNoType, @Param("fDate") Timestamp fDate, @Param("tDate") Timestamp tDate);
	@Query(value = " SELECT t FROM OutboundCalls AS t WHERE t.allocatedUserId=:allocatedUserId AND "
			+ " t.callStatus=:callStatus AND t.phoneNumberType=:phoneNoType "
			+ " AND ((:fDate between t.callDateFrom AND t.callDateTo) OR (:tDate between t.callDateFrom AND t.callDateTo)) AND t.childId IS NOT NULL  ")
	Page<OutboundCalls> getAllocatedRecordsUserByRecordTypeAndPhoneTypeChild(Pageable pageable,
			@Param("allocatedUserId") Integer allocatedUserId, @Param("callStatus") String callStatus,
			@Param("phoneNoType") String phoneNoType, @Param("fDate") Timestamp fDate, @Param("tDate") Timestamp tDate);

	@Modifying
	@Transactional
	@Query(" UPDATE OutboundCalls SET beneficiaryRegId = :beneficiaryRegId, phoneNumberType = :phoneNumberType WHERE motherId = :motherId AND childId IS NULL ")
	public int updateBeneficiaryRegIdForMother(@Param("beneficiaryRegId") Long beneficiaryRegId,
			@Param("motherId") Long motherId, @Param("phoneNumberType") String phoneNumberType);

	@Modifying
	@Transactional
	@Query(" UPDATE OutboundCalls SET beneficiaryRegId = :beneficiaryRegId, phoneNumberType = :phoneNumberType WHERE childId = :childId")
	public int updateBeneficiaryRegIdForChild(@Param("beneficiaryRegId") Long beneficiaryRegId,
			@Param("childId") Long childId, @Param("phoneNumberType") String phoneNumberType);

	@Modifying
	@Transactional
	@Query(" UPDATE OutboundCalls SET phoneNumberType = :phoneNumberType WHERE motherId = :motherId AND beneficiaryRegId = :beneficiaryRegId AND childId IS NULL")
	public int updatePhoneNoTypeForMother(@Param("beneficiaryRegId") Long beneficiaryRegId,
			@Param("motherId") Long motherId, @Param("phoneNumberType") String phoneNumberType);

	@Modifying
	@Transactional
	@Query(" UPDATE OutboundCalls SET phoneNumberType = :phoneNumberType WHERE childId = :childId AND beneficiaryRegId = :beneficiaryRegId")
	public int updatePhoneNoTypeForChild(@Param("beneficiaryRegId") Long beneficiaryRegId,
			@Param("childId") Long childId, @Param("phoneNumberType") String phoneNumberType);

	@Transactional
	@Modifying
	@Query("UPDATE OutboundCalls SET allocationStatus = 'allocated', allocatedUserId = :userId "
			+ " WHERE motherId = :motherId AND callDateTo>current_date() AND obCallId!=:id")
	public int stickyMotherAgentAllocation(@Param("id") Long id, @Param("motherId") Long motherId,
			@Param("userId") Integer userId);

	@Transactional
	@Modifying
	@Query("UPDATE OutboundCalls SET allocationStatus = 'allocated', allocatedUserId = :userId "
			+ " WHERE childId = :childId AND callDateTo>current_date() AND obCallId!=:id")
	public int stickyChildAgentAllocation(@Param("id") Long id, @Param("childId") Long childId,
			@Param("userId") Integer userId);

	@Query(value = " SELECT t FROM OutboundCalls AS t WHERE t.psmId=:psmId AND "
			+ " t.callStatus=:callStatus AND t.ecdCallType=:ecdCallType AND t.beneficiaryRegId IS NOT NULL")
	List<OutboundCalls> getIntroductoryRecordsUser(@Param("psmId") Integer psmId,
			@Param("callStatus") String callStatus, @Param("ecdCallType") String ecdCallType);

}
