package com.aeternal.iuadditions.integration.crafttweaker;

import com.denfop.api.Recipes;
import com.denfop.api.recipe.BaseMachineRecipe;
import com.denfop.api.recipe.Input;
import com.denfop.api.recipe.RecipeOutput;
import com.denfop.integration.crafttweaker.BaseAction;
import com.denfop.integration.crafttweaker.InputItemStack;
import crafttweaker.CraftTweakerAPI;
import crafttweaker.annotations.ModOnly;
import crafttweaker.annotations.ZenRegister;
import crafttweaker.api.item.IIngredient;
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.minecraft.CraftTweakerMC;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

/**
 * Zen usage:
 * mods.industrialupgrade.Programming.removeRecipe(IItemStack);
 * mods.industrialupgrade.Programming.addRecipe(IItemStack_out, IItemStack_in);
 */
@ZenClass("mods.industrialupgrade.Programming")
@ModOnly("industrialupgrade")
@ZenRegister
public class CTProgramming {
    private static final String KEY = "programming";
    @ZenMethod
    public static void addRecipe(IItemStack output, IIngredient container) {
        Recipes.recipes.addAdderRecipe(
                KEY,
                new BaseMachineRecipe(
                        new Input(
                                new InputItemStack(container)
                        ),
                        new RecipeOutput(null, CraftTweakerMC.getItemStacks(output))
                )
        );


    }


    @ZenMethod
    public static void remove(IItemStack output) {
        CraftTweakerAPI.apply(new CTProgramming.Remove(output));
    }


    private static class Remove extends BaseAction {

        private final IItemStack output;

        public Remove(IItemStack output) {
            super(KEY);
            this.output = output;
        }

        public void apply() {
            Recipes.recipes.addRemoveRecipe(KEY, CraftTweakerMC.getItemStack(output));

        }


    }

}
