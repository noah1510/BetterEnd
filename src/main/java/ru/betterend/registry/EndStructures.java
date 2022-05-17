package ru.betterend.registry;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep.Decoration;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceType;
import ru.bclib.api.tag.TagAPI;
import ru.bclib.world.structures.BCLStructure;
import ru.betterend.BetterEnd;
import ru.betterend.world.structures.features.EternalPortalStructure;
import ru.betterend.world.structures.features.GiantIceStarStructure;
import ru.betterend.world.structures.features.GiantMossyGlowshroomStructure;
import ru.betterend.world.structures.features.MegaLakeSmallStructure;
import ru.betterend.world.structures.features.MegaLakeStructure;
import ru.betterend.world.structures.features.MountainStructure;
import ru.betterend.world.structures.features.PaintedMountainStructure;
import ru.betterend.world.structures.piece.CavePiece;
import ru.betterend.world.structures.piece.CrystalMountainPiece;
import ru.betterend.world.structures.piece.LakePiece;
import ru.betterend.world.structures.piece.NBTPiece;
import ru.betterend.world.structures.piece.PaintedMountainPiece;
import ru.betterend.world.structures.piece.VoxelPiece;

public class EndStructures {
	public static final StructurePieceType VOXEL_PIECE = register("voxel", VoxelPiece::new);
	public static final StructurePieceType MOUNTAIN_PIECE = register("mountain_piece", CrystalMountainPiece::new);
	public static final StructurePieceType CAVE_PIECE = register("cave_piece", CavePiece::new);
	public static final StructurePieceType LAKE_PIECE = register("lake_piece", LakePiece::new);
	public static final StructurePieceType PAINTED_MOUNTAIN_PIECE = register("painted_mountain_piece", PaintedMountainPiece::new);
	public static final StructurePieceType NBT_PIECE = register("nbt_piece", NBTPiece::new);
	
	public static final BCLStructure<GiantMossyGlowshroomStructure> GIANT_MOSSY_GLOWSHROOM = new BCLStructure<>(
		BetterEnd.makeID("giant_mossy_glowshroom"),
		GiantMossyGlowshroomStructure::new,
		Decoration.SURFACE_STRUCTURES,
		16,
		8
	);
	public static final BCLStructure<MegaLakeStructure> MEGALAKE = new BCLStructure<>(
		BetterEnd.makeID("megalake"),
		MegaLakeStructure::new,
		Decoration.RAW_GENERATION,
		4,
		1
	);
	public static final BCLStructure<MegaLakeSmallStructure> MEGALAKE_SMALL = new BCLStructure<>(
		BetterEnd.makeID("megalake_small"),
		MegaLakeSmallStructure::new,
		Decoration.RAW_GENERATION,
		4,
		1
	);
	public static final BCLStructure<MountainStructure> MOUNTAIN = new BCLStructure<>(
		BetterEnd.makeID("mountain"),
		MountainStructure::new,
		Decoration.RAW_GENERATION,
		3,
		2
	);
	public static final BCLStructure<PaintedMountainStructure> PAINTED_MOUNTAIN = new BCLStructure<>(
		BetterEnd.makeID("painted_mountain"),
		PaintedMountainStructure::new,
		Decoration.RAW_GENERATION,
		3,
		2
	);
	public static final BCLStructure<EternalPortalStructure> ETERNAL_PORTAL = new BCLStructure<>(
		BetterEnd.makeID("eternal_portal"),
		EternalPortalStructure::new,
		Decoration.SURFACE_STRUCTURES,
		16,
		6
	);
	public static final BCLStructure<GiantIceStarStructure> GIANT_ICE_STAR = new BCLStructure<>(
		BetterEnd.makeID("giant_ice_star"),
		GiantIceStarStructure::new,
		Decoration.SURFACE_STRUCTURES,
		16,
		8
	);
	
	public static void register() {}
	
	private static StructurePieceType register(String id, StructurePieceType pieceType) {
		return Registry.register(Registry.STRUCTURE_PIECE, BetterEnd.makeID(id), pieceType);
	}
	
	public static void addBiomeStructures(ResourceLocation biomeID, Holder<Biome> biome) {
		if (!biomeID.getPath().contains("mountain") && !biomeID.getPath().contains("lake")) {
			TagAPI.addBiomeTag(ETERNAL_PORTAL.biomeTag, biome.value());
		}
	}
}
