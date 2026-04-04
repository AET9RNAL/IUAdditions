package com.aeternal.iuadditions.recipe;

import com.aeternal.iuadditions.api.Recipes;
import com.aeternal.iuadditions.api.crafting.BaseRecipe;
import com.aeternal.iuadditions.api.crafting.BaseShapelessRecipe;
import com.aeternal.iuadditions.api.crafting.PartRecipe;
import com.aeternal.iuadditions.api.crafting.RecipeGrid;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CraftingManager {

    public CraftingManager() {
    }

    public BaseRecipe addRecipe(RegistryObject<? extends Item> output, Object... input) {
        return addRecipe(new ItemStack(output.get()), input);
    }

    public BaseRecipe addRecipe(Item output, Object... input) {
        return addRecipe(new ItemStack(output), input);
    }

    public BaseRecipe addRecipe(String output, Object... input) {
        return addRecipe(Recipes.inputFactory.getInput(output).getInputs().get(0), input);
    }

    public BaseRecipe addRecipe(ItemStack output, Object... input) {
        List<Object> objects = Arrays.asList(input);
        List<String> list = new ArrayList<>();
        List<PartRecipe> objectMap = new ArrayList<>();
        int i = 0;
        for (; i < 3; i++) {
            if (objects.get(i) instanceof String) {
                list.add((String) objects.get(i));
            } else {
                break;
            }
        }
        RecipeGrid grid = new RecipeGrid(list);
        for (; i < objects.size(); i += 2) {
            Character character = (Character) objects.get(i);
            if (grid.getCharactersList().contains(character)) {
                Object object = objects.get(i + 1);
                IInputItemStack recipeInput = getRecipeInput(object);
                objectMap.add(new PartRecipe(character.toString(), recipeInput));
            }
        }
        return new BaseRecipe(output, grid, objectMap);
    }

    public BaseShapelessRecipe addShapelessRecipe(ItemStack output, Object... input) {
        List<IInputItemStack> recipeInputList = new ArrayList<>();
        for (Object object : input) {
            IInputItemStack recipeInput = getRecipeInput(object);
            recipeInputList.add(recipeInput);
        }
        return new BaseShapelessRecipe(output, recipeInputList);
    }

    public BaseShapelessRecipe addShapelessRecipe(Item output, Object... input) {
        return addShapelessRecipe(new ItemStack(output), input);
    }

    public BaseShapelessRecipe addShapelessRecipe(RegistryObject<? extends Item> output, Object... input) {
        return addShapelessRecipe(new ItemStack(output.get()), input);
    }

    public BaseShapelessRecipe addShapelessRecipe(Block output, Object... input) {
        return addShapelessRecipe(new ItemStack(output), input);
    }

    private IInputItemStack getRecipeInput(Object o) {
        if (o == null) {
            throw new NullPointerException("Null recipe input object.");
        } else if (o instanceof IInputItemStack) {
            return (IInputItemStack) o;
        } else if (o instanceof RegistryObject) {
            Object reg = ((RegistryObject<?>) o).get();
            if (reg instanceof Item) {
                return Recipes.inputFactory.getInput(new ItemStack((Item) reg));
            } else if (reg instanceof Block) {
                return Recipes.inputFactory.getInput(new ItemStack((Block) reg));
            }
        } else if (o instanceof ItemStack) {
            return Recipes.inputFactory.getInput((ItemStack) o);
        } else if (o instanceof Block) {
            return Recipes.inputFactory.getInput(new ItemStack((Block) o));
        } else if (o instanceof Item) {
            return Recipes.inputFactory.getInput(new ItemStack((Item) o));
        } else if (o instanceof String) {
            return Recipes.inputFactory.getInput((String) o);
        }
        throw new RuntimeException("Unsupported recipe input formatting. Use String, ItemStack, Item, or Block.");
    }

}
