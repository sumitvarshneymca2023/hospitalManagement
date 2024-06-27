package com.hospital.management.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PatientDTO {

    @NotBlank(message = "Patient  name is mandatory")
    @Size(min = 3, message = "Patient name should be at least 3 characters")
    private String patientName;

    @NotBlank(message = "Email should be valid")
    @Email(message = "Email should be valid")
    private String email;

    @Pattern(regexp = "^\\d{10}$", message = "Mobile number should be 10 digits")
    @Size(min = 10, message = "Mobile number should be at least 10 digits")
    private String mobileNumber;

    @NotBlank(message = "City is mandatory")
    @Size(max = 20, message = "City should be at most 20 characters")
    private String city;

    @NotBlank(message = "Symptom is mandatory")
    private String symptom;
}
