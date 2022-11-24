package com.german.ci.domain.repository;

import com.german.ci.domain.drone.Drone;
import com.german.ci.domain.general.PageRequest;
import org.springframework.data.domain.Page;

import java.util.List;

public interface DroneRepository {

    Drone save(Drone drone);

    Page<Drone> getDrones(PageRequest request);

    Drone getDrone(String id);

    void saveAll(List<Drone> drones);

}
