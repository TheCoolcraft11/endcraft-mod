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
import net.minecraft.util.math.RotationAxis;
import net.minecraft.world.LightType;
import net.minecraft.world.World;
import net.thecoolcraft11.endcraft.block.entity.EnderForgeConverterBlockEntity;
import net.thecoolcraft11.endcraft.block.entity.EssenceAltarBlockEntity;

import java.util.Random;

public class EssenceAltarBlockEntityRenderer implements BlockEntityRenderer<EssenceAltarBlockEntity> {
    public EssenceAltarBlockEntityRenderer(BlockEntityRendererFactory.Context context) {

    }

    @Override
    public void render(EssenceAltarBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {

        ItemRenderer itemRenderer = MinecraftClient.getInstance().getItemRenderer();
        ItemStack itemStack = entity.getRenderStack();
        matrices.push();
        matrices.translate(0.5f, entity.getScale() * 1.5,0.5f);
        matrices.scale(entity.getScale() * 2.75f, entity.getScale() * 2.75f,entity.getScale() * 2.75f);
        itemRenderer.renderItem(itemStack, ModelTransformationMode.HEAD,getLightLevel(entity.getWorld(), entity.getPos()), OverlayTexture.DEFAULT_UV, matrices, vertexConsumers, entity.getWorld(),1);
        matrices.pop();


    }
    private int getLightLevel(World world, BlockPos pos) {
        int bLight = world.getLightLevel(LightType.BLOCK, pos);
        int sLight = world.getLightLevel(LightType.SKY, pos);
        return LightmapTextureManager.pack(bLight, sLight);
    }

}
