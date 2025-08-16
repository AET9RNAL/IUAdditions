// File: CTCrystalGrower.java
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
 * mods.industrialupgrade.CrystalGrower.removeRecipe(IItemStack);
 * mods.industrialupgrade.CrystalGrower.addRecipe(IItemStack_out, IItemStack_in0, IItemStack_in1);
 */
@ZenClass("mods.industrialupgrade.CrystalGrower")
@ModOnly("industrialupgrade")
@ZenRegister
public class CTCrystalGrower {

    private static final String KEY = "silicon_recipe";

    @ZenMethod
    public static void removeRecipe(IItemStack output) {
        CraftTweakerAPI.apply(new Remove(output));
    }

    @ZenMethod
    public static void addRecipe(IItemStack output, IItemStack inputA, IItemStack inputB) {
        if (output == null || inputA == null || inputB == null) {
            CraftTweakerAPI.logError("CrystalGrower.addRecipe: output, inputA and inputB cannot be null");
            return;
        }
        CraftTweakerAPI.apply(new AddAction(getItemStack(inputA), getItemStack(inputB), getItemStack(output)));
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
            return "Removing CrystalGrower recipe(s) with output: " + output;
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(output);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof Remove)) return false;
            Remove other = (Remove) obj;
            return Objects.equals(this.output, other.output);
        }
    }

    private static class AddAction implements IAction {
        private final ItemStack inA;
        private final ItemStack inB;
        private final ItemStack out;

        private AddAction(ItemStack inA, ItemStack inB, ItemStack out) {
            this.inA = inA == null ? ItemStack.EMPTY : inA.copy();
            this.inB = inB == null ? ItemStack.EMPTY : inB.copy();
            this.out = out == null ? ItemStack.EMPTY : out.copy();
        }

        @Override
        public void apply() {
            final IInputHandler ih = com.denfop.api.Recipes.inputFactory;
            Recipes.recipes.addAdderRecipe(KEY,
                    new BaseMachineRecipe(
                            new Input(
                                    ih.getInput(inA),
                                    ih.getInput(inB)
                            ),
                            new RecipeOutput(null, out)
                    )
            );
        }

        @Override
        public String describe() {
            return "Adding CrystalGrower recipe: " + Arrays.asList(inA, inB) + " -> " + out;
        }

        @Override
        public int hashCode() {
            return Objects.hash(inA, inB, out);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof AddAction)) return false;
            AddAction other = (AddAction) obj;
            return Objects.equals(this.inA, other.inA) &&
                    Objects.equals(this.inB, other.inB) &&
                    Objects.equals(this.out, other.out);
        }
    }

    private static ItemStack getItemStack(IItemStack item) {
        if (item == null) return ItemStack.EMPTY;
        Object internal = CraftTweakerMC.getItemStack(item);
        if (!(internal instanceof ItemStack)) {
            CraftTweakerAPI.logError("CrystalGrower: not a valid item stack: " + item);
            return ItemStack.EMPTY;
        }
        ItemStack st = (ItemStack) internal;
        return new ItemStack(st.getItem(), item.getAmount(), item.getDamage());
    }
}
