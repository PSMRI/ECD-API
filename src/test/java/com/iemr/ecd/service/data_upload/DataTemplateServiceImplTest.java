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
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.ByteArrayOutputStream;
import java.util.Base64;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import com.iemr.ecd.dao.DataTemplate;
import com.iemr.ecd.repository.ecd.DataTemplateRepo;
import com.iemr.ecd.utils.advice.exception_handler.ECDException;
import com.iemr.ecd.utils.advice.exception_handler.InvalidRequestException;

@ExtendWith(MockitoExtension.class)
class DataTemplateServiceImplTest {

	private static String validXlsxBase64;

	@Mock
	private DataTemplateRepo dataTemplateRepo;

	@InjectMocks
	private DataTemplateServiceImpl service;

	@BeforeAll
	static void buildWorkbook() throws Exception {
		try (XSSFWorkbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream()) {
			workbook.createSheet("Sheet1").createRow(0).createCell(0).setCellValue("header");
			workbook.write(out);
			validXlsxBase64 = Base64.getEncoder().encodeToString(out.toByteArray());
		}
	}

	private DataTemplate template(String fileName, String content) {
		DataTemplate template = new DataTemplate();
		template.setFileName(fileName);
		template.setFileContent(content);
		template.setPsmId(1);
		template.setFileType("RCH");
		return template;
	}

	@SuppressWarnings("unchecked")
	@Test
	void uploadTemplateSavesNewTemplate() {
		when(dataTemplateRepo.findByPsmIdAndFileTypeAndDeleted(1, "RCH", false)).thenReturn(null);
		DataTemplate template = template("upload.xlsx", validXlsxBase64);

		String response = service.uploadTemplate(template);

		ArgumentCaptor<List<DataTemplate>> captor = ArgumentCaptor.forClass(List.class);
		verify(dataTemplateRepo).saveAll(captor.capture());
		assertEquals(List.of(template), captor.getValue());
		assertTrue(response.contains("template uploaded successfully"));
	}

	@SuppressWarnings("unchecked")
	@Test
	void uploadTemplateSoftDeletesExistingTemplate() {
		DataTemplate existing = new DataTemplate();
		existing.setFileId(9);
		when(dataTemplateRepo.findByPsmIdAndFileTypeAndDeleted(1, "RCH", false)).thenReturn(existing);

		service.uploadTemplate(template("upload.xlsx", validXlsxBase64));

		ArgumentCaptor<List<DataTemplate>> captor = ArgumentCaptor.forClass(List.class);
		verify(dataTemplateRepo).saveAll(captor.capture());
		assertEquals(2, captor.getValue().size());
		assertTrue(existing.getDeleted());
	}

	@SuppressWarnings("unchecked")
	@Test
	void uploadTemplateIgnoresExistingTemplateWithoutFileId() {
		when(dataTemplateRepo.findByPsmIdAndFileTypeAndDeleted(1, "RCH", false)).thenReturn(new DataTemplate());

		service.uploadTemplate(template("upload.xlsx", validXlsxBase64));

		ArgumentCaptor<List<DataTemplate>> captor = ArgumentCaptor.forClass(List.class);
		verify(dataTemplateRepo).saveAll(captor.capture());
		assertEquals(1, captor.getValue().size());
	}

	@Test
	void uploadTemplateRejectsMissingPayload() {
		assertThrows(ECDException.class, () -> service.uploadTemplate(null));
		assertThrows(ECDException.class, () -> service.uploadTemplate(template("upload.xlsx", null)));
		assertThrows(ECDException.class, () -> service.uploadTemplate(template("upload.xlsx", "")));
		assertThrows(ECDException.class, () -> service.uploadTemplate(template(null, validXlsxBase64)));
		verify(dataTemplateRepo, never()).saveAll(any());
	}

	@Test
	void uploadTemplateRejectsNonExcelExtension() {
		assertThrows(ECDException.class, () -> service.uploadTemplate(template("upload.csv", validXlsxBase64)));
		assertThrows(ECDException.class, () -> service.uploadTemplate(template("upload", validXlsxBase64)));
	}

	@Test
	void uploadTemplateRejectsCorruptWorkbook() {
		String notAWorkbook = Base64.getEncoder().encodeToString("not-a-workbook".getBytes());

		assertThrows(ECDException.class, () -> service.uploadTemplate(template("upload.xlsx", notAWorkbook)));
	}

	@Test
	void downloadTemplateDelegatesToRepository() {
		DataTemplate template = new DataTemplate();
		when(dataTemplateRepo.findByPsmIdAndFileTypeAndDeleted(1, "RCH", false)).thenReturn(template);

		assertSame(template, service.downloadTemplate(1, "RCH"));
	}

	@Test
	void downloadTemplateWrapsRepositoryFailure() {
		when(dataTemplateRepo.findByPsmIdAndFileTypeAndDeleted(anyInt(), anyString(), anyBoolean()))
				.thenThrow(new IllegalStateException("db"));

		assertThrows(ECDException.class, () -> service.downloadTemplate(1, "RCH"));
	}

	private boolean hasExcelMagicBytes(byte[] content) {
		Boolean result = ReflectionTestUtils.invokeMethod(service, "isValidExcelMagicBytes", (Object) content);
		return Boolean.TRUE.equals(result);
	}

	@Test
	void magicByteValidationAcceptsExcelSignatures() {
		assertTrue(hasExcelMagicBytes(new byte[] { (byte) 0xD0, (byte) 0xCF, (byte) 0x11, (byte) 0xE0 }));
		assertTrue(hasExcelMagicBytes(new byte[] { (byte) 0x50, (byte) 0x4B, (byte) 0x03, (byte) 0x04 }));
	}

	@Test
	void magicByteValidationRejectsOtherContent() {
		assertFalse(hasExcelMagicBytes(null));
		assertFalse(hasExcelMagicBytes(new byte[] { 1, 2 }));
		assertFalse(hasExcelMagicBytes(new byte[] { 1, 2, 3, 4 }));
	}

	@Test
	void mimeTypeValidationAcceptsExcelAndRejectsOtherContent() {
		byte[] zip = { (byte) 0x50, (byte) 0x4B, (byte) 0x03, (byte) 0x04 };

		ReflectionTestUtils.invokeMethod(service, "validateMimeType", zip, "xlsx");

		assertThrows(InvalidRequestException.class,
				() -> ReflectionTestUtils.invokeMethod(service, "validateMimeType", new byte[] { 1, 2, 3, 4 },
						"xlsx"));
	}

	@Test
	void extensionValidationRejectsUnsupportedTypes() {
		ReflectionTestUtils.invokeMethod(service, "validateExtension", "xlsx");

		assertThrows(InvalidRequestException.class,
				() -> ReflectionTestUtils.invokeMethod(service, "validateExtension", "csv"));
	}

	@Test
	void getExtensionHandlesNamesWithoutExtension() {
		assertEquals("", ReflectionTestUtils.invokeMethod(service, "getExtension", (Object) null));
		assertEquals("", ReflectionTestUtils.invokeMethod(service, "getExtension", "noextension"));
		assertEquals("xlsx", ReflectionTestUtils.invokeMethod(service, "getExtension", "Some.File.XLSX"));
	}
}
