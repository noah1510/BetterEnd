package org.betterx.betterend.blocks.basis;

import net.minecraft.world.level.block.state.BlockState;

import org.betterx.bclib.api.tag.CommonBlockTags;
import org.betterx.bclib.blocks.UnderwaterPlantBlock;

import java.util.function.Function;

public class EndUnderwaterPlantBlock extends UnderwaterPlantBlock {

    public EndUnderwaterPlantBlock() {
    }

    public EndUnderwaterPlantBlock(int light, Function<Properties, Properties> propMod) {
        super(light, propMod);
    }

    public EndUnderwaterPlantBlock(Properties settings) {
        super(settings);
    }

    @Override
    protected boolean isTerrain(BlockState state) {
        return state.is(CommonBlockTags.END_STONES);
    }
}
