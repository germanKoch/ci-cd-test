package com.musala.musalatestapp.adapters.controller.resource.drone;

import com.musala.musalatestapp.domain.drone.DroneModel;
import com.musala.musalatestapp.domain.drone.State;
import lombok.Data;

@Data
public class CreateDroneRequest {
    private String serialNumber;
    private DroneModel model;
    private Double weightLimit;
    private Integer batteryCapacity;
    private State state;
}
