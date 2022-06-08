package org.betterx.betterend.world.features;

import org.betterx.betterend.blocks.EndLilySeedBlock;
import org.betterx.betterend.registry.EndBlocks;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;

public class EndLilyFeature extends UnderwaterPlantScatter {
    public EndLilyFeature(int radius) {
        super(radius);
    }

    @Override
    public void generate(WorldGenLevel world, RandomSource random, BlockPos blockPos) {
        EndLilySeedBlock seed = (EndLilySeedBlock) EndBlocks.END_LILY_SEED;
        seed.grow(world, random, blockPos);
    }

    @Override
    protected int getChance() {
        return 15;
    }
}
