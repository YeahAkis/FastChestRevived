package org.notionsmp.fastchestrevived.mixin;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import org.notionsmp.fastchestrevived.StaticChests;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractBlock.AbstractBlockState.class)
public abstract class BlockStateBaseMixin {

    @Inject(method = "getRenderShape", at = @At("HEAD"), cancellable = true)
    private void fastchestrevived$renderAsModel(CallbackInfoReturnable<BlockRenderType> cir) {
        if (StaticChests.isStaticChest((BlockState) (Object) this)) {
            cir.setReturnValue(BlockRenderType.MODEL);
        }
    }
}
