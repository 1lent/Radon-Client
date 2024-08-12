package com.brodi.onehack.render;

import com.brodi.onehack.module.Mod;
import com.brodi.onehack.module.settings.ColorSetting;
import com.brodi.onehack.module.settings.SpacebarSetting;
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
    public ColorSetting keystrokeColor = new ColorSetting("Keystroke Color", Color.RED);

    public SpacebarSetting spacebarTick = new SpacebarSetting("Spacebar", Color.RED);

    public KeystrokesOverlay() {
        super("KeystrokesOverlay", "Displays keys pressed on the screen", Category.RENDER);
        this.setKey(GLFW.GLFW_KEY_K);
        addSettings(keystrokeColor); // Add the color setting
        addSettings(spacebarTick);
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
        int x = 10;
        int y = 10;
        int z = 50;
        int spacing = 20;

        // Render WASD keys
        drawKeyboardKey(context, "W", GLFW.GLFW_KEY_W, x + spacing, y);
        drawKeyboardKey(context, "A", GLFW.GLFW_KEY_A, x, y + spacing);
        drawKeyboardKey(context, "S", GLFW.GLFW_KEY_S, x + spacing, y + spacing);
        drawKeyboardKey(context, "D", GLFW.GLFW_KEY_D, x + spacing * 2, y + spacing);
        drawKeyboardKey(context, "Space", GLFW.GLFW_KEY_SPACE, 5 + spacing, z + spacing);

        // Render mouse buttons
        drawMouseButton(context, "LMB", GLFW.GLFW_MOUSE_BUTTON_1, x, y + spacing * 2);
        drawMouseButton(context, "RMB", GLFW.GLFW_MOUSE_BUTTON_2, x + spacing * 2, y + spacing * 2);
    }

    private void drawKeyboardKey(DrawContext context, String name, int key, int x, int y) {
        boolean pressed = GLFW.glfwGetKey(mc.getWindow().getHandle(), key) == GLFW.GLFW_PRESS;

        // Use the color from the setting
        Color color = keystrokeColor.getValue();

        int rgbColor = pressed ? color.getRGB() : color.darker().getRGB();

        // Draw key background
        context.fill(x, y, x + 20, y + 20, pressed ? 0xAA000000 : 0x55000000);

        // Draw key label
        context.drawTextWithShadow(textRenderer, name, x + 5, y + 5, rgbColor);
    }

    private void drawMouseButton(DrawContext context, String name, int button, int x, int y) {
        boolean pressed = GLFW.glfwGetMouseButton(mc.getWindow().getHandle(), button) == GLFW.GLFW_PRESS;

        // Use the color from the setting
        Color color = keystrokeColor.getValue();
        int rgbColor = pressed ? color.getRGB() : color.darker().getRGB();

        // Draw button background
        context.fill(x, y, x + 40, y + 20, pressed ? 0xAA000000 : 0x55000000);

        // Draw button label
        context.drawTextWithShadow(textRenderer, name, x + 5, y + 5, rgbColor);
    }

    @Override
    public void onTick() {
        // Optional: Update logic per tick
    }
}
