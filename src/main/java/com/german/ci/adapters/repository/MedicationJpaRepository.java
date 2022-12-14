package com.german.ci.adapters.repository;

import com.german.ci.adapters.repository.entity.MedicationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicationJpaRepository extends JpaRepository<MedicationEntity, Long> {

    List<MedicationEntity> findByIdIn(List<Long> ids);

}
