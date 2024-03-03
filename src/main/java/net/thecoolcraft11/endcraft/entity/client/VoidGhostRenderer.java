package net.thecoolcraft11.endcraft.entity.client;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.thecoolcraft11.endcraft.Endcraft;
import net.thecoolcraft11.endcraft.entity.custom.VoidGhostEntity;

public class VoidGhostRenderer extends MobEntityRenderer<VoidGhostEntity, VoidGhostModel<VoidGhostEntity>> {
    private static final Identifier TEXTURE = new Identifier(Endcraft.MOD_ID, "textures/entity/void_ghost.png");
    public VoidGhostRenderer(EntityRendererFactory.Context context) {
        super(context, new VoidGhostModel<>(context.getPart(ModModelLayers.VOIDGHOST)), 0.4f);
    }

    @Override
    public Identifier getTexture(VoidGhostEntity entity) {
        return TEXTURE;
    }

    @Override
    public void render(VoidGhostEntity mobEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        super.render(mobEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }
}
