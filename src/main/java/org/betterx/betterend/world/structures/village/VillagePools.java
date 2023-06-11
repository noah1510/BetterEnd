package org.betterx.betterend.world.structures.village;

import org.betterx.bclib.api.v2.levelgen.structures.StructurePools;
import org.betterx.betterend.BetterEnd;
import org.betterx.betterend.registry.EndProcessors;

import com.mojang.datafixers.util.Pair;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.Pools;
import net.minecraft.data.worldgen.ProcessorLists;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorList;

import com.google.common.collect.ImmutableList;

public class VillagePools {
    public static ResourceKey<StructureTemplatePool> TERMINATORS_KEY = StructurePools
            .createKey(BetterEnd.makeID("village/terminators"));
    public static ResourceKey<StructureTemplatePool> START = StructurePools
            .createKey(BetterEnd.makeID("village/center_piece"));
    public static ResourceKey<StructureTemplatePool> HOUSES_KEY = StructurePools
            .createKey(BetterEnd.makeID("village/houses"));
    public static ResourceKey<StructureTemplatePool> STREET_KEY = StructurePools
            .createKey(BetterEnd.makeID("village/streets"));
    public static ResourceKey<StructureTemplatePool> STREET_DECO_KEY = StructurePools
            .createKey(BetterEnd.makeID("village/street_decorations"));
    public static ResourceKey<StructureTemplatePool> DECORATIONS_KEY = StructurePools
            .createKey(BetterEnd.makeID("village/decorations"));

