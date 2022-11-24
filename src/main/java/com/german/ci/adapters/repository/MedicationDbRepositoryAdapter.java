package com.german.ci.adapters.repository;

import com.german.ci.domain.exceptions.NotFoundException;
import com.german.ci.adapters.repository.mappers.MedicationEntityMapper;
import com.german.ci.domain.general.PageRequest;
import com.german.ci.domain.medication.Medication;
import com.german.ci.domain.repository.MedicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MedicationDbRepositoryAdapter implements MedicationRepository {

    private final MedicationJpaRepository repository;
    private final MedicationEntityMapper mapper;

    @Override
    public List<Medication> getByIds(List<Long> ids) {
        return repository.findByIdIn(ids).stream().map(mapper::map).toList();
    }

    @Override
    public Medication save(Medication medication) {
        return mapper.map(
                repository.save(
                        mapper.map(medication)
                )
        );
    }

    @Override
    public Medication get(Long id) {
        return repository.findById(id).map(mapper::map).orElseThrow(() -> new NotFoundException("Medicaton not found"));
    }

    @Override
    public Page<Medication> get(PageRequest request) {
        return repository
                .findAll(org.springframework.data.domain.PageRequest.of(request.getPage(), request.getSize()))
                .map(mapper::map);
    }
}
