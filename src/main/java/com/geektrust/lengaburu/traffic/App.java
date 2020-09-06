/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package com.geektrust.lengaburu.traffic;

import com.geektrust.lengaburu.traffic.entities.*;
import com.geektrust.lengaburu.traffic.utils.MiscUtils;

import java.util.*;
import java.util.stream.Collectors;

import static com.geektrust.lengaburu.traffic.entities.Weather.ImpactOnCraters.DECREASE;

public class App {

    public static void main(String[] args) throws Exception {
        App app = new App();
        if (args.length == 1) {
            MiscUtils.getInputFromFile(args[0]).forEach(commands -> System.out.println(app.determineVehicleAndRoute(commands)));
        } else if (args.length == 3) {
            System.out.println(app.determineVehicleAndRoute(String.join(" ", args)));
        } else {
            System.out.println("Invalid Parameters.");
        }
    }

    public String determineVehicleAndRoute(String argument) {
        try {
            InputParameters inputParameters = InputParameters.parse(argument);
            List<ResultData> resultDataStream = Arrays.stream(Vehicles.values())
                    .filter(vehicles -> vehicles.getAllowedWeather().contains(inputParameters.getWeather()))
                    .map(vehicle -> calculateTimeTakenForEachVehicle(vehicle, inputParameters))
                    .collect(Collectors.toList());
            //System.out.println("time calculated and filtered : " + resultDataStream.toString());
            Map<Orbit, ResultData> resultMap = new HashMap<>();
            Arrays.stream(Orbit.values()).forEach(orbit -> resultMap.put(orbit, resultDataStream.stream().min(Comparator.comparing(r -> r.getTimeEntries().get(orbit))).orElse(null)));
            //System.out.println("Filtered on orbits : " + resultMap.toString());
            Map.Entry<Orbit, ResultData> finalResult = resultMap.entrySet().stream().min(Comparator.comparing(t -> t.getValue().getTimeEntries().get(t.getKey()))).orElse(null);
            //System.out.println("Final Result : " + finalResult.toString());
            return finalResult.getValue().getVehicle().getShortName() + " " + finalResult.getKey().name();
        } catch (Exception ex) {
            return ex.getMessage();
        }
    }

    private ResultData calculateTimeTakenForEachVehicle(Vehicles vehicle, InputParameters inputParameters) {
        final ResultData resultData = new ResultData(vehicle);
        Arrays.stream(Orbit.values()).forEach(orbit ->
                resultData.getTimeEntries().put(orbit, getTimeTakenForVehicleForAnOrbit(orbit, vehicle, inputParameters))
        );
        return resultData;
    }

    private double getTimeTakenForVehicleForAnOrbit(Orbit orbit, Vehicles vehicle, InputParameters inputParameters) {
        return (getStraightUpTimeTakenInHours(orbit, vehicle, inputParameters) * 60.0)
                + getDelayDueToCratersInMinutes(orbit, vehicle, inputParameters);
    }

    private double getStraightUpTimeTakenInHours(Orbit orbit, Vehicles vehicle, InputParameters inputParameters) {
        return 1.0 * orbit.getDistance().getValue()
                / Math.min(vehicle.getSpeed().getValue(), inputParameters.getOrbitsCurrentTrafficSpeeds().get(orbit).getValue());
    }

    private double getDelayDueToCratersInMinutes(Orbit orbit, Vehicles vehicle, InputParameters inputParameters) {
        return vehicle.getTimeToCrossCrater() * getTotalNoOfCraters(orbit, inputParameters);
    }

    /**
     * Returns either reduced or increased number of craters depending on weather and orbit.
     * Result is case to int to keep craters count in realistic.
     *
     * @param inputParameters input parameters
     * @return increased or decreased count of craters for the given weather and orbit
     */
    private int getTotalNoOfCraters(Orbit orbit, InputParameters inputParameters) {
        Weather currentWeather = inputParameters.getWeather();
        // Total no of craters = craters + craters created due to season
        return (int) (
                orbit.getNoOfCraters()
                        * (1 + ((currentWeather.getImpactOnCraters() == DECREASE ? -1 : 1)) * currentWeather.getRateOfImpactOnCraters())
        );
    }
}