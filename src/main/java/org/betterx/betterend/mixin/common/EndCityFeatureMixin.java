package org.betterx.betterend.mixin.common;

import org.betterx.betterend.world.generator.GeneratorOptions;

import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.minecraft.world.level.levelgen.XoroshiroRandomSource;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.structures.EndCityStructure;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;

@Mixin(EndCityStructure.class)
public class EndCityFeatureMixin {
    @Inject(method = "findGenerationPoint", at = @At("HEAD"), cancellable = true)
    private void be_findGenerationPoint(
            Structure.GenerationContext context,
            CallbackInfoReturnable<Optional<Structure.GenerationStub>> info
    ) {
        final ChunkPos pos = context.chunkPos();
        WorldgenRandom chunkRandom = new WorldgenRandom(new XoroshiroRandomSource(pos.x, pos.z));

        if (GeneratorOptions.useNewGenerator()) {
            int chance = GeneratorOptions.getEndCityFailChance();
            if (chance > 0 && chunkRandom.nextInt(chance) != 0) {
                info.setReturnValue(Optional.empty());
                info.cancel();
            }
        }
    }
}
