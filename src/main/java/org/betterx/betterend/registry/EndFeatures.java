package org.betterx.betterend.registry;

import org.betterx.bclib.api.v2.levelgen.biomes.BCLBiome;
import org.betterx.bclib.api.v2.levelgen.biomes.BCLBiomeBuilder;
import org.betterx.bclib.api.v2.levelgen.biomes.BiomeAPI;
import org.betterx.bclib.api.v2.levelgen.features.features.DefaultFeature;
import org.betterx.bclib.api.v3.levelgen.features.BCLFeature;
import org.betterx.bclib.api.v3.levelgen.features.BCLFeatureBuilder;
import org.betterx.bclib.util.JsonFactory;
import org.betterx.betterend.BetterEnd;
import org.betterx.betterend.complexmaterials.StoneMaterial;
import org.betterx.betterend.config.Configs;
import org.betterx.betterend.world.biome.cave.EndCaveBiome;
import org.betterx.betterend.world.biome.land.UmbraValleyBiome;
import org.betterx.betterend.world.features.*;
import org.betterx.betterend.world.features.bushes.*;
import org.betterx.betterend.world.features.terrain.*;
import org.betterx.betterend.world.features.terrain.caves.RoundCaveFeature;
import org.betterx.betterend.world.features.terrain.caves.TunelCaveFeature;
import org.betterx.betterend.world.features.trees.*;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.GenerationStep.Decoration;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import com.google.common.collect.Lists;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.InputStream;
import java.util.List;

public class EndFeatures {
    // Trees //
    public static final BCLFeature<MossyGlowshroomFeature, NoneFeatureConfiguration> MOSSY_GLOWSHROOM = registerVegetation(
            "mossy_glowshroom",
            new MossyGlowshroomFeature(),
            2
    );
    public static final BCLFeature<PythadendronTreeFeature, NoneFeatureConfiguration> PYTHADENDRON_TREE = registerVegetation(
            "pythadendron_tree",
            new PythadendronTreeFeature(),
            1
    );
    public static final BCLFeature<LacugroveFeature, NoneFeatureConfiguration> LACUGROVE = registerVegetation(
            "lacugrove",
            new LacugroveFeature(),
            4
    );
    public static final BCLFeature<DragonTreeFeature, NoneFeatureConfiguration> DRAGON_TREE = registerVegetation(
            "dragon_tree",
            new DragonTreeFeature(),
            2
    );
    public static final BCLFeature<TenaneaFeature, NoneFeatureConfiguration> TENANEA = registerVegetation(
            "tenanea",
            new TenaneaFeature(),
            2
    );
    public static final BCLFeature<HelixTreeFeature, NoneFeatureConfiguration> HELIX_TREE = registerVegetation(
            "helix_tree",
            new HelixTreeFeature(),
            1
    );
    public static final BCLFeature<UmbrellaTreeFeature, NoneFeatureConfiguration> UMBRELLA_TREE = registerVegetation(
            "umbrella_tree",
            new UmbrellaTreeFeature(),
            2
    );
    public static final BCLFeature<JellyshroomFeature, NoneFeatureConfiguration> JELLYSHROOM = registerVegetation(
            "jellyshroom",
            new JellyshroomFeature(),
            2
    );
    public static final BCLFeature<GiganticAmaranitaFeature, NoneFeatureConfiguration> GIGANTIC_AMARANITA = registerVegetation(
            "gigantic_amaranita",
            new GiganticAmaranitaFeature(),
            1
    );
    public static final BCLFeature<LucerniaFeature, NoneFeatureConfiguration> LUCERNIA = registerVegetation(
            "lucernia",
            new LucerniaFeature(),
            3
    );

