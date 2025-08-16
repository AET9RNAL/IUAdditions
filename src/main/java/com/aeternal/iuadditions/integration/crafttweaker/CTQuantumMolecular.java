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
import crafttweaker.api.item.IIngredient;
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.minecraft.CraftTweakerMC;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.oredict.OreDictionary;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import java.util.Arrays;
import java.util.Objects;

/**
 * Zen usage:
 * mods.industrialupgrade.QuantumMolecular.removeRecipe(IItemStack);
 * mods.industrialupgrade.QuantumMolecular.addRecipe(IItemStack_out, IItemStack_in0, IItemStack_in1);
 */
@ZenClass("mods.industrialupgrade.QuantumMolecular")
@ModOnly("industrialupgrade")
@ZenRegister
public class CTQuantumMolecular {
    private static final String KEY = "quantummolecular";
    @ZenMethod
    public static void removeRecipe(IItemStack output) {
        CraftTweakerAPI.apply(new Remove(output));
    }


    @ZenMethod
    public static void addRecipe(IItemStack output, IItemStack inputA, IItemStack inputB, double energy) {
        if (inputA == null || inputB == null) {
            CraftTweakerAPI.logError("QuantumMolecular.addRecipe: inputs cannot be null");
            return;
        }
        final NBTTagCompound tag = new NBTTagCompound();
        tag.setDouble("energy", energy);
        CraftTweakerAPI.apply(new AddQMAction(
                ingredient(inputA), ingredient(inputB),
                new ItemStack[]{getItemStack(output)},
                tag, false, false
        ));
    }

    @ZenMethod
    public static void addOre2Recipe(IItemStack output, IItemStack inputA, String oreB, double energy) {
        if (inputA == null || oreB == null) {
            CraftTweakerAPI.logError("QuantumMolecular.addOre2Recipe: inputA and oreB cannot be null");
            return;
        }
        final NBTTagCompound tag = new NBTTagCompound();
        tag.setDouble("energy", energy);
        CraftTweakerAPI.apply(new AddQMAction(
                ingredient(inputA), ore(oreB),
                new ItemStack[]{getItemStack(output)},
                tag, false, true
        ));
    }

    @ZenMethod
    public static void addOreRecipe(IItemStack output, String oreA, String oreB, double energy) {
        if (oreA == null || oreB == null) {
            CraftTweakerAPI.logError("QuantumMolecular.addOreRecipe: oreA and oreB cannot be null");
            return;
        }
        final NBTTagCompound tag = new NBTTagCompound();
        tag.setDouble("energy", energy);
        CraftTweakerAPI.apply(new AddQMAction(
                ore(oreA), ore(oreB),
                new ItemStack[]{getItemStack(output)},
                tag, true, true
        ));
    }

