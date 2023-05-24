package org.betterx.betterend.tab;

import org.betterx.bclib.behaviours.interfaces.*;
import org.betterx.betterend.BetterEnd;
import org.betterx.betterend.registry.EndBlocks;
import org.betterx.betterend.registry.EndItems;
import org.betterx.worlds.together.tag.v3.CommonBlockTags;
import org.betterx.worlds.together.tag.v3.CommonItemTags;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;

import java.util.stream.Collectors;

public class CreativeTabs {
    public static final CreativeModeTab TAB_BLOCKS;
    public static final CreativeModeTab TAB_ITEMS;
    public static final CreativeModeTab TAB_PLANTS;

    public static final ResourceKey<CreativeModeTab> TAB_ITEMS_KEY = ResourceKey.create(
            Registries.CREATIVE_MODE_TAB,
            BetterEnd.makeID("item_tab")
    );
    public static final ResourceKey<CreativeModeTab> TAB_BLOCKS_KEY = ResourceKey.create(
            Registries.CREATIVE_MODE_TAB,
            BetterEnd.makeID("block_tab")
    );
    public static final ResourceKey<CreativeModeTab> TAB_PLANTS_KEY = ResourceKey.create(
            Registries.CREATIVE_MODE_TAB,
            BetterEnd.makeID("plant_tab")
    );

    public static void register() {
        Registry.register(
                BuiltInRegistries.CREATIVE_MODE_TAB,
                TAB_ITEMS_KEY,
                TAB_ITEMS
        );

        Registry.register(
                BuiltInRegistries.CREATIVE_MODE_TAB,
                TAB_BLOCKS_KEY,
                TAB_BLOCKS
        );

        Registry.register(
                BuiltInRegistries.CREATIVE_MODE_TAB,
                TAB_PLANTS_KEY,
                TAB_PLANTS
        );
    }

    static {
        TAB_BLOCKS = FabricItemGroup
                .builder()
                .icon(() -> new ItemStack(EndBlocks.END_MYCELIUM))
                .title(Component.translatable("itemGroup.betterend.blocks"))
                .displayItems((featureFlagSet, output) -> output.acceptAll(EndBlocks.getModBlockItems()
                                                                                    .stream()
                                                                                    .map(b -> new ItemStack(b, 1))
                                                                                    .collect(Collectors.toList())))
                .build();
        TAB_ITEMS = FabricItemGroup
                .builder()
                .title(Component.translatable("itemGroup.betterend.items"))
                .icon(() -> new ItemStack(EndItems.ETERNAL_CRYSTAL))
                .displayItems((featureFlagSet, output) -> output.acceptAll(EndItems.getModItems()
                                                                                   .stream()
                                                                                   .map(b -> new ItemStack(b, 1))
                                                                                   .collect(Collectors.toList())))
                .build();
        TAB_PLANTS = FabricItemGroup
                .builder()
                .title(Component.translatable("itemGroup.betterend.plants"))
                .icon(() -> new ItemStack(EndBlocks.FILALUX_LANTERN))
                .displayItems((featureFlagSet, output) -> {
                    output.acceptAll(EndItems.getModItems()
                                             .stream()
                                             .map(b -> new ItemStack(b, 1))
                                             .filter(s -> s.is(CommonItemTags.COMPOSTABLE)
                                                     || s.is(CommonItemTags.LEAVES)
                                                     || s.is(CommonItemTags.SAPLINGS)
                                                     || s.is(CommonItemTags.SEEDS))
                                             .collect(Collectors.toList()));

                    output.acceptAll(EndBlocks.getModBlocks()
                                              .stream()
                                              .filter(b -> b.asItem() != null && b.asItem() != Items.AIR)
                                              .filter(b -> b instanceof BehaviourVine
                                                      || b instanceof BehaviourLeaves
                                                      || b instanceof BehaviourPlant
                                                      || b instanceof BehaviourWaterPlant
                                                      || b instanceof BehaviourSeed
                                                      || b instanceof BehaviourSapling
                                                      || b instanceof BehaviourCompostable
                                                      || b.defaultBlockState().is(CommonBlockTags.WATER_PLANT)
                                                      || b.defaultBlockState().is(CommonBlockTags.PLANT)
                                                      || b.defaultBlockState().is(CommonBlockTags.SEEDS)
                                                      || b.defaultBlockState().is(CommonBlockTags.SAPLINGS)
                                                      || b.defaultBlockState().is(CommonBlockTags.LEAVES))
                                              .map(b -> new ItemStack(b, 1))

                                              .collect(Collectors.toList()));
                })
                .build();
    }
}
