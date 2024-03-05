package net.thecoolcraft11.endcraft.item.custom;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.VexEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.StackReference;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShieldItem;
import net.minecraft.screen.slot.Slot;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.ClickType;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.thecoolcraft11.endcraft.statuseffects.ModStatusEffects;

public class ShadowVeilItem extends ShieldItem {
    public ShadowVeilItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        StatusEffectInstance effect = new StatusEffectInstance(ModStatusEffects.SHADOW_VEIL, 300, 0, false, false);
        player.addStatusEffect(effect);
        player.getItemCooldownManager().set(player.getMainHandStack().getItem(), 500);
        player.getMainHandStack().damage(1, player,
                playerEntity -> playerEntity.sendToolBreakStatus(playerEntity.getActiveHand()));
        return TypedActionResult.success(player.getMainHandStack(), true);
    }
}