    public static void bootstrap(BootstapContext<StructureTemplatePool> ctx) {
        final HolderGetter<StructureProcessorList> processorGetter = ctx.lookup(Registries.PROCESSOR_LIST);
        final HolderGetter<PlacedFeature> featureGetter = ctx.lookup(Registries.PLACED_FEATURE);
        final HolderGetter<StructureTemplatePool> poolGetter = ctx.lookup(Registries.TEMPLATE_POOL);
        HolderGetter<Biome> biomeGetter = ctx.lookup(Registries.BIOME);

        final Holder.Reference<StructureTemplatePool> emptyPool = poolGetter.getOrThrow(Pools.EMPTY);
        final Holder.Reference<StructureTemplatePool> terminatorPool = poolGetter.getOrThrow(VillagePools.TERMINATORS_KEY);

        Holder.Reference<StructureProcessorList> mossify = processorGetter.getOrThrow(EndProcessors.WEATHERED_10_PERCENT);
        Holder.Reference<StructureProcessorList> crack = processorGetter.getOrThrow(EndProcessors.CRACK_20_PERCENT);
        Holder.Reference<StructureProcessorList> crackAndWeather = processorGetter.getOrThrow(EndProcessors.CRACK_AND_WEATHER);

        final Holder.Reference<StructureProcessorList> emptyProcessor = processorGetter.getOrThrow(ProcessorLists.EMPTY);
        ctx.register(VillagePools.TERMINATORS_KEY, new StructureTemplatePool(
                emptyPool,
                ImmutableList.of(Pair.of(
                        StructurePools.single(
                                BetterEnd.makeID("village/terminators/stree_terminator_01"),
                                emptyProcessor
                        ),
                        1
                )),
                StructureTemplatePool.Projection.TERRAIN_MATCHING
        ));
        ctx.register(VillagePools.START, new StructureTemplatePool(
                terminatorPool,
                ImmutableList.of(Pair.of(
                        StructurePools.single(BetterEnd.makeID("village/center/light_pyramid_01"), emptyProcessor),
                        1
                )),
                StructureTemplatePool.Projection.RIGID
        ));
        ctx.register(VillagePools.HOUSES_KEY, new StructureTemplatePool(
                terminatorPool,
                ImmutableList.of(Pair.of(StructurePoolElement.empty(), 5), Pair.of(
                        StructurePools.single(BetterEnd.makeID("village/houses/small_house_01"), emptyProcessor),
                        1
                ), Pair.of(
                        StructurePools.single(BetterEnd.makeID("village/houses/small_house_02"), emptyProcessor),
                        1
                ), Pair.of(
                        StructurePools.single(BetterEnd.makeID("village/houses/small_house_03"), emptyProcessor),
                        1
                ), Pair.of(
                        StructurePools.single(BetterEnd.makeID("village/houses/small_house_04"), emptyProcessor),
                        1
                ), Pair.of(
                        StructurePools.single(BetterEnd.makeID("village/houses/small_house_05"), emptyProcessor),
                        1
                ), Pair.of(
                        StructurePools.single(BetterEnd.makeID("village/houses/small_house_06"), emptyProcessor),
                        1
                ), Pair.of(
                        StructurePools.single(BetterEnd.makeID("village/houses/small_house_07"), emptyProcessor),
                        1
                ), Pair.of(
                        StructurePools.single(BetterEnd.makeID("village/houses/small_house_08"), emptyProcessor),
                        1
                ), Pair.of(
                        StructurePools.single(BetterEnd.makeID("village/houses/small_house_09"), emptyProcessor),
                        1
                ), Pair.of(
                        StructurePools.single(BetterEnd.makeID("village/houses/small_house_10"), emptyProcessor),
                        1
                ), Pair.of(
                        StructurePools.single(BetterEnd.makeID("village/houses/small_house_11"), emptyProcessor),
                        1
                ), Pair.of(
                        StructurePools.single(BetterEnd.makeID("village/houses/small_house_12"), emptyProcessor),
                        1
                ), Pair.of(
                        StructurePools.single(BetterEnd.makeID("village/houses/small_house_13"), emptyProcessor),
                        1
                ), Pair.of(
                        StructurePools.single(BetterEnd.makeID("village/houses/small_house_14"), emptyProcessor),
                        1
                ), Pair.of(
                        StructurePools.single(BetterEnd.makeID("village/houses/small_house_15"), emptyProcessor),
                        1
                ), Pair.of(
                        StructurePools.single(BetterEnd.makeID("village/houses/small_house_16"), emptyProcessor),
                        1
                ), Pair.of(
                        StructurePools.single(BetterEnd.makeID("village/houses/animal_pen_01"), emptyProcessor),
                        1
                )),
                StructureTemplatePool.Projection.RIGID
        ));
        ctx.register(VillagePools.STREET_KEY, new StructureTemplatePool(
                terminatorPool,
                ImmutableList.of(Pair.of(
                        StructurePools.single(BetterEnd.makeID("village/streets/street_01"), crackAndWeather),
                        6
                ), Pair.of(
                        StructurePools.single(BetterEnd.makeID("village/streets/street_02"), crackAndWeather),
                        4
                ), Pair.of(
                        StructurePools.single(BetterEnd.makeID("village/streets/curve_01"), crackAndWeather),
                        3
                ), Pair.of(
                        StructurePools.single(BetterEnd.makeID("village/streets/t_crossing_01"), crackAndWeather),
                        1
                ), Pair.of(
                        StructurePools.single(BetterEnd.makeID("village/streets/t_crossing_02"), crackAndWeather),
                        2
                )),
                StructureTemplatePool.Projection.TERRAIN_MATCHING
        ));
        ctx.register(VillagePools.STREET_DECO_KEY, new StructureTemplatePool(
                terminatorPool,
                ImmutableList.of(
                        Pair.of(StructurePoolElement.empty(), 5),
                        Pair.of(
                                StructurePools.single(
                                        BetterEnd.makeID("village/street_decoration/lamp_02"),
                                        emptyProcessor
                                ),
                                2
                        ),
                        Pair.of(
                                StructurePools.single(
                                        BetterEnd.makeID("village/street_decoration/lamp_05"),
                                        emptyProcessor
                                ),
                                1
                        )
                ),
                StructureTemplatePool.Projection.RIGID
        ));
        ctx.register(VillagePools.DECORATIONS_KEY, new StructureTemplatePool(
                terminatorPool,
                ImmutableList.of(),
                StructureTemplatePool.Projection.RIGID
        ));
    }
}
