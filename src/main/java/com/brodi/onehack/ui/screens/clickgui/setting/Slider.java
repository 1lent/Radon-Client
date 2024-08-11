package com.brodi.onehack.ui.screens.clickgui.setting;

import com.brodi.onehack.module.settings.NumberSetting;
import com.brodi.onehack.module.settings.Setting;
import com.brodi.onehack.ui.screens.clickgui.ModuleButton;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;

import java.awt.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class Slider extends Component {

    private NumberSetting numSet;
    private boolean sliding = false;
    private final MinecraftClient mc = MinecraftClient.getInstance();
    private final TextRenderer textRenderer = mc.textRenderer;  // Adjust if textRenderer has a different name

    public Slider(Setting setting, ModuleButton parent, int offset) {
        super(setting, parent, offset);
        this.numSet = (NumberSetting) setting;
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        int x = parent.parent.x;
        int y = parent.parent.y + parent.offset + offset;
        int width = parent.parent.width;
        int height = parent.parent.height;

        // Draw the background with rounded corners
        context.fill(x, y, x + width, y + height, new Color(0, 0, 0, 160).getRGB());

        // Slider fill
        double mousePositionRelative = Math.min(width, Math.max(0, mouseX - x));
        int renderWidth = (int) (width * (numSet.getValue() - numSet.getMin()) / (numSet.getMax() - numSet.getMin()));
        context.fill(x, y, x + renderWidth, y + height, new Color(100, 150, 255).getRGB());

        // Draw text
        context.drawText(textRenderer, numSet.getName() + ": " + roundTo(numSet.getValue(), 2), x + 2, y + 2, -1, true);
    }

    @Override
    public void mouseClicked(double mouseX, double mouseY, int button) {
        if (isHovered(mouseX, mouseY) && button == 0) {
            sliding = true;
            updateSlider(mouseX);
        }
    }

    @Override
    public void mouseReleased(double mouseX, double mouseY, int button) {
        if (button == 0) {
            sliding = false;
        }
    }

    @Override
    public void updatePosition(double mouseX, double mouseY) {
        if (sliding) {
            updateSlider(mouseX);
        }
    }

    private void updateSlider(double mouseX) {
        int x = parent.parent.x;
        int width = parent.parent.width;
        double percentage = (mouseX - x) / width;
        double newValue = numSet.getMin() + (numSet.getMax() - numSet.getMin()) * percentage;
        numSet.setValue(Math.min(numSet.getMax(), Math.max(numSet.getMin(), newValue)));
    }

    private double roundTo(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();
        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
