package org.betterx.betterend.integration.emi;

import org.betterx.bclib.integration.emi.EMIAbstractAlloyingRecipe;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.item.crafting.BlastingRecipe;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeType;

import dev.emi.emi.api.EmiRegistry;

public class EMIBlastingRecipe extends EMIAbstractAlloyingRecipe<Container, BlastingRecipe> {
    public EMIBlastingRecipe(BlastingRecipe recipe) {
        super(recipe, new ResourceLocation(
                "emi",
                recipe.getId().getNamespace() + "/" + recipe.getId().getPath() + "/allloying"
        ), 2, false);
    }

    @Override
    protected int getSmeltTime() {
        return recipe.getCookingTime();
    }

    @Override
    protected float getExperience() {
        return recipe.getExperience();
    }

    static void addAllRecipes(EmiRegistry emiRegistry, RecipeManager manager) {
        for (BlastingRecipe recipe : manager.getAllRecipesFor(RecipeType.BLASTING)) {
            emiRegistry.addRecipe(new EMIBlastingRecipe(recipe));
        }
    }
}
