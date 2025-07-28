package com.aeternal.integration.draconicevolution.recipe;

import com.aeternal.Constants;
import com.brandon3055.draconicevolution.api.fusioncrafting.IFusionRecipe;
import com.brandon3055.draconicevolution.api.fusioncrafting.SimpleFusionRecipe;
import com.brandon3055.draconicevolution.lib.RecipeManager;
import net.minecraft.item.ItemStack;

public class DERecipeHelper {

    public static void addDEFusion(ItemStack result, ItemStack catalyst, long energyCost, int craftingTier, Object... ingredients) {
        if (Constants.DE_LOADED) {
            IFusionRecipe recipe = new SimpleFusionRecipe(result, catalyst, energyCost / ingredients.length, craftingTier, ingredients);
            RecipeManager.FUSION_REGISTRY.add(recipe);
        }
    }

}
