package net.thecoolcraft11.endcraft.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.thecoolcraft11.endcraft.block.ModBlocks;

public class FakeBlock extends Block {
    public FakeBlock(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        changeBlocksAroundPoint(world, pos, 9);
        return ActionResult.SUCCESS;
    }



    private static void changeBlocksAroundPoint(World world, BlockPos centerPos, int radius) {
        int halfRadius = radius / 2;

        for (int x = -halfRadius; x <= halfRadius; x++) {
            for (int y = -halfRadius; y <= halfRadius; y++) {
                for (int z = -halfRadius; z <= halfRadius; z++) {
                    BlockPos blockPos = centerPos.add(x, y - halfRadius + 1, z);

                    Block block = world.getBlockState(blockPos).getBlock();

                    if (block == ModBlocks.FAKE_BLOCK) {
                        world.setBlockState(blockPos, ModBlocks.FAKE_BLOCK2.getDefaultState());
                        }
                    if (block == ModBlocks.FAKE_BLOCK2) {
                        world.setBlockState(blockPos, ModBlocks.FAKE_BLOCK.getDefaultState());
                    }
                }
            }
        }
    }
}
