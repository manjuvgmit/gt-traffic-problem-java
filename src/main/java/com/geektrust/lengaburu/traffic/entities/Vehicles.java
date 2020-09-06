package com.geektrust.lengaburu.traffic.entities;

import java.util.Set;

import static com.geektrust.lengaburu.traffic.entities.Weather.*;
import static java.util.Collections.unmodifiableSet;

public enum Vehicles {
    BIKE("BIKE", new Speed(10), 2, unmodifiableSet(Set.of(SUNNY, WINDY))),
    TUK_TUK("TUKTUK", new Speed(12), 1, unmodifiableSet(Set.of(SUNNY, RAINY))),
    SUPER_CAR("CAR", new Speed(20), 3, unmodifiableSet(Set.of(SUNNY, RAINY, WINDY)));

    private final String shortName;
    private final Speed speed;
    private final int timeToCrossCrater;
    private final Set<Weather> allowedWeather;

    Vehicles(String shortName, Speed speed, int timeToCrossCrater, Set<Weather> allowedWeather) {
        this.shortName = shortName;
        this.speed = speed;
        this.timeToCrossCrater = timeToCrossCrater;
        this.allowedWeather = allowedWeather;
    }

    public String getShortName() {
        return shortName;
    }

    public Speed getSpeed() {
        return speed;
    }

    public int getTimeToCrossCrater() {
        return timeToCrossCrater;
    }

    public Set<Weather> getAllowedWeather() {
        return allowedWeather;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Vehicles{");
        sb.append("name=").append(name());
        sb.append(", speed=").append(speed);
        sb.append(", timeToCrossCrater=").append(timeToCrossCrater);
        sb.append(", allowedWeather=").append(allowedWeather);
        sb.append("}");
        return sb.toString();
    }
}
