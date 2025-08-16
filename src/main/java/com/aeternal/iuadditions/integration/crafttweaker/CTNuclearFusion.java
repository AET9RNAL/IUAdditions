// File: CTNuclearFusion.java
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
import net.minecraft.nbt.NBTTagCompound;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import java.util.Arrays;
import java.util.Objects;

/**
 * Zen usage:
 * mods.industrialupgrade.NuclearFusion.removeRecipe(IItemStack);
 * mods.industrialupgrade.NuclearFusion.addRecipe(IItemStack_out, IItemStack_in, IItemStack_in, Int_chance, Int_rad);
 */
@ZenClass("mods.industrialupgrade.NuclearFusion")
@ModOnly("industrialupgrade")
@ZenRegister
public class CTNuclearFusion {

    private static final String KEY = "synthesis";

    @ZenMethod
    public static void removeRecipe(IItemStack output) {
        CraftTweakerAPI.apply(new Remove(output));
    }

    /**
     * Add a synthesis recipe.
     * @param output Resulting item
     * @param inputA First input item
     * @param inputB Second input item
     * @param percent Synthesis chance in percent (integer)
     * @param radAmount Radiation amount consumed per operation (integer)
     */
    @ZenMethod
    public static void addRecipe(IItemStack output, IItemStack inputA, IItemStack inputB, int percent, int radAmount) {
        if (output == null || inputA == null || inputB == null) {
            CraftTweakerAPI.logError("NuclearFusion.addRecipe: output, inputA, and inputB cannot be null");
            return;
        }
        final NBTTagCompound nbt = new NBTTagCompound();
        nbt.setInteger("percent", percent);
        nbt.setInteger("rad_amount", radAmount);

        CraftTweakerAPI.apply(new AddAction(
                getItemStack(inputA),
                getItemStack(inputB),
                getItemStack(output),
                nbt
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
            return "Removing NuclearFusion (synthesis) recipe(s) with output: " + output;
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
        private final ItemStack inA;
        private final ItemStack inB;
        private final ItemStack output;
        private final NBTTagCompound nbt;

        private AddAction(ItemStack inA, ItemStack inB, ItemStack output, NBTTagCompound nbt) {
            this.inA = inA == null ? ItemStack.EMPTY : inA.copy();
            this.inB = inB == null ? ItemStack.EMPTY : inB.copy();
            this.output = output == null ? ItemStack.EMPTY : output.copy();
            this.nbt = nbt;
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
                            new RecipeOutput(nbt, output)
                    )
            );
        }

        @Override
        public String describe() {
            return "Adding NuclearFusion (synthesis) recipe: " +
                    Arrays.asList(inA, inB) + " -> " + output +
                    " {percent=" + (nbt != null ? nbt.getInteger("percent") : "n/a") +
                    ", rad_amount=" + (nbt != null ? nbt.getInteger("rad_amount") : "n/a") + "}";
        }

        @Override
        public int hashCode() {
            return Objects.hash(inA, inB, output, nbt);
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) return true;
            if (obj == null || obj.getClass() != getClass()) return false;
            AddAction other = (AddAction) obj;
            return Objects.equals(this.inA, other.inA) &&
                    Objects.equals(this.inB, other.inB) &&
                    Objects.equals(this.output, other.output) &&
                    Objects.equals(this.nbt, other.nbt);
        }
    }

    private static ItemStack getItemStack(IItemStack item) {
        if (item == null) return ItemStack.EMPTY;
        Object internal = CraftTweakerMC.getItemStack(item);
        if (!(internal instanceof ItemStack)) {
            CraftTweakerAPI.logError("NuclearFusion: not a valid item stack: " + item);
            return ItemStack.EMPTY;
        }
        ItemStack st = (ItemStack) internal;
        return new ItemStack(st.getItem(), item.getAmount(), item.getDamage());
    }
}
