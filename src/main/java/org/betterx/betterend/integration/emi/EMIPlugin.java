package org.betterx.betterend.integration.emi;

import org.betterx.bclib.BCLib;
import org.betterx.betterend.BetterEnd;
import org.betterx.betterend.recipe.builders.AlloyingRecipe;
import org.betterx.betterend.registry.EndBlocks;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeManager;

import dev.emi.emi.api.EmiPlugin;
import dev.emi.emi.api.EmiRegistry;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.stack.EmiStack;

public class EMIPlugin implements EmiPlugin {
    public static final ResourceLocation MY_SPRITE_SHEET = BetterEnd.makeID(
            "textures/gui/emi_simplified_textures.png"
    );

    public static final EmiStack END_SMELTER_WORKSTATION = EmiStack.of(EndBlocks.END_STONE_SMELTER);
    public static final EmiRecipeCategory END_SMELTER_CATEGORY = new EmiRecipeCategory(
            BCLib.makeID("end_stone_smelter"),
            END_SMELTER_WORKSTATION//,
            //new EmiTexture(MY_SPRITE_SHEET, 0, 0, 16, 16)
    );

    @Override
    public void register(EmiRegistry emiRegistry) {
        emiRegistry.addCategory(END_SMELTER_CATEGORY);

        // Add all the workstations your category uses
        emiRegistry.addWorkstation(END_SMELTER_CATEGORY, END_SMELTER_WORKSTATION);

        RecipeManager manager = emiRegistry.getRecipeManager();
        for (AlloyingRecipe recipe : manager.getAllRecipesFor(AlloyingRecipe.TYPE)) {
            emiRegistry.addRecipe(new EMIAlloyingRecipe(recipe));
        }
    }
}
