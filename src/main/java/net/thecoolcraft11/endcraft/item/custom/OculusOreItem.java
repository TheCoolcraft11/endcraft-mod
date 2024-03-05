package net.thecoolcraft11.endcraft.item.custom;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.registry.Registry;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.thecoolcraft11.endcraft.Endcraft;
import net.thecoolcraft11.endcraft.item.ModItems;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class OculusOreItem extends Item {
    public OculusOreItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity player, LivingEntity entity, Hand hand) {
        if (!player.getMainHandStack().getOrCreateNbt().getBoolean("hasEntity")) {
            player.getMainHandStack().getOrCreateNbt().putString("entity", entity.getType().toString().replace("entity.", "").replace(".", ":"));
            player.getMainHandStack().getOrCreateNbt().putBoolean("hasEntity", true);
            return ActionResult.SUCCESS;
        }
        return ActionResult.FAIL;
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        if (context.getStack().getOrCreateNbt().getBoolean("hasEntity")) {
            EntityType<?> entity = EntityType.get(context.getStack().getOrCreateNbt().getString("entity")).orElse(null);
            if(!(entity == null)) {
                Entity entity1 = entity.create(context.getWorld());
                assert entity1 != null;
                entity1.updatePosition(context.getBlockPos().getX(), context.getBlockPos().getY()+ 1, context.getBlockPos().getZ());
                context.getWorld().spawnEntity(entity1);
                context.getPlayer().getMainHandStack().damage(1, context.getPlayer(),
                        playerEntity -> playerEntity.sendToolBreakStatus(playerEntity.getActiveHand()));
                return ActionResult.SUCCESS;
            }
        }
        return ActionResult.FAIL;
    }

    @Override
    public int getItemBarColor(ItemStack stack) {
        if(stack.getOrCreateNbt().getBoolean("hasEntity")) {
            EntityType<?> entity = EntityType.get(stack.getOrCreateNbt().getString("entity")).orElse(null);
            if(!(SpawnEggItem.forEntity(entity) == null)) {
                return SpawnEggItem.forEntity(entity).getColor(0);
            }

        }
        return super.getItemBarColor(stack);
    }

    @Override
    public Text getName(ItemStack stack) {
        if(stack.getOrCreateNbt().getBoolean("hasEntity")) {
            EntityType<?> entity = EntityType.get(stack.getOrCreateNbt().getString("entity")).orElse(null);
            if(entity != null) {
                return (Text.translatable("items.endcraft.oculus_ore").copy().append(" [").append(Text.translatable(entity.toString().replace(":", "."))).append("]"));
            }
        }
        return super.getName(stack);
    }

    @Override
    public boolean isItemBarVisible(ItemStack stack) {
        return (stack.getOrCreateNbt().getBoolean("hasEntity"));
    }
}
