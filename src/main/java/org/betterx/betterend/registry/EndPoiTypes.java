package org.betterx.betterend.registry;

import org.betterx.bclib.api.v2.poi.BCLPoiType;
import org.betterx.bclib.api.v2.poi.PoiRegistry;
import org.betterx.bclib.blocks.BlockProperties;
import org.betterx.betterend.BetterEnd;

import java.util.stream.Collectors;

public class EndPoiTypes {
    public static final BCLPoiType ETERNAL_PORTAL = PoiRegistry
            .register(
                    BetterEnd.makeID("eternal_portal"),
                    () -> BCLPoiType
                            .getBlockStates(EndBlocks.FLAVOLITE_RUNED_ETERNAL)
                            .stream().filter(state -> !state.getValue(BlockProperties.ACTIVE))
                            .collect(Collectors.toSet()),
                    0, 1
                     );

    private static void bootstrap() {
        //This basically ensures that our static structures are loaded and registered
    }

    public static void register() {
        PoiRegistry.registerForBootstrap(EndPoiTypes::bootstrap);
    }
}
