package com.aeternal.iuadditions.container;

import com.aeternal.iuadditions.tiles.mechanism.assembler.block.TileAssemblerMain;
import com.denfop.container.ContainerFullInv;
import com.denfop.container.SlotInvSlot;
import net.minecraft.entity.player.EntityPlayer;

import javax.annotation.Nonnull;

public class ContainerAssembler extends ContainerFullInv<TileAssemblerMain> {

    public ContainerAssembler(EntityPlayer entityPlayer, TileAssemblerMain tileEntityAssemblerMain) {
        super(entityPlayer, tileEntityAssemblerMain, 166);


        addSlotToContainer(new SlotInvSlot(tileEntityAssemblerMain.output, 0,
                116, 35
        ));


        addSlotToContainer(new SlotInvSlot(tileEntityAssemblerMain.invSlotAssembler, 0,
                56, 34
        ));


        addSlotToContainer(new SlotInvSlot(tileEntityAssemblerMain.output1,
                0, 29, 62
        ));
        addSlotToContainer(new SlotInvSlot(tileEntityAssemblerMain.fluidSlot,
                0, 8, 62
        ));
    }


    @Override
    public void onContainerClosed(@Nonnull final EntityPlayer playerIn) {
        super.onContainerClosed(playerIn);
        this.base.entityPlayerList.remove(playerIn);
    }

    //TODO game stages xD
    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return true;
    }

}
