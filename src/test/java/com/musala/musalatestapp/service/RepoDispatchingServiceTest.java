package com.musala.musalatestapp.service;

import com.musala.musalatestapp.domain.client.DroneClient;
import com.musala.musalatestapp.domain.drone.Drone;
import com.musala.musalatestapp.domain.drone.DroneModel;
import com.musala.musalatestapp.domain.drone.State;
import com.musala.musalatestapp.domain.exceptions.InvalidStateException;
import com.musala.musalatestapp.domain.general.DeliverRequest;
import com.musala.musalatestapp.domain.medication.Medication;
import com.musala.musalatestapp.domain.repository.DroneRepository;
import com.musala.musalatestapp.domain.repository.MedicationRepository;
import com.musala.musalatestapp.service.impl.RepoDispatchingService;
import org.apache.commons.compress.utils.Lists;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RepoDispatchingServiceTest {

    private static final String SERIAL_NUM_TEST = "serialNum";

    @Mock
    private DroneRepository droneRepository;
    @Mock
    private MedicationRepository medicationRepository;
    @Mock
    private DroneClient droneClient;

    @InjectMocks
    private RepoDispatchingService dispatchingService;

    @Test
    void loadMedicationSuccessfully() {
        Drone drone = new Drone(SERIAL_NUM_TEST, DroneModel.CRUISERWEIGHT, 10.0, 100, State.IDLE, Lists.newArrayList());
        Medication medication1 = new Medication(1L, "name1", 5.0, "code1", "image1");
        Medication medication2 = new Medication(2L, "name2", 4.0, "code2", "image2");

        mockDroneRepo(drone);
        mockMedicationRepo(List.of(medication1, medication2));

        dispatchingService.loadMedications(SERIAL_NUM_TEST, List.of(1L, 2L));

        Drone expectedDrone = new Drone(SERIAL_NUM_TEST, DroneModel.CRUISERWEIGHT, 10.0, 100, State.LOADING, List.of(medication1, medication2));
        verify(droneRepository).save(expectedDrone);
    }

    @Test
    void loadMedicationShouldBeAppliedOnlyForLoadingAndIdleStatuses() {
        Drone drone = new Drone(SERIAL_NUM_TEST, DroneModel.CRUISERWEIGHT, 10.0, 100, State.LOADED, Lists.newArrayList());

        mockDroneRepo(drone);
        assertThrows(InvalidStateException.class, () -> dispatchingService.loadMedications(SERIAL_NUM_TEST, List.of(1L, 2L)));

        verify(droneRepository, never()).save(any());
    }

    @Test
    void loadMedicationShouldBeAppliedOnlyWhenBatteryMoreThan25() {
        Drone drone = new Drone(SERIAL_NUM_TEST, DroneModel.CRUISERWEIGHT, 10.0, 24, State.LOADED, Lists.newArrayList());
        mockDroneRepo(drone);

        assertThrows(InvalidStateException.class, () -> dispatchingService.loadMedications(SERIAL_NUM_TEST, List.of(1L, 2L)));

        verify(droneRepository, never()).save(any());
    }

    @Test
    void prepareToDeliverSuccessfully() {
        Medication medication1 = new Medication(1L, "name1", 5.0, "code1", "image1");
        Medication medication2 = new Medication(2L, "name2", 4.0, "code2", "image2");
        Drone drone = new Drone(SERIAL_NUM_TEST, DroneModel.CRUISERWEIGHT, 10.0, 100, State.LOADING, List.of(medication1, medication2));

        mockDroneRepo(drone);

        dispatchingService.prepareToDeliver(SERIAL_NUM_TEST);

        Drone expectedDrone = new Drone(SERIAL_NUM_TEST, DroneModel.CRUISERWEIGHT, 10.0, 100, State.LOADED, List.of(medication1, medication2));
        verify(droneRepository).save(expectedDrone);
    }

    @Test
    void prepareForDeliverShouldBeAppliedOnlyForLoadingStatus() {
        Drone drone = new Drone(SERIAL_NUM_TEST, DroneModel.CRUISERWEIGHT, 10.0, 100, State.IDLE, List.of());

        mockDroneRepo(drone);

        assertThrows(InvalidStateException.class, () -> dispatchingService.prepareToDeliver(SERIAL_NUM_TEST));

        verify(droneRepository, never()).save(any());
    }

    @Test
    void prepareForDeliverShouldBeAppliedWhenAvailableWeightMoreThanZero() {
        Medication medication1 = new Medication(1L, "name1", 5.0, "code1", "image1");
        Medication medication2 = new Medication(2L, "name2", 6.0, "code2", "image2");
        Drone overloadedDrone = new Drone(SERIAL_NUM_TEST, DroneModel.CRUISERWEIGHT, 10.0, 100, State.LOADING, List.of(medication1, medication2));

        mockDroneRepo(overloadedDrone);

        assertThrows(InvalidStateException.class, () -> dispatchingService.prepareToDeliver(SERIAL_NUM_TEST));

        verify(droneRepository, never()).save(any());
    }

    @Test
    void startDeliveringSuccessfully() {
        Medication medication1 = new Medication(1L, "name1", 5.0, "code1", "image1");
        Medication medication2 = new Medication(2L, "name2", 4.0, "code2", "image2");
        Drone drone = new Drone(SERIAL_NUM_TEST, DroneModel.CRUISERWEIGHT, 9.0, 100, State.LOADED, List.of(medication1, medication2));

        mockDroneRepo(drone);

        dispatchingService.startDelivering(SERIAL_NUM_TEST);

        DeliverRequest expectedRequest = DeliverRequest.of(SERIAL_NUM_TEST, List.of(medication1, medication2));
        verify(droneClient).sendDroneToDeliver(expectedRequest);
        Drone expectedDrone = new Drone(SERIAL_NUM_TEST, DroneModel.CRUISERWEIGHT, 9.0, 100, State.DELIVERING, List.of(medication1, medication2));
        verify(droneRepository).save(expectedDrone);
    }

    @Test
    void startDeliveringShouldBeAppliedOnlyWhenStatusLoaded() {
        Medication medication1 = new Medication(1L, "name1", 5.0, "code1", "image1");
        Medication medication2 = new Medication(2L, "name2", 4.0, "code2", "image2");
        Drone drone = new Drone(SERIAL_NUM_TEST, DroneModel.CRUISERWEIGHT, 9.0, 100, State.LOADING, List.of(medication1, medication2));

        mockDroneRepo(drone);

        assertThrows(InvalidStateException.class, () -> dispatchingService.startDelivering(SERIAL_NUM_TEST));

        verify(droneRepository, never()).save(any());
    }

    private void mockDroneRepo(Drone drone) {
        when(droneRepository.getDrone(SERIAL_NUM_TEST)).thenReturn(drone);
    }

    private void mockMedicationRepo(List<Medication> medications) {
        List<Long> ids = medications.stream().map(Medication::getId).toList();
        when(medicationRepository.getByIds(ids)).thenReturn(medications);
    }
}
