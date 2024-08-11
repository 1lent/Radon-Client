package com.brodi.onehack.ui;

import com.brodi.onehack.module.Mod;
import com.brodi.onehack.module.ModuleManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;

import java.util.Comparator;
import java.util.List;

public class Hud {

    private static final MinecraftClient mc = MinecraftClient.getInstance();
    private static final int MARGIN = 10; // Margin from screen edge
    private static final int TEXT_COLOR = -1; // Default text color
    private static final int TEXT_SPACING = 2; // Spacing between text lines

    public static void render(DrawContext context, float tickDelta) {
        renderArrayList(context);
    }

    public static void renderArrayList(DrawContext context) {
        int index = 0;
        int screenWidth = mc.getWindow().getScaledWidth();
        int fontHeight = mc.textRenderer.fontHeight;

        // Get and sort enabled modules by display name width (largest first)
        List<Mod> enabledModules = ModuleManager.INSTANCE.getEnabledModules();
        enabledModules.sort(Comparator.comparingInt(mod -> -mc.textRenderer.getWidth(mod.getDisplayName())));

        for (Mod mod : enabledModules) {
            String displayName = mod.getDisplayName();
            int textWidth = mc.textRenderer.getWidth(displayName);

            // Draw the module name, aligned to the right
//            context.drawText(mc.textRenderer, displayName, screenWidth - MARGIN - textWidth, MARGIN + (index * (fontHeight + TEXT_SPACING)), TEXT_COLOR, true);
//            index++;
        }
    }
}
