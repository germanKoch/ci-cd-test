package com.german.ci.adapters.controller.resource.medication;

import lombok.Data;

@Data
public class MedicationResource {
    private Long id;
    private String name;
    private Double weight;
    private String code;
    private String imageLink;
}
