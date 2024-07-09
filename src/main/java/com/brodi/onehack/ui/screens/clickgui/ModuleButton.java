package com.brodi.onehack.ui.screens.clickgui;
import com.brodi.onehack.module.Mod.Category;
import net.minecraft.client.gui.DrawContext;
import com.brodi.onehack.module.Mod;
import com.brodi.onehack.ui.screens.clickgui.Frame;
import com.brodi.onehack.ui.Hud;
import net.minecraft.client.MinecraftClient;
import java.awt.*;

public class ModuleButton {
    protected MinecraftClient mc = MinecraftClient.getInstance();
    public Mod module;
    public Frame parent;
    public int offset;

    public ModuleButton(Mod module, Frame parent, int offset) {
        this.module = module;
        this.parent = parent;
        this.offset = offset;
    }

    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        context.fill(parent.x, parent.y + offset, parent.x + parent.width, parent.y + offset + parent.height, Color.black.getRGB());
        if (isHovered(mouseX, mouseY)) context.fill(parent.x, parent.y + offset, parent.x + parent.width, parent.y + offset + parent.height, Color.black.getRGB());
        context.drawText(mc.textRenderer, module.getName(), parent.x + 2, parent.y + offset + 2, - 1);
    }

    public void mouseClicked(double mouseX, double mouseY, int button) {
        if (isHovered((int) mouseX, (int) mouseY)) {
            if (button == 0) {
                module.toggle();
            } else {

            }
        }
    }

    public boolean isHovered(int mouseX, int mouseY) {
        return mouseX > parent.x && mouseX < parent.x + parent.width && mouseY > parent.y + offset && mouseY < parent.y + offset + parent.height;
    }

}
