package org.example.repositories;

import org.example.dto.JobVacancyStats;
import org.example.entities.JobVacancy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobsVacancyRepository extends JpaRepository<JobVacancy, Long> {

    List<JobVacancy> findAllByRemoteIsTrue();

    @Query(value = "SELECT new org.example.dto.JobVacancyStats(j.location , COUNT(j.id))" +
            " FROM JOB_VACANCY j " +
            "GROUP BY j.location")
    List<JobVacancyStats> findStatistic();
}