package com.brodi.onehack.module;

import com.brodi.onehack.module.settings.Setting;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.effect.StatusEffect;

import java.util.ArrayList;
import java.util.List;

public abstract class Mod {

    private String name;
    private String displayName;
    private String description;
    private int key;
    private Category category;
    private boolean enabled;

    private List<Setting> settings = new ArrayList<>();

    protected MinecraftClient mc = MinecraftClient.getInstance();

    public Mod(String name, String description, Category category) {
        this.name = name;
        this.displayName = name;
        this.description = description;
        this.category = category;
    }

    // Getters and Setters
    public List<Setting> getSettings() {
        return settings;
    }

    public void addSetting(Setting setting) {
        settings.add(setting);
    }

    public void addSettings(Setting... settings) {
        for (Setting setting : settings) addSetting(setting);
    }

    public void toggle() {
        this.enabled = !this.enabled;
        if (enabled) {
            onEnable();
            displayName = "§c" + name; // Display name in red when enabled
        } else {
            onDisable();
            displayName = "§f" + name; // Display name in white when disabled
        }
        System.out.println("Toggled " + name + ": " + (enabled ? "Enabled" : "Disabled"));
    }


    public void onEnable() {
        // Implement functionality when the mod is enabled
    }

    public void onDisable() {
        // Implement functionality when the mod is disabled
    }

    public void onTick() {
        // Implement functionality for each game tick
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
        if (enabled) onEnable();
        else onDisable();
    }

    public Category getCategory() {
        return category;
    }

    // Placeholder implementation for setGlowing
    public final StatusEffect setGlowing() {
        return null; // Modify if needed for specific functionality
    }

    // Enum for module categories
    public enum Category {
        COMBAT("Combat"),
        MOVEMENT("Movement"),
        RENDER("Render"),
        WORLD("World"),
        MISC("Misc");

        public final String name;

        private Category(String name) {
            this.name = name;
        }
    }
}
