package com.hospital.management.Entity;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Doctor extends BaseEntity {

    private String doctorName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id")
    private City city;

    private String email;

    private String mobileNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "speciality_id")
    private Speciality speciality;





}
