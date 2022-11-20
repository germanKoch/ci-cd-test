package com.musala.musalatestapp.service.impl;

import com.musala.musalatestapp.domain.exceptions.InvalidRequestException;
import com.musala.musalatestapp.domain.general.PageRequest;
import com.musala.musalatestapp.domain.medication.Medication;
import com.musala.musalatestapp.domain.repository.MedicationRepository;
import com.musala.musalatestapp.service.MedicationService;
import com.musala.musalatestapp.service.mappers.UpdateMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class RepoMedicationService implements MedicationService {

    private static Pattern NOT_VALID_CHARS_NAME = Pattern.compile("[^0-9a-zA-Z-_]");
    private static Pattern NOT_VALID_CHARS_CODE = Pattern.compile("[^0-9A-Z_]");
    private final MedicationRepository repository;
    private final UpdateMapper mapperToUpdate;

    @Override
    public Medication create(Medication medication) {
        validateMedication(medication);
        return repository.save(medication);
    }

    @Override
    public Medication update(Medication medication) {
        Medication toUpdate = repository.get(medication.getId());
        mapperToUpdate.update(medication, toUpdate);
        validateMedication(toUpdate);

        return repository.save(toUpdate);
    }

    @Override
    public Medication get(Long id) {
        return repository.get(id);
    }


    @Override
    public Page<Medication> get(PageRequest request) {
        return repository.get(request);
    }

    private void validateMedication(Medication medication) {
        StringBuilder errors = new StringBuilder();
        if (medication.getName() == null || NOT_VALID_CHARS_NAME.matcher(medication.getName()).matches()) {
            errors.append("Medication name may contain letters, numbers, '-' and '_'. \n");
        }
        if (medication.getWeight() == null || medication.getWeight() < 0) {
            errors.append("Medication weight should be a positive number. \n");
        }
        if (medication.getCode() == null || NOT_VALID_CHARS_CODE.matcher(medication.getCode()).matches()) {
            errors.append("Medication code may contain upper case letters, underscore and numbers");
        }
        if (!errors.isEmpty()) {
            throw new InvalidRequestException(errors.toString());
        }
    }


}
