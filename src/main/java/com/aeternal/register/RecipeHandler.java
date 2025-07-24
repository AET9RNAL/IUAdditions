package com.aeternal.register;

import com.aeternal.spectralconverters.recipes.SRecipes;
import com.aeternal.Constants;
import com.aeternal.integration.astralsorcery.recipes.ASRecipes;
import com.aeternal.integration.forestry.recipes.ForestryRecipes;

public class RecipeHandler {

    public static void init() {

        if (Constants.AS_LOADED && Constants.AS_CONFIRM) {
            ASRecipes.init();
        }
        if (Constants.DIV_LOADED && Constants.DIV_CONFIRM) {
            //FIXME
        }
        if (Constants.FO_LOADED && Constants.EXBEES_LOADED && Constants.FO_CONFIRM) {
            ForestryRecipes.init();
        }
        if (Constants.DE_LOADED && Constants.DE_CONFIRM) {
            SRecipes.init();
        }

    }

}
