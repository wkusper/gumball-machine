package edu.iu.habahram.GumballMachine.model;

public class GumballMachine implements IGumballMachine {
    public final static String SOLD_OUT = "Out of Gumballs";
    public final static String NO_QUARTER = "No Quarter";
    public final static String HAS_QUARTER = "Has Quarter";
    public final static String SOLD = "Gumball Sold";
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
        String stateAfterTheAttempt = null;
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
        stateAfterTheAttempt = state;
        return new TransitionResult(succeeded, message, stateAfterTheAttempt, count);
    }

    @Override
    public TransitionResult ejectQuarter() {
        //TODO
        return null;
    }

    @Override
    public TransitionResult turnCrank() {
        //TODO
        return null;
    }

    @Override
    public TransitionResult dispense() {
        //TODO
        return null;
    }
}
