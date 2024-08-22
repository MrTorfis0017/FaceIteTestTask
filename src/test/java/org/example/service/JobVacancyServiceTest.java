package org.example.service;


import org.example.services.JobVacancyService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class JobVacancyServiceTest {

    @InjectMocks
    private JobVacancyService jobVacancyService;

    @Test
    public void testJobVacancyService() {
        assertThrows(Exception.class, () -> jobVacancyService.saveResponse(1));
    }
}