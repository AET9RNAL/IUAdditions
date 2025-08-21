package com.aeternal.iuadditions.spectralconverters.container;


import com.aeternal.iuadditions.spectralconverters.tiles.TileSpectralQEConverter;
import com.denfop.container.ContainerFullInv;
import com.denfop.container.SlotInvSlot;
import net.minecraft.entity.player.EntityPlayer;

public class ContainerSpectralQEConverter extends ContainerFullInv<TileSpectralQEConverter> {

    public final EntityPlayer entityPlayer;

    public ContainerSpectralQEConverter(EntityPlayer entityPlayer, TileSpectralQEConverter tileEntity) {
        super(entityPlayer, tileEntity, 211,192);
        this.entityPlayer = entityPlayer;
        for (int k = 0; k < 4; k++) {
            addSlotToContainer(new SlotInvSlot(tileEntity.upgradeSlot, k, 187, 19 + k * 18));
        }
    }

    public void onContainerClosed(EntityPlayer playerIn) {
        this.base.list.remove(playerIn);
        super.onContainerClosed(playerIn);
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return true;
    }

}
