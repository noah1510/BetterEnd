package org.betterx.betterend.blocks;

import org.betterx.bclib.behaviours.BehaviourBuilders;
import org.betterx.bclib.behaviours.interfaces.BehaviourPlant;
import org.betterx.betterend.blocks.basis.EndPlantBlock;
import org.betterx.betterend.interfaces.survives.SurvivesOnChorusNylium;

import net.minecraft.world.level.material.MapColor;

public class ChorusGrassBlock extends EndPlantBlock implements SurvivesOnChorusNylium, BehaviourPlant {
    public ChorusGrassBlock() {
        super(BehaviourBuilders.createGrass(MapColor.COLOR_PURPLE).replaceable());
    }
}
