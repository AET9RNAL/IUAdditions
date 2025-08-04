package com.aeternal.iuadditions.integration.astralsorcery;

import com.aeternal.iuadditions.integration.astralsorcery.block.BlockAstralSolarPanel;
import com.denfop.blocks.BlockTileEntity;
import com.denfop.blocks.TileBlockCreator;

public  class AstralSorceryIntegration {
    public static BlockTileEntity blockASSolarPanel;


    public static void init() {
        blockASSolarPanel = TileBlockCreator.instance.create(BlockAstralSolarPanel.class);
    }


}
