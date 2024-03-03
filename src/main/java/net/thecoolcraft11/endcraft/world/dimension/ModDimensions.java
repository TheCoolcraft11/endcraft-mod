package net.thecoolcraft11.endcraft.world.dimension;

import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionOptions;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.dimension.DimensionTypes;
import net.thecoolcraft11.endcraft.Endcraft;

import java.util.OptionalLong;

public class ModDimensions {
    public static final RegistryKey<DimensionOptions> VOIDBORN_ABYSS = RegistryKey.of(RegistryKeys.DIMENSION,
            new Identifier(Endcraft.MOD_ID, "voidborn_abyss"));
    public static final RegistryKey<World> VOIDBORN_ABYSS_LEVEL_KEY = RegistryKey.of(RegistryKeys.WORLD,
            new Identifier(Endcraft.MOD_ID, "voidborn_abyss"));
    public static final RegistryKey<DimensionType> VOIDBORN_ABYSS_DIM_TYPE = RegistryKey.of(RegistryKeys.DIMENSION_TYPE,
            new Identifier(Endcraft.MOD_ID, "voidborn_abyss_type.json"));
    public static void bootstrapType(Registerable<DimensionType> context) {
        context.register(VOIDBORN_ABYSS_DIM_TYPE, new DimensionType(
                OptionalLong.of(12000), // fixedTime
                false, // hasSkylight
                false, // hasCeiling
                false, // ultraWarm
                true, // natural
                1.0, // coordinateScale
                false, // bedWorks
                false, // respawnAnchorWorks
                -128, // minY
                640, // height
                640, // logicalHeight
                BlockTags.INFINIBURN_OVERWORLD, // infiniburn
                DimensionTypes.OVERWORLD_ID, // effectsLocation
                1.0f, // ambientLight
                new DimensionType.MonsterSettings(false, false, UniformIntProvider.create(0, 0), 0)));
    }
}
