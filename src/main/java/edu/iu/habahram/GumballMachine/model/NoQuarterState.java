package edu.iu.habahram.GumballMachine.model;

public class NoQuarterState implements IState{
    IGumballMachine gumballMachine;
    public NoQuarterState(IGumballMachine gumballMachine) {
        this.gumballMachine = gumballMachine;
    }
    @Override
    public TransitionResult insertQuarter() {
        gumballMachine.changeTheStateTo(GumballMachineState.HAS_QUARTER);
        String message = "You inserted a quarter";
        boolean succeeded = true;
        int count = gumballMachine.getCount();
        return new TransitionResult(succeeded, message,
                gumballMachine.getTheStateName(), count);
    }

    @Override
    public TransitionResult ejectQuarter() {
        String message = "You haven't inserted a quarter";
        boolean succeeded = false;
        int count = gumballMachine.getCount();
        return new TransitionResult(succeeded, message,
                gumballMachine.getTheStateName(), count);
    }

    @Override
    public TransitionResult turnCrank() {
        String message = "You turned, but there's no quarter";
        boolean succeeded = false;
        int count = gumballMachine.getCount();
        return new TransitionResult(succeeded, message,
                gumballMachine.getTheStateName(), count);
    }

    @Override
    public TransitionResult dispense() {
        String message = "You need to pay first";
        boolean succeeded = false;
        int count = gumballMachine.getCount();
        return new TransitionResult(succeeded, message,
                gumballMachine.getTheStateName(), count);
    }
    @Override
    public String getTheName() {
        return GumballMachineState.NO_QUARTER.name();
    }

}
