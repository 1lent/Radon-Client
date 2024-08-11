package com.brodi.onehack.ui.screens.clickgui.setting;

import com.brodi.onehack.module.settings.Setting;
import com.brodi.onehack.ui.screens.clickgui.ModuleButton;
import net.minecraft.client.gui.DrawContext;

public abstract class Component {

    public final Setting setting;
    protected final ModuleButton parent;
    public int offset;

    public Component(Setting setting, ModuleButton parent, int offset) {
        this.setting = setting;
        this.parent = parent;
        this.offset = offset;
    }

    public abstract void render(DrawContext context, int mouseX, int mouseY, float delta);

    public abstract void mouseClicked(double mouseX, double mouseY, int button);

    public abstract void mouseReleased(double mouseX, double mouseY, int button);

    public abstract void updatePosition(double mouseX, double mouseY);

    public boolean isHovered(double mouseX, double mouseY) {
        int x = parent.parent.x;
        int y = parent.parent.y + parent.offset + offset;
        int width = parent.parent.width;
        int height = parent.parent.height;
        return mouseX > x && mouseX < x + width && mouseY > y && mouseY < y + height;
    }
}
