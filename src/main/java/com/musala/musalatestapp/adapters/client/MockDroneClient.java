package com.musala.musalatestapp.adapters.client;

import com.musala.musalatestapp.domain.client.DroneClient;
import com.musala.musalatestapp.domain.drone.Drone;
import com.musala.musalatestapp.domain.general.DeliverRequest;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Component
@Log4j2
/**
 * Mock drone client. It's assumed that we have a third party api to work with drones.
 */
public class MockDroneClient implements DroneClient {
    @Override
    public void sendDroneToDeliver(DeliverRequest request) {
        log.info("Request to deliver: {}", request);
    }

    @Override
    public Integer checkDroneBattery(Drone drone) {
        return 100;
    }
}
