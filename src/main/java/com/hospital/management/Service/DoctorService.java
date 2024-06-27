package com.hospital.management.Service;

import com.hospital.management.Dto.DoctorDTO;

import java.util.Map;

public interface DoctorService {

    Map<String, Object> addDoctor(DoctorDTO doctorDTO);

    Map<String, Object> deleteDoctor(Long doctorId);
}
