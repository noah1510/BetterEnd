package org.betterx.betterend.world.biome.cave;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.feature.Feature;

import org.betterx.bclib.api.biomes.BCLBiomeBuilder;
import org.betterx.bclib.api.biomes.BCLBiomeBuilder.BiomeSupplier;
import org.betterx.bclib.api.biomes.BiomeAPI;
import org.betterx.bclib.api.features.BCLCommonFeatures;
import org.betterx.bclib.util.WeightedList;
import org.betterx.bclib.world.biomes.BCLBiomeSettings;
import org.betterx.bclib.world.features.BCLFeature;
import org.betterx.betterend.BetterEnd;
import org.betterx.betterend.registry.EndSounds;
import org.betterx.betterend.world.biome.EndBiome;
import org.betterx.betterend.world.features.terrain.caves.CaveChunkPopulatorFeature;

public class EndCaveBiome extends EndBiome {
    public static abstract class Config extends EndBiome.Config {
        protected Config(String name) {
            super(name);
        }

        @Override
        protected void addCustomBuildData(BCLBiomeBuilder builder) {
            BCLFeature feature = BCLCommonFeatures.makeChunkFeature(
                    BetterEnd.makeID(ID.getPath() + "_cave_populator"),
                    GenerationStep.Decoration.RAW_GENERATION,
                    new CaveChunkPopulatorFeature(() -> (EndCaveBiome) BiomeAPI.getBiome(ID))
                                                                   );

            builder.feature(feature)
                   .music(EndSounds.MUSIC_CAVES)
                   .loop(EndSounds.AMBIENT_CAVES);
        }

        @Override
        protected boolean hasCaves() {
            return false;
        }

        @Override
        public BiomeSupplier<EndBiome> getSupplier() {
            return EndCaveBiome::new;
        }
    }

    private final WeightedList<Feature<?>> floorFeatures = new WeightedList<Feature<?>>();
    private final WeightedList<Feature<?>> ceilFeatures = new WeightedList<Feature<?>>();

    public EndCaveBiome(ResourceLocation biomeID, Biome biome, BCLBiomeSettings settings) {
        super(biomeID, biome, settings);
    }

    public void addFloorFeature(Feature<?> feature, float weight) {
        floorFeatures.add(feature, weight);
    }

    public void addCeilFeature(Feature<?> feature, float weight) {
        ceilFeatures.add(feature, weight);
    }

    public Feature<?> getFloorFeature(RandomSource random) {
        return floorFeatures.isEmpty() ? null : floorFeatures.get(random);
    }

    public Feature<?> getCeilFeature(RandomSource random) {
        return ceilFeatures.isEmpty() ? null : ceilFeatures.get(random);
    }

    public float getFloorDensity() {
        return 0;
    }

    public float getCeilDensity() {
        return 0;
    }

    public BlockState getCeil(BlockPos pos) {
        return null;
    }

    public BlockState getWall(BlockPos pos) {
        return null;
    }

    public static EndCaveBiome create(EndBiome.Config biomeConfig) {
        return (EndCaveBiome) EndBiome.create(biomeConfig);
    }
}
