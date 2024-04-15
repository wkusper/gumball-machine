package edu.iu.habahram.GumballMachine.service;

import edu.iu.habahram.GumballMachine.model.*;
import edu.iu.habahram.GumballMachine.repository.IGumballRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class GumballService implements IGumballService{

    IGumballRepository gumballRepository;

    public GumballService(IGumballRepository gumballRepository) {
        this.gumballRepository = gumballRepository;
    }

    private TransitionResult transit(String id, Transition action) throws IOException {
        GumballMachineRecord record = gumballRepository.findById(id);
        IGumballMachine machine = new GumballMachine2(record.getId(), record.getState(), record.getCount());
        TransitionResult result = runTheMachine(machine, action);
        if(result.succeeded()) {
            record.setState(result.stateAfter());
            record.setCount(result.countAfter());
            save(record);
        }
        return result;
    }

    private TransitionResult runTheMachine(IGumballMachine machine, Transition action) {
        switch (action) {
            case INSERT_QUARTER -> {
                return machine.insertQuarter();
            }
            case EJECT_QUARTER -> {
                try {
                    return machine.ejectQuarter();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            case TURN_CRANK -> {
                try {
                    return machine.turnCrank();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return null;
    }

    @Override
    public TransitionResult insertQuarter(String id) throws IOException {
        return transit(id, Transition.INSERT_QUARTER);
    }

    @Override
    public TransitionResult ejectQuarter(String id) {
        try {
            return transit(id, Transition.EJECT_QUARTER);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public TransitionResult turnCrank(String id) {
        try {
            return transit(id, Transition.TURN_CRANK);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



    @Override
    public List<GumballMachineRecord> findAll() throws IOException {
        return gumballRepository.findAll();
    }

    @Override
    public GumballMachineRecord findById(String id) throws IOException {
        return gumballRepository.findById(id);
    }

    @Override
    public String save(GumballMachineRecord gumballMachineRecord) throws IOException {
        return gumballRepository.save(gumballMachineRecord);
    }
}