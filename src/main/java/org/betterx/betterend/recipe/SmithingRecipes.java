package org.betterx.betterend.recipe;

import org.betterx.bclib.recipes.BCLRecipeBuilder;
import org.betterx.betterend.BetterEnd;
import org.betterx.betterend.registry.EndBlocks;
import org.betterx.betterend.registry.EndItems;

import net.minecraft.world.item.Items;

public class SmithingRecipes {

    public static void register() {
        BCLRecipeBuilder.smithing(BetterEnd.makeID("aeternium_sword_handle"), EndItems.AETERNIUM_SWORD_HANDLE)
                        .setPrimaryInputAndUnlock(EndBlocks.TERMINITE.ingot)
                        .setAddition(EndItems.LEATHER_WRAPPED_STICK)
                        .build();

        BCLRecipeBuilder.smithing(BetterEnd.makeID("aeternium_sword"), EndItems.AETERNIUM_SWORD)
                        .setPrimaryInputAndUnlock(EndItems.AETERNIUM_SWORD_BLADE)
                        .setAddition(EndItems.AETERNIUM_SWORD_HANDLE)
                        .build();
        BCLRecipeBuilder.smithing(BetterEnd.makeID("aeternium_pickaxe"), EndItems.AETERNIUM_PICKAXE)
                        .setPrimaryInputAndUnlock(EndItems.AETERNIUM_PICKAXE_HEAD)
                        .setAddition(EndItems.LEATHER_WRAPPED_STICK)
                        .build();
        BCLRecipeBuilder.smithing(BetterEnd.makeID("aeternium_axe"), EndItems.AETERNIUM_AXE)
                        .setPrimaryInputAndUnlock(EndItems.AETERNIUM_AXE_HEAD)
                        .setAddition(EndItems.LEATHER_WRAPPED_STICK)
                        .build();
        BCLRecipeBuilder.smithing(BetterEnd.makeID("aeternium_shovel"), EndItems.AETERNIUM_SHOVEL)
                        .setPrimaryInputAndUnlock(EndItems.AETERNIUM_SHOVEL_HEAD)
                        .setAddition(EndItems.LEATHER_WRAPPED_STICK)
                        .build();
        BCLRecipeBuilder.smithing(BetterEnd.makeID("aeternium_hoe"), EndItems.AETERNIUM_HOE)
                        .setPrimaryInputAndUnlock(EndItems.AETERNIUM_HOE_HEAD)
                        .setAddition(EndItems.LEATHER_WRAPPED_STICK)
                        .build();
        BCLRecipeBuilder.smithing(BetterEnd.makeID("aeternium_hammer"), EndItems.AETERNIUM_HAMMER)
                        .setPrimaryInputAndUnlock(EndItems.AETERNIUM_HAMMER_HEAD)
                        .setAddition(EndItems.LEATHER_WRAPPED_STICK)
                        .build();

        BCLRecipeBuilder.smithing(BetterEnd.makeID("netherite_hammer"), EndItems.NETHERITE_HAMMER)
                        .setPrimaryInputAndUnlock(EndItems.DIAMOND_HAMMER)
                        .setAddition(Items.NETHERITE_INGOT)
                        .build();

        BCLRecipeBuilder.smithing(BetterEnd.makeID("aeternium_helmet"), EndItems.AETERNIUM_HELMET)
                        .setPrimaryInputAndUnlock(EndBlocks.TERMINITE.helmet)
                        .setAddition(EndItems.AETERNIUM_FORGED_PLATE)
                        .build();
        BCLRecipeBuilder.smithing(BetterEnd.makeID("aeternium_chestplate"), EndItems.AETERNIUM_CHESTPLATE)
                        .setPrimaryInputAndUnlock(EndBlocks.TERMINITE.chestplate)
                        .setAddition(EndItems.AETERNIUM_FORGED_PLATE)
                        .build();
        BCLRecipeBuilder.smithing(BetterEnd.makeID("aeternium_leggings"), EndItems.AETERNIUM_LEGGINGS)
                        .setPrimaryInputAndUnlock(EndBlocks.TERMINITE.leggings)
                        .setAddition(EndItems.AETERNIUM_FORGED_PLATE)
                        .build();
        BCLRecipeBuilder.smithing(BetterEnd.makeID("aeternium_boots"), EndItems.AETERNIUM_BOOTS)
                        .setPrimaryInputAndUnlock(EndBlocks.TERMINITE.boots)
                        .setAddition(EndItems.AETERNIUM_FORGED_PLATE)
                        .build();

        BCLRecipeBuilder.smithing(BetterEnd.makeID("thallasium_anvil_updrade"), EndBlocks.TERMINITE.anvilBlock.asItem())
                        .setPrimaryInputAndUnlock(EndBlocks.THALLASIUM.anvilBlock.asItem())
                        .setAddition(EndBlocks.TERMINITE.block)
                        .build();
        BCLRecipeBuilder.smithing(BetterEnd.makeID("terminite_anvil_updrade"), EndBlocks.AETERNIUM_ANVIL.asItem())
                        .setPrimaryInputAndUnlock(EndBlocks.TERMINITE.anvilBlock.asItem())
                        .setAddition(EndItems.AETERNIUM_INGOT)
                        .build();

        BCLRecipeBuilder.smithing(BetterEnd.makeID("armored_elytra"), EndItems.ARMORED_ELYTRA)
                        .setPrimaryInputAndUnlock(Items.ELYTRA)
                        .setAddition(EndItems.AETERNIUM_INGOT)
                        .build();
    }
}
