package edu.iu.habahram.GumballMachine.model;

public class HasQuarterState implements IState {
    IGumballMachine gumballMachine;

    public HasQuarterState(IGumballMachine gumballMachine) {
        this.gumballMachine = gumballMachine;
    }

    @Override
    public TransitionResult insertQuarter() {
        String message = "You can't insert another quarter";
        boolean succeeded = false;
        int count = gumballMachine.getCount();
        return new TransitionResult(succeeded, message,
                gumballMachine.getTheStateName(), count);
    }

    @Override
    public TransitionResult ejectQuarter() {
        gumballMachine.changeTheStateTo(GumballMachineState.NO_QUARTER);
        String message = "Quarter returned";
        boolean succeeded = true;
        int count = gumballMachine.getCount();
        return new TransitionResult(succeeded, message,
                gumballMachine.getTheStateName(), count);
    }

    @Override
    public TransitionResult turnCrank() {
        gumballMachine.changeTheStateTo(GumballMachineState.GUMBALL_SOLD);
        String message = "You turned...";
        boolean succeeded = true;
        int count = gumballMachine.getCount();
        return new TransitionResult(succeeded, message,
                gumballMachine.getTheStateName(), count);
    }

    @Override
    public TransitionResult dispense() {
        String message = "No gumball dispensed";
        boolean succeeded = false;
        int count = gumballMachine.getCount();
        return new TransitionResult(succeeded, message,
                gumballMachine.getTheStateName(), count);
    }
    @Override
    public String toString() {
        return GumballMachineState.HAS_QUARTER.name();
    }
}