    @ZenMethod
    public static void addAnyRecipe(IItemStack output, IIngredient inputA, IIngredient inputB, double energy) {
        if (inputA == null || inputB == null) {
            CraftTweakerAPI.logError("QuantumMolecular.addAnyRecipe: inputs cannot be null");
            return;
        }
        final NBTTagCompound tag = new NBTTagCompound();
        tag.setDouble("energy", energy);
        // Resolve A
        InputToken tokA = resolve(inputA);
        InputToken tokB = resolve(inputB);
        CraftTweakerAPI.apply(new AddQMAction(
                tokA, tokB,
                new ItemStack[]{getItemStack(output)},
                tag, tokA.isOre, tokB.isOre
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
            return "Removing QuantumMolecular recipe(s) with output: " + output;
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

    private static class AddQMAction implements IAction {
        private final InputToken inA;
        private final InputToken inB;
        private final ItemStack[] output;
        private final NBTTagCompound tag;
        private final boolean oreA;
        private final boolean oreB;

        private AddQMAction(InputToken inA, InputToken inB, ItemStack[] output, NBTTagCompound tag, boolean oreA, boolean oreB) {
            this.inA = inA;
            this.inB = inB;
            this.output = output;
            this.tag = tag;
            this.oreA = oreA;
            this.oreB = oreB;
        }

        @Override
        public void apply() {
            IInputHandler ih = Recipes.inputFactory;
            Recipes.recipes.addAdderRecipe(KEY,
                    new BaseMachineRecipe(
                            new Input(
                                    inA.isOre ? ih.getInput(inA.oreName) : ih.getInput(inA.stack),
                                    inB.isOre ? ih.getInput(inB.oreName) : ih.getInput(inB.stack)
                            ),
                            new RecipeOutput(tag, output)
                    )
            );
        }

        @Override
        public String describe() {
            return "Adding QuantumMolecular recipe: "
                    + (oreA ? ("<ore:" + inA.oreName + ">") : inA.stack)
                    + " + "
                    + (oreB ? ("<ore:" + inB.oreName + ">") : inB.stack)
                    + " -> " + Arrays.toString(output)
                    + " (energy=" + (tag != null && tag.hasKey("energy") ? tag.getDouble("energy") : "n/a") + ")";
        }

        @Override
        public int hashCode() {
            int hash = 7;
            hash = 67 * hash + (inA != null ? inA.hashCode() : 0);
            hash = 67 * hash + (inB != null ? inB.hashCode() : 0);
            hash = 67 * hash + Arrays.hashCode(output);
            hash = 67 * hash + (tag != null ? tag.hashCode() : 0);
            hash = 67 * hash + (oreA ? 1 : 0);
            hash = 67 * hash + (oreB ? 1 : 0);
            return hash;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) return true;
            if (obj == null || obj.getClass() != getClass()) return false;
            AddQMAction other = (AddQMAction) obj;
            return Objects.equals(this.inA, other.inA) &&
                    Objects.equals(this.inB, other.inB) &&
                    Arrays.equals(this.output, other.output) &&
                    Objects.equals(this.tag, other.tag) &&
                    this.oreA == other.oreA &&
                    this.oreB == other.oreB;
        }
    }

    private static class InputToken {
        final boolean isOre;
        final String oreName; // only if isOre
        final ItemStack stack; // only if !isOre

        private InputToken(String oreName) {
            this.isOre = true;
            this.oreName = oreName;
            this.stack = ItemStack.EMPTY;
        }

        private InputToken(ItemStack stack) {
            this.isOre = false;
            this.oreName = null;
            this.stack = stack;
        }

        static InputToken ofOre(String name) {
            return new InputToken(name);
        }

        static InputToken ofStack(ItemStack st) {
            return new InputToken(st);
        }

        @Override
        public int hashCode() {
            return isOre ? Objects.hash(true, oreName) : Objects.hash(false, stack);
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) return true;
            if (obj == null || obj.getClass() != getClass()) return false;
            InputToken other = (InputToken) obj;
            return this.isOre == other.isOre &&
                    Objects.equals(this.oreName, other.oreName) &&
                    Objects.equals(this.stack, other.stack);
        }
    }

    private static InputToken ingredient(IItemStack item) {
        return InputToken.ofStack(getItemStack(item));
    }

    private static InputToken ore(String name) {
        return InputToken.ofOre(name);
    }

    private static InputToken resolve(IIngredient ing) {
        // Try ore dict first
        try {
            Object internal = ing.getInternal();
            if (internal instanceof ItemStack) {
                // If this stack has at least one ore id, prefer the first as an ore token
                int[] ids = OreDictionary.getOreIDs((ItemStack) internal);
                if (ids != null && ids.length > 0) {
                    String ore = OreDictionary.getOreName(ids[0]);
                    if (ore != null && !ore.isEmpty()) {
                        return InputToken.ofOre(ore);
                    }
                }
                // fallback to stack
                return InputToken.ofStack((ItemStack) internal);
            }
        } catch (Throwable t) {
            // ignore and fallback
        }
        // Fallback: expand to first itemstack from CT and use that
        IItemStack[] items = ing.getItems().toArray(new IItemStack[0]);
        if (items != null && items.length > 0) {
            return InputToken.ofStack(getItemStack(items[0]));
        }
        CraftTweakerAPI.logWarning("QuantumMolecular: could not resolve ingredient '" + ing + "', using AIR as placeholder");
        return InputToken.ofStack(ItemStack.EMPTY);
    }

    private static ItemStack getItemStack(IItemStack item) {
        if (item == null) return ItemStack.EMPTY;
        Object internal = CraftTweakerMC.getItemStack(item);
        if (!(internal instanceof ItemStack)) {
            CraftTweakerAPI.logError("Not a valid item stack: " + item);
            return ItemStack.EMPTY;
        }
        ItemStack st = (ItemStack) internal;
        return new ItemStack(st.getItem(), item.getAmount(), item.getDamage());
    }
}
