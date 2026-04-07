package net.cp.gemsmod.gem;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.server.network.ServerPlayerEntity;

public final class GemTickHandler {
    private GemTickHandler() {
    }

    public static void register() {
        ServerTickEvents.END_SERVER_TICK.register(server -> {
            for (ServerPlayerEntity player : server.getPlayerManager().getPlayerList()) {
                GemAbilityHandler.applyPassiveEffects(player);
            }
        });
    }
}
