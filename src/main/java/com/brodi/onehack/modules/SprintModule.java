package com.brodi.onehack.modules;

import net.minecraft.client.gui.DrawContext;

public class SprintModule extends Module {
    public SprintModule() {
        super("Sprint", "Automatically sprints for the player");
    }

    @Override
    public void onTick() {
        if (mc.player != null && mc.player.input.movementForward > 0) {
            mc.player.setSprinting(true);
        }
    }

    @Override
    public void render(DrawContext context) {}
}