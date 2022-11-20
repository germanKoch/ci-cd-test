package com.musala.musalatestapp.service.impl;

import com.musala.musalatestapp.domain.client.DroneClient;
import com.musala.musalatestapp.domain.drone.Drone;
import com.musala.musalatestapp.domain.general.PageRequest;
import com.musala.musalatestapp.domain.repository.DroneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DroneMonitoringService {

    public static final Integer CHUNK_SIZE = 10;

    private final DroneClient droneClient;
    private final DroneRepository droneRepository;

    @Scheduled(fixedDelay = 60000)
    public void updateBatteries() {
        boolean hasMore = true;
        int currentPage = 0;

        while (hasMore) {
            Page<Drone> drones = droneRepository.getDrones(PageRequest.of(CHUNK_SIZE, currentPage));
            List<Drone> updatedDrones = drones.stream().peek(drone -> {
                Integer batteryCapacity = droneClient.checkDroneBattery(drone);
                drone.setBatteryCapacity(batteryCapacity);
            }).toList();
            droneRepository.saveAll(updatedDrones);

            hasMore = drones.hasNext();
            currentPage += 1;
        }
    }

}
