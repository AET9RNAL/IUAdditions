// File: CTCrystalCharger.java
package com.aeternal.iuadditions.integration.crafttweaker;

import com.denfop.api.Recipes;
import com.denfop.api.recipe.BaseMachineRecipe;
import com.denfop.api.recipe.Input;
import com.denfop.api.recipe.RecipeOutput;
import com.denfop.recipe.IInputHandler;
import crafttweaker.CraftTweakerAPI;
import crafttweaker.IAction;
import crafttweaker.annotations.ModOnly;
import crafttweaker.annotations.ZenRegister;
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.minecraft.CraftTweakerMC;
import net.minecraft.item.ItemStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import java.util.Arrays;
import java.util.Objects;

/**
 * Zen usage:
 * mods.industrialupgrade.CrystalCharger.removeRecipe(<modid:out_item>);
 * mods.industrialupgrade.CrystalCharger.addRecipe(<modid:out_item>, <modid:in_item>);
 */
@ZenClass("mods.industrialupgrade.CrystalCharger")
@ModOnly("industrialupgrade")
@ZenRegister
public class CTCrystalCharger {

    private static final String KEY = "charger";

    @ZenMethod
    public static void removeRecipe(IItemStack output) {
        CraftTweakerAPI.apply(new Remove(output));
    }


    @ZenMethod
    public static void addRecipe(IItemStack output, IItemStack input) {
        if (input == null || output == null) {
            CraftTweakerAPI.logError("CrystalCharger.addRecipe: input and output cannot be null");
            return;
        }
        CraftTweakerAPI.apply(new AddAction(
                getItemStack(input),
                new ItemStack[]{ getItemStack(output) }
        ));
    }

    private static class Remove implements IAction {
        private final IItemStack output;

        private Remove(IItemStack output) {
            this.output = output;
        }

        @Override
        public void apply() {
            Recipes.recipes.addRemoveRecipe(KEY, getItemStack(output));
        }

        @Override
        public String describe() {
            return "Removing CrystalCharger recipe(s) with output: " + output;
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(output);
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) return true;
            if (obj == null || obj.getClass() != getClass()) return false;
            Remove other = (Remove) obj;
            return Objects.equals(this.output, other.output);
        }
    }

    private static class AddAction implements IAction {
        private final ItemStack input;
        private final ItemStack[] output;

        private AddAction(ItemStack input, ItemStack[] output) {
            this.input = input == null ? ItemStack.EMPTY : input.copy();
            this.output = output;
        }

        @Override
        public void apply() {
            final IInputHandler ih = com.denfop.api.Recipes.inputFactory;
            Recipes.recipes.addAdderRecipe(KEY,
                    new BaseMachineRecipe(
                            new Input(ih.getInput(input)),
                            new RecipeOutput(null, output)
                    )
            );
        }

        @Override
        public String describe() {
            return "Adding CrystalCharger recipe: " + input + " -> " + Arrays.toString(output);
        }

        @Override
        public int hashCode() {
            return Objects.hash(input, Arrays.hashCode(output));
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) return true;
            if (obj == null || obj.getClass() != getClass()) return false;
            AddAction other = (AddAction) obj;
            return Objects.equals(this.input, other.input) && Arrays.equals(this.output, other.output);
        }
    }

    private static ItemStack getItemStack(IItemStack item) {
        if (item == null) return ItemStack.EMPTY;
        Object internal = CraftTweakerMC.getItemStack(item);
        if (!(internal instanceof ItemStack)) {
            CraftTweakerAPI.logError("CrystalCharger: not a valid item stack: " + item);
            return ItemStack.EMPTY;
        }
        ItemStack st = (ItemStack) internal;
        return new ItemStack(st.getItem(), item.getAmount(), item.getDamage());
    }
}
