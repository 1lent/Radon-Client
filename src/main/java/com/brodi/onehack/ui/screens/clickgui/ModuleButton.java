package com.brodi.onehack.ui.screens.clickgui;

import com.brodi.onehack.module.Mod;
import com.brodi.onehack.module.settings.*;
import com.brodi.onehack.ui.screens.clickgui.setting.CheckBox;
import com.brodi.onehack.ui.screens.clickgui.setting.ColorPicker;
import com.brodi.onehack.ui.screens.clickgui.setting.Component;
import com.brodi.onehack.ui.screens.clickgui.setting.ModeBox;
import com.brodi.onehack.ui.screens.clickgui.setting.Slider;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class ModuleButton {
    public final Mod module;
    public final Frame parent;
    public int offset;
    final List<Component> components;
    boolean extended;

    public ModuleButton(Mod module, Frame parent, int offset) {
        this.module = module;
        this.parent = parent;
        this.offset = offset;
        this.components = new ArrayList<>();
        this.extended = false;

        int setOffset = parent.height;
        for (Setting setting : module.getSettings()) {
            if (setting instanceof BooleanSetting) {
                components.add(new CheckBox(setting, this, setOffset));
            } else if (setting instanceof ModeSetting) {
                components.add(new ModeBox(setting, this, setOffset));
            } else if (setting instanceof NumberSetting) {
                components.add(new Slider(setting, this, setOffset));
            } else if (setting instanceof ColorSetting) {
                components.add(new ColorPicker((ColorSetting) setting, this, setOffset));
            }
            setOffset += parent.height;
        }
    }

    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        int x = parent.x;
        int y = parent.y + offset;
        int width = parent.width;
        int height = parent.height;

        // Render the module button background and name
        context.fill(x, y, x + width, y + height, new Color(50, 50, 50, 160).getRGB());
        if (isHovered(mouseX, mouseY)) {
            context.fill(x, y, x + width, y + height, new Color(100, 100, 100, 200).getRGB()); // Highlight when hovered
        }
        context.drawText(MinecraftClient.getInstance().textRenderer, module.getName(), x + 2, y + 2, module.isEnabled() ? Color.red.getRGB() : Color.white.getRGB(), true);

        // Render the settings if extended is true
        if (extended) {
            int currentOffset = height;
            for (Component component : components) {
                component.offset = this.offset + currentOffset;
                component.render(context, mouseX, mouseY, delta);
                if (component instanceof ColorPicker && ((ColorPicker) component).isSelecting()) {
                    // Adjust the current offset based on the size of the expanded ColorPicker
                    currentOffset += 70; // This is the approximate height of the color picker when expanded
                } else {
                    currentOffset += height;
                }
            }
        }
    }

    public void mouseClicked(double mouseX, double mouseY, int button) {
        if (isHovered((int) mouseX, (int) mouseY)) {
            if (button == 0) { // Left-click toggles the module
                module.toggle();
            } else if (button == 1) { // Right-click toggles the settings
                extended = !extended;
                parent.updateButtons(); // Ensure buttons are updated with new offsets
            }
        }
        if (extended) {
            for (Component component : components) {
                component.mouseClicked(mouseX, mouseY, button);
            }
        }
    }

    public void mouseReleased(double mouseX, double mouseY, int button) {
        for (Component component : components) {
            component.mouseReleased(mouseX, mouseY, button);
        }
    }

    public boolean isHovered(int mouseX, int mouseY) {
        int x = parent.x;
        int y = parent.y + offset;
        int width = parent.width;
        int height = parent.height;
        return mouseX > x && mouseX < x + width && mouseY > y && mouseY < y + height;
    }
}
