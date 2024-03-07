package net.thecoolcraft11.endcraft.item.custom;


import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.Keyboard;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.StackReference;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.Items;
import net.minecraft.screen.slot.Slot;
import net.minecraft.text.Text;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.thecoolcraft11.endcraft.Endcraft;
import net.thecoolcraft11.endcraft.EndcraftClient;
import net.thecoolcraft11.endcraft.block.ModBlocks;
import net.thecoolcraft11.endcraft.item.ModItems;
import org.lwjgl.glfw.GLFW;


public class PhantasmaPrismItem extends Item {

    public PhantasmaPrismItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        if (context.getPlayer().getMainHandStack().getOrCreateNbt().getInt("mode") == 1) {
            if (!context.getPlayer().isSneaking()) {
                context.getStack().getOrCreateNbt().putInt("x1", context.getBlockPos().getX());
                context.getStack().getOrCreateNbt().putInt("y1", context.getBlockPos().getY());
                context.getStack().getOrCreateNbt().putInt("z1", context.getBlockPos().getZ());
            } else {
                context.getStack().getOrCreateNbt().putInt("x2", context.getBlockPos().getX());
                context.getStack().getOrCreateNbt().putInt("y2", context.getBlockPos().getY());
                context.getStack().getOrCreateNbt().putInt("z2", context.getBlockPos().getZ());
            }
        }
        if (context.getPlayer().getMainHandStack().getOrCreateNbt().getInt("mode") == 2) {
            if (!context.getPlayer().isSneaking()) {
                context.getStack().getOrCreateNbt().putInt("x1", context.getBlockPos().getX());
                context.getStack().getOrCreateNbt().putInt("y1", context.getBlockPos().getY());
                context.getStack().getOrCreateNbt().putInt("z1", context.getBlockPos().getZ());
            }else {
                if(context.getStack().getOrCreateNbt().getInt("radius") < 16) {
                    context.getStack().getOrCreateNbt().putInt("radius", context.getStack().getOrCreateNbt().getInt("radius") + 1);
                }else {
                    context.getStack().getOrCreateNbt().putInt("radius", 0);
                }
                context.getPlayer().sendMessage(Text.of(Text.translatable("message.endcraft.phantasma_prism.radius")).copy().append(String.valueOf(context.getStack().getOrCreateNbt().getInt("radius"))).formatted(Formatting.BLUE), true);
            }
        }
        return ActionResult.SUCCESS;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        Endcraft.LOGGER.info(String.valueOf(user.getOffHandStack().getItem() == Items.AIR));
        if(user.isSneaking()) {
            if(user.getMainHandStack().getOrCreateNbt().getInt("mode") < 2) {
                user.getMainHandStack().getOrCreateNbt().putInt("mode", user.getMainHandStack().getOrCreateNbt().getInt("mode") + 1);
            }else {
                user.getMainHandStack().getOrCreateNbt().putInt("mode", 1);
            }
            user.sendMessage(Text.translatable("message.endcraft.phantasma_prism_mode." + user.getMainHandStack().getOrCreateNbt().getInt("mode")).formatted(Formatting.AQUA),true);
             // 1 cube, 2 sphere
        }else {

            BlockPos pos1 = new BlockPos(user.getMainHandStack().getOrCreateNbt().getInt("x1"), user.getMainHandStack().getOrCreateNbt().getInt("y1"), user.getMainHandStack().getOrCreateNbt().getInt("z1"));
            BlockPos pos2 = new BlockPos(user.getMainHandStack().getOrCreateNbt().getInt("x2"), user.getMainHandStack().getOrCreateNbt().getInt("y2"), user.getMainHandStack().getOrCreateNbt().getInt("z2"));
            if (countBlocks(world, pos1, pos2) < 16386 && user.getMainHandStack().getOrCreateNbt().getInt("mode") == 1) {
                    if(!(user.getOffHandStack().getItem() == ModItems.VOID_ESSENCE)) {
                    if(getItemCount(user, user.getOffHandStack().getItem()) >= countBlocks(world, pos1, pos2)) {
                        fillBlocks(world, pos1, pos2, Block.getBlockFromItem(user.getOffHandStack().getItem()));
                        user.getMainHandStack().getOrCreateNbt().remove("x1");
                        user.getMainHandStack().getOrCreateNbt().remove("y1");
                        user.getMainHandStack().getOrCreateNbt().remove("z1");
                        user.getMainHandStack().getOrCreateNbt().remove("x2");
                        user.getMainHandStack().getOrCreateNbt().remove("y2");
                        user.getMainHandStack().getOrCreateNbt().remove("z2");
                        user.getMainHandStack().getOrCreateNbt().remove("radius");
                    }else {
                        user.sendMessage(Text.of(Text.translatable("message.endcraft.phantasma_prism_to.many.blocks").copy().append(" "+ getItemCount(user, user.getOffHandStack().getItem()) + " < " + countBlocks(world, pos1, pos2)).formatted(Formatting.DARK_RED)), true);
                    }
                }else {
                        fillBlocks(world, pos1, pos2, Blocks.AIR);
                        user.getMainHandStack().getOrCreateNbt().remove("x1");
                        user.getMainHandStack().getOrCreateNbt().remove("y1");
                        user.getMainHandStack().getOrCreateNbt().remove("z1");
                        user.getMainHandStack().getOrCreateNbt().remove("x2");
                        user.getMainHandStack().getOrCreateNbt().remove("y2");
                        user.getMainHandStack().getOrCreateNbt().remove("z2");
                        user.getMainHandStack().getOrCreateNbt().remove("radius");
                    }
                }else {
                if(user.getMainHandStack().getOrCreateNbt().getInt("mode") == 1) {
                    user.sendMessage(Text.translatable("message.endcraft.phantasma_prism_fill.limit").copy().append(" 16386").formatted(Formatting.DARK_RED));
                }
            }
            if (countSphere(world, pos1, user.getMainHandStack().getOrCreateNbt().getInt("radius")) < 16386 && user.getMainHandStack().getOrCreateNbt().getInt("mode") == 2){
                    if(!(user.getOffHandStack().getItem() == ModItems.VOID_ESSENCE)) {
                    if(getItemCount(user, user.getOffHandStack().getItem()) >= countSphere(world, pos1, user.getMainHandStack().getOrCreateNbt().getInt("radius"))) {
                        spawnSphere(world, pos1, user.getMainHandStack().getOrCreateNbt().getInt("radius"), Block.getBlockFromItem(user.getOffHandStack().getItem()));
                        user.getMainHandStack().getOrCreateNbt().remove("x1");
                        user.getMainHandStack().getOrCreateNbt().remove("y1");
                        user.getMainHandStack().getOrCreateNbt().remove("z1");
                        user.getMainHandStack().getOrCreateNbt().remove("x2");
                        user.getMainHandStack().getOrCreateNbt().remove("y2");
                        user.getMainHandStack().getOrCreateNbt().remove("z2");
                        user.getMainHandStack().getOrCreateNbt().remove("radius");
                    }else {
                            user.sendMessage(Text.of(Text.translatable("message.endcraft.phantasma_prism_to.many.blocks").copy().append(" "+ getItemCount(user, user.getOffHandStack().getItem()) + " < " + countSphere(world, pos1, user.getMainHandStack().getOrCreateNbt().getInt("radius"))).formatted(Formatting.DARK_RED)), true);
                        }
                     }else {
                        fillBlocks(world, pos1, pos2, Blocks.AIR);
                        user.getMainHandStack().getOrCreateNbt().remove("x1");
                        user.getMainHandStack().getOrCreateNbt().remove("y1");
                        user.getMainHandStack().getOrCreateNbt().remove("z1");
                        user.getMainHandStack().getOrCreateNbt().remove("x2");
                        user.getMainHandStack().getOrCreateNbt().remove("y2");
                        user.getMainHandStack().getOrCreateNbt().remove("z2");
                        user.getMainHandStack().getOrCreateNbt().remove("radius");
                    }
            }else {
                if(user.getMainHandStack().getOrCreateNbt().getInt("mode") == 2) {
                    user.sendMessage(Text.translatable("message.endcraft.phantasma_prism_fill.limit").copy().append(" 16386").formatted(Formatting.DARK_RED));
                }
            }
        }


