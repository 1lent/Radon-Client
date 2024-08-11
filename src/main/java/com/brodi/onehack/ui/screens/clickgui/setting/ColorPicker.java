package com.brodi.onehack.ui.screens.clickgui.setting;

import com.brodi.onehack.module.settings.ColorSetting;
import com.brodi.onehack.ui.screens.clickgui.ModuleButton;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;

import java.awt.Color;

public class ColorPicker extends Component {

    private final ColorSetting setting;
    private boolean isSelecting;

    private int redValue;
    private int greenValue;
    private int blueValue;

    private final int sliderWidth = 100;
    private final int sliderHeight = 5;

    public ColorPicker(ColorSetting setting, ModuleButton parent, int offset) {
        super(setting, parent, offset);
        this.setting = setting;
        this.isSelecting = false;

        // Initialize RGB values from the current color setting
        Color initialColor = setting.getValue();
        this.redValue = initialColor.getRed();
        this.greenValue = initialColor.getGreen();
        this.blueValue = initialColor.getBlue();
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        int x = parent.parent.x;
        int y = parent.parent.y + offset;
        int width = parent.parent.width;
        int height = parent.parent.height;

        // Draw the current color display
        context.fill(x, y, x + width, y + height, new Color(redValue, greenValue, blueValue).getRGB());
        context.drawText(MinecraftClient.getInstance().textRenderer, setting.getName(), x + 2, y + 2, Color.WHITE.getRGB(), true);

        if (isSelecting) {
            // Render RGB sliders
            renderSlider(context, "R", x, y + height + 10, redValue, mouseX, mouseY);
            renderSlider(context, "G", x, y + height + 30, greenValue, mouseX, mouseY);
            renderSlider(context, "B", x, y + height + 50, blueValue, mouseX, mouseY);
        }
    }

    private void renderSlider(DrawContext context, String label, int x, int y, int value, int mouseX, int mouseY) {
        int sliderX = x + 20;

        // Draw slider background
        context.fill(sliderX, y, sliderX + sliderWidth, y + sliderHeight, Color.DARK_GRAY.getRGB());

        // Draw slider fill
        int sliderFill = (int) ((value / 255.0) * sliderWidth);
        context.fill(sliderX, y, sliderX + sliderFill, y + sliderHeight, Color.GRAY.getRGB());

        // Draw the label and value
        context.drawText(MinecraftClient.getInstance().textRenderer, label + ": " + value, x, y - 2, Color.WHITE.getRGB(), true);

        // Handle slider interaction
        if (isHoveredOverSlider(mouseX, mouseY, sliderX, y)) {
            int newValue = (int) ((mouseX - sliderX) / (float) sliderWidth * 255);
            newValue = Math.max(0, Math.min(255, newValue)); // Clamp value between 0 and 255
            switch (label) {
                case "R":
                    redValue = newValue;
                    break;
                case "G":
                    greenValue = newValue;
                    break;
                case "B":
                    blueValue = newValue;
                    break;
            }
            setting.setValue(new Color(redValue, greenValue, blueValue));
        }
    }

    private boolean isHoveredOverSlider(int mouseX, int mouseY, int sliderX, int sliderY) {
        return mouseX >= sliderX && mouseX <= sliderX + sliderWidth && mouseY >= sliderY && mouseY <= sliderY + sliderHeight;
    }

    @Override
    public void mouseClicked(double mouseX, double mouseY, int button) {
        if (isHovered(mouseX, mouseY)) {
            isSelecting = !isSelecting;
        }
    }

    @Override
    public void mouseReleased(double mouseX, double mouseY, int button) {
        // Stop selecting on mouse release
        isSelecting = false;
    }

    @Override
    public boolean isHovered(double mouseX, double mouseY) {
        int x = parent.parent.x;
        int y = parent.parent.y + offset;
        int width = parent.parent.width;
        int height = parent.parent.height;
        return mouseX > x && mouseX < x + width && mouseY > y && mouseY < y + height;
    }

    @Override
    public void updatePosition(double mouseX, double mouseY) {
        // No specific position update logic needed for this component
    }

    public boolean isSelecting() {
        return isSelecting;
    }

}
