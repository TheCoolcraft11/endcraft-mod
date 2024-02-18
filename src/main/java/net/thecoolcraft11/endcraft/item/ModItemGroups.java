package net.thecoolcraft11.endcraft.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.thecoolcraft11.endcraft.Endcraft;
import net.thecoolcraft11.endcraft.block.ModBlocks;

public class ModItemGroups {
    public static final ItemGroup RUBY_GROUP = Registry.register(Registries.ITEM_GROUP,
            new Identifier(Endcraft.MOD_ID, "enderite"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.endcraft"))
                    .icon(() -> new ItemStack(ModBlocks.ENDERITE_ORE)).entries((displayContext, entries) -> {
                        entries.add(ModBlocks.ENDERITE_ORE);
                        entries.add(ModItems.ENDERITE_SCRAP);
                    }).build());
    public static void registerItemGroups() {
        Endcraft.LOGGER.info("Registring Item Groups for " + Endcraft.MOD_ID);
    }
}
