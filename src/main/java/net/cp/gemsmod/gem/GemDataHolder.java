package net.cp.gemsmod.gem;

import net.minecraft.nbt.NbtCompound;

public interface GemDataHolder {
    NbtCompound mysticgemsmod$getGemData();

    void mysticgemsmod$setGemData(NbtCompound gemData);
}
