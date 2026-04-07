package net.cp.gemsmod.item;

import net.cp.gemsmod.config.ModConfig;
import net.cp.gemsmod.gem.GemAbilityHandler;
import net.cp.gemsmod.gem.GemDataManager;
import net.cp.gemsmod.gem.GemType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class GemItem extends Item {
    private final GemType gemType;

    public GemItem(Settings settings, GemType gemType) {
        super(settings);
        this.gemType = gemType;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, net.minecraft.entity.player.PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        if (world.isClient() || !(user instanceof ServerPlayerEntity serverPlayer)) {
            return TypedActionResult.pass(stack);
        }

        if (serverPlayer.isSneaking()) {
            boolean changed = GemDataManager.toggleAttunedGem(serverPlayer, gemType);
            if (!changed) {
                serverPlayer.sendMessage(Text.translatable("message.mysticgemsmod.gem_limit", ModConfig.maxAttunedGems()), true);
            }
            return TypedActionResult.success(stack);
        }

        ActionResult activationResult = GemAbilityHandler.tryActivate(serverPlayer, gemType);
        return activationResult.isAccepted() ? TypedActionResult.success(stack) : TypedActionResult.fail(stack);
    }
}
