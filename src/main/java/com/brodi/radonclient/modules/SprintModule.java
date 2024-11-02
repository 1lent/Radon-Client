package com.brodi.radonclient.modules;

import net.minecraft.client.gui.DrawContext;

public class SprintModule extends Module {
    public SprintModule() {
        super("Sprint", "Automatically sprints for the player");
    }

    @Override
    public void onTick() {
        mc.player.setSprinting(true);
        super.onTick();
    }

    @Override
    public void render(DrawContext context) {
        mc.player.setSprinting(true);
        super.onTick();
        context.drawTextWithShadow(mc.textRenderer, "Sprinting [Toggled]", 5, 35, 0xFFFFFF);

    }
}