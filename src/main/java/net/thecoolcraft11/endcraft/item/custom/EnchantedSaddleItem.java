package net.thecoolcraft11.endcraft.item.custom;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;

public class EnchantedSaddleItem extends Item {
    public EnchantedSaddleItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity player, LivingEntity entity, Hand hand) {
        if (!player.getWorld().isClient) {
          if(player.isSneaking()) {
              player.getMainHandStack().getOrCreateNbt().putInt("entity", entity.getId());
              player.sendMessage(entity.getName().copy().formatted(Formatting.AQUA), true);
              player.getMainHandStack().getOrCreateNbt().putString("entity_name", entity.getType().getTranslationKey());
          }else {
              if(player.getWorld().getEntityById(player.getMainHandStack().getOrCreateNbt().getInt("entity")) != null) {
                  Entity entity1 = player.getWorld().getEntityById(stack.getOrCreateNbt().getInt("entity"));
                  if(entity != entity1) {
                      if(entity.getType() != EntityType.PLAYER) {
                      entity1.startRiding(entity, true);
                      player.getMainHandStack().getOrCreateNbt().putInt("entity", 0);
                    }
                  }
              }else {
                  player.startRiding(entity);
             }
          }
        }
        return super.useOnEntity(stack, player, entity, hand);
    }

    @Override
    public Text getName(ItemStack stack) {
            String entity = stack.getOrCreateNbt().getString("entity_name");
            if(entity != null) {
                return (Text.translatable("item.endcraft.enchanted_saddle").copy().append(" [").append(Text.translatable(entity)).append("]"));
            }
        return super.getName(stack);
    }
}