    // Bushes //
    public static final BCLFeature<BushFeature, NoneFeatureConfiguration> PYTHADENDRON_BUSH = registerVegetation(
            "pythadendron_bush",
            new BushFeature(
                    EndBlocks.PYTHADENDRON_LEAVES,
                    EndBlocks.PYTHADENDRON.getBark()
            ),
            3
    );
    public static final BCLFeature<BushFeature, NoneFeatureConfiguration> DRAGON_TREE_BUSH = registerVegetation(
            "dragon_tree_bush",
            new BushFeature(
                    EndBlocks.DRAGON_TREE_LEAVES,
                    EndBlocks.DRAGON_TREE.getBark()
            ),
            5
    );
    public static final BCLFeature<TenaneaBushFeature, NoneFeatureConfiguration> TENANEA_BUSH = registerVegetation(
            "tenanea_bush",
            new TenaneaBushFeature(),
            6
    );
    public static final BCLFeature<Lumecorn, NoneFeatureConfiguration> LUMECORN = registerVegetation(
            "lumecorn",
            new Lumecorn(),
            5
    );
    public static final BCLFeature<LargeAmaranitaFeature, NoneFeatureConfiguration> LARGE_AMARANITA = registerVegetation(
            "large_amaranita",
            new LargeAmaranitaFeature(),
            5
    );
    public static final BCLFeature<BushWithOuterFeature, NoneFeatureConfiguration> LUCERNIA_BUSH = registerVegetation(
            "lucernia_bush",
            new BushWithOuterFeature(
                    EndBlocks.LUCERNIA_LEAVES,
                    EndBlocks.LUCERNIA_OUTER_LEAVES,
                    EndBlocks.LUCERNIA.getBark()
            ),
            10
    );
    public static final BCLFeature<BushWithOuterFeature, NoneFeatureConfiguration> LUCERNIA_BUSH_RARE = registerVegetation(
            "lucernia_bush_rare",
            new BushWithOuterFeature(
                    EndBlocks.LUCERNIA_LEAVES,
                    EndBlocks.LUCERNIA_OUTER_LEAVES,
                    EndBlocks.LUCERNIA.getBark()
            ),
            1
    );
    public static final BCLFeature<NeonCactusFeature, NoneFeatureConfiguration> NEON_CACTUS = registerVegetation(
            "neon_cactus",
            new NeonCactusFeature(),
            2
    );

