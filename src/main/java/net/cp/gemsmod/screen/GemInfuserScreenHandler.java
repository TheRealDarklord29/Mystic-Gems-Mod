package net.cp.gemsmod.screen;

import net.cp.gemsmod.registry.BlockRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.CraftingScreenHandler;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;

public class GemInfuserScreenHandler extends CraftingScreenHandler {
    private final ScreenHandlerContext context;

    public GemInfuserScreenHandler(int syncId, PlayerInventory playerInventory, ScreenHandlerContext context) {
        super(syncId, playerInventory, context);
        this.context = context;
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return ScreenHandler.canUse(this.context, player, BlockRegistry.GEM_INFUSER);
    }
}
