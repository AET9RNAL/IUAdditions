package com.aeternal.iuadditions.proxy;

import com.aeternal.iuadditions.Constants;
import com.aeternal.iuadditions.integration.astralsorcery.render.TileEntityDimSolarPanelRender;
import com.aeternal.iuadditions.integration.astralsorcery.render.TileEntityFaintSolarPanelRender;
import com.aeternal.iuadditions.integration.astralsorcery.render.TileEntitySolarPanelRender;
import com.aeternal.iuadditions.integration.astralsorcery.tile.TileBrightstarlightcollectorSolarPanel;
import com.aeternal.iuadditions.integration.astralsorcery.tile.TileDimstarlightcollectorSolarPanel;
import com.aeternal.iuadditions.integration.astralsorcery.tile.TileFaintstarlightcollectorSolarPanel;
import com.aeternal.iuadditions.integration.divinerpg.render.*;
import com.aeternal.iuadditions.integration.divinerpg.tile.*;
import com.denfop.api.IModelRegister;
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
