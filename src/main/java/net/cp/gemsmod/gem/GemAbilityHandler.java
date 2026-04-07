package net.cp.gemsmod.gem;

import net.cp.gemsmod.config.ModConfig;
import net.cp.gemsmod.item.ModItems;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.Item;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;

public final class GemAbilityHandler {
    private GemAbilityHandler() {
    }

    public static ActionResult tryActivate(ServerPlayerEntity player, GemType gemType) {
        if (!GemDataManager.hasAttunedGem(player, gemType)) {
            player.sendMessage(Text.translatable("message.mysticgemsmod.gem_not_attuned").formatted(Formatting.RED), true);
            return ActionResult.FAIL;
        }

        Item cooldownItem = itemForGem(gemType);
        if (player.getItemCooldownManager().isCoolingDown(cooldownItem)) {
            player.sendMessage(Text.translatable("message.mysticgemsmod.gem_on_cooldown").formatted(Formatting.YELLOW), true);
            return ActionResult.FAIL;
        }

        player.addStatusEffect(new StatusEffectInstance(
                gemType.activeEffect(),
                ModConfig.activeDurationTicks(gemType),
                gemType.activeAmplifier(),
                false,
                true,
                true
        ));
        player.getItemCooldownManager().set(cooldownItem, ModConfig.cooldownTicks(gemType));
        spawnActivationParticles(player, gemType);
        playActivationSound(player, gemType);
        player.sendMessage(Text.translatable("message.mysticgemsmod.gem_ability_used", Text.translatable(GemDataManager.itemTranslationKey(gemType))), true);
        return ActionResult.SUCCESS;
    }

    public static void applyPassiveEffects(ServerPlayerEntity player) {
        for (GemType gemType : GemType.values()) {
            if (!GemDataManager.hasAttunedGem(player, gemType)) {
                continue;
            }
            if (!GemDataManager.hasGemInInventory(player, gemType)) {
                continue;
            }
            player.addStatusEffect(new StatusEffectInstance(
                    gemType.passiveEffect(),
                    220,
                    gemType.passiveAmplifier(),
                    false,
                    false,
                    true
            ));
            if (player.age % 20 == 0) {
                spawnPassiveParticles(player, gemType);
            }
        }
    }

    private static Item itemForGem(GemType gemType) {
        return switch (gemType) {
            case FIRE -> ModItems.FIRE_GEM;
            case WATER -> ModItems.WATER_GEM;
            case SPEED -> ModItems.SPEED_GEM;
            case STRENGTH -> ModItems.STRENGTH_GEM;
        };
    }

    private static void spawnActivationParticles(ServerPlayerEntity player, GemType gemType) {
        switch (gemType) {
            case FIRE -> player.getServerWorld().spawnParticles(ParticleTypes.FLAME, player.getX(), player.getBodyY(0.6), player.getZ(), 30, 0.5, 0.6, 0.5, 0.02);
            case WATER -> player.getServerWorld().spawnParticles(ParticleTypes.SPLASH, player.getX(), player.getBodyY(0.6), player.getZ(), 24, 0.5, 0.4, 0.5, 0.05);
            case SPEED -> player.getServerWorld().spawnParticles(ParticleTypes.CLOUD, player.getX(), player.getBodyY(0.6), player.getZ(), 26, 0.5, 0.2, 0.5, 0.03);
            case STRENGTH -> player.getServerWorld().spawnParticles(ParticleTypes.CRIT, player.getX(), player.getBodyY(0.6), player.getZ(), 28, 0.5, 0.5, 0.5, 0.2);
        }
    }

    private static void spawnPassiveParticles(ServerPlayerEntity player, GemType gemType) {
        switch (gemType) {
            case FIRE -> player.getServerWorld().spawnParticles(ParticleTypes.SMALL_FLAME, player.getX(), player.getBodyY(0.5), player.getZ(), 4, 0.25, 0.3, 0.25, 0.01);
            case WATER -> player.getServerWorld().spawnParticles(ParticleTypes.BUBBLE_POP, player.getX(), player.getBodyY(0.5), player.getZ(), 4, 0.25, 0.3, 0.25, 0.01);
            case SPEED -> player.getServerWorld().spawnParticles(ParticleTypes.END_ROD, player.getX(), player.getBodyY(0.5), player.getZ(), 3, 0.2, 0.2, 0.2, 0.0);
            case STRENGTH -> player.getServerWorld().spawnParticles(ParticleTypes.ENCHANTED_HIT, player.getX(), player.getBodyY(0.5), player.getZ(), 4, 0.2, 0.2, 0.2, 0.01);
        }
    }

    private static void playActivationSound(ServerPlayerEntity player, GemType gemType) {
        switch (gemType) {
            case FIRE -> player.getWorld().playSound(null, player.getBlockPos(), SoundEvents.ITEM_FIRECHARGE_USE, SoundCategory.PLAYERS, 0.9f, 1.0f);
            case WATER -> player.getWorld().playSound(null, player.getBlockPos(), SoundEvents.ENTITY_PLAYER_SPLASH_HIGH_SPEED, SoundCategory.PLAYERS, 0.9f, 1.0f);
            case SPEED -> player.getWorld().playSound(null, player.getBlockPos(), SoundEvents.ENTITY_BREEZE_SHOOT, SoundCategory.PLAYERS, 0.8f, 1.4f);
            case STRENGTH -> player.getWorld().playSound(null, player.getBlockPos(), SoundEvents.ENTITY_IRON_GOLEM_ATTACK, SoundCategory.PLAYERS, 0.8f, 1.0f);
        }
        player.getWorld().playSound(null, player.getBlockPos(), SoundEvents.BLOCK_AMETHYST_BLOCK_CHIME, SoundCategory.PLAYERS, 0.6f, 1.3f);
    }
}
