package net.cp.gemsmod.block;

import net.cp.gemsmod.screen.GemInfuserScreenHandler;
import net.minecraft.block.CraftingTableBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.block.BlockState;

public class GemInfuserBlock extends CraftingTableBlock {
    private static final Text TITLE = Text.translatable("block.mysticgemsmod.gem_infuser");

    public GemInfuserBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if (world.isClient()) {
            return ActionResult.SUCCESS;
        }
        NamedScreenHandlerFactory factory = new SimpleNamedScreenHandlerFactory(
                (syncId, playerInventory, screenPlayer) ->
                        new GemInfuserScreenHandler(syncId, playerInventory, ScreenHandlerContext.create(world, pos)),
                TITLE
        );
        player.openHandledScreen(factory);
        return ActionResult.CONSUME;
    }
}
