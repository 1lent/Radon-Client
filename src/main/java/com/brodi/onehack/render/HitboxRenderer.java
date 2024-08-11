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
            if (entity == mc.player) continue; // Skip rendering hitbox around the player themselves
            renderHitbox(entity, matrices, cameraX, cameraY, cameraZ);
        }
    }

    private static void renderHitbox(Entity entity, MatrixStack matrices, double cameraX, double cameraY, double cameraZ) {
        double x = entity.prevX + (entity.getX() - entity.prevX) * MinecraftClient.getInstance().getTickDelta();
        double y = entity.prevY + (entity.getY() - entity.prevY) * MinecraftClient.getInstance().getTickDelta();
        double z = entity.prevZ + (entity.getZ() - entity.prevZ) * MinecraftClient.getInstance().getTickDelta();

        Box boundingBox = entity.getBoundingBox().offset(-cameraX, -cameraY, -cameraZ);

        RenderSystem.disableDepthTest(); // Disable depth testing
        RenderSystem.setShaderColor(1.0f, 0.0f, 0.0f, 0.5f); // Set hitbox color (red in this case)

        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferBuilder = tessellator.getBuffer();
        bufferBuilder.begin(VertexFormat.DrawMode.LINES, VertexFormats.POSITION_COLOR);

        // Bottom face
        drawBoundingBoxLine(bufferBuilder, matrices, boundingBox.minX, boundingBox.minY, boundingBox.minZ, boundingBox.maxX, boundingBox.minY, boundingBox.minZ);
        drawBoundingBoxLine(bufferBuilder, matrices, boundingBox.maxX, boundingBox.minY, boundingBox.minZ, boundingBox.maxX, boundingBox.minY, boundingBox.maxZ);
        drawBoundingBoxLine(bufferBuilder, matrices, boundingBox.maxX, boundingBox.minY, boundingBox.maxZ, boundingBox.minX, boundingBox.minY, boundingBox.maxZ);
        drawBoundingBoxLine(bufferBuilder, matrices, boundingBox.minX, boundingBox.minY, boundingBox.maxZ, boundingBox.minX, boundingBox.minY, boundingBox.minZ);

        // Top face
        drawBoundingBoxLine(bufferBuilder, matrices, boundingBox.minX, boundingBox.maxY, boundingBox.minZ, boundingBox.maxX, boundingBox.maxY, boundingBox.minZ);
        drawBoundingBoxLine(bufferBuilder, matrices, boundingBox.maxX, boundingBox.maxY, boundingBox.minZ, boundingBox.maxX, boundingBox.maxY, boundingBox.maxZ);
        drawBoundingBoxLine(bufferBuilder, matrices, boundingBox.maxX, boundingBox.maxY, boundingBox.maxZ, boundingBox.minX, boundingBox.maxY, boundingBox.maxZ);
        drawBoundingBoxLine(bufferBuilder, matrices, boundingBox.minX, boundingBox.maxY, boundingBox.maxZ, boundingBox.minX, boundingBox.maxY, boundingBox.minZ);

        // Vertical lines
        drawBoundingBoxLine(bufferBuilder, matrices, boundingBox.minX, boundingBox.minY, boundingBox.minZ, boundingBox.minX, boundingBox.maxY, boundingBox.minZ);
        drawBoundingBoxLine(bufferBuilder, matrices, boundingBox.maxX, boundingBox.minY, boundingBox.minZ, boundingBox.maxX, boundingBox.maxY, boundingBox.minZ);
        drawBoundingBoxLine(bufferBuilder, matrices, boundingBox.maxX, boundingBox.minY, boundingBox.maxZ, boundingBox.maxX, boundingBox.maxY, boundingBox.maxZ);
        drawBoundingBoxLine(bufferBuilder, matrices, boundingBox.minX, boundingBox.minY, boundingBox.maxZ, boundingBox.minX, boundingBox.maxY, boundingBox.maxZ);

        tessellator.draw();

        RenderSystem.enableDepthTest(); // Re-enable depth testing
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f); // Reset the shader color to default
    }

    private static void drawBoundingBoxLine(BufferBuilder buffer, MatrixStack matrices, double x1, double y1, double z1, double x2, double y2, double z2) {
        buffer.vertex(matrices.peek().getPositionMatrix(), (float) x1, (float) y1, (float) z1).color(1.0f, 0.0f, 0.0f, 0.5f).next();
        buffer.vertex(matrices.peek().getPositionMatrix(), (float) x2, (float) y2, (float) z2).color(1.0f, 0.0f, 0.0f, 0.5f).next();
    }
}
