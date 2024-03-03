package net.thecoolcraft11.endcraft.block.custom;

import com.ibm.icu.impl.number.AffixPatternProvider;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.network.packet.s2c.play.PositionFlag;
import net.minecraft.registry.RegistryKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.TeleportTarget;
import net.minecraft.world.World;
import net.thecoolcraft11.endcraft.block.ModBlocks;
import net.thecoolcraft11.endcraft.world.dimension.ModDimensions;

import java.util.Optional;
import java.util.Set;

public class VoidbornAbyssPortalBlock extends Block {
    protected static final VoxelShape SHAPE = Block.createCuboidShape(0.0, 6.0, 0.0, 16.0, 12.0, 16.0);
    public static final BooleanProperty ACTIVATED = BooleanProperty.of("activated");
    public VoidbornAbyssPortalBlock(Settings settings) {
        super(settings);
        setDefaultState(getDefaultState().with(ACTIVATED, false));
    }
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(ACTIVATED);
    }

    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        if (world.getBlockState(pos).get(ACTIVATED)) {
            if (world instanceof ServerWorld && entity.canUsePortals() && VoxelShapes.matchesAnywhere(VoxelShapes.cuboid(entity.getBoundingBox().offset(-pos.getX(), -pos.getY(), -pos.getZ())), state.getOutlineShape(world, pos), BooleanBiFunction.AND)) {
                RegistryKey<World> registryKey = world.getRegistryKey() == ModDimensions.VOIDBORN_ABYSS_LEVEL_KEY ? World.OVERWORLD : ModDimensions.VOIDBORN_ABYSS_LEVEL_KEY;
                ServerWorld serverWorld = ((ServerWorld)world).getServer().getWorld(registryKey);
                if (serverWorld == null) {
                    return;
                }
                if (registryKey == ModDimensions.VOIDBORN_ABYSS_LEVEL_KEY) {
                    entity.teleport(serverWorld, pos.getX(),475,pos.getZ(),Set.of(PositionFlag.X, PositionFlag.Y, PositionFlag.X_ROT, PositionFlag.Y_ROT),0,0);
                    placeBlocksAround(serverWorld, pos,415, Blocks.OBSIDIAN, 5);
                    placeBlocksAround(serverWorld, pos,416, Blocks.AIR, 5);
                    placeBlocksAround(serverWorld, pos,417, Blocks.AIR, 5);
                    placeBlocksAround(serverWorld, pos,418, Blocks.AIR, 5);
                    placeBlocksAround(serverWorld, pos,419, Blocks.AIR, 5);
                    placeBlocksAround(serverWorld, pos,420, Blocks.AIR, 5);
                }else {
                    PlayerEntity player = (PlayerEntity) entity;
                    BlockPos blockPos = player.getSleepingPosition().orElse(null);

                    if(blockPos != null) {
                        player.teleport(serverWorld, blockPos.getX(),blockPos.getY() ,blockPos.getZ(),Set.of(PositionFlag.X, PositionFlag.Y, PositionFlag.X_ROT, PositionFlag.Y_ROT),0,0);
                    }else {
                        player.teleport(serverWorld, serverWorld.getServer().getWorld(registryKey).getSpawnPos().getX(),serverWorld.getServer().getWorld(registryKey).getSpawnPos().getY() ,serverWorld.getServer().getWorld(registryKey).getSpawnPos().getZ(),Set.of(PositionFlag.X, PositionFlag.Y, PositionFlag.X_ROT, PositionFlag.Y_ROT),0,0);
                    }
                }
            }
        }else {
            entity.addVelocity(0.2,1,0.2);
        }
    }
    private static void placeBlocksAround(World world, BlockPos centerPos, int y, Block block, int radius) {
        int halfRadius = radius / 2;

        for (int x = -halfRadius; x <= halfRadius; x++) {
                for (int z = -halfRadius; z <= halfRadius; z++) {
                    BlockPos blockPos = centerPos.add(x, 415, z);
                        world.setBlockState(blockPos, block.getDefaultState(), Block.NOTIFY_ALL);
            }
        }
    }
}