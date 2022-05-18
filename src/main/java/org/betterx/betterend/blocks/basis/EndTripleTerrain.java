package org.betterx.betterend.blocks.basis;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.MaterialColor;

import org.betterx.bclib.blocks.TripleTerrainBlock;
import org.betterx.betterend.interfaces.PottableTerrain;

public class EndTripleTerrain extends TripleTerrainBlock implements PottableTerrain {
    public EndTripleTerrain(MaterialColor color) {
        super(Blocks.END_STONE, color);
    }
}
