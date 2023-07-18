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
package com.iemr.ecd.service.report;

import java.io.ByteArrayInputStream;

public interface ReportService {
	
	public ByteArrayInputStream getCallDetailsReport(String request, String filename) throws Exception;
	public ByteArrayInputStream getCallSummaryReport(String request, String filename) throws Exception;
	public ByteArrayInputStream getCumulativeDistrictReport(String request, String filename) throws Exception;
	public ByteArrayInputStream getBeneficiarywisefollowupdetails(String request, String filename) throws Exception;
	public ByteArrayInputStream getBirthDefectReport(String request, String filename) throws Exception;
	public ByteArrayInputStream getAashaHomeVisitGapReport(String request, String filename) throws Exception;
	public ByteArrayInputStream getCalciumIFATabNonadherenceReport(String request, String filename) throws Exception;
	public ByteArrayInputStream getAbsenceInVHSNDReport(String request, String filename) throws Exception;
	public ByteArrayInputStream getVaccineDropOutIdentifiedReport(String request, String filename) throws Exception;
	public ByteArrayInputStream getVaccineLeftOutIdentifiedReport(String request, String filename) throws Exception;
	public ByteArrayInputStream getCallDetailReportUnique(String request, String filename) throws Exception;
	public ByteArrayInputStream getDevelopmentalDelayReport(String request, String filename) throws Exception;
	public ByteArrayInputStream getAbortionReport(String request, String filename) throws Exception;
	public ByteArrayInputStream getDeliveryStatusReport(String request, String filename) throws Exception;
	public ByteArrayInputStream getHRPCasesIdentifiedReport(String request, String filename) throws Exception;
	public ByteArrayInputStream getInfantsHighRiskReport(String request, String filename) throws Exception;
	public ByteArrayInputStream getMaternalDeathReport(String request, String filename) throws Exception;
	public ByteArrayInputStream getStillBirthReport(String request, String filename) throws Exception;
	public ByteArrayInputStream getBabyDeathReport(String request, String filename) throws Exception;
	public ByteArrayInputStream getNotConnectedPhonelistDiffformatReport(String request, String filename) throws Exception;
	public ByteArrayInputStream getJSYRelatedComplaintsReport(String request, String filename) throws Exception;
	public ByteArrayInputStream getMiscarriageReport(String request, String filename) throws Exception;

}
