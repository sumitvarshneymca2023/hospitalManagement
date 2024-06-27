package com.hospital.management.Repository;

import com.hospital.management.Entity.City;
import com.hospital.management.Entity.Speciality;
import com.hospital.management.Enum.Specialities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SpecialityRepository extends JpaRepository<Speciality,Long> {

    Speciality findBySpecialities(Specialities specialities);

    @Query(nativeQuery = true, value = "SELECT * FROM speciality WHERE specialities = :specialityName")
    Speciality findSpeciality(@Param("specialityName") String specialityName);


}
