package net.thecoolcraft11.endcraft.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.particle.ParticleGroup;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleType;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.thecoolcraft11.endcraft.Endcraft;
import net.thecoolcraft11.endcraft.block.ModBlocks;
import net.thecoolcraft11.endcraft.item.ModItems;
import org.jetbrains.annotations.Nullable;

public class VoidbornPortalActivatorBlock extends Block {
    private static final VoxelShape SHAPE_UPPER = Block.createCuboidShape(0, -16, 0, 16, 16, 16);
    private static final VoxelShape SHAPE_LOWER = Block.createCuboidShape(0, 0, 0, 16, 32, 16);
    public static final IntProperty HALF = IntProperty.of("half",0,1);
    public static final BooleanProperty ON = BooleanProperty.of("on");
    public VoidbornPortalActivatorBlock(Settings settings) {
        super(settings);
        setDefaultState(getDefaultState().with(HALF, 0));
        setDefaultState(getDefaultState().with(ON, false));

    }
    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(HALF);
        builder.add(ON);
    }
    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        if(state.get(HALF) == 0) {
            return SHAPE_LOWER;
        }
        return SHAPE_UPPER;
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        world.setBlockState(pos.add(0,1,0), ModBlocks.VOIDBORN_PORTAL_ACTIVATOR_BLOCK.getDefaultState().with(HALF, 1));
        super.onPlaced(world, pos, state, placer, itemStack);
    }

    @Override
    public BlockState onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        if(state.get(HALF) == 0) {
            world.setBlockState(pos.add(0,1,0), Blocks.AIR.getDefaultState());
        }else if (state.get(HALF) == 1) {
            world.setBlockState(pos.add(0,-1,0), Blocks.AIR.getDefaultState());
        }
        return super.onBreak(world, pos, state, player);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if(!state.get(ON)) {
        if(hasItem(world, pos.add(5, -1, 2), ModItems.ENDERITE_INGOT, 0)) {
            if(hasItem(world, pos.add(5, -1, -2), ModItems.ENDERITE_INGOT, 0)) {
                if(hasItem(world, pos.add(-5, -1, 2), ModItems.ENDERITE_INGOT, 0)) {
                    if(hasItem(world, pos.add(-5, -1, -2), ModItems.ENDERITE_INGOT, 0)) {
                        if(hasItem(world, pos.add(2, -1, 5), ModItems.ENDERITE_INGOT, 0)) {
                            if(hasItem(world, pos.add(-2, -1, 5), ModItems.ENDERITE_INGOT, 0)) {
                                if(hasItem(world, pos.add(2, -1, -5), ModItems.ENDERITE_INGOT, 0)) {
                                    if(hasItem(world, pos.add(-2, -1, -5), ModItems.ENDERITE_INGOT, 0)) {
                                        activatePortal(world, pos,-1, 2);
                                        world.addParticle(ParticleTypes.EXPLOSION, pos.getX(), pos.getY(), pos.getZ(),0,0,0);
                                        world.setBlockState(pos, ModBlocks.VOIDBORN_PORTAL_ACTIVATOR_BLOCK.getDefaultState().with(HALF,1).with(ON, true));
                                        world.setBlockState(pos.down(1), ModBlocks.VOIDBORN_PORTAL_ACTIVATOR_BLOCK.getDefaultState().with(HALF,0).with(ON, true));
                                        player.addVelocity(0.5, 2, 0.5);
                                        return ActionResult.SUCCESS;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

        }
        return ActionResult.FAIL;
    }

    private boolean hasItem(World world, BlockPos pos, Item stack, int slot) {
        if(world.getBlockEntity(pos) != null) {
            Inventory blockEntity = (Inventory) world.getBlockEntity(pos);
            return (blockEntity.getStack(slot).getItem() == stack);

        }
        return false;
    }
    private static void activatePortal(World world, BlockPos centerPos, int y, int radius) {
        int halfRadius = radius / 2;

        for (int x = -halfRadius; x <= halfRadius; x++) {
            for (int z = -halfRadius; z <= halfRadius; z++) {
                BlockPos blockPos = centerPos.add(x, y, z);
                    world.setBlockState(blockPos, ModBlocks.VOIDBORN_ABYSS_PORTAL.getDefaultState().with(VoidbornAbyssPortalBlock.ACTIVATED,true));
            }
        }
    }
}
