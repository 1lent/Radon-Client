package com.brodi.onehack.render;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import com.brodi.onehack.module.Mod;
import org.lwjgl.glfw.GLFW;

public class PingMod extends Mod {

    private static final MinecraftClient mc = MinecraftClient.getInstance();
    private static final TextRenderer textRenderer = mc.textRenderer;

    public PingMod() {
        super("Ping", "Shows Ping on the screen", Category.RENDER);
        this.setKey(GLFW.GLFW_KEY_P);

        // Register HUD render callback
        HudRenderCallback.EVENT.register((context, tickDelta) -> {
            renderPing(context, tickDelta);
        });
    }

    private void renderPing(DrawContext context, float tickDelta) {
        // Get FPS from the Minecraft client
        int ping = MinecraftClient.getInstance().getNetworkHandler().getPlayerListEntry(MinecraftClient.getInstance().player.getUuid()).getLatency();
        String pingText = "ping " + ping + "ms";

        // Create a Text object
        Text text = Text.of(pingText);

        // Calculate position for text
        int x = mc.getWindow().getScaledWidth() - 150;
        int y = 30;


        // Draw background rectangle with a shadow effect
        drawBackground(context, x, y, 100, 20, 0xAA000000); // Semi-transparent black

        // Draw FPS text with a custom color drawshadowed
        context.drawTextWithShadow(textRenderer, text, x + 2, y + 2, 0xFFFFFF); // White text with shadow


    }

    private void drawBackground(DrawContext context, int x, int y, int width, int height, int color) {
        // Draw a semi-transparent background rectangle
        context.fill(x, y, x + width, y + height, color);
    }

    @Override
    public void onEnable() {
        // Additional setup when the mod is enabled, if necessary
    }

    @Override
    public void onDisable() {
        // Cleanup or reset when the mod is disabled, if necessary
    }

    @Override
    public void onTick() {
        // Update logic each tick, if necessary
    }
}