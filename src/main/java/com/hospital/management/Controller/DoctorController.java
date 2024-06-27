package com.hospital.management.Controller;


import com.hospital.management.Constants.Literals;
import com.hospital.management.Constants.MessageCode;
import com.hospital.management.Constants.UrlMapping;
import com.hospital.management.Dto.DoctorDTO;
import com.hospital.management.Service.DoctorService;
import com.hospital.management.Utils.ResponseHandler;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
public class DoctorController {

    private final DoctorService doctorService;

    @Autowired
    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @Operation(method = "ADD DOCTOR", description = "This can be used to add doctor")
    @PostMapping(value = UrlMapping.ADD_DOCTOR)
    public ResponseEntity<Object> addDoctors(@Valid  @RequestBody DoctorDTO doctorDTO) {
        try {
            log.info("ADD DOCTOR :: {}",doctorDTO);
            Map<String, Object> resultMap = doctorService.addDoctor(doctorDTO);
            if (resultMap.get(Literals.STATUS).equals(Literals.TRUE)) {
                return ResponseHandler.response(resultMap.get(Literals.RESPONSE), resultMap.get(Literals.MESSAGE).toString(), true, HttpStatus.OK);
            }
            return ResponseHandler.response(resultMap.get(Literals.RESPONSE), resultMap.get(Literals.MESSAGE).toString(), false, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            log.error(Literals.CATCH_EXCEPTION, e);
        }
        return ResponseHandler.response(null, MessageCode.SOMETHING_WENT_WRONG, false, HttpStatus.BAD_REQUEST);
    }




}
