package net.cp.gemsmod;

import net.cp.gemsmod.item.ModItems;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MysticGemsMod implements ModInitializer {
	public static final String MOD_ID = "mysticgemsmod";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
        ModItems.registerModItems();
	}
}