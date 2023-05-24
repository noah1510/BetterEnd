package org.betterx.betterend.blocks.basis;

import org.betterx.bclib.blocks.BaseLeavesBlock;
import org.betterx.betterend.interfaces.PottablePlant;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.MapColor;

public class PottableLeavesBlock extends BaseLeavesBlock implements PottablePlant {

    public PottableLeavesBlock(Block sapling, MapColor color) {
        super(sapling, color);
    }

    public PottableLeavesBlock(Block sapling, MapColor color, int light) {
        super(sapling, color, light);
    }

    @Override
    public boolean canPlantOn(Block block) {
        if (sapling instanceof PottablePlant) {
            return ((PottablePlant) sapling).canPlantOn(block);
        }
        return true;
    }
}
