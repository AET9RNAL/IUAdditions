package com.aeternal.iuadditions.proxy;

import com.aeternal.iuadditions.Constants;
import com.aeternal.iuadditions.integration.astralsorcery.tile.TileBrightstarlightcollectorSolarPanel;
import com.aeternal.iuadditions.integration.astralsorcery.tile.TileDimstarlightcollectorSolarPanel;
import com.aeternal.iuadditions.integration.astralsorcery.tile.TileFaintstarlightcollectorSolarPanel;
import com.aeternal.iuadditions.integration.divinerpg.tile.*;
import com.aeternal.iuadditions.render.GenericSolarPanelRenderer;
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

        if(Constants.isActive("astralsorcery")){
            ClientRegistry.bindTileEntitySpecialRenderer(TileBrightstarlightcollectorSolarPanel.class, new GenericSolarPanelRenderer<>());
            ClientRegistry.bindTileEntitySpecialRenderer(TileDimstarlightcollectorSolarPanel.class, new GenericSolarPanelRenderer<>());
            ClientRegistry.bindTileEntitySpecialRenderer(TileFaintstarlightcollectorSolarPanel.class, new GenericSolarPanelRenderer<>());
        }
        if(Constants.isActive("divinerpg")){
            ClientRegistry.bindTileEntitySpecialRenderer(TileEdenSolarPanel.class, new GenericSolarPanelRenderer<>());
            ClientRegistry.bindTileEntitySpecialRenderer(TileWildwoodSolarPanel.class, new GenericSolarPanelRenderer<>());
            ClientRegistry.bindTileEntitySpecialRenderer(TileApalachiaSolarPanel.class, new GenericSolarPanelRenderer<>());
            ClientRegistry.bindTileEntitySpecialRenderer(TileSkythernSolarPanel.class, new GenericSolarPanelRenderer<>());
            ClientRegistry.bindTileEntitySpecialRenderer(TileMortumSolarPanel.class, new GenericSolarPanelRenderer<>());
            ClientRegistry.bindTileEntitySpecialRenderer(TileArcanaSolarPanel.class, new GenericSolarPanelRenderer<>());
            ClientRegistry.bindTileEntitySpecialRenderer(TileVetheaSolarPanel.class, new GenericSolarPanelRenderer<>());
            ClientRegistry.bindTileEntitySpecialRenderer(TileCrossDimentionalSolarPanel.class, new GenericSolarPanelRenderer<>());
        }

    }

    public void postInit(FMLPostInitializationEvent event) {
        super.postInit(event);
    }
}
