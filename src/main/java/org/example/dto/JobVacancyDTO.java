package org.example.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;
import org.example.entities.JobVacancy;

@Getter
@Setter
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@NoArgsConstructor
@AllArgsConstructor
public class JobVacancyDTO {

    private String slug;

    private String companyName;

    private String title;

    private String description;

    private Boolean remote;

    private String url;

    private String[] tags;

    private String[] jobTypes;

    private String location;

    private Long createdAt;

    public JobVacancy fromDTO() {
        return JobVacancy.builder()
                .slug(this.slug)
                .companyName(this.companyName)
                .title(this.title)
                .description(this.description)
                .remote(this.remote)
                .url(this.url)
                .tags(this.tags)
                .jobTypes(this.jobTypes)
                .location(this.location)
                .createdAt(this.createdAt)
                .build();
    }
}
