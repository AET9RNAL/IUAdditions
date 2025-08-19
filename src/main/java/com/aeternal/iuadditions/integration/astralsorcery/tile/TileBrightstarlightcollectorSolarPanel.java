package com.aeternal.iuadditions.integration.astralsorcery.tile;

import com.aeternal.iuadditions.Config;
import com.aeternal.iuadditions.integration.astralsorcery.AstralSorceryIntegration;
import com.aeternal.iuadditions.integration.astralsorcery.block.BlockAstralSolarPanel;
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

public class TileBrightstarlightcollectorSolarPanel extends TileSolarPanel {

    public TileBrightstarlightcollectorSolarPanel() {
        super(7, Config.brightstarlightcollectorGenDay * 1, Config.brightstarlightcollectorOutput * 1,
                Config.brightstarlightcollectorStorage * 1, null
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
        return new ItemStack(Item.getItemFromBlock(AstralSorceryIntegration.blockASSolarPanel), 1, BlockAstralSolarPanel.brightscp_solar_panel.getId());
    }

    @Override
    public boolean isNormalCube() {
        return false;
    }

    @Override
    public IMultiTileBlock getTeBlock() {
        return BlockAstralSolarPanel.brightscp_solar_panel;
    }

    @Override
    public BlockTileEntity getBlock() {
        return AstralSorceryIntegration.blockASSolarPanel;
    }


}
