package com.musala.musalatestapp.domain.drone;

import com.musala.musalatestapp.domain.medication.Medication;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
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
