package com.hospital.management.Service;


import com.hospital.management.Constants.Literals;
import com.hospital.management.Constants.MessageCode;
import com.hospital.management.Dto.PatientDTO;
import com.hospital.management.Entity.*;
import com.hospital.management.Repository.CityRepository;
import com.hospital.management.Repository.DoctorRepository;
import com.hospital.management.Repository.PatientsRepository;
import com.hospital.management.Repository.SymptomRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class PatientServiceImpl implements PatientService {



    @Autowired
    private PatientsRepository patientRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private SymptomRepository symptomRepository;

    @Autowired
    private DoctorRepository doctorRepository;



    @Override
    @Transactional
    public Map<String, Object> addPatient(PatientDTO patientDTO) {
        Map<String, Object> mapResult = new HashMap<>();
        mapResult.put(Literals.STATUS, Literals.FALSE);

        // Convert DTO to Entity
        Patients patient = new Patients();
        patient.setPatientName(patientDTO.getPatientName());
        patient.setEmail(patientDTO.getEmail());
        patient.setMobileNumber(patientDTO.getMobileNumber());

        // Find City and Symptom entities
        City city = cityRepository.findCity(patientDTO.getCity().toUpperCase());
        if (city == null) {
            mapResult.put(Literals.MESSAGE, MessageCode.CITY_NOT_FOUND);
        }
        Symptom symptom = symptomRepository.findSymptom(patientDTO.getSymptom().toUpperCase());

        if (symptom == null) {
            mapResult.put(Literals.MESSAGE, MessageCode.SYMPTOM_NOT_FOUND);
        }

        patient.setCity(city);
        patient.setSymptom(symptom);

        List<Doctor> doctor = doctorRepository.findByCityId(city.getId());

        if (doctor.isEmpty()) {
            mapResult.put(Literals.MESSAGE, MessageCode.DOCTOR_NOT_AVAILABLE);
        }
        patient.setSymptom(symptom);

        // Save Patient
        patientRepository.save(patient);

        mapResult.put(Literals.STATUS, Literals.TRUE);
        mapResult.put(Literals.MESSAGE, "Patient added successfully");
        return mapResult;
    }


}
