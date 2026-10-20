package org.notionsmp.fastchestrevived.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.LevelChunk;
import org.notionsmp.fastchestrevived.StaticChests;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LevelChunk.class)
public abstract class LevelChunkMixin {

    @Shadow
    @Final
    private Level level;

    @Shadow
    public abstract BlockState getBlockState(BlockPos pos);

    @Inject(method = "createBlockEntity", at = @At("HEAD"), cancellable = true)
    private void fastchestrevived$skipCreate(BlockPos pos, CallbackInfoReturnable<BlockEntity> cir) {
        if (StaticChests.applies(level, getBlockState(pos))) {
            cir.setReturnValue(null);
        }
    }

    @Inject(method = "setBlockEntity", at = @At("HEAD"), cancellable = true)
    private void fastchestrevived$skipSet(BlockPos pos, BlockEntity blockEntity, CallbackInfo ci) {
        if (StaticChests.applies(level, getBlockState(pos))) {
            ci.cancel();
        }
    }

    @Inject(method = "addBlockEntity", at = @At("HEAD"), cancellable = true)
    private void fastchestrevived$skipAdd(BlockEntity blockEntity, CallbackInfo ci) {
        if (StaticChests.applies(level, getBlockState(blockEntity.getBlockPos()))) {
            ci.cancel();
        }
    }
}
