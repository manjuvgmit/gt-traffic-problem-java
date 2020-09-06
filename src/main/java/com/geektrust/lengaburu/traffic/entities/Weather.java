package com.geektrust.lengaburu.traffic.entities;

import static com.geektrust.lengaburu.traffic.entities.Weather.ImpactOnCraters.*;

public enum Weather {
    SUNNY(0.10, DECREASE),
    RAINY(0.20, INCREASE),
    WINDY(0.00, NEUTRAL);

    private final double rateOfImpactOnCraters;
    private final ImpactOnCraters impactOnCraters;

    Weather(double rateOfImpactOnCraters, ImpactOnCraters impactOnCraters) {
        this.rateOfImpactOnCraters = rateOfImpactOnCraters;
        this.impactOnCraters = impactOnCraters;
    }

    public double getRateOfImpactOnCraters() {
        return rateOfImpactOnCraters;
    }

    public ImpactOnCraters getImpactOnCraters() {
        return impactOnCraters;
    }

    public enum ImpactOnCraters {
        INCREASE, DECREASE, NEUTRAL
    }
}