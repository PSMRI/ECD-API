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
package com.iemr.ecd.controller.associate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.iemr.ecd.dto.RequestBeneficiaryRegistrationDTO;
import com.iemr.ecd.service.associate.BeneficiaryRegistrationServiceImpl;

@ExtendWith(MockitoExtension.class)
class BeneficiaryRegistrationControllerTest {

	@Mock
	private BeneficiaryRegistrationServiceImpl beneficiaryRegistrationServiceImpl;

	@InjectMocks
	private BeneficiaryRegistrationController controller;

	@Test
	void registrationDelegatesToService() {
		RequestBeneficiaryRegistrationDTO request = new RequestBeneficiaryRegistrationDTO();
		when(beneficiaryRegistrationServiceImpl.beneficiaryRegistration(request, "auth")).thenReturn("registered");

		ResponseEntity<Object> response = controller.beneficiaryRegistration(request, "auth");

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals("registered", response.getBody());
	}

	@Test
	void updateDelegatesToService() {
		RequestBeneficiaryRegistrationDTO request = new RequestBeneficiaryRegistrationDTO();
		when(beneficiaryRegistrationServiceImpl.updateBeneficiaryDetails(request, "auth")).thenReturn("updated");

		ResponseEntity<Object> response = controller.updateBeneficiaryDetails(request, "auth");

		assertEquals("updated", response.getBody());
	}
}
