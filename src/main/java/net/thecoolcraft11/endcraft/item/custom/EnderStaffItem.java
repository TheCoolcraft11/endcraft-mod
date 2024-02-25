package net.thecoolcraft11.endcraft.item.custom;

import net.minecraft.client.MinecraftClient;
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

public class EnderStaffItem extends Item {
    public EnderStaffItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        if(!world.isClient) {
            double reachDistance = 64;
            if (!(player.getMainHandStack().getDamage() >= player.getMainHandStack().getMaxDamage())) {
                player.teleport(Raycast.raycast(reachDistance).getBlockPos().getX(), Raycast.raycast(reachDistance).getBlockPos().getY(), Raycast.raycast(reachDistance).getBlockPos().getZ());
                player.getMainHandStack().setDamage(player.getMainHandStack().getDamage() + 1);
                return TypedActionResult.success(player.getMainHandStack(),true);
            }
        }
        return TypedActionResult.pass(player.getMainHandStack());
    }

    @Override
    public int getItemBarColor(ItemStack stack) {
        return 0x7119F0;
    }
}
