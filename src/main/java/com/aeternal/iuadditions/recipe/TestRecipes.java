package com.aeternal.iuadditions.recipe;

import com.aeternal.iuadditions.api.annotation.AutoRecipeProvider;
import com.aeternal.iuadditions.api.Recipes;
import net.minecraft.world.item.Items;

@AutoRecipeProvider
public class TestRecipes {
    public TestRecipes() {
        // Test Shapeless Recipe
        Recipes.recipe.addShapelessRecipe(Items.DIAMOND, Items.DIRT, Items.DIRT);
        
        // Test Shaped Recipe
        Recipes.recipe.addRecipe(Items.DIAMOND_SWORD, 
            " A ", 
            " A ", 
            " B ", 
            'A', Items.DIAMOND, 
            'B', Items.STICK
        );
    }
}
