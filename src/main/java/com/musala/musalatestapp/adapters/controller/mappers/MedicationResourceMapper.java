package com.musala.musalatestapp.adapters.controller.mappers;

import com.musala.musalatestapp.adapters.controller.resource.medication.CreateMedicationRequest;
import com.musala.musalatestapp.adapters.controller.resource.medication.MedicationResource;
import com.musala.musalatestapp.config.mappers.DefaultMapperConfig;
import com.musala.musalatestapp.domain.medication.Medication;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
        config = DefaultMapperConfig.class
)
public interface MedicationResourceMapper {

    Medication map(MedicationResource resource);

    MedicationResource map(Medication medication);

    @Mapping(target = "id", ignore = true)
    Medication map(CreateMedicationRequest request);

}
