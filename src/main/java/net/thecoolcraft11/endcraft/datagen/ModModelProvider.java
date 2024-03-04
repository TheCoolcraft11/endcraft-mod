package net.thecoolcraft11.endcraft.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Model;
import net.minecraft.data.client.Models;
import net.minecraft.util.Identifier;
import net.thecoolcraft11.endcraft.block.ModBlocks;
import net.thecoolcraft11.endcraft.item.ModItems;

import java.util.Optional;


public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.ENDERITE_ORE);
        blockStateModelGenerator.registerSimpleState(ModBlocks.ENDER_FORGE_CONVERTER);
        blockStateModelGenerator.registerSimpleState(ModBlocks.ESSENCE_ALTAR);
        blockStateModelGenerator.registerSimpleState(ModBlocks.ENDER_CHARGER);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.ENDERITE_BLOCK);
        blockStateModelGenerator.registerSimpleState(ModBlocks.MOD_TABLE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.FAKE_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.FAKE_BLOCK2);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.VOID_FLUID);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.INFECTED_DIRT);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.INFECTED_STONE);
        blockStateModelGenerator.registerSimpleState(ModBlocks.INFECTED_GRASS);



    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.ENDERITE_SCRAP, Models.GENERATED);
        itemModelGenerator.register(ModItems.ENDERITE_SWORD, Models.GENERATED);
        itemModelGenerator.register(ModItems.ENDERITE_PICKAXE, Models.GENERATED);
        itemModelGenerator.register(ModItems.ENDERITE_AXE, Models.GENERATED);
        itemModelGenerator.register(ModItems.ENDERITE_SHOVEL, Models.GENERATED);
        itemModelGenerator.register(ModItems.ENDERITE_HOE, Models.GENERATED);
        itemModelGenerator.register(ModItems.ENDERITE_INGOT, Models.GENERATED);
        itemModelGenerator.register(ModItems.ENDERITE_CORE, Models.GENERATED);
        itemModelGenerator.register(ModItems.ESSENCE_CORE, Models.GENERATED);
        itemModelGenerator.register(ModItems.ENDER_UPGRADE_RANGE_1, Models.GENERATED);
        itemModelGenerator.register(ModItems.ENDER_UPGRADE_RANGE_2, Models.GENERATED);
        itemModelGenerator.register(ModItems.ENDER_UPGRADE_RANGE_3, Models.GENERATED);
        itemModelGenerator.register(ModItems.ENDER_UPGRADE_RANGE_4, Models.GENERATED);
        itemModelGenerator.register(ModItems.ENDER_UPGRADE_RANGE_5, Models.GENERATED);
        itemModelGenerator.register(ModItems.ENDER_UPGRADE_DURABILITY_1, Models.GENERATED);
        itemModelGenerator.register(ModItems.ENDER_UPGRADE_DURABILITY_2, Models.GENERATED);
        itemModelGenerator.register(ModItems.ENDER_UPGRADE_DURABILITY_3, Models.GENERATED);
        itemModelGenerator.register(ModItems.ENDER_UPGRADE_DURABILITY_4, Models.GENERATED);
        itemModelGenerator.register(ModItems.ENDER_UPGRADE_DURABILITY_5, Models.GENERATED);
        itemModelGenerator.register(ModItems.ENDER_UPGRADE_FALL_1, Models.GENERATED);
        itemModelGenerator.register(ModItems.ENDER_UPGRADE_FALL_2, Models.GENERATED);
        itemModelGenerator.register(ModItems.ENDER_UPGRADE_FALL_3, Models.GENERATED);
        itemModelGenerator.register(ModItems.ENDER_UPGRADE_FALL_4, Models.GENERATED);
        itemModelGenerator.register(ModItems.ENDER_UPGRADE_FALL_5, Models.GENERATED);
        itemModelGenerator.register(ModItems.SHADOW_VEIL, Models.GENERATED);

        itemModelGenerator.register(ModItems.VOID_GHOST_SPAWN_EGG, new Model(Optional.of(new Identifier("item/template_spawn_egg")), Optional.empty()));
    }
}
