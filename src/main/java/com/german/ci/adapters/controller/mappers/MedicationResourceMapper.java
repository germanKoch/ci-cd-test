package com.german.ci.adapters.controller.mappers;

import com.german.ci.adapters.controller.resource.medication.CreateMedicationRequest;
import com.german.ci.adapters.controller.resource.medication.MedicationResource;
import com.german.ci.domain.medication.Medication;
import com.german.ci.config.mappers.DefaultMapperConfig;
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
