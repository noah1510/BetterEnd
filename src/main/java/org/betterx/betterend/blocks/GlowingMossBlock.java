package org.betterx.betterend.blocks;

import org.betterx.bclib.behaviours.BehaviourBuilders;
import org.betterx.bclib.behaviours.interfaces.BehaviourPlant;
import org.betterx.betterend.blocks.basis.EndPlantBlock;
import org.betterx.betterend.interfaces.survives.SurvivesOnMossOrMycelium;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.material.MapColor;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

public class GlowingMossBlock extends EndPlantBlock implements SurvivesOnMossOrMycelium, BehaviourPlant {
    public GlowingMossBlock(int light) {
        super(BehaviourBuilders
                .createGrass(MapColor.COLOR_LIGHT_BLUE)
                .lightLevel((bs) -> light)
                .ignitedByLava()
        );
    }


    @Environment(EnvType.CLIENT)
    public boolean hasEmissiveLighting(BlockGetter world, BlockPos pos) {
        return true;
    }

    @Environment(EnvType.CLIENT)
    public float getAmbientOcclusionLightLevel(BlockGetter world, BlockPos pos) {
        return 1F;
    }
}