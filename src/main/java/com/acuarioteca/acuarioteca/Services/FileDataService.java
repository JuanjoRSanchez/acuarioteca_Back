package com.acuarioteca.acuarioteca.Services;

import com.acuarioteca.acuarioteca.Models.FileData;
import com.acuarioteca.acuarioteca.Models.Fish;
import com.acuarioteca.acuarioteca.Repositories.FileDataRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
@AllArgsConstructor
public class FileDataService {

    private final FileDataRepository fileDataRepository;

    public FileData getFileDataByName(String fileName){

        return fileDataRepository.findByName(fileName).orElseThrow();
    }

    public Boolean existsById(int id){
        return fileDataRepository.existsById(Long.valueOf(id));
    }
    public Boolean existsByName(String name){
        return fileDataRepository.existsFileDataByName(name);
    }

    public FileData getFileByName(String fileName) {

        return fileDataRepository.findByName(fileName).get();
    }

    public FileData getFileById(Long id) {

        return fileDataRepository.findById(id).get();
    }
}