    // Plants //
    public static final BCLFeature<DoublePlantFeature, NoneFeatureConfiguration> UMBRELLA_MOSS = registerVegetation(
            "umbrella_moss",
            new DoublePlantFeature(
                    EndBlocks.UMBRELLA_MOSS,
                    EndBlocks.UMBRELLA_MOSS_TALL,
                    5
            ),
            3
    );
    public static final BCLFeature<SinglePlantFeature, NoneFeatureConfiguration> CREEPING_MOSS = registerVegetation(
            "creeping_moss",
            new SinglePlantFeature(
                    EndBlocks.CREEPING_MOSS,
                    5
            ),
            3
    );
    public static final BCLFeature<BlueVineFeature, NoneFeatureConfiguration> BLUE_VINE = registerVegetation(
            "blue_vine",
            new BlueVineFeature(),
            1
    );
    public static final BCLFeature<SinglePlantFeature, NoneFeatureConfiguration> CHORUS_GRASS = registerVegetation(
            "chorus_grass",
            new SinglePlantFeature(EndBlocks.CHORUS_GRASS, 4),
            3
    );
    public static final BCLFeature<SinglePlantFeature, NoneFeatureConfiguration> CRYSTAL_GRASS = registerVegetation(
            "crystal_grass",
            new SinglePlantFeature(
                    EndBlocks.CRYSTAL_GRASS,
                    8,
                    false
            ),
            5
    );
    public static final BCLFeature<SinglePlantFeature, NoneFeatureConfiguration> SHADOW_PLANT = registerVegetation(
            "shadow_plant",
            new SinglePlantFeature(EndBlocks.SHADOW_PLANT, 6),
            5
    );
    public static final BCLFeature MURKWEED = registerVegetation(
            "murkweed",
            new SinglePlantFeature(EndBlocks.MURKWEED, 3),
            2
    );
    public static final BCLFeature NEEDLEGRASS = registerVegetation(
            "needlegrass",
            new SinglePlantFeature(EndBlocks.NEEDLEGRASS, 3),
            1
    );
    public static final BCLFeature SHADOW_BERRY = registerVegetation(
            "shadow_berry",
            new SinglePlantFeature(EndBlocks.SHADOW_BERRY, 2),
            1
    );
    public static final BCLFeature BUSHY_GRASS = registerVegetation(
            "bushy_grass",
            new SinglePlantFeature(
                    EndBlocks.BUSHY_GRASS,
                    8,
                    false
            ),
            10
    );
    public static final BCLFeature BUSHY_GRASS_WG = registerVegetation(
            "bushy_grass_wg",
            new SinglePlantFeature(EndBlocks.BUSHY_GRASS, 5),
            8
    );
    public static final BCLFeature AMBER_GRASS = registerVegetation(
            "amber_grass",
            new SinglePlantFeature(EndBlocks.AMBER_GRASS, 6),
            7
    );
    public static final BCLFeature LANCELEAF = registerVegetation("lanceleaf", new LanceleafFeature(), 2);
    public static final BCLFeature GLOW_PILLAR = registerVegetation("glow_pillar", new GlowPillarFeature(), 1);
    public static final BCLFeature TWISTED_UMBRELLA_MOSS = registerVegetation(
            "twisted_umbrella_moss",
            new DoublePlantFeature(
                    EndBlocks.TWISTED_UMBRELLA_MOSS,
                    EndBlocks.TWISTED_UMBRELLA_MOSS_TALL,
                    6
            ),
            3
    );
    public static final BCLFeature JUNGLE_GRASS = registerVegetation(
            "jungle_grass",
            new SinglePlantFeature(
                    EndBlocks.JUNGLE_GRASS,
                    7,
                    3
            ),
            6
    );
    public static final BCLFeature SMALL_JELLYSHROOM_FLOOR = registerVegetation(
            "small_jellyshroom_floor",
            new SinglePlantFeature(
                    EndBlocks.SMALL_JELLYSHROOM,
                    5,
                    5
            ),
            2
    );
    public static final BCLFeature BLOSSOM_BERRY = registerVegetation(
            "blossom_berry",
            new SinglePlantFeature(
                    EndBlocks.BLOSSOM_BERRY,
                    3,
                    3
            ),
            2
    );
    public static final BCLFeature BLOOMING_COOKSONIA = registerVegetation(
            "blooming_cooksonia",
            new SinglePlantFeature(
                    EndBlocks.BLOOMING_COOKSONIA,
                    5
            ),
            5
    );
    public static final BCLFeature SALTEAGO = registerVegetation(
            "salteago",
            new SinglePlantFeature(EndBlocks.SALTEAGO, 5),
            5
    );
    public static final BCLFeature VAIOLUSH_FERN = registerVegetation(
            "vaiolush_fern",
            new SinglePlantFeature(
                    EndBlocks.VAIOLUSH_FERN,
                    5
            ),
            5
    );
    public static final BCLFeature FRACTURN = registerVegetation(
            "fracturn",
            new SinglePlantFeature(EndBlocks.FRACTURN, 5),
            5
    );
    public static final BCLFeature UMBRELLA_MOSS_RARE = registerVegetation(
            "umbrella_moss_rare",
            new SinglePlantFeature(
                    EndBlocks.UMBRELLA_MOSS,
                    3
            ),
            2
    );
    public static final BCLFeature CREEPING_MOSS_RARE = registerVegetation(
            "creeping_moss_rare",
            new SinglePlantFeature(
                    EndBlocks.CREEPING_MOSS,
                    3
            ),
            2
    );
    public static final BCLFeature TWISTED_UMBRELLA_MOSS_RARE = registerVegetation(
            "twisted_umbrella_moss_rare",
            new SinglePlantFeature(
                    EndBlocks.TWISTED_UMBRELLA_MOSS,
                    3
            ),
            2
    );
    public static final BCLFeature ORANGO = registerVegetation(
            "orango",
            new SinglePlantFeature(EndBlocks.ORANGO, 5),
            6
    );
    public static final BCLFeature AERIDIUM = registerVegetation(
            "aeridium",
            new SinglePlantFeature(EndBlocks.AERIDIUM, 5, 4),
            5
    );
    public static final BCLFeature LUTEBUS = registerVegetation(
            "lutebus",
            new SinglePlantFeature(EndBlocks.LUTEBUS, 5, 2),
            5
    );
    public static final BCLFeature LAMELLARIUM = registerVegetation(
            "lamellarium",
            new SinglePlantFeature(EndBlocks.LAMELLARIUM, 5),
            6
    );
    public static final BCLFeature SMALL_AMARANITA = registerVegetation(
            "small_amaranita",
            new SinglePlantFeature(
                    EndBlocks.SMALL_AMARANITA_MUSHROOM,
                    5,
                    5
            ),
            4
    );
    public static final BCLFeature GLOBULAGUS = registerVegetation(
            "globulagus",
            new SinglePlantFeature(EndBlocks.GLOBULAGUS, 5, 3),
            6
    );
    public static final BCLFeature CLAWFERN = registerVegetation(
            "clawfern",
            new SinglePlantFeature(EndBlocks.CLAWFERN, 5, 4),
            5
    );
    public static final BCLFeature BOLUX_MUSHROOM = registerVegetation(
            "bolux_mushroom",
            new SinglePlantFeature(
                    EndBlocks.BOLUX_MUSHROOM,
                    5,
                    5
            ),
            2
    );
    public static final BCLFeature CHORUS_MUSHROOM = registerVegetation(
            "chorus_mushroom",
            new SinglePlantFeature(
                    EndBlocks.CHORUS_MUSHROOM,
                    3,
                    5
            ),
            1
    );
    public static final BCLFeature AMBER_ROOT = registerVegetation(
            "amber_root",
            new SinglePlantFeature(EndBlocks.AMBER_ROOT, 5, 5),
            1
    );
    //public static final BCLFeature PEARLBERRY = redisterVegetation("pearlberry", new SinglePlantFeature(EndBlocks.PEARLBERRY, 5, 5), 1);
    public static final BCLFeature INFLEXIA = registerVegetation(
            "inflexia",
            new SinglePlantFeature(
                    EndBlocks.INFLEXIA,
                    7,
                    false,
                    3
            ),
            16
    );
    public static final BCLFeature FLAMMALIX = registerVegetation(
            "flammalix",
            new SinglePlantFeature(
                    EndBlocks.FLAMMALIX,
                    3,
                    false,
                    7
            ),
            5
    );

