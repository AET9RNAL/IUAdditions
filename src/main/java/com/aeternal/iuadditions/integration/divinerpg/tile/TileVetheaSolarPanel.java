package com.aeternal.iuadditions.integration.divinerpg.tile;

import com.aeternal.iuadditions.Config;
import com.aeternal.iuadditions.integration.divinerpg.DivinerpgIntegration;
import com.aeternal.iuadditions.integration.divinerpg.block.BlockDivineSolarPanel;
import com.denfop.api.tile.IMultiTileBlock;
import com.denfop.blocks.BlockTileEntity;
import com.denfop.tiles.panels.entity.TileSolarPanel;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TileVetheaSolarPanel extends TileSolarPanel {

    public TileVetheaSolarPanel() {
        super(14, Config.vetheaGenDay * 1, Config.vetheaOutput * 1,
                Config.vetheaStorage * 1, null
        );
    }

    public boolean doesSideBlockRendering(EnumFacing side) {
        return true;
    }

    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(EnumFacing side, BlockPos otherPos) {
        return true;
    }

    @Override
    public ItemStack getPickBlock(final EntityPlayer player, final RayTraceResult target) {
        return new ItemStack(Item.getItemFromBlock(DivinerpgIntegration.blockDivineSolarPanel), 1, BlockDivineSolarPanel.vethea_solar_panel.getId());
    }

    @Override
    public boolean isNormalCube() {
        return false;
    }

    @Override
    public IMultiTileBlock getTeBlock() {
        return BlockDivineSolarPanel.vethea_solar_panel;
    }

    @Override
    public BlockTileEntity getBlock() {
        return DivinerpgIntegration.blockDivineSolarPanel;
    }


}
