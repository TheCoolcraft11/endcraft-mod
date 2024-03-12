package net.thecoolcraft11.endcraft.networking.packet;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionTypes;
import net.thecoolcraft11.endcraft.item.ModItems;
import net.thecoolcraft11.endcraft.item.custom.EnderStaffItem;
import net.thecoolcraft11.endcraft.world.dimension.ModDimensions;

public class TeleportationStaffC2SPacket {

    public static void recive(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender) {
        int i = buf.readInt();

        if (player.getMainHandStack().getItem() == ModItems.STAFF_OF_TELEPORTATION) {
            if(i == 1) {
                RegistryKey<World> registryKey = World.OVERWORLD;
                RegistryKey<World> registryKey2 = World.NETHER;
                RegistryKey<World> registryKey3 = World.END;
                ServerWorld serverWorld = ((ServerWorld)player.getWorld()).getServer().getWorld(registryKey);
                ServerWorld serverWorld2 = ((ServerWorld)player.getWorld()).getServer().getWorld(registryKey2);
                ServerWorld serverWorld3 = ((ServerWorld)player.getWorld()).getServer().getWorld(registryKey3);
                if(player.getMainHandStack().getOrCreateNbt().getString("world1").equals("overworld")) {
                    player.teleport(serverWorld,player.getMainHandStack().getOrCreateNbt().getInt("x1"), player.getMainHandStack().getOrCreateNbt().getInt("y1"), player.getMainHandStack().getOrCreateNbt().getInt("z1"), 1f,1f);
                }
                if(player.getMainHandStack().getOrCreateNbt().getString("world1").equals("nether")) {
                    player.teleport(serverWorld2,player.getMainHandStack().getOrCreateNbt().getInt("x1"), player.getMainHandStack().getOrCreateNbt().getInt("y1"), player.getMainHandStack().getOrCreateNbt().getInt("z1"), 1f,1f);
                }
                if(player.getMainHandStack().getOrCreateNbt().getString("world1").equals("end")) {
                    player.teleport(serverWorld3,player.getMainHandStack().getOrCreateNbt().getInt("x1"), player.getMainHandStack().getOrCreateNbt().getInt("y1"), player.getMainHandStack().getOrCreateNbt().getInt("z1"), 1f,1f);
                }
            }
            if(i == 2) {
                RegistryKey<World> registryKey = World.OVERWORLD;
                RegistryKey<World> registryKey2 = World.NETHER;
                RegistryKey<World> registryKey3 = World.END;
                ServerWorld serverWorld = ((ServerWorld)player.getWorld()).getServer().getWorld(registryKey);
                ServerWorld serverWorld2 = ((ServerWorld)player.getWorld()).getServer().getWorld(registryKey2);
                ServerWorld serverWorld3 = ((ServerWorld)player.getWorld()).getServer().getWorld(registryKey3);
                if(player.getMainHandStack().getOrCreateNbt().getString("world2").equals("overworld")) {
                    player.teleport(serverWorld,player.getMainHandStack().getOrCreateNbt().getInt("x2"), player.getMainHandStack().getOrCreateNbt().getInt("y2"), player.getMainHandStack().getOrCreateNbt().getInt("z2"), 1f,1f);
                }
                if(player.getMainHandStack().getOrCreateNbt().getString("world2").equals("nether")) {
                    player.teleport(serverWorld2,player.getMainHandStack().getOrCreateNbt().getInt("x2"), player.getMainHandStack().getOrCreateNbt().getInt("y2"), player.getMainHandStack().getOrCreateNbt().getInt("z2"), 1f,1f);
                }
                if(player.getMainHandStack().getOrCreateNbt().getString("world2").equals("end")) {
                    player.teleport(serverWorld3,player.getMainHandStack().getOrCreateNbt().getInt("x2"), player.getMainHandStack().getOrCreateNbt().getInt("y2"), player.getMainHandStack().getOrCreateNbt().getInt("z2"), 1f,1f);
                }
            }
        }
            if(i == 3) {
                RegistryKey<World> registryKey = World.OVERWORLD;
                RegistryKey<World> registryKey2 = World.NETHER;
                RegistryKey<World> registryKey3 = World.END;
                ServerWorld serverWorld = ((ServerWorld)player.getWorld()).getServer().getWorld(registryKey);
                ServerWorld serverWorld2 = ((ServerWorld)player.getWorld()).getServer().getWorld(registryKey2);
                ServerWorld serverWorld3 = ((ServerWorld)player.getWorld()).getServer().getWorld(registryKey3);
                if(player.getMainHandStack().getOrCreateNbt().getString("world3").equals("overworld")) {
                    player.teleport(serverWorld,player.getMainHandStack().getOrCreateNbt().getInt("x3"), player.getMainHandStack().getOrCreateNbt().getInt("y3"), player.getMainHandStack().getOrCreateNbt().getInt("z3"), 1f,1f);
                }
                if(player.getMainHandStack().getOrCreateNbt().getString("world3").equals("nether")) {
                    player.teleport(serverWorld2,player.getMainHandStack().getOrCreateNbt().getInt("x3"), player.getMainHandStack().getOrCreateNbt().getInt("y3"), player.getMainHandStack().getOrCreateNbt().getInt("z3"), 1f,1f);
                }
                if(player.getMainHandStack().getOrCreateNbt().getString("world3").equals("end")) {
                    player.teleport(serverWorld3,player.getMainHandStack().getOrCreateNbt().getInt("x3"), player.getMainHandStack().getOrCreateNbt().getInt("y3"), player.getMainHandStack().getOrCreateNbt().getInt("z3"), 1f,1f);
                }
            }
    }
}
