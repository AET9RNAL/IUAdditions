//---This code is a part of the original IU(Industrial Upgrade) code: https://github.com/ZelGimi/industrialupgrade. Project: https://www.curseforge.com/minecraft/mc-mods/industrial-upgrade.
//---The rights to this code belong to their original authors.---///
//---The usage and modification of it are a subject to the license of the original souce code and discretion of it's author.---///

package com.aeternal.spectralconverters.gui;


import com.aeternal.Constants;
import com.aeternal.Utils;
import com.aeternal.spectralconverters.container.ContainerSpectralConverter;
import com.aeternal.spectralconverters.container.ContainerSpectralQEConverter;
import com.aeternal.spectralconverters.tiles.TileSpectralConverter;
import com.aeternal.spectralconverters.tiles.TileSpectralQEConverter;
import com.denfop.Localization;
import com.denfop.api.energy.EnergyNetGlobal;
import com.denfop.gui.GuiCore;
import com.denfop.network.packet.PacketUpdateServerTile;
import com.denfop.utils.ModUtils;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import java.io.IOException;
import java.util.Collections;

public class GuiSpectralQEConverter extends GuiCore<ContainerSpectralQEConverter> {

    private static final ResourceLocation background = new ResourceLocation(
            Constants.MOD_ID,
            "textures/gui/qesc_gui.png"
    );
    private final ContainerSpectralQEConverter container;
    private final TileSpectralQEConverter tileentity;

    public GuiSpectralQEConverter(ContainerSpectralQEConverter container1) {
        super(container1);
        this.xSize = 211;
        this.ySize = 192;
        this.container = container1;
        this.tileentity = container1.base;

    }


    protected void mouseClicked(int i, int j, int k) throws IOException {
        super.mouseClicked(i, j, k);
        int xMin = (this.width - this.xSize) / 2;
        int yMin = (this.height - this.ySize) / 2;
        int x = i - xMin;
        int y = j - yMin;

        if (x >= 39 && x < 59 && y >= 91 && y <= 102) {
            if (this.container.base.rf) {
                new PacketUpdateServerTile(this.container.base, 0);
            }
        } else if (x >= 59 && x < 79 && y >= 91 && y <= 102) {
            if (!this.container.base.rf) {
                new PacketUpdateServerTile(this.container.base, 0);
            }

        }
    }

    @Override
    protected ResourceLocation getTexture() {
        return background;
    }

    @Override
    protected void drawForegroundLayer(int par1, int par2) {
        int nmPos = (this.xSize - this.fontRenderer.getStringWidth(Localization.translate(this.container.base.getName()))) / 2;
        this.fontRenderer.drawString(Localization.translate(this.container.base.getName()), nmPos, 6, 7718655);
        this.fontRenderer.drawString(
                "QE: " + ModUtils.getString(this.tileentity.energy.getEnergy()) + "/" + ModUtils.getString(
                        this.tileentity.energy.capacity),
                9,
                20,
                13487565
        );
        this.fontRenderer.drawString("RF: " + ModUtils.getString(this.tileentity.energy2) + "/" + ModUtils.getString(this.tileentity.maxStorage2),
                9, 30, 13487565
        );
        if (this.tileentity.isCore && this.tileentity.isCoreActive) {
            this.fontRenderer.drawString("DE CORE: ONLINE" , 9, 70,
                    43520
            );
        }else {
            this.fontRenderer.drawString("DE CORE: OFFLINE" , 9, 70,
                    11141120
            );

        }
//        this.fontRenderer.drawString(
//                "Energy: " + ModUtils.getString(this.tileentity.coreEnergy), 9, 80, 0xFFFFFF);

        this.fontRenderer.drawString(
                Utils.getWholeNumberString(this.tileentity.coreEnergy), 154, 72, 0xcdcdcd);
        if (this.tileentity.rf) {
            this.fontRenderer.drawString(
                    "MAX IN: " + ModUtils.getString(EnergyNetGlobal.instance.getPowerFromTier(this.tileentity.tier)) + " QE/t",
                    9,
                    40,
                    13487565
            );
            this.fontRenderer.drawString("IN: " + ModUtils.getString(this.tileentity.differenceenergy1) + " QE/t", 9, 50,
                    13487565
            );
            this.fontRenderer.drawString("OUT: " + ModUtils.getString(this.tileentity.differenceenergy) + " RF/t", 9, 60,
                    13487565
            );

        } else {
            this.fontRenderer.drawString(
                    "MAX OUT: " + ModUtils.getString(EnergyNetGlobal.instance.getPowerFromTier(this.tileentity.tier)) + " QE/t",
                    9,
                    40,
                    13487565
            );
            this.fontRenderer.drawString("IN: " + ModUtils.getString(this.tileentity.perenergy) + " RF/t", 9, 50,
                    13487565
            );
            this.fontRenderer.drawString("OUT: " + ModUtils.getString(this.tileentity.differenceenergy) + " QE/t", 9, 60,
                    13487565
            );
        }
    }

    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(background);
        int j = (this.width - this.xSize) / 2;
        int k = (this.height - this.ySize) / 2;
        drawTexturedModalRect(j, k, 0, 0, this.xSize, this.ySize);

