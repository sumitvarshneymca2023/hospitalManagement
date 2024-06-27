package com.hospital.management.Controller;


import com.hospital.management.Constants.Literals;
import com.hospital.management.Constants.MessageCode;
import com.hospital.management.Constants.UrlMapping;
import com.hospital.management.Dto.PatientDTO;
import com.hospital.management.Enum.Specialities;
import com.hospital.management.Service.PatientService;
import com.hospital.management.Service.SpecialitiesService;
import com.hospital.management.Utils.ResponseHandler;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
public class PatientsController {


    private final PatientService patientService;

    @Autowired
    public PatientsController(PatientService patientService) {
        this.patientService = patientService;
    }


    @Operation(method = "ADD PATIENT", description = "This can be used to add patient")
    @PostMapping(value = UrlMapping.ADD_PATIENT)
    public ResponseEntity<Object> addPatients(@Valid @RequestBody PatientDTO patientDTO) {
        try {
            log.info("ADD PATIENT :: {}", patientDTO);
            Map<String, Object> resultMap = patientService.addPatient(patientDTO);
            if (Literals.TRUE.equals(resultMap.get(Literals.STATUS))) {
                return ResponseHandler.response(resultMap.get(Literals.RESPONSE), resultMap.get(Literals.MESSAGE).toString(), true, HttpStatus.OK);
            }
            return ResponseHandler.response(resultMap.get(Literals.RESPONSE), resultMap.get(Literals.MESSAGE).toString(), false, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            log.error(Literals.CATCH_EXCEPTION, e);
            return ResponseHandler.response(null, MessageCode.SOMETHING_WENT_WRONG, false, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}


