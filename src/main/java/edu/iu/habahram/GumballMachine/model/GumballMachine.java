package edu.iu.habahram.GumballMachine.model;

public class GumballMachine implements IGumballMachine {
    final String SOLD_OUT = GumballMachineState.OUT_OF_GUMBALLS.name();
    final String NO_QUARTER = GumballMachineState.NO_QUARTER.name();
    final String HAS_QUARTER = GumballMachineState.HAS_QUARTER.name();
    final String SOLD = GumballMachineState.GUMBALL_SOLD.name();
    private String id;
    String state = SOLD_OUT;
    int count = 0;

    public GumballMachine(String id, String state, int count) {
        this.id = id;
        this.state = state;
        this.count = count;
    }

    @Override
    public TransitionResult insertQuarter() {
        boolean succeeded = false;
        String message = "";
        if (state.equalsIgnoreCase(HAS_QUARTER)) {
            message = "You can't insert another quarter";
        } else if (state.equalsIgnoreCase(NO_QUARTER)) {
            state = HAS_QUARTER;
            message = "You inserted a quarter";
            succeeded = true;
        } else if (state.equalsIgnoreCase(SOLD_OUT)) {
            message = "You can't insert a quarter, the machine is sold out";
        } else if (state.equalsIgnoreCase(SOLD)) {
            message = "Please wait, we're already giving you a gumball";
        }
        return new TransitionResult(succeeded, message, state, count);
    }

    @Override
    public TransitionResult ejectQuarter() {
        boolean succeeded = false;
        String message = "";
        if (state.equalsIgnoreCase(HAS_QUARTER)) {
            state = NO_QUARTER;
            message = "You ejected the quarter";
            succeeded = true;
        } else if (state.equalsIgnoreCase(NO_QUARTER)) {
            message = "You can't eject no quarter";
        } else if (state.equalsIgnoreCase(SOLD_OUT)) {
            message = "You can't insert a quarter, the machine is sold out";
        } else if (state.equalsIgnoreCase(SOLD)) {
            message = "Please wait, we're already giving you a gumball";
        }
        return new TransitionResult(succeeded, message, state, count);
    }

    @Override
    public TransitionResult turnCrank() {

        if (count == 0) {
            state = SOLD_OUT;
        }
        boolean succeeded = false;
        String message = "";
        if (state.equalsIgnoreCase(SOLD_OUT)) {
            message = "There are no Gumballs left";
        } else if (state.equalsIgnoreCase(NO_QUARTER)) {
            message = "There is no coin to turn";
        } else if (state.equalsIgnoreCase(HAS_QUARTER)) {
            message = "Turning quarter...";
            succeeded = true;
            state = NO_QUARTER;
            return dispense();
        } else if (state.equalsIgnoreCase(SOLD)) {
            message = "You already got a gumball";
        }
        return new TransitionResult(succeeded, message, state, count);
    }

    private TransitionResult dispense() {
        count --;
        String message = "Dispensing Gumball";
        boolean succeeded = true;
        return  new TransitionResult(succeeded, message, state, count);
    }

    @Override
    public void changeTheStateTo(GumballMachineState name) {

    }

    @Override
    public Integer getCount() {
        return count;
    }

    @Override
    public String getTheStateName() {
        return null;
    }

    @Override
    public void releaseBall() {

    }


}