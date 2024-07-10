package com.brodi.onehack.ui.screens.clickgui.setting;

import com.brodi.onehack.module.settings.BooleanSetting;
import com.brodi.onehack.module.settings.Setting;
import com.brodi.onehack.ui.screens.clickgui.ModuleButton;
import com.brodi.onehack.module.settings.Setting;
import net.minecraft.client.gui.DrawContext;

import java.awt.*;

public class CheckBox extends Component{

    private BooleanSetting boolSet = (BooleanSetting)setting;

    public CheckBox(Setting setting, ModuleButton parent, int offset) {
        super(setting, parent, offset);
        this.boolSet = (BooleanSetting)setting;
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        context.fill(parent.parent.x, parent.parent.y + parent.offset + offset, parent.parent.x + parent.parent.width, parent.parent.y + parent.offset + offset + parent.parent.height, new Color(0,0,0,160).getRGB());
        int textOffset = ((parent.parent.height / 2) - parent.mc.textRenderer.fontHeight / 2);
        context.drawText(mc.textRenderer, boolSet.getName() + ":" + boolSet.isEnabled(), parent.parent.x + textOffset, parent.parent.y + parent.offset + offset + textOffset, -1, true);
        super.render(context, mouseX, mouseY, delta);
    }

    @Override
    public void mouseClicked(double mouseX, double mouseY, int button) {
        if (isHovered((int) mouseX, (int) mouseY) && button == 0) {
            boolSet.toggle();
        }
            super.mouseClicked(mouseX, mouseY, button);

    }

}
