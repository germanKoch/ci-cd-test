package com.german.ci.service.mappers;

import com.german.ci.domain.medication.Medication;
import com.german.ci.config.mappers.DefaultMapperConfig;
import com.german.ci.domain.drone.Drone;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(
        config = DefaultMapperConfig.class
)
public interface UpdateMapper {

    @Mapping(target = "items", ignore = true)
    void update(Drone from, @MappingTarget Drone to);

    void update(Medication from, @MappingTarget Medication to);
}
