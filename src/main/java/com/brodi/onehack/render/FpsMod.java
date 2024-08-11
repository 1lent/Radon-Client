package com.brodi.onehack.render;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import com.brodi.onehack.module.Mod;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.ScreenPos;
import org.lwjgl.glfw.GLFW;



public class FpsMod extends Mod {

    int fps = mc.getCurrentFps();
    private ScreenPos pos;
    String fpsText = "FPS: " + fps;

    static MinecraftClient mc = MinecraftClient.getInstance();
    public FpsMod() {
        super("FPS", "shows fps", Category.RENDER);
        this.setKey(GLFW.GLFW_KEY_G);

    }
    public static void render(DrawContext context, float tickDelta) {
    }

    @Override
    public void onTick() {
        int index = 0;
        int sWidth = mc.getWindow().getScaledWidth();
        HudRenderCallback.EVENT.register((drawContext, tickDelta) -> {
            drawContext.drawText(mc.textRenderer, "FPS: " + mc.getCurrentFps(), mc.currentScreen.width - 150, 0, -1, true);
            System.out.println(mc.getWindow().getWidth());
        });

    }

    @Override
    public void onEnable() {
    }









}
