package org.example.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(description = "Loading vacancies")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success|OK"),
    })
    @PostMapping("/load/{pageNum}")
    public void loadVacancies(@PathVariable int pageNum) {
        jobVacancyService.saveResponse(pageNum);
    }

    @Operation(description = "Find all vacations with pagination")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success|OK"),
    })
    @GetMapping
    public Page<JobVacancyDTO> pagination(@PageableDefault(page = 1, size = 15) @SortDefault.SortDefaults({
            @SortDefault(sort = "createdAt", direction = Sort.Direction.DESC)
    }) Pageable pageable) {
        return jobVacancyService.getPage(pageable);
    }

    @Operation(description = "Find top vacations(top 10)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success|OK"),
    })
    @GetMapping("/get-top")
    public List<JobVacancyDTO> getTop() {
        return jobVacancyService.getTop();
    }

    @Operation(description = "Get statistic")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success|OK"),
    })
    @GetMapping("/get-statistics")
    public List<JobVacancyStats> getStatistics() {
        return jobVacancyService.getStatistics();
    }
}