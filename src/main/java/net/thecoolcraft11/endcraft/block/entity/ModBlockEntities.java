package net.thecoolcraft11.endcraft.block.entity;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.thecoolcraft11.endcraft.Endcraft;
import net.thecoolcraft11.endcraft.block.ModBlocks;
import net.thecoolcraft11.endcraft.block.custom.EnderChestBlock;
import net.thecoolcraft11.endcraft.block.custom.EnderStaffConfigurationBlock;

public class ModBlockEntities {
    public static final BlockEntityType<EnderForgeConverterBlockEntity> ENDER_FORGE_CONVERTER_BLOCK_ENTITY =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(Endcraft.MOD_ID, "ender_forge_converter_be"),
                    FabricBlockEntityTypeBuilder.create(EnderForgeConverterBlockEntity::new,
                            ModBlocks.ENDER_FORGE_CONVERTER).build());
    public static final BlockEntityType<EssenceAltarBlockEntity> ESSENCE_ALTAR_BLOCK_ENTITY =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(Endcraft.MOD_ID, "essence_altar_be"),
                    FabricBlockEntityTypeBuilder.create(EssenceAltarBlockEntity::new,
                            ModBlocks.ESSENCE_ALTAR).build());
    public static final BlockEntityType<EnderChargerBlockEntity> ENDER_CHARGER_BLOCK_ENTITY =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(Endcraft.MOD_ID, "ender_charger_be"),
                    FabricBlockEntityTypeBuilder.create(EnderChargerBlockEntity::new,
                            ModBlocks.ENDER_CHARGER).build());
    public static final BlockEntityType<ModTableBlockEntity> MOD_TABLE_BLOCK_ENTITY =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(Endcraft.MOD_ID, "mod_table_be"),
                    FabricBlockEntityTypeBuilder.create(ModTableBlockEntity::new,
                            ModBlocks.MOD_TABLE).build());
    public static final BlockEntityType<EndPedastelBlockEntity> END_PEDASTEL_BLOCK_ENTITY =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(Endcraft.MOD_ID, "end_pedastel_be"),
                    FabricBlockEntityTypeBuilder.create(EndPedastelBlockEntity::new,
                            ModBlocks.END_PEDASTEL).build());
    public static final BlockEntityType<EnderStaffConfigurationBlockEntity> ENDER_STAFF_CONFIGURATION_BLOCK_ENTITY =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(Endcraft.MOD_ID, "ender_staff_configuration_be"),
                    FabricBlockEntityTypeBuilder.create(EnderStaffConfigurationBlockEntity::new,
                            ModBlocks.ENDER_STAFF_CONFIGURATION_BLOCK).build());
    public static final BlockEntityType<EnderChestBlockEntity> ENDER_CHEST_BLOCK_ENTITY =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(Endcraft.MOD_ID, "ender_chest_be"),
                    FabricBlockEntityTypeBuilder.create(EnderChestBlockEntity::new,
                            ModBlocks.ENDER_CHEST).build());

    public static void registerBlockEntities() {
        Endcraft.LOGGER.info("Gegistering Block Entities for "+ Endcraft.MOD_ID);
    }

}
