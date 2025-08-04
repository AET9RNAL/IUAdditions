package com.aeternal.register;

import com.aeternal.IUAItem;
import com.aeternal.tiles.mechanism.assembler.api.IAssemblerCasing;
import com.aeternal.tiles.mechanism.assembler.api.IAssemblerMain;
import com.denfop.api.multiblock.MultiBlockStructure;
import com.denfop.api.multiblock.MultiBlockSystem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;

public class MultiBlockSystemHandler {

    public static MultiBlockStructure assemblerMultiBlock;

    public static void init() {
        new MultiBlockSystem();
        assemblerMultiBlock =
                MultiBlockSystem.instance
                        .add("Assembler")
                        .setMain(IAssemblerMain.class)
                        .setHasActivatedItem(false)
//                        .setActivateItem(new ItemStack(IUItem.ForgeHammer))
                        .setIgnoreMetadata(true);
        assemblerMultiBlock.add(assemblerMultiBlock.getPos(), IAssemblerMain.class, new ItemStack(IUAItem.assembler, 1, 0), EnumFacing.NORTH);
        assemblerMultiBlock.add(assemblerMultiBlock.getPos().add(0, -1, 0), IAssemblerCasing.class, new ItemStack(IUAItem.assembler, 1, 1));
    }
}
