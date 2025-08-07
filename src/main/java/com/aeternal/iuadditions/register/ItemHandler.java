package com.aeternal.iuadditions.register;

import com.aeternal.iuadditions.Constants;
import com.aeternal.iuadditions.IUAItem;
import com.aeternal.iuadditions.blocks.BlockAssembler;
import com.aeternal.iuadditions.blocks.BlockMekaAssembler;
import com.aeternal.iuadditions.integration.astralsorcery.AstralSorceryIntegration;
import com.aeternal.iuadditions.integration.astralsorcery.item.AstralCraftingElements;
import com.aeternal.iuadditions.integration.divinerpg.DivinerpgIntegration;
import com.aeternal.iuadditions.integration.divinerpg.item.DivItems;
import com.aeternal.iuadditions.integration.forestry.ForestryIntegration;
import com.aeternal.iuadditions.integration.forestry.items.*;
import com.denfop.blocks.TileBlockCreator;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class ItemHandler {

    public static void init() {

        if(Constants.FO_LOADED && Constants.FO_CONFIRM) {
            IUAItem.compressedHoneycomb = new CompressedHoneyComb();
            IUAItem.honeyCrystal = new HoneyCrystal();
            IUAItem.honeyPlate = new HoneyPlate();
            IUAItem.honeyGlass = new HoneyGlass();
            IUAItem.refractive = new Refractive();
            ForestryIntegration.init();
        }
        if (Constants.AS_LOADED && Constants.AS_CONFIRM) {
            IUAItem.astralElements = new AstralCraftingElements();
            AstralSorceryIntegration.init();
        }
        if (Constants.DIV_LOADED && Constants.DIV_CONFIRM) {
            IUAItem.divItems = new DivItems();
            DivinerpgIntegration.init();
        }

        IUAItem.assembler = TileBlockCreator.instance.create(BlockAssembler.class);
        if (Constants.MEKA_LOADED) {
            IUAItem.assemblerMeka = TileBlockCreator.instance.create(BlockMekaAssembler.class);
        }
    }
    
    public static <T extends Item> T registerItem(T item, ResourceLocation rl) {
        item.setRegistryName(rl);
        return registerItem(item);
    }

    public static <T extends Item> T registerItem(T item) {
        ForgeRegistries.ITEMS.register(item);
        return item;
    }

    public static <T extends Block> T registerBlock(T item, ResourceLocation rl) {
        item.setRegistryName(rl);
        return registerBlock(item);
    }

    public static <T extends Block> T registerBlock(T item) {
        ForgeRegistries.BLOCKS.register(item);
        return item;
    }


}
