package com.hospital.management.Controller;


import com.hospital.management.Constants.Literals;
import com.hospital.management.Constants.MessageCode;
import com.hospital.management.Constants.UrlMapping;
import com.hospital.management.Enum.Specialities;
import com.hospital.management.Service.SpecialitiesService;
import com.hospital.management.Utils.ResponseHandler;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
public class SpecialityController {

    private final SpecialitiesService specialitiesService;

    @Autowired
    public SpecialityController(SpecialitiesService specialitiesService) {
        this.specialitiesService = specialitiesService;
    }

    @Operation(method = "ADD SPECIALITY", description = "This can be used to add speciality")
    @PostMapping(value = UrlMapping.ADD_SPECIALITY)
    public ResponseEntity<Object> addSpeciality(@RequestParam Specialities specialities) {
        try {
            log.info("ADD SPECIALITY :: {}",specialities);
            Map<String, Object> resultMap = specialitiesService.addSpecialitys(specialities);
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
