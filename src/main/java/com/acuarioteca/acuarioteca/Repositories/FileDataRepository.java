package com.acuarioteca.acuarioteca.Repositories;

import com.acuarioteca.acuarioteca.Models.FileData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FileDataRepository extends JpaRepository<FileData, Long> {

    Optional<FileData> findByName(String name);

    FileData save(FileData build);

    Boolean existsFileDataByName(String name);
}
