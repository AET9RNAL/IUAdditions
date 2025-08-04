package com.aeternal.iuadditions.register;

import com.aeternal.iuadditions.integration.divinerpg.recipes.DIVRecipes;
import com.aeternal.iuadditions.spectralconverters.recipes.SRecipes;
import com.aeternal.iuadditions.Constants;
import com.aeternal.iuadditions.integration.astralsorcery.recipes.ASRecipes;
import com.aeternal.iuadditions.integration.forestry.recipes.ForestryRecipes;

public class RecipeHandler {

    public static void init() {

        if (Constants.AS_LOADED && Constants.AS_CONFIRM) {
            ASRecipes.init();
        }
        if (Constants.DIV_LOADED && Constants.DIV_CONFIRM) {
            DIVRecipes.init();
        }
        if (Constants.FO_LOADED && Constants.FO_CONFIRM) {
            ForestryRecipes.init();
        }
        if (Constants.DE_LOADED && Constants.DE_CONFIRM) {
            SRecipes.init();
        }

    }

}
