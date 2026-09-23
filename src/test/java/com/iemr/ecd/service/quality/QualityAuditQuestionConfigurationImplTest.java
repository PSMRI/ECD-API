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
package com.iemr.ecd.service.quality;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import com.iemr.ecd.dao.QualityAuditQuestionConfig;
import com.iemr.ecd.dao.QualityAuditQuestionnaireValues;
import com.iemr.ecd.dto.QualityAuditorSectionQuestionaireResponseDTO;
import com.iemr.ecd.repository.quality.QualityAuditQuestionConfigRepo;
import com.iemr.ecd.repository.quality.QualityAuditQuestionnaireValuesRepo;
import com.iemr.ecd.utils.advice.exception_handler.ECDException;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class QualityAuditQuestionConfigurationImplTest {

	@Mock
	private QualityAuditQuestionConfigRepo qualityAuditQuestionConfigRepo;
	@Mock
	private QualityAuditQuestionnaireValuesRepo qualityAuditQuestionnaireValuesRepo;

	@InjectMocks
	private QualityAuditQuestionConfigurationImpl service;

	private QualityAuditQuestionConfig question(Integer id) {
		QualityAuditQuestionConfig question = new QualityAuditQuestionConfig();
		question.setId(id);
		question.setQuestionnaire("Was the greeting polite?");
		question.setPsmId(1);
		question.setCreatedBy("admin");
		question.setOptions(new String[] { "Yes", "No" });
		question.setScores(new Integer[] { 10, 0 });
		question.setRoles(List.of("ASSOCIATE", "ANM"));
		return question;
	}

	@SuppressWarnings("unchecked")
	@Test
	void createSavesQuestionAndItsOptions() {
		QualityAuditQuestionConfig question = question(null);
		QualityAuditQuestionConfig saved = question(55);
		when(qualityAuditQuestionConfigRepo.save(question)).thenReturn(saved);

		String response = service.createQualityAuditQuestionnaireConfiguration(List.of(question));

		assertEquals("ASSOCIATE,ANM", question.getRole());
		ArgumentCaptor<Set<QualityAuditQuestionnaireValues>> captor = ArgumentCaptor.forClass(Set.class);
		verify(qualityAuditQuestionnaireValuesRepo).saveAll(captor.capture());
		Set<QualityAuditQuestionnaireValues> options = captor.getValue();
		assertEquals(2, options.size());
		options.forEach(option -> {
			assertEquals(55, option.getQuestionId());
			assertEquals(1, option.getPsmId());
			assertEquals("admin", option.getCreatedBy());
		});
		assertTrue(response.contains("Qulaity Audit Questionnaire Created Successfully"));
	}

	@Test
	void createSkipsOptionSaveWhenQuestionHasNoOptions() {
		QualityAuditQuestionConfig question = new QualityAuditQuestionConfig();
		question.setOptions(new String[0]);
		when(qualityAuditQuestionConfigRepo.save(question)).thenReturn(question);

		service.createQualityAuditQuestionnaireConfiguration(List.of(question));

		verify(qualityAuditQuestionnaireValuesRepo, never()).saveAll(any());
	}

	@Test
	void createSkipsOptionSaveWhenSavedQuestionHasNoId() {
		QualityAuditQuestionConfig question = question(null);
		when(qualityAuditQuestionConfigRepo.save(question)).thenReturn(new QualityAuditQuestionConfig());

		service.createQualityAuditQuestionnaireConfiguration(List.of(question));

		verify(qualityAuditQuestionnaireValuesRepo, never()).saveAll(any());
	}

	@Test
	void createSkipsRoleFlatteningWhenRolesEmpty() {
		QualityAuditQuestionConfig question = question(null);
		question.setRoles(List.of());
		when(qualityAuditQuestionConfigRepo.save(question)).thenReturn(question(55));

		service.createQualityAuditQuestionnaireConfiguration(List.of(question));

		assertNull(question.getRole());
	}

	@Test
	void createWrapsFailure() {
		QualityAuditQuestionConfig question = question(null);
		when(qualityAuditQuestionConfigRepo.save(question)).thenThrow(new IllegalStateException("db"));

		assertThrows(ECDException.class,
				() -> service.createQualityAuditQuestionnaireConfiguration(List.of(question)));
	}

	private String[] questionnaireRow() {
		return new String[] { "3", "Greeting", "55", "Was the greeting polite?", "1", "2", "radio", "1", "true",
				"false", "admin", "2024-01-01 00:00:00", "editor", "2024-02-01 00:00:00", "ASSOCIATE,ANM" };
	}

	@Test
	void getByPsmIdMapsEveryColumnAndAttachesOptions() {
		QualityAuditQuestionnaireValues option = new QualityAuditQuestionnaireValues();
		option.setQuestionValues("Yes");
		option.setScore(10);
		when(qualityAuditQuestionConfigRepo.getQualityAuditQuestionnaire(1))
				.thenReturn(List.<String[]>of(questionnaireRow()));
		when(qualityAuditQuestionnaireValuesRepo.findByQuestionIdAndPsmIdAndDeleted(55, 1, false))
				.thenReturn(List.of(option));

		QualityAuditorSectionQuestionaireResponseDTO dto = service
				.getQualityAuditQuestionnaireConfigurationByPSMId(1).get(0);

		assertEquals(3, dto.getSectionId());
		assertEquals("Greeting", dto.getSectionName());
		assertEquals(55, dto.getQuestionId());
		assertEquals("Was the greeting polite?", dto.getQuestion());
		assertEquals(1, dto.getSectionRank());
		assertEquals(2, dto.getQuestionRank());
		assertEquals("radio", dto.getAnswerType());
		assertEquals(1, dto.getPsmId());
		assertTrue(dto.getIsFatalQues());
		assertEquals(false, dto.getDeleted());
		assertEquals("admin", dto.getCreatedBy());
		assertEquals(Timestamp.valueOf("2024-01-01 00:00:00"), dto.getCreatedDate());
		assertEquals("editor", dto.getModifiedBy());
		assertEquals(Timestamp.valueOf("2024-02-01 00:00:00"), dto.getLastModDate());
		assertEquals(List.of("ASSOCIATE", "ANM"), dto.getRoles());
		assertEquals(List.of("Yes"), dto.getOptions());
		assertEquals(List.of(10), dto.getScores());
	}

	@Test
	void getByPsmIdSkipsNullColumnsAndBlankRoles() {
		String[] row = new String[15];
		row[14] = "";
		when(qualityAuditQuestionConfigRepo.getQualityAuditQuestionnaire(1)).thenReturn(List.<String[]>of(row));
		when(qualityAuditQuestionnaireValuesRepo.findByQuestionIdAndPsmIdAndDeleted(null, null, false))
				.thenReturn(List.of());

		QualityAuditorSectionQuestionaireResponseDTO dto = service
				.getQualityAuditQuestionnaireConfigurationByPSMId(1).get(0);

		assertNull(dto.getSectionId());
		assertNull(dto.getRoles());
		assertTrue(dto.getOptions().isEmpty());
	}

	@Test
	void getByPsmIdHandlesEmptyAndNullResults() {
		when(qualityAuditQuestionConfigRepo.getQualityAuditQuestionnaire(anyInt())).thenReturn(List.of(), null);

		assertTrue(service.getQualityAuditQuestionnaireConfigurationByPSMId(1).isEmpty());
		assertTrue(service.getQualityAuditQuestionnaireConfigurationByPSMId(1).isEmpty());
	}

	@Test
	void getByPsmIdWrapsFailure() {
		when(qualityAuditQuestionConfigRepo.getQualityAuditQuestionnaire(anyInt()))
				.thenThrow(new IllegalStateException("db"));

		assertThrows(ECDException.class, () -> service.getQualityAuditQuestionnaireConfigurationByPSMId(1));
	}

	@Test
	void getByPsmIdWrapsOptionLookupFailure() {
		when(qualityAuditQuestionConfigRepo.getQualityAuditQuestionnaire(1))
				.thenReturn(List.<String[]>of(questionnaireRow()));
		when(qualityAuditQuestionnaireValuesRepo.findByQuestionIdAndPsmIdAndDeleted(anyInt(), anyInt(),
				anyBoolean())).thenThrow(new IllegalStateException("db"));

		assertThrows(ECDException.class, () -> service.getQualityAuditQuestionnaireConfigurationByPSMId(1));
	}

	@SuppressWarnings("unchecked")
	@Test
	void updateSoftDeletesOldOptionsAndSavesNewOnes() {
		QualityAuditQuestionConfig question = question(55);
		QualityAuditQuestionnaireValues existing = new QualityAuditQuestionnaireValues();
		existing.setQuestionValues("Old");
		when(qualityAuditQuestionnaireValuesRepo.findByQuestionIdAndDeleted(55, false))
				.thenReturn(List.of(existing));

		String response = service.updateQualityAuditQuestionnaireConfiguration(question);

		assertEquals("ASSOCIATE,ANM", question.getRole());
		verify(qualityAuditQuestionConfigRepo).save(question);
		assertTrue(existing.getDeleted());
		ArgumentCaptor<Set<QualityAuditQuestionnaireValues>> captor = ArgumentCaptor.forClass(Set.class);
		verify(qualityAuditQuestionnaireValuesRepo).saveAll(captor.capture());
		assertEquals(3, captor.getValue().size());
		assertTrue(response.contains("Qulaity Audit Questionnaire Updated Successfully"));
	}

	@Test
	void updateSkipsOptionSaveWhenNothingChanges() {
		QualityAuditQuestionConfig question = new QualityAuditQuestionConfig();
		question.setId(55);
		when(qualityAuditQuestionnaireValuesRepo.findByQuestionIdAndDeleted(55, false)).thenReturn(List.of());

		service.updateQualityAuditQuestionnaireConfiguration(question);

		verify(qualityAuditQuestionnaireValuesRepo, never()).saveAll(any());
	}

	@Test
	void updateSkipsOptionSaveWhenScoresMissing() {
		QualityAuditQuestionConfig question = question(55);
		question.setScores(new Integer[0]);
		when(qualityAuditQuestionnaireValuesRepo.findByQuestionIdAndDeleted(55, false)).thenReturn(null);

		service.updateQualityAuditQuestionnaireConfiguration(question);

		verify(qualityAuditQuestionnaireValuesRepo, never()).saveAll(any());
	}

	@Test
	void updateWrapsFailure() {
		QualityAuditQuestionConfig question = question(55);
		when(qualityAuditQuestionConfigRepo.save(question)).thenThrow(new IllegalStateException("db"));

		assertThrows(ECDException.class, () -> service.updateQualityAuditQuestionnaireConfiguration(question));
	}

	@Test
	void flattenRolesJoinsRolesAndIgnoresEmptyLists() {
		QualityAuditQuestionConfig question = new QualityAuditQuestionConfig();
		question.setRoles(List.of("A", "B"));
		question.flattenRoles();
		assertEquals("A,B", question.getRole());

		QualityAuditQuestionConfig empty = new QualityAuditQuestionConfig();
		empty.flattenRoles();
		assertNull(empty.getRole());

		QualityAuditQuestionConfig blank = new QualityAuditQuestionConfig();
		blank.setRoles(List.of());
		blank.flattenRoles();
		assertNull(blank.getRole());
	}
}
