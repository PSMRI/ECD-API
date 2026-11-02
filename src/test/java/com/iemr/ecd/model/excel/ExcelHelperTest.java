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
package com.iemr.ecd.model.excel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.util.List;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Test;

class ExcelHelperTest {

	private Criteria criteria() {
		Criteria criteria = new Criteria();
		criteria.setStart_Date("2024-01-01 00:00:00.123");
		criteria.setEnd_Date("2024-01-31 23:59:59.456");
		criteria.setAgent_Id("agent-1");
		criteria.setType("ASSOCIATE");
		return criteria;
	}

	@Test
	void tutorialsToExcelBuildsCriteriaAndReportSheets() throws Exception {
		String[] headers = { "S.No", "Name", "Date" };
		List<Object[]> rows = List.of(new Object[] { "ben-1", "2024-02-01" }, new Object[] { null, "plain" });

		ByteArrayInputStream stream = ExcelHelper.tutorialsToExcel(headers, rows, criteria(),
				new String[] { "Start_Date", "End_Date", "Role", "Agent_Id", "Unknown" });

		assertNotNull(stream);
		try (Workbook workbook = new XSSFWorkbook(stream)) {
			Sheet criteriaSheet = workbook.getSheet("Criteria");
			assertEquals("Filter Name", criteriaSheet.getRow(0).getCell(0).getStringCellValue());
			assertEquals("2024-01-01 00:00:00", criteriaSheet.getRow(1).getCell(1).getStringCellValue());
			assertEquals("2024-01-31 23:59:59", criteriaSheet.getRow(2).getCell(1).getStringCellValue());
			assertEquals("ASSOCIATE", criteriaSheet.getRow(3).getCell(1).getStringCellValue());
			assertEquals("agent-1", criteriaSheet.getRow(4).getCell(1).getStringCellValue());

			Sheet reportSheet = workbook.getSheet("Report");
			assertEquals("S.No", reportSheet.getRow(0).getCell(0).getStringCellValue());
			assertEquals(1.0, reportSheet.getRow(1).getCell(0).getNumericCellValue());
			assertEquals("ben-1", reportSheet.getRow(1).getCell(1).getStringCellValue());
			assertEquals("", reportSheet.getRow(2).getCell(1).getStringCellValue());
		}
	}

	@Test
	void tutorialsToExcelWrapsFailures() {
		Exception exception = assertThrows(Exception.class,
				() -> ExcelHelper.tutorialsToExcel(new String[] { "a" }, List.of(), new Criteria(),
						new String[] { "Start_Date" }));

		assertTrue(exception.getMessage().startsWith("fail to import data to Excel file"));
	}

	@Test
	void isValidDateAcceptsIsoDates() {
		assertTrue(ExcelHelper.isValidDate("2024-01-01"));
	}

	@Test
	void isValidDateRejectsNonDates() {
		assertFalse(ExcelHelper.isValidDate("not-a-date"));
		assertFalse(ExcelHelper.isValidDate(""));
	}

	@Test
	void typeConstantIsSpreadsheetMimeType() {
		assertEquals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", ExcelHelper.TYPE);
		assertNotNull(new ExcelHelper());
	}

	@Test
	void criteriaAccessorsRoundTrip() {
		Criteria criteria = criteria();

		assertEquals("agent-1", criteria.getAgent_Id());
		assertEquals("ASSOCIATE", criteria.getRole());
		assertEquals("2024-01-01 00:00:00.123", criteria.getStart_Date());
		assertEquals("2024-01-31 23:59:59.456", criteria.getEnd_Date());
	}
}