    // Vines //
    public static final BCLFeature DENSE_VINE = registerVegetation(
            "dense_vine",
            new VineFeature(EndBlocks.DENSE_VINE, 24),
            3
    );
    public static final BCLFeature TWISTED_VINE = registerVegetation(
            "twisted_vine",
            new VineFeature(EndBlocks.TWISTED_VINE, 24),
            1
    );
    public static final BCLFeature BULB_VINE = registerVegetation(
            "bulb_vine",
            new VineFeature(EndBlocks.BULB_VINE, 24),
            3
    );
    public static final BCLFeature JUNGLE_VINE = registerVegetation(
            "jungle_vine",
            new VineFeature(EndBlocks.JUNGLE_VINE, 24),
            5
    );

    // Ceil plants
    public static final BCLFeature SMALL_JELLYSHROOM_CEIL = registerVegetation(
            "small_jellyshroom_ceil",
            new SingleInvertedScatterFeature(
                    EndBlocks.SMALL_JELLYSHROOM,
                    8
            ),
            8
    );

    // Wall Plants //
    public static final BCLFeature PURPLE_POLYPORE = registerVegetation(
            "purple_polypore",
            new WallPlantOnLogFeature(
                    EndBlocks.PURPLE_POLYPORE,
                    3
            ),
            5
    );
    public static final BCLFeature AURANT_POLYPORE = registerVegetation(
            "aurant_polypore",
            new WallPlantOnLogFeature(
                    EndBlocks.AURANT_POLYPORE,
                    3
            ),
            5
    );
    public static final BCLFeature TAIL_MOSS = registerVegetation(
            "tail_moss",
            new WallPlantFeature(EndBlocks.TAIL_MOSS, 3),
            15
    );
    public static final BCLFeature CYAN_MOSS = registerVegetation(
            "cyan_moss",
            new WallPlantFeature(EndBlocks.CYAN_MOSS, 3),
            15
    );
    public static final BCLFeature TAIL_MOSS_WOOD = registerVegetation(
            "tail_moss_wood",
            new WallPlantOnLogFeature(
                    EndBlocks.TAIL_MOSS,
                    4
            ),
            25
    );
    public static final BCLFeature CYAN_MOSS_WOOD = registerVegetation(
            "cyan_moss_wood",
            new WallPlantOnLogFeature(
                    EndBlocks.CYAN_MOSS,
                    4
            ),
            25
    );
    public static final BCLFeature TWISTED_MOSS = registerVegetation(
            "twisted_moss",
            new WallPlantFeature(EndBlocks.TWISTED_MOSS, 6),
            15
    );
    public static final BCLFeature TWISTED_MOSS_WOOD = registerVegetation(
            "twisted_moss_wood",
            new WallPlantOnLogFeature(
                    EndBlocks.TWISTED_MOSS,
                    6
            ),
            25
    );
    public static final BCLFeature BULB_MOSS = registerVegetation(
            "bulb_moss",
            new WallPlantFeature(EndBlocks.BULB_MOSS, 6),
            1
    );
    public static final BCLFeature BULB_MOSS_WOOD = registerVegetation(
            "bulb_moss_wood",
            new WallPlantOnLogFeature(
                    EndBlocks.BULB_MOSS,
                    6
            ),
            15
    );
    public static final BCLFeature SMALL_JELLYSHROOM_WALL = registerVegetation(
            "small_jellyshroom_wall",
            new WallPlantFeature(
                    EndBlocks.SMALL_JELLYSHROOM,
                    4
            ),
            4
    );
    public static final BCLFeature SMALL_JELLYSHROOM_WOOD = registerVegetation(
            "small_jellyshroom_wood",
            new WallPlantOnLogFeature(
                    EndBlocks.SMALL_JELLYSHROOM,
                    4
            ),
            8
    );
    public static final BCLFeature JUNGLE_FERN_WOOD = registerVegetation(
            "jungle_fern_wood",
            new WallPlantOnLogFeature(
                    EndBlocks.JUNGLE_FERN,
                    3
            ),
            12
    );
    public static final BCLFeature RUSCUS = registerVegetation("ruscus", new WallPlantFeature(EndBlocks.RUSCUS, 6), 10);
    public static final BCLFeature RUSCUS_WOOD = registerVegetation(
            "ruscus_wood",
            new WallPlantOnLogFeature(EndBlocks.RUSCUS, 6),
            10
    );

