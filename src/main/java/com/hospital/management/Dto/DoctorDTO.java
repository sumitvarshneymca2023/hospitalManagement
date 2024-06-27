package com.hospital.management.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class DoctorDTO {

    @NotBlank(message = "Doctor name is mandatory")
    @Size(min = 3, message = "Doctor name should be at least 3 characters")
    private String doctorName;

    @NotBlank(message = "Email should be valid")
    @Email(message = "Email should be valid")
    private String email;

    @Pattern(regexp = "^\\d{10}$", message = "Mobile number should be 10 digits")
    private String mobileNumber;

    @NotBlank(message = "City is mandatory")
    @Size(max = 20, message = "City should be at max 20 characters")
    private String city;

    @NotBlank(message = "Speciality is mandatory")
    private String speciality;
}
