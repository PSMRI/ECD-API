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
package com.iemr.ecd.service.data_upload;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.codec.binary.Base64;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.google.gson.Gson;
import com.iemr.ecd.dao.associate.ChildRecord;
import com.iemr.ecd.dao.associate.MotherRecord;
import com.iemr.ecd.dto.supervisor.RCHFileUploadDto;
import com.iemr.ecd.repo.call_conf_allocation.ChildRecordRepo;
import com.iemr.ecd.repo.call_conf_allocation.MotherRecordRepo;
import com.iemr.ecd.utils.advice.exception_handler.ECDException;
import com.iemr.ecd.utils.advice.exception_handler.InvalidRequestException;

import jakarta.transaction.Transactional;

@Service
public class RCHDataUploadServiceImpl {

	Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	@Autowired
	private MotherRecordRepo motherRecordRepo;
	@Autowired
	private ChildRecordRepo childRecordRepo;

	@Transactional(rollbackOn = Exception.class)
	public String uploadRCDData(RCHFileUploadDto rchFileUploadDto) {

		Map<String, String> responseMap = new HashMap<>();

		Workbook workbook = null;
		try {

			if (rchFileUploadDto != null && rchFileUploadDto.getFileContent() != null
					&& rchFileUploadDto.getFileContent().length() > 0) {

				String base64File = rchFileUploadDto.getFileContent();
				byte[] excelDataBytes = Base64.decodeBase64(base64File);
				workbook = this.getXSSFWorkbook(excelDataBytes);

				if (workbook.getNumberOfSheets() > 0) {
					Sheet sheet = workbook.getSheetAt(0);

					if (sheet != null) {

						if (sheet.getPhysicalNumberOfRows() > 1000)
							throw new ECDException(
									"File is having more then 1000 records,please upload max 1000 records at a time");

						if (rchFileUploadDto.getFieldFor() != null
								&& rchFileUploadDto.getFieldFor().equalsIgnoreCase("Mother Data")) {
							responseMap.put("response", getMotherValidRecords(sheet,
									rchFileUploadDto.getProviderServiceMapID(), rchFileUploadDto.getCreatedBy()));

						} else {
							if (rchFileUploadDto.getFieldFor() != null
									&& rchFileUploadDto.getFieldFor().equalsIgnoreCase("Child Data")) {
								responseMap.put("response", getChildValidRecords(sheet,
										rchFileUploadDto.getProviderServiceMapID(), rchFileUploadDto.getCreatedBy()));
							} else
								throw new InvalidRequestException("",
										"invalid record-type. Please pass valid record type/field for");
						}

					}

					return new Gson().toJson(responseMap);
				} else
					throw new InvalidRequestException("", "no record is available to upload in sheet");
			} else
				throw new InvalidRequestException("NULL file content",
						"NULL / Blank data upload file. Please pass valid excel sheet data");
		} catch (Exception e) {
			throw new ECDException(e);
		} finally {
			if (workbook != null)
				try {
					workbook.close();
				} catch (IOException e) {
					// log
				}

		}

	}

