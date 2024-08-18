package com.brodi.onehack.modules;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;

public abstract class Module {
    protected final MinecraftClient mc = MinecraftClient.getInstance();
    private final String name;
    private final String description;
    private boolean enabled;
    private int key;


    public Module(String name, String description) {
        this.name = name;
        this.description = description;
        this.key = -1;
        this.enabled = false;  // Start disabled by default
    }

    public abstract void onTick();
    public abstract void render(DrawContext context);

    public void toggle() {
        this.enabled = !this.enabled;
        if (this.enabled) {
            onEnable();
        } else {
            onDisable();
        }
    }

    protected void onEnable() {
        // Override this in subclasses if needed
    }

    protected void onDisable() {
        // Override this in subclasses if needed
    }

    public String getName() { return name; }
    public String getDescription() { return description; }
    public boolean isEnabled() { return enabled; }
    public void setEnabled(boolean enabled) {
        if (this.enabled != enabled) {
            this.enabled = enabled;
            if (this.enabled) {
                onEnable();
            } else {
                onDisable();
            }
        }
    }
    public int getKey() { return key; }
    public void setKey(int key) { this.key = key; }
}