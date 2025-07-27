package com.aeternal.register;

import com.aeternal.tiles.mechanism.assembler.api.IAssemblerMain;
import com.denfop.IUItem;
import com.denfop.api.multiblock.MultiBlockStructure;
import com.denfop.api.multiblock.MultiBlockSystem;
import net.minecraft.item.ItemStack;

public class MultiBlockSystemHandler {

    public static MultiBlockStructure assemblerMultiBlock;

    public static void init() {
        new MultiBlockSystem();
        assemblerMultiBlock =
                MultiBlockSystem.instance
                        .add("Assembler")
                        .setMain(IAssemblerMain.class)
                        .setHasActivatedItem(true)
                        .setActivateItem(new ItemStack(IUItem.ForgeHammer))
                        .setIgnoreMetadata(true);


    }
}
