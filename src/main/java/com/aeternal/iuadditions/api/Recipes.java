package com.aeternal.iuadditions.api;

import com.aeternal.iuadditions.recipe.CraftingManager;
import com.aeternal.iuadditions.recipe.IInputHandler;
import com.aeternal.iuadditions.recipe.InputHandler;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.world.item.crafting.Recipe;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

public class Recipes {

    public static IInputHandler inputFactory = new InputHandler();
    public static CraftingManager recipe = new CraftingManager();
    static Map<String, Recipe<?>> recipeMap = new ConcurrentHashMap<>();
    private static int recipeID = 0;

    public static void registerRecipe(Consumer<FinishedRecipe> consumer, RecipeBuilder recipe, String id) {
        try {
            recipe.save(consumer, id);
        } catch (Exception e) {
            System.out.println(recipe);
        }
    }

    public static CraftingManager getRecipe() {
        return recipe;
    }

    public static Map<String, Recipe<?>> getRecipeMap() {
        return recipeMap;
    }

    public static String registerRecipe(Recipe<?> Recipe) {
        String id = "iuadditions:" + "iuadditions_" + recipeID++;
        recipeMap.put(id, Recipe);
        return id;
    }
}
