package org.betterx.betterend.commands;

import org.betterx.bclib.api.v2.poi.BCLPoiType;
import org.betterx.bclib.util.BlocksHelper;
import org.betterx.betterend.registry.EndPoiTypes;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.core.BlockPos;
import net.minecraft.core.BlockPos.MutableBlockPos;
import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class CommandRegistry {
    public static void register() {
        CommandRegistrationCallback.EVENT.register(CommandRegistry::register);
    }

    private static void register(
            CommandDispatcher<CommandSourceStack> dispatcher,
            CommandBuildContext commandBuildContext,
            Commands.CommandSelection commandSelection
    ) {
        dispatcher.register(
                Commands.literal("be")
                        .requires(source -> source.hasPermission(Commands.LEVEL_OWNERS))
                        .then(Commands.literal("locate_portal")
                                      .requires(source -> source.hasPermission(Commands.LEVEL_OWNERS))
                                      .executes(ctx -> find_poi(ctx, EndPoiTypes.ETERNAL_PORTAL_INACTIVE))
                        )
        );
    }

    private static final Map<Holder<Biome>, BlockState> biomeMap = new HashMap<>();
    private static final int biomeMapIdx = 0;
    private static final BlockState[] states = {
            Blocks.RED_STAINED_GLASS.defaultBlockState(),
            Blocks.BLUE_STAINED_GLASS.defaultBlockState(),
            Blocks.YELLOW_STAINED_GLASS.defaultBlockState(),
            Blocks.LIME_STAINED_GLASS.defaultBlockState(),
            Blocks.PINK_STAINED_GLASS.defaultBlockState(),
            Blocks.GREEN_STAINED_GLASS.defaultBlockState(),
            Blocks.WHITE_STAINED_GLASS.defaultBlockState(),
            Blocks.BLACK_STAINED_GLASS.defaultBlockState(),
            Blocks.ORANGE_STAINED_GLASS.defaultBlockState(),
            Blocks.LIGHT_BLUE_STAINED_GLASS.defaultBlockState()
    };
    private static final BlockState[] states2 = {
            Blocks.RED_CONCRETE.defaultBlockState(),
            Blocks.BLUE_CONCRETE.defaultBlockState(),
            Blocks.YELLOW_CONCRETE.defaultBlockState(),
            Blocks.LIME_CONCRETE.defaultBlockState(),
            Blocks.PINK_CONCRETE.defaultBlockState(),
            Blocks.GREEN_CONCRETE.defaultBlockState(),
            Blocks.WHITE_CONCRETE.defaultBlockState(),
            Blocks.BLACK_CONCRETE.defaultBlockState(),
            Blocks.ORANGE_CONCRETE.defaultBlockState(),
            Blocks.LIGHT_BLUE_CONCRETE.defaultBlockState()
    };


    private static int find_poi(CommandContext<CommandSourceStack> ctx, BCLPoiType poi) throws CommandSyntaxException {
        final CommandSourceStack source = ctx.getSource();
        final ServerPlayer player = source.getPlayerOrException();
        Vec3 pos = source.getPosition();
        final ServerLevel level = source.getLevel();
        MutableBlockPos mPos = new BlockPos(pos).mutable();
        System.out.println("Searching POI: " + poi.key);
        Optional<BlockPos> found = poi.findPoiAround(level, mPos, false, level.getWorldBorder());
        System.out.println("Found at: " + found.orElse(null));
        if (found.isPresent()) {
            BlocksHelper.setWithoutUpdate(level, found.get(), Blocks.YELLOW_CONCRETE);
            BlocksHelper.setWithoutUpdate(level, mPos, Blocks.LIGHT_BLUE_CONCRETE);
        } else {
            BlocksHelper.setWithoutUpdate(level, mPos, Blocks.RED_CONCRETE);
        }
        return Command.SINGLE_SUCCESS;
    }
}

