package com.aeternal.iuadditions.integration.astralsorcery.tile;

import com.aeternal.iuadditions.integration.astralsorcery.block.BlockAstralSolarPanel;
import com.aeternal.iuadditions.items.IUAItemBase;
import com.denfop.blocks.BlockTileEntity;
import com.denfop.blocks.TileBlockCreator;
import net.minecraft.item.Item;

public  class AstralSorceryIntegration {
    public static BlockTileEntity blockASSolarPanel;
    public static Item plate_astralstarmetal;
    public static Item capacitor_stellar;
    public static Item soic_stellar;
    public static Item qfp_stellar;
    public static Item core_stellar;
    public static Item circuit_stellar;
    public static Item iridium_stellar;
    public static Item iridium_reinforced_stellar;
    public static Item iridium_dense_stellar;
    public static Item iridium_advanced_stellar;


    public static void init() {
        plate_astralstarmetal = new IUAItemBase("plate_astralstarmetal");
        capacitor_stellar = new IUAItemBase("capacitor_stellar");
        soic_stellar = new IUAItemBase("soic_stellar");
        qfp_stellar = new IUAItemBase("qfp_stellar");
        core_stellar = new IUAItemBase("core_stellar");
        iridium_stellar = new IUAItemBase("iridium_stellar");
        iridium_reinforced_stellar = new IUAItemBase("iridium_reinforced_stellar");
        iridium_dense_stellar = new IUAItemBase("iridium_dense_stellar");
        iridium_advanced_stellar = new IUAItemBase("iridium_advanced_stellar");
        circuit_stellar = new IUAItemBase("circuit_stellar");
        blockASSolarPanel = TileBlockCreator.instance.create(BlockAstralSolarPanel.class);
    }


}
