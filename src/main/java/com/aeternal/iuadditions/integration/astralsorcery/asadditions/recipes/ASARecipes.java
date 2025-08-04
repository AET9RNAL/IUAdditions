package com.aeternal.iuadditions.integration.astralsorcery.asadditions.recipes;

import arcaios26.astraladditions.init.BlockInit;
import com.aeternal.iuadditions.IUAItem;
import com.denfop.IUItem;
import com.denfop.tiles.base.TileDoubleMolecular;
import net.minecraft.item.ItemStack;

public class ASARecipes {

    public static void ASAStellarIridium() {
        TileDoubleMolecular.addrecipe(new ItemStack(IUItem.crafting_elements, 1, 275), new ItemStack(BlockInit.BLOCK_STARMETAL, 1), new ItemStack(IUAItem.astralElements, 1, 1), 1000000L);
    }
}
