package com.acuarioteca.acuarioteca.Services;

import com.acuarioteca.acuarioteca.DTOClasses.FishImageDTO;
import com.acuarioteca.acuarioteca.DTOClasses.FishResponseDTO;
import com.acuarioteca.acuarioteca.Exceptions.NoFoundException;
import com.acuarioteca.acuarioteca.Models.FileData;
import com.acuarioteca.acuarioteca.Models.Fish;
import com.acuarioteca.acuarioteca.Repositories.FishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FishService {

    private final FishRepository fishRepository;

    private final FileDataService fileDataService;

    private final ImageService imageService;


    public Fish getFishById(String fish_id){
        return fishRepository.findById(Long.valueOf(fish_id)).orElseThrow(
                () -> new NoFoundException("No fish found with the Id:" + fish_id));
    }

    public List<Fish> getAllFishes() {
        return fishRepository.findAll();
    }
    public List<FishImageDTO> getAllFishesWithImage() throws IOException {
        List <Fish> fishes = fishRepository.findAll();
        List <FishImageDTO> fishesDTO = new ArrayList<>();
        for(Fish fish : fishes){
            FishImageDTO fishImageDTO =  new FishImageDTO();
            if (fish.getFileData() != null){
                String imagePath = fish.getFileData().getFilePath();
                fishImageDTO.setImagePath(imagePath);
            }
            fishImageDTO.setFish(fish);
            fishesDTO.add(fishImageDTO);
        }

        return fishesDTO;
    }

    public Fish getFishByCommonName(String commonName){
        return fishRepository.findByCommonName(commonName).orElseThrow(
                () -> new NoFoundException("No fish found with the name: " + commonName)
        );
    }

    public String addNewFish(Fish fish){
        Fish fish1 = fishRepository.save(fish);
        if(fish1 != null){
            return "Fish added successfully";
        }
        return "Sorry, the save process fail.";
    }

    public String addImageToFish(MultipartFile file, String fish_id) throws IOException {
        Fish fish = fishRepository.findById(Long.valueOf(fish_id)).orElseThrow();


        if(fish != null ){
            imageService.uploadImageToFileSystemTest(file, fish);
            FileData fileData = fileDataService.getFileByName(file.getOriginalFilename());
            fish.setFileData(fileData);
            fishRepository.save(fish);
            return "Image added correctly.";
        }else{
            return "Sorry, the upload failed.";
        }

    }


    public Fish updateFish(Fish fish) {
        Fish fishAux = fishRepository.findById(fish.getId()).orElseThrow(
                () -> new NoFoundException("No fish found with the Id:" + fish.getId()));

        return fishRepository.save(fishAux);
    }
}