	private String getMotherValidRecords(Sheet sheet, int psmId, String createdBy) {
		ArrayList<MotherRecord> motherRecordList = new ArrayList<>();
		MotherRecord motherRecord;

		Set<Long> motherEcdIdSet = new HashSet<>();
		try {

			for (Row row : sheet) {
				try {
					if (row.getRowNum() == 0) {
						// ignore this row, as this is header
						// System.out.println(row.getRowNum());
					} else {
						motherRecord = new MotherRecord();

//						if (row.getCell(14) != null && !row.getCell(14).getCellType().equals(CellType.BLANK))
//							motherRecord.setRegistrationNo(Long.valueOf(row.getCell(14).getStringCellValue()));
//						if (motherRecord.getRegistrationNo().toString().length() != 12) {
//							logger.info("registration no is not valid : " + row.getRowNum());
//						} else {
//							logger.info("registration no is blank for record no : " + row.getRowNum());
//							continue;
//						}
						
						

						// check if record already exist
						if (row.getCell(14) != null && !row.getCell(14).getCellType().equals(CellType.BLANK)) {
							if (row.getCell(14).getCellType().equals(CellType.STRING))
								motherRecord.setEcdIdNo(Long.valueOf(row.getCell(14).getStringCellValue()));
							else if (row.getCell(14).getCellType().equals(CellType.NUMERIC)) {
								Double d = row.getCell(14).getNumericCellValue();

								motherRecord.setEcdIdNo(d.longValue());
							}
							if (motherEcdIdSet.contains(motherRecord.getEcdIdNo()))
								continue;
							else
								motherEcdIdSet.add(motherRecord.getEcdIdNo());
							if(motherRecord.getEcdIdNo().toString().length() != 12) {
								logger.info("registration no is not valid : " + row.getRowNum());
								continue;
							}

							MotherRecord mr = motherRecordRepo.findByEcdIdNo(motherRecord.getEcdIdNo());
							if (mr == null)
								;
							else {
								logger.info("mother record already exist in AMRIT : " + motherRecord.getEcdIdNo());
								continue;
							}
						} else {
							logger.info("mother rch id no is blank : " + row.getRowNum());
							continue;
						}

						if (row.getCell(20) != null && !row.getCell(20).getCellType().equals(CellType.BLANK)) {
							if (row.getCell(20).getStringCellValue().equalsIgnoreCase("self")
									|| row.getCell(20).getStringCellValue().equalsIgnoreCase("husband")
									|| row.getCell(20).getStringCellValue().equalsIgnoreCase("wife")) {
								motherRecord.setWhomPhoneNo("Self");

							} else if (row.getCell(20).getStringCellValue().equalsIgnoreCase("other")
									|| row.getCell(20).getStringCellValue().equalsIgnoreCase("others")) {
								motherRecord.setWhomPhoneNo("Others");

							} else {
								logger.info("invalid data - phone no not as per required : " + row.getRowNum());
								continue;
							}

						}

						motherRecord.setCreatedBy(createdBy);
						motherRecord.setIsAllocated(false);

						if (row.getCell(0) != null && !row.getCell(0).getCellType().equals(CellType.BLANK))
							motherRecord.setStateID((int) row.getCell(0).getNumericCellValue());
						if (row.getCell(1) != null && !row.getCell(1).getCellType().equals(CellType.BLANK))
							motherRecord.setStateName(row.getCell(1).getStringCellValue());
						if (row.getCell(2) != null && !row.getCell(2).getCellType().equals(CellType.BLANK))
							motherRecord.setDistrictID((int) row.getCell(2).getNumericCellValue());
						if (row.getCell(3) != null && !row.getCell(3).getCellType().equals(CellType.BLANK))
							motherRecord.setDistrictName(row.getCell(3).getStringCellValue());
						if (row.getCell(4) != null && !row.getCell(4).getCellType().equals(CellType.BLANK))
							motherRecord.setTalukaId(Integer.valueOf(row.getCell(4).getStringCellValue()));
						if (row.getCell(5) != null && !row.getCell(5).getCellType().equals(CellType.BLANK))
							motherRecord.setTalukaName(row.getCell(5).getStringCellValue());
						if (row.getCell(6) != null && !row.getCell(6).getCellType().equals(CellType.BLANK))
							motherRecord.setBlockID((int) row.getCell(6).getNumericCellValue());
						if (row.getCell(7) != null && !row.getCell(7).getCellType().equals(CellType.BLANK))
							motherRecord.setBlockName(row.getCell(7).getStringCellValue());
						if (row.getCell(8) != null && !row.getCell(8).getCellType().equals(CellType.BLANK)) {
							Double phcIdDouble = row.getCell(8).getNumericCellValue();
							int phcIdInt = phcIdDouble.intValue();
							motherRecord.setPHCID(phcIdInt + "");
						}
						if (row.getCell(9) != null && !row.getCell(9).getCellType().equals(CellType.BLANK))
							motherRecord.setPHCName(row.getCell(9).getStringCellValue());
						if (row.getCell(10) != null && !row.getCell(10).getCellType().equals(CellType.BLANK))
							motherRecord.setSubCenterId((int) row.getCell(10).getNumericCellValue());
						if (row.getCell(11) != null && !row.getCell(11).getCellType().equals(CellType.BLANK))
							motherRecord.setSubCenterName(row.getCell(11).getStringCellValue());
						if (row.getCell(12) != null && !row.getCell(12).getCellType().equals(CellType.BLANK))
							motherRecord.setVillageID((int) row.getCell(12).getNumericCellValue());
						if (row.getCell(13) != null && !row.getCell(13).getCellType().equals(CellType.BLANK))
							motherRecord.setVillageName(row.getCell(13).getStringCellValue());

						/***
						 * moved this on top, so validation can be done at beginning
						 */
//						if (row.getCell(17) != null && !row.getCell(17).getCellType().equals(CellType.BLANK)) {
//							if (row.getCell(17).getCellType().equals(CellType.STRING))
//								motherRecord.setEcdIdNo(Long.valueOf(row.getCell(17).getStringCellValue()));
//							else if (row.getCell(17).getCellType().equals(CellType.NUMERIC)) {
//								Double d = row.getCell(17).getNumericCellValue();
//
//								motherRecord.setEcdIdNo(d.longValue());
//							}
//							if (motherEcdIdSet.contains(motherRecord.getEcdIdNo()))
//								continue;
//							else
//								motherEcdIdSet.add(motherRecord.getEcdIdNo());
//
//							MotherRecord mr = motherRecordRepo.findByEcdIdNo(motherRecord.getEcdIdNo());
//							if (mr == null)
//								;
//							else {
//								// invalid record, record already exits
//								continue;
//							}
//						}

						if (row.getCell(18) != null && !row.getCell(18).getCellType().equals(CellType.BLANK))
							motherRecord.setMotherName(row.getCell(18).getStringCellValue());
						if (row.getCell(19) != null && !row.getCell(19).getCellType().equals(CellType.BLANK))
							motherRecord.setHusbandName(row.getCell(19).getStringCellValue());

						if (row.getCell(21) != null && !row.getCell(21).getCellType().equals(CellType.BLANK)) {
							if (row.getCell(21).getCellType().equals(CellType.STRING))
								motherRecord.setLandlineNo(row.getCell(21).getStringCellValue());
							else if (row.getCell(21).getCellType().equals(CellType.NUMERIC)) {
								Double d = row.getCell(21).getNumericCellValue();
								motherRecord.setLandlineNo(String.valueOf(d.longValue()));
							}
						}
						if (row.getCell(22) != null && !row.getCell(22).getCellType().equals(CellType.BLANK)) {
							if (row.getCell(22).getCellType().equals(CellType.STRING))
								motherRecord.setPhoneNo(row.getCell(22).getStringCellValue());
							else if (row.getCell(22).getCellType().equals(CellType.NUMERIC)) {
								Double d = row.getCell(22).getNumericCellValue();
								motherRecord.setPhoneNo(String.valueOf(d.longValue()));
							}
						}

						if (row.getCell(28) != null && !row.getCell(28).getCellType().equals(CellType.BLANK))
							motherRecord.setAddress(row.getCell(28).getStringCellValue());

						if (row.getCell(30) != null && !row.getCell(30).getCellType().equals(CellType.BLANK))
							motherRecord.setCaste(row.getCell(30).getStringCellValue());

						if (row.getCell(45) != null && !row.getCell(45).getCellType().equals(CellType.BLANK))
							motherRecord.setAnmName(row.getCell(45).getStringCellValue());
						if (row.getCell(46) != null && !row.getCell(46).getCellType().equals(CellType.BLANK))
							motherRecord.setAshaName(row.getCell(46).getStringCellValue());

						if (row.getCell(47) != null && !row.getCell(47).getCellType().equals(CellType.BLANK)) {
							if (row.getCell(47).getCellType().equals(CellType.STRING))
								motherRecord.setAnmPh(row.getCell(47).getStringCellValue());
							else if (row.getCell(47).getCellType().equals(CellType.NUMERIC)) {
								Double d = row.getCell(47).getNumericCellValue();
								motherRecord.setAnmPh(String.valueOf(d.longValue()));
							}
						}
						if (row.getCell(48) != null && !row.getCell(48).getCellType().equals(CellType.BLANK)) {
							if (row.getCell(48).getCellType().equals(CellType.STRING))
								motherRecord.setAshaPh(row.getCell(48).getStringCellValue());
							else if (row.getCell(48).getCellType().equals(CellType.NUMERIC)) {
								Double d = row.getCell(48).getNumericCellValue();
								motherRecord.setAshaPh(String.valueOf(d.longValue()));
							}
						}
						
						if (row.getCell(17) != null && !row.getCell(17).getCellType().equals(CellType.BLANK)) {
							if (row.getCell(17).getCellType().equals(CellType.STRING))
								motherRecord.setMotherMctsId(Long.valueOf(row.getCell(17).getStringCellValue()));
							else if (row.getCell(17).getCellType().equals(CellType.NUMERIC)) {
								Double d = row.getCell(17).getNumericCellValue();

								motherRecord.setMotherMctsId(d.longValue());
							}
						}

						if (motherRecord.getLandlineNo() == null && motherRecord.getPhoneNo() == null)
							continue;

						if (row.getCell(69) != null && !row.getCell(69).getCellType().equals(CellType.BLANK))
							motherRecord.setMotherWeight((int) row.getCell(69).getNumericCellValue());
						if (row.getCell(70) != null && !row.getCell(70).getCellType().equals(CellType.BLANK)
								&& row.getCell(70).getCellType().equals(CellType.NUMERIC)) {
							Date date = row.getCell(70).getDateCellValue();
							Long cellLongValue = date.getTime();
							motherRecord.setBirthDate(new Timestamp(cellLongValue));
						}
						if (row.getCell(71) != null && !row.getCell(71).getCellType().equals(CellType.BLANK))
							motherRecord.setAge((int) row.getCell(71).getNumericCellValue());

						if (row.getCell(74) != null && !row.getCell(74).getCellType().equals(CellType.BLANK))
							motherRecord.setJsyBeneficiary(row.getCell(74).getStringCellValue());

						if (row.getCell(79) != null && !row.getCell(79).getCellType().equals(CellType.BLANK)) {

							Double ry = row.getCell(79).getNumericCellValue();
							int ryInt = ry.intValue();

							motherRecord.setMotherRegistrationYear(ryInt + "");
							// System.out.println(motherRecord.getMotherRegistrationYear());
						}

						if (row.getCell(81) != null && !row.getCell(81).getCellType().equals(CellType.BLANK)
								&& row.getCell(81).getCellType().equals(CellType.NUMERIC)) {
							Date date = row.getCell(81).getDateCellValue();
							Long cellLongValue = date.getTime();
							motherRecord.setLmpDate(new Timestamp(cellLongValue));
						}

						if (row.getCell(83) != null && !row.getCell(83).getCellType().equals(CellType.BLANK)
								&& row.getCell(83).getCellType().equals(CellType.NUMERIC)) {
							Date date = row.getCell(83).getDateCellValue();
							Long cellLongValue = date.getTime();
							motherRecord.setEdd(new Timestamp(cellLongValue));
						}

						// ANC 1
						if (row.getCell(98) != null && !row.getCell(98).getCellType().equals(CellType.BLANK)
								&& row.getCell(98).getCellType().equals(CellType.NUMERIC)) {
							Date date = row.getCell(98).getDateCellValue();
							Long cellLongValue = date.getTime();
							motherRecord.setAnc1Date(new Timestamp(cellLongValue));
						}
						if (row.getCell(116) != null && !row.getCell(116).getCellType().equals(CellType.BLANK)) {
							motherRecord.setAnc1SymptomsHighRisk(row.getCell(116).getStringCellValue());

							if (motherRecord.getAnc1SymptomsHighRisk().equalsIgnoreCase("None"))
								;
							else
								motherRecord.setHighRisk(true);
						}

						// ANC 2
						if (row.getCell(123) != null && !row.getCell(123).getCellType().equals(CellType.BLANK)
								&& row.getCell(123).getCellType().equals(CellType.NUMERIC)) {
							Date date = row.getCell(123).getDateCellValue();
							Long cellLongValue = date.getTime();
							motherRecord.setAnc2Date(new Timestamp(cellLongValue));
						}
						if (row.getCell(141) != null && !row.getCell(141).getCellType().equals(CellType.BLANK)) {
							motherRecord.setAnc2SymptomsHighRisk(row.getCell(141).getStringCellValue());

							if (motherRecord.getAnc2SymptomsHighRisk().equalsIgnoreCase("None"))
								;
							else
								motherRecord.setHighRisk(true);
						}

						// ANC 3
						if (row.getCell(148) != null && !row.getCell(148).getCellType().equals(CellType.BLANK)
								&& row.getCell(148).getCellType().equals(CellType.NUMERIC)) {
							Date date = row.getCell(148).getDateCellValue();
							Long cellLongValue = date.getTime();
							motherRecord.setAnc3Date(new Timestamp(cellLongValue));
						}
						if (row.getCell(166) != null && !row.getCell(166).getCellType().equals(CellType.BLANK)) {
							motherRecord.setAnc3SymptomsHighRisk(row.getCell(166).getStringCellValue());

							if (motherRecord.getAnc3SymptomsHighRisk().equalsIgnoreCase("None"))
								;
							else
								motherRecord.setHighRisk(true);
						}

						// ANC 4
						if (row.getCell(173) != null && !row.getCell(173).getCellType().equals(CellType.BLANK)
								&& row.getCell(173).getCellType().equals(CellType.NUMERIC)) {
							Date date = row.getCell(173).getDateCellValue();
							Long cellLongValue = date.getTime();
							motherRecord.setAnc4Date(new Timestamp(cellLongValue));
						}
						if (row.getCell(191) != null && !row.getCell(191).getCellType().equals(CellType.BLANK)) {
							motherRecord.setAnc4SymptomsHighRisk(row.getCell(191).getStringCellValue());

							if (motherRecord.getAnc4SymptomsHighRisk().equalsIgnoreCase("None"))
								;
							else
								motherRecord.setHighRisk(true);
						}

						// TT 1
						if (row.getCell(198) != null && !row.getCell(198).getCellType().equals(CellType.BLANK)
								&& row.getCell(198).getCellType().equals(CellType.NUMERIC)) {
							Date date = row.getCell(198).getDateCellValue();
							Long cellLongValue = date.getTime();
							motherRecord.setTt1Date(new Timestamp(cellLongValue));
						}
						// TT 2
						if (row.getCell(199) != null && !row.getCell(199).getCellType().equals(CellType.BLANK)
								&& row.getCell(199).getCellType().equals(CellType.NUMERIC)) {
							Date date = row.getCell(199).getDateCellValue();
							Long cellLongValue = date.getTime();
							motherRecord.setTt2Date(new Timestamp(cellLongValue));
						}
						// TT B
						if (row.getCell(200) != null && !row.getCell(200).getCellType().equals(CellType.BLANK)
								&& row.getCell(200).getCellType().equals(CellType.NUMERIC)) {
							Date date = row.getCell(200).getDateCellValue();
							Long cellLongValue = date.getTime();
							motherRecord.setTtBoosterDate(new Timestamp(cellLongValue));
						}

						// Delivery Date
						if (row.getCell(210) != null && !row.getCell(210).getCellType().equals(CellType.BLANK)
								&& row.getCell(210).getCellType().equals(CellType.NUMERIC)) {
							Date date = row.getCell(210).getDateCellValue();
							Long cellLongValue = date.getTime();
							motherRecord.setDeliveryDate(new Timestamp(cellLongValue));
						}
						// Delivery Place
						if (row.getCell(211) != null && !row.getCell(211).getCellType().equals(CellType.BLANK))
							motherRecord.setDeliveryPlace(row.getCell(211).getStringCellValue());
						// Delivery Place Name
						if (row.getCell(212) != null && !row.getCell(212).getCellType().equals(CellType.BLANK))
							motherRecord.setDeliveryPlaceName(row.getCell(212).getStringCellValue());

						// Delivery Type
						if (row.getCell(214) != null && !row.getCell(214).getCellType().equals(CellType.BLANK))
							motherRecord.setDeliveryType(row.getCell(214).getStringCellValue());

						// Delivery Complication
						if (row.getCell(225) != null && !row.getCell(225).getCellType().equals(CellType.BLANK))
							motherRecord.setDeliveryComplications(row.getCell(225).getStringCellValue());

						motherRecordList.add(motherRecord);

					}
				} catch (Exception e) {
					logger.error("exception in record no: " + row.getRowNum() + " - " + e);
				}
			}
		} catch (Exception e) {
			throw new ECDException(e);
		}

		if (motherRecordList.size() > 0) {
			motherRecordRepo.saveAll(motherRecordList);
			return motherRecordList.size() + " valid mother records are uploaded in AMRIT";
		} else
			return "No valid mother record found to upload";

	}

