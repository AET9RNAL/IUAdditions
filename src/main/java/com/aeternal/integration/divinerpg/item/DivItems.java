package com.aeternal.integration.divinerpg.item;

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

public class DivItems extends ItemSubTypes<DivItems.DivItemsTypes> implements IModelRegister {

    protected static final String NAME = "divinerpg_elements";

    public DivItems() {
        super(DivItemsTypes.class);
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
                        Constants.MOD_ID + ":" + NAME + "_" + DivItemsTypes.getFromID(meta).getName(),
                        null
                )
        );
    }

    public enum DivItemsTypes implements ISubEnum {
        eden_capacitor(0),
        wildwood_capacitor(1),
        apalachia_capacitor(2),
        skythern_capacitor(3),
        mortum_capacitor(4),
        arcana_capacitor(5),
        vethea_capacitor(6),
        eden_soic(7),
        wildwood_soic(8),
        apalachia_soic(9),
        skythern_soic(10),
        mortum_soic(11),
        arcana_soic(12),
        vethea_soic(13),
        eden_qfp(14),
        wildwood_qfp(15),
        apalachia_qfp(16),
        skythern_qfp(17),
        mortum_qfp(18),
        arcana_qfp(19),
        vethea_qfp(20),
        eden_circuit(21),
        wildwood_circuit(22),
        apalachia_circuit(23),
        skythern_circuit(24),
        mortum_circuit(25),
        arcana_circuit(26),
        vethea_circuit(27),
        eden_core(28),
        wildwood_core(29),
        apalachia_core(30),
        skythern_core(31),
        mortum_core(32),
        arcana_core(33),
        vethea_core(34),
        crossdimensional_core(35);

        private final String name;
        private final int ID;

        DivItemsTypes(final int ID) {
            this.name = this.name().toLowerCase(Locale.US);
            this.ID = ID;
        }

        public static DivItemsTypes getFromID(final int ID) {
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
