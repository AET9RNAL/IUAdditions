package com.aeternal.iuadditions.datagen;

import com.aeternal.iuadditions.api.Recipes;
import com.aeternal.iuadditions.api.crafting.BaseRecipe;
import com.aeternal.iuadditions.api.crafting.BaseShapelessRecipe;
import com.aeternal.iuadditions.api.crafting.PartRecipe;
import com.aeternal.iuadditions.recipe.IInputItemStack;
import com.aeternal.iuadditions.recipe.IngredientInput;
import com.aeternal.iuadditions.api.annotation.AnnotationScanner;
import com.aeternal.iuadditions.api.annotation.AutoRecipeProvider;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraftforge.common.crafting.PartialNBTIngredient;
import net.minecraftforge.common.crafting.StrictNBTIngredient;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class RecipeProvider extends net.minecraft.data.recipes.RecipeProvider {
    
    public RecipeProvider(DataGenerator generator) {
        super(generator);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {
        // Scan and invoke all @AutoRecipeProvider classes to populate Recipes.recipeMap
        List<Class<?>> recipeProviders = AnnotationScanner.findAnnotatedClasses(AutoRecipeProvider.class);
        for (Class<?> clazz : recipeProviders) {
            try {
                // Assuming zero-args constructor for initialization like dataset static init did
                clazz.getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                System.err.println("Failed to initialize @AutoRecipeProvider class: " + clazz.getName());
                e.printStackTrace();
            }
        }

        Map<String, Recipe<?>> map = Recipes.getRecipeMap();
        for (Map.Entry<String, Recipe<?>> entry : map.entrySet()) {
            try {
                String id = entry.getKey();
                Recipe<?> recipe = entry.getValue();
                if (recipe instanceof BaseRecipe) {
                    BaseRecipe baseRecipe = (BaseRecipe) recipe;
                    ShapedRecipeBuilder shaped = ShapedRecipeBuilder.shaped(baseRecipe.getOutput().getItem(), baseRecipe.getOutput().getCount());
                    baseRecipe.getRecipeGrid().getGrids().get(0).forEach(shaped::pattern);
                    for (PartRecipe partRecipe : baseRecipe.getPartRecipe()) {
                        Character character = partRecipe.getIndex().charAt(0);
                        IInputItemStack recipeInput = partRecipe.getInput();
                        if (!recipeInput.getInputs().isEmpty() && recipeInput.getInputs().get(0).hasTag()) {
                            if (recipeInput.getInputs().size() == 1) {
                                shaped.define(character, StrictNBTIngredient.of(recipeInput.getInputs().get(0)));
                            } else {
                                List<Item> items = new ArrayList<>();
                                recipeInput.getInputs().forEach(stack -> items.add(stack.getItem()));
                                shaped.define(character, PartialNBTIngredient.of(recipeInput.getInputs().get(0).getTag(), items.toArray(new Item[0])));
                            }
                        } else {
                            shaped.define(character, new IngredientInput(recipeInput).getInput());
                        }
                    }
                    shaped.unlockedBy("any", InventoryChangeTrigger.TriggerInstance.hasItems(Items.AIR));
                    Recipes.registerRecipe(consumer, shaped, id.toLowerCase());
                } else if (recipe instanceof BaseShapelessRecipe) {
                    BaseShapelessRecipe baseShapelessRecipe = (BaseShapelessRecipe) recipe;
                    ShapelessRecipeBuilder shaped = ShapelessRecipeBuilder.shapeless(baseShapelessRecipe.getOutput().getItem(), baseShapelessRecipe.getOutput().getCount());
                    for (IInputItemStack recipeInput : baseShapelessRecipe.getRecipeInputList()) {
                        if (!recipeInput.getInputs().isEmpty() && recipeInput.getInputs().get(0).hasTag()) {
                            if (recipeInput.getInputs().size() == 1) {
                                shaped.requires(StrictNBTIngredient.of(recipeInput.getInputs().get(0)));
                            } else {
                                List<Item> items = new ArrayList<>();
                                recipeInput.getInputs().forEach(stack -> items.add(stack.getItem()));
                                shaped.requires(PartialNBTIngredient.of(recipeInput.getInputs().get(0).getTag(), items.toArray(new Item[0])));
                            }
                        } else {
                            shaped.requires(new IngredientInput(recipeInput).getInput());
                        }
                    }
                    shaped.unlockedBy("any", InventoryChangeTrigger.TriggerInstance.hasItems(Items.AIR));
                    Recipes.registerRecipe(consumer, shaped, id.toLowerCase());
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
