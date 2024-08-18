package com.brodi.onehack.modules;

import com.brodi.onehack.modules.settings.Setting;

import java.util.ArrayList;
import java.util.List;

public abstract class Mod {
    private final String name;
    private final String description;
    private final Category category;
    private boolean enabled = false;
    private int key;
    private final List<Setting<?>> settings = new ArrayList<>();

    public Mod(String name, String description, Category category) {
        this.name = name;
        this.description = description;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void toggle() {
        enabled = !enabled;
        if (enabled) {
            onEnable();
        } else {
            onDisable();
        }
    }

    public void setKey(int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public Category getCategory() {
        return category;
    }

    public List<Setting<?>> getSettings() {
        return settings;
    }

    public void addSetting(Setting<?> setting) {
        settings.add(setting);
    }

    protected abstract void onEnable();

    protected abstract void onDisable();

    public abstract void onTick();

    public enum Category {
        COMBAT("Combat"),
        MOVEMENT("Movement"),
        RENDER("Render"),
        WORLD("World"),
        MISC("Misc");

        private final String name;

        Category(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
}
