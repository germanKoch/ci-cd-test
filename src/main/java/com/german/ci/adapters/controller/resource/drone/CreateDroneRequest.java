package com.german.ci.adapters.controller.resource.drone;

import com.german.ci.domain.drone.State;
import com.german.ci.domain.drone.DroneModel;
import lombok.Data;

@Data
public class CreateDroneRequest {
    private String serialNumber;
    private DroneModel model;
    private Double weightLimit;
    private Integer batteryCapacity;
    private State state;
}
