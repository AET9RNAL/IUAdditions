//---This code is a part of the original IU(Industrial Upgrade) code: https://github.com/ZelGimi/industrialupgrade. Project: https://www.curseforge.com/minecraft/mc-mods/industrial-upgrade.
//---The rights to this code belong to their original authors.---///
//---The usage and modification of it are a subject to the license of the original souce code and discretion of it's author.---///

package com.aeternal.proxy;

import com.aeternal.api.IModelRegister;
import com.aeternal.register.ItemHandler;
import com.aeternal.register.MultiBlockSystemHandler;
import com.aeternal.register.RecipeHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;


public class CommonProxy  {

    public void preInit(FMLPreInitializationEvent event) {

        ItemHandler.init();


    }

    public void init(FMLInitializationEvent event) {

    }

    public void postInit(FMLPostInitializationEvent event) {
        MultiBlockSystemHandler.init();

        RecipeHandler.init();

    }

    public boolean addIModelRegister(IModelRegister modelRegister) {
        return false;
    }

}
