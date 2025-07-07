//---This code is a part of the original IU(Industrial Upgrade) code: https://github.com/ZelGimi/industrialupgrade. Project: https://www.curseforge.com/minecraft/mc-mods/industrial-upgrade.
//---The rights to this code belong to their original authors.---///
//---The usage and modification of it are a subject to the license of the original souce code and discretion of it's author.---///

package com.aeternal.proxy;

import com.aeternal.Constants;
import com.aeternal.api.IModelRegister;
import com.aeternal.integration.astralsorcery.recipes.ASRecipes;
import com.aeternal.integration.astralsorcery.tile.AstralSorceryIntegration;
import com.aeternal.integration.divinerpg.DivinerpgIntegration;
import com.aeternal.integration.divinerpg.recipes.DIVRecipes;
import com.aeternal.integration.forestry.ForestryIntegration;
import com.aeternal.integration.forestry.recipes.ForestryRecipes;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import static com.aeternal.integration.astralsorcery.recipes.ASRecipes.*;


public class CommonProxy  {

    public void preInit(FMLPreInitializationEvent event) {

        if (Constants.AS_LOADED && Constants.AS_CONFIRM) {
            AstralSorceryIntegration.init();
        }
        if (Constants.FO_LOADED && Constants.FO_CONFIRM) {
            ForestryIntegration.init();
        }
        if (Constants.DIV_LOADED && Constants.DIV_CONFIRM) {
            DivinerpgIntegration.init();
        }
/*        if (Constants.BA_LOADED && Constants.BA_CONFIRM) {
           BotaniaIntegration.init();
        }*/

    }

    public void init(FMLInitializationEvent event) {
        if (Constants.AS_LOADED && Constants.AS_CONFIRM) {

            //TODO: fix this
         //   ASRecipes.ASBaseRecipe();
            ASRecipes.ASCompressorRecipe();
            ASRecipes.MicrochipRecipe();
            ASRecipes.ASDoubleMolecularRecipe();
            ASRecipes.InitConstellationRecipes();
        }
        if (Constants.DIV_LOADED && Constants.DIV_CONFIRM) {
            //TODO: fix this
        //   DIVRecipes.DIVBaseRecipe();
           DIVRecipes.MicrochipRecipe();
           DIVRecipes.DIVCompressorRecipe();
           DIVRecipes.DIVRollingRecipe();
        }
        if (Constants.FO_LOADED && Constants.BINNIE_LOADED && Constants.FO_CONFIRM) {
            ForestryRecipes.FOCompressorRecipe();
        }
//        if (Constants.DE_LOADED && Constants.DE_CONFIRM) {
//            SCRecipes.Init();
//        }
    }

    public void postInit(FMLPostInitializationEvent event) {

    }


    public boolean addIModelRegister(IModelRegister modelRegister) {
        return false;
    }


}
