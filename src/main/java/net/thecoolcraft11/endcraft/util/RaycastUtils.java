package net.thecoolcraft11.endcraft.util;

import net.minecraft.entity.Entity;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;

public class RaycastUtils {

    public static BlockPos getBlockLookingAt(World world, Vec3d start, Vec3d end, Entity entity) {
        BlockHitResult blockHitResult = world.raycast(new RaycastContext(start, end, RaycastContext.ShapeType.OUTLINE, RaycastContext.FluidHandling.NONE, (Entity) entity));
        return blockHitResult.getBlockPos();
    }

    public static Vec3d getEndVec(Vec3d start, Vec3d direction, double distance) {
        return start.add(direction.normalize().multiply(distance));
    }

    public static Vec3d getPlayerLookingAtPosition(Vec3d playerPos, Vec3d playerLook, double reachDistance) {
        return playerPos.add(playerLook.normalize().multiply(reachDistance));
    }

    public static double getBlockHitX(BlockPos blockPos, Vec3d start, Vec3d end) {
        double distanceX = end.x - start.x;
        double deltaX = blockPos.getX() - start.x;
        double ratio = deltaX / distanceX;
        return start.x + (end.x - start.x) * ratio;
    }

    public static double getBlockHitY(BlockPos blockPos, Vec3d start, Vec3d end) {
        double distanceY = end.y - start.y;
        double deltaY = blockPos.getY() - start.y;
        double ratio = deltaY / distanceY;
        return start.y + (end.y - start.y) * ratio;
    }

    public static double getBlockHitZ(BlockPos blockPos, Vec3d start, Vec3d end) {
        double distanceZ = end.z - start.z;
        double deltaZ = blockPos.getZ() - start.z;
        double ratio = deltaZ / distanceZ;
        return start.z + (end.z - start.z) * ratio;
    }
}

