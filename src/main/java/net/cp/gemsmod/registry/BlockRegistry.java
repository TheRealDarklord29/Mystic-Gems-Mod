package net.cp.gemsmod.registry;

import net.cp.gemsmod.MysticGemsMod;
import net.cp.gemsmod.block.GemInfuserBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public final class BlockRegistry {
    public static final Block GEM_INFUSER = registerBlock(
            "gem_infuser",
            new GemInfuserBlock(AbstractBlock.Settings.copy(Blocks.CRAFTING_TABLE))
    );

    private BlockRegistry() {
    }

    public static void register() {
        MysticGemsMod.LOGGER.info("Registering Blocks for " + MysticGemsMod.MOD_ID);
    }

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(MysticGemsMod.MOD_ID, name), block);
    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(Registries.ITEM, Identifier.of(MysticGemsMod.MOD_ID, name), new BlockItem(block, new Item.Settings()));
    }
}
