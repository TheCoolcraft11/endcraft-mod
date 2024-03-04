package net.thecoolcraft11.endcraft.block;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.ExperienceDroppingBlock;
import net.minecraft.block.piston.PistonBehavior;
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
    public static final Block END_PEDASTEL = registerBlock("end_pedastel",
            new EndPedastelBlock(FabricBlockSettings.copyOf(Blocks.OBSIDIAN).strength(-1f).resistance(36000000f).nonOpaque())); //Schattenschleier, Oculus-Erz, Novaflame Spark, Nebulith Crystal, Chronikum, Phantasma Prism,Nebula Shard , Nullstone,
    public static final Block VOIDBORN_ABYSS_PORTAL = registerBlock("voidborn_abyss_portal",
            new VoidbornAbyssPortalBlock(FabricBlockSettings.copyOf(Blocks.END_PORTAL).strength(-1f).resistance(36000000f).nonOpaque().dropsNothing().pistonBehavior(PistonBehavior.BLOCK).noCollision()));
    public static final Block VOID_FLUID = registerBlock("void_fluid",
            new VoidBlock(FabricBlockSettings.copyOf(Blocks.OBSIDIAN).strength(-1f).noCollision()));
    public static final Block VOID_LAYER = registerBlock("void_layer",
            new VoidLayerBlock(FabricBlockSettings.copyOf(Blocks.OBSIDIAN).strength(-1f).noCollision()));
    public static final Block INFECTED_DIRT = registerBlock("infected_dirt",
            new Block(FabricBlockSettings.copyOf(Blocks.OBSIDIAN).strength(100f)));
    public static final Block INFECTED_GRASS = registerBlock("infected_grass",
            new Block(FabricBlockSettings.copyOf(Blocks.OBSIDIAN).strength(100f)));
    public static final Block INFECTED_STONE = registerBlock("infected_stone",
            new Block(FabricBlockSettings.copyOf(Blocks.OBSIDIAN).strength(500f)));
    public static final Block VOIDBORN_PORTAL_ACTIVATOR_BLOCK = registerBlock("voidborn_portal_activator_block",
            new VoidbornPortalActivatorBlock(FabricBlockSettings.copyOf(Blocks.END_PORTAL).collidable(true)));


    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, new Identifier(Endcraft.MOD_ID, name), block);

    }
    private static Block registerBlockWithOutItem(String name, Block block) {
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
