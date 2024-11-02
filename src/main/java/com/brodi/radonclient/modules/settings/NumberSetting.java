package com.brodi.radonclient.modules.settings;

public class NumberSetting extends Setting<Double> {

    private double min, max, increment;

    public NumberSetting(String name, double min, double max, double defaultValue, double increment) {
        super(name, defaultValue); // Pass name and defaultValue to the Setting constructor
        this.min = min;
        this.max = max;
        this.increment = increment;
        // Ensure the default value is within the min and max range
        this.setValue(clamp(defaultValue, min, max));
    }

    public static double clamp(double value, double min, double max) {
        return Math.max(min, Math.min(value, max));
    }

    public Double getValue() {
        return super.getValue();
    }

    public void setValue(double value) {
        value = clamp(value, min, max);
        value = Math.round(value * (1.0 / increment)) / (1.0 / increment);
        super.setValue(value);
    }

    public void increment(boolean positive) {
        if (positive) setValue(getValue() + increment);
        else setValue(getValue() - increment);
    }

    public double getMin() {
        return min;
    }

    public double getMax() {
        return max;
    }

    public double getIncrement() {
        return increment;
    }
}
