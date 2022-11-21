package com.musala.musalatestapp.domain.medication;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Medication {
    private Long id;
    private String name;
    private Double weight;
    private String code;
    private String imageLink;
}
