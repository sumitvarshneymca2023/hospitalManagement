package com.hospital.management.Repository;

import com.hospital.management.Entity.City;
import com.hospital.management.Entity.Symptom;
import com.hospital.management.Enum.Symptoms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SymptomRepository extends JpaRepository<Symptom, Long> {

    Symptom findBySymptom(Symptoms symptoms);


    @Query(nativeQuery = true, value = "Select * from symptom where symptom = :symptomName")
    Symptom findSymptom(String symptomName);

}
