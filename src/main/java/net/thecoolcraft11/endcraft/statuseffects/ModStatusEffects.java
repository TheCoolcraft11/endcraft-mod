package net.thecoolcraft11.endcraft.statuseffects;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.ExperienceDroppingBlock;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.thecoolcraft11.endcraft.Endcraft;

public class ModStatusEffects {

    public static final StatusEffect FLY = registerStatusEffect("fly",
            new FlyStatusEffect());
    public static final StatusEffect VOID = registerStatusEffect("void",
            new VoidStatusEffect());
    public static final StatusEffect SHADOW_VEIL = registerStatusEffect("shadow_veil",
            new ShadowStatusEffect());

    public static StatusEffect registerStatusEffect(String name, StatusEffect statusEffect) {
        return Registry.register(Registries.STATUS_EFFECT, new Identifier(Endcraft.MOD_ID, name), statusEffect);
    }

    public static void registerStatusEffects() {
        Endcraft.LOGGER.info("Registring Status Effects for " + Endcraft.MOD_ID);
    }
}
