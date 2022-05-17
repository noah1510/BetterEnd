package ru.betterend.world.features;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import ru.betterend.blocks.basis.EndPlantWithAgeBlock;
import ru.betterend.registry.EndBlocks;

import java.util.Random;
import net.minecraft.util.RandomSource;

public class GlowPillarFeature extends ScatterFeature {
	public GlowPillarFeature() {
		super(9);
	}
	
	@Override
	public boolean canGenerate(WorldGenLevel world, RandomSource random, BlockPos center, BlockPos blockPos, float radius) {
		return EndBlocks.GLOWING_PILLAR_SEED.canSurvive(AIR, world, blockPos);
	}
	
	@Override
	public void generate(WorldGenLevel world, RandomSource random, BlockPos blockPos) {
		EndPlantWithAgeBlock seed = ((EndPlantWithAgeBlock) EndBlocks.GLOWING_PILLAR_SEED);
		seed.growAdult(world, random, blockPos);
	}
	
	@Override
	protected int getChance() {
		return 10;
	}
}
