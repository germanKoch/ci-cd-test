package com.german.ci.domain.repository;

import com.german.ci.domain.general.PageRequest;
import com.german.ci.domain.medication.Medication;
import org.springframework.data.domain.Page;

import java.util.List;

public interface MedicationRepository {

    List<Medication> getByIds(List<Long> ids);

    Medication save(Medication medication);

    Medication get(Long id);

    Page<Medication> get(PageRequest request);
}
