package com.hospital.management.Service;

import com.hospital.management.Dto.PatientDTO;

import java.util.Map;

public interface PatientService {

    Map<String, Object> addPatient(PatientDTO patientDTO);

    Map<String, Object> deletePatient(Long patientId);

    Map<String, Object> getDoctorAccordingSymptom(Long patientId);
}
