package org.betterx.betterend.blocks;

import org.betterx.bclib.behaviours.BehaviourBuilders;
import org.betterx.bclib.behaviours.interfaces.BehaviourWaterPlant;
import org.betterx.bclib.interfaces.SurvivesOnWater;
import org.betterx.betterend.blocks.basis.EndUnderwaterPlantBlock;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;

public class CharniaBlock extends EndUnderwaterPlantBlock implements BehaviourWaterPlant, SurvivesOnWater {
    public CharniaBlock() {
        super(
                BehaviourBuilders.createWaterPlant()
        );
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader world, BlockPos pos) {
        return canSupportCenter(world, pos.below(), Direction.UP) && world.getFluidState(pos).getType() == Fluids.WATER;
    }

    @Override
    public boolean isTerrain(BlockState state) {
        return SurvivesOnWater.super.isTerrain(state);
    }
}
