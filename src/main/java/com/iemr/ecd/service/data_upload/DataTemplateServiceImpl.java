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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.iemr.ecd.dao.DataTemplate;
import com.iemr.ecd.repository.ecd.DataTemplateRepo;
import com.iemr.ecd.utils.advice.exception_handler.ECDException;
import com.iemr.ecd.utils.advice.exception_handler.InvalidRequestException;

import jakarta.transaction.Transactional;

@Service
public class DataTemplateServiceImpl {
	@Autowired
	private DataTemplateRepo dataTemplateRepo;

	@Transactional(rollbackOn = Exception.class)
	public String uploadTemplate(DataTemplate dataTemplate) {
		List<DataTemplate> resultList = new ArrayList<>();
		try {
			if (dataTemplate != null && dataTemplate.getFileContent() != null
					&& dataTemplate.getFileContent().length() > 0 && dataTemplate.getFileName() != null) {

				DataTemplate tempObj = downloadTemplate(dataTemplate.getPsmId(), dataTemplate.getFileType());
				if (tempObj != null && tempObj.getFileId() != null) {
					tempObj.setDeleted(true);
					resultList.add(tempObj);
				}
				resultList.add(dataTemplate);

				dataTemplateRepo.saveAll(resultList);

			} else
				throw new InvalidRequestException("NULL/Invalid filecontent", "pass valid file-content");

			Map<String, Object> responseMap = new HashMap<>();
			responseMap.put("response", "template uploaded successfully");
			return new Gson().toJson(responseMap);

		} catch (Exception e) {
			throw new ECDException(e);
		}

	}

	public DataTemplate downloadTemplate(Integer psmId, String fileType) {
		try {
			return dataTemplateRepo.findByPsmIdAndFileTypeAndDeleted(psmId, fileType, false);
		} catch (Exception e) {
			throw new ECDException(e);
		}
	}

}
