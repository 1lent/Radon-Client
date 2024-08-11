package com.brodi.onehack.movement;

import com.brodi.onehack.module.settings.BooleanSetting;
import com.brodi.onehack.module.settings.ModeSetting;
import com.brodi.onehack.module.settings.NumberSetting;
import org.lwjgl.glfw.GLFW;
import net.minecraft.client.MinecraftClient;
import com.brodi.onehack.module.Mod;

public class Flight extends Mod {

    // Define settings for the Flight module
    public NumberSetting speed = new NumberSetting("Speed", 0.1, 10, 1, 0.1); // Default value 0.1, min 0.1, max 10
    public BooleanSetting testBool = new BooleanSetting("Check", true);
    public ModeSetting testMode = new ModeSetting("Mode", "test", "test 2", "test 3");

    public Flight() {
        super("Flight", "Allows you to fly", Category.MOVEMENT);
        this.setKey(GLFW.GLFW_KEY_L); // Set default key binding for the Flight module
        addSettings(speed, testBool, testMode);
    }

    @Override
    public void onEnable() {
        assert mc.player != null;
        mc.player.getAbilities().flying = true; // Enable flight
        mc.player.getAbilities().setFlySpeed(speed.getValueFloat()); // Set flight speed from settings
        super.onEnable(); // Call super method
    }

    @Override
    public void onDisable() {
        assert mc.player != null;
        mc.player.getAbilities().flying = false; // Disable flight
        mc.player.getAbilities().setFlySpeed(0.05F); // Reset fly speed
        super.onDisable(); // Call super method
    }

    @Override
    public void onTick() {
        // Optionally adjust or update flight behavior here
        super.onTick(); // Call super method
    }
}
