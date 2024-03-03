package net.thecoolcraft11.endcraft.entity.custom;


import net.minecraft.block.Blocks;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.ai.control.FlightMoveControl;
import net.minecraft.entity.ai.control.MoveControl;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.mob.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.thecoolcraft11.endcraft.statuseffects.ModStatusEffects;
import org.jetbrains.annotations.Nullable;

import java.util.EnumSet;

public class VoidGhostEntity extends HostileEntity {

    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;




    public VoidGhostEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
        this.moveControl = new FlightMoveControl(this, 20, true);
    }
    private void setupAnimationStates() {
        if (this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = this.random.nextInt(40) + 80;
            this.idleAnimationState.start(this.age);
        } else {
            --this.idleAnimationTimeout;
        }
    }
    @Override
    protected void updateLimbs(float posDelta) {
        float f = this.getPose() == EntityPose.STANDING ? Math.min(posDelta * 6.0f, 1.0f) : 0.0f;
        this.limbAnimator.updateLimbs(f, 0.2f);
    }
    @Override
    public void tick() {
        if(this.getWorld().isClient) {
setupAnimationStates();
        }
        super.tick();
    }



    @Override
    protected void initGoals() {
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(3, new GoToWalkTargetGoal(this, 3.0));
        this.goalSelector.add(2, new MeleeAttackGoal(this, 3.0, false));
        this.goalSelector.add(4, new WanderAroundFarGoal(this, 3.25D));
        this.goalSelector.add(5, new LookAtEntityGoal(this, PlayerEntity.class, 32f));
        this.goalSelector.add(6, new LookAroundGoal(this));
        this.targetSelector.add(1, new ActiveTargetGoal<PlayerEntity>(this, PlayerEntity.class, true));
        super.initGoals();
    }


    public static DefaultAttributeContainer.Builder createVoidGhostAttributes() {
        return HostileEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 10)
                .add(EntityAttributes.GENERIC_FLYING_SPEED, 0.2)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.2)
                .add(EntityAttributes.GENERIC_ARMOR, 2)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 2);
        }
    @Override
    public void move(MovementType movementType, Vec3d movement) {
        super.move(movementType, movement);
        this.checkBlockCollision();
    }




    static class TargetGoal<T extends LivingEntity>
            extends ActiveTargetGoal<T> {
        public TargetGoal(VoidGhostEntity voidGhost, Class<T> targetEntityClass) {
            super((MobEntity)voidGhost, targetEntityClass, true);
        }

        @Override
        public boolean canStart() {
            return super.canStart();
        }
    }


    @Override
    public void onAttacking(Entity target) {
        PlayerEntity player = (PlayerEntity) target;
        StatusEffectInstance effect = new StatusEffectInstance(ModStatusEffects.VOID, 40, 1);
        player.addStatusEffect(effect);
        player.getAttacker().remove(RemovalReason.DISCARDED);
        super.onAttacking(target);
    }
}
