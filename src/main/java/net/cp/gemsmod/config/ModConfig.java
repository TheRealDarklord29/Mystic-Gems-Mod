package net.cp.gemsmod.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.cp.gemsmod.MysticGemsMod;
import net.cp.gemsmod.gem.GemType;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;

public final class ModConfig {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Path CONFIG_PATH = Path.of("config", "mysticgemsmod.json");

    private static ConfigData data = new ConfigData();

    private ModConfig() {
    }

    public static void load() {
        try {
            Files.createDirectories(CONFIG_PATH.getParent());
            if (!Files.exists(CONFIG_PATH)) {
                save();
                return;
            }
            try (Reader reader = Files.newBufferedReader(CONFIG_PATH)) {
                ConfigData loaded = GSON.fromJson(reader, ConfigData.class);
                if (loaded != null) {
                    data = loaded;
                }
            }
            save();
        } catch (IOException e) {
            MysticGemsMod.LOGGER.error("Failed to load mysticgemsmod config", e);
        }
    }

    private static void save() throws IOException {
        try (Writer writer = Files.newBufferedWriter(CONFIG_PATH)) {
            GSON.toJson(data, writer);
        }
    }

    public static int maxAttunedGems() {
        return Math.max(1, data.maxAttunedGems);
    }

    public static float miningDropChance() {
        return clampChance(data.miningDropChance);
    }

    public static float mobDropChance(GemType gemType) {
        return switch (gemType) {
            case FIRE -> clampChance(data.fireMobDropChance);
            case WATER -> clampChance(data.waterMobDropChance);
            case SPEED -> clampChance(data.speedMobDropChance);
            case STRENGTH -> clampChance(data.strengthMobDropChance);
        };
    }

    public static int cooldownTicks(GemType gemType) {
        int value = switch (gemType) {
            case FIRE -> data.fireCooldownTicks;
            case WATER -> data.waterCooldownTicks;
            case SPEED -> data.speedCooldownTicks;
            case STRENGTH -> data.strengthCooldownTicks;
        };
        return Math.max(20, value);
    }

    public static int activeDurationTicks(GemType gemType) {
        int value = switch (gemType) {
            case FIRE -> data.fireActiveDurationTicks;
            case WATER -> data.waterActiveDurationTicks;
            case SPEED -> data.speedActiveDurationTicks;
            case STRENGTH -> data.strengthActiveDurationTicks;
        };
        return Math.max(20, value);
    }

    private static float clampChance(float chance) {
        return Math.max(0.0f, Math.min(1.0f, chance));
    }

    private static final class ConfigData {
        int maxAttunedGems = 3;

        float miningDropChance = 0.015f;
        float fireMobDropChance = 0.20f;
        float waterMobDropChance = 0.15f;
        float speedMobDropChance = 0.12f;
        float strengthMobDropChance = 0.10f;

        int fireCooldownTicks = 20 * 30;
        int waterCooldownTicks = 20 * 30;
        int speedCooldownTicks = 20 * 25;
        int strengthCooldownTicks = 20 * 30;

        int fireActiveDurationTicks = 20 * 15;
        int waterActiveDurationTicks = 20 * 12;
        int speedActiveDurationTicks = 20 * 10;
        int strengthActiveDurationTicks = 20 * 10;
    }
}
