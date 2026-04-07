package net.cp.gemsmod.gem;

import net.cp.gemsmod.config.ModConfig;
import net.cp.gemsmod.registry.ItemRegistry;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.minecraft.entity.mob.BlazeEntity;
import net.minecraft.entity.mob.DrownedEntity;
import net.minecraft.entity.mob.SpiderEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.random.Random;

public final class GemDropHandler {
    private GemDropHandler() {
    }

    public static void register() {
        PlayerBlockBreakEvents.AFTER.register((world, player, pos, state, blockEntity) -> {
            if (world.isClient() || !(player instanceof ServerPlayerEntity serverPlayer)) {
                return;
            }
            Random random = world.getRandom();
            if (random.nextFloat() > ModConfig.miningDropChance()) {
                return;
            }
            GemType randomGem = GemType.values()[random.nextInt(GemType.values().length)];
            serverPlayer.dropItem(gemStack(randomGem), false);
        });

        ServerLivingEntityEvents.AFTER_DEATH.register((entity, source) -> {
            if (entity.getWorld().isClient()) {
                return;
            }
            Random random = entity.getWorld().getRandom();
            if (entity instanceof BlazeEntity && random.nextFloat() <= ModConfig.mobDropChance(GemType.FIRE)) {
                entity.dropStack(gemStack(GemType.FIRE));
            } else if (entity instanceof DrownedEntity && random.nextFloat() <= ModConfig.mobDropChance(GemType.WATER)) {
                entity.dropStack(gemStack(GemType.WATER));
            } else if (entity instanceof SpiderEntity && random.nextFloat() <= ModConfig.mobDropChance(GemType.SPEED)) {
                entity.dropStack(gemStack(GemType.SPEED));
            } else if (entity instanceof ZombieEntity && random.nextFloat() <= ModConfig.mobDropChance(GemType.STRENGTH)) {
                entity.dropStack(gemStack(GemType.STRENGTH));
            }
        });
    }

    private static ItemStack gemStack(GemType gemType) {
        return switch (gemType) {
            case FIRE -> new ItemStack(ItemRegistry.FIRE_GEM);
            case WATER -> new ItemStack(ItemRegistry.WATER_GEM);
            case SPEED -> new ItemStack(ItemRegistry.SPEED_GEM);
            case STRENGTH -> new ItemStack(ItemRegistry.STRENGTH_GEM);
        };
    }
}
