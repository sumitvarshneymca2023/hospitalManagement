package com.hospital.management.Service;

import com.hospital.management.Constants.Literals;
import com.hospital.management.Constants.MessageCode;
import com.hospital.management.Entity.City;
import com.hospital.management.Enum.Cities;
import com.hospital.management.Repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;

    @Autowired
    public CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public Map<String, Object> addCity(Cities cities) {
        Map<String, Object> mapResult = new HashMap<>();
        mapResult.put(Literals.STATUS, Literals.FALSE);
        City city = cityRepository.findCityByCity(cities);
        if (city != null) {
            mapResult.put(Literals.MESSAGE, MessageCode.CITY_EXISTS);
        } else {
            City newCity = new City();
            newCity.setCity(cities);
            cityRepository.save(newCity);
            mapResult.put(Literals.MESSAGE, MessageCode.CITY_ADD);
            mapResult.put(Literals.STATUS, Literals.TRUE);
        }
        return mapResult;
    }


}
