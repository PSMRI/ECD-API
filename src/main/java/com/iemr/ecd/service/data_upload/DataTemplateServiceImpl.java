/*

* AMRIT – Accessible Medical Records via Integrated Technology
* Integrated EHR (Electronic Health Records) Solution
* Copyright (C) "Piramal Swasthya Management and Research Institute"
* This file is part of AMRIT.
* This program is free software: you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation, either version 3 of the License, or
* (at your option) any later version.
* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with this program.  If not, see https://www.gnu.org/licenses/.

*/
 
package com.iemr.ecd.service.data_upload; 

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.*;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
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
 
    private static final Set<String> ALLOWED_EXTENSIONS = Set.of("xls", "xlsx", "xlsm", "xlsb");
 
    private void validateExtension(String ext) {
        if (!ALLOWED_EXTENSIONS.contains(ext)) {
            throw new InvalidRequestException(
                "Invalid file type", 
                "Only Excel files are allowed (.xls, .xlsx, .xlsm, .xlsb)"
            );
        }
    }
 
    /** Validate MIME type using magic bytes for Excel files*/

    private void validateMimeType(byte[] fileContent, String ext) throws IOException {

        // Check magic bytes for Excel files
        boolean isValidExcel = isValidExcelMagicBytes(fileContent);
        if (!isValidExcel) {
            throw new InvalidRequestException("Invalid MIME type", "Uploaded content is not a valid Excel file");
        }
    }

    

    /** Check if file has valid Excel magic bytes*/

    private boolean isValidExcelMagicBytes(byte[] fileContent) {
        if (fileContent == null || fileContent.length < 4) {
            return false;
        }
        // XLS (OLE2) magic bytes: D0 CF 11 E0
        boolean isOle2 = fileContent[0] == (byte) 0xD0 && fileContent[1] == (byte) 0xCF && fileContent[2] == (byte) 0x11 && fileContent[3] == (byte) 0xE0;
         // XLSX (ZIP) magic bytes: 50 4B 03 04
        boolean isZip = fileContent[0] == (byte) 0x50 && fileContent[1] == (byte) 0x4B  && fileContent[2] == (byte) 0x03 && fileContent[3] == (byte) 0x04;
        return isOle2 || isZip;
    }

    

    /** Validate Excel file with Apache POI*/

    private void validateExcelContent(byte[] content) {
        try (InputStream is = new ByteArrayInputStream(content)) {
            Workbook workbook = WorkbookFactory.create(is);  // Valid Excel → OK
            workbook.close();
        } catch (Exception e) {
            throw new InvalidRequestException(
                "Invalid Excel File",
                "Uploaded file is corrupted or not a valid Excel file"
            );
        }
    }
 
 
@Transactional(rollbackOn = Exception.class)
public String uploadTemplate(DataTemplate dataTemplate) {

    try {
        if (dataTemplate == null ||
            dataTemplate.getFileContent() == null ||
            dataTemplate.getFileContent().isEmpty() ||
            dataTemplate.getFileName() == null) {
            throw new InvalidRequestException("NULL/Invalid filecontent", "pass valid file-content");
        }
 
        // String → Base64 decode → byte[]
        byte[] fileContent = Base64.getDecoder().decode(dataTemplate.getFileContent());
        String fileName = dataTemplate.getFileName();
        String ext = getExtension(fileName);
 
        // Step 1: Validate extension
        validateExtension(ext);
 
        // Step 2: Validate Excel integrity using Apache POI
        validateExcelContent(fileContent);
 
        // Step 3: Soft delete old file
        List<DataTemplate> resultList = new ArrayList<>();
        DataTemplate tempObj = downloadTemplate(dataTemplate.getPsmId(), dataTemplate.getFileType());
         if (tempObj != null && tempObj.getFileId() != null) {
            tempObj.setDeleted(true);
            resultList.add(tempObj);
        }
 
        // Step 4: Save new Base64 string (unchanged)

        resultList.add(dataTemplate);
        dataTemplateRepo.saveAll(resultList);
         Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("response", "template uploaded successfully");
        return new Gson().toJson(responseMap);
    } catch (Exception e) {
        throw new ECDException(e);
    }
}
 
   
 
    private String getExtension(String fileName) {
        if (fileName == null || !fileName.contains(".")) {
            return "";
        }
        return fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
    }
 
    public DataTemplate downloadTemplate(Integer psmId, String fileType) {
        try {
            return dataTemplateRepo.findByPsmIdAndFileTypeAndDeleted(psmId, fileType, false);
        } catch (Exception e) {
            throw new ECDException(e);
        }
    }
}

 