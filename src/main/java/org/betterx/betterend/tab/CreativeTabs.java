package org.betterx.betterend.tab;

import org.betterx.bclib.creativetab.BCLCreativeTab;
import org.betterx.bclib.creativetab.BCLCreativeTabManager;
import org.betterx.betterend.BetterEnd;
import org.betterx.betterend.registry.EndBlocks;
import org.betterx.betterend.registry.EndItems;

public class CreativeTabs {

    public static void register() {
        BCLCreativeTabManager.create(BetterEnd.MOD_ID)
                             .createTab("nature")
                             .setPredicate(item -> BCLCreativeTab.NATURE.contains(item)
//                                     || item == NetherItems.AGAVE_LEAF
//                                     || item == NetherItems.BLACK_APPLE
//                                     || item == NetherBlocks.MAGMA_FLOWER.asItem()
//                                     || item == NetherBlocks.MAT_RUBEUS.getBlockItem(NetherSlots.CONE)
//                                     || item == NetherBlocks.MAT_WILLOW.getBlockItem(WillowMaterial.BLOCK_TORCH)
                             )
                             .setIcon(EndBlocks.FILALUX_LANTERN)
                             .build()
                             .createBlockTab(EndBlocks.END_MYCELIUM)
                             .build()
                             .createItemsTab(EndItems.ETERNAL_CRYSTAL)
                             .build()
                             .processBCLRegistry()
                             .register();
    }
}
