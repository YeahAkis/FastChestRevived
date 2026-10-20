package org.notionsmp.fastchestrevived.mixin;

import net.minecraft.client.renderer.block.BlockModelSet;
import net.minecraft.client.renderer.block.model.BlockModel;
import net.minecraft.world.level.block.state.BlockState;
import org.notionsmp.fastchestrevived.StaticChests;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;

@Mixin(BlockModelSet.class)
public abstract class BlockModelSetMixin {

    @Shadow
    @Final
    private Map<BlockState, BlockModel> blockModelByStateCache;

    @Inject(method = "<init>", at = @At("TAIL"))
    private void fastchestrevived$useJsonModels(CallbackInfo ci) {
        blockModelByStateCache.keySet().removeIf(StaticChests::isStaticChest);
    }
}
