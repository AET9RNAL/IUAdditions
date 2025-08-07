package com.aeternal.iuadditions.tiles.mechanism.assembler.integrations.mekanism.block;

import com.aeternal.iuadditions.IUAItem;
import com.aeternal.iuadditions.Localization;
import com.aeternal.iuadditions.blocks.BlockMekaAssembler;
import com.aeternal.iuadditions.components.MekaGasses;
import com.aeternal.iuadditions.tiles.mechanism.assembler.integrations.mekanism.api.IAssemblerMekaGasInput;
import com.denfop.IUItem;
import com.denfop.api.tile.IMultiTileBlock;
import com.denfop.blocks.BlockTileEntity;
import com.denfop.invslot.InvSlot;
import com.denfop.tiles.mechanism.multiblocks.base.TileEntityMultiBlockElement;
import mekanism.api.gas.Gas;
import mekanism.api.gas.GasTank;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class TileAssemblerGasInput extends TileEntityMultiBlockElement implements IAssemblerMekaGasInput {
    private final MekaGasses gasses = (MekaGasses)this.addComponent(new MekaGasses(this));
    GasTank tank;

    public TileAssemblerGasInput() {
        this.tank = this.gasses.addTank("tank", 100000, InvSlot.TypeItemSlot.INPUT, MekaGasses.gasPredicate(new Gas[]{}));
    }

    public IMultiTileBlock getTeBlock() {
        return BlockMekaAssembler.assembler_gas_input;
    }

    public BlockTileEntity getBlock() {
        return IUAItem.assemblerMeka;
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, List<String> tooltip) {
        super.addInformation(stack, tooltip);
//        tooltip.add(Localization.translate("iu.blastfurnace.info1"));
        boolean add = tooltip.add(Localization.translate("iua.assembler.info1") + Localization.translate(new ItemStack(
                IUAItem.assembler,
                1,
                0
        ).getUnlocalizedName()));
//        tooltip.add(Localization.translate("iu.blastfurnace.info4"));
        tooltip.add(Localization.translate("iua.assembler.info2") + com.denfop.Localization.translate(IUItem.ForgeHammer.getUnlocalizedName()));
//        tooltip.add(Localization.translate("iu.blastfurnace.info6"));
    }

    public GasTank getGasTank() {
        return this.tank;
    }

    public MekaGasses getGas() {
        return this.gasses;
    }

    @Override
    public boolean hasOwnInventory() {
        return true;
    }
}
