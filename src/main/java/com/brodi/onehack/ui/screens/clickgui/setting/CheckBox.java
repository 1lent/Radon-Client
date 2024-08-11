package com.brodi.onehack.ui.screens.clickgui.setting;

import com.brodi.onehack.module.settings.BooleanSetting;
import com.brodi.onehack.module.settings.Setting;
import com.brodi.onehack.ui.screens.clickgui.ModuleButton;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.MinecraftClient;

import java.awt.*;

public class CheckBox extends Component {

    private BooleanSetting boolSet;
    private final MinecraftClient mc = MinecraftClient.getInstance(); // Ensure you have this import

    public CheckBox(Setting setting, ModuleButton parent, int offset) {
        super(setting, parent, offset);
        if (setting instanceof BooleanSetting) {
            this.boolSet = (BooleanSetting) setting;
        } else {
            throw new IllegalArgumentException("Setting must be an instance of BooleanSetting");
        }
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        int x = parent.parent.x;
        int y = parent.parent.y + parent.offset + offset;
        int width = 20; // Adjust according to your design
        int height = 20; // Adjust according to your design

        // Draw the checkbox background
        context.fill(x, y, x + width, y + height, new Color(50, 50, 50, 160).getRGB());
        if (isHovered(mouseX, mouseY)) {
            context.fill(x, y, x + width, y + height, new Color(100, 100, 100, 200).getRGB()); // Highlight when hovered
        }

        // Draw the checkmark if enabled
        if (boolSet.isEnabled()) {
            context.fill(x + 4, y + 4, x + width - 4, y + height - 4, Color.green.getRGB());
        }

        //removed for now
        // Draw the setting name
        //context.drawText(mc.textRenderer, boolSet.getName(), x + height + 2, y + 2, Color.white.getRGB(), true);
    }

    @Override
    public void mouseClicked(double mouseX, double mouseY, int button) {
        if (isHovered(mouseX, mouseY) && button == 0) {
            boolSet.toggle();
        }
    }

    @Override
    public void mouseReleased(double mouseX, double mouseY, int button) {
        // Implement as needed
    }

    @Override
    public void updatePosition(double mouseX, double mouseY) {
        // Implement as needed
    }
}
