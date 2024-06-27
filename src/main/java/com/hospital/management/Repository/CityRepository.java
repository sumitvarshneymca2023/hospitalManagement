package com.hospital.management.Repository;

import com.hospital.management.Entity.City;
import com.hospital.management.Enum.Cities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CityRepository extends JpaRepository<City, Long> {


    City findCityByCity(Cities city);

    @Query(nativeQuery = true, value = "Select * from city where city = :cityName")
    City findCity(String cityName);





}
