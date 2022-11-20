package com.musala.musalatestapp.adapters.repository.mappers;

import com.musala.musalatestapp.adapters.repository.entity.DroneEntity;
import com.musala.musalatestapp.config.mappers.DefaultMapperConfig;
import com.musala.musalatestapp.domain.drone.Drone;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(
        config = DefaultMapperConfig.class,
        uses = {MedicationEntityMapper.class}
)
public interface DroneEntityMapper {

    Drone map(DroneEntity droneEntity);

    DroneEntity map(Drone drone);

}
