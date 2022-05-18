package org.betterx.betterend.blocks;

import net.minecraft.world.level.block.Blocks;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.registry.FuelRegistry;

import org.betterx.bclib.blocks.BaseBlock;

public class CharcoalBlock extends BaseBlock {
    public CharcoalBlock() {
        super(FabricBlockSettings.copyOf(Blocks.COAL_BLOCK));
        FuelRegistry.INSTANCE.add(this, 16000);
    }
}
