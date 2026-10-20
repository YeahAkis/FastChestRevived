package org.notionsmp.fastchestrevived.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import org.notionsmp.fastchestrevived.StaticChests;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Chunk.class)
public abstract class LevelChunkMixin {

    @Shadow
    @Final
    private World level;

    @Shadow
    public abstract BlockState getBlockState(BlockPos pos);

    @Inject(method = "createBlockEntity", at = @At("HEAD"), cancellable = true)
    private void fastchestrevived$skipCreate(BlockPos pos, CallbackInfoReturnable<TileEntity> cir) {
        if (StaticChests.applies(level, getBlockState(pos))) {
            cir.setReturnValue(null);
        }
    }

    @Inject(method = "setBlockEntity", at = @At("HEAD"), cancellable = true)
    private void fastchestrevived$skipSet(BlockPos pos, TileEntity blockEntity, CallbackInfo ci) {
        if (StaticChests.applies(level, getBlockState(pos))) {
            ci.cancel();
        }
    }

    @Inject(method = "addBlockEntity", at = @At("HEAD"), cancellable = true)
    private void fastchestrevived$skipAdd(TileEntity blockEntity, CallbackInfo ci) {
        if (StaticChests.applies(level, getBlockState(blockEntity.getBlockPos()))) {
            ci.cancel();
        }
    }
}
