package org.notionsmp.fastchestrevived.mixin;

import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import org.notionsmp.fastchestrevived.StaticChests;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlockBehaviour.BlockStateBase.class)
public abstract class BlockStateBaseMixin {

    @Inject(method = "getRenderShape", at = @At("HEAD"), cancellable = true)
    private void fastchestrevived$renderAsModel(CallbackInfoReturnable<RenderShape> cir) {
        if (StaticChests.isStaticChest((BlockState) (Object) this)) {
            cir.setReturnValue(RenderShape.MODEL);
        }
    }
}
