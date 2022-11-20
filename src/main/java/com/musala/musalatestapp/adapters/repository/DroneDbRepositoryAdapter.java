package com.musala.musalatestapp.adapters.repository;

import com.musala.musalatestapp.adapters.repository.entity.DroneEntity;
import com.musala.musalatestapp.adapters.repository.mappers.DroneEntityMapper;
import com.musala.musalatestapp.domain.drone.Drone;
import com.musala.musalatestapp.domain.exceptions.NotFoundException;
import com.musala.musalatestapp.domain.general.PageRequest;
import com.musala.musalatestapp.domain.repository.DroneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component
@RequiredArgsConstructor
@Transactional
public class DroneDbRepositoryAdapter implements DroneRepository {

    private final DroneJpaRepository repository;
    private final DroneEntityMapper mapper;

    @Override
    public Drone save(Drone drone) {
        return mapper.map(
                repository.save(
                        mapper.map(drone)
                )
        );
    }

    @Override
    public Page<Drone> getDrones(PageRequest request) {
        return repository
                .findAll(org.springframework.data.domain.PageRequest.of(request.getPage(), request.getSize()))
                .map(mapper::map);
    }

    @Override
    public Drone getDrone(String id) {
        return repository.findById(id).map(mapper::map).orElseThrow(() -> new NotFoundException("Drone not found"));
    }

    @Override
    public void saveAll(List<Drone> drones) {
        List<DroneEntity> droneEntities = drones.stream().map(mapper::map).toList();
        repository.saveAll(droneEntities);
    }

}
