package com.hospital.management.Service;


import com.hospital.management.Constants.Literals;
import com.hospital.management.Constants.MessageCode;
import com.hospital.management.Dto.DoctorDTO;
import com.hospital.management.Entity.City;
import com.hospital.management.Entity.Doctor;
import com.hospital.management.Entity.Speciality;
import com.hospital.management.Enum.Cities;
import com.hospital.management.Repository.CityRepository;
import com.hospital.management.Repository.DoctorRepository;
import com.hospital.management.Repository.SpecialityRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class DoctorServiceImpl implements DoctorService {

    private static final Logger log = LoggerFactory.getLogger(DoctorServiceImpl.class);
    private final DoctorRepository doctorRepository;
    private final CityRepository cityRepository;
    private final SpecialityRepository specialityRepository;

    @Autowired
    public DoctorServiceImpl(DoctorRepository doctorRepository,
                             CityRepository cityRepository,
                             SpecialityRepository specialityRepository) {
        this.doctorRepository = doctorRepository;
        this.cityRepository = cityRepository;
        this.specialityRepository = specialityRepository;
    }

    @Override
    @Transactional
    public Map<String, Object> addDoctor(DoctorDTO doctorDTO) {
        Map<String, Object> mapResult = new HashMap<>();
        mapResult.put(Literals.STATUS, Literals.FALSE);

        // Check if doctor with the given email already exists
        if (doctorRepository.findByEmail(doctorDTO.getEmail()) != null) {
            mapResult.put(Literals.MESSAGE, MessageCode.EMAIL_ALREADY);
            return mapResult;
        }

        // Check if doctor with the given mobile number already exists
        if (doctorRepository.findByMobileNumber(doctorDTO.getMobileNumber()) != null) {
            mapResult.put(Literals.MESSAGE, MessageCode.MOBILE_NUMBER);
            return mapResult;
        }

        City city = cityRepository.findCity(doctorDTO.getCity().toUpperCase());
        // Check if the city exists
        if (city == null) {
            mapResult.put(Literals.MESSAGE, MessageCode.CITY_NOT_FOUND);
            return mapResult;
        }

        Speciality speciality = specialityRepository.findSpeciality(doctorDTO.getSpeciality().toUpperCase());
        // Check if the speciality exists
        if (speciality == null) {
            mapResult.put(Literals.MESSAGE, MessageCode.SPECIALITY_NOT_FOUND);
            return mapResult;
        }

        // Map DoctorDTO to Doctor entity
        Doctor doctor = new Doctor();
        doctor.setDoctorName(doctorDTO.getDoctorName());
        doctor.setEmail(doctorDTO.getEmail());
        doctor.setMobileNumber(doctorDTO.getMobileNumber());
        doctor.setCity(city);
        doctor.setSpeciality(speciality);

        // Save the doctor to the database
        doctorRepository.save(doctor);

        // Update the result map
        mapResult.put(Literals.STATUS, Literals.TRUE);
        mapResult.put(Literals.MESSAGE, MessageCode.DOCTOR_ADD);

        return mapResult;
    }
}
