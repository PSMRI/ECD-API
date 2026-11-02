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
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.iemr.ecd.dao.GradeConfiguration;
import com.iemr.ecd.dao.QualityAuditSectionConfiguration;
import com.iemr.ecd.dao.SampleSelectionConfiguration;
import com.iemr.ecd.repo.call_conf_allocation.GradeConfigurationRepo;
import com.iemr.ecd.repo.call_conf_allocation.SampleSelectionConfigurationRepo;
import com.iemr.ecd.repository.quality.QualityAuditSectionConfigurationRepo;
import com.iemr.ecd.utils.advice.exception_handler.ECDException;

@ExtendWith(MockitoExtension.class)
class ConfigurationServicesTest {

	@Mock
	private GradeConfigurationRepo gradeConfigurationRepo;
	@Mock
	private SampleSelectionConfigurationRepo sampleSelectionConfigurationRepo;
	@Mock
	private QualityAuditSectionConfigurationRepo qualityAuditSectionConfigurationRepo;

	@InjectMocks
	private GradeConfigurationImpl gradeConfigurationImpl;
	@InjectMocks
	private SampleSelectionConfigurationImpl sampleSelectionConfigurationImpl;
	@InjectMocks
	private QualityAuditSectionConfigurationImpl qualityAuditSectionConfigurationImpl;

	@Test
	void createGradeConfigurationSavesAllAndReturnsMessage() {
		List<GradeConfiguration> configurations = List.of(new GradeConfiguration());

		String response = gradeConfigurationImpl.createGradeConfiguration(configurations);

		verify(gradeConfigurationRepo).saveAll(configurations);
		assertTrue(response.contains("Grade Configuration Created Successfully"));
	}

	@Test
	void createGradeConfigurationWrapsRepositoryFailure() {
		List<GradeConfiguration> configurations = List.of(new GradeConfiguration());
		when(gradeConfigurationRepo.saveAll(configurations)).thenThrow(new IllegalStateException("db"));

		assertThrows(ECDException.class, () -> gradeConfigurationImpl.createGradeConfiguration(configurations));
	}

	@Test
	void getGradeConfigurationByPsmIdReturnsRepositoryResult() {
		List<GradeConfiguration> configurations = List.of(new GradeConfiguration());
		when(gradeConfigurationRepo.findByPsmIdOrderByLastModDateDesc(1)).thenReturn(configurations);

		assertSame(configurations, gradeConfigurationImpl.getGradeConfigurationByPSMId(1));
	}

	@Test
	void getGradeConfigurationByPsmIdWrapsRepositoryFailure() {
		when(gradeConfigurationRepo.findByPsmIdOrderByLastModDateDesc(anyInt()))
				.thenThrow(new IllegalStateException("db"));

		assertThrows(ECDException.class, () -> gradeConfigurationImpl.getGradeConfigurationByPSMId(1));
	}

	@Test
	void updateGradeConfigurationSavesWhenIdPresent() {
		GradeConfiguration configuration = new GradeConfiguration();
		configuration.setId(4);

		String response = gradeConfigurationImpl.updateGradeConfiguration(configuration);

		verify(gradeConfigurationRepo).save(configuration);
		assertTrue(response.contains("Grade Configuration Updated Successfully"));
	}

	@Test
	void updateGradeConfigurationSkipsSaveWhenIdMissing() {
		gradeConfigurationImpl.updateGradeConfiguration(new GradeConfiguration());
		gradeConfigurationImpl.updateGradeConfiguration(null);

		verify(gradeConfigurationRepo, never()).save(any());
	}

	@Test
	void updateGradeConfigurationWrapsRepositoryFailure() {
		GradeConfiguration configuration = new GradeConfiguration();
		configuration.setId(4);
		when(gradeConfigurationRepo.save(configuration)).thenThrow(new IllegalStateException("db"));

		assertThrows(ECDException.class, () -> gradeConfigurationImpl.updateGradeConfiguration(configuration));
	}

	@Test
	void createSampleSelectionConfigurationSavesAllAndReturnsMessage() {
		List<SampleSelectionConfiguration> configurations = List.of(new SampleSelectionConfiguration());

		String response = sampleSelectionConfigurationImpl.createSampleSelectionConfiguration(configurations);

		verify(sampleSelectionConfigurationRepo).saveAll(configurations);
		assertTrue(response.contains("Sample Selection Configuration Created Successfully"));
	}

	@Test
	void createSampleSelectionConfigurationWrapsRepositoryFailure() {
		List<SampleSelectionConfiguration> configurations = List.of(new SampleSelectionConfiguration());
		when(sampleSelectionConfigurationRepo.saveAll(configurations)).thenThrow(new IllegalStateException("db"));

		assertThrows(ECDException.class,
				() -> sampleSelectionConfigurationImpl.createSampleSelectionConfiguration(configurations));
	}

	@Test
	void getSampleSelectionConfigurationByPsmIdReturnsRepositoryResult() {
		List<SampleSelectionConfiguration> configurations = List.of(new SampleSelectionConfiguration());
		when(sampleSelectionConfigurationRepo.findByPsmIdOrderByLastModDateDesc(1)).thenReturn(configurations);

		assertSame(configurations, sampleSelectionConfigurationImpl.getSampleSelectionConfigurationByPSMId(1));
	}

