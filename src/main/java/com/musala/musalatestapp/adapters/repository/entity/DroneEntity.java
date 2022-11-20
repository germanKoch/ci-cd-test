package com.musala.musalatestapp.adapters.repository.entity;

import com.musala.musalatestapp.domain.drone.DroneModel;
import com.musala.musalatestapp.domain.drone.State;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@Table(name = "drone")
@EqualsAndHashCode(of = "serialNumber")
public class DroneEntity {
    @Id
    private String serialNumber;
    @Enumerated(EnumType.STRING)
    private DroneModel model;
    private Double weightLimit;
    private Integer batteryCapacity;
    @Enumerated(EnumType.STRING)
    private State state;
    @ManyToMany
    @JoinTable(
            name = "drone_medication",
            joinColumns = @JoinColumn(name = "drone_id"),
            inverseJoinColumns = @JoinColumn(name = "medication_id")
    )
    private List<MedicationEntity> items;
}
