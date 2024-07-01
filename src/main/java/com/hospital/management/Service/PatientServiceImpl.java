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
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Service
@Slf4j
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
            return mapResult;
        }

        Symptom symptom = symptomRepository.findSymptom(patientDTO.getSymptom().toUpperCase());
        if (symptom == null) {
            mapResult.put(Literals.MESSAGE, MessageCode.SYMPTOM_NOT_FOUND);
            return mapResult;
        }

        List<Doctor> doctors = doctorRepository.findByCityId(city.getId());
        if (doctors.isEmpty()) {
            mapResult.put(Literals.MESSAGE, MessageCode.DOCTOR_NOT_AVAILABLE);
            return mapResult;
        }

        List<Speciality> speciality = symptom.getSpecialities();
        List<Doctor> symptomDoctor = doctorRepository.findBySpecialityId(speciality.get(0).getId());
        if (symptomDoctor.isEmpty()) {
            mapResult.put(Literals.MESSAGE, MessageCode.DOCTOR_NOT_AVAILABLE_FOR_THIS_SYMPTOM);
            return mapResult;
        }
        patient.setCity(city);
        patient.setSymptom(symptom);
        // Save Patient
        patientRepository.save(patient);

        mapResult.put(Literals.STATUS, Literals.TRUE);
        mapResult.put(Literals.MESSAGE, MessageCode.PATIENT_CREATED);
        return mapResult;
    }



    @Override
    @Transactional
    public Map<String, Object> deletePatient(Long patientId) {
        Map<String, Object> mapResult = new HashMap<>();
        mapResult.put(Literals.STATUS, Literals.FALSE);
        Optional<Patients> patients = patientRepository.findById(patientId);
        if (patients.isPresent()) {
            Patients patientDelete = patients.get();
            patientRepository.delete(patientDelete);
            mapResult.put(Literals.STATUS, Literals.TRUE);
            mapResult.put(Literals.MESSAGE, MessageCode.DELETE_DOCTOR);
        } else {
            mapResult.put(Literals.MESSAGE, MessageCode.DOCTOR_NOT_FOUND);
        }
        return mapResult;
    }

    @Override
    public Map<String, Object> getDoctorAccordingSymptom(Long patientId) {
        Map<String, Object> mapResult = new HashMap<>();
        mapResult.put(Literals.STATUS, Literals.FALSE);

        try {
            Patients patient = patientRepository.findById(patientId).orElse(null);
            if (patient == null) {
                mapResult.put(Literals.MESSAGE, MessageCode.PATIENT_NOT_FOUND);
                return mapResult;
            }

            Long cityId = patient.getCity().getId();
            Long symptomId = patient.getSymptom().getId();

            List<Doctor> doctors = doctorRepository.findDoctorsByCityAndSymptom(cityId, symptomId);
            if (doctors.isEmpty()) {
                mapResult.put(Literals.MESSAGE, "No doctors found for the given symptom in the patient's city");
            } else {
                mapResult.put(Literals.STATUS, Literals.TRUE);
                mapResult.put(Literals.RESPONSE, doctors);
                mapResult.put(Literals.MESSAGE, MessageCode.DOCTOR_FETCHED );

            }
        } catch (Exception e) {
            log.error("Exception occurred while fetching doctors: ", e);
            mapResult.put(Literals.MESSAGE, MessageCode.SOMETHING_WENT_WRONG);
        }

        return mapResult;
    }


}
