package net.cp.gemsmod.item;

import net.cp.gemsmod.MysticGemsMod;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final Item FIRE_GEM = registerItem("fire_gem", new Item(new Item.Settings()));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(MysticGemsMod.MOD_ID, name), item);
    }

    public static void registerModItems() {
        MysticGemsMod.LOGGER.info("Registering Mod Items for " + MysticGemsMod.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
            entries.add(FIRE_GEM);
        });
    }
}