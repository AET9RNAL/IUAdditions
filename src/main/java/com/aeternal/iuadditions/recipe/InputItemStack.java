package com.aeternal.iuadditions.recipe;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.Collections;
import java.util.List;

public class InputItemStack implements IInputItemStack {
    public static InputItemStack EMPTY = new InputItemStack(ItemStack.EMPTY, 1, true);
    public final ItemStack input;
    public int amount;

    public InputItemStack(ItemStack input) {
        this(input, input.getCount());
    }

    public InputItemStack(ItemStack input, int amount) {
        this.input = input.copy();
        this.amount = amount;
    }

    public InputItemStack(CompoundTag compoundTag) {
        boolean exist = compoundTag.getBoolean("exist");
        if (exist) {
            this.input = ItemStack.of(compoundTag.getCompound("input"));
            this.amount = compoundTag.getInt("amount");
        } else {
            this.input = ItemStack.EMPTY;
            this.amount = 1;
        }
    }

    InputItemStack(ItemStack input, int amount, boolean f) {
        this.input = input.copy();
        this.amount = amount;
    }

    @Override
    public CompoundTag writeNBT() {
        CompoundTag compoundTag = new CompoundTag();
        compoundTag.putByte("id", (byte) 0);
        compoundTag.putBoolean("exist", input != null && !input.isEmpty());
        if (input != null && !input.isEmpty()) {
            compoundTag.putInt("amount", amount);
            compoundTag.put("input", input.save(new CompoundTag()));
        }
        return compoundTag;
    }

    @Override
    public void growAmount(final int col) {
        this.amount += col;
        this.input.setCount(amount);
    }

    public boolean matches(ItemStack subject) {
        return subject.getItem() == this.input.getItem() && ItemStack.tagMatches(this.input, subject);
    }

    public int getAmount() {
        return this.amount;
    }

    private ItemStack setSize(ItemStack stack, int size) {
        ItemStack copy = stack.copy();
        copy.setCount(size);
        return copy;
    }

    public List<ItemStack> getInputs() {
        return Collections.singletonList(setSize(this.input, this.getAmount()));
    }

    @Override
    public boolean hasTag() {
        return false;
    }

    @Override
    public TagKey<Item> getTag() {
        return null;
    }

    @Override
    public void toNetwork(FriendlyByteBuf buffer) {
        buffer.writeInt(0);
        buffer.writeItem(this.input);
    }

    public String toString() {
        return "InputItemStack<" + setSize(this.input, this.amount) + ">";
    }

    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof InputItemStack)) return false;
        InputItemStack other = (InputItemStack) obj;
        return ItemStack.matches(this.input, other.input) && other.amount == this.amount;
    }
}
