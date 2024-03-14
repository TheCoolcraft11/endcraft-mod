package net.thecoolcraft11.endcraft.item.custom;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtIo;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.server.MinecraftServer;
import net.minecraft.text.Text;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.thecoolcraft11.endcraft.Endcraft;
import net.thecoolcraft11.endcraft.block.ModBlocks;
import net.thecoolcraft11.endcraft.block.entity.EnderChargerBlockEntity;
import net.thecoolcraft11.endcraft.block.entity.EnderChestBlockEntity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Random;
import java.util.UUID;

public class EnderiteChestKeyItem extends Item {
    public EnderiteChestKeyItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        if (context.getWorld().getBlockState(context.getBlockPos()) == ModBlocks.ENDER_CHEST.getDefaultState()) {
            if (!context.getPlayer().getMainHandStack().getOrCreateNbt().getBoolean("aligned")) {
                if(context.getWorld().getBlockState(context.getBlockPos()) == ModBlocks.ENDER_CHEST.getDefaultState()) {
                    context.getPlayer().getMainHandStack().getOrCreateNbt().putInt("x1", context.getBlockPos().getX());
                    context.getPlayer().getMainHandStack().getOrCreateNbt().putInt("y1", context.getBlockPos().getY());
                    context.getPlayer().getMainHandStack().getOrCreateNbt().putInt("z1", context.getBlockPos().getZ());
                    BlockEntity blockEntity = context.getWorld().getBlockEntity(context.getBlockPos());
                    if (blockEntity instanceof EnderChestBlockEntity) {
                        EnderChestBlockEntity customBlockEntity = (EnderChestBlockEntity) blockEntity;
                        context.getPlayer().getMainHandStack().getOrCreateNbt().putUuid("pwd", customBlockEntity.getPwd());
                    }
                    context.getPlayer().getMainHandStack().getOrCreateNbt().putBoolean("aligned", true);
                }
            }else {
                context.getPlayer().sendMessage(Text.translatable("message.endcraft.enderite_chest.already_aligned").formatted(Formatting.DARK_RED), true);
            }
        }
        return super.useOnBlock(context);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
            BlockPos pos = new BlockPos(player.getMainHandStack().getOrCreateNbt().getInt("x1"), player.getMainHandStack().getOrCreateNbt().getInt("y1"), player.getMainHandStack().getOrCreateNbt().getInt("z1"));
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (blockEntity instanceof EnderChestBlockEntity) {
            EnderChestBlockEntity customBlockEntity = (EnderChestBlockEntity) blockEntity;
            if (player.getMainHandStack().getOrCreateNbt().getUuid("pwd").equals(customBlockEntity.getPwd())) {
                NamedScreenHandlerFactory screenHandlerFactory = ((EnderChestBlockEntity) world.getBlockEntity(pos));
                if (screenHandlerFactory != null) {
                    player.openHandledScreen(screenHandlerFactory);
                }
            }else {
                player.sendMessage(Text.translatable("message.endcraft.enderite_chest.pwd_not_match").copy().formatted(Formatting.DARK_RED),true);
            }
        }
        return super.use(world, player, hand);
    }


    @Override
    public boolean hasGlint(ItemStack stack) {
        return true;
    }
}
