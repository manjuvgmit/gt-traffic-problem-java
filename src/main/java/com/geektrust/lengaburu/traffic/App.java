/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package com.geektrust.lengaburu.traffic;

import com.geektrust.lengaburu.traffic.utils.MiscUtils;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Main app and entry point for the problem
 */
public class App {

    /**
     * Main entry point processing multiple input patterns
     * @param args VM args passed in from CLI
     * @throws Exception throws exception if there any issues with input parameters
     */
    public static void main(String[] args) throws Exception {
        App app = new App();
        if (args.length == 1) {
            System.out.println(String.join("\n", app.processInputFromFile(args[0])));
        } else if (args.length == 3) {
            System.out.println(app.processInputFromCli(args));
        } else {
            System.out.println("Invalid Parameters.");
        }
    }

    /**
     * Assumes passed CLI parameters are program arguments directly and processes them
     * @param args program arguments like 'WINDY 45 20'
     * @return fastest route and vehicle like 'CAR ORBIT1'
     */
    public String processInputFromCli(String[] args) {
        return getRouteResolver().determineVehicleAndRoute(String.join(" ", args));
    }

    /**
     * Assumes passed CLI parameter is path where program arguments are stored.
     * Reads the file and processed each line of the txt file as program argument.
     * @param arg program arguments like 'inputs/sample-01.txt' , '~/Downloads/sample-02.txt'
     * @return fastest route and vehicle like 'CAR ORBIT1'
     */
    public List<String> processInputFromFile(String arg) throws IOException {
        return MiscUtils.getInputFromFile(arg).stream()
                .map(commands -> getRouteResolver().determineVehicleAndRoute(commands))
                .collect(Collectors.toList());
    }

    private RouteResolver getRouteResolver() {
        return new RouteResolver();
    }
}
