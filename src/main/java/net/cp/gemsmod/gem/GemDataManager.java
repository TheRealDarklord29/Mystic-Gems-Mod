package net.cp.gemsmod.gem;

import net.cp.gemsmod.config.ModConfig;
import net.cp.gemsmod.item.ModItems;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Item;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.nbt.NbtString;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.EnumSet;
import java.util.Set;

public final class GemDataManager {
    private static final String ATTUNED_LIST_KEY = "attuned_gems";

    private GemDataManager() {
    }

    public static boolean toggleAttunedGem(ServerPlayerEntity player, GemType gemType) {
        Set<GemType> attuned = getAttunedGems(player);
        if (attuned.contains(gemType)) {
            attuned.remove(gemType);
            setAttunedGems(player, attuned);
            player.sendMessage(Text.translatable("message.mysticgemsmod.gem_unattuned", Text.translatable(itemTranslationKey(gemType))), true);
            return true;
        }

        if (attuned.size() >= ModConfig.maxAttunedGems()) {
            return false;
        }

        attuned.add(gemType);
        setAttunedGems(player, attuned);
        player.sendMessage(Text.translatable("message.mysticgemsmod.gem_attuned", Text.translatable(itemTranslationKey(gemType))), true);
        return true;
    }

    public static boolean hasAttunedGem(ServerPlayerEntity player, GemType gemType) {
        return getAttunedGems(player).contains(gemType);
    }

    public static boolean hasGemInInventory(ServerPlayerEntity player, GemType gemType) {
        Item gemItem = gemItemForType(gemType);
        PlayerInventory inventory = player.getInventory();
        for (int i = 0; i < inventory.size(); i++) {
            if (inventory.getStack(i).isOf(gemItem)) {
                return true;
            }
        }
        return false;
    }

    public static String itemTranslationKey(GemType gemType) {
        return "item.mysticgemsmod." + gemType.itemId();
    }

    private static Item gemItemForType(GemType gemType) {
        return switch (gemType) {
            case FIRE -> ModItems.FIRE_GEM;
            case WATER -> ModItems.WATER_GEM;
            case SPEED -> ModItems.SPEED_GEM;
            case STRENGTH -> ModItems.STRENGTH_GEM;
        };
    }

    private static Set<GemType> getAttunedGems(ServerPlayerEntity player) {
        Set<GemType> result = EnumSet.noneOf(GemType.class);
        NbtCompound data = getData(player);
        if (!data.contains(ATTUNED_LIST_KEY)) {
            return result;
        }

        NbtList list = data.getList(ATTUNED_LIST_KEY, NbtElement.STRING_TYPE);
        for (int i = 0; i < list.size(); i++) {
            String rawId = list.getString(i);
            Identifier id = Identifier.tryParse(rawId);
            if (id == null || !id.getNamespace().equals("mysticgemsmod")) {
                continue;
            }
            for (GemType gemType : GemType.values()) {
                if (id.getPath().equals(gemType.itemId())) {
                    result.add(gemType);
                    break;
                }
            }
        }
        return result;
    }

    private static void setAttunedGems(ServerPlayerEntity player, Set<GemType> attuned) {
        NbtList list = new NbtList();
        for (GemType gemType : attuned) {
            Identifier id = Identifier.of("mysticgemsmod", gemType.itemId());
            list.add(NbtString.of(id.toString()));
        }
        NbtCompound data = getData(player);
        data.put(ATTUNED_LIST_KEY, list);
        ((GemDataHolder) player).mysticgemsmod$setGemData(data);
    }

    private static NbtCompound getData(ServerPlayerEntity player) {
        NbtCompound data = ((GemDataHolder) player).mysticgemsmod$getGemData().copy();
        if (!data.contains(ATTUNED_LIST_KEY)) {
            data.put(ATTUNED_LIST_KEY, new NbtList());
        }
        return data;
    }
}
