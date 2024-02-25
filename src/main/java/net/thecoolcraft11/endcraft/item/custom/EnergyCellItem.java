package net.thecoolcraft11.endcraft.item.custom;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.thecoolcraft11.endcraft.item.ModItems;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class EnergyCellItem extends Item {
    public EnergyCellItem(Settings settings) {
        super(settings);
    }
    public int MaxStorage = 8192;

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        if (getNbtInt(player.getMainHandStack(), "Energies") + countItems(player, ModItems.ENDERITE_ENERGY) < 8192) {
            addNbt(player.getMainHandStack(), "Energies", countItems(player, ModItems.ENDERITE_ENERGY));
            removeItems(player, ModItems.ENDERITE_ENERGY);
            return TypedActionResult.success(player.getMainHandStack(), true);
        }
        player.playSound(SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.PLAYERS, 1f,1f);
        return TypedActionResult.fail(player.getMainHandStack());
    }
    private static int countItems(PlayerEntity player, Item item) {
        int energyCount = 0;
        for (ItemStack stack : player.getInventory().main) {
            if (!stack.isEmpty() && stack.getItem() == item) {
                energyCount += stack.getCount();
            }
        }
        return energyCount;
    }
    private void removeItems(PlayerEntity player, Item item) {
        for (ItemStack stack : player.getInventory().main) {
            if (!stack.isEmpty() && stack.getItem() == item) {
                stack.setCount(0);
            }
        }
    }
    private int getNbtInt(ItemStack item, String string) {
        return item.getOrCreateNbt().getInt(string);
    }

    private void addNbt(ItemStack itemStack, String string, int i) {
        itemStack.getOrCreateNbt().putInt(string, i);
    }
    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.of("Energies: " + getNbtInt(stack, "Energies")));
        // super.appendTooltip(stack, world, tooltip, context);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        super.inventoryTick(stack, world, entity, slot, selected);
        stack.setDamage(stack.getMaxDamage() - stack.getOrCreateNbt().getInt("Energies"));
    }

    @Override
    public int getItemBarColor(ItemStack stack) {
        return 0x7119F0;
    }
}
