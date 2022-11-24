package com.german.ci.adapters.controller.resource.drone;

import com.german.ci.domain.drone.State;
import com.german.ci.adapters.controller.resource.medication.MedicationResource;
import com.german.ci.domain.drone.DroneModel;
import lombok.Data;

import java.util.List;

@Data
public class DroneResource {
    private String serialNumber;
    private DroneModel model;
    private Double weightLimit;
    private Integer batteryCapacity;
    private State state;
    private List<MedicationResource> items;
}
