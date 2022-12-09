package org.betterx.datagen.betterend.worldgen;

import org.betterx.bclib.api.v2.levelgen.biomes.BCLBiomeBuilder;
import org.betterx.bclib.api.v2.levelgen.biomes.BiomeAPI.BiomeType;
import org.betterx.bclib.api.v3.datagen.TagDataProvider;
import org.betterx.betterend.BetterEnd;
import org.betterx.betterend.world.biome.EndBiome;
import org.betterx.betterend.world.biome.air.BiomeIceStarfield;
import org.betterx.betterend.world.biome.cave.*;
import org.betterx.betterend.world.biome.land.*;
import org.betterx.worlds.together.tag.v3.TagManager;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.world.level.biome.Biome;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class EndBiomesDataProvider extends TagDataProvider<Biome> {
    // Better End Land
    public static final EndBiome FOGGY_MUSHROOMLAND = registerBiome(new FoggyMushroomlandBiome(), BiomeType.END_LAND);
    public static final EndBiome CHORUS_FOREST = registerBiome(new ChorusForestBiome(), BiomeType.END_LAND);
    public static final EndBiome DUST_WASTELANDS = registerBiome(new DustWastelandsBiome(), BiomeType.END_LAND);
    public static final EndBiome NEON_OASIS = registerSubBiome(new NeonOasisBiome(), DUST_WASTELANDS);
    public static final EndBiome PAINTED_MOUNTAINS = registerSubBiome(new PaintedMountainsBiome(), DUST_WASTELANDS);
    public static final EndBiome MEGALAKE = registerBiome(new MegalakeBiome(), BiomeType.END_LAND);
    public static final EndBiome MEGALAKE_GROVE = registerSubBiome(new MegalakeGroveBiome(), MEGALAKE);
    public static final EndBiome CRYSTAL_MOUNTAINS = registerBiome(new CrystalMountainsBiome(), BiomeType.END_LAND);
    public static final EndBiome SHADOW_FOREST = registerBiome(new ShadowForestBiome(), BiomeType.END_LAND);
    public static final EndBiome AMBER_LAND = registerBiome(new AmberLandBiome(), BiomeType.END_LAND);
    public static final EndBiome BLOSSOMING_SPIRES = registerBiome(new BlossomingSpiresBiome(), BiomeType.END_LAND);
    public static final EndBiome SULPHUR_SPRINGS = registerBiome(new SulphurSpringsBiome(), BiomeType.END_LAND);
    public static final EndBiome UMBRELLA_JUNGLE = registerBiome(new UmbrellaJungleBiome(), BiomeType.END_LAND);
    public static final EndBiome GLOWING_GRASSLANDS = registerBiome(new GlowingGrasslandsBiome(), BiomeType.END_LAND);
    public static final EndBiome DRAGON_GRAVEYARDS = registerBiome(new DragonGraveyardsBiome(), BiomeType.END_LAND);
    public static final EndBiome DRY_SHRUBLAND = registerBiome(new DryShrublandBiome(), BiomeType.END_LAND);
    public static final EndBiome LANTERN_WOODS = registerBiome(new LanternWoodsBiome(), BiomeType.END_LAND);
    public static final EndBiome UMBRA_VALLEY = registerBiome(new UmbraValleyBiome(), BiomeType.END_LAND);
    // Better End Void
    public static final EndBiome ICE_STARFIELD = registerBiome(new BiomeIceStarfield(), BiomeType.END_VOID);
    // Better End Caves
    public static final EndCaveBiome EMPTY_END_CAVE = registerCaveBiome(new EmptyEndCaveBiome());
    public static final EndCaveBiome EMPTY_SMARAGDANT_CAVE = registerCaveBiome(new EmptySmaragdantCaveBiome());
    public static final EndCaveBiome LUSH_SMARAGDANT_CAVE = registerCaveBiome(new LushSmaragdantCaveBiome());
    public static final EndCaveBiome EMPTY_AURORA_CAVE = registerCaveBiome(new EmptyAuroraCaveBiome());
    public static final EndCaveBiome LUSH_AURORA_CAVE = registerCaveBiome(new LushAuroraCaveBiome());
    public static final EndCaveBiome JADE_CAVE = registerCaveBiome(new JadeCaveBiome());

    /**
     * Constructs a new {@link FabricTagProvider} with the default computed path.
     *
     * <p>Common implementations of this class are provided.
     *
     * @param output           the {@link FabricDataOutput} instance
     * @param registriesFuture the backing registry for the tag type
     */
    public EndBiomesDataProvider(
            FabricDataOutput output,
            CompletableFuture<HolderLookup.Provider> registriesFuture
    ) {
        super(TagManager.BIOMES, List.of(BetterEnd.MOD_ID), output, registriesFuture);
    }


    public static void bootstrap(BootstapContext<Biome> ctx) {
        BCLBiomeBuilder.registerUnbound(ctx);
    }

    public static void ensureStaticallyLoaded() {
    }

    /**
     * Put existing {@link EndBiome} as a sub-biome into selected parent.
     *
     * @param biomeConfig - {@link EndBiome.Config} instance
     * @param parent      - {@link EndBiome} to be linked with
     * @return registered {@link EndBiome}
     */
    public static EndBiome registerSubBiome(EndBiome.Config biomeConfig, EndBiome parent) {
        return EndBiome.createSubBiome(biomeConfig, parent);
    }

    /**
     * Registers {@link EndBiome} and adds it into worldgen.
     *
     * @param biomeConfig - {@link EndBiome.Config} instance
     * @param type        - {@link BiomeType}
     * @return registered {@link EndBiome}
     */
    public static EndBiome registerBiome(EndBiome.Config biomeConfig, BiomeType type) {
        final EndBiome biome = EndBiome.create(biomeConfig, type);
        return biome;
    }

    public static EndCaveBiome registerCaveBiome(EndCaveBiome.Config biomeConfig) {
        return EndCaveBiome.create(biomeConfig);
    }
}
