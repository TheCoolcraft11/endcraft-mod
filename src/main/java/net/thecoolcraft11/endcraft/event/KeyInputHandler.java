package net.thecoolcraft11.endcraft.event;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.mixin.client.keybinding.KeyBindingAccessor;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.network.PacketByteBuf;
import net.thecoolcraft11.endcraft.networking.ModMessages;
import org.lwjgl.glfw.GLFW;

public class KeyInputHandler {
    public static final String KEY_CATEGORY_ENDCRAFT = "key.category.endcraft.endcraft";

    private static KeyBinding teleportation_staff_hold;
    private static KeyBinding teleportation_staff_1;

    private static KeyBinding teleportation_staff_2;
    private static KeyBinding teleportation_staff_3;


    public static void registerKeyInputs() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if(teleportation_staff_hold.isPressed()) {
              if(teleportation_staff_1.wasPressed()) {
                  PacketByteBuf buf = PacketByteBufs.create();
                  buf.writeInt(1);
                  ClientPlayNetworking.send(ModMessages.TELEPORTATION_STAFF_ID, buf);
              }
              if(teleportation_staff_2.wasPressed()) {
                  PacketByteBuf buf = PacketByteBufs.create();
                  buf.writeInt(2);
                  ClientPlayNetworking.send(ModMessages.TELEPORTATION_STAFF_ID, buf);
              }
              if(teleportation_staff_3.wasPressed()) {
                  PacketByteBuf buf = PacketByteBufs.create();
                  buf.writeInt(3);
                  ClientPlayNetworking.send(ModMessages.TELEPORTATION_STAFF_ID, buf);
              }
            }
        });
    }

    public static void register() {
        teleportation_staff_hold = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.endcraft.teleportation_staff_hold",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_LEFT_ALT,
                "category.endcraft.endcraft"
        ));
        teleportation_staff_1 = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.endcraft.teleportation_staff_1",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_Y,
                "category.endcraft.endcraft"
        ));
        teleportation_staff_2 = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.endcraft.teleportation_staff_2",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_X,
                "category.endcraft.endcraft"
        ));
        teleportation_staff_3 = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.endcraft.teleportation_staff_3",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_C,
                "category.endcraft.endcraft"
        ));
        registerKeyInputs();
    }
}