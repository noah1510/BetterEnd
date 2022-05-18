package org.betterx.betterend.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import org.betterx.betterend.blocks.basis.PottableFeatureSapling;
import org.betterx.betterend.registry.EndBlocks;
import org.betterx.betterend.registry.EndFeatures;

public class MossyGlowshroomSaplingBlock extends PottableFeatureSapling {
    public MossyGlowshroomSaplingBlock() {
        super(7, (state) -> EndFeatures.MOSSY_GLOWSHROOM.getFeature());
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader world, BlockPos pos) {
        return world.getBlockState(pos.below()).is(EndBlocks.END_MOSS) || world.getBlockState(pos.below())
                                                                               .is(EndBlocks.END_MYCELIUM);
    }

    @Override
    public boolean canPlantOn(Block block) {
        return block == EndBlocks.END_MOSS || block == EndBlocks.END_MYCELIUM;
    }
}
