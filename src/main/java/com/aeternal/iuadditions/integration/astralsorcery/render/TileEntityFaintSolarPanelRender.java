package com.aeternal.iuadditions.integration.astralsorcery.render;

import com.aeternal.iuadditions.Constants;
import com.aeternal.iuadditions.integration.astralsorcery.tile.TileFaintstarlightcollectorSolarPanel;
import com.denfop.render.panel.DataPollution;
import com.denfop.render.panel.PollutionModel;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;

public class TileEntityFaintSolarPanelRender<T extends TileFaintstarlightcollectorSolarPanel> extends TileEntitySpecialRenderer<T> {

    public static final ResourceLocation texture = new ResourceLocation(
            Constants.MOD_ID,
            "textures/models/pollution.png"
    );
    private Map<BlockPos, DataPollution> entries = new HashMap<>();

    public void render(
            @Nonnull TileFaintstarlightcollectorSolarPanel te,
            double x,
            double y,
            double z,
            float partialTicks,
            int destroyStage,
            float alpha
    ) {


        GlStateManager.pushMatrix();
        GlStateManager.translate(x, y, z);
        this.bindTexture(texture);
        DataPollution dataPollution = entries.get(te.getBlockPos());
        if(dataPollution == null){
            dataPollution = new DataPollution(te.timer.getIndexWork(), new PollutionModel(te.getWorld().rand,
                    te.timer.getIndexWork()));
            entries.put(te.getBlockPos(),dataPollution);
        }

        if(dataPollution.getIndex() != te.timer.getIndexWork()){
            dataPollution.setIndex( te.timer.getIndexWork());
            dataPollution.setModel(null);
        }
        if(dataPollution.getModel() == null){
            dataPollution.setModel(new PollutionModel(te.getWorld().rand,
                    te.timer.getIndexWork()));
        }
        dataPollution.getModel().render(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1F);
        GlStateManager.popMatrix();

    }
}
