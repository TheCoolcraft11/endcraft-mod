package net.thecoolcraft11.endcraft.item.custom;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import net.thecoolcraft11.endcraft.Endcraft;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class EnderUpgradeItem extends Item {
    private String type;
    private int level;
    public EnderUpgradeItem(Settings settings, String type1, int level1) {
        super(settings);
        type = type1;
        level = level1;
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        super.inventoryTick(stack, world, entity, slot, selected);
            stack.getOrCreateNbt().putString("type", type);
            stack.getOrCreateNbt().putInt("level", level);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.translatable("upgrade.endcraft." + type + "." + level));
        super.appendTooltip(stack, world, tooltip, context);
    }

}
