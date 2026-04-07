package net.cp.gemsmod.gem;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.registry.entry.RegistryEntry;

public enum GemType {
    FIRE(
            "fire_gem",
            StatusEffects.FIRE_RESISTANCE, 0,
            StatusEffects.FIRE_RESISTANCE, 0,
            20 * 15,
            20 * 30
    ),
    WATER(
            "water_gem",
            StatusEffects.WATER_BREATHING, 0,
            StatusEffects.DOLPHINS_GRACE, 0,
            20 * 12,
            20 * 30
    ),
    SPEED(
            "speed_gem",
            StatusEffects.SPEED, 0,
            StatusEffects.SPEED, 1,
            20 * 10,
            20 * 25
    ),
    STRENGTH(
            "strength_gem",
            StatusEffects.STRENGTH, 0,
            StatusEffects.STRENGTH, 1,
            20 * 10,
            20 * 30
    );

    private final String itemId;
    private final RegistryEntry<StatusEffect> passiveEffect;
    private final int passiveAmplifier;
    private final RegistryEntry<StatusEffect> activeEffect;
    private final int activeAmplifier;
    private final int activeDurationTicks;
    private final int cooldownTicks;

    GemType(
            String itemId,
            RegistryEntry<StatusEffect> passiveEffect,
            int passiveAmplifier,
            RegistryEntry<StatusEffect> activeEffect,
            int activeAmplifier,
            int activeDurationTicks,
            int cooldownTicks
    ) {
        this.itemId = itemId;
        this.passiveEffect = passiveEffect;
        this.passiveAmplifier = passiveAmplifier;
        this.activeEffect = activeEffect;
        this.activeAmplifier = activeAmplifier;
        this.activeDurationTicks = activeDurationTicks;
        this.cooldownTicks = cooldownTicks;
    }

    public String itemId() {
        return itemId;
    }

    public RegistryEntry<StatusEffect> passiveEffect() {
        return passiveEffect;
    }

    public int passiveAmplifier() {
        return passiveAmplifier;
    }

    public RegistryEntry<StatusEffect> activeEffect() {
        return activeEffect;
    }

    public int activeAmplifier() {
        return activeAmplifier;
    }

    public int activeDurationTicks() {
        return activeDurationTicks;
    }

    public int cooldownTicks() {
        return cooldownTicks;
    }
}
