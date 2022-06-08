package org.betterx.betterend.mixin.common;

import org.betterx.bclib.api.v2.levelgen.biomes.BiomeAPI;
import org.betterx.betterend.BetterEnd;
import org.betterx.betterend.interfaces.BETargetChecker;
import org.betterx.betterend.registry.EndBlocks;
import org.betterx.betterend.world.generator.GeneratorOptions;
import org.betterx.betterend.world.generator.TerrainGenerator;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.progress.ChunkProgressListener;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import net.minecraft.world.level.storage.LevelStorageSource.LevelStorageAccess;
import net.minecraft.world.level.storage.ServerLevelData;
import net.minecraft.world.level.storage.WritableLevelData;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.function.Supplier;

@Mixin(ServerLevel.class)
public abstract class ServerLevelMixin extends Level {
    protected ServerLevelMixin(
            WritableLevelData writableLevelData,
            ResourceKey<Level> resourceKey,
            Holder<DimensionType> holder,
            Supplier<ProfilerFiller> supplier,
            boolean bl,
            boolean bl2,
            long l,
            int i
    ) {
        super(writableLevelData, resourceKey, holder, supplier, bl, bl2, l, i);
    }

    private final static List<ResourceKey<DimensionType>> BE_TEST_DIMENSIONS = List.of(
            BuiltinDimensionTypes.OVERWORLD,
            BuiltinDimensionTypes.OVERWORLD_CAVES,
            BuiltinDimensionTypes.NETHER
    );

    @ModifyArg(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/core/Holder;is(Lnet/minecraft/resources/ResourceKey;)Z"))
    ResourceKey<DimensionType> be_dragonFight(ResourceKey<DimensionType> resourceKey) {
        if (!GeneratorOptions.hasDragonFights()) {
            //this object would pass the test for the End-Dimension, so make sure we compare against something else to disabled the Dragon-Fight
            if (this.dimensionTypeRegistration().is(BuiltinDimensionTypes.END)) return BuiltinDimensionTypes.OVERWORLD;
        }
        return resourceKey;
    }

    @Inject(method = "<init>*", at = @At("TAIL"))
    private void be_onServerWorldInit(
            MinecraftServer minecraftServer,
            Executor executor,
            LevelStorageAccess levelStorageAccess,
            ServerLevelData serverLevelData,
            ResourceKey resourceKey,
            LevelStem levelStem,
            ChunkProgressListener chunkProgressListener,
            boolean bl,
            long seed,
            List list,
            boolean bl2,
            CallbackInfo ci
    ) {
        ServerLevel level = ServerLevel.class.cast(this);
        if (level.dimension() == Level.END) {
            final ChunkGenerator chunkGenerator = levelStem.generator();
            if (chunkGenerator instanceof NoiseBasedChunkGenerator) {
                Holder<NoiseGeneratorSettings> sHolder = ((NoiseBasedChunkGeneratorAccessor) chunkGenerator)
                        .be_getSettings();
                BETargetChecker.class.cast(sHolder.value()).be_setTarget(true);

            }
            TerrainGenerator.initNoise(
                    seed,
                    chunkGenerator.getBiomeSource(),
                    level.getChunkSource().randomState().sampler()
            );
        }
    }


    @Inject(method = "makeObsidianPlatform", at = @At("HEAD"), cancellable = true)
    private static void be_createObsidianPlatform(ServerLevel serverLevel, CallbackInfo info) {
        if (!GeneratorOptions.generateObsidianPlatform()) {
            info.cancel();
        } else if (GeneratorOptions.changeSpawn()) {
            BlockPos blockPos = GeneratorOptions.getSpawn();
            int i = blockPos.getX();
            int j = blockPos.getY() - 2;
            int k = blockPos.getZ();
            BlockPos.betweenClosed(i - 2, j + 1, k - 2, i + 2, j + 3, k + 2).forEach((blockPosx) -> {
                serverLevel.setBlockAndUpdate(blockPosx, Blocks.AIR.defaultBlockState());
            });
            BlockPos.betweenClosed(i - 2, j, k - 2, i + 2, j, k + 2).forEach((blockPosx) -> {
                serverLevel.setBlockAndUpdate(blockPosx, Blocks.OBSIDIAN.defaultBlockState());
            });
            info.cancel();
        }
    }

    @ModifyArg(method = "tickChunk", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/level/ServerLevel;setBlockAndUpdate(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;)Z"))
    private BlockState be_modifyTickState(BlockPos pos, BlockState state) {
        if (state.is(Blocks.ICE)) {
            ResourceLocation biome = BiomeAPI.getBiomeID(getBiome(pos));
            if (biome.getNamespace().equals(BetterEnd.MOD_ID)) {
                state = EndBlocks.EMERALD_ICE.defaultBlockState();
            }
        }
        return state;
    }
}
