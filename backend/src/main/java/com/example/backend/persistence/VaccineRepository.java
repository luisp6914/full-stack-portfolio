package com.example.backend.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VaccineRepository extends JpaRepository<VaccineEntity, Integer> {

    VaccineEntity getVaccineEntityById(Integer id);
}
