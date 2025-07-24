//---This code is a part of the original IU(Industrial Upgrade) code: https://github.com/ZelGimi/industrialupgrade. Project: https://www.curseforge.com/minecraft/mc-mods/industrial-upgrade.
//---The rights to this code belong to their original authors.---///
//---The usage and modification of it are a subject to the license of the original souce code and discretion of it's author.---///

package com.aeternal.spectralconverters.gui;


import com.aeternal.Config;
import com.aeternal.Constants;
import com.aeternal.spectralconverters.container.ContainerManaConverter;
import com.aeternal.spectralconverters.tiles.TileManaConverter;
import com.denfop.Localization;
import com.denfop.api.energy.EnergyNetGlobal;
import com.denfop.gui.GuiCore;
import com.denfop.network.packet.PacketUpdateServerTile;
import com.denfop.utils.ModUtils;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import java.io.IOException;

public class GuiManaConverter extends GuiCore<ContainerManaConverter> {

    private static final ResourceLocation background = new ResourceLocation(
            Constants.MOD_ID,
            "textures/gui/mn_gui.png"
    );
    private final ContainerManaConverter container;
    private final TileManaConverter tileentity;

    public GuiManaConverter(ContainerManaConverter container1) {
        super(container1);
        this.xSize = this.getXSize() - 1;
        this.ySize = 186;
        this.container = container1;
        this.tileentity = container1.base;

    }


    protected void mouseClicked(int i, int j, int k) throws IOException {
        super.mouseClicked(i, j, k);
        int xMin = (this.width - this.xSize) / 2;
        int yMin = (this.height - this.ySize) / 2;
        int x = i - xMin;
        int y = j - yMin;

        if (x >= 39 && x < 59 && y >= 81 && y <= 91) {
            // left button = switch to MN→EF
            if (this.container.base.mn
                    && Config.allowManaToEfConversion
            ) {
                new PacketUpdateServerTile(this.container.base, 0);
            }
        }
// right button = switch to EF→MN
        else if (x >= 59 && x < 79 && y >= 81 && y <= 91) {
            if (!this.container.base.mn
                    && Config.allowEfToManaConversion
            ) {
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
                "EF: " + ModUtils.getString(this.tileentity.energy.getEnergy()) + "/" + ModUtils.getString(
                        this.tileentity.energy.capacity),
                9,
                20,
                13487565
        );
        this.fontRenderer.drawString("MANA: " + ModUtils.getString(this.tileentity.mana) + "/" + ModUtils.getString(this.tileentity.manaCap),
                9, 30, 13487565
        );
        if (this.tileentity.mn) {
            this.fontRenderer.drawString(
                    "MAX IN: " + ModUtils.getString(EnergyNetGlobal.instance.getPowerFromTier(this.tileentity.tier)) + " EF/t",
                    9,
                    40,
                    13487565
            );
            this.fontRenderer.drawString("IN: " + ModUtils.getString(this.tileentity.differenceenergy1) + " EF/t", 9, 50,
                    13487565
            );
            this.fontRenderer.drawString("OUT: " + ModUtils.getString(this.tileentity.perenergy) + " MANA/t", 9, 60,
                    13487565
            );
        } else {
            this.fontRenderer.drawString(
                    "MAX OUT: " + ModUtils.getString(EnergyNetGlobal.instance.getPowerFromTier(this.tileentity.tier)) + " EF/t",
                    9,
                    40,
                    13487565
            );
            this.fontRenderer.drawString("IN: " + ModUtils.getString(this.tileentity.perenergy) + " MANA/t", 9, 50,
                    13487565
            );
            this.fontRenderer.drawString("OUT: " + ModUtils.getString(this.tileentity.differenceenergy) + " EF/t", 9, 60,
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
        if (!Config.allowEfToManaConversion
                && !Config.allowManaToEfConversion) {
            // both false → draw big thing
            drawTexturedModalRect(j + 39, k + 81, 175, 100, 40, 11);
            drawTexturedModalRect(j + 150, k + 18, 219, 18, 19, 72);
        } else {
            // otherwise draw half of the thing
            if (!Config.allowManaToEfConversion) {
                // disable MN→EF (left half)
                drawTexturedModalRect(j + 39, k + 81, 175, 113, 20, 11);
            }
            if (!Config.allowEfToManaConversion) {
                // disable EF→MN (right half)
                drawTexturedModalRect(j + 59, k + 81, 175, 113, 20, 11);
            }
            }
        if (!this.tileentity.mn && Config.allowManaToEfConversion) {
            if (isPointInRegion(39, 81, 20, 12, mouseX, mouseY)) {
                drawTexturedModalRect(j + 39, k + 81, 197, 87, 20, 11);
            } else {
                drawTexturedModalRect(j + 39, k + 81, 175, 87, 20, 11);
            }
            drawTexturedModalRect(j + 118, k + 72, 175, 78, 7, 7);
        } else if (this.tileentity.mn && Config.allowEfToManaConversion){
            if (isPointInRegion(59, 81, 20, 12, mouseX, mouseY)) {
                drawTexturedModalRect(j + 59, k + 81, 197, 87, 20, 11);
            } else {
                drawTexturedModalRect(j + 59, k + 81, 175, 87, 20, 11);
            }
            drawTexturedModalRect(j + 136, k + 72, 184, 78, 7, 7);
        }
        if (this.tileentity.energy.getEnergy() > 0) {
            int gaugeFullHeight = 41;
            int gaugeScaledHeight = this.tileentity.gaugeICEnergyScaled(gaugeFullHeight);
            drawTexturedModalRect(j + 117, k + 70 - gaugeScaledHeight, 175, 76 - gaugeScaledHeight, 9, gaugeScaledHeight);
        }
        if (this.tileentity.mana > 0) {
            int gaugeFullHeight = 41;
            int gaugeScaledHeight = this.tileentity.gaugeTEEnergyScaled(gaugeFullHeight);
            drawTexturedModalRect(j + 135, k + 70 - gaugeScaledHeight, 186, 76 - gaugeScaledHeight, 9, gaugeScaledHeight);
        }
    }

}
