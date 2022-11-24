package com.german.ci.domain.client;

import com.german.ci.domain.drone.Drone;
import com.german.ci.domain.general.DeliverRequest;

public interface DroneClient {

    void sendDroneToDeliver(DeliverRequest request);
    Integer checkDroneBattery(Drone drone);
}
