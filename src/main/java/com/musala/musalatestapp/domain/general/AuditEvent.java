package com.musala.musalatestapp.domain.general;

import com.musala.musalatestapp.domain.drone.State;
import lombok.Data;

@Data(staticConstructor = "of")
public class AuditEvent {
    private final String droneId;
    private final Integer batteryCapacity;
    private final State state;
}
