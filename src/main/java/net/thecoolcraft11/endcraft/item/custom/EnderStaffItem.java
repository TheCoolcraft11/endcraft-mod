package net.thecoolcraft11.endcraft.item.custom;

import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.StackReference;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.screen.slot.Slot;
import net.minecraft.sound.SoundCategory;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ClickType;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.thecoolcraft11.endcraft.item.ModItems;
import net.thecoolcraft11.endcraft.util.Raycast;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class EnderStaffItem extends Item {
    public EnderStaffItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        if(!world.isClient) {
            double reachDistance = 64;
            if(hasNbtUpgrade(player.getMainHandStack(), "range")) {
                reachDistance = 64 + 10 * getNbtLevel(player.getMainHandStack(), "range");
            }
            if (!(player.getMainHandStack().getDamage() >= player.getMainHandStack().getMaxDamage())) {
                player.teleport(Raycast.raycast(reachDistance).getBlockPos().getX(), Raycast.raycast(reachDistance).getBlockPos().getY(), Raycast.raycast(reachDistance).getBlockPos().getZ());
                if (hasNbtUpgrade(player.getMainHandStack(), "durability")) {
                    player.getMainHandStack().setDamage((int) (player.getMainHandStack().getDamage() + 5 / getNbtLevel(player.getMainHandStack(), "durability")));
                }else {
                    player.getMainHandStack().setDamage(player.getMainHandStack().getDamage() + 5 );
                }
                if(hasNbtUpgrade(player.getMainHandStack(), "fall")) {
                    player.fallDistance = (float) (player.fallDistance - getNbtLevel(player.getMainHandStack(), "fall") * 10);
                }
                return TypedActionResult.success(player.getMainHandStack(),true);
            }
        }
        return TypedActionResult.pass(player.getMainHandStack());
    }

    @Override
    public int getItemBarColor(ItemStack stack) {
        return 0x7119F0;
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if(stack.getOrCreateNbt().getString("upgrade1").isBlank()) {
            stack.getOrCreateNbt().putString("upgrade1", "none");
        }
        if(stack.getOrCreateNbt().getString("upgrade2").isBlank()) {
            stack.getOrCreateNbt().putString("upgrade2", "none");
        }
        if(stack.getOrCreateNbt().getString("upgrade3").isBlank()) {
            stack.getOrCreateNbt().putString("upgrade3", "none");
        }
        super.inventoryTick(stack, world, entity, slot, selected);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        if(!(stack.getOrCreateNbt().getString("upgrade1").isBlank())) {
            tooltip.add(Text.translatable("upgrade.endcraft." + stack.getOrCreateNbt().getString("upgrade1")+ "." + stack.getOrCreateNbt().getInt("upgrade1level")));
        }
        if(!(stack.getOrCreateNbt().getString("upgrade2").isBlank())) {
            tooltip.add(Text.translatable("upgrade.endcraft." + stack.getOrCreateNbt().getString("upgrade2")+ "." + stack.getOrCreateNbt().getInt("upgrade2level")));
        }
        if(!(stack.getOrCreateNbt().getString("upgrade3").isBlank())) {
            tooltip.add(Text.translatable("upgrade.endcraft." + stack.getOrCreateNbt().getString("upgrade3")+ "." + stack.getOrCreateNbt().getInt("upgrade3level")));
        }


        super.appendTooltip(stack, world, tooltip, context);
    }
    private boolean hasNbtUpgrade(ItemStack itemStack, String string) {
        return itemStack.getOrCreateNbt().getString("upgrade1").equals(string) ||itemStack.getOrCreateNbt().getString("upgrade2").equals(string) || itemStack.getOrCreateNbt().getString("upgrade3").equals(string);
    }
    private double getNbtLevel(ItemStack itemStack, String string) {
        int level = 0;
        if(itemStack.getOrCreateNbt().getString("upgrade1").equals(string)) {
            level = itemStack.getOrCreateNbt().getInt("upgrade1level");
        }
        if(itemStack.getOrCreateNbt().getString("upgrade2").equals(string)) {
            level = itemStack.getOrCreateNbt().getInt("upgrade2level");
        }
        if(itemStack.getOrCreateNbt().getString("upgrade3").equals(string)) {
            level = itemStack.getOrCreateNbt().getInt("upgrade3level");
        }
        return level;
    }
}
