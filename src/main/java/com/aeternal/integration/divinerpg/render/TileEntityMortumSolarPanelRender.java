//---This code is a part of the original IU(Industrial Upgrade) code: https://github.com/ZelGimi/industrialupgrade. Project: https://www.curseforge.com/minecraft/mc-mods/industrial-upgrade.
//---The rights to this code belong to their original authors.---///
//---The usage and modification of it are a subject to the license of the original souce code and discretion of it's author.---///

package com.aeternal.integration.divinerpg.render;

import com.aeternal.Constants;
import com.aeternal.integration.divinerpg.tile.TileMortumSolarPanel;
import com.denfop.render.panel.DataPollution;
import com.denfop.render.panel.PollutionModel;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;

public class TileEntityMortumSolarPanelRender<T extends TileMortumSolarPanel> extends TileEntitySpecialRenderer<T> {

    public static final ResourceLocation texture = new ResourceLocation(
            Constants.TEXTURES,
            "textures/models/pollution.png"
    );
    private Map<BlockPos, DataPollution> entries = new HashMap<>();

    public void render(
            @Nonnull TileMortumSolarPanel te,
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
