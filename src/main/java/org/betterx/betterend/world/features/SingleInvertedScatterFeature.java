package org.betterx.betterend.world.features;

import org.betterx.bclib.blocks.BaseAttachedBlock;
import org.betterx.bclib.util.BlocksHelper;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

public class SingleInvertedScatterFeature extends InvertedScatterFeature {
    private final Block block;

    public SingleInvertedScatterFeature(Block block, int radius) {
        super(radius);
        this.block = block;
    }

    @Override
    public boolean canGenerate(
            WorldGenLevel world,
            RandomSource random,
            BlockPos center,
            BlockPos blockPos,
            float radius
    ) {
        if (!world.isEmptyBlock(blockPos)) {
            return false;
        }
        BlockState state = block.defaultBlockState();
        if (block instanceof BaseAttachedBlock) {
            state = state.setValue(BlockStateProperties.FACING, Direction.DOWN);
        }
        return state.canSurvive(world, blockPos);
    }

    @Override
    public void generate(WorldGenLevel world, RandomSource random, BlockPos blockPos) {
        BlockState state = block.defaultBlockState();
        if (block instanceof BaseAttachedBlock) {
            state = state.setValue(BlockStateProperties.FACING, Direction.DOWN);
        }
        BlocksHelper.setWithoutUpdate(world, blockPos, state);
    }
}
