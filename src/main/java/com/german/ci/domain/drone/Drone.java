package com.german.ci.domain.drone;

import com.german.ci.domain.medication.Medication;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Drone {

    private String serialNumber;
    private DroneModel model;
    private Double weightLimit;
    private Integer batteryCapacity;
    private State state;
    private List<Medication> items = new ArrayList<>();

    public Double getAvailableWeight() {
        Double medicationWeight = items.stream().map(Medication::getWeight).reduce(Double::sum).orElse(0.0);
        return weightLimit - medicationWeight;
    }

}
