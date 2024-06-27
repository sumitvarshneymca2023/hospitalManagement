package com.hospital.management.Service;

import com.hospital.management.Constants.Literals;
import com.hospital.management.Constants.MessageCode;
import com.hospital.management.Entity.Speciality;
import com.hospital.management.Enum.Specialities;
import com.hospital.management.Repository.SpecialityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class SpecialitiesServiceImpl implements SpecialitiesService {

    private final SpecialityRepository specialityRepository;

    @Autowired
    public SpecialitiesServiceImpl(SpecialityRepository specialityRepository) {
        this.specialityRepository = specialityRepository;
    }

    @Override
    public Map<String, Object> addSpecialitys(Specialities specialities) {
        Map<String, Object> mapResult = new HashMap<>();
        mapResult.put(Literals.STATUS, Literals.FALSE);
        Speciality speciality = specialityRepository.findBySpecialities(specialities);
        if (speciality != null) {
            mapResult.put(Literals.MESSAGE, MessageCode.SPECIALITY_EXISTS);
        } else {
            Speciality newSpeciality = new Speciality();
            newSpeciality.setSpecialities(specialities);
            specialityRepository.save(newSpeciality);
            mapResult.put(Literals.MESSAGE, MessageCode.SPECIALITY_ADD);
            mapResult.put(Literals.STATUS, Literals.TRUE);
        }
        return mapResult;
    }
}
