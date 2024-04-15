package com.enviro.assessment.grad001.mtramothole.model;

import jakarta.persistence.*;
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
public class Waste {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "waste_id", nullable = false)
    private Long id;

    @Column(name = "waste_category", nullable = false)
    private String wastecategory;

    @Column(name = "disposal_guideline", nullable = false)
    private String disposalguideline;

    @Column(name = "recycling_tips", nullable = false)
    private String recyclingtip;
}