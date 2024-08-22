package org.example.entities;

import jakarta.persistence.*;
import lombok.*;
import org.example.dto.JobVacancyDTO;
import org.example.utils.StringArrayJsonConverter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "JOB_VACANCY")
public class JobVacancy {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "SLUG")
    private String slug;

    @Column(name = "COMPANY_NAME")
    private String companyName;

    @Column(name = "TITLE")
    private String title;

    @Lob
    @Column(name = "DESCRIPTION", length = 65535, columnDefinition = "CLOB")
    private String description;

    @Column(name = "REMOTE")
    private Boolean remote;

    @Column(name = "URL")
    private String url;

    @Column(name = "TAGS")
    @Convert(converter = StringArrayJsonConverter.class)
    private String[] tags;

    @Column(name = "JOB_TYPES")
    @Convert(converter = StringArrayJsonConverter.class)
    private String[] jobTypes;

    @Column(name = "LOCATION")
    private String location;

    @Column(name = "CREATED_AT")
    private Long createdAt;

    public JobVacancyDTO toDTO() {
        return JobVacancyDTO.builder()
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