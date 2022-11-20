package com.musala.musalatestapp.adapters.client;

import com.musala.musalatestapp.domain.client.DroneClient;
import com.musala.musalatestapp.domain.drone.Drone;
import com.musala.musalatestapp.domain.medication.Medication;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
/**
 * Mock drone client. It's assumed that we have a third party api to work with drones.
 */
public class MockDroneClient implements DroneClient {
    @Override
    public void loadDrone(Drone drone, List<Medication> medications) {

    }

    @Override
    public void prepareToDeliver(Drone drone) {

    }

    @Override
    public void deliver(Drone drone) {

    }

    @Override
    public Integer checkDroneBattery(Drone drone) {
        return 100;
    }
}
