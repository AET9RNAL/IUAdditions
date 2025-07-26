//---This code is a part of the original IU(Industrial Upgrade) code: https://github.com/ZelGimi/industrialupgrade. Project: https://www.curseforge.com/minecraft/mc-mods/industrial-upgrade.
//---The rights to this code belong to their original authors.---///
//---The usage and modification of it are a subject to the license of the original souce code and discretion of it's author.---///

package com.aeternal.integration.astralsorcery.tile;

import com.aeternal.integration.astralsorcery.block.BlockAstralSolarPanel;
import com.aeternal.items.IUAItemBase;
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
