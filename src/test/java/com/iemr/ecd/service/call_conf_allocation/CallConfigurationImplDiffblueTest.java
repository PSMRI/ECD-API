package com.iemr.ecd.service.call_conf_allocation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
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

import com.iemr.ecd.dao.CallConfiguration;
import com.iemr.ecd.dao.CallSectionMapping;
import com.iemr.ecd.dao.V_GetCallSectionMapping;
import com.iemr.ecd.dto.CallSectionMappingDTO;
import com.iemr.ecd.repo.call_conf_allocation.CallConfigurationRepo;
import com.iemr.ecd.repo.call_conf_allocation.CallSectionMappingRepo;
import com.iemr.ecd.repo.call_conf_allocation.V_GetCallSectionMappingRepo;
import com.iemr.ecd.utils.advice.exception_handler.ECDException;

import io.micrometer.observation.NullObservation;
import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationRegistry;

@ContextConfiguration(classes = { CallConfigurationImpl.class, CallSectionMappingDTO.class })
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class CallConfigurationImplDiffblueTest {
	@Autowired
	private CallSectionMappingDTO callSectionMappingDTO;

	@Autowired
	private CallConfigurationImpl callConfigurationImpl;

	@MockBean
	private CallConfigurationRepo callConfigurationRepo;

	@MockBean
	private CallSectionMappingRepo callSectionMappingRepo;

	@MockBean
	private ObservationRegistry observationRegistry;

	@MockBean
	private V_GetCallSectionMappingRepo v_GetCallSectionMappingRepo;

	/**
	 * Method under test:
	 * {@link CallConfigurationImpl#createCallConfigurations(List)}
	 */
	@Test
	void testCreateCallConfigurations() {
		// Arrange
		ArrayList<CallConfiguration> callConfigurationList = new ArrayList<>();
		when(callConfigurationRepo.saveAll(Mockito.<Iterable<CallConfiguration>>any()))
				.thenReturn(callConfigurationList);
		when(observationRegistry.observationConfig()).thenReturn(new ObservationRegistry.ObservationConfig());
		NullObservation nullObservation = new NullObservation(observationRegistry);
		when(observationRegistry.getCurrentObservation()).thenReturn(nullObservation);
		when(observationRegistry.observationConfig()).thenReturn(new ObservationRegistry.ObservationConfig());
		NullObservation nullObservation2 = new NullObservation(observationRegistry);
		when(observationRegistry.getCurrentObservation()).thenReturn(nullObservation2);
		when(observationRegistry.observationConfig()).thenReturn(new ObservationRegistry.ObservationConfig());
		when(observationRegistry.isNoop()).thenReturn(true);

		// Act
		List<CallConfiguration> actualCreateCallConfigurationsResult = callConfigurationImpl
				.createCallConfigurations(new ArrayList<>());

		// Assert

		assertTrue(actualCreateCallConfigurationsResult.isEmpty());
		assertTrue(callConfigurationImpl.getCallConfigurations().isEmpty());
		assertSame(callConfigurationList, actualCreateCallConfigurationsResult);
	}

	/**
	 * Method under test:
	 * {@link CallConfigurationImpl#createCallConfigurations(List)}
	 */
	@Test
	void testCreateCallConfigurations2() {
		// Arrange
		doThrow(new ECDException("Exception Msg")).when(observationRegistry)
				.setCurrentObservationScope(Mockito.<Observation.Scope>any());
		when(observationRegistry.observationConfig()).thenReturn(new ObservationRegistry.ObservationConfig());
		NullObservation nullObservation = new NullObservation(observationRegistry);
		when(observationRegistry.getCurrentObservationScope()).thenReturn(mock(Observation.Scope.class));
		doNothing().when(observationRegistry).setCurrentObservationScope(Mockito.<Observation.Scope>any());
		when(observationRegistry.getCurrentObservation()).thenReturn(nullObservation);
		when(observationRegistry.observationConfig()).thenReturn(new ObservationRegistry.ObservationConfig());
		NullObservation nullObservation2 = new NullObservation(observationRegistry);
		when(observationRegistry.getCurrentObservationScope()).thenReturn(mock(Observation.Scope.class));
		doNothing().when(observationRegistry).setCurrentObservationScope(Mockito.<Observation.Scope>any());
		when(observationRegistry.getCurrentObservation()).thenReturn(nullObservation2);
		when(observationRegistry.observationConfig()).thenReturn(new ObservationRegistry.ObservationConfig());
		when(observationRegistry.isNoop()).thenReturn(false);

		// Act and Assert
		assertThrows(ECDException.class, () -> callConfigurationImpl.createCallConfigurations(new ArrayList<>()));
		verify(observationRegistry, atLeast(1)).getCurrentObservation();
		verify(observationRegistry, atLeast(1)).getCurrentObservationScope();
		verify(observationRegistry).isNoop();
		verify(observationRegistry, atLeast(1)).observationConfig();
		verify(observationRegistry).setCurrentObservationScope(isA(Observation.Scope.class));
	}

	/**
	 * Method under test: {@link CallConfigurationImpl#getCallConfigurations()}
	 */
	@Test
	void testGetCallConfigurations() {
		// Arrange
		ArrayList<CallConfiguration> callConfigurationList = new ArrayList<>();
		when(callConfigurationRepo.findTop10ByDeleted(Mockito.<Boolean>any())).thenReturn(callConfigurationList);
		when(observationRegistry.observationConfig()).thenReturn(new ObservationRegistry.ObservationConfig());
		NullObservation nullObservation = new NullObservation(observationRegistry);
		when(observationRegistry.getCurrentObservation()).thenReturn(nullObservation);
		when(observationRegistry.observationConfig()).thenReturn(new ObservationRegistry.ObservationConfig());
		NullObservation nullObservation2 = new NullObservation(observationRegistry);
		when(observationRegistry.getCurrentObservation()).thenReturn(nullObservation2);
		when(observationRegistry.observationConfig()).thenReturn(new ObservationRegistry.ObservationConfig());
		when(observationRegistry.isNoop()).thenReturn(true);

		// Act
		List<CallConfiguration> actualCallConfigurations = callConfigurationImpl.getCallConfigurations();

		// Assert

		assertTrue(actualCallConfigurations.isEmpty());
		assertSame(callConfigurationList, actualCallConfigurations);
	}

	/**
	 * Method under test: {@link CallConfigurationImpl#getCallConfigurations()}
	 */
	@Test
	void testGetCallConfigurations2() {
		// Arrange
		doThrow(new ECDException("Exception Msg")).when(observationRegistry)
				.setCurrentObservationScope(Mockito.<Observation.Scope>any());
		when(observationRegistry.observationConfig()).thenReturn(new ObservationRegistry.ObservationConfig());
		NullObservation nullObservation = new NullObservation(observationRegistry);
		when(observationRegistry.getCurrentObservationScope()).thenReturn(mock(Observation.Scope.class));
		doNothing().when(observationRegistry).setCurrentObservationScope(Mockito.<Observation.Scope>any());
		when(observationRegistry.getCurrentObservation()).thenReturn(nullObservation);
		when(observationRegistry.observationConfig()).thenReturn(new ObservationRegistry.ObservationConfig());
		NullObservation nullObservation2 = new NullObservation(observationRegistry);
		when(observationRegistry.getCurrentObservationScope()).thenReturn(mock(Observation.Scope.class));
		doNothing().when(observationRegistry).setCurrentObservationScope(Mockito.<Observation.Scope>any());
		when(observationRegistry.getCurrentObservation()).thenReturn(nullObservation2);
		when(observationRegistry.observationConfig()).thenReturn(new ObservationRegistry.ObservationConfig());
		when(observationRegistry.isNoop()).thenReturn(false);

		// Act and Assert
		assertThrows(ECDException.class, () -> callConfigurationImpl.getCallConfigurations());
		verify(observationRegistry, atLeast(1)).getCurrentObservation();
		verify(observationRegistry, atLeast(1)).getCurrentObservationScope();
		verify(observationRegistry).isNoop();
		verify(observationRegistry, atLeast(1)).observationConfig();
		verify(observationRegistry).setCurrentObservationScope(isA(Observation.Scope.class));
	}

	/**
	 * Method under test:
	 * {@link CallConfigurationImpl#getCallConfigurationById(Long)}
	 */
	@Test
	void testGetCallConfigurationById() {
		// Arrange
		CallConfiguration callConfiguration = new CallConfiguration();
		callConfiguration.setBaseLine("Base Line");
		callConfiguration.setCallConfigId(1L);
		callConfiguration.setCallType("Call Type");
		callConfiguration.setConfigId(1L);
		callConfiguration.setConfigTerms("Config Terms");
		callConfiguration.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
		callConfiguration.setCreatedDate(mock(Timestamp.class));
		callConfiguration.setDeleted(true);
		callConfiguration.setDisplayName("Display Name");
		callConfiguration.setEffectiveEndDate(mock(Timestamp.class));
		callConfiguration.setEffectiveStartDate(mock(Timestamp.class));
		callConfiguration.setLastModDate(mock(Timestamp.class));
		callConfiguration.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
		callConfiguration.setNextAttemptPeriod(1);
		callConfiguration.setNoOfAttempts(1);
		callConfiguration.setPsmId(1);
		callConfiguration.setTermRange(1);
		when(callConfigurationRepo.findByCallConfigId(Mockito.<Long>any())).thenReturn(callConfiguration);
		when(observationRegistry.observationConfig()).thenReturn(new ObservationRegistry.ObservationConfig());
		NullObservation nullObservation = new NullObservation(observationRegistry);
		when(observationRegistry.getCurrentObservation()).thenReturn(nullObservation);
		when(observationRegistry.observationConfig()).thenReturn(new ObservationRegistry.ObservationConfig());
		NullObservation nullObservation2 = new NullObservation(observationRegistry);
		when(observationRegistry.getCurrentObservation()).thenReturn(nullObservation2);
		when(observationRegistry.observationConfig()).thenReturn(new ObservationRegistry.ObservationConfig());
		when(observationRegistry.isNoop()).thenReturn(true);

		// Act
		CallConfiguration actualCallConfigurationById = callConfigurationImpl.getCallConfigurationById(1L);

		// Assert

		assertTrue(callConfigurationImpl.getCallConfigurations().isEmpty());
		assertSame(callConfiguration, actualCallConfigurationById);
	}

	/**
	 * Method under test:
	 * {@link CallConfigurationImpl#getCallConfigurationByPSMID(Integer)}
	 */
	@Test
	void testGetCallConfigurationByPSMID() {
		// Arrange
		ArrayList<CallConfiguration> callConfigurationList = new ArrayList<>();
		when(callConfigurationRepo.findByPsmIdAndDeleted(Mockito.<Integer>any(), Mockito.<Boolean>any()))
				.thenReturn(callConfigurationList);
		when(observationRegistry.observationConfig()).thenReturn(new ObservationRegistry.ObservationConfig());
		NullObservation nullObservation = new NullObservation(observationRegistry);
		when(observationRegistry.getCurrentObservation()).thenReturn(nullObservation);
		when(observationRegistry.observationConfig()).thenReturn(new ObservationRegistry.ObservationConfig());
		NullObservation nullObservation2 = new NullObservation(observationRegistry);
		when(observationRegistry.getCurrentObservation()).thenReturn(nullObservation2);
		when(observationRegistry.observationConfig()).thenReturn(new ObservationRegistry.ObservationConfig());
		when(observationRegistry.isNoop()).thenReturn(true);

		// Act
		List<CallConfiguration> actualCallConfigurationByPSMID = callConfigurationImpl.getCallConfigurationByPSMID(1);

		// Assert

		assertTrue(actualCallConfigurationByPSMID.isEmpty());
		assertTrue(callConfigurationImpl.getCallConfigurations().isEmpty());
		assertSame(callConfigurationList, actualCallConfigurationByPSMID);
	}

	/**
	 * Method under test:
	 * {@link CallConfigurationImpl#getCallConfigurationByPSMID(Integer)}
	 */
	@Test
	void testGetCallConfigurationByPSMID2() {
		// Arrange
		doThrow(new ECDException("Exception Msg")).when(observationRegistry)
				.setCurrentObservationScope(Mockito.<Observation.Scope>any());
		when(observationRegistry.observationConfig()).thenReturn(new ObservationRegistry.ObservationConfig());
		NullObservation nullObservation = new NullObservation(observationRegistry);
		when(observationRegistry.getCurrentObservationScope()).thenReturn(mock(Observation.Scope.class));
		doNothing().when(observationRegistry).setCurrentObservationScope(Mockito.<Observation.Scope>any());
		when(observationRegistry.getCurrentObservation()).thenReturn(nullObservation);
		when(observationRegistry.observationConfig()).thenReturn(new ObservationRegistry.ObservationConfig());
		NullObservation nullObservation2 = new NullObservation(observationRegistry);
		when(observationRegistry.getCurrentObservationScope()).thenReturn(mock(Observation.Scope.class));
		doNothing().when(observationRegistry).setCurrentObservationScope(Mockito.<Observation.Scope>any());
		when(observationRegistry.getCurrentObservation()).thenReturn(nullObservation2);
		when(observationRegistry.observationConfig()).thenReturn(new ObservationRegistry.ObservationConfig());
		when(observationRegistry.isNoop()).thenReturn(false);

		// Act and Assert
		assertThrows(ECDException.class, () -> callConfigurationImpl.getCallConfigurationByPSMID(1));
		verify(observationRegistry, atLeast(1)).getCurrentObservation();
		verify(observationRegistry, atLeast(1)).getCurrentObservationScope();
		verify(observationRegistry).isNoop();
		verify(observationRegistry, atLeast(1)).observationConfig();
		verify(observationRegistry).setCurrentObservationScope(isA(Observation.Scope.class));
	}

	/**
	 * Method under test:
	 * {@link CallConfigurationImpl#updateCallConfigurations(List)}
	 */
	@Test
	void testUpdateCallConfigurations() {
		// Arrange
		ArrayList<CallConfiguration> callConfigurationList = new ArrayList<>();
		when(callConfigurationRepo.saveAll(Mockito.<Iterable<CallConfiguration>>any()))
				.thenReturn(callConfigurationList);
		when(observationRegistry.observationConfig()).thenReturn(new ObservationRegistry.ObservationConfig());
		NullObservation nullObservation = new NullObservation(observationRegistry);
		when(observationRegistry.getCurrentObservation()).thenReturn(nullObservation);
		when(observationRegistry.observationConfig()).thenReturn(new ObservationRegistry.ObservationConfig());
		NullObservation nullObservation2 = new NullObservation(observationRegistry);
		when(observationRegistry.getCurrentObservation()).thenReturn(nullObservation2);
		when(observationRegistry.observationConfig()).thenReturn(new ObservationRegistry.ObservationConfig());
		when(observationRegistry.isNoop()).thenReturn(true);

		// Act
		List<CallConfiguration> actualUpdateCallConfigurationsResult = callConfigurationImpl
				.updateCallConfigurations(new ArrayList<>());

		// Assert

		assertTrue(callConfigurationImpl.getCallConfigurations().isEmpty());
		assertTrue(actualUpdateCallConfigurationsResult.isEmpty());
		assertSame(callConfigurationList, actualUpdateCallConfigurationsResult);
	}

	/**
	 * Method under test:
	 * {@link CallConfigurationImpl#updateCallConfigurations(List)}
	 */
	@Test
	void testUpdateCallConfigurations2() {
		// Arrange
		doThrow(new ECDException("Exception Msg")).when(observationRegistry)
				.setCurrentObservationScope(Mockito.<Observation.Scope>any());
		when(observationRegistry.observationConfig()).thenReturn(new ObservationRegistry.ObservationConfig());
		NullObservation nullObservation = new NullObservation(observationRegistry);
		
		nullObservation.toString();
		
		when(observationRegistry.getCurrentObservationScope()).thenReturn(mock(Observation.Scope.class));
		doNothing().when(observationRegistry).setCurrentObservationScope(Mockito.<Observation.Scope>any());
		when(observationRegistry.getCurrentObservation()).thenReturn(nullObservation);
		when(observationRegistry.observationConfig()).thenReturn(new ObservationRegistry.ObservationConfig());
		NullObservation nullObservation2 = new NullObservation(observationRegistry);
		when(observationRegistry.getCurrentObservationScope()).thenReturn(mock(Observation.Scope.class));
		doNothing().when(observationRegistry).setCurrentObservationScope(Mockito.<Observation.Scope>any());
		when(observationRegistry.getCurrentObservation()).thenReturn(nullObservation2);
		when(observationRegistry.observationConfig()).thenReturn(new ObservationRegistry.ObservationConfig());
		when(observationRegistry.isNoop()).thenReturn(false);

		// Act and Assert
		assertThrows(ECDException.class, () -> callConfigurationImpl.updateCallConfigurations(new ArrayList<>()));
		verify(observationRegistry, atLeast(1)).getCurrentObservation();
		verify(observationRegistry, atLeast(1)).getCurrentObservationScope();
		verify(observationRegistry).isNoop();
		verify(observationRegistry, atLeast(1)).observationConfig();
		verify(observationRegistry).setCurrentObservationScope(isA(Observation.Scope.class));
	}

	/**
	 * Method under test:
	 * {@link CallConfigurationImpl#mapCallAndSection(CallSectionMappingDTO)}
	 */
	@Test
	void testMapCallAndSection() {
		// Arrange, Act and Assert
		assertThrows(ECDException.class, () -> callConfigurationImpl.mapCallAndSection(callSectionMappingDTO));
	}

	/**
	 * Method under test:
	 * {@link CallConfigurationImpl#getCallAndSectionMapByPsmIdAndCallConfigId(Integer, Integer)}
	 */
	@Test
	void testGetCallAndSectionMapByPsmIdAndCallConfigId() {
		// Arrange
		when(callSectionMappingRepo.findByPsmIdAndCallConfigIdAndDeletedOrderByLastModDateDesc(Mockito.<Integer>any(),
				Mockito.<Integer>any(), Mockito.<Boolean>any())).thenReturn(new ArrayList<>());

		// Act
		List<V_GetCallSectionMapping> actualCallAndSectionMapByPsmIdAndCallConfigId = callConfigurationImpl
				.getCallAndSectionMapByPsmIdAndCallConfigId(1, 1);

		// Assert
		verify(callSectionMappingRepo).findByPsmIdAndCallConfigIdAndDeletedOrderByLastModDateDesc(isA(Integer.class),
				isA(Integer.class), isA(Boolean.class));
		assertTrue(actualCallAndSectionMapByPsmIdAndCallConfigId.isEmpty());
	}

	/**
	 * Method under test:
	 * {@link CallConfigurationImpl#getCallAndSectionMapByPsmIdAndCallConfigId(Integer, Integer)}
	 */
	@Test
	void testGetCallAndSectionMapByPsmIdAndCallConfigId2() {
		// Arrange
		CallSectionMapping callSectionMapping = new CallSectionMapping();
		callSectionMapping.setCallConfigId(1);
		callSectionMapping.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
		callSectionMapping.setCreatedDate(mock(Timestamp.class));
		callSectionMapping.setDeleted(true);
		callSectionMapping.setId(1);
		callSectionMapping.setIsChecked(true);
		callSectionMapping.setLastModDate(mock(Timestamp.class));
		callSectionMapping.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
		callSectionMapping.setOutboundCallType("Outbound Call Type");
		callSectionMapping.setPsmId(1);
		callSectionMapping.setSectionId(1);
		callSectionMapping.setSectionRank(1);
		
		callSectionMapping.toString();

		ArrayList<CallSectionMapping> callSectionMappingList = new ArrayList<>();
		callSectionMappingList.add(callSectionMapping);
		when(callSectionMappingRepo.findByPsmIdAndCallConfigIdAndDeletedOrderByLastModDateDesc(Mockito.<Integer>any(),
				Mockito.<Integer>any(), Mockito.<Boolean>any())).thenReturn(callSectionMappingList);
		when(v_GetCallSectionMappingRepo.findByCallConfigIdAndSectionIdAndPsmId(Mockito.<Integer>any(),
				Mockito.<Integer>any(), Mockito.<Integer>any())).thenReturn(new ArrayList<>());

		// Act
		List<V_GetCallSectionMapping> actualCallAndSectionMapByPsmIdAndCallConfigId = callConfigurationImpl
				.getCallAndSectionMapByPsmIdAndCallConfigId(1, 1);

		// Assert
		verify(callSectionMappingRepo).findByPsmIdAndCallConfigIdAndDeletedOrderByLastModDateDesc(isA(Integer.class),
				isA(Integer.class), isA(Boolean.class));
		verify(v_GetCallSectionMappingRepo).findByCallConfigIdAndSectionIdAndPsmId(isA(Integer.class),
				isA(Integer.class), isA(Integer.class));
		assertTrue(actualCallAndSectionMapByPsmIdAndCallConfigId.isEmpty());
	}

	/**
	 * Method under test:
	 * {@link CallConfigurationImpl#getCallAndSectionMapByPsmIdAndCallConfigId(Integer, Integer)}
	 */
	@Test
	void testGetCallAndSectionMapByPsmIdAndCallConfigId3() {
		// Arrange
		CallSectionMapping callSectionMapping = new CallSectionMapping();
		callSectionMapping.setCallConfigId(1);
		callSectionMapping.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
		callSectionMapping.setCreatedDate(mock(Timestamp.class));
		callSectionMapping.setDeleted(true);
		callSectionMapping.setId(1);
		callSectionMapping.setIsChecked(true);
		callSectionMapping.setLastModDate(mock(Timestamp.class));
		callSectionMapping.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
		callSectionMapping.setOutboundCallType("Outbound Call Type");
		callSectionMapping.setPsmId(1);
		callSectionMapping.setSectionId(1);
		callSectionMapping.setSectionRank(1);

		ArrayList<CallSectionMapping> callSectionMappingList = new ArrayList<>();
		callSectionMappingList.add(callSectionMapping);
		when(callSectionMappingRepo.findByPsmIdAndCallConfigIdAndDeletedOrderByLastModDateDesc(Mockito.<Integer>any(),
				Mockito.<Integer>any(), Mockito.<Boolean>any())).thenReturn(callSectionMappingList);

		V_GetCallSectionMapping v_GetCallSectionMapping = new V_GetCallSectionMapping();
		v_GetCallSectionMapping.setCallConfigId(1);
		v_GetCallSectionMapping.setDeleted(true);
		v_GetCallSectionMapping.setId(1);
		v_GetCallSectionMapping.setIsChecked(true);
		v_GetCallSectionMapping.setPsmId(1);
		v_GetCallSectionMapping.setSectionId(1);
		v_GetCallSectionMapping.setSectionName("Section Name");
		v_GetCallSectionMapping.setSectionRank(1);

		ArrayList<V_GetCallSectionMapping> v_GetCallSectionMappingList = new ArrayList<>();
		v_GetCallSectionMappingList.add(v_GetCallSectionMapping);
		when(v_GetCallSectionMappingRepo.findByCallConfigIdAndSectionIdAndPsmId(Mockito.<Integer>any(),
				Mockito.<Integer>any(), Mockito.<Integer>any())).thenReturn(v_GetCallSectionMappingList);

		// Act
		List<V_GetCallSectionMapping> actualCallAndSectionMapByPsmIdAndCallConfigId = callConfigurationImpl
				.getCallAndSectionMapByPsmIdAndCallConfigId(1, 1);

		// Assert
		verify(callSectionMappingRepo).findByPsmIdAndCallConfigIdAndDeletedOrderByLastModDateDesc(isA(Integer.class),
				isA(Integer.class), isA(Boolean.class));
		verify(v_GetCallSectionMappingRepo).findByCallConfigIdAndSectionIdAndPsmId(isA(Integer.class),
				isA(Integer.class), isA(Integer.class));
		assertEquals(1, actualCallAndSectionMapByPsmIdAndCallConfigId.size());
	}

	/**
	 * Method under test:
	 * {@link CallConfigurationImpl#getCallAndSectionMapByPsmIdAndCallConfigId(Integer, Integer)}
	 */
	@Test
	void testGetCallAndSectionMapByPsmIdAndCallConfigId4() {
		// Arrange
		when(callSectionMappingRepo.findByPsmIdAndDeletedOrderByLastModDateDesc(Mockito.<Integer>any(),
				Mockito.<Boolean>any())).thenReturn(new ArrayList<>());

		// Act
		List<V_GetCallSectionMapping> actualCallAndSectionMapByPsmIdAndCallConfigId = callConfigurationImpl
				.getCallAndSectionMapByPsmIdAndCallConfigId(1, 0);

		// Assert
		verify(callSectionMappingRepo).findByPsmIdAndDeletedOrderByLastModDateDesc(isA(Integer.class),
				isA(Boolean.class));
		assertTrue(actualCallAndSectionMapByPsmIdAndCallConfigId.isEmpty());
	}

	/**
	 * Method under test:
	 * {@link CallConfigurationImpl#getCallAndSectionMapByPsmIdAndCallConfigId(Integer, Integer)}
	 */
	@Test
	void testGetCallAndSectionMapByPsmIdAndCallConfigId5() {
		// Arrange
		CallSectionMapping callSectionMapping = new CallSectionMapping();
		callSectionMapping.setCallConfigId(1);
		callSectionMapping.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
		callSectionMapping.setCreatedDate(mock(Timestamp.class));
		callSectionMapping.setDeleted(true);
		callSectionMapping.setId(1);
		callSectionMapping.setIsChecked(true);
		callSectionMapping.setLastModDate(mock(Timestamp.class));
		callSectionMapping.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
		callSectionMapping.setOutboundCallType("Outbound Call Type");
		callSectionMapping.setPsmId(1);
		callSectionMapping.setSectionId(1);
		callSectionMapping.setSectionRank(1);

		ArrayList<CallSectionMapping> callSectionMappingList = new ArrayList<>();
		callSectionMappingList.add(callSectionMapping);
		when(callSectionMappingRepo.findByPsmIdAndDeletedOrderByLastModDateDesc(Mockito.<Integer>any(),
				Mockito.<Boolean>any())).thenReturn(callSectionMappingList);
		when(v_GetCallSectionMappingRepo.findBySectionIdAndPsmId(Mockito.<Integer>any(), Mockito.<Integer>any()))
				.thenReturn(new ArrayList<>());

		// Act
		List<V_GetCallSectionMapping> actualCallAndSectionMapByPsmIdAndCallConfigId = callConfigurationImpl
				.getCallAndSectionMapByPsmIdAndCallConfigId(1, 0);

		// Assert
		verify(callSectionMappingRepo).findByPsmIdAndDeletedOrderByLastModDateDesc(isA(Integer.class),
				isA(Boolean.class));
		verify(v_GetCallSectionMappingRepo).findBySectionIdAndPsmId(isA(Integer.class), isA(Integer.class));
		assertTrue(actualCallAndSectionMapByPsmIdAndCallConfigId.isEmpty());
	}
}
