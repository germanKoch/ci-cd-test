package com.musala.musalatestapp.service;

import java.util.List;

public interface DroneDispatchingService {

    void loadMedications(String droneId, List<Long> medicationList);

    void prepareToDeliver(String droneId);

    void startDelivering(String droneId);
}
