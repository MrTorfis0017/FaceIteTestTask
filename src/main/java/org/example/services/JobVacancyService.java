package org.example.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.JobVacancyDTO;
import org.example.dto.JobVacancyStats;
import org.example.entities.JobVacancy;
import org.example.repositories.JobsVacancyRepository;
import org.example.repositories.PublicRepository;
import org.example.utils.JobBoardResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class JobVacancyService {

    private final PublicRepository publicRepository;

    private final JobsVacancyRepository jobsVacancyRepository;

    public void saveResponse(Integer pageNum) {
        try {
            jobsVacancyRepository.saveAll(new ObjectMapper().readValue(publicRepository.findAll(pageNum), JobBoardResponse.class).data().stream()
                    .map(JobVacancyDTO::fromDTO).toList());
        } catch (Exception e) {
            log.warn("Warning !", e);
            throw new RuntimeException(e);
        }
    }

    public Page<JobVacancyDTO> getPage(Pageable pageable) {
        return jobsVacancyRepository.findAll(pageable).map(JobVacancy::toDTO);
    }

    public List<JobVacancyDTO> getTop() {
        return jobsVacancyRepository.findAllByRemoteIsTrue().stream().sorted(Comparator.comparingLong(JobVacancy::getCreatedAt)).toList().stream().map(JobVacancy::toDTO).toList();
    }

    public List<JobVacancyStats> getStatistics() {
        return jobsVacancyRepository.findStatistic();
    }
}