    // Sky plants
    public static final BCLFeature FILALUX = registerVegetation("filalux", new FilaluxFeature(), 1);

    // Water //
    public static final BCLFeature BUBBLE_CORAL = registerVegetation(
            "bubble_coral",
            new UnderwaterPlantFeature(
                    EndBlocks.BUBBLE_CORAL,
                    6
            ),
            10
    );
    public static final BCLFeature BUBBLE_CORAL_RARE = registerVegetation(
            "bubble_coral_rare",
            new UnderwaterPlantFeature(
                    EndBlocks.BUBBLE_CORAL,
                    3
            ),
            4
    );
    public static final BCLFeature END_LILY = registerVegetation("end_lily", new EndLilyFeature(6), 10);
    public static final BCLFeature END_LILY_RARE = registerVegetation("end_lily_rare", new EndLilyFeature(3), 4);
    public static final BCLFeature END_LOTUS = registerVegetation("end_lotus", new EndLotusFeature(7), 5);
    public static final BCLFeature END_LOTUS_LEAF = registerVegetation(
            "end_lotus_leaf",
            new EndLotusLeafFeature(20),
            25
    );
    public static final BCLFeature HYDRALUX = registerVegetation("hydralux", new HydraluxFeature(5), 5);
    public static final BCLFeature POND_ANEMONE = registerVegetation(
            "pond_anemone",
            new UnderwaterPlantFeature(
                    EndBlocks.POND_ANEMONE,
                    6
            ),
            10
    );

    public static final BCLFeature CHARNIA_RED = registerVegetation(
            "charnia_red",
            new CharniaFeature(EndBlocks.CHARNIA_RED),
            10
    );
    public static final BCLFeature CHARNIA_PURPLE = registerVegetation(
            "charnia_purple",
            new CharniaFeature(EndBlocks.CHARNIA_PURPLE),
            10
    );
    public static final BCLFeature CHARNIA_CYAN = registerVegetation(
            "charnia_cyan",
            new CharniaFeature(EndBlocks.CHARNIA_CYAN),
            10
    );
    public static final BCLFeature CHARNIA_LIGHT_BLUE = registerVegetation(
            "charnia_light_blue",
            new CharniaFeature(EndBlocks.CHARNIA_LIGHT_BLUE),
            10
    );
    public static final BCLFeature CHARNIA_ORANGE = registerVegetation(
            "charnia_orange",
            new CharniaFeature(EndBlocks.CHARNIA_ORANGE),
            10
    );
    public static final BCLFeature CHARNIA_GREEN = registerVegetation(
            "charnia_green",
            new CharniaFeature(EndBlocks.CHARNIA_GREEN),
            10
    );
    public static final BCLFeature MENGER_SPONGE = registerVegetation("menger_sponge", new MengerSpongeFeature(5), 1);
    public static final BCLFeature CHARNIA_RED_RARE = registerVegetation(
            "charnia_red_rare",
            new CharniaFeature(EndBlocks.CHARNIA_RED),
            2
    );
    public static final BCLFeature<BiomeIslandFeature, NoneFeatureConfiguration> BIOME_ISLAND = BCLFeatureBuilder
            .start(
                    BetterEnd.makeID("overworld_island"),
                    new BiomeIslandFeature()
            )
            .buildAndRegister()
            .place()
            .decoration(Decoration.RAW_GENERATION)
            .buildAndRegister();
    public static final BCLFeature FLAMAEA = registerVegetation(
            "flamaea",
            new SinglePlantFeature(EndBlocks.FLAMAEA, 12, false, 5),
            20
    );

