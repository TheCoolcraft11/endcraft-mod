package net.thecoolcraft11.endcraft.block;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.ExperienceDroppingBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.thecoolcraft11.endcraft.Endcraft;
import net.thecoolcraft11.endcraft.block.custom.*;

public class ModBlocks {
    public static final Block ENDERITE_ORE = registerBlock("enderite_ore",
            new ExperienceDroppingBlock(UniformIntProvider.create(5,10),FabricBlockSettings.copyOf(Blocks.END_STONE).sounds(BlockSoundGroup.AMETHYST_BLOCK).strength(10f)));
    public static final Block ENDER_FORGE_CONVERTER = registerBlock("ender_forge_converter",
            new EnderForgeConverterBlock(FabricBlockSettings.copyOf(Blocks.CRAFTING_TABLE).strength(4f).nonOpaque()));
    public static final Block ENDERITE_BLOCK = registerBlock("enderite_block",
            new Block(FabricBlockSettings.copyOf(Blocks.NETHERITE_BLOCK).strength(14f)));
    public static final Block ESSENCE_ALTAR = registerBlock("essence_altar",
            new EssenceAltarBlock(FabricBlockSettings.copyOf(Blocks.CRAFTING_TABLE).strength(4f).nonOpaque()));
    public static final Block ENDER_CHARGER = registerBlock("ender_charger",
            new EnderChargerBlock(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK).strength(4f).nonOpaque()));
    public static final Block MOD_TABLE = registerBlock("mod_table",
            new ModTableBlock(FabricBlockSettings.copyOf(Blocks.OBSIDIAN).strength(4f).nonOpaque()));
    public static final Block FAKE_BLOCK = registerBlock("fake_block",
            new FakeBlock(FabricBlockSettings.copyOf(Blocks.OBSIDIAN).strength(4f)));
    public static final Block FAKE_BLOCK2 = registerBlock("fake_block2",
            new FakeBlock(FabricBlockSettings.copyOf(Blocks.OBSIDIAN).strength(4f).noCollision()));
    public static final Block END_PEDASTEL_EYE = registerBlock("end_pedastel_eye",
            new Block(FabricBlockSettings.copyOf(Blocks.OBSIDIAN).strength(4f)));
    public static final Block END_PEDASTEL_DARKNESS = registerBlock("end_pedastel_darkness",
            new Block(FabricBlockSettings.copyOf(Blocks.OBSIDIAN).strength(36000000).resistance(36000000).nonOpaque()));
    public static final Block END_PEDASTEL_FLAME = registerBlock("end_pedastel_flame",
            new Block(FabricBlockSettings.copyOf(Blocks.OBSIDIAN).strength(36000000).resistance(36000000).nonOpaque()));
    public static final Block END_PEDASTEL_FOG = registerBlock("end_pedastel_fog",
            new Block(FabricBlockSettings.copyOf(Blocks.OBSIDIAN).strength(36000000).resistance(36000000).nonOpaque()));
    public static final Block END_PEDASTEL_KNOWLAGE = registerBlock("end_pedastel_knowlage",
            new Block(FabricBlockSettings.copyOf(Blocks.OBSIDIAN).strength(36000000).resistance(36000000).nonOpaque()));
    public static final Block END_PEDASTEL_MYTHIC = registerBlock("end_pedastel_mythic",
            new Block(FabricBlockSettings.copyOf(Blocks.OBSIDIAN).strength(36000000).resistance(36000000).nonOpaque()));
    public static final Block END_PEDASTEL_PATH = registerBlock("end_pedastel_path",
            new Block(FabricBlockSettings.copyOf(Blocks.OBSIDIAN).strength(36000000).resistance(36000000).nonOpaque()));
    public static final Block END_PEDASTEL_TEAR = registerBlock("end_pedastel_tear",
            new Block(FabricBlockSettings.copyOf(Blocks.OBSIDIAN).strength(36000000).resistance(36000000).nonOpaque()));

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, new Identifier(Endcraft.MOD_ID, name), block);

    }
    private static Item registerBlockItem(String name, Block block) {
        return Registry.register(Registries.ITEM, new Identifier(Endcraft.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings()));
    }
    public static void registerModBlocks() {
        Endcraft.LOGGER.info("Registring Mod Blocks for " + Endcraft.MOD_ID);
    }
}
