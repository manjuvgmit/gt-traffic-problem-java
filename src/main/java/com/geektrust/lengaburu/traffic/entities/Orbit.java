package com.geektrust.lengaburu.traffic.entities;

public enum Orbit {
    ORBIT1(new Distance(18), 20),
    ORBIT2(new Distance(20), 10);

    private Distance distance;
    private int noOfCraters;

    Orbit(Distance distance, int noOfCraters) {
        this.distance = distance;
        this.noOfCraters = noOfCraters;
    }

    public Distance getDistance() {
        return distance;
    }

    public int getNoOfCraters() {
        return noOfCraters;
    }
}
