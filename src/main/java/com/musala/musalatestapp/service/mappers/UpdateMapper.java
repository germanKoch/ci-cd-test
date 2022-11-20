package com.musala.musalatestapp.service.mappers;

import com.musala.musalatestapp.config.mappers.DefaultMapperConfig;
import com.musala.musalatestapp.domain.drone.Drone;
import com.musala.musalatestapp.domain.medication.Medication;
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
