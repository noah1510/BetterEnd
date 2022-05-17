package ru.betterend.world.structures.features;

import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.LegacyRandomSource;
import net.minecraft.world.level.levelgen.RandomState;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGeneratorSupplier;
import ru.bclib.world.structures.BCLStructure;

import java.util.Random;
import net.minecraft.util.RandomSource;

public abstract class FeatureBaseStructure extends Structure {
	protected static final BlockState AIR = Blocks.AIR.defaultBlockState();
	
	public FeatureBaseStructure(PieceGeneratorSupplier<NoneFeatureConfiguration> pieceGeneratorSupplier) {
		super(NoneFeatureConfiguration.CODEC, pieceGeneratorSupplier);
	}

	protected static boolean checkLocation(PieceGeneratorSupplier.Context<NoneFeatureConfiguration> context) {
		return getGenerationHeight(context.chunkPos(), context.chunkGenerator(), context.heightAccessor(), context.randomState()) >= 20 && BCLStructure.isValidBiome(context);
	}
	
	private static int getGenerationHeight(ChunkPos chunkPos, ChunkGenerator chunkGenerator, LevelHeightAccessor levelHeightAccessor, RandomState rState) {
		LegacyRandomSource random = new LegacyRandomSource((long) (chunkPos.x + chunkPos.z * 10387313));
		Rotation blockRotation = Rotation.getRandom(random);

		int i = 5;
		int j = 5;
		if (blockRotation == Rotation.CLOCKWISE_90) {
			i = -5;
		}
		else if (blockRotation == Rotation.CLOCKWISE_180) {
			i = -5;
			j = -5;
		}
		else if (blockRotation == Rotation.COUNTERCLOCKWISE_90) {
			j = -5;
		}
		
		int k = chunkPos.getBlockX(7);
		int l = chunkPos.getBlockZ(7);
		int m = chunkGenerator.getFirstOccupiedHeight(k, l, Heightmap.Types.WORLD_SURFACE_WG, levelHeightAccessor, rState);
		int n = chunkGenerator.getFirstOccupiedHeight(k, l + j, Heightmap.Types.WORLD_SURFACE_WG, levelHeightAccessor, rState);
		int o = chunkGenerator.getFirstOccupiedHeight(k + i, l, Heightmap.Types.WORLD_SURFACE_WG, levelHeightAccessor, rState);
		int p = chunkGenerator.getFirstOccupiedHeight(
			k + i,
			l + j,
			Heightmap.Types.WORLD_SURFACE_WG,
			levelHeightAccessor, rState
		);
		return Math.min(Math.min(m, n), Math.min(o, p));
	}
}
