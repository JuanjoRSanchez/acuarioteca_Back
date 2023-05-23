package com.acuarioteca.acuarioteca.DTOClasses;

import com.acuarioteca.acuarioteca.Models.Fish;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FishResponseDTO {

    private Fish fish;

    private byte[] image;
}
