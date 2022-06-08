package org.betterx.betterend.integration;

import org.betterx.bclib.api.v2.tag.TagAPI;
import org.betterx.bclib.integration.ModIntegration;
import org.betterx.betterend.registry.EndItems;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class NourishIntegration extends ModIntegration {
    public NourishIntegration() {
        super("nourish");
    }

    @Override
    public void init() {
        TagKey<Item> fats = getItemTag("fats");
        TagKey<Item> fruit = getItemTag("fruit");
        TagKey<Item> protein = getItemTag("protein");
        TagKey<Item> sweets = getItemTag("sweets");

        TagAPI.addItemTag(fats, EndItems.END_FISH_RAW, EndItems.END_FISH_COOKED);
        TagAPI.addItemTag(
                fruit,
                EndItems.SHADOW_BERRY_RAW,
                EndItems.SHADOW_BERRY_COOKED,
                EndItems.BLOSSOM_BERRY,
                EndItems.SHADOW_BERRY_JELLY,
                EndItems.SWEET_BERRY_JELLY,
                EndItems.BLOSSOM_BERRY_JELLY,
                EndItems.AMBER_ROOT_RAW,
                EndItems.CHORUS_MUSHROOM_RAW,
                EndItems.CHORUS_MUSHROOM_COOKED,
                EndItems.BOLUX_MUSHROOM_COOKED
        );
        TagAPI.addItemTag(
                protein,
                EndItems.END_FISH_RAW,
                EndItems.END_FISH_COOKED,
                EndItems.CHORUS_MUSHROOM_COOKED,
                EndItems.BOLUX_MUSHROOM_COOKED,
                EndItems.CAVE_PUMPKIN_PIE
        );
        TagAPI.addItemTag(
                sweets,
                EndItems.SHADOW_BERRY_JELLY,
                EndItems.SWEET_BERRY_JELLY,
                EndItems.BLOSSOM_BERRY_JELLY,
                EndItems.CAVE_PUMPKIN_PIE
        );
    }
}
