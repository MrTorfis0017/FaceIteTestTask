package org.example.utils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.example.dto.JobVacancyDTO;
import org.example.entities.JobVacancy;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record JobBoardResponse(List<JobVacancyDTO> data) {
}