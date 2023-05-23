package com.acuarioteca.acuarioteca.Services;

import com.acuarioteca.acuarioteca.Models.FileData;
import com.acuarioteca.acuarioteca.Models.Fish;
import com.acuarioteca.acuarioteca.Repositories.FileDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final FileDataRepository fileDataRepository;
    private final String FOLDER_PATH="C:/Users/Juanjo Rubio/Desktop/Acuarioteca/Acuarioteca_Back/fish_images/";
    public String uploadImageToFileSystem(MultipartFile file) throws IOException {
        String filePath=FOLDER_PATH+file.getOriginalFilename();
        FileData fileData=fileDataRepository.save(FileData.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .filePath(filePath).build());

        file.transferTo(new File(filePath));

        if (fileData != null) {
            return "file uploaded successfully : " + filePath;
        }
        return "Sorry, the upload process fail.";
    }

    public String uploadImageToFileSystemTest(MultipartFile file, Fish fish) throws IOException {
        String filePath=FOLDER_PATH+file.getOriginalFilename();
        FileData fileData=fileDataRepository.save(FileData.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .filePath(filePath)
                .fish(fish).build());

        file.transferTo(new File(filePath));

        if (fileData != null) {
            return "file uploaded successfully : " + filePath;
        }
        return "Sorry, the upload process fail.";
    }

    public byte[] downloadImageFromFileSystem(String fileName) throws IOException {
        Optional<FileData> fileData = fileDataRepository.findByName(fileName);
        String filePath=fileData.get().getFilePath();
        byte[] images = Files.readAllBytes(new File(filePath).toPath());
        return images;
    }
}
