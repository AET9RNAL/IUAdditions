//---This code is a part of the original IU(Industrial Upgrade) code: https://github.com/ZelGimi/industrialupgrade. Project: https://www.curseforge.com/minecraft/mc-mods/industrial-upgrade.
//---The rights to this code belong to their original authors.---///
//---The usage and modification of it are a subject to the license of the original souce code and discretion of it's author.---///

package com.aeternal.proxy;

import com.aeternal.Constants;
import com.aeternal.api.IModelRegister;
import com.aeternal.integration.astralsorcery.render.TileEntityDimSolarPanelRender;
import com.aeternal.integration.astralsorcery.render.TileEntityFaintSolarPanelRender;
import com.aeternal.integration.astralsorcery.render.TileEntitySolarPanelRender;
import com.aeternal.integration.astralsorcery.tile.TileBrightstarlightcollectorSolarPanel;
import com.aeternal.integration.astralsorcery.tile.TileDimstarlightcollectorSolarPanel;
import com.aeternal.integration.astralsorcery.tile.TileFaintstarlightcollectorSolarPanel;
import com.aeternal.integration.divinerpg.render.*;
import com.aeternal.integration.divinerpg.tile.*;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.util.ArrayList;

public class ClientProxy extends CommonProxy {

    public static final ArrayList<IModelRegister> modelList = new ArrayList<>();
    public boolean addIModelRegister(IModelRegister modelRegister) {
        return modelList.add(modelRegister);
    }

    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);
        for (IModelRegister register : modelList) {
            register.registerModels();
        }


        if(Constants.AS_LOADED && Constants.AS_CONFIRM){
            ClientRegistry.bindTileEntitySpecialRenderer(TileBrightstarlightcollectorSolarPanel.class, new TileEntitySolarPanelRender<>());
            ClientRegistry.bindTileEntitySpecialRenderer(TileDimstarlightcollectorSolarPanel.class, new TileEntityDimSolarPanelRender<>());
            ClientRegistry.bindTileEntitySpecialRenderer(TileFaintstarlightcollectorSolarPanel.class, new TileEntityFaintSolarPanelRender<>());
        }
        if(Constants.DIV_LOADED && Constants.DIV_CONFIRM){
            ClientRegistry.bindTileEntitySpecialRenderer(TileEdenSolarPanel.class, new TileEntityEdenSolarPanelRender<>());
            ClientRegistry.bindTileEntitySpecialRenderer(TileWildwoodSolarPanel.class, new TileEntityWildwoodSolarPanelRender<>());
            ClientRegistry.bindTileEntitySpecialRenderer(TileApalachiaSolarPanel.class, new TileEntityApalachiaSolarPanelRender<>());
            ClientRegistry.bindTileEntitySpecialRenderer(TileSkythernSolarPanel.class, new TileEntitySkythernSolarPanelRender<>());
            ClientRegistry.bindTileEntitySpecialRenderer(TileMortumSolarPanel.class, new TileEntityMortumSolarPanelRender<>());
            ClientRegistry.bindTileEntitySpecialRenderer(TileArcanaSolarPanel.class, new TileEntityArcanaSolarPanelRender<>());
            ClientRegistry.bindTileEntitySpecialRenderer(TileVetheaSolarPanel.class, new TileEntityVetheaSolarPanelRender<>());
            ClientRegistry.bindTileEntitySpecialRenderer(TileCrossDimentionalSolarPanel.class, new TileEntityCrossDimSolarPanelRender<>());
        }

    }

    public void postInit(FMLPostInitializationEvent event) {
        super.postInit(event);

    }
}
