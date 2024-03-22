package edu.iu.habahram.GumballMachine.service;

import edu.iu.habahram.GumballMachine.model.GumballMachineRecord;
import edu.iu.habahram.GumballMachine.model.TransitionResult;

import java.io.IOException;
import java.util.List;

public interface IGumballService {
    TransitionResult insertQuarter(String id) throws IOException;
    TransitionResult ejectQuarter(String id);
    TransitionResult turnCrank(String id);
    
    List<GumballMachineRecord> findAll() throws IOException;

    GumballMachineRecord findById(String id) throws IOException;

    String save(GumballMachineRecord gumballMachineRecord) throws IOException;

}
