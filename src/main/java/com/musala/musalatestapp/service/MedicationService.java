package com.musala.musalatestapp.service;

import com.musala.musalatestapp.domain.general.PageRequest;
import com.musala.musalatestapp.domain.medication.Medication;
import org.springframework.data.domain.Page;

public interface MedicationService {
    Medication create(Medication medication);

    Medication update(Medication medication);

    Medication get(Long id);

    Page<Medication> get(PageRequest request);
}
