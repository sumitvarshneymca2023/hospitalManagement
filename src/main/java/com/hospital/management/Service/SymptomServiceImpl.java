package com.hospital.management.Service;

import com.hospital.management.Constants.Literals;
import com.hospital.management.Constants.MessageCode;
import com.hospital.management.Entity.Speciality;
import com.hospital.management.Entity.Symptom;
import com.hospital.management.Enum.Symptoms;
import com.hospital.management.Repository.SpecialityRepository;
import com.hospital.management.Repository.SymptomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class SymptomServiceImpl implements SymptomService {

    private final SymptomRepository symptomRepository;
    private final SpecialityRepository specialityRepository;

    @Autowired
    public SymptomServiceImpl(SymptomRepository symptomRepository, SpecialityRepository specialityRepository) {
        this.symptomRepository = symptomRepository;
        this.specialityRepository = specialityRepository;
    }

    @Override
    public Map<String, Object> addSymptoms(Symptoms symptomEnum, long specialityId) {
        Map<String, Object> mapResult = new HashMap<>();
        mapResult.put(Literals.STATUS, Literals.FALSE);

        // Check if the symptom already exists
        Symptom symptom = symptomRepository.findBySymptom(symptomEnum);


        // Retrieve the speciality
        Speciality speciality = specialityRepository.findById(specialityId).orElse(null);

        if (speciality == null) {
            mapResult.put(Literals.MESSAGE, MessageCode.SPECIALITY_NOT_FOUND);
        } else {
            if (symptom == null) {
                // Create a new symptom if it doesn't exist
                symptom = new Symptom();
                symptom.setSymptom(symptomEnum);
                symptom = symptomRepository.save(symptom);
            }

            // Check if the speciality already contains the symptom
            if (!speciality.getSymptoms().contains(symptom)) {
                speciality.getSymptoms().add(symptom);
                specialityRepository.save(speciality); // This should cascade to the join table update
                mapResult.put(Literals.MESSAGE, MessageCode.SPECIALITY_ADD);
                mapResult.put(Literals.STATUS, Literals.TRUE);
            } else {
                mapResult.put(Literals.MESSAGE, MessageCode.SYMPTOM_EXISTS);
            }
        }

        return mapResult;
    }
}
