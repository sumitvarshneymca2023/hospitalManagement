package com.hospital.management.Repository;


import com.hospital.management.Entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface DoctorRepository extends JpaRepository <Doctor, Long> {


    Doctor findByEmail(String email);
    Doctor findByMobileNumber(String mobileNumber);
    List<Doctor> findByCityId(Long cityId);


}
