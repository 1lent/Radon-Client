package com.brodi.onehack.modules.settings;

import java.awt.Color;

public class SpacebarSetting extends Setting<Boolean> {
    private Color color;

    public SpacebarSetting(String name, Color color) {
        super(name, true);  // Pass the name and the default value for 'enabled' (true).
        this.color = color;
    }

    public boolean isEnabled() {
        return this.getValue(); // Use the generic getValue() method.
    }

    public void setEnabled(boolean enabled) {
        this.setValue(enabled); // Use the generic setValue() method.
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
