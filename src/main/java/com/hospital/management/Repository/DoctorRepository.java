package com.hospital.management.Repository;


import com.hospital.management.Entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface DoctorRepository extends JpaRepository <Doctor, Long> {


    Doctor findByEmail(String email);

    Doctor findByMobileNumber(String mobileNumber);

    List<Doctor> findByCityId(Long cityId);

    List<Doctor> findBySpecialityId(Long specialityId);

    @Query(value = "SELECT * FROM Doctor d WHERE d.city_id = :cityId AND d.speciality_id IN (SELECT s.id FROM Symptom s WHERE s.id = :symptomId)", nativeQuery = true)
    List<Doctor> findDoctorsByCityAndSymptom(@Param("cityId") long cityId, @Param("symptomId") long symptomId);



}
