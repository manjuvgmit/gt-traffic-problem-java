package com.geektrust.lengaburu.traffic;

import com.geektrust.lengaburu.traffic.entities.InputParameters;
import com.geektrust.lengaburu.traffic.entities.Orbit;
import com.geektrust.lengaburu.traffic.entities.Vehicle;
import com.geektrust.lengaburu.traffic.entities.Weather;

import java.util.Comparator;
import java.util.Map;

import static com.geektrust.lengaburu.traffic.entities.Weather.ImpactOnCraters.DECREASE;
import static java.util.Arrays.stream;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

/**
 * This class responsible for determining the fastest route considering the weather parameters
 */
public class RouteResolver {
    public String determineVehicleAndRoute(String argument) {
        try {
            InputParameters inputParameters = InputParameters.parse(argument);

            Map<Vehicle, Map<Orbit, Double>> weatherFilteredAndTimeCalculatedData = stream(Vehicle.values())
                    // Filter all vehicles not supported for the weather first
                    .filter(vehicle -> vehicle.getAllowedWeather().contains(inputParameters.getWeather()))
                    // Calculate time would take for each vehicle for all orbits available. i.e. ORBIT1, ORBIT2
                    .collect(toMap(identity(), vehicle -> calculateTimeTakenForEachVehicle(vehicle, inputParameters)));

            return stream(Orbit.values())
                    // Find out faster vehicle for each orbit
                    .collect(toMap(identity(), orbit -> weatherFilteredAndTimeCalculatedData.entrySet().stream().min(Comparator.comparing(r -> r.getValue().get(orbit))).get()))
                    // Find out fastest vehicle among faster vehicle for each orbit
                    .entrySet().stream().min(Comparator.comparing(t -> t.getValue().getValue().get(t.getKey())))
                    // format data in required way
                    .map(entry -> entry.getValue().getKey().getShortName() + " " + entry.getKey().name())
                    .orElse(null);

        } catch (Exception ex) {
            return ex.getMessage();
        }
    }

    /**
     * Calculates time taken by each vehicle for all available orbits considering the weather conditions.
     * @param @param vehicle vehicle chosen for an orbit to travel
     * @param inputParameters input parameters passed from command line consisting of weather and traffic parameters
     * @return a map containing time taken by the vehicle for each orbit in minutes
     */
    private Map<Orbit, Double> calculateTimeTakenForEachVehicle(Vehicle vehicle, InputParameters inputParameters) {
        return stream(Orbit.values()).collect(toMap(identity(), orbit -> getTimeTakenForVehicleForAnOrbit(orbit, vehicle, inputParameters)));
    }

    /**
     * Calculates overall time taken by a vehicle to travel the given orbit's distance considering potholes delay as well.
     * @param orbit orbit being considered for a vehicle to travel
     * @param vehicle vehicle chosen for an orbit to travel
     * @param inputParameters input parameters passed from command line consisting of weather and traffic parameters
     * @return time taken in minutes for a vehicle to travel the passed in Orbit's distance without delay from potholes
     */
    private double getTimeTakenForVehicleForAnOrbit(Orbit orbit, Vehicle vehicle, InputParameters inputParameters) {
        return (getStraightUpTimeTakenInHours(orbit, vehicle, inputParameters) * 60.0)
                + getDelayDueToCratersInMinutes(orbit, vehicle, inputParameters);
    }

    /**
     * Calculates time taken by a vehicle to travel the given orbit's distance without considering potholes delay
     * @param orbit orbit being considered for a vehicle to travel
     * @param vehicle vehicle chosen for an orbit to travel
     * @param inputParameters input parameters passed from command line consisting of weather and traffic parameters
     * @return time taken in minutes for a vehicle to travel the passed in Orbit's distance without delay from potholes
     */
    private double getStraightUpTimeTakenInHours(Orbit orbit, Vehicle vehicle, InputParameters inputParameters) {
        return 1.0 * orbit.getDistance().getValue()
                / Math.min(vehicle.getSpeed().getValue(), inputParameters.getOrbitsCurrentTrafficSpeeds().get(orbit).getValue());
    }

    /**
     * Calculates delay caused for any vehicle type when going over an orbit considering weather conditions
     * @param orbit orbit being considered for a vehicle to travel
     * @param vehicle vehicle chosen for an orbit to travel
     * @param inputParameters input parameters passed from command line consisting of weather and traffic parameters
     * @return time delay in minutes introduced for a vehicle to travel a given Orbit's distance considering weather condition
     */
    private double getDelayDueToCratersInMinutes(Orbit orbit, Vehicle vehicle, InputParameters inputParameters) {
        return vehicle.getTimeToCrossCrater() * getTotalNoOfCraters(orbit, inputParameters);
    }

    /**
     * Returns either reduced or increased number of craters depending on weather and orbit.
     * Result has been cast to int to keep craters count in integers
     * @param inputParameters input parameters
     * @return increased or decreased count of craters for the given weather and orbit
     */
    private int getTotalNoOfCraters(Orbit orbit, InputParameters inputParameters) {
        Weather currentWeather = inputParameters.getWeather();
        // Total no of craters = craters + craters created due to season
        return (int) (orbit.getNoOfCraters()
                * (1 + ((currentWeather.getImpactOnCraters() == DECREASE ? -1 : 1)) * currentWeather.getRateOfImpactOnCraters())
        );
    }
}
