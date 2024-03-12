package net.thecoolcraft11.endcraft.entity.custom;

import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleFactory;
import net.minecraft.client.particle.ParticleGroup;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleType;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.world.World;
import net.thecoolcraft11.endcraft.entity.ModEntities;

public class OculusOreEntity extends ThrownItemEntity {
    public OculusOreEntity(EntityType<? extends ThrownItemEntity> entityType, World world) {
        super(entityType, world);
    }
    public OculusOreEntity(LivingEntity livingEntity, World world) {
        super(ModEntities.OCULUS_PROJECTILE, livingEntity, world);
    }

    @Override
    protected Item getDefaultItem() {
        return null;
    }

    @Override
    protected void onBlockHit(BlockHitResult blockHitResult) {
        if(!this.getWorld().isClient()) {
            PlayerEntity player = (PlayerEntity) this.getOwner();
            if (player.getMainHandStack().getOrCreateNbt().getBoolean("hasEntity")) {
                this.getWorld().sendEntityStatus(this, (byte) 3);
                EntityType<?> entity = EntityType.get(player.getMainHandStack().getOrCreateNbt().getString("entity")).orElse(null);
                if(!(entity == null)) {
                    Entity entity1 = entity.create(this.getWorld());

                    if (entity1 != null) {
                        entity1.updatePosition(this.getBlockPos().getX(), this.getBlockPos().getY()+ 1, this.getBlockPos().getZ());
                        this.getWorld().spawnEntity(entity1);
                        this.getStack().setDamage(this.getStack().getDamage() - 1);
                        this.discard();
                    }else {
                        this.discard();
                    }
                    if(player.getMainHandStack().getDamage() >= player.getMainHandStack().getMaxDamage()) {
                        player.getMainHandStack().decrement(1);
                    }
                }
            }else {
                this.getWorld().addParticle(ParticleTypes.EXPLOSION, this.getBlockX(), this.getBlockY(), this.getBlockZ(), 0, 0,0);
                this.discard();
            }
        }
        super.onBlockHit(blockHitResult);
    }
}
