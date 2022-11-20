package com.musala.musalatestapp.adapters.controller;

import com.musala.musalatestapp.adapters.controller.resource.LoadMedicationRequest;
import com.musala.musalatestapp.adapters.controller.resource.drone.DroneResource;
import com.musala.musalatestapp.service.impl.RepoDispatchingService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/drone/")
@RequiredArgsConstructor
public class DispatchController {

    private final RepoDispatchingService service;

    @PutMapping("/load")
    public ResponseEntity<DroneResource> loadDrone(@RequestBody LoadMedicationRequest request) {
        service.loadMedications(request.getDroneId(), request.getMedicationIds());
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @PutMapping("/prepare-to-deliver")
    public ResponseEntity<DroneResource> prepareToDeliver(@RequestParam String droneId) {
        service.prepareToDeliver(droneId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/deliver")
    public ResponseEntity<Page<DroneResource>> sendToDeliver(@RequestParam String droneId) {
        service.sendToDeliver(droneId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
