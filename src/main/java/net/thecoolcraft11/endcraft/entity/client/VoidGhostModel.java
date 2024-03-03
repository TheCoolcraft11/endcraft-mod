package net.thecoolcraft11.endcraft.entity.client;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.thecoolcraft11.endcraft.entity.animation.ModAnimations;
import net.thecoolcraft11.endcraft.entity.custom.VoidGhostEntity;

// Made with Blockbench 4.9.4
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports
public class VoidGhostModel<T extends VoidGhostEntity> extends SinglePartEntityModel<T> {
	private final ModelPart void_ghost;
	private final ModelPart head;

	public VoidGhostModel(ModelPart root) {
		this.void_ghost = root.getChild("void_ghost");
		this.head = void_ghost.getChild("head");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData void_ghost = modelPartData.addChild("void_ghost", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

		ModelPartData head = void_ghost.addChild("head", ModelPartBuilder.create().uv(0, 0).cuboid(-2.5F, -5.0F, -2.5F, 5.0F, 5.0F, 5.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -6.0F, 0.0F));

		ModelPartData body = void_ghost.addChild("body", ModelPartBuilder.create().uv(0, 10).cuboid(-1.5F, 0.0F, -1.0F, 3.0F, 4.0F, 2.0F, new Dilation(0.0F))
		.uv(0, 16).cuboid(-1.5F, 1.0F, -1.0F, 3.0F, 5.0F, 2.0F, new Dilation(-0.2F)), ModelTransform.pivot(0.0F, -6.0F, 0.0F));

		ModelPartData right_arm = void_ghost.addChild("right_arm", ModelPartBuilder.create().uv(23, 0).cuboid(-1.5F, -0.5F, -1.0F, 2.0F, 4.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(-2.0F, -5.5F, 0.0F));

		ModelPartData left_arm = void_ghost.addChild("left_arm", ModelPartBuilder.create().uv(23, 6).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 4.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(2.5F, -4.0F, 0.0F));

		return TexturedModelData.of(modelData, 32, 32);
	}
	@Override
	public void setAngles(VoidGhostEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.getPart().traverse().forEach(ModelPart::resetTransform);
		this.setHeadAngels(netHeadYaw, headPitch);

		this.updateAnimation(entity.idleAnimationState, ModAnimations.IDLE, ageInTicks, 1f);
	}

	private void setHeadAngels(float headYaw, float headPitch) {
		headYaw = MathHelper.clamp(headYaw, -30.0F, 30.0F);
		headPitch = MathHelper.clamp(headPitch, -25.0F, 45.0F);

		this.head.yaw = headYaw * 0.017453292F;
		this.head.pitch = headPitch * 0.017453292F;
	}
	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		void_ghost.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart getPart() {
		return void_ghost;
	}
}