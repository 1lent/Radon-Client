package com.brodi.onehack.render;

import com.brodi.onehack.module.Mod;
import com.brodi.onehack.module.ModuleManager;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.BufferBuilder;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.VertexFormat;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.Box;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderContext;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;
import org.lwjgl.opengl.GL11;

public class HitboxRenderer extends Mod {

    public HitboxRenderer() {
        super("HitboxRenderer", "Renders hitboxes around entities", Category.RENDER);
    }

    @Override
    public void onEnable() {
        WorldRenderEvents.BEFORE_DEBUG_RENDER.register(HitboxRenderer::onRenderWorld);
    }

    @Override
    public void onDisable() {

    }

    public static void onRenderWorld(WorldRenderContext context) {
        HitboxRenderer instance = (HitboxRenderer) ModuleManager.INSTANCE.getModuleByName("HitboxRenderer");
        if (instance != null && instance.isEnabled()) {
            renderHitboxes(context);
        }
    }

    private static void renderHitboxes(WorldRenderContext context) {
        MinecraftClient mc = MinecraftClient.getInstance();

        if (mc.world == null || mc.player == null) {
            return;
        }

        MatrixStack matrices = context.matrixStack();
        double cameraX = context.camera().getPos().getX();
        double cameraY = context.camera().getPos().getY();
        double cameraZ = context.camera().getPos().getZ();

        for (Entity entity : mc.world.getEntities()) {
            if (entity == mc.player) continue; // Skip player's hitbox

            // Interpolate entity position for smoother rendering
            double x = entity.prevX + (entity.getX() - entity.prevX) * mc.getTickDelta();
            double y = entity.prevY + (entity.getY() - entity.prevY) * mc.getTickDelta();
            double z = entity.prevZ + (entity.getZ() - entity.prevZ) * mc.getTickDelta();

            Box boundingBox = entity.getBoundingBox().offset(-cameraX, -cameraY, -cameraZ);

            renderBoundingBox(matrices, boundingBox);
        }
    }

    private static void renderBoundingBox(MatrixStack matrices, Box boundingBox) {
        RenderSystem.disableDepthTest(); // Disable depth testing for hitboxes
        RenderSystem.setShaderColor(1.0f, 0.0f, 0.0f, 0.5f); // Red color with transparency

        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferBuilder = tessellator.getBuffer();
        bufferBuilder.begin(VertexFormat.DrawMode.LINES, VertexFormats.POSITION_COLOR);

        // Draw lines for the bounding box (simplified for clarity)
        // You can customize the line drawing based on your needs
        drawBoxLine(bufferBuilder, matrices, boundingBox.minX, boundingBox.minY, boundingBox.minZ, boundingBox.maxX, boundingBox.minY, boundingBox.maxZ);

        tessellator.draw();

        RenderSystem.enableDepthTest(); // Re-enable depth testing
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f); // Reset color
    }

    private static void drawBoxLine(BufferBuilder buffer, MatrixStack matrices, double x1, double y1, double z1, double x2, double y2, double z2) {
        buffer.vertex(matrices.peek().getPositionMatrix(), (float) x1, (float) y1, (float) z1).color(1.0f, 0.0f, 0.0f, 0.5f).next();
        buffer.vertex(matrices.peek().getPositionMatrix(), (float) x2, (float) y2, (float) z2).color(1.0f, 0.0f, 0.0f, 0.5f).next();
    }
}
