package com.brodi.radonclient.modules.settings;

public class BooleanSetting extends Setting<Boolean> {

    public BooleanSetting(String name, boolean defaultValue) {
        super(name, defaultValue); // Pass name and defaultValue to the Setting constructor
    }

    public void toggle() {
        this.setValue(!this.getValue());
    }

    public boolean isEnabled() {
        return this.getValue();
    }
}
