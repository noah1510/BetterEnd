package org.betterx.betterend.blocks;

import org.betterx.betterend.blocks.basis.PottableFeatureSapling;
import org.betterx.betterend.registry.EndBlocks;
import org.betterx.betterend.registry.EndFeatures;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class HelixTreeSaplingBlock extends PottableFeatureSapling {
    public HelixTreeSaplingBlock() {
        super((state) -> EndFeatures.HELIX_TREE);
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader world, BlockPos pos) {
        return world.getBlockState(pos.below()).is(EndBlocks.AMBER_MOSS);
    }

    @Override
    public boolean canPlantOn(Block block) {
        return block == EndBlocks.AMBER_MOSS;
    }
}
