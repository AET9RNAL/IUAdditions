package com.aeternal.iuadditions.tabs;

import com.aeternal.iuadditions.IUAItem;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

public class TabCore extends CreativeTabs {

    private final int type;

    public TabCore(int type, String name) {
        super(name);
        this.type = type;
    }

    @Nonnull
    public ItemStack getTabIconItem() {
        switch (type) {
            case 0:
                return new ItemStack(IUAItem.blocktest);
        }
        return new ItemStack(IUAItem.blocktest);
    }

}

