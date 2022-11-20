package com.musala.musalatestapp.adapters.repository.mappers;

import com.musala.musalatestapp.adapters.repository.entity.MedicationEntity;
import com.musala.musalatestapp.config.mappers.DefaultMapperConfig;
import com.musala.musalatestapp.domain.medication.Medication;
import org.mapstruct.Mapper;

@Mapper(
        config = DefaultMapperConfig.class
)
public interface MedicationEntityMapper {

    MedicationEntity map(Medication medication);

    Medication map(MedicationEntity medication);

}
