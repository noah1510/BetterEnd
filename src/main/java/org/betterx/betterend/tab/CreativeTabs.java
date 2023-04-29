package org.betterx.betterend.tab;

import org.betterx.betterend.BetterEnd;
import org.betterx.betterend.registry.EndBlocks;
import org.betterx.betterend.registry.EndItems;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;

import java.util.stream.Collectors;

public class CreativeTabs {
    public static final CreativeModeTab TAB_BLOCKS;
    public static final CreativeModeTab TAB_ITEMS;

    static {
        TAB_BLOCKS = FabricItemGroup
                .builder(BetterEnd.makeID("end_blocks"))
                .icon(() -> new ItemStack(EndBlocks.END_MYCELIUM))
                .displayItems((featureFlagSet, output) -> output.acceptAll(EndBlocks.getModBlockItems()
                                                                                    .stream()
                                                                                    .map(ItemStack::new)
                                                                                    .collect(Collectors.toList())))
                .build();
        TAB_ITEMS = FabricItemGroup
                .builder(BetterEnd.makeID("end_items"))
                .icon(() -> new ItemStack(EndItems.ETERNAL_CRYSTAL))
                .displayItems((featureFlagSet, output) -> output.acceptAll(EndItems.getModItems()
                                                                                   .stream()
                                                                                   .map(ItemStack::new)
                                                                                   .collect(Collectors.toList())))
                .build();
    }
}
