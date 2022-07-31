package org.betterx.betterend.advancements;

import org.betterx.betterend.BetterEnd;

import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.PlayerTrigger;

import net.fabricmc.fabric.api.object.builder.v1.advancement.CriterionRegistry;

public class BECriteria {
    public static PlayerTrigger PORTAL_ON;
    public static PlayerTrigger PORTAL_TRAVEL;

    public static PlayerTrigger.TriggerInstance PORTAL_ON_TRIGGER;
    public static PlayerTrigger.TriggerInstance PORTAL_TRAVEL_TRIGGER;


    public static void register() {
        PORTAL_ON = CriterionRegistry.register(new PlayerTrigger(BetterEnd.makeID("portal_on")));
        PORTAL_TRAVEL = CriterionRegistry.register(new PlayerTrigger(BetterEnd.makeID("portal_travel")));

        PORTAL_ON_TRIGGER = new PlayerTrigger.TriggerInstance(
                PORTAL_ON.getId(),
                EntityPredicate.Composite.ANY
        );
        PORTAL_TRAVEL_TRIGGER = new PlayerTrigger.TriggerInstance(
                PORTAL_TRAVEL.getId(),
                EntityPredicate.Composite.ANY
        );
    }
}
