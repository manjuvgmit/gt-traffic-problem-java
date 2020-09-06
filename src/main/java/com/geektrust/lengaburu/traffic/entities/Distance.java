package com.geektrust.lengaburu.traffic.entities;

public class Distance {
    private int value;
    private Unit unit;

    public Distance(int value, Unit unit) {
        this.value = value;
        this.unit = unit;
    }

    public Distance(int value) {
        this.value = value;
        this.unit = Unit.MegaMiles;
    }

    public int getValue() {
        return value;
    }

    public Unit getUnit() {
        return unit;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Distance{");
        sb.append("distance=").append(value);
        sb.append(", unit=").append(unit);
        sb.append('}');
        return sb.toString();
    }

    enum Unit {
        MegaMiles("mm");

        private final String unit;

        Unit(String unit) {
            this.unit = unit;
        }

        public String getUnit() {
            return unit;
        }
    }
}
