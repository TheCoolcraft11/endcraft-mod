package net.thecoolcraft11.endcraft.item.custom;


import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class PhantasmaPrismItem extends Item {
    private final int FILL_LIMIT = 262176;

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
                context.getStack().getOrCreateNbt().putBoolean("hasp1", true);
            } else {
                context.getStack().getOrCreateNbt().putInt("x2", context.getBlockPos().getX());
                context.getStack().getOrCreateNbt().putInt("y2", context.getBlockPos().getY());
                context.getStack().getOrCreateNbt().putInt("z2", context.getBlockPos().getZ());
                context.getStack().getOrCreateNbt().putBoolean("hasp2", true);
            }
        }
        if (context.getPlayer().getMainHandStack().getOrCreateNbt().getInt("mode") == 2) {
            if (!context.getPlayer().isSneaking()) {
                context.getStack().getOrCreateNbt().putInt("x1", context.getBlockPos().getX());
                context.getStack().getOrCreateNbt().putInt("y1", context.getBlockPos().getY());
                context.getStack().getOrCreateNbt().putInt("z1", context.getBlockPos().getZ());
                context.getStack().getOrCreateNbt().putBoolean("hasp1", true);
            }else {
                if(context.getStack().getOrCreateNbt().getInt("radius") < 25) {
                    context.getStack().getOrCreateNbt().putInt("radius", context.getStack().getOrCreateNbt().getInt("radius") + 1);
                }else {
                    context.getStack().getOrCreateNbt().putInt("radius", 0);
                }
                context.getPlayer().sendMessage(Text.of(Text.translatable("message.endcraft.phantasma_prism.radius")).copy().append(String.valueOf(context.getStack().getOrCreateNbt().getInt("radius"))).formatted(Formatting.BLUE), true);
            }
        }
        if (context.getPlayer().getMainHandStack().getOrCreateNbt().getInt("mode") == 1) {
            if (!context.getPlayer().isSneaking()) {
                context.getStack().getOrCreateNbt().putInt("x1", context.getBlockPos().getX());
                context.getStack().getOrCreateNbt().putInt("y1", context.getBlockPos().getY());
                context.getStack().getOrCreateNbt().putInt("z1", context.getBlockPos().getZ());
                context.getStack().getOrCreateNbt().putBoolean("hasp1", true);
            } else {
                context.getStack().getOrCreateNbt().putInt("x2", context.getBlockPos().getX());
                context.getStack().getOrCreateNbt().putInt("y2", context.getBlockPos().getY());
                context.getStack().getOrCreateNbt().putInt("z2", context.getBlockPos().getZ());
                context.getStack().getOrCreateNbt().putBoolean("hasp1", true);
            }
        }
        if (context.getPlayer().getMainHandStack().getOrCreateNbt().getInt("mode") == 2) {
            if (!context.getPlayer().isSneaking()) {
                context.getStack().getOrCreateNbt().putInt("x1", context.getBlockPos().getX());
                context.getStack().getOrCreateNbt().putInt("y1", context.getBlockPos().getY());
                context.getStack().getOrCreateNbt().putInt("z1", context.getBlockPos().getZ());
                context.getStack().getOrCreateNbt().putBoolean("hasp1", true);
            }else {
                if(context.getStack().getOrCreateNbt().getInt("radius") < 25) {
                    context.getStack().getOrCreateNbt().putInt("radius", context.getStack().getOrCreateNbt().getInt("radius") + 1);
                }else {
                    context.getStack().getOrCreateNbt().putInt("radius", 0);
                }
                context.getPlayer().sendMessage(Text.of(Text.translatable("message.endcraft.phantasma_prism.radius")).copy().append(String.valueOf(context.getStack().getOrCreateNbt().getInt("radius"))).formatted(Formatting.BLUE), true);
            }
        }
        if (context.getPlayer().getMainHandStack().getOrCreateNbt().getInt("mode") == 3) {
            if (!context.getPlayer().isSneaking()) {
                context.getStack().getOrCreateNbt().putInt("x1", context.getBlockPos().getX());
                context.getStack().getOrCreateNbt().putInt("y1", context.getBlockPos().getY());
                context.getStack().getOrCreateNbt().putInt("z1", context.getBlockPos().getZ());
                context.getStack().getOrCreateNbt().putBoolean("hasp1", true);
            } else {
                context.getStack().getOrCreateNbt().putInt("x2", context.getBlockPos().getX());
                context.getStack().getOrCreateNbt().putInt("y2", context.getBlockPos().getY());
                context.getStack().getOrCreateNbt().putInt("z2", context.getBlockPos().getZ());
                context.getStack().getOrCreateNbt().putBoolean("hasp2", true);
            }
        }
        if (context.getPlayer().getMainHandStack().getOrCreateNbt().getInt("mode") == 4) {
            if (!context.getPlayer().isSneaking()) {
                context.getStack().getOrCreateNbt().putInt("x1", context.getBlockPos().getX());
                context.getStack().getOrCreateNbt().putInt("y1", context.getBlockPos().getY());
                context.getStack().getOrCreateNbt().putInt("z1", context.getBlockPos().getZ());
                context.getStack().getOrCreateNbt().putBoolean("hasp1", true);
            }else {
                if(context.getStack().getOrCreateNbt().getInt("radius") < 25) {
                    context.getStack().getOrCreateNbt().putInt("radius", context.getStack().getOrCreateNbt().getInt("radius") + 1);
                }else {
                    context.getStack().getOrCreateNbt().putInt("radius", 0);
                }
                context.getPlayer().sendMessage(Text.of(Text.translatable("message.endcraft.phantasma_prism.radius")).copy().append(String.valueOf(context.getStack().getOrCreateNbt().getInt("radius"))).formatted(Formatting.BLUE), true);
            }
            if (context.getPlayer().getMainHandStack().getOrCreateNbt().getInt("mode") == 5) {
                if (!context.getPlayer().isSneaking()) {
                    context.getStack().getOrCreateNbt().putInt("x1", context.getBlockPos().getX());
                    context.getStack().getOrCreateNbt().putInt("y1", context.getBlockPos().getY());
                    context.getStack().getOrCreateNbt().putInt("z1", context.getBlockPos().getZ());
                    context.getStack().getOrCreateNbt().putBoolean("hasp1", true);
                } else {
                    context.getStack().getOrCreateNbt().putInt("x2", context.getBlockPos().getX());
                    context.getStack().getOrCreateNbt().putInt("y2", context.getBlockPos().getY());
                    context.getStack().getOrCreateNbt().putInt("z2", context.getBlockPos().getZ());
                    context.getStack().getOrCreateNbt().putBoolean("hasp2", true);
                }
            }
        }
        return ActionResult.SUCCESS;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if(user.isSneaking()) {
            if(user.getMainHandStack().getOrCreateNbt().getInt("mode") < 6) {
                user.getMainHandStack().getOrCreateNbt().putInt("mode", user.getMainHandStack().getOrCreateNbt().getInt("mode") + 1);
            }else {
                user.getMainHandStack().getOrCreateNbt().putInt("mode", 1);
            }
            user.sendMessage(Text.translatable("message.endcraft.phantasma_prism_mode." + user.getMainHandStack().getOrCreateNbt().getInt("mode")).formatted(Formatting.AQUA), true);

            // 1 cube, 2 sphere
        }else {
            BlockPos pos1 = new BlockPos(user.getMainHandStack().getOrCreateNbt().getInt("x1"), user.getMainHandStack().getOrCreateNbt().getInt("y1"), user.getMainHandStack().getOrCreateNbt().getInt("z1"));
            BlockPos pos2 = new BlockPos(user.getMainHandStack().getOrCreateNbt().getInt("x2"), user.getMainHandStack().getOrCreateNbt().getInt("y2"), user.getMainHandStack().getOrCreateNbt().getInt("z2"));
            NbtCompound item = user.getMainHandStack().getOrCreateNbt();

            if(item.getInt("mode") == 1) {
                if(countBlocks(pos1, pos2) <= FILL_LIMIT) {
                    if(getItemCount(user, user.getOffHandStack().getItem()) >= countBlocks(pos1, pos2)) {
                        fillBlocks(world, pos1, pos2, Block.getBlockFromItem(user.getOffHandStack().getItem()));
                        user.getMainHandStack().getOrCreateNbt().remove("x1");
                        user.getMainHandStack().getOrCreateNbt().remove("y1");
                        user.getMainHandStack().getOrCreateNbt().remove("z1");
                        user.getMainHandStack().getOrCreateNbt().remove("x2");
                        user.getMainHandStack().getOrCreateNbt().remove("y2");
                        user.getMainHandStack().getOrCreateNbt().remove("z2");
                        user.getMainHandStack().getOrCreateNbt().remove("radius");
                        user.getMainHandStack().getOrCreateNbt().remove("hasp1");
                        user.getMainHandStack().getOrCreateNbt().remove("hasp2");
                    }else {
                        user.sendMessage(Text.of(Text.translatable("message.endcraft.phantasma_prism_to.many.blocks").copy().append(" " + getItemCount(user, user.getOffHandStack().getItem()) + " < " + countBlocks(pos1, pos2)).formatted(Formatting.DARK_RED)), true);
                    }
                }else {
                    user.sendMessage(Text.translatable("message.endcraft.phantasma_prism_fill.limit").copy().append(" "+ FILL_LIMIT).formatted(Formatting.DARK_RED));

                }
            }else {
                if(item.getInt("mode") == 2) {
                    if(countSphere(pos1, item.getInt("radius")) <= FILL_LIMIT) {
                        if(getItemCount(user, user.getOffHandStack().getItem()) <= countSphere(pos1, item.getInt("radius"))) {
                            fillSphere(world, pos1, user.getMainHandStack().getOrCreateNbt().getInt("radius"), Block.getBlockFromItem(user.getOffHandStack().getItem()));
                            user.getMainHandStack().getOrCreateNbt().remove("x1");
                            user.getMainHandStack().getOrCreateNbt().remove("y1");
                            user.getMainHandStack().getOrCreateNbt().remove("z1");
                            user.getMainHandStack().getOrCreateNbt().remove("x2");
                            user.getMainHandStack().getOrCreateNbt().remove("y2");
                            user.getMainHandStack().getOrCreateNbt().remove("z2");
                            user.getMainHandStack().getOrCreateNbt().remove("radius");
                            user.getMainHandStack().getOrCreateNbt().remove("hasp1");
                            user.getMainHandStack().getOrCreateNbt().remove("hasp2");
                        }else {
                            user.sendMessage(Text.of(Text.translatable("message.endcraft.phantasma_prism_to.many.blocks").copy().append(" " + getItemCount(user, user.getOffHandStack().getItem()) + " < " + countBlocks(pos1, pos2)).formatted(Formatting.DARK_RED)), true);
                        }
                    }else {
                        user.sendMessage(Text.translatable("message.endcraft.phantasma_prism_fill.limit").copy().append(" "+ FILL_LIMIT).formatted(Formatting.DARK_RED));

                    }
                }
            }
            if(item.getInt("mode") == 3) {
                if(countBlocks(pos1, pos2) <= FILL_LIMIT) {
                    if(!user.getOffHandStack().isEmpty()) {
                        replaceBlocks(world, pos1, pos2, Block.getBlockFromItem(user.getOffHandStack().getItem()), Blocks.AIR);
                    }else {
                        fillBlocks(world, pos1, pos2, Block.getBlockFromItem(user.getOffHandStack().getItem()));
                    }
                    user.getMainHandStack().getOrCreateNbt().remove("x1");
                    user.getMainHandStack().getOrCreateNbt().remove("y1");
                    user.getMainHandStack().getOrCreateNbt().remove("z1");
                    user.getMainHandStack().getOrCreateNbt().remove("x2");
                    user.getMainHandStack().getOrCreateNbt().remove("y2");
                    user.getMainHandStack().getOrCreateNbt().remove("z2");
                    user.getMainHandStack().getOrCreateNbt().remove("radius");
                    user.getMainHandStack().getOrCreateNbt().remove("hasp1");
                    user.getMainHandStack().getOrCreateNbt().remove("hasp2");

                }else {
                    user.sendMessage(Text.translatable("message.endcraft.phantasma_prism_fill.limit").copy().append(" "+ FILL_LIMIT).formatted(Formatting.DARK_RED));

                }
            }else {
                if(item.getInt("mode") == 4) {
                    if(countSphere(pos1, item.getInt("radius")) <= FILL_LIMIT) {
                        if(!user.getOffHandStack().isEmpty()) {
                            replaceSphere(world, pos1, user.getMainHandStack().getOrCreateNbt().getInt("radius"), Block.getBlockFromItem(user.getOffHandStack().getItem()), Blocks.AIR);
                        }else {
                            fillSphere(world, pos1, user.getMainHandStack().getOrCreateNbt().getInt("radius"), Blocks.AIR);
                        }
                        user.getMainHandStack().getOrCreateNbt().remove("x1");
                        user.getMainHandStack().getOrCreateNbt().remove("y1");
                        user.getMainHandStack().getOrCreateNbt().remove("z1");
                        user.getMainHandStack().getOrCreateNbt().remove("x2");
                        user.getMainHandStack().getOrCreateNbt().remove("y2");
                        user.getMainHandStack().getOrCreateNbt().remove("z2");
                        user.getMainHandStack().getOrCreateNbt().remove("radius");
                        user.getMainHandStack().getOrCreateNbt().remove("hasp1");
                        user.getMainHandStack().getOrCreateNbt().remove("hasp2");
                    }else {
                        user.sendMessage(Text.translatable("message.endcraft.phantasma_prism_fill.limit").copy().append(" "+ FILL_LIMIT).formatted(Formatting.DARK_RED));
                    }
                }
            }
                if(item.getInt("mode") == 5) {
                        if (countHollowBlocks(pos1, pos2) <= FILL_LIMIT) {
                            if (getItemCount(user, user.getOffHandStack().getItem()) >= countHollowBlocks( pos1, pos2)) {
                                fillHollowBlocks(world, pos1, pos2, Block.getBlockFromItem(user.getOffHandStack().getItem()));
                                user.getMainHandStack().getOrCreateNbt().remove("x1");
                                user.getMainHandStack().getOrCreateNbt().remove("y1");
                                user.getMainHandStack().getOrCreateNbt().remove("z1");
                                user.getMainHandStack().getOrCreateNbt().remove("x2");
                                user.getMainHandStack().getOrCreateNbt().remove("y2");
                                user.getMainHandStack().getOrCreateNbt().remove("z2");
                                user.getMainHandStack().getOrCreateNbt().remove("radius");
                                user.getMainHandStack().getOrCreateNbt().remove("hasp1");
                                user.getMainHandStack().getOrCreateNbt().remove("hasp2");
                            } else {
                                user.sendMessage(Text.of(Text.translatable("message.endcraft.phantasma_prism_to.many.blocks").copy().append(" " + getItemCount(user, user.getOffHandStack().getItem()) + " < " + countHollowBlocks( pos1, pos2)).formatted(Formatting.DARK_RED)), true);
                            }
                        } else {
                            user.sendMessage(Text.translatable("message.endcraft.phantasma_prism_fill.limit").copy().append(" "+ FILL_LIMIT).formatted(Formatting.DARK_RED));

                        }
                    }
                if(item.getInt("mode") == 6) {
                        if (countHollowBlocks(pos1, pos2) <= FILL_LIMIT) {
                            if (getItemCount(user, user.getOffHandStack().getItem()) >= countPyramid( pos1, pos2)) {
                                fillPyramid(world, pos1, pos2, Block.getBlockFromItem(user.getOffHandStack().getItem()));
                                user.getMainHandStack().getOrCreateNbt().remove("x1");
                                user.getMainHandStack().getOrCreateNbt().remove("y1");
                                user.getMainHandStack().getOrCreateNbt().remove("z1");
                                user.getMainHandStack().getOrCreateNbt().remove("x2");
                                user.getMainHandStack().getOrCreateNbt().remove("y2");
                                user.getMainHandStack().getOrCreateNbt().remove("z2");
                                user.getMainHandStack().getOrCreateNbt().remove("radius");
                                user.getMainHandStack().getOrCreateNbt().remove("hasp1");
                                user.getMainHandStack().getOrCreateNbt().remove("hasp2");
                            } else {
                                user.sendMessage(Text.of(Text.translatable("message.endcraft.phantasma_prism_to.many.blocks").copy().append(" " + getItemCount(user, user.getOffHandStack().getItem()) + " < " + countPyramid(pos1, pos2)).formatted(Formatting.DARK_RED)), true);
                            }
                        } else {
                            user.sendMessage(Text.translatable("message.endcraft.phantasma_prism_fill.limit").copy().append(" "+ FILL_LIMIT).formatted(Formatting.DARK_RED));

                        }
                    }
            }


        return super.use(world, user, hand);
    }

    private static void fillBlocks(World world, BlockPos pos1, BlockPos pos2, Block block) {
        int minX = Math.min(pos1.getX(), pos2.getX());
        int minY = Math.min(pos1.getY(), pos2.getY());
        int minZ = Math.min(pos1.getZ(), pos2.getZ());
        int maxX = Math.max(pos1.getX(), pos2.getX());
        int maxY = Math.max(pos1.getY(), pos2.getY());
        int maxZ = Math.max(pos1.getZ(), pos2.getZ());

        for (int x = minX; x <= maxX; x++) {
            for (int y = minY; y <= maxY; y++) {
                for (int z = minZ; z <= maxZ; z++) {
                    BlockPos currentPos = new BlockPos(x, y, z);
                    world.setBlockState(currentPos, block.getDefaultState(), 3);
                }
            }
        }
    }
    private static void fillHollowBlocks(World world, BlockPos pos1, BlockPos pos2, Block block) {

        int minX = Math.min(pos1.getX(), pos2.getX());
        int minY = Math.min(pos1.getY(), pos2.getY());
        int minZ = Math.min(pos1.getZ(), pos2.getZ());
        int maxX = Math.max(pos1.getX(), pos2.getX());
        int maxY = Math.max(pos1.getY(), pos2.getY());
        int maxZ = Math.max(pos1.getZ(), pos2.getZ());

        for (int x = minX; x <= maxX; x++) {
            for (int y = minY; y <= maxY; y++) {
                for (int z = minZ; z <= maxZ; z++) {
                    BlockPos currentPos = new BlockPos(x, y, z);
                    if(x == minX || x == maxX || y == minY || y == maxY || z == minZ || z == maxZ) {
                        world.setBlockState(currentPos, block.getDefaultState(), 3);
                    }
                }
            }
        }
    }
    private static int countHollowBlocks(BlockPos pos1, BlockPos pos2) {

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
                    if(x == minX || x == maxX || y == minY || y == maxY || z == minZ || z == maxZ) {
                  count++;
                    }
                }
            }
        }
        return count;
    }
    private static void replaceBlocks(World world, BlockPos pos1, BlockPos pos2, Block block, Block block2) {
        int minX = Math.min(pos1.getX(), pos2.getX());
        int minY = Math.min(pos1.getY(), pos2.getY());
        int minZ = Math.min(pos1.getZ(), pos2.getZ());
        int maxX = Math.max(pos1.getX(), pos2.getX());
        int maxY = Math.max(pos1.getY(), pos2.getY());
        int maxZ = Math.max(pos1.getZ(), pos2.getZ());

        for (int x = minX; x <= maxX; x++) {
            for (int y = minY; y <= maxY; y++) {
                for (int z = minZ; z <= maxZ; z++) {
                    BlockPos currentPos = new BlockPos(x, y, z);
                    if(world.getBlockState(currentPos) == block.getDefaultState()) {
                        world.setBlockState(currentPos, block2.getDefaultState());
                    }
                }
            }
        }
    }
    private static int countBlocks(BlockPos pos1, BlockPos pos2) {
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
    private static int countSphere( BlockPos center, int radius) {
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
    private static void fillSphere(World world, BlockPos center, int radius, Block block) {
        int diameter = radius * 2;
        for (int x = center.getX() - radius; x <= center.getX() + radius; x++) {
            for (int y = center.getY() - radius; y <= center.getY() + radius; y++) {
                for (int z = center.getZ() - radius; z <= center.getZ() + radius; z++) {
                    double distanceSquared = Math.pow(x - center.getX(), 2) + Math.pow(y - center.getY(), 2) + Math.pow(z - center.getZ(), 2);
                    if (distanceSquared <= radius * radius) {
                        BlockPos currentPos = new BlockPos(x, y, z);
                        world.setBlockState(currentPos, block.getDefaultState(), 3);
                    }
                }
            }
        }
    }
    private static void replaceSphere(World world, BlockPos center, int radius, Block block, Block block2) {
        int diameter = radius * 2;
        for (int x = center.getX() - radius; x <= center.getX() + radius; x++) {
            for (int y = center.getY() - radius; y <= center.getY() + radius; y++) {
                for (int z = center.getZ() - radius; z <= center.getZ() + radius; z++) {
                    double distanceSquared = Math.pow(x - center.getX(), 2) + Math.pow(y - center.getY(), 2) + Math.pow(z - center.getZ(), 2);
                    if (distanceSquared <= radius * radius) {
                        BlockPos currentPos = new BlockPos(x, y, z);
                       if(world.getBlockState(currentPos) == block.getDefaultState()) {
                           world.setBlockState(currentPos, block2.getDefaultState());
                       }
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
    public static void fillPyramid(World world, BlockPos pos1, BlockPos pos2, Block block) {
        int minX = Math.min(pos1.getX(), pos2.getX());
        int minY = Math.min(pos1.getY(), pos2.getY());
        int minZ = Math.min(pos1.getZ(), pos2.getZ());


        int height = Math.abs(pos1.getY() - pos2.getY()) + 1;

        for (int y = 0; y < height; y++) {
            int width = height - y - 1;

            for (int x = minX + y; x <= minX + width; x++) {
                for (int z = minZ + y; z <= minZ + width; z++) {
                    BlockPos blockPos = new BlockPos(x, minY + y, z);
                    world.setBlockState(blockPos, block.getDefaultState(), 3); // Platzieren Sie den Block
                }
            }
        }
    }
    public static int countPyramid(BlockPos pos1, BlockPos pos2) {
        int minX = Math.min(pos1.getX(), pos2.getX());
        int minY = Math.min(pos1.getY(), pos2.getY());
        int minZ = Math.min(pos1.getZ(), pos2.getZ());


        int height = Math.abs(pos1.getY() - pos2.getY()) + 1;
        int count = 0;

        for (int y = 0; y < height; y++) {
            int width = height - y - 1;

            for (int x = minX + y; x <= minX + width; x++) {
                for (int z = minZ + y; z <= minZ + width; z++) {
                    count++;

                }
            }
        }
        return count;
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        if(stack.getOrCreateNbt().getInt("mode") == 1) {
            return stack.getOrCreateNbt().getBoolean("hasp1") && stack.getOrCreateNbt().getBoolean("hasp2");
        }
        if(stack.getOrCreateNbt().getInt("mode") == 2) {
            return stack.getOrCreateNbt().getBoolean("hasp1") && stack.getOrCreateNbt().getInt("radius") > 0;
        }
        if(stack.getOrCreateNbt().getInt("mode") == 3) {
            return stack.getOrCreateNbt().getBoolean("hasp1") && stack.getOrCreateNbt().getBoolean("hasp2");
        }
        if(stack.getOrCreateNbt().getInt("mode") == 4) {
        return stack.getOrCreateNbt().getBoolean("hasp1") && stack.getOrCreateNbt().getInt("radius") > 0;
    }
        if(stack.getOrCreateNbt().getInt("mode") == 5) {
            return stack.getOrCreateNbt().getBoolean("hasp1") && stack.getOrCreateNbt().getBoolean("hasp2");
        }
        return true;
    }
    
    @Override
    public Text getName(ItemStack stack) {
        if (!(stack.getOrCreateNbt().getInt("mode") <= 0)) {
            return Text.translatable("item.endcraft.phantasma_prism").copy().append("[").append(Text.translatable("message.endcraft.phantasma_prism_mode_item." + stack.getOrCreateNbt().getInt("mode")).append("]"));
        }
        return super.getName(stack);
    }
}