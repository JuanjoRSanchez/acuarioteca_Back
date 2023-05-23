package com.acuarioteca.acuarioteca.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class FileData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    private String type;

    private String filePath;

    @JoinColumn(name = "fish_id")
    @OneToOne(fetch = FetchType.LAZY)
    private Fish fish;
}
