package edu.iu.habahram.GumballMachine.model;

public record TransitionResult(boolean succeeded, String message, String stateAfter, Integer countAfter) {
}
