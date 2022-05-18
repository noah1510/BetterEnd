package org.betterx.betterend.item;

import net.minecraft.world.item.Rarity;

import org.betterx.bclib.items.ModelProviderItem;
import org.betterx.betterend.registry.EndItems;

public class EternalCrystalItem extends ModelProviderItem {
    public EternalCrystalItem() {
        super(EndItems.makeEndItemSettings().stacksTo(16).rarity(Rarity.EPIC));
    }
}