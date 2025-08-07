package com.aeternal.iuadditions.tiles.mechanism.assembler.api;

import com.aeternal.iuadditions.tiles.mechanism.assembler.block.TileAssemblerMain;
import com.denfop.invslot.InvSlot;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class InvSlotAssembler extends InvSlot {

    private int stackSizeLimit;

    public InvSlotAssembler(TileAssemblerMain base1) {
        super(base1, TypeItemSlot.INPUT, 1);
        this.setStackSizeLimit(64);
    }

    public boolean accepts(ItemStack itemStack, final int index) {
        return itemStack.getItem().equals(Items.IRON_INGOT);
    }

    public int getStackSizeLimit() {
        return this.stackSizeLimit;
    }

    public void setStackSizeLimit(int stackSizeLimit) {
        this.stackSizeLimit = stackSizeLimit;
    }

}
