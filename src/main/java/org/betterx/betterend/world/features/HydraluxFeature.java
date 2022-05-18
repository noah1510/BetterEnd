package org.betterx.betterend.world.features;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;

import org.betterx.betterend.blocks.HydraluxSaplingBlock;
import org.betterx.betterend.registry.EndBlocks;

public class HydraluxFeature extends UnderwaterPlantScatter {
    public HydraluxFeature(int radius) {
        super(radius);
    }

    @Override
    public void generate(WorldGenLevel world, RandomSource random, BlockPos blockPos) {
        HydraluxSaplingBlock seed = (HydraluxSaplingBlock) EndBlocks.HYDRALUX_SAPLING;
        seed.grow(world, random, blockPos);
    }

    @Override
    protected int getChance() {
        return 15;
    }
}
