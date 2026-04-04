package com.aeternal.iuadditions.recipe;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.material.Fluid;

import java.util.List;

public class InputHandler implements IInputHandler {

    public InputHandler() {
    }

    public IInputItemStack getInput(ItemStack stack) {
        return new InputItemStack(stack);
    }

    @Override
    public IInputItemStack getInput(ItemStack[] stacks) {
        if (stacks.length == 1)
            return new InputItemStack(stacks[0]);
        else {
            if (!stacks[0].getTags().findAny().isEmpty()) {
                TagKey<Item> tag = stacks[0].getTags().toList().get(0);
                return new InputOreDict(tag, stacks[0].getCount());
            }
            return new InputItemStack(stacks[0]); 
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public IInputItemStack getInput(final Object var1) {
        if (var1 instanceof IInputItemStack)
            return (IInputItemStack) var1;
        if (var1 instanceof ItemStack)
            return this.getInput((ItemStack) var1);
        if (var1 instanceof String)
            return this.getInput((String) var1);
        if (var1 instanceof Item)
            return this.getInput(new ItemStack((Item) var1));
        if (var1 instanceof TagKey)
            return this.getInput((TagKey<Item>) var1);
        if (var1 instanceof Ingredient) {
            Ingredient ingredient = (Ingredient) var1;
            return this.getInput(ingredient.getItems());
        }
        if (var1 instanceof List) {
            List<?> list = (List<?>) var1;
            boolean allItemStacks = list.stream().allMatch(e -> e instanceof ItemStack);
            if (allItemStacks) {
                List<ItemStack> itemStacks = (List<ItemStack>) list;
                return this.getInput(itemStacks.toArray(new ItemStack[0]));
            }
            List<TagKey<Item>> var2 = (List<TagKey<Item>>) var1;
            TagKey<Item> mainTag = var2.get(0);
            return this.getInput(mainTag.location().toString());
        }
        return null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public IInputItemStack getInput(final Object var1, int i) {
        if (var1 instanceof ItemStack)
            return this.getInput((ItemStack) var1, i);
        if (var1 instanceof String)
            return this.getInput((String) var1, i);
        if (var1 instanceof TagKey)
            return this.getInput((TagKey<Item>) var1, i);
        return null;
    }

    public IInputItemStack getInput(TagKey<Item> name, int i) {
        return new InputOreDict(name, i);
    }

    public IInputItemStack getInput(TagKey<Item> name) {
        return new InputOreDict(name, 1);
    }

    public IInputItemStack getInput(ItemStack stack, int amount) {
        return new InputItemStack(stack, amount);
    }

    public IInputItemStack getInput(String name) {
        return new InputOreDict(name);
    }

    public IInputItemStack getInput(String name, int amount) {
        return new InputOreDict(name, amount);
    }

    public IInputItemStack getInput(String name, int amount, int metaOverride) {
        return new InputOreDict(name, amount, metaOverride);
    }

    @Override
    public IInputItemStack getInput(Fluid fluid) {
        throw new UnsupportedOperationException("Fluids are not supported in shaped/shapeless recipes.");
    }

    @Override
    public IInputItemStack getInput(Fluid fluid, int amount) {
        throw new UnsupportedOperationException("Fluids are not supported in shaped/shapeless recipes.");
    }
}
