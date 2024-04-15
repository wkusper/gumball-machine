package edu.iu.habahram.GumballMachine.model;

public interface IState {
    TransitionResult insertQuarter();
    TransitionResult ejectQuarter();
    TransitionResult turnCrank();
    TransitionResult dispense();
    TransitionResult refill();
    String getTheName();
}
