package com.aeternal.iuadditions.integration.forestry.items;

import com.aeternal.iuadditions.Constants;
import com.aeternal.iuadditions.Core;
import com.aeternal.iuadditions.items.resource.IUAItemSubTypes;
import com.aeternal.iuadditions.register.ItemHandler;
import com.denfop.api.IModelRegister;
import com.denfop.blocks.ISubEnum;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;

public class CompressedHoneyComb extends IUAItemSubTypes<CompressedHoneyComb.CompressedHoneyCombTypes> implements IModelRegister {

    protected static final String NAME = "compressed_honeycomb";

    public CompressedHoneyComb() {
        super(CompressedHoneyCombTypes.class);
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
                        Constants.MOD_ID + ":" + NAME + "_" + CompressedHoneyComb.CompressedHoneyCombTypes.getFromID(meta).getName(),
                        null
                )
        );
    }

    public enum CompressedHoneyCombTypes implements ISubEnum {
        shimmering(0),
        radioactive(1),
        venomous(2),
        certus(3),
        statics(4),
        dripping(5);

        private final String name;
        private final int ID;

        CompressedHoneyCombTypes(final int ID) {
            this.name = this.name().toLowerCase(Locale.US);
            this.ID = ID;
        }

        public static CompressedHoneyCombTypes getFromID(final int ID) {
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
