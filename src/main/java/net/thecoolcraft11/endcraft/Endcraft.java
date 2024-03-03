package net.thecoolcraft11.endcraft;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.FluidBlock;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.thecoolcraft11.endcraft.block.ModBlocks;
import net.thecoolcraft11.endcraft.block.entity.ModBlockEntities;
import net.thecoolcraft11.endcraft.entity.ModEntities;
import net.thecoolcraft11.endcraft.entity.custom.VoidGhostEntity;
import net.thecoolcraft11.endcraft.item.ModItems;
import net.thecoolcraft11.endcraft.item.ModItemGroups;
import net.thecoolcraft11.endcraft.recipe.ModRecipes;
import net.thecoolcraft11.endcraft.screen.ModScreenHandlers;
import net.thecoolcraft11.endcraft.world.gen.ModWorldGeneration;
import net.thecoolcraft11.endcraft.statuseffects.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class Endcraft implements ModInitializer {

	public static final String MOD_ID = "endcraft";
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		LOGGER.info("Hello Fabric world!");
		ModItemGroups.registerItemGroups();
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		//ModLootTableModifiers.modifyLootTables();
		//ModCustomTrades.registerTrades();
		//ModVillagers.registerVillager();
		//ModSounds.registerSounds();
		ModBlockEntities.registerBlockEntities();
		ModScreenHandlers.registerScreenHandlers();
		ModRecipes.registerRecipes();
		ModWorldGeneration.generateModWorldGen();
		ModStatusEffects.registerStatusEffects();

		FabricDefaultAttributeRegistry.register(ModEntities.VOID_GHOST, VoidGhostEntity.createVoidGhostAttributes());

		LOGGER.info("Initialize Endcraft!");
	}
}