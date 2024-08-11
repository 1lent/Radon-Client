package com.brodi.onehack.render;

import com.brodi.onehack.module.Mod;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import org.lwjgl.glfw.GLFW;

import java.awt.*;

public class KeystrokesOverlay extends Mod {

    private static final MinecraftClient mc = MinecraftClient.getInstance();
    private static final TextRenderer textRenderer = mc.textRenderer;

    public KeystrokesOverlay() {
        super("KeystrokesOverlay", "Displays keys pressed on the screen", Category.RENDER);
        this.setKey(GLFW.GLFW_KEY_K);
    }

    @Override
    public void onEnable() {
        HudRenderCallback.EVENT.register(this::renderKeystrokes);
    }

    @Override
    public void onDisable() {
        // Optionally, you can unregister the callback here if necessary
    }

    private void renderKeystrokes(DrawContext context, float tickDelta) {
        int x = 10;
        int y = 10;
        int spacing = 20;

        // Render WASD keys
        drawKeyboardKey(context, "W", GLFW.GLFW_KEY_W, x + spacing, y);
        drawKeyboardKey(context, "A", GLFW.GLFW_KEY_A, x, y + spacing);
        drawKeyboardKey(context, "S", GLFW.GLFW_KEY_S, x + spacing, y + spacing);
        drawKeyboardKey(context, "D", GLFW.GLFW_KEY_D, x + spacing * 2, y + spacing);

        // Render mouse buttons
        drawMouseButton(context, "LMB", GLFW.GLFW_MOUSE_BUTTON_1, x, y + spacing * 2);
        drawMouseButton(context, "RMB", GLFW.GLFW_MOUSE_BUTTON_2, x + spacing * 2, y + spacing * 2);
    }

  private void drawKeyboardKey(DrawContext context, String name, int key, int x, int y) {
      boolean pressed = GLFW.glfwGetKey(mc.getWindow().getHandle(), key) == GLFW.GLFW_PRESS;

      // Get the current system time in milliseconds
      long time = System.currentTimeMillis();

      // Calculate the hue value. The modulus operator (%) is used to loop the hue value between 0 and 1.
      float hue = (time % 2000) / 2000f;

      // Convert the HSB value to an RGB value. The saturation and brightness values are set to 1.
      int color = Color.HSBtoRGB(hue, 1, 1);

      // If the key is not pressed, make the color darker
      if (!pressed) {
          color = color & 0x7F7F7F; // Bitwise AND with 0x7F7F7F to make the color darker
      }

      // Draw key background
      context.fill(x, y, x + 20, y + 20, pressed ? 0xAA000000 : 0x55000000); // Semi-transparent black

      // Draw key label
      context.drawTextWithShadow(textRenderer, name, x + 5, y + 5, color);
  }

    private void drawMouseButton(DrawContext context, String name, int button, int x, int y) {
        boolean pressed = GLFW.glfwGetMouseButton(mc.getWindow().getHandle(), button) == GLFW.GLFW_PRESS;
        int color = pressed ? 0xFFFFFF : 0xAAAAAA; // White when pressed, gray otherwise

        // Draw key background
        context.fill(x, y, x + 40, y + 20, pressed ? 0xAA000000 : 0x55000000); // Semi-transparent black

        // Draw key label
        context.drawTextWithShadow(textRenderer, name, x + 5, y + 5, color);
    }

    @Override
    public void onTick() {
        // Update logic each tick, if necessary
    }
}
