package ru.betterend.world.features;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import ru.betterend.blocks.HydraluxSaplingBlock;
import ru.betterend.registry.EndBlocks;

import java.util.Random;
import net.minecraft.util.RandomSource;

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
