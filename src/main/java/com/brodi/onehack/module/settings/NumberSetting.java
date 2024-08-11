package com.brodi.onehack.module.settings;

public class NumberSetting extends Setting {

    private double min, max, increment;
    private double value;

    /**
     * Constructor to initialize a NumberSetting with given parameters.
     */
    public NumberSetting(String name, double min, double max, double defaultValue, double increment) {
        super(name);
        this.min = min;
        this.max = max;
        this.increment = increment;
        // Ensure the default value is within the min and max range
        this.value = clamp(defaultValue, min, max);
    }

    /**
     * Clamps a value between min and max.
     */
    public static double clamp(double value, double min, double max) {
        return Math.max(min, Math.min(value, max));
    }

    /**
     * Gets the current value.
     */
    public double getValue() {
        return value;
    }

    /**
     * Gets the current value as a float.
     */
    public float getValueFloat() {
        return (float) value;
    }

    /**
     * Gets the current value as an integer.
     */
    public int getValueInt() {
        return (int) value;
    }

    /**
     * Gets the increment value.
     */
    public double getIncrement() {
        return increment;
    }

    /**
     * Sets the value, clamping it within min and max, and rounding it based on increment.
     */
    public void setValue(double value) {
        value = clamp(value, min, max);
        value = Math.round(value * (1.0 / increment)) / (1.0 / increment);
        this.value = value;
    }

    /**
     * Increments or decrements the value by the increment.
     */
    public void increment(boolean positive) {
        if (positive) setValue(value + increment);
        else setValue(value - increment);
    }

    /**
     * Gets the minimum value.
     */
    public double getMin() {
        return min;
    }

    /**
     * Gets the maximum value.
     */
    public double getMax() {
        return max;
    }
}