        return super.use(world, user, hand);
    }

    private static void fillBlocks(World world, BlockPos pos1, BlockPos pos2, Block block) {
        // Bestimme die Grenzen der zu füllenden Bereiche
        int minX = Math.min(pos1.getX(), pos2.getX());
        int minY = Math.min(pos1.getY(), pos2.getY());
        int minZ = Math.min(pos1.getZ(), pos2.getZ());
        int maxX = Math.max(pos1.getX(), pos2.getX());
        int maxY = Math.max(pos1.getY(), pos2.getY());
        int maxZ = Math.max(pos1.getZ(), pos2.getZ());

        // Fülle die Blöcke im definierten Bereich mit dem angegebenen Blocktyp
        for (int x = minX; x <= maxX; x++) {
            for (int y = minY; y <= maxY; y++) {
                for (int z = minZ; z <= maxZ; z++) {
                    BlockPos currentPos = new BlockPos(x, y, z);
                    world.setBlockState(currentPos, block.getDefaultState(), 3); // 3 bedeutet Auslösen von Blockaktualisierungen
                }
            }
        }
    }
    private static int countBlocks(World world, BlockPos pos1, BlockPos pos2) {
        int minX = Math.min(pos1.getX(), pos2.getX());
        int minY = Math.min(pos1.getY(), pos2.getY());
        int minZ = Math.min(pos1.getZ(), pos2.getZ());
        int maxX = Math.max(pos1.getX(), pos2.getX());
        int maxY = Math.max(pos1.getY(), pos2.getY());
        int maxZ = Math.max(pos1.getZ(), pos2.getZ());

        int count = 0;
        for (int x = minX; x <= maxX; x++) {
            for (int y = minY; y <= maxY; y++) {
                for (int z = minZ; z <= maxZ; z++) {
                    BlockPos currentPos = new BlockPos(x, y, z);
                    count++;
                }
            }
        }
        return count;
    }
    private static int countSphere(World world, BlockPos center, int radius) {
        int diameter = radius * 2;
        int count = 0;
        for (int x = center.getX() - radius; x <= center.getX() + radius; x++) {
            for (int y = center.getY() - radius; y <= center.getY() + radius; y++) {
                for (int z = center.getZ() - radius; z <= center.getZ() + radius; z++) {
                    double distanceSquared = Math.pow(x - center.getX(), 2) + Math.pow(y - center.getY(), 2) + Math.pow(z - center.getZ(), 2);
                    if (distanceSquared <= radius * radius) {
                        BlockPos currentPos = new BlockPos(x, y, z);
                      count++;
                    }
                }
            }
        }
        return count;
    }
    private static void spawnSphere(World world, BlockPos center, int radius, Block block) {
        int diameter = radius * 2;
        for (int x = center.getX() - radius; x <= center.getX() + radius; x++) {
            for (int y = center.getY() - radius; y <= center.getY() + radius; y++) {
                for (int z = center.getZ() - radius; z <= center.getZ() + radius; z++) {
                    double distanceSquared = Math.pow(x - center.getX(), 2) + Math.pow(y - center.getY(), 2) + Math.pow(z - center.getZ(), 2);
                    if (distanceSquared <= radius * radius) {
                        BlockPos currentPos = new BlockPos(x, y, z);
                        world.setBlockState(currentPos, block.getDefaultState(), 3); // 3 bedeutet Auslösen von Blockaktualisierungen
                    }
                }
            }
        }
    }
    private static int getItemCount(PlayerEntity player, Item item) {
        Inventory inventory = player.getInventory();
        int count = 0;

        for (int i = 0; i < inventory.size(); i++) {
            ItemStack stack = inventory.getStack(i);
            if (stack.getItem() == item) {
                count += stack.getCount();
            }
        }
        return count;
    }
}