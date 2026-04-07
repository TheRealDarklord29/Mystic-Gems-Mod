package net.cp.gemsmod.item;

import net.minecraft.item.Item;
import net.cp.gemsmod.registry.ItemRegistry;

@Deprecated(forRemoval = false)
public class ModItems {
    public static final Item FIRE_GEM = ItemRegistry.FIRE_GEM;
    public static final Item WATER_GEM = ItemRegistry.WATER_GEM;
    public static final Item SPEED_GEM = ItemRegistry.SPEED_GEM;
    public static final Item STRENGTH_GEM = ItemRegistry.STRENGTH_GEM;
    public static final net.minecraft.item.ItemGroup MYSTIC_GEMS_GROUP = ItemRegistry.MYSTIC_GEMS_GROUP;

    public static void registerModItems() {
        ItemRegistry.register();
    }
}