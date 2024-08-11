package com.brodi.onehack.render;

import com.brodi.onehack.module.Mod;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.particle.GlowParticle;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

public class PlayerESP extends Mod {

    public PlayerESP() {
        super("PlayerESP", "See players through GLow", Category.RENDER);
    }
    @Override
    public void onTick() {
        super.onTick();


    }

    @Override
    public void onEnable() {
        for (AbstractClientPlayerEntity player : mc.world.getPlayers()) {
            mc.worldRenderer.reload();
            //player.addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 1000, 1));
            mc.player.setGlowing(true);
        }
        super.onEnable();
    }

    @Override
    public void onDisable() {
        for (AbstractClientPlayerEntity player : mc.world.getPlayers()) {
            player.removeStatusEffect(StatusEffects.GLOWING);
            player.setGlowing(false);
        }

        // For abstractCLientplayerentity player out of mc.world.getplayers: means looping over all the players in the world
        super.onTick();

    }

}