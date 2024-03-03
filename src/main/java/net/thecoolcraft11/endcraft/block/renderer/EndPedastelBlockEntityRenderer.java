package net.thecoolcraft11.endcraft.block.renderer;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.LightmapTextureManager;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.LightType;
import net.minecraft.world.World;
import net.thecoolcraft11.endcraft.block.entity.EndPedastelBlockEntity;
import net.thecoolcraft11.endcraft.block.entity.EssenceAltarBlockEntity;

public class EndPedastelBlockEntityRenderer implements BlockEntityRenderer<EndPedastelBlockEntity> {
    public EndPedastelBlockEntityRenderer(BlockEntityRendererFactory.Context context) {

    }

    @Override
    public void render(EndPedastelBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {

        ItemRenderer itemRenderer = MinecraftClient.getInstance().getItemRenderer();
        ItemStack itemStack = entity.getRenderStack();
        matrices.push();
        matrices.translate(0.5f, 0.6f, 0.5f);
        matrices.scale(1,1,1);
        itemRenderer.renderItem(itemStack, ModelTransformationMode.GROUND,getLightLevel(entity.getWorld(), entity.getPos()), OverlayTexture.DEFAULT_UV, matrices, vertexConsumers, entity.getWorld(),1);
        matrices.pop();


    }
    private int getLightLevel(World world, BlockPos pos) {
        int bLight = world.getLightLevel(LightType.BLOCK, pos);
        int sLight = world.getLightLevel(LightType.SKY, pos);
        return LightmapTextureManager.pack(bLight, sLight);
    }

}
