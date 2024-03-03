package net.thecoolcraft11.endcraft.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.SnowBlock;
import net.minecraft.entity.Entity;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.thecoolcraft11.endcraft.damagetypes.ModDamageTypes;

public class VoidLayerBlock extends SnowBlock {
    public static final int MAX_LAYERS = 8;
    public static final IntProperty LAYERS = Properties.LAYERS;
    private static final VoxelShape SHAPE = Block.createCuboidShape(0, 0, 0, 0, 0, 0);
    public VoidLayerBlock(Settings settings) {
        super(settings.noCollision());
        this.setDefaultState((BlockState) this.stateManager.getDefaultState().with(LAYERS, 1));
    }
    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }
    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }
    @Override
    public VoxelShape getSidesShape(BlockState state, BlockView world, BlockPos pos) {
        return SHAPE;
    }

    @Override
    public VoxelShape getCameraCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        entity.damage(ModDamageTypes.of(entity.getWorld(), ModDamageTypes.SWAM_VOID), 4f);
        super.onEntityCollision(state, world, pos, entity);
    }
}
