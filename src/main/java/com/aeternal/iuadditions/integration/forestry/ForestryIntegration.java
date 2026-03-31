package com.aeternal.iuadditions.integration.forestry;

import com.aeternal.iuadditions.integration.forestry.block.BlockForestrySolarPanel;
import com.denfop.blocks.BlockTileEntity;
import com.denfop.blocks.TileBlockCreator;
import net.minecraft.item.Item;

public class ForestryIntegration {

    public static BlockTileEntity blockForestrySolarPanel;

    public static void init() {
        blockForestrySolarPanel = TileBlockCreator.instance.create(BlockForestrySolarPanel.class);
    }

}
