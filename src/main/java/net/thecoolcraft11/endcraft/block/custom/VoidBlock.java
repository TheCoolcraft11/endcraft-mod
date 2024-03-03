package net.thecoolcraft11.endcraft.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.thecoolcraft11.endcraft.block.ModBlocks;
import net.thecoolcraft11.endcraft.statuseffects.ModStatusEffects;

import java.util.Random;

public class VoidBlock extends Block {
    private static final VoxelShape SHAPE = Block.createCuboidShape(0, 0, 0, 0, 0, 0);
    public VoidBlock(Settings settings) {
        super(settings);
    }
    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }
    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
       if(entity instanceof PlayerEntity) {
        PlayerEntity player = (PlayerEntity) entity;
        StatusEffectInstance effect = null;
        if (player.hasStatusEffect(ModStatusEffects.VOID)) {
            int duration = 1;
            effect = new StatusEffectInstance(ModStatusEffects.VOID, player.getStatusEffect(ModStatusEffects.VOID).getDuration() + 1, 1);
            if (player.getStatusEffect(ModStatusEffects.VOID).getDuration()/ 10 < 25) {
                duration = player.getStatusEffect(ModStatusEffects.VOID).getDuration()/ 10;
            }else {
                duration = 25;
            }
            spread(world, pos, duration, 500, false);
        }else {
           // spread(world,pos,5, 250,false);
            effect = new StatusEffectInstance(ModStatusEffects.VOID, 1, 1);
        }
        player.addStatusEffect(effect);
        }else {
           Random random = new Random();
           int rxyz = random.nextInt(64 * 2)- 64 + 1;
           int rd = random.nextInt(16)+ 1;
           entity.kill();
           //drawLine(world,pos, pos.add(rxyz,rxyz,rxyz), ModBlocks.VOID_FLUID, rd);
       }

    }


    private void spread(World world, BlockPos centerPos, int radius, int chance, boolean spreadair) {
        int halfRadius = radius / 2;

        for (int x = -halfRadius; x <= halfRadius; x++) {
            for (int y = -halfRadius; y <= halfRadius; y++) {
                for (int z = -halfRadius; z <= halfRadius; z++) {
                    BlockPos blockPos = centerPos.add(x, y-halfRadius, z);
                    if(spreadair) {
                    if(!(world.getBlockState(blockPos).getBlock().getBlastResistance() >= 3600000)) {
                        Random random = new Random();
                        int randomnumber = random.nextInt(chance) + 1;
                        if (randomnumber == 1) {
                            world.setBlockState(blockPos, ModBlocks.VOID_FLUID.getDefaultState());
                            }
                        }
                    }else {
                        if(!(world.getBlockState(blockPos).getBlock().getBlastResistance() >= 3600000 )) {
                            Random random = new Random();
                            if(world.getBlockState(blockPos).getBlock() != Blocks.AIR) {
                            int randomnumber = random.nextInt(chance) + 1;
                            if (randomnumber == 1) {
                                world.setBlockState(blockPos, ModBlocks.VOID_FLUID.getDefaultState());
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    private static void drawLine(World world, BlockPos start, BlockPos end, Block block, int diameter) {
        int startX = start.getX();
        int startY = start.getY();
        int startZ = start.getZ();
        int endX = end.getX();
        int endY = end.getY();
        int endZ = end.getZ();

        int deltaX = endX - startX;
        int deltaY = endY - startY;
        int deltaZ = endZ - startZ;

        int absDeltaX = Math.abs(deltaX);
        int absDeltaY = Math.abs(deltaY);
        int absDeltaZ = Math.abs(deltaZ);


        int maxDelta = Math.max(Math.max(absDeltaX, absDeltaY), absDeltaZ);

        float normalizedStepX = (float) deltaX / maxDelta;
        float normalizedStepY = (float) deltaY / maxDelta;
        float normalizedStepZ = (float) deltaZ / maxDelta;

        float x = startX;
        float y = startY;
        float z = startZ;
        for (int i = 0; i <= maxDelta; i++) {
            for (int xi = -diameter / 2; xi <= diameter / 2; xi++) {
                for (int yi = -diameter / 2; yi <= diameter / 2; yi++) {
                    for (int zi = -diameter / 2; zi <= diameter / 2; zi++) {
                        if (world.getBlockState(new BlockPos((int) x + xi, (int) y + yi, (int) z + zi)).getBlock() != Blocks.AIR) {
                            world.setBlockState(new BlockPos((int) x + xi, (int) y + yi, (int) z + zi), block.getDefaultState());
                    }
                }
            }

            x += normalizedStepX;
            y += normalizedStepY;
            z += normalizedStepZ;
        }
    }
    }
}

