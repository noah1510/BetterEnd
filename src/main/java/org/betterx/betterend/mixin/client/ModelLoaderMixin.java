package org.betterx.betterend.mixin.client;

import org.betterx.betterend.BetterEnd;
import org.betterx.betterend.world.generator.GeneratorOptions;

import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.resources.ResourceLocation;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(ModelBakery.class)
public abstract class ModelLoaderMixin {
    @ModifyVariable(method = "loadModel", ordinal = 2, at = @At(value = "INVOKE"))
    public ResourceLocation be_switchModel(ResourceLocation id) {
        if (GeneratorOptions.changeChorusPlant() && be_changeModel(id)) {
            String path = id.getPath().replace("chorus", "custom_chorus");
            id = BetterEnd.makeID(path);
        }
        return id;
    }

    private boolean be_changeModel(ResourceLocation id) {
        return id.getNamespace().equals("minecraft")
                && id.getPath().startsWith("blockstates/")
                && id.getPath().contains("chorus")
                && !id.getPath().contains("custom_");
    }
}
