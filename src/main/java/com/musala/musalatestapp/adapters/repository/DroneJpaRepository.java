package com.musala.musalatestapp.adapters.repository;

import com.musala.musalatestapp.adapters.repository.entity.DroneEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DroneJpaRepository extends JpaRepository<DroneEntity, String> {
}
