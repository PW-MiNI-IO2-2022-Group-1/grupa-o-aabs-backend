package com.example.SOPSbackend.model;

import com.example.SOPSbackend.dto.BugDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Entity
@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BugEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String description;

    @Column(nullable = false, columnDefinition = "TIMESTAMP")
    private LocalDateTime creationDate;

    public BugEntity(BugDto bugDto) {
        this.name = bugDto.getName();
        this.description = bugDto.getDescription();
        this.creationDate = LocalDateTime.now(ZoneOffset.UTC);
    }
}
