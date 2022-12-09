package org.betterx.datagen.betterend;

import org.betterx.bclib.api.v2.levelgen.biomes.BCLBiomeRegistry;

import net.minecraft.core.RegistrySetBuilder;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class BetterEndDatagen implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator dataGenerator) {
        BCLBiomeRegistry.prepareForDatagen();

        final FabricDataGenerator.Pack pack = dataGenerator.createPack();
    }

    @Override
    public void buildRegistry(RegistrySetBuilder registryBuilder) {
        EndRegistrySupplier.INSTANCE.bootstrapRegistries(registryBuilder);
    }
}
