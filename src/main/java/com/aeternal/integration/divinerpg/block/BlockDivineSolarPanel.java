//---This code is a part of the original IU(Industrial Upgrade) code: https://github.com/ZelGimi/industrialupgrade. Project: https://www.curseforge.com/minecraft/mc-mods/industrial-upgrade.
//---The rights to this code belong to their original authors.---///
//---The usage and modification of it are a subject to the license of the original souce code and discretion of it's author.---///

package com.aeternal.integration.divinerpg.block;


import com.aeternal.Core;
import com.aeternal.integration.divinerpg.tile.*;
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

public enum BlockDivineSolarPanel implements IMultiTileBlock {
    eden_solar_panel(TileEdenSolarPanel.class, 0, EnumRarity.UNCOMMON),
    wildwood_solar_panel(TileWildwoodSolarPanel.class, 1, EnumRarity.UNCOMMON),
    apalachia_solar_panel(TileApalachiaSolarPanel.class, 2, EnumRarity.RARE),
    skythern_solar_panel(TileSkythernSolarPanel.class, 3, EnumRarity.RARE),
    mortum_solar_panel(TileMortumSolarPanel.class, 4, EnumRarity.EPIC),
    arcana_solar_panel(TileArcanaSolarPanel.class, 5, EnumRarity.EPIC),
    vethea_solar_panel(TileVetheaSolarPanel.class, 6, EnumRarity.EPIC),
    crossdimentional_solar_panel(TileCrossDimentionalSolarPanel.class, 7, EnumRarity.EPIC),

    ;

    //
    public static final ResourceLocation IDENTITY = Core.getIdentifier("divinepanel");

    private final Class<? extends TileEntityBlock> teClass;
    private final int itemMeta;
    private final EnumRarity rarity;
    private TileEntityBlock dummyTe;


    BlockDivineSolarPanel(final Class<? extends TileEntityBlock> teClass, final int itemMeta, final EnumRarity rarity) {
        this.teClass = teClass;
        this.itemMeta = itemMeta;
        this.rarity = rarity;

        GameRegistry.registerTileEntity(teClass, Core.getIdentifier(this.getName()));
    }

    public static BlockDivineSolarPanel getFromID(final int ID) {
        return values()[ID % values().length];
    }

    public void buildDummies() {
        for (final BlockDivineSolarPanel block : values()) {
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

    int idBlock;
    public  int getIDBlock(){
        return idBlock;
    };

    public void setIdBlock(int id){
        idBlock = id;
    };
}
