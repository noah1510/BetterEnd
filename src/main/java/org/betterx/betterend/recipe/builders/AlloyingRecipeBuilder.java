package org.betterx.betterend.recipe.builders;

import org.betterx.bclib.recipes.AlloyingRecipe;
import org.betterx.betterend.BetterEnd;
import org.betterx.betterend.config.Configs;

public class AlloyingRecipeBuilder extends AlloyingRecipe.Builder {
    private AlloyingRecipeBuilder() {
    }

    public static AlloyingRecipe.Builder create(String id) {
        return AlloyingRecipe.Builder.create(BetterEnd.MOD_ID, id, Configs.RECIPE_CONFIG);
    }
}
