package org.betterx.datagen.betterend;

import org.betterx.bclib.api.v2.levelgen.biomes.BCLBiomeRegistry;
import org.betterx.datagen.betterend.worldgen.EndBiomesDataProvider;
import org.betterx.datagen.betterend.worldgen.EndRegistriesDataProvider;

import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class BetterEndDatagen implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator dataGenerator) {
        BCLBiomeRegistry.prepareForDatagen();
        EndBiomesDataProvider.ensureStaticallyLoaded();

        final FabricDataGenerator.Pack pack = dataGenerator.createPack();
        pack.addProvider(EndBiomesDataProvider::new);
        pack.addProvider(EndRegistriesDataProvider::new);
    }

    @Override
    public void buildRegistry(RegistrySetBuilder registryBuilder) {
        EndRegistrySupplier.INSTANCE.bootstrapRegistries(registryBuilder);
        registryBuilder.add(Registries.BIOME, EndBiomesDataProvider::bootstrap);
    }
}
