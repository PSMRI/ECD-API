package com.iemr.ecd.service.associate;

import static org.junit.jupiter.api.Assertions.assertThrows;

import com.iemr.ecd.dto.RequestBeneficiaryRegistrationDTO;
import com.iemr.ecd.repo.call_conf_allocation.ChildRecordRepo;
import com.iemr.ecd.repo.call_conf_allocation.MotherRecordRepo;
import com.iemr.ecd.repo.call_conf_allocation.OutboundCallsRepo;
import com.iemr.ecd.utils.advice.exception_handler.ECDException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {BeneficiaryRegistrationServiceImpl.class, RequestBeneficiaryRegistrationDTO.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class BeneficiaryRegistrationServiceImplDiffblueTest {
    @Autowired
    private BeneficiaryRegistrationServiceImpl beneficiaryRegistrationServiceImpl;

    @MockBean
    private ChildRecordRepo childRecordRepo;

    @MockBean
    private MotherRecordRepo motherRecordRepo;

    @MockBean
    private OutboundCallsRepo outboundCallsRepo;

    @Autowired
    private RequestBeneficiaryRegistrationDTO requestBeneficiaryRegistrationDTO;

    /**
     * Method under test:
     * {@link BeneficiaryRegistrationServiceImpl#beneficiaryRegistration(RequestBeneficiaryRegistrationDTO, String)}
     */
    @Test
    void testBeneficiaryRegistration() {
        // Arrange, Act and Assert
        assertThrows(ECDException.class,
                () -> beneficiaryRegistrationServiceImpl.beneficiaryRegistration(requestBeneficiaryRegistrationDTO, "JaneDoe"));
        assertThrows(ECDException.class, () -> beneficiaryRegistrationServiceImpl
                .beneficiaryRegistration(requestBeneficiaryRegistrationDTO, "U://U@[9U]:{UU?U#U"));
    }

    /**
     * Method under test:
     * {@link BeneficiaryRegistrationServiceImpl#updateBeneficiaryDetails(RequestBeneficiaryRegistrationDTO, String)}
     */
    @Test
    void testUpdateBeneficiaryDetails() {
        // Arrange, Act and Assert
        assertThrows(ECDException.class, () -> beneficiaryRegistrationServiceImpl
                .updateBeneficiaryDetails(requestBeneficiaryRegistrationDTO, "JaneDoe"));
    }
}
