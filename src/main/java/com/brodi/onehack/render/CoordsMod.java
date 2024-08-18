package com.brodi.onehack.render;

import com.brodi.onehack.module.Mod;
import com.brodi.onehack.module.settings.SpacebarSetting;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import org.lwjgl.glfw.GLFW;

import java.awt.Color;

public class CoordsMod extends Mod {
    private static final MinecraftClient mc = MinecraftClient.getInstance();
    private static final TextRenderer textRenderer = mc.textRenderer;

    private SpacebarSetting spacebarSetting;

    public CoordsMod() {
        super("Coords", "Displays current player coordinates", Category.RENDER);
        this.setKey(GLFW.GLFW_KEY_K);

        // Initialize the spacebar setting
        spacebarSetting = new SpacebarSetting("Spacebar Display", Color.WHITE);
        this.addSetting(spacebarSetting);

        // Register HUD render callback
        HudRenderCallback.EVENT.register(this::renderCoords);
    }

    private void renderCoords(DrawContext context, float tickDelta) {
        if (mc.player == null) return;

        int xCoord = mc.player.getBlockX();
        int yCoord = mc.player.getBlockY();
        int zCoord = mc.player.getBlockZ();
        assert mc.world != null;
        float biome = mc.world.getBiome(BlockPos, mc.player.getBlockPos());
        String coords = String.format("X: %d Y: %d Z: %d", xCoord, yCoord, zCoord);

        Text text = Text.of(coords + biome);

        int xPosition = mc.getWindow().getScaledWidth() - 150;
        int yPosition = 50;

        // Draw background rectangle based on whether the spacebar setting is enabled
        int backgroundHeight = spacebarSetting.isEnabled() ? 40 : 20;
        drawBackground(context, xPosition, yPosition, 100, backgroundHeight, 0xAA000000);

        // Draw coordinates text with shadow
        context.drawTextWithShadow(textRenderer, text, xPosition + 2, yPosition + 2, 0xFFFFFF);

        // Render spacebar if the setting is enabled
        if (spacebarSetting.isEnabled()) {
            renderSpacebar(context, xPosition + 2, yPosition + 22);
        }
    }

    private void drawBackground(DrawContext context, int x, int y, int width, int height, int color) {
        context.fill(x, y, x + width, y + height, color);
    }

    private void renderSpacebar(DrawContext context, int x, int y) {
        String spacebarText = "Press SPACE to jump!";
        Text spacebar = Text.of(spacebarText);

        // Draw spacebar text with shadow
        context.drawTextWithShadow(textRenderer, spacebar, x, y, spacebarSetting.getColor().getRGB());
    }

    @Override
    public void onEnable() {
        // Optional setup when the mod is enabled
    }

    @Override
    public void onDisable() {
        // Optional cleanup when the mod is disabled
    }

    @Override
    public void onTick() {
        // Optional logic to update each tick
    }
}
