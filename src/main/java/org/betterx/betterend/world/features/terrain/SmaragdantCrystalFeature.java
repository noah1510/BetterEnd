package org.betterx.betterend.world.features.terrain;

import org.betterx.bclib.api.v2.levelgen.features.features.DefaultFeature;
import org.betterx.bclib.api.v2.tag.CommonBlockTags;
import org.betterx.bclib.util.BlocksHelper;
import org.betterx.bclib.util.MHelper;
import org.betterx.betterend.registry.EndBlocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.BlockPos.MutableBlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class SmaragdantCrystalFeature extends DefaultFeature {
    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> featureConfig) {
        final RandomSource random = featureConfig.random();
        final BlockPos pos = featureConfig.origin();
        final WorldGenLevel world = featureConfig.level();
        if (!world.getBlockState(pos.below()).is(CommonBlockTags.GEN_END_STONES)) {
            return false;
        }

        MutableBlockPos mut = new MutableBlockPos();
        int count = MHelper.randRange(15, 30, random);
        BlockState crystal = EndBlocks.SMARAGDANT_CRYSTAL.defaultBlockState();
        BlockState shard = EndBlocks.SMARAGDANT_CRYSTAL_SHARD.defaultBlockState();
        for (int i = 0; i < count; i++) {
            mut.set(pos)
               .move(MHelper.floor(random.nextGaussian() * 2 + 0.5), 5, MHelper.floor(random.nextGaussian() * 2 + 0.5));
            int dist = MHelper.floor(1.5F - MHelper.length(
                    mut.getX() - pos.getX(),
                    mut.getZ() - pos.getZ()
            )) + random.nextInt(3);
            if (dist > 0) {
                BlockState state = world.getBlockState(mut);
                for (int n = 0; n < 10 && state.isAir(); n++) {
                    mut.setY(mut.getY() - 1);
                    state = world.getBlockState(mut);
                }
                if (state.is(CommonBlockTags.GEN_END_STONES) && !world.getBlockState(mut.above())
                                                                      .is(crystal.getBlock())) {
                    for (int j = 0; j <= dist; j++) {
                        BlocksHelper.setWithoutUpdate(world, mut, crystal);
                        mut.setY(mut.getY() + 1);
                    }
                    boolean waterlogged = !world.getFluidState(mut).isEmpty();
                    BlocksHelper.setWithoutUpdate(
                            world,
                            mut,
                            shard.setValue(BlockStateProperties.WATERLOGGED, waterlogged)
                    );
                }
            }
        }

        return true;
    }
}
