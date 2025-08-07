package com.aeternal.iuadditions.tiles.mechanism.assembler.block;

import com.aeternal.iuadditions.IUAItem;
import com.aeternal.iuadditions.Localization;
import com.aeternal.iuadditions.blocks.BlockAssembler;
import com.aeternal.iuadditions.tiles.mechanism.assembler.api.IAssemblerCasing;
import com.denfop.IUItem;
import com.denfop.api.multiblock.IMainMultiBlock;
import com.denfop.api.tile.IMultiTileBlock;
import com.denfop.blocks.BlockTileEntity;
import com.denfop.tiles.mechanism.multiblocks.base.TileEntityMultiBlockElement;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class TileAssemblerCasing extends TileEntityMultiBlockElement implements IAssemblerCasing {

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
        return BlockAssembler.assembler_casing;
    }

    public BlockTileEntity getBlock() {
        return IUAItem.assembler;
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
        return super.hasOwnInventory();
    }
}
