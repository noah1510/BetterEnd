package org.betterx.betterend.blocks.basis;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;

import org.betterx.bclib.api.v2.levelgen.features.BCLFeature;
import org.betterx.bclib.blocks.FeatureSaplingBlock;
import org.betterx.betterend.interfaces.PottablePlant;

import java.util.function.Function;

public abstract class PottableFeatureSapling extends FeatureSaplingBlock implements PottablePlant {
    public PottableFeatureSapling(Function<BlockState, BCLFeature> featureSupplier) {
        super(featureSupplier);
    }

    public PottableFeatureSapling(int light, Function<BlockState, BCLFeature> featureSupplier) {
        super(light, featureSupplier);
    }
}
