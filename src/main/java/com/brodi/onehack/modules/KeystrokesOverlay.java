package com.brodi.onehack.modules;

import com.brodi.onehack.modules.settings.ColorSetting;
import com.brodi.onehack.modules.settings.SpacebarSetting;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import org.lwjgl.glfw.GLFW;

import java.awt.Color;

public class KeystrokesOverlay extends Mod {

    private static final MinecraftClient mc = MinecraftClient.getInstance();
    private static final TextRenderer textRenderer = mc.textRenderer;

    // Color setting for the keystrokes
    public final ColorSetting keystrokeColor;
    public final SpacebarSetting spacebarTick;

    public KeystrokesOverlay() {
        super("KeystrokesOverlay", "Displays keys pressed on the screen", Category.RENDER);
        this.setKey(GLFW.GLFW_KEY_K);

        keystrokeColor = new ColorSetting("Keystroke Color", Color.RED);
        spacebarTick = new SpacebarSetting("Spacebar", Color.RED);

        // Register the settings
        addSetting(keystrokeColor);
        addSetting(spacebarTick);
    }

    @Override
    public void onEnable() {
        HudRenderCallback.EVENT.register(this::renderKeystrokes);
    }

    @Override
    public void onDisable() {
        // Unregister the callback if needed
    }

    private void renderKeystrokes(DrawContext context, float tickDelta) {
        // Keystroke rendering logic here
    }

    @Override
    public void onTick() {
        // Optional: Update logic per tick
    }
}
