package com.iemr.ecd.controller.dataupload;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.iemr.ecd.dto.supervisor.RCHFileUploadDto;
import com.iemr.ecd.service.data_upload.RCHDataUploadServiceImpl;

@ContextConfiguration(classes = { DataUploadController.class, RCHFileUploadDto.class })
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class DataUploadControllerDiffblueTest {
	@Autowired
	private DataUploadController dataUploadController;

	@MockBean
	private RCHDataUploadServiceImpl rCHDataUploadServiceImpl;

	@Autowired
	private RCHFileUploadDto rCHFileUploadDto;

	@Test
	void testAllocateCalls() throws Exception {

		String Authorization = "Authorization";

		RCHFileUploadDto rchFileUploadDto = new RCHFileUploadDto();
		rchFileUploadDto.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
		rchFileUploadDto.setFieldFor("Field For");
		rchFileUploadDto.setFileContent("Not all who wander are lost");
		rchFileUploadDto.setFileExtension("File Extension");
		rchFileUploadDto.setFileName("foo.txt");
		rchFileUploadDto.setProviderServiceMapID(1);
		rchFileUploadDto.setUserID(1);

		rchFileUploadDto.toString();

		ResponseEntity<Object> response = dataUploadController.allocateCalls(Authorization, rchFileUploadDto);

		assertEquals(response, dataUploadController.allocateCalls(Authorization, rchFileUploadDto));
	}
}
