package org.betterx.betterend.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import org.betterx.betterend.blocks.basis.PottableFeatureSapling;
import org.betterx.betterend.registry.EndBlocks;
import org.betterx.betterend.registry.EndFeatures;

public class TenaneaSaplingBlock extends PottableFeatureSapling {
    public TenaneaSaplingBlock() {
        super((state) -> EndFeatures.TENANEA);
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader world, BlockPos pos) {
        return world.getBlockState(pos.below()).is(EndBlocks.PINK_MOSS);
    }

    @Override
    public boolean canPlantOn(Block block) {
        return block == EndBlocks.PINK_MOSS;
    }
}
