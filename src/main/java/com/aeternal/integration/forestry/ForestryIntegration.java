//---This code is a part of the original IU(Industrial Upgrade) code: https://github.com/ZelGimi/industrialupgrade. Project: https://www.curseforge.com/minecraft/mc-mods/industrial-upgrade.
//---The rights to this code belong to their original authors.---///
//---The usage and modification of it are a subject to the license of the original souce code and discretion of it's author.---///

package com.aeternal.integration.forestry;

import com.aeternal.integration.forestry.block.BlockForestrySolarPanel;
import com.denfop.blocks.BlockTileEntity;
import com.denfop.blocks.TileBlockCreator;
import net.minecraft.item.Item;

public class ForestryIntegration {

    public static BlockTileEntity blockForestrySolarPanel;
    public static Item crystal_shimmering;
    public static Item crystal_radioactive;
    public static Item crystal_venomous;
    public static Item crystal_certus;
    public static Item crystal_static;
    public static Item crystal_dripping;
    public static Item plate_shimmering;
    public static Item plate_radioactive;
    public static Item plate_venomous;
    public static Item plate_certus;
    public static Item plate_static;
    public static Item plate_dripping;

    public static void init() {
        blockForestrySolarPanel = TileBlockCreator.instance.create(BlockForestrySolarPanel.class);
    }

}