        if (!this.tileentity.rf) {
            if (isPointInRegion(39, 91, 20, 12, mouseX, mouseY)) {
                drawTexturedModalRect(j + 39, k + 91, 235, 103, 20, 11);
            } else {
                drawTexturedModalRect(j + 39, k + 91, 213, 103, 20, 11);
            }
            drawTexturedModalRect(j + 118, k + 72, 213, 94, 7, 7);
        } else {
            if (isPointInRegion(59, 91, 20, 12, mouseX, mouseY)) {
                drawTexturedModalRect(j + 59, k + 91, 235, 103, 20, 11);
            } else {
                drawTexturedModalRect(j + 59, k + 91, 213, 103, 20, 11);
            }
            drawTexturedModalRect(j + 136, k + 72, 222, 94, 7, 7);
        }
        if (this.tileentity.energy.getEnergy() > 0) {
            int gaugQEullHeight = 41;
            int gaugeScaledHeight = this.tileentity.gaugeICEnergyScaled(gaugQEullHeight);
            drawTexturedModalRect(j + 117, k + 70 - gaugeScaledHeight, 213, 92 - gaugeScaledHeight, 9, gaugeScaledHeight);
        }
        if (this.tileentity.energy2 > 0) {
            int gaugQEullHeight = 41;
            int gaugeScaledHeight = this.tileentity.gaugeTEEnergyScaled(gaugQEullHeight);
            drawTexturedModalRect(j + 135, k + 70 - gaugeScaledHeight, 224, 92 - gaugeScaledHeight, 9, gaugeScaledHeight);
        }
        if (this.tileentity.isCore &&  this.tileentity.isCoreActive) {
            int coreHeight = (this.tileentity.coreCapacity > 0)
                    ? (int) Math.min((this.tileentity.coreEnergy * 41) / this.tileentity.coreCapacity, 41)
                    : 0;
            this.drawTexturedModalRect(j + 154, k + 29 + (41 - coreHeight), 213, 7 + (41 - coreHeight), 25, coreHeight);

        }else{
            this.drawTexturedModalRect(j + 154, k + 29, 213, 117, 25, 41);
        }
        if (this.tileentity.isCore && this.tileentity.isCoreActive &&
                isPointInRegion(154, 29, 25, 41, mouseX, mouseY)) {

            long energy = this.tileentity.coreEnergy;
            long max = this.tileentity.coreCapacity;

            int percent = (max > 0) ? (int) ((double) energy / max * 100.0) : 0;

            this.drawHoveringText(Collections.singletonList(percent + "%"), mouseX, mouseY, this.fontRenderer);
        }
    }

}
