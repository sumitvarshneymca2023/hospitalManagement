package com.hospital.management.Repository;

import com.hospital.management.Entity.Patients;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientsRepository extends JpaRepository<Patients, Long> {
}
