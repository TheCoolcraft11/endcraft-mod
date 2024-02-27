package net.thecoolcraft11.endcraft.datagen;


import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.util.Identifier;
import net.thecoolcraft11.endcraft.block.ModBlocks;
import net.thecoolcraft11.endcraft.item.ModItems;

import java.util.List;

public class ModRecipeProvider extends FabricRecipeProvider {
    private static final List<ItemConvertible> ENDERITE_SMELTABLES = List.of(ModBlocks.ENDERITE_ORE);


    public ModRecipeProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate(RecipeExporter exporter) {
        offerSmelting(exporter, ENDERITE_SMELTABLES, RecipeCategory.MISC, ModItems.ENDERITE_SCRAP,
                3f, 400, "enderite");
        offerBlasting(exporter, ENDERITE_SMELTABLES, RecipeCategory.MISC, ModItems.ENDERITE_SCRAP,
                3f, 200, "enderite");
        offerReversibleCompactingRecipes(exporter, RecipeCategory.BUILDING_BLOCKS, ModItems.ENDERITE_INGOT, RecipeCategory.DECORATIONS,
                ModBlocks.ENDERITE_BLOCK);
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.ENDERITE_INGOT, 1)
                .pattern("SSS")
                .pattern("SNN")
                .pattern("NN ")
                .input('S', ModItems.ENDERITE_SCRAP)
                .input('N', Items.NETHERITE_INGOT)
                .criterion(hasItem(ModItems.ENDERITE_SCRAP), conditionsFromItem(ModItems.ENDERITE_SCRAP))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.ENDERITE_SCRAP)));
    }
}