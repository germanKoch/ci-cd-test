package com.german.ci.service;

import com.german.ci.domain.general.PageRequest;
import com.german.ci.domain.drone.Drone;
import org.springframework.data.domain.Page;

public interface DroneService {
    Drone create(Drone drone);

    Drone update(Drone drone);

    Drone get(String id);

    Page<Drone> get(PageRequest request);
}
