package net.thecoolcraft11.endcraft.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.thecoolcraft11.endcraft.Endcraft;
import net.thecoolcraft11.endcraft.block.ModBlocks;

public class ModItemGroups {
    public static final ItemGroup ENDCRAFT_GROUP = Registry.register(Registries.ITEM_GROUP,
            new Identifier(Endcraft.MOD_ID, "enderite"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.endcraft"))
                    .icon(() -> new ItemStack(ModBlocks.ENDERITE_ORE)).entries((displayContext, entries) -> {
                        entries.add(ModBlocks.ENDERITE_ORE);
                        entries.add(ModItems.ENDERITE_SCRAP);
                        entries.add(ModBlocks.ENDER_FORGE_CONVERTER);
                        entries.add(ModItems.ENDERITE_SWORD);
                        entries.add(ModItems.ENDERITE_PICKAXE);
                        entries.add(ModItems.ENDERITE_AXE);
                        entries.add(ModItems.ENDERITE_SHOVEL);
                        entries.add(ModItems.ENDERITE_HOE);
                        entries.add(ModItems.ENDERITE_INGOT);
                        entries.add(ModBlocks.ENDERITE_BLOCK);
                        entries.add(ModItems.EMPTY_ESSENCE);
                        entries.add(ModItems.VOID_ESSENCE);
                        entries.add(ModItems.OVERWORLD_ESSENCE);
                        entries.add(ModItems.NETHER_ESSENCE);
                        entries.add(ModItems.THE_END_ESSENCE);
                        entries.add(ModBlocks.ESSENCE_ALTAR);
                        entries.add(ModItems.ESSENCE_CORE);
                        entries.add(ModItems.ENDERITE_CORE);
                        entries.add(ModItems.ENDERITE_ENERGY);
                        entries.add(ModItems.ENERGY_CELL);
                        entries.add(ModBlocks.ENDER_CHARGER);
                        entries.add(ModItems.ENDER_STAFF);
                        entries.add(ModBlocks.MOD_TABLE);
                        entries.add(ModItems.ENDER_UPGRADE_RANGE_1);
                        entries.add(ModItems.ENDER_UPGRADE_RANGE_2);
                        entries.add(ModItems.ENDER_UPGRADE_RANGE_3);
                        entries.add(ModItems.ENDER_UPGRADE_RANGE_4);
                        entries.add(ModItems.ENDER_UPGRADE_RANGE_5);
                        entries.add(ModItems.ENDER_UPGRADE_DURABILITY_1);
                        entries.add(ModItems.ENDER_UPGRADE_DURABILITY_2);
                        entries.add(ModItems.ENDER_UPGRADE_DURABILITY_3);
                        entries.add(ModItems.ENDER_UPGRADE_DURABILITY_4);
                        entries.add(ModItems.ENDER_UPGRADE_DURABILITY_5);
                        entries.add(ModItems.ENDER_UPGRADE_FALL_1);
                        entries.add(ModItems.ENDER_UPGRADE_FALL_2);
                        entries.add(ModItems.ENDER_UPGRADE_FALL_3);
                        entries.add(ModItems.ENDER_UPGRADE_FALL_4);
                        entries.add(ModItems.ENDER_UPGRADE_FALL_5);
                        entries.add(ModBlocks.END_PEDASTEL);
                        entries.add(ModBlocks.FAKE_BLOCK);
                        entries.add(ModBlocks.INFECTED_DIRT);
                        entries.add(ModBlocks.INFECTED_GRASS);
                        entries.add(ModBlocks.INFECTED_STONE);
                        entries.add(ModBlocks.VOID_FLUID);
                        entries.add(ModBlocks.VOID_LAYER);
                        entries.add(ModItems.VOID_GHOST_SPAWN_EGG);
                        entries.add(ModItems.OCULUS_ORE);
                        entries.add(ModItems.ENCHANTED_SADDLE);
                        entries.add(ModItems.SHADOW_VEIL);
                        entries.add(ModItems.STAFF_OF_TELEPORTATION);
                        entries.add(ModBlocks.ENDER_CHEST);
                        entries.add(ModItems.ENDERITE_CHEST_KEY);
                    }).build());
    public static void registerItemGroups() {
        Endcraft.LOGGER.info("Registring Item Groups for " + Endcraft.MOD_ID);
    }
}
