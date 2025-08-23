package com.aeternal.iuadditions.integration.astralsorcery.item;

import com.aeternal.iuadditions.Constants;
import com.aeternal.iuadditions.Core;
import com.aeternal.iuadditions.items.resource.IUAItemSubTypes;
import com.aeternal.iuadditions.register.ItemHandler;
import com.denfop.api.IModelRegister;
import com.aeternal.iuadditions.api.block.ISubEnum;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;

public class AstralCraftingElements extends IUAItemSubTypes<AstralCraftingElements.AstralCraftingElementsTypes> implements IModelRegister {

    protected static final String NAME = "astral_element";

    public AstralCraftingElements() {
        super(AstralCraftingElements.AstralCraftingElementsTypes.class);
        this.setCreativeTab(Core.IUATab);
        this.setMaxStackSize(64);
        ItemHandler.registerItem((Item) this, Core.getIdentifier(NAME)).setUnlocalizedName(NAME);
        Core.proxy.addIModelRegister(this);
    }
    @Override
    public @NotNull String getUnlocalizedName() {
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
