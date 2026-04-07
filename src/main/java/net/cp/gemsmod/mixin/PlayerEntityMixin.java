package net.cp.gemsmod.mixin;

import net.cp.gemsmod.gem.GemDataHolder;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin implements GemDataHolder {
    @Unique
    private NbtCompound mysticgemsmod$gemData = new NbtCompound();

    @Inject(method = "readCustomDataFromNbt", at = @At("TAIL"))
    private void mysticgemsmod$readGemData(NbtCompound nbt, CallbackInfo ci) {
        if (nbt.contains("MysticGemData")) {
            this.mysticgemsmod$gemData = nbt.getCompound("MysticGemData").copy();
        }
    }

    @Inject(method = "writeCustomDataToNbt", at = @At("TAIL"))
    private void mysticgemsmod$writeGemData(NbtCompound nbt, CallbackInfo ci) {
        nbt.put("MysticGemData", this.mysticgemsmod$gemData.copy());
    }

    @Override
    public NbtCompound mysticgemsmod$getGemData() {
        return this.mysticgemsmod$gemData;
    }

    @Override
    public void mysticgemsmod$setGemData(NbtCompound gemData) {
        this.mysticgemsmod$gemData = gemData;
    }
}
