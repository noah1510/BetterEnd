package org.betterx.betterend.registry;

import org.betterx.bclib.api.v3.datagen.ProcessorHelper;
import org.betterx.betterend.BetterEnd;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorList;

public class EndProcessors {
    public static final ResourceKey<StructureProcessorList> WEATHERED_10_PERCENT =
            ProcessorHelper.createKey(BetterEnd.makeID("weather_10_percent"));
    public static final ResourceKey<StructureProcessorList> CRACK_20_PERCENT =
            ProcessorHelper.createKey(BetterEnd.makeID("crack_20_percent"));
    public static final ResourceKey<StructureProcessorList> CRACK_AND_WEATHER =
            ProcessorHelper.createKey(BetterEnd.makeID("crack_and_weather"));
}
