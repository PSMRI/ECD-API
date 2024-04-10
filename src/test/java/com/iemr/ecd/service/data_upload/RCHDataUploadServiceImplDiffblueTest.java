package com.iemr.ecd.service.data_upload;

import static org.junit.jupiter.api.Assertions.assertThrows;

import com.iemr.ecd.dto.supervisor.RCHFileUploadDto;
import com.iemr.ecd.repo.call_conf_allocation.ChildRecordRepo;
import com.iemr.ecd.repo.call_conf_allocation.MotherRecordRepo;
import com.iemr.ecd.utils.advice.exception_handler.ECDException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {RCHDataUploadServiceImpl.class, RCHFileUploadDto.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class RCHDataUploadServiceImplDiffblueTest {
    @MockBean
    private ChildRecordRepo childRecordRepo;

    @MockBean
    private MotherRecordRepo motherRecordRepo;

    @Autowired
    private RCHDataUploadServiceImpl rCHDataUploadServiceImpl;

    @Autowired
    private RCHFileUploadDto rCHFileUploadDto;

    /**
     * Method under test:
     * {@link RCHDataUploadServiceImpl#uploadRCDData(RCHFileUploadDto)}
     */
    @Test
    void testUploadRCDData() {
        // Arrange, Act and Assert
        assertThrows(ECDException.class, () -> rCHDataUploadServiceImpl.uploadRCDData(rCHFileUploadDto));
    }
}
