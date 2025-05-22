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
package com.iemr.ecd.controller.reports;

import java.io.InputStream;

import org.apache.poi.util.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.iemr.ecd.service.report.ReportService;
import com.iemr.ecd.utils.advice.exception_handler.CustomExceptionResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@CrossOrigin
@RequestMapping({ "/ecdReportController" })
@RestController
public class ReportController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	@Autowired
	private ReportService reportService;

	@PostMapping(value = "/getECDCallDetailsReport", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Get call details report", description = "Desc - Get call details report")
	@ApiResponses(value = {
			@ApiResponse(responseCode = CustomExceptionResponse.SUCCESS_SC_V, description = CustomExceptionResponse.SUCCESS_SC, content = {
					@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = CustomExceptionResponse.NOT_FOUND_SC_V, description = CustomExceptionResponse.NOT_FOUND_SC),
			@ApiResponse(responseCode = CustomExceptionResponse.INTERNAL_SERVER_ERROR_SC_V, description = CustomExceptionResponse.INTERNAL_SERVER_ERROR_SC),
			@ApiResponse(responseCode = CustomExceptionResponse.DB_EXCEPTION_SC_V, description = CustomExceptionResponse.DB_EXCEPTION_SC),
			@ApiResponse(responseCode = CustomExceptionResponse.BAD_REQUEST_SC_V, description = CustomExceptionResponse.BAD_REQUEST_SC) })
	public ResponseEntity<Object> getCallDetailsReport(@RequestBody String jsonRequest) throws Exception {
		String filename = null;
		InputStream inputStream = null;
		try {
			filename = getFileName(jsonRequest, "ECDCallDetailsReport");
			inputStream = reportService.getCallDetailsReport(jsonRequest, filename);
			if (inputStream != null) {
				byte[] fileBytes = IOUtils.toByteArray(inputStream);
				return ResponseEntity.ok()
						.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + (filename + ".xlsx"))
						.contentType(MediaType.parseMediaType("application/vnd.ms-excel")).body(fileBytes);
			} else {
				return ResponseEntity.ok("No data found");
			}
		} catch (Exception e) {
			logger.error("Report Name:" + filename + " Timestamp:" + System.currentTimeMillis() + " Error: "
					+ e.getMessage());
			return ResponseEntity.status(500).body(e.getMessage());
		}
	}

	@CrossOrigin
	@Operation(summary = "Get ECD call summary report", description = "Desc - Get call details report")
	@RequestMapping(value = "/getECDCallSummaryReport", method = RequestMethod.POST, headers = "Authorization")
	public ResponseEntity<Object> getCallSummaryReport(@RequestBody String jsonRequest) {
		String filename = null;
		InputStream inputStream = null;
		try {
			filename = getFileName(jsonRequest, "ECDCallSummaryReport");
			inputStream = reportService.getCallSummaryReport(jsonRequest, filename);
			if (inputStream != null) {
				byte[] fileBytes = IOUtils.toByteArray(inputStream);
				return ResponseEntity.ok()
						.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + (filename + ".xlsx"))
						.contentType(MediaType.parseMediaType("application/vnd.ms-excel")).body(fileBytes);
			} else {
				return ResponseEntity.ok("No data found");
			}
		} catch (Exception e) {
			logger.error("Report Name:" + filename + " Timestamp:" + System.currentTimeMillis() + " Error: "
					+ e.getMessage());
			return ResponseEntity.status(500).body(e.getMessage());
		}
	}

	@CrossOrigin
	@Operation(summary = "Get ECD cumulative district report", description = "Desc - Get call details report")
	@RequestMapping(value = "/getECDCumulativeDistrictReport", method = RequestMethod.POST)
	public ResponseEntity<Object> getCumulativeDistrictReport(@RequestBody String jsonRequest) {
		String filename = null;
		InputStream inputStream = null;
		try {
			filename = getFileName(jsonRequest, "ECDCumulativeDistrictReport");
			inputStream = reportService.getCumulativeDistrictReport(jsonRequest, filename);
			if (inputStream != null) {

				byte[] fileBytes = IOUtils.toByteArray(inputStream);
				return ResponseEntity.ok()
						.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + (filename + ".xlsx"))
						.contentType(MediaType.parseMediaType("application/vnd.ms-excel")).body(fileBytes);
			} else {
				return ResponseEntity.ok("No data found");
			}
		} catch (Exception e) {
			logger.error("Report Name:" + filename + " Timestamp:" + System.currentTimeMillis() + " Error: "
					+ e.getMessage());
			return ResponseEntity.status(500).body(e.getMessage());
		}
	}

	@CrossOrigin
	@Operation(summary = "Get ECD beneficiary wise follow up details report", description = "Desc - Get call details report")
	@RequestMapping(value = "/getECDBeneficiarywisefollowupdetailsReport", method = RequestMethod.POST, headers = "Authorization")
	public ResponseEntity<Object> getBeneficiarywisefollowupdetails(@RequestBody String jsonRequest) {
		String filename = null;
		InputStream inputStream = null;
		try {
			filename = getFileName(jsonRequest, "ECDBeneficiarywisefollowupdetailsReport");
			inputStream = reportService.getBeneficiarywisefollowupdetails(jsonRequest, filename);
			if (inputStream != null) {
				byte[] fileBytes = IOUtils.toByteArray(inputStream);
				return ResponseEntity.ok()
						.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + (filename + ".xlsx"))
						.contentType(MediaType.parseMediaType("application/vnd.ms-excel")).body(fileBytes);
			} else {
				return ResponseEntity.ok("No data found");
			}
		} catch (Exception e) {
			logger.error("Report Name:" + filename + " Timestamp:" + System.currentTimeMillis() + " Error: "
					+ e.getMessage());
			return ResponseEntity.status(500).body(e.getMessage());
		}
	}

	@CrossOrigin
	@Operation(summary = "Get ECD call detail report unique", description = "Desc - Get call details report")
	@RequestMapping(value = "/getECDCallDetailReportUnique", method = RequestMethod.POST, headers = "Authorization")
	public ResponseEntity<Object> getCallDetailReportUnique(@RequestBody String requestObj) {
		String filename = null;
		InputStream inputStream = null;
		try {
			filename = getFileName(requestObj, "CallUniqueDetailsReport");
			inputStream = reportService.getCallDetailReportUnique(requestObj, filename);
			if (inputStream != null) {

				byte[] fileBytes = IOUtils.toByteArray(inputStream);
				return ResponseEntity.ok()
						.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + (filename + ".xlsx"))
						.contentType(MediaType.parseMediaType("application/vnd.ms-excel")).body(fileBytes);
			} else {
				return ResponseEntity.ok("No data found");
			}
		} catch (Exception e) {
			logger.error("Report Name:" + filename + " Timestamp:" + System.currentTimeMillis() + " Error: "
					+ e.getMessage());
			return ResponseEntity.status(500).body(e.getMessage());
		}
	}

	@CrossOrigin
	@Operation(summary = "Get ECD birth defect report", description = "Desc - Get call details report")
	@RequestMapping(value = "/getECDBirthDefectReport", method = RequestMethod.POST, headers = "Authorization")
	public ResponseEntity<Object> getBirthDefectReport(@RequestBody String jsonRequest) {
		String filename = null;
		InputStream inputStream = null;
		try {
			filename = getFileName(jsonRequest, "ECDBirthDefectReport");
			inputStream = reportService.getBirthDefectReport(jsonRequest, filename);
			if (inputStream != null) {

				byte[] fileBytes = IOUtils.toByteArray(inputStream);
				return ResponseEntity.ok()
						.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + (filename + ".xlsx"))
						.contentType(MediaType.parseMediaType("application/vnd.ms-excel")).body(fileBytes);
			} else {
				return ResponseEntity.ok("No data found");
			}
		} catch (Exception e) {
			logger.error("Report Name:" + filename + " Timestamp:" + System.currentTimeMillis() + " Error: "
					+ e.getMessage());
			return ResponseEntity.status(500).body(e.getMessage());
		}
	}

	@CrossOrigin
	@Operation(summary = "Get ECD aasha home visit gap report", description = "Desc - Get call details report")
	@RequestMapping(value = "/getECDAashaHomeVisitGapReport", method = RequestMethod.POST, headers = "Authorization")
	public ResponseEntity<Object> getAashaHomeVisitGapReport(@RequestBody String jsonRequest) {
		String filename = null;
		InputStream inputStream = null;
		try {
			filename = getFileName(jsonRequest, "ECDAashaHomeVisitGapReport");
			inputStream = reportService.getAashaHomeVisitGapReport(jsonRequest, filename);
			if (inputStream != null) {

				byte[] fileBytes = IOUtils.toByteArray(inputStream);
				return ResponseEntity.ok()
						.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + (filename + ".xlsx"))
						.contentType(MediaType.parseMediaType("application/vnd.ms-excel")).body(fileBytes);
			} else {
				return ResponseEntity.ok("No data found");
			}
		} catch (Exception e) {
			logger.error("Report Name:" + filename + " Timestamp:" + System.currentTimeMillis() + " Error: "
					+ e.getMessage());
			return ResponseEntity.status(500).body(e.getMessage());
		}
	}

	@CrossOrigin
	@Operation(summary = "Get ECD calcium IFA tablet non adherence report", description = "Desc - Get call details report")
	@RequestMapping(value = "/getECDCalciumIFATabNonadherenceReport", method = RequestMethod.POST, headers = "Authorization")
	public ResponseEntity<Object> getCalciumIFATabNonadherenceReport(@RequestBody String jsonRequest) {
		String filename = null;
		InputStream inputStream = null;
		try {
			filename = getFileName(jsonRequest, "ECDCalciumIFATabNonadherenceReport");
			inputStream = reportService.getCalciumIFATabNonadherenceReport(jsonRequest, filename);
			if (inputStream != null) {

				byte[] fileBytes = IOUtils.toByteArray(inputStream);
				return ResponseEntity.ok()
						.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + (filename + ".xlsx"))
						.contentType(MediaType.parseMediaType("application/vnd.ms-excel")).body(fileBytes);
			} else {
				return ResponseEntity.ok("No data found");
			}
		} catch (Exception e) {
			logger.error("Report Name:" + filename + " Timestamp:" + System.currentTimeMillis() + " Error: "
					+ e.getMessage());
			return ResponseEntity.status(500).body(e.getMessage());
		}
	}

	@CrossOrigin
	@Operation(summary = "Get ECD absence in VHSND report", description = "Desc - Get call details report")
	@RequestMapping(value = "/getECDAbsenceInVHSNDReport", method = RequestMethod.POST, headers = "Authorization")
	public ResponseEntity<Object> getAbsenceInVHSNDReport(@RequestBody String jsonRequest) {
		String filename = null;
		InputStream inputStream = null;
		try {
			filename = getFileName(jsonRequest, "ECDAbsenceInVHSNDReport");
			inputStream = reportService.getAbsenceInVHSNDReport(jsonRequest, filename);
			if (inputStream != null) {

				byte[] fileBytes = IOUtils.toByteArray(inputStream);
				return ResponseEntity.ok()
						.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + (filename + ".xlsx"))
						.contentType(MediaType.parseMediaType("application/vnd.ms-excel")).body(fileBytes);
			} else {
				return ResponseEntity.ok("No data found");
			}
		} catch (Exception e) {
			logger.error("Report Name:" + filename + " Timestamp:" + System.currentTimeMillis() + " Error: "
					+ e.getMessage());
			return ResponseEntity.status(500).body(e.getMessage());
		}
	}

	@CrossOrigin
	@Operation(summary = "Get ECD vaccine drop out identified report", description = "Desc - Get call details report")
	@RequestMapping(value = "/getECDVaccineDropOutIdentifiedReport", method = RequestMethod.POST, headers = "Authorization")
	public ResponseEntity<Object> getVaccineDropOutIdentifiedReport(@RequestBody String jsonRequest) {
		String filename = null;
		InputStream inputStream = null;
		try {
			filename = getFileName(jsonRequest, "ECDVaccineDropOutIdentifiedReport");
			inputStream = reportService.getVaccineDropOutIdentifiedReport(jsonRequest, filename);
			if (inputStream != null) {

				byte[] fileBytes = IOUtils.toByteArray(inputStream);
				return ResponseEntity.ok()
						.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + (filename + ".xlsx"))
						.contentType(MediaType.parseMediaType("application/vnd.ms-excel")).body(fileBytes);
			} else {
				return ResponseEntity.ok("No data found");
			}
		} catch (Exception e) {
			logger.error("Report Name:" + filename + " Timestamp:" + System.currentTimeMillis() + " Error: "
					+ e.getMessage());
			return ResponseEntity.status(500).body(e.getMessage());
		}
	}

	@CrossOrigin
	@Operation(summary = "Get ECD vaccine left out identified report", description = "Desc - Get call details report")
	@RequestMapping(value = "/getECDVaccineLeftOutIdentifiedReport", method = RequestMethod.POST, headers = "Authorization")
	public ResponseEntity<Object> getVaccineLeftOutIdentifiedReport(@RequestBody String jsonRequest) {
		String filename = null;
		InputStream inputStream = null;
		try {
			filename = getFileName(jsonRequest, "ECDVaccineLeftOutIdentifiedReport");
			inputStream = reportService.getVaccineLeftOutIdentifiedReport(jsonRequest, filename);

			if (inputStream != null) {

				byte[] fileBytes = IOUtils.toByteArray(inputStream);
				return ResponseEntity.ok()
						.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + (filename + ".xlsx"))
						.contentType(MediaType.parseMediaType("application/vnd.ms-excel")).body(fileBytes);
			} else {
				return ResponseEntity.ok("No data found");
			}
		} catch (Exception e) {
			logger.error("Report Name:" + filename + " Timestamp:" + System.currentTimeMillis() + " Error: "
					+ e.getMessage());
			return ResponseEntity.status(500).body(e.getMessage());
		}
	}

	@CrossOrigin
	@Operation(summary = "Get ECD developmental delay report", description = "Desc - Get call details report")
	@RequestMapping(value = "/getECDDevelopmentalDelayReport", method = RequestMethod.POST, headers = "Authorization")
	public ResponseEntity<Object> getDevelopmentalDelayReport(@RequestBody String jsonRequest) {
		String filename = null;
		InputStream inputStream = null;
		try {
			filename = getFileName(jsonRequest, "ECDDevelopmentalDelayReport");
			inputStream = reportService.getDevelopmentalDelayReport(jsonRequest, filename);
			if (inputStream != null) {

				byte[] fileBytes = IOUtils.toByteArray(inputStream);
				return ResponseEntity.ok()
						.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + (filename + ".xlsx"))
						.contentType(MediaType.parseMediaType("application/vnd.ms-excel")).body(fileBytes);
			} else {
				return ResponseEntity.ok("No data found");
			}
		} catch (Exception e) {
			logger.error("Report Name:" + filename + " Timestamp:" + System.currentTimeMillis() + " Error: "
					+ e.getMessage());
			return ResponseEntity.status(500).body(e.getMessage());
		}
	}

	@CrossOrigin
	@Operation(summary = "Get ECD abortion report", description = "Desc - Get call details report")
	@RequestMapping(value = "/getECDAbortionReport", method = RequestMethod.POST, headers = "Authorization")
	public ResponseEntity<Object> getAbortionReport(@RequestBody String jsonRequest) {
		String filename = null;
		InputStream inputStream = null;
		try {
			filename = getFileName(jsonRequest, "ECDAbortionReport");
			inputStream = reportService.getAbortionReport(jsonRequest, filename);
			if (inputStream != null) {

				byte[] fileBytes = IOUtils.toByteArray(inputStream);
				return ResponseEntity.ok()
						.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + (filename + ".xlsx"))
						.contentType(MediaType.parseMediaType("application/vnd.ms-excel")).body(fileBytes);
			} else {
				return ResponseEntity.ok("No data found");
			}
		} catch (Exception e) {
			logger.error("Report Name:" + filename + " Timestamp:" + System.currentTimeMillis() + " Error: "
					+ e.getMessage());
			return ResponseEntity.status(500).body(e.getMessage());
		}
	}

	@CrossOrigin
	@Operation(summary = "Get ECD delivery status report", description = "Desc - Get call details report")
	@RequestMapping(value = "/getECDDeliveryStatusReport", method = RequestMethod.POST, headers = "Authorization")
	public ResponseEntity<Object> getDeliveryStatusReport(@RequestBody String jsonRequest) {
		String filename = null;
		InputStream inputStream = null;
		try {
			filename = getFileName(jsonRequest, "ECDDeliveryStatusReport");
			inputStream = reportService.getDeliveryStatusReport(jsonRequest, filename);
			if (inputStream != null) {

				byte[] fileBytes = IOUtils.toByteArray(inputStream);
				return ResponseEntity.ok()
						.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + (filename + ".xlsx"))
						.contentType(MediaType.parseMediaType("application/vnd.ms-excel")).body(fileBytes);
			} else {
				return ResponseEntity.ok("No data found");
			}
		} catch (Exception e) {
			logger.error("Report Name:" + filename + " Timestamp:" + System.currentTimeMillis() + " Error: "
					+ e.getMessage());
			return ResponseEntity.status(500).body(e.getMessage());
		}
	}

	@CrossOrigin
	@Operation(summary = "Get ECD high risk pregnancy cases identified report", description = "Desc - Get call details report")
	@RequestMapping(value = "/getECDHRPCasesIdentifiedReport", method = RequestMethod.POST, headers = "Authorization")
	public ResponseEntity<Object> getHRPCasesIdentifiedReport(@RequestBody String jsonRequest) {
		String filename = null;
		InputStream inputStream = null;
		try {
			filename = getFileName(jsonRequest, "ECDHRPWCasesIdentifiedReport");
			inputStream = reportService.getHRPCasesIdentifiedReport(jsonRequest, filename);
			if (inputStream != null) {

				byte[] fileBytes = IOUtils.toByteArray(inputStream);
				return ResponseEntity.ok()
						.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + (filename + ".xlsx"))
						.contentType(MediaType.parseMediaType("application/vnd.ms-excel")).body(fileBytes);
			} else {
				return ResponseEntity.ok("No data found");
			}
		} catch (Exception e) {
			logger.error("Report Name:" + filename + " Timestamp:" + System.currentTimeMillis() + " Error: "
					+ e.getMessage());
			return ResponseEntity.status(500).body(e.getMessage());
		}
	}

	@CrossOrigin
	@Operation(summary = "Get ECD infants high risk report", description = "Desc - Get call details report")
	@RequestMapping(value = "/getECDInfantsHighRiskReport", method = RequestMethod.POST, headers = "Authorization")
	public ResponseEntity<Object> getInfantsHighRiskReport(@RequestBody String jsonRequest) {
		String filename = null;
		InputStream inputStream = null;
		try {
			filename = getFileName(jsonRequest, "ECDInfantsHighRiskReport");
			inputStream = reportService.getInfantsHighRiskReport(jsonRequest, filename);
			if (inputStream != null) {

				byte[] fileBytes = IOUtils.toByteArray(inputStream);
				return ResponseEntity.ok()
						.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + (filename + ".xlsx"))
						.contentType(MediaType.parseMediaType("application/vnd.ms-excel")).body(fileBytes);
			} else {
				return ResponseEntity.ok("No data found");
			}
		} catch (Exception e) {
			logger.error("Report Name:" + filename + " Timestamp:" + System.currentTimeMillis() + " Error: "
					+ e.getMessage());
			return ResponseEntity.status(500).body(e.getMessage());
		}
	}

	@CrossOrigin
	@Operation(summary = "Get ECD maternal death report", description = "Desc - Get call details report")
	@RequestMapping(value = "/getECDMaternalDeathReport", method = RequestMethod.POST, headers = "Authorization")
	public ResponseEntity<Object> getMaternalDeathReport(@RequestBody String jsonRequest) {
		String filename = null;
		InputStream inputStream = null;
		try {
			filename = getFileName(jsonRequest, "ECDMaternalDeathReport");
			inputStream = reportService.getMaternalDeathReport(jsonRequest, filename);
			if (inputStream != null) {

				byte[] fileBytes = IOUtils.toByteArray(inputStream);
				return ResponseEntity.ok()
						.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + (filename + ".xlsx"))
						.contentType(MediaType.parseMediaType("application/vnd.ms-excel")).body(fileBytes);
			} else {
				return ResponseEntity.ok("No data found");
			}
		} catch (Exception e) {
			logger.error("Report Name:" + filename + " Timestamp:" + System.currentTimeMillis() + " Error: "
					+ e.getMessage());
			return ResponseEntity.status(500).body(e.getMessage());
		}
	}

	@CrossOrigin
	@Operation(summary = "Get ECD still birth report", description = "Desc - Get call details report")
	@RequestMapping(value = "/getECDStillBirthReport", method = RequestMethod.POST, headers = "Authorization")
	public ResponseEntity<Object> getStillBirthReport(@RequestBody String jsonRequest) {
		String filename = null;
		InputStream inputStream = null;
		try {
			filename = getFileName(jsonRequest, "ECDStillBirthReport");
			inputStream = reportService.getStillBirthReport(jsonRequest, filename);
			if (inputStream != null) {

				byte[] fileBytes = IOUtils.toByteArray(inputStream);
				return ResponseEntity.ok()
						.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + (filename + ".xlsx"))
						.contentType(MediaType.parseMediaType("application/vnd.ms-excel")).body(fileBytes);
			} else {
				return ResponseEntity.ok("No data found");
			}
		} catch (Exception e) {
			logger.error("Report Name:" + filename + " Timestamp:" + System.currentTimeMillis() + " Error: "
					+ e.getMessage());
			return ResponseEntity.status(500).body(e.getMessage());
		}
	}

	@CrossOrigin
	@Operation(summary = "Get ECD baby death report", description = "Desc - Get call details report")
	@RequestMapping(value = "/getECDBabyDeathReport", method = RequestMethod.POST, headers = "Authorization")
	public ResponseEntity<Object> getBabyDeathReport(@RequestBody String jsonRequest) {
		String filename = null;
		InputStream inputStream = null;
		try {
			filename = getFileName(jsonRequest, "ECDBabyDeathReport");
			inputStream = reportService.getBabyDeathReport(jsonRequest, filename);
			if (inputStream != null) {

				byte[] fileBytes = IOUtils.toByteArray(inputStream);
				return ResponseEntity.ok()
						.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + (filename + ".xlsx"))
						.contentType(MediaType.parseMediaType("application/vnd.ms-excel")).body(fileBytes);
			} else {
				return ResponseEntity.ok("No data found");
			}
		} catch (Exception e) {
			logger.error("Report Name:" + filename + " Timestamp:" + System.currentTimeMillis() + " Error: "
					+ e.getMessage());
			return ResponseEntity.status(500).body(e.getMessage());
		}
	}

	@CrossOrigin
	@Operation(summary = "Get ECD not connected phone list diffrent format report", description = "Desc - Get call details report")
	@RequestMapping(value = "/getECDNotConnectedPhonelistDiffformatReport", method = RequestMethod.POST, headers = "Authorization")
	public ResponseEntity<Object> getNotConnectedPhonelistDiffformatReport(@RequestBody String jsonRequest) {
		String filename = null;
		InputStream inputStream = null;
		try {
			filename = getFileName(jsonRequest, "ECDNotConnectedPhonelistDiffformatReport");
			inputStream = reportService.getNotConnectedPhonelistDiffformatReport(jsonRequest, filename);
			if (inputStream != null) {

				byte[] fileBytes = IOUtils.toByteArray(inputStream);
				return ResponseEntity.ok()
						.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + (filename + ".xlsx"))
						.contentType(MediaType.parseMediaType("application/vnd.ms-excel")).body(fileBytes);
			} else {
				return ResponseEntity.ok("No data found");
			}
		} catch (Exception e) {
			logger.error("Report Name:" + filename + " Timestamp:" + System.currentTimeMillis() + " Error: "
					+ e.getMessage());
			return ResponseEntity.status(500).body(e.getMessage());
		}
	}

	@CrossOrigin
	@Operation(summary = "Get ECDJSY related complaints report", description = "Desc - Get call details report")
	@RequestMapping(value = "/getECDJSYRelatedComplaintsReport", method = RequestMethod.POST, headers = "Authorization")
	public ResponseEntity<Object> getJSYRelatedComplaintsReport(@RequestBody String jsonRequest) {
		String filename = null;
		InputStream inputStream = null;
		try {
			filename = getFileName(jsonRequest, "ECDJSYRelatedComplaintsReport");
			inputStream = reportService.getJSYRelatedComplaintsReport(jsonRequest, filename);
			if (inputStream != null) {

				byte[] fileBytes = IOUtils.toByteArray(inputStream);
				return ResponseEntity.ok()
						.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + (filename + ".xlsx"))
						.contentType(MediaType.parseMediaType("application/vnd.ms-excel")).body(fileBytes);
			} else {
				return ResponseEntity.ok("No data found");
			}
		} catch (Exception e) {
			logger.error("Report Name:" + filename + " Timestamp:" + System.currentTimeMillis() + " Error: "
					+ e.getMessage());
			return ResponseEntity.status(500).body(e.getMessage());
		}
	}

	@CrossOrigin
	@Operation(summary = "Get ECD miscarriage report", description = "Desc - Get call details report")
	@RequestMapping(value = "/getECDMiscarriageReport", method = RequestMethod.POST, headers = "Authorization")
	public ResponseEntity<Object> getMiscarriageReport(@RequestBody String jsonRequest) {
		String filename = null;
		InputStream inputStream = null;
		try {
			filename = getFileName(jsonRequest, "ECDMiscarriageReport");
			inputStream = reportService.getMiscarriageReport(jsonRequest, filename);
			if (inputStream != null) {

				byte[] fileBytes = IOUtils.toByteArray(inputStream);
				return ResponseEntity.ok()
						.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + (filename + ".xlsx"))
						.contentType(MediaType.parseMediaType("application/vnd.ms-excel")).body(fileBytes);
			} else {
				return ResponseEntity.ok("No data found");
			}
		} catch (Exception e) {
			logger.error("Report Name:" + filename + " Timestamp:" + System.currentTimeMillis() + " Error: "
					+ e.getMessage());
			return ResponseEntity.status(500).body(e.getMessage());
		}
	}

	public String getFileName(String jsonRequest, String name) {
		String fileName = null;
		JsonObject jsnOBJ = new JsonObject();
		JsonParser jsnParser = new JsonParser();
		JsonElement jsnElmnt = jsnParser.parse(jsonRequest);
		jsnOBJ = jsnElmnt.getAsJsonObject();
		if (jsnOBJ != null && jsnOBJ.has("fileName"))// add null check for fileName
		{
			fileName = jsnOBJ.get("fileName").getAsString();
		}
		if (fileName == null)
			fileName = name;

		return fileName;
	}

}
