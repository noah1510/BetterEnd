package ru.betterend.world.structures.features;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.Heightmap.Types;
import net.minecraft.world.level.levelgen.RandomState;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGenerator;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGeneratorSupplier;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePiecesBuilder;
import ru.bclib.util.MHelper;
import ru.betterend.registry.EndStructures;
import ru.betterend.world.structures.piece.LakePiece;

import java.util.Random;
import net.minecraft.util.RandomSource;

public class MegaLakeStructure extends FeatureBaseStructure {


	public MegaLakeStructure(Structure.StructureSettings structureSettings){
		super(structureSettings);
	}

	@Override
	public StructureType<MegaLakeStructure> type() {
		return EndStructures.MEGALAKE.structureType;
	}

	protected void generatePieces(StructurePiecesBuilder structurePiecesBuilder, Structure.GenerationContext context) {
		final RandomSource random = context.random();
		final ChunkPos chunkPos = context.chunkPos();
		final ChunkGenerator chunkGenerator = context.chunkGenerator();
		final RandomState rState = context.randomState();

		final LevelHeightAccessor levelHeightAccessor = context.heightAccessor();

		int x = chunkPos.getBlockX(MHelper.randRange(4, 12, random));
		int z = chunkPos.getBlockZ(MHelper.randRange(4, 12, random));
		int y = chunkGenerator.getBaseHeight(x, z, Types.WORLD_SURFACE_WG, levelHeightAccessor, rState);

		if (y > 5) {
			Holder<Biome> biome = getNoiseBiome(chunkGenerator, rState, x >> 2, y >> 2, z >> 2);

			float radius = MHelper.randRange(32, 64, random);
			float depth = MHelper.randRange(7, 15, random);
			LakePiece piece = new LakePiece(new BlockPos(x, y, z), radius, depth, random, biome);
			structurePiecesBuilder.addPiece(piece);
		}
	}

}
