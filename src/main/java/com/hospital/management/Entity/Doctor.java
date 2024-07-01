package com.hospital.management.Entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Doctor extends BaseEntity {

    private String doctorName;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    private String email;

    private String mobileNumber;

    @ManyToOne
    @JoinColumn(name = "speciality_id")
    private Speciality speciality;





}