    // Terrain //
    public static final BCLFeature END_LAKE = registerLake("end_lake", new EndLakeFeature(), 4);
    public static final BCLFeature END_LAKE_NORMAL = registerLake("end_lake_normal", new EndLakeFeature(), 20);
    public static final BCLFeature END_LAKE_RARE = registerLake("end_lake_rare", new EndLakeFeature(), 40);
    public static final BCLFeature DESERT_LAKE = registerLake("desert_lake", new DesertLakeFeature(), 8);
    public static final BCLFeature ROUND_CAVE = registerRawGen("round_cave", new RoundCaveFeature(), 2);
    public static final BCLFeature SPIRE = registerRawGen("spire", new SpireFeature(), 4);
    public static final BCLFeature FLOATING_SPIRE = registerRawGen("floating_spire", new FloatingSpireFeature(), 8);
    public static final BCLFeature GEYSER = registerRawGen("geyser", new GeyserFeature(), 8);
    public static final BCLFeature SULPHURIC_LAKE = registerLake("sulphuric_lake", new SulphuricLakeFeature(), 8);
    public static final BCLFeature SULPHURIC_CAVE = BCLFeatureBuilder
            .start(
                    BetterEnd.makeID("sulphuric_cave"),
                    new SulphuricCaveFeature()
            )
            .buildAndRegister()
            .place()
            .decoration(Decoration.RAW_GENERATION)
            .count(2)
            .squarePlacement()
            .onlyInBiome()
            .buildAndRegister();
    public static final BCLFeature ICE_STAR = registerRawGen("ice_star", new IceStarFeature(5, 15, 10, 25), 15);
    public static final BCLFeature ICE_STAR_SMALL = registerRawGen(
            "ice_star_small",
            new IceStarFeature(3, 5, 7, 12),
            8
    );
    public static final BCLFeature SURFACE_VENT = registerChanced("surface_vent", new SurfaceVentFeature(), 4);
    public static final BCLFeature SULPHUR_HILL = registerChanced("sulphur_hill", new SulphurHillFeature(), 8);
    public static final BCLFeature OBSIDIAN_PILLAR_BASEMENT = registerChanced(
            "obsidian_pillar_basement",
            new ObsidianPillarBasementFeature(),
            8
    );
    public static final BCLFeature OBSIDIAN_BOULDER = registerChanced(
            "obsidian_boulder",
            new ObsidianBoulderFeature(),
            10
    );
    public static final BCLFeature FALLEN_PILLAR = registerChanced("fallen_pillar", new FallenPillarFeature(), 20);
    public static final BCLFeature TUNEL_CAVE = BCLFeatureBuilder
            .start(
                    BetterEnd.makeID("tunel_cave"),
                    new TunelCaveFeature()
            ).buildAndRegister()
            .place()
            .decoration(Decoration.RAW_GENERATION)
            .count(1)
            .onlyInBiome()
            .buildAndRegister();
    public static final BCLFeature UMBRALITH_ARCH = registerChanced("umbralith_arch", new ArchFeature(
            EndBlocks.UMBRALITH.stone,
            pos -> UmbraValleyBiome.getSurface(pos.getX(), pos.getZ()).defaultBlockState()
    ), 10);
    public static final BCLFeature THIN_UMBRALITH_ARCH = registerChanced(
            "thin_umbralith_arch",
            new ThinArchFeature(EndBlocks.UMBRALITH.stone),
            15
    );

    // Ores //
    public static final BCLFeature THALLASIUM_ORE = registerOre("thallasium_ore", EndBlocks.THALLASIUM.ore, 24, 8);
    public static final BCLFeature ENDER_ORE = registerOre("ender_ore", EndBlocks.ENDER_ORE, 12, 4);
    public static final BCLFeature AMBER_ORE = registerOre("amber_ore", EndBlocks.AMBER_ORE, 60, 6);
    public static final BCLFeature DRAGON_BONE_BLOCK_ORE = registerOre(
            "dragon_bone_ore",
            EndBlocks.DRAGON_BONE_BLOCK,
            24,
            8
    );
    public static final BCLFeature VIOLECITE_LAYER = registerLayer(
            "violecite_layer",
            EndBlocks.VIOLECITE,
            15,
            16,
            128,
            8
    );
    public static final BCLFeature FLAVOLITE_LAYER = registerLayer(
            "flavolite_layer",
            EndBlocks.FLAVOLITE,
            12,
            16,
            128,
            6
    );

    // Buildings
    public static final BCLFeature CRASHED_SHIP = registerChanced("crashed_ship", new CrashedShipFeature(), 500);

    // Mobs
    public static final BCLFeature SILK_MOTH_NEST = registerChanced("silk_moth_nest", new SilkMothNestFeature(), 2);

