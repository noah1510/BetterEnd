package ru.betterend.mixin.common;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import ru.betterend.world.generator.GeneratorOptions;

@Mixin(Level.class)
public class LevelMixin {

    @Inject(method = "getSharedSpawnPos", at = @At("HEAD"), cancellable = true)
    private void be_getSharedSpawnPos(CallbackInfoReturnable<BlockPos> info) {
        if (GeneratorOptions.changeSpawn()) {
            if (ServerLevel.class.cast(this).dimension() == Level.END) {
                BlockPos pos = GeneratorOptions.getSpawn();
                info.setReturnValue(pos);
            }
        }
    }
}
