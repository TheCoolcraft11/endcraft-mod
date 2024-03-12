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
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.thecoolcraft11.endcraft.Endcraft;
import net.thecoolcraft11.endcraft.entity.custom.OculusOreEntity;
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
                context.getStack().setDamage(context.getStack().getDamage() + 1);
                if(context.getStack().getDamage() >= context.getStack().getMaxDamage()) {
                    context.getStack().decrement(1);
                }
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
                return (Text.translatable("item.endcraft.oculus_ore").copy().append(" [").append(Text.translatable(entity.toString().replace(":", "."))).append("]"));
            }
        }
        return super.getName(stack);
    }

    @Override
    public boolean isItemBarVisible(ItemStack stack) {
        return (stack.getOrCreateNbt().getBoolean("hasEntity"));
    }

    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);
        if (player.getMainHandStack().getOrCreateNbt().getBoolean("hasEntity")) {
            if(player.getMainHandStack().getDamage() < player.getMainHandStack().getMaxDamage()) {
        world.playSound(null, player.getX(), player.getY(), player.getZ(),
                SoundEvents.ENTITY_SNOWBALL_THROW, SoundCategory.NEUTRAL, 0.5f, 0.4f / (world.getRandom().nextFloat() * 0.4f + 0.8f));

        if (!world.isClient) {
            OculusOreEntity oculusOreEntity = new OculusOreEntity(player, world);
            oculusOreEntity.setItem(itemStack);
            oculusOreEntity.setVelocity(player, player.getPitch(), player.getYaw(), 0.0f, 1.5f, 1.0f);
            oculusOreEntity.setOwner(player);
            world.spawnEntity(oculusOreEntity);
        }

        player.incrementStat(Stats.USED.getOrCreateStat(this));
        if (!player.getAbilities().creativeMode) {
            itemStack.setDamage(itemStack.getDamage() + 1);
                }
            }
        }
        return TypedActionResult.success(itemStack, world.isClient());
    }
}
