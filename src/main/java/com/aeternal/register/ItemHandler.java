package com.aeternal.register;

import com.aeternal.Constants;
import com.aeternal.IUAItem;
import com.aeternal.integration.astralsorcery.AstralSorceryIntegration;
import com.aeternal.integration.astralsorcery.item.AstralCraftingElements;
import com.aeternal.integration.divinerpg.DivinerpgIntegration;
import com.aeternal.integration.divinerpg.item.DivItems;
import com.aeternal.integration.forestry.ForestryIntegration;
import com.aeternal.integration.forestry.items.*;
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
