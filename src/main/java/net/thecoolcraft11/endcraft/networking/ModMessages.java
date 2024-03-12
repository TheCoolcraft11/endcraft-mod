package net.thecoolcraft11.endcraft.networking;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.util.Identifier;
import net.thecoolcraft11.endcraft.Endcraft;
import net.thecoolcraft11.endcraft.networking.packet.EnderStaffC2SPacket;
import net.thecoolcraft11.endcraft.networking.packet.TeleportationStaffC2SPacket;

public class ModMessages {
    public static final Identifier ENDER_STAFF_ID = new Identifier(Endcraft.MOD_ID, "ender_staff");
    public static final Identifier TELEPORTATION_STAFF_ID = new Identifier(Endcraft.MOD_ID, "teleportation_staff_id");

    public static void registerC2SPackets() {
        ServerPlayNetworking.registerGlobalReceiver(ENDER_STAFF_ID, EnderStaffC2SPacket::recive);
        ServerPlayNetworking.registerGlobalReceiver(TELEPORTATION_STAFF_ID, TeleportationStaffC2SPacket::recive);
    }
    public static void registerS2CPackets() {

    }
}
