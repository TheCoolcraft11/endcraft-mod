package net.thecoolcraft11.endcraft.item.custom;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.dimension.DimensionTypes;
import net.thecoolcraft11.endcraft.Endcraft;
import net.thecoolcraft11.endcraft.item.ModItems;

import java.util.*;

public class EmptyEssenceItem extends Item {

    public EmptyEssenceItem(Settings settings) {
        super(settings);

    }


    @Override
    public int getItemBarColor(ItemStack stack) {
        return 0x7119F0;
    }

    @Override
    public boolean isItemBarVisible(ItemStack stack) {
        return true;

    }

     @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
         super.inventoryTick(stack, world, entity, slot, selected);
         if (isInVoid((PlayerEntity) entity)) {
             stack.getOrCreateNbt().putInt("MaxDurability", stack.getOrCreateNbt().getInt("MaxDurability") + 1);
             stack.getOrCreateNbt().putString("essence", "the_void");
         }
         stack.setDamage(stack.getMaxDamage() - stack.getOrCreateNbt().getInt("MaxDurability"));

            if(stack.getDamage() == 0) {
                if(stack.getOrCreateNbt().getString("essence").equals("the_void")) {
                    removeItem((PlayerEntity) entity, getItem((PlayerEntity) entity, ModItems.EMPTY_ESSENCE));
                    addItem(((PlayerEntity) entity), ModItems.VOID_ESSENCE.getDefaultStack());
                }
                if(stack.getOrCreateNbt().getString("essence").equals("overworld")) {
                    removeItem((PlayerEntity) entity, getItem((PlayerEntity) entity, ModItems.EMPTY_ESSENCE));
                    addItem(((PlayerEntity) entity), ModItems.OVERWORLD_ESSENCE.getDefaultStack());
                }

            }
        }
    private boolean isInVoid(PlayerEntity player) {
        return player.getY() < -64 && player.getWorld().getDimensionKey() == DimensionTypes.THE_END;
    }
    private int getItem(PlayerEntity player, Item item) {
        for (int i = 0; i < player.getInventory().size(); i++) {
            ItemStack itemStack = player.getInventory().getStack(i);
            if (!itemStack.isEmpty() && itemStack.getItem() == item) {
                return i;
            }
        }
        return -1;
    }
    public static void removeItem(PlayerEntity player, int slot) {
        Endcraft.LOGGER.info("" + slot);
        if (!(slot == -1))  {
         player.getInventory().removeStack(slot);
        }
    }
    public static void addItem(PlayerEntity player, ItemStack stack) {
         player.giveItemStack(stack);
    }
}
