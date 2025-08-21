package com.aeternal.iuadditions.proxy;

import com.aeternal.iuadditions.register.ItemHandler;
import com.aeternal.iuadditions.register.RecipeHandler;
import com.denfop.api.IModelRegister;
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
        RecipeHandler.init();
    }

    public boolean addIModelRegister(IModelRegister modelRegister) {
        return false;
    }

}
