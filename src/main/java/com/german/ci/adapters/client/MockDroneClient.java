package com.german.ci.adapters.client;

import com.german.ci.domain.client.DroneClient;
import com.german.ci.domain.drone.Drone;
import com.german.ci.domain.general.DeliverRequest;
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
