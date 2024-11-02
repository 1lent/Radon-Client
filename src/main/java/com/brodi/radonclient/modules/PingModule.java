package com.brodi.radonclient.modules;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;

public class PingModule extends Module {

    private int ping;
    private String pingText;

    public PingModule() {
        super("Ping", "Displays current Ping on the screen");
    }

    @Override
    public void onTick() {}

    @Override
    public void render(DrawContext context) {
        ping = MinecraftClient.getInstance().getNetworkHandler().getPlayerListEntry(MinecraftClient.getInstance().player.getUuid()).getLatency();
        pingText =  (ping + "ms");
        context.drawTextWithShadow(mc.textRenderer, pingText, 50, 5, 0xFFFFFF);
    }
}
