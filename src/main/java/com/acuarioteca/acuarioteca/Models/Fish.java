package com.acuarioteca.acuarioteca.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Fish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;

    @JsonIgnore
    @OneToOne
    private FileData fileData;

    @Column(name = "common_name", unique = true)
    private String commonName;

    @Column(name = "scientist_name")
    private String scientistName;

    @Column(name = "introduction")
    private String introduction;

    @Column(name = "origin")
    private String origin;

    private Integer size;

    private Integer longevity;

    private String diet;

    @Column(name = "min_population")
    private Integer minPopulation;

    private String difficulty;

    private Float minPh;

    private Float maxPh;

    private Integer minGh;

    private Integer maxGh;

    @Column(name = "max_temperature")
    private Float maxTemperature;

    @Column(name = "min_temperature")
    private Float minTemperature;

    @Column(name = "min_volume")
    private Integer minVolume;

}