	@Test
	void getSampleSelectionConfigurationByPsmIdWrapsRepositoryFailure() {
		when(sampleSelectionConfigurationRepo.findByPsmIdOrderByLastModDateDesc(anyInt()))
				.thenThrow(new IllegalStateException("db"));

		assertThrows(ECDException.class,
				() -> sampleSelectionConfigurationImpl.getSampleSelectionConfigurationByPSMId(1));
	}

	@Test
	void updateSampleSelectionConfigurationSavesWhenIdPresent() {
		SampleSelectionConfiguration configuration = new SampleSelectionConfiguration();
		configuration.setId(2);

		String response = sampleSelectionConfigurationImpl.updateSampleSelectionConfiguration(configuration);

		verify(sampleSelectionConfigurationRepo).save(configuration);
		assertTrue(response.contains("Sample Selection Configuration Upated Sucessfully"));
	}

	@Test
	void updateSampleSelectionConfigurationSkipsSaveWhenIdMissing() {
		sampleSelectionConfigurationImpl.updateSampleSelectionConfiguration(new SampleSelectionConfiguration());
		sampleSelectionConfigurationImpl.updateSampleSelectionConfiguration(null);

		verify(sampleSelectionConfigurationRepo, never()).save(any());
	}

	@Test
	void updateSampleSelectionConfigurationWrapsRepositoryFailure() {
		SampleSelectionConfiguration configuration = new SampleSelectionConfiguration();
		configuration.setId(2);
		when(sampleSelectionConfigurationRepo.save(configuration)).thenThrow(new IllegalStateException("db"));

		assertThrows(ECDException.class,
				() -> sampleSelectionConfigurationImpl.updateSampleSelectionConfiguration(configuration));
	}

	@Test
	void createQualityAuditSectionConfigurationSavesAllAndReturnsMessage() {
		List<QualityAuditSectionConfiguration> configurations = List.of(new QualityAuditSectionConfiguration());

		String response = qualityAuditSectionConfigurationImpl
				.createQualityAuditSectionConfiguration(configurations);

		verify(qualityAuditSectionConfigurationRepo).saveAll(configurations);
		assertTrue(response.contains("Section Configuration Created Successfully"));
	}

	@Test
	void createQualityAuditSectionConfigurationWrapsRepositoryFailure() {
		List<QualityAuditSectionConfiguration> configurations = List.of(new QualityAuditSectionConfiguration());
		when(qualityAuditSectionConfigurationRepo.saveAll(configurations))
				.thenThrow(new IllegalStateException("db"));

		assertThrows(ECDException.class, () -> qualityAuditSectionConfigurationImpl
				.createQualityAuditSectionConfiguration(configurations));
	}

	@Test
	void getQualityAuditSectionConfigurationByPsmIdReturnsRepositoryResult() {
		List<QualityAuditSectionConfiguration> configurations = List.of(new QualityAuditSectionConfiguration());
		when(qualityAuditSectionConfigurationRepo.findByPsmIdOrderByLastModDateDesc(1)).thenReturn(configurations);

		assertSame(configurations,
				qualityAuditSectionConfigurationImpl.getQualityAuditSectionConfigurationByPSMId(1));
	}

	@Test
	void getQualityAuditSectionConfigurationByPsmIdWrapsRepositoryFailure() {
		when(qualityAuditSectionConfigurationRepo.findByPsmIdOrderByLastModDateDesc(anyInt()))
				.thenThrow(new IllegalStateException("db"));

		assertThrows(ECDException.class,
				() -> qualityAuditSectionConfigurationImpl.getQualityAuditSectionConfigurationByPSMId(1));
	}

	@Test
	void updateQualityAuditSectionConfigurationSavesWhenSectionIdPresent() {
		QualityAuditSectionConfiguration configuration = new QualityAuditSectionConfiguration();
		configuration.setSectionId(6);

		String response = qualityAuditSectionConfigurationImpl
				.updateQualityAuditSectionConfiguration(configuration);

		verify(qualityAuditSectionConfigurationRepo).save(configuration);
		assertTrue(response.contains("Updated Successfully"));
	}

	@Test
	void updateQualityAuditSectionConfigurationSkipsSaveWhenSectionIdMissing() {
		qualityAuditSectionConfigurationImpl
				.updateQualityAuditSectionConfiguration(new QualityAuditSectionConfiguration());
		qualityAuditSectionConfigurationImpl.updateQualityAuditSectionConfiguration(null);

		verify(qualityAuditSectionConfigurationRepo, never()).save(any());
	}

	@Test
	void updateQualityAuditSectionConfigurationWrapsRepositoryFailure() {
		QualityAuditSectionConfiguration configuration = new QualityAuditSectionConfiguration();
		configuration.setSectionId(6);
		when(qualityAuditSectionConfigurationRepo.save(configuration)).thenThrow(new IllegalStateException("db"));

		assertThrows(ECDException.class, () -> qualityAuditSectionConfigurationImpl
				.updateQualityAuditSectionConfiguration(configuration));
	}
}
