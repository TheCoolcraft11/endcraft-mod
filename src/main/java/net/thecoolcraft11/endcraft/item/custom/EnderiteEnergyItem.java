package net.thecoolcraft11.endcraft.item.custom;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionTypes;
import net.thecoolcraft11.endcraft.Endcraft;
import net.thecoolcraft11.endcraft.item.ModItems;

public class EnderiteEnergyItem extends Item {

    public EnderiteEnergyItem(Settings settings) {
        super(settings);

    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        return true;
    }
}