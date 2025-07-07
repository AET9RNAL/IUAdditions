//---This code is a part of the original IU(Industrial Upgrade) code: https://github.com/ZelGimi/industrialupgrade. Project: https://www.curseforge.com/minecraft/mc-mods/industrial-upgrade.
//---The rights to this code belong to their original authors.---///
//---The usage and modification of it are a subject to the license of the original souce code and discretion of it's author.---///

package com.aeternal.integration.astralsorcery.tile;

import com.aeternal.Config;
import com.aeternal.integration.astralsorcery.block.BlockAstralSolarPanel;
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

public class TileFaintstarlightcollectorSolarPanel extends TileSolarPanel {

    public TileFaintstarlightcollectorSolarPanel() {
        super(11, Config.faintstarlightcollectorGenDay * 1, Config.faintstarlightcollectorOutput * 1,
                Config.faintstarlightcollectorStorage * 1, null
        );
    }

    public boolean doesSideBlockRendering(EnumFacing side) {
        return false;
    }

    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(EnumFacing side, BlockPos otherPos) {
        return true;
    }

    @Override
    public ItemStack getPickBlock(final EntityPlayer player, final RayTraceResult target) {
        return new ItemStack(Item.getItemFromBlock(AstralSorceryIntegration.blockASSolarPanel), 1, BlockAstralSolarPanel.faintscp_solar_panel.getId());
    }

    @Override
    public boolean isNormalCube() {
        return false;
    }

    @Override
    public IMultiTileBlock getTeBlock() {
        return BlockAstralSolarPanel.faintscp_solar_panel;
    }

    @Override
    public BlockTileEntity getBlock() {
        return AstralSorceryIntegration.blockASSolarPanel;
    }

}
