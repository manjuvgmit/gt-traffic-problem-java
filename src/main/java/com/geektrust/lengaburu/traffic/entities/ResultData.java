package com.geektrust.lengaburu.traffic.entities;

import java.util.HashMap;
import java.util.Map;

public class ResultData {
    private Vehicles vehicle;
    private Map<Orbit, Double> timeEntries;

    public ResultData(Vehicles vehicle) {
        this.vehicle = vehicle;
        this.timeEntries = new HashMap<>();
    }

    public Vehicles getVehicle() {
        return vehicle;
    }

    public Map<Orbit, Double> getTimeEntries() {
        return timeEntries;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ResultData{");
        sb.append("vehicle=").append(vehicle);
        sb.append(", timeEntries=").append(timeEntries);
        sb.append('}');
        return sb.toString();
    }
}
