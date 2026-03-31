package com.aeternal.iuadditions.register;

import com.aeternal.iuadditions.Config;
import com.aeternal.iuadditions.integration.divinerpg.recipes.DIVRecipes;
import com.aeternal.iuadditions.recipes.RecipesDoubleMolecular;
import com.aeternal.iuadditions.spectralconverters.recipes.SRecipes;
import com.aeternal.iuadditions.Constants;
import com.aeternal.iuadditions.integration.astralsorcery.recipes.ASRecipes;
import com.aeternal.iuadditions.integration.forestry.recipes.ForestryRecipes;

public class RecipeHandler {

    public static void init() {

        if (Constants.isActive("astralsorcery")) {
            ASRecipes.init();
        }
        if (Constants.isActive("divinerpg")) {
            DIVRecipes.init();
        }
        if (Constants.isActive("forestry") && Constants.isActive("extrabees")) {
            ForestryRecipes.init();
        }
        if (Constants.DE_LOADED && Constants.DE_CONFIRM && Config.itemSpecialArmorMixins && Config.UpgradeModulesMixins) {
            SRecipes.init();
            RecipesDoubleMolecular.initMixinRecipe();
        }

    }

}
