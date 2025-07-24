package com.aeternal.integration.astralsorcery.item;

import com.aeternal.Constants;
import com.aeternal.Core;
import com.aeternal.api.IModelRegister;
import com.aeternal.api.block.ISubEnum;
import com.aeternal.items.resource.ItemSubTypes;
import com.aeternal.register.ItemHandler;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Locale;

public class AstralCraftingElements extends ItemSubTypes<AstralCraftingElements.AstralCraftingElementsTypes> implements IModelRegister {

    protected static final String NAME = "astral_element";

    public AstralCraftingElements() {
        super(AstralCraftingElements.AstralCraftingElementsTypes.class);
        this.setCreativeTab(Core.IUATab);
        this.setMaxStackSize(64);
        ItemHandler.registerItem((Item) this, Core.getIdentifier(NAME)).setUnlocalizedName(NAME);
        Core.proxy.addIModelRegister(this);
    }

    public String getUnlocalizedName() {
        return Constants.MOD_ID + "." + super.getUnlocalizedName().substring(3);
    }

    @SideOnly(Side.CLIENT)
    public void registerModel(Item stack, final int meta, final String extraName) {
        ModelLoader.setCustomModelResourceLocation(
                this,
                meta,
                new ModelResourceLocation(
                        Constants.MOD_ID + ":" + NAME + "_" + AstralCraftingElements.AstralCraftingElementsTypes.getFromID(meta).getName(),
                        null
                )
        );
    }

    public enum AstralCraftingElementsTypes implements ISubEnum {
        plate(0),
        iridium(1),
        reinforced_iridium(2),
        dense_reinforced_iridium(3),
        iridium_advanced(4),
        capacitor(5),
        soic_chip(6),
        qfp_chip(7),
        circuit(8),
        core(9);

        private final String name;
        private final int ID;

        AstralCraftingElementsTypes(final int ID) {
            this.name = this.name().toLowerCase(Locale.US);
            this.ID = ID;
        }

        public static AstralCraftingElements.AstralCraftingElementsTypes getFromID(final int ID) {
            return values()[ID % values().length];
        }

        public String getName() {
            return this.name;
        }

        public int getId() {
            return this.ID;
        }

        public static int getLength() {
            return values().length;
        }


    }
}
