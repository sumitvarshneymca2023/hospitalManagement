package com.hospital.management.Repository;


import com.hospital.management.Dto.DoctorsDTO;
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

    @Query(value = "SELECT d.id AS doctorId, d.doctor_name AS doctorName, d.email AS email, d.mobile_number AS mobileNumber, " +
            "d.city_id AS cityId, d.speciality_id AS specialityId, s.specialities " +
            "FROM doctor d " +
            "JOIN city c ON c.id = d.city_id " +
            "JOIN speciality s ON s.id = d.speciality_id " +
            "JOIN speciality_symptom ss ON ss.speciality_id = s.id " +
            "JOIN symptom sy ON sy.id = ss.symptom_id " +
            "WHERE c.id = :cityId AND sy.id = :symptomId", nativeQuery = true)
    List<DoctorsDTO> findDoctorsByCityAndSymptom(@Param("cityId") long cityId, @Param("symptomId") long symptomId);



}
