package com.musala.musalatestapp.service;

import com.musala.musalatestapp.domain.drone.Drone;
import com.musala.musalatestapp.domain.general.PageRequest;
import org.springframework.data.domain.Page;

public interface DroneService {
    Drone create(Drone drone);

    Drone update(Drone drone);

    Drone get(String id);

    Page<Drone> get(PageRequest request);
}
