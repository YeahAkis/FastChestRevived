package org.notionsmp.fastchestrevived.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.notionsmp.fastchestrevived.StaticChests;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(World.class)
public abstract class LevelMixin {

    @Shadow
    public abstract BlockState getBlockState(BlockPos pos);

    @Inject(method = "setBlockEntity", at = @At("HEAD"), cancellable = true)
    private void fastchestrevived$skipSet(BlockPos pos, TileEntity blockEntity, CallbackInfo ci) {
        if (StaticChests.applies((World) (Object) this, getBlockState(pos))) {
            ci.cancel();
        }
    }

    @Inject(method = "addBlockEntity", at = @At("HEAD"), cancellable = true)
    private void fastchestrevived$skipAdd(TileEntity blockEntity, CallbackInfoReturnable<Boolean> cir) {
        if (StaticChests.applies((World) (Object) this, getBlockState(blockEntity.getBlockPos()))) {
            cir.setReturnValue(false);
        }
    }
}
