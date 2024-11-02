package com.brodi.radonclient;

import com.brodi.radonclient.modules.Module;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.client.gui.DrawContext;
import org.lwjgl.glfw.GLFW;

import com.brodi.radonclient.modules.*;
import com.brodi.radonclient.gui.ModernClickGuiScreen;

import java.util.ArrayList;
import java.util.List;

public class OneHackClient implements ClientModInitializer {
    public static final OneHackClient INSTANCE = new OneHackClient();
    private static final MinecraftClient mc = MinecraftClient.getInstance();
    private final List<Module> modules = new ArrayList<>();
    private KeyBinding menuKey;

    @Override
    public void onInitializeClient() {
        initializeModules();
        registerKeybindings();
        registerEvents();
    }

    private void initializeModules() {
        modules.add(new SprintModule());
        modules.add(new FpsModule());
        modules.add(new CoordsModule());
        modules.add(new PingModule());
        // Add other modules here
    }

    private void registerKeybindings() {
        menuKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.onehack.menu", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_RIGHT_SHIFT, "category.onehack.general"
        ));

        for (Module module : modules) {
            if (module.getKey() != -1) {
                KeyBinding moduleKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                        "key.onehack." + module.getName().toLowerCase(),
                        InputUtil.Type.KEYSYM,
                        module.getKey(),
                        "category.onehack.modules"
                ));
            }
        }
    }

    private void registerEvents() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (menuKey.wasPressed()) {
                mc.setScreen(new ModernClickGuiScreen(modules));
            }

            for (Module module : modules) {
                if (module.getKey() != -1 && InputUtil.isKeyPressed(mc.getWindow().getHandle(), module.getKey())) {
                    module.toggle();
                }
            }
        });

        HudRenderCallback.EVENT.register(this::renderHud);
    }

    public void onTick() {
        for (Module module : modules) {
            if (module.isEnabled()) {
                module.onTick();
            }
        }
    }

    public void renderHud(DrawContext context, float tickDelta) {
        for (Module module : modules) {
            if (module.isEnabled()) {
                module.render(context);
            }
        }
    }

    public List<Module> getModules() {
        return modules;
    }

    public List<Module> getEnabledModules() {
        List<Module> enabledModules = new ArrayList<>();
        for (Module module : modules) {
            if (module.isEnabled()) {
                enabledModules.add(module);
            }
        }
        return enabledModules;
    }

    public Module getModuleByName(String name) {
        for (Module module : modules) {
            if (module.getName().equalsIgnoreCase(name)) {
                return module;
            }
        }
        return null;
    }
}