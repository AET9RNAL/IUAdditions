//---This code is a part of the original IU(Industrial Upgrade) code: https://github.com/ZelGimi/industrialupgrade. Project: https://www.curseforge.com/minecraft/mc-mods/industrial-upgrade.
//---The rights to this code belong to their original authors.---///
//---The usage and modification of it are a subject to the license of the original souce code and discretion of it's author.---///

package com.aeternal.integration.forestry.block;


import com.aeternal.Core;
import com.aeternal.integration.forestry.tile.*;
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

public enum BlockForestrySolarPanel implements IMultiTileBlock {

    cyanbeekeeper_solar_panel(TileCyanBeekeeperSolarPanel.class, 0, EnumRarity.UNCOMMON),
    bluebeekeeper_solar_panel(TileBlueBeekeeperSolarPanel.class, 1, EnumRarity.UNCOMMON),
    greenbeekeeper_solar_panel(TileGreenBeekeeperSolarPanel.class, 2, EnumRarity.COMMON),
    staticbeekeeper_solar_panel(TileStaticBeekeeperSolarPanel.class, 3, EnumRarity.RARE),
    purplebeekeeper_solar_panel(TilePurpleBeekeeperSolarPanel.class, 4, EnumRarity.EPIC),
    amberbeekeeper_solar_panel(TileAmberBeekeeperSolarPanel.class, 5, EnumRarity.EPIC),
    refractivebeekeeper_solar_panel(TileRefractiveSolarPanel.class, 6, EnumRarity.EPIC),

    ;

    //
    public static final ResourceLocation IDENTITY = Core.getIdentifier("forestrypanel");

    private final Class<? extends TileEntityBlock> teClass;
    private final int itemMeta;
    private final EnumRarity rarity;
    private TileEntityBlock dummyTe;


    BlockForestrySolarPanel(final Class<? extends TileEntityBlock> teClass, final int itemMeta, final EnumRarity rarity) {
        this.teClass = teClass;
        this.itemMeta = itemMeta;
        this.rarity = rarity;
        GameRegistry.registerTileEntity(teClass, Core.getIdentifier(this.getName()));


    }

    public static BlockForestrySolarPanel getFromID(final int ID) {
        return values()[ID % values().length];
    }

    public void buildDummies() {
        for (final BlockForestrySolarPanel block : values()) {
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
