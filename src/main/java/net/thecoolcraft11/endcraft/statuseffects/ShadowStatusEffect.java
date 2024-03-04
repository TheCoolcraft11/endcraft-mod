package net.thecoolcraft11.endcraft.statuseffects;

import com.mojang.brigadier.Command;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.command.CommandSource;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.command.GameModeCommand;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.hit.HitResult.Type;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.GameMode;
import net.thecoolcraft11.endcraft.util.Raycast;

public class ShadowStatusEffect extends StatusEffect {
    public ShadowStatusEffect() {
        super(
                StatusEffectCategory.BENEFICIAL, // whether beneficial or harmful for entities
                0x000000); // color in RGB
    }

    // This method is called every tick to check whether it should apply the status effect or not
    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        // In our case, we just make it return true so that it applies the status effect every tick.
        return duration % 20 == 0;
    }

    // This method is called when it applies the status effect. We implement custom functionality here.
    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        if (entity instanceof PlayerEntity) {
            if(shouldHaveShadowEffect(((PlayerEntity) entity))) {
                 ((PlayerEntity) entity).setInvisible(true);
                 ((PlayerEntity) entity).setInvulnerable(true);
                 ((PlayerEntity) entity).getAbilities().flying = true;
                 ((PlayerEntity) entity).getAbilities().allowFlying = true;
                 ((PlayerEntity) entity).getAbilities().invulnerable = true;
                 StatusEffectInstance effect = new StatusEffectInstance(StatusEffects.BLINDNESS, 20, 0, false, false);
                 ((PlayerEntity)entity).addStatusEffect(effect);
            }else {
                ((PlayerEntity) entity).setInvisible(false);
                ((PlayerEntity) entity).setInvulnerable(false);
                ((PlayerEntity) entity).getAbilities().flying = false;
                ((PlayerEntity) entity).getAbilities().allowFlying = false;
                ((PlayerEntity) entity).getAbilities().invulnerable = false;
                ((PlayerEntity) entity).removeStatusEffect(StatusEffects.BLINDNESS);
            }
        }
    }
    private boolean shouldHaveShadowEffect(PlayerEntity player) {
        return  player.getStatusEffect(ModStatusEffects.SHADOW_VEIL).getDuration() > 20;
    }
}

