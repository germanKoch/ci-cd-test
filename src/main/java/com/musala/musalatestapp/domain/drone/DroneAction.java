package com.musala.musalatestapp.domain.drone;

import java.util.List;

import static com.musala.musalatestapp.domain.drone.State.IDLE;
import static com.musala.musalatestapp.domain.drone.State.LOADED;
import static com.musala.musalatestapp.domain.drone.State.LOADING;

public enum DroneAction {
    LOAD(List.of(IDLE, LOADING)),
    PREPARE_TO_DELIVER(List.of(LOADING)),
    SEND_TO_DELIVER(List.of(LOADED)),
    ;

    private final List<State> availableInState;

    DroneAction(List<State> availableInStates) {
        this.availableInState = availableInStates;
    }

    public boolean isAvailableOnState(State state) {
        return availableInState.contains(state);
    }
}
