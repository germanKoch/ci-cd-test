package com.musala.musalatestapp.service.impl;

import com.musala.musalatestapp.domain.client.AuditClient;
import com.musala.musalatestapp.domain.client.DroneClient;
import com.musala.musalatestapp.domain.drone.Drone;
import com.musala.musalatestapp.domain.general.AuditEvent;
import com.musala.musalatestapp.domain.general.PageRequest;
import com.musala.musalatestapp.domain.repository.DroneRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class DroneMonitoringService {

    public static final Integer CHUNK_SIZE = 10;

    private final DroneClient droneClient;
    private final AuditClient auditClient;
    private final DroneRepository droneRepository;

    @Scheduled(fixedDelay = 60000)
    public void updateBatteries() {
        try {
            boolean hasMore = true;
            int currentPage = 0;

            while (hasMore) {
                Page<Drone> drones = droneRepository.getDrones(PageRequest.of(CHUNK_SIZE, currentPage));
                List<Drone> updatedDrones = drones.stream().peek(drone -> {
                    Integer batteryCapacity = droneClient.checkDroneBattery(drone);
                    drone.setBatteryCapacity(batteryCapacity);
                }).toList();

                droneRepository.saveAll(updatedDrones);
                updatedDrones.forEach(drone -> auditClient.sendAuditEvent(
                        AuditEvent.of(drone.getSerialNumber(), drone.getBatteryCapacity(), drone.getState()))
                );

                hasMore = drones.hasNext();
                currentPage += 1;
            }
        } catch (Exception e) {
            log.error("Error occurred while drones monitoring", e);
        }
    }

}
