package net.thecoolcraft11.endcraft.datagen;


import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.ItemTags;
import net.thecoolcraft11.endcraft.block.ModBlocks;
import net.thecoolcraft11.endcraft.item.ModItems;
import net.thecoolcraft11.endcraft.util.ModTags;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public ModItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
        getOrCreateTagBuilder(ModTags.Items.ENDER_UPGRADE)
                .add(ModItems.ENDER_UPGRADE_RANGE_1)
                .add(ModItems.ENDER_UPGRADE_RANGE_2)
                .add(ModItems.ENDER_UPGRADE_RANGE_3)
                .add(ModItems.ENDER_UPGRADE_RANGE_4)
                .add(ModItems.ENDER_UPGRADE_RANGE_5)
                .add(ModItems.ENDER_UPGRADE_DURABILITY_1)
                .add(ModItems.ENDER_UPGRADE_DURABILITY_2)
                .add(ModItems.ENDER_UPGRADE_DURABILITY_3)
                .add(ModItems.ENDER_UPGRADE_DURABILITY_4)
                .add(ModItems.ENDER_UPGRADE_DURABILITY_5)
                .add(ModItems.ENDER_UPGRADE_FALL_1)
                .add(ModItems.ENDER_UPGRADE_FALL_2)
                .add(ModItems.ENDER_UPGRADE_FALL_3)
                .add(ModItems.ENDER_UPGRADE_FALL_4)
                .add(ModItems.ENDER_UPGRADE_FALL_5);

    }
}
