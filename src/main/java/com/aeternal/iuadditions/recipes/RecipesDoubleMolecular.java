package com.aeternal.iuadditions.recipes;

import com.aeternal.iuadditions.Config;
import com.aeternal.iuadditions.Constants;
import com.brandon3055.draconicevolution.DEFeatures;
import com.denfop.IUItem;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Optional;

import static com.denfop.tiles.base.TileDoubleMolecular.addrecipe;

public class RecipesDoubleMolecular {

    @Optional.Method(modid = "draconicevolution")
    public static void initMixinRecipe() {
        if (Constants.DE_LOADED && Constants.DE_CONFIRM && Config.itemSpecialArmorMixins && Config.UpgradeModulesMixins) {
            addrecipe(new ItemStack(IUItem.module_schedule, 1), new ItemStack(DEFeatures.toolUpgrade, 1, 8),
                    new ItemStack(IUItem.upgrademodule, 1, 47), 12000000
            );
        }
    }
}
