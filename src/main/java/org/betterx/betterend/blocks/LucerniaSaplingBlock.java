package org.betterx.betterend.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import org.betterx.betterend.blocks.basis.PottableFeatureSapling;
import org.betterx.betterend.registry.EndBlocks;
import org.betterx.betterend.registry.EndFeatures;

public class LucerniaSaplingBlock extends PottableFeatureSapling {
    public LucerniaSaplingBlock() {
        super((state) -> EndFeatures.LUCERNIA.getFeature());
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader world, BlockPos pos) {
        return world.getBlockState(pos.below()).is(EndBlocks.RUTISCUS);
    }

    @Override
    public boolean canPlantOn(Block block) {
        return block == EndBlocks.RUTISCUS;
    }
}
