package com.aeternal.iuadditions.spectralconverters.container;


import com.aeternal.iuadditions.Config;
import com.aeternal.iuadditions.spectralconverters.tiles.TileManaConverter;
import com.denfop.container.ContainerFullInv;
import com.denfop.container.SlotInvSlot;
import net.minecraft.entity.player.EntityPlayer;

public class ContainerManaConverter extends ContainerFullInv<TileManaConverter> {

    public final EntityPlayer entityPlayer;

    public ContainerManaConverter(EntityPlayer entityPlayer, TileManaConverter tileEntity) {
        super(entityPlayer, tileEntity, 176,183);
        this.entityPlayer = entityPlayer;
        if (!Config.allowEfToManaConversion && !Config.allowManaToEfConversion) {
            return;
        }
        for (int k = 0; k < 4; k++) {
            addSlotToContainer(new SlotInvSlot(tileEntity.upgradeSlot, k, 151, 19 + k * 18));
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
