package net.thecoolcraft11.endcraft.networking.packet;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.thecoolcraft11.endcraft.item.custom.EnderStaffItem;
import net.thecoolcraft11.endcraft.util.Raycast;

public class EnderStaffC2SPacket {
    public static void recive(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender) {

        double reachDistance = 64;
        if(EnderStaffItem.hasNbtUpgrade(player.getMainHandStack(), "range")) {
            reachDistance = 64 + 10 * EnderStaffItem.getNbtLevel(player.getMainHandStack(), "range");
        }
        if (!(player.getMainHandStack().getDamage() >= player.getMainHandStack().getMaxDamage())) {

            player.teleport(player.getServerWorld(),
                    player.raycast(reachDistance, 0, false).getPos().x + 0.5,
                    player.raycast(reachDistance, 0, false).getPos().y + 1,
                    player.raycast(reachDistance, 0, false).getPos().z + 0.5,
                    player.getYaw(),
                    player.getPitch());

            if (EnderStaffItem.hasNbtUpgrade(player.getMainHandStack(), "durability")) {
                player.getMainHandStack().setDamage((int) (player.getMainHandStack().getDamage() + 5 / EnderStaffItem.getNbtLevel(player.getMainHandStack(), "durability")));
            } else {
                player.getMainHandStack().setDamage(player.getMainHandStack().getDamage() + 5);
            }
            if (EnderStaffItem.hasNbtUpgrade(player.getMainHandStack(), "fall")) {
                player.fallDistance = (float) (player.fallDistance - EnderStaffItem.getNbtLevel(player.getMainHandStack(), "fall") * 10);
            }
        }
    }
}
