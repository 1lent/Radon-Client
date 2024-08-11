package com.brodi.onehack.movement;
import com.brodi.onehack.module.Mod;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.damage.DamageSource;
import org.lwjgl.glfw.GLFW;

public class NoFall extends Mod {
    protected MinecraftClient mc = MinecraftClient.getInstance();

    public NoFall(String name, String description, Category category) {
        super("No fall", "Take no fall damage", Category.MOVEMENT);
        this.setKey(GLFW.GLFW_KEY_N);
    }

    @Override
    public void onTick() {
//        if(this.isEnabled()) {
     //       if (mc.player.handleFallDamage(2F, 0F,damagesource.fall)) {
            }
        }
    //}



    //    @Override
     //   public void onEnable() {

  //  }
//
   // @Override
   // public void onDisable() {

    //}

//}///
