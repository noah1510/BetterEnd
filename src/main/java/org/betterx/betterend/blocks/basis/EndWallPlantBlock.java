package org.betterx.betterend.blocks.basis;

import net.minecraft.world.level.block.state.BlockState;

import org.betterx.bclib.api.v2.tag.CommonBlockTags;
import org.betterx.bclib.blocks.BaseWallPlantBlock;

public class EndWallPlantBlock extends BaseWallPlantBlock {
    public EndWallPlantBlock() {
    }

    public EndWallPlantBlock(int light) {
        super(light);
    }

    public EndWallPlantBlock(Properties settings) {
        super(settings);
    }

    @Override
    protected boolean isTerrain(BlockState state) {
        return state.is(CommonBlockTags.END_STONES);
    }
}
