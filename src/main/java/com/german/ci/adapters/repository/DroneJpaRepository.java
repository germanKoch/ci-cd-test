package com.german.ci.adapters.repository;

import com.german.ci.adapters.repository.entity.DroneEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DroneJpaRepository extends JpaRepository<DroneEntity, String> {
}
