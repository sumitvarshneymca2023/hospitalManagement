package com.hospital.management.Entity;

import com.hospital.management.Enum.Symptoms;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Symptom extends BaseEntity {


    @Enumerated(EnumType.STRING)
    private Symptoms symptom;


    @ManyToMany(mappedBy = "symptoms")
    private List<Speciality> specialities = new ArrayList<>();

}
