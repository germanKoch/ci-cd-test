package com.musala.musalatestapp.domain.repository;

import com.musala.musalatestapp.domain.general.PageRequest;
import com.musala.musalatestapp.domain.medication.Medication;
import org.springframework.data.domain.Page;

import java.util.List;

public interface MedicationRepository {

    List<Medication> getByIds(List<Long> ids);

    Medication save(Medication medication);

    Medication get(Long id);

    Page<Medication> get(PageRequest request);
}
