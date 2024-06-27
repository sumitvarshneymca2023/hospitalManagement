package com.hospital.management.Dto;

import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

import java.util.List;

@Data
@XmlRootElement(name = "error")
public class ErrorResponse {
    //General error message about nature of error
    private String message;
    //Specific errors in API request processing
    private List<String> details;

    public ErrorResponse(String message, List<String> details) {
        super();
        this.message = message;
        this.details = details;
    }

}
