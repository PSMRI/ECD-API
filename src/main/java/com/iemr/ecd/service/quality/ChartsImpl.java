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

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iemr.ecd.dto.ResponseFetchQualityChartsDataDTO;
import com.iemr.ecd.repository.quality.QualityAuditorRatingRepo;
import com.iemr.ecd.utils.advice.exception_handler.ECDException;

@Service
public class ChartsImpl {

	@Autowired
	private QualityAuditorRatingRepo qualityAuditorRatingRepo;

	// Pr_trend_overall_quality_rating
	public List<ResponseFetchQualityChartsDataDTO> getTrendAnalysisOfCentreOverallQualityRatings(Integer psmId,
			String frequency, String month) {
		try {
			List<String[]> chartsRes = qualityAuditorRatingRepo.getTrendAnalysisOfCentreOverallQualityRatings(psmId,
					frequency, month);

			return getChartData(chartsRes);
		} catch (Exception e) {
			throw new ECDException(e);
		}
	}

	// Pr_actor_wise_quality_rating
	public List<ResponseFetchQualityChartsDataDTO> getActorWiseQualityRatings(Integer psmId, String roleName,
			String month) {
		try {
			List<String[]> chartsResActorWise = qualityAuditorRatingRepo.getActorWiseQualityRatings(psmId, roleName,
					month);

			return getChartData(chartsResActorWise);
		} catch (Exception e) {
			throw new ECDException(e);
		}
	}

	// Pr_tenture_wise_quality_rating
	public List<ResponseFetchQualityChartsDataDTO> getTenureWiseQualityRatings(Integer psmId, String roleName) {
		try {
			List<String[]> chartsRes = qualityAuditorRatingRepo.getTenureWiseQualityRatings(psmId, roleName);

			return getChartData(chartsRes);
		} catch (Exception e) {
			throw new ECDException();
		}
	}

	// Pr_grade_wise_agent_count
	public List<ResponseFetchQualityChartsDataDTO> gradeWiseAgentCount(Integer psmId, String frequency,
			String frequencyValue) {
		try {
			List<String[]> chartsRes = qualityAuditorRatingRepo.getGradeWiseAgentCount(psmId, frequency,
					frequencyValue);

			return getChartData(chartsRes);
		} catch (Exception e) {
			throw new ECDException(e);
		}
	}

	private List<ResponseFetchQualityChartsDataDTO> getChartData(List<String[]> chartSpData) throws Exception {
		List<ResponseFetchQualityChartsDataDTO> chartData = new ArrayList<>();
		ResponseFetchQualityChartsDataDTO obj;
		if (chartSpData != null && chartSpData.size() > 0) {
			for (String[] strArr : chartSpData) {
				obj = new ResponseFetchQualityChartsDataDTO();

				if (strArr.length == 2) {
					if (strArr[0] != null) {
						obj.setName(strArr[0]);
						if (strArr[1] != null)
							obj.setValue(Integer.valueOf(strArr[1]));

						chartData.add(obj);
					}
				} else if (strArr.length == 3) {
					if (strArr[1] != null)
						obj.setName(strArr[1]);
					if (strArr[2] != null)
						obj.setValue(Integer.valueOf(strArr[2]));

					chartData.add(obj);
				}

			}
		}
		return chartData;
	}

}
