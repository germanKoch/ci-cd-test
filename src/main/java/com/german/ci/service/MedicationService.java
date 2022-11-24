package com.german.ci.service;

import com.german.ci.domain.general.PageRequest;
import com.german.ci.domain.medication.Medication;
import org.springframework.data.domain.Page;

public interface MedicationService {
    Medication create(Medication medication);

    Medication update(Medication medication);

    Medication get(Long id);

    Page<Medication> get(PageRequest request);
}
