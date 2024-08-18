package com.brodi.onehack.modules;

import net.minecraft.client.gui.DrawContext;

public class FpsModule extends Module {
    public FpsModule() {
        super("FPS", "Displays current FPS on the screen");
    }

    @Override
    public void onTick() {}

    @Override
    public void render(DrawContext context) {
        String fpsText = mc.fpsDebugString.split(" ")[0] + " FPS";
        context.drawTextWithShadow(mc.textRenderer, fpsText, 5, 5, 0xFFFFFF);
    }
}