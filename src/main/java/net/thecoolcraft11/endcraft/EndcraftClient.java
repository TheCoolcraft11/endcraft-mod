package net.thecoolcraft11.endcraft;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.mixin.client.rendering.EntityRenderersMixin;
import net.fabricmc.fabric.mixin.object.builder.client.EntityModelLayersMixin;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.InputUtil;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.thecoolcraft11.endcraft.block.ModBlocks;
import net.thecoolcraft11.endcraft.block.entity.EndPedastelBlockEntity;
import net.thecoolcraft11.endcraft.block.entity.EssenceAltarBlockEntity;
import net.thecoolcraft11.endcraft.block.entity.ModBlockEntities;
import net.thecoolcraft11.endcraft.block.renderer.EndPedastelBlockEntityRenderer;
import net.thecoolcraft11.endcraft.block.renderer.EnderForgeConverterBlockEntityRenderer;
import net.thecoolcraft11.endcraft.block.renderer.EssenceAltarBlockEntityRenderer;
import net.thecoolcraft11.endcraft.entity.ModEntities;
import net.thecoolcraft11.endcraft.entity.client.ModModelLayers;
import net.thecoolcraft11.endcraft.entity.client.VoidGhostModel;
import net.thecoolcraft11.endcraft.entity.client.VoidGhostRenderer;
import net.thecoolcraft11.endcraft.screen.*;
import org.lwjgl.glfw.GLFW;

public class EndcraftClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.ENDER_FORGE_CONVERTER, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.ESSENCE_ALTAR, RenderLayer.getCutout());
        HandledScreens.register(ModScreenHandlers.ENDER_FORGE_CONVERTING_SCREEN_HANDLER, EnderForgeConvertingScreen::new);
        BlockEntityRendererFactories.register(ModBlockEntities.ENDER_FORGE_CONVERTER_BLOCK_ENTITY, EnderForgeConverterBlockEntityRenderer::new);
        HandledScreens.register(ModScreenHandlers.ESSENCE_ALTAR_SCREEN_HANDLER, EssenceAltarScreen::new);
        BlockEntityRendererFactories.register(ModBlockEntities.ESSENCE_ALTAR_BLOCK_ENTITY, EssenceAltarBlockEntityRenderer::new);
        HandledScreens.register(ModScreenHandlers.ENDER_CHARGER_SCREEN_HANDLER, EnderChargerScreen::new);
        HandledScreens.register(ModScreenHandlers.MOD_TABLE_SCREEN_HANDLER, ModTableScreen::new);
        BlockEntityRendererFactories.register(ModBlockEntities.END_PEDASTEL_BLOCK_ENTITY, EndPedastelBlockEntityRenderer::new);

        EntityRendererRegistry.register(ModEntities.VOID_GHOST, VoidGhostRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.VOIDGHOST, VoidGhostModel::getTexturedModelData);
    }

}
