/*
package com.aeternal.integration.botania.block;


import com.aeternal.Core;
import com.aeternal.integration.astralsorcery.tile.TileBrightstarlightcollectorSolarPanel;
import com.aeternal.integration.astralsorcery.tile.TileDimstarlightcollectorSolarPanel;
import com.aeternal.integration.astralsorcery.tile.TileFaintstarlightcollectorSolarPanel;
import com.denfop.api.tile.IMultiTileBlock;
import com.denfop.blocks.MultiTileBlock;
import com.denfop.tiles.base.TileEntityBlock;
import com.denfop.utils.ModUtils;
import net.minecraft.item.EnumRarity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

import javax.annotation.Nonnull;
import java.util.Set;

public enum BlockBotaniaSolarPanel implements IMultiTileBlock {

    brightscp_solar_panel(TileBrightstarlightcollectorSolarPanel.class, 0, EnumRarity.RARE),
    dimscp_solar_panel(TileDimstarlightcollectorSolarPanel.class, 1, EnumRarity.RARE),
    faintscp_solar_panel(TileFaintstarlightcollectorSolarPanel.class, 2, EnumRarity.EPIC),

    ;

    //
    public static final ResourceLocation IDENTITY = Core.getIdentifier("astralpanel");

    private final Class<? extends TileEntityBlock> teClass;
    private final int itemMeta;
    private final EnumRarity rarity;
    private TileEntityBlock dummyTe;


    BlockBotaniaSolarPanel(final Class<? extends TileEntityBlock> teClass, final int itemMeta, final EnumRarity rarity) {
        this.teClass = teClass;
        this.itemMeta = itemMeta;
        this.rarity = rarity;

        GameRegistry.registerTileEntity(teClass, Core.getIdentifier(this.getName()));


    }

    public static BlockBotaniaSolarPanel getFromID(final int ID) {
        return values()[ID % values().length];
    }

    public void buildDummies() {
        for (final BlockBotaniaSolarPanel block : values()) {
            if (block.teClass != null) {
                try {
                    block.dummyTe = block.teClass.newInstance();
                } catch (Exception ignored) {
                }
            }
        }
    }

    @Override
    public String getName() {
        return this.name();
    }

    @Override
    public int getId() {
        return this.itemMeta;
    }

    @Override
    @Nonnull
    public ResourceLocation getIdentifier() {
        return IDENTITY;
    }

    @Override
    public boolean hasItem() {
        return true;
    }

    @Override
    public Class<? extends TileEntityBlock> getTeClass() {
        return this.teClass;
    }

    @Override
    public boolean hasActive() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    @Nonnull
    public Set<EnumFacing> getSupportedFacings() {
        return ModUtils.horizontalFacings;
    }

    @Override
    public float getHardness() {
        return 3.0f;
    }

    @Override
    @Nonnull
    public MultiTileBlock.HarvestTool getHarvestTool() {
        return MultiTileBlock.HarvestTool.Wrench;
    }

    @Override
    @Nonnull
    public MultiTileBlock.DefaultDrop getDefaultDrop() {
        return MultiTileBlock.DefaultDrop.Self;
    }

    @Override
    public boolean allowWrenchRotating() {
        return true;
    }

    @Override
    public TileEntityBlock getDummyTe() {
        return this.dummyTe;
    }





}
*/