	private String getChildValidRecords(Sheet sheet, int psmId, String createdBy) {
		ChildRecord childRecord;
		List<ChildRecord> childRecordList = new ArrayList<>();

		Set<Long> childEcdIdSet = new HashSet<>();
		try {
			for (Row row : sheet) {
				try {
					if (row.getRowNum() == 0) {
						// log the columns
					} else {
						childRecord = new ChildRecord();
						
//						if (row.getCell(25) != null && !row.getCell(25).getCellType().equals(CellType.BLANK)) {
//							if (row.getCell(25).getCellType().equals(CellType.STRING))
//								childRecord.setEcdIdNoChildId(Long.valueOf(row.getCell(25).getStringCellValue()));
//							else if (row.getCell(25).getCellType().equals(CellType.NUMERIC)) {
//								Double d = row.getCell(25).getNumericCellValue();
//
//								childRecord.setEcdIdNoChildId(d.longValue());
//							}

						if (row.getCell(14) != null && !row.getCell(14).getCellType().equals(CellType.BLANK)) {
							if (row.getCell(14).getCellType().equals(CellType.STRING))
								childRecord.setEcdIdNoChildId(Long.valueOf(row.getCell(14).getStringCellValue()));
							else if (row.getCell(14).getCellType().equals(CellType.NUMERIC)) {
								Double d = row.getCell(14).getNumericCellValue();

								childRecord.setEcdIdNoChildId(d.longValue());
							}
							if (childEcdIdSet.contains(childRecord.getEcdIdNoChildId()))
								continue;
							else
								childEcdIdSet.add(childRecord.getEcdIdNoChildId());
							if(childRecord.getEcdIdNoChildId().toString().length() != 12) {
								 logger.info("child rch id no is not valid : " + row.getRowNum());
								 continue;
							}

							ChildRecord cr = childRecordRepo.findByEcdIdNoChildId(childRecord.getEcdIdNoChildId());
							if (cr == null)
								;
							else {
								logger.info("child record already exist in AMRIT : " + childRecord.getEcdIdNoChildId());
								continue;
							}
						}
						if (row.getCell(38) != null && !row.getCell(38).getCellType().equals(CellType.BLANK)) {
							if (row.getCell(38).getStringCellValue().equalsIgnoreCase("self")
									|| row.getCell(38).getStringCellValue().equalsIgnoreCase("husband")
									|| row.getCell(38).getStringCellValue().equalsIgnoreCase("wife")) {
								childRecord.setWhomPhoneNo("Self");

							} else if (row.getCell(38).getStringCellValue().equalsIgnoreCase("other")
									|| row.getCell(38).getStringCellValue().equalsIgnoreCase("others")) {
								childRecord.setWhomPhoneNo("Others");

							} else {
								logger.info("invalid data - phone no not as per required : " + row.getRowNum());
								continue;
							}
						}
							

						childRecord.setCreatedBy(createdBy);
						childRecord.setIsAllocated(false);

						if (row.getCell(0) != null && !row.getCell(0).getCellType().equals(CellType.BLANK))
							childRecord.setStateID((int) row.getCell(0).getNumericCellValue());

						if (row.getCell(1) != null && !row.getCell(1).getCellType().equals(CellType.BLANK))
							childRecord.setStateName(row.getCell(1).getStringCellValue());
						if (row.getCell(2) != null && !row.getCell(2).getCellType().equals(CellType.BLANK))
							childRecord.setDistrictID((int) row.getCell(2).getNumericCellValue());
						if (row.getCell(3) != null && !row.getCell(3).getCellType().equals(CellType.BLANK))
							childRecord.setDistrictName(row.getCell(3).getStringCellValue());
						if (row.getCell(4) != null && !row.getCell(4).getCellType().equals(CellType.BLANK))
							childRecord.setTalukaId(Integer.valueOf(row.getCell(4).getStringCellValue()));
						if (row.getCell(5) != null && !row.getCell(5).getCellType().equals(CellType.BLANK))
							childRecord.setTalukaName(row.getCell(5).getStringCellValue());
						if (row.getCell(6) != null && !row.getCell(6).getCellType().equals(CellType.BLANK))
							childRecord.setBlockID((int) row.getCell(6).getNumericCellValue());
						if (row.getCell(7) != null && !row.getCell(7).getCellType().equals(CellType.BLANK))
							childRecord.setBlockName(row.getCell(7).getStringCellValue());
						if (row.getCell(8) != null && !row.getCell(8).getCellType().equals(CellType.BLANK)) {
							Double phcIdDouble = row.getCell(8).getNumericCellValue();
							childRecord.setPHCID(phcIdDouble.intValue());
						}
						if (row.getCell(9) != null && !row.getCell(9).getCellType().equals(CellType.BLANK))
							childRecord.setPHCName(row.getCell(9).getStringCellValue());
						if (row.getCell(10) != null && !row.getCell(10).getCellType().equals(CellType.BLANK))
							childRecord.setSubCenterId((int) row.getCell(10).getNumericCellValue());
						if (row.getCell(11) != null && !row.getCell(11).getCellType().equals(CellType.BLANK))
							childRecord.setSubCenterName(row.getCell(11).getStringCellValue());
						if (row.getCell(12) != null && !row.getCell(12).getCellType().equals(CellType.BLANK))
							childRecord.setVillageID((int) row.getCell(12).getNumericCellValue());
						if (row.getCell(13) != null && !row.getCell(13).getCellType().equals(CellType.BLANK))
							childRecord.setVillageName(row.getCell(13).getStringCellValue());

						// child MCTS ID NO
						if (row.getCell(25) != null && !row.getCell(25).getCellType().equals(CellType.BLANK)) {
							if (row.getCell(25).getCellType().equals(CellType.STRING))
								childRecord.setChildMctsId(Long.valueOf(row.getCell(14).getStringCellValue()));
							else if (row.getCell(25).getCellType().equals(CellType.NUMERIC)) {
								Double d = row.getCell(25).getNumericCellValue();
								childRecord.setChildMctsId(d.longValue());
							}
						}

						if (row.getCell(17) != null && !row.getCell(17).getCellType().equals(CellType.BLANK)
								&& row.getCell(17).getCellType().equals(CellType.NUMERIC)) {
							Date date = row.getCell(17).getDateCellValue();
							Long cellLongValue = date.getTime();
							childRecord.setRegistrationDate(new Timestamp(cellLongValue));
						}
						if (row.getCell(18) != null && !row.getCell(18).getCellType().equals(CellType.BLANK))
							childRecord.setChildName(row.getCell(18).getStringCellValue());

						if (row.getCell(19) != null && !row.getCell(19).getCellType().equals(CellType.BLANK))
							childRecord.setGender(row.getCell(19).getStringCellValue());
						if (row.getCell(20) != null && !row.getCell(20).getCellType().equals(CellType.BLANK)
								&& row.getCell(20).getCellType().equals(CellType.NUMERIC)) {
							Date date = row.getCell(20).getDateCellValue();
							Long cellLongValue = date.getTime();
							childRecord.setDob(new Timestamp(cellLongValue));
						}

						if (row.getCell(21) != null && !row.getCell(21).getCellType().equals(CellType.BLANK))
							childRecord.setPlaceOfBirth(row.getCell(21).getStringCellValue());

						// mother MCTS ID NO
						if (row.getCell(24) != null && !row.getCell(24).getCellType().equals(CellType.BLANK)) {
							if (row.getCell(24).getCellType().equals(CellType.STRING))
								childRecord.setMotherMctsId((Long.valueOf(row.getCell(24).getStringCellValue())));
							else if (row.getCell(24).getCellType().equals(CellType.NUMERIC)) {
								Double d = row.getCell(24).getNumericCellValue();
								childRecord.setMotherMctsId(d.longValue());
							}
						}
						
                        //mother RCH ID No
						if (row.getCell(22) != null && !row.getCell(22).getCellType().equals(CellType.BLANK)) {
							if (row.getCell(22).getCellType().equals(CellType.STRING))
								childRecord.setMotherId(Long.valueOf(row.getCell(22).getStringCellValue()));
							else if (row.getCell(22).getCellType().equals(CellType.NUMERIC)) {
								Double d = row.getCell(22).getNumericCellValue();
								childRecord.setMotherId(d.longValue());
							}
							
							if(childRecord.getMotherId().toString().length() != 12) {
								logger.info("mother rch id no is not valid : " + row.getRowNum());
								continue;
							}
						}

						/***
						 * moved this piece of code to top, so primary validation can be done at
						 * beginning itself
						 */
//						if (row.getCell(25) != null && !row.getCell(25).getCellType().equals(CellType.BLANK)) {
//							if (row.getCell(25).getCellType().equals(CellType.STRING))
//								childRecord.setEcdIdNoChildId(Long.valueOf(row.getCell(25).getStringCellValue()));
//							else if (row.getCell(25).getCellType().equals(CellType.NUMERIC)) {
//								Double d = row.getCell(25).getNumericCellValue();
//
//								childRecord.setEcdIdNoChildId(d.longValue());
//							}
//							if (childEcdIdSet.contains(childRecord.getEcdIdNoChildId()))
//								continue;
//							else
//								childEcdIdSet.add(childRecord.getEcdIdNoChildId());
//
//							ChildRecord cr = childRecordRepo.findByEcdIdNoChildId(childRecord.getEcdIdNoChildId());
//							if (cr == null)
//								;
//							else {
//								// invalid record, record already exits
//								continue;
//							}
//						}

						if (row.getCell(26) != null && !row.getCell(26).getCellType().equals(CellType.BLANK))
							childRecord.setMotherName(row.getCell(26).getStringCellValue());

						if (row.getCell(28) != null && !row.getCell(28).getCellType().equals(CellType.BLANK)) {
							if (row.getCell(28).getCellType().equals(CellType.STRING))
								childRecord.setPhoneNo(row.getCell(28).getStringCellValue());
							else if (row.getCell(28).getCellType().equals(CellType.NUMERIC)) {
								Double d = row.getCell(28).getNumericCellValue();
								childRecord.setPhoneNo(String.valueOf(d.longValue()));
							}
						}
						if (row.getCell(29) != null && !row.getCell(29).getCellType().equals(CellType.BLANK))
							childRecord.setAddress(row.getCell(29).getStringCellValue());
						if (row.getCell(31) != null && !row.getCell(31).getCellType().equals(CellType.BLANK))
							childRecord.setCaste(row.getCell(31).getStringCellValue());

						if (row.getCell(32) != null && !row.getCell(32).getCellType().equals(CellType.BLANK))
							childRecord.setAnmName(row.getCell(32).getStringCellValue());
						if (row.getCell(33) != null && !row.getCell(33).getCellType().equals(CellType.BLANK))
							childRecord.setAshaName(row.getCell(33).getStringCellValue());

						if (row.getCell(34) != null && !row.getCell(34).getCellType().equals(CellType.BLANK)) {
							if (row.getCell(34).getCellType().equals(CellType.STRING))
								childRecord.setAnmPh(row.getCell(34).getStringCellValue());
							else if (row.getCell(34).getCellType().equals(CellType.NUMERIC)) {
								Double d = row.getCell(34).getNumericCellValue();
								childRecord.setAnmPh(String.valueOf(d.longValue()));
							}
						}
						if (row.getCell(35) != null && !row.getCell(35).getCellType().equals(CellType.BLANK)) {
							if (row.getCell(35).getCellType().equals(CellType.STRING))
								childRecord.setAshaPh(row.getCell(35).getStringCellValue());
							else if (row.getCell(35).getCellType().equals(CellType.NUMERIC)) {
								Double d = row.getCell(35).getNumericCellValue();
								childRecord.setAshaPh(String.valueOf(d.longValue()));
							}
						}

						if (row.getCell(37) != null && !row.getCell(37).getCellType().equals(CellType.BLANK))
							childRecord.setFatherName(row.getCell(37).getStringCellValue());
						

						if (row.getCell(39) != null && !row.getCell(39).getCellType().equals(CellType.BLANK))
							childRecord.setWeightOfChild(row.getCell(39).getNumericCellValue());

						if (row.getCell(44) != null && !row.getCell(44).getCellType().equals(CellType.BLANK))
							childRecord.setEntryTypeStr(row.getCell(44).getStringCellValue());

						if (row.getCell(55) != null && !row.getCell(55).getCellType().equals(CellType.BLANK)
								&& row.getCell(55).getCellType().equals(CellType.NUMERIC)) {
							Date date = row.getCell(55).getDateCellValue();
							Long cellLongValue = date.getTime();
							childRecord.setBcgDate(new Timestamp(cellLongValue));
						}
						if (row.getCell(59) != null && !row.getCell(59).getCellType().equals(CellType.BLANK)
								&& row.getCell(59).getCellType().equals(CellType.NUMERIC)) {
							Date date = row.getCell(59).getDateCellValue();
							Long cellLongValue = date.getTime();
							childRecord.setOpv0Date(new Timestamp(cellLongValue));
						}
						if (row.getCell(67) != null && !row.getCell(67).getCellType().equals(CellType.BLANK)
								&& row.getCell(67).getCellType().equals(CellType.NUMERIC)) {
							Date date = row.getCell(67).getDateCellValue();
							Long cellLongValue = date.getTime();
							childRecord.setOpv2Date(new Timestamp(cellLongValue));
						}
						if (row.getCell(71) != null && !row.getCell(71).getCellType().equals(CellType.BLANK)
								&& row.getCell(71).getCellType().equals(CellType.NUMERIC)) {
							Date date = row.getCell(71).getDateCellValue();
							Long cellLongValue = date.getTime();
							childRecord.setOpv3Date(new Timestamp(cellLongValue));
						}
						if (row.getCell(75) != null && !row.getCell(75).getCellType().equals(CellType.BLANK)
								&& row.getCell(75).getCellType().equals(CellType.NUMERIC)) {
							Date date = row.getCell(75).getDateCellValue();
							Long cellLongValue = date.getTime();
							childRecord.setOpvBoosterDate(new Timestamp(cellLongValue));
						}

						if (row.getCell(108) != null && !row.getCell(108).getCellType().equals(CellType.BLANK)
								&& row.getCell(108).getCellType().equals(CellType.NUMERIC)) {
							Date date = row.getCell(108).getDateCellValue();
							Long cellLongValue = date.getTime();
							childRecord.setHepatitisB0Dt(new Timestamp(cellLongValue));
						}
						if (row.getCell(153) != null && !row.getCell(153).getCellType().equals(CellType.BLANK)
								&& row.getCell(153).getCellType().equals(CellType.NUMERIC)) {
							Date date = row.getCell(153).getDateCellValue();
							Long cellLongValue = date.getTime();
							childRecord.setJeDate(new Timestamp(cellLongValue));
						}

						if (row.getCell(161) != null && !row.getCell(161).getCellType().equals(CellType.BLANK)
								&& row.getCell(161).getCellType().equals(CellType.NUMERIC)) {
							Date date = row.getCell(161).getDateCellValue();
							Long cellLongValue = date.getTime();
							childRecord.setVitADose1Date(new Timestamp(cellLongValue));
						}
						if (row.getCell(165) != null && !row.getCell(165).getCellType().equals(CellType.BLANK)
								&& row.getCell(165).getCellType().equals(CellType.NUMERIC)) {
							Date date = row.getCell(165).getDateCellValue();
							Long cellLongValue = date.getTime();
							childRecord.setVitADose2Date(new Timestamp(cellLongValue));
						}
						if (row.getCell(169) != null && !row.getCell(169).getCellType().equals(CellType.BLANK)
								&& row.getCell(169).getCellType().equals(CellType.NUMERIC)) {
							Date date = row.getCell(169).getDateCellValue();
							Long cellLongValue = date.getTime();
							childRecord.setVitADose3Date(new Timestamp(cellLongValue));
						}
						if (row.getCell(193) != null && !row.getCell(193).getCellType().equals(CellType.BLANK)
								&& row.getCell(193).getCellType().equals(CellType.NUMERIC)) {
							Date date = row.getCell(193).getDateCellValue();
							Long cellLongValue = date.getTime();
							childRecord.setVitADose9Date(new Timestamp(cellLongValue));
						}

						if (row.getCell(201) != null && !row.getCell(201).getCellType().equals(CellType.BLANK)
								&& row.getCell(201).getCellType().equals(CellType.NUMERIC)) {
							Date date = row.getCell(201).getDateCellValue();
							Long cellLongValue = date.getTime();
							childRecord.setMrDate(new Timestamp(cellLongValue));
						}

						childRecordList.add(childRecord);

					}

				} catch (Exception e) {
					logger.error("exception in record no: " + row.getRowNum() + " - " + e);
				}
			}
		} catch (Exception e) {
			throw new ECDException(e);
		}
		if (childRecordList.size() > 0) {
			childRecordRepo.saveAll(childRecordList);
			return childRecordList.size() + " valid child records are uploaded in AMRIT";
		} else
			return "No valid child record found to upload";

	}

	private XSSFWorkbook getXSSFWorkbook(byte[] byteArray) throws InvalidFormatException, IOException {

		XSSFWorkbook workbook = new XSSFWorkbook(new ByteArrayInputStream(byteArray));
		return workbook;
	}

	private Timestamp getTimestampFromString(String date) {

		DateTimeFormatter ISO_DATE_TIME = DateTimeFormatter.ISO_DATE_TIME;
		LocalDateTime localDateTime = LocalDateTime.from(ISO_DATE_TIME.parse(date));

		Timestamp timestamp = Timestamp.valueOf(localDateTime);

		return timestamp;

	}

}
