package org.example.controllers;

import lombok.RequiredArgsConstructor;
import org.example.dto.JobVacancyDTO;
import org.example.dto.JobVacancyStats;
import org.example.services.JobVacancyService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/job-vacancy")
public class JobVacancyController {

    private final JobVacancyService jobVacancyService;

    @PostMapping("/load/{pageNum}")
    public void loadVacancies(@PathVariable int pageNum) {
        jobVacancyService.saveResponse(pageNum);
    }

    @GetMapping
    public Page<JobVacancyDTO> pagination(@PageableDefault(page = 1, size = 15) @SortDefault.SortDefaults({
            @SortDefault(sort = "createdAt", direction = Sort.Direction.DESC)
    }) Pageable pageable) {
        return jobVacancyService.getPage(pageable);
    }

    @GetMapping("/get-top")
    public List<JobVacancyDTO> getTop() {
        return jobVacancyService.getTop();
    }

    @GetMapping("/get-statistics")
    public List<JobVacancyStats> getStatistics() {
        return jobVacancyService.getStatistics();
    }
}