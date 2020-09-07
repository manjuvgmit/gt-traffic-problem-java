package com.geektrust.lengaburu.traffic.entities;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public final class InputParameters {
    public static final String ORBIT = "ORBIT";
    private final Weather weather;
    private final Map<Orbit, Speed> orbitsCurrentTrafficSpeeds;

    public static InputParameters parse(String parameters) throws Exception {
        Objects.requireNonNull(parameters, "Input parameters cannot be null.");
        String[] parametersArray = parameters.split(" ");
        if (parametersArray.length < 3) {
            throw new Exception("Missing mandatory parameters. Please pass parameters in the format: <WEATHER ORBIT_1_TRAFFIC_SPEED ORBIT_2_TRAFFIC_SPEED>");
        }
        InputParameters parametersInstance = new InputParameters(Weather.valueOf(parametersArray[0]));
        populateOrbitSpeeds(parametersArray, parametersInstance);
        return parametersInstance;
    }

    private static void populateOrbitSpeeds(String[] parametersArray, InputParameters parametersInstance) {
        String[] orbitsSpeedArray = Arrays.copyOfRange(parametersArray, 1, parametersArray.length);
        for (int index = 0; index < orbitsSpeedArray.length; index++) {
            try {
                Orbit orbit = Orbit.valueOf(ORBIT + (index + 1));
                if (Objects.nonNull(orbit)) {
                    parametersInstance.orbitsCurrentTrafficSpeeds.put(orbit, new Speed(Integer.parseInt(orbitsSpeedArray[index])));
                }
            } catch (IllegalArgumentException ignored) {}
        }
    }

    private InputParameters(Weather weather) {
        this.weather = weather;
        this.orbitsCurrentTrafficSpeeds = new HashMap<>();
    }

    public Weather getWeather() {
        return weather;
    }

    public Map<Orbit, Speed> getOrbitsCurrentTrafficSpeeds() {
        return orbitsCurrentTrafficSpeeds;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("InputParameters{");
        sb.append("weather=").append(weather);
        sb.append(", orbitsCurrentTrafficSpeeds=").append(orbitsCurrentTrafficSpeeds);
        sb.append('}');
        return sb.toString();
    }
}
