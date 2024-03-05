package net.thecoolcraft11.endcraft.item;

import net.fabricmc.fabric.api.item.v1.CustomDamageHandler;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.MinecraftVersion;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.server.dedicated.MinecraftDedicatedServer;
import net.minecraft.util.Identifier;
import net.thecoolcraft11.endcraft.Endcraft;
import net.thecoolcraft11.endcraft.entity.ModEntities;
import net.thecoolcraft11.endcraft.item.custom.*;

import java.util.function.Consumer;

public class ModItems {
    public static final Item ENDERITE_SCRAP = registerItem("enderite_scrap", new Item(new FabricItemSettings()));
    public static final Item ENDERITE_INGOT = registerItem("enderite_ingot", new Item(new FabricItemSettings()));
    public static final Item ENDERITE_PICKAXE = registerItem("enderite_pickaxe",
            new PickaxeItem(ModToolMaterial.ENDERITE, 3, 4f, new FabricItemSettings()));
    public static final Item ENDERITE_SWORD = registerItem("enderite_sword",
            new PickaxeItem(ModToolMaterial.ENDERITE, 10, 2f, new FabricItemSettings()));
    public static final Item ENDERITE_AXE = registerItem("enderite_axe",
            new PickaxeItem(ModToolMaterial.ENDERITE, 11, 2f, new FabricItemSettings()));
    public static final Item ENDERITE_SHOVEL = registerItem("enderite_shovel",
            new PickaxeItem(ModToolMaterial.ENDERITE, 2, 2f, new FabricItemSettings()));
    public static final Item ENDERITE_HOE = registerItem("enderite_hoe",
            new PickaxeItem(ModToolMaterial.ENDERITE, 1, 2f, new FabricItemSettings()));
    public static final Item EMPTY_ESSENCE = registerItem("empty_essence", new EmptyEssenceItem(new FabricItemSettings().maxDamage(64)) {});
    public static final Item VOID_ESSENCE = registerItem("void_essence", new Item(new FabricItemSettings().maxCount(1)) {});
    public static final Item OVERWORLD_ESSENCE = registerItem("overworld_essence", new Item(new FabricItemSettings().maxCount(1)) {});
    public static final Item NETHER_ESSENCE = registerItem("nether_essence", new Item(new FabricItemSettings().maxCount(1)) {});
    public static final Item THE_END_ESSENCE = registerItem("the_end_essence", new Item(new FabricItemSettings().maxCount(1)) {});
    public static final Item ESSENCE_CORE = registerItem("essence_core", new Item(new FabricItemSettings().maxCount(1)) {});
    public static final Item ENDERITE_CORE = registerItem("enderite_core", new Item(new FabricItemSettings().maxCount(1)) {});
    public static final Item ENDERITE_ENERGY = registerItem("enderite_energy", new Item(new FabricItemSettings().maxCount(64)) {});
    public static final Item ENDER_STAFF = registerItem("ender_staff", new EnderStaffItem(new FabricItemSettings().maxCount(1).maxDamage(2048)) {});
    public static final Item ENERGY_CELL = registerItem("energy_cell", new EnergyCellItem(new FabricItemSettings().maxCount(1)));
    public static final Item ENDER_UPGRADE_RANGE_1 = registerItem("ender_upgrade_range_1", new EnderUpgradeItem(new FabricItemSettings().maxCount(1), "range", 1));
    public static final Item ENDER_UPGRADE_RANGE_2 = registerItem("ender_upgrade_range_2", new EnderUpgradeItem(new FabricItemSettings().maxCount(1), "range", 2));
    public static final Item ENDER_UPGRADE_RANGE_3 = registerItem("ender_upgrade_range_3", new EnderUpgradeItem(new FabricItemSettings().maxCount(1), "range", 3));
    public static final Item ENDER_UPGRADE_RANGE_4 = registerItem("ender_upgrade_range_4", new EnderUpgradeItem(new FabricItemSettings().maxCount(1), "range", 4));
    public static final Item ENDER_UPGRADE_RANGE_5 = registerItem("ender_upgrade_range_5", new EnderUpgradeItem(new FabricItemSettings().maxCount(1), "range", 5));
    public static final Item ENDER_UPGRADE_DURABILITY_1 = registerItem("ender_upgrade_durability_1", new EnderUpgradeItem(new FabricItemSettings().maxCount(1), "durability", 1));
    public static final Item ENDER_UPGRADE_DURABILITY_2 = registerItem("ender_upgrade_durability_2", new EnderUpgradeItem(new FabricItemSettings().maxCount(1), "durability", 2));
    public static final Item ENDER_UPGRADE_DURABILITY_3 = registerItem("ender_upgrade_durability_3", new EnderUpgradeItem(new FabricItemSettings().maxCount(1), "durability", 3));
    public static final Item ENDER_UPGRADE_DURABILITY_4 = registerItem("ender_upgrade_durability_4", new EnderUpgradeItem(new FabricItemSettings().maxCount(1), "durability", 4));
    public static final Item ENDER_UPGRADE_DURABILITY_5 = registerItem("ender_upgrade_durability_5", new EnderUpgradeItem(new FabricItemSettings().maxCount(1), "durability", 5));
    public static final Item ENDER_UPGRADE_FALL_1 = registerItem("ender_upgrade_fall_1", new EnderUpgradeItem(new FabricItemSettings().maxCount(1), "fall", 1));
    public static final Item ENDER_UPGRADE_FALL_2 = registerItem("ender_upgrade_fall_2", new EnderUpgradeItem(new FabricItemSettings().maxCount(1), "fall", 2));
    public static final Item ENDER_UPGRADE_FALL_3 = registerItem("ender_upgrade_fall_3", new EnderUpgradeItem(new FabricItemSettings().maxCount(1), "fall", 3));
    public static final Item ENDER_UPGRADE_FALL_4 = registerItem("ender_upgrade_fall_4", new EnderUpgradeItem(new FabricItemSettings().maxCount(1), "fall", 4));
    public static final Item ENDER_UPGRADE_FALL_5 = registerItem("ender_upgrade_fall_5", new EnderUpgradeItem(new FabricItemSettings().maxCount(1), "fall", 5));
    public static final Item VOID_GHOST_SPAWN_EGG = registerItem("void_ghost_spawn_egg", new SpawnEggItem(ModEntities.VOID_GHOST, 0x000000, 0xFFFFFF, new FabricItemSettings()));
    public static final Item SHADOW_VEIL = registerItem("shadow_veil", new ShadowVeilItem(new FabricItemSettings().maxCount(1).maxDamage(64)));
    public static final Item OCULUS_ORE = registerItem("oculus_ore", new OculusOreItem(new FabricItemSettings().maxCount(1).maxDamage(1)));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(Endcraft.MOD_ID, name), item);

    }

    public static void registerModItems() {
        Endcraft.LOGGER.info("Registering Mod Items for " + Endcraft.MOD_ID);

    }
}
