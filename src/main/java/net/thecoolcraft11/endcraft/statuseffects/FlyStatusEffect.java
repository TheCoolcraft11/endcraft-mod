package net.thecoolcraft11.endcraft.statuseffects;

import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.thecoolcraft11.endcraft.Endcraft;

public class FlyStatusEffect extends StatusEffect {
    public FlyStatusEffect() {
        super(
                StatusEffectCategory.BENEFICIAL, // whether beneficial or harmful for entities
                0x98D982); // color in RGB
    }

    // This method is called every tick to check whether it should apply the status effect or not
    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        // In our case, we just make it return true so that it applies the status effect every tick.
        return true;
    }

    // This method is called when it applies the status effect. We implement custom functionality here.
    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        if (entity instanceof PlayerEntity) {
            if(shouldHaveFlyingEffect(((PlayerEntity) entity))) {
                ((PlayerEntity) entity).getAbilities().flying = true;
                ((PlayerEntity) entity).getAbilities().allowFlying = true;
                ((PlayerEntity) entity).getAbilities().setFlySpeed(0.1f * (amplifier + 1));
            }else {
                if (!(((PlayerEntity) entity).isCreative() || entity.isSpectator())) {
                ((PlayerEntity) entity).getAbilities().flying = false;
                ((PlayerEntity) entity).getAbilities().allowFlying = false;
                }
                ((PlayerEntity) entity).getAbilities().setFlySpeed(0.1f);
            }

        }
    }
    private boolean shouldHaveFlyingEffect(PlayerEntity player) {
        return  player.getStatusEffect(ModStatusEffects.FLY).getDuration() > 2;
    }
}

