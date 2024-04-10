package com.iemr.ecd.controller.dataupload;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import java.sql.Timestamp;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.iemr.ecd.dao.DataTemplate;
import com.iemr.ecd.service.data_upload.DataTemplateServiceImpl;

@ContextConfiguration(classes = { DataTemplateController.class })
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class DataTemplateControllerDiffblueTest {
	@Autowired
	private DataTemplateController dataTemplateController;

	@MockBean
	private DataTemplateServiceImpl dataTemplateServiceImpl;

	@Test
	void testDownloadTemplate() throws Exception {

		Integer psmId = 123;
		String fileType = "pdf";

		ResponseEntity<Object> response = dataTemplateController.downloadTemplate(psmId, fileType);

		assertEquals(response, dataTemplateController.downloadTemplate(psmId, fileType));
	}

	@Test
	void testUploadTemplate() throws Exception {

		DataTemplate dataTemplate = new DataTemplate();
		dataTemplate.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
		dataTemplate.setCreatedDate(mock(Timestamp.class));
		dataTemplate.setDeleted(true);
		dataTemplate.setFileContent("Not all who wander are lost");
		dataTemplate.setFileId(1);
		dataTemplate.setFileName("foo.txt");
		dataTemplate.setFileType("File Type");
		dataTemplate.setFileTypeId(1);
		dataTemplate.setLastModDate(mock(Timestamp.class));
		dataTemplate.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
		dataTemplate.setPsmId(1);

		dataTemplate.toString();

		ResponseEntity<Object> response = dataTemplateController.uploadTemplate(dataTemplate);

		assertEquals(response, dataTemplateController.uploadTemplate(dataTemplate));

	}
}
