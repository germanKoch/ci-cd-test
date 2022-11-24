package com.german.ci.adapters.repository.mappers;

import com.german.ci.adapters.repository.entity.MedicationEntity;
import com.german.ci.domain.medication.Medication;
import com.german.ci.config.mappers.DefaultMapperConfig;
import org.mapstruct.Mapper;

@Mapper(
        config = DefaultMapperConfig.class
)
public interface MedicationEntityMapper {

    MedicationEntity map(Medication medication);

    Medication map(MedicationEntity medication);

}
