package net.cp.gemsmod.registry;

import net.cp.gemsmod.MysticGemsMod;
import net.cp.gemsmod.gem.GemType;
import net.cp.gemsmod.item.GemItem;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public final class ItemRegistry {
    public static final Item FIRE_GEM = registerItem("fire_gem", new GemItem(new Item.Settings().maxCount(1), GemType.FIRE));
    public static final Item WATER_GEM = registerItem("water_gem", new GemItem(new Item.Settings().maxCount(1), GemType.WATER));
    public static final Item SPEED_GEM = registerItem("speed_gem", new GemItem(new Item.Settings().maxCount(1), GemType.SPEED));
    public static final Item STRENGTH_GEM = registerItem("strength_gem", new GemItem(new Item.Settings().maxCount(1), GemType.STRENGTH));

    public static final net.minecraft.item.ItemGroup MYSTIC_GEMS_GROUP =
            FabricItemGroup.builder()
                    .icon(() -> FIRE_GEM.getDefaultStack())
                    .displayName(Text.translatable("itemGroup.mysticgemsmod.main"))
                    .entries((displayContext, entries) -> {
                        entries.add(BlockRegistry.GEM_INFUSER);
                        entries.add(FIRE_GEM);
                        entries.add(WATER_GEM);
                        entries.add(SPEED_GEM);
                        entries.add(STRENGTH_GEM);
                    })
                    .build();

    private ItemRegistry() {
    }

    public static void register() {
        MysticGemsMod.LOGGER.info("Registering Items for " + MysticGemsMod.MOD_ID);

        RegistryKey<net.minecraft.item.ItemGroup> groupKey =
                RegistryKey.of(Registries.ITEM_GROUP.getKey(), Identifier.of(MysticGemsMod.MOD_ID, "main"));

        Registry.register(Registries.ITEM_GROUP, groupKey, MYSTIC_GEMS_GROUP);

        ItemGroupEvents.modifyEntriesEvent(groupKey)
                .register(entries -> {
                    entries.add(BlockRegistry.GEM_INFUSER);
                    entries.add(FIRE_GEM);
                    entries.add(WATER_GEM);
                    entries.add(SPEED_GEM);
                    entries.add(STRENGTH_GEM);
                });
    }

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(MysticGemsMod.MOD_ID, name), item);
    }
}
