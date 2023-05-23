package com.acuarioteca.acuarioteca.Controllers;

import com.acuarioteca.acuarioteca.DTOClasses.FishImageDTO;
import com.acuarioteca.acuarioteca.DTOClasses.FishResponseDTO;
import com.acuarioteca.acuarioteca.Models.FileData;
import com.acuarioteca.acuarioteca.Models.Fish;
import com.acuarioteca.acuarioteca.Services.FileDataService;
import com.acuarioteca.acuarioteca.Services.FishService;
import com.acuarioteca.acuarioteca.Services.ImageService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping(path = "/api/v1/acuarioteca")
public class FishController {

    private final ImageService imageService;

    private final FishService fishService;

    private final FileDataService fileDataService;

    @GetMapping("/fileSystem/{fileName}")
    public ResponseEntity<?> downloadImageFromFileSystem(@PathVariable String fileName) throws IOException {
        System.out.println(fileName);
        byte[] imageData=imageService.downloadImageFromFileSystem(fileName);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(imageData);

    }

    @GetMapping("fish/{fish_id}")
    public ResponseEntity<Fish> getFishById(@PathVariable String fish_id){
        Fish fish = fishService.getFishById(fish_id);
        return ResponseEntity.status(HttpStatus.OK).body(fish);
    }

    @GetMapping("fishes")
    public ResponseEntity<List<Fish>> getAllFishes(){
        return ResponseEntity.status(HttpStatus.OK).body(fishService.getAllFishes());
    }


    @GetMapping("/fishesWithImage")
    public ResponseEntity<List<FishImageDTO>> getAllFishesWithImage() throws IOException {
        return ResponseEntity.status(HttpStatus.OK).body(fishService.getAllFishesWithImage());
    }

    /// Test only
    @GetMapping("/imageToFish1/{fileName}")
    public ResponseEntity<?> getFileDataIdByName(@PathVariable String fileName) throws IOException {
        FileData fileData = fileDataService.getFileByName(fileName);
        return ResponseEntity.status(HttpStatus.OK).body(fileData);
    }

    @PostMapping("/fish")
    public ResponseEntity<?> addFish(@RequestBody Fish fish) {
        String resp = fishService.addNewFish(fish);
        return ResponseEntity.status(HttpStatus.OK).body(resp);
    }

    @PostMapping("/imageToFish/{fish_id}")
    public ResponseEntity<?> addImageToFish(@RequestParam("image")MultipartFile file, @PathVariable String fish_id) throws IOException {
        String resp = fishService.addImageToFish(file, fish_id);
        return ResponseEntity.status(HttpStatus.OK).body(resp);
    }


    @PostMapping("/fileSystem")
    public ResponseEntity<?> uploadImageToFIleSystem(@RequestParam("image")MultipartFile file) throws IOException {
        String uploadImage = imageService.uploadImageToFileSystem(file);
        return ResponseEntity.status(HttpStatus.OK)
                .body(uploadImage);
    }

    @PutMapping("/updateFish")
    public ResponseEntity<Fish> updateFish(@RequestBody Fish fish){
        Fish fishAux = fishService.updateFish(fish);
        return ResponseEntity.status(HttpStatus.OK).body(fishAux);
    }

}
