package net.thecoolcraft11.endcraft.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.server.MinecraftServer;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.thecoolcraft11.endcraft.Endcraft;

import java.util.UUID;

public class EnderChestScreen extends HandledScreen<EnderChestScreenHandler> {
    private static final Identifier TEXTURE = new Identifier(Endcraft.MOD_ID, "textures/gui/enderite_chest_gui.png");

    public EnderChestScreen(EnderChestScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Override
    protected void init() {
        super.init();
        titleY = -1 / 20;
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        RenderSystem.setShaderColor(1f, 1f, 1, 1f);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;

        context.drawTexture(TEXTURE, x, y, 0, 0, backgroundWidth, backgroundHeight);
        if (handler.blockEntity.getGuests() != null) {
            UUID placer = handler.blockEntity.getPlacer();
            Endcraft.LOGGER.info(handler.blockEntity.getGuests().toString());
            // for(int i = 1; i < handler.blockEntity.getGuests().size() + 5; i++) {
            //   context.drawCenteredTextWithShadow(textRenderer, client.getServer().getPlayerManager().getPlayer(handler.blockEntity.getGuests().get(i)).getName(), x, y, 0xFFFFFF);
            // Endcraft.LOGGER.info(String.valueOf(client.getServer().getPlayerManager().getPlayer(handler.blockEntity.getGuests().get(i)).getName()));

            //}
        }

        renderProgressArrow(context, x, y);
    }

    private void renderProgressArrow(DrawContext context, int x, int y) {
        if(handler.isCrafting()) {
            context.drawTexture(TEXTURE, x + 85, y + 30, 176, 0, 8, handler.getScaledProgress());
        }
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        renderBackground(context, mouseX, mouseY, delta);

        super.render(context, mouseX, mouseY, delta);
        drawMouseoverTooltip(context, mouseX, mouseY);
    }
}