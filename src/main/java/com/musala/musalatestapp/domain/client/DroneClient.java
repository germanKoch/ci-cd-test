package com.musala.musalatestapp.domain.client;

import com.musala.musalatestapp.domain.drone.Drone;
import com.musala.musalatestapp.domain.general.DeliverRequest;

public interface DroneClient {

    void sendDroneToDeliver(DeliverRequest request);
    Integer checkDroneBattery(Drone drone);
}
