package com.german.ci.domain.drone;

import java.util.List;

public enum DroneAction {
    LOAD(List.of(State.IDLE, State.LOADING)),
    PREPARE_TO_DELIVER(List.of(State.LOADING)),
    SEND_TO_DELIVER(List.of(State.LOADED)),
    ;

    private final List<State> availableInState;

    DroneAction(List<State> availableInStates) {
        this.availableInState = availableInStates;
    }

    public boolean isAvailableOnState(State state) {
        return availableInState.contains(state);
    }
}
