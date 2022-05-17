package ru.betterend.world.features;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import ru.betterend.blocks.EndLilySeedBlock;
import ru.betterend.registry.EndBlocks;

import java.util.Random;
import net.minecraft.util.RandomSource;

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
