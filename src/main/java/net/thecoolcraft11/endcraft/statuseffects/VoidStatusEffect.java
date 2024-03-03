package net.thecoolcraft11.endcraft.statuseffects;


import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;
import net.thecoolcraft11.endcraft.block.ModBlocks;
import net.thecoolcraft11.endcraft.block.custom.VoidLayerBlock;
import net.thecoolcraft11.endcraft.damagetypes.ModDamageTypes;

public class VoidStatusEffect extends StatusEffect {
    public VoidStatusEffect() {
        super(
                StatusEffectCategory.HARMFUL, // whether beneficial or harmful for entities
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
        entity.damage(ModDamageTypes.of(entity.getWorld(), ModDamageTypes.SWAM_VOID), 5f * amplifier);
        if (!entity.isSpectator()) {
            entity.move(MovementType.PLAYER, new Vec3d(0,-1,0));

        if(entity.getWorld().getBlockState(entity.getBlockPos()).getBlock() == ModBlocks.VOID_LAYER) {
            int layer = entity.getWorld().getBlockState(entity.getBlockPos()).get(VoidLayerBlock.LAYERS);
            if(layer < 8) {
                entity.getWorld().setBlockState(entity.getBlockPos(), ModBlocks.VOID_LAYER.getDefaultState().with(VoidLayerBlock.LAYERS, (layer + 1)), 3);
            }
        }else {
            if (entity.getWorld().getBlockState(entity.getBlockPos()).getBlock() != ModBlocks.VOID_FLUID) {
                entity.getWorld().setBlockState(entity.getBlockPos(), ModBlocks.VOID_LAYER.getDefaultState(), 3);
                }
            }
        }
    }
}

