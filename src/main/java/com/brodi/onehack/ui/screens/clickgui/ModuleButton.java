package com.brodi.onehack.ui.screens.clickgui;
import com.brodi.onehack.module.Mod.Category;
import com.brodi.onehack.module.settings.BooleanSetting;
import com.brodi.onehack.module.settings.ModeSetting;
import com.brodi.onehack.module.settings.NumberSetting;
import com.brodi.onehack.module.settings.Setting;
import com.brodi.onehack.ui.screens.clickgui.setting.CheckBox;
import com.brodi.onehack.ui.screens.clickgui.setting.ModeBox;
import com.brodi.onehack.ui.screens.clickgui.setting.Slider;
import com.mojang.datafixers.types.templates.Check;
import net.minecraft.client.gui.DrawContext;
import com.brodi.onehack.module.Mod;
import com.brodi.onehack.ui.screens.clickgui.Frame;
import com.brodi.onehack.ui.Hud;
import com.brodi.onehack.ui.screens.clickgui.setting.Component;
import net.minecraft.client.MinecraftClient;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class ModuleButton {
    public MinecraftClient mc = MinecraftClient.getInstance();
    public Mod module;
    public Frame parent;
    public int offset;

    public List<Component> components;
    public boolean extended;


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
            }
            setOffset += parent.height;
        }
    }

    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        context.fill(parent.x, parent.y + offset, parent.x + parent.width, parent.y + offset + parent.height, new Color(0, 0, 0, 160).getRGB());
        if (isHovered(mouseX, mouseY))
            context.fill(parent.x, parent.y + offset, parent.x + parent.width, parent.y + offset + parent.height, new Color(0, 0, 0, 160).getRGB());

        context.drawText(mc.textRenderer, module.getName(), parent.x + 2, parent.y + offset + 2, module.isEnabled() ? Color.red.getRGB() : -1, true);

        if (extended) {
            for (Component component : components) {
                component.render(context, mouseX, mouseY, delta);
            }

        }
    }

    public void mouseClicked(double mouseX, double mouseY, int button) {
        if (isHovered((int) mouseX, (int) mouseY)) {
            if (button == 0) {
                module.toggle();
            } else if (button == 1) {
                extended = !extended;
                parent.updateButtons();
            }

        }
        for (Component component : components) {
            component.mouseClicked(mouseX, mouseY, button);
        }
    }

    public void mouseReleased(double mouseX, double mouseY, int button) {
        for (Component component : components) {
            component.mouseReleased(mouseX, mouseY, button);
    }
        }


        public boolean isHovered(int mouseX, int mouseY) {
        return mouseX > parent.x && mouseX < parent.x + parent.width && mouseY > parent.y + offset && mouseY < parent.y + offset + parent.height;
    }

}
