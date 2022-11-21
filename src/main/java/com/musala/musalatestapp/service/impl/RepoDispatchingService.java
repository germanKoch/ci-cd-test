package com.musala.musalatestapp.service.impl;

import com.musala.musalatestapp.domain.client.DroneClient;
import com.musala.musalatestapp.domain.drone.Drone;
import com.musala.musalatestapp.domain.drone.DroneAction;
import com.musala.musalatestapp.domain.drone.State;
import com.musala.musalatestapp.domain.exceptions.InvalidStateException;
import com.musala.musalatestapp.domain.general.DeliverRequest;
import com.musala.musalatestapp.domain.medication.Medication;
import com.musala.musalatestapp.domain.repository.DroneRepository;
import com.musala.musalatestapp.domain.repository.MedicationRepository;
import com.musala.musalatestapp.service.DroneDispatchingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RepoDispatchingService implements DroneDispatchingService {

    private final DroneRepository droneRepository;
    private final MedicationRepository medicationRepository;
    private final DroneClient droneClient;

    @Override
    public void loadMedications(String droneId, List<Long> medicationListId) {
        Drone drone = droneRepository.getDrone(droneId);

        if (!DroneAction.LOAD.isAvailableOnState(drone.getState())) {
            throw new InvalidStateException("Drone could not be loaded in state " + drone.getState());
        }
        if (drone.getBatteryCapacity() < 25) {
            throw new InvalidStateException("Drone battery level below 25");
        }

        List<Medication> medications = medicationRepository.getByIds(medicationListId);
        drone.setItems(new ArrayList<>(medications));
        drone.setState(State.LOADING);
        droneRepository.save(drone);
    }

    @Override
    public void prepareToDeliver(String droneId) {
        Drone drone = droneRepository.getDrone(droneId);

        if (!DroneAction.PREPARE_TO_DELIVER.isAvailableOnState(drone.getState())) {
            throw new InvalidStateException("Drone could not be prepared to deliver in state " + drone.getState());
        }
        if (drone.getAvailableWeight() <= 0) {
            throw new InvalidStateException("The weight of medications is too big.");
        }

        drone.setState(State.LOADED);
        droneRepository.save(drone);
    }

    @Override
    public void startDelivering(String droneId) {
        Drone drone = droneRepository.getDrone(droneId);

        if (!DroneAction.SEND_TO_DELIVER.isAvailableOnState(drone.getState())) {
            throw new InvalidStateException("Drone could not be sent to deliver in state " + drone.getState());
        }

        drone.setState(State.DELIVERING);
        droneClient.sendDroneToDeliver(DeliverRequest.of(droneId, drone.getItems()));
        droneRepository.save(drone);
    }
}
