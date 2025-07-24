package com.aeternal.api.recipe;

import com.aeternal.Core;
import com.brandon3055.draconicevolution.api.fusioncrafting.IFusionRecipe;
import com.brandon3055.draconicevolution.api.fusioncrafting.SimpleFusionRecipe;
import com.brandon3055.draconicevolution.lib.RecipeManager;
import com.denfop.api.Recipes;
import com.denfop.api.recipe.BaseMachineRecipe;
import com.denfop.api.recipe.Input;
import com.denfop.api.recipe.RecipeOutput;
import com.denfop.recipe.IInputItemStack;
import com.denfop.recipe.InputItemStack;
import com.denfop.tiles.mechanism.dual.heat.TileAlloySmelter;
import com.denfop.utils.ModUtils;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class RecipeHelper {

    public static ItemStack setCount(ItemStack itemStack, int count) {
        itemStack.setCount(count);
        return itemStack;
    }

    public static void addAlloySmelter(ItemStack input0, ItemStack input1, ItemStack output, int temp) {
        TileAlloySmelter.addAlloysmelter(new InputItemStack(input0), new InputItemStack(input1), output, temp);
    }

    public static void addPerfectAlloySmelter(ItemStack input1, ItemStack input2, ItemStack input3, ItemStack input4, ItemStack input5,ItemStack output, int temperature) {
        if (temperature >= Short.MAX_VALUE) {
            Core.LOGGER.error("Oh, it's hot, the temperature is too high!");
        } else {
            NBTTagCompound nbt = ModUtils.nbt();
            nbt.setShort("temperature", (short) temperature);
            Recipes.recipes.addRecipe("peralloysmelter", new BaseMachineRecipe(new Input(new IInputItemStack[]{
                    new InputItemStack(input1),
                    new InputItemStack(input2),
                    new InputItemStack(input3),
                    new InputItemStack(input4),
                    new InputItemStack(input5)
            }), new RecipeOutput(nbt, output)));
        }
    }

    public static void addDEFusion(ItemStack result, ItemStack catalyst, long energyCost, int craftingTier, Object... ingredients) {
        IFusionRecipe recipe = new SimpleFusionRecipe(result, catalyst, energyCost / ingredients.length, craftingTier, ingredients);
        RecipeManager.FUSION_REGISTRY.add(recipe);
    }

    public static void addRolling(ItemStack input, ItemStack output) {
        Recipes.recipes.addRecipe(
                "rolling",
                new BaseMachineRecipe(new Input(new InputItemStack(input)),
                new RecipeOutput((NBTTagCompound) null, output))
        );
    }

}
