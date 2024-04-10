package edu.iu.habahram.GumballMachine.model;

public class SoldState implements IState{
    IGumballMachine gumballMachine;
    public SoldState(IGumballMachine gumballMachine) {
        this.gumballMachine = gumballMachine;
    }
    @Override
    public TransitionResult insertQuarter() {
        String message = "Please wait, we're already giving you a gumball";
        boolean succeeded = false;
        int count = gumballMachine.getCount();
        return new TransitionResult(succeeded, message,
                gumballMachine.getTheStateName(), count);
    }

    @Override
    public TransitionResult ejectQuarter() {
        String message = "Sorry, you already turned the crank";
        boolean succeeded = false;
        int count = gumballMachine.getCount();
        return new TransitionResult(succeeded, message,
                gumballMachine.getTheStateName(), count);
    }

    @Override
    public TransitionResult turnCrank() {
        String message = "Turning twice doesn't get you another gumball!";
        boolean succeeded = false;
        int count = gumballMachine.getCount();
        return new TransitionResult(succeeded, message,
                gumballMachine.getTheStateName(), count);
    }

    @Override
    public TransitionResult dispense() {
        gumballMachine.changeTheStateTo(GumballMachineState.NO_QUARTER);
        String message = "Here is your gumball";
        boolean succeeded = true;
        int count = gumballMachine.getCount();
        return new TransitionResult(succeeded, message,
                gumballMachine.getTheStateName(), count);
    }
    @Override
    public String getTheName() {
        return GumballMachineState.GUMBALL_SOLD.name();
    }
}
