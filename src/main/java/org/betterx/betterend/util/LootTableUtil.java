package org.betterx.betterend.util;

import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;

import org.betterx.bclib.api.v2.levelgen.biomes.BCLBiome;
import org.betterx.bclib.api.v2.levelgen.biomes.BiomeAPI;
import org.betterx.bclib.complexmaterials.WoodenComplexMaterial;
import org.betterx.betterend.BetterEnd;
import org.betterx.betterend.registry.EndBiomes;
import org.betterx.betterend.registry.EndBlocks;
import org.betterx.betterend.registry.EndItems;

public class LootTableUtil {
    private static final ResourceLocation END_CITY_TREASURE_ID = new ResourceLocation("chests/end_city_treasure");
    private static final ResourceLocation COMMON = BetterEnd.makeID("chests/common");
    private static final ResourceLocation FOGGY_MUSHROOMLAND = BetterEnd.makeID("chests/foggy_mushroomland");
    private static final ResourceLocation CHORUS_FOREST = BetterEnd.makeID("chests/chorus_forest");
    private static final ResourceLocation SHADOW_FOREST = BetterEnd.makeID("chests/shadow_forest");
    private static final ResourceLocation LANTERN_WOODS = BetterEnd.makeID("chests/lantern_woods");
    private static final ResourceLocation UMBRELLA_JUNGLE = BetterEnd.makeID("chests/umbrella_jungle");

