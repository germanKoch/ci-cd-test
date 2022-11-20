package com.musala.musalatestapp.adapters.controller;

import com.musala.musalatestapp.adapters.controller.mappers.MedicationResourceMapper;
import com.musala.musalatestapp.adapters.controller.resource.medication.CreateMedicationRequest;
import com.musala.musalatestapp.adapters.controller.resource.medication.MedicationResource;
import com.musala.musalatestapp.domain.general.PageRequest;
import com.musala.musalatestapp.domain.medication.Medication;
import com.musala.musalatestapp.service.impl.RepoMedicationService;
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
@RequestMapping("/v1/medication")
@RequiredArgsConstructor
public class MedicationController {

    private final MedicationResourceMapper mapper;
    private final RepoMedicationService service;

    @PostMapping
    public ResponseEntity<MedicationResource> createMedication(@RequestBody CreateMedicationRequest resource) {
        Medication result = service.create(mapper.map(resource));
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.map(result));
    }

    @PatchMapping
    public ResponseEntity<MedicationResource> updateDrone(@RequestBody  CreateMedicationRequest resource) {
        Medication result = service.update(mapper.map(resource));
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(mapper.map(result));
    }

    @GetMapping
    public ResponseEntity<Page<MedicationResource>> getDrones(@RequestParam(defaultValue = "10") Integer size, @RequestParam(defaultValue = "0") Integer page) {
        Page<MedicationResource> result = service.get(PageRequest.of(size, page)).map(mapper::map);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicationResource> getDrone(@PathVariable Long id) {
        Medication result = service.get(id);
        return ResponseEntity.status(HttpStatus.OK).body(mapper.map(result));
    }

}
