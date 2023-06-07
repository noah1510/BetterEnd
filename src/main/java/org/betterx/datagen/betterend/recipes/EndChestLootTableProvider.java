package org.betterx.datagen.betterend.recipes;

import org.betterx.betterend.BetterEnd;
import org.betterx.betterend.registry.EndBlocks;
import org.betterx.betterend.registry.EndItems;
import org.betterx.betterend.registry.EndTemplates;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider;

import java.util.function.BiConsumer;

public class EndChestLootTableProvider extends SimpleFabricLootTableProvider {

    public EndChestLootTableProvider(
            FabricDataOutput output
    ) {
        super(output, LootContextParamSets.CHEST);
    }

    //TODO: 1.20 - find where Betterend Injects Loot into chests and make it conform to regular minecraft
    public static final ResourceLocation VILLAGE_LOOT = BetterEnd.makeID("chests/end_village_loot");
    public static final ResourceLocation VILLAGE_TEMPLATE_LOOT = BetterEnd.makeID("chests/end_village_template_loot");
    public static final ResourceLocation VILLAGE_BONUS_LOOT = BetterEnd.makeID("chests/end_village_bonus_loot");


    @Override
    public void generate(BiConsumer<ResourceLocation, LootTable.Builder> biConsumer) {
        biConsumer.accept(
                VILLAGE_LOOT,
                LootTable.lootTable()
                         .withPool(simpleVillageLoot())
                         .withPool(plateUpgradeLootPool())
        );

        biConsumer.accept(
                VILLAGE_TEMPLATE_LOOT,
                LootTable.lootTable()
                         .withPool(simpleVillageLoot())
                         .withPool(handleLootPool())
                         .withPool(leatherHandleLootPool())
                         .withPool(plateUpgradeLootPool())
                         .withPool(upgradeLootPool())
        );

        biConsumer.accept(
                VILLAGE_BONUS_LOOT,
                LootTable.lootTable()
                         .withPool(leatherHandleLootPool())
                         .withPool(plateUpgradeLootPool())
                         .withPool(villageBonusLoot())
                         .withPool(elytraLoot(0.2f))
        );
    }

    private LootPool.Builder upgradeLootPool() {
        return LootPool
                .lootPool()
                .setRolls(UniformGenerator.between(1, 2))
                .add(LootItem
                        .lootTableItem(EndTemplates.THALLASIUM_UPGRADE)
                        .setWeight(8)
                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1)))
                )
                .add(LootItem
                        .lootTableItem(EndTemplates.TERMINITE_UPGRADE)
                        .setWeight(4)
                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1)))
                )
                .add(LootItem
                        .lootTableItem(EndTemplates.AETERNIUM_UPGRADE)
                        .setWeight(2)
                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1)))
                )
                .add(LootItem
                        .lootTableItem(EndTemplates.NETHERITE_UPGRADE)
                        .setWeight(8)
                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1)))
                );
    }

    private LootPool.Builder plateUpgradeLootPool() {
        return LootPool
                .lootPool()
                .setRolls(ConstantValue.exactly(1))
                .add(LootItem
                        .lootTableItem(EndTemplates.PLATE_UPGRADE)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 2)))
                        .when(LootItemRandomChanceCondition.randomChance(0.70F))
                );
    }

    private LootPool.Builder leatherHandleLootPool() {
        return LootPool
                .lootPool()
                .setRolls(ConstantValue.exactly(1))
                .add(LootItem
                        .lootTableItem(EndTemplates.LEATHER_HANDLE_ATTACHMENT)
                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1)))
                        .when(LootItemRandomChanceCondition.randomChance(0.75F))
                );
    }

    private LootPool.Builder handleLootPool() {
        return LootPool
                .lootPool()
                .setRolls(ConstantValue.exactly(1))
                .add(LootItem
                        .lootTableItem(EndTemplates.HANDLE_ATTACHMENT)
                        .setWeight(8)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 4)))
                )
                .add(LootItem
                        .lootTableItem(EndTemplates.TOOL_ASSEMBLY)
                        .setWeight(2)
                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1)))
                );
    }

    private LootPool.Builder villageBonusLoot() {
        return LootPool
                .lootPool()
                .setRolls(UniformGenerator.between(1, 2))
                .add(LootItem
                        .lootTableItem(EndItems.AETERNIUM_INGOT)
                        .setWeight(8)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 4)))
                )
                .add(LootItem
                        .lootTableItem(EndBlocks.FLAVOLITE_RUNED_ETERNAL)
                        .setWeight(4)
                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1)))
                )
                .add(LootItem
                        .lootTableItem(EndItems.ETERNAL_CRYSTAL)
                        .setWeight(1)
                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1)))
                )
                .add(LootItem
                        .lootTableItem(Items.SHULKER_SHELL)
                        .setWeight(1)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(2, 4)))
                );
    }

    private LootPool.Builder elytraLoot(float chance) {
        return LootPool
                .lootPool()
                .setRolls(UniformGenerator.between(1, 3))
                .add(LootItem
                        .lootTableItem(Items.ELYTRA)
                        .setWeight(1)
                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1)))
                ).add(LootItem
                        .lootTableItem(Items.FIREWORK_ROCKET)
                        .setWeight(8)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(4, 8)))
                )
                .when(LootItemRandomChanceCondition.randomChance(chance));
    }

    private LootPool.Builder simpleVillageLoot() {
        return LootPool
                .lootPool()
                .setRolls(UniformGenerator.between(1, 3))
                .add(LootItem
                        .lootTableItem(EndItems.LEATHER_WRAPPED_STICK)
                        .setWeight(10)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(2, 5)))
                )
                .add(LootItem
                        .lootTableItem(Items.ENDER_PEARL)
                        .setWeight(9)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 5)))
                )
                .add(LootItem
                        .lootTableItem(EndItems.SHADOW_BERRY_COOKED)
                        .setWeight(8)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 4)))
                )
                .add(LootItem
                        .lootTableItem(EndItems.BLOSSOM_BERRY_JELLY)
                        .setWeight(4)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 4)))
                )
                .add(LootItem
                        .lootTableItem(EndBlocks.THALLASIUM.shovelHead)
                        .setWeight(2)
                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1)))
                )
                .add(LootItem
                        .lootTableItem(Blocks.ENDER_CHEST)
                        .setWeight(1)
                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1)))
                );
    }
}