    public static void init() {
        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, table, setter) -> {
            if (END_CITY_TREASURE_ID.equals(id)) {
                LootPool.Builder builder = LootPool.lootPool();
                builder.setRolls(ConstantValue.exactly(1));
                builder.conditionally(LootItemRandomChanceCondition.randomChance(0.2f).build());
                builder.add(LootItem.lootTableItem(Items.GHAST_TEAR));
                table.withPool(builder);

                builder = LootPool.lootPool();
                builder.setRolls(UniformGenerator.between(0, 3));
                builder.add(LootItem.lootTableItem(EndItems.MUSIC_DISC_STRANGE_AND_ALIEN));
                builder.add(LootItem.lootTableItem(EndItems.MUSIC_DISC_GRASPING_AT_STARS));
                builder.add(LootItem.lootTableItem(EndItems.MUSIC_DISC_ENDSEEKER));
                builder.add(LootItem.lootTableItem(EndItems.MUSIC_DISC_EO_DRACONA));
                table.withPool(builder);
            } else if (id.getNamespace().equals(BetterEnd.MOD_ID)) {
                addCommonItems(table);
                if (FOGGY_MUSHROOMLAND.equals(id)) {
                    LootPool.Builder builder =LootPool.lootPool();
                    builder.setRolls(UniformGenerator.between(4, 8));
                    builder.add(LootItem.lootTableItem(EndBlocks.MOSSY_GLOWSHROOM.getBlock(WoodenComplexMaterial.BLOCK_PLANKS)));
                    builder.add(LootItem.lootTableItem(EndBlocks.MOSSY_GLOWSHROOM_SAPLING));
                    builder.add(LootItem.lootTableItem(EndBlocks.BLUE_VINE_SEED));
                    table.withPool(builder);
                } else if (CHORUS_FOREST.equals(id)) {
                    LootPool.Builder builder = LootPool.lootPool();
                    builder.setRolls(UniformGenerator.between(4, 8));
                    builder.add(LootItem.lootTableItem(EndBlocks.PYTHADENDRON.getBlock(WoodenComplexMaterial.BLOCK_PLANKS)));
                    builder.add(LootItem.lootTableItem(EndBlocks.PYTHADENDRON_SAPLING));
                    builder.add(LootItem.lootTableItem(EndBlocks.CHORUS_MUSHROOM));
                    table.withPool(builder);
                } else if (SHADOW_FOREST.equals(id)) {
                    LootPool.Builder builder = LootPool.lootPool();
                    builder.setRolls(UniformGenerator.between(4, 8));
                    builder.add(LootItem.lootTableItem(EndBlocks.DRAGON_TREE.getBlock(WoodenComplexMaterial.BLOCK_PLANKS)));
                    builder.add(LootItem.lootTableItem(EndBlocks.DRAGON_TREE_SAPLING));
                    builder.add(LootItem.lootTableItem(EndBlocks.SHADOW_BERRY));
                    builder.add(LootItem.lootTableItem(EndItems.SHADOW_BERRY_RAW));
                    table.withPool(builder);
                } else if (LANTERN_WOODS.equals(id)) {
                    LootPool.Builder builder =LootPool.lootPool();
                    builder.setRolls(UniformGenerator.between(4, 8));
                    builder.add(LootItem.lootTableItem(EndBlocks.LUCERNIA.getBlock(WoodenComplexMaterial.BLOCK_PLANKS)));
                    builder.add(LootItem.lootTableItem(EndBlocks.LUCERNIA_SAPLING));
                    builder.add(LootItem.lootTableItem(EndBlocks.BOLUX_MUSHROOM));
                    table.withPool(builder);
                } else if (UMBRELLA_JUNGLE.equals(id)) {
                    LootPool.Builder builder = LootPool.lootPool();
                    builder.setRolls(UniformGenerator.between(4, 8));
                    builder.add(LootItem.lootTableItem(EndBlocks.UMBRELLA_TREE.getBlock(WoodenComplexMaterial.BLOCK_PLANKS)));
                    builder.add(LootItem.lootTableItem(EndBlocks.UMBRELLA_TREE_SAPLING));
                    builder.add(LootItem.lootTableItem(EndBlocks.SMALL_JELLYSHROOM));
                    table.withPool(builder);
                }
            }
        });
    }

    public static ResourceLocation getTable(Holder<Biome> biome) {
        BCLBiome bclBiome = BiomeAPI.getBiome(biome.value());
        if (bclBiome == EndBiomes.FOGGY_MUSHROOMLAND) {
            return FOGGY_MUSHROOMLAND;
        } else if (bclBiome == EndBiomes.CHORUS_FOREST) {
            return CHORUS_FOREST;
        } else if (bclBiome == EndBiomes.SHADOW_FOREST) {
            return SHADOW_FOREST;
        } else if (bclBiome == EndBiomes.LANTERN_WOODS) {
            return LANTERN_WOODS;
        } else if (bclBiome == EndBiomes.UMBRELLA_JUNGLE) {
            return UMBRELLA_JUNGLE;
        }
        return COMMON;
    }

    private static void addCommonItems(LootTable.Builder table) {
        LootPool.Builder builder = LootPool.lootPool();
        builder.setRolls(UniformGenerator.between(0, 2));
        builder.add(LootItem.lootTableItem(EndItems.MUSIC_DISC_STRANGE_AND_ALIEN));
        builder.add(LootItem.lootTableItem(EndItems.MUSIC_DISC_GRASPING_AT_STARS));
        builder.add(LootItem.lootTableItem(EndItems.MUSIC_DISC_ENDSEEKER));
        builder.add(LootItem.lootTableItem(EndItems.MUSIC_DISC_EO_DRACONA));
        table.withPool(builder);

        builder = LootPool.lootPool();
        builder.setRolls(UniformGenerator.between(4, 8));
        builder.add(LootItem.lootTableItem(EndBlocks.THALLASIUM.ingot));
        builder.add(LootItem.lootTableItem(EndBlocks.THALLASIUM.rawOre));
        builder.add(LootItem.lootTableItem(Items.ENDER_PEARL));
        table.withPool(builder);

        builder = LootPool.lootPool();
        builder.setRolls(UniformGenerator.between(2, 4));
        builder.add(LootItem.lootTableItem(EndBlocks.TERMINITE.ingot));
        builder.add(LootItem.lootTableItem(EndItems.ENDER_SHARD));
        builder.add(LootItem.lootTableItem(EndBlocks.AURORA_CRYSTAL));
        builder.add(LootItem.lootTableItem(EndBlocks.THALLASIUM.axe));
        builder.add(LootItem.lootTableItem(EndBlocks.THALLASIUM.pickaxe));
        builder.add(LootItem.lootTableItem(EndBlocks.THALLASIUM.hoe));
        builder.add(LootItem.lootTableItem(EndBlocks.THALLASIUM.sword));
        builder.add(LootItem.lootTableItem(EndBlocks.THALLASIUM.shovel));
        builder.add(LootItem.lootTableItem(Items.ENDER_EYE));
        builder.add(LootItem.lootTableItem(Blocks.OBSIDIAN));
        table.withPool(builder);

        builder = LootPool.lootPool();
        builder.setRolls(UniformGenerator.between(0, 4));
        builder.add(LootItem.lootTableItem(EndBlocks.FLAVOLITE_RUNED));
        builder.add(LootItem.lootTableItem(EndItems.AETERNIUM_INGOT));
        builder.add(LootItem.lootTableItem(EndItems.AMBER_GEM));
        builder.add(LootItem.lootTableItem(Items.END_CRYSTAL));
        builder.add(LootItem.lootTableItem(Items.GHAST_TEAR));
        table.withPool(builder);
    }
}
