package net.cp.gemsmod;

import net.cp.gemsmod.config.ModConfig;
import net.cp.gemsmod.gem.GemDropHandler;
import net.cp.gemsmod.gem.GemTickHandler;
import net.cp.gemsmod.registry.BlockRegistry;
import net.cp.gemsmod.registry.ItemRegistry;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MysticGemsMod implements ModInitializer {
	public static final String MOD_ID = "mysticgemsmod";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
        ModConfig.load();
        BlockRegistry.register();
        ItemRegistry.register();
        GemDropHandler.register();
        GemTickHandler.register();
	}
}