package com.aeternal.iuadditions.registry;

import com.aeternal.iuadditions.IUAdditions;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

/**
 * Note: In 1.19.3+, creative tabs become a registry (DeferredRegister).
 */
public class ModCreativeTabs {

    public static final CreativeModeTab MOD_TAB = new CreativeModeTab(IUAdditions.MOD_ID + ".tab") {
        @Override
        public ItemStack makeIcon() {
            // Replace with your mod's icon item
            return new ItemStack(Items.DIAMOND);
            // return new ItemStack(ModItems.EXAMPLE_ITEM.get());
        }
    };
}