    // Caves
    public static final DefaultFeature SMARAGDANT_CRYSTAL = new SmaragdantCrystalFeature();
    public static final DefaultFeature SMARAGDANT_CRYSTAL_SHARD = new SingleBlockFeature(EndBlocks.SMARAGDANT_CRYSTAL_SHARD);
    public static final DefaultFeature BIG_AURORA_CRYSTAL = new BigAuroraCrystalFeature();
    public static final DefaultFeature CAVE_BUSH = new BushFeature(EndBlocks.CAVE_BUSH, EndBlocks.CAVE_BUSH);
    public static final DefaultFeature CAVE_GRASS = new SingleBlockFeature(EndBlocks.CAVE_GRASS);
    public static final DefaultFeature RUBINEA = new VineFeature(EndBlocks.RUBINEA, 8);
    public static final DefaultFeature MAGNULA = new VineFeature(EndBlocks.MAGNULA, 8);
    public static final DefaultFeature END_STONE_STALACTITE = new StalactiteFeature(
            true,
            EndBlocks.END_STONE_STALACTITE,
            Blocks.END_STONE
    );
    public static final DefaultFeature END_STONE_STALAGMITE = new StalactiteFeature(
            false,
            EndBlocks.END_STONE_STALACTITE,
            Blocks.END_STONE
    );
    public static final DefaultFeature END_STONE_STALACTITE_CAVEMOSS = new StalactiteFeature(
            true,
            EndBlocks.END_STONE_STALACTITE_CAVEMOSS,
            Blocks.END_STONE,
            EndBlocks.CAVE_MOSS
    );
    public static final DefaultFeature END_STONE_STALAGMITE_CAVEMOSS = new StalactiteFeature(
            false,
            EndBlocks.END_STONE_STALACTITE_CAVEMOSS,
            EndBlocks.CAVE_MOSS
    );
    public static final DefaultFeature CAVE_PUMPKIN = new CavePumpkinFeature();

    private static <F extends Feature<NoneFeatureConfiguration>> BCLFeature<F, NoneFeatureConfiguration> registerVegetation(
            String name,
            F feature,
            int density
    ) {
        ResourceLocation id = BetterEnd.makeID(name);
        return BCLFeatureBuilder.start(id, feature)
                                .buildAndRegister()
                                .place()
                                .countMax(density)
                                .onlyInBiome()
                                .buildAndRegister();
    }

    private static BCLFeature registerRawGen(String name, Feature<NoneFeatureConfiguration> feature, int chance) {
        return BCLFeatureBuilder
                .start(BetterEnd.makeID(name), feature)
                .buildAndRegister()
                .place()
                .decoration(Decoration.RAW_GENERATION)
                .onceEvery(chance)
                .squarePlacement()
                .onlyInBiome()
                .buildAndRegister();
    }

    private static BCLFeature registerLake(String name, Feature<NoneFeatureConfiguration> feature, int chance) {
        return BCLFeatureBuilder
                .start(BetterEnd.makeID(name), feature)
                .buildAndRegister()
                .place()
                .decoration(Decoration.LAKES)
                .onceEvery(chance)
                .squarePlacement()
                .onlyInBiome()
                .buildAndRegister();
    }

    private static BCLFeature registerChanced(String name, Feature<NoneFeatureConfiguration> feature, int chance) {
        return
                BCLFeatureBuilder
                        .start(BetterEnd.makeID(name), feature)
                        .buildAndRegister()
                        .place()
                        .decoration(Decoration.SURFACE_STRUCTURES)
                        .onceEvery(chance)
                        .squarePlacement()
                        .onlyInBiome()
                        .buildAndRegister();
    }

    private static BCLFeature registerOre(String name, Block blockOre, int veins, int veinSize) {
        return BCLFeatureBuilder
                .startOre(BetterEnd.makeID(name))
                .add(Blocks.END_STONE, blockOre)
                .veinSize(veinSize)
                .discardChanceOnAirExposure(0)
                .buildAndRegister()
                .place()
                .decoration(Decoration.UNDERGROUND_ORES)
                .count(veins)
                .randomHeight8FromFloorCeil()
                .squarePlacement()
                .onlyInBiome()
                .buildAndRegister();
    }

    private static BCLFeature registerLayer(String name, Block block, float radius, int minY, int maxY, int count) {
        OreLayerFeature layer = new OreLayerFeature(block.defaultBlockState(), radius, minY, maxY);
        return BCLFeatureBuilder
                .start(BetterEnd.makeID(name), layer)
                .buildAndRegister()
                .place()
                .decoration(GenerationStep.Decoration.UNDERGROUND_ORES)
                .modifier(CountPlacement.of(count))
                .buildAndRegister();
    }

