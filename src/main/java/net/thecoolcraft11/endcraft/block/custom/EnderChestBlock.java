package net.thecoolcraft11.endcraft.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.thecoolcraft11.endcraft.Endcraft;
import net.thecoolcraft11.endcraft.block.entity.EnderChestBlockEntity;
import net.thecoolcraft11.endcraft.item.ModItems;
import org.jetbrains.annotations.Nullable;

public class EnderChestBlock extends BlockWithEntity implements BlockEntityProvider {
    private static final VoxelShape SHAPE = Block.createCuboidShape(0, 0, 0, 16, 15, 16);


    public static final MapCodec<EnderChestBlock> CODEC = EnderChestBlock.createCodec(EnderChestBlock::new);

    public EnderChestBlock(Settings settings) {
        super(settings);
    }
    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return CODEC;
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new EnderChestBlockEntity(pos, state);
    }

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof EnderChestBlockEntity) {
                ItemScatterer.spawn(world, pos, (EnderChestBlockEntity)blockEntity);
                world.updateComparators(pos,this);
            }
            super.onStateReplaced(state, world, pos, newState, moved);
        }
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof EnderChestBlockEntity) {
                Endcraft.LOGGER.info("1");
                EnderChestBlockEntity customBlockEntity = (EnderChestBlockEntity) blockEntity;
                Endcraft.LOGGER.info("B = " + customBlockEntity.getPlayer());
                Endcraft.LOGGER.info("P = " + player.getUuid());
            if(customBlockEntity.getPlayer().equals(player.getUuid())) {
            if (player.getMainHandStack().getItem() != ModItems.ENDERITE_CHEST_KEY && !player.getMainHandStack().getOrCreateNbt().getBoolean("aligned")) {
                Endcraft.LOGGER.info("3");
                NamedScreenHandlerFactory screenHandlerFactory = ((EnderChestBlockEntity) world.getBlockEntity(pos));

                if (screenHandlerFactory != null) {
                    Endcraft.LOGGER.info("4");
                    player.openHandledScreen(screenHandlerFactory);
                        }
                    }
                }
            }
        }
        return ActionResult.SUCCESS;
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        if (!world.isClient) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof EnderChestBlockEntity) {
                EnderChestBlockEntity customBlockEntity = (EnderChestBlockEntity) blockEntity;
                customBlockEntity.setPlacer(placer.getUuid());
            }
        }
        super.onPlaced(world, pos, state, placer, itemStack);
    }
}