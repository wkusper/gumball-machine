package edu.iu.habahram.GumballMachine.model;

import java.io.IOException;

public interface IGumballMachine {
    TransitionResult insertQuarter();
    TransitionResult ejectQuarter() throws IOException;
    TransitionResult turnCrank() throws IOException;
    void changeTheStateTo(GumballMachineState name);
    Integer getCount();
    String getTheStateName();

    void releaseBall();
}
