package net.thecoolcraft11.endcraft.damagetypes;

import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import net.thecoolcraft11.endcraft.Endcraft;

public class ModDamageTypes {

    public static final RegistryKey<DamageType> ESSENCE_AREA_REMOVED = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, new Identifier(Endcraft.MOD_ID, "death.essence.removed_area"));
    public static final RegistryKey<DamageType> SWAM_VOID = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, new Identifier(Endcraft.MOD_ID, "death.swam_void"));

    public static DamageSource of(World world, RegistryKey<DamageType> key) {
        return new DamageSource(world.getRegistryManager().get(RegistryKeys.DAMAGE_TYPE).entryOf(key));
    }
}
