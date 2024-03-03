package net.thecoolcraft11.endcraft.entity.client;

import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;
import net.thecoolcraft11.endcraft.Endcraft;

public class ModModelLayers {
    public static final EntityModelLayer VOIDGHOST =
            new EntityModelLayer(new Identifier(Endcraft.MOD_ID, "void_ghost"), "main");
}
