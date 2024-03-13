package net.thecoolcraft11.endcraft.item.custom;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.registry.RegistryKey;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.thecoolcraft11.endcraft.block.ModBlocks;
import net.thecoolcraft11.endcraft.block.entity.EnderChargerBlockEntity;
import net.thecoolcraft11.endcraft.block.entity.EnderChestBlockEntity;

import java.util.Random;

public class EnderiteChestKeyItem extends Item {
    public EnderiteChestKeyItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        if (!context.getPlayer().getMainHandStack().getOrCreateNbt().getBoolean("aligned")) {
            if(context.getWorld().getBlockState(context.getBlockPos()) == ModBlocks.ENDER_CHEST.getDefaultState()) {
                context.getPlayer().getMainHandStack().getOrCreateNbt().putInt("x1", context.getBlockPos().getX());
                context.getPlayer().getMainHandStack().getOrCreateNbt().putInt("y1", context.getBlockPos().getY());
                context.getPlayer().getMainHandStack().getOrCreateNbt().putInt("z1", context.getBlockPos().getZ());
                Random random = new Random();
                context.getPlayer().getMainHandStack().getOrCreateNbt().putInt("pwd", random.nextInt(1000000));
                context.getPlayer().getMainHandStack().getOrCreateNbt().putBoolean("aligned", true);
                if (context.getPlayer().getWorld().getRegistryKey() == World.OVERWORLD) {
                    context.getPlayer().getMainHandStack().getOrCreateNbt().putString("world1", "overworld");
                } else if (context.getPlayer().getWorld().getRegistryKey() == World.NETHER) {
                    context.getPlayer().getMainHandStack().getOrCreateNbt().putString("world1", "nether");
                } else if (context.getPlayer().getWorld().getRegistryKey() == World.END) {
                    context.getPlayer().getMainHandStack().getOrCreateNbt().putString("world1", "end");
                }
            }
        }
        return super.useOnBlock(context);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
            BlockPos pos = new BlockPos(player.getMainHandStack().getOrCreateNbt().getInt("x1"), player.getMainHandStack().getOrCreateNbt().getInt("y1"), player.getMainHandStack().getOrCreateNbt().getInt("z1"));
            NamedScreenHandlerFactory screenHandlerFactory = ((EnderChestBlockEntity) world.getBlockEntity(pos));
            if (screenHandlerFactory != null) {
                player.openHandledScreen(screenHandlerFactory);
            }
        return super.use(world, player, hand);
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        return true;
    }
}
