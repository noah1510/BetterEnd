package org.betterx.betterend.world.biome.cave;

import net.minecraft.resources.ResourceLocation;

import org.betterx.bclib.api.biomes.BCLBiomeBuilder;
import org.betterx.bclib.api.biomes.BCLBiomeBuilder.BiomeSupplier;
import org.betterx.bclib.api.biomes.BCLBiomeSettings;
import org.betterx.betterend.registry.EndFeatures;
import org.betterx.betterend.registry.EndParticles;
import org.betterx.betterend.world.biome.EndBiome;

public class EmptyAuroraCaveBiome extends EndCaveBiome.Config {
    public static class Biome extends EndCaveBiome {
        public Biome(ResourceLocation biomeID, net.minecraft.world.level.biome.Biome biome, BCLBiomeSettings settings) {
            super(biomeID, biome, settings);

            this.addFloorFeature(EndFeatures.BIG_AURORA_CRYSTAL, 1);

            this.addCeilFeature(EndFeatures.END_STONE_STALACTITE, 1);
        }

        @Override
        public float getFloorDensity() {
            return 0.01F;
        }

        @Override
        public float getCeilDensity() {
            return 0.1F;
        }
    }

    public EmptyAuroraCaveBiome() {
        super("empty_aurora_cave");
    }

    @Override
    protected void addCustomBuildData(BCLBiomeBuilder builder) {
        super.addCustomBuildData(builder);
        builder.fogColor(150, 30, 68)
               .fogDensity(2.0F)
               .plantsColor(108, 25, 46)
               .waterAndFogColor(186, 77, 237)
               .particles(EndParticles.GLOWING_SPHERE, 0.001F);
    }

    @Override
    public BiomeSupplier<EndBiome> getSupplier() {
        return EmptyAuroraCaveBiome.Biome::new;
    }
}
