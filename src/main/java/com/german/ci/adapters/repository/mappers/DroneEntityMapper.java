package com.german.ci.adapters.repository.mappers;

import com.german.ci.adapters.repository.entity.DroneEntity;
import com.german.ci.domain.drone.Drone;
import com.german.ci.config.mappers.DefaultMapperConfig;
import org.mapstruct.Mapper;

@Mapper(
        config = DefaultMapperConfig.class,
        uses = {MedicationEntityMapper.class}
)
public interface DroneEntityMapper {

    Drone map(DroneEntity droneEntity);

    DroneEntity map(Drone drone);

}
