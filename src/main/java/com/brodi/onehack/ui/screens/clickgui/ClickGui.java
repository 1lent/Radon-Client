package com.brodi.onehack.ui.screens.clickgui;

import com.brodi.onehack.module.Mod.Category;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.List;

public class ClickGui extends Screen {

    public static final ClickGui INSTANCE = new ClickGui();

    private List<Frame> frames;

    private ClickGui() {
        super(Text.literal("ClickGui"));

        frames = new ArrayList<>();

        int offset = 20;
        for (Category category : Category.values()) {
            frames.add(new Frame(category, offset, 30, 100, 30));
            offset += 120;
        }

    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        for (Frame frame : frames) {
            frame.render(context, mouseX, mouseY, delta);
        }
            super.render(context, mouseX, mouseY, delta);
        }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        for (Frame frame : frames) {
            frame.mouseClicked(mouseX, mouseY, button);
        }
            return super.mouseClicked(mouseX, mouseY, button);


        }
}
