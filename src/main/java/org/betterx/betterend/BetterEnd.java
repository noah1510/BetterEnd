package org.betterx.betterend;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biomes;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;

import org.betterx.bclib.api.v2.WorldDataAPI;
import org.betterx.bclib.api.v2.levelgen.biomes.BiomeAPI;
import org.betterx.bclib.util.Logger;
import org.betterx.betterend.api.BetterEndPlugin;
import org.betterx.betterend.config.Configs;
import org.betterx.betterend.effects.EndPotions;
import org.betterx.betterend.integration.Integrations;
import org.betterx.betterend.recipe.*;
import org.betterx.betterend.registry.*;
import org.betterx.betterend.util.BonemealPlants;
import org.betterx.betterend.util.LootTableUtil;
import org.betterx.betterend.world.generator.GeneratorOptions;
import org.betterx.betterend.world.generator.TerrainGenerator;

public class BetterEnd implements ModInitializer {
    public static final String MOD_ID = "betterend";
    public static final Logger LOGGER = new Logger(MOD_ID);
    public static final boolean RUNS_FALL_FLYING_LIB = FabricLoader.getInstance()
                                                                   .getModContainer("fallflyinglib")
                                                                   .isPresent();

    @Override
    public void onInitialize() {
        WorldDataAPI.registerModCache(MOD_ID);
        EndPortals.loadPortals();
        EndSounds.register();
        EndBlockEntities.register();
        EndFeatures.register();
        EndEntities.register();
        EndBiomes.register();
        EndTags.register();
        EndEnchantments.register();
        EndPotions.register();
        CraftingRecipes.register();
        FurnaceRecipes.register();
        AlloyingRecipes.register();
        AnvilRecipes.register();
        SmithingRecipes.register();
        InfusionRecipes.register();
        EndStructures.register();
        BonemealPlants.init();
        GeneratorOptions.init();
        LootTableUtil.init();
        FabricLoader.getInstance()
                    .getEntrypoints("betterend", BetterEndPlugin.class)
                    .forEach(BetterEndPlugin::register);
        Integrations.init();
        Configs.saveConfigs();

        if (GeneratorOptions.useNewGenerator()) {
            org.betterx.bclib.api.v2.generator.GeneratorOptions.setFarEndBiomes(GeneratorOptions.getIslandDistBlock());
            org.betterx.bclib.api.v2.generator.GeneratorOptions.setEndLandFunction((pos) -> TerrainGenerator.isLand(pos.x,
                    pos.y));
        }

        BiomeAPI.registerEndBiomeModification((biomeID, biome) -> {
            if (!biomeID.equals(Biomes.THE_VOID.location())) {
                EndFeatures.addBiomeFeatures(biomeID, biome);
            }
        });

        BiomeAPI.onFinishingEndBiomeTags((biomeID, biome) -> {
            if (!biomeID.equals(Biomes.THE_VOID.location())) {
                EndStructures.addBiomeStructures(biomeID, biome);
            }
        });

    }

    public static ResourceLocation makeID(String path) {
        return new ResourceLocation(MOD_ID, path);
    }

}
