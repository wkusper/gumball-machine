package edu.iu.habahram.GumballMachine.model;
public class GumballMachine2 implements IGumballMachine{
    String id;
    IState soldOutState;
    IState noQuarterState;
    IState hasQuarterState;
    IState soldState;

    IState state;

    public IState getState() {
        return state;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getTheStateName() {
        return state.getTheName();
    }



    public void setState(IState state) {
        this.state = state;
    }

    int count = 0;

    public GumballMachine2(String id, String stateName, int count) {
        soldOutState = new SoldOutState(this);
        noQuarterState = new NoQuarterState(this);
        hasQuarterState = new HasQuarterState(this);
        soldState = new SoldState(this);

        this.id = id;
        this.count = count;
        changeTheStateTo(GumballMachineState.valueOf(stateName));
    }
    @Override
    public TransitionResult insertQuarter() {
        return state.insertQuarter();
    }

    @Override
    public TransitionResult ejectQuarter() {
        return state.ejectQuarter();
    }

    @Override
    public TransitionResult turnCrank() {
        state.turnCrank();

        return state.dispense();
    }

    @Override
    public void releaseBall() {
        count = count - 1;
    }

    @Override
    public void changeTheStateTo(GumballMachineState name) {
        switch (name) {
            case NO_QUARTER -> {
                this.setState(noQuarterState);
            }
            case HAS_QUARTER -> {
                this.setState(hasQuarterState);
            }
            case OUT_OF_GUMBALLS -> {
                this.setState(soldOutState);
            }
            case GUMBALL_SOLD -> {
                this.setState(soldState);
            }
        }
    }
}