package net.thecoolcraft11.endcraft.util;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.thecoolcraft11.endcraft.Endcraft;

public class ModTags {
    public static class Blocks {

        private static TagKey<Block> createTag(String name) {
            return TagKey.of(RegistryKeys.BLOCK, new Identifier(Endcraft.MOD_ID, name));
        }
    }

    public static class Items {
        public static final TagKey<Item> ENDER_UPGRADE = createTag("ender_upgrade");
        private static TagKey<Item> createTag(String name) {
            return TagKey.of(RegistryKeys.ITEM, new Identifier(Endcraft.MOD_ID, name));
        }
    }
}