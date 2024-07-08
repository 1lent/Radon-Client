package com.brodi.onehack.ui.screens.clickgui;

import com.brodi.onehack.module.Mod.Category;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.Drawable;

public class Frame {

    public int x, y, width, height;
    public Category category;

    public boolean dragging;

    private MinecraftClient mc = MinecraftClient.getInstance();

    public Frame(Category category, int x, int y, int width, int height) {
        this.y = y;
        this.x = x;
        this.width = width;
        this.height = height;
        this.category = category;
        this.dragging = false;

    }

    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        DrawableHelper.fill(context, x, y, x+width, y+height, -1);
        mc.textRenderer.drawWithShadow(context, category.name(), x + 2, y + 2, Color.black.getRGB());
    }

    public void mouseClicked(double mouseX, double mouseY, int button) {

    }
}
