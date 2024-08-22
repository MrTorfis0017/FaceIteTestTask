package org.example.repositories;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Repository
@FeignClient(url = "${job-vacancy-url}", name = "jobsApi")
public interface PublicRepository {
    @GetMapping("?page={pageNum}")
    String findAll(@PathVariable Integer pageNum);
}
