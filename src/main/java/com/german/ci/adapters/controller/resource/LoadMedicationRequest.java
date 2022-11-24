package com.german.ci.adapters.controller.resource;

import lombok.Data;

import java.util.List;

@Data
public class LoadMedicationRequest {
    private String droneId;
    private List<Long> medicationIds;

}
