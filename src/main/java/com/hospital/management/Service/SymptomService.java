package com.hospital.management.Service;

import com.hospital.management.Enum.Symptoms;

import java.util.Map;

public interface SymptomService {

    Map<String, Object> addSymptoms(Symptoms symptoms, long specialityId);

}
