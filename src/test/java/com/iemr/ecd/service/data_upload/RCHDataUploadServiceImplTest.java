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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.ByteArrayOutputStream;
import java.sql.Timestamp;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.function.Consumer;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.test.util.ReflectionTestUtils;

import com.iemr.ecd.dao.associate.ChildRecord;
import com.iemr.ecd.dao.associate.MotherRecord;
import com.iemr.ecd.dto.supervisor.RCHFileUploadDto;
import com.iemr.ecd.repo.call_conf_allocation.ChildRecordRepo;
import com.iemr.ecd.repo.call_conf_allocation.MotherRecordRepo;
import com.iemr.ecd.utils.advice.exception_handler.ECDException;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class RCHDataUploadServiceImplTest {

	private static final long VALID_RCH_ID = 123456789012L;
	private static final Date SAMPLE_DATE = new Date(Timestamp.valueOf("2024-01-01 00:00:00").getTime());

	@Mock
	private MotherRecordRepo motherRecordRepo;
	@Mock
	private ChildRecordRepo childRecordRepo;

	@InjectMocks
	private RCHDataUploadServiceImpl service;

	private static void numeric(Row row, int index, double value) {
		row.createCell(index).setCellValue(value);
	}

	private static void text(Row row, int index, String value) {
		row.createCell(index).setCellValue(value);
	}

	private static void date(Row row, int index) {
		row.createCell(index).setCellValue(SAMPLE_DATE);
	}

	/** Builds a workbook whose first sheet has a header row plus the given data rows. */
	private String workbook(List<Consumer<Row>> dataRows) throws Exception {
		try (XSSFWorkbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream()) {
			Sheet sheet = workbook.createSheet("Sheet1");
			sheet.createRow(0).createCell(0).setCellValue("header");
			int rowIndex = 1;
			for (Consumer<Row> dataRow : dataRows) {
				dataRow.accept(sheet.createRow(rowIndex++));
			}
			workbook.write(out);
			return Base64.getEncoder().encodeToString(out.toByteArray());
		}
	}

	private RCHFileUploadDto request(String fieldFor, String content) {
		RCHFileUploadDto request = new RCHFileUploadDto();
		request.setFieldFor(fieldFor);
		request.setFileContent(content);
		request.setProviderServiceMapID(1);
		request.setCreatedBy("supervisor");
		return request;
	}

	private Consumer<Row> fullMotherRow(long rchId, String phoneOwner, String highRiskSymptom) {
		return row -> {
			numeric(row, 0, 10);
			text(row, 1, "State");
			numeric(row, 2, 20);
			text(row, 3, "District");
			text(row, 4, "30");
			text(row, 5, "Taluka");
			numeric(row, 6, 40);
			text(row, 7, "Block");
			numeric(row, 8, 50);
			text(row, 9, "PHC");
			numeric(row, 10, 60);
			text(row, 11, "SubCentre");
			numeric(row, 12, 70);
			text(row, 13, "Village");
			numeric(row, 14, rchId);
			numeric(row, 17, 987654321012L);
			text(row, 18, "Asha Devi");
			text(row, 19, "Husband");
			text(row, 20, phoneOwner);
			numeric(row, 21, 1234567890L);
			numeric(row, 22, 9999999999L);
			text(row, 28, "Address");
			text(row, 30, "General");
			text(row, 45, "ANM");
			text(row, 46, "Asha");
			numeric(row, 47, 7777777777L);
			numeric(row, 48, 8888888888L);
			numeric(row, 69, 55);
			date(row, 70);
			numeric(row, 71, 28);
			text(row, 74, "Yes");
			numeric(row, 79, 2024);
			date(row, 81);
			date(row, 83);
			date(row, 98);
			text(row, 116, highRiskSymptom);
			date(row, 123);
			text(row, 141, highRiskSymptom);
			date(row, 148);
			text(row, 166, highRiskSymptom);
			date(row, 173);
			text(row, 191, highRiskSymptom);
			date(row, 198);
			date(row, 199);
			date(row, 200);
			date(row, 210);
			text(row, 211, "Home");
			text(row, 212, "Home Name");
			text(row, 214, "Normal");
			text(row, 225, "None");
		};
	}

	@SuppressWarnings("unchecked")
	@Test
	void motherUploadMapsEveryColumnAndFlagsHighRisk() throws Exception {
		when(motherRecordRepo.findByEcdIdNo(anyLong())).thenReturn(null);

		String response = service.uploadRCDData(
				request("Mother Data", workbook(List.of(fullMotherRow(VALID_RCH_ID, "Self", "Bleeding")))));

		ArgumentCaptor<List<MotherRecord>> captor = ArgumentCaptor.forClass(List.class);
		verify(motherRecordRepo).saveAll(captor.capture());
		MotherRecord record = captor.getValue().get(0);
		assertEquals(VALID_RCH_ID, record.getEcdIdNo());
		assertEquals("Self", record.getWhomPhoneNo());
		assertEquals("supervisor", record.getCreatedBy());
		assertEquals(false, record.getIsAllocated());
		assertEquals(10, record.getStateID());
		assertEquals("State", record.getStateName());
		assertEquals(20, record.getDistrictID());
		assertEquals("District", record.getDistrictName());
		assertEquals(30, record.getTalukaId());
		assertEquals("Taluka", record.getTalukaName());
		assertEquals(40, record.getBlockID());
		assertEquals("Block", record.getBlockName());
		assertEquals("50", record.getPHCID());
		assertEquals("PHC", record.getPHCName());
		assertEquals(60, record.getSubCenterId());
		assertEquals("SubCentre", record.getSubCenterName());
		assertEquals(70, record.getVillageID());
		assertEquals("Village", record.getVillageName());
		assertEquals("Asha Devi", record.getMotherName());
		assertEquals("Husband", record.getHusbandName());
		assertEquals("1234567890", record.getLandlineNo());
		assertEquals("9999999999", record.getPhoneNo());
		assertEquals("Address", record.getAddress());
		assertEquals("General", record.getCaste());
		assertEquals("ANM", record.getAnmName());
		assertEquals("Asha", record.getAshaName());
		assertEquals("7777777777", record.getAnmPh());
		assertEquals("8888888888", record.getAshaPh());
		assertEquals(987654321012L, record.getMotherMctsId());
		assertEquals(55, record.getMotherWeight());
		assertEquals(28, record.getAge());
		assertEquals("Yes", record.getJsyBeneficiary());
		assertEquals("2024", record.getMotherRegistrationYear());
		assertEquals("Home", record.getDeliveryPlace());
		assertEquals("Home Name", record.getDeliveryPlaceName());
		assertEquals("Normal", record.getDeliveryType());
		assertEquals("None", record.getDeliveryComplications());
		assertEquals(true, record.getHighRisk());
		assertEquals(new Timestamp(SAMPLE_DATE.getTime()), record.getLmpDate());
		assertEquals(new Timestamp(SAMPLE_DATE.getTime()), record.getEdd());
		assertEquals(new Timestamp(SAMPLE_DATE.getTime()), record.getAnc1Date());
		assertEquals(new Timestamp(SAMPLE_DATE.getTime()), record.getAnc4Date());
		assertEquals(new Timestamp(SAMPLE_DATE.getTime()), record.getTt1Date());
		assertEquals(new Timestamp(SAMPLE_DATE.getTime()), record.getDeliveryDate());
		assertTrue(response.contains("1 valid mother records are uploaded in AMRIT"));
	}

	@SuppressWarnings("unchecked")
	@Test
	void motherUploadLeavesHighRiskUnsetWhenSymptomsAreNone() throws Exception {
		service.uploadRCDData(
				request("Mother Data", workbook(List.of(fullMotherRow(VALID_RCH_ID, "husband", "None")))));

		ArgumentCaptor<List<MotherRecord>> captor = ArgumentCaptor.forClass(List.class);
		verify(motherRecordRepo).saveAll(captor.capture());
		assertNull(captor.getValue().get(0).getHighRisk());
	}

	@SuppressWarnings("unchecked")
	@Test
	void motherUploadNormalisesOtherPhoneOwner() throws Exception {
		service.uploadRCDData(
				request("Mother Data", workbook(List.of(fullMotherRow(VALID_RCH_ID, "others", "None")))));

		ArgumentCaptor<List<MotherRecord>> captor = ArgumentCaptor.forClass(List.class);
		verify(motherRecordRepo).saveAll(captor.capture());
		assertEquals("Others", captor.getValue().get(0).getWhomPhoneNo());
	}

	@SuppressWarnings("unchecked")
	@Test
	void motherUploadReadsStringVariantsOfNumericColumns() throws Exception {
		String content = workbook(List.of(row -> {
			text(row, 14, String.valueOf(VALID_RCH_ID));
			text(row, 20, "wife");
			text(row, 21, "1234567890");
			text(row, 22, "9999999999");
			text(row, 17, "987654321012");
			text(row, 47, "7777777777");
			text(row, 48, "8888888888");
		}));

		service.uploadRCDData(request("Mother Data", content));

		ArgumentCaptor<List<MotherRecord>> captor = ArgumentCaptor.forClass(List.class);
		verify(motherRecordRepo).saveAll(captor.capture());
		MotherRecord record = captor.getValue().get(0);
		assertEquals(VALID_RCH_ID, record.getEcdIdNo());
		assertEquals("1234567890", record.getLandlineNo());
		assertEquals("9999999999", record.getPhoneNo());
		assertEquals("7777777777", record.getAnmPh());
		assertEquals("8888888888", record.getAshaPh());
	}

	@Test
	void motherUploadSkipsDuplicateRowsWithinTheSheet() throws Exception {
		String content = workbook(List.of(fullMotherRow(VALID_RCH_ID, "Self", "None"),
				fullMotherRow(VALID_RCH_ID, "Self", "None")));

		String response = service.uploadRCDData(request("Mother Data", content));

		assertTrue(response.contains("1 valid mother records are uploaded"));
		assertTrue(response.contains("1 duplicate records skipped"));
	}

	@Test
	void motherUploadSkipsRecordsAlreadyPresentInAmrit() throws Exception {
		when(motherRecordRepo.findByEcdIdNo(VALID_RCH_ID)).thenReturn(new MotherRecord());

		String response = service.uploadRCDData(
				request("Mother Data", workbook(List.of(fullMotherRow(VALID_RCH_ID, "Self", "None")))));

		assertTrue(response.contains("No valid mother record found to upload"));
		assertTrue(response.contains("1 duplicate records skipped"));
		verify(motherRecordRepo, never()).saveAll(any());
	}

	@Test
	void motherUploadSkipsRowsWithInvalidRchIdLength() throws Exception {
		String response = service.uploadRCDData(
				request("Mother Data", workbook(List.of(fullMotherRow(12345L, "Self", "None")))));

		assertTrue(response.contains("1 invalid records skipped"));
	}

	@Test
	void motherUploadSkipsRowsWithoutRchId() throws Exception {
		String content = workbook(List.<Consumer<Row>>of(row -> text(row, 1, "State")));

		String response = service.uploadRCDData(request("Mother Data", content));

		assertTrue(response.contains("1 invalid records skipped"));
	}

	@Test
	void motherUploadSkipsRowsWithUnknownPhoneOwner() throws Exception {
		String response = service.uploadRCDData(
				request("Mother Data", workbook(List.of(fullMotherRow(VALID_RCH_ID, "uncle", "None")))));

		assertTrue(response.contains("1 invalid records skipped"));
	}

	@Test
	void motherUploadSkipsRowsWithoutAnyPhoneNumber() throws Exception {
		String content = workbook(List.<Consumer<Row>>of(row -> {
			numeric(row, 14, VALID_RCH_ID);
			text(row, 20, "Self");
		}));

		String response = service.uploadRCDData(request("Mother Data", content));

		assertTrue(response.contains("1 invalid records skipped"));
	}

	@Test
	void motherUploadSkipsRowsThatFailToParse() throws Exception {
		String content = workbook(List.<Consumer<Row>>of(row -> {
			numeric(row, 14, VALID_RCH_ID);
			text(row, 20, "Self");
			text(row, 4, "not-a-number");
			numeric(row, 22, 9999999999L);
		}));

		String response = service.uploadRCDData(request("Mother Data", content));

		assertTrue(response.contains("1 invalid records skipped"));
	}

	private Consumer<Row> fullChildRow(long childId, long motherId, String phoneOwner) {
		return row -> {
			numeric(row, 0, 10);
			text(row, 1, "State");
			numeric(row, 2, 20);
			text(row, 3, "District");
			text(row, 4, "30");
			text(row, 5, "Taluka");
			numeric(row, 6, 40);
			text(row, 7, "Block");
			numeric(row, 8, 50);
			text(row, 9, "PHC");
			numeric(row, 10, 60);
			text(row, 11, "SubCentre");
			numeric(row, 12, 70);
			text(row, 13, "Village");
			numeric(row, 14, childId);
			date(row, 17);
			text(row, 18, "Baby");
			text(row, 19, "Female");
			date(row, 20);
			text(row, 21, "Home");
			numeric(row, 22, motherId);
			numeric(row, 24, 987654321012L);
			numeric(row, 25, 876543210987L);
			text(row, 26, "Asha Devi");
			numeric(row, 28, 9999999999L);
			text(row, 29, "Address");
			text(row, 31, "General");
			text(row, 32, "ANM");
			text(row, 33, "Asha");
			numeric(row, 34, 7777777777L);
			numeric(row, 35, 8888888888L);
			text(row, 37, "Father");
			text(row, 38, phoneOwner);
			numeric(row, 39, 3.2);
			text(row, 44, "New");
			date(row, 55);
			date(row, 59);
			date(row, 67);
			date(row, 71);
			date(row, 75);
			date(row, 108);
			date(row, 153);
			date(row, 161);
			date(row, 165);
			date(row, 169);
			date(row, 193);
			date(row, 201);
		};
	}

	@SuppressWarnings("unchecked")
	@Test
	void childUploadMapsEveryColumn() throws Exception {
		when(childRecordRepo.findByEcdIdNoChildId(anyLong())).thenReturn(null);

		String response = service.uploadRCDData(request("Child Data",
				workbook(List.of(fullChildRow(VALID_RCH_ID, 210987654321L, "Self")))));

		ArgumentCaptor<List<ChildRecord>> captor = ArgumentCaptor.forClass(List.class);
		verify(childRecordRepo).saveAll(captor.capture());
		ChildRecord record = captor.getValue().get(0);
		assertEquals(VALID_RCH_ID, record.getEcdIdNoChildId());
		assertEquals(210987654321L, record.getMotherId());
		assertEquals("Self", record.getWhomPhoneNo());
		assertEquals("supervisor", record.getCreatedBy());
		assertEquals(false, record.getIsAllocated());
		assertEquals(10, record.getStateID());
		assertEquals("State", record.getStateName());
		assertEquals(30, record.getTalukaId());
		assertEquals(50, record.getPHCID());
		assertEquals("Baby", record.getChildName());
		assertEquals("Female", record.getGender());
		assertEquals("Home", record.getPlaceOfBirth());
		assertEquals(987654321012L, record.getMotherMctsId());
		assertEquals(876543210987L, record.getChildMctsId());
		assertEquals("Asha Devi", record.getMotherName());
		assertEquals("9999999999", record.getPhoneNo());
		assertEquals("Address", record.getAddress());
		assertEquals("General", record.getCaste());
		assertEquals("ANM", record.getAnmName());
		assertEquals("Asha", record.getAshaName());
		assertEquals("7777777777", record.getAnmPh());
		assertEquals("8888888888", record.getAshaPh());
		assertEquals("Father", record.getFatherName());
		assertEquals(3.2, record.getWeightOfChild());
		assertEquals("New", record.getEntryTypeStr());
		assertEquals(new Timestamp(SAMPLE_DATE.getTime()), record.getDob());
		assertEquals(new Timestamp(SAMPLE_DATE.getTime()), record.getBcgDate());
		assertEquals(new Timestamp(SAMPLE_DATE.getTime()), record.getMrDate());
		assertTrue(response.contains("1 valid child records are uploaded in AMRIT"));
	}

	@SuppressWarnings("unchecked")
	@Test
	void childUploadReadsStringVariantsAndOtherPhoneOwner() throws Exception {
		String content = workbook(List.<Consumer<Row>>of(row -> {
			text(row, 14, String.valueOf(VALID_RCH_ID));
			text(row, 38, "other");
			text(row, 22, "210987654321");
			text(row, 24, "987654321012");
			text(row, 25, String.valueOf(VALID_RCH_ID));
			text(row, 28, "9999999999");
			text(row, 34, "7777777777");
			text(row, 35, "8888888888");
		}));

		service.uploadRCDData(request("Child Data", content));

		ArgumentCaptor<List<ChildRecord>> captor = ArgumentCaptor.forClass(List.class);
		verify(childRecordRepo).saveAll(captor.capture());
		ChildRecord record = captor.getValue().get(0);
		assertEquals("Others", record.getWhomPhoneNo());
		assertEquals(210987654321L, record.getMotherId());
		assertEquals(VALID_RCH_ID, record.getChildMctsId());
		assertEquals("9999999999", record.getPhoneNo());
	}

	@Test
	void childUploadSkipsDuplicatesAndExistingRecords() throws Exception {
		String duplicateSheet = workbook(List.of(fullChildRow(VALID_RCH_ID, 210987654321L, "Self"),
				fullChildRow(VALID_RCH_ID, 210987654321L, "Self")));

		assertTrue(service.uploadRCDData(request("Child Data", duplicateSheet))
				.contains("1 valid child records are uploaded"));

		when(childRecordRepo.findByEcdIdNoChildId(VALID_RCH_ID)).thenReturn(new ChildRecord());
		assertTrue(service
				.uploadRCDData(request("Child Data",
						workbook(List.of(fullChildRow(VALID_RCH_ID, 210987654321L, "Self")))))
				.contains("No valid child record found to upload"));
	}

	@Test
	void childUploadSkipsInvalidIdsAndPhoneOwners() throws Exception {
		assertTrue(service
				.uploadRCDData(
						request("Child Data", workbook(List.of(fullChildRow(12345L, 210987654321L, "Self")))))
				.contains("No valid child record found"));
		assertTrue(service
				.uploadRCDData(request("Child Data",
						workbook(List.of(fullChildRow(VALID_RCH_ID, 12345L, "Self")))))
				.contains("No valid child record found"));
		assertTrue(service
				.uploadRCDData(request("Child Data",
						workbook(List.of(fullChildRow(VALID_RCH_ID, 210987654321L, "uncle")))))
				.contains("No valid child record found"));
	}

	@Test
	void childUploadSkipsRowsThatFailToParse() throws Exception {
		String content = workbook(List.<Consumer<Row>>of(row -> {
			numeric(row, 14, VALID_RCH_ID);
			text(row, 4, "not-a-number");
		}));

		assertTrue(service.uploadRCDData(request("Child Data", content))
				.contains("No valid child record found to upload"));
	}

	@Test
	void uploadRejectsUnknownRecordType() throws Exception {
		String content = workbook(List.of(fullMotherRow(VALID_RCH_ID, "Self", "None")));

		assertThrows(ECDException.class, () -> service.uploadRCDData(request("Father Data", content)));
		assertThrows(ECDException.class, () -> service.uploadRCDData(request(null, content)));
	}

	@Test
	void uploadRejectsMissingFileContent() {
		assertThrows(ECDException.class, () -> service.uploadRCDData(request("Mother Data", null)));
		assertThrows(ECDException.class, () -> service.uploadRCDData(request("Mother Data", "")));
		assertThrows(ECDException.class, () -> service.uploadRCDData(null));
	}

	@Test
	void uploadRejectsCorruptWorkbook() {
		String notAWorkbook = Base64.getEncoder().encodeToString("not-a-workbook".getBytes());

		assertThrows(ECDException.class, () -> service.uploadRCDData(request("Mother Data", notAWorkbook)));
	}

	@Test
	void uploadRejectsSheetsWithTooManyRows() throws Exception {
		try (XSSFWorkbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream()) {
			Sheet sheet = workbook.createSheet("Sheet1");
			for (int i = 0; i <= 1001; i++) {
				Cell cell = sheet.createRow(i).createCell(0);
				cell.setCellValue(i);
			}
			workbook.write(out);
			String content = Base64.getEncoder().encodeToString(out.toByteArray());

			ECDException exception = assertThrows(ECDException.class,
					() -> service.uploadRCDData(request("Mother Data", content)));
			assertTrue(exception.getMessage().contains("1000"));
		}
	}

	private static void blank(Row row, int index) {
		row.createCell(index);
	}

	private static final int[] MOTHER_COLUMNS = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 17, 18, 19, 20, 21,
			28, 30, 45, 46, 47, 48, 69, 70, 71, 74, 79, 81, 83, 98, 116, 123, 141, 148, 166, 173, 191, 198, 199,
			200, 210, 211, 212, 214, 225 };

	private static final int[] MOTHER_DATE_COLUMNS = { 70, 81, 83, 98, 123, 148, 173, 198, 199, 200, 210 };

	private static final int[] CHILD_COLUMNS = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 17, 18, 19, 20, 21,
			22, 24, 25, 26, 28, 29, 31, 32, 33, 34, 35, 37, 38, 39, 44, 55, 59, 67, 71, 75, 108, 153, 161, 165,
			169, 193, 201 };

	private static final int[] CHILD_DATE_COLUMNS = { 17, 20, 55, 59, 67, 71, 75, 108, 153, 161, 165, 169, 193,
			201 };

	@Test
	void motherUploadAcceptsRowWithOnlyMandatoryColumns() throws Exception {
		String content = workbook(List.<Consumer<Row>>of(row -> {
			numeric(row, 14, VALID_RCH_ID);
			text(row, 20, "Self");
			numeric(row, 22, 9999999999L);
		}));

		assertTrue(service.uploadRCDData(request("Mother Data", content))
				.contains("1 valid mother records are uploaded"));
	}

	@Test
	void motherUploadTreatsBlankCellsAsMissingValues() throws Exception {
		String content = workbook(List.<Consumer<Row>>of(row -> {
			numeric(row, 14, VALID_RCH_ID);
			numeric(row, 22, 9999999999L);
			for (int column : MOTHER_COLUMNS) {
				blank(row, column);
			}
		}));

		assertTrue(service.uploadRCDData(request("Mother Data", content))
				.contains("1 valid mother records are uploaded"));
	}

	@Test
	void motherUploadIgnoresNonNumericDateColumns() throws Exception {
		String content = workbook(List.<Consumer<Row>>of(row -> {
			numeric(row, 14, VALID_RCH_ID);
			numeric(row, 22, 9999999999L);
			for (int column : MOTHER_DATE_COLUMNS) {
				text(row, column, "not-a-date");
			}
		}));

		assertTrue(service.uploadRCDData(request("Mother Data", content))
				.contains("1 valid mother records are uploaded"));
	}

	@Test
	void childUploadAcceptsRowWithOnlyMandatoryColumns() throws Exception {
		String content = workbook(List.<Consumer<Row>>of(row -> numeric(row, 14, VALID_RCH_ID)));

		assertTrue(service.uploadRCDData(request("Child Data", content))
				.contains("1 valid child records are uploaded"));
	}

	@Test
	void childUploadTreatsBlankCellsAsMissingValues() throws Exception {
		String content = workbook(List.<Consumer<Row>>of(row -> {
			numeric(row, 14, VALID_RCH_ID);
			for (int column : CHILD_COLUMNS) {
				blank(row, column);
			}
		}));

		assertTrue(service.uploadRCDData(request("Child Data", content))
				.contains("1 valid child records are uploaded"));
	}

	@Test
	void childUploadIgnoresNonNumericDateColumns() throws Exception {
		String content = workbook(List.<Consumer<Row>>of(row -> {
			numeric(row, 14, VALID_RCH_ID);
			for (int column : CHILD_DATE_COLUMNS) {
				text(row, column, "not-a-date");
			}
		}));

		assertTrue(service.uploadRCDData(request("Child Data", content))
				.contains("1 valid child records are uploaded"));
	}

	@Test
	void timestampParsingUsesIsoDateTime() {
		Timestamp timestamp = ReflectionTestUtils.invokeMethod(service, "getTimestampFromString",
				"2024-01-01T10:30:00+05:30");

		assertEquals(Timestamp.valueOf("2024-01-01 10:30:00"), timestamp);
	}
}
