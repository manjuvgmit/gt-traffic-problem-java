/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package com.geektrust.lengaburu.traffic;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AppTest {
    App classUnderTest = new App();

    @Test
    public void testScenario01() throws Exception {
        runTest("RAINY 40 25", "CAR ORBIT2");
    }

    @Test
    public void testScenario02() throws Exception {
        runTest("SUNNY 12 10", "TUKTUK ORBIT1");
    }

    @Test
    public void testScenario03() throws Exception {
        runTest("WINDY 14 20", "CAR ORBIT2");
    }

    @Test
    public void testScenario04() throws Exception {
        runTest("RAINY 8 15", "TUKTUK ORBIT2");
    }

    private void runTest(String input, String output) throws Exception {
        assertEquals(output, classUnderTest.determineVehicleAndRoute(input));
    }
}
