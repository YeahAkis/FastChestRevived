package org.notionsmp.fastchestrevived;

import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public final class StaticChests {

    private StaticChests() {
    }

    public static boolean applies(Level level, BlockState state) {
        return level != null && level.isClientSide() && isStaticChest(state);
    }

    public static boolean isStaticChest(BlockState state) {
        Block block = state.getBlock();
        return block == Blocks.CHEST
                || block == Blocks.TRAPPED_CHEST
                || block == Blocks.ENDER_CHEST;
    }
}
