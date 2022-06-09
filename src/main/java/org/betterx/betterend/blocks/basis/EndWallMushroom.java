package org.betterx.betterend.blocks.basis;

import org.betterx.bclib.api.v2.tag.CommonBlockTags;
import org.betterx.bclib.blocks.WallMushroomBlock;

import net.minecraft.world.level.block.state.BlockState;

public class EndWallMushroom extends WallMushroomBlock {

    public EndWallMushroom(int light) {
        super(light);
    }

    @Override
    protected boolean isTerrain(BlockState state) {
        return state.is(CommonBlockTags.END_STONES);
    }
}
