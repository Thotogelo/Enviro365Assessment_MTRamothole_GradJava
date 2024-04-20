package com.enviro.assessment.grad001.mtramothole.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "waste")
public class Waste {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name = "waste_id")
    private Long id;

    @Column(name = "waste_category")
    @Size(min = 1, max = 50, message = "Waste category must be between 1 and 50 characters")
    private String wastecategory;

    @Column(name = "disposal_guideline")
    @Size(min = 1, max = 50, message = "Disposal guidelines must be between 1 and 50 characters")
    private String disposalguideline;

    @Column(name = "recycling_tips")
    @Size(min = 1, max = 50, message = "Recycling tips must be between 1 and 50 characters")
    private String recyclingtip;
}