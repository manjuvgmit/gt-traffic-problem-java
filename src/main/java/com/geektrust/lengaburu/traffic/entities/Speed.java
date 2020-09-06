package com.geektrust.lengaburu.traffic.entities;

public class Speed {
    private int value;
    private Unit unit;

    public Speed(int value, Unit unit) {
        this.value = value;
        this.unit = unit;
    }

    public Speed(int value) {
        this.value = value;
        this.unit = Unit.MegaMilesPerHour;
    }

    public int getValue() {
        return value;
    }

    public Unit getUnit() {
        return unit;
    }

    enum Unit {
        MegaMilesPerHour("mm/hr");

        private final String unit;

        Unit(String unit) {
            this.unit = unit;
        }

        public String getUnit() {
            return unit;
        }
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Speed{");
        sb.append("speed=").append(value);
        sb.append(", unit=").append(unit);
        sb.append('}');
        return sb.toString();
    }
}
