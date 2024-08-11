package com.brodi.onehack.ui.screens.clickgui;

import com.brodi.onehack.module.Mod.Category;
import com.brodi.onehack.module.ModuleManager;
import com.brodi.onehack.ui.screens.clickgui.setting.ColorPicker;
import com.brodi.onehack.ui.screens.clickgui.setting.Component;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.util.math.MatrixStack;
import com.brodi.onehack.module.Mod;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class Frame {

    public int x, y, width, height, dragX, dragY;
    public Category category;

    public boolean dragging, extended;
    public int offset;

    private List<ModuleButton> buttons;
    private final MinecraftClient mc;

    public Frame(Category category, int x, int y, int width, int height) {
        this.mc = MinecraftClient.getInstance();
        this.y = y;
        this.x = x;
        this.width = width;
        this.height = height;
        this.category = category;
        this.dragging = false;
        this.extended = false;

        buttons = new ArrayList<>();

        int offset = height;
        for (Mod mod : ModuleManager.INSTANCE.getModulesInCategory(category)) {
            buttons.add(new ModuleButton(mod, this, offset));
            offset += height;
        }
    }

    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        if (extended) {
            drawBlurredBackground(context);
        }

        // Draw the frame with gradient
        context.fillGradient(x, y, x + width, y + height, Color.darkGray.getRGB(), Color.black.getRGB());
        int offset = ((height / 2) - mc.textRenderer.fontHeight / 2);

        context.drawText(mc.textRenderer, category.name, x + offset, y + offset, Color.white.getRGB(), true);
        context.drawText(mc.textRenderer, extended ? "-" : "+", x + width - offset - 2 - mc.textRenderer.getWidth("+"), y + offset, Color.white.getRGB(), true);

        if (extended) {
            for (ModuleButton button : buttons) {
                button.render(context, mouseX, mouseY, delta);
            }
        }
    }

    private void drawBlurredBackground(DrawContext context) {
        MatrixStack matrixStack = context.getMatrices();
        matrixStack.push();

        // Get screen dimensions
        int screenWidth = mc.getWindow().getWidth();
        int screenHeight = mc.getWindow().getHeight();

        // Set up the semi-transparent overlay
        context.fill(0, 0, screenWidth, screenHeight, new Color(0, 0, 0, 150).getRGB());

        matrixStack.pop();
    }

    public void mouseClicked(double mouseX, double mouseY, int button) {
        if (isHovered(mouseX, mouseY)) {
            if (button == 0) {
                dragging = true;
                dragX = (int) (mouseX - x);
                dragY = (int) (mouseY - y);
            } else if (button == 1) {
                extended = !extended;
            }
        }
        if (extended) {
            for (ModuleButton mb : buttons) {
                mb.mouseClicked(mouseX, mouseY, button);
            }
        }
    }

    public void mouseReleased(double mouseX, double mouseY, int button) {
        if (button == 0 && dragging) dragging = false;

        for (ModuleButton mb : buttons) {
            mb.mouseReleased(mouseX, mouseY, button);
        }
    }

    public boolean isHovered(double mouseX, double mouseY) {
        return mouseX > x && mouseX < x + width && mouseY > y && mouseY < y + height;
    }

    public void updatePosition(double mouseX, double mouseY) {
        if (dragging) {
            x = (int) (mouseX - dragX);
            y = (int) (mouseY - dragY);
        }
    }

    public void updateButtons() {
        int offset = height;
        for (ModuleButton button : buttons) {
            button.offset = offset;
            offset += height;

            if (button.extended) {
                for (Component component : button.components) {
                    if (component instanceof ColorPicker && ((ColorPicker) component).isSelecting()) {
                        offset += 70; // Adjust the offset for the expanded ColorPicker
                    } else {
                        offset += height;
                    }
                }
            }
        }
    }
}
