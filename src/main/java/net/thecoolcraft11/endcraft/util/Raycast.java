package net.thecoolcraft11.endcraft.util;

import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;

public class Raycast {
    public static BlockHitResult raycast(double reachDistance) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.world == null || client.player == null)
            return null;

        Vec3d cameraPos = client.player.getCameraPosVec(1.0f);
        Vec3d rotationVec = client.player.getRotationVec(1.0f);
        Vec3d endVec = cameraPos.add(rotationVec.x * reachDistance, rotationVec.y * reachDistance, rotationVec.z * reachDistance);

        RaycastContext.FluidHandling fluidHandling = RaycastContext.FluidHandling.NONE;

        RaycastContext raycastContext = new RaycastContext(
                cameraPos,
                endVec,
                RaycastContext.ShapeType.OUTLINE,
                fluidHandling,
                client.player
        );

        return client.world.raycast(raycastContext);
    }
}

