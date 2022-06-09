package org.betterx.betterend.world.structures.features;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.LegacyRandomSource;
import net.minecraft.world.level.levelgen.RandomState;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePiecesBuilder;

import java.util.Optional;

public abstract class FeatureBaseStructure extends Structure {
    protected static final BlockState AIR = Blocks.AIR.defaultBlockState();

    public FeatureBaseStructure(Structure.StructureSettings structureSettings) {
        super(structureSettings);
    }

    @Override
    public Optional<GenerationStub> findGenerationPoint(GenerationContext context) {
        BlockPos pos = getGenerationHeight(
                context.chunkPos(),
                context.chunkGenerator(),
                context.heightAccessor(),
                context.randomState()
        );
        if (pos.getZ() >= 20) {
            return Optional.of(new Structure.GenerationStub(pos, (structurePiecesBuilder) -> {
                generatePieces(structurePiecesBuilder, context);
            }));
        }
        return Optional.empty();
    }

    protected Holder<Biome> getNoiseBiome(ChunkGenerator cg, RandomState rState, int i, int j, int k) {
        return cg.getBiomeSource().getNoiseBiome(i, j, k, rState.sampler());
    }

    protected abstract void generatePieces(
            StructurePiecesBuilder structurePiecesBuilder,
            Structure.GenerationContext context
    );

    private static BlockPos getGenerationHeight(
            ChunkPos chunkPos,
            ChunkGenerator chunkGenerator,
            LevelHeightAccessor levelHeightAccessor,
            RandomState rState
    ) {
        LegacyRandomSource random = new LegacyRandomSource(chunkPos.x + chunkPos.z * 10387313);
        Rotation blockRotation = Rotation.getRandom(random);

        int i = 5;
        int j = 5;
        if (blockRotation == Rotation.CLOCKWISE_90) {
            i = -5;
        } else if (blockRotation == Rotation.CLOCKWISE_180) {
            i = -5;
            j = -5;
        } else if (blockRotation == Rotation.COUNTERCLOCKWISE_90) {
            j = -5;
        }

        int k = chunkPos.getBlockX(7);
        int l = chunkPos.getBlockZ(7);
        int m = chunkGenerator.getFirstOccupiedHeight(
                k,
                l,
                Heightmap.Types.WORLD_SURFACE_WG,
                levelHeightAccessor,
                rState
        );
        int n = chunkGenerator.getFirstOccupiedHeight(
                k,
                l + j,
                Heightmap.Types.WORLD_SURFACE_WG,
                levelHeightAccessor,
                rState
        );
        int o = chunkGenerator.getFirstOccupiedHeight(
                k + i,
                l,
                Heightmap.Types.WORLD_SURFACE_WG,
                levelHeightAccessor,
                rState
        );
        int p = chunkGenerator.getFirstOccupiedHeight(
                k + i,
                l + j,
                Heightmap.Types.WORLD_SURFACE_WG,
                levelHeightAccessor, rState
        );
        return new BlockPos(k, l, Math.min(Math.min(m, n), Math.min(o, p)));
    }
}
