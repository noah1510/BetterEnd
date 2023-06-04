package org.betterx.datagen.betterend;

import org.betterx.bclib.api.v3.datagen.TagDataProvider;
import org.betterx.betterend.BetterEnd;
import org.betterx.worlds.together.tag.v3.TagManager;

import net.minecraft.core.HolderLookup;
import net.minecraft.world.level.block.Block;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class EndBlockTagDataProvider extends TagDataProvider<Block> {

    public EndBlockTagDataProvider(
            FabricDataOutput output,
            CompletableFuture<HolderLookup.Provider> registriesFuture
    ) {
        super(TagManager.BLOCKS, List.of("minecraft", BetterEnd.MOD_ID, "c"), output, registriesFuture);
    }
}
