package com.brodi.onehack;
import com.brodi.onehack.ui.screens.clickgui.ClickGui;
import net.minecraft.client.MinecraftClient;

import net.fabricmc.api.ClientModInitializer;
import com.brodi.onehack.module.Mod;
import com.brodi.onehack.module.ModuleManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.glfw.GLFW;


public class Client implements ClientModInitializer {

    public static final Client INSTANCE = new Client();
    public Logger logger = LogManager.getLogger(Client.class);

    private MinecraftClient mc = MinecraftClient.getInstance();

    @Override
    public void onInitializeClient() {
        logger.info("Hello World!");
    }

    public void onKeyPress(int key, int action) {
        if (action == GLFW.GLFW_PRESS) {
            for (Mod module : ModuleManager.INSTANCE.getModules()) {
                if (key == module.getKey()) module.toggle();
            }
            if (key == GLFW.GLFW_KEY_RIGHT_SHIFT) mc.setScreen(ClickGui.INSTANCE);
        }
    }

    public void onTick() {
        if (mc.player != null) {
            for (Mod module : ModuleManager.INSTANCE.getEnabledModules()) {
                module.onTick();
            }
        }
    }
}