package com.aeternal.iuadditions.tiles.mechanism.assembler.block;

import com.aeternal.iuadditions.IUAItem;
import com.aeternal.iuadditions.Localization;
import com.aeternal.iuadditions.blocks.BlockAssembler;
import com.aeternal.iuadditions.tiles.mechanism.assembler.api.IAssemblerInputFluid;
import com.denfop.IUItem;
import com.denfop.api.tile.IMultiTileBlock;
import com.denfop.blocks.BlockTileEntity;
import com.denfop.componets.Fluids;
import com.denfop.invslot.InvSlot;
import com.denfop.tiles.mechanism.multiblocks.base.TileEntityMultiBlockElement;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class TileAssemblerFluidInput extends TileEntityMultiBlockElement implements IAssemblerInputFluid {

    private final Fluids fluids = (Fluids)this.addComponent(new Fluids(this));
    FluidTank tank;

    public TileAssemblerFluidInput() {
        this.tank = this.fluids.addTank("tank1", 100000, InvSlot.TypeItemSlot.INPUT, Fluids.fluidPredicate(new Fluid[]{/*FluidRegistry.WATER*/}));
        this.tank = this.fluids.addTank("tank2", 100000, InvSlot.TypeItemSlot.INPUT, Fluids.fluidPredicate(new Fluid[]{/*FluidRegistry.WATER*/}));
    }

    public IMultiTileBlock getTeBlock() {
        return BlockAssembler.assembler_input_fluid;
    }

    public BlockTileEntity getBlock() {
        return IUAItem.assembler;
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

    public FluidTank getFluidTank() {
        return this.tank;
    }

    public Fluids getFluid() {
        return this.fluids;
    }



}
