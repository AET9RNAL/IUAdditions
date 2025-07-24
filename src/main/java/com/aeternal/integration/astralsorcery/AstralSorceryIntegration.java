package com.aeternal.integration.astralsorcery;

import com.aeternal.integration.astralsorcery.block.BlockAstralSolarPanel;
import com.denfop.blocks.BlockTileEntity;
import com.denfop.blocks.TileBlockCreator;

public  class AstralSorceryIntegration {
    public static BlockTileEntity blockASSolarPanel;


    public static void init() {
        blockASSolarPanel = TileBlockCreator.instance.create(BlockAstralSolarPanel.class);
    }


}
