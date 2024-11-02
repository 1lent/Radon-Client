package com.brodi.radonclient.modules;

import net.minecraft.client.gui.DrawContext;

public class CoordsModule extends Module {
    public CoordsModule() {
        super("Coords", "Displays current player coordinates");
    }

    @Override
    public void onTick() {}

    @Override
    public void render(DrawContext context) {
        if (mc.player != null) {
            String coords = String.format("X: %.1f, Y: %.1f, Z: %.1f",
                    mc.player.getX(), mc.player.getY(), mc.player.getZ());
            context.drawTextWithShadow(mc.textRenderer, coords, 5, 20, 0xFFFFFF);
        }
    }
}