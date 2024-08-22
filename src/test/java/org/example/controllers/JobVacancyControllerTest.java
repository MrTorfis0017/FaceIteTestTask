package org.example.controllers;

import org.example.dto.JobVacancyDTO;
import org.example.repositories.JobsVacancyRepository;
import org.example.utils.BaseTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class JobVacancyControllerTest extends BaseTest {

    @Autowired
    private JobsVacancyRepository jobsVacancyRepository;

    @Test
    public void loadPagesTest() throws Exception {
        mockMvc.perform(post("/job-vacancy/load/1")).andExpect(status().isOk());
        assertFalse(jobsVacancyRepository.findAll().isEmpty());
    }

    @DisplayName("Test for pagination")
    @Test
    public void paginationTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/job-vacancy")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
    }

    @DisplayName("Test for last 10 popular vacancy")
    @Test
    public void getTopTest() throws Exception {
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/job-vacancy/get-top")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
        var result = objectMapper.readValue(resultActions.andReturn().getResponse().getContentAsString(), JobVacancyDTO[].class);
        assertTrue(Arrays.stream(result).allMatch(JobVacancyDTO::getRemote));
    }

    @DisplayName("Test for statistic")
    @Test
    public void getStatisticTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/job-vacancy/get-statistics")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
}