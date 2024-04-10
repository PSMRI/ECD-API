package com.iemr.ecd.service.data_upload;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.iemr.ecd.dao.DataTemplate;
import com.iemr.ecd.repository.ecd.DataTemplateRepo;
import com.iemr.ecd.utils.advice.exception_handler.ECDException;
import com.iemr.ecd.utils.advice.exception_handler.InvalidRequestException;

import java.sql.Timestamp;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {DataTemplateServiceImpl.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class DataTemplateServiceImplDiffblueTest {
    @MockBean
    private DataTemplateRepo dataTemplateRepo;

    @Autowired
    private DataTemplateServiceImpl dataTemplateServiceImpl;

    /**
     * Method under test:
     * {@link DataTemplateServiceImpl#uploadTemplate(DataTemplate)}
     */
    @Test
    void testUploadTemplate() {
        // Arrange
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
        
        when(dataTemplateRepo.findByPsmIdAndFileTypeAndDeleted(Mockito.<Integer>any(), Mockito.<String>any(),
                Mockito.<Boolean>any())).thenReturn(dataTemplate);
        when(dataTemplateRepo.saveAll(Mockito.<Iterable<DataTemplate>>any())).thenReturn(new ArrayList<>());

        DataTemplate dataTemplate2 = new DataTemplate();
        dataTemplate2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        dataTemplate2.setCreatedDate(mock(Timestamp.class));
        dataTemplate2.setDeleted(true);
        dataTemplate2.setFileContent("Not all who wander are lost");
        dataTemplate2.setFileId(1);
        dataTemplate2.setFileName("foo.txt");
        dataTemplate2.setFileType("File Type");
        dataTemplate2.setFileTypeId(1);
        dataTemplate2.setLastModDate(mock(Timestamp.class));
        dataTemplate2.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        dataTemplate2.setPsmId(1);

        // Act
        String actualUploadTemplateResult = dataTemplateServiceImpl.uploadTemplate(dataTemplate2);

        // Assert
        verify(dataTemplateRepo).findByPsmIdAndFileTypeAndDeleted(isA(Integer.class), eq("File Type"), isA(Boolean.class));
        verify(dataTemplateRepo).saveAll(isA(Iterable.class));
        assertEquals("{\"response\":\"template uploaded successfully\"}", actualUploadTemplateResult);
    }

    /**
     * Method under test:
     * {@link DataTemplateServiceImpl#uploadTemplate(DataTemplate)}
     */
    @Test
    void testUploadTemplate2() {
        // Arrange
        DataTemplate dataTemplate = new DataTemplate();
        dataTemplate.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        dataTemplate.setCreatedDate(mock(Timestamp.class));
        dataTemplate.setDeleted(true);
        dataTemplate.setFileContent("Not all who wander are lost");
        dataTemplate.setFileId(null);
        dataTemplate.setFileName("foo.txt");
        dataTemplate.setFileType("File Type");
        dataTemplate.setFileTypeId(1);
        dataTemplate.setLastModDate(mock(Timestamp.class));
        dataTemplate.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        dataTemplate.setPsmId(1);
        when(dataTemplateRepo.findByPsmIdAndFileTypeAndDeleted(Mockito.<Integer>any(), Mockito.<String>any(),
                Mockito.<Boolean>any())).thenReturn(dataTemplate);
        when(dataTemplateRepo.saveAll(Mockito.<Iterable<DataTemplate>>any())).thenReturn(new ArrayList<>());

        DataTemplate dataTemplate2 = new DataTemplate();
        dataTemplate2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        dataTemplate2.setCreatedDate(mock(Timestamp.class));
        dataTemplate2.setDeleted(true);
        dataTemplate2.setFileContent("Not all who wander are lost");
        dataTemplate2.setFileId(1);
        dataTemplate2.setFileName("foo.txt");
        dataTemplate2.setFileType("File Type");
        dataTemplate2.setFileTypeId(1);
        dataTemplate2.setLastModDate(mock(Timestamp.class));
        dataTemplate2.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        dataTemplate2.setPsmId(1);

        // Act
        String actualUploadTemplateResult = dataTemplateServiceImpl.uploadTemplate(dataTemplate2);

        // Assert
        verify(dataTemplateRepo).findByPsmIdAndFileTypeAndDeleted(isA(Integer.class), eq("File Type"), isA(Boolean.class));
        verify(dataTemplateRepo).saveAll(isA(Iterable.class));
        assertEquals("{\"response\":\"template uploaded successfully\"}", actualUploadTemplateResult);
    }

    /**
     * Method under test:
     * {@link DataTemplateServiceImpl#uploadTemplate(DataTemplate)}
     */
    @Test
    void testUploadTemplate3() {
        // Arrange
        DataTemplate dataTemplate = mock(DataTemplate.class);
        when(dataTemplate.getPsmId()).thenThrow(new ECDException("Exception Msg"));
        when(dataTemplate.getFileName()).thenReturn("foo.txt");
        when(dataTemplate.getFileContent()).thenReturn("Not all who wander are lost");
        doNothing().when(dataTemplate).setCreatedBy(Mockito.<String>any());
        doNothing().when(dataTemplate).setCreatedDate(Mockito.<Timestamp>any());
        doNothing().when(dataTemplate).setDeleted(Mockito.<Boolean>any());
        doNothing().when(dataTemplate).setFileContent(Mockito.<String>any());
        doNothing().when(dataTemplate).setFileId(Mockito.<Integer>any());
        doNothing().when(dataTemplate).setFileName(Mockito.<String>any());
        doNothing().when(dataTemplate).setFileType(Mockito.<String>any());
        doNothing().when(dataTemplate).setFileTypeId(Mockito.<Integer>any());
        doNothing().when(dataTemplate).setLastModDate(Mockito.<Timestamp>any());
        doNothing().when(dataTemplate).setModifiedBy(Mockito.<String>any());
        doNothing().when(dataTemplate).setPsmId(Mockito.<Integer>any());
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

        // Act and Assert
        assertThrows(ECDException.class, () -> dataTemplateServiceImpl.uploadTemplate(dataTemplate));
        verify(dataTemplate, atLeast(1)).getFileContent();
        verify(dataTemplate).getFileName();
        verify(dataTemplate).getPsmId();
        verify(dataTemplate).setCreatedBy(eq("Jan 1, 2020 8:00am GMT+0100"));
        verify(dataTemplate).setCreatedDate(isA(Timestamp.class));
        verify(dataTemplate).setDeleted(isA(Boolean.class));
        verify(dataTemplate).setFileContent(eq("Not all who wander are lost"));
        verify(dataTemplate).setFileId(isA(Integer.class));
        verify(dataTemplate).setFileName(eq("foo.txt"));
        verify(dataTemplate).setFileType(eq("File Type"));
        verify(dataTemplate).setFileTypeId(isA(Integer.class));
        verify(dataTemplate).setLastModDate(isA(Timestamp.class));
        verify(dataTemplate).setModifiedBy(eq("Jan 1, 2020 9:00am GMT+0100"));
        verify(dataTemplate).setPsmId(isA(Integer.class));
    }

    /**
     * Method under test:
     * {@link DataTemplateServiceImpl#uploadTemplate(DataTemplate)}
     */
    @Test
    void testUploadTemplate4() {
        // Arrange
        DataTemplate dataTemplate = mock(DataTemplate.class);
        when(dataTemplate.getFileName()).thenReturn(null);
        when(dataTemplate.getFileContent()).thenReturn("Not all who wander are lost");
        doNothing().when(dataTemplate).setCreatedBy(Mockito.<String>any());
        doNothing().when(dataTemplate).setCreatedDate(Mockito.<Timestamp>any());
        doNothing().when(dataTemplate).setDeleted(Mockito.<Boolean>any());
        doNothing().when(dataTemplate).setFileContent(Mockito.<String>any());
        doNothing().when(dataTemplate).setFileId(Mockito.<Integer>any());
        doNothing().when(dataTemplate).setFileName(Mockito.<String>any());
        doNothing().when(dataTemplate).setFileType(Mockito.<String>any());
        doNothing().when(dataTemplate).setFileTypeId(Mockito.<Integer>any());
        doNothing().when(dataTemplate).setLastModDate(Mockito.<Timestamp>any());
        doNothing().when(dataTemplate).setModifiedBy(Mockito.<String>any());
        doNothing().when(dataTemplate).setPsmId(Mockito.<Integer>any());
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

        // Act and Assert
        assertThrows(ECDException.class, () -> dataTemplateServiceImpl.uploadTemplate(dataTemplate));
        verify(dataTemplate, atLeast(1)).getFileContent();
        verify(dataTemplate).getFileName();
        verify(dataTemplate).setCreatedBy(eq("Jan 1, 2020 8:00am GMT+0100"));
        verify(dataTemplate).setCreatedDate(isA(Timestamp.class));
        verify(dataTemplate).setDeleted(isA(Boolean.class));
        verify(dataTemplate).setFileContent(eq("Not all who wander are lost"));
        verify(dataTemplate).setFileId(isA(Integer.class));
        verify(dataTemplate).setFileName(eq("foo.txt"));
        verify(dataTemplate).setFileType(eq("File Type"));
        verify(dataTemplate).setFileTypeId(isA(Integer.class));
        verify(dataTemplate).setLastModDate(isA(Timestamp.class));
        verify(dataTemplate).setModifiedBy(eq("Jan 1, 2020 9:00am GMT+0100"));
        verify(dataTemplate).setPsmId(isA(Integer.class));
    }

    /**
     * Method under test:
     * {@link DataTemplateServiceImpl#uploadTemplate(DataTemplate)}
     */
    @Test
    void testUploadTemplate5() {
        // Arrange
        DataTemplate dataTemplate = mock(DataTemplate.class);
        when(dataTemplate.getFileContent()).thenReturn(null);
        doNothing().when(dataTemplate).setCreatedBy(Mockito.<String>any());
        doNothing().when(dataTemplate).setCreatedDate(Mockito.<Timestamp>any());
        doNothing().when(dataTemplate).setDeleted(Mockito.<Boolean>any());
        doNothing().when(dataTemplate).setFileContent(Mockito.<String>any());
        doNothing().when(dataTemplate).setFileId(Mockito.<Integer>any());
        doNothing().when(dataTemplate).setFileName(Mockito.<String>any());
        doNothing().when(dataTemplate).setFileType(Mockito.<String>any());
        doNothing().when(dataTemplate).setFileTypeId(Mockito.<Integer>any());
        doNothing().when(dataTemplate).setLastModDate(Mockito.<Timestamp>any());
        doNothing().when(dataTemplate).setModifiedBy(Mockito.<String>any());
        doNothing().when(dataTemplate).setPsmId(Mockito.<Integer>any());
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

        // Act and Assert
        assertThrows(ECDException.class, () -> dataTemplateServiceImpl.uploadTemplate(dataTemplate));
        verify(dataTemplate).getFileContent();
        verify(dataTemplate).setCreatedBy(eq("Jan 1, 2020 8:00am GMT+0100"));
        verify(dataTemplate).setCreatedDate(isA(Timestamp.class));
        verify(dataTemplate).setDeleted(isA(Boolean.class));
        verify(dataTemplate).setFileContent(eq("Not all who wander are lost"));
        verify(dataTemplate).setFileId(isA(Integer.class));
        verify(dataTemplate).setFileName(eq("foo.txt"));
        verify(dataTemplate).setFileType(eq("File Type"));
        verify(dataTemplate).setFileTypeId(isA(Integer.class));
        verify(dataTemplate).setLastModDate(isA(Timestamp.class));
        verify(dataTemplate).setModifiedBy(eq("Jan 1, 2020 9:00am GMT+0100"));
        verify(dataTemplate).setPsmId(isA(Integer.class));
    }

    /**
     * Method under test:
     * {@link DataTemplateServiceImpl#uploadTemplate(DataTemplate)}
     */
    @Test
    void testUploadTemplate6() {
        // Arrange
        DataTemplate dataTemplate = mock(DataTemplate.class);
        when(dataTemplate.getFileContent()).thenReturn("");
        doNothing().when(dataTemplate).setCreatedBy(Mockito.<String>any());
        doNothing().when(dataTemplate).setCreatedDate(Mockito.<Timestamp>any());
        doNothing().when(dataTemplate).setDeleted(Mockito.<Boolean>any());
        doNothing().when(dataTemplate).setFileContent(Mockito.<String>any());
        doNothing().when(dataTemplate).setFileId(Mockito.<Integer>any());
        doNothing().when(dataTemplate).setFileName(Mockito.<String>any());
        doNothing().when(dataTemplate).setFileType(Mockito.<String>any());
        doNothing().when(dataTemplate).setFileTypeId(Mockito.<Integer>any());
        doNothing().when(dataTemplate).setLastModDate(Mockito.<Timestamp>any());
        doNothing().when(dataTemplate).setModifiedBy(Mockito.<String>any());
        doNothing().when(dataTemplate).setPsmId(Mockito.<Integer>any());
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

        // Act and Assert
        assertThrows(ECDException.class, () -> dataTemplateServiceImpl.uploadTemplate(dataTemplate));
        verify(dataTemplate, atLeast(1)).getFileContent();
        verify(dataTemplate).setCreatedBy(eq("Jan 1, 2020 8:00am GMT+0100"));
        verify(dataTemplate).setCreatedDate(isA(Timestamp.class));
        verify(dataTemplate).setDeleted(isA(Boolean.class));
        verify(dataTemplate).setFileContent(eq("Not all who wander are lost"));
        verify(dataTemplate).setFileId(isA(Integer.class));
        verify(dataTemplate).setFileName(eq("foo.txt"));
        verify(dataTemplate).setFileType(eq("File Type"));
        verify(dataTemplate).setFileTypeId(isA(Integer.class));
        verify(dataTemplate).setLastModDate(isA(Timestamp.class));
        verify(dataTemplate).setModifiedBy(eq("Jan 1, 2020 9:00am GMT+0100"));
        verify(dataTemplate).setPsmId(isA(Integer.class));
    }

    /**
     * Method under test:
     * {@link DataTemplateServiceImpl#downloadTemplate(Integer, String)}
     */
    @Test
    void testDownloadTemplate() {
        // Arrange
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
        when(dataTemplateRepo.findByPsmIdAndFileTypeAndDeleted(Mockito.<Integer>any(), Mockito.<String>any(),
                Mockito.<Boolean>any())).thenReturn(dataTemplate);

        // Act
        DataTemplate actualDownloadTemplateResult = dataTemplateServiceImpl.downloadTemplate(1, "File Type");

        // Assert
        verify(dataTemplateRepo).findByPsmIdAndFileTypeAndDeleted(isA(Integer.class), eq("File Type"), isA(Boolean.class));
        assertSame(dataTemplate, actualDownloadTemplateResult);
    }

    /**
     * Method under test:
     * {@link DataTemplateServiceImpl#downloadTemplate(Integer, String)}
     */
    @Test
    void testDownloadTemplate2() {
        // Arrange
        when(dataTemplateRepo.findByPsmIdAndFileTypeAndDeleted(Mockito.<Integer>any(), Mockito.<String>any(),
                Mockito.<Boolean>any())).thenThrow(new InvalidRequestException("An error occurred"));

        // Act and Assert
        assertThrows(ECDException.class, () -> dataTemplateServiceImpl.downloadTemplate(1, "File Type"));
        verify(dataTemplateRepo).findByPsmIdAndFileTypeAndDeleted(isA(Integer.class), eq("File Type"), isA(Boolean.class));
    }
}
