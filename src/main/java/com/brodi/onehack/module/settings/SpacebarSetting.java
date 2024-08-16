package com.brodi.onehack.module.settings;

import java.awt.Color;

public class SpacebarSetting extends Setting {
    public boolean enabled;
    private Color color;

    public SpacebarSetting(String name, Color color) {
        super(name);
        this.enabled = true;
        this.color = color;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
