// File: CTUpgradeMachineFactory.java
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
 * mods.industrialupgrade.UpgradeMachineFactory.removeRecipe(IItemStack);
 * mods.industrialupgrade.UpgradeMachineFactory.addRecipe(IItemStack_out,
 * <in0>, <in1>, <in2>,
 * <in3>, <in4>, <in5>,
 * <in6>, <in7>, <in8>);
 */
@ZenClass("mods.industrialupgrade.UpgradeMachineFactory")
@ModOnly("industrialupgrade")
@ZenRegister
public class CTUpgradeMachineFactory {

    private static final String KEY = "upgrade_machine";

    @ZenMethod
    public static void removeRecipe(IItemStack output) {
        CraftTweakerAPI.apply(new Remove(output));
    }

    /** Add a recipe: 9 input items -> 1 output item. */
    @ZenMethod
    public static void addRecipe(IItemStack output,
                                 IItemStack in0, IItemStack in1, IItemStack in2,
                                 IItemStack in3, IItemStack in4, IItemStack in5,
                                 IItemStack in6, IItemStack in7, IItemStack in8) {
        if (output == null || in0 == null || in1 == null || in2 == null ||
                in3 == null || in4 == null || in5 == null ||
                in6 == null || in7 == null || in8 == null) {
            CraftTweakerAPI.logError("UpgradeMachineFactory.addRecipe: output and all 9 inputs are required and cannot be null");
            return;
        }
        CraftTweakerAPI.apply(new AddAction(
                get(in0), get(in1), get(in2), get(in3), get(in4),
                get(in5), get(in6), get(in7), get(in8),
                get(output)
        ));
    }

    private static class Remove implements IAction {
        private final IItemStack output;

        private Remove(IItemStack output) {
            this.output = output;
        }

        @Override
        public void apply() {
            Recipes.recipes.addRemoveRecipe(KEY, get(output));
        }

        @Override
        public String describe() {
            return "Removing UpgradeMachineFactory recipe(s) with output: " + output;
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
        private final ItemStack in0, in1, in2, in3, in4, in5, in6, in7, in8;
        private final ItemStack out;

        private AddAction(ItemStack in0, ItemStack in1, ItemStack in2,
                          ItemStack in3, ItemStack in4, ItemStack in5,
                          ItemStack in6, ItemStack in7, ItemStack in8,
                          ItemStack out) {
            this.in0 = safe(in0);
            this.in1 = safe(in1);
            this.in2 = safe(in2);
            this.in3 = safe(in3);
            this.in4 = safe(in4);
            this.in5 = safe(in5);
            this.in6 = safe(in6);
            this.in7 = safe(in7);
            this.in8 = safe(in8);
            this.out = safe(out);
        }

        @Override
        public void apply() {
            final IInputHandler ih = com.denfop.api.Recipes.inputFactory;
            Recipes.recipes.addAdderRecipe(KEY,
                    new BaseMachineRecipe(
                            new Input(
                                    ih.getInput(in0),
                                    ih.getInput(in1),
                                    ih.getInput(in2),
                                    ih.getInput(in3),
                                    ih.getInput(in4),
                                    ih.getInput(in5),
                                    ih.getInput(in6),
                                    ih.getInput(in7),
                                    ih.getInput(in8)
                            ),
                            new RecipeOutput(null, out)
                    )
            );
        }

        @Override
        public String describe() {
            return "Adding UpgradeMachineFactory recipe: " +
                    Arrays.asList(in0,in1,in2,in3,in4,in5,in6,in7,in8) + " -> " + out;
        }

        @Override
        public int hashCode() {
            return Objects.hash(in0,in1,in2,in3,in4,in5,in6,in7,in8,out);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof AddAction)) return false;
            AddAction other = (AddAction) obj;
            return Objects.equals(this.in0, other.in0) &&
                    Objects.equals(this.in1, other.in1) &&
                    Objects.equals(this.in2, other.in2) &&
                    Objects.equals(this.in3, other.in3) &&
                    Objects.equals(this.in4, other.in4) &&
                    Objects.equals(this.in5, other.in5) &&
                    Objects.equals(this.in6, other.in6) &&
                    Objects.equals(this.in7, other.in7) &&
                    Objects.equals(this.in8, other.in8) &&
                    Objects.equals(this.out, other.out);
        }

        private static ItemStack safe(ItemStack s) { return (s == null ? ItemStack.EMPTY : s.copy()); }
    }


    private static ItemStack get(IItemStack ct) {
        if (ct == null) return ItemStack.EMPTY;
        Object internal = CraftTweakerMC.getItemStack(ct);
        if (!(internal instanceof ItemStack)) {
            CraftTweakerAPI.logError("UpgradeMachineFactory: not a valid item stack: " + ct);
            return ItemStack.EMPTY;
        }
        ItemStack st = (ItemStack) internal;
        return new ItemStack(st.getItem(), ct.getAmount(), ct.getDamage());
    }
}
