package com.german.ci.domain.general;

import com.german.ci.domain.drone.State;
import lombok.Data;

@Data(staticConstructor = "of")
public class AuditEvent {
    private final String droneId;
    private final Integer batteryCapacity;
    private final State state;
}
