package com.musala.musalatestapp.adapters.controller.resource.medication;

import lombok.Data;

@Data
public class CreateMedicationRequest {
    private String name;
    private Double weight;
    private String code;
    private String imageLink;
}
