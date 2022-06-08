package org.betterx.betterend.registry;

import org.betterx.bclib.api.v2.poi.BCLPoiType;
import org.betterx.betterend.BetterEnd;
import org.betterx.betterend.blocks.RunedFlavolite;

import java.util.Set;

public class EndPoiTypes {
    public static final BCLPoiType ETERNAL_PORTAL_INACTIVE = BCLPoiType.register(
            BetterEnd.makeID("eternal_portal_inactive"),
            Set.of(EndBlocks.FLAVOLITE_RUNED_ETERNAL.defaultBlockState().setValue(RunedFlavolite.ACTIVATED, false)),
            0, 1);

    public static void register() {

    }
}
