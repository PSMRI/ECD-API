package com.iemr.ecd.service.masters;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
import com.iemr.ecd.repo.call_conf_allocation.QuestionnaireSectionRepo;
import com.iemr.ecd.repository.masters.AgentsViewMasterRepo;
import com.iemr.ecd.repository.masters.AnswerTypeRepo;
import com.iemr.ecd.repository.masters.CallNotAnsweredRepo;
import com.iemr.ecd.repository.masters.CongenitalAnomaliesRepo;
import com.iemr.ecd.repository.masters.CycleRepo;
import com.iemr.ecd.repository.masters.FrequencyRepo;
import com.iemr.ecd.repository.masters.GenderRepo;
import com.iemr.ecd.repository.masters.GradeMasterRepo;
import com.iemr.ecd.repository.masters.HRNIReasonsRepo;
import com.iemr.ecd.repository.masters.HRPReasonsRepo;
import com.iemr.ecd.repository.masters.LanguageRepo;
import com.iemr.ecd.repository.masters.NoFurtherCallsReasonRepo;
import com.iemr.ecd.repository.masters.OfficesRepo;
import com.iemr.ecd.repository.masters.ParametersRepo;
import com.iemr.ecd.repository.masters.QuestionnaireTypeRepo;
import com.iemr.ecd.repository.masters.RoleRepo;
import com.iemr.ecd.repository.masters.SMSTypeRepo;
import com.iemr.ecd.repository.masters.TypeOfComplaintsRepo;
import com.iemr.ecd.repository.masters.UserServiceRoleMappingRepo;
import com.iemr.ecd.repository.masters.V_GetUserlangmappingRepo;
import com.iemr.ecd.repository.masters.ValueRepo;
import com.iemr.ecd.utils.advice.exception_handler.ECDException;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {MasterServiceImpl.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class MasterServiceImplDiffblueTest {
    @MockBean
    private AgentsViewMasterRepo agentsViewMasterRepo;

    @MockBean
    private AnswerTypeRepo answerTypeRepo;

    @MockBean
    private CallNotAnsweredRepo callNotAnsweredRepo;

    @MockBean
    private CongenitalAnomaliesRepo congenitalAnomaliesRepo;

    @MockBean
    private CycleRepo cycleRepo;

    @MockBean
    private FrequencyRepo frequencyRepo;

    @MockBean
    private GenderRepo genderRepo;

    @MockBean
    private GradeMasterRepo gradeMasterRepo;

    @MockBean
    private HRNIReasonsRepo hRNIReasonsRepo;

    @MockBean
    private HRPReasonsRepo hRPReasonsRepo;

    @MockBean
    private LanguageRepo languageRepo;

    @Autowired
    private MasterServiceImpl masterServiceImpl;

    @MockBean
    private NoFurtherCallsReasonRepo noFurtherCallsReasonRepo;

    @MockBean
    private OfficesRepo officesRepo;

    @MockBean
    private ParametersRepo parametersRepo;

    @MockBean
    private QuestionnaireSectionRepo questionnaireSectionRepo;

    @MockBean
    private QuestionnaireTypeRepo questionnaireTypeRepo;

    @MockBean
    private RoleRepo roleRepo;

    @MockBean
    private SMSTypeRepo sMSTypeRepo;

    @MockBean
    private TypeOfComplaintsRepo typeOfComplaintsRepo;

    @MockBean
    private UserServiceRoleMappingRepo userServiceRoleMappingRepo;

    @MockBean
    private V_GetUserlangmappingRepo v_GetUserlangmappingRepo;

    @MockBean
    private ValueRepo valueRepo;

    /**
     * Method under test: {@link MasterServiceImpl#getAllHRPReasons()}
     */
    @Test
    void testGetAllHRPReasons() throws ECDException {
        // Arrange
        ArrayList<HRPReasons> hrpReasonsList = new ArrayList<>();
        when(hRPReasonsRepo.findByDeleted(Mockito.<Boolean>any())).thenReturn(hrpReasonsList);

        // Act
        List<HRPReasons> actualAllHRPReasons = masterServiceImpl.getAllHRPReasons();

        // Assert
        verify(hRPReasonsRepo).findByDeleted(isA(Boolean.class));
        assertTrue(actualAllHRPReasons.isEmpty());
        assertSame(hrpReasonsList, actualAllHRPReasons);
    }

    /**
     * Method under test: {@link MasterServiceImpl#getAllHRPReasons()}
     */
    @Test
    void testGetAllHRPReasons2() throws ECDException {
        // Arrange
        when(hRPReasonsRepo.findByDeleted(Mockito.<Boolean>any())).thenThrow(new ECDException("Exception Msg"));

        // Act and Assert
        assertThrows(ECDException.class, () -> masterServiceImpl.getAllHRPReasons());
        verify(hRPReasonsRepo).findByDeleted(isA(Boolean.class));
    }

    /**
     * Method under test: {@link MasterServiceImpl#getAllHRNIReasons()}
     */
    @Test
    void testGetAllHRNIReasons() throws ECDException {
        // Arrange
        ArrayList<HRNIReasons> hrniReasonsList = new ArrayList<>();
        when(hRNIReasonsRepo.findByDeleted(Mockito.<Boolean>any())).thenReturn(hrniReasonsList);

        // Act
        List<HRNIReasons> actualAllHRNIReasons = masterServiceImpl.getAllHRNIReasons();

        // Assert
        verify(hRNIReasonsRepo).findByDeleted(isA(Boolean.class));
        assertTrue(actualAllHRNIReasons.isEmpty());
        assertSame(hrniReasonsList, actualAllHRNIReasons);
    }

    /**
     * Method under test: {@link MasterServiceImpl#getAllHRNIReasons()}
     */
    @Test
    void testGetAllHRNIReasons2() throws ECDException {
        // Arrange
        when(hRNIReasonsRepo.findByDeleted(Mockito.<Boolean>any())).thenThrow(new ECDException("Exception Msg"));

        // Act and Assert
        assertThrows(ECDException.class, () -> masterServiceImpl.getAllHRNIReasons());
        verify(hRNIReasonsRepo).findByDeleted(isA(Boolean.class));
    }

    /**
     * Method under test: {@link MasterServiceImpl#getAllCycles()}
     */
    @Test
    void testGetAllCycles() throws ECDException {
        // Arrange
        ArrayList<Cycles> cyclesList = new ArrayList<>();
        when(cycleRepo.findByDeleted(Mockito.<Boolean>any())).thenReturn(cyclesList);

        // Act
        List<Cycles> actualAllCycles = masterServiceImpl.getAllCycles();

        // Assert
        verify(cycleRepo).findByDeleted(isA(Boolean.class));
        assertTrue(actualAllCycles.isEmpty());
        assertSame(cyclesList, actualAllCycles);
    }

    /**
     * Method under test: {@link MasterServiceImpl#getAllCycles()}
     */
    @Test
    void testGetAllCycles2() throws ECDException {
        // Arrange
        when(cycleRepo.findByDeleted(Mockito.<Boolean>any())).thenThrow(new ECDException("Exception Msg"));

        // Act and Assert
        assertThrows(ECDException.class, () -> masterServiceImpl.getAllCycles());
        verify(cycleRepo).findByDeleted(isA(Boolean.class));
    }

    /**
     * Method under test: {@link MasterServiceImpl#findOffices()}
     */
    @Test
    void testFindOffices() throws ECDException {
        // Arrange
        ArrayList<Offices> officesList = new ArrayList<>();
        when(officesRepo.findByDeleted(Mockito.<Boolean>any())).thenReturn(officesList);

        // Act
        List<Offices> actualFindOfficesResult = masterServiceImpl.findOffices();

        // Assert
        verify(officesRepo).findByDeleted(isA(Boolean.class));
        assertTrue(actualFindOfficesResult.isEmpty());
        assertSame(officesList, actualFindOfficesResult);
    }

    /**
     * Method under test: {@link MasterServiceImpl#findOffices()}
     */
    @Test
    void testFindOffices2() throws ECDException {
        // Arrange
        when(officesRepo.findByDeleted(Mockito.<Boolean>any())).thenThrow(new ECDException("Exception Msg"));

        // Act and Assert
        assertThrows(ECDException.class, () -> masterServiceImpl.findOffices());
        verify(officesRepo).findByDeleted(isA(Boolean.class));
    }

    /**
     * Method under test: {@link MasterServiceImpl#findOfficesByPsmId(Integer)}
     */
    @Test
    void testFindOfficesByPsmId() throws ECDException {
        // Arrange
        ArrayList<Offices> officesList = new ArrayList<>();
        when(officesRepo.findByPsmId(Mockito.<Integer>any())).thenReturn(officesList);

        // Act
        List<Offices> actualFindOfficesByPsmIdResult = masterServiceImpl.findOfficesByPsmId(1);

        // Assert
        verify(officesRepo).findByPsmId(isA(Integer.class));
        assertTrue(actualFindOfficesByPsmIdResult.isEmpty());
        assertSame(officesList, actualFindOfficesByPsmIdResult);
    }

    /**
     * Method under test: {@link MasterServiceImpl#findOfficesByPsmId(Integer)}
     */
    @Test
    void testFindOfficesByPsmId2() throws ECDException {
        // Arrange
        when(officesRepo.findByPsmId(Mockito.<Integer>any())).thenThrow(new ECDException("Exception Msg"));

        // Act and Assert
        assertThrows(ECDException.class, () -> masterServiceImpl.findOfficesByPsmId(1));
        verify(officesRepo).findByPsmId(isA(Integer.class));
    }

    /**
     * Method under test: {@link MasterServiceImpl#findOfficesByDistrictId(Integer)}
     */
    @Test
    void testFindOfficesByDistrictId() throws ECDException {
        // Arrange
        ArrayList<Offices> officesList = new ArrayList<>();
        when(officesRepo.findByDistrictId(Mockito.<Integer>any())).thenReturn(officesList);

        // Act
        List<Offices> actualFindOfficesByDistrictIdResult = masterServiceImpl.findOfficesByDistrictId(1);

        // Assert
        verify(officesRepo).findByDistrictId(isA(Integer.class));
        assertTrue(actualFindOfficesByDistrictIdResult.isEmpty());
        assertSame(officesList, actualFindOfficesByDistrictIdResult);
    }

    /**
     * Method under test: {@link MasterServiceImpl#findOfficesByDistrictId(Integer)}
     */
    @Test
    void testFindOfficesByDistrictId2() throws ECDException {
        // Arrange
        when(officesRepo.findByDistrictId(Mockito.<Integer>any())).thenThrow(new ECDException("Exception Msg"));

        // Act and Assert
        assertThrows(ECDException.class, () -> masterServiceImpl.findOfficesByDistrictId(1));
        verify(officesRepo).findByDistrictId(isA(Integer.class));
    }

    /**
     * Method under test:
     * {@link MasterServiceImpl#findOfficesByDistrictIdAndPsmId(Integer, Integer)}
     */
    @Test
    void testFindOfficesByDistrictIdAndPsmId() throws ECDException {
        // Arrange
        ArrayList<Offices> officesList = new ArrayList<>();
        when(officesRepo.findByDistrictIdAndPsmId(Mockito.<Integer>any(), Mockito.<Integer>any())).thenReturn(officesList);

        // Act
        List<Offices> actualFindOfficesByDistrictIdAndPsmIdResult = masterServiceImpl.findOfficesByDistrictIdAndPsmId(1, 1);

        // Assert
        verify(officesRepo).findByDistrictIdAndPsmId(isA(Integer.class), isA(Integer.class));
        assertTrue(actualFindOfficesByDistrictIdAndPsmIdResult.isEmpty());
        assertSame(officesList, actualFindOfficesByDistrictIdAndPsmIdResult);
    }

    /**
     * Method under test:
     * {@link MasterServiceImpl#findOfficesByDistrictIdAndPsmId(Integer, Integer)}
     */
    @Test
    void testFindOfficesByDistrictIdAndPsmId2() throws ECDException {
        // Arrange
        when(officesRepo.findByDistrictIdAndPsmId(Mockito.<Integer>any(), Mockito.<Integer>any()))
                .thenThrow(new ECDException("Exception Msg"));

        // Act and Assert
        assertThrows(ECDException.class, () -> masterServiceImpl.findOfficesByDistrictIdAndPsmId(1, 1));
        verify(officesRepo).findByDistrictIdAndPsmId(isA(Integer.class), isA(Integer.class));
    }

    /**
     * Method under test: {@link MasterServiceImpl#getAgentByRoleId(Integer)}
     */
    @Test
    void testGetAgentByRoleId() throws ECDException {
        // Arrange
        ArrayList<AgentsViewMaster> agentsViewMasterList = new ArrayList<>();
        when(agentsViewMasterRepo.findByRoleId(Mockito.<Integer>any())).thenReturn(agentsViewMasterList);

        // Act
        List<AgentsViewMaster> actualAgentByRoleId = masterServiceImpl.getAgentByRoleId(1);

        // Assert
        verify(agentsViewMasterRepo).findByRoleId(isA(Integer.class));
        assertTrue(actualAgentByRoleId.isEmpty());
        assertSame(agentsViewMasterList, actualAgentByRoleId);
    }

    /**
     * Method under test: {@link MasterServiceImpl#getAgentByRoleId(Integer)}
     */
    @Test
    void testGetAgentByRoleId2() throws ECDException {
        // Arrange
        when(agentsViewMasterRepo.findByRoleId(Mockito.<Integer>any())).thenThrow(new ECDException("Exception Msg"));

        // Act and Assert
        assertThrows(ECDException.class, () -> masterServiceImpl.getAgentByRoleId(1));
        verify(agentsViewMasterRepo).findByRoleId(isA(Integer.class));
    }

    /**
     * Method under test: {@link MasterServiceImpl#getAnswerType()}
     */
    @Test
    void testGetAnswerType() throws ECDException {
        // Arrange
        ArrayList<AnswerType> answerTypeList = new ArrayList<>();
        when(answerTypeRepo.findByDeleted(Mockito.<Boolean>any())).thenReturn(answerTypeList);

        // Act
        List<AnswerType> actualAnswerType = masterServiceImpl.getAnswerType();

        // Assert
        verify(answerTypeRepo).findByDeleted(isA(Boolean.class));
        assertTrue(actualAnswerType.isEmpty());
        assertSame(answerTypeList, actualAnswerType);
    }

    /**
     * Method under test: {@link MasterServiceImpl#getAnswerType()}
     */
    @Test
    void testGetAnswerType2() throws ECDException {
        // Arrange
        when(answerTypeRepo.findByDeleted(Mockito.<Boolean>any())).thenThrow(new ECDException("Exception Msg"));

        // Act and Assert
        assertThrows(ECDException.class, () -> masterServiceImpl.getAnswerType());
        verify(answerTypeRepo).findByDeleted(isA(Boolean.class));
    }

    /**
     * Method under test: {@link MasterServiceImpl#getCallNotAnsweredReasons()}
     */
    @Test
    void testGetCallNotAnsweredReasons() {
        // Arrange
        ArrayList<CallNotAnsweredReason> callNotAnsweredReasonList = new ArrayList<>();
        when(callNotAnsweredRepo.findByDeleted(Mockito.<Boolean>any())).thenReturn(callNotAnsweredReasonList);

        // Act
        List<CallNotAnsweredReason> actualCallNotAnsweredReasons = masterServiceImpl.getCallNotAnsweredReasons();

        // Assert
        verify(callNotAnsweredRepo).findByDeleted(isA(Boolean.class));
        assertTrue(actualCallNotAnsweredReasons.isEmpty());
        assertSame(callNotAnsweredReasonList, actualCallNotAnsweredReasons);
    }

    /**
     * Method under test: {@link MasterServiceImpl#getCallNotAnsweredReasons()}
     */
    @Test
    void testGetCallNotAnsweredReasons2() {
        // Arrange
        when(callNotAnsweredRepo.findByDeleted(Mockito.<Boolean>any())).thenThrow(new ECDException("Exception Msg"));

        // Act and Assert
        assertThrows(ECDException.class, () -> masterServiceImpl.getCallNotAnsweredReasons());
        verify(callNotAnsweredRepo).findByDeleted(isA(Boolean.class));
    }

    /**
     * Method under test: {@link MasterServiceImpl#getCongenitalAnomalies()}
     */
    @Test
    void testGetCongenitalAnomalies() {
        // Arrange
        ArrayList<CongenitalAnomalies> congenitalAnomaliesList = new ArrayList<>();
        when(congenitalAnomaliesRepo.findByDeleted(Mockito.<Boolean>any())).thenReturn(congenitalAnomaliesList);

        // Act
        List<CongenitalAnomalies> actualCongenitalAnomalies = masterServiceImpl.getCongenitalAnomalies();

        // Assert
        verify(congenitalAnomaliesRepo).findByDeleted(isA(Boolean.class));
        assertTrue(actualCongenitalAnomalies.isEmpty());
        assertSame(congenitalAnomaliesList, actualCongenitalAnomalies);
    }

    /**
     * Method under test: {@link MasterServiceImpl#getCongenitalAnomalies()}
     */
    @Test
    void testGetCongenitalAnomalies2() {
        // Arrange
        when(congenitalAnomaliesRepo.findByDeleted(Mockito.<Boolean>any())).thenThrow(new ECDException("Exception Msg"));

        // Act and Assert
        assertThrows(ECDException.class, () -> masterServiceImpl.getCongenitalAnomalies());
        verify(congenitalAnomaliesRepo).findByDeleted(isA(Boolean.class));
    }

    /**
     * Method under test: {@link MasterServiceImpl#getGrades()}
     */
    @Test
    void testGetGrades() {
        // Arrange
        ArrayList<GradeMaster> gradeMasterList = new ArrayList<>();
        when(gradeMasterRepo.findByDeleted(Mockito.<Boolean>any())).thenReturn(gradeMasterList);

        // Act
        List<GradeMaster> actualGrades = masterServiceImpl.getGrades();

        // Assert
        verify(gradeMasterRepo).findByDeleted(isA(Boolean.class));
        assertTrue(actualGrades.isEmpty());
        assertSame(gradeMasterList, actualGrades);
    }

    /**
     * Method under test: {@link MasterServiceImpl#getGrades()}
     */
    @Test
    void testGetGrades2() {
        // Arrange
        when(gradeMasterRepo.findByDeleted(Mockito.<Boolean>any())).thenThrow(new ECDException("Exception Msg"));

        // Act and Assert
        assertThrows(ECDException.class, () -> masterServiceImpl.getGrades());
        verify(gradeMasterRepo).findByDeleted(isA(Boolean.class));
    }

    /**
     * Method under test: {@link MasterServiceImpl#getLanguage()}
     */
    @Test
    void testGetLanguage() {
        // Arrange
        ArrayList<Language> languageList = new ArrayList<>();
        when(languageRepo.findByDeleted(Mockito.<Boolean>any())).thenReturn(languageList);

        // Act
        List<Language> actualLanguage = masterServiceImpl.getLanguage();

        // Assert
        verify(languageRepo).findByDeleted(isA(Boolean.class));
        assertTrue(actualLanguage.isEmpty());
        assertSame(languageList, actualLanguage);
    }

    /**
     * Method under test: {@link MasterServiceImpl#getLanguage()}
     */
    @Test
    void testGetLanguage2() {
        // Arrange
        when(languageRepo.findByDeleted(Mockito.<Boolean>any())).thenThrow(new ECDException("Exception Msg"));

        // Act and Assert
        assertThrows(ECDException.class, () -> masterServiceImpl.getLanguage());
        verify(languageRepo).findByDeleted(isA(Boolean.class));
    }

    /**
     * Method under test: {@link MasterServiceImpl#getNoFurtherCallsReason()}
     */
    @Test
    void testGetNoFurtherCallsReason() {
        // Arrange
        ArrayList<NoFurtherCallsReason> noFurtherCallsReasonList = new ArrayList<>();
        when(noFurtherCallsReasonRepo.findByDeleted(Mockito.<Boolean>any())).thenReturn(noFurtherCallsReasonList);

        // Act
        List<NoFurtherCallsReason> actualNoFurtherCallsReason = masterServiceImpl.getNoFurtherCallsReason();

        // Assert
        verify(noFurtherCallsReasonRepo).findByDeleted(isA(Boolean.class));
        assertTrue(actualNoFurtherCallsReason.isEmpty());
        assertSame(noFurtherCallsReasonList, actualNoFurtherCallsReason);
    }

    /**
     * Method under test: {@link MasterServiceImpl#getNoFurtherCallsReason()}
     */
    @Test
    void testGetNoFurtherCallsReason2() {
        // Arrange
        when(noFurtherCallsReasonRepo.findByDeleted(Mockito.<Boolean>any())).thenThrow(new ECDException("Exception Msg"));

        // Act and Assert
        assertThrows(ECDException.class, () -> masterServiceImpl.getNoFurtherCallsReason());
        verify(noFurtherCallsReasonRepo).findByDeleted(isA(Boolean.class));
    }

    /**
     * Method under test: {@link MasterServiceImpl#getSMSParameters()}
     */
    @Test
    void testGetSMSParameters() {
        // Arrange
        ArrayList<SMSParametersMapping> smsParametersMappingList = new ArrayList<>();
        when(parametersRepo.findByDeleted(Mockito.<Boolean>any())).thenReturn(smsParametersMappingList);

        // Act
        List<SMSParametersMapping> actualSMSParameters = masterServiceImpl.getSMSParameters();

        // Assert
        verify(parametersRepo).findByDeleted(isA(Boolean.class));
        assertTrue(actualSMSParameters.isEmpty());
        assertSame(smsParametersMappingList, actualSMSParameters);
    }

    /**
     * Method under test: {@link MasterServiceImpl#getSMSParameters()}
     */
    @Test
    void testGetSMSParameters2() {
        // Arrange
        when(parametersRepo.findByDeleted(Mockito.<Boolean>any())).thenThrow(new ECDException("Exception Msg"));

        // Act and Assert
        assertThrows(ECDException.class, () -> masterServiceImpl.getSMSParameters());
        verify(parametersRepo).findByDeleted(isA(Boolean.class));
    }

    /**
     * Method under test: {@link MasterServiceImpl#getQuestionnaireType()}
     */
    @Test
    void testGetQuestionnaireType() {
        // Arrange
        ArrayList<QuestionnaireType> questionnaireTypeList = new ArrayList<>();
        when(questionnaireTypeRepo.findByDeleted(Mockito.<Boolean>any())).thenReturn(questionnaireTypeList);

        // Act
        List<QuestionnaireType> actualQuestionnaireType = masterServiceImpl.getQuestionnaireType();

        // Assert
        verify(questionnaireTypeRepo).findByDeleted(isA(Boolean.class));
        assertTrue(actualQuestionnaireType.isEmpty());
        assertSame(questionnaireTypeList, actualQuestionnaireType);
    }

    /**
     * Method under test: {@link MasterServiceImpl#getQuestionnaireType()}
     */
    @Test
    void testGetQuestionnaireType2() {
        // Arrange
        when(questionnaireTypeRepo.findByDeleted(Mockito.<Boolean>any())).thenThrow(new ECDException("Exception Msg"));

        // Act and Assert
        assertThrows(ECDException.class, () -> masterServiceImpl.getQuestionnaireType());
        verify(questionnaireTypeRepo).findByDeleted(isA(Boolean.class));
    }

    /**
     * Method under test: {@link MasterServiceImpl#getRolesByPsmId(Integer)}
     */
    @Test
    void testGetRolesByPsmId() {
        // Arrange
        ArrayList<Role> roleList = new ArrayList<>();
        when(roleRepo.findByPsmIdAndDeleted(Mockito.<Integer>any(), Mockito.<Boolean>any())).thenReturn(roleList);

        // Act
        List<Role> actualRolesByPsmId = masterServiceImpl.getRolesByPsmId(1);

        // Assert
        verify(roleRepo).findByPsmIdAndDeleted(isA(Integer.class), isA(Boolean.class));
        assertTrue(actualRolesByPsmId.isEmpty());
        assertSame(roleList, actualRolesByPsmId);
    }

    /**
     * Method under test: {@link MasterServiceImpl#getRolesByPsmId(Integer)}
     */
    @Test
    void testGetRolesByPsmId2() {
        // Arrange
        when(roleRepo.findByPsmIdAndDeleted(Mockito.<Integer>any(), Mockito.<Boolean>any()))
                .thenThrow(new ECDException("Exception Msg"));

        // Act and Assert
        assertThrows(ECDException.class, () -> masterServiceImpl.getRolesByPsmId(1));
        verify(roleRepo).findByPsmIdAndDeleted(isA(Integer.class), isA(Boolean.class));
    }

    /**
     * Method under test: {@link MasterServiceImpl#getSMSTypes()}
     */
    @Test
    void testGetSMSTypes() {
        // Arrange
        ArrayList<SMSType> smsTypeList = new ArrayList<>();
        when(sMSTypeRepo.findByDeleted(Mockito.<Boolean>any())).thenReturn(smsTypeList);

        // Act
        List<SMSType> actualSMSTypes = masterServiceImpl.getSMSTypes();

        // Assert
        verify(sMSTypeRepo).findByDeleted(isA(Boolean.class));
        assertTrue(actualSMSTypes.isEmpty());
        assertSame(smsTypeList, actualSMSTypes);
    }

    /**
     * Method under test: {@link MasterServiceImpl#getSMSTypes()}
     */
    @Test
    void testGetSMSTypes2() {
        // Arrange
        when(sMSTypeRepo.findByDeleted(Mockito.<Boolean>any())).thenThrow(new ECDException("Exception Msg"));

        // Act and Assert
        assertThrows(ECDException.class, () -> masterServiceImpl.getSMSTypes());
        verify(sMSTypeRepo).findByDeleted(isA(Boolean.class));
    }

    /**
     * Method under test: {@link MasterServiceImpl#getTypeOfComplaints()}
     */
    @Test
    void testGetTypeOfComplaints() {
        // Arrange
        ArrayList<TypeOfComplaints> typeOfComplaintsList = new ArrayList<>();
        when(typeOfComplaintsRepo.findByDeleted(Mockito.<Boolean>any())).thenReturn(typeOfComplaintsList);

        // Act
        List<TypeOfComplaints> actualTypeOfComplaints = masterServiceImpl.getTypeOfComplaints();

        // Assert
        verify(typeOfComplaintsRepo).findByDeleted(isA(Boolean.class));
        assertTrue(actualTypeOfComplaints.isEmpty());
        assertSame(typeOfComplaintsList, actualTypeOfComplaints);
    }

    /**
     * Method under test: {@link MasterServiceImpl#getTypeOfComplaints()}
     */
    @Test
    void testGetTypeOfComplaints2() {
        // Arrange
        when(typeOfComplaintsRepo.findByDeleted(Mockito.<Boolean>any())).thenThrow(new ECDException("Exception Msg"));

        // Act and Assert
        assertThrows(ECDException.class, () -> masterServiceImpl.getTypeOfComplaints());
        verify(typeOfComplaintsRepo).findByDeleted(isA(Boolean.class));
    }

    /**
     * Method under test: {@link MasterServiceImpl#getSMSValues()}
     */
    @Test
    void testGetSMSValues() {
        // Arrange
        ArrayList<SMSParameters> smsParametersList = new ArrayList<>();
        when(valueRepo.findByDeleted(Mockito.<Boolean>any())).thenReturn(smsParametersList);

        // Act
        List<SMSParameters> actualSMSValues = masterServiceImpl.getSMSValues();

        // Assert
        verify(valueRepo).findByDeleted(isA(Boolean.class));
        assertTrue(actualSMSValues.isEmpty());
        assertSame(smsParametersList, actualSMSValues);
    }

    /**
     * Method under test: {@link MasterServiceImpl#getSMSValues()}
     */
    @Test
    void testGetSMSValues2() {
        // Arrange
        when(valueRepo.findByDeleted(Mockito.<Boolean>any())).thenThrow(new ECDException("Exception Msg"));

        // Act and Assert
        assertThrows(ECDException.class, () -> masterServiceImpl.getSMSValues());
        verify(valueRepo).findByDeleted(isA(Boolean.class));
    }

    /**
     * Method under test: {@link MasterServiceImpl#getSectionsByPsmId(Integer)}
     */
    @Test
    void testGetSectionsByPsmId() {
        // Arrange
        ArrayList<QuestionnaireSections> questionnaireSectionsList = new ArrayList<>();
        when(questionnaireSectionRepo.findByPsmIdAndDeleted(Mockito.<Integer>any(), Mockito.<Boolean>any()))
                .thenReturn(questionnaireSectionsList);

        // Act
        List<QuestionnaireSections> actualSectionsByPsmId = masterServiceImpl.getSectionsByPsmId(1);

        // Assert
        verify(questionnaireSectionRepo).findByPsmIdAndDeleted(isA(Integer.class), isA(Boolean.class));
        assertTrue(actualSectionsByPsmId.isEmpty());
        assertSame(questionnaireSectionsList, actualSectionsByPsmId);
    }

    /**
     * Method under test: {@link MasterServiceImpl#getSectionsByPsmId(Integer)}
     */
    @Test
    void testGetSectionsByPsmId2() {
        // Arrange
        when(questionnaireSectionRepo.findByPsmIdAndDeleted(Mockito.<Integer>any(), Mockito.<Boolean>any()))
                .thenThrow(new ECDException("Exception Msg"));

        // Act and Assert
        assertThrows(ECDException.class, () -> masterServiceImpl.getSectionsByPsmId(1));
        verify(questionnaireSectionRepo).findByPsmIdAndDeleted(isA(Integer.class), isA(Boolean.class));
    }

    /**
     * Method under test: {@link MasterServiceImpl#getFrequency()}
     */
    @Test
    void testGetFrequency() {
        // Arrange
        ArrayList<Frequency> frequencyList = new ArrayList<>();
        when(frequencyRepo.findByDeleted(Mockito.<Boolean>any())).thenReturn(frequencyList);

        // Act
        List<Frequency> actualFrequency = masterServiceImpl.getFrequency();

        // Assert
        verify(frequencyRepo).findByDeleted(isA(Boolean.class));
        assertTrue(actualFrequency.isEmpty());
        assertSame(frequencyList, actualFrequency);
    }

    /**
     * Method under test: {@link MasterServiceImpl#getFrequency()}
     */
    @Test
    void testGetFrequency2() {
        // Arrange
        when(frequencyRepo.findByDeleted(Mockito.<Boolean>any())).thenThrow(new ECDException("Exception Msg"));

        // Act and Assert
        assertThrows(ECDException.class, () -> masterServiceImpl.getFrequency());
        verify(frequencyRepo).findByDeleted(isA(Boolean.class));
    }

    /**
     * Method under test: {@link MasterServiceImpl#getLanguageByUserId(Integer)}
     */
    @Test
    void testGetLanguageByUserId() throws ECDException {
        // Arrange
        ArrayList<V_GetUserlangmapping> v_GetUserlangmappingList = new ArrayList<>();
        when(v_GetUserlangmappingRepo.findByUserId(Mockito.<Integer>any())).thenReturn(v_GetUserlangmappingList);

        // Act
        List<V_GetUserlangmapping> actualLanguageByUserId = masterServiceImpl.getLanguageByUserId(1);

        // Assert
        verify(v_GetUserlangmappingRepo).findByUserId(isA(Integer.class));
        assertTrue(actualLanguageByUserId.isEmpty());
        assertSame(v_GetUserlangmappingList, actualLanguageByUserId);
    }

    /**
     * Method under test: {@link MasterServiceImpl#getLanguageByUserId(Integer)}
     */
    @Test
    void testGetLanguageByUserId2() throws ECDException {
        // Arrange
        when(v_GetUserlangmappingRepo.findByUserId(Mockito.<Integer>any())).thenThrow(new ECDException("Exception Msg"));

        // Act and Assert
        assertThrows(ECDException.class, () -> masterServiceImpl.getLanguageByUserId(1));
        verify(v_GetUserlangmappingRepo).findByUserId(isA(Integer.class));
    }

    /**
     * Method under test: {@link MasterServiceImpl#getGenders()}
     */
    @Test
    void testGetGenders() {
        // Arrange
        ArrayList<Gender> genderList = new ArrayList<>();
        when(genderRepo.findByDeleted(Mockito.<Boolean>any())).thenReturn(genderList);

        // Act
        List<Gender> actualGenders = masterServiceImpl.getGenders();

        // Assert
        verify(genderRepo).findByDeleted(isA(Boolean.class));
        assertTrue(actualGenders.isEmpty());
        assertSame(genderList, actualGenders);
    }

    /**
     * Method under test: {@link MasterServiceImpl#getGenders()}
     */
    @Test
    void testGetGenders2() {
        // Arrange
        when(genderRepo.findByDeleted(Mockito.<Boolean>any())).thenThrow(new ECDException("Exception Msg"));

        // Act and Assert
        assertThrows(ECDException.class, () -> masterServiceImpl.getGenders());
        verify(genderRepo).findByDeleted(isA(Boolean.class));
    }
}
