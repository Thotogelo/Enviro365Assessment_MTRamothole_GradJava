package com.enviro.assessment.grad001.mtramothole.model;

import java.io.Serializable;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "waste")
public class Waste implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name = "waste_id")
    private Long id;

    @NotNull
    @NotEmpty
    @NotBlank
    @Column(name = "waste_category")
    @Size(min = 1, max = 50, message = "Waste category must be between 1 and 50 characters")
    private String wastecategory;

    @NotNull
    @NotEmpty
    @NotBlank
    @Column(name = "disposal_guideline")
    @Size(min = 1, max = 50, message = "Disposal guidelines must be between 1 and 50 characters")
    private String disposalguideline;

    @NotNull
    @NotEmpty
    @NotBlank
    @Size(min = 1, max = 50, message = "Recycling tips must be between 1 and 50 characters")
    @Column(name = "recycling_tips")
    private String recyclingtip;
}