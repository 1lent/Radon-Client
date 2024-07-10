package com.brodi.onehack.ui.screens.clickgui.setting;

import com.brodi.onehack.module.settings.BooleanSetting;
import com.brodi.onehack.module.settings.ModeSetting;
import com.brodi.onehack.module.settings.Setting;
import com.brodi.onehack.ui.screens.clickgui.ModuleButton;
import net.minecraft.client.gui.DrawContext;

import java.awt.*;

public class ModeBox extends Component {

    private ModeSetting modeSet = (ModeSetting) setting;

    public ModeBox(Setting setting, ModuleButton parent, int offset) {
        super(setting, parent, offset);

    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        context.fill(parent.parent.x, parent.parent.y + parent.offset + offset, parent.parent.x + parent.parent.width, parent.parent.y + parent.offset + offset + parent.parent.height, new Color(0,0,0,160).getRGB());

        int textOffset = ((parent.parent.height / 2) - parent.mc.textRenderer.fontHeight / 2);
        context.drawText(mc.textRenderer, modeSet.getName() + ":" + modeSet.getMode(), parent.parent.x + textOffset, parent.parent.y + parent.offset + offset + textOffset, -1, true);
        super.render(context, mouseX, mouseY, delta);
    }

    @Override
    public void mouseClicked(double mouseX, double mouseY, int button) {
        if (isHovered((int) mouseX, (int) mouseY) && button == 00) modeSet.cycle();
        super.mouseClicked(mouseX, mouseY, button);
    }
}
