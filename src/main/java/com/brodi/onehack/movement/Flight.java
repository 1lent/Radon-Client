package com.brodi.onehack.movement;
import com.brodi.onehack.module.settings.BooleanSetting;
import com.brodi.onehack.module.settings.ModeSetting;
import com.brodi.onehack.module.settings.NumberSetting;
import org.lwjgl.glfw.GLFW;
import net.minecraft.client.MinecraftClient;
import com.brodi.onehack.module.Mod;

public class Flight extends Mod {


    public NumberSetting speed = new NumberSetting("Speed", 0 , 10, 1, 0.1);
    public BooleanSetting testBool = new BooleanSetting("Check", true) ;
    public ModeSetting testMode = new ModeSetting("Mode", "test", "test 2", "test 3");

    public Flight() {
        super("Flight", "Allows you to fly", Category.MOVEMENT);
        this.setKey(GLFW.GLFW_KEY_L);
        addSettings(speed, testBool, testMode);
    }

    @Override
    public void onTick() {
        mc.player.getAbilities().flying = true;
        super.onTick();
        mc.player.getAbilities().setFlySpeed(speed.getValueFloat());
    }

    @Override
    public void onEnable() {
        mc.player.getAbilities().setFlySpeed(0.1F);
        super.onEnable();
    }

    @Override
    public void onDisable() {
        mc.player.getAbilities().setFlySpeed(0.05F);
        mc.player.getAbilities().flying = false;
        super.onTick();

    }
}