    private static BCLFeature registerLayer(
            String name,
            StoneMaterial material,
            float radius,
            int minY,
            int maxY,
            int count
    ) {
        return registerLayer(name, material.stone, radius, minY, maxY, count);
    }

    public static void addBiomeFeatures(ResourceLocation id, Holder<Biome> biome) {
        BiomeAPI.addBiomeFeature(biome, FLAVOLITE_LAYER);
        BiomeAPI.addBiomeFeature(biome, THALLASIUM_ORE);
        BiomeAPI.addBiomeFeature(biome, ENDER_ORE);
        BiomeAPI.addBiomeFeature(biome, CRASHED_SHIP);

        BCLBiome bclbiome = BiomeAPI.getBiome(id);
        BCLFeature feature = getBiomeStructures(bclbiome);
        if (feature != null) {
            BiomeAPI.addBiomeFeature(biome, feature);
        }

        if (id.getNamespace().equals(BetterEnd.MOD_ID)) {
            return;
        }

        boolean hasCaves = bclbiome.getCustomData("has_caves", true) && !(bclbiome instanceof EndCaveBiome);
        //TODO: 1.19 Test Cave generation
        if (hasCaves && !BiomeAPI.wasRegisteredAsEndVoidBiome(id) /*!BiomeAPI.END_VOID_BIOME_PICKER.containsImmutable(id)*/) {
            if (Configs.BIOME_CONFIG.getBoolean(id, "hasCaves", true)) {
                BiomeAPI.addBiomeFeature(biome, ROUND_CAVE);
                BiomeAPI.addBiomeFeature(biome, TUNEL_CAVE);
            }
        }
    }

    private static BCLFeature getBiomeStructures(BCLBiome biome) {
        String ns = biome.getID().getNamespace();
        String nm = biome.getID().getPath();
        ResourceLocation id = new ResourceLocation(ns, nm + "_structures");

        if (BuiltinRegistries.PLACED_FEATURE.containsKey(id)) {
            PlacedFeature placed = BuiltinRegistries.PLACED_FEATURE.get(id);
            Feature<NoneFeatureConfiguration> feature = (Feature<NoneFeatureConfiguration>) Registry.FEATURE.get(id);
            return BCLFeatureBuilder
                    .start(id, feature)
                    .configuration((NoneFeatureConfiguration) placed.feature().value().config())
                    .buildAndRegister()
                    .place()
                    .decoration(Decoration.SURFACE_STRUCTURES)
                    .modifier(placed.placement())
                    .buildAndRegister();
        }

        String path = "/data/" + ns + "/structures/biome/" + nm + "/";
        InputStream inputstream = EndFeatures.class.getResourceAsStream(path + "structures.json");
        if (inputstream != null) {
            JsonObject obj = JsonFactory.getJsonObject(inputstream);
            JsonArray structures = obj.getAsJsonArray("structures");
            if (structures != null) {
                List<ListFeature.StructureInfo> list = Lists.newArrayList();
                structures.forEach((entry) -> {
                    JsonObject e = entry.getAsJsonObject();
                    String structure = path + e.get("nbt").getAsString() + ".nbt";
                    NBTFeature.TerrainMerge terrainMerge = NBTFeature.TerrainMerge.getFromString(e
                            .get("terrainMerge")
                            .getAsString());
                    int offsetY = e.get("offsetY").getAsInt();
                    list.add(new ListFeature.StructureInfo(structure, offsetY, terrainMerge));
                });
                if (!list.isEmpty()) {
                    return BCLFeatureBuilder.start(
                                                    new ResourceLocation(ns, nm + "_structures"),
                                                    new BuildingListFeature(list, Blocks.END_STONE.defaultBlockState())
                                            )
                                            .buildAndRegister()
                                            .place()
                                            .decoration(Decoration.SURFACE_STRUCTURES)
                                            .onceEvery(10)
                                            .squarePlacement()
                                            .onlyInBiome()
                                            .buildAndRegister();
                }
            }
        }
        return null;
    }

    public static BCLBiomeBuilder addDefaultFeatures(BCLBiomeBuilder builder, boolean hasCaves) {
        builder.feature(FLAVOLITE_LAYER);
        builder.feature(THALLASIUM_ORE);
        builder.feature(ENDER_ORE);
        builder.feature(CRASHED_SHIP);

        if (hasCaves) {
            builder.feature(ROUND_CAVE);
            builder.feature(TUNEL_CAVE);
        }

        return builder;
    }

    public static void register() {
    }
}
