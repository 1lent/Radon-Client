package com.brodi.radonclient.gui;

import com.brodi.radonclient.modules.Module;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.List;

public class ModernClickGuiScreen extends Screen {
    private final List<Module> modules;
    private final List<ButtonWidget> buttons = new ArrayList<>();
    private static final int BUTTON_WIDTH = 120;
    private static final int BUTTON_HEIGHT = 20;
    private static final int PADDING = 5;

    public ModernClickGuiScreen(List<Module> modules) {
        super(Text.literal("OneHack Menu"));
        this.modules = modules;
    }

    @Override
    protected void init() {
        buttons.clear();
        int startX = (width - BUTTON_WIDTH) / 2;
        int startY = 50;

        for (int i = 0; i < modules.size(); i++) {
            Module module = modules.get(i);
            int x = startX;
            int y = startY + (BUTTON_HEIGHT + PADDING) * i;

            ButtonWidget button = ButtonWidget.builder(Text.literal(getButtonText(module)), b -> {
                        module.toggle();
                        b.setMessage(Text.literal(getButtonText(module)));
                    })
                    .dimensions(x, y, BUTTON_WIDTH, BUTTON_HEIGHT)
                    .build();

            buttons.add(button);
            addDrawableChild(button);
        }
    }

    private String getButtonText(Module module) {
        return module.getName() + (module.isEnabled() ? " §a[ON]" : " §c[OFF]");
    }


    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        renderBackground(context);
        context.fill(0, 0, width, height, 0x80000000);  // Semi-transparent background

        context.drawCenteredTextWithShadow(textRenderer, title, width / 2, 20, 0xFFFFFF);

        super.render(context, mouseX, mouseY, delta);

        for (ButtonWidget button : buttons) {
            if (button.isHovered()) {
                String moduleName = button.getMessage().getString().split(" ")[0];
                Module module = modules.stream()
                        .filter(m -> m.getName().equals(moduleName))
                        .findFirst()
                        .orElse(null);
                if (module != null) {
                    context.drawTooltip(textRenderer, Text.literal(module.getDescription()), mouseX, mouseY);
                }
            }
        }
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        boolean result = super.mouseClicked(mouseX, mouseY, button);
        for (ButtonWidget b : buttons) {
            if (b.isHovered()) {
                b.onClick(mouseX, mouseY);
                return true;
            }
        }
        return result;
    }

    @Override
    public boolean shouldPause() {
        return false;
    }
}