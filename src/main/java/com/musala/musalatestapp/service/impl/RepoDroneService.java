package com.musala.musalatestapp.service.impl;

import com.musala.musalatestapp.domain.drone.Drone;
import com.musala.musalatestapp.domain.drone.State;
import com.musala.musalatestapp.domain.exceptions.InvalidRequestException;
import com.musala.musalatestapp.domain.general.PageRequest;
import com.musala.musalatestapp.domain.repository.DroneRepository;
import com.musala.musalatestapp.service.DroneService;
import com.musala.musalatestapp.service.mappers.UpdateMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class RepoDroneService implements DroneService {

    private final DroneRepository repository;
    private final UpdateMapper mapperToUpdate;

    @Override
    public Drone create(Drone drone) {
        drone.setItems(new ArrayList<>());
        drone.setState(State.IDLE);

        validateDrone(drone);
        return repository.save(drone);
    }

    @Override
    public Drone update(Drone drone) {
        Drone droneToUpdate = repository.getDrone(drone.getSerialNumber());
        mapperToUpdate.update(drone, droneToUpdate);
        validateDrone(droneToUpdate);

        return repository.save(droneToUpdate);
    }

    @Override
    public Drone get(String id) {
        return repository.getDrone(id);
    }


    @Override
    public Page<Drone> get(PageRequest request) {
        return repository.getDrones(request);
    }

    private void validateDrone(Drone drone) {
        StringBuilder errors = new StringBuilder();
        if (drone.getWeightLimit() == null || drone.getWeightLimit() < 0) {
            errors.append("Drone weight limit should be a positive number. \n");
        }
        if (drone.getBatteryCapacity() == null || drone.getBatteryCapacity() > 100 || drone.getBatteryCapacity() < 0) {
            errors.append("Drone battery capacity should be a number between 0 and 100. \n");
        }
        if (drone.getSerialNumber() == null || drone.getSerialNumber().length() > 100) {
            errors.append("Drone serial number should have a size between 0 and 100");
        }
        if (!errors.isEmpty()) {
            throw new InvalidRequestException(errors.toString());
        }
    }

}
