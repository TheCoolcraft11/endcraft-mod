package net.thecoolcraft11.endcraft.entity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.thecoolcraft11.endcraft.Endcraft;
import net.thecoolcraft11.endcraft.entity.custom.OculusOreEntity;
import net.thecoolcraft11.endcraft.entity.custom.VoidGhostEntity;

public class ModEntities {
    public static final EntityType<VoidGhostEntity> VOID_GHOST = Registry.register(Registries.ENTITY_TYPE, new Identifier(Endcraft.MOD_ID, "void_ghost"), FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, VoidGhostEntity::new).dimensions(EntityDimensions.fixed(0.5f,0.5f)).build());
    public static  final EntityType<OculusOreEntity> OCULUS_PROJECTILE = Registry.register(Registries.ENTITY_TYPE, new Identifier(Endcraft.MOD_ID, "oculus_projectile"), FabricEntityTypeBuilder.<OculusOreEntity>create(SpawnGroup.MISC, OculusOreEntity::new).dimensions(EntityDimensions.fixed(0.25f, 0.25f)).build());
}
