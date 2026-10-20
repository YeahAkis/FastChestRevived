package org.notionsmp.fastchestrevived;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.world.World;

public final class StaticChests {

    private StaticChests() {
    }

    public static boolean applies(World level, BlockState state) {
        return level != null && level.isClientSide() && isStaticChest(state);
    }

    public static boolean isStaticChest(BlockState state) {
        Block block = state.getBlock();
        return block == Blocks.CHEST
                || block == Blocks.TRAPPED_CHEST
                || block == Blocks.ENDER_CHEST;
    }
}
