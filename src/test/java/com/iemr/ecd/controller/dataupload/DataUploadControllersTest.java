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
package com.iemr.ecd.controller.dataupload;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.iemr.ecd.dao.DataTemplate;
import com.iemr.ecd.dto.supervisor.RCHFileUploadDto;
import com.iemr.ecd.service.data_upload.DataTemplateServiceImpl;
import com.iemr.ecd.service.data_upload.RCHDataUploadServiceImpl;

@ExtendWith(MockitoExtension.class)
class DataUploadControllersTest {

	@Mock
	private DataTemplateServiceImpl dataTemplateServiceImpl;
	@Mock
	private RCHDataUploadServiceImpl rchDataUploadServiceImpl;

	@InjectMocks
	private DataTemplateController dataTemplateController;
	@InjectMocks
	private DataUploadController dataUploadController;

	@Test
	void uploadTemplateDelegatesToService() throws Exception {
		DataTemplate dataTemplate = new DataTemplate();
		when(dataTemplateServiceImpl.uploadTemplate(dataTemplate)).thenReturn("uploaded");

		ResponseEntity<Object> response = dataTemplateController.uploadTemplate(dataTemplate);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals("uploaded", response.getBody());
	}

	@Test
	void downloadTemplateDelegatesToService() {
		DataTemplate dataTemplate = new DataTemplate();
		when(dataTemplateServiceImpl.downloadTemplate(1, "RCH")).thenReturn(dataTemplate);

		assertSame(dataTemplate, dataTemplateController.downloadTemplate(1, "RCH").getBody());
	}

	@Test
	void uploadRchDataDelegatesToService() {
		RCHFileUploadDto request = new RCHFileUploadDto();
		when(rchDataUploadServiceImpl.uploadRCDData(request)).thenReturn("done");

		ResponseEntity<Object> response = dataUploadController.allocateCalls("auth", request);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals("done", response.getBody());
	}
}
