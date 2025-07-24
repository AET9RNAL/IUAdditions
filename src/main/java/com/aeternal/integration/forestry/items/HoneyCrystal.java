package com.aeternal.integration.forestry.items;

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

public class HoneyCrystal extends ItemSubTypes<HoneyCrystal.HoneyCrystalTypes> implements IModelRegister {

    protected static final String NAME = "crystal";

    public HoneyCrystal() {
        super(HoneyCrystalTypes.class);
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
                        Constants.MOD_ID + ":" + NAME + "_" + CompressedHoneyComb.CompressedHoneyCombTypes.getFromID(meta).getName(),
                        null
                )
        );
    }

    public enum HoneyCrystalTypes implements ISubEnum {
        shimmering(0),
        radioactive(1),
        venomous(2),
        certus(3),
        statics(4),
        dripping(5);

        private final String name;
        private final int ID;

        HoneyCrystalTypes(final int ID) {
            this.name = this.name().toLowerCase(Locale.US);
            this.ID = ID;
        }

        public static HoneyCrystalTypes getFromID(final int ID) {
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
