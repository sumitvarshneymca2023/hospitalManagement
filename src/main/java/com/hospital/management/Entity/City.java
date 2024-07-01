package com.hospital.management.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hospital.management.Enum.Cities;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class City extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private Cities city;
}
