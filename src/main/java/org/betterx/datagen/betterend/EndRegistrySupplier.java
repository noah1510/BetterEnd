package org.betterx.datagen.betterend;

import org.betterx.bclib.api.v2.levelgen.biomes.BCLBiomeRegistry;
import org.betterx.bclib.api.v2.levelgen.biomes.BiomeData;
import org.betterx.bclib.api.v3.datagen.RegistrySupplier;
import org.betterx.betterend.BetterEnd;
import org.betterx.worlds.together.surfaceRules.AssignedSurfaceRule;
import org.betterx.worlds.together.surfaceRules.SurfaceRuleRegistry;

import java.util.List;
import org.jetbrains.annotations.Nullable;

public class EndRegistrySupplier extends RegistrySupplier {
    public static final EndRegistrySupplier INSTANCE = new EndRegistrySupplier();

    protected EndRegistrySupplier() {
        super(List.of(BetterEnd.MOD_ID));
    }

    @Override
    protected List<RegistryInfo<?>> initializeRegistryList(@Nullable List<String> modIDs) {
        return List.of(
                new RegistryInfo<>(
                        BCLBiomeRegistry.BCL_BIOMES_REGISTRY,
                        BiomeData.CODEC
                ),
                new RegistryInfo<>(
                        SurfaceRuleRegistry.SURFACE_RULES_REGISTRY,
                        AssignedSurfaceRule.CODEC
                )
//                ,
//                new RegistryInfo<>(
//                        Registries.STRUCTURE,
//                        Structure.DIRECT_CODEC, StructureDataProvider::bootstrap
//                ),
//                new RegistryInfo<>(
//                        Registries.STRUCTURE_SET,
//                        StructureSet.DIRECT_CODEC,
//                        StructureDataProvider::bootstrapSets
//                ),
//                new RegistryInfo<>(
//                        Registries.CONFIGURED_FEATURE,
//                        ConfiguredFeature.DIRECT_CODEC,
//                        ConfiguredFeatureDataProvider::bootstrap
//                ),
//                new RegistryInfo<>(
//                        Registries.PLACED_FEATURE,
//                        PlacedFeature.DIRECT_CODEC,
//                        PlacedFeatureDataProvider::bootstrap
//                ),
//                new RegistryInfo<>(
//                        Registries.BIOME,
//                        Biome.DIRECT_CODEC,
//                        NetherBiomesDataProvider::bootstrap
//                )
        );
    }
}