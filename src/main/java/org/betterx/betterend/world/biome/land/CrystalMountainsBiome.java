package org.betterx.betterend.world.biome.land;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.state.BlockState;

import org.betterx.bclib.api.biomes.BCLBiomeBuilder;
import org.betterx.bclib.interfaces.SurfaceMaterialProvider;
import org.betterx.betterend.registry.EndBlocks;
import org.betterx.betterend.registry.EndFeatures;
import org.betterx.betterend.registry.EndSounds;
import org.betterx.betterend.registry.EndStructures;
import org.betterx.betterend.world.biome.EndBiome;

public class CrystalMountainsBiome extends EndBiome.Config {
    public CrystalMountainsBiome() {
        super("crystal_mountains");
    }

    @Override
    protected void addCustomBuildData(BCLBiomeBuilder builder) {
        builder
                .structure(EndStructures.MOUNTAIN)
                .plantsColor(255, 133, 211)
                .music(EndSounds.MUSIC_OPENSPACE)
                .feature(EndFeatures.CRYSTAL_GRASS)
                .spawn(EntityType.ENDERMAN, 50, 1, 2);
    }

    @Override
    protected SurfaceMaterialProvider surfaceMaterial() {
        return new EndBiome.DefaultSurfaceMaterialProvider() {
            @Override
            public BlockState getTopMaterial() {
                return EndBlocks.CRYSTAL_MOSS.defaultBlockState();
            }
        };
    }
}
