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

import static com.denfop.api.Recipes.inputFactory;

/**
 * Zen usage:
 * mods.industrialupgrade.ElectronicsAssembler.removeRecipe(IItemStack);
 * mods.industrialupgrade.ElectronicsAssembler.addRecipe(IItemStack_out, IItemStack_in, IItemStack_in, IItemStack_in, IItemStack_in, IItemStack_in);
 */
@ZenClass("mods.industrialupgrade.ElectronicsAssembler")
@ModOnly("industrialupgrade")
@ZenRegister
public class CTElectronicsAssembler {

    private static final String KEY = "electronics";


    @ZenMethod
    public static void removeRecipe(IItemStack output) {
        CraftTweakerAPI.apply(new Remove(output));
    }

    @ZenMethod
    public static void addRecipe(IItemStack output,
                                 IItemStack in1,
                                 IItemStack in2,
                                 IItemStack in3,
                                 IItemStack in4,
                                 IItemStack in5) {
        if (output == null || in1 == null || in2 == null || in3 == null || in4 == null || in5 == null) {
            CraftTweakerAPI.logError("ElectronicsAssembler.addRecipe: all six parameters (output + 5 inputs) are required and cannot be null");
            return;
        }
        CraftTweakerAPI.apply(new AddAction(
                getItemStack(in1),
                getItemStack(in2),
                getItemStack(in3),
                getItemStack(in4),
                getItemStack(in5),
                getItemStack(output)
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
            return "Removing ElectronicsAssembler recipe(s) with output: " + output;
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
        private final ItemStack in1, in2, in3, in4, in5;
        private final ItemStack output;

        private AddAction(ItemStack in1, ItemStack in2, ItemStack in3, ItemStack in4, ItemStack in5, ItemStack output) {
            this.in1 = safe(in1);
            this.in2 = safe(in2);
            this.in3 = safe(in3);
            this.in4 = safe(in4);
            this.in5 = safe(in5);
            this.output = safe(output);
        }

        @Override
        public void apply() {
            final IInputHandler ih = inputFactory;
            Recipes.recipes.addAdderRecipe(KEY,
                    new BaseMachineRecipe(
                            new Input(
                                    ih.getInput(in1),
                                    ih.getInput(in2),
                                    ih.getInput(in3),
                                    ih.getInput(in4),
                                    ih.getInput(in5)
                            ),
                            new RecipeOutput(null, output)
                    )
            );
        }

        @Override
        public String describe() {
            return "Adding ElectronicsAssembler recipe: "
                    + Arrays.asList(in1, in2, in3, in4, in5) + " -> " + output;
        }

        @Override
        public int hashCode() {
            return Objects.hash(in1, in2, in3, in4, in5, output);
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) return true;
            if (obj == null || obj.getClass() != getClass()) return false;
            AddAction other = (AddAction) obj;
            return Objects.equals(this.in1, other.in1) &&
                    Objects.equals(this.in2, other.in2) &&
                    Objects.equals(this.in3, other.in3) &&
                    Objects.equals(this.in4, other.in4) &&
                    Objects.equals(this.in5, other.in5) &&
                    Objects.equals(this.output, other.output);
        }

        private static ItemStack safe(ItemStack st) {
            return (st == null ? ItemStack.EMPTY : st.copy());
        }
    }


    private static ItemStack getItemStack(IItemStack item) {
        if (item == null) return ItemStack.EMPTY;
        Object internal = CraftTweakerMC.getItemStack(item);
        if (!(internal instanceof ItemStack)) {
            CraftTweakerAPI.logError("ElectronicsAssembler: not a valid item stack: " + item);
            return ItemStack.EMPTY;
        }
        ItemStack st = (ItemStack) internal;
        return new ItemStack(st.getItem(), item.getAmount(), item.getDamage());
    }
}
