package org.betterx.datagen.betterend.advancement;

import org.betterx.bclib.api.v2.advancement.AdvancementManager;
import org.betterx.bclib.api.v3.datagen.AdvancementDataProvider;
import org.betterx.betterend.BetterEnd;
import org.betterx.betterend.advancements.BECriteria;
import org.betterx.betterend.registry.EndBlocks;
import org.betterx.betterend.registry.EndItems;
import org.betterx.betterend.registry.EndStructures;
import org.betterx.betterend.world.biome.EndBiome;

import net.minecraft.advancements.FrameType;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.critereon.ChangeDimensionTrigger;
import net.minecraft.advancements.critereon.LocationPredicate;
import net.minecraft.advancements.critereon.PlayerTrigger;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;

import java.util.List;

public class EndAdvancementDataProvider extends AdvancementDataProvider {
    public EndAdvancementDataProvider(
            FabricDataOutput output
    ) {
        super(List.of(BetterEnd.MOD_ID), output);
    }

    @Override
    protected void bootstrap() {
        ResourceLocation root = AdvancementManager.Builder
                .create(BetterEnd.makeID("root"))
                .startDisplay(EndBlocks.END_MYCELIUM)
                .frame(FrameType.TASK)
                .hideFromChat()
                .background(new ResourceLocation("textures/gui/advancements/backgrounds/end.png"))
                .endDisplay()
                .addCriterion(
                        "welcome",
                        PlayerTrigger.TriggerInstance.located(LocationPredicate.ANY)
                )
                .requirements(RequirementsStrategy.OR)
                .build();

        ResourceLocation enterEnd = AdvancementManager.Builder
                .create(BetterEnd.makeID("enter_end"))
                .startDisplay(EndBlocks.CAVE_MOSS)
                .endDisplay()
                .parent(root)
                .addCriterion(
                        "entered_end",
                        ChangeDimensionTrigger
                                .TriggerInstance
                                .changedDimensionTo(Level.END)
                )
                .requirements(RequirementsStrategy.OR)
                .build();

        ResourceLocation portal = AdvancementManager.Builder
                .create(BetterEnd.makeID("portal"))
                .parent(root)
                .startDisplay(EndBlocks.ETERNAL_PEDESTAL)
                .frame(FrameType.GOAL)
                .endDisplay()
                .addAtStructureCriterion("eternal_portal", EndStructures.ETERNAL_PORTAL)
                .requirements(RequirementsStrategy.OR)
                .build();

        ResourceLocation portalOn = AdvancementManager.Builder
                .create(BetterEnd.makeID("portal_on"))
                .parent(portal)
                .startDisplay(EndItems.ETERNAL_CRYSTAL)
                .endDisplay()
                .addCriterion("turn_on", BECriteria.PORTAL_ON_TRIGGER)
                .requirements(RequirementsStrategy.OR)
                .build();

        ResourceLocation portalTravel = AdvancementManager.Builder
                .create(BetterEnd.makeID("portal_travel"))
                .parent(portalOn)
                .startDisplay(Items.GRASS_BLOCK)
                .frame(FrameType.CHALLENGE)
                .endDisplay()
                .addCriterion("travel", BECriteria.PORTAL_TRAVEL_TRIGGER)
                .requirements(RequirementsStrategy.OR)
                .build();

        ResourceLocation allTheBiomes = AdvancementManager.Builder
                .create(BetterEnd.makeID("all_the_biomes"))
                .parent(enterEnd)
                .startDisplay(EndItems.AETERNIUM_BOOTS)
                .frame(FrameType.CHALLENGE)
                .endDisplay()
                .addVisitBiomesCriterion(EndBiome.getAllBeBiomes().stream().map(b -> b.getBiomeKey()).toList())
                .requirements(RequirementsStrategy.AND)
                .rewardXP(1500)
                .build();

        ResourceLocation allElytras = AdvancementManager.Builder
                .create(BetterEnd.makeID("all_elytras"))
                .parent(root)
                .startDisplay(EndItems.CRYSTALITE_ELYTRA)
                .frame(FrameType.GOAL)
                .endDisplay()
                .addInventoryChangedCriterion("vanilla", Items.ELYTRA)
                .addInventoryChangedCriterion("crystalite", EndItems.CRYSTALITE_ELYTRA)
                .addInventoryChangedCriterion("armored", EndItems.ARMORED_ELYTRA)
                .requirements(RequirementsStrategy.AND)
                .build();

        ResourceLocation infusion = AdvancementManager.Builder
                .create(BetterEnd.makeID("infusion"))
                .parent(root)
                .startDisplay(EndBlocks.INFUSION_PEDESTAL)
                .endDisplay()
                .addInventoryChangedCriterion("infusion_pedestal", EndBlocks.INFUSION_PEDESTAL)
                .requirements(RequirementsStrategy.OR)
                .build();

        ResourceLocation infusionFinished = AdvancementManager.Builder
                .create(BetterEnd.makeID("infusion_finished"))
                .parent(infusion)
                .startDisplay(Items.ENDER_EYE)
                .frame(FrameType.GOAL)
                .endDisplay()
                .addCriterion("finished", BECriteria.INFUSION_FINISHED_TRIGGER)
                .requirements(RequirementsStrategy.OR)
                .build();
    }
}
