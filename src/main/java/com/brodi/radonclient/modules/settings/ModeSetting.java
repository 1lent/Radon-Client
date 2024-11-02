package com.brodi.radonclient.modules.settings;

import java.util.List;
import java.util.Arrays;

public class ModeSetting extends Setting<String> {

    private List<String> modes;
    private int index;

    public ModeSetting(String name, String defaultMode, String... modes) {
        super(name, defaultMode); // Pass name and defaultMode to the Setting constructor
        this.modes = Arrays.asList(modes);
        this.setValue(defaultMode);
        this.index = this.modes.indexOf(defaultMode);
    }

    public String getMode() {
        return this.getValue();
    }

    public List<String> getModes() {
        return modes;
    }

    public void setMode(String mode) {
        this.setValue(mode);
        this.index = modes.indexOf(mode);
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
        this.setValue(modes.get(index));
    }

    public void cycle() {
        if (index < modes.size() - 1) {
            setIndex(index + 1);
        } else {
            setIndex(0);
        }
    }

    public boolean isMode(String mode) {
        return this.getValue().equals(mode);
    }
}
