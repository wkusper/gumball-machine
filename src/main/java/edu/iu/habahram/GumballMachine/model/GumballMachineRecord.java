package edu.iu.habahram.GumballMachine.model;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public final class GumballMachineRecord {
    private String id;
    private String state;

    public void setId(String id) {
        this.id = id;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setCount(int count) {
        this.count = count;
    }

    private Integer count;

    public GumballMachineRecord(String id, String state, int count) {
        this.id = id;
        this.state = state;
        this.count = count;
    }

    public String toLine(String machineId) {
        String line = String.format("%1s,%2s,%3s",
                machineId,
                getState(),
                getCount());
        return line;
    }

    public static GumballMachineRecord fromLine(String line) {
        String[] tokens = line.split(",");
        GumballMachineRecord gumballMachineRecord = new
                GumballMachineRecord(tokens[0],
                tokens[1],
                Integer.parseInt(tokens[2].trim()));
        return gumballMachineRecord;
    }

    public String getId() {
        return id;
    }

    public String getState() {
        return state;
    }

    public Integer getCount() {
        return count;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (GumballMachineRecord) obj;
        return Objects.equals(this.id, that.id) &&
                Objects.equals(this.state, that.state) &&
                this.count == that.count;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, state, count);
    }

    @Override
    public String toString() {
        return "GumballMachineRecord[" +
                "id=" + id + ", " +
                "state=" + state + ", " +
                "count=" + count + ']';
    }

}