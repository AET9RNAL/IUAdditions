//---This code is a part of the original IU(Industrial Upgrade) code: https://github.com/ZelGimi/industrialupgrade. Project: https://www.curseforge.com/minecraft/mc-mods/industrial-upgrade.
//---The rights to this code belong to their original authors.---///
//---The usage and modification of it are a subject to the license of the original souce code and discretion of it's author.---///

package com.aeternal.integration.forestry;


import com.aeternal.integration.forestry.block.BlockForestrySolarPanel;
import com.aeternal.items.IUAItemBase;
import com.denfop.blocks.BlockTileEntity;
import com.denfop.blocks.TileBlockCreator;
import net.minecraft.item.Item;

public class ForestryIntegration {

    public static BlockTileEntity blockForestrySolarPanel;
    public static Item compressed_shimmeringhoneycomb;
    public static Item compressed_radioactivehoneycomb;
    public static Item compressed_venomoushoneycomb;
    public static Item compressed_certushoneycomb;
    public static Item compressed_statichoneycomb;
    public static Item compressed_drippinghoneycomb;
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
    public static Item plate_refractive;
    public static Item glass_shimmering;
    public static Item glass_radioactive;
    public static Item glass_venomous;
    public static Item glass_certus;
    public static Item glass_static;
    public static Item glass_dripping;
    public static Item glass_refractive;

    public static void init() {
        blockForestrySolarPanel = TileBlockCreator.instance.create(BlockForestrySolarPanel.class);

        compressed_shimmeringhoneycomb = new IUAItemBase("compressed_shimmeringhoneycomb");
        compressed_radioactivehoneycomb = new IUAItemBase("compressed_radioactivehoneycomb");
        compressed_venomoushoneycomb = new IUAItemBase("compressed_venomoushoneycomb");
        compressed_certushoneycomb = new IUAItemBase("compressed_certushoneycomb");
        compressed_statichoneycomb = new IUAItemBase("compressed_statichoneycomb");
        compressed_drippinghoneycomb = new IUAItemBase("compressed_drippinghoneycomb");
        crystal_shimmering = new IUAItemBase("crystal_shimmering");
        crystal_radioactive = new IUAItemBase("crystal_radioactive");
        crystal_venomous = new IUAItemBase("crystal_venomous");
        crystal_certus = new IUAItemBase("crystal_certus");
        crystal_static = new IUAItemBase("crystal_static");
        crystal_dripping = new IUAItemBase("crystal_dripping");
        plate_shimmering = new IUAItemBase("plate_shimmering");
        plate_radioactive = new IUAItemBase("plate_radioactive");
        plate_venomous = new IUAItemBase("plate_venomous");
        plate_certus = new IUAItemBase("plate_certus");
        plate_static = new IUAItemBase("plate_static");
        plate_dripping = new IUAItemBase("plate_dripping");
        plate_refractive = new IUAItemBase("plate_refractive");
        glass_shimmering = new IUAItemBase("glass_shimmering");
        glass_radioactive = new IUAItemBase("glass_radioactive");
        glass_venomous = new IUAItemBase("glass_venomous");
        glass_certus = new IUAItemBase("glass_certus");
        glass_static = new IUAItemBase("glass_static");
        glass_dripping = new IUAItemBase("glass_dripping");
        glass_refractive = new IUAItemBase("glass_refractive");
    }

}
