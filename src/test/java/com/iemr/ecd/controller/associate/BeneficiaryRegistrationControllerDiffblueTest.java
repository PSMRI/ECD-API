package com.iemr.ecd.controller.associate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import java.sql.Timestamp;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.iemr.ecd.dto.RequestBeneficiaryDemographicsDTO;
import com.iemr.ecd.dto.RequestBeneficiaryRegistrationDTO;
import com.iemr.ecd.service.associate.BeneficiaryRegistrationServiceImpl;

@ContextConfiguration(classes = { BeneficiaryRegistrationController.class, RequestBeneficiaryRegistrationDTO.class })
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class BeneficiaryRegistrationControllerDiffblueTest {
	@Autowired
	private BeneficiaryRegistrationController beneficiaryRegistrationController;

	@MockBean
	private BeneficiaryRegistrationServiceImpl beneficiaryRegistrationServiceImpl;

	@Autowired
	private RequestBeneficiaryRegistrationDTO requestBeneficiaryRegistrationDTO;

	@Test
	void testBeneficiaryRegistration() throws Exception {

		String Authorization = "Authorization";

		RequestBeneficiaryRegistrationDTO requestBeneficiaryRegistrationDTO2 = new RequestBeneficiaryRegistrationDTO();
		requestBeneficiaryRegistrationDTO2.setAge(1);
		requestBeneficiaryRegistrationDTO2.setAlternatePhoneNo("6625550144");
		requestBeneficiaryRegistrationDTO2.setAnmName("Anm Name");
		requestBeneficiaryRegistrationDTO2.setAnmPh("Anm Ph");
		requestBeneficiaryRegistrationDTO2.setAshaName("Asha Name");
		requestBeneficiaryRegistrationDTO2.setAshaPh("Asha Ph");
		requestBeneficiaryRegistrationDTO2.setBenPhoneMaps(new ArrayList<>());
		requestBeneficiaryRegistrationDTO2.setBeneficiaryRegID(1L);
		requestBeneficiaryRegistrationDTO2.setBlockName("Block Name");
		requestBeneficiaryRegistrationDTO2.setChangeInAddress(true);
		requestBeneficiaryRegistrationDTO2.setChangeInContacts(true);
		requestBeneficiaryRegistrationDTO2.setChangeInFamilyDetails(true);
		requestBeneficiaryRegistrationDTO2.setChangeInOtherDetails(true);
		requestBeneficiaryRegistrationDTO2.setChangeInSelfDetails(true);
		requestBeneficiaryRegistrationDTO2.setChildId(1L);
		requestBeneficiaryRegistrationDTO2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
		requestBeneficiaryRegistrationDTO2.setDOB(mock(Timestamp.class));
		requestBeneficiaryRegistrationDTO2.setDateOfBirth("2020-03-01");
		requestBeneficiaryRegistrationDTO2.setEdd(mock(Timestamp.class));
		requestBeneficiaryRegistrationDTO2.setEmergencyRegistration(true);
		requestBeneficiaryRegistrationDTO2.setFirstName("Jane");
		requestBeneficiaryRegistrationDTO2.setGenderID(1);
		requestBeneficiaryRegistrationDTO2.setGenderName("Gender Name");
		requestBeneficiaryRegistrationDTO2.setI_bendemographics(new RequestBeneficiaryDemographicsDTO());
		requestBeneficiaryRegistrationDTO2.setLastName("Doe");
		requestBeneficiaryRegistrationDTO2.setLmp(mock(Timestamp.class));
		requestBeneficiaryRegistrationDTO2.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
		requestBeneficiaryRegistrationDTO2.setMotherId(1L);
		requestBeneficiaryRegistrationDTO2.setMotherName("Mother Name");
		requestBeneficiaryRegistrationDTO2.setName("Name");
		requestBeneficiaryRegistrationDTO2.setPhcName("Phc Name");
		requestBeneficiaryRegistrationDTO2.setPhoneNo("6625550144");
		requestBeneficiaryRegistrationDTO2.setPhoneNoOfWhom("6625550144");
		requestBeneficiaryRegistrationDTO2.setProviderServiceMapID(1);
		requestBeneficiaryRegistrationDTO2.setSpouseName("Spouse Name");
		requestBeneficiaryRegistrationDTO2.setVanID(1);

		requestBeneficiaryRegistrationDTO2.toString();

		ResponseEntity<Object> response = beneficiaryRegistrationController
				.beneficiaryRegistration(requestBeneficiaryRegistrationDTO2, Authorization);

		assertEquals(response, beneficiaryRegistrationController
				.beneficiaryRegistration(requestBeneficiaryRegistrationDTO2, Authorization));
	}

	@Test
	void testUpdateBeneficiaryDetails() throws Exception {

		String Authorization = "Authorization";

		RequestBeneficiaryRegistrationDTO requestBeneficiaryRegistrationDTO2 = new RequestBeneficiaryRegistrationDTO();
		requestBeneficiaryRegistrationDTO2.setAge(1);
		requestBeneficiaryRegistrationDTO2.setAlternatePhoneNo("6625550144");
		requestBeneficiaryRegistrationDTO2.setAnmName("Anm Name");
		requestBeneficiaryRegistrationDTO2.setAnmPh("Anm Ph");
		requestBeneficiaryRegistrationDTO2.setAshaName("Asha Name");
		requestBeneficiaryRegistrationDTO2.setAshaPh("Asha Ph");
		requestBeneficiaryRegistrationDTO2.setBenPhoneMaps(new ArrayList<>());
		requestBeneficiaryRegistrationDTO2.setBeneficiaryRegID(1L);
		requestBeneficiaryRegistrationDTO2.setBlockName("Block Name");
		requestBeneficiaryRegistrationDTO2.setChangeInAddress(true);
		requestBeneficiaryRegistrationDTO2.setChangeInContacts(true);
		requestBeneficiaryRegistrationDTO2.setChangeInFamilyDetails(true);
		requestBeneficiaryRegistrationDTO2.setChangeInOtherDetails(true);
		requestBeneficiaryRegistrationDTO2.setChangeInSelfDetails(true);
		requestBeneficiaryRegistrationDTO2.setChildId(1L);
		requestBeneficiaryRegistrationDTO2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
		requestBeneficiaryRegistrationDTO2.setDOB(mock(Timestamp.class));
		requestBeneficiaryRegistrationDTO2.setDateOfBirth("2020-03-01");
		requestBeneficiaryRegistrationDTO2.setEdd(mock(Timestamp.class));
		requestBeneficiaryRegistrationDTO2.setEmergencyRegistration(true);
		requestBeneficiaryRegistrationDTO2.setFirstName("Jane");
		requestBeneficiaryRegistrationDTO2.setGenderID(1);
		requestBeneficiaryRegistrationDTO2.setGenderName("Gender Name");
		requestBeneficiaryRegistrationDTO2.setI_bendemographics(new RequestBeneficiaryDemographicsDTO());
		requestBeneficiaryRegistrationDTO2.setLastName("Doe");
		requestBeneficiaryRegistrationDTO2.setLmp(mock(Timestamp.class));
		requestBeneficiaryRegistrationDTO2.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
		requestBeneficiaryRegistrationDTO2.setMotherId(1L);
		requestBeneficiaryRegistrationDTO2.setMotherName("Mother Name");
		requestBeneficiaryRegistrationDTO2.setName("Name");
		requestBeneficiaryRegistrationDTO2.setPhcName("Phc Name");
		requestBeneficiaryRegistrationDTO2.setPhoneNo("6625550144");
		requestBeneficiaryRegistrationDTO2.setPhoneNoOfWhom("6625550144");
		requestBeneficiaryRegistrationDTO2.setProviderServiceMapID(1);
		requestBeneficiaryRegistrationDTO2.setSpouseName("Spouse Name");
		requestBeneficiaryRegistrationDTO2.setVanID(1);

		requestBeneficiaryRegistrationDTO2.toString();

		ResponseEntity<Object> response = beneficiaryRegistrationController
				.updateBeneficiaryDetails(requestBeneficiaryRegistrationDTO2, Authorization);

		assertEquals(response, beneficiaryRegistrationController
				.updateBeneficiaryDetails(requestBeneficiaryRegistrationDTO2, Authorization));

	}
}
