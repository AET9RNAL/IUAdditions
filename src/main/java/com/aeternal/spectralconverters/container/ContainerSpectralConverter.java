//---This code is a part of the original IU(Industrial Upgrade) code: https://github.com/ZelGimi/industrialupgrade. Project: https://www.curseforge.com/minecraft/mc-mods/industrial-upgrade.
//---The rights to this code belong to their original authors.---///
//---The usage and modification of it are a subject to the license of the original souce code and discretion of it's author.---///

package com.aeternal.spectralconverters.container;


import com.aeternal.spectralconverters.tiles.TileSpectralConverter;
import com.denfop.container.ContainerFullInv;
import com.denfop.container.SlotInvSlot;
import net.minecraft.entity.player.EntityPlayer;

public class ContainerSpectralConverter extends ContainerFullInv<TileSpectralConverter> {

    public final EntityPlayer entityPlayer;

    public ContainerSpectralConverter(EntityPlayer entityPlayer, TileSpectralConverter tileEntity) {
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
