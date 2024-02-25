package net.thecoolcraft11.endcraft.recipe;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.thecoolcraft11.endcraft.Endcraft;

public class ModRecipes {
    public static void registerRecipes() {
        Registry.register(Registries.RECIPE_SERIALIZER, new Identifier(Endcraft.MOD_ID, EnderForgeConverterRecipe.Serializer.ID),
                EnderForgeConverterRecipe.Serializer.INSTANCE);
        Registry.register(Registries.RECIPE_TYPE, new Identifier(Endcraft.MOD_ID, EnderForgeConverterRecipe.Type.ID),
                EnderForgeConverterRecipe.Type.INSTANCE);
    }
}
