package org.betterx.betterend.world.features;

import net.minecraft.core.BlockPos;
import net.minecraft.core.BlockPos.MutableBlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

import org.betterx.bclib.api.tag.CommonBlockTags;
import org.betterx.bclib.util.BlocksHelper;
import org.betterx.bclib.util.MHelper;
import org.betterx.bclib.world.features.DefaultFeature;
import org.betterx.betterend.util.GlobalState;

public abstract class ScatterFeature extends DefaultFeature {
    private final int radius;

    public ScatterFeature(int radius) {
        this.radius = radius;
    }

    public abstract boolean canGenerate(WorldGenLevel world,
                                        RandomSource random,
                                        BlockPos center,
                                        BlockPos blockPos,
                                        float radius);

    public abstract void generate(WorldGenLevel world, RandomSource random, BlockPos blockPos);

    protected BlockPos getCenterGround(WorldGenLevel world, BlockPos pos) {
        return getPosOnSurfaceWG(world, pos);
    }

    protected boolean canSpawn(WorldGenLevel world, BlockPos pos) {
        if (pos.getY() < 5) {
            return false;
        } else return world.getBlockState(pos.below()).is(CommonBlockTags.END_STONES);
    }

    protected boolean getGroundPlant(WorldGenLevel world, MutableBlockPos pos) {
        int down = BlocksHelper.downRay(world, pos, 16);
        if (down > Math.abs(getYOffset() * 2)) {
            return false;
        }
        pos.setY(pos.getY() - down);
        return true;
    }

    protected int getYOffset() {
        return 5;
    }

    protected int getChance() {
        return 1;
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> featureConfig) {
        final MutableBlockPos POS = GlobalState.stateForThread().POS;
        final RandomSource random = featureConfig.random();
        BlockPos center = featureConfig.origin();
        final WorldGenLevel world = featureConfig.level();
        center = getCenterGround(world, center);

        if (!canSpawn(world, center)) {
            return false;
        }

        float r = MHelper.randRange(radius * 0.5F, radius, random);
        int count = MHelper.floor(r * r * MHelper.randRange(1.5F, 3F, random));
        for (int i = 0; i < count; i++) {
            float pr = r * (float) Math.sqrt(random.nextFloat());
            float theta = random.nextFloat() * MHelper.PI2;
            float x = pr * (float) Math.cos(theta);
            float z = pr * (float) Math.sin(theta);

            POS.set(center.getX() + x, center.getY() + getYOffset(), center.getZ() + z);
            if (getGroundPlant(world, POS) && canGenerate(
                    world,
                    random,
                    center,
                    POS,
                    r
                                                         ) && (getChance() < 2 || random.nextInt(getChance()) == 0)) {
                generate(world, random, POS);
            }
        }

        return true;
    }
}
