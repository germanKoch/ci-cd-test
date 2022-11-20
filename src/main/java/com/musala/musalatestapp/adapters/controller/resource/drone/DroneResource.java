package com.musala.musalatestapp.adapters.controller.resource.drone;

import com.musala.musalatestapp.adapters.controller.resource.medication.MedicationResource;
import com.musala.musalatestapp.domain.drone.DroneModel;
import com.musala.musalatestapp.domain.drone.State;
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
