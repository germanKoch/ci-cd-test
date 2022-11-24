package com.german.ci.adapters.controller;

import com.german.ci.adapters.controller.mappers.DroneResourceMapper;
import com.german.ci.adapters.controller.resource.drone.CreateDroneRequest;
import com.german.ci.adapters.controller.resource.drone.DroneResource;
import com.german.ci.domain.drone.Drone;
import com.german.ci.domain.general.PageRequest;
import com.german.ci.service.impl.RepoDroneService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/drone")
@RequiredArgsConstructor
//TODO: logs
public class DroneController {

    private final DroneResourceMapper mapper;
    private final RepoDroneService service;

    @PostMapping
    public ResponseEntity<DroneResource> createDrone(@RequestBody CreateDroneRequest resource) {
        Drone result = service.create(mapper.map(resource));
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.map(result));
    }

    @PatchMapping
    public ResponseEntity<DroneResource> updateDrone(@RequestBody DroneResource resource) {
        Drone result = service.update(mapper.map(resource));
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(mapper.map(result));
    }

    @GetMapping
    public ResponseEntity<Page<DroneResource>> getDrones(@RequestParam(defaultValue = "10") Integer size, @RequestParam(defaultValue = "0") Integer page) {
        Page<DroneResource> result = service.get(PageRequest.of(size, page)).map(mapper::map);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DroneResource> getDrone(@PathVariable String id) {
        Drone result = service.get(id);
        return ResponseEntity.status(HttpStatus.OK).body(mapper.map(result));
    }
}
