package org.notionsmp.fastchestrevived.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.notionsmp.fastchestrevived.StaticChests;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Level.class)
public abstract class LevelMixin {

    @Shadow
    public abstract BlockState getBlockState(BlockPos pos);

    @Inject(method = "setBlockEntity", at = @At("HEAD"), cancellable = true)
    private void fastchestrevived$skipSet(BlockPos pos, BlockEntity blockEntity, CallbackInfo ci) {
        if (StaticChests.applies((Level) (Object) this, getBlockState(pos))) {
            ci.cancel();
        }
    }

    @Inject(method = "addBlockEntity", at = @At("HEAD"), cancellable = true)
    private void fastchestrevived$skipAdd(BlockEntity blockEntity, CallbackInfoReturnable<Boolean> cir) {
        if (StaticChests.applies((Level) (Object) this, getBlockState(blockEntity.getBlockPos()))) {
            cir.setReturnValue(false);
        }
    }
}
