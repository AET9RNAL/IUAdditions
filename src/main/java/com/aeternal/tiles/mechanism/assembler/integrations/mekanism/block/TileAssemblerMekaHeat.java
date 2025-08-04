package com.aeternal.tiles.mechanism.assembler.integrations.mekanism.block;

import com.aeternal.IUAItem;
import com.aeternal.Localization;
import com.aeternal.blocks.assembler.integrations.mekanism.BlockMekaAssembler;
import com.aeternal.tiles.mechanism.assembler.integrations.mekanism.api.IAssemblerMekaHeat;
import com.denfop.IUItem;
import com.denfop.api.multiblock.IMainMultiBlock;
import com.denfop.api.tile.IMultiTileBlock;
import com.denfop.blocks.BlockTileEntity;
import com.denfop.tiles.mechanism.multiblocks.base.TileEntityMultiBlockElement;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class TileAssemblerMekaHeat extends TileEntityMultiBlockElement implements IAssemblerMekaHeat {

    @SideOnly(Side.CLIENT)
    public void addInformation(final ItemStack stack, final List<String> tooltip) {
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

    public IMultiTileBlock getTeBlock() {
        return BlockMekaAssembler.assembler_meka_heat;
    }

    public BlockTileEntity getBlock() {
        return IUAItem.assemblerMeka;
    }

    @Override
    public boolean isMain() {
        return false;
    }

    @Override
    public int getBlockLevel() {
        return super.getBlockLevel();
    }

    @Override
    public boolean canCreateSystem(IMainMultiBlock mainMultiBlock) {
        return super.canCreateSystem(mainMultiBlock);
    }

    @Override
    public boolean hasOwnInventory() {
        return true;
    }

}
