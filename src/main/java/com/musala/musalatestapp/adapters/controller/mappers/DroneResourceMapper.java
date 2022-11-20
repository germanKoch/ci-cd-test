package com.musala.musalatestapp.adapters.controller.mappers;

import com.musala.musalatestapp.adapters.controller.resource.drone.CreateDroneRequest;
import com.musala.musalatestapp.adapters.controller.resource.drone.DroneResource;
import com.musala.musalatestapp.config.mappers.DefaultMapperConfig;
import com.musala.musalatestapp.domain.drone.Drone;
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
