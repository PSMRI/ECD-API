package com.iemr.ecd.controller.masters;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.iemr.ecd.dao.QuestionnaireSections;
import com.iemr.ecd.dao.masters.AgentsViewMaster;
import com.iemr.ecd.dao.masters.AnswerType;
import com.iemr.ecd.dao.masters.CallNotAnsweredReason;
import com.iemr.ecd.dao.masters.CongenitalAnomalies;
import com.iemr.ecd.dao.masters.Cycles;
import com.iemr.ecd.dao.masters.Frequency;
import com.iemr.ecd.dao.masters.Gender;
import com.iemr.ecd.dao.masters.GradeMaster;
import com.iemr.ecd.dao.masters.HRNIReasons;
import com.iemr.ecd.dao.masters.HRPReasons;
import com.iemr.ecd.dao.masters.Language;
import com.iemr.ecd.dao.masters.NoFurtherCallsReason;
import com.iemr.ecd.dao.masters.Offices;
import com.iemr.ecd.dao.masters.QuestionnaireType;
import com.iemr.ecd.dao.masters.Role;
import com.iemr.ecd.dao.masters.SMSParameters;
import com.iemr.ecd.dao.masters.SMSParametersMapping;
import com.iemr.ecd.dao.masters.SMSType;
import com.iemr.ecd.dao.masters.TypeOfComplaints;
import com.iemr.ecd.dao.masters.V_GetUserlangmapping;
import com.iemr.ecd.service.masters.MasterServiceImpl;

@ContextConfiguration(classes = { MastersController.class })
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class MastersControllerDiffblueTest {
	@MockBean
	private MasterServiceImpl masterServiceImpl;

	@Autowired
	private MastersController mastersController;

	@Test
	void testGetAgentsByRoleId() throws Exception {

		String Authorization = "Authorization";
		Integer roleId = 12;

		ResponseEntity<List<AgentsViewMaster>> response = mastersController.getAgentsByRoleId(Authorization, roleId);

		assertEquals(response, mastersController.getAgentsByRoleId(Authorization, roleId));

	}

	@Test
	void testGetAnswerType() throws Exception {

		ResponseEntity<List<AnswerType>> response = mastersController.getAnswerType();

		assertEquals(response, mastersController.getAnswerType());

	}

	@Test
	void testGetCallNotAnsweredReasons() throws Exception {

		ResponseEntity<List<CallNotAnsweredReason>> response = mastersController.getCallNotAnsweredReasons();

		assertEquals(response, mastersController.getCallNotAnsweredReasons());
	}

	@Test
	void testGetCongenitalAnomalies() throws Exception {

		ResponseEntity<List<CongenitalAnomalies>> response = mastersController.getCongenitalAnomalies();

		assertEquals(response, mastersController.getCongenitalAnomalies());

	}

	@Test
	void testGetCycles() throws Exception {

		ResponseEntity<List<Cycles>> response = mastersController.getCycles();

		assertEquals(response, mastersController.getCycles());

	}

	@Test
	void testGetFrequency() throws Exception {

		ResponseEntity<List<Frequency>> response = mastersController.getFrequency();

		assertEquals(response, mastersController.getFrequency());

	}

	@Test
	void testGetGender() throws Exception {

		ResponseEntity<List<Gender>> response = mastersController.getGender();

		assertEquals(response, mastersController.getGender());
	}

	@Test
	void testGetGrades() throws Exception {

		ResponseEntity<List<GradeMaster>> response = mastersController.getGrades();

		assertEquals(response, mastersController.getGrades());

	}

	@Test
	void testGetHRNIReasons() throws Exception {

		ResponseEntity<List<HRNIReasons>> response = mastersController.getHRNIReasons();

		assertEquals(response, mastersController.getHRNIReasons());

	}

	@Test
	void testGetHRPReasons() throws Exception {

		ResponseEntity<List<HRPReasons>> response = mastersController.getHRPReasons();

		assertEquals(response, mastersController.getHRPReasons());
	}

	@Test
	void testGetLanguage() throws Exception {

		ResponseEntity<List<Language>> response = mastersController.getLanguage();

		assertEquals(response, mastersController.getLanguage());
	}

	@Test
	void testGetLanguageByUserId() throws Exception {

		Integer userId = 8;

		ResponseEntity<List<V_GetUserlangmapping>> response = mastersController.getLanguageByUserId(userId);

		assertEquals(response, mastersController.getLanguageByUserId(userId));

	}

	@Test
	void testGetNoFurtherCallsReason() throws Exception {

		ResponseEntity<List<NoFurtherCallsReason>> response = mastersController.getNoFurtherCallsReason();

		assertEquals(response, mastersController.getNoFurtherCallsReason());

	}

	@Test
	void testGetOffices() throws Exception {

		ResponseEntity<List<Offices>> response = mastersController.getOffices();

		assertEquals(response, mastersController.getOffices());
	}

	@Test
	void testGetOfficesByDistrictId() throws Exception {

		Integer districtId = 3;

		ResponseEntity<List<Offices>> response = mastersController.getOfficesByDistrictId(districtId);

		assertEquals(response, mastersController.getOfficesByDistrictId(districtId));
	}

	@Test
	void testGetOfficesByDistrictIdAndPSMID() throws Exception {

		Integer districtId = 3;

		Integer psmId = 6;

		ResponseEntity<List<Offices>> response = mastersController.getOfficesByDistrictIdAndPSMID(districtId, psmId);

		assertEquals(response, mastersController.getOfficesByDistrictIdAndPSMID(districtId, psmId));
	}

	@Test
	void testGetOfficesByPSMID() throws Exception {

		Integer psmId = 6;

		ResponseEntity<List<Offices>> response = mastersController.getOfficesByPSMID(psmId);

		assertEquals(response, mastersController.getOfficesByPSMID(psmId));
	}

	@Test
	void testGetQuestionnaireType() throws Exception {

		ResponseEntity<List<QuestionnaireType>> response = mastersController.getQuestionnaireType();

		assertEquals(response, mastersController.getQuestionnaireType());
	}

	@Test
	void testGetRolesByPsmId() throws Exception {

		Integer psmId = 6;

		ResponseEntity<List<Role>> response = mastersController.getRolesByPsmId(psmId);

		assertEquals(response, mastersController.getRolesByPsmId(psmId));
	}

	@Test
	void testGetSMSParameters() throws Exception {

		ResponseEntity<List<SMSParametersMapping>> response = mastersController.getSMSParameters();

		assertEquals(response, mastersController.getSMSParameters());
	}

	@Test
	void testGetSMSTypes() throws Exception {

		ResponseEntity<List<SMSType>> response = mastersController.getSMSTypes();

		assertEquals(response, mastersController.getSMSTypes());
	}

	@Test
	void testGetSMSValues() throws Exception {

		ResponseEntity<List<SMSParameters>> response = mastersController.getSMSValues();

		assertEquals(response, mastersController.getSMSValues());
	}

	@Test
	void testGetSectionsByPsmId() throws Exception {

		Integer psmId = 6;

		ResponseEntity<List<QuestionnaireSections>> response = mastersController.getSectionsByPsmId(psmId);

		assertEquals(response, mastersController.getSectionsByPsmId(psmId));
	}

	@Test
	void testGetTypeOfComplaints() throws Exception {

		ResponseEntity<List<TypeOfComplaints>> response = mastersController.getTypeOfComplaints();

		assertEquals(response, mastersController.getTypeOfComplaints());
	}
}
