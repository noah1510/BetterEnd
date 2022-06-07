package org.betterx.betterend.blocks.basis;

import net.minecraft.world.level.block.state.BlockState;

import org.betterx.bclib.api.v2.tag.CommonBlockTags;
import org.betterx.bclib.blocks.BasePlantWithAgeBlock;

import java.util.function.Function;

public abstract class EndPlantWithAgeBlock extends BasePlantWithAgeBlock {

    public EndPlantWithAgeBlock() {
    }

    public EndPlantWithAgeBlock(Function<Properties, Properties> propMod) {
        super(propMod);
    }

    public EndPlantWithAgeBlock(Properties settings) {
        super(settings);
    }

    @Override
    protected boolean isTerrain(BlockState state) {
        return state.is(CommonBlockTags.END_STONES);
    }
}
