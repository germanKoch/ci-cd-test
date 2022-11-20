package com.musala.musalatestapp.domain.medication;

import lombok.Data;

@Data
public class Medication {
    private Long id;
    private String name;
    private Double weight;
    private String code;
    private String imageLink;
}
