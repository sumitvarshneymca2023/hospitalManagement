package com.hospital.management.Entity;


import com.hospital.management.Enum.Specialities;
import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Speciality extends BaseEntity {


    @Enumerated(EnumType.STRING)
    private Specialities specialities;

    @ManyToMany
    @JoinTable(
            name = "speciality_symptom",
            joinColumns = @JoinColumn(name = "speciality_id"),
            inverseJoinColumns = @JoinColumn(name = "symptom_id")
    )
    private Set<Symptom> symptoms = new HashSet<>();


}
