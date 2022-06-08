package org.betterx.betterend.commands;

import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.core.Holder;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

import java.util.HashMap;
import java.util.Map;

public class CommandRegistry {
    public static void register() {
        CommandRegistrationCallback.EVENT.register(CommandRegistry::register);
    }

    private static void register(CommandDispatcher<CommandSourceStack> dispatcher,
                                 CommandBuildContext commandBuildContext,
                                 Commands.CommandSelection commandSelection) {
//        dispatcher.register(
//                Commands.literal("be")
//                        .requires(source -> source.hasPermission(Commands.LEVEL_OWNERS))
//                        .then(Commands.literal("locate_portal")
//                                      .requires(source -> source.hasPermission(Commands.LEVEL_OWNERS))
//                                      .executes(ctx -> find_poi(ctx))
//                             )
//                           );
    }

    private static final Map<Holder<Biome>, BlockState> biomeMap = new HashMap<>();
    private static int biomeMapIdx = 0;
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


    private static int find_poi(CommandContext<CommandSourceStack> ctx) throws CommandSyntaxException {

        return Command.SINGLE_SUCCESS;
    }
}

