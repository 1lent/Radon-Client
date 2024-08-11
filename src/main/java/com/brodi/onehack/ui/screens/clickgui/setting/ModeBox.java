package com.brodi.onehack.ui.screens.clickgui.setting;

import com.brodi.onehack.module.settings.ModeSetting;
import com.brodi.onehack.module.settings.Setting;
import com.brodi.onehack.ui.screens.clickgui.ModuleButton;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;

import java.awt.*;

public class ModeBox extends Component {

    private ModeSetting modeSet = (ModeSetting) setting;
    private final MinecraftClient mc = MinecraftClient.getInstance();

    public ModeBox(Setting setting, ModuleButton parent, int offset) {
        super(setting, parent, offset);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        context.fill(parent.parent.x, parent.parent.y + parent.parent.offset + offset,
                parent.parent.x + parent.parent.width,
                parent.parent.y + parent.parent.offset + offset + parent.parent.height,
                new Color(0, 0, 0, 160).getRGB());

        int textOffset = ((parent.parent.height / 2) - mc.textRenderer.fontHeight / 2);
        context.drawText(mc.textRenderer, modeSet.getName() + ": " + modeSet.getMode(),
                parent.parent.x + textOffset,
                parent.parent.y + parent.parent.offset + offset + textOffset,
                -1, true);
    }

    @Override
    public void mouseClicked(double mouseX, double mouseY, int button) {
        if (isHovered((int) mouseX, (int) mouseY) && button == 0) {
            modeSet.cycle();
        }
    }

    @Override
    public void mouseReleased(double mouseX, double mouseY, int button) {
    }

    @Override
    public void updatePosition(double mouseX, double mouseY) {


    }
}
