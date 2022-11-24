package com.german.ci.adapters.controller.mappers;

import com.german.ci.adapters.controller.resource.drone.CreateDroneRequest;
import com.german.ci.adapters.controller.resource.drone.DroneResource;
import com.german.ci.domain.drone.Drone;
import com.german.ci.config.mappers.DefaultMapperConfig;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
        config = DefaultMapperConfig.class,
        uses = {MedicationResourceMapper.class}
)
public interface DroneResourceMapper {

    Drone map(DroneResource resource);

    @Mapping(target = "items", ignore = true)
    Drone map(CreateDroneRequest request);

    DroneResource map(Drone resource);

}
