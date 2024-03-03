package net.thecoolcraft11.endcraft.world.biome.surface;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.world.gen.surfacebuilder.MaterialRules;
import net.thecoolcraft11.endcraft.block.ModBlocks;
import net.thecoolcraft11.endcraft.world.biome.ModBiomes;

public class ModMaterialRules {
    private static final MaterialRules.MaterialRule END_STONE = makeStateRule(Blocks.END_STONE);
    private static final MaterialRules.MaterialRule END_GATEWAY = makeStateRule(Blocks.END_GATEWAY);
    private static final MaterialRules.MaterialRule FAKE_BLOCK = makeStateRule(ModBlocks.FAKE_BLOCK);

    public static MaterialRules.MaterialRule makeRules() {
        MaterialRules.MaterialCondition isAtOrAboveWaterLevel = MaterialRules.water(-1, 0);

        MaterialRules.MaterialRule grassSurface = MaterialRules.sequence(MaterialRules.condition(isAtOrAboveWaterLevel, END_STONE), END_STONE);

        return MaterialRules.sequence(
                MaterialRules.sequence(MaterialRules.condition(MaterialRules.biome(ModBiomes.VOIDBORN_ABYSS_BIOME),
                                MaterialRules.condition(MaterialRules.STONE_DEPTH_FLOOR, END_GATEWAY)),
                        MaterialRules.condition(MaterialRules.STONE_DEPTH_CEILING, FAKE_BLOCK)),

                // Default to a grass and dirt surface
                MaterialRules.condition(MaterialRules.STONE_DEPTH_FLOOR, grassSurface)
        );
    }

    private static MaterialRules.MaterialRule makeStateRule(Block block) {
        return MaterialRules.block(block.getDefaultState());
    }
}