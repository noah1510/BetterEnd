package org.betterx.betterend.world.features.terrain;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import com.google.common.collect.Lists;
import org.betterx.bclib.api.v2.levelgen.features.features.DefaultFeature;
import org.betterx.bclib.api.v2.tag.CommonBlockTags;
import org.betterx.bclib.sdf.SDF;
import org.betterx.bclib.sdf.operator.SDFDisplacement;
import org.betterx.bclib.sdf.operator.SDFRotation;
import org.betterx.bclib.sdf.primitive.SDFTorus;
import org.betterx.bclib.util.MHelper;
import org.betterx.betterend.noise.OpenSimplexNoise;

import java.util.List;
import java.util.function.Function;

public class ArchFeature extends DefaultFeature {
    private final Function<BlockPos, BlockState> surfaceFunction;
    private final Block block;

    public ArchFeature(Block block, Function<BlockPos, BlockState> surfaceFunction) {
        this.surfaceFunction = surfaceFunction;
        this.block = block;
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> featurePlaceContext) {
        final WorldGenLevel world = featurePlaceContext.level();
        BlockPos origin = featurePlaceContext.origin();
        RandomSource random = featurePlaceContext.random();

        BlockPos pos = getPosOnSurfaceWG(
                world,
                new BlockPos((origin.getX() & 0xFFFFFFF0) | 7, 0, (origin.getZ() & 0xFFFFFFF0) | 7)
        );
        if (!world.getBlockState(pos.below(5)).is(CommonBlockTags.GEN_END_STONES)) {
            return false;
        }

        float bigRadius = MHelper.randRange(10F, 20F, random);
        float smallRadius = MHelper.randRange(3F, 7F, random);
        if (smallRadius + bigRadius > 23) {
            smallRadius = 23 - bigRadius;
        }
        SDF arch = new SDFTorus().setBigRadius(bigRadius).setSmallRadius(smallRadius).setBlock(block);
        arch = new SDFRotation().setRotation(MHelper.randomHorizontal(random), (float) Math.PI * 0.5F).setSource(arch);

        final float smallRadiusF = smallRadius;
        OpenSimplexNoise noise = new OpenSimplexNoise(random.nextLong());
        arch = new SDFDisplacement().setFunction((vec) -> {
            return (float) (Math.abs(noise.eval(vec.x() * 0.1,
                    vec.y() * 0.1,
                    vec.z() * 0.1
            )) * 3F + Math.abs(noise.eval(
                    vec.x() * 0.3,
                    vec.y() * 0.3 + 100,
                    vec.z() * 0.3
            )) * 1.3F) - smallRadiusF * Math.abs(1 - vec.y() / bigRadius);
        }).setSource(arch);

        List<BlockPos> surface = Lists.newArrayList();
        arch.addPostProcess((info) -> {
            if (info.getStateUp().isAir()) {
                return surfaceFunction.apply(info.getPos());
            }
            return info.getState();
        });

        float side = (bigRadius + smallRadius + 3F) * 2;
        if (side > 47) {
            side = 47;
        }
        arch.fillArea(world, pos, AABB.ofSize(Vec3.atCenterOf(pos), side, side, side));

        return true;
    }
}
