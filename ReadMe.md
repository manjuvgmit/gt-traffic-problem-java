# Langaburu Traffic Problem

Solution for the Geektrust *Lengaburu Traffic* problem.

## Problem Statement

Please refer to the pdf document with problem statement. Same has been copied below.
[Geektrust_in_traffic_java.pdf](/docs/Geektrust_in_traffic_java.pdf)


## Sample input & output
Your program should take the location to the test file as parameter. Input needs to be read from a text file,
and output should be printed to the console.

**Input Format**
```
"Weather" ”ORBIT1 SPEED" "ORBIT2 SPEED"
```

**Output Format**
```
"Vehicle" "ORBIT"
```

**Sample Input**
```
RAINY 40 25
SUNNY 12 10
WINDY 14 20
RAINY 8 15
```

**Sample Output**
```
CAR ORBIT2
TUKTUK ORBIT1
CAR ORBIT2
TUKTUK ORBIT2
```

### Error Scenario

**Sample Input**
```
RAINY 40
```

**Sample Output**
```
Missing mandatory parameters. Please pass parameters in the format: <WEATHER ORBIT_1_TRAFFIC_SPEED ORBIT_2_TRAFFIC_SPEED>
```

## How to run

### With a file from repo
```console
$ gradle run --args="src/test/resources/inputs/sample-01.txt"
```

### With a file of your own
```console
$ gradle run --args="home/Downloads/sample.txt" --info
```