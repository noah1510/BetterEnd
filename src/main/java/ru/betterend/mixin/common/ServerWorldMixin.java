package ru.betterend.mixin.common;

import java.io.File;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Executor;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.metadata.ModMetadata;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtHelper;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.WorldGenerationProgressListener;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.Spawner;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.level.ServerWorldProperties;
import net.minecraft.world.level.storage.LevelStorage;
import ru.betterend.BetterEnd;
import ru.betterend.util.DataFixerUtil;
import ru.betterend.util.WorldDataUtil;
import ru.betterend.world.generator.GeneratorOptions;

@Mixin(ServerWorld.class)
public class ServerWorldMixin {
	private static final int DEV_VERSION = be_getVersionInt("63.63.63");
	private static final int FIX_VERSION = DEV_VERSION;
	private static String lastWorld = null;
	
	@Inject(method = "<init>*", at = @At("TAIL"))
	private void be_onServerWorldInit(MinecraftServer server, Executor workerExecutor, LevelStorage.Session session, ServerWorldProperties properties, RegistryKey<World> registryKey, DimensionType dimensionType, WorldGenerationProgressListener worldGenerationProgressListener, ChunkGenerator chunkGenerator, boolean debugWorld, long l, List<Spawner> list, boolean bl, CallbackInfo info) {
		if (lastWorld != null && lastWorld.equals(session.getDirectoryName())) {
			return;
		}
		
		lastWorld = session.getDirectoryName();
		
		@SuppressWarnings("resource")
		ServerWorld world = (ServerWorld) (Object) this;
		File dir = session.getWorldDirectory(world.getRegistryKey());
		if (!new File(dir, "level.dat").exists()) {
			dir = dir.getParentFile();
		}
		File data = new File(dir, "data/betterend_data.nbt");
		
		ModMetadata meta = FabricLoader.getInstance().getModContainer(BetterEnd.MOD_ID).get().getMetadata();
		int version = BetterEnd.isDevEnvironment() ? DEV_VERSION : be_getVersionInt(meta.getVersion().toString());
		
		WorldDataUtil.load(data);
		CompoundTag root = WorldDataUtil.getRootTag();
		int dataVersion = be_getVersionInt(root.getString("version"));
		GeneratorOptions.setPortalPos(NbtHelper.toBlockPos(root.getCompound("portal")));
		
		if (dataVersion < version) {
			if (version < FIX_VERSION) {
				DataFixerUtil.fixData(data.getParentFile());
			}
			root.putString("version", be_getVersionString(version));
			WorldDataUtil.saveFile();
		}
	}

	@Inject(method = "getSpawnPos", at = @At("HEAD"), cancellable = true)
	private void be_getSpawnPos(CallbackInfoReturnable<BlockPos> info) {
		if (GeneratorOptions.changeSpawn()) {
			if (((ServerWorld) (Object) this).getRegistryKey() == World.END) {
				info.setReturnValue(GeneratorOptions.getSpawn());
				info.cancel();
			}
		}
	}
	
	private static int be_getVersionInt(String version) {
		if (version.isEmpty()) {
			return 0;
		}
		try {
			String[] values = version.split("\\.");
			return Integer.parseInt(values[0]) << 12 | Integer.parseInt(values[1]) << 6 | Integer.parseInt(values[1]);
		}
		catch (Exception e) {
			return 0;
		}
	}
	
	private static String be_getVersionString(int version) {
		int a = (version >> 12) & 63;
		int b = (version >> 6) & 63;
		int c = version & 63;
		return String.format(Locale.ROOT, "%d.%d.%d", a, b, c);
	}
}