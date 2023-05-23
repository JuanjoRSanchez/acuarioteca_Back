package com.acuarioteca.acuarioteca.Repositories;


import com.acuarioteca.acuarioteca.Models.Fish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FishRepository extends JpaRepository<Fish, Long> {

    Optional <Fish> findByCommonName(String commonName);
 }
