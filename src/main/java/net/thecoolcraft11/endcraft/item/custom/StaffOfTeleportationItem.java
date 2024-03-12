package net.thecoolcraft11.endcraft.item.custom;

import net.minecraft.block.*;
import net.minecraft.block.entity.ChestBlockEntity;
import net.minecraft.block.entity.CommandBlockBlockEntity;
import net.minecraft.block.entity.SignBlockEntity;
import net.minecraft.entity.mob.PiglinBrain;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.thecoolcraft11.endcraft.block.entity.EnderChargerBlockEntity;

public class StaffOfTeleportationItem extends Item {
    public StaffOfTeleportationItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {

        if (!context.getPlayer().isSneaking()) {
            if (!context.getPlayer().getMainHandStack().getOrCreateNbt().getBoolean("p1")) {
                context.getPlayer().getMainHandStack().getOrCreateNbt().putInt("x1", context.getBlockPos().getX());
                context.getPlayer().getMainHandStack().getOrCreateNbt().putInt("y1", context.getBlockPos().getY());
                context.getPlayer().getMainHandStack().getOrCreateNbt().putInt("z1", context.getBlockPos().getZ());
                if(context.getPlayer().getWorld().getRegistryKey() == World.OVERWORLD) {
                    context.getPlayer().getMainHandStack().getOrCreateNbt().putString("world1", "overworld");
                }else if(context.getPlayer().getWorld().getRegistryKey() == World.NETHER) {
                    context.getPlayer().getMainHandStack().getOrCreateNbt().putString("world1", "nether");
                }else if(context.getPlayer().getWorld().getRegistryKey() == World.END) {
                    context.getPlayer().getMainHandStack().getOrCreateNbt().putString("world1", "end");
                }
                context.getPlayer().getMainHandStack().getOrCreateNbt().putBoolean("p1", true);
            }else if (!context.getPlayer().getMainHandStack().getOrCreateNbt().getBoolean("p2")) {
                context.getPlayer().getMainHandStack().getOrCreateNbt().putInt("x2", context.getBlockPos().getX());
                context.getPlayer().getMainHandStack().getOrCreateNbt().putInt("y2", context.getBlockPos().getY());
                context.getPlayer().getMainHandStack().getOrCreateNbt().putInt("z2", context.getBlockPos().getZ());
                if(context.getPlayer().getWorld().getRegistryKey() == World.OVERWORLD) {
                    context.getPlayer().getMainHandStack().getOrCreateNbt().putString("world2", "overworld");
                }else if(context.getPlayer().getWorld().getRegistryKey() == World.NETHER) {
                    context.getPlayer().getMainHandStack().getOrCreateNbt().putString("world2", "nether");
                }else if(context.getPlayer().getWorld().getRegistryKey() == World.END) {
                    context.getPlayer().getMainHandStack().getOrCreateNbt().putString("world2", "end");
                }
                context.getPlayer().getMainHandStack().getOrCreateNbt().putBoolean("p2", true);
            }else if (!context.getPlayer().getMainHandStack().getOrCreateNbt().getBoolean("p3")) {
                context.getPlayer().getMainHandStack().getOrCreateNbt().putInt("x3", context.getBlockPos().getX());
                context.getPlayer().getMainHandStack().getOrCreateNbt().putInt("y3", context.getBlockPos().getY());
                context.getPlayer().getMainHandStack().getOrCreateNbt().putInt("z3", context.getBlockPos().getZ());
                if(context.getPlayer().getWorld().getRegistryKey() == World.OVERWORLD) {
                    context.getPlayer().getMainHandStack().getOrCreateNbt().putString("world3", "overworld");
                }else if(context.getPlayer().getWorld().getRegistryKey() == World.NETHER) {
                    context.getPlayer().getMainHandStack().getOrCreateNbt().putString("world3", "nether");
                }else if(context.getPlayer().getWorld().getRegistryKey() == World.END) {
                    context.getPlayer().getMainHandStack().getOrCreateNbt().putString("world3", "end");
                }
                context.getPlayer().getMainHandStack().getOrCreateNbt().putBoolean("p3", true);
            }
        }else {
            context.getPlayer().getMainHandStack().getOrCreateNbt().remove("x1");
            context.getPlayer().getMainHandStack().getOrCreateNbt().remove("y1");
            context.getPlayer().getMainHandStack().getOrCreateNbt().remove("z1");
            context.getPlayer().getMainHandStack().getOrCreateNbt().remove("p1");
            context.getPlayer().getMainHandStack().getOrCreateNbt().remove("x2");
            context.getPlayer().getMainHandStack().getOrCreateNbt().remove("y2");
            context.getPlayer().getMainHandStack().getOrCreateNbt().remove("z2");
            context.getPlayer().getMainHandStack().getOrCreateNbt().remove("p2");
            context.getPlayer().getMainHandStack().getOrCreateNbt().remove("x3");
            context.getPlayer().getMainHandStack().getOrCreateNbt().remove("y3");
            context.getPlayer().getMainHandStack().getOrCreateNbt().remove("z3");
            context.getPlayer().getMainHandStack().getOrCreateNbt().remove("p3");
        }
        return super.useOnBlock(context);
    }
}
