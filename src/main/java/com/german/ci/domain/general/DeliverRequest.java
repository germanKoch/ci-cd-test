package com.german.ci.domain.general;

import com.german.ci.domain.medication.Medication;
import lombok.Data;

import java.util.List;

@Data(staticConstructor = "of")
public class DeliverRequest {

    private final String droneId;
    private final List<Medication> medications;
}
