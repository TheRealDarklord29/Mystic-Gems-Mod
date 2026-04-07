package net.cp.gemsmod.block;

import net.cp.gemsmod.MysticGemsMod;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public final class ModBlocks {
    public static final Block GEM_INFUSER = registerBlock(
            "gem_infuser",
            new GemInfuserBlock(AbstractBlock.Settings.copy(Blocks.CRAFTING_TABLE))
    );

    private ModBlocks() {
    }

    public static void registerModBlocks() {
        MysticGemsMod.LOGGER.info("Registering Mod Blocks for " + MysticGemsMod.MOD_ID);
    }

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(MysticGemsMod.MOD_ID, name), block);
    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(Registries.ITEM, Identifier.of(MysticGemsMod.MOD_ID, name), new BlockItem(block, new Item.Settings()));
    }
}
