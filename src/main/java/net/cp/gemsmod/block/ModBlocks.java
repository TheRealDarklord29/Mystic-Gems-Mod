package net.cp.gemsmod.block;

import net.minecraft.block.Block;
import net.cp.gemsmod.registry.BlockRegistry;

@Deprecated(forRemoval = false)
public final class ModBlocks {
    public static final Block GEM_INFUSER = BlockRegistry.GEM_INFUSER;

    private ModBlocks() {
    }

    public static void registerModBlocks() {
        BlockRegistry.register();
    }
}
