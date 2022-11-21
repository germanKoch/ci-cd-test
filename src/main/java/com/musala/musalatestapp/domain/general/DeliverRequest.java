package com.musala.musalatestapp.domain.general;

import com.musala.musalatestapp.domain.medication.Medication;
import lombok.Data;

import java.util.List;

@Data(staticConstructor = "of")
public class DeliverRequest {

    private final String droneId;
    private final List<Medication> medications;
}
