package com.musala.musalatestapp.domain.client;

import com.musala.musalatestapp.domain.drone.Drone;
import com.musala.musalatestapp.domain.medication.Medication;

import java.util.List;

public interface DroneClient {

    void loadDrone(Drone drone, List<Medication> medications);

    void prepareToDeliver(Drone drone);

    void deliver(Drone drone);

    Integer checkDroneBattery(Drone drone);
}
