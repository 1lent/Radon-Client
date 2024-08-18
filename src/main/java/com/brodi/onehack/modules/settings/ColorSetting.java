package com.brodi.onehack.modules.settings;

import java.awt.Color;

public class ColorSetting extends Setting<Color> {

    public ColorSetting(String name, Color defaultValue) {
        super(name, defaultValue); // Pass name and defaultValue to the Setting constructor
    }

    public Color getValue() {
        return super.getValue();
    }

    public void setValue(Color value) {
        super.setValue(value);
    }
}
